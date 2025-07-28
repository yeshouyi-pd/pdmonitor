<template>
  <div>

    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">趸船数据查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  采集时间：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="pgstime" end-id="pgetime"></times>
                </td>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
                  <!--                  <input class="form-control" type="text"  v-model="equipmentFileDto.sbbh"/>-->
                  <select v-model="pontoonGpsDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td style="width:20%" class="text-center">
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

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>设备名称</th>
          <th>设备sn</th>
          <th>采集时间</th>
          <th>gps</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in pontoonGpss">
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.cjsj}}</td>
          <td>{{item.gps}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>

  </div>
</template>
<script>
import Pagination from "../../components/pagination";
import Times from "../../components/times";

export default {
  name: 'pontoon-gps',
  components:{Pagination,Times},
  data: function (){
    return {
      pontoonGps:{},
      pontoonGpsDto:{},
      pontoonGpss:[],
      waterEquipments:[],
      maxHeight:'',
      shj:LOCAL_SSBRL
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8;
    _this.list(1);
    _this.$refs.pagination.size = 10;
    _this.findDeviceInfo();
  },
  methods: {
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.pontoonGpsDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.pontoonGpsDto.etime = rep;
      _this.$forceUpdate();
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001','dqzl':'A2'};
      }else{
        data = {'sblb':'0001','dqzl':'A2','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
      })
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.pontoonGpsDto.page=page;
      _this.pontoonGpsDto.size=_this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.pontoonGpsDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/pontoonGps/list', _this.pontoonGpsDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.pontoonGpss = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>