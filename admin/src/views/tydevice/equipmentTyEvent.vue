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
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 15%">
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
          <th>开始时间</th>
          <th>结束时间</th>
          <th>头数</th>
          <th style="width: 18%;">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in equipmentTyEvents">
          <td>{{deptMap|optionMapKV(item.deptcode)}}</td>
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.kssj}}</td>
          <td>{{item.jssj}}</td>
          <td>{{item.ts}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group" v-if="userDto.rode=='00000000'">
              <button v-on:click="edit(item)" class="btn btn-xs btn-info" title="修改">
                <i class="ace-icon fa fa-pencil bigger-120">修改</i>
              </button>
              <button v-on:click="downloadFile(item.id)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="ace-icon fa fa-volume-down bigger-120">下载文件</i>
              </button>
              <button v-on:click="downloadVedio(item.id)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="ace-icon fa fa-volume-down bigger-120">下载视频</i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>


    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">设备sn</label>
                <div class="col-sm-10">
                  <input v-model="equipmentTyEvent.sbbh" class="form-control" readonly>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">开始时间</label>
                <div class="col-sm-10">
                  <input v-model="equipmentTyEvent.kssj" class="form-control" readonly>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">结束时间</label>
                <div class="col-sm-10">
                  <input v-model="equipmentTyEvent.jssj" class="form-control" readonly>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">头数</label>
                <div class="col-sm-10">
                  <input v-model="equipmentTyEvent.ts" class="form-control" >
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
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
      waterEquipments: [],
      userDto:null,
      shj:LOCAL_SSBRL
    }
  },
  mounted() {
    let _this = this;
    _this.userDto = Tool.getLoginUser();
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
     *修改
     */
    edit(equipmentTyEvent){
      let _this = this;
      _this.equipmentTyEvent = $.extend({}, equipmentTyEvent);
      $("#form-modal").modal("show");
    },
    /**
     * 点击【保存】
     */
    save() {
      let _this = this;
      // 保存校验
      if (1 != 1
          || !Validator.require(_this.equipmentTyEvent.ts, "头数")
          || !Validator.checkIsDigits(_this.equipmentTyEvent.ts, "头数")
      ) {
        return;
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentTyEvent/save', _this.equipmentTyEvent).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message);
        }
      })
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
    },
    downloadFile(id){
      let _this = this;
      let url = "";
      if(_this.shj){
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFileById53?id='+id;
      }else{
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFileById?id='+id;
      }
      console.log(url);
      window.location.href = url;
    },
    downloadVedio(id){
      let _this = this;
      let url = "";
      if(_this.shj){
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downZipById53?id='+id;
      }else{
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downZipById?id='+id;
      }
      _this.$ajax.get(url).then((response)=>{
        if(response.data && response.data.message && response.data.message.includes("系统异常")){
          Toast.error("系统异常，请联系管理员！");
        }else if(response.data && response.data.includes("未找到")){
          Toast.error("未找到对应视频！");
        }else{
          window.location.href = url;
        }
      })
    }
  }
}
</script>