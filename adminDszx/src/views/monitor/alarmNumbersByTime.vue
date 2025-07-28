<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">出现事件查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  出现日期：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="eqstime" end-id="eqetime"></times>
                </td>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
<!--                  <input class="form-control" type="text"  v-model="alarmNumbersDto.sbbh"/>-->
                  <select v-model="alarmNumbersDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td style="width: 20%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </a>
                  <button type="button" v-on:click="exportExcel()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
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

    <div v-bind:style="{height:+maxHeight+'px',overflow:'auto'}">
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
          <tr>
            <th>监测点</th>
            <th>设备名称</th>
            <th>设备sn</th>
            <th>出现时间</th>
            <th>出现次数</th>
            <th>事件次数</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in alarmDatas">
            <td>{{deptMap|optionMapKV(item.deptcode)}}</td>
            <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
            <td>{{item.sbbh}}</td>
            <td>{{item.eventTime}}</td>
            <td>{{item.alarmNum}}</td>
            <td>1</td>
          </tr>
        </tbody>
      </table>
    </div>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
  </div>
</template>
<script>
import Times from "../../components/times";
import Pagination from "../../components/pagination";
export default {
  components: {Pagination,Times},
  name: "alarm-numbers-by-time",
  data: function (){
    return {
      alarmNumbersDto:{},
      deptMap:[],
      alarmDatas:[],
      waterEquipments:[],
      maxHeight:''
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8-120;
    _this.deptMap = Tool.getDeptUser();
    _this.list(1);
    _this.$refs.pagination.size = 10;
    _this.findDeviceInfo();
  },
  methods: {
    exportExcel(){
      let _this = this;
      let paramsStr = "";
      if("460100"==Tool.getLoginUser().deptcode){
        paramsStr = "deptcode="+Tool.getLoginUser().deptcode;
      }else{
        paramsStr = "deptcode="+Tool.getLoginUser().deptcode+"&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(Tool.isNotEmpty(_this.alarmNumbersDto.stime)){
        paramsStr = paramsStr + "&stime="+_this.alarmNumbersDto.stime;
      }
      if(Tool.isNotEmpty(_this.alarmNumbersDto.etime)){
        paramsStr = paramsStr + "&etime="+_this.alarmNumbersDto.etime;
      }
      if(Tool.isNotEmpty(_this.alarmNumbersDto.sbbh)){
        paramsStr = paramsStr + "&sbbh="+_this.alarmNumbersDto.sbbh;
      }
      let url = process.env.VUE_APP_SERVER + '/monitor/export/exportAlarmEvent?'+paramsStr;
      window.location.href = url;
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001','dqzl':'A1,A4'};
      }else{
        data = {'sblb':'0001','dqzl':'A1,A4','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
      })
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.alarmNumbersDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.alarmNumbersDto.etime = rep;
      _this.$forceUpdate();
    },
    list(page){
      let _this = this;
      Loading.show();
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.alarmNumbersDto.xmbh = Tool.getLoginUser().xmbh;
      }
      _this.alarmNumbersDto.page = page;
      _this.alarmNumbersDto.size = _this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileAlarmEvent/list', _this.alarmNumbersDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.alarmDatas = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);

      })
    }
  }
}
</script>