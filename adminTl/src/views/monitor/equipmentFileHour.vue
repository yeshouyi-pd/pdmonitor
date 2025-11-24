<template>
  <div style="display: flex">
    <div id="tree" class="ztree" :style="{height: heightMax + 'px', overflowY: 'auto', width:'20%', border:'1px solid #ccc'}"></div>
    <div :style="{height: heightMax + 'px',overflowY: 'auto', width:'78%', border: '1px solid #ccc', marginLeft: '1%'}">
      <div>
        <div id="echartOneDay"  style='height: 400px;'></div>
      </div>
      <div>
        <div id="echartSevenDay"  style='height: 400px;'></div>
      </div>
      <div>
        <div class="widget-body">
          <div class="widget-main">
            <form>
              <table style="font-size: 1.1em;width:80%" class="text-right" >
                <tbody>
                <tr>
                  <td style="width: 15%;">
                    采集时间：
                  </td>
                  <td style="width: 35%;">
                    <times v-bind:startTime="startTime" v-bind:endTime="endTime" v-bind:svalue="alarmNumbersDto2.stime" v-bind:evalue="alarmNumbersDto2.etime" start-id="fileHourStart" end-id="fileHourEnd"></times>
                  </td>
                  <td colspan="2" class="text-center">
                    <button type="button" v-on:click="getChangeMonthData()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                      <i class="ace-icon fa fa-book"></i>
                      查询
                    </button>
                    <button type="button" v-on:click="reloadPage()"   class="btn btn-sm btn-success btn-round" style="margin-right: 10px;">
                      <i class="ace-icon fa fa-refresh"></i>
                      重置
                    </button>
                    <button type="button" v-on:click="exportFile()"   class="btn btn-sm btn-success btn-round">
                      <i class="ace-icon fa fa-book"></i>
                      导出
                    </button>
                  </td>
                </tr>
                </tbody>
              </table>
            </form>
          </div>
        </div>
        <div id="echartOneMonth"  style='height: 400px;'></div>
      </div>
    </div>
  </div>
</template>
<script>
import Times from "../../components/times";
export default {
  components: {Times},
  name: 'equipment-file-hour',
  data: function (){
    return {
      trees:[],
      heightMax:'',
      curDate:'',
      alarmNumbersDto:{},
      alarmNumbersDto1:{},
      alarmNumbersDto2:{}
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.heightMax = h*0.8-35;
    _this.curDate = new Date();
    _this.findMonitorEquipmentTreeByFile();
    _this.alarmNumbersDto2.stime=Tool.dateFormat("yyyy-MM-dd",new Date(_this.curDate.getTime()-3600000*24*29));
    _this.alarmNumbersDto2.etime=Tool.dateFormat("yyyy-MM-dd",_this.curDate);
  },
  methods: {
    exportFile(){
      let _this = this;
      let paramsStr = "type=hour"+"&stime="+_this.alarmNumbersDto2.stime+"&etime="+_this.alarmNumbersDto2.etime+"&sbbh="+_this.curNode.code+"&deptcode="+Tool.getLoginUser().deptcode;
      let url = process.env.VUE_APP_SERVER + '/monitor/export/exportEquipmentFile?'+paramsStr;
      window.location.href = url;
    },
    reloadPage(){
      let _this = this;
      _this.alarmNumbersDto2.stime=Tool.dateFormat("yyyy-MM-dd",new Date(_this.curDate.getTime()-3600000*24*29));
      _this.alarmNumbersDto2.etime=Tool.dateFormat("yyyy-MM-dd",_this.curDate);
      _this.getChangeMonthData();
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.alarmNumbersDto2.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.alarmNumbersDto2.etime = rep;
      _this.$forceUpdate();
    },
    findMonitorEquipmentTreeByFile(){
      let _this = this;
      let url = "";
      if("460100"==Tool.getLoginUser().deptcode){
        url = process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findMonitorEquipmentTreeByFile';
      }else{
        url = process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findMonitorEquipmentTreeByFile/'+Tool.getLoginUser().xmbh;
      }
      _this.$ajax.get(url).then((res) => {
        let response = res.data;
        _this.trees = response.content;
        // 初始化树
        _this.initTree();
      })
    },
    initTree(){
      let _this = this;
      let setting = {
        data: {
          simpleData: {
            idKey: 'code'
          },
          key: {
            name: "name"
          }
        },
        view: {
          showIcon: false
        },
        callback: {
          onClick: _this.zTreeOnClick
        },
      };
      _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.trees);
      _this.zTree.expandAll(true);
      //获取第一个节点
      let node = _this.zTree.getNodes()[0].children[0];
      //选中第一个子节点
      _this.zTree.selectNode(node);
      setting.callback.onClick(null, _this.zTree.setting.treeId, node);
    },
    zTreeOnClick(event, treeId, treeNode){
      let _this = this;
      if(treeNode.type=='2'){
        _this.curNode = treeNode;
        _this.getOneDayData();
        _this.getSevenDayData();
        _this.getChangeMonthData();
      }
    },
    getOneDayData(){
      let _this = this;
      Loading.show();
      _this.alarmNumbersDto.stime=Tool.dateFormat("yyyy-MM-dd",_this.curDate);
      _this.alarmNumbersDto.etime=Tool.dateFormat("yyyy-MM-dd",_this.curDate);
      _this.alarmNumbersDto.sbbh=_this.curNode.code;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/statisticsAlarmNumsByHour',_this.alarmNumbersDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.initEchartData('今天(单位：小时)',resp.content,"echartOneDay");
      })
    },
    getSevenDayData(){
      let _this = this;
      Loading.show();
      _this.alarmNumbersDto1.stime=Tool.dateFormat("yyyy-MM-dd",new Date(_this.curDate.getTime()-3600000*24*6));
      _this.alarmNumbersDto1.etime=Tool.dateFormat("yyyy-MM-dd",_this.curDate);
      _this.alarmNumbersDto1.sbbh=_this.curNode.code;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/statisticsAlarmNumsByHour',_this.alarmNumbersDto1).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.initEchartData('过去7天(单位：小时)',resp.content,"echartSevenDay");
      })
    },
    getChangeMonthData(){
      let _this = this;
      Loading.show();
      _this.alarmNumbersDto2.sbbh=_this.curNode.code;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/statisticsAlarmNumsByHour',_this.alarmNumbersDto2).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.initEchartData('过去30天(单位：小时)',resp.content,"echartOneMonth");
      })
    },
    initEchartData(xAxisName,data,id){
      let _this = this;
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          bottom: '30%'
        },
        dataZoom:[{
          id: 'dataZoomX',
          type: 'slider',
          xAxisIndex: [0],
          top:'85%'
        }],
        xAxis: {
          show: true,
          name: xAxisName,
          nameTextStyle: {
            fontWeight: "bolder",
            fontSize: 20
          },
          nameLocation:'center',
          type: 'category',
          nameGap: 40,
          boundaryGap: true,
          data:data.xAixsData,
          axisLine:{
            show: true,
            symbol: ['none', 'path://M5,20 L5,5 L8,8 L5,2 L2,8 L5,5 L5.3,6 L5.3,20 '],
            symbolOffset: 10,
            symbolSize:[35, 35]
          },
          axisLabel: {
            show: true,
            width: 100,
            overflow: 'break'
          }
        },
        yAxis: {
          show: true,
          name: "占总侦测事件百分比",
          type: 'value',
          nameGap: 30,
          boundaryGap: [0, '30%'],
          axisLine:{
            show: true,
            symbol: ['none', 'path://M5,20 L5,5 L8,8 L5,2 L2,8 L5,5 L5.3,6 L5.3,20 '],
            symbolOffset: 10,
            symbolSize:[35, 35]
          }
        },
        series: [
          {
            type: 'bar',
            smooth: 0.6,
            symbolSize: 8,
            lineStyle: {
              color: '#5470C6',
              width: 2
            },
            data: data.yAixsData
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById(id));
      echartsData.setOption(option);
    }
  }
}
</script>