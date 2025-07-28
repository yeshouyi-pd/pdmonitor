<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">出现次数预测查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  出现日期：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="cxstime" end-id="cxetime"></times>
                </td>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
                  <select v-model="forecastNumDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
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

    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="importData()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-upload"></i>
        导入
      </button>
    </p>

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>监测点</th>
          <th>设备名称</th>
          <th>设备sn</th>
          <th>出现时间</th>
          <th>出现次数</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in forecastNums">
          <td>{{deptMap|optionMapKV(item.depcode)}}</td>
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.cxsj}}</td>
          <td>{{item.cxcs}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(item)" class="btn btn-xs btn-info" title="修改">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
              </button>
              <button v-on:click="del(item.id)" class="btn btn-xs btn-danger" title="删除">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">新增</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">设备名称</label>
                <div class="col-sm-10">
                  <select v-model="forecastNum.sbbh" class="form-control">
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">出现次数</label>
                <div class="col-sm-10">
                  <input v-model="forecastNum.cxcs" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">出现时间</label>
                <div class="col-sm-10">
                  <date-time @methodName="cxsjDate" style="width:100%" idValue="cxsj" :setValue="forecastNum.cxsj"></date-time>
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

    <div id="form-modal-import" class="modal fade" tabindex="-1" role="dialog" >
      <div class="modal-dialog" role="document"  style="width:30%">
        <div class="modal-content"  >
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">导入</h4>
          </div>
          <div class="modal-body" >
            <div class="import-file-div">
              <div class="file-template">
                <label>文件选择:</label>
                <div class="simulate-input">{{submitFileName}}</div>
                <button class="plane-icon-btn btn-look" v-on:click="importFile()">浏览</button>
              </div>
              <input type="file" accept=".xls,.xlsx" ref="file" name="file" class="file-hidden"
                     v-on:change="uploadFile()" />
              <div class="file-template-a"><a v-on:click="exportTemplate()">下载模板</a></div>
            </div>
          </div>
          <div class="modal-footer">
            <button v-on:click="importFileCancle()" type="button" class="btn btn-default">取消</button>
            <button v-on:click="importFileConfim()" type="button" class="btn btn-primary">确认</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="form-modal-import-error" class="modal fade" tabindex="-1" role="dialog" >
      <div class="modal-dialog" role="document"  style="width:30%">
        <div class="modal-content"  >
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">导入结果</h4>
          </div>
          <div class="modal-body" >
            <div v-html="resultMessage"></div>
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
import DateTime from "@/components/dateTime";
import Times from "@/components/times";

export default {
  components: {Pagination,Times,DateTime},
  name: 'forecast-num',
  data: function (){
    return {
      forecastNum:{},
      forecastNumDto:{},
      forecastNums:[],
      deptMap:[],
      waterEquipments:[],
      submitFile:null,
      submitFileName:'',
      resultMessage:''
    }
  },
  mounted() {
    let _this = this;
    _this.deptMap = Tool.getDeptUser();
    _this.list(1);
    _this.$refs.pagination.size = 10;
    _this.findDeviceInfo();
  },
  methods: {
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
      })
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.forecastNumDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.forecastNumDto.etime = rep;
      _this.$forceUpdate();
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.forecastNumDto.page=page;
      _this.forecastNumDto.size=_this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/forecastNum/list', _this.forecastNumDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.forecastNums = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    //新增
    add(){
      let _this = this;
      _this.forecastNum = {};
      $("#form-modal").modal("show");
    },
    cxsjDate(rep){
      let _this = this;
      _this.forecastNum.cxsj = rep;
      _this.$forceUpdate();
    },
    //保存
    save(){
      let _this = this;
      // 保存校验
      if (1 != 1
          || !Validator.require(_this.forecastNum.sbbh, "设备名称")
          || !Validator.require(_this.forecastNum.cxcs, "出现次数")
          || !Validator.require(_this.forecastNum.cxsj, "出现时间")
          || !Validator.checkisNaN(_this.forecastNum.cxcs, "出现次数")
      ) {
        return;
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/forecastNum/save', _this.forecastNum).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message);
        }
      })
    },
    //修改
    edit(item){
      let _this = this;
      _this.forecastNum = $.extend({}, item);
      _this.$forceUpdate();
      $("#form-modal").modal("show");
    },
    //删除
    del(id){
      let _this = this;
      Confirm.show("删除后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/forecastNum/delete/' + id).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            _this.list(1);
            Toast.success("删除成功！");
          }
        })
      });
    },
    //导入
    importData(){
      let _this = this;
      $(_this.$refs.file).val('');
      _this.submitFileName = '';
      _this.submitFile = null;
      $("#form-modal-import").modal("show");
    },
    importFile: function () {
      let _this = this;
      $(_this.$refs.file).trigger('click');
    },
    //上传文件
    uploadFile: function () {
      let _this = this;
      let fileList = _this.$refs.file.files;
      _this.submitFile = fileList[0];
      _this.submitFileName = _this.submitFile.name;
    },
    //导出模板
    exportTemplate: function () {
      let _this = this;
      _this.preventEnter();
      let url = process.env.VUE_APP_SERVER + "/monitor/import/exportForecastNumTemplate";
      window.location.href = url;
    },
    //确认导入
    importFileConfim: function () {
      let _this = this;
      _this.preventEnter();
      if (!_this.submitFile) {
        Toast.warning("请选择需要上传的文件");
        return;
      }
      Loading.show();
      let formData = new FormData();
      formData.append("file", this.submitFile);
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/import/importForecastNum', formData).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (response.status=='200') {
          if(resp.includes("导入成功")){
            _this.resultMsg(resp);
            _this.importFileCancle();
            _this.list(1);
          }else{
            Toast.warning(resp);
          }
        }
      })
    },
    //取消导入
    importFileCancle: function () {
      let _this = this;
      $(_this.$refs.file).val('');
      _this.submitFileName = '';
      _this.submitFile = null;
      $("#form-modal-import").modal("hide");
    },
    resultMsg(msg){
      let _this = this;
      if(msg.includes("href=")){
        _this.resultMessage = msg.substring(0,msg.indexOf("href=")+5) + process.env.VUE_APP_SERVER + msg.substring(msg.indexOf("href=")+5,msg.length);
      }else{
        _this.resultMessage = msg;
      }
      $("#form-modal-import-error").modal("show");
    },
    preventEnter:function (elem) {
      let elemTemp = elem ? elem : document.activeElement;
      if (elemTemp) elemTemp.blur();
    }
  }
}
</script>
<style scoped>
.import-file-div .file-hidden{
  display:none;
}
.import-file-div .file-template{
  display:flex;
  align-items:center;
}
.import-file-div .file-template-a{
  padding-top: 10px;
  display: block;
  display: flex;
  direction: rtl;
  cursor: pointer;
}
.simulate-input{
  margin-left: 10px;
  margin-right: 10px;
  height: 34px;
  width: 222px;
  border-radius: 4px;
  border: 1px solid #ccc;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 34px;
  padding-left: 6px;
}
</style>