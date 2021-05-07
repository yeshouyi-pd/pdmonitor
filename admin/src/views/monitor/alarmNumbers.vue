<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">报警次数查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  报警时间：
                </td>
                <td style="width: 25%">
                  <Times v-bind:startTime="startTime" v-bind:endTime="endTime"></Times>
                </td>
                <td style="width:10%">
                  设备sn：
                </td>
                <td style="width: 15%">
                  <input class="input-sm" type="text"  v-model="alarmNumbersDto.sbbh"/>
                </td>
                <td colspan="2" class="text-center">
                  <button v-on:click="statisticsAlarmNums()" type="button" class="btn btn-sm  btn-info btn-round">
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

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
          <tr>
            <th>监测点</th>
            <th>设备sn</th>
            <th>报警时间</th>
            <th>报警次数</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in alarmDatas">
            <td>{{deptMap|optionMapKV(item.deptcode)}}</td>
            <td>{{item.sbbh}}</td>
            <td>{{item.bjsj}}</td>
            <td>{{item.alarmNum}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import Times from "../../components/time";
export default {
  components: {Times},
  name: "alarm-numbers",
  data: function (){
    return {
      alarmNumbersDto:{},
      deptMap:[],
      alarmDatas:[]
    }
  },
  mounted() {
    let _this = this;
    _this.deptMap = Tool.getDeptUser();
    _this.statisticsAlarmNums();
  },
  methods: {
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
    statisticsAlarmNums(){
      let _this = this;
      Loading.show();
      _this.alarmDatas = [];
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/statisticsAlarmNums', _this.alarmNumbersDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.alarmDatas = resp.content;
      })
    }
  }
}
</script>