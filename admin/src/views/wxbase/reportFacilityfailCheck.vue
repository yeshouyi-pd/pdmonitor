<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">交安故障上报处理</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;width: 100%" class="text-right">
                  <tbody  >
                  <tr  >
                    <td style="width:8%" >
                      身份证明号码：
                    </td>
                    <td style="width: 8%">
                      <to-upper type="toUpper" idValue="form-field-2" class="input-sm"
                                v-model="reportFacilityfailDto.sfzmhm"/>
                    </td>
                    <!--<td style="width:6%" >
                      上报人：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="input-sm"  v-model="reportFacilityfailDto.sbr"/>
                    </td>-->
                    <td style="width:6%" >
                      故障类型：
                    </td>
                    <td style="width: 20%">
                      <select v-model="reportFacilityfailDto.gzlx" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in gzlx" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>

                    <td  style="width: 6%">
                      上报日期：
                    </td>
                    <td style="width: 15%" >
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


    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>故障类型</th>
          <th>故障地点</th>
          <th>上报人</th>
          <th>手机号</th>
          <th>身份证号码</th>
          <th>上报日期</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="reportFacilityfail in reportFacilityfails">
          <td>{{gzlx|optionMapKV(reportFacilityfail.gzlx)}}</td>
          <td>{{reportFacilityfail.gzdd}}</td>
          <td>{{reportFacilityfail.sbr}}</td>
          <td>{{decryptByDES(reportFacilityfail.sbrsjh)}}</td>
          <td>{{decryptByDES(reportFacilityfail.sfzmhm)}}</td>
          <td>{{reportFacilityfail.createTime}}</td>
          <td><span v-if="reportFacilityfail.zt =='1'">已上报</span><span v-else-if="reportFacilityfail.zt =='2'">已处理</span><span v-else-if="reportFacilityfail.zt =='3'">虚假上报</span></td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(reportFacilityfail)" class="btn btn-xs btn-info2" title="处理">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
              </button>
<!--              <button v-on:click="del(reportFacilityfail.id)" class="btn btn-xs btn-danger">-->
<!--                <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--              </button>-->
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">交安故障上报信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">

                  <div class="profile-info-name"  style="width: 10%">故障地点</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="gzdd">{{reportFacilityfail.gzdd}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">故障类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="gzlx">{{reportFacilityfail.gzlx}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">故障坐标</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="gzgps">{{reportFacilityfail.gzgps}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">详细地址</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="gzxxdz">{{reportFacilityfail.gzxxdz}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">故障描述</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="gzms">{{reportFacilityfail.gzms}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">上报人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sbr">{{reportFacilityfail.sbr}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">上报人手机号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sbrsjh">{{decryptByDES(reportFacilityfail.sbrsjh)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">身份证号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sfzmhm">{{decryptByDES(reportFacilityfail.sfzmhm)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">上报日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="createTime">{{reportFacilityfail.createTime}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">流水号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="lsh">{{reportFacilityfail.lsh}}</span>
                  </div>
                </div>

              </div>

              <h6 class="header blue lighter less-margin">上报图片</h6>
<!--              <div class="profile-info-row">-->
<!--                <div class="profile-info-value" v-for="zp in zplist">-->
<!--                  <img  v-bind:src="zp.zplj" style="height: 100px" class="img-responsive">-->
<!--                </div>-->
<!--              </div>-->
              <show-pic :pics="zplist"></show-pic>

              <h6 class="header blue lighter less-margin">处理信息</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">处理结果</label>
                <div class="col-sm-10">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="reportFacilityfail.zt" checked/>
                      <span class="lbl">已处理</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="3" v-model="reportFacilityfail.zt"/>
                      <span class="lbl">虚假上报</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group" v-if="reportFacilityfail.zt=='3'">
                <label class="col-sm-2 control-label">处理意见说明</label>
                <div class="col-sm-8">
                  <input v-model="reportFacilityfail.clyj" class="form-control">
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
import ToUpper from "../../components/upperLowerTransform";
import ShowPic from "../../components/showpic";
export default {
  components: {Pagination, Times, ToUpper, ShowPic},
  name: "wxbase-reportFacilityfail",
  data: function () {
    return {
      reportFacilityfail: {},
      reportFacilityfailDto: {},
      reportFacilityfails: [],
      usersMap: [],
      gzlx: [],
      zplist: []
    }
  },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getFacility();
      _this.usersMap = Tool.getUserCode();

    },
    methods: {
      //DES 解密
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
        _this.reportFacilityfailDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.reportFacilityfailDto.etime = rep;
        _this.$forceUpdate();
      },

      /**
       * 获取故障类型
       */
      getFacility(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getFacility', {
        }).then((response)=>{
          let resp = response.data;
          _this.gzlx = resp.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.reportFacilityfail = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(reportFacilityfail) {
        let _this = this;
        _this.reportFacilityfail = $.extend({}, reportFacilityfail);
        _this.reportFacilityfail.zt = "2";
        _this.$forceUpdate();
        _this.getFileInfo(reportFacilityfail.lsh);
        $("#form-modal").modal("show");
      },

      getFileInfo(lsh){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatFileinfo/getFileinfoByLsh/'+ lsh).then((response)=>{
          let resp = response.data;
          _this.zplist = resp.content;
        })
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.reportFacilityfailDto.page=page;
        _this.reportFacilityfailDto.size=_this.$refs.pagination.size;
        _this.reportFacilityfailDto.zt="1";
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/reportFacilityfail/list',_this.reportFacilityfailDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.reportFacilityfails = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;
        if(_this.reportFacilityfail.zt){
          if(!Validator.require(_this.reportFacilityfail.clyj, "处理意见说明")){
            return ;
          }
        }
        // 保存校验
        if (1 != 1
                || !Validator.length(_this.reportFacilityfail.createBy, "创建人", 1, 50)
                || !Validator.length(_this.reportFacilityfail.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.reportFacilityfail.sysOrgCode, "所属部门", 1, 64)
                || !Validator.length(_this.reportFacilityfail.gzdd, "故障地点", 1, 128)
                || !Validator.length(_this.reportFacilityfail.gzlx, "故障类型", 1, 32)
                || !Validator.length(_this.reportFacilityfail.gzgps, "故障坐标", 1, 128)
                || !Validator.length(_this.reportFacilityfail.gzxxdz, "详细地址", 1, 2000)
                || !Validator.length(_this.reportFacilityfail.gzms, "故障描述", 1, 2000)
                || !Validator.length(_this.reportFacilityfail.sbr, "上报人", 1, 128)
                || !Validator.length(_this.reportFacilityfail.sbrsjh, "手机号", 1, 20)
                || !Validator.length(_this.reportFacilityfail.sfzmhm, "身份证号码", 1, 32)
                || !Validator.length(_this.reportFacilityfail.shr, "审核人", 1, 32)
                || !Validator.length(_this.reportFacilityfail.zt, "状态", 1, 2)
                || !Validator.length(_this.reportFacilityfail.bz, "备注", 1, 4000)
                || !Validator.length(_this.reportFacilityfail.clyj, "处理意见", 1, 4000)
                || !Validator.length(_this.reportFacilityfail.lsh, "流水号", 1, 32)
                || !Validator.length(_this.reportFacilityfail.openid, "微信用户id", 1, 32)
        ) {
          return;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/reportFacilityfail/save', _this.reportFacilityfail).then((response)=>{
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
        Confirm.show("删除交安故障上报后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/reportFacilityfail/delete/' + id).then((response)=>{
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