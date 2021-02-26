<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">违法代码管理</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;" class="text-right  " >
                  <tbody  >
                  <tr  >
                    <td style="width: 8%" >
                      违法代码：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-3"  v-model="webchatIllcodeDto.wfdm"/>
                    </td>
                    <td style="width:8%" >
                      违法代码类别：
                    </td>
                    <td style="width: 10%">
                      <select v-model="webchatIllcodeDto.lb" class="form-control" id="form-field-select-1">
                        <option value="" selected>请选择</option>
                        <option value="1">机动车</option>
                        <option value="2">电动车</option>
                      </select>
                    </td>
                    <td style="width: 8%" >
                      违法行为：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-2"  v-model="webchatIllcodeDto.wfxw"/>
                    </td>

                    <td style="width: 8%" >
                      状态：
                    </td>
                    <td style="width: 10%">
                      <select v-model="webchatIllcodeDto.zt" class="form-control" id="form-field-select-3">
                        <option value="" selected>请选择</option>
                        <option value="1">启用</option>
                        <option value="2">禁用</option>
                      </select>
                    </td>
                    <td   colspan="2" class="text-center">
                      <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      &nbsp;&nbsp;&nbsp;
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
        <!-- query end -->
      </div><!-- col-md-12 -->
    </div><!-- row -->

    <div style="background: #F5F5F5;margin-top: -2px">
      <div class="space-2"></div>
      <button v-on:click="add()" class="btn  btn-sm btn-success btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增违法代码
      </button>&nbsp;
      <button v-on:click="importIllcode()" class="btn  btn-sm btn-success btn-round">
        <i class="ace-icon fa fa-edit"></i>
        导入
      </button>&nbsp;
      <button v-on:click="batchDelete()" class="btn  btn-sm btn-success btn-round">
        <i class="ace-icon fa fa-edit"></i>
        批量删除
      </button>&nbsp;
      <div class="space-2"></div>
    </div>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
              <label class="pos-rel">
                <input type="checkbox" class="ace" ref="selectAll">
                <span class="lbl"></span>
              </label>
            </th>
            <th>违法代码</th>
            <th>违法行为</th>
            <th>违法代码类别</th>
            <th>记分</th>
            <th>处罚金额</th>
            <th>创建用户</th>
            <th>创建时间</th>
            <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody ref="tbody">
      <tr v-for="webchatIllcode in webchatIllcodes">
              <td class="center">
                <label class="pos-rel" v-bind:id="webchatIllcode.id">
                  <input type="checkbox" class="ace" :value="webchatIllcode.id">
                  <span class="lbl"></span>
                </label>
              </td>
              <td>{{webchatIllcode.wfdm}}</td>
              <td>{{webchatIllcode.wfxw}}</td>
              <td><span v-if="webchatIllcode.lb =='1'">机动车</span><span v-else-if="webchatIllcode.lb =='2'">电动车</span></td>
              <td>{{webchatIllcode.jf}}</td>
              <td>{{webchatIllcode.fkje}}</td>
              <td>{{webchatIllcode.usercode}}</td>
              <td>{{webchatIllcode.fbsj}}</td>
              <td><span v-if="webchatIllcode.zt =='1'">启用</span><span v-else-if="webchatIllcode.zt =='2'">禁用</span></td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(webchatIllcode)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(webchatIllcode.id)" class="btn btn-xs btn-danger" title="删除">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    <div align="right"><pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination></div>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog" >
      <div class="modal-dialog" role="document"  style="width:60%">
        <div class="modal-content"  >
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">违法代码管理</h4>
          </div>
          <div class="modal-body" >
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">违法代码</label>
                      <div class="col-sm-3">
                        <input v-model="webchatIllcode.wfdm" class="form-control">
                      </div>

                      <label class="col-sm-2 control-label">违法行为</label>
                      <div class="col-sm-3">
                        <input v-model="webchatIllcode.wfxw" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">违法代码类别</label>
                      <div class="col-sm-3">
                        <div class="radio">
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="1" v-model="webchatIllcode.lb"/>
                            <span class="lbl">机动车</span>
                          </label>
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="webchatIllcode.lb"/>
                            <span class="lbl">电动车</span>
                          </label>
                        </div>
                      </div>
                      <label class="col-sm-2 control-label">违法代码状态</label>
                      <div class="col-sm-3">
                        <div class="radio">
                          <label>
                            <input name="form-field-radio1" type="radio" class="ace"  value="1" v-model="webchatIllcode.zt"/>
                            <span class="lbl">启用</span>
                          </label>
                          <label>
                            <input name="form-field-radio1" type="radio" class="ace"  value="2" v-model="webchatIllcode.zt"/>
                            <span class="lbl">禁用</span>
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">记分</label>
                      <div class="col-sm-3">
                        <input v-model="webchatIllcode.jf" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">处罚金额（元）</label>
                      <div class="col-sm-3">
                        <input v-model="webchatIllcode.fkje" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">处罚依据</label>
                      <div class="col-sm-8">
                        <input v-model="webchatIllcode.cfyj" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">违法条例</label>
                      <div class="col-sm-10">
                        <textarea v-model="webchatIllcode.wftl" rows="4" cols="102"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">其他处罚</label>
                      <div class="col-sm-10">
                        <textarea v-model="webchatIllcode.qtcf" rows="4" cols="102"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">措施</label>
                      <div class="col-sm-10">
                        <textarea v-model="webchatIllcode.cs" rows="4" cols="102"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-10">
                        <textarea v-model="webchatIllcode.bz" rows="4" cols="102"/>
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

    <div id="form-modal1" class="modal fade" tabindex="-1" role="dialog" >
      <div class="modal-dialog" role="document"  style="width:30%">
        <div class="modal-content"  >
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">违法代码导入</h4>
          </div>
          <div class="modal-body" >
            <div class="import-file-div">
              <div class="file-template">
                <label>文件选择:</label>
                <div class="simulate-input">{{submitFileName}}</div>
                <button class="plane-icon-btn btn-look" v-on:click="importIllcodeFile()">浏览</button>
              </div>
              <input type="file" accept=".xls,.xlsx" ref="file" name="file" class="file-hidden"
                    v-on:change="uploadFile()" />
              <div class="file-template-a"><a v-on:click="exportAssetTemplate()">下载模板</a></div>
            </div>
          </div>
          <div class="modal-footer">
            <button v-on:click="importFileCancle()" type="button" class="btn btn-default">取消</button>
            <button v-on:click="importFileConfim()" type="button" class="btn btn-primary">确认</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="form-modal2" class="modal fade" tabindex="-1" role="dialog" >
      <div class="modal-dialog" role="document"  style="width:30%">
        <div class="modal-content"  >
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">违法代码导入结果</h4>
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
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "wxbase-webchatIllcode",
    data: function() {
      return {
        webchatIllcode: {},
        webchatIllcodeDto:{},
        webchatIllcodes: [],
        decoder:{},
        submitFile:null,
        submitFileName:'',
        resultMessage:'',
        ids:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-webchatIllcode-sidebar");
      _this.initTable();
    },
    methods: {

      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.webchatIllcode = {};
        $("#form-modal").modal("show");
      },
      /**
       * 点击【编辑】
       */
      edit(webchatIllcode) {
        let _this = this;
        _this.webchatIllcode = $.extend({}, webchatIllcode);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.webchatIllcodeDto.page=page;
        _this.webchatIllcodeDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatIllcode/list',_this.webchatIllcodeDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.webchatIllcodes = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
          _this.$nextTick(function(){
            _this.updateTable(_this.webchatIllcodes,_this.$refs.pagination.size);
          })
        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;

        // 保存校验
        if (1 != 1
                || !Validator.require(_this.webchatIllcode.wfdm, "违法代码")
                || !Validator.length(_this.webchatIllcode.wfdm, "违法代码", 1, 15)
                || !Validator.require(_this.webchatIllcode.wfxw, "违法行为")
                || !Validator.length(_this.webchatIllcode.wfxw, "违法行为", 1, 4000)
                || !Validator.require(_this.webchatIllcode.wftl, "违法条例")
                || !Validator.length(_this.webchatIllcode.wftl, "违法条例", 1, 4000)
                || !Validator.require(_this.webchatIllcode.cfyj, "处罚依据")
                || !Validator.length(_this.webchatIllcode.cfyj, "处罚依据", 1, 4000)
                || !Validator.require(_this.webchatIllcode.jf, "记分")
                || !Validator.length(_this.webchatIllcode.jf, "记分", 1, 45)
                || !Validator.length(_this.webchatIllcode.qtcf, "其他处罚", 1, 400)
                || !Validator.length(_this.webchatIllcode.cs, "措施", 1, 400)
                || !Validator.length(_this.webchatIllcode.bz, "备注", 1, 400)
                || !Validator.length(_this.webchatIllcode.deptcode, "发布机构", 1, 45)
                || !Validator.length(_this.webchatIllcode.usercode, "发布用户", 1, 45)
                || !Validator.length(_this.webchatIllcode.zt, "状态", 1, 45)
                || !Validator.length(_this.webchatIllcode.lb, "违法代码类别", 1, 45)
                || !Validator.checkisNaN(_this.webchatIllcode.fkje,"处罚金额")
                || !Validator.checkIsFF(_this.webchatIllcode.jf,"记分")
        ) {
          return;
        }
        if(_this.webchatIllcode.fkje){
          _this.webchatIllcode.fkje = Number(_this.webchatIllcode.fkje);
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatIllcode/save', _this.webchatIllcode).then((response)=>{
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
        Confirm.show("删除违法代码后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatIllcode/delete/' + id).then((response)=>{
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
      importIllcode(){
        let _this = this;
        $(_this.$refs.file).val('');
        _this.submitFileName = '';
        _this.submitFile = null;
        $("#form-modal1").modal("show");
      },
      importIllcodeFile: function () {
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
      exportAssetTemplate: function () {
        let _this = this;
        _this.preventEnter();
        let url = process.env.VUE_APP_SERVER + "/wxbase/import/webchatIllcodeImport/exportIllcodeTemplate";
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
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/import/webchatIllcodeImport/importIllcode', formData).then((response)=>{
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
        $("#form-modal1").modal("hide");
      },
      resultMsg(msg){
        let _this = this;
        if(msg.includes("href=")){
          _this.resultMessage = msg.substring(0,msg.indexOf("href=")+5) + process.env.VUE_APP_SERVER + msg.substring(msg.indexOf("href=")+5,msg.length);
        }else{
          _this.resultMessage = msg;
        }
        $("#form-modal2").modal("show");
      },
      preventEnter:function (elem) {
        let elemTemp = elem ? elem : document.activeElement;
        if (elemTemp) elemTemp.blur();
      },
      /**
       * 初始化表格及点击事件
       */
      initTable(){
        let _this = this;
        $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
        //select/deselect all rows according to table header checkbox
        $('#simple-table input[type=checkbox]').eq(0).on('click', function(){
          let outThis = this;
          $('#simple-table').find('tbody > tr').each(function(){
            let inputKJ = this.firstElementChild.firstElementChild.firstElementChild;
            let id = inputKJ.defaultValue;
            if(outThis.checked){
              $(inputKJ).prop('checked', true);
              _this.ids.push(id);
            }else{
              $(inputKJ).prop('checked', false);
              _this.ids.splice(_this.ids.indexOf(id),1)
            }
          }).bind(outThis);
        });
        //select/deselect a row when the checkbox is checked/unchecked
        $('#simple-table').on('click', 'td input[type=checkbox]' , function(){
          let id = this.defaultValue;
          if(this.checked) {
            if(!_this.ids.includes(id)){
              _this.ids.push(id);
            }
          }else {
            if(_this.ids.includes(id)){
              _this.ids.splice(_this.ids.indexOf(id),1)
            }
          }
        });
      },

      /**
       * 更新表格
       */
      updateTable(webchatIllcodes,pageSize){
        let _this = this;
        //获取当前页面的数据
        let childrens = _this.$refs.tbody.children;
        let count =0;//计数
        if(childrens.length>0 && _this.ids.length>0){
          for(let i=0;i<childrens.length;i++){
            let trChild = childrens[i];
            if(_this.ids.includes(trChild.firstElementChild.firstElementChild.firstElementChild.defaultValue)){
              count++;
              $(trChild.firstElementChild.firstElementChild.firstElementChild).prop('checked', true);
            }else{
              $(trChild.firstElementChild.firstElementChild.firstElementChild).prop('checked', false);
            }
            if(childrens.length==count){
              $(_this.$refs.selectAll).prop('checked', true)
            }else{
              $(_this.$refs.selectAll).prop('checked', false)
            }
          }
        }else{
          $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
        }
      },
      batchDelete:function(){
        let _this = this;
        let webchatIllcodeDto = {};
        if(_this.ids.length<=0){
          Toast.warning("请先选择需要删除的数据");
          return ;
        }
        Confirm.show("删除违法代码后不可恢复，确认删除？", function () {
          Loading.show();
          webchatIllcodeDto.idList = _this.ids;
          _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatIllcode/deleteBatch',webchatIllcodeDto).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.ids = [];
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
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