<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">方位角统计查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width: 10%">
                  设备SN：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="azimuthAngleDto.sbsn"/>
                </td>
                <td style="width: 10%">
                  摄像头ip：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="azimuthAngleDto.ip"/>
                </td>
                <td style="width: 10%">
                  摄像头用户名：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="azimuthAngleDto.username"/>
                </td>
                <td style="width: 30%;" class="text-center">
                  <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round" style="margin-right: 10px;">
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
          <th>所属机构</th>
          <th>设备编号</th>
          <th>设备名称</th>
          <th>日期</th>
          <th>分钟</th>
          <th>发声次数</th>
          <th>北-北东</th>
          <th>北东-东</th>
          <th>东-东南</th>
          <th>东南-南</th>
          <th>南-南西</th>
          <th>南西-西</th>
          <th>西-西北</th>
          <th>西北-北</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="azimuthAngle in azimuthAngles">
          <td>{{deptMap|optionMapKV(azimuthAngle.deptcode)}}</td>
          <td>{{azimuthAngle.sbbh}}</td>
          <td>{{waterEquipments|optionNSArray(azimuthAngle.sbbh)}}</td>
          <td>{{azimuthAngle.rq}}</td>
          <td>{{azimuthAngle.fz}}</td>
          <td>{{azimuthAngle.ts}}</td>
          <td>{{azimuthAngle.northNortheast}}</td>
          <td>{{azimuthAngle.northeastEast}}</td>
          <td>{{azimuthAngle.eastEastsouth}}</td>
          <td>{{azimuthAngle.eastsouthSouth}}</td>
          <td>{{azimuthAngle.southSouthwest}}</td>
          <td>{{azimuthAngle.southwestWest}}</td>
          <td>{{azimuthAngle.westWestnorth}}</td>
          <td>{{azimuthAngle.westnorthNorth}}</td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>
    
  </div>
</template>
<script>
import Pagination from "../../components/pagination";
export default {
  name:'azimuth-angle',
  components: {Pagination},
  data:function (){
    return {
      azimuthAngle:{},
      azimuthAngleDto:{},
      azimuthAngles:[],
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
    list(page){
      let _this = this;
      Loading.show();
      _this.azimuthAngleDto.page = page;
      _this.azimuthAngleDto.size = _this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/azimuthAngle/list', _this.azimuthAngleDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.azimuthAngles = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        _this.$forceUpdate();
      })
    }
  }
}
</script>