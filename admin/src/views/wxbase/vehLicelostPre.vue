<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">车牌遗失查询</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;width: 80%" class="text-right">
                  <tbody  >
                  <tr  >
                    <td style="width:6%" >
                      车牌类型：
                    </td>
                    <td style="width: 15%">
                      <select v-model="vehLicelostDto.hpzl" class="select2"  style="width:78%">
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in allcplx" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      号牌号码：
                    </td>
                    <td style="width: 8%">
                      <to-upper class="form-control" v-model="vehLicelostDto.hphm" idValue="form-field-1"
                                type="toUpper"></to-upper>
                    </td>
                    <td  style="width: 6%">
                      录入日期：
                    </td>
                    <td style="width: 20%" >
                      <times v-bind:startTime="startTime"
                             v-bind:endTime="endTime"></times>
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
        车牌录入
      </button>&nbsp;
      <button v-on:click="addAll()" class="btn  btn-sm btn-success btn-round">
        <i class="ace-icon fa fa-edit"></i>
        车牌批量录入
      </button>&nbsp;
      <div class="space-2"></div>
    </div>
    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>车牌类型</th>
            <th>号牌号码</th>
            <th>录入部门</th>
            <th>录入人</th>
            <th>录入日期</th>
            <th>状态</th>
            <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehLicelost in vehLicelosts">
              <td>{{allcplx|optionMapKV(vehLicelost.hpzl)}}</td>
              <td>{{vehLicelost.hphm}}</td>
              <td>{{maps|optionMapKV(vehLicelost.sysOrgCode)}}</td>
              <td>{{usersMap|optionMapKV(vehLicelost.createBy)}}</td>
              <td>{{vehLicelost.createTime}}</td>
              <td><span v-if="vehLicelost.zt =='1'">已录入</span><span v-else-if="vehLicelost.zt =='2'">已申领</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehLicelost)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(vehLicelost.id)" class="btn btn-xs btn-danger" title="删除">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
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
            <h4 class="modal-title">车牌录入</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车牌类型</label>
                      <div class="col-sm-10">
                        <select v-model="vehLicelost.hpzl" class="form-control">
                          <option v-for="(value, key) in allcplx" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">号牌号码</label>
                      <div class="col-sm-10">
                        <to-upper class="form-control" v-model="vehLicelost.hphm" idValue="form-control-1"
                                  type="toUpper"></to-upper>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">领取地地址</label>
                      <div class="col-sm-10">
                        <input v-model="vehLicelost.lqdz" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">领取地电话</label>
                      <div class="col-sm-10">
                        <input v-model="vehLicelost.lqdh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-10">
                        <input v-model="vehLicelost.bz" class="form-control">
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

    <div id="form-modal-batch" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">车牌批量录入</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" ref="_formControl" id="_formControl">
              <div class="form-group">
                <label class="col-sm-2 control-label">领取地地址</label>
                <div class="col-sm-4">
                  <input v-model="vehLicelost.lqdz" class="form-control">
                </div>
                <label class="col-sm-1 control-label">领取地电话</label>
                <div class="col-sm-4">
                  <input v-model="vehLicelost.lqdh" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-9">
                  <input v-model="vehLicelost.bz" class="form-control">
                </div>
              </div>
              <button style="margin-bottom: 15px" id="addCpInfoBtn" type="button" class="ace-icon glyphicon glyphicon-plus btn btn-success" v-on:click="addCpInfo()">添加车牌信息</button>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="saveAll()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
import Pagination from "../../components/pagination";
import Times from "../../components/time";
import ToUpper from "../../components/upperLowerTransform";
export default {
  components: {Pagination, Times, ToUpper},
  name: "wxbase-vehLicelost",
  data: function () {
    return {
      vehLicelost: {},
      vehLicelostDto: {},
      vehLicelosts: [],
      allcplx: [],
      maps: [],
      usersMap: [],
      index:0,
      indexs:[]
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getCplx();
      _this.usersMap = Tool.getUserCode();
      _this.maps = Tool.getDeptUser() ;
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-vehLicelost-sidebar");

    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.vehLicelostDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehLicelostDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 获取车牌类型
       */
      getCplx(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getCplx', {
        }).then((response)=>{
          let resp = response.data;
          _this.allcplx = resp.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehLicelost = {};
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 点击【批量新增】
       */
      addAll() {
        let _this = this;
        _this.vehLicelost = {};
        $("#addCpInfoBtn").nextAll().remove();
        _this.index = 0;
        _this.indexs = [];
        $("#form-modal-batch").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehLicelost) {
        let _this = this;
        _this.vehLicelost = $.extend({}, vehLicelost);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.vehLicelostDto.page=page;
        _this.vehLicelostDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLicelost/prelist',  _this.vehLicelostDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehLicelosts = resp.content.list;
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
                || !Validator.require(_this.vehLicelost.hpzl,"车牌类型")
                || !Validator.require(_this.vehLicelost.hphm,"号牌号码")
                || !Validator.require(_this.vehLicelost.lqdz,"领取地地址")
                || !Validator.require(_this.vehLicelost.lqdh,"领取地电话")
                || !Validator.length(_this.vehLicelost.createBy, "创建人", 1, 128)
                || !Validator.length(_this.vehLicelost.updateBy, "更新人", 1, 128)
                || !Validator.length(_this.vehLicelost.sysOrgCode, "所属部门", 1, 64)
                || !Validator.length(_this.vehLicelost.hpzl, "号牌种类", 1, 32)
                || !Validator.length(_this.vehLicelost.hphm, "号牌号码", 1, 32)
                || !Validator.length(_this.vehLicelost.lqdz, "领取地址", 1, 512)
                || !Validator.length(_this.vehLicelost.lqdh, "领取电话", 1, 11)
                || !Validator.length(_this.vehLicelost.lqrxm, "领取人姓名", 1, 32)
                || !Validator.length(_this.vehLicelost.lqrdh, "领取人电话", 1, 32)
                || !Validator.length(_this.vehLicelost.sfzmhm, "身份证明号码", 1, 18)
                || !Validator.length(_this.vehLicelost.lqsm, "领取说明", 1, 4000)
                || !Validator.length(_this.vehLicelost.zt, "状态", 1, 2)
                || !Validator.length(_this.vehLicelost.openid, "微信用户openid", 1, 32)
                || !Validator.length(_this.vehLicelost.bz, "备注", 1, 4000)
                || !Validator.length(_this.vehLicelost.lsh, "流水号", 1, 45)
        ) {
          return;
        }
        if(!Validator.checkIsGH(_this.vehLicelost.lqdh,"领取地电话")){
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLicelost/save', _this.vehLicelost).then((response)=>{
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
       * 批量保存
       */
      saveAll(){
        let _this = this;

        // 保存校验
        if (1 != 1
            || !Validator.require(_this.vehLicelost.lqdz,"领取地地址")
            || !Validator.require(_this.vehLicelost.lqdh,"领取地电话")
            || !Validator.length(_this.vehLicelost.createBy, "创建人", 1, 128)
            || !Validator.length(_this.vehLicelost.updateBy, "更新人", 1, 128)
            || !Validator.length(_this.vehLicelost.sysOrgCode, "所属部门", 1, 64)
            || !Validator.length(_this.vehLicelost.hpzl, "号牌种类", 1, 32)
            || !Validator.length(_this.vehLicelost.hphm, "号牌号码", 1, 32)
            || !Validator.length(_this.vehLicelost.lqdz, "领取地址", 1, 512)
            || !Validator.length(_this.vehLicelost.lqdh, "领取电话", 1, 32)
            || !Validator.length(_this.vehLicelost.lqrxm, "领取人姓名", 1, 32)
            || !Validator.length(_this.vehLicelost.lqrdh, "领取人电话", 1, 32)
            || !Validator.length(_this.vehLicelost.sfzmhm, "身份证明号码", 1, 32)
            || !Validator.length(_this.vehLicelost.lqsm, "领取说明", 1, 4000)
            || !Validator.length(_this.vehLicelost.zt, "状态", 1, 2)
            || !Validator.length(_this.vehLicelost.openid, "微信用户openid", 1, 32)
            || !Validator.length(_this.vehLicelost.bz, "备注", 1, 4000)
            || !Validator.length(_this.vehLicelost.lsh, "流水号", 1, 45)
            || !Validator.checkIsGH(_this.vehLicelost.lqdh,"领取地电话")
        ) {
          return;
        }
        let cpInfos = [];
        if(_this.indexs.length>0){
          let flag = false;
          for(let i=0;i<_this.indexs.length;i++){
            let index = _this.indexs[i];
            if(!$('#addCpInfoHphm'+index).val()){
              flag = true;
            }else {
              let obj = {
                'cplx':$('#addCpInfoCplx'+index).val(),
                'hphm':$('#addCpInfoHphm'+index).val()
              };
              cpInfos.push(obj);
            }
          }
          if(flag){
            Toast.warning("存在未填写的号牌号码");
            return;
          }
        }else{
          Toast.warning("请添加车牌信息");
          return ;
        }
        _this.vehLicelost.cpInfos = cpInfos;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLicelost/saveAll', _this.vehLicelost).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            $("#form-modal-batch").modal("hide");
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLicelost/delete/' + id).then((response)=>{
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
       * 添加车牌信息
       */
      addCpInfo(){
        let _this = this;
        let options = '';
        $.each(_this.allcplx,function(key,values){
          let option = '<option value="'+key+'">'+values+'</option>';
          options = options+option;
        });
        _this.index =  _this.index+1;
        let box=$('<div class="form-group">\n' +
            '         <label class="col-sm-2 control-label">车牌类型</label>\n' +
            '         <div class="col-sm-4">\n' +
            '         <select class="form-control" id="addCpInfoCplx'+_this.index+'">\n' +
            options +
            '         </select>\n' +
            '         </div>\n' +
            '         <label class="col-sm-1 control-label">号牌号码</label>\n' +
            '         <div class="col-sm-4">\n' +
            '         <input class="form-control" style="text-transform:uppercase" id="addCpInfoHphm'+_this.index+'">\n' +
            '         </div>\n' +
            '         <button id="cpInfoDel'+_this.index+'" class="btn btn-xs btn-danger">\n' +
            '         <i class="ace-icon fa fa-trash-o bigger-120"></i>\n' +
            '         </button>'+
            '         </div>');
        $('#_formControl').append(box);
        _this.indexs.push(_this.index);
        $('#cpInfoDel'+_this.index).on("click",function(){
          _this.indexs.splice(_this.indexs.indexOf(Number.parseInt(this.id.charAt(this.id.length-1))),1);
          this.parentElement.remove();
        });
      }
    }
  }
</script>