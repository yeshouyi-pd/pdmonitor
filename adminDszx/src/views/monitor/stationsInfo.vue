<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">基站基本信息查询</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form>
                <table style="font-size: 1.1em;width:100%" class="text-right" >
                  <tbody>
                  <tr>
                    <td style="width:10%">
                      基站id：
                    </td>
                    <td style="width: 20%">
                      <input class="form-control" type="text"  v-model="stationsInfoDto.id"/>
                    </td>
                    <td style="width:10%">
                      基站编号：
                    </td>
                    <td style="width: 20%">
                      <input class="form-control" type="text"  v-model="stationsInfoDto.nodeNum"/>
                    </td>
                    <td style="width:10%">
                      基站名称：
                    </td>
                    <td style="width: 20%">
                      <input class="form-control" type="text"  v-model="stationsInfoDto.nodeName"/>
                    </td>
                    <td class="text-center">
                      <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      <a href="javascript:location.replace(location.href);"  class="btn btn-sm btn-success btn-round" style="margin-right: 10px;">
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
          <th>基站id</th>
          <th>基站名称</th>
          <th>基站编号</th>
          <th>信标编号</th>
          <th>序号</th>
          <th>坐标点</th>
          <th>百度坐标点</th>
          <th>最后在线时间</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="stationsInfo in stationsInfos">
          <td>{{stationsInfo.id}}</td>
          <td>{{stationsInfo.nodeName}}</td>
          <td>{{stationsInfo.nodeNum}}</td>
          <td>{{stationsInfo.loraId}}</td>
          <td>{{stationsInfo.xh}}</td>
          <td>{{stationsInfo.gps}}</td>
          <td>{{stationsInfo.bdGps}}</td>
          <td>{{stationsInfo.lastOnlineTime}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(stationsInfo)" class="btn btn-xs btn-info" title="修改">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
              </button>
              <button v-on:click="del(stationsInfo)" class="btn btn-xs btn-info" title="删除">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
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
                <label class="col-sm-2 control-label">基站id</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.id" class="form-control">
                </div>
                <label class="col-sm-2 control-label">基站名称</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.nodeName" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">基站编号</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.nodeNum" class="form-control">
                </div>
                <label class="col-sm-2 control-label">信标编号</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.loraId" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">序号</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.xh" class="form-control">
                </div>
                <label class="col-sm-2 control-label">电话</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.dh" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">坐标点</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.gps" class="form-control">
                </div>
                <label class="col-sm-2 control-label">百度坐标点</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.bdGps" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">最后在线时间</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.lastOnlineTime" class="form-control">
                </div>
                <label class="col-sm-2 control-label">地址</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.dz" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">部门编号</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.deptCode" class="form-control">
                </div>
                <label class="col-sm-2 control-label">创建时间</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.createTime" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">修改时间</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.updateTime" class="form-control">
                </div>
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.bz" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">说明</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.sm" class="form-control">
                </div>
                <label class="col-sm-2 control-label">标注</label>
                <div class="col-sm-4">
                  <input v-model="stationsInfo.remark" class="form-control">
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
  name: "stations-info",
  components: {Pagination},
  data: function (){
    return {
      stationsInfo:{},
      stationsInfoDto:{},
      stationsInfos:[]
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
  },
  methods: {
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.stationsInfo = {};
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(stationsInfo) {
      let _this = this;
      _this.stationsInfo = $.extend({}, stationsInfo);
      $("#form-modal").modal("show");
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.stationsInfoDto.page = page;
      _this.stationsInfoDto.size = _this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/stationsInfo/list', _this.stationsInfoDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.stationsInfos = resp.content.list;
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
          || !Validator.require(_this.stationsInfo.id, "基站id")
          || !Validator.length(_this.stationsInfo.id, "基站id", 1, 100)
          || !Validator.require(_this.stationsInfo.nodeName, "基站名称")
          || !Validator.length(_this.stationsInfo.nodeName, "基站名称", 1, 255)
          || !Validator.require(_this.stationsInfo.nodeNum, "基站编号")
          || !Validator.length(_this.stationsInfo.nodeNum, "基站编号", 1, 255)
          || !Validator.require(_this.stationsInfo.loraId, "信标编号")
          || !Validator.length(_this.stationsInfo.loraId, "信标编号", 1, 255)
      ) {
        return;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/stationsInfo/save', _this.stationsInfo).then((response)=>{
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
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/stationsInfo/delete/' + id).then((response)=>{
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