<template>
  <div>
    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>设备sn</th>
            <th>设备IP</th>
            <th>设备端口号</th>
            <th>数据中心</th>
            <th>水深</th>
            <th>水流</th>
            <th>温度</th>
            <th>溶解氧</th>
            <th>电导率/盐度</th>
            <th>COD</th>
            <th>浊度</th>
            <th>PH值</th>
            <th>透明度</th>
            <th>氨氮</th>
            <th>悬浮物</th>
            <th>余氯</th>
            <th>氯离子、氟离子</th>
            <th>叶绿素</th>
            <th>蓝绿藻</th>
            <th>ORP氧化还原</th>
            <th>油</th>
            <th>总磷</th>
            <th>测量时间</th>
            <th>负责人</th>
<!--        <th>操作</th>-->
      </tr>
      </thead>

      <tbody>
      <tr v-for="waterQuality in waterQualitys">
              <td>{{waterQuality.sbsn}}</td>
              <td>{{waterQuality.ip}}</td>
              <td>{{waterQuality.port}}</td>
              <td>{{waterDatas|optionWDArray(waterQuality.datacenter)}}</td>
              <td>{{waterQuality.waterDepth}}M</td>
              <td>{{waterQuality.waterVelocity}}M/S</td>
              <td>{{waterQuality.temperature}}°C</td>
              <td>{{waterQuality.oxidative}}mg/L</td>
              <td>{{waterQuality.salinity}}uS/cm</td>
              <td>{{waterQuality.cod}}mg/L</td>
              <td>{{waterQuality.turbidity}}NTU</td>
              <td>{{waterQuality.ph}}pH</td>
              <td>{{waterQuality.transparency}}mm</td>
              <td>{{waterQuality.ad}}mg/L</td>
              <td>{{waterQuality.suspended}}mg/L</td>
              <td>{{waterQuality.ppm}}ppm</td>
              <td>{{waterQuality.chlFlu}}mg/L</td>
              <td>{{waterQuality.chlorophyll}}ug/L</td>
              <td>{{waterQuality.cyanobacteria}}cells/mL</td>
              <td>{{waterQuality.orp}}mV</td>
              <td>{{waterQuality.oil}}ug/L</td>
              <td>{{waterQuality.phosphorus}}mg/L</td>
              <td>{{waterQuality.createTime}}</td>
              <td>{{waterQuality.fzr}}</td>
<!--        <td>-->
<!--          <div class="hidden-sm hidden-xs btn-group">-->
<!--            <button v-on:click="detail(waterQuality)" class="btn btn-xs btn-info" title="详情">-->
<!--              <i class="ace-icon fa fa-list bigger-120"></i>-->
<!--            </button>-->
<!--            <button v-on:click="edit(waterQuality)" class="btn btn-xs btn-info">-->
<!--              <i class="ace-icon fa fa-pencil bigger-120"></i>-->
<!--            </button>-->
<!--            <button v-on:click="del(waterQuality.id)" class="btn btn-xs btn-danger">-->
<!--              <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--            </button>-->
<!--          </div>-->
<!--        </td>-->
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
                      <label class="col-sm-2 control-label">设备sn</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.sbsn" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备IP</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.ip" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备端口号</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.port" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">数据中心</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.datacenter" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">水深</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.waterDepth" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">水流</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.waterVelocity" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">温度</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.temperature" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">溶解氧</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.oxidative" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">电导率/盐度</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.salinity" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">COD</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.cod" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">浊度</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.turbidity" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">PH值</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.ph" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">透明度</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.transparency" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">氨氮</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.ad" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">悬浮物</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.suspended" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">余氯</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.ppm" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">氯离子、氟离子</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.chlFlu" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">叶绿素</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.chlorophyll" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">蓝绿藻</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.cyanobacteria" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">ORP氧化还原</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.orp" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">油</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.oil" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">总磷</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.phosphorus" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">测量时间</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.createTime" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">负责人</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.fzr" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.bz" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">说明1</label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.sm1" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.sm2" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.sm3" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-10">
                        <input v-model="waterQuality.sm4" class="form-control">
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
    name: "monitor-waterQuality",
    data: function() {
      return {
        waterQuality: {},
        waterQualitys: [],
        waterDatas:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-waterQuality-sidebar");
      _this.findWaterData();
    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.waterQuality = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(waterQuality) {
        let _this = this;
        _this.waterQuality = $.extend({}, waterQuality);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterQuality/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.waterQualitys = resp.content.list;
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
                || !Validator.length(_this.waterQuality.sbsn, "设备sn", 1, 30)
                || !Validator.length(_this.waterQuality.ip, "设备IP", 1, 30)
                || !Validator.length(_this.waterQuality.datacenter, "数据中心", 1, 20)
                || !Validator.length(_this.waterQuality.temperature, "温度", 1, 20)
                || !Validator.length(_this.waterQuality.oxidative, "溶解氧", 1, 20)
                || !Validator.length(_this.waterQuality.salinity, "电导率/盐度", 1, 128)
                || !Validator.length(_this.waterQuality.cod, "COD", 1, 128)
                || !Validator.length(_this.waterQuality.turbidity, "浊度", 1, 128)
                || !Validator.length(_this.waterQuality.ph, "PH值", 1, 20)
                || !Validator.length(_this.waterQuality.transparency, "透明度", 1, 20)
                || !Validator.length(_this.waterQuality.ad, "氨氮", 1, 20)
                || !Validator.length(_this.waterQuality.suspended, "悬浮物", 1, 20)
                || !Validator.length(_this.waterQuality.ppm, "余氯", 1, 20)
                || !Validator.length(_this.waterQuality.chlFlu, "氯离子、氟离子", 1, 20)
                || !Validator.length(_this.waterQuality.chlorophyll, "叶绿素", 1, 20)
                || !Validator.length(_this.waterQuality.cyanobacteria, "蓝绿藻", 1, 20)
                || !Validator.length(_this.waterQuality.orp, "ORP氧化还原", 1, 20)
                || !Validator.length(_this.waterQuality.oil, "油", 1, 20)
                || !Validator.length(_this.waterQuality.phosphorus, "总磷", 1, 20)
                || !Validator.length(_this.waterQuality.fzr, "负责人", 1, 128)
                || !Validator.length(_this.waterQuality.bz, "备注", 1, 2000)
                || !Validator.length(_this.waterQuality.sm1, "说明1", 1, 2000)
                || !Validator.length(_this.waterQuality.sm2, "", 1, 200)
                || !Validator.length(_this.waterQuality.sm3, "", 1, 200)
                || !Validator.length(_this.waterQuality.sm4, "", 1, 200)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterQuality/save', _this.waterQuality).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/waterQuality/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      },
      /**
       * 查询数据中心
       */
      findWaterData(){
        let _this = this;
        let userInfo = Tool.getLoginUser();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/findAll/' + userInfo.deptcode).then((response)=>{
          let resp = response.data;
          if (resp.success) {
            _this.waterDatas = resp.content;
          }
        })
      }
    }
  }
</script>