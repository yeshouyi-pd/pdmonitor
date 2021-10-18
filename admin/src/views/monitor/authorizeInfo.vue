<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">授权信息查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  单位名称：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="authorizeInfoDto.dwmc"/>
                </td>
                <td style="width: 10%">
                  设备编号：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="authorizeInfoDto.sbsn"/>
                </td>
                <td style="width: 10%">
                  客户ip：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="authorizeInfoDto.ip"/>
                </td>
                <td class="text-center"  style="width: 15%;">
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
    </p>

    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
        <tr>
          <th>单位名称</th>
          <th>设备编号</th>
          <th>授权码</th>
          <th>客户ip</th>
          <th>操作</th>
        </tr>
      </thead>

      <tbody>
      <tr v-for="authorizeInfo in authorizeInfos">
        <td>{{authorizeInfo.dwmc}}</td>
        <td>{{authorizeInfo.sbsn}}</td>
        <td>{{authorizeInfo.sqm}}</td>
        <td>{{authorizeInfo.ip}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(authorizeInfo)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(authorizeInfo.id)" class="btn btn-xs btn-danger">
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
      <div class="modal-dialog" role="document" style="width: 60%;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">单位名称</label>
                      <div class="col-sm-4">
                        <input v-model="authorizeInfo.dwmc" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">设备编号</label>
                      <div class="col-sm-4">
                        <input v-model="authorizeInfo.sbsn" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">授权码</label>
                      <div class="col-sm-4">
                        <input v-model="authorizeInfo.sqm" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">客户ip</label>
                      <div class="col-sm-4">
                        <input v-model="authorizeInfo.ip" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-4">
                        <input v-model="authorizeInfo.bz" class="form-control">
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
    name: "monitor-authorizeInfo",
    data: function() {
      return {
        authorizeInfoDto: {},
        authorizeInfo: {},
        authorizeInfos: [],
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-authorizeInfo-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.authorizeInfo = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(authorizeInfo) {
        let _this = this;
        _this.authorizeInfo = $.extend({}, authorizeInfo);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.authorizeInfoDto.page = page;
        _this.authorizeInfoDto.size = _this.$refs.pagination.size;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/authorizeInfo/list', _this.authorizeInfoDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.authorizeInfos = resp.content.list;
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
                || !Validator.length(_this.authorizeInfo.dwmc, "单位名称", 1, 255)
                || !Validator.require(_this.authorizeInfo.dwmc, "单位名称")
                || !Validator.length(_this.authorizeInfo.sbsn, "设备编号", 1, 255)
                || !Validator.require(_this.authorizeInfo.sbsn, "设备编号")
                || !Validator.length(_this.authorizeInfo.sqm, "授权码", 1, 255)
                || !Validator.require(_this.authorizeInfo.sqm, "授权码")
                || !Validator.length(_this.authorizeInfo.ip, "客户ip", 1, 255)
                || !Validator.require(_this.authorizeInfo.ip, "客户ip")
                || !Validator.length(_this.authorizeInfo.bz, "备注", 1, 255)
                || !Validator.length(_this.authorizeInfo.bz1, "备注字段1", 1, 255)
                || !Validator.length(_this.authorizeInfo.bz2, "备注字段2", 1, 255)
                || !Validator.length(_this.authorizeInfo.bz3, "备注字段3", 1, 255)
        ) {
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/authorizeInfo/save', _this.authorizeInfo).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/authorizeInfo/delete/' + id).then((response)=>{
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