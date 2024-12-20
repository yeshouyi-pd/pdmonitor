<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">太阳能板管理查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
                  <input class="form-control" type="text"  v-model="solarPannelDto.deviceName"/>
                </td>
<!--                <td style="width: 10%">-->
<!--                  设备编号：-->
<!--                </td>-->
<!--                <td style="width: 20%">-->
<!--                  <input class="form-control" type="text" v-model="solarPannelDto.deviceNumber"/>-->
<!--                </td>-->
                <td style="width: 20%" class="text-center">
                  <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round" style="margin-left: 10px;">
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
          <th>设备名称</th>
          <th>设备编号</th>
          <th>设备温度</th>
          <th>电池电压</th>
          <th>负载电压</th>
          <th>负载电流</th>
          <th>当日用电</th>
          <th>当月累计用电</th>
          <th>太阳能电压</th>
          <th>太阳能电流</th>
          <th>发电功率</th>
          <th>当日累计充电</th>
          <th>当月累计充电</th>
          <th>电池电量</th>
          <th>更新时间</th>
          <th>是否在线</th>
          <th>开关</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in solarPannels">
          <td>{{item.deviceName}}</td>
          <td>{{item.deviceNumber}}</td>
          <td>{{item.temperature}}</td>
          <td>{{item.batteryVoltage}}</td>
          <td>{{item.loadVoltage}}</td>
          <td>{{item.loadCurrent}}</td>
          <td>{{item.dailyElectricityConsumption}}</td>
          <td>{{item.monthlyElectricityConsumption}}</td>
          <td>{{item.solarPanelVoltage}}</td>
          <td>{{item.solarPannelCurrent}}</td>
          <td>{{item.powerGeneration}}</td>
          <td>{{item.dailyCharge}}</td>
          <td>{{item.monthlyCharge}}</td>
          <td>{{item.batteryPercent}}</td>
          <td>{{item.updateTime}}</td>
          <td><span v-if="item.online=='1'">在线</span><span v-else>不在线</span></td>
          <td><span v-if="item.handSwitch=='1'">开</span><span v-else>关</span></td>
          <td>
            <div class="hidden-sm hidden-xs btn-group" title="详情">
              <button v-on:click="detail(item)" class="btn btn-xs btn-info">
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
                  <div class="profile-info-name" style="width: 10%;">设备号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.deviceId}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">设备名称</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.deviceName}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">设备编号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.deviceNumber}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">通道号</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.channelId}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">分组id</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.groupId}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">用户名</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.userName}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">经度</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.longitude}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">纬度</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.latitude}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">是否在线</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable" v-if="solarPannel.online=='1'">在线</span>
                    <span class="editable" v-else>不在线</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">手动控制开关</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable" v-if="solarPannel.handSwitch=='1'">开</span>
                    <span class="editable" v-else>关</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">电池校准</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.batterycorrect}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">机内温度</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.temperature}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">电池电压</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.batteryVoltage}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">电池电流</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.batteryCurrent}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">负载电压</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.loadVoltage}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">负载电流</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.loadCurrent}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">太阳能板电压</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.solarPanelVoltage}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">太阳能板电流</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.solarPannelCurrent}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">发电功率</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.powerGeneration}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">电池剩余电量</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.batteryPercent}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">最低电压</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.minVoltage}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">最高电压</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.maxVoltage}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">当日用电量</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.dailyElectricityConsumption}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">当日累计充电量</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.dailyCharge}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">当月累计用电量</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.monthlyElectricityConsumption}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">当月累计充电量</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.monthlyCharge}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">创建时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.createTime}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">更新时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.updateTime}}</span>
                  </div>
                </div>
              </div>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;">心跳时间</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable">{{solarPannel.heartbeatTime}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%;">删除标志</div>
                  <div class="profile-info-value" style="width: 40%;">
                    <span class="editable" v-if="solarPannel.del=='1'">删除</span>
                    <span class="editable" v-else>未删除</span>
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
import Pagination from "@/components/pagination";
export default {
  name: 'solar-pannel',
  components: {Pagination},
  data: function (){
    return {
      solarPannels:[],
      solarPannel:{},
      solarPannelDto:{}
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
  },
  methods: {
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.solarPannelDto.page = page;
      _this.solarPannelDto.size = _this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/solarPannel/list', _this.solarPannelDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.solarPannels = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    /**
     * 详情
     */
    detail(item){
      let _this = this;
      _this.solarPannel = $.extend({}, item);
      $("#form-modal").modal("show");
    }
  }
}
</script>