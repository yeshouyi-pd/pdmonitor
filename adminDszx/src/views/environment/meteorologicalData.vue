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
          <h4 class="widget-title">气象数据查询</h4>
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
                    <select v-model="meteorologicalDataDto.bz" class="form-control">
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
<!--                    <button type="button" v-on:click="exportExcle()" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">-->
<!--                      <i class="ace-icon fa fa-leaf"></i>-->
<!--                      导出-->
<!--                    </button>-->
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
            <th>风速(m/s)</th>
            <th>风向(度)</th>
            <th>温度(摄氏度)</th>
            <th>湿度(度)</th>
            <th>大气压力(hPa)</th>
            <th>分钟雨量(mm)</th>
            <th>小时雨量(mm)</th>
            <th>24小时雨量(mm)</th>
            <th>测量雨量累加值(mm)</th>
            <th>太阳光强度(W/m2)</th>
            <th>短波辐射</th>
            <th>长波辐射</th>
            <th>东向风速</th>
            <th>北向风速</th>
            <th>采集时间</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="meteorologicalData in meteorologicalDatas">
            <td>{{zdysbList|optionKVArray(meteorologicalData.bz)}}</td>
            <td>{{meteorologicalData.speed}}</td>
            <td>{{meteorologicalData.winddirection}}</td>
            <td>{{meteorologicalData.temperature}}</td>
            <td>{{meteorologicalData.humidity}}</td>
            <td>{{meteorologicalData.pressure}}</td>
            <td>{{meteorologicalData.minuterainfall}}</td>
            <td>{{meteorologicalData.hourrainfall}}</td>
            <td>{{meteorologicalData.dayrainfall}}</td>
            <td>{{meteorologicalData.rainfallaccumulation}}</td>
            <td>{{meteorologicalData.solarintensity}}</td>
            <td>{{meteorologicalData.shortwave}}</td>
            <td>{{meteorologicalData.longwave}}</td>
            <td>{{meteorologicalData.uwindSpeed}}</td>
            <td>{{meteorologicalData.vwindSpeed}}</td>
            <td>{{meteorologicalData.cjsj}}</td>
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
                       start-id="meteorologicalTbStartId"
                       end-id="meteorologicalTbEndId"
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
        <div id="echartMeteorological"  style='height: 500px;width: 100%;'></div>
      </div>
    </div>

  </div>
</template>
<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/times";
  export default {
    components: {Pagination, Times},
    name: "meteorological-data",
    data: function() {
      return {
        meteorologicalDatas:[],
        meteorologicalData:{},
        meteorologicalDataDto:{},
        defaultShow:true,
        etime:'',
        stime:'',
        cursbbh:'RPCDA4000',
        zdysbList:[
          {key:"RPCDA4001", value:"8号航标"},
          {key:"RPCDA4002", value:"珠海A4002"},
          {key:"RPCDA4003", value:"5号航标"},
          {key:"RPCDA4004", value:"2号航标"},
          {key:"RPCDA4005", value:"3号航标"},
          {key:"RPCDA4006", value:"6号航标"},
          {key:"RPCDA4007", value:"9号航标"},
          {key:"RPCDA4008", value:"11号航标"},
          {key:"RPCDA4009", value:"7号航标"},
          {key:"RPCDA4010", value:"10号航标"},
          {key:"RPCDA4011", value:"12号航标"},
          {key:"RPCDA4012", value:"4号航标"},
          {key:"RPCDA4013", value:"1号航标"},
          {key:"RPCDA4014", value:"14号航标"},
          {key:"RPCDA4015", value:"13号航标"},
          {key:"RPCDA4016", value:"16号航标"},
          {key:"RPCDA4006-4", value:"平台4"},
          {key:"RPCDA4009-3", value:"平台3"},
          {key:"RPCDA4000", value:"保护区楼顶气象站"}
        ]
      }
    },
    mounted() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
      _this.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
    },
    methods: {
      exportExcle(){
        let _this = this;
        let param = "";
        if(!Tool.isEmpty(_this.meteorologicalDataDto.bz)){
          param+="&bz="+_this.meteorologicalDataDto.bz;
        }
        if(!Tool.isEmpty(_this.meteorologicalDataDto.stime)){
          param+="&stime="+_this.meteorologicalDataDto.stime;
        }
        if(!Tool.isEmpty(_this.meteorologicalDataDto.etime)){
          param+="&etime="+_this.meteorologicalDataDto.etime;
        }
        if(Tool.isEmpty(param)){
          window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportMeteorologicalData';
        }else{
          window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportMeteorologicalData?'+param.substring(1,param.length);
        }
      },
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
        obj.stime = _this.stime;
        obj.etime = _this.etime;
        obj.bz = _this.cursbbh;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/meteorologicalData/getAllDataByTime',obj).then((response)=>{
          Loading.hide();
          let resp = response.data;
          let xAxisDatas = [];
          let seriesData1 = [];
          let seriesData2 = [];
          let seriesData3 = [];
          let seriesData4 = [];
          let seriesData5 = [];
          let seriesData6 = [];
          let seriesData7 = [];
          let seriesData8 = [];
          let seriesData9 = [];
          let seriesData10 = [];
          for(let i=0;i<resp.content.length;i++){
            let meteorologicalData = resp.content[i];
            xAxisDatas.push(meteorologicalData.cjsj);
            seriesData1.push(meteorologicalData.speed);
            seriesData2.push(meteorologicalData.winddirection);
            seriesData3.push(meteorologicalData.temperature);
            seriesData4.push(meteorologicalData.humidity);
            seriesData5.push(meteorologicalData.pressure);
            seriesData6.push(meteorologicalData.minuterainfall);
            seriesData7.push(meteorologicalData.hourrainfall);
            seriesData8.push(meteorologicalData.dayrainfall);
            seriesData9.push(meteorologicalData.rainfallaccumulation);
            seriesData10.push(meteorologicalData.solarintensity);
          }
          _this.$nextTick(function (){
            _this.initEchartData(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7,seriesData8,seriesData9,seriesData10);
          })
        })
      },
      initEchartData(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7,seriesData8,seriesData9,seriesData10){
        let option = {
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ["风速","风向","温度","湿度","大气压力","分钟雨量","小时雨量","24小时雨量","观测雨量累加值","太阳光强度"]
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
              name: '风速',
              type: 'line',
              data: seriesData1
            },
            {
              name: '风向',
              type: 'line',
              data: seriesData2
            },
            {
              name: '温度',
              type: 'line',
              data: seriesData3
            },
            {
              name: '湿度',
              type: 'line',
              data: seriesData4
            },
            {
              name: '大气压力',
              type: 'line',
              data: seriesData5
            },
            {
              name: '分钟雨量',
              type: 'line',
              data: seriesData6
            },
            {
              name: '小时雨量',
              type: 'line',
              data: seriesData7
            },
            {
              name: '24小时雨量',
              type: 'line',
              data: seriesData8
            },
            {
              name: '观测雨量累加值',
              type: 'line',
              data: seriesData9
            },
            {
              name: '太阳光强度',
              type: 'line',
              data: seriesData10
            }
          ]
        };
        let echartsData = echarts.init(document.getElementById("echartMeteorological"));
        echartsData.setOption(option);
      },
      list(page){
        let _this = this;
        Loading.show();
        _this.meteorologicalDataDto.page = page;
        _this.meteorologicalDataDto.size = _this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/meteorologicalData/list', _this.meteorologicalDataDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.meteorologicalDatas = resp.content.list;
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
        _this.meteorologicalDataDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.meteorologicalDataDto.etime = rep;
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