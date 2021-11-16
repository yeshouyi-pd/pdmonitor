<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">报警事件查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  报警日期：
                </td>
                <td style="width: 25%">
                  <time-range-picker v-bind:startTime="startTime" v-bind:endTime="endTime" v-bind:svalue="curDateStr+' 00:00'" v-bind:evalue="curDateStr+' 23:59'"></time-range-picker>
                </td>
                <td style="width:10%">
                  设备sn：
                </td>
                <td style="width: 10%">
                  <input class="form-control" type="text"  v-model="alarmNumbersDto.sbbh"/>
                </td>
                <td colspan="2" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
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

    <div v-bind:style="{height:+maxHeight+'px',overflow:'auto'}">
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
          <tr>
            <th>监测点</th>
            <th>设备名称</th>
            <th>设备sn</th>
            <th>报警时间</th>
            <th>报警次数</th>
            <th>事件次数</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in alarmDatas">
            <td>{{deptMap|optionMapKV(item.deptcode)}}</td>
            <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
            <td>{{item.sbbh}}</td>
            <td>{{item.bjsj}}</td>
            <td>{{item.alarmNum}}</td>
            <td>1</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import TimeRangePicker from "../../components/timeRangePicker";
export default {
  components: {TimeRangePicker},
  name: "alarm-numbers-by-time",
  data: function (){
    return {
      alarmNumbersDto:{},
      deptMap:[],
      alarmDatas:[],
      waterEquipments:[],
      curDateStr:'',
      maxHeight:''
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8-120;
    _this.deptMap = Tool.getDeptUser();
    _this.curDateStr = Tool.dateFormat("yyyy-MM-dd",new Date());
    _this.list(1);
    _this.findDeviceInfo();
    _this.alarmNumbersDto.stime = _this.curDateStr+" 00:00";
    _this.alarmNumbersDto.etime = _this.curDateStr+" 23:59";
  },
  methods: {
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', {'sblb':'0001','xmbh':Tool.getLoginUser().xmbh}).then((response)=>{
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
      _this.alarmNumbersDto.xmbh = Tool.getLoginUser().xmbh;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/statisticsAlarmNumsByTime', _this.alarmNumbersDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.alarmDatas = resp.content;
      })
    }
  }
}
</script>