<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">业务预约数量统计查询</h4>
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
                      </select>
                    </td>
                    <td style="width: 10%" v-show="chooseType=='1'">
                      <input type="text" id="form-field-1"  class="form-control"/>
                    </td>
                    <td style="width: 8%" v-show="chooseType=='2'">
                      <input type="text" id="form-field-2"  class="form-control"/>
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

    <div v-show="showTable" style="height: 500px;overflow-y: auto;overflow-x: hidden" v-if="chooseType=='1'">
      <div style="font-size: large;line-height: 60px;text-align: center;font-weight: 600">{{title}}
        业务预约数量查询统计
      </div>
      <table id="simple-table" class="table  table-bordered table-hover" style="width: 100%">
        <thead>
        <tr>
          <th rowspan="2"></th>
          <th colspan="3">预约数</th>
          <th colspan="3">到场数</th>
        </tr>
        <tr>
          <th>今年预约数</th>
          <th>去年同期</th>
          <th>同比增长</th>
          <th>今年到场数</th>
          <th>去年同期</th>
          <th>同比增长</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in lists">
          <td>{{maps|optionMapKV(item.deptcode)}}</td>
          <td>{{item.curNfYysl}}</td>
          <td>{{item.beforeNfYysl}}</td>
          <td>{{item.tbzzYysl}}</td>
          <td>{{item.curNfDcsl}}</td>
          <td>{{item.beforeNfDcsl}}</td>
          <td>{{item.tbzzDcsl}}</td>
        </tr>
        <tr>
          <td>合计</td>
          <td>{{listzj.curNfYysl}}</td>
          <td>{{listzj.beforeNfYysl}}</td>
          <td>{{listzj.tbzzYysl}}</td>
          <td>{{listzj.curNfDcsl}}</td>
          <td>{{listzj.beforeNfDcsl}}</td>
          <td>{{listzj.tbzzDcsl}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-show="showTable" style="height: 500px;overflow-y: auto;overflow-x: hidden" v-if="chooseType=='2'">
      <div style="font-size: large;line-height: 60px;text-align: center;font-weight: 600">{{title}}
        业务预约数量查询统计
      </div>
      <table id="simple-table1" class="table  table-bordered table-hover" style="width: 100%">
        <thead>
        <tr>
          <th rowspan="2"></th>
          <th colspan="3">预约数</th>
          <th colspan="3">到场数</th>
        </tr>
        <tr>
          <th>本月预约数</th>
          <th>上月同期</th>
          <th>环比增长</th>
          <th>本月到场数</th>
          <th>上月同期</th>
          <th>环比增长</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in lists">
          <td>{{maps|optionMapKV(item.deptcode)}}</td>
          <td>{{item.curYfYysl}}</td>
          <td>{{item.beforeYfYysl}}</td>
          <td>{{item.hbzzYysl}}</td>
          <td>{{item.curYfDcsl}}</td>
          <td>{{item.beforeYfDcsl}}</td>
          <td>{{item.hbzzDcsl}}</td>
        </tr>
        <tr>
          <td>合计</td>
          <td>{{listzj.curYfYysl}}</td>
          <td>{{listzj.beforeYfYysl}}</td>
          <td>{{listzj.hbzzYysl}}</td>
          <td>{{listzj.curYfDcsl}}</td>
          <td>{{listzj.beforeYfDcsl}}</td>
          <td>{{listzj.hbzzDcsl}}</td>
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
      chooseType:'2',
      showTable:false,
      maps: [],
      lists:[],
      listzj: {}
    }
  },
  mounted: function() {
    let _this = this;
    let year = new Date().getFullYear();
    let month = new Date().getMonth()
    if(month==0){
      year = year-1;
      month=12;
    }
    $("#form-field-1").datetimepicker({
      format: 'YYYY',//显示格式
      locale: moment.locale('zh-cn')
    })
    $("#form-field-2").datetimepicker({
      format: 'YYYY-MM',//显示格式
      locale: moment.locale('zh-cn'),
      defaultDate:year+"-"+month
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
      _this.wxYyStatisticsDto.chooseType = _this.chooseType;
      if(_this.chooseType=='1'){
        _this.wxYyStatisticsDto.nf = $("#form-field-1").val();
        _this.title = $("#form-field-1").val()+"年";
      }else if(_this.chooseType=='2'){
        let dates = $("#form-field-2").val().split("-");
        _this.wxYyStatisticsDto.nf = dates[0];
        _this.wxYyStatisticsDto.yf = dates[1];
        _this.title = dates[0]+"年"+dates[1]+"月";
      }
      _this.$forceUpdate();
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxYyStatistics/getYyslCountStatistics', _this.wxYyStatisticsDto).then((response)=>{
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
      let paramsStr = "deptcode="+loginUser.deptcode+"&chooseType="+_this.chooseType;
      if(_this.chooseType=='1'){
        let nf = $("#form-field-1").val();
        paramsStr = paramsStr+ "&nf="+nf;
      }else if(_this.chooseType=='2'){
        let dates = $("#form-field-2").val().split("-");
        let nf = dates[0];
        let yf = dates[1];
        paramsStr = paramsStr+"&nf="+nf+"&yf="+yf;
      }
      let url = process.env.VUE_APP_SERVER + '/wxbase/export/wxYyStatistics/exportWxYwYyStatistics?'+paramsStr;
      console.log(url);
      window.location.href = url;
    }
  }
}
</script>