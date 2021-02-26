<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">奖励金额查询</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;width: 80%" class="text-right  " >
                  <tbody  >
                  <tr  >
                    <td style="width: 6%" >
                      车牌类型：
                    </td>
                    <td style="width: 8%">
                      <select v-model="webchatRewardDto.cplx" class="form-control">
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in allcplx" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      车牌号码：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-3"  v-model="webchatRewardDto.cphm"/>
                    </td>
                    <td style="width: 8%" >
                      身份证明号码：
                    </td>
                    <td style="width: 10%">
                      <input class="form-control" type="text" id="form-field-2"  v-model="webchatRewardDto.sfzmhm"/>
                    </td>
                    <td style="width: 8%" >
                      手机号：
                    </td>
                    <td style="width: 10%">
                      <input class="form-control" type="text" id="form-field-5"  v-model="webchatRewardDto.sjh"/>
                    </td>
<!--                    <td style="width: 8%" >-->
<!--                      状态：-->
<!--                    </td>-->
<!--                    <td style="width: 10%">-->
<!--                      <select v-model="webchatRewardDto.zt"  class="form-control" style="width: 65%" id="form-field-select-3">-->
<!--                        <option value="" selected>请选择</option>-->
<!--                        <option value="1">已上报</option>-->
<!--                        <option value="2">审核通过</option>-->
<!--                        <option value="3">审核不通过</option>-->
<!--                      </select>-->
<!--                    </td>-->

                  </tr>
                  <tr style="height: 10px"></tr>
                  <tr>
<!--                    <td style="width: 6%" >-->
<!--                      违法类别：-->
<!--                    </td>-->
<!--                    <td style="width: 8%">-->
<!--                      <select v-model="webchatRewardDto.wflb" class="form-control" >-->
<!--                        <option value="" selected>请选择</option>-->
<!--                        <option v-for="(value, key) in wflb" v-bind:value="key">{{value}}</option>-->
<!--                      </select>-->
<!--                    </td>-->
<!--                    <td style="width: 6%">-->
<!--                      奖励发放：-->
<!--                    </td>-->
<!--                    <td style="width: 8%" >-->
<!--                      <select v-model="webchatRewardDto.jlff"  class="form-control" style="width: 95%">-->
<!--                        <option value="" selected>请选择</option>-->
<!--                        <option value="0" >未发放</option>-->
<!--                        <option value="1">已发放</option>-->
<!--                      </select>-->
<!--                    </td>-->
                    <td  style="width: 6%">
                      举报日期：
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
        <th>举报人</th>
        <th>举报日期</th>
        <th>车牌类型</th>
        <th>车牌号码</th>
        <th>身份证明号码</th>
        <th>手机号</th>
        <th>奖励发放状态</th>
        <th>所属部门</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="webchatReward in webchatRewards">
        <td>{{webchatReward.createBy}}</td>
        <td>{{webchatReward.createTime}}</td>
        <td>{{allcplx|optionMapKV(webchatReward.cplx)}}</td>
        <td>{{webchatReward.cphm}}</td>
        <td>{{decryptByDES(webchatReward.sfzmhm)}}</td>
        <td>{{decryptByDES(webchatReward.sjh)}}</td>
        <td><span v-if="webchatReward.ffzt =='0'">未发放</span><span v-else-if="webchatReward.ffzt =='1'">已发放</span></td>
        <td>{{maps|optionMapKV(webchatReward.sysOrgCode)}}</td>
        <td><span v-if="webchatReward.zt =='4'">审核通过</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(webchatReward)" class="btn btn-xs btn-info" title="详情">
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
      <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">奖励金额查询</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">举报人基本信息</h6>
              <div class="profile-info-row">
                <div class="profile-info-name"  style="width: 10%">举报人</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{webchatReward.createBy}}</span>
                </div>
                <div class="profile-info-name"  style="width: 10%">举报日期</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{webchatReward.createTime}}</span>
                </div>
              </div>

              <div class="profile-info-row">
                <div class="profile-info-name"  style="width: 10%">审核部门</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{maps|optionMapKV(webchatReward.sysOrgCode)}}</span>
                </div>
                <div class="profile-info-name"  style="width: 10%">违法行为详细</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{wfxw|optionMapKV(webchatReward.wfxwxx)}}</span>
                </div>
              </div>

              <div class="profile-info-row">
                <div class="profile-info-name"  style="width: 10%">车牌类型</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{allcplx|optionMapKV(webchatReward.cplx)}}</span>
                </div>
                <div class="profile-info-name"  style="width: 10%">车牌号码</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{webchatReward.cphm}}</span>
                </div>
              </div>

              <div class="profile-info-row">
                <div class="profile-info-name"  style="width: 10%">身份证明号码</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{webchatReward.sfzmhm}}</span>
                </div>
                <div class="profile-info-name"  style="width: 10%">手机号</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{webchatReward.sjh}}</span>
                </div>
              </div>

              <div class="profile-info-row">
                <div class="profile-info-name"  style="width: 10%">用户openid</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{webchatReward.openid}}</span>
                </div>
                <div class="profile-info-name"  style="width: 10%">奖励金额</div>
                <div class="profile-info-value" style="width: 40%">
                  <span class="editable">{{jljr}}</span>
                </div>
              </div>


              <!--              <h6 class="header blue lighter less-margin">审核信息</h6>-->
              <!--              <div class="form-group">-->
              <!--                <label class="col-sm-2 control-label">审核结果*</label>-->
              <!--                <div class="col-sm-10">-->
              <!--                  <div class="radio">-->
              <!--                    <label>-->
              <!--                      <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="rewardCheck.zt"/>-->
              <!--                      <span class="lbl">申请通过</span>-->
              <!--                    </label>-->
              <!--                    <label>-->
              <!--                      <input name="form-field-radio" type="radio" class="ace"  value="5" v-model="rewardCheck.zt"/>-->
              <!--                      <span class="lbl">申请不通过</span>-->
              <!--                    </label>-->
              <!--                  </div>-->
              <!--                </div>-->
              <!--              </div>-->

              <!--              <div class="form-group">-->
              <!--                <label class="col-sm-2 control-label">审核原因（非必填项，审核不通过需要填写）</label>-->
              <!--                <div class="col-sm-8">-->
              <!--                  <select v-model="rewardCheck.shyy" class="form-control" >-->
              <!--                    <option v-for="(value, key) in shyy" v-bind:value="key">{{value}}</option>-->
              <!--                  </select>-->
              <!--                </div>-->
              <!--              </div>-->
              <!--              <div class="form-group">-->
              <!--                <label class="col-sm-2 control-label">审核说明*</label>-->
              <!--                <div class="col-sm-8">-->
              <!--                  <input v-model="rewardCheck.shsm" class="form-control">-->
              <!--                </div>-->
              <!--              </div>-->
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
  export default {
    components: {Pagination,Times},
    name: "wxbase-rewardQuery",
    data: function() {
      return {
      webchatRewardDto:{},
      webchatReward: {},
      webchatRewards: [],
      rewardQuery: {},
      rewardQuerys: [],
      allcplx: [],
      wfxw: [],
      maps: [],
      jljr: '',
      usersMap:[],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getWfxw();
      _this.maps = Tool.getDeptUser() ;
      _this.usersMap = Tool.getUserCode();
      _this.getCplx();
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-rewardQuery-sidebar");

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
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.rewardQuery = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(webchatReward) {
        let _this = this;
        _this.webchatReward = $.extend({}, webchatReward);
        _this.webchatReward.sfzmhm = _this.decryptByDES(webchatReward.sfzmhm);
        _this.webchatReward.sjh = _this.decryptByDES(webchatReward.sjh);


        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatIllegal/getJljeByCode' ,_this.webchatReward).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.jljr=resp.content.jljr

        })
        $("#form-modal").modal("show");
      },
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.webchatRewardDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.webchatRewardDto.etime = rep;
        _this.$forceUpdate();
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.webchatRewardDto.page=page;
        _this.webchatRewardDto.size=_this.$refs.pagination.size;
        _this.webchatRewardDto.zt="4";
        _this.webchatRewardDto.ffzt="1";
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatReward/list2',_this.webchatRewardDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.webchatRewards = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 获取违法行为
       */
      getWfxw(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWfxw', {
        }).then((response)=>{
          let resp = response.data;
          _this.wfxw = resp.content;
        })
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
       * 点击【保存】
       */
      save() {
        let _this = this;

        // 保存校验
        if (1 != 1
                || !Validator.length(_this.rewardQuery.createBy, "创建人", 1, 30)
                || !Validator.length(_this.rewardQuery.updateBy, "更新人", 1, 30)
                || !Validator.length(_this.rewardQuery.sysOrgCode, "所属部门", 1, 50)
                || !Validator.length(_this.rewardQuery.wfxwxx, "违法行为详细", 1, 200)
                || !Validator.length(_this.rewardQuery.sfzmhm, "身份证明号码", 1, 60)
                || !Validator.length(_this.rewardQuery.sjh, "手机号", 1, 60)
                || !Validator.length(_this.rewardQuery.shr, "审核人", 1, 30)
                || !Validator.length(_this.rewardQuery.shyy, "审核原因", 1, 30)
                || !Validator.length(_this.rewardQuery.shsm, "审核说明", 1, 500)
                || !Validator.length(_this.rewardQuery.zt, "状态", 1, 1)
                || !Validator.length(_this.rewardQuery.openid, "用户openid", 1, 40)
                || !Validator.length(_this.rewardQuery.lsh, "流水号", 1, 40)
                || !Validator.length(_this.rewardQuery.ffzt, "奖励发放状态", 1, 1)
                || !Validator.length(_this.rewardQuery.cplx, "车牌类型", 1, 20)
                || !Validator.length(_this.rewardQuery.cphm, "车牌号码", 1, 20)
                || !Validator.length(_this.rewardQuery.ffr, "发放人", 1, 20)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/rewardQuery/save', _this.rewardQuery).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/rewardQuery/delete/' + id).then((response)=>{
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