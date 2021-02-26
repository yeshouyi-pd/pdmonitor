<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">车牌螺丝固封申领查询</h4>
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
                    <td style="width:6%" >
                      号牌号码：
                    </td>
                    <td style="width: 10%">
                      <to-upper class="form-control" v-model="vehScrewDto.hphm" idValue="form-field-1"
                                type="toUpper"></to-upper>
                    </td>
                    <td style="width: 8%" >
                      身份证明号码：
                    </td>
                    <td style="width: 10%">
                      <to-upper class="input-sm" type="toUpper" idValue="form-field-2" v-model="vehScrewDto.sfzmhm"/>
                    </td>
                    <td style="width: 6%" >
                      申领类别：
                    </td>
                    <td style="width: 10%">
                      <select v-model="vehScrewDto.sllb" class="form-control">
                        <option v-for="(lb, key) in vehScrew_lb" v-bind:value="lb.key">{{lb.value}}</option>
                      </select>
                    </td>
                    <td  style="width: 6%">
                      申领时间：
                    </td>
                    <td style="width: 15%">
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
          <th>身份证明号码</th>
          <th>号牌号码</th>
          <th>申领原因</th>
          <th>申领人</th>
          <th>申领数量</th>
          <th>是否本人车辆</th>
          <th>申领类别</th>
          <th>联系电话</th>
          <th>状态</th>
          <th>申领时间</th>
          <th>审核人</th>
          <th>审核时间</th>
          <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="vehScrew in vehScrews">
          <td>{{decryptByDES(vehScrew.sfzmhm)}}</td>
          <td>{{ vehScrew.hphm }}</td>
          <td>{{ screwyy|optionMapKV(vehScrew.sqyy) }}</td>
          <td>{{ vehScrew.createBy }}</td>
          <td>{{ vehScrew_sl|optionKV(vehScrew.slsl) }}</td>
          <td>{{vehScrew_sfbr | optionKV(vehScrew.sfbr)}} </td>
          <td>{{vehScrew_lb | optionKV(vehScrew.sllb)}}</td>
          <td>{{decryptByDES(vehScrew.lxdh)}}</td>
          <td>{{vehScrew_zt| optionKV(vehScrew.zt)}}</td>
          <td>{{ vehScrew.createTime }}</td>
          <td>{{ usersMap|optionMapKV(vehScrew.shr) }}</td>
          <td>{{ vehScrew.shsj }}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group" >
              <button v-on:click="edit(vehScrew)" class="btn btn-xs btn-info" title="详情">
                <i class="ace-icon fa fa-list bigger-120"></i>
              </button>
              <!--<button v-on:click="del(vehScrew.id)" class="btn btn-xs btn-danger">
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
      <div class="modal-dialog" role="document"  style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">车牌固封螺丝申领信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">

                  <div class="profile-info-name"  style="width: 10%">号牌号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="hphm">{{vehScrew.hphm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">申领原因</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sqyy">{{screwyy| optionMapKV(vehScrew.sqyy)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">申请人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="createBy">{{vehScrew.createBy}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">身份证明号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sfzmhm">{{decryptByDES(vehScrew.sfzmhm)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">业务名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="ywmc">{{vehScrew.ywmc}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">申领数量</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sbr">{{ vehScrew_sl|optionKV(vehScrew.slsl) }}</span>
                  </div>

                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">申领网点</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sysOrgCode">{{ maps|optionMapKV(vehScrew.sysOrgCode)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">申领时间</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="createTime">{{vehScrew.createTime}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">是否本人车辆</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sfbr">{{vehScrew_sfbr | optionKV(vehScrew.sfbr)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">申领类别</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sllb">{{vehScrew_lb | optionKV(vehScrew.sllb)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >处理人</div>
                  <div class="profile-info-value">
                    <span class="editable" id="shr">{{ usersMap|optionMapKV(vehScrew.shr) }}</span>
                  </div>
                  <div class="profile-info-name"  >处理时间</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="shsj">{{vehScrew.shsj}}</span>
                  </div>
                </div>


                <div class="profile-info-row">
                  <div class="profile-info-name" >处理结果</div>
                  <div class="profile-info-value">
                    <span class="editable" id="zt">{{vehScrew_zt| optionKV(vehScrew.zt)}}</span>
                  </div>
                  <div class="profile-info-name"  >审核说明</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="shyy">{{vehScrew.shyy}}</span>
                  </div>
                </div>

              </div>
              <h6 class="header blue lighter less-margin">邮寄信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">收件人</div>
                  <div class="profile-info-value"  style="width: 40%">
                    <span class="editable" id="sjr">{{vehScrew.sjr}}</span>
                  </div>
                  <div class="profile-info-name"   style="width: 10%">收件人电话</div>
                  <div class="profile-info-value"  style="width: 40%">
                    <span class="editable" id="lxdh">{{decryptByDES(vehScrew.lxdh)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >收件人地址</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sf">{{vehScrew.sf}}{{vehScrew.cs}}{{vehScrew.qx}}{{vehScrew.jddz}}</span>
                  </div>
                  <div class="profile-info-name"  >单号</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="dh">{{vehScrew.dh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  >备注</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="bz">{{vehScrew.bz}}</span>
                  </div>
                  <div class="profile-info-name"  ></div>
                  <div class="profile-info-value" >
                    <span class="editable"></span>
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
import Times from "@/components/time";
import ToUpper from "../../components/upperLowerTransform";
export default {
  components: {Pagination, Times, ToUpper},
  name: "wxbase-vehScrew",
  data: function () {
    return {
      vehScrew: {},
      vehScrewDto: {},
      vehScrews: [],
      screwyy: [],
      maps: [],
      usersMap: [],
      vehScrew_lb: VEHSCREW_LB,
      vehScrew_sfbr:VEHSCREW_SFBR,
      vehScrew_zt:VEHSCREW_ZT,
      vehScrew_sl:VEHSCREW_SL,
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
    _this.getScrewYy();
    _this.maps = Tool.getDeptUser() ;
    _this.usersMap = Tool.getUserCode();

  },
  methods: {

    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.vehScrewDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.vehScrewDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 获取固封螺丝申领原因
     */
    getScrewYy(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getScrewYy', {
      }).then((response)=>{
        let resp = response.data;
        _this.screwyy = resp.content;
      })
    },
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.vehScrew = {};
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(vehScrew) {
      let _this = this;
      _this.vehScrew = $.extend({}, vehScrew);
      $("#form-modal").modal("show");
    },

    /**
     * 列表查询
     */
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.vehScrewDto.page=page;
      _this.vehScrewDto.size=_this.$refs.pagination.size;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehScrew/list', _this.vehScrewDto ).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.vehScrews = resp.content.list;
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
          || !Validator.length(_this.vehScrew.createBy, "申请人", 1, 50)
          || !Validator.length(_this.vehScrew.updateBy, "更新人", 1, 50)
          || !Validator.length(_this.vehScrew.sysOrgCode, "申领网点", 1, 64)
          || !Validator.length(_this.vehScrew.sfzmhm, "身份证明号码", 1, 32)
          || !Validator.length(_this.vehScrew.hphm, "号牌号码", 1, 32)
          || !Validator.length(_this.vehScrew.sqyy, "申请原因", 1, 32)
          || !Validator.length(_this.vehScrew.slsl, "申领数量", 1, 32)
          || !Validator.length(_this.vehScrew.sjr, "收件人", 1, 32)
          || !Validator.length(_this.vehScrew.lxdh, "联系电话", 1, 32)
          || !Validator.length(_this.vehScrew.sf, "省份", 1, 32)
          || !Validator.length(_this.vehScrew.cs, "城市", 1, 32)
          || !Validator.length(_this.vehScrew.qx, "区县", 1, 32)
          || !Validator.length(_this.vehScrew.jddz, "街道地址", 1, 32)
          || !Validator.length(_this.vehScrew.zt, "状态", 1, 2)
          || !Validator.length(_this.vehScrew.shr, "审核人", 1, 128)
          || !Validator.length(_this.vehScrew.bz, "备注", 1, 4000)
          || !Validator.length(_this.vehScrew.openid, "微信用户id", 1, 32)
          || !Validator.length(_this.vehScrew.shyy, "审核原因说明", 1, 32)
          || !Validator.length(_this.vehScrew.ywmc, "业务名称", 1, 32)
          || !Validator.length(_this.vehScrew.lsh, "流水号", 1, 32)
          || !Validator.length(_this.vehScrew.sfbr, "是否本人车辆1是0否", 1, 1)
          || !Validator.length(_this.vehScrew.sllb, "申领类别1线上申领2线下申领", 1, 1)
      ) {
        return;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehScrew/save', _this.vehScrew).then((response)=>{
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
     * 点击【删除】
     */
    del(id) {
      let _this = this;
      Confirm.show("删除车牌固封螺丝申请表后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehScrew/delete/' + id).then((response)=>{
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