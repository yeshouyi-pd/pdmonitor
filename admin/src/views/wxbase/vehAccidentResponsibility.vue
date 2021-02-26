<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">事故管理查询</h4>
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
                  <tbody>
                  <tr>
                    <td style="width:8%" >
                      事故编号：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-2"  class="input-sm"  v-model="vehAccidentDto.sgbh"/>
                    </td>
                    <td  style="width: 8%">
                      事故发生时间：
                    </td>
                    <td style="width: 15%" >
                      <times v-bind:startTime="startTime"
                             v-bind:endTime="endTime"></times>
                    </td>
                    <td style="width:8%" >
                      是否高架/高速：
                    </td>
                    <td style="width: 8%">
                      <select v-model="vehAccidentDto.sfgj" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                      </select>
                    </td>
                    <td style="width:8%" >
                      处理方式：
                    </td>
                    <td style="width: 8%">
                      <select v-model="vehAccidentDto.clfs" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option value="1">自行商定</option>
                        <option value="2">远程定责</option>
                      </select>
                    </td>
                    <td colspan="2" class="text-center">
                      <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
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
        <th>事故编号</th>
        <th class="col-sm-1">事故发生时间</th>
        <th>事故地点</th>
        <th>事故情形</th>
        <th>行政区划</th>
        <th class="col-sm-1">管理部门</th>
        <th class="col-sm-1">是否高架/高速</th>
        <th class="col-sm-1">处理方式</th>
        <th class="col-sm-1">状态</th>
        <th class="col-sm-1">操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="vehAccident in vehAccidents">
        <td>{{vehAccident.sgbh}}</td>
        <td class="col-sm-1">{{vehAccident.sgfssj}}</td>
        <td>{{vehAccident.sgdd}}</td>
        <td>{{sgqx|optionMapKV(vehAccident.sgqx)}}</td>
        <td>{{vehAccident.xzqh}}</td>
        <td class="col-sm-1">{{maps|optionMapKV(vehAccident.glbm)}}</td>
        <td class="col-sm-1"><span v-if="vehAccident.sfgj=='1'">是</span><span v-if="vehAccident.sfgj=='2'">否</span></td>
        <td class="col-sm-1"><span v-if="vehAccident.clfs=='1'">自行商定</span><span v-if="vehAccident.clfs=='2'">远程定责</span></td>
        <td class="col-sm-1"><span v-if="vehAccident.zt=='0'">未处理</span><span v-if="vehAccident.zt=='1'">已审核</span><span v-if="vehAccident.zt=='2'">已定责</span></td>
        <td class="col-sm-1">
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehAccident)" class="btn btn-xs btn-info" title="定责">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
<!--            <button v-on:click="detail(vehAccident)" class="btn btn-xs btn-info">-->
<!--              <i class="ace-icon fa fa-list bigger-120"></i>-->
<!--            </button>-->
          </div>
        </td>
      </tr>
      </tbody>
    </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width:80%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">事故图片</h6>
              <showpic v-bind:pics="pics"></showpic>
              <h6 class="header blue lighter less-margin">事故信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">流水号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.lsh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">事故发生时间</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.sgfssj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">事故地点</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.sgdd}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">事故情形</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{sgqx|optionMapKV(vehAccident.sgqx)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">行政区划</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.xzqh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">管理部门</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.glbm}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">是否高架/高速</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.sfgj=='1'">是</span>
                    <span class="editable" v-if="vehAccident.sfgj=='2'">否</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">天气</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.tq}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">处理方式</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.clfs=='1'">自行商定</span>
                    <span class="editable" v-if="vehAccident.clfs=='2'">远程定责</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">审核人</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.shrxm}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">审核原因</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.shyy}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">审核结果</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.shsm=='1'">审核通过</span>
                    <span class="editable" v-if="vehAccident.shsm=='2'">审核不通过</span>
                  </div>
                </div>
              </div>
              <h6 class="header blue lighter less-margin">当事人1信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">当事人姓名</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrxm1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">身份证号码</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrsfzmhm1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">手机号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrsjhm1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">号牌种类</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{hpzl|optionMapKV(vehAccident.hpzl1)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">号牌号码</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.hphm1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">交通方式</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrjtfs1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">车损部位</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrcsbw1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">事故责任</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.dsrbsgzr1=='1'">全责</span>
                    <span class="editable" v-if="vehAccident.dsrbsgzr1=='2'">无责</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">违法行为</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrwfxw1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">违法条款</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrwftk1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">保险公司</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{depts|optionMapKV(vehAccident.dsrbxgs1)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">保险期止</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrbxqz1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">当事人所属</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.dsrss1=='1'">甲方</span>
                    <span class="editable" v-if="vehAccident.dsrss1=='2'">已方</span>
                  </div>
                  <div class="profile-info-name"  style="width: 8%">保险凭证号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrbxpzh1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <label class="profile-info-name"  style="width: 8%">事故责任</label>
                  <div class="col-sm-10">
                    <div class="radio" v-on:change="chooseDsrsgzr1(vehAccident.dsrbsgzr1)">
                      <label>
                        <input name="form-field-radio" type="radio" class="ace"  value="1" v-model="vehAccident.dsrbsgzr1"/>
                        <span class="lbl">全责</span>
                      </label>
                      <label>
                        <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="vehAccident.dsrbsgzr1"/>
                        <span class="lbl">无责</span>
                      </label>
                    </div>
                  </div>
                </div>
              </div>
              <h6 class="header blue lighter less-margin">当事人2信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">当事人姓名</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrxm2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">身份证号码</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrsfzmhm2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">手机号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrsjhm2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">号牌种类</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{hpzl|optionMapKV(vehAccident.hpzl2)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">号牌号码</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.hphm2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">交通方式</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrjtfs2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">车损部位</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrcsbw2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">事故责任</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.dsrbsgzr2=='1'">全责</span>
                    <span class="editable" v-if="vehAccident.dsrbsgzr2=='2'">无责</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">违法行为</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrwfxw2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">违法条款</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrwftk2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">保险公司</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{depts|optionMapKV(vehAccident.dsrbxgs2)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">保险期止</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrbxqz2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">当事人所属</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.dsrss2=='1'">甲方</span>
                    <span class="editable" v-if="vehAccident.dsrss2=='2'">已方</span>
                  </div>
                  <div class="profile-info-name"  style="width: 8%">保险凭证号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrbxpzh2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <label class="profile-info-name"  style="width: 8%">事故责任</label>
                  <div class="col-sm-10">
                    <div class="radio" v-on:change="chooseDsrsgzr2(vehAccident.dsrbsgzr2)">
                      <label>
                        <input name="form-field-radio1" type="radio" class="ace"  value="1" v-model="vehAccident.dsrbsgzr2"/>
                        <span class="lbl">全责</span>
                      </label>
                      <label>
                        <input name="form-field-radio1" type="radio" class="ace"  value="2" v-model="vehAccident.dsrbsgzr2"/>
                        <span class="lbl">无责</span>
                      </label>
                    </div>
                  </div>
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
  import showpic from "@/components/showpic";
  import Times from "../../components/time";
  export default {
    components: {Pagination,showpic,Times},
    name: "wxbase-vehAccident",
    data: function() {
      return {
        vehAccident: {},
        vehAccidents: [],
        vehAccidentDto: {},
        hpzl:[],
        pics:[],
        sgqx:[],
        maps: [],
        depts:[],
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-vehAccident-sidebar");
      _this.getHpzl();
      _this.maps = Tool.getDeptUser() ;
      _this.getSgqx();
      _this.getDeptMap();
    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.vehAccidentDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehAccidentDto.etime = rep;
        _this.$forceUpdate();
      },

      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehAccident = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehAccident) {
        let _this = this;
        _this.getFileInfo(vehAccident.lsh);
        _this.vehAccident = $.extend({}, vehAccident);
        _this.vehAccident.dsrbsgzr1 = '1';
        _this.vehAccident.dsrbsgzr2 = '2';
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      getFileInfo(lsh){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatFileinfo/getFileinfoByLsh/'+ lsh).then((response)=>{
          let resp = response.data;
          _this.pics = resp.content;
        })
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.vehAccidentDto.zt='1';
        _this.vehAccidentDto.shsm='1';
        _this.vehAccidentDto.page=page;
        _this.vehAccidentDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehAccident/list', _this.vehAccidentDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehAccidents = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;
        _this.vehAccident.zt='2';
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehAccident/update', _this.vehAccident).then((response)=>{
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

      chooseDsrsgzr1(value){
        let _this = this;
        if(value=='1'){
          _this.vehAccident.dsrbsgzr2='2';
        }else{
          _this.vehAccident.dsrbsgzr2='1';
        }
        _this.$forceUpdate();
      },

      chooseDsrsgzr2(value){
        let _this = this;
        if(value=='1'){
          _this.vehAccident.dsrbsgzr1='2';
        }else{
          _this.vehAccident.dsrbsgzr1='1';
        }
        _this.$forceUpdate();
      },

      /**
       * 获取事故情形
       */
      getSgqx(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getSgqx', {
        }).then((response)=>{
          let resp = response.data;
          _this.sgqx = resp.content;
        })
      },
      /**
       * 获取保险公司
       */
      getDeptMap(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDeptMap', {
        }).then((response)=>{
          let resp = response.data;
          _this.depts = resp.content;
        })
      }
    }
  }
</script>