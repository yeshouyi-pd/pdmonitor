<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">黑名单管理</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table style="font-size: 1.1em;width: 90%" class="text-right">
                  <tbody>
                  <tr>
                    <td style="width:6%">
                      姓名：
                    </td>
                    <td style="width: 10%">
                      <input type="text" id="form-field-1" class="input-sm" v-model="webchatBlackDto.xm"/>
                    </td>
                    <td style="width: 8%">
                      身份证明号码：
                    </td>
                    <td style="width: 10%">
                      <to-upper class="input-sm" type="toUpper" idValue="form-field-2"
                                v-model="webchatBlackDto.sfzmhm"/>
                    </td>

                    <td  style="width: 6%">
                      黑名单时间：
                    </td>
                    <td style="width: 20%">
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
            <th>姓名</th>
            <th>身份证明号码</th>
            <th>原因</th>
            <th>黑名单开始时间</th>
            <th>黑名单结束时间</th>
            <th>创建人</th>
            <th>创建日期</th>
            <th>处理部门</th>
            <th>处理人</th>
            <th>处理日期</th>
            <th>状态</th>
            <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="webchatBlack in webchatBlacks">
              <td>{{webchatBlack.xm}}</td>
        <td>{{ decryptByDES(webchatBlack.sfzmhm) }}</td>
              <td>{{blackyy|optionMapKV(webchatBlack.yy)}}</td>
              <td>{{webchatBlack.kssj}}</td>
              <td>{{webchatBlack.jssj}}</td>
              <td>{{usersMap|optionMapKV(webchatBlack.createBy)}}</td>
              <td>{{webchatBlack.createTime}}</td>
              <td>{{maps|optionMapKV(webchatBlack.sysOrgCode)}}</td>
              <td>{{webchatBlack.updateBy}}</td>
              <td>{{webchatBlack.updateTime}}</td>
              <td><span v-if="webchatBlack.zt =='1'">黑名单中</span><span v-else-if="webchatBlack.zt =='2'">已解除</span><span v-else-if="webchatBlack.zt =='3'">已到期</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(webchatBlack)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(webchatBlack.id)" class="btn btn-xs btn-danger">
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
      <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h5 class="modal-title">黑名单管理</h5>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">黑名单详细信息</h6>
              <div class="profile-user-info profile-user-info-striped">

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">姓名</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="xm">{{webchatBlack.xm}} </span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">身份证明号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sfzmhm">{{ decryptByDES(webchatBlack.sfzmhm) }}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">原因</div>
                  <div class="profile-info-value">
                    <span class="editable" id="yy">{{blackyy|optionMapKV(webchatBlack.yy)}}</span>
                  </div>
                  <div class="profile-info-name">原因说明</div>
                  <div class="profile-info-value">
                    <span class="editable" id="yysm">{{webchatBlack.yysm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">黑名单开始时间</div>
                  <div class="profile-info-value">
                    <span class="editable" id="kssj">{{webchatBlack.kssj}}</span>
                  </div>
                  <div class="profile-info-name">黑名单结束时间</div>
                  <div class="profile-info-value">
                    <span class="editable" id="jssj">{{webchatBlack.jssj}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">创建时间</div>
                  <div class="profile-info-value">
                    <span class="editable" id="createTime">{{webchatBlack.createTime}}</span>
                  </div>
                  <div class="profile-info-name">创建人</div>
                  <div class="profile-info-value">
                    <span class="editable" id="createBy">{{usersMap|optionMapKV(webchatBlack.createBy)}}</span>
                  </div>
                </div>


              </div>

              <h6 class="header blue lighter less-margin">处理信息</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">黑名单处理结果</label>
                <div class="col-sm-10">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="1" v-model="webchatBlack.zt"/>
                      <span class="lbl">黑名单中</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="webchatBlack.zt"/>
                      <span class="lbl">解除黑名单</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">处理备注</label>
                <div class="col-sm-8">
                  <input v-model="webchatBlack.bz" class="form-control">
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
  export default {
    components: {Pagination, Times, ToUpper},
    name: "wxbase-webchatBlack",
    data: function () {
      return {
        webchatBlack: {},
        webchatBlacks: [],
        blackyy: [],
        maps: [],
        usersMap: [],
        webchatBlackDto: {},
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getBlackYy();
      _this.maps = Tool.getDeptUser() ;
      _this.usersMap = Tool.getUserCode();
    },
    methods: {
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
      startTime(rep) {
        let _this = this;
        _this.webchatBlackDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.webchatBlackDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 获取黑名单原因
       */
      getBlackYy(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getBlackyy', {
        }).then((response)=>{
          let resp = response.data;
          _this.blackyy = resp.content;
        })
      },

      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.webchatBlack = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(webchatBlack) {
        let _this = this;
        _this.webchatBlack = $.extend({}, webchatBlack);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.webchatBlackDto.page=page;
        _this.webchatBlackDto.size=_this.$refs.pagination.size;
        _this.webchatBlackDto.zt="1";
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatBlack/list', _this.webchatBlackDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.webchatBlacks = resp.content.list;
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
                || !Validator.length(_this.webchatBlack.createBy, "创建人", 1, 50)
                || !Validator.length(_this.webchatBlack.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.webchatBlack.sysOrgCode, "所属部门", 1, 64)
                || !Validator.length(_this.webchatBlack.xm, "姓名", 1, 32)
                || !Validator.length(_this.webchatBlack.sfzmhm, "身份证明号码", 1, 32)
                || !Validator.length(_this.webchatBlack.yy, "原因", 1, 32)
                || !Validator.length(_this.webchatBlack.yysm, "原因说明", 1, 400)
                || !Validator.length(_this.webchatBlack.zt, "状态", 1, 2)
                || !Validator.length(_this.webchatBlack.openid, "微信用户id", 1, 32)
                || !Validator.length(_this.webchatBlack.bz, "备注", 1, 400)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatBlack/save', _this.webchatBlack).then((response)=>{
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
        Confirm.show("删除黑名单管理后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatBlack/delete/' + id).then((response)=>{
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