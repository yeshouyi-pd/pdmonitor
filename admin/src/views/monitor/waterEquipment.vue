<template>
  <div>
    <div style="margin-bottom: 20px;">
      <ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
        <li class="active" v-on:click="changeTab()">
          <a data-toggle="tab" href="#faq-tab-1" aria-expanded="true">
            列表模式
          </a>
        </li>
        <li class="" v-on:click="changeTab()">
          <a data-toggle="tab" href="#faq-tab-2" aria-expanded="false">
            地图模式
          </a>
        </li>
      </ul>
    </div>

    <div v-if="defaultShow">
      <div class="widget-box">
        <div class="widget-header">
          <h4 class="widget-title">设备管理查询</h4>
        </div>
        <div class="widget-body">
          <div class="widget-main">
            <form>
              <table style="font-size: 1.1em;width:80%" class="text-right">
                <tbody>
                <tr>
                  <td style="width:10%">
                    设备SN：
                  </td>
                  <td style="width: 15%">
                    <input class="input-sm" type="text"  v-model="waterEquipmentDto.sbsn"/>
                  </td>
                  <td style="width: 10%">
                    设备类别：
                  </td>
                  <td style="width: 20%">
                    <select v-model="waterEquipmentDto.sblb" style="width: 100%;">
                      <option value="">请选择</option>
                      <option v-for="(key,value) in sblbs" :value="value">{{key}}</option>
                    </select>
                  </td>
                  <td colspan="2" class="text-center">
                    <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
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
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
          <tr>
              <th>设备名称</th>
              <th>设备SN</th>
  <!--            <th>设备端口</th>-->
  <!--            <th>设备IP</th>-->
              <th>所属监测点</th>
              <th>所属数据中心</th>
              <th>设备类别</th>
              <th>设备读取指令</th>
              <th>设备型号</th>
              <th>设备gps坐标</th>
<!--              <th>设备负责人</th>-->
<!--              <th>负责人电话</th>-->
<!--              <th>设备量程</th>-->
<!--              <th>设备进度范围</th>-->
              <th>设备放置位置</th>
              <th>SIM卡卡号</th>
              <th>设备状态</th>
            <th>操作</th>
          </tr>
        </thead>

        <tbody>
        <tr v-for="waterEquipment in waterEquipments">
                <td>{{waterEquipment.sbmc}}</td>
                <td>{{waterEquipment.sbsn}}</td>
  <!--              <td>{{waterEquipment.port}}</td>-->
  <!--              <td>{{waterEquipment.ip}}</td>-->
                <td>{{deptMap|optionMapKV(waterEquipment.deptcode)}}</td>
                <td>{{waterDatas|optionWDArray(waterEquipment.centerCode)}}</td>
                <td>{{sblbs|optionMapKV(waterEquipment.sblb)}}</td>
                <td>{{waterEquipment.dqzl}}</td>
                <td>{{waterEquipment.sbxh}}</td>
                <td>{{waterEquipment.gps}}</td>
<!--                <td>{{waterEquipment.fzr}}</td>-->
<!--                <td>{{waterEquipment.fzrdh}}</td>-->
<!--                <td>{{waterEquipment.sblc}}</td>-->
<!--                <td>{{waterEquipment.jdfw}}</td>-->
                <td>{{waterEquipment.fzwz}}</td>
                <td>{{waterEquipment.sbcj}}</td>
          <td><span v-if="waterEquipment.sbzt=='1'">正常</span><span v-if="waterEquipment.sbzt=='2'">离线</span><span v-if="waterEquipment.sbzt=='3'">设备故障</span></td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(waterEquipment)" class="btn btn-xs btn-info" title="修改">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
              </button>
              <button v-on:click="restart(waterEquipment.sbcj)" class="btn btn-xs btn-info" title="重启">
                <i class="ace-icon fa fa-refresh bigger-120"></i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>

    <div v-else><equipment-map></equipment-map></div>

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
                      <label class="col-sm-2 control-label">*设备名称</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.sbmc" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">设备SN</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.sbsn" v-bind:disabled="waterEquipment.id" class="form-control">
                      </div>
                    </div>
<!--                    <div class="form-group">-->
<!--                      <label class="col-sm-2 control-label">*设备IP</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="waterEquipment.ip" v-bind:disabled="waterEquipment.id" class="form-control">-->
<!--                      </div>-->
<!--                      <label class="col-sm-2 control-label">*设备端口</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="waterEquipment.port" v-bind:disabled="waterEquipment.id" class="form-control">-->
<!--                      </div>-->
<!--                    </div>-->
                    <div class="form-group">
                      <label class="col-sm-2 control-label">*所属数据中心</label>
                      <div class="col-sm-4">
<!--                        <input v-model="waterEquipment.centerCode" v-bind:disabled="waterEquipment.id" class="form-control">-->
                        <select v-model="waterEquipment.centerCode" style="width: 100%;" v-bind:disabled="waterEquipment.id">
                          <option v-for="item in waterDatas" :value="item.centerCode">{{item.centerName}}</option>
                        </select>
                      </div>
                      <label class="col-sm-2 control-label">*所属监测点</label>
                      <div class="col-sm-4 add-input" v-on:click="chooseDept()" style="cursor: pointer">
                        <input v-bind:value="chooseDeptName" class="form-control" readonly style="cursor: pointer;padding-left: 20px">
                        <i></i>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备读取指令</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.dqzl" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">设备类别</label>
                      <div class="col-sm-4">
<!--                        <input v-model="waterEquipment.sblb" class="form-control">-->
                        <select v-model="waterEquipment.sblb" style="width: 100%;">
                          <option v-for="(key,value) in sblbs" :value="value">{{key}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备gps坐标</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.gps" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">设备型号</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.sbxh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备负责人</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.fzr" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">负责人电话</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.fzrdh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备量程</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.sblc" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">设备进度范围</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.jdfw" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">*设备状态</label>
                      <div class="col-sm-4">
                        <div class="radio">
                          <label>
                            <input name="form-sbzt-radio" type="radio"  value="1" v-model="waterEquipment.sbzt" class="ace input-lg" />
                            <span class="lbl bigger-120">正常</span>
                          </label>
                          <label>
                            <input name="form-sbzt-radio" type="radio"  value="2"  v-model="waterEquipment.sbzt" class="ace input-lg" />
                            <span class="lbl bigger-120">离线</span>
                          </label>
                          <label>
                            <input name="form-sbzt-radio" type="radio"  value="3"  v-model="waterEquipment.sbzt" class="ace input-lg" />
                            <span class="lbl bigger-120">设备故障</span>
                          </label>
                        </div>
                      </div>
                      <label class="col-sm-2 control-label">设备放置位置</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.fzwz" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">SIM卡卡号</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.sbcj" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-4">
                        <input v-model="waterEquipment.bz" class="form-control">
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

    <div id="tree-modal" class="modal fade" tabindex="-1"  role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">双击选择监测点</h4>
          </div>
          <div class="modal-body" :style="{height: checkHeightMax + 'px' ,overflowY: 'auto'}">
            <ul id="tree" class="ztree"></ul>
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
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import EquipmentMap from "@/views/monitor/equipmentMap";
  export default {
    components: {Pagination, EquipmentMap},
    name: "monitor-waterEquipment",
    data: function() {
      return {
        waterEquipmentDto: {},
        waterEquipment: {},
        waterEquipments: [],
        waterDatas:[],
        deptMap:[],
        trees:[],
        checkHeightMax:'',
        chooseDeptName:'',
        sblbs:[],
        defaultShow:true
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getDeptTree();
      _this.findWaterData();
      _this.getSblb();
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-waterData-sidebar");
      _this.deptMap = Tool.getDeptUser();
      let h = document.documentElement.clientHeight || document.body.clientHeight;
      _this.checkHeightMax = h*0.8;
    },
    methods: {
      restart(sbcj){
        let _this = this;
        Loading.show();
        if(!Tool.isEmpty(sbcj)){
          _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/ldTaskListSec/restart/'+sbcj).then((res) => {
            Loading.hide();
            let response = res.data;
            if(response.success){
              Toast.success("执行完毕");
            }else{
              Toast.success("执行出错，请联系管理员");
            }
          })
        }else{
          Toast.success("没有SIM卡卡号，不执行！");
          Loading.hide();
        }
      },
      changeTab(){
        let _this = this;
        _this.defaultShow = !_this.defaultShow;
        if(!_this.defaultShow){
          $("map-top").css("top","58px");
        }
      },
      /**
       * 获取设备型号
       */
      getSblb(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getSblb').then((res) => {
          let response = res.data;
          _this.sblbs = response.content;
        })
      },
      getDeptTree() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/dept/load-deptTree').then((res) => {
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
            key: {
              name: "deptname",
            },
            simpleData: {
              idKey: "deptcode",
              pIdKey: "upcode",
              rootPId: "0",
              enable: true
            }
          }, callback: {
            onDblClick: _this.zTreeOnDblClick
          },
        };
        _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.trees);
        _this.zTree.expandAll(true);
      },
      zTreeOnDblClick(event, treeId, treeNode) {
        let _this = this;
        _this.waterEquipment.deptcode = treeNode.deptcode;
        _this.chooseDeptName = treeNode.deptname;
        _this.$forceUpdate();
        $("#tree-modal").modal("hide");
      },
      /**
       * 选择所属监测点
       */
      chooseDept() {
        $("#tree-modal").modal("show");
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.waterEquipment = {};
        _this.chooseDeptName = '';
        _this.waterEquipment.sbzt = '1';
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(waterEquipment) {
        let _this = this;
        _this.waterEquipment = $.extend({}, waterEquipment);
        _this.chooseDeptName = _this.optionMapKV(_this.deptMap,_this.waterEquipment.deptcode);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.waterEquipmentDto.page = page;
        _this.waterEquipmentDto.size = _this.$refs.pagination.size;
        if("460100"!=Tool.getLoginUser().deptcode){
          _this.waterEquipmentDto.xmbh = Tool.getLoginUser().xmbh;
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/list', _this.waterEquipmentDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.waterEquipments = resp.content.list;
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
                || !Validator.require(_this.waterEquipment.sbmc, "设备名称")
                || !Validator.length(_this.waterEquipment.sbmc, "设备名称", 1, 128)
                // || !Validator.require(_this.waterEquipment.sbsn, "设备SN")
                || !Validator.length(_this.waterEquipment.sbsn, "设备SN", 1, 128)
                //|| !Validator.require(_this.waterEquipment.port, "设备端口")
                //|| !Validator.require(_this.waterEquipment.ip, "设备IP")
                || !Validator.length(_this.waterEquipment.ip, "设备IP", 1, 45)
                || !Validator.require(_this.waterEquipment.deptcode, "所属监测点")
                || !Validator.length(_this.waterEquipment.deptcode, "所属监测点", 1, 45)
                || !Validator.require(_this.waterEquipment.centerCode, "所属数据中心")
                || !Validator.length(_this.waterEquipment.centerCode, "所属数据中心", 1, 45)
                || !Validator.require(_this.waterEquipment.sblb, "设备类别")
                || !Validator.length(_this.waterEquipment.sblb, "设备类别", 1, 45)
                // || !Validator.require(_this.waterEquipment.dqzl, "设备读取指令")
                || !Validator.length(_this.waterEquipment.dqzl, "设备读取指令", 1, 45)
                // || !Validator.require(_this.waterEquipment.sbxh, "设备型号")
                || !Validator.length(_this.waterEquipment.sbxh, "设备型号", 1, 45)
                // || !Validator.require(_this.waterEquipment.gps, "设备gps坐标")
                || !Validator.length(_this.waterEquipment.gps, "设备gps坐标", 1, 45)
                // || !Validator.require(_this.waterEquipment.fzr, "设备负责人")
                || !Validator.length(_this.waterEquipment.fzr, "设备负责人", 1, 128)
                // || !Validator.require(_this.waterEquipment.fzrdh, "负责人电话")
                ||!Validator.checkIsGH(_this.waterEquipment.fzrdh, "负责人电话")
                || !Validator.length(_this.waterEquipment.fzrdh, "负责人电话", 1, 45)
                // || !Validator.require(_this.waterEquipment.sblc, "设备量程")
                || !Validator.length(_this.waterEquipment.sblc, "设备量程", 1, 45)
                // || !Validator.require(_this.waterEquipment.jdfw, "设备进度范围")
                || !Validator.length(_this.waterEquipment.jdfw, "设备进度范围", 1, 128)
                // || !Validator.require(_this.waterEquipment.fzwz, "设备放置位置")
                || !Validator.length(_this.waterEquipment.fzwz, "设备放置位置", 1, 450)
                // || !Validator.require(_this.waterEquipment.sbcj, "SIM卡卡号")
                || !Validator.length(_this.waterEquipment.sbcj, "SIM卡卡号", 1, 450)
                || !Validator.length(_this.waterEquipment.sbzt, "设备状态", 1, 1)
                || !Validator.length(_this.waterEquipment.bz, "备注", 1, 2000)
                || !Validator.length(_this.waterEquipment.createBy, "创建人", 1, 128)
                || !Validator.length(_this.waterEquipment.updateBy, "更新人", 1, 128)
                || !Validator.length(_this.waterEquipment.sm1, "", 1, 450)
                || !Validator.length(_this.waterEquipment.sm2, "", 1, 450)
                || !Validator.length(_this.waterEquipment.sm3, "", 1, 450)
        ) {
          return;
        }
        if(_this.waterEquipment.gps){
          let arr = _this.waterEquipment.gps.split(",");
          if(!(arr && arr.length==2)){
            return Toast.warning("设备gps坐标格式填写错误");
          }
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/save', _this.waterEquipment).then((response)=>{
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
        Confirm.show("删除设备管理后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      },

      /**
       * 查询数据中心
       */
      findWaterData(){
        let _this = this;
        let userInfo = Tool.getLoginUser();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/findAll/' + userInfo.deptcode).then((response)=>{
          let resp = response.data;
          if (resp.success) {
            _this.waterDatas = resp.content;
          }
        })
      },
      optionMapKV(object, key){
        if (!object || !key) {
          return "";
        } else {
          let result = "";
          for(let enums in object){
            if (key === enums) {
              result = object[enums];
            }
          }
          return result;
        }
      }
    }
  }
</script>