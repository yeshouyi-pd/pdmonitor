<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">车架管业务预约统计查询</h4>
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
        车驾管业务预约查询统计
      </div>
      <table id="simple-table" class="table  table-bordered table-hover" style="width: 100%">
        <thead>
        <tr>
          <th rowspan="2"></th>
          <th rowspan="2">预约总数合计</th>
          <th colspan="11">机动车业务预约</th>
          <th colspan="10">驾驶证业务预约</th>
        </tr>
        <tr>
          <th>小计</th>
          <th>小客车新车注册</th>
          <th>二手车过户</th>
          <th>小客车转入业务</th>
          <th>补换领机动车号牌</th>
          <th>补换领行驶证</th>
          <th>抵押/解除抵押</th>
          <th>大中型机动车注册登记</th>
          <th>大中型机动车在用车业务</th>
          <th>其他机动车业务</th>
          <th>到场数</th>
          <th>小计</th>
          <th>外地驾驶证转入</th>
          <th>期满换证</th>
          <th>补换领驾驶证</th>
          <th>军警换证</th>
          <th>境外换证</th>
          <th>满分业务</th>
          <th>审验业务</th>
          <th>其他业务</th>
          <th>到场数</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in lists">
          <td>{{maps|optionMapKV(item.deptcode)}}</td>
          <td>{{item.sumjdcxkcxczc+item.sumjdcescgh+item.sumjdcxkczr+item.sumjdcbhljdchp+item.sumjdcbhlxsz+item.sumjdcdyyw+item.sumjdcdzxzcdj+item.sumjdcdzxzycyw+item.sumjdcqtyw+item.sumjszwdzr+item.sumjszqmhz+item.sumjszbhlyw+item.sumjszjjhz+item.sumjszjwhz+item.sumjszmfyw+item.sumjszsyyw+item.sumjszqtyw}}</td>
          <td>{{item.sumjdcxkcxczc+item.sumjdcescgh+item.sumjdcxkczr+item.sumjdcbhljdchp+item.sumjdcbhlxsz+item.sumjdcdyyw+item.sumjdcdzxzcdj+item.sumjdcdzxzycyw+item.sumjdcqtyw}}</td>
          <td>{{item.sumjdcxkcxczc}}</td>
          <td>{{item.sumjdcescgh}}</td>
          <td>{{item.sumjdcxkczr}}</td>
          <td>{{item.sumjdcbhljdchp}}</td>
          <td>{{item.sumjdcbhlxsz}}</td>
          <td>{{item.sumjdcdyyw}}</td>
          <td>{{item.sumjdcdzxzcdj}}</td>
          <td>{{item.sumjdcdzxzycyw}}</td>
          <td>{{item.sumjdcqtyw}}</td>
          <td>{{item.sumjdcdcs}}</td>
          <td>{{item.sumjszwdzr+item.sumjszqmhz+item.sumjszbhlyw+item.sumjszjjhz+item.sumjszjwhz+item.sumjszmfyw+item.sumjszsyyw+item.sumjszqtyw}}</td>
          <td>{{item.sumjszwdzr}}</td>
          <td>{{item.sumjszqmhz}}</td>
          <td>{{item.sumjszbhlyw}}</td>
          <td>{{item.sumjszjjhz}}</td>
          <td>{{item.sumjszjwhz}}</td>
          <td>{{item.sumjszmfyw}}</td>
          <td>{{item.sumjszsyyw}}</td>
          <td>{{item.sumjszqtyw}}</td>
          <td>{{item.sumjszdcs}}</td>
        </tr>
        <tr>
          <td>合计</td>
          <td>{{listzj.sumjdcxkcxczc+listzj.sumjdcescgh+listzj.sumjdcxkczr+listzj.sumjdcbhljdchp+listzj.sumjdcbhlxsz+listzj.sumjdcdyyw+listzj.sumjdcdzxzcdj+listzj.sumjdcdzxzycyw+listzj.sumjdcqtyw+listzj.sumjszwdzr+listzj.sumjszqmhz+listzj.sumjszbhlyw+listzj.sumjszjjhz+listzj.sumjszjwhz+listzj.sumjszmfyw+listzj.sumjszsyyw+listzj.sumjszqtyw}}</td>
          <td>{{listzj.sumjdcxkcxczc+listzj.sumjdcescgh+listzj.sumjdcxkczr+listzj.sumjdcbhljdchp+listzj.sumjdcbhlxsz+listzj.sumjdcdyyw+listzj.sumjdcdzxzcdj+listzj.sumjdcdzxzycyw+listzj.sumjdcqtyw}}</td>
          <td>{{listzj.sumjdcxkcxczc}}</td>
          <td>{{listzj.sumjdcescgh}}</td>
          <td>{{listzj.sumjdcxkczr}}</td>
          <td>{{listzj.sumjdcbhljdchp}}</td>
          <td>{{listzj.sumjdcbhlxsz}}</td>
          <td>{{listzj.sumjdcdyyw}}</td>
          <td>{{listzj.sumjdcdzxzcdj}}</td>
          <td>{{listzj.sumjdcdzxzycyw}}</td>
          <td>{{listzj.sumjdcqtyw}}</td>
          <td>{{listzj.sumjdcdcs}}</td>
          <td>{{listzj.sumjszwdzr+listzj.sumjszqmhz+listzj.sumjszbhlyw+listzj.sumjszjjhz+listzj.sumjszjwhz+listzj.sumjszmfyw+listzj.sumjszsyyw+listzj.sumjszqtyw}}</td>
          <td>{{listzj.sumjszwdzr}}</td>
          <td>{{listzj.sumjszqmhz}}</td>
          <td>{{listzj.sumjszbhlyw}}</td>
          <td>{{listzj.sumjszjjhz}}</td>
          <td>{{listzj.sumjszjwhz}}</td>
          <td>{{listzj.sumjszmfyw}}</td>
          <td>{{listzj.sumjszsyyw}}</td>
          <td>{{listzj.sumjszqtyw}}</td>
          <td>{{listzj.sumjszdcs}}</td>
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
      _this.wxYyStatisticsDto.ywflList = ['2','3'];
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
      let paramsStr = "deptcode="+loginUser.deptcode+"&ywflListStr=2,3";
      if(_this.chooseType=='1'){
        let nf = $("#form-field-1").val();
        _this.title = $("#form-field-1").val()+"年 车驾管业务预约查询统计";
        paramsStr = paramsStr+ "&nf="+nf+"&title="+_this.title;
      }else if(_this.chooseType=='2'){
        let yf = $("#form-field-2").val();
        let dates = $("#form-field-2").val().split("-");
        _this.title = dates[0]+"年"+dates[1]+"月 车驾管业务预约查询统计";
        paramsStr = paramsStr+ "&yf="+yf+"&title="+_this.title;
      }else if(_this.chooseType=='3'){
        let rq = $("#form-field-3").val();
        let dates = $("#form-field-3").val().split("-");
        _this.title = dates[0]+"年"+dates[1]+"月"+dates[2]+"  车驾管业务预约查询统计";
        paramsStr = paramsStr+ "&rq="+rq+"&title="+_this.title;
      }
      let url = process.env.VUE_APP_SERVER + '/wxbase/export/wxYyStatistics/exportWxYyStatisticsByCjg?'+paramsStr;
      console.log(url);
      window.location.href = url;
    }
  }
}
</script>