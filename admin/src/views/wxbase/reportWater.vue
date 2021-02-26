<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">渍水上报查询</h4>
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
                    <td style="width:10%" >
                      上报人身份证明号码：
                    </td>
                    <td style="width: 8%">
                      <to-upper type="toUpper" idValue="form-field-2" class="input-sm" v-model="reportWaterDto.sfzmhm"/>
                    </td>

                    <td style="width:6%" >
                      上报人：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="input-sm"  v-model="reportWaterDto.sbr"/>
                    </td>
                    <td style="width: 6%" >
                      状态：
                    </td>
                    <td style="width: 8%">
                      <select v-model="reportWaterDto.zt" class="form-control" id="form-field-select-1">
                        <option value="" selected>请选择</option>
                        <option value="1">已上报</option>
                        <option value="2">已处理</option>
                        <option value="3">虚假上报</option>
                      </select>
                    </td>
                    <td  style="width: 6%">
                      上报时间：
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
            <th>上报人</th>
            <th>上报人手机号</th>
            <th>上报人身份证号码</th>
            <th>上报时间</th>
            <th>渍水地点</th>
            <th>处理部门</th>
            <th>处理人</th>
            <th>处理时间</th>
            <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="reportWater in reportWaters">
              <td>{{reportWater.sbr}}</td>
              <td>{{decryptByDES(reportWater.sbrsjh)}}</td>
              <td>{{decryptByDES(reportWater.sfzmhm)}}</td>
              <td>{{reportWater.createTime}}</td>
              <td>{{reportWater.zsdd}}</td>
              <td>{{maps|optionMapKV(reportWater.sysOrgCode)}}</td>
              <td>{{usersMap|optionMapKV(reportWater.shr)}}</td>
              <td>{{reportWater.shsj}}</td>
              <td><span v-if="reportWater.zt =='2'">已处理</span><span v-else-if="reportWater.zt =='3'">虚假上报</span><span v-else-if="reportWater.zt =='1'">已上报</span></td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(reportWater)" class="btn btn-xs btn-info" title="详情">
              <i class="ace-icon fa fa-list bigger-120"></i>
            </button>
            <!--<button v-on:click="del(reportWater.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>-->
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
            <h4 class="modal-title">渍水上报</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">渍水上报信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name">上报人</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sbr">{{reportWater.sbr}}</span>
                  </div>
                  <div class="profile-info-name">身份证明号码</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sfzmhm">{{reportWater.sfzmhm}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name">上报人手机号</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sbrsjh">{{reportWater.sbrsjh}}</span>
                  </div>
                  <div class="profile-info-name">上报日期</div>
                  <div class="profile-info-value">
                    <span class="editable" id="createTime">{{reportWater.createTime}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">渍水坐标</div>
                  <div class="profile-info-value">
                    <span class="editable" id="gps">{{reportWater.gps}}</span>
                  </div>
                  <div class="profile-info-name">渍水地点</div>
                  <div class="profile-info-value">
                    <span class="editable" id="zsdd">{{reportWater.zsdd}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">渍水详细地址</div>
                  <div class="profile-info-value">
                    <span class="editable" id="zsddxx">{{reportWater.zsddxx}}</span>
                  </div>
                  <div class="profile-info-name">上报说明</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sbsm">{{reportWater.sbsm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">处理人</div>
                  <div class="profile-info-value">
                    <span class="editable">{{usersMap|optionMapKV(reportWater.shr)}}</span>
                  </div>
                  <div class="profile-info-name">状态</div>
                  <div class="profile-info-value">
                    <span class="editable" ><span v-if="reportWater.zt =='2'">已处理</span><span v-else-if="reportWater.zt =='3'">虚假上报</span><span v-else-if="reportWater.zt =='1'">已上报</span></span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">处理反馈</div>
                  <div class="profile-info-value">
                    <span class="editable" >{{reportWater.clyj}}</span>
                  </div>
                  <div class="profile-info-name">处理时间</div>
                  <div class="profile-info-value">
                    <span class="editable" >{{reportWater.shsj}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name">备注</div>
                  <div class="profile-info-value">
                    <span class="editable" >{{reportWater.bz}}</span>
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
import ShowPic from "../../components/showpic";
export default {
  components: {Pagination, Times, ToUpper, ShowPic},
  name: "wxbase-reportWater",
  data: function () {
    return {
      reportWater: {},
      reportWaterDto: {},
      reportWaters: [],
      zplist: [],
      maps: [],
      usersMap: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.usersMap = Tool.getUserCode();
      _this.maps = Tool.getDeptUser() ;
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
       * 获取故障类型
       */
      getFileInfo(lsh){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatFileinfo/getFileinfoByLsh/'+ lsh).then((response)=>{
          let resp = response.data;
          _this.zplist = resp.content;
        })
      },

      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.reportWaterDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.reportWaterDto.etime = rep;
        _this.$forceUpdate();
      },

      /**
       * 点击【编辑】
       */
      edit(reportWater) {
        let _this = this;
        _this.reportWater = $.extend({}, reportWater);
        _this.reportWater.sfzmhm = _this.decryptByDES(reportWater.sfzmhm);
        _this.reportWater.sbrsjh = _this.decryptByDES(reportWater.sbrsjh);
        _this.$forceUpdate();
        _this.getFileInfo(reportWater.lsh);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.reportWaterDto.page=page;
        _this.reportWaterDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/reportWater/list',_this.reportWaterDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.reportWaters = resp.content.list;
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
                || !Validator.length(_this.reportWater.sfzmhm, "上报人身份证号码", 1, 50)
                || !Validator.length(_this.reportWater.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.reportWater.sysOrgCode, "处理部门", 1, 64)
                || !Validator.length(_this.reportWater.zsdd, "渍水地点", 1, 512)
                || !Validator.length(_this.reportWater.gps, "渍水坐标", 1, 128)
                || !Validator.length(_this.reportWater.zsddxx, "渍水详细地址", 1, 512)
                || !Validator.length(_this.reportWater.sbr, "上报人", 1, 128)
                || !Validator.length(_this.reportWater.sbrsjh, "上报人手机号", 1, 20)
                || !Validator.length(_this.reportWater.sbsm, "上报说明", 1, 2000)
                || !Validator.length(_this.reportWater.shr, "审核人", 1, 128)
                || !Validator.length(_this.reportWater.clyj, "处理反馈", 1, 2000)
                || !Validator.length(_this.reportWater.zt, "状态", 1, 2)
                || !Validator.length(_this.reportWater.bz, "备注", 1, 4000)
                || !Validator.length(_this.reportWater.openid, "微信用户openid", 1, 32)
                || !Validator.length(_this.reportWater.lsh, "流水号", 1, 32)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/reportWater/save', _this.reportWater).then((response)=>{
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
        Confirm.show("删除渍水上报后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/reportWater/delete/' + id).then((response)=>{
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