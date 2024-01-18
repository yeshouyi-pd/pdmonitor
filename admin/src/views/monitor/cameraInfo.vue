<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">设备相机查询</h4>
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
                  <input class="form-control" type="text"  v-model="cameraInfoDto.sbsn"/>
                </td>
                <td style="width: 10%">
                  摄像头ip：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="cameraInfoDto.ip"/>
                </td>
                <td style="width: 10%">
                  摄像头用户名：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="cameraInfoDto.username"/>
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

    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
          <tr>
            <th>设备sn</th>
            <th>所属机构</th>
            <th>摄像头编号</th>
            <th>设备名称</th>
            <th>摄像头IP</th>
            <th>NVR剪切端口</th>
            <th>NVR用户名</th>
            <th>NVR密码</th>
            <th>通道号</th>
            <th>NVR的IP</th>
            <th>预置位</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="cameraInfo in cameraInfos">
            <td>{{cameraInfo.sbsn}}</td>
            <td>{{alldept|optionDCNArray(cameraInfo.deptcode)}}</td>
            <td>{{cameraInfo.sm2}}</td>
            <td>{{cameraInfo.sm3}}</td>
            <td>{{cameraInfo.ip}}</td>
            <td>{{cameraInfo.port}}</td>
            <td>{{cameraInfo.username}}</td>
            <td>{{cameraInfo.camerapws}}</td>
            <td>{{cameraInfo.sbdk}}</td>
            <td>{{cameraInfo.dvrip}}</td>
            <td>{{cameraInfo.sm4}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit(cameraInfo)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
                <button v-on:click="del(cameraInfo.id)" class="btn btn-xs btn-danger">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">设备sn</label>
                <div class="col-sm-4">
<!--                  <input v-model="cameraInfo.sbsn" class="form-control">-->
                  <div class="row">
                    <div class="col-sm-9">
                      <input disabled="disabled" v-model="cameraInfo.sbsn" class="form-control">
                    </div>
                    <div>
                      <button  v-on:click="chenckSbsncode()" type="button" class="btn btn-success">选择设备</button>
                    </div>
                  </div>
                </div>
                <label class="col-sm-2 control-label">所属机构</label>
                <div class="col-sm-4">
<!--                  <input v-model="cameraInfo.deptcode" class="form-control">-->
                  <div class="row">
                    <div class="col-sm-9">
                      <input  style="display: none" v-model="cameraInfo.deptcode"  class="form-control">
                      <input   disabled="disabled" v-for="updept in alldept.filter(t=>{return t.deptcode===cameraInfo.deptcode})" v-bind:value="updept.deptname"  class="form-control">
                    </div>
                    <div>
                      <button  v-on:click="chencktreeid()" type="button" class="btn btn-success" >选择部门</button>
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">摄像头编号</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.sm2" class="form-control" v-bind:disabled="cameraInfo.id">
                </div>
                <label class="col-sm-2 control-label">设备名称</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.sm3" class="form-control" v-bind:disabled="cameraInfo.id">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">摄像头IP</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.ip" class="form-control">
                </div>
                <label class="col-sm-2 control-label">NVR剪切端口</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.port" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">NVR用户名</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.username" class="form-control">
                </div>
                <label class="col-sm-2 control-label">NVR密码</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.camerapws" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">通道号</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.sbdk" class="form-control">
                </div>
                <label class="col-sm-2 control-label">NVR的IP</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.dvrip" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">预置位</label>
                <div class="col-sm-4">
                  <input v-model="cameraInfo.sm4" class="form-control">
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

    <div id="tree-modal" class="modal fade" tabindex="-1"  style="z-index: 1060"  role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">双击选择部门</h4>
          </div>
          <div class="modal-body">
            <ul id="treeNode" class="ztree"></ul>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
              <i class="ace-icon fa fa-times"></i>
              关闭
            </button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="form-modal-equipmentcommen" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content"  :visible.sync="showEquipmentBody" v-if="showEquipmentBody">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">选择设备</h4>
          </div>
          <div class="modal-body">
            <equipmentcommen :is-multiple="equipmentMultiple" v-bind:sbsncodes.sync="sbsncodes" @equipmentBack="equipmentBack"></equipmentcommen>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Equipmentcommen from "../../components/equipmentcommen";
  export default {
    components: {Pagination, Equipmentcommen},
    name: "monitor-cameraInfo",
    data: function() {
      return {
        cameraInfoDto: {},
        cameraInfo: {},
        cameraInfos: [],
        alldept: [],
        trees:[],
        sbsncodes:[],
        showEquipmentBody:false,
        equipmentMultiple:''
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getAllDept();
      _this.getDeptTree();
      $('#form-modal-equipmentcommen').on('hidden.bs.modal', function () {
        _this.showEquipmentBody = false;
        _this.sbsncodes = [];
        _this.$forceUpdate();
      })
    },
    methods: {
      /**
       * 选择设备
       */
      chenckSbsncode(){
        let _this = this;
        _this.equipmentMultiple = false;
        _this.sbsncodes=_this.cameraInfo.sbsn?_this.cameraInfo.sbsn.split(","):[];
        _this.showEquipmentBody = true;
        _this.$forceUpdate();
        $("#form-modal-equipmentcommen").modal("show");
      },
      equipmentBack(sbsncodes){
        let _this = this;
        if(sbsncodes.length<=0){
          Toast.warning("请选择设备");
          return
        }
        _this.cameraInfo.sbsn = sbsncodes.toString();
        _this.showEquipmentBody = false;
        _this.$forceUpdate();
        $("#form-modal-equipmentcommen").modal("hide");
      },

      /**
       * 选择部门
       */
      chencktreeid(){
        let _this = this;
        $("#tree-modal").modal("show");
      },
      getDeptTree() {
        let _this = this;
        Loading.show();
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/dept/load-deptTree').then((res)=>{
          Loading.hide();
          let response = res.data;
          _this.trees = response.content;
          // 初始化树
          _this.initTree();
        })
      },
      /**
       * 初始资源树
       */
      initTree() {
        let _this = this;
        let setting = {
          data: {
            key:{
              name:"deptname",
            },
            simpleData: {
              idKey: "deptcode",
              pIdKey: "upcode",
              rootPId: "0",
              enable: true
            }
          },callback: {
            onDblClick:  _this.zTreeOnDblClick
          },
        };
        _this.zTree = $.fn.zTree.init($("#treeNode"), setting, _this.trees);
        _this.zTree.expandAll(true);
      },
      zTreeOnDblClick(event, treeId, treeNode) {
        let  _this = this;
        _this.cameraInfo.deptcode = treeNode.deptcode;
        _this.$forceUpdate();
        $("#tree-modal").modal("hide");
      },

      /**
       * 获取所有数据
       */
      getAllDept() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/dept/getAllDept').then((response)=>{
          let resp = response.data;
          _this.alldept = resp.content;
          _this.$forceUpdate();
        })
      },

      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.cameraInfo = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(cameraInfo) {
        let _this = this;
        _this.cameraInfo = $.extend({}, cameraInfo);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.cameraInfoDto.page = page;
        _this.cameraInfoDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/cameraInfo/list', _this.cameraInfoDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.cameraInfos = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;
        // 保存校验
        if (1 != 1
                || !Validator.length(_this.cameraInfo.sbsn, "设备sn", 1, 128)
                || !Validator.length(_this.cameraInfo.deptcode, "机构编号", 1, 45)
                || !Validator.length(_this.cameraInfo.ip, "摄像头IP", 1, 128)
                || !Validator.length(_this.cameraInfo.username, "摄像头用户名", 1, 128)
                || !Validator.length(_this.cameraInfo.camerapws, "摄像头密码", 1, 128)
                || !Validator.length(_this.cameraInfo.dvrip, "所属DVR的IP", 1, 128)
                || !Validator.length(_this.cameraInfo.xmbh, "项目编号", 1, 255)
                || !Validator.length(_this.cameraInfo.cjr, "创建人", 1, 128)
                || !Validator.require(_this.cameraInfo.sbsn, "设备sn")
                || !Validator.require(_this.cameraInfo.deptcode, "机构编号")
                || !Validator.require(_this.cameraInfo.ip, "摄像头IP")
                || !Validator.require(_this.cameraInfo.port, "端口号")
                || !Validator.checkisNaN(_this.cameraInfo.port, "端口号")
                || !Validator.require(_this.cameraInfo.username, "摄像头用户名")
                || !Validator.require(_this.cameraInfo.camerapws, "摄像头密码")
                || !Validator.require(_this.cameraInfo.sbdk, "通道号")
                || !Validator.checkisNaN(_this.cameraInfo.sbdk, "通道号")
                || !Validator.require(_this.cameraInfo.sm2, "摄像头编号")
                || !Validator.require(_this.cameraInfo.sm3, "摄像头名称")
        ) {
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/cameraInfo/save', _this.cameraInfo).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            Toast.success("保存成功！");
          } else {
            Toast.warning(resp.message)
          }
        })
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("删除后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/cameraInfo/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      }
    }
  }
</script>