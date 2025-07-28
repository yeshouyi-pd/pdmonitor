<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">方位角统计查询(历史)</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  日期：
                </td>
                <td style="width: 20%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="startId" end-id="endId"></times>
                </td>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
                  <select v-model="azimuthAngleUniqueDto.sbbh" class="form-control" >
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td style="width: 30%;" class="text-center">
                  <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </a>
                  <button type="button" v-on:click="exportExcel()" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">
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
          <th>所属机构</th>
          <th>设备编号</th>
          <th>设备名称</th>
          <th>日期</th>
          <th>分钟</th>
          <th>发声次数</th>
          <th>北偏东45度</th>
          <th>东偏北45度</th>
          <th>东偏南45度</th>
          <th>南偏东45度</th>
          <th>南偏西45度</th>
          <th>西偏南45度</th>
          <th>西偏北45度</th>
          <th>北偏西45度</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="azimuthAngleUnique in azimuthAngleUniques">
          <td>{{deptMap|optionMapKV(azimuthAngleUnique.deptcode)}}</td>
          <td>{{azimuthAngleUnique.sbbh}}</td>
          <td>{{waterEquipments|optionNSArray(azimuthAngleUnique.sbbh)}}</td>
          <td>{{azimuthAngleUnique.rq}}</td>
          <td>{{azimuthAngleUnique.fz}}</td>
          <td>{{azimuthAngleUnique.ts}}</td>
          <td>{{azimuthAngleUnique.northNortheast}}</td>
          <td>{{azimuthAngleUnique.northeastEast}}</td>
          <td>{{azimuthAngleUnique.eastEastsouth}}</td>
          <td>{{azimuthAngleUnique.eastsouthSouth}}</td>
          <td>{{azimuthAngleUnique.southSouthwest}}</td>
          <td>{{azimuthAngleUnique.southwestWest}}</td>
          <td>{{azimuthAngleUnique.westWestnorth}}</td>
          <td>{{azimuthAngleUnique.westnorthNorth}}</td>
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
  name:'azimuth-angle',
  components: {Pagination,Times},
  data:function (){
    return {
      azimuthAngleUnique:{},
      azimuthAngleUniqueDto:{},
      azimuthAngleUniques:[],
      deptMap:null,
      waterEquipments:[]
    }
  },
  mounted() {
    let _this = this;
    _this.findDeviceInfo();
    _this.$refs.pagination.size = 10;
    _this.deptMap = Tool.getDeptUser();
    _this.list(1);
  },
  methods:{
    exportExcel(){
      let _this = this;
      let paramsStr = "";
      if("460100"==Tool.getLoginUser().deptcode){
        paramsStr = "deptcode="+Tool.getLoginUser().deptcode;
      }else{
        paramsStr = "deptcode="+Tool.getLoginUser().deptcode+"&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(Tool.isNotEmpty(_this.azimuthAngleUniqueDto.stime)){
        paramsStr = paramsStr + "&stime="+_this.azimuthAngleUniqueDto.stime;
      }
      if(Tool.isNotEmpty(_this.azimuthAngleUniqueDto.etime)){
        paramsStr = paramsStr + "&etime="+_this.azimuthAngleUniqueDto.etime;
      }
      if(Tool.isNotEmpty(_this.azimuthAngleUniqueDto.sbbh)){
        paramsStr = paramsStr + "&sbbh="+_this.azimuthAngleUniqueDto.sbbh;
      }
      let url = process.env.VUE_APP_SERVER + '/monitor/export/exportAzimuthAngleUnique?'+paramsStr;
      console.log(url);
      window.location.href = url;
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.azimuthAngleUniqueDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.azimuthAngleUniqueDto.etime = rep;
      _this.$forceUpdate();
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.azimuthAngleUniqueDto.page = page;
      _this.azimuthAngleUniqueDto.size = _this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.azimuthAngleUniqueDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/azimuthAngleUnique/list', _this.azimuthAngleUniqueDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.azimuthAngleUniques = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {'sblb':'0001','dqzl':'A4'}
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        _this.$forceUpdate();
      })
    }
  }
}
</script>