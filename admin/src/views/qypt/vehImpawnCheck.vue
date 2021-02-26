<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">金融抵押信息审核</h4>
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
                      机动车序号：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-5"  class="form-control"  v-model="vehImpawnCheckDto.xh"/>
                    </td>
                    <td style="width:6%" >
                      车辆识别代号：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="form-control"  v-model="vehImpawnCheckDto.clsbdh"/>
                    </td>
                    <td style="width: 6%" >
                      主合同号：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-2"  v-model="vehImpawnCheckDto.zhth"/>
                    </td>
                    <td style="width: 12%" >
                      抵押合同号：
                    </td>
                    <td style="width: 10%">
                      <input  class="form-control"  type="text" id="form-field-3"  v-model="vehImpawnCheckDto.dyhth"/>
                    </td>


                  </tr>
                  <tr style="height: 10px"></tr>
                  <tr>
                    <td style="width: 6%" >
                      身份证明号码：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-4"  v-model="vehImpawnCheckDto.sfzmhm"/>
                    </td>
                    <td  style="width: 6%">
                      录入日期：
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
        <th>机动车序号</th>
        <th>车辆识别代号</th>
        <th>主合同号</th>
        <th>抵押合同号</th>
        <th>抵押金额</th>
        <th>抵押登记日期</th>
        <th>解除抵押日期</th>
        <th>经办人</th>
        <th>流水号</th>
        <th>身份证明名称</th>
        <th>身份证明号码</th>
        <th>管理部门</th>
        <th>创建人</th>
        <th>创建日期</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehImpawnCheck in vehImpawnChecks">
        <td>{{vehImpawnCheck.xh}}</td>
        <td>{{vehImpawnCheck.clsbdh}}</td>
        <td>{{vehImpawnCheck.zhth}}</td>
        <td>{{vehImpawnCheck.dyhth}}</td>
        <td>{{vehImpawnCheck.dyje}}</td>
        <td>{{vehImpawnCheck.dydjrq}}</td>
        <td>{{vehImpawnCheck.jcrq}}</td>
        <td>{{usersMap|optionMapKV(vehImpawnCheck.jbr)}}</td>
        <td>{{vehImpawnCheck.lsh}}</td>
        <td>{{vehImpawnCheck.sfzmmc}}</td>
        <td>{{decryptByDES(vehImpawnCheck.sfzmhm)}}</td>
        <td>{{maps|optionMapKV(vehImpawnCheck.glbm)}}</td>
        <td>{{usersMap|optionMapKV(vehImpawnCheck.createBy)}}</td>
        <td>{{vehImpawnCheck.createTime}}</td>
        <td><span v-if="vehImpawnCheck.zt =='1'">已录入</span><span v-if="vehImpawnCheck.zt =='2'">审核通过</span><span v-if="vehImpawnCheck.zt =='3'">审核不通过</span></td>
        <td>
        <div class="hidden-sm hidden-xs btn-group">
          <button v-on:click="edit(vehImpawnCheck)" class="btn btn-xs btn-info" title="审核">
            <i class="ace-icon fa fa-pencil bigger-120"></i>
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
            <h4 class="modal-title">金融抵押信息审核</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">抵押基本信息</h6>
              <div class="profile-user-info profile-user-info-striped">


                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">车辆识别代号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.clsbdh}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">机动车序号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.xh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">所属部门</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{maps|optionMapKV(vehImpawnCheck.sysOrgCode)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">主合同号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.zhth}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押合同号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.dyhth}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">抵押金额</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.dyje}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押登记日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.dydjrq}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">经办人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.jbr}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">流水号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.lsh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">机动车所有人身份证明名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.sfzmmc}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 20%">机动车所有人身份证明号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.sfzmhm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押权人行政区划</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{xzqh|optionMapKV(vehImpawnCheck.xzqh)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">详细地址</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.xxdz}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押权人代码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{dyqrdm|optionMapKV(vehImpawnCheck.dyqrdm)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">抵押权人类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="vehImpawnCheck.dyqrlx =='1'">商业银行</span><span v-if="vehImpawnCheck.dyqrlx =='2'">汽车金融机构</span></span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押权人身份证名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="vehImpawnCheck.dyqrsfzmmc =='B'">组织机构代码证</span><span v-else-if="vehImpawnCheck.dyqrsfzmmc=='N'">统一社会信用代码</span></span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">抵押权人身份证号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.dyqrsfzmhm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押权人手机号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.sjhm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">号牌种类</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{hpzl|optionMapKV(vehImpawnCheck.hpzl)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">号牌号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.hphm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">抵押状态</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="vehImpawnCheck.dyzt =='0'">已解除抵押</span><span v-else-if="vehImpawnCheck.dyzt=='1'">抵押中</span></span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">邮政编码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.yzbm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">联系电话</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.lxdh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">解除抵押日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.jcrq}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">管理部门</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{maps|optionMapKV(vehImpawnCheck.glbm)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">


                  <div class="profile-info-name"  style="width: 10%">录入人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{usersMap|optionMapKV(vehImpawnCheck.createBy)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">录入日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.createTime}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">备注</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnCheck.bz}}</span>
                  </div>
                </div>
              </div>

              <h6 class="header blue lighter less-margin">审核信息</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核结果*</label>
                <div class="col-sm-10">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="1" v-model="vehImpawnCheck.zt"/>
                      <span class="lbl">申请通过</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="3" v-model="vehImpawnCheck.zt"/>
                      <span class="lbl">申请不通过</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group" v-if="vehImpawnCheck.zt=='3'">
                <label class="col-sm-2 control-label">审核原因</label>
                <div class="col-sm-8">
                  <select v-model="vehImpawnCheck.shyy" class="form-control" >
                    <option v-for="(value, key) in shyy" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核说明</label>
                <div class="col-sm-8">
                  <input v-model="vehImpawnCheck.shsm" class="form-control">
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
  export default {
    components: {Pagination,Times},
    name: "wxbase-vehImpawnCheck",
    data: function() {
      return {
        vehImpawnCheck: {},
        vehImpawnCheckDto:{},
        vehImpawnChecks: [],
        hpzl:[],
        shyy:[],
        maps: [],
        usersMap:[],
        xzqh:[],
        dyqrdm:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getHpzl();
      _this.getShyy();
      _this.getDyqr();
      _this.getXzqh();
      _this.maps = Tool.getDeptUser() ;
      _this.usersMap = Tool.getUserCode();
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-vehImpawnCheck-sidebar");

    },
    methods: {decryptByDES(ciphertext) {
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
        _this.vehImpawnCheckDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehImpawnCheckDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehImpawnCheck = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehImpawnCheck) {
        let _this = this;
        _this.vehImpawnCheck = $.extend({}, vehImpawnCheck);
        _this.vehImpawnCheck.sfzmhm = _this.decryptByDES(vehImpawnCheck.sfzmhm);
        _this.vehImpawnCheck.dyqrsfzmhm = _this.decryptByDES(vehImpawnCheck.dyqrsfzmhm);
        _this.vehImpawnCheck.lxdh = _this.decryptByDES(vehImpawnCheck.lxdh);
        _this.vehImpawnCheck.sjhm = _this.decryptByDES(vehImpawnCheck.sjhm);
        $("#form-modal").modal("show");
      },
      /**
       * 获取审核原因
       */
      getShyy(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getShyy', {
        }).then((response)=>{
          let resp = response.data;
          _this.shyy = resp.content;
        })
      },
      /**
       * 获取抵押权人
       */
      getDyqr(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDyqr', {
        }).then((response)=>{
          let resp = response.data;
          _this.dyqrdm = resp.content;
        })
      },
      /**
       * 获取行政区划
       */
      getXzqh(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getXzqh', {
        }).then((response)=>{
          let resp = response.data;
          _this.xzqh = resp.content;
        })
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.vehImpawnCheckDto.page=page;
        _this.vehImpawnCheckDto.size=_this.$refs.pagination.size;
        _this.vehImpawnCheckDto.zt="1";
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawnCheck/list',_this.vehImpawnCheckDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehImpawnChecks = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;
        if(_this.vehImpawnCheck.zt=='3'&&!Validator.require(_this.vehImpawnCheck.shyy, "审核原因")){
          return;
        }
        // 保存校验
        if (1 != 1
            || !Validator.require(_this.vehImpawnCheck.zt, "审核结果")
            || !Validator.length(_this.vehImpawnCheck.zt, "审核结果", 1, 1)
            || !Validator.length(_this.vehImpawnCheck.shyy, "审核原因", 1, 1000)
            || !Validator.length(_this.vehImpawnCheck.shsm, "审核说明", 1, 2000)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawnCheck/save', _this.vehImpawnCheck).then((response)=>{
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
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("删除后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawnCheck/delete/' + id).then((response)=>{
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