<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">答题日志查询</h4>
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
                      姓名：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-2"  v-model="drvExamlogDto.xm"/>
                    </td>
                    <td style="width:6%" >
                      处罚编号：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text" id="form-field-3"  v-model="drvExamlogDto.cfbh"/>
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
            <th>姓名</th>
            <th>身份证明号码</th>
            <th>手机号</th>
            <th>处罚编号</th>
            <th class="col-xs-1">考试类型</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>所得分数</th>
            <th>是否及格</th>
            <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="drvExamlog in drvExamlogs">
              <td>{{drvExamlog.xm}}</td>
              <td>{{drvExamlog.sfzmhm}}</td>
              <td>{{drvExamlog.sjh}}</td>
              <td>{{drvExamlog.cfbh}}</td>
              <td class="col-xs-1"><span v-if="drvExamlog.dtkm=='1'">电动车</span><span v-else-if="drvExamlog.dtkm=='2'">摩托车</span></td>
              <td>{{drvExamlog.kssj}}</td>
              <td>{{drvExamlog.jssj}}</td>
              <td>{{drvExamlog.sdfs}}</td>
              <td><span v-if="drvExamlog.sfjg =='1'">及格</span><span v-else-if="drvExamlog.sfjg =='0'">不及格</span></td>
              <td>
                <div class="hidden-sm hidden-xs btn-group">
                  <button v-on:click="record(drvExamlog)" class="btn btn-xs btn-info" title="答题记录">
                    <i class="ace-icon fa fa-list bigger-120"></i>
                  </button>
                </div>
              </td>
      </tr>
      </tbody>
    </table>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "wxbase-drvExamlog",
    data: function() {
      return {
        drvExamlog: {},
        drvExamlogs: [],
        drvExamlogDto:{}
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-drvExamlog-sidebar");
      // 
    },
    methods: {
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.drvExamlogDto.page=page;
        _this.drvExamlogDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/drvExamlog/list', _this.drvExamlogDto).then((response) => {
          Loading.hide();
          let resp = response.data;
          _this.drvExamlogs = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },
      record(drvExamlog){
        let _this = this;
        _this.$router.push({
          path:"/jkmn/drvExamrecord",
          query:{
            id:drvExamlog.id
          }
        });
      }
    }
  }
</script>