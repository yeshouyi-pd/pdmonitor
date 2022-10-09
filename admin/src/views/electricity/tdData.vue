<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">通道数据查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  事件时间：
                </td>
                <td style="width: 20%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="eftstime" end-id="eftetime"></times>
                </td>
                <td style="width:10%">
                  SIM卡卡号：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="tdDataDto.iccid"/>
                </td>
                <td style="width:10%">
                  设备IP：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="tdDataDto.ipport"/>
                </td>
                <td style="width: 20%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
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
            <th>SIM卡卡号</th>
            <th>设备IP:端口</th>
            <th>数据类型</th>
            <th>电压(V)</th>
            <th>电流(mA)</th>
            <th>通道状态</th>
            <th>事件时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tdData in tdDatas">
            <td>{{tdData.iccid}}</td>
            <td>{{tdData.ipport}}</td>
            <td>
              <span v-if="tdData.tdlx=='40'">雷达电源通道的工作状态</span>
              <span v-if="tdData.tdlx=='41'">指示灯输出通道的工作状态</span>
              <span v-if="tdData.tdlx=='42'">喇叭电源输出通道的工作状态</span>
              <span v-if="tdData.tdlx=='43'">备用输出通道的工作状态</span>
            </td>
            <td>{{tdData.dy}}</td>
            <td>{{tdData.dl}}</td>
            <td><span v-if="tdData.tdzt=='0'||tdData.tdzt=='1'">正常</span><span v-else>异常</span></td>
            <td>{{tdData.sjdate}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group" title="详情">
                <button v-on:click="edit(tdData)" class="btn btn-xs btn-info">
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
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">SIM卡卡号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{tdData.iccid}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">设备IP:端口</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{tdData.ipport}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">数据类型</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable" v-if="tdData.tdlx=='40'">雷达电源通道的工作状态</span>
                    <span class="editable" v-if="tdData.tdlx=='41'">指示灯输出通道的工作状态</span>
                    <span class="editable" v-if="tdData.tdlx=='42'">喇叭电源输出通道的工作状态</span>
                    <span class="editable" v-if="tdData.tdlx=='43'">备用输出通道的工作状态</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">通道号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{tdData.tdh}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">电压(V)</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{tdData.dy}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">电流(mA)</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{tdData.dl}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">通道状态</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable" v-if="tdData.tdzt=='0'||tdData.tdzt=='1'">正常</span>
                    <span class="editable" v-else>异常</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">事件时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{tdData.sjdate}}</span>
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
  import Times from "../../components/times";
  export default {
    components: {Pagination,Times},
    name: "monitor-tdData",
    data: function() {
      return {
        tdDataDto: {},
        tdData: {},
        tdDatas: [],
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.tdDataDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.tdDataDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.tdDataDto.page = page;
        _this.tdDataDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/tdData/list', _this.tdDataDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.tdDatas = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },
      /**
       * 详情
       */
      edit(tdData){
        let _this = this;
        _this.tdData = $.extend({}, tdData);
        $("#form-modal").modal("show");
      }
    }
  }
</script>