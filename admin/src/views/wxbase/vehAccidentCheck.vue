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
              <button v-on:click="edit(vehAccident)" class="btn btn-xs btn-info" title="审核">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
              </button>
              <button v-on:click="detail(vehAccident)" class="btn btn-xs btn-info" title="详情">
                <i class="ace-icon fa fa-list bigger-120"></i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">审核</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">事故图片</h6>
              <showpic v-bind:pics="pics"></showpic>
              <h6 class="header blue lighter less-margin">审核信息</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核结果</label>
                <div class="col-sm-10">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="1" v-model="vehAccident.shsm"/>
                      <span class="lbl">审核通过</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="vehAccident.shsm"/>
                      <span class="lbl">审核不通过</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group" v-show="vehAccident.shsm=='2'">
                <label class="col-sm-2 control-label">审核原因</label>
                <div class="col-sm-8">
                  <input v-model="vehAccident.shyy" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-8">
                  <input v-model="vehAccident.bz" class="form-control">
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
  name: "wxbase-vehAccidentCheck",
  data: function() {
    return {
      vehAccident: {},
      vehAccidents: [],
      vehAccidentDto:{},
      pics:[],
      sgqx:[],
      maps: []
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("wxbase-vehAccident-sidebar");
    _this.maps = Tool.getDeptUser() ;
    _this.getSgqx();
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
     * 点击【详情】
     */
    detail(vehAccident) {
      let _this = this;
      SessionStorage.set(SESSION_KEY_ACCIDENT,vehAccident); //写入缓存
      _this.$router.push({
        path:"/wxbase/vehAccidentDetail",
        query:{
          type:'check'
        }
      });
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      _this.vehAccidentDto.zt='0';
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
     * 审核
     */
    edit(vehAccident){
      let _this = this;
      _this.getFileInfo(vehAccident.lsh);
      _this.vehAccident = $.extend({}, vehAccident);
      _this.vehAccident.shsm = "1";
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

    save(){
      let _this = this;
      if(_this.vehAccident.shsm=='2'&&!Validator.require(_this.vehAccident.shyy, "审核原因")){
        return;
      }
      // 保存校验
      if (1 != 1
          || !Validator.length(_this.vehAccident.shsm, "审核结果", 1, 128)
          || !Validator.length(_this.vehAccident.shyy, "审核原因", 1, 2000)
          || !Validator.length(_this.vehAccident.bz, "状态", 1, 1)
          || !Validator.require(_this.vehAccident.shsm,"审核结果")
      ) {
        return;
      }
      _this.vehAccident.zt='1';
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
     * 获取事故情形
     */
    getSgqx(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getSgqx', {
      }).then((response)=>{
        let resp = response.data;
        _this.sgqx = resp.content;
      })
    }
  }
}
</script>