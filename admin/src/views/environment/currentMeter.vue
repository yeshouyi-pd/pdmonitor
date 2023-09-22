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
          <h4 class="widget-title">海流计数据查询</h4>
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
            <th>产品编号</th>
            <th>序列号</th>
            <th>abs速度</th>
            <th>方向</th>
            <th>北</th>
            <th>东</th>
            <th>朝向</th>
            <th>倾斜度X</th>
            <th>倾斜度Y</th>
            <th>Sp标准</th>
            <th>力度</th>
            <th>平计数</th>
            <th>Abs倾斜度</th>
            <th>最大倾斜度</th>
            <th>标准倾斜度</th>
            <th>采集时间</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="currentMeter in currentMeters">
            <td>{{currentMeter.productNumber}}</td>
            <td>{{currentMeter.serialNumber}}</td>
            <td>{{currentMeter.absSpeed}}</td>
            <td>{{currentMeter.direction}}</td>
            <td>{{currentMeter.north}}</td>
            <td>{{currentMeter.east}}</td>
            <td>{{currentMeter.heading}}</td>
            <td>{{currentMeter.tiltX}}</td>
            <td>{{currentMeter.tiltY}}</td>
            <td>{{currentMeter.spStd}}</td>
            <td>{{currentMeter.strength}}</td>
            <td>{{currentMeter.pingCount}}</td>
            <td>{{currentMeter.absTilt}}</td>
            <td>{{currentMeter.maxTilt}}</td>
            <td>{{currentMeter.stdTilt}}</td>
            <td>{{currentMeter.cjsj}}</td>
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
            <td style="width:10%">
              采集日期：
            </td>
            <td style="width: 20%">
              <times v-bind:startTime="startTimeTb"
                     v-bind:endTime="endTimeTb"
                     start-id="currentMeterTbStartId"
                     end-id="currentMeterTbEndId"
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
        <div id="echartCurrentMeter"  style='height: 500px;'></div>
      </div>
    </div>

  </div>
</template>
<script>
import Pagination from "../../components/pagination";
import Times from "../../components/times";
export default {
  components: {Pagination, Times},
  name: "current-meter",
  data: function() {
    return {
      currentMeters:[],
      currentMeter:{},
      currentMeterDto:{},
      defaultShow:true,
      etime:'',
      stime:''
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
      obj.stime = _this.stime;
      obj.etime = _this.etime;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/currentMeter/getAllDataByTime',obj).then((response)=>{
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
        for(let i=0;i<resp.content.length;i++){
          let currentMeter = resp.content[i];
          xAxisDatas.push(currentMeter.cjsj);
          seriesData1.push(currentMeter.absSpeed);
          seriesData2.push(currentMeter.tiltX);
          seriesData3.push(currentMeter.tiltY);
          seriesData4.push(currentMeter.spStd);
          seriesData5.push(currentMeter.strength);
          seriesData6.push(currentMeter.pingCount);
          seriesData7.push(currentMeter.absTilt);
          seriesData8.push(currentMeter.maxTilt);
          seriesData9.push(currentMeter.stdTilt);
        }
        _this.$nextTick(function (){
          _this.initEchartData(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7,seriesData8,seriesData9);
        })
      })
    },
    initEchartData(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7,seriesData8,seriesData9){
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ["abs速度","倾斜度X","倾斜度Y","Sp标准","力度","平计数","Abs倾斜度","最大倾斜度","标准倾斜度"]
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
            name: 'abs速度',
            type: 'line',
            data: seriesData1
          },
          {
            name: '倾斜度X',
            type: 'line',
            data: seriesData2
          },
          {
            name: '倾斜度Y',
            type: 'line',
            data: seriesData3
          },
          {
            name: 'Sp标准',
            type: 'line',
            data: seriesData4
          },
          {
            name: '力度',
            type: 'line',
            data: seriesData5
          },
          {
            name: '平计数',
            type: 'line',
            data: seriesData6
          },
          {
            name: 'Abs倾斜度',
            type: 'line',
            data: seriesData7
          },
          {
            name: '最大倾斜度',
            type: 'line',
            data: seriesData8
          },
          {
            name: '标准倾斜度',
            type: 'line',
            data: seriesData9
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("echartCurrentMeter"));
      echartsData.setOption(option);
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.currentMeterDto.page = page;
      _this.currentMeterDto.size = _this.$refs.pagination.size;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/currentMeter/list', _this.currentMeterDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.currentMeters = resp.content.list;
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
      _this.currentMeterDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.currentMeterDto.etime = rep;
      _this.$forceUpdate();
    }
  }
}
</script>