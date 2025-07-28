<template>
  <div>

    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">群次查询</h4>
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
                      设备名称：
                    </td>
                    <td style="width: 20%">
<!--                      <input class="form-control" type="text"  v-model="groupEventDto.sbbh"/>-->
                      <select v-model="groupEventDto.sbbh" class="form-control" id="form-field-select-1">
                        <option value="" selected>请选择</option>
                        <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                      </select>
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
          <th>监测点</th>
          <th>设备sn</th>
          <th>开始时间</th>
          <th>结束时间</th>
          <th>侦测头数</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in groupEvents">
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.kssj}}</td>
          <td>{{item.jssj}}</td>
          <td>{{item.zcts}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="details(item.zcList)" class="btn btn-xs btn-info" title="方位角信息">
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
            <h4 class="modal-title">方位角信息</h4>
          </div>
          <div class="modal-body" :style="{height: heightMax + 'px' ,overflowY: 'auto'}">
            <table class="table  table-bordered table-hover">
              <thead>
              <tr>
                <th>豚编号</th>
                <th>起始方位角</th>
                <th>最终方位角</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in fwjList">
                <td>{{item.substring(0,item.indexOf(':'))}}</td>
                <td>{{item.substring(item.indexOf(':')+1,item.indexOf(';'))}}</td>
                <td>{{item.substring(item.indexOf(';')+1,item.length)}}</td>
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

export default {
  components: {Pagination},
  name: "group-event",
  data: function (){
    return {
      groupEvent: {},
      groupEventDto: {},
      groupEvents:[],
      heightMax:'',
      fwjList:[],
      waterEquipments: []
    }
  },
  mounted() {
    let _this = this;
    _this.findDeviceInfo();
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.heightMax = h*0.8-150;
    _this.$refs.pagination.size = 10;
    _this.list(1);
  },
  methods: {
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001'};
      }else{
        data = {'sblb':'0001','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
      })
    },
    list(page){
      console.log(page);
      let _this = this;
      Loading.show();
      _this.groupEventDto.page = page;
      _this.groupEventDto.size = _this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.groupEventDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/groupEvent/list', _this.groupEventDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.groupEvents = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    details(arr){
      let _this = this;
      _this.fwjList = $.extend([], arr);
      _this.$forceUpdate();
      $("#form-modal").modal("show");
    }
  }
}
</script>