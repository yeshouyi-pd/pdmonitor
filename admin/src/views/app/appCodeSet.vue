<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">代码集管理</h4>
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
                      代码类型：
                    </td>
                    <td style="width: 10%">
                      <select v-model="appCodeSetDto.typeValue" class="input-sm">
                        <option value="" selected>请选择</option>
                        <option v-for="(name, value) in allType" v-bind:value="value">{{name}}</option>
                      </select>
                    </td>
                    <td style="width: 8%" >
                      代码值：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-1"  v-model="appCodeSetDto.codeValue"/>
                    </td>
                    <td style="width: 8%" >
                      代码名称：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-2"  v-model="appCodeSetDto.codeName"/>
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
        新增代码
      </button>&nbsp;
      <div class="space-2"></div>
    </div>



      <div>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>代码类型</th>
            <th>代码值</th>
            <th>代码名称</th>
            <th>创建时间</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="appCodeSet in appCodeSets">
              <td>
                <template v-for="(name, value) in allType">
                  <span v-if="value === appCodeSet.typeValue">{{name}}</span>
                </template>
              </td>
              <td>{{appCodeSet.codeValue}}</td>
              <td>{{appCodeSet.codeName}}</td>
              <td>{{appCodeSet.createTime}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(appCodeSet)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(appCodeSet.id)" class="btn btn-xs btn-danger" title="删除">
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
            <h4 class="modal-title">代码集管理</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">代码类型</label>
                      <div class="col-sm-10">
                        <select v-model="appCodeSet.typeValue" class="form-control">
                          <option value="" selected>请选择</option>
                          <option v-for="(name, value) in allType" v-bind:value="value">{{name}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">代码值</label>
                      <div class="col-sm-10">
                        <input v-model="appCodeSet.codeValue" v-bind:disabled="appCodeSet.id" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">代码名称</label>
                      <div class="col-sm-10">
                        <input v-model="appCodeSet.codeName" class="form-control">
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
    name: "admin-appCodeSet",
    data: function() {
      return {
      appCodeSet: {},
        appCodeSetDto:{},
      appCodeSets: [],
        allType:{},//所有代码类型
    }
    },
    mounted: function() {
      let _this = this;
      this.getAllType();
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("app-appCodeSet-sidebar");

    },
    methods: {
      /**
       * 获取所有代码类型
       */
      getAllType(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/appCodeSet/getAllType').then((res)=>{
          let response = res.data;
          _this.allType = response.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.appCodeSet = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(appCodeSet) {
        let _this = this;
        _this.appCodeSet = $.extend({}, appCodeSet);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.appCodeSetDto.page=page;
        _this.appCodeSetDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/appCodeSet/list',_this.appCodeSetDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.appCodeSets = resp.content.list;
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
                || !Validator.require(_this.appCodeSet.typeValue, "代码类型")
                || !Validator.require(_this.appCodeSet.codeValue, "代码值")
                || !Validator.require(_this.appCodeSet.codeName, "代码名称")
                || !Validator.length(_this.appCodeSet.typeValue, "代码类型", 1, 100)
                || !Validator.length(_this.appCodeSet.codeValue, "代码值", 1, 100)
                || !Validator.length(_this.appCodeSet.codeName, "代码名称", 1, 255)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/appCodeSet/save', _this.appCodeSet).then((response)=>{
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
        Confirm.show("删除代码后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/appCodeSet/delete/' + id).then((response)=>{
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