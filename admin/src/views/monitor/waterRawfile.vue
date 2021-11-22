<template>
  <div>
    <p>
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
            <th>设备编号</th>
            <th>文件类型</th>
            <th>文件路径</th>
            <th>上传采集时间</th>
            <th>分析次数</th>
            <th>状态</th>
            <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="waterRawfile in waterRawfiles">
              <td>{{waterRawfile.xmbh}}</td>
              <td>{{waterRawfile.sbbh}}</td>
              <td>{{waterRawfile.wjlx}}</td>
              <td>{{waterRawfile.wjlj}}</td>
              <td>{{waterRawfile.cjsj}}</td>
              <td>{{waterRawfile.fxcs}}</td>
              <td>{{waterRawfile.zt|getStatesType}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(waterRawfile)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(waterRawfile.id)" class="btn btn-xs btn-danger">
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
                      <label class="col-sm-2 control-label">项目编号</label>
                      <div class="col-sm-10">
                        <input v-model="waterRawfile.xmbh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备编号</label>
                      <div class="col-sm-10">
                        <input v-model="waterRawfile.sbbh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">文件类型</label>
                      <div class="col-sm-10">
                        <input v-model="waterRawfile.wjlx" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">文件路径</label>
                      <div class="col-sm-10">
                        <input v-model="waterRawfile.wjlj" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">上传采集时间</label>
                      <div class="col-sm-10">
                        <input v-model="waterRawfile.cjsj" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">分析次数</label>
                      <div class="col-sm-10">
                        <input v-model="waterRawfile.fxcs" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">状态</label>
                      <div class="col-sm-10">
                        <input v-model="waterRawfile.zt" class="form-control">
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
    name: "monitor-waterRawfile",
    data: function() {
          return {
              waterRawfile: {},
              waterRawfiles: [],
              waterRawfileDto: {},
          }
    },
    filters: {
      getStatesType(type) {
          const map = new Map([['0', '未分析'], ['1', '已分析'], ['2', '分析失败']]);
          return map.get(type);
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-waterRawfile-sidebar");

    },
    methods: {
      getStatesType(type) {
        const map = new Map([['0', '未分析'], ['1', '已分析'], ['2', '分析失败']]);
        return map.get(type);
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.waterRawfile = {};
        $("#form-modal").modal("show");
      },
      /**
       * 点击【编辑】
       */
      edit(waterRawfile) {
        let _this = this;
        _this.waterRawfile = $.extend({}, waterRawfile);
        _this.waterRawfile.zt = _this.getStatesType(_this.waterRawfile.zt);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.waterRawfileDto.page = page;
        _this.waterRawfileDto.size = _this.$refs.pagination.size;
        _this.waterRawfileDto.xmbh = Tool.getLoginUser().xmbh;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterRawfile/list', _this.waterRawfileDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.waterRawfiles = resp.content.list;
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
                || !Validator.length(_this.waterRawfile.xmbh, "项目编号", 1, 36)
                || !Validator.length(_this.waterRawfile.sbbh, "设备编号", 1, 50)
                || !Validator.length(_this.waterRawfile.wjlx, "文件类型", 1, 128)
                || !Validator.length(_this.waterRawfile.wjlj, "文件路径", 1, 512)
                || !Validator.length(_this.waterRawfile.zt, "状态0未分析1已分析2分析失败", 1, 2)
                || !Validator.length(_this.waterRawfile.sm1, "", 1, 128)
                || !Validator.length(_this.waterRawfile.sm2, "", 1, 128)
                || !Validator.length(_this.waterRawfile.sm3, "", 1, 128)
                || !Validator.length(_this.waterRawfile.sm4, "", 1, 128)
                || !Validator.length(_this.waterRawfile.sm5, "", 1, 128)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterRawfile/save', _this.waterRawfile).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/waterRawfile/delete/' + id).then((response)=>{
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