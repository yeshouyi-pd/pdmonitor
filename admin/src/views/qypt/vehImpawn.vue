<template>


  <div>

    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">金融抵押信息录入</h4>
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
                      机动车序号：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-5"  class="form-control"  v-model="vehImpawnDto.xh"/>
                    </td>
                    <td style="width:6%" >
                      车辆识别代号：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="form-control"  v-model="vehImpawnDto.clsbdh"/>
                    </td>
                    <td style="width: 6%" >
                      主合同号：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-2"  v-model="vehImpawnDto.zhth"/>
                    </td>
                    <td style="width: 12%" >
                      抵押合同号：
                    </td>
                    <td style="width: 10%">
                      <input  class="form-control"  type="text" id="form-field-3"  v-model="vehImpawnDto.dyhth"/>
                    </td>


                  </tr>
                  <tr style="height: 10px"></tr>
                  <tr>
                    <td style="width: 6%" >
                      身份证明号码：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-4"  v-model="vehImpawnDto.sfzmhm"/>
                    </td>
                    <td  style="width: 6%">
                      录入日期：
                    </td>
                    <td style="width: 10%" >
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
        金融抵押信息录入
      </button>&nbsp;
      <div class="space-2"></div>
      <div class="space-2"></div>
    </div>
    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>机动车序号</th>
          <th>车辆识别代号</th>
          <th>主合同号</th>
          <th>抵押合同号</th>
          <th>抵押金额</th>
          <th>抵押登记日期</th>
          <th>解除抵押日期</th>
          <th>经办人</th>
          <th>身份证明名称</th>
          <th>身份证明号码</th>
          <th>管理部门</th>
          <th>创建人</th>
          <th>创建日期</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="vehImpawn in vehImpawns">

          <td>{{vehImpawn.xh}}</td>
          <td>{{vehImpawn.clsbdh}}</td>
          <td>{{vehImpawn.zhth}}</td>
          <td>{{vehImpawn.dyhth}}</td>
          <td>{{vehImpawn.dyje}}</td>
          <td>{{vehImpawn.dydjrq}}</td>
          <td>{{vehImpawn.jcrq}}</td>
          <td>{{usersMap|optionMapKV(vehImpawn.jbr)}}</td>
          <td>{{vehImpawn.sfzmmc}}</td>
          <td>{{decryptByDES(vehImpawn.sfzmhm)}}</td>
          <td>{{maps|optionMapKV(vehImpawn.glbm)}}</td>
          <td>{{usersMap|optionMapKV(vehImpawn.createBy)}}</td>
          <td>{{vehImpawn.createTime}}</td>
          <td><span v-if="vehImpawn.zt =='1'">已录入</span><span v-if="vehImpawn.zt =='2'">审核通过</span><span v-if="vehImpawn.zt =='3'">审核不通过</span></td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(vehImpawn)" class="btn btn-xs btn-info" title="修改">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
              </button>
              <button v-on:click="del(vehImpawn.id)" class="btn btn-xs btn-danger" title="删除">
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
      <div class="modal-dialog" role="document" style="width:70%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">金融抵押信息录入</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">

              <div class="form-group">
                <label class="col-sm-2 control-label">号牌种类*</label>
                <div class="col-sm-4">
                  <select v-model="vehImpawn.hpzl" class="form-control">
                    <option v-for="(value, key) in hpzl" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
                <label class="col-sm-2 control-label">号牌号码*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.hphm" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">机动车序号*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.xh" class="form-control">
                </div>
                <label class="col-sm-2 control-label">车辆识别代号*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.clsbdh" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">主合同号*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.zhth" class="form-control">
                </div>
                <label class="col-sm-2 control-label">抵押合同号*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.dyhth" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">抵押金额*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.dyje" class="form-control">
                </div>
                <label class="col-sm-2 control-label">抵押登记日期*</label>
                  <div class="col-sm-4">
                    <Date style="width:100%" @methodName="dyDate" idValue="dyDate" :setValue="vehImpawn.dydjrq"></Date>
<!--                    <input v-model="vehImpawn.dydjrq" class="form-control">-->
                  </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">机动车所有人身份证明名称*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.sfzmmc" class="form-control">
                </div>
                <label class="col-sm-2 control-label">机动车所有人身份证明号码*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.sfzmhm" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">抵押权人*</label>
                <div class="col-sm-4">
                  <select v-model="vehImpawn.dyqrdm" class="form-control">
                    <option v-for="(value, key) in dyqrdm" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
                <label class="col-sm-2 control-label">抵押权人行政区划*</label>
                <div class="col-sm-4">
<!--                  <input v-model="vehImpawn.xzqh" class="form-control">-->
                  <select v-model="vehImpawn.xzqh" class="form-control">
                    <option v-for="(value, key) in xzqh" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">抵押权人类型*</label>
                <div class="col-sm-4">
                  <select v-model="vehImpawn.dyqrlx" class="form-control">
                    <option v-bind:value="1">商业银行</option>
                    <option v-bind:value="2">汽车金融机构</option>
                  </select>
                </div>
                <label class="col-sm-2 control-label">抵押权人身份证名称*</label>
                <div class="col-sm-4">
                  <select v-model="vehImpawn.dyqrsfzmmc" class="form-control">
                    <option v-for="(value, key) in dyqrsfzmmc" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">抵押权人身份证号码*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.dyqrsfzmhm" class="form-control">
                </div>
                <label class="col-sm-2 control-label">抵押权人手机号*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.sjhm" class="form-control">
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">抵押状态*</label>
                <div class="col-sm-4">
                  <select v-model="vehImpawn.dyzt" class="form-control">
                    <option v-for="(value, key) in dyzt" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
              </div>


              <div class="form-group">
                <label class="col-sm-2 control-label">邮政编码</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.yzbm" class="form-control">
                </div>
                <label class="col-sm-2 control-label">详细地址*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.xxdz" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">解除抵押日期（非必填项，解除抵押需填写）</label>
                <div class="col-sm-4">
                  <Date style="width:100%" @methodName="jcDate" idValue="jcDate" :setValue="vehImpawn.jcrq"></Date>
                </div>
                <label class="col-sm-2 control-label">联系电话*</label>
                <div class="col-sm-4">
                  <input v-model="vehImpawn.lxdh" class="form-control">
                </div>

              </div>

              <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-10">
                  <textarea   v-model="vehImpawn.bz" style="height: 50px" class="autosize-transition form-control" ></textarea>
                </div>
              </div>
              <h6 class="header blue lighter less-margin">图片</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">图片</label>
                <div class="col-sm-10">
                  <show-pic :pics="tpLj"></show-pic>
                  <file input-id="tp" :suffixs="picSuffixs" :afterUpload="tpAfterUpload"></file>
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
import Times from "../../components/time";
import Date from "../../components/date";
import DateTime from "../../components/dateTime";
import File from "../../components/file";
import ShowPic from "../../components/showpic";
import { videoPlayer } from 'vue-video-player';
import 'video.js/dist/video-js.css';
import 'vue-video-player/src/custom-theme.css';
import axios from 'axios'
var formData = new window.FormData() // vue 中使用此方法创建FromData对象 window.FormData(),否则会报 'FormData isn't definded'
export default {
  components: {Pagination,Times,Date,DateTime,File,ShowPic,videoPlayer},
  name: "wxbase-vehImpawn",
  data: function() {
    return {
      vehImpawn: {},
      vehImpawnDto:{},
      vehImpawns: [],
      dyzt:[],
      hpzl:[],
      dyqrlx:[],
      dyqrsfzmmc:[],
      usersMap:{},
      maps: [],
      picSuffixs:['png','jpg','gif','jpeg'],
      tpLj:[],
      xzqh:[],
      dyqrdm:[]
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
    _this.getDyqr();
    _this.getHpzl();
    _this.getXzqh();
    _this.dyzt={0: "已解除抵押", 1: "抵押中"};
    _this.dyqrsfzmmc={B: "组织机构代码证", N: "统一社会信用代码"};
    _this.maps = Tool.getDeptUser() ;
    _this.usersMap = Tool.getUserCode();
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("wxbase-vehImpawn-sidebar");

  },
  methods: {
    decryptByDES(ciphertext) {
      if(ciphertext != undefined){
        let key = "11111111";
        let keyHex = CryptoJS.enc.Utf8.parse(key);
        let decrypted = CryptoJS.DES.decrypt({
          ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
        }, keyHex, {
          mode: CryptoJS.mode.ECB,
          padding: CryptoJS.pad.Pkcs7,
        });
        return decrypted.toString(CryptoJS.enc.Utf8);
      }
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.vehImpawnDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.vehImpawnDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 报废日期
     */
    dyDate(rep){
      let _this = this;
      _this.vehImpawn.dydjrq = rep;
      _this.$forceUpdate();
    },
    /**
     * 报废日期
     */
    jcDate(rep){
      let _this = this;
      _this.vehImpawn.jcrq = rep;
      _this.$forceUpdate();
    },
    /**
     * 获取抵押权人
     */
    getDyqr(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDyqr', {
      }).then((response)=>{
        let resp = response.data;
        _this.dyqrdm = resp.content;
      })
    },

    /**
     * 获取号牌种类
     */
    getHpzl(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getHpzl', {
      }).then((response)=>{
        let resp = response.data;
        _this.hpzl = resp.content;
      })
    },

    /**
     * 获取行政区划
     */
    getXzqh(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getXzqh', {
      }).then((response)=>{
        let resp = response.data;
        _this.xzqh = resp.content;
      })
    },

    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.vehImpawn = {};
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(vehImpawn) {
      let _this = this;
      _this.vehImpawn = $.extend({}, vehImpawn);
      _this.vehImpawn.sfzmhm = _this.decryptByDES(vehImpawn.sfzmhm);
      _this.vehImpawn.dyqrsfzmhm = _this.decryptByDES(vehImpawn.dyqrsfzmhm);
      _this.vehImpawn.lxdh = _this.decryptByDES(vehImpawn.lxdh);
      _this.vehImpawn.sjhm = _this.decryptByDES(vehImpawn.sjhm);
      _this.getFileInfo(_this.vehImpawn.lsh,"1");
      $("#form-modal").modal("show");
    },

    /**
     * 获取当前信息的图片
     */
    getFileInfo(lsh,zplx){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatFileinfo/getFileinfoByLsh/'+ lsh+"/"+zplx).then((response)=>{
        let resp = response.data;
        if("1" == zplx){
          _this.tpLj = resp.content;
        }
      })
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.vehImpawnDto.page=page;
      _this.vehImpawnDto.size=_this.$refs.pagination.size;
      _this.vehImpawnDto.zt="1";
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawn/list',_this.vehImpawnDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.vehImpawns = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);

      })
    },

    /**
     * 点击【保存】
     */
    save() {
      let _this = this;
      let vehImpawnDto = {};
      if(_this.vehImpawn.dyzt=='0'&&!Validator.require(_this.vehImpawn.jcrq, "解除抵押日期")){
        return;
      }
      // 保存校验
      if (1 != 1

          || !Validator.require(_this.vehImpawn.hpzl, "号牌种类")
          || !Validator.length(_this.vehImpawn.hpzl, "号牌种类", 1, 2)
          || !Validator.require(_this.vehImpawn.hphm, "号牌号码")
          || !Validator.length(_this.vehImpawn.hphm, "号牌号码", 1, 20)
          || !Validator.require(_this.vehImpawn.xh, "机动车序号")
          || !Validator.length(_this.vehImpawn.xh, "机动车序号", 1, 14)
          || !Validator.require(_this.vehImpawn.clsbdh, "车辆识别代号")
          || !Validator.length(_this.vehImpawn.clsbdh, "车辆识别代号", 1, 20)
          || !Validator.require(_this.vehImpawn.zhth, "主合同号")
          || !Validator.length(_this.vehImpawn.zhth, "主合同号", 1, 64)
          || !Validator.require(_this.vehImpawn.dyhth, "抵押合同号")
          || !Validator.length(_this.vehImpawn.dyhth, "抵押合同号", 1, 32)
          || !Validator.require(_this.vehImpawn.dydjrq, "抵押登记日期")
          || !Validator.require(_this.vehImpawn.dyje,"抵押金额")
          || !Validator.checkisNaN(_this.vehImpawn.dyje,"抵押金额")
          || !Validator.require(_this.vehImpawn.sfzmmc, "机动车所有人身份证明名称")
          || !Validator.length(_this.vehImpawn.sfzmmc, "机动车所有人身份证明名称", 1, 10)
          || !Validator.require(_this.vehImpawn.sfzmhm, "机动车所有人身份证明号码")
          || !Validator.length(_this.vehImpawn.sfzmhm, "机动车所有人身份证明号码", 1, 18)
          || !Validator.lengthCorrect(_this.vehImpawn.sfzmhm, "机动车所有人身份证明号码", 18)
          || !Validator.require(_this.vehImpawn.xzqh, "抵押权人行政区划")
          || !Validator.length(_this.vehImpawn.xzqh, "抵押权人行政区划", 1, 128)
          || !Validator.require(_this.vehImpawn.xxdz, "详细地址")
          || !Validator.length(_this.vehImpawn.xxdz, "详细地址", 1, 256)
          || !Validator.length(_this.vehImpawn.yzbm, "邮政编码", 1, 6)
          || !Validator.require(_this.vehImpawn.lxdh, "联系电话")
          || !Validator.checkIsGH(_this.vehImpawn.lxdh, "联系电话")
          || !Validator.length(_this.vehImpawn.lxdh, "联系电话", 1, 11)
          || !Validator.length(_this.vehImpawn.xgzl, "相关资料", 1, 20)
          || !Validator.length(_this.vehImpawn.bz, "备注", 1, 1000)
          || !Validator.require(_this.vehImpawn.dyqrdm,"抵押权人")
          || !Validator.length(_this.vehImpawn.dyqrdm, "抵押权人", 1, 128)
          || !Validator.require(_this.vehImpawn.dyqrlx, "抵押权人类型")
          || !Validator.length(_this.vehImpawn.dyqrlx, "抵押权人类型", 1, 20)
          || !Validator.require(_this.vehImpawn.dyqrsfzmmc, "抵押权人身份证名称")
          || !Validator.length(_this.vehImpawn.dyqrsfzmmc, "抵押权人身份证名称", 1, 20)
          || !Validator.require(_this.vehImpawn.dyqrsfzmhm, "抵押权人身份证号码")
          || !Validator.length(_this.vehImpawn.dyqrsfzmhm, "抵押权人身份证号码", 1, 18)
          || !Validator.lengthCorrect(_this.vehImpawn.dyqrsfzmhm, "抵押权人身份证号码", 18)
          || !Validator.require(_this.vehImpawn.sjhm, "抵押权人手机号")
          || !Validator.checkIsGH(_this.vehImpawn.sjhm, "抵押权人手机号")
          || !Validator.length(_this.vehImpawn.sjhm, "抵押权人手机号", 1, 11)
          || !Validator.require(_this.vehImpawn.dyzt, "抵押状态")
          || !Validator.length(_this.vehImpawn.dyzt, "抵押状态", 1, 1)

      ) {
        return;
      }
      if(null==_this.tpLj||_this.tpLj.length<=0){
        Toast.warning("请上传图片");
        return;
      }
      vehImpawnDto = $.extend({}, _this.vehImpawn);
      if(_this.tpLj.length>0){
        let tpListStr = [];
        for(var i=0;i<_this.tpLj.length;i++){
          tpListStr.push(_this.tpLj[i].zplj);
        }
        vehImpawnDto.tpListStr = tpListStr;
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawn/save', vehImpawnDto).then((response)=>{
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
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawn/delete/' + id).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            _this.list(1);
            Toast.success("删除成功！");
          }
        })
      });
    },
    tpAfterUpload(res){
      let _this = this;
      if(res && res.success){
        let obj = {
          'zplj':res.content
        };
        _this.tpLj.push(obj);
      }else{
        Toast.error(res.content);
      }
    }
  }
}
</script>
