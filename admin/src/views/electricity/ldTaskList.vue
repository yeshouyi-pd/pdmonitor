<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">下发命令查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  SIM卡卡号：
                </td>
                <td style="width: 20%">
                  <input class="form-control" type="text"  v-model="ldTaskListDto.iccid"/>
                </td>
                <td style="width: 10%">
                  状态：
                </td>
                <td style="width: 20%">
                  <select v-model="ldTaskListDto.state" style="width: 100%;">
                    <option value="">请选择</option>
                    <option value="0">等待执行</option>
                    <option value="1">执行完毕</option>
                  </select>
                </td>
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
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
    </p>
    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
          <tr>
            <th>设备编号</th>
            <th>SIM卡卡号</th>
            <th>命令</th>
            <th>状态</th>
            <th>任务发生时间</th>
            <th>任务完成时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="ldTaskList in ldTaskLists">
            <td>{{smEq|optionCNArray(ldTaskList.iccid)}}</td>
            <td>{{ldTaskList.iccid}}</td>
            <td>{{fszls|optionMapKV(ldTaskList.task)}}</td>
            <td><span v-if="ldTaskList.state=='0'">等待执行</span><span v-else>执行完毕</span></td>
            <td>{{ldTaskList.fsdate}}</td>
            <td>{{ldTaskList.jsdate}}</td>
          </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 50%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">SIM卡卡号</label>
                <div class="col-sm-10">
<!--                  <input v-model="ldTaskList.iccid" class="form-control">-->
                  <select v-model="ldTaskList.iccid" style="width: 100%;">
                    <option v-for="item in waterEquipments" :value="item.sbcj">{{item.sbmc}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">命令</label>
                <div class="col-sm-10">
                  <select v-model="ldTaskList.task" style="width: 100%;">
                    <option v-for="(key,value) in fszls" :value="value">{{key}}</option>
                  </select>
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
    name: "monitor-ldTaskList",
    data: function() {
      return {
        ldTaskListDto: {},
        ldTaskList: {},
        ldTaskLists: [],
        fszls:[],
        waterEquipments:[],
        smEq:[]
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
      _this.getFszl();
    },
    methods: {
      getEquip(){
        let _this = this;
        let obj = {};
        if("460100"!=Tool.getLoginUser().deptcode){
          obj.xmbh = Tool.getLoginUser().xmbh;
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll',obj).then((response)=>{
          _this.waterEquipments = response.data.content;
          _this.waterEquipments.forEach(function (item){
            if(!Tool.isEmpty(item.sbcj)){
              let obj = {"code":item.sbcj,"name":item.sbsn};
              _this.smEq.push(obj);
            }
          })
          _this.$forceUpdate();
        })
      },
      /**
       * 获取设备型号
       */
      getFszl(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getFszl').then((res) => {
          let response = res.data;
          _this.fszls = response.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.ldTaskList = {};
        $("#form-modal").modal("show");
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.ldTaskListDto.page = page;
        _this.ldTaskListDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/ldTaskListSec/list', _this.ldTaskListDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.ldTaskLists = resp.content.list;
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
                || !Validator.length(_this.ldTaskList.iccid, "SIM卡卡号", 1, 32)
                || !Validator.require(_this.ldTaskList.iccid, "SIM卡卡号")
                || !Validator.length(_this.ldTaskList.task, "命令", 1, 32)
                || !Validator.require(_this.ldTaskList.task, "命令")
        ) {
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/ldTaskListSec/save', _this.ldTaskList).then((response)=>{
          setTimeout(function (){
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              $("#form-modal").modal("hide");
              _this.list(1);
              Toast.success("保存成功！");
            } else {
              Toast.warning(resp.message)
            }
          },5000);
        })
      }
    }
  }
</script>