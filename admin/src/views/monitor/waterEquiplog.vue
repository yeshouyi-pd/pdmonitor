<template>
  <div>
      <div class="widget-box">
          <div class="widget-header">
              <h4 class="widget-title">设备心跳包查询</h4>
          </div>
          <div class="widget-body">
              <div class="widget-main">
                  <form>
                      <table style="font-size: 1.1em;width:80%" class="text-right">
                          <tbody>
                          <tr>
                              <td style="width:10%">
                                  设备编号：
                              </td>
                              <td style="width: 15%">
                                  <input class="input-sm" type="text"  v-model="waterEquiplogDto.sbbh"/>
                              </td>
                              <td style="width: 10%">
                                  设备状态：
                              </td>
                              <td style="width: 20%">
                                  <select v-model="waterEquiplogDto.code" style="width: 100%;">
                                      <option value="">请选择</option>
                                      <option value="1">设备正常</option>
                                      <option value="2">设备离线</option>
                                      <option value="3">设备故障</option>
                                  </select>
                              </td>
                              <td colspan="2" class="text-center">
                                  <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
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
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>
    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <!--<th>主键</th>-->
            <th>设备编号</th>
            <th>设备状态</th>
            <th>回传说明</th>
            <th>回传时间</th>
            <th>接口请求参数</th>
            <th>接口返回参数</th>
            <!--<th>操作</th>-->
      </tr>
      </thead>

      <tbody>
      <tr v-for="waterEquiplog in waterEquiplogs">
              <!--<td>{{waterEquiplog.id}}</td>-->
              <td>{{waterEquiplog.sbbh}}</td>
              <td>{{waterEquiplog.code|getStatesType}}</td>
              <td>{{waterEquiplog.msg}}</td>
              <td>{{waterEquiplog.cjsj}}</td>
              <td>{{waterEquiplog.reqmsg}}</td>
              <td>{{waterEquiplog.respmsg}}</td>
        <!--<td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(waterEquiplog)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(waterEquiplog.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>-->
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
                      <label class="col-sm-2 control-label">设备编号</label>
                      <div class="col-sm-10">
                        <input v-model="waterEquiplog.sbbh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">返回code值1设备正常2设备离线3设备故障</label>
                      <div class="col-sm-10">
                        <input v-model="waterEquiplog.code" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">回传说明</label>
                      <div class="col-sm-10">
                        <input v-model="waterEquiplog.msg" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">回传时间</label>
                      <div class="col-sm-10">
                        <input v-model="waterEquiplog.cjsj" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">接口请求参数</label>
                      <div class="col-sm-10">
                        <input v-model="waterEquiplog.reqmsg" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">接口返回参数</label>
                      <div class="col-sm-10">
                        <input v-model="waterEquiplog.respmsg" class="form-control">
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
    name: "monitor-waterEquiplog",
    data: function() {
      return {
      waterEquiplogDto: {},
      waterEquiplog: {},
      waterEquiplogs: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-waterEquiplog-sidebar");

    },
    filters: {
      getStatesType(type) {
          const map = new Map([
              ['1', '设备正常'],
              ['2', '设备离线'],
              ['3', '设备故障']
          ]);
          return map.get(type);
      }
    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.waterEquiplog = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(waterEquiplog) {
        let _this = this;
        _this.waterEquiplog = $.extend({}, waterEquiplog);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.waterEquiplogDto.page = page;
        _this.waterEquiplogDto.size = _this.$refs.pagination.size;
        if("460100"!=Tool.getLoginUser().deptcode){
          _this.waterEquiplogDto.xmbh = Tool.getLoginUser().xmbh;
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquiplog/list', _this.waterEquiplogDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.waterEquiplogs = resp.content.list;
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
                || !Validator.length(_this.waterEquiplog.sbbh, "设备编号", 1, 50)
                || !Validator.length(_this.waterEquiplog.code, "返回code值1设备正常2设备离线3设备故障", 1, 2)
                || !Validator.length(_this.waterEquiplog.msg, "回传说明", 1, 200)
                || !Validator.length(_this.waterEquiplog.reqmsg, "接口请求参数", 1, 2000)
                || !Validator.length(_this.waterEquiplog.respmsg, "接口返回参数", 1, 2000)
                || !Validator.length(_this.waterEquiplog.sm1, "", 1, 1000)
                || !Validator.length(_this.waterEquiplog.sm2, "", 1, 1000)
                || !Validator.length(_this.waterEquiplog.sm3, "", 1, 1000)
                || !Validator.length(_this.waterEquiplog.sm4, "", 1, 1000)
                || !Validator.length(_this.waterEquiplog.sm5, "", 1, 1000)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquiplog/save', _this.waterEquiplog).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquiplog/delete/' + id).then((response)=>{
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