<template>
  <div>

    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">请求日志查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  设备ip：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="interfaceLogDto.ip"/>
                </td>
                <td style="width: 10%">
                  请求人员：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="interfaceLogDto.qqry"/>
                </td>
                <td style="width: 10%;">
                  请求时间：
                </td>
                <td style="width: 20%;">
                  <time-range-picker v-bind:startTime="startTime" v-bind:endTime="endTime"></time-range-picker>
                </td>
                <td class="text-center" style="width: 20%;">
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
            <th>请求ip</th>
            <th>请求参数</th>
            <th>请求时间</th>
            <th>请求人员</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="interfaceLog in interfaceLogs">
            <td>{{interfaceLog.ip}}</td>
            <td>{{interfaceLog.qqcs}}</td>
            <td>{{interfaceLog.qqsj}}</td>
            <td>{{interfaceLog.qqry}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="edit(interfaceLog)" class="btn btn-xs btn-info">
                  <i class="ace-icon fa fa-pencil bigger-120"></i>
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
                  <div class="profile-info-name" style="width: 10%;">请求ip</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{interfaceLog.ip}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">请求参数</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{interfaceLog.qqcs}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">请求时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{interfaceLog.qqsj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">请求结果</div>
                  <div class="profile-info-value" style="width: 40%;">
<!--                    <span class="editable">{{interfaceLog.fhsj}}</span>-->
                    <textarea v-model="interfaceLog.fhsj" rows="8" cols="63"  placeholder=""></textarea>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">请求人员</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{interfaceLog.qqry}}</span>
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
  import TimeRangePicker from "../../components/time";
  export default {
    components: {Pagination,TimeRangePicker},
    name: "monitor-interfaceLog",
    data: function() {
      return {
        interfaceLogDto: {},
        interfaceLog: {},
        interfaceLogs: [],
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-interfaceLog-sidebar");

    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.interfaceLogDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.interfaceLogDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 点击【编辑】
       */
      edit(interfaceLog) {
        let _this = this;
        _this.interfaceLog = $.extend({}, interfaceLog);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.interfaceLogDto.page = page;
        _this.interfaceLogDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/interfaceLog/list', _this.interfaceLogDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.interfaceLogs = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>