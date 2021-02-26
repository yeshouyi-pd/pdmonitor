<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">车牌螺丝固封申领线上处理</h4>
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
                    <td style="width: 6%" >
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
          <td>
            <div class="hidden-sm hidden-xs btn-group" >
              <button v-on:click="edit(vehScrew)" class="btn btn-xs btn-info" title="处理">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
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
                    <span class="editable" id="sf">{{addrList|optionCNArray(vehScrew.sf)}}{{addrList|optionCNArray(vehScrew.cs)}}{{addrList|optionCNArray(vehScrew.qx)}}{{vehScrew.jddz}}</span>
                  </div>
                  <div class="profile-info-name"  >备注</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="bz">{{vehScrew.bz}}</span>
                  </div>
                </div>

              </div>

              <h6 class="header blue lighter less-margin">处理信息</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">处理结果</label>
                <div class="col-sm-10">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="vehScrew.zt" checked/>
                      <span class="lbl">已邮寄</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="4" v-model="vehScrew.zt"/>
                      <span class="lbl">无法申领</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核说明</label>
                <div class="col-sm-8">
                  <input v-model="vehScrew.shyy" class="form-control">
                </div>
              </div>
              <div class="form-group" v-if="vehScrew.zt==2">
                <label class="col-sm-2 control-label">快递单号</label>
                <div class="col-sm-4">
                  <input v-model="vehScrew.dh" class="form-control" readonly>
                </div>
                <div class="col-sm-4">
                  <button  type="button"  class="btn btn-sm  btn-info btn-round" v-on:click="getEmailNo(vehScrew)">
                    <i class="ace-icon fa fa-book"></i>
                    获取单号
                  </button>
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
      slcs:"",
      addrList:[]
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
    _this.getScrewYy();
    _this.maps = Tool.getDeptUser() ;
    _this.usersMap = Tool.getUserCode();
    _this.findAddrListAll();
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
     * 根据号牌获取车辆领取固封螺丝次数
     */
    getScrewByHphm(hphm){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/vehScrew/getScrewByHphm/'+ hphm).then((response)=>{
        let resp = response.data;
        _this.slcs = resp.content;
      })
    },
    /**
     * 点击【编辑】
     */
    edit(vehScrew) {
      let _this = this;
      _this.vehScrew = $.extend({}, vehScrew);
      _this.getScrewByHphm(vehScrew.hphm);
      _this.vehScrew.zt = "2";
      _this.$forceUpdate();
      $("#form-modal").modal("show");
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.vehScrewDto.page=page;
      _this.vehScrewDto.size=_this.$refs.pagination.size;
      _this.vehScrewDto.sllb="1";
      _this.vehScrewDto.zt="1";
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
          || !Validator.length(_this.vehScrew.sf, "省份", 1, 128)
          || !Validator.length(_this.vehScrew.cs, "城市", 1, 128)
          || !Validator.length(_this.vehScrew.qx, "区县", 1, 128)
          || !Validator.length(_this.vehScrew.jddz, "街道地址", 1, 256)
          || !Validator.length(_this.vehScrew.zt, "状态", 1, 2)
          || !Validator.length(_this.vehScrew.shr, "审核人", 1, 128)
          || !Validator.length(_this.vehScrew.bz, "备注", 1, 4000)
          || !Validator.length(_this.vehScrew.openid, "微信用户id", 1, 32)
          || !Validator.length(_this.vehScrew.shyy, "审核原因说明", 1, 200)
          || !Validator.length(_this.vehScrew.ywmc, "业务名称", 1, 32)
          || !Validator.length(_this.vehScrew.lsh, "流水号", 1, 32)
          || !Validator.length(_this.vehScrew.sfbr, "是否本人车辆1是0否", 1, 1)
          || !Validator.length(_this.vehScrew.sllb, "申领类别1线上申领2线下申领", 1, 1)
      ) {
        return;
      }
      if(_this.vehScrew.zt=='2'){
        if(!Validator.require(_this.vehScrew.dh,"快递单号")){
          return ;
        }
        _this.vehScrew.shyy="";
      }
      if(_this.vehScrew.zt=='4'){
        if(!Validator.require(_this.vehScrew.shyy,"审核说明")){
          return ;
        }
        _this.vehScrew.dh="";
      }
      _this.$forceUpdate();
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
    },

    /**
     * 获取单号
     */
    getEmailNo(vehScrew){
      let _this = this;
      let obj = {
        'distributor_orderID':vehScrew.id,
        'receiver_name':vehScrew.sjr,
        'receiver_phone':_this.decryptByDES(vehScrew.lxdh),
        'receiver_province_code':vehScrew.sf,
        'receiver_city_code':vehScrew.cs,
        'receiver_county_code':vehScrew.qx,
        'receiver_address':vehScrew.jddz
      };
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getEmailNo', obj).then((response)=>{
        if(response.data.success){
          let result = response.data.content;
          if(result.status==0){
            _this.vehScrew.dh = result.data.mail_no;
            _this.$forceUpdate();
          }else {
            Toast.warning(result.msg);
          }
        }else {
          Toast.warning(response.data.content);
        }
      })
    },

    /**
     * 查询所有地址信息
     */
    findAddrListAll(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/addrInfo/findListAll').then((response)=>{
        let resp = response.data;
        _this.addrList = resp.content;
      })
    }
  }
}
</script>