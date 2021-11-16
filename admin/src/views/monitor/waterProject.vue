<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">项目管理查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  项目编号：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="waterProjectDto.xmbh"/>
                </td>
                <td style="width: 10%">
                  项目名称：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="waterProjectDto.xmmc"/>
                </td>
                <td colspan="2" class="text-center">
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
            <th>项目编号</th>
            <th>项目名称</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>项目负责人</th>
            <th>所属部门</th>
            <th>项目用途</th>
            <th>操作</th>
          </tr>
        </thead>

        <tbody>
        <tr v-for="waterProject in waterProjects">
          <td>{{waterProject.xmbh}}</td>
          <td>{{waterProject.xmmc}}</td>
          <td>{{waterProject.kssj}}</td>
          <td>{{waterProject.jssj}}</td>
          <td>{{waterProject.fzr}}</td>
          <td>{{alldept|optionDCNArray(waterProject.deptcode)}}</td>
          <td>{{allXmyt|optionMapKV(waterProject.xmyt)}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(waterProject)" class="btn btn-xs btn-info" title="修改">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
              </button>
              <button v-on:click="detail(waterProject)" class="btn btn-xs btn-info" title="详情">
                <i class="ace-icon fa fa-list bigger-120"></i>
              </button>
              <button v-on:click="del(waterProject.id)" class="btn btn-xs btn-danger" title="删除">
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
      <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">项目编号</label>
                <div class="col-sm-4">
                  <input v-model="waterProject.xmbh" class="form-control" :disabled="waterProject.id">
                </div>
                <label class="col-sm-2 control-label">项目名称</label>
                <div class="col-sm-4">
                  <input v-model="waterProject.xmmc" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">项目时间</label>
                <div class="col-sm-4">
                  <times v-bind:start-time="startTime" v-bind:end-time="endTime"
                  start-id="wpstime" end-id="wpetime"
                  v-bind:svalue="waterProject.kssj" v-bind:evalue="waterProject.jssj"></times>
                </div>
                <label class="col-sm-2 control-label">项目用途</label>
                <div class="col-sm-4">
                  <select v-model="waterProject.xmyt" class="form-control">
                    <option v-for="(key,value) in allXmyt" v-bind:value="value">{{key}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">项目负责人</label>
                <div class="col-sm-4">
<!--                  <input v-model="waterProject.fzr" class="form-control">-->
                  <div class="row">
                    <div class="col-sm-9">
                      <input disabled="disabled" v-model="waterProject.fzr" class="form-control">
                    </div>
                    <div class="col-sm-2">
                      <button  v-on:click="chenckChargeUsercode()" type="button" class="btn btn-success" >选择用户</button>
                    </div>
                  </div>
                </div>
                <label class="col-sm-2 control-label">所属部门</label>
                <div class="col-sm-4">
                  <div class="row">
                    <div class="col-sm-9">
                      <input  style="display: none" v-model="waterProject.deptcode"  class="form-control">
                      <input   disabled="disabled" v-for="updept in alldept.filter(t=>{return t.deptcode===waterProject.deptcode})" v-bind:value="updept.deptname"  class="form-control">
                    </div>
                    <div  class="col-sm-2">
                      <button  v-on:click="chencktreeid()" type="button" class="btn btn-success" >选择部门</button>
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">参与人员</label>
                <div class="col-sm-10">
                  <div class="row">
                    <div class="col-sm-9">
                      <input disabled="disabled" v-model="waterProject.userCodes" class="form-control">
                    </div>
                    <div class="col-sm-2">
                      <button  v-on:click="chenckUsercode()" type="button" class="btn btn-success" >选择用户</button>
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">涉及设备</label>
                <div class="col-sm-10">
                  <div class="row">
                    <div class="col-sm-9">
                      <input disabled="disabled" v-model="waterProject.sbsnCodes" class="form-control">
                    </div>
                    <div class="col-sm-2">
                      <button  v-on:click="chenckSbsncode()" type="button" class="btn btn-success" >选择设备</button>
                    </div>
                  </div>
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

    <div id="form-modal-detail" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">详情</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">项目编号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProject.xmbh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">项目名称</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProject.xmmc}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">开始时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProject.kssj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">结束时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProject.jssj}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">项目用途</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{allXmyt|optionMapKV(waterProject.xmyt)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">项目负责人</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProject.fzr}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">所属部门</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{alldept|optionDCNArray(waterProject.deptcode)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">创建人</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProject.cjr}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">创建时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProject.cjsj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">更新时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProject.gxsj}}</span>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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


    <div id="form-modal-usercommen" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content"  :visible.sync="showUserBody" v-if="showUserBody">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">选择用户</h4>
          </div>
          <div class="modal-body">
            <usecommen :is-multiple="userMultiple" v-bind:usercodes.sync="usercodes" @userBack="userBack"></usecommen>
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
  import Times from "../../components/times";
  import Usecommen from "../../components/usecommen";
  import Equipmentcommen from "../../components/equipmentcommen";
  export default {
    components: {Times, Pagination, Usecommen, Equipmentcommen},
    name: "monitor-waterProject",
    data: function() {
      return {
        waterProjectDto: {},
        waterProject: {},
        waterProjects: [],
        alldept:[],
        trees:[],
        allXmyt:[],
        usercodes:[],
        showUserBody:false,
        userMultiple:'',
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
      _this.getXmyt();
      $('#form-modal-usercommen').on('hidden.bs.modal', function () {
        _this.showUserBody = false;
        _this.$forceUpdate();
      })
      $('#form-modal-equipmentcommen').on('hidden.bs.modal', function () {
        _this.showEquipmentBody = false;
        _this.$forceUpdate();
      })
    },
    methods: {
      /**
       * 选择设备
       */
      chenckSbsncode(){
        let _this = this;
        _this.equipmentMultiple=true;
        _this.sbsncodes=_this.waterProject.sbsnCodes?_this.waterProject.sbsnCodes.split(","):[];
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
        _this.waterProject.sbsnCodes = sbsncodes.toString();
        _this.showEquipmentBody = false;
        _this.$forceUpdate();
        $("#form-modal-equipmentcommen").modal("hide");
      },
      /**
       * 选择负责人
       */
      chenckChargeUsercode(){
        let _this = this;
        _this.userMultiple=false;
        _this.usercodes=_this.waterProject.fzr?_this.waterProject.fzr.split(","):[];
        _this.showUserBody = true;
        _this.$forceUpdate();
        $("#form-modal-usercommen").modal("show");
      },
      /**
       * 选择参与人员
       */
      chenckUsercode(){
        let _this = this;
        _this.userMultiple=true;
        _this.usercodes=_this.waterProject.userCodes?_this.waterProject.userCodes.split(","):[];
        _this.showUserBody = true;
        _this.$forceUpdate();
        $("#form-modal-usercommen").modal("show");
      },
      userBack(usercodes){
        let _this = this;
        if(usercodes.length<=0){
          Toast.warning("请选择人员");
          return
        }
        if(_this.userMultiple){
          _this.waterProject.userCodes = usercodes.toString();
        }else{
          _this.waterProject.fzr = usercodes.toString();
        }
        _this.showUserBody = false;
        _this.$forceUpdate();
        $("#form-modal-usercommen").modal("hide");
      },
      /**
       * 获取项目用途
       */
      getXmyt() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getXmyt').then((response)=>{
          let resp = response.data;
          _this.allXmyt = resp.content;
          _this.$forceUpdate();
        })
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
        _this.waterProject.deptcode = treeNode.deptcode;
        _this.$forceUpdate();
        $("#tree-modal").modal("hide");
      },

      /**
       * 选择部门
       */
      chencktreeid(){
        let _this = this;
        $("#tree-modal").modal("show");
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

      startTime(resp){
        let _this = this;
        _this.waterProject.kssj = resp;
        _this.$forceUpdate();
      },

      endTime(resp){
        let _this = this;
        _this.waterProject.jssj = resp;
        _this.$forceUpdate();
      },

      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.waterProject = {};
        _this.usercodes=[];
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(waterProject) {
        let _this = this;
        _this.getCyry(waterProject.xmbh);
        _this.getCysb(waterProject.xmbh);
        _this.waterProject = $.extend({}, waterProject);
        $("#form-modal").modal("show");
      },

      /**
       * 获取参与人员
       */
      getCyry(xmbh){
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterProUser/findAllByExample' , {'xmbh':xmbh,'isboss':'0'}).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            let users = resp.content;
            let cyryStr = '';
            for(let i=0;i<users.length;i++){
              cyryStr = cyryStr + users[i].usercode + ",";
            }
            if(cyryStr.length>1){
              _this.waterProject.userCodes = cyryStr.substring(0,cyryStr.length-1);
            }
            _this.$forceUpdate();
          }
        })
      },
      /**
       * 获取参与设备
       */
      getCysb(xmbh){
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterProEquip/findAllByExample' , {'xmbh':xmbh}).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            let equips = resp.content;
            let cysbStr = '';
            for(let i=0;i<equips.length;i++){
              cysbStr = cysbStr + equips[i].sbsn + ",";
            }
            if(cysbStr.length>1){
              _this.waterProject.sbsnCodes = cysbStr.substring(0,cysbStr.length-1);
            }
            _this.$forceUpdate();
          }
        })
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.waterProjectDto.page = page;
        _this.waterProjectDto.size = _this.$refs.pagination.size
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterProject/list', _this.waterProjectDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.waterProjects = resp.content.list;
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
                || !Validator.length(_this.waterProject.xmbh, "项目编号", 1, 36)
                || !Validator.length(_this.waterProject.xmmc, "项目名称", 1, 512)
                || !Validator.length(_this.waterProject.fzr, "项目负责人", 1, 128)
                || !Validator.length(_this.waterProject.deptcode, "所属部门", 1, 36)
                || !Validator.length(_this.waterProject.cjr, "创建人", 1, 128)
                || !Validator.length(_this.waterProject.xmyt, "项目用途", 1, 1024)
                || !Validator.length(_this.waterProject.sm1, "说明1", 1, 1024)
                || !Validator.length(_this.waterProject.sm2, "说明2", 1, 1024)
                || !Validator.length(_this.waterProject.sm3, "说明3", 1, 1024)
                || !Validator.length(_this.waterProject.sm4, "说明4", 1, 1024)
                || !Validator.length(_this.waterProject.sm5, "说明5", 1, 1024)
                || !Validator.require(_this.waterProject.xmbh, "项目编号")
                || !Validator.require(_this.waterProject.xmmc, "项目名称")
                || !Validator.require(_this.waterProject.kssj, "项目开始时间")
                || !Validator.require(_this.waterProject.jssj, "项目结束时间")
                || !Validator.require(_this.waterProject.fzr, "项目负责人")
                || !Validator.require(_this.waterProject.deptcode, "所属部门")
                || !Validator.require(_this.waterProject.xmyt, "项目用途")
                || !Validator.require(_this.waterProject.userCodes, "参与人员")
                || !Validator.require(_this.waterProject.sbsnCodes, "涉及设备")
        ) {
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterProject/save', _this.waterProject).then((response)=>{
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
       * 详情
       */
      detail(waterProject){
        let _this = this;
        _this.waterProject = $.extend({}, waterProject);
        $("#form-modal-detail").modal("show");
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("删除后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/waterProject/delete/' + id).then((response)=>{
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