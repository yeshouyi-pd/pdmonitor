<template>
  <div>
      <div class="row">
          <div class="col-md-12">
              <!-- query start -->
              <div class="widget-box">
                  <div class="widget-header">
                      <h4 class="widget-title">微信用户查询</h4>
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
                                      <td style="width:6%" >
                                          姓名：
                                      </td>
                                      <td style="width: 8%">
                                          <input type="text" id="form-field-1"  class="input-sm"  v-model="wxUsersDto.zjmc"/>
                                      </td>
                                      <td style="width:8%" >
                                          身份证明号码：
                                      </td>
                                      <td style="width: 8%">
                                          <input type="text" id="form-field-2"  class="input-sm"  v-model="wxUsersDto.zjhm"/>
                                      </td>
                                      <td style="width: 6%" >
                                          是否认证：
                                      </td>
                                      <td style="width: 10%">
                                          <select v-model="wxUsersDto.sfrz" class="form-control">
                                              <option value="" selected>请选择</option>
                                              <option value="1">是</option>
                                              <option value="0">否</option>
                                          </select>
                                      </td>
                                      <td  style="width: 6%">
                                          认证时间：
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

    <p>
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>



    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>身份证明号码</th>
<!--            <th>证件地址</th>-->
            <th>认证时间</th>
            <th>是否认证</th>
            <th>准驾车型</th>
            <th>系统编号</th>
            <th>驾驶证号</th>
            <th>初次领证日期</th>
            <th>有效期止</th>
            <th>联系电话</th>
            <th>用户等级</th>
            <th>关注车辆</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="(wxUsers,index) in wxUserss">
              <td>{{index+1}}</td>
              <td>{{wxUsers.zjmc}}</td>
              <td>{{wxUsers.sex}}</td>
              <td>{{decryptByDES(wxUsers.zjhm)==null?decryptByDES(wxUsers.zjhm):decryptByDES(wxUsers.zjhm).replace(/(\d{3})\d{10}(\w{5})/, '$1**********$2')}}</td>
<!--              <td>{{wxUsers.zjdz}}</td>-->
              <td>{{wxUsers.cjsj}}</td>
              <td><span v-if="wxUsers.sfrz =='1'">是</span><span v-else>否</span></td>
              <td>{{wxUsers.zjcx}}</td>
              <td>{{wxUsers.xtbh}}</td>
              <td>{{wxUsers.jszh}}</td>
              <td>{{wxUsers.cclzrq}}</td>
              <td>{{wxUsers.jszyxqz}}</td>
              <td>{{decryptByDES(wxUsers.lxdh)==null?decryptByDES(wxUsers.lxdh):decryptByDES(wxUsers.lxdh).replace(/(\d{3})\d{4}(\d{4})/, '$1****$2')}}</td>
              <td>{{wxUsers.yhdj}}</td>
              <td>{{wxUsers.gzcl}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="detail(wxUsers)" class="btn btn-xs btn-info" title="详情">
              <i class="ace-icon fa fa-list bigger-120"></i>
            </button>
            <button v-on:click="del(wxUsers.id)" class="btn btn-xs btn-danger" title="删除">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
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
                      <h4 class="modal-title">用户信息</h4>
                  </div>
                  <div class="modal-body">
                      <form class="form-horizontal">
                          <div class="profile-user-info profile-user-info-striped">
                              <div class="profile-info-row">
                                  <div class="profile-info-name">昵称</div>
                                  <div class="profile-info-value">
                                      <span class="editable">{{wxUsers.nickname}}</span>
                                  </div>
                                  <div class="profile-info-name">备注名</div>
                                  <div class="profile-info-value">
                                      <span class="editable">{{wxUsers.remark}}</span>
                                  </div>
                              </div>
                              <div class="profile-info-row">
                                  <div class="profile-info-name">性别</div>
                                  <div class="profile-info-value">
                                      <span class="editable">{{wxUsers.sex}}</span>
                                  </div>
                                  <div class="profile-info-name">证件名称</div>
                                  <div class="profile-info-value">
                                      <span class="editable">{{wxUsers.zjmc}}</span>
                                  </div>
                              </div>

                              <div class="profile-info-row">
                                  <div class="profile-info-name">身份证明号码</div>
                                  <div class="profile-info-value">
                                      <span class="editable" id="qxkm">{{decryptByDES(wxUsers.zjhm)==null?decryptByDES(wxUsers.zjhm):decryptByDES(wxUsers.zjhm).replace(/(\d{3})\d{10}(\w{5})/, '$1**********$2')}}</span>
                                  </div>
                                  <div class="profile-info-name">创建时间</div>
                                  <div class="profile-info-value">
                                      <span class="editable" id="yykssj">{{wxUsers.cjsj}}</span>
                                  </div>
                              </div>

                              <div class="profile-info-row">
                                  <div class="profile-info-name" style="width: 10%;height: 30px">是否认证</div>
                                  <div class="profile-info-value"  style="width: 40%">
<!--                                      <span class="editable" id="createTime">{{wxUsers.sfrz}}</span>-->
                                    <span class="editable" v-if="wxUsers.sfrz =='1'">是</span>
                                    <span class="editable" v-else>否</span>
                                  </div>
                                  <div class="profile-info-name"  style="width: 10%">准驾车型</div>
                                  <div class="profile-info-value" style="width: 40%">
                                      <span class="editable" id="sqsm">{{wxUsers.zjcx}}</span>
                                  </div>
                              </div>

                              <div class="profile-info-row">
                                  <div class="profile-info-name" style="width: 10%;height: 30px">系统编号</div>
                                  <div class="profile-info-value"  style="width: 40%">
                                      <span class="editable" >{{wxUsers.xtbh}}</span>
                                  </div>
                                  <div class="profile-info-name" style="width: 10%;height: 30px">备注</div>
                                  <div class="profile-info-value"  style="width: 40%">
                                      <span class="editable" >{{wxUsers.bz}}</span>
                                  </div>
                              </div>

                              <div class="profile-info-row">
                                  <div class="profile-info-name" style="width: 10%;height: 30px">驾驶证号</div>
                                  <div class="profile-info-value"  style="width: 40%">
                                      <span class="editable" >{{wxUsers.jszh}}</span>
                                  </div>
                                  <div class="profile-info-name" style="width: 10%;height: 30px">初次领证日期</div>
                                  <div class="profile-info-value"  style="width: 40%">
                                      <span class="editable" >{{wxUsers.cclzrq}}</span>
                                  </div>
                              </div>

                              <div class="profile-info-row">
                                  <div class="profile-info-name" style="width: 10%;height: 30px">有效期止</div>
                                  <div class="profile-info-value"  style="width: 40%">
                                      <span class="editable" >{{wxUsers.jszyxqz}}</span>
                                  </div>
                                  <div class="profile-info-name" style="width: 10%;height: 30px">联系电话</div>
                                  <div class="profile-info-value"  style="width: 40%">
                                      <span class="editable" >{{decryptByDES(wxUsers.lxdh)==null?decryptByDES(wxUsers.lxdh):decryptByDES(wxUsers.lxdh).replace(/(\d{3})\d{4}(\d{4})/, '$1****$2')}}</span>
                                  </div>
                              </div>

                              <div class="profile-info-row">
                                  <div class="profile-info-name" style="width: 10%;height: 30px">用户等级</div>
                                  <div class="profile-info-value"  style="width: 40%">
                                      <span class="editable" >{{wxUsers.yhdj}}</span>
                                  </div>
                                  <div class="profile-info-name" style="width: 10%;height: 30px">关注车辆</div>
                                  <div class="profile-info-value"  style="width: 40%">
                                      <span class="editable" >{{wxUsers.gzcl}}</span>
                                  </div>
                              </div>
                            <div class="profile-info-row">
                              <div class="profile-info-name" style="width: 10%;height: 30px">两个人脸的相似度</div>
                              <div class="profile-info-value"  style="width: 40%">
                                <span class="editable" >{{wxUsers.similarity}}</span>
                              </div>
                            </div>
                            <div class="profile-info-row">
                              <div class="profile-info-name"  style="width: 10%">正脸照片</div>
                              <div class="profile-info-value" style="width: 40%">
                                <show-pic :pics="tp1Lj"></show-pic>
                              </div>
                              <div class="profile-info-name"  style="width: 10%">身份证正面</div>
                              <div class="profile-info-value" style="width: 40%">
                                <show-pic :pics="tp2Lj"></show-pic>
                              </div>
                            </div>
                            <div class="profile-info-row">
                              <div class="profile-info-name"  style="width: 10%">身份证反面</div>
                              <div class="profile-info-value" style="width: 40%">
                                <show-pic :pics="tp3Lj"></show-pic>
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

      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/time";
  import ToUpper from "../../components/upperLowerTransform";
  import ShowPic from "../../components/showpic";
  export default {
    components: {Pagination, Times, ToUpper, ShowPic},
    name: "wxbase-wxUsers",
    data: function() {
      return {
        wxUsers: {},
        wxUsersDto: {},
        wxUserss: [],
        zjlx: [],
        tp1Lj:[],
        tp2Lj:[],
        tp3Lj:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.list(1);
        _this.getZjlx();
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-wxUsers-sidebar");

    },
    methods: {
        /**
         *开始时间
         */
        startTime(rep){
            let _this = this;
            _this.wxUsersDto.stime = rep;
            _this.$forceUpdate();
        },
        /**
         *结束时间
         */
        endTime(rep){
            let _this = this;
            _this.wxUsersDto.etime = rep;
            _this.$forceUpdate();
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
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.wxUsers = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(wxUsers) {
        let _this = this;
        _this.wxUsers = $.extend({}, wxUsers);
        $("#form-modal").modal("show");
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
        Loading.show();
        _this.wxUsersDto.page=page;
        _this.wxUsersDto.size=_this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxUsers/list',
            _this.wxUsersDto
        ).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.wxUserss = resp.content.list;
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
                || !Validator.length(_this.wxUsers.openid, "openid", 1, 32)
                || !Validator.length(_this.wxUsers.nickname, "昵称", 1, 50)
                || !Validator.length(_this.wxUsers.remark, "备注名", 1, 50)
                || !Validator.length(_this.wxUsers.sex, "性别", 1, 4)
                || !Validator.length(_this.wxUsers.city, "市", 1, 30)
                || !Validator.length(_this.wxUsers.province, "省", 1, 30)
                || !Validator.length(_this.wxUsers.country, "国家", 1, 30)
                || !Validator.length(_this.wxUsers.zjmc, "证件名称", 1, 50)
                || !Validator.length(_this.wxUsers.zjhm, "证件号", 1, 100)
                || !Validator.length(_this.wxUsers.zjlx, "证件类型", 1, 4)
                || !Validator.length(_this.wxUsers.zjdz, "证件地址", 1, 60)
                || !Validator.length(_this.wxUsers.sfrz, "是否认证", 1, 1)
                || !Validator.length(_this.wxUsers.zjcx, "准驾车型", 1, 4)
                || !Validator.length(_this.wxUsers.xtbh, "系统编号", 1, 36)
                || !Validator.length(_this.wxUsers.bz, "备注", 1, 1000)
                || !Validator.length(_this.wxUsers.similarity, "两个人脸的相似度", 1, 3)
                || !Validator.length(_this.wxUsers.failflag, "标志失败图片，0-没有失败，1-第一张，2-第二张", 1, 1)
                || !Validator.length(_this.wxUsers.jszh, "驾驶证号", 1, 24)
                || !Validator.length(_this.wxUsers.lxdh, "联系电话", 1, 100)
                || !Validator.length(_this.wxUsers.yhdj, "用户等级", 1, 1)
                || !Validator.length(_this.wxUsers.gzcl, "关注车辆", 1, 200)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxUsers/save', _this.wxUsers).then((response)=>{
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
        Confirm.show("删除微信认证用户信息后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/wxUsers/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      },
      detail(wxUsers){
        let _this = this;
        _this.tp1Lj = [];
        _this.tp2Lj = [];
        _this.tp3Lj = [];
        _this.tp1Lj.push({'zplj': wxUsers.tp1});
        _this.tp2Lj.push({'zplj': wxUsers.tp2});
        _this.tp3Lj.push({'zplj': wxUsers.tp3});
        _this.wxUsers = $.extend({}, wxUsers);
        $("#form-modal").modal("show");
      }
    }
  }
</script>