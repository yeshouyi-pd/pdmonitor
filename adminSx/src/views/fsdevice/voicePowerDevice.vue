<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">工作日志查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  设备编号：
                </td>
                <td style="width: 15%">
                  <select v-model="voicePowerDeviceDto.sbbh" class="form-control">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td style="width: 10%;">
                  开始日期：
                </td>
                <td style="width: 15%;">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="rStime" end-id="rEtime"></times>
                </td>
                <td  style="width: 20%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round" style="margin-right: 10px;">
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
          <th>主题</th>
          <th>日期</th>
          <th>开始指令下发时间</th>
          <th>是否播放</th>
          <th>结束指令下发时间</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in voicePowerDevices">
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.topicName}}</td>
          <td>{{item.rq}}</td>
          <td>{{item.sendTime}}</td>
          <td><span v-if="item.isPlay=='1'">已播放</span><span v-else>未播放</span></td>
          <td>{{item.stopTime}}</td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

  </div>
</template>
<script>
import Pagination from "../../components/pagination";
import Times from "../../components/times";

export default {
  name: 'voice-power-device',
  components: {Pagination,Times},
  data: function (){
    return {
      voicePowerDevice:{},
      voicePowerDeviceDto:{},
      voicePowerDevices:[],
      waterEquipments:[]
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.findDeviceInfo();
    _this.list(1);
  },
  methods: {
    exportExcle(){
      let _this = this;
      let param = "";
      if("460100"!=Tool.getLoginUser().deptcode){
        param+="&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(!Tool.isEmpty(_this.voicePowerDeviceDto.sbbh)){
        param+="&sbbh="+_this.voicePowerDeviceDto.sbbh;
      }
      if(!Tool.isEmpty(_this.voicePowerDeviceDto.stime)){
        param+="&stime="+_this.voicePowerDeviceDto.stime;
      }
      if(!Tool.isEmpty(_this.voicePowerDeviceDto.etime)){
        param+="&etime="+_this.voicePowerDeviceDto.etime;
      }
      if(Tool.isEmpty(param)){
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportVoicePowerDevice';
      }else{
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportVoicePowerDevice?'+param.substring(1,param.length);
      }
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0005','dqzl':'F1'};
      }else{
        data = {'sblb':'0005','dqzl':'F1','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        _this.$forceUpdate();
      })
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.voicePowerDeviceDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.voicePowerDeviceDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.voicePowerDeviceDto.page = page;
      _this.voicePowerDeviceDto.size = _this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.voicePowerDeviceDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/voicePowerDevice/list', _this.voicePowerDeviceDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.voicePowerDevices = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>