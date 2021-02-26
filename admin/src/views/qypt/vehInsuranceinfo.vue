<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">保险信息录入</h4>
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
                      号牌种类：
                    </td>
                    <td style="width: 8%">
                      <select v-model="vehInsuranceinfoDto.hpzl" class="form-control" >
                        <option value="" >请选择</option>
                        <option v-for="(value, key) in hpzl" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      号牌号码：
                    </td>
                    <td style="width: 8%">
                      <to-upper type="toUpper" idValue="form-field-1" class="form-control"
                                v-model="vehInsuranceinfoDto.hphm"></to-upper>
                    </td>
                    <td style="width: 6%" >
                      车辆识别代号：
                    </td>
                    <td style="width: 8%">
                      <to-upper type="toUpper" idValue="form-field-2" class="form-control"
                                v-model="vehInsuranceinfoDto.clsbdh"></to-upper>
                    </td>
                    <td style="width: 12%" >
                      被保险人身份证明号码：
                    </td>
                    <td style="width: 10%">
                      <to-upper type="toUpper" idValue="form-field-3" class="form-control"
                                v-model="vehInsuranceinfoDto.bbxrsfzmhm"></to-upper>
                    </td>


                  </tr>
                  <tr style="height: 10px"></tr>
                  <tr>
                    <td style="width: 6%" >
                      保险凭证号：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-4"  v-model="vehInsuranceinfoDto.bxpzh"/>
                    </td>
                    <td style="width: 6%">
                      交强险投标状态：
                    </td>
                    <td style="width: 8%" >
                      <select v-model="vehInsuranceinfoDto.jqxtbzt"  class="form-control">
                        <option value="" >请选择</option>
                        <option value="1" >确认</option>
                        <option value="2">一般投保退保</option>
                        <option value="3">重复投保退保</option>
                        <option value="4">起保前退保</option>
                      </select>
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
        保险录入
      </button>&nbsp;
      <div class="space-2"></div>
    </div>
    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>号牌种类</th>
        <th>号牌号码</th>
        <th>车辆识别代号</th>
        <th>保险凭证号码</th>
        <th>保险公司</th>
        <th>生效日期(保险日期开始时间)</th>
        <th>终止日期(保险日期结束时间)</th>
        <th>被保险人名称</th>
        <th>被保险人身份证明号码</th>
        <th>交强险投标状态</th>
        <th>录入人</th>
        <th>录入日期</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehInsuranceinfo in vehInsuranceinfos">
              <td>{{hpzl|optionMapKV(vehInsuranceinfo.hpzl)}}</td>
              <td>{{vehInsuranceinfo.hphm}}</td>
              <td>{{vehInsuranceinfo.clsbdh}}</td>
              <td>{{vehInsuranceinfo.bxpzh}}</td>
              <td>{{maps|optionMapKV(vehInsuranceinfo.sysOrgCode)}}</td>
              <td>{{vehInsuranceinfo.sxrq}}</td>
              <td>{{vehInsuranceinfo.zzrq}}</td>
              <td>{{vehInsuranceinfo.bbxr}}</td>
              <td>{{decryptByDES(vehInsuranceinfo.bbxrsfzmhm)}}</td>
              <td><span v-if="vehInsuranceinfo.jqxtbzt =='1'">确认</span><span v-else-if="vehInsuranceinfo.jqxtbzt=='2'">一般投保退保</span><span v-else-if="vehInsuranceinfo.jqxtbzt=='3'">重复投保退保</span><span v-else-if="vehInsuranceinfo.jqxtbzt=='4'">起保前退保</span></td>
              <td>{{usersMap|optionMapKV(vehInsuranceinfo.createBy)}}</td>
              <td>{{vehInsuranceinfo.createTime}}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehInsuranceinfo)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(vehInsuranceinfo.id)" class="btn btn-xs btn-danger" title="删除">
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
      <div class="modal-dialog" role="document"  style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">保险录入</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">

                    <div class="form-group">
                      <label class="col-sm-2  control-label">号牌种类</label>
                      <div class="col-sm-4">
                        <select v-model="vehInsuranceinfo.hpzl" class="form-control">
                          <option v-for="(value, key) in hpzl" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                      <label class="col-sm-2 control-label">号牌号码</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.hphm" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车辆识别代号*</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.clsbdh" class="form-control">
                      </div>

                      <label class="col-sm-2 control-label">保险凭证号码*</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.bxpzh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">

                      <label class="col-sm-2 control-label">保险日期*</label>
                      <div class="col-sm-4">
                        <times v-bind:startTime="sxrq" v-bind:start-id="'sxrq'" v-bind:svalue="vehInsuranceinfo.sxrq"
                               v-bind:endTime="zzrq" v-bind:end-id="'zzrq'" v-bind:evalue="vehInsuranceinfo.zzrq"></times>
                      </div>
                      <label class="col-sm-2 control-label">保险金额*</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.bxje" class="form-control">
                      </div>
                    </div>


                    <div class="form-group">
                      <label class="col-sm-2 control-label">被保险人名称*</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.bbxr" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">被保险人身份证明号码*</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.bbxrsfzmhm" class="form-control" >
                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-sm-2 control-label">交强险投标状态*</label>
                      <div class="col-sm-10">
                        <div class="radio">
                          <label>
                            <input name="form-field-radio1" type="radio" class="ace"  value="1" v-model="vehInsuranceinfo.jqxtbzt"/>
                            <span class="lbl">确认</span>
                          </label>
                          <label>
                            <input name="form-field-radio1" type="radio" class="ace"  value="2" v-model="vehInsuranceinfo.jqxtbzt"/>
                            <span class="lbl">一般投保退保</span>
                          </label>
                          <label>
                            <input name="form-field-radio1" type="radio" class="ace"  value="3" v-model="vehInsuranceinfo.jqxtbzt"/>
                            <span class="lbl">重复投保退保</span>
                          </label>
                          <label>
                            <input name="form-field-radio1" type="radio" class="ace"  value="4" v-model="vehInsuranceinfo.jqxtbzt"/>
                            <span class="lbl">起保前退保</span>
                          </label>
                        </div>


                      </div>
                    </div>

              <h6 class="header blue lighter less-margin">车船税纳税信息</h6>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车船税纳税情况</label>
                      <div class="col-sm-4">
                        <div class="radio">
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="1" v-model="vehInsuranceinfo.ccsnsqk"/>
                            <span class="lbl">完税</span>
                          </label>
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="vehInsuranceinfo.ccsnsqk"/>
                            <span class="lbl">免税</span>
                          </label>
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="3" v-model="vehInsuranceinfo.ccsnsqk"/>
                            <span class="lbl">减税</span>
                          </label>
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="4" v-model="vehInsuranceinfo.ccsnsqk"/>
                            <span class="lbl">拒缴</span>
                          </label>
                        </div>
                      </div>
                      <label class="col-sm-2 control-label">车船税纳税金额</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.ccsnsje" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车船税完税/免税凭证号码</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.wspzhm" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">车船税纳税人识别号</label>
                      <div class="col-sm-4">
                        <input v-model="vehInsuranceinfo.nsrsbh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车船税纳税日期</label>
                      <div class="col-sm-4">
                        <Date style="width:100%" @methodName="ccsnsrqDate" idValue="ccsnsrqDate"
                              :setValue="vehInsuranceinfo.ccsnsrq"></Date>
                      </div>
                      <label class="col-sm-2 control-label">纳税所属时期起止日期</label>
                      <div class="col-sm-4">
                        <times v-bind:startTime="ccsnsqsrq" v-bind:start-id="'ccsnsqsrq'" v-bind:svalue="vehInsuranceinfo.ccsnsqsrq"
                               v-bind:endTime="ccsnszzrq" v-bind:end-id="'ccsnszzrq'" v-bind:evalue="vehInsuranceinfo.ccsnszzrq"></times>

                      </div>
                    </div>

                    <div class="form-group">
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-10">
                        <input v-model="vehInsuranceinfo.bz" class="form-control">
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
import ToUpper from "../../components/upperLowerTransform";
export default {
  components: {Pagination, Times, Date, DateTime, ToUpper},
  name: "wxbase-vehInsuranceinfo",
  data: function () {
    return {
      vehInsuranceinfo: {},
      vehInsuranceinfoDto: {},
      vehInsuranceinfos: [],
      hpzl: [],
      maps: [],
      usersMap: [],

    }
  },
  computed: {
    value2Computed: {
      get: function () {
        return this.value2;
      },
      set: function (val) {
        this.value2 = val.toUpperCase();
      }
    }
  },
  mounted: function () {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
    _this.maps = Tool.getDeptUser();
    _this.usersMap = Tool.getUserCode();
    _this.getHpzl();

  },
  methods: {

      /**
       * 日期
       */
      ccsnsrqDate(rep) {
        let _this = this;
        _this.vehInsuranceinfo.ccsnsrq = rep;
        _this.$forceUpdate();
      },


      //DES 解密
      decryptByDES(ciphertext) {
        if (ciphertext != undefined) {
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
        _this.vehInsuranceinfoDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehInsuranceinfoDto.etime = rep;
        _this.$forceUpdate();
      },

      /**
       *开始时间
       */
      sxrq(rep){
        let _this = this;
        _this.vehInsuranceinfo.sxrq = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      ccsnsqsrq(rep) {
        let _this = this;
        _this.vehInsuranceinfo.ccsnsqsrq = rep;
        _this.$forceUpdate();
      },
      /**
       *开始时间
       */
      ccsnszzrq(rep) {
        let _this = this;
        _this.vehInsuranceinfo.ccsnszzrq = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      zzrq(rep) {
        let _this = this;
        _this.vehInsuranceinfo.zzrq = rep;
        _this.$forceUpdate();
      },

      /**
       * 获取号牌种类
       */
      getHpzl() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getHpzl', {}).then((response) => {
          let resp = response.data;
          _this.hpzl = resp.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehInsuranceinfo = {};
        _this.vehInsuranceinfo.ccsnsqk = "1";
        _this.vehInsuranceinfo.jqxtbzt = "1";
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehInsuranceinfo) {
        let _this = this;
        _this.vehInsuranceinfo = $.extend({}, vehInsuranceinfo);
        _this.vehInsuranceinfo.jqxtbzt = "1";
        _this.sxrq = vehInsuranceinfo.sxrq;
        _this.zzrq = vehInsuranceinfo.zzrq;
        _this.vehInsuranceinfo.bbxrsfzmhm = _this.decryptByDES(vehInsuranceinfo.bbxrsfzmhm);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.vehInsuranceinfoDto.page=page;
        _this.vehInsuranceinfoDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehInsuranceinfo/list',_this.vehInsuranceinfoDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehInsuranceinfos = resp.content.list;
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
                || !Validator.length(_this.vehInsuranceinfo.createBy, "创建人", 1, 50)
                || !Validator.length(_this.vehInsuranceinfo.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.vehInsuranceinfo.sysOrgCode, "保险公司代码", 1, 64)
                || !Validator.length(_this.vehInsuranceinfo.hpzl, "号牌种类", 1, 32)
                || !Validator.length(_this.vehInsuranceinfo.hphm, "号牌号码", 1, 32)
                || !Validator.require(_this.vehInsuranceinfo.clsbdh, "车辆识别代号")
                || !Validator.length(_this.vehInsuranceinfo.clsbdh, "车辆识别代号", 1, 32)
                || !Validator.require(_this.vehInsuranceinfo.bxpzh, "保险凭证号码")
                || !Validator.length(_this.vehInsuranceinfo.bxpzh, "保险凭证号码", 1, 32)
                || !Validator.require(_this.vehInsuranceinfo.bxje, "保险金额")
                || !Validator.checkisNaN(_this.vehInsuranceinfo.bxje, "保险金额")
                || !Validator.length(_this.vehInsuranceinfo.bxje, "保险金额", 1, 32)
                || !Validator.require($("#sxrq-input").val(), "保险日期开始时间")
                || !Validator.require($("#zzrq-input").val(), "保险日期结束时间")
                || !Validator.require(_this.vehInsuranceinfo.bbxr, "被保险人名称")
                || !Validator.length(_this.vehInsuranceinfo.bbxr, "被保险人名称", 1, 128)
                || !Validator.require(_this.vehInsuranceinfo.bbxrsfzmhm, "被保险人身份证明号码")
                || !Validator.lengthCorrect(_this.vehInsuranceinfo.bbxrsfzmhm, "被保险人身份证明号码",18)
                || !Validator.length(_this.vehInsuranceinfo.bbxrsfzmhm, "被保险人身份证明号码", 1, 128)
                || !Validator.require(_this.vehInsuranceinfo.jqxtbzt, "交强险投标状态")
                || !Validator.length(_this.vehInsuranceinfo.jqxtbzt, "交强险投标状态", 1, 1)
                || !Validator.length(_this.vehInsuranceinfo.ccsnsqk, "车船税纳税情况", 1, 1)
                || !Validator.length(_this.vehInsuranceinfo.ccsnsje, "车船税纳税金额", 1, 6)
                || !Validator.length(_this.vehInsuranceinfo.wspzhm, "车船税完税/免税凭证号码", 1, 30)
                || !Validator.length(_this.vehInsuranceinfo.nsrsbh, "车船税纳税人识别号", 1, 18)
                || !Validator.length(_this.vehInsuranceinfo.lsh, "流水号", 1, 32)
                || !Validator.length(_this.vehInsuranceinfo.bz, "备注", 1, 32)
                || !Validator.length(_this.vehInsuranceinfo.zt, "状态", 1, 1)
                || !Validator.length(_this.vehInsuranceinfo.shr, "审核人", 1, 128)
                || !Validator.length(_this.vehInsuranceinfo.shsm, "审核说明", 1, 4000)
                || !Validator.length(_this.vehInsuranceinfo.shyy, "审核原因", 1, 40)
        ) {
          return;
        }
        _this.vehInsuranceinfo.ccsnsqsrq = $("#ccsnsqsrq-input").val();
        _this.vehInsuranceinfo.ccsnszzrq = $("#ccsnszzrq-input").val();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehInsuranceinfo/save', _this.vehInsuranceinfo).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehInsuranceinfo/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      }
    }
  }
</script>