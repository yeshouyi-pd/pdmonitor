<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">违法处理业务预约统计查询</h4>
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
                    <td style="width:8%" >
                      <select class="form-control" v-model="chooseType">
                        <option value="1">按年统计</option>
                        <option value="2">按月统计</option>
                        <option value="3">按日期统计</option>
                      </select>
                    </td>
                    <td style="width: 10%" v-show="chooseType=='1'">
                      <input type="text" id="form-field-1"  class="form-control"/>
                    </td>
                    <td style="width: 8%" v-show="chooseType=='2'">
                      <input type="text" id="form-field-2"  class="form-control"/>
                    </td>
                    <td style="width: 8%" v-show="chooseType=='3'">
                      <input type="text" id="form-field-3"  class="form-control"/>
                    </td>
                    <td style="width: 11%;text-align: left;color: #ccc">(按照预约日期进行统计)</td>
                    <td colspan="2" class="text-center">
                      <button  type="button" v-on:click="list()" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round">
                        <i class="ace-icon fa fa-refresh"></i>
                        重置
                      </a>
                    </td>
                    <td colspan="2" class="text-center">
                      <button  type="button" v-on:click="exportAll()" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        导出
                      </button>
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

    <div v-show="showTable" style="height: 500px;overflow-y: auto;overflow-x: hidden">
      <div style="font-size: large;line-height: 60px;text-align: center;font-weight: 600">{{title}}
        违法处理业务预约查询统计
      </div>
      <table id="simple-table" class="table  table-bordered table-hover" style="width: 100%">
        <thead>
        <tr>
          <th></th>
          <th>预约总数合计</th>
          <th>机动车违法处理</th>
          <th>电动车违法处理</th>
          <th>到场数</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in lists">
          <td>{{maps|optionMapKV(item.deptcode)}}</td>
          <td>{{item.sumwfjdc+item.sumwfddc}}</td>
          <td>{{item.sumwfjdc}}</td>
          <td>{{item.sumwfddc}}</td>
          <td>{{item.sumwfdcs}}</td>
        </tr>
        <tr>
          <td>合计</td>
          <td>{{listzj.sumwfjdc+listzj.sumwfddc}}</td>
          <td>{{listzj.sumwfjdc}}</td>
          <td>{{listzj.sumwfddc}}</td>
          <td>{{listzj.sumwfdcs}}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Pagination from "../../components/pagination";
export default {
  components: {Pagination},
  name: "wxbase-wxYyStatistics",
  data: function() {
    return {
      title: '',
      wxYyStatisticsDto:{},
      chooseType:'3',
      showTable:false,
      maps: [],
      lists:[],
      listzj: {}
    }
  },
  mounted: function() {
    let _this = this;
    $("#form-field-1").datetimepicker({
      format: 'YYYY',//显示格式
      locale: moment.locale('zh-cn')
    })
    $("#form-field-2").datetimepicker({
      format: 'YYYY-MM',//显示格式
      locale: moment.locale('zh-cn')
    })
    $("#form-field-3").datetimepicker({
      format: 'YYYY-MM-DD',//显示格式
      locale: moment.locale('zh-cn'),
      defaultDate:new Date().getFullYear()+"-" + (new Date().getMonth()+1) + "-" + (new Date().getDate()-1)
    })
    _this.list();
    _this.maps = Tool.getDeptUser();
  },
  methods: {
    /**
     * 列表查询
     */
    list() {
      let _this = this;
      _this.wxYyStatisticsDto={};
      _this.wxYyStatisticsDto.ywflList = ['5'];
      _this.wxYyStatisticsDto.chooseType = _this.chooseType;
      if(_this.chooseType=='1'){
        _this.wxYyStatisticsDto.nf = $("#form-field-1").val();
        _this.title = $("#form-field-1").val()+"年";
      }else if(_this.chooseType=='2'){
        let dates = $("#form-field-2").val().split("-");
        _this.wxYyStatisticsDto.nf = dates[0];
        _this.wxYyStatisticsDto.yf = dates[1];
        _this.title = dates[0]+"年"+dates[1]+"月";
      }else if(_this.chooseType=='3'){
        _this.wxYyStatisticsDto.rq = $("#form-field-3").val();
        let dates = $("#form-field-3").val().split("-");
        _this.title = dates[0]+"年"+dates[1]+"月"+dates[2];
      }
      _this.$forceUpdate();
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxYyStatistics/getStatisticsContent', _this.wxYyStatisticsDto).then((response)=>{
        Loading.hide();
        let resp = response.data.content;
        _this.lists = resp.mx;
        _this.listzj = resp.zj;
        _this.showTable = true;
      })
    },
    exportAll(){
      let _this = this;
      let loginUser = Tool.getLoginUser();
      console.log(loginUser);
      let paramsStr = "deptcode="+loginUser.deptcode+"&ywflListStr=5";
      if(_this.chooseType=='1'){
        let nf = $("#form-field-1").val();
        _this.title = $("#form-field-1").val()+"年 违法处理业务预约查询统计";
        paramsStr = paramsStr+ "&nf="+nf+"&title="+_this.title;
      }else if(_this.chooseType=='2'){
        let yf = $("#form-field-2").val();
        let dates = $("#form-field-2").val().split("-");
        _this.title = dates[0]+"年"+dates[1]+"月 违法处理业务预约查询统计";
        paramsStr = paramsStr+ "&yf="+yf+"&title="+_this.title;
      }else if(_this.chooseType=='3'){
        let rq = $("#form-field-3").val();
        let dates = $("#form-field-3").val().split("-");
        _this.title = dates[0]+"年"+dates[1]+"月"+dates[2]+" 违法处理业务预约查询统计";
        paramsStr = paramsStr+ "&rq="+rq+"&title="+_this.title;
      }
      let url = process.env.VUE_APP_SERVER + '/wxbase/export/wxYyStatistics/exportWxYyStatisticsByWfcl?'+paramsStr;
      console.log(url);
      window.location.href = url;
    }
  }
}
</script>