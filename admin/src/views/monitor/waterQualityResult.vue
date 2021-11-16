<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">列表监测查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;" class="text-right">
              <tbody>
              <tr>
                <td style="width:8%">
                  设备SN：
                </td>
                <td style="width: 10%">
                  <input class="input-sm" type="text"  v-model="waterQualityResultDto.ip" style="width: 100%;"/>
                </td>
                <td style="width: 8%">
                  监测项目：
                </td>
                <td style="width: 10%">
                  <select v-model="waterQualityResultDto.jcxm" style="width: 100%;">
                    <option value="">请选择</option>
                    <option v-for="(key,value) in szjcx" :value="value">{{key}}</option>
                  </select>
                </td>
                <td style="width:8%">
                  创建时间：
                </td>
                <td style="width: 15%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" end-id="qualityResultEId" start-id="qualityResultSId"></times>
                </td>
                <td style="width: 15%;" class="text-center">
                  <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round" style="margin:0px 10px;">
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
      <button v-on:click="getRealtimeData()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        获取实时数据
      </button>
      (因后台有定时任务，请不要在整点的时候点击该按钮，最好能在整点过5分钟以后点击)
    </p>
    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>设备SN</th>
<!--            <th>设备端口号</th>-->
            <th>数据中心</th>
            <th>检测项目</th>
<!--            <th>指令</th>-->
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
<!--              <td>{{waterQualityResult.port}}</td>-->
              <td>{{waterDatas|optionWDArray(waterQualityResult.datacenter)}}</td>
              <td>{{szjcx|optionMapKV(waterQualityResult.jcxm)}}</td>
<!--              <td>{{waterQualityResult.instruct}}</td>-->
              <td>
                <span v-show="waterQualityResult.dataResult">
                  {{waterQualityResult.dataResult}}{{JYXM_DW|optionKV(waterQualityResult.jcxm)}}
                </span>
              </td>
              <td>
                <span>{{waterQualityResult.dataOriginal}}</span></td>
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
  import Times from "../../components/times";
  export default {
    components: {Pagination,Times},
    name: "monitor-waterQualityResult",
    data: function() {
      return {
        waterQualityResult: {},
        waterQualityResultDto: {},
        waterQualityResults: [],
        waterDatas:[],
        szjcx:[],
        num:0,
        JYXM_DW:JYXM_DW
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
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.waterQualityResultDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.waterQualityResultDto.etime = rep;
        _this.$forceUpdate();
      },
      getRealtimeData(){
        let _this = this;
        Loading.show();
        if(_this.num==0){
          _this.num=1;
          _this.$forceUpdate();
          _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/realtimeData/getRealtimeData', {
          }).then((response)=>{
            Loading.hide();
            let resp = response.data;
            Toast.warning(resp.message);
            if(resp.success){
              _this.list(1);
            }
            _this.num=0;
            _this.$forceUpdate();
          })
        }
      },
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
        _this.waterQualityResultDto.page = page;
        _this.waterQualityResultDto.size = _this.$refs.pagination.size;
        _this.waterQualityResultDto.xmbh = Tool.getLoginUser().xmbh;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterQualityResult/list', _this.waterQualityResultDto).then((response)=>{
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