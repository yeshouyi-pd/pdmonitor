<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">项目人员查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  项目编号：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="waterProUserDto.xmbh"/>
                </td>
                <td style="width: 10%">
                  人员账号：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="waterProUserDto.usercode"/>
                </td>
                <td colspan="2" class="text-center">
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

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
          <tr>
              <th>项目编号</th>
              <th>人员账号</th>
              <th>创建人</th>
              <th>创建时间</th>
              <th>更新时间</th>
              <th>是否是负责人</th>
              <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="waterProUser in waterProUsers">
            <td>{{waterProUser.xmbh}}</td>
            <td>{{waterProUser.usercode}}</td>
            <td>{{waterProUser.cjr}}</td>
            <td>{{waterProUser.cjsj}}</td>
            <td>{{waterProUser.gxsj}}</td>
            <td><span v-if="waterProUser.isboss=='1'">是</span><span v-if="waterProUser.isboss=='0'">否</span></td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="detail(waterProUser)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-list bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
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
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">项目编号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProUser.xmbh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">人员账号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProUser.usercode}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">创建人</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProUser.cjr}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">创建时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProUser.cjsj}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">更新时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProUser.gxsj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">是否是负责人</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable" v-if="waterProUser.isboss=='1'">是</span>
                    <span class="editable" v-if="waterProUser.isboss=='0'">否</span>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
    name: "monitor-waterProUser",
    data: function() {
      return {
        waterProUserDto:{},
        waterProUser: {},
        waterProUsers: [],
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
    },
    methods: {
      /**
       * 点击【详情】
       */
      detail(waterProUser) {
        let _this = this;
        _this.waterProUser = $.extend({}, waterProUser);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.waterProUserDto.page = page;
        _this.waterProUserDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterProUser/list', _this.waterProUserDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.waterProUsers = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>