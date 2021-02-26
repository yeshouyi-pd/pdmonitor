<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">通行证线路设置</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form>
                <table style="font-size: 1.1em;width: 70%" class="text-right  ">
                  <tbody>
                  <tr>
                    <td style="width:6%">
                      区县：
                    </td>
                    <td style="width: 10%">
                      <select v-model="vehPasslineDto.qx" class="form-control">
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in xzqh" v-bind:value="key">{{ value }}</option>
                      </select>
                    </td>
                    <td style="width: 8%">
                      线路名称：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-2" v-model="vehPasslineDto.jddz"/>
                    </td>
                    <td style="width:6%">
                      状态：
                    </td>
                    <td style="width: 10%">
                      <select v-model="vehPasslineDto.qx" class="form-control">
                        <option value="" selected>请选择</option>
                        <option value="1">正常</option>
                        <option value="2">禁用</option>
                      </select>
                    </td>
                    <td colspan="2" class="text-center">
                      <button type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      &nbsp;&nbsp;&nbsp;
                      <a href="javascript:location.replace(location.href);" class="btn btn-sm   btn-success btn-round">
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
        新增线路
      </button>&nbsp;
      <div class="space-2"></div>
    </div>


    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>

          <th>区县</th>
          <th>线路名称</th>
            <th>创建人</th>
            <th>创建部门</th>
            <th>创建时间</th>
            <th>更新人</th>
            <th>更新时间</th>
            <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehPassline in vehPasslines">

              <td>{{xzqh|optionMapKV(vehPassline.qx)}}</td>
              <td>{{vehPassline.jddz}}</td>
              <td>{{usersMap|optionMapKV(vehPassline.createBy)}}</td>
              <td>{{maps|optionMapKV(vehPassline.sysOrgCode)}}</td>
              <td>{{vehPassline.createTime}}</td>
              <td>{{ usersMap|optionMapKV(vehPassline.updateBy)}}</td>
              <td>{{vehPassline.updateTime}}</td>
        <td><span v-if="vehPassline.zt =='1'">正常</span><span v-else-if="vehPassline.zt =='2'">禁用</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehPassline)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(vehPassline.id)" class="btn btn-xs btn-danger" title="删除">
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
      <div class="modal-dialog" role="document" >
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">通行证线路管理</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">区县*</label>
                      <div class="col-sm-8">
                        <select v-model="vehPassline.qx" class="form-control">
                          <option v-for="(value, key) in xzqh" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">线路名称*</label>
                        <div class="col-sm-8">
                          <input v-model="vehPassline.jddz" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">排序</label>
                      <div class="col-sm-8">
                        <input v-model="vehPassline.px" class="form-control">
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-sm-2 control-label">状态*</label>
                      <div class="col-sm-8">
                        <div class="radio">
                          <label>
                            <input name="form-field-radio" type="radio" class="ace" value="1" v-model="vehPassline.zt"/>
                            <span class="lbl">正常</span>
                          </label>
                          <label>
                            <input name="form-field-radio" type="radio" class="ace" value="2" v-model="vehPassline.zt"/>
                            <span class="lbl">禁用</span>
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-8">
                        <input v-model="vehPassline.bz" class="form-control">
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
    name: "wxbase-vehPassline",
    data: function() {
      return {
        vehPassline: {},
        vehPasslineDto: {},
        vehPasslines: [],
        xzqh: [],
        maps: [],
        usersMap: [],
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getXzqh();
      _this.maps = Tool.getDeptUser() ;
      _this.usersMap = Tool.getUserCode();

    },
    methods: {
      /**
       * 获取行政区划
       */
      getXzqh(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getXzqh', {
        }).then((response)=>{
          let resp = response.data;
          _this.xzqh = resp.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehPassline = {};
        _this.vehPassline.zt = "1";
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehPassline) {
        let _this = this;
        _this.vehPassline = $.extend({}, vehPassline);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.vehPasslineDto.page = page;
        _this.vehPasslineDto.size = _this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehPassline/list', _this.vehPasslineDto).then((response) => {
          Loading.hide();
          let resp = response.data;
          _this.vehPasslines = resp.content.list;
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
                || !Validator.require(_this.vehPassline.qx, "区县")
                || !Validator.require(_this.vehPassline.jddz, "线路名称")
                || !Validator.length(_this.vehPassline.qx, "区县", 1, 128)
                || !Validator.length(_this.vehPassline.jddz, "线路名称", 1, 256)
                || !Validator.length(_this.vehPassline.ms, "描述", 1, 256)
                || !Validator.length(_this.vehPassline.bz, "备注", 1, 2000)
                || !Validator.length(_this.vehPassline.createBy, "创建人", 1, 256)
                || !Validator.length(_this.vehPassline.updateBy, "更新人", 1, 256)
                || !Validator.length(_this.vehPassline.sysOrgCode, "创建部门", 1, 64)
                || !Validator.length(_this.vehPassline.jddm, "街道代码", 1, 64)
                || !Validator.length(_this.vehPassline.zt, "状态", 1, 1)
                || !Validator.checkIsFF(_this.vehPassline.px,"排序")
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehPassline/save', _this.vehPassline).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehPassline/delete/' + id).then((response)=>{
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