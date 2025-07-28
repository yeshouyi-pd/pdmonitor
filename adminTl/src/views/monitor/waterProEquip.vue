<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">项目设备管理查询</h4>
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
                  <input class="form-control" type="text"  v-model="waterProEquipDto.xmbh"/>
                </td>
                <td style="width: 10%">
                  设备编号：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="waterProEquipDto.sbsn"/>
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
            <th>设备编号</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="waterProEquip in waterProEquips">
            <td>{{waterProEquip.xmbh}}</td>
            <td>{{waterProEquip.sbsn}}</td>
            <td>{{waterProEquip.cjr}}</td>
            <td>{{waterProEquip.cjsj}}</td>
            <td>{{waterProEquip.gxsj}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="detail(waterProEquip)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-list bigger-120"></i>
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
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">项目编号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProEquip.xmbh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">设备编号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProEquip.sbsn}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">创建人</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProEquip.cjr}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">创建时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{waterProEquip.cjsj}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name">更新时间</div>
                  <div class="profile-info-value">
                    <span class="editable">{{waterProEquip.gxsj}}</span>
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
    name: "monitor-waterProEquip",
    data: function() {
      return {
        waterProEquipDto: {},
        waterProEquip: {},
        waterProEquips: [],
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
      detail(waterProEquip) {
        let _this = this;
        _this.waterProEquip = $.extend({}, waterProEquip);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.waterProEquipDto.page = page;
        _this.waterProEquipDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterProEquip/list', _this.waterProEquipDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.waterProEquips = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>