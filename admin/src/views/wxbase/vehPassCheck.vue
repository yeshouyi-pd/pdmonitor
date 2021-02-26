<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">通行证审核</h4>
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
                      <to-upper class="input-sm" v-model="vehPassDto.hphm" idValue="form-field-1"
                                type="toUpper"></to-upper>
                    </td>
                    <td style="width: 8%" >
                      申请个人/企业：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-2"  v-model="vehPassDto.sqr"/>
                    </td>

                    <td  style="width: 6%">
                      申请时间：
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
        <th>号牌种类</th>
        <th>号牌号码</th>
        <th>申请个人/单位</th>
        <th>通行证类型</th>
        <th>出发地</th>
        <th>目的地</th>
        <th>通行开始日期</th>
        <th>通行结束日期</th>
        <th>所有人</th>
        <th>联系电话</th>
        <th>创建日期</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehPass in vehPasss">
              <td>{{hpzl|optionMapKV(vehPass.hpzl)}}</td>
              <td>{{vehPass.hphm}}</td>
              <td>{{vehPass.sqr}}</td>
              <td>{{vehPass.txzlx}}</td>
              <td>{{vehPass.cfd}}</td>
              <td>{{vehPass.mdd}}</td>
              <td>{{vehPass.yxqkssj}}</td>
              <td>{{vehPass.yxqjssj}}</td>
              <td>{{vehPass.syr}}</td>
              <td>{{vehPass.lxdh}}</td>
              <td>{{vehPass.createTime}}</td>
              <td><span v-if="vehPass.zt =='1'">正常</span><span v-else-if="vehPass.zt =='2'">禁用</span></td>


        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehPass)" class="btn btn-xs btn-info" title="审核">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
<!--            <button v-on:click="del(vehPass.id)" class="btn btn-xs btn-danger">-->
<!--              <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--            </button>-->
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
            <h4 class="modal-title">通行证详细信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">申个人/单位</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="sqr">{{vehPass.sqr}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">通行证类别</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" id="txzlx">{{vehPass.txzlx}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >出发地</div>
                  <div class="profile-info-value">
                    <span class="editable" id="cfd">{{vehPass.cfd}}</span>
                  </div>
                  <div class="profile-info-name" >目的地</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="mdd">{{vehPass.mdd}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >通行开始时间</div>
                  <div class="profile-info-value">
                    <span class="editable" id="yxqkssj">{{vehPass.yxqkssj}}</span>
                  </div>
                  <div class="profile-info-name" >通行结束时间</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="yxqjssj">{{vehPass.yxqjssj}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >号牌种类</div>
                  <div class="profile-info-value">
                    <span class="editable" id="hpzl">{{hpzl|optionMapKV(vehPass.hpzl)}}</span>
                  </div>
                  <div class="profile-info-name" >号牌号码</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="hphm">{{vehPass.hphm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >驾驶员姓名</div>
                  <div class="profile-info-value">
                    <span class="editable" id="syr">{{vehPass.syr}}</span>
                  </div>
                  <div class="profile-info-name" >驾驶员联系电话</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="lxdh">{{vehPass.lxdh}}</span>
                  </div>
                </div>

              </div>
              <showpic v-bind:pics="pics"></showpic>
              <h6 class="header blue lighter less-margin">通行路线</h6>
              <template v-for="(tt, key) in passline">
                <h6 class="header blue lighter less-margin">{{xzqh|optionMapKV(key)}}</h6>
                <label :key="value" v-for="(value, key) in tt">
                  <input name="form-field-checkbox" type="checkbox" class="ace" v-bind:value="value" v-model="txxl"/>
                  <span class="lbl"> {{value}}</span>
                </label>
              </template>

              <h6 class="header blue lighter less-margin">审核信息</h6>

              <div class="form-group">
                <label class="col-sm-2 control-label">审核结果</label>
                <div class="col-sm-8">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace" value="2" v-model="vehPass.zt"/>
                      <span class="lbl">审核通过</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace" value="3" v-model="vehPass.zt"/>
                      <span class="lbl">审核不通过</span>
                    </label>
                  </div>

                </div>
              </div>

              <div class="form-group" v-if="vehPass.zt=='3'">
                <label class="col-sm-2 control-label">审核说明</label>
                <div class="col-sm-8">
                  <input v-model="vehPass.shsm" class="form-control">
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
  import Showpic from "../../components/showpic";
  export default {
    components: {Pagination, Times, ToUpper,Showpic},
    name: "wxbase-vehPass",
    data: function () {
      return {
        vehPass: {},
        vehPassDto: {},
        passline: {},
        vehPasss: [],
        maps: [],
        txxl: [],
        usersMap: [],
        xzqh:[],
        hpzl:[],
        pics:[],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getXzqh();
      _this.getHpzl();
      _this.getPassLine();
      _this.maps = Tool.getDeptUser() ;
      _this.usersMap = Tool.getUserCode();
      _this.$forceUpdate();

    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.vehPassDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehPassDto.etime = rep;
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
       * 获取通行线路
       */
      getPassLine(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/vehPassline/getPassLine', {
        }).then((response)=>{
          let resp = response.data;
          _this.passline = resp.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehPass = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehPass) {
        let _this = this;
        _this.vehPass = $.extend({}, vehPass);
        _this.vehPass.zt = "2";
        _this.$forceUpdate();
        /**
         * 获取当前信息的图片
         */
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehPass/getpicById',  _this.vehPass).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.pics = resp.content;
        })

        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.vehPassDto.page=page;
        _this.vehPassDto.size = _this.$refs.pagination.size;
        _this.vehPassDto.zt = "1";
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehPass/vehPassCheck',  _this.vehPassDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehPasss = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;
        _this.vehPass.checktxxl = _this.txxl;
        _this.$forceUpdate();
        // 保存校验
        if (1 != 1
                || !Validator.length(_this.vehPass.createBy, "创建人", 1, 50)
                || !Validator.length(_this.vehPass.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.vehPass.sysOrgCode, "处理部门", 1, 64)
                || !Validator.length(_this.vehPass.sqr, "个人/单位", 1, 256)
                || !Validator.length(_this.vehPass.txzlx, "通行证类型", 1, 32)
                || !Validator.length(_this.vehPass.cfd, "出发地", 1, 128)
                || !Validator.length(_this.vehPass.mdd, "目的地", 1, 128)
                || !Validator.length(_this.vehPass.hpzl, "号牌种类", 1, 2)
                || !Validator.length(_this.vehPass.hphm, "号牌号码", 1, 32)
                || !Validator.length(_this.vehPass.syr, "所有人", 1, 128)
                || !Validator.length(_this.vehPass.lxdh, "联系电话", 1, 20)
                || !Validator.length(_this.vehPass.sqsm, "申请说明", 1, 500)
                || !Validator.length(_this.vehPass.txxl, "通行线路", 1, 4000)
                || !Validator.length(_this.vehPass.zt, "", 1, 2)
                || !Validator.length(_this.vehPass.shr, "审核人", 1, 128)
                || !Validator.length(_this.vehPass.shsm, "审核说明", 1, 256)
                || !Validator.length(_this.vehPass.openid, "微信用户id", 1, 32)
                || !Validator.length(_this.vehPass.lsh, "流水号", 1, 32)
                || !Validator.length(_this.vehPass.bz, "备注", 1, 2000)
        ) {
          return;
        }
        if(_this.vehPass.zt=="2"){
          if(!Validator.require(_this.vehPass.checktxxl,"通行线路")){
            return;
          }
        }
        if(_this.vehPass.zt=="3"){
          if(!Validator.require(_this.vehPass.shsm,"审核说明")){
            return;
          }
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehPass/save', _this.vehPass).then((response)=>{
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
        Confirm.show("删除通行证表后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehPass/delete/' + id).then((response)=>{
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