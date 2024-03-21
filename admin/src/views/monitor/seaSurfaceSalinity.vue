<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">海表盐度图片查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  图片日期：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="tpstime" end-id="tpetime"></times>
                </td>
                <td style="width: 20%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
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

    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
    </p>

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>图片日期</th>
          <th>海表盐度图片</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in seaSurfaceSalinitys">
          <td>{{item.tprq}}</td>
          <td><img @click="pic(item)" :src="item.imgUrl" width="80px;" height="60px;" /></td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(item)" class="btn btn-xs btn-info" title="修改">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
              </button>
              <button v-on:click="del(item.id)" class="btn btn-xs btn-danger" title="删除">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" >
        <div class="modal-content" style="width:650px;">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">新增</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-3 control-label">图片日期</label>
                <div class="col-sm-9">
                  <Dategreater @methodName="tpDate" style="width:100%" idValue="tprq" :setValue="seaSurfaceSalinity.tprq"></Dategreater>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label">海表盐度图片</label>
                <div class="col-sm-9">
<!--                  <input v-model="imgUrl" disabled class="form-control" style="width: 400px;float: left;margin-right: 10px;">-->
                  <img v-if="imgUrl" :src="imgUrl" width="150px;" height="120px;" />
                  <file input-id="sm1" :suffixs="picSuffixs" :afterUpload="fmljAfterUpload"></file>
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

    <div id="form-modal1" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 50%">
        <div class="modal-content" style="width: 100%;margin: auto">
          <div class="modal-body">
            <img :src="tempUrl"/>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>
<script>
import Pagination from "@/components/pagination";
import Times from "@/components/times";
import Dategreater from "@/components/dategreater";
import File from "@/components/file";

export default {
  components: {Pagination,Times,Dategreater,File},
  name: 'sea-surface-salinity',
  data: function (){
    return {
      seaSurfaceSalinity:{},
      seaSurfaceSalinityDto:{},
      seaSurfaceSalinitys:[],
      picSuffixs:['png','jpg','gif'],
      imgUrl:'',
      tempUrl:''
    }
  },
  mounted() {
    let _this = this;
    _this.list(1);
    _this.$refs.pagination.size = 10;
  },
  methods: {
    fmljAfterUpload(res){
      let _this = this;
      if(res && res.success){
        _this.imgUrl = process.env.VUE_APP_SERVER + res.content;
        _this.seaSurfaceSalinity.imgUrl = _this.imgUrl;
      }else{
        Toast.error(res.content);
      }
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.seaSurfaceSalinityDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.seaSurfaceSalinityDto.etime = rep;
      _this.$forceUpdate();
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.seaSurfaceSalinityDto.page=page;
      _this.seaSurfaceSalinityDto.size=_this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/seaSurfaceSalinity/list', _this.seaSurfaceSalinityDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.seaSurfaceSalinitys = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    pic(item) {
      let _this = this;
      _this.tempUrl = item.imgUrl;
      $("#form-modal1").modal("show");
    },
    //新增
    add(){
      let _this = this;
      _this.seaSurfaceSalinity = {};
      $("#form-modal").modal("show");
    },
    tpDate(rep){
      let _this = this;
      _this.seaSurfaceSalinity.tprq = rep;
      _this.$forceUpdate();
    },
    //保存
    save(){
      let _this = this;
      // 保存校验
      if (1 != 1
          || !Validator.require(_this.seaSurfaceSalinity.tprq, "图片日期")
          || !Validator.require(_this.seaSurfaceSalinity.imgUrl, "海表盐度图片")
      ) {
        return;
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/seaSurfaceSalinity/save', _this.seaSurfaceSalinity).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("保存成功！");
        } else {
          Toast.warning(resp.message);
        }
      })
    },
    //修改
    edit(item){
      let _this = this;
      _this.seaSurfaceSalinity = $.extend({}, item);
      _this.$forceUpdate();
      $("#form-modal").modal("show");
    },
    //删除
    del(id){
      let _this = this;
      Confirm.show("删除后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/seaSurfaceSalinity/delete/' + id).then((response)=>{
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