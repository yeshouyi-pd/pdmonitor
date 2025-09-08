<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">类型管理</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;width:80%" class="text-right  " >
                  <tbody  >
                  <tr  >
                    <td style="width:8%" >
                      类型名称：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-1"  v-model="appCodeTypeDto.typeName"/>
                    </td>
                    <td style="width: 8%" >
                      类型代码：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-2"  v-model="appCodeTypeDto.typeValue"/>
                    </td>
                    <td   colspan="2" class="text-center">
                      <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      &nbsp;&nbsp;&nbsp;
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
        <!-- query end -->
      </div><!-- col-md-12 -->
    </div><!-- row -->

    <div style="background: #F5F5F5;margin-top: -2px">
      <div class="space-2"></div>
      <button v-on:click="add()" class="btn  btn-sm btn-success btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增类型
      </button>&nbsp;
      <div class="space-2"></div>
    </div>



      <div>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>类型代码</th>
            <th>类型名称</th>
            <th>创建时间</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="appCodeType in appCodeTypes">
              <td>{{appCodeType.typeValue}}</td>
              <td>{{appCodeType.typeName}}</td>
              <td>{{appCodeType.createTime}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(appCodeType)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(appCodeType.id)" class="btn btn-xs btn-danger" title="删除">
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
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">类型管理</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">类型代码</label>
                      <div class="col-sm-10">
                        <input v-model="appCodeType.typeValue" v-bind:disabled="appCodeType.id" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">类型名称</label>
                      <div class="col-sm-10">
                        <input v-model="appCodeType.typeName" class="form-control">
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
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "admin-appCodeType",
    data: function() {
      return {
      appCodeType: {},
        appCodeTypeDto:{},
      appCodeTypes: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("app-appCodeType-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.appCodeType = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(appCodeType) {
        let _this = this;
        _this.appCodeType = $.extend({}, appCodeType);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.appCodeTypeDto.page=page;
        _this.appCodeTypeDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/appCodeType/list',_this.appCodeTypeDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.appCodeTypes = resp.content.list;
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
                || !Validator.require(_this.appCodeType.typeValue, "类型代码")
                || !Validator.require(_this.appCodeType.typeName, "类型名称")
                || !Validator.length(_this.appCodeType.typeValue, "类型代码", 1, 255)
                || !Validator.length(_this.appCodeType.typeName, "类型名称", 1, 255)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/appCodeType/save', _this.appCodeType).then((response)=>{
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
        Confirm.show("删除类型后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/appCodeType/delete/' + id).then((response)=>{
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