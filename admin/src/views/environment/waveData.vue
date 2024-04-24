<template>
  <div>

    <div style="margin-bottom: 20px;">
      <ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
        <li class="active" v-on:click="changeTab()">
          <a data-toggle="tab" href="#faq-tab-1" aria-expanded="true">
            列表模式
          </a>
        </li>
        <li class="" v-on:click="changeTab()">
          <a data-toggle="tab" href="#faq-tab-2" aria-expanded="false">
            图形模式
          </a>
        </li>
      </ul>
    </div>

    <div v-show="defaultShow">
      <!-- query start -->
      <div class="widget-box">
        <div class="widget-header">
          <h4 class="widget-title">波浪数据查询</h4>
          <div class="widget-toolbar">
            <a href="#" data-action="collapse">
              <i class="ace-icon fa fa-chevron-up"></i>
            </a>
          </div>
        </div>
        <div class="widget-body">
          <div class="widget-main">
            <form >
              <table  style="font-size: 1.1em;" class="text-right  " >
                <tbody  >
                <tr  >
                  <td style="width: 15%" >
                    所在位置：
                  </td>
                  <td style="width: 25%;">
                    <select v-model="waveDataDto.sbbh" class="form-control">
                      <option value="" selected>请选择</option>
                      <option v-for="(item,index) in zdysbList" :value="item.key">{{item.value}}</option>
                    </select>
                  </td>
                  <td style="width: 15%" >
                    采集日期：
                  </td>
                  <td style="width: 25%;">
                    <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="eqstime" end-id="eqetime"></times>
                  </td>
                  <td style="width: 20%">
                    <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                      <i class="ace-icon fa fa-book"></i>
                      查询
                    </button>
                    &nbsp;&nbsp;&nbsp;
                    <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round">
                      <i class="ace-icon fa fa-refresh"></i>
                      重置
                    </a>
                  </td>
                </tr>
                </tbody>
              </table>
            </form>
          </div>
        </div>
      </div>
      <div>
        <table id="simple-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>所在位置</th>
            <th>有效波高(m)</th>
            <th>波向(°)</th>
            <th>波周期</th>
            <th>采集时间</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="waveData in waveDatas">
            <td>{{zdysbList|optionKVArray(waveData.sbbh)}}</td>
            <td>{{waveData.waveH}}</td>
            <td>{{waveData.waveDirection}}</td>
            <td>{{waveData.wavePeriod}}</td>
            <td>{{waveData.cjsj}}</td>
          </tr>
          </tbody>
        </table>
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
      </div>
    </div>

    <div v-show="!defaultShow">
      <div>
        <table style="font-size: 1.1em;width:100%;margin: 20px 0" class="text-right" >
          <tbody>
            <tr>
              <td style="width: 15%" >
                所在位置：
              </td>
              <td style="width: 25%;">
                <select v-model="cursbbh" class="form-control">
                  <option v-for="(item,index) in zdysbList" :value="item.key">{{item.value}}</option>
                </select>
              </td>
              <td style="width:10%">
                采集日期：
              </td>
              <td style="width: 20%">
                <times v-bind:startTime="startTimeTb"
                       v-bind:endTime="endTimeTb"
                       start-id="waveDataTbStartId"
                       end-id="waveDataTbEndId"
                       v-bind:evalue="etime"
                       v-bind:svalue="stime"></times>
              </td>
              <td style="width: 20%;" class="text-center">
                <button type="button" v-on:click="getAllDataByTime()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                  <i class="ace-icon fa fa-book"></i>
                  查询
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        <div id="echartwaveData"  style='height: 500px;'></div>
      </div>
    </div>

  </div>
</template>
<script>
import Pagination from "../../components/pagination";
import Times from "../../components/times";
export default {
  components: {Pagination, Times},
  name: "waveData",
  data: function() {
    return {
      waveDatas:[],
      waveData:{},
      waveDataDto:{},
      defaultShow:true,
      etime:'',
      stime:'',
      cursbbh:'RPCDA4016',
      zdysbList:[
        {key:"RPCDA4005", value:"3号航标"},
        {key:"RPCDA4012", value:"4号航标"},
        {key:"RPCDA4003", value:"5号航标"},
        {key:"RPCDA4006-4", value:"平台4"},
        {key:"RPCDA4009-3", value:"平台3"},
        {key:"RPCDA4001", value:"8号航标"},
        {key:"RPCDA4010", value:"10号航标"},
        {key:"RPCDA4008", value:"11号航标"},
        {key:"RPCDA4002", value:"淇澳岛"},
        {key:"RPCDA4016", value:"16号航标"}
      ]
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
  },
  methods: {
    changeTab(){
      let _this = this;
      _this.defaultShow = !_this.defaultShow;
      if(!_this.defaultShow){
        _this.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
        _this.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
        _this.getAllDataByTime();
      }
    },
    getAllDataByTime(){
      let _this = this;
      Loading.show();
      let obj = {};
      obj.sbbh = _this.cursbbh;
      obj.stime = _this.stime;
      obj.etime = _this.etime;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waveData/getAllDataByTime',obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        let xAxisDatas = [];
        let seriesData1 = [];
        let seriesData2 = [];
        let seriesData3 = [];
        for(let i=0;i<resp.content.length;i++){
          let waveData = resp.content[i];
          xAxisDatas.push(waveData.cjsj);
          seriesData1.push(waveData.waveH);
          seriesData2.push(waveData.waveDirection);
          seriesData3.push(waveData.wavePeriod);
        }
        _this.$nextTick(function (){
          _this.initEchartData(xAxisDatas,seriesData1,seriesData2,seriesData3);
        })
      })
    },
    initEchartData(xAxisDatas,seriesData1,seriesData2,seriesData3){
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ["有效波高(m)","波向(°)","波周期"]
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: xAxisDatas
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '有效波高(m)',
            type: 'line',
            data: seriesData1
          },
          {
            name: '波向(°)',
            type: 'line',
            data: seriesData2
          },
          {
            name: '波周期',
            type: 'line',
            data: seriesData3
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("echartwaveData"));
      echartsData.setOption(option);
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.waveDataDto.page = page;
      _this.waveDataDto.size = _this.$refs.pagination.size;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waveData/list', _this.waveDataDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.waveDatas = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);

      })
    },
    /**
     *开始时间
     */
    startTimeTb(rep){
      let _this = this;
      _this.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTimeTb(rep){
      let _this = this;
      _this.etime = rep;
      _this.$forceUpdate();
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.waveDataDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.waveDataDto.etime = rep;
      _this.$forceUpdate();
    }
  }
}
</script>
<style scoped>
.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover{
  background-color: #fff;
  color: #576373;
  border-top: 2px solid #4C8FBD;
}
.nav-tabs>li>a, .nav-tabs>li>a:focus{
  background-color: #fff;
}
</style>