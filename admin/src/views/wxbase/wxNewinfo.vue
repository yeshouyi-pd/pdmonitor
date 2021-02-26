<template>
  <div>
      <div class="row">
          <div class="col-md-12">
              <!-- query start -->
              <div class="widget-box">
                  <div class="widget-header">
                      <h4 class="widget-title">信息查询</h4>
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
                                      <td style="width:6%" >
                                          信息类别：
                                      </td>
                                      <td style="width: 10%">
                                          <select v-model="wxNewinfoDto.xxlb" class="form-control">
                                              <option value="" selected>请选择</option>
                                              <option v-for="(value, key) in newtype" v-bind:value="key">{{value}}</option>
                                          </select>
                                      </td>
                                      <td style="width: 6%" >
                                          状态：
                                      </td>
                                      <td style="width: 10%">
                                          <select v-model="wxNewinfoDto.zt" class="form-control">
                                              <option value="" selected>请选择</option>
                                              <option value="0">未审核</option>
                                              <option value="1">审核通过</option>
                                              <option value="2">审核不通过</option>
                                          </select>
                                      </td>
                                      <td  style="width: 6%">
                                          创建时间：
                                      </td>
                                      <td style="width: 18%">
                                          <times v-bind:startTime="startTime"
                                                 v-bind:endTime="endTime"></times>
                                      </td>
                                      <td class="text-center">
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
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>序号</th>
<!--            <th>流水号</th>-->
            <th>标题</th>
<!--            <th width="30%">信息内容</th>-->
            <th>关键字</th>
            <th>信息类别</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>所属部门</th>
<!--            <th>更新人</th>-->
<!--            <th>更新时间</th>-->
            <th>状态</th>
            <th width="5%">操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="(wxNewinfo,index) in wxNewinfos">
              <td>{{index+1}}</td>
<!--              <td>{{wxNewinfo.lsh}}</td>-->
              <td>{{wxNewinfo.title}}</td>
<!--              <td>{{wxNewinfo.content}}</td>-->
              <td>{{wxNewinfo.gjz}}</td>
              <td>{{newtype|optionMapKV(wxNewinfo.xxlb)}}</td>
              <td>{{wxNewinfo.createBy}}</td>
              <td>{{wxNewinfo.createTime | formatDateTime}}</td>
              <td>{{maps|optionMapKV(wxNewinfo.sysOrgCode)}}</td>
<!--              <td>{{wxNewinfo.updateBy}}</td>-->
<!--              <td>{{wxNewinfo.updateTime | formatDateTime}}</td>-->
              <td>
                  <span v-if="wxNewinfo.zt =='0'">未审核</span>
                  <span v-else-if="wxNewinfo.zt =='1'">审核通过</span>
                  <span v-else-if="wxNewinfo.zt =='2'">审核不通过</span>
              </td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(wxNewinfo)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(wxNewinfo.id)" class="btn btn-xs btn-danger" title="删除" v-if="wxNewinfo.zt=='0'">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 1200px;">
        <div class="modal-content" style="width: 1200px;">
          <div class="modal-header" style="width: 1200px;">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">信息发布</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">标题</label>
                      <div class="col-sm-10">
                        <input v-model="wxNewinfo.title" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">信息内容</label>
                      <div class="col-sm-10">
<!--                          <textarea v-model="wxNewinfo.content" rows="8" cols="63"  placeholder=""></textarea>-->
                          <!--<script id="editor" type="text/plain" style="width:100%;height:400px;"></script>-->
<!--                          <script id="container" name="content" type="text/plain"></script>-->
                          <vue-ueditor-wrap v-model="wxNewinfo.content" :config="myConfig"></vue-ueditor-wrap>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">关键字</label>
                      <div class="col-sm-10">
                        <input v-model="wxNewinfo.gjz" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">信息类别</label>
                        <div class="col-sm-10">
                            <select v-model="wxNewinfo.xxlb" class="form-control">
                                <option v-for="(value, key) in newtype" v-bind:value="key">{{value}}</option>
                            </select>
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
  import VueUeditorWrap from 'vue-ueditor-wrap';
  import Times from "../../components/time";
  import ToUpper from "../../components/upperLowerTransform";
  export default {
    components: {Pagination,VueUeditorWrap, Times, ToUpper},
    name: "wxbase-wxNewinfo",
    data: function() {
      return {
          wxNewinfo: {},
          wxNewinfoDto: {},
          wxNewinfos: [],
          newtype: [],
          maps: [],
          myConfig: {
              imageActionName: 'uploadimage',
              imageFieldName: "upfile", /* 提交的图片表单名称 */
              imageMaxSize: 2048000, /* 上传大小限制，单位B */
              imageAllowFiles: [".png", ".jpg", ".jpeg", ".gif", ".bmp"], /* 上传图片格式显示 */
              imageCompressEnable: true, /* 是否压缩图片,默认是true */
              imageCompressBorder: 1600, /* 图片压缩最长边限制 */
              imageInsertAlign: "none", /* 插入的图片浮动方式 */
              imageUrlPrefix: process.env.VUE_APP_SERVER + "/wxbase/upload/readImg", /* 图片访问路径前缀 */
              imagePathFormat: "/ueditor/php/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}",
              autoHeightEnabled: false,
              autoFloatEnabled: false,
              initialFrameHeight: 300,
              initialFrameWidth: '100%',
              enableAutoSave: true,
          },
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getNewtype();
      _this.maps=Tool.getDeptUser() ;
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-wxNewinfo-sidebar");
    },
    methods: {
        /**
         *开始时间
         */
        startTime(rep){
            let _this = this;
            _this.wxNewinfoDto.stime = rep;
            _this.$forceUpdate();
        },
        /**
         *结束时间
         */
        endTime(rep){
            let _this = this;
            _this.wxNewinfoDto.etime = rep;
            _this.$forceUpdate();
        },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.wxNewinfo = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(wxNewinfo) {
        let _this = this;
        _this.wxNewinfo = $.extend({}, wxNewinfo);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.wxNewinfoDto.page=page;
        _this.wxNewinfoDto.size=_this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxNewinfo/list',
            _this.wxNewinfoDto
        ).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.wxNewinfos = resp.content.list;
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
                || !Validator.require(_this.wxNewinfo.title, "标题")
                || !Validator.require(_this.wxNewinfo.content, "信息类容")
                || !Validator.require(_this.wxNewinfo.xxlb, "信息类别")
                || !Validator.length(_this.wxNewinfo.createBy, "创建人", 1, 50)
                || !Validator.length(_this.wxNewinfo.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.wxNewinfo.sysOrgCode, "所属部门", 1, 64)
                || !Validator.length(_this.wxNewinfo.title, "标题", 1, 32)
                || !Validator.length(_this.wxNewinfo.gjz, "关键字", 1, 32)
                || !Validator.length(_this.wxNewinfo.xxlb, "信息类别", 1, 32)
                || !Validator.length(_this.wxNewinfo.zt, "状态", 1, 32)
                || !Validator.length(_this.wxNewinfo.shr, "审核人", 1, 32)
                || !Validator.length(_this.wxNewinfo.lsh, "流水号", 1, 32)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxNewinfo/save', _this.wxNewinfo).then((response)=>{
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
        Confirm.show("删除信息发布后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/wxNewinfo/delete/' + id).then((response)=>{
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
         * 获取信息类型
         */
        getNewtype(){
            let _this = this;
            _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getNewtype', {
            }).then((response)=>{
                let resp = response.data;
                _this.newtype = resp.content;
            })
        },

    }
  }
</script>

<style>
    .modal-backdrop {
        /*filter: alpha(opacity=0)!important;
        opacity: 0!important;*/
    }
</style>