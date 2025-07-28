<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">代码管理</h4>
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
                      代码类别：
                    </td>
                    <td style="width: 10%">
                      <select v-model="codesetDto.type" class="input-sm">
                        <option value="" selected>请选择</option>
                        <option v-for="o in alltype" v-bind:value="o.code">{{o.name}}</option>
                      </select>
                    </td>
                    <td style="width: 8%" >
                      代码值：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-2"  v-model="codesetDto.code"/>
                    </td>
                    <td style="width: 8%" >
                      代码名称：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-1"  v-model="codesetDto.name"/>
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
            <th>代码值</th>
            <th>名称</th>
            <th>代码类别</th>
            <th>描述</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="codeset in codesets">
              <td>{{codeset.code}}</td>
              <td>{{codeset.name}}</td>
              <td>
              <template  v-for="all in alltype.filter(t=>{return t.code===codeset.type})">
                   {{all.name}}
              </template>
              </td>
              <td>{{codeset.content}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(codeset)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(codeset.id)" class="btn btn-xs btn-danger" title="删除">
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
            <h4 class="modal-title">代码管理</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">代码值</label>
                      <div class="col-sm-10">
                        <input v-model="codeset.code" v-bind:disabled="codeset.id" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">名称</label>
                      <div class="col-sm-10">
                        <input v-model="codeset.name" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">代码类别</label>
                      <div class="col-sm-10">
                        <select v-model="codeset.type" class="form-control">
                          <option v-for="o in alltype" v-bind:value="o.code">{{o.name}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">描述</label>
                      <div class="col-sm-10">
                        <input v-model="codeset.content" class="form-control">
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
    name: "system-codeset",
    data: function() {
      return {
      codeset: {},
        codesetDto:{},
      codesets: [],
        alltype:[],//所有业务种类
    }
    },
    mounted: function() {
      let _this = this;
      this.gatAlltype();
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("system-codeset-sidebar");

    },
    methods: {
      /**
       * 获取业务类型
       */
      gatAlltype(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/codeset/getAlltype').then((res)=>{
          let response = res.data;
          _this.alltype = response.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.codeset = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(codeset) {
        let _this = this;
        _this.codeset = $.extend({}, codeset);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.codesetDto.page=page;
        _this.codesetDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/codeset/list',_this.codesetDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.codesets = resp.content.list;
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
                || !Validator.require(_this.codeset.code, "代码值")
                || !Validator.require(_this.codeset.name, "名称", )
                || !Validator.require(_this.codeset.type, "代码类别")
                || !Validator.length(_this.codeset.code, "代码值", 1, 100)
                || !Validator.length(_this.codeset.name, "名称", 1, 100)
                || !Validator.length(_this.codeset.type, "代码类别", 1, 100)
                || !Validator.length(_this.codeset.content, "描述", 1, 100)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/codeset/save', _this.codeset).then((response)=>{
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
        Confirm.show("删除代码维护后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/codeset/delete/' + id).then((response)=>{
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