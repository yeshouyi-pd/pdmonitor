<template>
  <div>
    <div class="content container-fluid">
      <div class="row">
        <div class="col-xl-3 col-sm-3 col-12">
          <div class="card">
            <div class="card-body" style="border: 1px solid #FFEEC3;">
              <div class="card-num" style="background-color: #FFEEC3">{{ todayListStr.sumjdcyysl }}</div>
              <div class="card-text">机动业务预约数量</div>
            </div>
          </div>
        </div>
        <div class="col-xl-3 col-sm-3 col-12">
          <div class="card">
            <div class="card-body" style="border: 1px solid #C3E3EE;">
              <div class="card-num" style="background-color: #C3E3EE">{{ todayListStr.sumjszyysl }}</div>
              <div class="card-text">驾驶业务预约数量</div>
            </div>
          </div>
        </div>
        <div class="col-xl-3 col-sm-3 col-12">
          <div class="card">
            <div class="card-body" style="border: 1px solid #CEFFD5;">
              <div class="card-num" style="background-color: #CEFFD5">{{ todayListStr.sumwfyysl }}</div>
              <div class="card-text">违法业务预约数量</div>
            </div>
          </div>
        </div>
        <div class="col-xl-3 col-sm-3 col-12">
          <div class="card">
            <div class="card-body" style="border: 1px solid #FCCAFF;">
              <div class="card-num" style="background-color: #FCCAFF">0</div>
              <div class="card-text">查验业务预约数量</div>
            </div>
          </div>
        </div>
      </div>
      <div class="statistics-tb-body">
        <div class="statistics-tb-div">
          <div class="statistics-tb-content" style="margin: 20px 10px 20px 0px;">
            <div class="tb-title-box">
              <div>未来15天之内的预约数</div>
            </div>
            <div id="echarts1" style="width: 100%;height:390px;margin-top: 10px"></div>
          </div>
        </div>
        <div class="statistics-tb-div">
          <div class="statistics-tb-content" style="margin: 20px 0px;">
            <div class="tb-change-tab-box">
              <div :class="{'tab-ny-change':isActive}" v-on:click="changeTab()" style="border-left: 1px solid #ccc;">本月预约数</div>
              <div :class="{'tab-ny-change':!isActive}" v-on:click="changeTab()">本年预约数</div>
            </div>
            <div id="echarts21" style="width: 100%;height:390px;margin-top: 10px" v-if="showYysl"></div>
            <div id="echarts22" style="width: 100%;height:390px;margin-top: 10px" v-if="!showYysl"></div>
          </div>
        </div>
        <div class="statistics-tb-div">
          <div class="statistics-tb-content" style="margin: 0px 10px 20px 0px;">
            <div class="tb-change-tab-bigbox">
              <div class="tb-change-tab-box">
                <div :class="{'tab-ny-change':isActiveGrOrDw}" v-on:click="changeGrOrDwTab()" style="border-left: 1px solid #ccc;">个人</div>
                <div :class="{'tab-ny-change':!isActiveGrOrDw}" v-on:click="changeGrOrDwTab()">单位</div>
              </div>
              <div class="tb-change-tab-box">
                <div :class="{'tab-ny-change':isActive2}" v-on:click="changeTab2()" style="border-left: 1px solid #ccc;">本月预约数</div>
                <div :class="{'tab-ny-change':!isActive2}" v-on:click="changeTab2()">本年预约数</div>
              </div>
            </div>
            <div style="width: 95%;height:350px;margin: 0px auto;overflow-y: auto">
              <table id="simple-table" class="table  table-bordered table-hover">
                <thead><tr><th>预约部门</th><th>预约数量</th></tr></thead>
                <tbody>
                <tr v-for="wxYy in wxYys">
                  <td>{{maps|optionMapKV(wxYy.deptcode)}}</td><td>{{wxYy.count}}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="statistics-tb-div">
          <div class="statistics-tb-content" style="margin: 0px 0px 20px 0px;">
            <div class="tb-change-tab-box" style="margin-bottom: 20px;">
              <div :class="{'tab-ny-change':isActive3}" v-on:click="changeWfccTab()" style="border-left: 1px solid #ccc;">本月出场数</div>
              <div :class="{'tab-ny-change':!isActive3}" v-on:click="changeWfccTab()">本年出场数</div>
            </div>
            <div style="width: 95%;height:350px;margin: 0px auto;overflow-y: auto">
              <table id="simple-table1" class="table  table-bordered table-hover">
                <thead><tr><th>停车场</th><th>出场数量</th></tr></thead>
                <tbody>
                <tr v-for="wfcc in wfccs">
                  <td>{{maps|optionMapKV(wfcc.syscode)}}</td><td>{{wfcc.count}}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  name:'welcome',
  data: function (){
    return {
      todayListStr:new Object(),
      yfListStr:new Object(),
      nfListStr:new Object(),
      showYysl:true,
      isActive:true,
      maps:[],
      wxYys:[],
      nfDwList:[],
      nfGrList:[],
      yfDwList:[],
      yfGrList:[],
      isActiveGrOrDw:true,
      isActive2:true,
      isActive3:true,
      wfccs:[],
      nfCcsl:[],
      yfCcsl:[]
    }
  },
  mounted:function(){
    let _this = this;
    _this.maps = Tool.getDeptUser();
    _this.getYyslByDay();
    _this.getWlqtYysl();
    _this.getYyslByBeforeTen();
    _this.getWfccslByDay();
  },
  methods:{
    getYyslByDay(){
      let _this =this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/wxYy/getYyslByDay').then((res)=> {
        let resp = res.data.content;
        _this.todayListStr = resp.today;
        _this.yfListStr = resp.yf;
        _this.nfListStr = resp.nf;
        _this.initEcharts2ByYf(_this.yfListStr);
      })
    },
    changeTab(){
      let _this = this;
      _this.isActive = !_this.isActive;
      _this.showYysl = !_this.showYysl;
      if(!_this.showYysl){
        _this.$nextTick(function(){
          _this.initEcharts2ByNf(_this.nfListStr);
        });
      }else{
        _this.$nextTick(function(){
          _this.initEcharts2ByYf(_this.yfListStr);
        });
      }
    },
    initEcharts2ByYf(data){
      let _this = this;
      let myChart = echarts.init(document.getElementById('echarts21'));
      myChart.setOption(_this.initOption2(data));
    },
    initEcharts2ByNf(data){
      let _this = this;
      let myChart = echarts.init(document.getElementById('echarts22'));
      myChart.setOption(_this.initOption2(data));
    },
    initOption2(data){
      let seriesData = [];
      seriesData.push(data.sumjdcyysl);
      seriesData.push(data.sumjszyysl);
      seriesData.push(data.sumwfyysl);
      seriesData.push(0);
      let option = {
        xAxis: {
          type: 'category',
          data: ['机动车业务', '车驾管业务', '违法业务', '查验业务'],
          axisLine: {
            symbol: ['none', 'arrow']
          }
        },
        yAxis: {
          name: '预约数',
          type: 'value',
          axisLine: {
            show: true,
            symbol: ['none', 'arrow']
          },
          boundaryGap: ['0%', '20%']
        },
        series: [{
          data: seriesData,
          type: 'bar'
        }]
      };
      return option;
    },
    getWlqtYysl(){
      let _this =this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/wxYy/getWlqtYysl').then((res)=> {
        let resp = res.data.content;
        _this.initEcharts1(resp);
      })
    },
    initEcharts1(data){
      let xAxisData = [];
      let seriesData = [];
      if(data && data.length>0){
        for(let i=0;i<data.length;i++){
          xAxisData.push(data[i].yysj);
          seriesData.push(data[i].count);
        }
      }
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: xAxisData,
          axisLine: {
            show: true,
            symbol: ['none', 'arrow']
          },
          axisTick: {
            show: true
          }
        },
        yAxis: {
          name: '预约数',
          type: 'value',
          minInterval: 1,
          axisLine: {
            show: true,
            symbol: ['none', 'arrow']
          },
          boundaryGap: ['0%', '20%']
        },
        series: [{
          data: seriesData,
          type: 'line'
        }]
      };
      let myChart = echarts.init(document.getElementById('echarts1'));
      myChart.setOption(option);
    },
    getYyslByBeforeTen(){
      let _this =this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/wxYy/getYyslByBeforeTen').then((res)=> {
        let resp = res.data.content;
        _this.nfGrList = resp.nfGrList;
        _this.nfDwList = resp.nfDwList;
        _this.yfGrList = resp.yfGrList;
        _this.yfDwList = resp.yfDwList;
        _this.wxYys = resp.yfGrList;
      })
    },
    changeGrOrDwTab(){
      let _this = this;
      _this.isActiveGrOrDw = !_this.isActiveGrOrDw;
      _this.changeList();
    },
    changeTab2(){
      let _this = this;
      _this.isActive2 = !_this.isActive2;
      _this.changeList();
    },
    changeList(){
      let _this = this;
      if(_this.isActiveGrOrDw && _this.isActive2){
        _this.wxYys = _this.yfGrList;
      }else if(_this.isActiveGrOrDw && !_this.isActive2){
        _this.wxYys = _this.nfGrList;
      }else if(!_this.isActiveGrOrDw && _this.isActive2){
        _this.wxYys = _this.yfDwList;
      }else if(!_this.isActiveGrOrDw && !_this.isActive2){
        _this.wxYys = _this.nfDwList;
      }
    },
    getWfccslByDay(){
      let _this =this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/vehParkLot/getWfccslByDay').then((res)=> {
        let resp = res.data.content;
        _this.yfCcsl = resp.yfCcsl;
        _this.nfCcsl = resp.nfCcsl;
        _this.wfccs = resp.yfCcsl;
      })
    },
    changeWfccTab(){
      let _this =this;
      _this.isActive3 = !_this.isActive3;
      if(_this.isActive3){
        _this.wfccs = _this.yfCcsl;
      }else{
        _this.wfccs = _this.nfCcsl;
      }
    }
  }
}
</script>
<style scoped>
.card{

}
.card-body{
  display: flex;
  width: 98%;
  height: 80px;
  border-radius: 10px;
}
.card-num{
  height: 60px;
  line-height: 60px;
  margin-top: 10px;
  width: 16%;
  text-align: center;
  border-radius: 10px;
  margin-left: 5px;
  font-size: 22px;
}
.card-text{
  line-height: 80px;
  margin-left: 10px;
  font-size: 18px;
  font-weight: 600;
}
.statistics-tb-body{
  display: flex;
  width: 100%;
  flex-wrap: wrap;
}
.statistics-tb-div{
  width: 50%;
}
.statistics-tb-content{
  height: 400px;
  border: 1px solid #ccc;
  border-radius: 10px;
}
.tb-title-box{
  margin-top: 10px;
  text-align: center;
  font-size: 18px;
}
.tab-ny-change{
  border-top: 2px solid #4C8FBD;
}
.tb-change-tab-box{
  margin: 10px 10px 0 0;
  display: flex;
  justify-content: flex-end;
}
.tb-change-tab-box>div{
  padding: 5px;
  border-bottom: 1px solid #ccc;
  border-right: 1px solid #ccc;
  cursor:pointer
}
.tb-change-tab-box>div:not(.tab-ny-change){
  border-top: 1px solid #ccc;
}
.table {
  width: 100%;
  max-width: 100%;
   margin-bottom: 0px;
}
.tb-change-tab-bigbox{
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}
</style>