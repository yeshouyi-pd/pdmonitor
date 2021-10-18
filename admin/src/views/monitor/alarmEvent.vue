<template>
  <div>
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
                      报警日期：
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
                <th>报警次数</th>
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
      heightMax:''
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
  },
  methods: {
    exportExcel(){
      let _this = this;
      let paramsStr = "deptcode="+Tool.getLoginUser().deptcode;
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
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', {'sblb':'0001'}).then((response)=>{
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
    }
  }
}
</script>