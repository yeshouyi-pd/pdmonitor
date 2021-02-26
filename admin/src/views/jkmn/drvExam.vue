<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">真题管理查询</h4>
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
                    <td style="width: 8%" >
                      考试类型：
                    </td>
                    <td style="width: 10%">
                      <select v-model="drvExamDto.kskm" class="form-control" id="form-field-select-1">
                        <option value="" selected>请选择</option>
                        <option value="1">电动车</option>
                        <option value="2">摩托车</option>
                      </select>
                    </td>
                    <td style="width: 8%" >
                      答题类别：
                    </td>
                    <td style="width: 10%">
                      <select v-model="drvExamDto.dtlb" class="form-control" id="form-field-select-2">
                        <option value="" selected>请选择</option>
                        <option value="1">单选</option>
                        <option value="2">多选</option>
                      </select>
                    </td>
                    <td style="width:8%" >
                      考试题目标题：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-1"  v-model="drvExamDto.tmbt"/>
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
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
    </p>


    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th class="col-xs-1">考试类型</th>
            <th class="col-xs-1">答题类别</th>
            <th>考试题目标题</th>
            <th class="col-xs-1">准确答案</th>
<!--            <th>答题说明</th>-->
            <th class="col-xs-1">操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="drvExam in drvExams">
              <td class="col-xs-1"><span v-if="drvExam.kskm =='1'">电动车</span><span v-else-if="drvExam.kskm =='2'">摩托车</span></td>
              <td class="col-xs-1"><span v-if="drvExam.dtlb =='1'">单选</span><span v-else-if="drvExam.dtlb =='2'">多选</span></td>
              <td>{{drvExam.tmbt}}</td>
              <td class="col-xs-1">{{drvExam.zqda}}</td>
<!--              <td>{{drvExam.dtsm}}</td>-->
        <td class="col-xs-1">
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(drvExam)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(drvExam.id)" class="btn btn-xs btn-danger" title="删除">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">考试科目*</label>
                      <div class="col-sm-10">
                        <div class="radio">
                          <label>
                            <input name="drvKskm" type="radio" class="ace"  value="1" v-model="drvExam.kskm"/>
                            <span class="lbl">电动车</span>
                          </label>
                          <label>
                            <input name="drvKskm" type="radio" class="ace"  value="2" v-model="drvExam.kskm"/>
                            <span class="lbl">摩托车</span>
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">答题类别*</label>
                      <div class="col-sm-10">
<!--                        <input v-model="drvExam.dtlb" class="form-control">-->
                        <div class="radio">
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="1" v-model="drvExam.dtlb"/>
                            <span class="lbl">单选</span>
                          </label>
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="2" v-model="drvExam.dtlb"/>
                            <span class="lbl">多选</span>
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">考试题目标题*</label>
                      <div class="col-sm-10">
                        <input v-model="drvExam.tmbt" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">答案A*</label>
                      <div class="col-sm-10">
                        <input v-model="drvExam.a" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">答案B*</label>
                      <div class="col-sm-10">
                        <input v-model="drvExam.b" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">答案C</label>
                      <div class="col-sm-10">
                        <input v-model="drvExam.c" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">答案D</label>
                      <div class="col-sm-10">
                        <input v-model="drvExam.d" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">图片1</label>
                      <div class="col-sm-10">
<!--                        <input v-model="drvExam.tp1" class="form-control">-->
                        <show-pic :pics="tp1Lj"></show-pic>
                        <file input-id="drvExamTp1" :suffixs="picSuffixs" :afterUpload="tp1AfterUpload"></file>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">图片2</label>
                      <div class="col-sm-10">
<!--                        <input v-model="drvExam.tp2" class="form-control">-->
                        <show-pic :pics="tp2Lj"></show-pic>
                        <file input-id="drvExamTp2" :suffixs="picSuffixs" :afterUpload="tp2AfterUpload"></file>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">准确答案*</label>
                      <div class="col-sm-10">
                        <input v-model="drvExam.zqda" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">答题说明</label>
                      <div class="col-sm-10">
                        <input v-model="drvExam.dtsm" class="form-control">
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
  import File from "../../components/file";
  import ShowPic from "../../components/showpic";
  export default {
    components: {Pagination,File,ShowPic},
    name: "wxbase-drvExam",
    data: function() {
      return {
        drvExam: {},
        drvExams: [],
        drvExamDto: {},
        picSuffixs:['png','jpg','gif','jpeg'],
        tp1Lj:[],
        tp2Lj:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-drvExam-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.tp1Lj = [];
        _this.tp2Lj = [];
        _this.drvExam = {};
        _this.drvExam.kskm=1;
        _this.drvExam.dtlb=1;
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(drvExam) {
        let _this = this;
        _this.tp1Lj = [];
        _this.tp2Lj = [];
        if(drvExam.tp1){
          let tp1LjList = drvExam.tp1.split(",");
          for(let i=0;i<tp1LjList.length;i++){
            let obj = {'zplj': tp1LjList[i]};
            _this.tp1Lj.push(obj);
          }
        }
        if(drvExam.tp2){
          let tp2LjList = drvExam.tp2.split(",");
          for(let i=0;i<tp2LjList.length;i++){
            let obj = {'zplj':tp2LjList[i]};
            _this.tp2Lj.push(obj);
          }
        }
        _this.drvExam = $.extend({}, drvExam);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.drvExamDto.page=page;
        _this.drvExamDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/drvExam/list', _this.drvExamDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.drvExams = resp.content.list;
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
                || !Validator.length(_this.drvExam.kskm, "电动车摩托车网上学习", 1, 10)
                || !Validator.length(_this.drvExam.dtlb, "答题类别1单选2多选", 1, 45)
                || !Validator.require(_this.drvExam.tmbt, "考试题目标题")
                || !Validator.length(_this.drvExam.tmbt, "考试题目标题", 1, 3000)
                || !Validator.require(_this.drvExam.a, "答案A")
                || !Validator.length(_this.drvExam.a, "答案A", 1, 200)
                || !Validator.require(_this.drvExam.b, "答案B")
                || !Validator.length(_this.drvExam.b, "答案B", 1, 200)
                || !Validator.length(_this.drvExam.c, "答案C", 1, 200)
                || !Validator.length(_this.drvExam.d, "答案D", 1, 200)
                || !Validator.length(_this.drvExam.tp1, "图片1", 1, 200)
                || !Validator.length(_this.drvExam.tp2, "图片2", 1, 200)
                || !Validator.length(_this.drvExam.zqda, "准确答案", 1, 1000)
                || !Validator.length(_this.drvExam.dtsm, "答题说明", 1, 1000)
                || !Validator.require(_this.drvExam.zqda, "准确答案")
        ) {
          return;
        }

        Loading.show();
        let tp1 = "";
        let tp2= "";
        for(let i=0;i<_this.tp1Lj.length;i++){
          tp1 = tp1 + this.tp1Lj[i].zplj + ",";
        }
        for(let i=0;i<_this.tp2Lj.length;i++){
          tp2 = tp2 + this.tp2Lj[i].zplj + ",";
        }
        _this.drvExam.tp1 = tp1.substring(0,tp1.length-1);
        _this.drvExam.tp2 = tp2.substring(0,tp2.length-1);
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/drvExam/save', _this.drvExam).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/drvExam/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      },

      tp1AfterUpload(res){
        let _this = this;
        if(res && res.success){
          _this.tp1Lj = [];
          let obj = {
            'zplj':res.content
          };
          _this.tp1Lj.push(obj);
        }else{
          Toast.error(res.content);
        }
      },

      tp2AfterUpload(res){
        let _this = this;
        if(res && res.success){
          _this.tp2Lj = [];
          let obj = {
            'zplj':res.content
          };
          _this.tp2Lj.push(obj);
        }else{
          Toast.error(res.content);
        }
      },
    }
  }
</script>