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
      <div class="row">
        <div class="col-md-12">
          <!-- query start -->
          <div class="widget-box">
            <div class="widget-header">
              <h4 class="widget-title">事件统计查询</h4>
              <div class="widget-toolbar">
                <a href="#" data-action="collapse">
                  <i class="ace-icon fa fa-chevron-up"></i>
                </a>
              </div>
            </div>
            <div class="widget-body">
              <div class="widget-main">
                <form>
                  <table style="font-size: 1.1em;width:80%" class="text-right" >
                    <tbody>
                    <tr>
                      <td style="width:10%">
                        事件日期：
                      </td>
                      <td style="width: 20%">
                        <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="alarmEventStartId" end-id="alarmEventEndId"></times>
                      </td>
                      <td style="width:10%">
                        设备sn：
                      </td>
                      <td style="width: 20%">
                        <input class="form-control" type="text"  v-model="alarmEventDto.sbbh"/>
                      </td>
                      <td colspan="2" class="text-center">
                        <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                          <i class="ace-icon fa fa-book"></i>
                          查询
                        </button>
                        <a href="javascript:location.replace(location.href);"  class="btn btn-sm btn-success btn-round" style="margin-right: 10px;">
                          <i class="ace-icon fa fa-refresh"></i>
                          重置
                        </a>
                        <button type="button" v-on:click="exportExcel()" class="btn btn-sm btn-info btn-round">
                          <i class="ace-icon fa fa-download"></i>
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
          <!-- query end -->
        </div><!-- col-md-12 -->
      </div><!-- row -->
      <div>
        <table id="simple-table" class="table  table-bordered table-hover">
          <thead>
          <tr>
            <th>所属监测点</th>
            <th>设备名称</th>
            <th>设备sn</th>
            <th>事件日期</th>
            <th>事件次数</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="alarmEvent in alarmEvents">
            <td>{{deptMap|optionMapKV(alarmEvent.deptcode)}}</td>
            <td>{{waterEquipments|optionNSArray(alarmEvent.sbbh)}}</td>
            <td>{{alarmEvent.sbbh}}</td>
            <td>{{alarmEvent.bjsj}}</td>
            <td>{{alarmEvent.counts}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="details(alarmEvent)" class="btn btn-xs btn-info" title="详情">
                  <i class="ace-icon fa fa-list bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
      </div>
    </div>
    <div  v-show="!defaultShow"  style="display: flex">
      <div id="treeAlarm" class="ztree" :style="{height: txheightMax + 'px', overflowY: 'auto', width:'20%', border:'1px solid #ccc'}"></div>
      <div :style="{height: txheightMax + 'px',overflowY: 'auto', width:'78%', border: '1px solid #ccc', marginLeft: '1%'}">
        <div>
          <table style="font-size: 1.1em;width:100%;margin: 20px 0" class="text-right" >
            <tbody>
            <tr>
              <td style="width:10%">
                事件日期：
              </td>
              <td style="width: 20%">
                <times v-bind:startTime="startTimeTb"
                       v-bind:endTime="endTimeTb"
                       start-id="alarmEventTbStartId"
                       end-id="alarmEventTbEndId"
                       v-bind:evalue="etime"
                       v-bind:svalue="stime"></times>
              </td>
              <td style="width: 15%;text-align: left;">(只能查询当天之前的数据，不包含当天)</td>
              <td style="width: 20%;" class="text-center">
                <button type="button" v-on:click="getAlarmEventData()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                  <i class="ace-icon fa fa-book"></i>
                  查询
                </button>
              </td>
            </tr>
            </tbody>
          </table>
          <div id="echartAlarmEvent"  style='height: 500px;'></div>
        </div>
      </div>
    </div>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">事件详情</h4>
          </div>
          <div class="modal-body" :style="{height: heightMax + 'px' ,overflowY: 'auto'}">
            <table class="table  table-bordered table-hover">
              <thead>
              <tr>
                <th>监测点</th>
                <th>设备名称</th>
                <th>设备sn</th>
                <th>事件发生时间</th>
                <th>事件次数</th>
                <th>出现次数</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in detailList">
                <td>{{deptMap|optionMapKV(item.deptcode)}}</td>
                <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
                <td>{{item.sbbh}}</td>
                <td>{{item.eventTime}}</td>
                <td>1</td>
                <td>{{item.alarmNum}}</td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>
</template>
<script>
import Pagination from "../../components/pagination";
import Times from "../../components/times";
export default {
  components: {Pagination,Times},
  name: "alarm-event",
  data: function (){
    return {
      alarmEvent:{},
      alarmEventDto:{},
      alarmEvents:[],
      deptMap:[],
      waterEquipments:[],
      detailList:[],
      heightMax:'',
      defaultShow:true,
      txheightMax:'',
      trees:[],
      etime:'',
      stime:''
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
    _this.findDeviceInfo();
    _this.deptMap = Tool.getDeptUser();
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.heightMax = h*0.8-150;
    _this.txheightMax = h*0.8-100;
    _this.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
    _this.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*30));
  },
  methods: {
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
    changeTab(){
      let _this = this;
      _this.defaultShow = !_this.defaultShow;
      if(!_this.defaultShow){
        $("map-top").css("top","58px");
        _this.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
        _this.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*30));
        _this.findMonitorEquipmentTreeByFile();
      }
    },
    exportExcel(){
      let _this = this;
      let paramsStr = "";
      if("460100"==Tool.getLoginUser().deptcode){
        paramsStr = "deptcode="+Tool.getLoginUser().deptcode;
      }else{
        paramsStr = "deptcode="+Tool.getLoginUser().deptcode+"&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(Tool.isNotEmpty(_this.alarmEventDto.stime)){
        paramsStr = paramsStr + "&stime="+_this.alarmEventDto.stime;
      }
      if(Tool.isNotEmpty(_this.alarmEventDto.etime)){
        paramsStr = paramsStr + "&etime="+_this.alarmEventDto.etime;
      }
      if(Tool.isNotEmpty(_this.alarmEventDto.sbbh)){
        paramsStr = paramsStr + "&sbbh="+_this.alarmEventDto.sbbh;
      }
      let url = process.env.VUE_APP_SERVER + '/monitor/export/exportByAlarmEvent?'+paramsStr;
      console.log(url);
      window.location.href = url;
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.alarmEventDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.alarmEventDto.etime = rep;
      _this.$forceUpdate();
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
      })
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.alarmEventDto.page = page;
      _this.alarmEventDto.size = _this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.alarmEventDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileAlarmEvent/statistics', _this.alarmEventDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.alarmEvents = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    details(alarmEvent){
      let _this = this;
      $("#form-modal").modal("show");
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileAlarmEvent/detailByParam', {
        'sbbh':alarmEvent.sbbh,
        'deptcode':alarmEvent.deptcode,
        'bjsj':alarmEvent.bjsj
      }).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if(resp.success){
          _this.detailList = resp.content;
        }else {
          _this.detailList = [];
        }
        _this.$forceUpdate();
      })
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
      _this.zTree = $.fn.zTree.init($("#treeAlarm"), setting, _this.trees);
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
        _this.getAlarmEventData();
      }
    },
    getAlarmEventData(){
      let _this = this;
      Loading.show();
      let obj = {};
      obj.sbbh = _this.curNode.code;
      obj.stime = _this.stime;
      obj.etime = _this.etime;
      if("460100"!=Tool.getLoginUser().deptcode){
        obj.xmbh = Tool.getLoginUser().xmbh;
      }
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileAlarmEvent/echartsAlarmData',obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.$nextTick(function (){
          _this.initEchartData(resp.content);
        })
      })
    },
    initEchartData(data){
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
          name: '事件日期',
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
          name: '事件次数',
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
      let echartsData = echarts.init(document.getElementById("echartAlarmEvent"));
      echartsData.setOption(option);
    }
  }
}
</script>