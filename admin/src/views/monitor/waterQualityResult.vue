<template>
  <div>
    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>设备IP</th>
            <th>设备端口号</th>
            <th>数据中心</th>
            <th>检测项目</th>
            <th>指令</th>
            <th>检测数据</th>
            <th>原始数据</th>
            <th>创建时间</th>
            <th>备注</th>
<!--        <th>操作</th>-->
      </tr>
      </thead>

      <tbody>
      <tr v-for="waterQualityResult in waterQualityResults">
              <td>{{waterQualityResult.ip}}</td>
              <td>{{waterQualityResult.port}}</td>
              <td>{{waterDatas|optionWDArray(waterQualityResult.datacenter)}}</td>
              <td>{{szjcx|optionMapKV(waterQualityResult.jcxm)}}</td>
              <td>{{waterQualityResult.instruct}}</td>
              <td>
                <span v-if="waterQualityResult.jcxm=='waterDepth' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}M</span>
                <span v-if="waterQualityResult.jcxm=='waterVelocity' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}M/S</span>
                <span v-if="waterQualityResult.jcxm=='temperature' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}°C</span>
                <span v-if="waterQualityResult.jcxm=='oxidative' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='salinity' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}uS/cm</span>
                <span v-if="waterQualityResult.jcxm=='cod' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='turbidity' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}NTU</span>
                <span v-if="waterQualityResult.jcxm=='ph' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}pH</span>
                <span v-if="waterQualityResult.jcxm=='transparency' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}mm</span>
                <span v-if="waterQualityResult.jcxm=='ad' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='suspended' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='ppm' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}ppm</span>
                <span v-if="waterQualityResult.jcxm=='chlFlu' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='chlorophyll' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}ug/L</span>
                <span v-if="waterQualityResult.jcxm=='cyanobacteria' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}cells/mL</span>
                <span v-if="waterQualityResult.jcxm=='orp' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}mV</span>
                <span v-if="waterQualityResult.jcxm=='oil' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}ug/L</span>
                <span v-if="waterQualityResult.jcxm=='phosphorus' && waterQualityResult.dataResult">{{waterQualityResult.dataResult}}mg/L</span>
              </td>
              <td>
                <span v-if="waterQualityResult.jcxm=='waterDepth' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}M</span>
                <span v-if="waterQualityResult.jcxm=='waterVelocity' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}M/S</span>
                <span v-if="waterQualityResult.jcxm=='temperature' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}°C</span>
                <span v-if="waterQualityResult.jcxm=='oxidative' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='salinity' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}uS/cm</span>
                <span v-if="waterQualityResult.jcxm=='cod' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='turbidity' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}NTU</span>
                <span v-if="waterQualityResult.jcxm=='ph' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}pH</span>
                <span v-if="waterQualityResult.jcxm=='transparency' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}mm</span>
                <span v-if="waterQualityResult.jcxm=='ad' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='suspended' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='ppm' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}ppm</span>
                <span v-if="waterQualityResult.jcxm=='chlFlu' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}mg/L</span>
                <span v-if="waterQualityResult.jcxm=='chlorophyll' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}ug/L</span>
                <span v-if="waterQualityResult.jcxm=='cyanobacteria' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}cells/mL</span>
                <span v-if="waterQualityResult.jcxm=='orp' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}mV</span>
                <span v-if="waterQualityResult.jcxm=='oil' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}ug/L</span>
                <span v-if="waterQualityResult.jcxm=='phosphorus' && waterQualityResult.dataOriginal">{{waterQualityResult.dataOriginal}}mg/L</span>
              </td>
              <td>{{waterQualityResult.createTime}}</td>
              <td>{{waterQualityResult.bz}}</td>
<!--        <td>-->
<!--          <div class="hidden-sm hidden-xs btn-group">-->
<!--            <button v-on:click="edit(waterQualityResult)" class="btn btn-xs btn-info">-->
<!--              <i class="ace-icon fa fa-pencil bigger-120"></i>-->
<!--            </button>-->
<!--            <button v-on:click="del(waterQualityResult.id)" class="btn btn-xs btn-danger">-->
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
                      <label class="col-sm-2 control-label">设备IP</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.ip" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">设备端口号</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.port" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">数据中心</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.datacenter" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">检测项目</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.jcxm" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">指令</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.instruct" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">检测数据</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.dataResult" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">原始数据</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.dataOriginal" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">创建时间</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.createTime" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.bz" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.sm1" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.sm2" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.sm3" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label"></label>
                      <div class="col-sm-10">
                        <input v-model="waterQualityResult.sm4" class="form-control">
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
    name: "monitor-waterQualityResult",
    data: function() {
      return {
        waterQualityResult: {},
        waterQualityResults: [],
        waterDatas:[],
        szjcx:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-waterQualityResult-sidebar");
      _this.findWaterData();
      _this.getSzjcx();
    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.waterQualityResult = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(waterQualityResult) {
        let _this = this;
        _this.waterQualityResult = $.extend({}, waterQualityResult);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterQualityResult/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.waterQualityResults = resp.content.list;
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
                || !Validator.length(_this.waterQualityResult.ip, "设备IP", 1, 20)
                || !Validator.length(_this.waterQualityResult.datacenter, "数据中心", 1, 128)
                || !Validator.length(_this.waterQualityResult.jcxm, "检测项目", 1, 128)
                || !Validator.length(_this.waterQualityResult.instruct, "指令", 1, 128)
                || !Validator.length(_this.waterQualityResult.dataResult, "检测数据", 1, 128)
                || !Validator.length(_this.waterQualityResult.dataOriginal, "原始数据", 1, 128)
                || !Validator.length(_this.waterQualityResult.bz, "备注", 1, 2000)
                || !Validator.length(_this.waterQualityResult.sm1, "", 1, 200)
                || !Validator.length(_this.waterQualityResult.sm2, "", 1, 200)
                || !Validator.length(_this.waterQualityResult.sm3, "", 1, 200)
                || !Validator.length(_this.waterQualityResult.sm4, "", 1, 200)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterQualityResult/save', _this.waterQualityResult).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/waterQualityResult/delete/' + id).then((response)=>{
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
      },
      /**
       * 获取水质检测项
       */
      getSzjcx(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getSzjcx', {
        }).then((response)=>{
          let resp = response.data;
          _this.szjcx = resp.content;
        })
      }
    }
  }
</script>