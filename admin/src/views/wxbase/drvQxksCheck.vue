<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">驾驶人取消考试审核</h4>
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
                      <input type="text" id="form-field-2"  class="input-sm"  v-model="drvQxksDto.sfzmhm"/>
                    </td>
                    <td style="width:6%" >
                      考试科目：
                    </td>
                    <td style="width: 8%">
                      <select v-model="drvQxksDto.qxkm" class="select2"  style="width:78%">
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in allkskm" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      姓名：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="input-sm"  v-model="drvQxksDto.xm"/>
                    </td>
                    <td  style="width: 6%">
                      申请日期：
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

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>姓名</th>
            <th>证件类型</th>
            <th>身份证明号码</th>
            <th>手机号码</th>
            <th>取消考试科目</th>
            <th>预约考试时间</th>
            <th>申请日期</th>
            <th>审核人</th>
            <th>审核时间</th>
            <th>流水号</th>
            <th>审核状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="drvQxks in drvQxkss">

              <td>{{drvQxks.xm}}</td>
              <td>{{zjlx|optionMapKV(drvQxks.zjlx)}}</td>
              <td>{{decryptByDES(drvQxks.sfzmhm)}}</td>
              <td>{{decryptByDES(drvQxks.sjhm)}}</td>
              <td>{{allkskm|optionMapKV(drvQxks.qxkm)}}</td>
              <td>{{drvQxks.yykssj}}</td>
              <td>{{drvQxks.createTime}}</td>
              <td>{{drvQxks.shr}}</td>
              <td>{{drvQxks.shsj}}</td>
              <td>{{drvQxks.lsh}}</td>
        <td><span v-if="drvQxks.zt =='1'">已申请</span><span v-if="drvQxks.zt =='2'">审核通过</span><span v-else-if="drvQxks.zt =='3'">审核不通过</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="check(drvQxks)" class="btn btn-xs btn-info" title="审核">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
<!--            <button v-on:click="del(drvQxks.id)" class="btn btn-xs btn-danger" title="删除">-->
<!--              <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--            </button>-->

          </div>
        </td>
      </tr>
      </tbody>
    </table>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">驾驶人取消考试</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">申请人信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name">姓名</div>
                  <div class="profile-info-value">
                    <span class="editable" id="xm">{{drvQxks.xm}}</span>
                  </div>
                  <div class="profile-info-name">证件类型</div>
                  <div class="profile-info-value">
                    <span class="editable" id="zjlx">{{zjlx|optionMapKV(drvQxks.zjlx)}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name">身份证明号码</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sfzmhm">{{decryptByDES(drvQxks.sfzmhm)}}</span>
                  </div>
                  <div class="profile-info-name">手机号码</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sjhm">{{decryptByDES(drvQxks.sjhm)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">取消考试科目</div>
                  <div class="profile-info-value">
                    <span class="editable" id="qxkm">{{allkskm|optionMapKV(drvQxks.qxkm)}}</span>
                  </div>
                  <div class="profile-info-name">预约考试时间</div>
                  <div class="profile-info-value">
                    <span class="editable" id="yykssj">{{drvQxks.yykssj}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%;height: 30px">申请日期</div>
                  <div class="profile-info-value"  style="width: 40%">
                    <span class="editable" id="createTime">{{drvQxks.createTime}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">申请说明</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sqsm">{{drvQxks.sqsm}}</span>
                  </div>
                </div>
              </div>

              <h6 class="header blue lighter less-margin">审核信息</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核结果</label>
                <div class="col-sm-10">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="drvQxks.zt"/>
                      <span class="lbl">申请通过</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="3" v-model="drvQxks.zt"/>
                      <span class="lbl">申请不通过</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group" v-if="drvQxks.zt=='3'">
                <label class="col-sm-2 control-label">审核说明</label>
                <div class="col-sm-8">
                  <input v-model="drvQxks.shsm" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-8">
                  <input v-model="drvQxks.bz" class="form-control">
                </div>
              </div>


            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">审核</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div align="right"><pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination></div>

  </div>
</template>

<script>
import Pagination from "../../components/pagination";
import Times from "../../components/time";
export default {
  components: {Pagination,Times},
    name: "wxbase-drvQxks",
    data: function() {
      return {
        drvQxks: {},
        drvQxksDto: {},
        drvQxkss: [],
        allkskm: [],
        maps: [],
        usersMap:[],
        zjlx: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.list(1);
      _this.getKskm();
      _this.maps = Tool.getDeptUser() ;
      _this.usersMap = Tool.getUserCode();
      _this.getZjlx();
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
        _this.drvQxksDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.drvQxksDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 点击【审核】
       */
      check(drvQxks) {
        let _this = this;
        _this.drvQxks = $.extend({}, drvQxks);
        _this.drvQxks.zt = "2";
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },
      /**
       * 获取考试科目
       */
      getKskm(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getKskm', {
        }).then((response)=>{
          let resp = response.data;
          _this.allkskm = resp.content;
        })
      },
      /**
       * 获取证件类型`
       */
      getZjlx(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getSfzjlx', {
        }).then((response)=>{
          let resp = response.data;
          _this.zjlx = resp.content;
        })
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.drvQxksDto.page=page;
        _this.drvQxksDto.zt="1";
        _this.drvQxksDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/drvQxks/list',_this.drvQxksDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.drvQxkss = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;
        if(_this.drvQxks.zt=='3'){
          if(!Validator.require(_this.drvQxks.shsm, "审核说明")){
            return;
          }
        }
        // 保存校验
        if (1 != 1
                || !Validator.length(_this.drvQxks.createBy, "创建人", 1, 50)
                || !Validator.length(_this.drvQxks.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.drvQxks.sysOrgCode, "所属部门", 1, 64)
                || !Validator.length(_this.drvQxks.xm, "姓名", 1, 128)
                || !Validator.length(_this.drvQxks.zjlx, "证件类型", 1, 32)
                || !Validator.length(_this.drvQxks.sfzmhm, "身份证明号码", 1, 32)
                || !Validator.length(_this.drvQxks.sjhm, "手机号码", 1, 20)
                || !Validator.length(_this.drvQxks.qxkm, "取消考试科目", 1, 32)
                || !Validator.length(_this.drvQxks.sqsm, "申请说明", 1, 2000)
                || !Validator.length(_this.drvQxks.shr, "审核人", 1, 32)
                || !Validator.length(_this.drvQxks.shsm, "审核说明", 1, 200)
                || !Validator.length(_this.drvQxks.bz, "备注", 1, 1000)
                || !Validator.length(_this.drvQxks.openid, "微信用户id", 1, 32)
                || !Validator.length(_this.drvQxks.lsh, "流水号", 1, 32)
                || !Validator.length(_this.drvQxks.zt, "状态", 1, 2)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/drvQxks/save', _this.drvQxks).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/drvQxks/delete/' + id).then((response)=>{
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