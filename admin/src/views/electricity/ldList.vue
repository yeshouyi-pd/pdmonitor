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
                    <th>SIM卡卡号</th>
            <th>经度</th>
            <th>纬度</th>
            <th>设备描述</th>
            <th>设备IP:端口</th>
            <th>状态0:离线;1:在线</th>
            <th>最后在线时间</th>
            <th>部门代码</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="ldList in ldLists">
              <td>{{ldList.iccid}}</td>
              <td>{{ldList.jd}}</td>
              <td>{{ldList.wd}}</td>
              <td>{{ldList.sbms}}</td>
              <td>{{ldList.ipport}}</td>
              <td>{{ldList.state}}</td>
              <td>{{ldList.upsj}}</td>
              <td>{{ldList.deptcode}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(ldList)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(ldList.id)" class="btn btn-xs btn-danger">
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
                      <label class="col-sm-2 control-label">SIM卡卡号</label>
                      <div class="col-sm-10">
                        <input v-model="ldList.iccid" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">经度</label>
                      <div class="col-sm-10">
                        <input v-model="ldList.jd" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">纬度</label>
                      <div class="col-sm-10">
                        <input v-model="ldList.wd" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备描述</label>
                      <div class="col-sm-10">
                        <input v-model="ldList.sbms" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备IP:端口</label>
                      <div class="col-sm-10">
                        <input v-model="ldList.ipport" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">状态0:离线;1:在线</label>
                      <div class="col-sm-10">
                        <input v-model="ldList.state" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">最后在线时间</label>
                      <div class="col-sm-10">
                        <input v-model="ldList.upsj" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">部门代码</label>
                      <div class="col-sm-10">
                        <input v-model="ldList.deptcode" class="form-control">
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
    name: "power-ldList",
    data: function() {
      return {
      ldList: {},
      ldLists: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("power-ldList-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.ldList = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(ldList) {
        let _this = this;
        _this.ldList = $.extend({}, ldList);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/power/admin/ldList/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.ldLists = resp.content.list;
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
                || !Validator.require(_this.ldList.iccid, "SIM卡卡号")
                || !Validator.length(_this.ldList.iccid, "SIM卡卡号", 1, 32)
                || !Validator.length(_this.ldList.jd, "经度", 1, 32)
                || !Validator.length(_this.ldList.wd, "纬度", 1, 32)
                || !Validator.length(_this.ldList.sbms, "设备描述", 1, 256)
                || !Validator.length(_this.ldList.ipport, "设备IP:端口", 1, 32)
                || !Validator.length(_this.ldList.state, "状态0:离线;1:在线", 1, 32)
                || !Validator.length(_this.ldList.deptcode, "部门代码", 1, 32)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/power/admin/ldList/save', _this.ldList).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/power/admin/ldList/delete/' + id).then((response)=>{
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