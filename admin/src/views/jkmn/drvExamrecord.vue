<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">答题记录查询</h4>
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
                      答题类别：
                    </td>
                    <td style="width: 10%">
                      <select v-model="drvExamrecordDto.dtlb" class="form-control" id="form-field-select-2">
                        <option value="" selected>请选择</option>
                        <option value="1">单选</option>
                        <option value="2">多选</option>
                      </select>
                    </td>
                    <td style="width:8%" >
                      考试题目标题：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-3"  v-model="drvExamrecordDto.tmbt"/>
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

    <div class="modal-header">
      <button v-on:click="back()" class="btn  btn-info btn-round">
        <i class="ace-icon fa fa-backward"></i>
        返回
      </button>
    </div>

    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th class="col-xs-1">考试类型</th>
            <th class="col-xs-1">答题类别</th>
            <th>考试题目标题</th>
            <th class="col-xs-1">准确答案</th>
            <th class="col-xs-1">选择答案</th>
            <th class="col-xs-1">操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="drvExamrecord in drvExamrecords">
              <td class="col-xs-1"><span v-if="drvExamrecord.kskm =='1'">电动车</span><span v-else-if="drvExamrecord.kskm =='2'">摩托车</span></td>
              <td class="col-xs-1"><span v-if="drvExamrecord.dtlb =='1'">单选</span><span v-else-if="drvExamrecord.dtlb =='2'">多选</span></td>
              <td>{{drvExamrecord.tmbt}}</td>
              <td class="col-xs-1">{{drvExamrecord.zqda}}</td>
              <td class="col-xs-1">{{drvExamrecord.xzda}}</td>
        <td class="col-xs-1">
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="detail(drvExamrecord)" class="btn btn-xs btn-info" title="详情">
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
      <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">答题记录详情</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">考试类型</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.kskm}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">答题类别</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.dtlb}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">考试题目标题</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.tmbt}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">答案A</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.a}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">答案B</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.b}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">答案C</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.c}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">答案D</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.d}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">图片1</div>
                  <div class="profile-info-value" style="width: 80%">
                    <show-pic :pics="tp1Lj"></show-pic>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">图片2</div>
                  <div class="profile-info-value" style="width: 80%">
                    <show-pic :pics="tp2Lj"></show-pic>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">准确答案</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.zqda}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">选择答案</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.xzda}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">微信openid</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.wxid}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">答题说明</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.dtsm}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">考题顺序</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.ktsx}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 20%">答题日志id</div>
                  <div class="profile-info-value" style="width: 80%">
                    <span class="editable">{{drvExamrecord.examlogid}}</span>
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
  import ShowPic from "../../components/showpic";
  export default {
    components: {Pagination,ShowPic},
    name: "wxbase-drvExamrecord",
    data: function() {
      return {
        drvExamrecord: {},
        drvExamrecords: [],
        drvExamrecordDto: {},
        tp1Lj:[],
        tp2Lj:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.drvExamrecordDto.examlogid=_this.$route.query.id;
      _this.$forceUpdate();
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-drvExamrecord-sidebar");

    },
    methods: {
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.drvExamrecordDto.page=page;
        _this.drvExamrecordDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/drvExamrecord/list', _this.drvExamrecordDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.drvExamrecords = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      back(){
        let _this = this;
        _this.$router.push("/jkmn/drvExamlog");
      },

      /**
       * 点击【详情】
       */
      detail(drvExamrecord) {
        let _this = this;
        _this.tp1Lj = [];
        _this.tp2Lj = [];
        if(drvExamrecord.tp1){
          let tp1LjList = drvExamrecord.tp1.split(",");
          for(let i=0;i<tp1LjList.length;i++){
            let obj = {'zplj': tp1LjList[i]};
            _this.tp1Lj.push(obj);
          }
        }
        if(drvExamrecord.tp2){
          let tp2LjList = drvExamrecord.tp2.split(",");
          for(let i=0;i<tp2LjList.length;i++){
            let obj = {'zplj':tp2LjList[i]};
            _this.tp2Lj.push(obj);
          }
        }
        _this.drvExamrecord = $.extend({}, drvExamrecord);
        $("#form-modal").modal("show");
      }
    }
  }
</script>