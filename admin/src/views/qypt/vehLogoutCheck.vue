<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">报废信息审核</h4>
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
                      号牌种类：
                    </td>
                    <td style="width: 10%">
                      <select v-model="vehLogoutDto.hpzl" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in hpzl" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      号牌号码：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="form-control"  v-model="vehLogoutDto.hphm"/>
                    </td>
                    <td style="width: 8%" >
                      车辆识别代号：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-2"  v-model="vehLogoutDto.clsbdh"/>
                    </td>
                    <td  style="width: 6%">
                      录入日期：
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
        <th>号牌种类</th>
        <th>号牌号码</th>
        <th>机动车序号</th>
        <th>机动车所有人</th>
        <th>回收企业名称</th>
        <th>车辆识别代号</th>
        <th>注销日期</th>
        <th>回收号牌</th>
        <th>回收行驶证</th>
        <th>回收登记证书</th>
        <th>监销民警</th>
        <th>报废日期</th>
        <th>创建日期</th>
        <th>审核部门</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehLogout in vehLogouts">
        <td>{{hpzl|optionMapKV(vehLogout.hpzl)}}</td>
        <td>{{vehLogout.hphm}}</td>
        <td>{{vehLogout.xh}}</td>
        <td>{{vehLogout.syr}}</td>
        <td>{{vehLogout.hsqymc}}</td>
        <td>{{vehLogout.clsbdh}}</td>
        <td>{{vehLogout.zxrq}}</td>
        <td><span v-if="vehLogout.hshp =='0'">未收回</span><span v-if="vehLogout.hshp =='1'" >回收机动车前号牌</span><span v-if="vehLogout.hshp =='2'">回收机动车后号牌</span><span v-if="vehLogout.hshp =='3'">回收机动车前、后号牌</span></td>
        <td><span v-if="vehLogout.hsxsz =='1'">收回</span><span v-else-if="vehLogout.hsxsz=='0'">未收回</span></td>
        <td><span v-if="vehLogout.hsdjzs =='0'">未收回</span><span v-if="vehLogout.hsdjzs =='1'">收回</span></td>
        <td>{{vehLogout.jxmj}}</td>
        <td>{{vehLogout.bfrq}}</td>
        <td>{{vehLogout.createTime}}</td>
        <td>{{maps|optionMapKV(vehLogout.glbm)}}</td>
        <td><span v-if="vehLogout.zt =='1'">已录入</span><span v-if="vehLogout.zt =='2'">审核通过</span><span v-if="vehLogout.zt =='3'">审核不通过</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehLogout)" class="btn btn-xs btn-info" title="审核">
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
      <div class="modal-dialog" role="document"  style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">报废信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                <div class="profile-user-info profile-user-info-striped">
                  <div class="profile-info-row">

                    <div class="profile-info-name"  style="width: 10%">号牌种类</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable" >{{hpzl|optionMapKV(vehLogout.hpzl)}}</span>
                    </div>
                    <div class="profile-info-name"  style="width: 10%">号牌号码</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable" >{{vehLogout.hphm}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" style="width: 10%">车辆识别代号</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable" >{{vehLogout.clsbdh}}</span>
                    </div>
                    <div class="profile-info-name" style="width: 10%">机动车序号</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable">{{vehLogout.xh}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" >车辆品牌</div>
                    <div class="profile-info-value">
                      <span class="editable" > {{vehLogout.clpp1}}</span>
                    </div>
                    <div class="profile-info-name"  >车辆型号</div>
                    <div class="profile-info-value" >
                      <span class="editable" >{{vehLogout.clxh}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" >机动车所有人</div>
                    <div class="profile-info-value">
                      <span class="editable" > {{vehLogout.syr}}</span>
                    </div>
                    <div class="profile-info-name"  >报废日期</div>
                    <div class="profile-info-value" >
                      <span class="editable" >{{vehLogout.bfrq}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name"  style="width: 10%">注销原因</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable">{{vehLogout.zxyy}}</span>
                    </div>
                    <div class="profile-info-name" style="width: 10%">回收企业名称</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable">{{vehLogout.hsqymc}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" style="width: 10%">注销日期</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable" >{{vehLogout.zxrq}}</span>
                    </div>
                    <div class="profile-info-name"  style="width: 10%">回收号牌</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable" ><span v-if="vehLogout.hshp =='0'">未收回</span><span v-if="vehLogout.hshp =='1'">回收机动车前号牌</span><span v-if="vehLogout.hshp =='2'">回收机动车后号牌</span><span v-if="vehLogout.hshp =='3'">回收机动车前、后号牌</span></span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" style="width: 10%">回收行驶证</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable"><span v-if="vehLogout.hsxsz =='1'">收回</span><span v-else-if="vehLogout.hsxsz=='0'">未收回</span></span>
                    </div>
                    <div class="profile-info-name"  style="width: 10%">回收登记证书</div>
                    <div class="profile-info-value" style="width: 40%">
                      <span class="editable" ><span v-if="vehLogout.hsxsz =='0'">未收回</span><span v-if="vehLogout.hsxsz =='1'">收回</span></span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" >处罚机关</div>
                    <div class="profile-info-value">
                      <span class="editable" > {{vehLogout.cfjg }}</span>
                    </div>
                    <div class="profile-info-name"  >处罚决定书编号</div>
                    <div class="profile-info-value" >
                      <span class="editable" >{{vehLogout.jdsbh}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" >处罚时间</div>
                    <div class="profile-info-value">
                      <span class="editable" > {{vehLogout.cfsj }}</span>
                    </div>
                    <div class="profile-info-name"  >监销民警</div>
                    <div class="profile-info-value" >
                      <span class="editable" >{{vehLogout.jxmj}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" >注销机关</div>
                    <div class="profile-info-value">
                      <span class="editable" > {{vehLogout.zxjg}}</span>
                    </div>
                    <div class="profile-info-name"  >经办人</div>
                    <div class="profile-info-value" >
                      <span class="editable" >{{vehLogout.jbr}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name" >录入人</div>
                    <div class="profile-info-value">
                      <span class="editable" id="shr"> {{ usersMap|optionMapKV(vehLogout.createBy) }}</span>
                    </div>
                    <div class="profile-info-name"  >录入时间</div>
                    <div class="profile-info-value" >
                      <span class="editable" id="shsj">{{vehLogout.createTime}}</span>
                    </div>
                  </div>

                  <div class="profile-info-row">
                    <div class="profile-info-name"  >备注</div>
                    <div class="profile-info-value" >
                      <span class="editable" >{{vehLogout.bz}}</span>
                    </div>
                  </div>
                </div>

              <h6 class="header blue lighter less-margin">审核信息</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核结果</label>
                <div class="col-sm-10">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="vehLogout.zt"/>
                      <span class="lbl">申请通过</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="3" v-model="vehLogout.zt"/>
                      <span class="lbl">申请不通过</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group" v-show="vehLogout.zt && vehLogout.zt=='3'">
                <label class="col-sm-2 control-label">审核原因</label>
                <div class="col-sm-8">
                  <select v-model="vehLogout.shyy" class="form-control" >
                    <option v-for="(value, key) in shyy" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核说明</label>
                <div class="col-sm-8">
                  <input v-model="vehLogout.shsm" class="form-control">
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
    name: "wxbase-vehLogout",
    data: function() {
      return {
        vehLogout: {},
        vehLogoutDto:{},
        vehLogouts: [],
        hpzl:[],
        maps: [],
        usersMap:[],
        shyy:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.maps = Tool.getDeptUser() ;
      _this.usersMap = Tool.getUserCode();
      _this.getHpzl();
      _this.getShyy();
    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.vehLogoutDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehLogoutDto.etime = rep;
        _this.$forceUpdate();
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
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehLogout = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehLogout) {
        let _this = this;
        _this.vehLogout = $.extend({}, vehLogout);
        _this.vehLogout.zt = "2";
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.vehLogoutDto.page=page;
        _this.vehLogoutDto.size=_this.$refs.pagination.size;
        _this.vehLogoutDto.zt="1";
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLogout/list',_this.vehLogoutDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehLogouts = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;
        if(_this.vehLogout.zt=='3'&&!Validator.require(_this.vehLogout.shyy, "审核原因")){
          return;
        }
        // 保存校验
        if (1 != 1
                || !Validator.length(_this.vehLogout.createBy, "创建人", 1, 50)
                || !Validator.length(_this.vehLogout.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.vehLogout.sysOrgCode, "所属部门", 1, 64)
                || !Validator.length(_this.vehLogout.xh, "机动车序号", 1, 32)
                || !Validator.length(_this.vehLogout.hpzl, "号牌种类", 1, 10)
                || !Validator.length(_this.vehLogout.hphm, "号牌号码", 1, 10)
                || !Validator.length(_this.vehLogout.zxyy, "注销原因", 1, 2)
                || !Validator.length(_this.vehLogout.hsqymc, "回收企业名称", 1, 256)
                || !Validator.length(_this.vehLogout.hshp, "回收号牌", 1, 32)
                || !Validator.length(_this.vehLogout.hsxsz, "回收行驶证", 1, 32)
                || !Validator.length(_this.vehLogout.hsdjzs, "回收登记证书", 1, 32)
                || !Validator.length(_this.vehLogout.cfjg, "处罚机关", 1, 256)
                || !Validator.length(_this.vehLogout.jdsbh, "处罚决定书编号", 1, 32)
                || !Validator.length(_this.vehLogout.jxmj, "监销民警", 1, 128)
                || !Validator.length(_this.vehLogout.zxjg, "注销机关", 1, 32)
                || !Validator.length(_this.vehLogout.jbr, "经办人", 1, 32)
                || !Validator.length(_this.vehLogout.glbm, "", 1, 128)
                || !Validator.length(_this.vehLogout.bz, "备注", 1, 4000)
                || !Validator.length(_this.vehLogout.clpp1, "车辆品牌", 1, 256)
                || !Validator.length(_this.vehLogout.clxh, "车辆型号", 1, 256)
                || !Validator.length(_this.vehLogout.syr, "机动车所有人", 1, 256)
                || !Validator.length(_this.vehLogout.clsbdh, "车辆识别代号", 1, 25)
                || !Validator.length(_this.vehLogout.lsh, "流水号", 1, 255)
                || !Validator.length(_this.vehLogout.shr, "审核人", 1, 128)
                || !Validator.length(_this.vehLogout.shyy, "审核原因", 1, 128)
                || !Validator.length(_this.vehLogout.shsm, "", 1, 2000)
                || !Validator.length(_this.vehLogout.zt, "状态", 1, 1)
                || !Validator.require(_this.vehLogout.zt,"状态")
                || !Validator.require(_this.vehLogout.shsm,"审核说明")
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLogout/save', _this.vehLogout).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLogout/delete/' + id).then((response)=>{
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