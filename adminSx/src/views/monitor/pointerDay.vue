<template>
  <div>
<!--    <div id="echartsBox" style="display: flex;flex-wrap: wrap;"></div>-->
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">当日水下环境指针预警数据</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
                  <select v-model="pointerDayDto.sm" class="form-control">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td style="width:10%">
                  预警日期：
                </td>
                <td style="width: 20%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="dayStartId" end-id="dayEndId"></times>
                </td>
                <td style="width: 20%" class="text-center">
                  <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </a>
                  <button type="button" v-on:click="exportExcle()" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-leaf"></i>
                    导出
                  </button>
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
          <th>设备名称</th>
          <th>设备编号</th>
          <th>预警数值</th>
          <th>预警时间</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="pointerDay in pointerDays">
          <td>{{waterEquipments|optionNSArray(pointerDay.sm)}}</td>
          <td>{{pointerDay.sm}}</td>
          <td>{{pointerDay.decibelValue}}</td>
          <td>{{pointerDay.cjsj}}</td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>
  </div>
</template>
<script>
import Date from "../../components/date";
import Times from "../../components/times";
import Pagination from "../../components/pagination";
export default {
  name: 'pointer-day',
  components: {Date,Pagination,Times},
  data: function (){
    return {
      pointerDay:{},
      pointerDayDto:{},
      pointerDays:[],
      echartsDataList:[],
      waterEquipments:[]
    }
  },
  created(){
    let _this = this;
    _this.findDeviceInfo();
  },
  mounted() {
    let _this = this;
    // _this.pointerDayDto.cjsj = Tool.dateFormat("yyyy-MM-dd hh:mm:ss");
    // _this.$forceUpdate();
    // _this.listAll();
    _this.list(1);
    _this.$refs.pagination.size = 10;
  },
  methods: {
    exportExcle(){
      let _this = this;
      let param = "";
      if("460100"!=Tool.getLoginUser().deptcode){
        param+="&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(!Tool.isEmpty(_this.pointerDayDto.sm)){
        param+="&sbbh="+_this.pointerDayDto.sm;
      }
      if(!Tool.isEmpty(_this.pointerDayDto.stime)){
        param+="&stime="+_this.pointerDayDto.stime;
      }
      if(!Tool.isEmpty(_this.pointerDayDto.etime)){
        param+="&etime="+_this.pointerDayDto.etime;
      }
      if(Tool.isEmpty(param)){
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportPointerDay';
      }else{
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportPointerDay?'+param.substring(1,param.length);
      }
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.pointerDayDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.pointerDayDto.etime = rep;
      _this.$forceUpdate();
    },
    list(page){
      let _this = this;
      Loading.show();
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.pointerDayDto.xmbh = Tool.getLoginUser().xmbh;
      }
      _this.pointerDayDto.page = page;
      _this.pointerDayDto.size = _this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/pointerDay/list',_this.pointerDayDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.pointerDays = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001'};
      }else{
        data = {'sblb':'0001','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        _this.$forceUpdate();
      })
    },
    optionMapKV(list, key){
      if (!list || !key) {
        return "";
      } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
          if (key === list[i]["sbsn"]) {
            result = list[i]["sbmc"];
          }
        }
        return result;
      }
    },
    listAll(){
      let _this = this;
      Loading.show();
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.pointerDayDto.xmbh = Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/pointerDay/listAll', _this.pointerDayDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.echartsDataList = resp.content;
        _this.initEcharts();
      })
    },
    initEcharts(){
      let _this = this;
      let echartsBox = document.getElementById("echartsBox");
      for(let i=0;i<_this.echartsDataList.length;i++){
        let obj = _this.echartsDataList[i];
        let div = document.createElement('div');
        div.style.width = "500px";
        div.style.height = "500px";
        let gaugeChart = echarts.init(div);
        let option = _this.initOption(obj.decibelValue,obj.cjsj,obj.sm);
        gaugeChart.setOption(option);
        echartsBox.append(div);
      }
    },
    initOption(value,time,sm){
      let _this = this;
      let option = {
        series: [
          {
            type: 'gauge',
            startAngle: 180,
            endAngle: 0,
            min: 120,
            max: 220,
            axisLine: {
              lineStyle: {
                width: 30,
                color: [
                  [0.6, '#327662'],
                  [0.8, '#F7BA0B'],
                  [1, '#701F29']
                ]
              }
            },
            pointer: {
              itemStyle: {
                color: 'auto'
              }
            },
            axisTick: {
              distance: -30,
              length: 8,
              lineStyle: {
                color: '#fff',
                width: 2
              }
            },
            splitLine: {
              distance: -30,
              length: 30,
              lineStyle: {
                color: '#fff',
                width: 4
              }
            },
            axisLabel: {
              color: 'auto',
              distance: 40,
              fontSize: 20
            },
            detail: {
              valueAnimation: true,
              formatter: '{value} dB\n'+time+'\n'+_this.optionMapKV(_this.waterEquipments,sm),
              color: 'auto'
            },
            data: [
              {
                value: value
              }
            ]
          }
        ]
      };
      return option;
    }
  }
}
</script>