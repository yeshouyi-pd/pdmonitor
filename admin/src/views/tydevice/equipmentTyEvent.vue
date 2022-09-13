<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">聚类事件查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
<!--                <td style="width:10%">-->
<!--                  创建日期：-->
<!--                </td>-->
<!--                <td style="width: 25%">-->
<!--                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="etestime" end-id="eteetime"></times>-->
<!--                </td>-->
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 15%">
<!--                  <input class="form-control" type="text"  v-model="equipmentTyEventDto.sbbh"/>-->
                  <select v-model="equipmentTyEventDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
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
          <th>检测点</th>
          <th>设备sn</th>
<!--          <th>创建时间</th>-->
          <th>开始时间</th>
          <th>结束时间</th>
          <th>头数</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in equipmentTyEvents">
          <td>{{deptMap|optionMapKV(item.deptcode)}}</td>
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
<!--          <td>{{item.cjsj}}</td>-->
          <td>{{item.kssj}}</td>
          <td>{{item.jssj}}</td>
          <td>{{item.ts}}</td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

  </div>
</template>
<script>
import Times from "../../components/times";
import Pagination from "../../components/pagination";

export default {
  name: '',
  components: {Pagination,Times},
  data: function (){
    return {
      equipmentTyEvent:{},
      equipmentTyEventDto:{},
      equipmentTyEvents:[],
      deptMap: [],
      waterEquipments: []
    }
  },
  mounted() {
    let _this = this;
    _this.deptMap = Tool.getDeptUser();
    _this.$refs.pagination.size = 10;
    _this.$forceUpdate();
    _this.list(1);
    _this.findDeviceInfo();
  },
  methods: {
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
        _this.$forceUpdate();
      })
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.equipmentTyEventDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.equipmentTyEventDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.equipmentTyEventDto.page = page;
      _this.equipmentTyEventDto.size = _this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.equipmentTyEventDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentTyEvent/list', _this.equipmentTyEventDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentTyEvents = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>