<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">金融抵押信息查询</h4>
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
                      <input type="text" id="form-field-5"  class="form-control"  v-model="vehImpawnQueryDto.xh"/>
                    </td>
                    <td style="width:6%" >
                      车辆识别代号：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="form-control"  v-model="vehImpawnQueryDto.clsbdh"/>
                    </td>
                    <td style="width: 6%" >
                      主合同号：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-2"  v-model="vehImpawnQueryDto.zhth"/>
                    </td>
                    <td style="width: 12%" >
                      抵押合同号：
                    </td>
                    <td style="width: 10%">
                      <input  class="form-control"  type="text" id="form-field-3"  v-model="vehImpawnQueryDto.dyhth"/>
                    </td>


                  </tr>
                  <tr style="height: 10px"></tr>
                  <tr>
                    <td style="width: 6%" >
                      身份证明号码：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-4"  v-model="vehImpawnQueryDto.sfzmhm"/>
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
        <th>身份证明名称</th>
        <th>身份证明号码</th>
        <th>管理部门</th>
        <th>录入人</th>
        <th>录入日期</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehImpawnQuery in vehImpawnQuerys">

        <td>{{vehImpawnQuery.xh}}</td>
        <td>{{vehImpawnQuery.clsbdh}}</td>
        <td>{{vehImpawnQuery.zhth}}</td>
        <td>{{vehImpawnQuery.dyhth}}</td>
        <td>{{vehImpawnQuery.dyje}}</td>
        <td>{{vehImpawnQuery.dydjrq}}</td>
        <td>{{vehImpawnQuery.jcrq}}</td>
        <td>{{usersMap|optionMapKV(vehImpawnQuery.jbr)}}</td>
        <td>{{vehImpawnQuery.sfzmmc}}</td>
        <td>{{decryptByDES(vehImpawnQuery.sfzmhm)}}</td>
        <td>{{maps|optionMapKV(vehImpawnQuery.glbm)}}</td>
        <td>{{usersMap|optionMapKV(vehImpawnQuery.createBy)}}</td>
        <td>{{vehImpawnQuery.createTime}}</td>
        <td><span v-if="vehImpawnQuery.zt =='1'">已录入</span><span v-if="vehImpawnQuery.zt =='2'">审核通过</span><span v-if="vehImpawnQuery.zt =='3'">审核不通过</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehImpawnQuery)" class="btn btn-xs btn-info" title="详情">
              <i class="ace-icon fa fa-list bigger-120"></i>
            </button>
            <!--  <button v-on:click="del(vehInsuranceinfo.id)" class="btn btn-xs btn-danger">
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
            <h4 class="modal-title">抵押详细信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">抵押基本信息</h6>
              <div class="profile-user-info profile-user-info-striped">


                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">车辆识别代号</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.clsbdh}}</span>
                      </div>
                      <div class="profile-info-name"  style="width: 10%">机动车序号</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.xh}}</span>
                      </div>
                    </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">所属部门</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{maps|optionMapKV(vehImpawnQuery.sysOrgCode)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">主合同号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnQuery.zhth}}</span>
                  </div>
                </div>

                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">抵押合同号</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.dyhth}}</span>
                      </div>
                      <div class="profile-info-name"  style="width: 10%">抵押金额</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.dyje}}</span>
                      </div>
                      </div>

                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">抵押登记日期</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.dydjrq}}</span>
                      </div>
                      <div class="profile-info-name"  style="width: 10%">经办人</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{usersMap|optionMapKV(vehImpawnQuery.jbr)}}</span>
                      </div>
                    </div>

                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">流水号</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.lsh}}</span>
                      </div>
                    </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">机动车所有人身份证明名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnQuery.sfzmmc}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 20%">机动车所有人身份证明号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnQuery.sfzmhm}}</span>
                  </div>
                </div>

                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">抵押权人行政区划</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{xzqh|optionMapKV(vehImpawnQuery.xzqh)}}</span>
                      </div>
                      <div class="profile-info-name"  style="width: 10%">详细地址</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.xxdz}}</span>
                      </div>
                    </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押权人代码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{dyqrdm|optionMapKV(vehImpawnQuery.dyqrdm)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">抵押权人类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="vehImpawnQuery.dyqrlx =='1'">商业银行</span><span v-if="vehImpawnQuery.dyqrlx =='2'">汽车金融机构</span></span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押权人身份证名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="vehImpawnQuery.dyqrsfzmmc =='B'">组织机构代码证</span><span v-else-if="vehImpawnQuery.dyqrsfzmmc=='N'">统一社会信用代码</span></span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">抵押权人身份证号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnQuery.dyqrsfzmhm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">抵押权人手机号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnQuery.sjhm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">号牌种类</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{hpzl|optionMapKV(vehImpawnQuery.hpzl)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">号牌号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnQuery.hphm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">抵押状态</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="vehImpawnQuery.dyzt =='0'">已解除抵押</span><span v-else-if="vehImpawnQuery.dyzt=='1'">抵押中</span></span>
                  </div>
                </div>

                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">邮政编码</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.yzbm}}</span>
                      </div>
                      <div class="profile-info-name"  style="width: 10%">联系电话</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.lxdh}}</span>
                      </div>
                    </div>

                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">解除抵押日期</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.jcrq}}</span>
                      </div>
                      <div class="profile-info-name"  style="width: 10%">管理部门</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{maps|optionMapKV(vehImpawnQuery.glbm)}}</span>
                      </div>
                    </div>

                <div class="profile-info-row">


                  <div class="profile-info-name"  style="width: 10%">录入人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{usersMap|optionMapKV(vehImpawnQuery.createBy)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">录入日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnQuery.createTime}}</span>
                  </div>

                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">备注</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehImpawnQuery.bz}}</span>
                  </div>
                </div>





                <h6 class="header blue lighter less-margin">审核信息</h6>
                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">审核人</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{usersMap|optionMapKV(vehImpawnQuery.shr)}}</span>
                      </div>
                      <div class="profile-info-name"  style="width: 10%">审核时间</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.shsj}}</span>
                      </div>
                    </div>

                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">审核说明</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{vehImpawnQuery.shsm}}</span>
                      </div>
                      <div class="profile-info-name"  style="width: 10%">审核原因</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable">{{shyy|optionMapKV(vehImpawnQuery.shyy)}}</span>
                      </div>
                    </div>

                    <div class="profile-info-row">
                      <div class="profile-info-name"  style="width: 10%">状态</div>
                      <div class="profile-info-value" style="width: 40%">
                        <span class="editable"><span v-if="vehImpawnQuery.zt =='1'">已录入</span><span v-if="vehImpawnQuery.zt =='2'">审核通过</span><span v-if="vehImpawnQuery.zt =='3'">审核不通过</span></span>
                      </div>
                    </div>
                    <h6 class="header blue lighter less-margin">图片</h6>
                    <div class="profile-info-row">
                      <div class="profile-info-name" >图片</div>
                      <div class="profile-info-value">
                        <show-pic :pics="tpLj"></show-pic>
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
  import ShowPic from "../../components/showpic";
  export default {
    components: {Pagination,Times,ShowPic},
    name: "wxbase-vehImpawnQuery",
    data: function() {
      return {
        vehImpawnQuery: {},
        vehImpawnQueryDto:{},
        vehImpawnQuerys: [],
        hpzl:[],
        maps: [],
        usersMap:[],
        shyy:[],
        tpLj:[],
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
      // this.$parent.activeSidebar("wxbase-vehImpawnQuery-sidebar");

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
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.vehImpawnQueryDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehImpawnQueryDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehImpawnQuery = {};
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
    },/**
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
       * 点击【编辑】
       */
      edit(vehImpawnQuery) {
        let _this = this;
        _this.vehImpawnQuery = $.extend({}, vehImpawnQuery);
        _this.vehImpawnQuery.jqxtbzt = "1";
        _this.sxrq = vehImpawnQuery.sxrq;
        _this.vehImpawnQuery.sfzmhm = _this.decryptByDES(vehImpawnQuery.sfzmhm);
        _this.vehImpawnQuery.dyqrsfzmhm = _this.decryptByDES(vehImpawnQuery.dyqrsfzmhm);
        _this.vehImpawnQuery.lxdh = _this.decryptByDES(vehImpawnQuery.lxdh);
        _this.vehImpawnQuery.sjhm = _this.decryptByDES(vehImpawnQuery.sjhm);
        _this.getFileInfo(_this.vehImpawnQuery.lsh,"1");
        $("#form-modal").modal("show");
      },

      /**
       * 获取当前信息的图片
       */
      getFileInfo(lsh,zplx){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatFileinfo/getFileinfoByLsh/'+ lsh+"/"+zplx).then((response)=>{
          let resp = response.data;
          _this.tpLj = resp.content;
        })
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.vehImpawnQueryDto.page=page;
        _this.vehImpawnQueryDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawnQuery/list',_this.vehImpawnQueryDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehImpawnQuerys = resp.content.list;
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
            || !Validator.require(_this.vehImpawnQuery.xh, "机动车序号")
            || !Validator.length(_this.vehImpawnQuery.xh, "机动车序号", 1, 14)
            || !Validator.require(_this.vehImpawnQuery.clsbdh, "车辆识别代号")
            || !Validator.length(_this.vehImpawnQuery.clsbdh, "车辆识别代号", 1, 20)
            || !Validator.require(_this.vehImpawnQuery.zhth, "主合同号")
            || !Validator.length(_this.vehImpawnQuery.zhth, "主合同号", 1, 64)
            || !Validator.require(_this.vehImpawnQuery.dyhth, "抵押合同号")
            || !Validator.length(_this.vehImpawnQuery.dyhth, "抵押合同号", 1, 32)
            || !Validator.length(_this.vehImpawnQuery.jbr, "经办人", 1, 128)
            || !Validator.length(_this.vehImpawnQuery.lsh, "流水号", 1, 32)
            || !Validator.require(_this.vehImpawnQuery.sfzmmc, "身份证明名称")
            || !Validator.length(_this.vehImpawnQuery.sfzmmc, "身份证明名称", 1, 10)
            || !Validator.require(_this.vehImpawnQuery.sfzmhm, "身份证明号码")
            || !Validator.length(_this.vehImpawnQuery.sfzmhm, "身份证明号码", 1, 32)
            || !Validator.require(_this.vehImpawnQuery.xzqh, "抵押权人行政区划")
            || !Validator.length(_this.vehImpawnQuery.xzqh, "抵押权人行政区划", 1, 128)
            || !Validator.require(_this.vehImpawnQuery.xxdz, "详细地址")
            || !Validator.length(_this.vehImpawnQuery.xxdz, "详细地址", 1, 256)
            || !Validator.length(_this.vehImpawnQuery.yzbm, "邮政编码", 1, 6)
            || !Validator.require(_this.vehImpawnQuery.lxdh, "联系电话")
            || !Validator.length(_this.vehImpawnQuery.lxdh, "联系电话", 1, 20)
            || !Validator.length(_this.vehImpawnQuery.xgzl, "相关资料", 1, 20)
            || !Validator.length(_this.vehImpawnQuery.bz, "备注", 1, 1000)
            || !Validator.length(_this.vehImpawnQuery.dyqrdm, "抵押权人代码", 1, 128)
            || !Validator.require(_this.vehImpawnQuery.dyqrlx, "抵押权人类型")
            || !Validator.length(_this.vehImpawnQuery.dyqrlx, "抵押权人类型", 1, 20)
            || !Validator.require(_this.vehImpawnQuery.dyqrsfzmmc, "抵押权人身份证名称")
            || !Validator.length(_this.vehImpawnQuery.dyqrsfzmmc, "抵押权人身份证名称", 1, 20)
            || !Validator.require(_this.vehImpawnQuery.dyqrsfzmhm, "抵押权人身份证号码")
            || !Validator.length(_this.vehImpawnQuery.dyqrsfzmhm, "抵押权人身份证号码", 1, 20)
            || !Validator.require(_this.vehImpawnQuery.sjhm, "抵押权人手机号")
            || !Validator.length(_this.vehImpawnQuery.sjhm, "抵押权人手机号", 1, 20)
            || !Validator.require(_this.vehImpawnQuery.hpzl, "号牌种类")
            || !Validator.length(_this.vehImpawnQuery.hpzl, "号牌种类", 1, 2)
            || !Validator.require(_this.vehImpawnQuery.hphm, "号牌号码")
            || !Validator.length(_this.vehImpawnQuery.hphm, "号牌号码", 1, 20)
            || !Validator.require(_this.vehImpawnQuery.dyzt, "抵押状态")
            || !Validator.length(_this.vehImpawnQuery.dyzt, "抵押状态", 1, 1)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawnQuery/save', _this.vehImpawnQuery).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehImpawnQuery/delete/' + id).then((response)=>{
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