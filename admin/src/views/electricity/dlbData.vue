<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">设备温度查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width: 10%">
                  事件时间：
                </td>
                <td style="width: 20%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="eftstime" end-id="eftetime"></times>
                </td>
                <td style="width: 10%">
                  SIM卡卡号：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="dlbDataDto.iccid"/>
                </td>
                <td style="width: 10%">
                  设备IP：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="dlbDataDto.ipport"/>
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
            <th>设备编号</th>
            <th>SIM卡卡号</th>
            <th>设备IP:端口</th>
            <th>电路板温度(°C)</th>
<!--            <th>转接板时间</th>-->
            <th>充电状态</th>
            <th>事件时间</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
          <tr v-for="dlbData in dlbDatas">
            <td>{{smEq|optionCNArray(dlbData.iccid)}}</td>
            <td>{{dlbData.iccid}}</td>
            <td>{{dlbData.ipport}}</td>
            <td>{{dlbData.wd/10}}</td>
<!--            <td>{{dlbData.sj}}</td>-->
            <td>{{dlbData.cdzt}}</td>
            <td>{{dlbData.sjdate}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group" title="详情">
                <button v-on:click="edit(dlbData)" class="btn btn-xs btn-info">
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
      <div class="modal-dialog" role="document" style="width: 60%">
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
                    <span class="editable">{{dlbData.iccid}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">设备IP:端口</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{dlbData.ipport}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">电路板温度(°C)</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{dlbData.wd/10}}</span>
                  </div>
<!--                  <div class="profile-info-name" style="width: 10%;">转接板时间</div>-->
<!--                  <div class="profile-info-value" style="width: 40%;">-->
<!--                    <span class="editable">{{dlbData.sj}}</span>-->
<!--                  </div>-->
                  <div class="profile-info-name" style="width: 10%;">事件时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{dlbData.sjdate}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name">充电状态</div>
                  <div class="profile-info-value">
                    <span class="editable">{{dlbData.cdzt}}</span>
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
    name: "monitor-dlbData",
    data: function() {
      return {
        dlbDataDto:{},
        dlbData: {},
        dlbDatas: [],
        smEq: []
      }
    },
    created:function(){
      let _this = this;
      _this.getEquip();
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
    },
    methods: {
      getEquip(){
        let _this = this;
        let obj = {};
        if("460100"!=Tool.getLoginUser().deptcode){
          obj.xmbh = Tool.getLoginUser().xmbh;
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll',obj).then((response)=>{
          let waterEquipments = response.data.content;
          waterEquipments.forEach(function (item){
            if(!Tool.isEmpty(item.sbcj)){
              let obj = {"code":item.sbcj,"name":item.sbsn};
              _this.smEq.push(obj);
            }
          })
          _this.$forceUpdate();
        })
      },
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.dlbDataDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.dlbDataDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.dlbDataDto.page = page;
        _this.dlbDataDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/dlbDataSec/list', _this.dlbDataDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.dlbDatas = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },
      /**
       * 详情
       */
      edit(dlbData){
        let _this = this;
        _this.dlbData = $.extend({}, dlbData);
        $("#form-modal").modal("show");
      }
    }
  }
</script>