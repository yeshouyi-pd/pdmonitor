<template>
  <div>
    <!-- query start -->
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">参数查询</h4>
        <div class="widget-toolbar">
          <a href="#" data-action="collapse">
            <i class="ace-icon fa fa-chevron-up"></i>
          </a>
        </div>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form >
            <table  style="font-size: 1.1em;" class="text-right  " >
              <tbody  >
              <tr  >
                <td style="width: 15%" >
                  属性代码：
                </td>
                <td style="width: 25%">
                  <input v-model="attrDto.attrcode"  class="form-control">
                </td>
                <td style="width: 15%" >
                  说明：
                </td>
                <td style="width: 25%">
                  <input v-model="attrDto.content"  class="form-control">
                </td>
                <td style="width: 20%">
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
            <th>属性代码</th>
            <th>属性值</th>
            <th>说明</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="attr in attrs">
              <td>{{attr.attrcode}}</td>
              <td>{{attr.attrkey}}</td>
              <td>{{attr.content}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(attr)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(attr.id)" class="btn btn-xs btn-danger" title="删除">
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
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">属性代码</label>
                      <div class="col-sm-10">
                        <input v-model="attr.attrcode" v-bind:disabled="attr.id" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">属性值</label>
                      <div class="col-sm-10">
                        <input v-model="attr.attrkey" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">说明</label>
                      <div class="col-sm-10">
                        <input v-model="attr.content" class="form-control">
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
    name: "system-attr",
    data: function() {
      return {
      attr: {},
      attrs: [],
      attrDto:{},//查询条件
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("system-attr-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.attr = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(attr) {
        let _this = this;
        _this.attr = $.extend({}, attr);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.attrDto.page = page;
        _this.attrDto.size = _this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/attr/list', _this.attrDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.attrs = resp.content.list;
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
                || !Validator.require(_this.attr.attrcode,"属性代码")
                || !Validator.length(_this.attr.attrcode, "属性代码", 1, 100)
                || !Validator.length(_this.attr.attrkey, "属性值", 1, 1000)
                || !Validator.length(_this.attr.content, "说明", 1, 256)
        ) {
          return;
        }


        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/attr/save', _this.attr).then((response)=>{
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
        Confirm.show("删除属性设置后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/attr/delete/' + id).then((response)=>{
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