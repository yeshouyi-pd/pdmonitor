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
                    <td style="width: 8%">
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
                    <td style="width: 8%" >
                      领取人身份证明号码：
                    </td>
                    <td style="width: 10%">
                      <input  class="form-control"  type="text" id="form-field-3"  v-model="vehLicelostDto.sfzmhm"/>
                    </td>
                    <td style="width: 6%" >
                      领取人：
                    </td>
                    <td style="width: 8%">
                      <input class="input-sm" type="text" id="form-field-2"  v-model="vehLicelostDto.lqrxm"/>
                    </td>

                  </tr>
                  <tr style="height: 10px"></tr>
                  <tr>
                    <td style="width: 6%" >
                      领取人电话：
                    </td>
                    <td style="width: 8%">
                      <input class="input-sm" type="text" id="form-field-4"  v-model="vehLicelostDto.lqrdh"/>
                    </td>
                    <td style="width: 6%">
                      状态：
                    </td>
                    <td style="width: 8%" >
                      <select v-model="vehLicelostDto.zt" class="input-sm" id="form-field-select-1"  style="width:78%">
                        <option value="" selected>请选择</option>
                        <option value="1">-----&nbsp;&nbsp;&nbsp;&nbsp;已&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;&nbsp;入&nbsp;&nbsp;&nbsp;&nbsp;-----</option>
                        <option value="2">-----&nbsp;&nbsp;&nbsp;&nbsp;已&nbsp;&nbsp;&nbsp;&nbsp;申&nbsp;&nbsp;&nbsp;&nbsp;领&nbsp;&nbsp;&nbsp;&nbsp;-----</option>
                      </select>
                    </td>
                    <td  style="width: 6%">
                      领取日期：
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

    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>车牌类型</th>
            <th>号牌号码</th>
            <th>领取部门</th>
            <th>领取人姓名</th>
            <th>领取人身份证明号码</th>
            <th>领取人电话</th>
            <th>领取日期</th>
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
              <td>{{vehLicelost.lqrxm}}</td>
              <td>{{vehLicelost.sfzmhm}}</td>
              <td>{{vehLicelost.lqrdh}}</td>
              <td>{{vehLicelost.lqrq}}</td>
              <td>{{usersMap|optionMapKV(vehLicelost.createBy)}}</td>
              <td>{{vehLicelost.createTime}}</td>
              <td><span v-if="vehLicelost.zt =='1'">已录入</span><span v-else-if="vehLicelost.zt =='2'">已申领</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehLicelost)" class="btn btn-xs btn-info" title="详情">
              <i class="ace-icon fa fa-list bigger-120"></i>
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
            <h4 class="modal-title">车牌遗失详细信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">车牌类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sbr">{{allcplx|optionMapKV(vehLicelost.hpzl)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">号牌号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="hphm">{{vehLicelost.hphm}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name">录入人</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sbrsjh">{{usersMap|optionMapKV(vehLicelost.createBy)}}</span>
                  </div>
                  <div class="profile-info-name">录入日期</div>
                  <div class="profile-info-value">
                    <span class="editable" id="createTime">{{vehLicelost.createTime}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">领取地址</div>
                  <div class="profile-info-value">
                    <span class="editable" id="gps">{{vehLicelost.lqdz}}</span>
                  </div>
                  <div class="profile-info-name">领取电话</div>
                  <div class="profile-info-value">
                    <span class="editable" id="zsdd">{{maps|optionMapKV(vehLicelost.sysOrgCode)}}--{{vehLicelost.lqdh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">领取人姓名</div>
                  <div class="profile-info-value">
                    <span class="editable" id="lqrxm">{{vehLicelost.lqrxm}}</span>
                  </div>
                  <div class="profile-info-name">身份证明号码</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sfzmhm">{{vehLicelost.sfzmhm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">领取人电话</div>
                  <div class="profile-info-value">
                    <span class="editable" id="lqrdh">{{vehLicelost.lqrdh}}</span>
                  </div>
                  <div class="profile-info-name">备注</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sjhm">{{vehLicelost.bz}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">领取日期</div>
                  <div class="profile-info-value">
                    <span class="editable" id="lqrq">{{vehLicelost.lqrq}}</span>
                  </div>
                  <div class="profile-info-name">状态</div>
                  <div class="profile-info-value">
                    <span class="editable" id="zt"><span v-if="vehLicelost.zt =='1'">已录入</span><span v-else-if="vehLicelost.zt =='2'">已申领</span></span>
                  </div>
                </div>


                <div class="profile-info-row">
                  <div class="profile-info-name">流水号</div>
                  <div class="profile-info-value">
                    <span class="editable" id="zsddxx">{{vehLicelost.lqrdh}}</span>
                  </div>
                  <div class="profile-info-name">领取说明</div>
                  <div class="profile-info-value">
                    <span class="editable" id="lqsm">{{vehLicelost.lqsm}}</span>
                  </div>
                </div>

              </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
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
      //DES 解密
      decryptByDES(ciphertext) {
        let key = "11111111";
        let keyHex = CryptoJS.enc.Utf8.parse(key);
        let decrypted = CryptoJS.DES.decrypt({
          ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
        }, keyHex, {
          mode: CryptoJS.mode.ECB,
          padding: CryptoJS.pad.Pkcs7
        });
        return decrypted.toString(CryptoJS.enc.Utf8);
      },
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
        Loading.show();
        _this.vehLicelostDto.page=page;
        _this.vehLicelostDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLicelost/list',  _this.vehLicelostDto).then((response)=>{
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
        ) {
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
      }
    }
  }
</script>