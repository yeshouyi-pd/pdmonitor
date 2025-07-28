<template>
  <div>
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
            <th>所属监测点</th>
            <th>代码</th>
            <th>名称</th>
            <th>用户名</th>
            <th>密码</th>
            <th>说明</th>
            <th>备注</th>
<!--            <th>说明1</th>-->
<!--            <th>说明2</th>-->
<!--            <th>说明3</th>-->
<!--            <th>说明4</th>-->
            <th>创建时间</th>
            <th>创建人员</th>
            <th>更新时间</th>
            <th>更新人员</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="waterData in waterDatas">
              <td>{{deptMap|optionMapKV(waterData.deptcode)}}</td>
              <td>{{waterData.centerCode}}</td>
              <td>{{waterData.centerName}}</td>
              <td>{{waterData.ip}}</td>
              <td>{{waterData.port}}</td>
              <td>{{waterData.sm}}</td>
              <td>{{waterData.bz}}</td>
<!--              <td>{{waterData.sm1}}</td>-->
<!--              <td>{{waterData.sm2}}</td>-->
<!--              <td>{{waterData.sm3}}</td>-->
<!--              <td>{{waterData.sm4}}</td>-->
              <td>{{waterData.createTime}}</td>
              <td>{{waterData.createBy}}</td>
              <td>{{waterData.gxsj}}</td>
              <td>{{waterData.updateBy}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(waterData)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
<!--            <button v-on:click="del(waterData.id)" class="btn btn-xs btn-danger">-->
<!--              <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--            </button>-->
          </div>
        </td>
      </tr>
      </tbody>
    </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">*代码</label>
                      <div class="col-sm-4">
                        <input v-model="waterData.centerCode" v-bind:disabled="waterData.id" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">*名称</label>
                      <div class="col-sm-4">
                        <input v-model="waterData.centerName" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
<!--                      <label class="col-sm-2 control-label">*IP</label>-->
                      <label class="col-sm-2 control-label">*用户名</label>
                      <div class="col-sm-4">
                        <input v-model="waterData.ip" class="form-control">
                      </div>
<!--                      <label class="col-sm-2 control-label">*端口</label>-->
                      <label class="col-sm-2 control-label">*密码</label>
                      <div class="col-sm-4">
                        <input v-model="waterData.port" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">*所属监测点</label>
                      <div class="col-sm-4 add-input" v-on:click="chooseDept()" style="cursor: pointer">
                        <input v-bind:value="chooseDeptName" class="form-control" readonly style="cursor: pointer;padding-left: 20px">
                        <i></i>
                      </div>
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-4">
                        <input v-model="waterData.bz" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">说明</label>
                      <div class="col-sm-4">
                        <input v-model="waterData.sm" class="form-control">
                      </div>
<!--                      <label class="col-sm-2 control-label">说明1</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="waterData.sm1" class="form-control">-->
<!--                      </div>-->
                    </div>
<!--                    <div class="form-group">-->
<!--                      <label class="col-sm-2 control-label">说明2</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="waterData.sm4" class="form-control">-->
<!--                      </div>-->
<!--                      <label class="col-sm-2 control-label">说明3</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="waterData.sm2" class="form-control">-->
<!--                      </div>-->
<!--                    </div>-->
<!--                    <div class="form-group">-->
<!--                      <label class="col-sm-2 control-label">说明4</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="waterData.sm3" class="form-control">-->
<!--                      </div>-->
<!--                    </div>-->
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
  export default {
    components: {Pagination},
    name: "monitor-waterData",
    data: function() {
      return {
        waterData: {},
        waterDatas: [],
        deptMap:[],
        trees:[],
        checkHeightMax:'',
        chooseDeptName:'',
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getDeptTree();
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-waterData-sidebar");
      _this.deptMap = Tool.getDeptUser();
      let h = document.documentElement.clientHeight || document.body.clientHeight;
      _this.checkHeightMax = h*0.8;
    },
    methods: {
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
        _this.waterData.deptcode = treeNode.deptcode;
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
        _this.waterData = {};
        _this.chooseDeptName = '';
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(waterData) {
        let _this = this;
        _this.waterData = $.extend({}, waterData);
        _this.chooseDeptName = _this.optionMapKV(_this.deptMap,_this.waterData.deptcode);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {
          Loading.hide();
          let resp = response.data;
          _this.waterDatas = resp.content.list;
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
            || !Validator.require(_this.waterData.centerCode, "代码")
            || !Validator.length(_this.waterData.centerCode, "代码", 1, 45)
            || !Validator.require(_this.waterData.centerName, "名称")
            || !Validator.length(_this.waterData.centerName, "名称", 1, 45)
            || !Validator.require(_this.waterData.ip, "用户名")
            || !Validator.length(_this.waterData.ip, "用户名", 1, 45)
            || !Validator.require(_this.waterData.port, "密码")
            || !Validator.require(_this.waterData.deptcode, "所属监测点")
            || !Validator.length(_this.waterData.sm, "说明", 1, 400)
            || !Validator.length(_this.waterData.bz, "备注", 1, 400)
            || !Validator.length(_this.waterData.sm1, "说明1", 1, 400)
            || !Validator.length(_this.waterData.sm2, "说明2", 1, 400)
            || !Validator.length(_this.waterData.sm3, "说明3", 1, 400)
            || !Validator.length(_this.waterData.sm4, "说明4", 1, 45)
            || !Validator.length(_this.waterData.createBy, "创建人员", 1, 128)
            || !Validator.length(_this.waterData.updateBy, "更新人员", 1, 128)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/save', _this.waterData).then((response) => {
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
        Confirm.show("删除数据中心后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/delete/' + id).then((response) => {
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
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