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
                    <th></th>
            <th>SIM卡号</th>
            <th>来向</th>
            <th>来向速度</th>
            <th>去向</th>
            <th>去向速度</th>
            <th>IP和端口</th>
            <th>时间</th>
            <th>所属部门</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="ldData in ldDatas">
              <td>{{ldData.id}}</td>
              <td>{{ldData.iccid}}</td>
              <td>{{ldData.lx}}</td>
              <td>{{ldData.lxsd}}</td>
              <td>{{ldData.qx}}</td>
              <td>{{ldData.qxsd}}</td>
              <td>{{ldData.ipport}}</td>
              <td>{{ldData.sjdate}}</td>
              <td>{{ldData.deptcode}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(ldData)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(ldData.id)" class="btn btn-xs btn-danger">
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
                      <label class="col-sm-2 control-label">SIM卡号</label>
                      <div class="col-sm-10">
                        <input v-model="ldData.iccid" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">来向</label>
                      <div class="col-sm-10">
                        <input v-model="ldData.lx" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">来向速度</label>
                      <div class="col-sm-10">
                        <input v-model="ldData.lxsd" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">去向</label>
                      <div class="col-sm-10">
                        <input v-model="ldData.qx" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">去向速度</label>
                      <div class="col-sm-10">
                        <input v-model="ldData.qxsd" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">IP和端口</label>
                      <div class="col-sm-10">
                        <input v-model="ldData.ipport" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">时间</label>
                      <div class="col-sm-10">
                        <input v-model="ldData.sjdate" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">所属部门</label>
                      <div class="col-sm-10">
                        <input v-model="ldData.deptcode" class="form-control">
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
    name: "power-ldData",
    data: function() {
      return {
      ldData: {},
      ldDatas: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("power-ldData-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.ldData = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(ldData) {
        let _this = this;
        _this.ldData = $.extend({}, ldData);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/power/admin/ldData/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.ldDatas = resp.content.list;
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
                || !Validator.length(_this.ldData.iccid, "SIM卡号", 1, 32)
                || !Validator.length(_this.ldData.lx, "来向", 1, 32)
                || !Validator.length(_this.ldData.lxsd, "来向速度", 1, 32)
                || !Validator.length(_this.ldData.qx, "去向", 1, 32)
                || !Validator.length(_this.ldData.qxsd, "去向速度", 1, 32)
                || !Validator.length(_this.ldData.ipport, "IP和端口", 1, 32)
                || !Validator.length(_this.ldData.deptcode, "所属部门", 1, 32)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/power/admin/ldData/save', _this.ldData).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/power/admin/ldData/delete/' + id).then((response)=>{
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