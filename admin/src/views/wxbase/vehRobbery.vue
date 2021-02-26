<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">套牌车报案查询</h4>
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
                      <select v-model="vehRobberyDto.hpzl" class="form-control" >{{hpzl}}
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in hpzl" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      被套车牌：
                    </td>
                    <td style="width: 8%">
                      <to-upper class="form-control" v-model="vehRobberyDto.hphm" idValue="form-field-3"
                                type="toUpper"></to-upper>
                    </td>
                    <td style="width:10%" >
                      身份证明号码：
                    </td>
                    <td style="width: 8%">
                      <to-upper type="toUpper" idValue="form-field-2" class="form-control"
                                v-model="vehRobberyDto.sfzmhm"/>
                    </td>

                    <td style="width:6%" >
                      上报人：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="form-control"  v-model="vehRobberyDto.xm"/>
                    </td>

                  </tr>
                  <tr style="height: 10px"></tr>
                  <tr>
                    <td style="width: 6%" >
                      状态：
                    </td>
                    <td style="width: 8%">
                      <select v-model="vehRobberyDto.zt"  class="form-control" id="form-field-select-1">
                        <option value="">请选择</option>
                        <option value="1">已上报</option>
                        <option value="2">已受理</option>
                        <option value="3">无法受理</option>
                      </select>
                    </td>
                    <td style="width: 6%" >
                      车辆类别：
                    </td>
                    <td style="width: 8%">
                      <select v-model="vehRobberyDto.lb"  class="form-control" id="form-field-select-2">
                        <option value="">请选择</option>
                        <option value="1">个人车辆</option>
                        <option value="2">企业车辆</option>
                      </select>
                    </td>
                    <td  style="width: 6%">
                      报案日期：
                    </td>
                    <td style="width: 15%"  colspan="2">
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
          <th>号牌种类</th>
          <th>被套车牌</th>
          <th>报案人</th>
          <th>身份证明号码</th>
          <th>被套车品牌</th>
          <th>车辆类别</th>
          <th>报案日期</th>
          <th>处理人</th>
          <th>处理时间</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="vehRobbery in vehRobberys">
          <td>{{hpzl|optionMapKV(vehRobbery.hpzl)}}</td>
          <td>{{vehRobbery.hphm}}</td>
          <td>{{vehRobbery.xm}}</td>
          <td>{{decryptByDES(vehRobbery.sfzmhm)}}</td>
          <td>{{vehRobbery.clpp}}</td>
          <td><span v-if="vehRobbery.lb =='1'">个人车辆</span><span v-else-if="vehRobbery.lb=='2'">企业车辆</span></td>
          <td>{{vehRobbery.createTime}}</td>
          <td>{{usersMap|optionMapKV(vehRobbery.shr)}}</td>
          <td>{{vehRobbery.shsj}}</td>
          <td><span v-if="vehRobbery.zt =='1'">已上报</span><span v-else-if="vehRobbery.zt=='2'">已受理</span><span v-else-if="vehRobbery.zt=='3'">无法受理</span></td>

          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(vehRobbery)" class="btn btn-xs btn-info" title="详情">
                <i class="ace-icon fa fa-list bigger-120"></i>
              </button>
              <!--<button v-on:click="del(vehRobbery.id)" class="btn btn-xs btn-danger">
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
            <h4 class="modal-title">套牌车报案信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name" >号牌种类</div>
                  <div class="profile-info-value">
                    <span class="editable" id="hpzl">{{hpzl|optionMapKV(vehRobbery.hpzl)}}</span>
                  </div>
                  <div class="profile-info-name" >被套车牌</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="hphm">{{vehRobbery.hphm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >被套车品牌</div>
                  <div class="profile-info-value">
                    <span class="editable" id="syr">{{vehRobbery.clpp}}</span>
                  </div>
                  <div class="profile-info-name" >车辆类别</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="lxdh"><span v-if="vehRobbery.lb =='1'">个人车辆</span><span v-else-if="vehRobbery.lb=='2'">企业车辆</span></span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">报案人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sqr">{{vehRobbery.xm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">身份证明号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="txzlx">{{decryptByDES(vehRobbery.sfzmhm)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >联系电话</div>
                  <div class="profile-info-value">
                    <span class="editable" id="cfd">{{decryptByDES(vehRobbery.lxdh)}}</span>
                  </div>
                  <div class="profile-info-name" >报案日期</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="mdd">{{vehRobbery.createTime}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >家庭住址</div>
                  <div class="profile-info-value">
                    <span class="editable" id="yxqkssj">{{vehRobbery.jtzz}}</span>
                  </div>
                  <div class="profile-info-name" >情况描述</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehRobbery.qksm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >举报证据</div>
                  <div class="profile-info-value">
                    <span class="editable" id="zj">{{vehRobbery.zj}}</span>
                  </div>
<!--                  <div class="profile-info-name" >情况描述</div>-->
<!--                  <div class="profile-info-value" >-->
<!--                    <span class="editable" id="createTime">{{vehRobbery.qksm}}</span>-->
<!--                  </div>-->
                  <div class="profile-info-name" >状态</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="zt"><span v-if="vehRobbery.zt =='1'">已上报</span><span v-else-if="vehRobbery.zt=='2'">已受理</span><span v-else-if="vehRobbery.zt=='3'">无法受理</span></span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >处理部门</div>
                  <div class="profile-info-value">
                    <span class="editable" id="sysOrgCode">{{maps|optionMapKV(vehRobbery.sysOrgCode)}}</span>
                  </div>
                  <div class="profile-info-name" >处理人</div>
                  <div class="profile-info-value">
                    <span class="editable" id="shr">{{usersMap|optionMapKV(vehRobbery.shr)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >处理说明</div>
                  <div class="profile-info-value">
                    <span class="editable" id="shsm">{{vehRobbery.shsm}}</span>
                  </div>
                  <div class="profile-info-name" >处理时间</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="shsj">{{vehRobbery.shsj}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >备注</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="bz">{{vehRobbery.bz}}</span>
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
            <button type="button"  class="btn btn-primary" data-dismiss="modal">关闭</button>
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
  name: "wxbase-vehRobbery",
  data: function () {
    return {
      vehRobbery: {},
      vehRobberyDto: {},
      vehRobberys: [],
      hpzl: [],
      maps: [],
      usersMap: [],
      zplist:[]
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
    _this.getHpzl();
    _this.maps = Tool.getDeptUser() ;
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
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.vehRobberyDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.vehRobberyDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.vehRobbery = {};
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(vehRobbery) {
      let _this = this;
      _this.vehRobbery = $.extend({}, vehRobbery);
      _this.getFileInfo(vehRobbery.lsh);
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
      _this.vehRobberyDto.page=page;
      _this.vehRobberyDto.size=_this.$refs.pagination.size;
      _this.$forceUpdate();
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehRobbery/list', _this.vehRobberyDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.vehRobberys = resp.content.list;
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
          || !Validator.length(_this.vehRobbery.createBy, "创建人", 1, 50)
          || !Validator.length(_this.vehRobbery.updateBy, "更新人", 1, 50)
          || !Validator.length(_this.vehRobbery.sysOrgCode, "所属部门", 1, 64)
          || !Validator.length(_this.vehRobbery.xm, "报案人", 1, 128)
          || !Validator.length(_this.vehRobbery.sfzmhm, "身份证明号码", 1, 20)
          || !Validator.length(_this.vehRobbery.lxdh, "联系电话", 1, 20)
          || !Validator.length(_this.vehRobbery.jtzz, "家庭住址", 1, 512)
          || !Validator.length(_this.vehRobbery.hphm, "被套车牌", 1, 20)
          || !Validator.length(_this.vehRobbery.clpp, "被套车品牌", 1, 256)
          || !Validator.length(_this.vehRobbery.zj, "车辆被套牌证据", 1, 256)
          || !Validator.length(_this.vehRobbery.qksm, "情况说明", 1, 3000)
          || !Validator.length(_this.vehRobbery.bz, "备注", 1, 4000)
          || !Validator.length(_this.vehRobbery.zt, "状态", 1, 2)
          || !Validator.length(_this.vehRobbery.openid, "微信用户id", 1, 128)
          || !Validator.length(_this.vehRobbery.lsh, "流水号", 1, 45)
          || !Validator.length(_this.vehRobbery.shr, "审核人", 1, 128)
          || !Validator.length(_this.vehRobbery.shsm, "审核说明", 1, 500)
      ) {
        return;
      }

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehRobbery/save', _this.vehRobbery).then((response)=>{
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
      Confirm.show("删除套牌车信息上报后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehRobbery/delete/' + id).then((response)=>{
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