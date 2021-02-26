<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">报废车统计查询</h4>
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
                    <td style="width: 11%;text-align: left;color: #ccc" v-show="chooseType!=3">(不包含今天的数据)</td>
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
        进场明细
      </div>
      <table id="simple-table" class="table  table-bordered table-hover" style="width: 80%;float: left">
        <thead>
        <tr>
              <th rowspan="2"></th>
              <th colspan="4">电动车</th>
              <th colspan="3">摩托车</th>
              <th rowspan="2">三轮车</th>
              <th rowspan="2">汽车</th>
              <th rowspan="2">农用车</th>
              <th rowspan="2">其他</th>
              <th rowspan="2">合计</th>
        </tr>
        <tr>
              <th>黄牌</th>
              <th>蓝牌</th>
              <th>无牌</th>
              <th>小计</th>
              <th>有牌</th>
              <th>无牌</th>
              <th>小计</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in rList">
                <td>{{maps|optionMapKV(item.sysOrgCode)}}</td>
                <td>{{item.sumddchp}}</td>
                <td>{{item.sumddcnp}}</td>
                <td>{{item.sumddcwp}}</td>
                <td>{{item.sumddchp+item.sumddcnp+item.sumddcwp}}</td>
                <td>{{item.summtcyp}}</td>
                <td>{{item.summtcwp}}</td>
                <td>{{item.summtcwp+item.summtcyp}}</td>
                <td>{{item.sumslc}}</td>
                <td>{{item.sumqc}}</td>
                <td>{{item.sumnyc}}</td>
                <td>{{item.sumqt}}</td>
                <td>{{item.sumddchp+item.sumddcnp+item.sumddcwp+item.summtcwp+item.summtcyp+item.sumslc+item.sumqc+item.sumnyc+item.sumqt}}</td>
        </tr>
        <tr>
                <td>合计</td>
                <td>{{rzj.sumddchp}}</td>
                <td>{{rzj.sumddcnp}}</td>
                <td>{{rzj.sumddcwp}}</td>
                <td>{{rzj.sumddchp+rzj.sumddcnp+rzj.sumddcwp}}</td>
                <td>{{rzj.summtcyp}}</td>
                <td>{{rzj.summtcwp}}</td>
                <td>{{rzj.summtcyp+rzj.summtcwp}}</td>
                <td>{{rzj.sumslc}}</td>
                <td>{{rzj.sumqc}}</td>
                <td>{{rzj.sumnyc}}</td>
                <td>{{rzj.sumqt}}</td>
                <td>{{rzj.sumddchp+rzj.sumddcnp+rzj.sumddcwp+rzj.summtcyp+rzj.summtcwp+rzj.sumslc+rzj.sumqc+rzj.sumnyc+rzj.sumqt}}</td>
        </tr>
        <tr>
                <td>总计</td>
                <td colspan="12">{{rzj.sumddchp+rzj.sumddcnp+rzj.sumddcwp+rzj.summtcyp+rzj.summtcwp+rzj.sumslc+rzj.sumqc+rzj.sumnyc+rzj.sumqt}}</td>
        </tr>
        </tbody>
      </table>
      <table id="simple-table2" class="table  table-bordered table-hover" style="width: 20%">
        <thead>
          <tr style="height: 72px;"><th>进场合计</th><th>分类</th></tr>
        </thead>
        <tbody>
          <tr><td>{{rzj.sumddchp+rzj.sumddcnp+rzj.sumddcwp}}</td><td>电动车</td></tr>
          <tr><td>{{rzj.summtcyp+rzj.summtcwp}}</td><td>摩托车</td></tr>
          <tr><td>{{rzj.sumslc}}</td><td>三轮车</td></tr>
          <tr><td>{{rzj.sumqc}}</td><td>汽车</td></tr>
          <tr><td>{{rzj.sumnyc}}</td><td>农用车</td></tr>
          <tr><td>{{rzj.sumqt}}</td><td>其他</td></tr>
          <tr><td>{{rzj.sumddchp+rzj.sumddcnp+rzj.sumddcwp+rzj.summtcyp+rzj.summtcwp+rzj.sumslc+rzj.sumqc+rzj.sumnyc+rzj.sumqt}}</td><td>合计</td></tr>
        </tbody>
      </table>
    </div>
    <div v-show="showTable" style="height: 500px;overflow-y: auto;overflow-x: hidden">
      <div style="font-size: large;line-height: 60px;text-align: center;font-weight: 600">{{title}}
        出场明细
      </div>
      <table id="simple-table3" class="table  table-bordered table-hover" style="width: 80%;float: left">
        <thead>
        <tr>
          <th rowspan="2"></th>
          <th colspan="4">电动车</th>
          <th colspan="3">摩托车</th>
          <th rowspan="2">三轮车</th>
          <th rowspan="2">汽车</th>
          <th rowspan="2">农用车</th>
          <th rowspan="2">其他</th>
          <th rowspan="2">合计</th>
        </tr>
        <tr>
          <th>黄牌</th>
          <th>蓝牌</th>
          <th>无牌</th>
          <th>小计</th>
          <th>有牌</th>
          <th>无牌</th>
          <th>小计</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item,index) in cList">
          <td>{{maps|optionMapKV(item.sysOrgCode)}}</td>
          <td>{{item.sumddchp}}</td>
          <td>{{item.sumddcnp}}</td>
          <td>{{item.sumddcwp}}</td>
          <td>{{item.sumddchp+item.sumddcnp+item.sumddcwp}}</td>
          <td>{{item.summtcyp}}</td>
          <td>{{item.summtcwp}}</td>
          <td>{{item.summtcwp+item.summtcyp}}</td>
          <td>{{item.sumslc}}</td>
          <td>{{item.sumqc}}</td>
          <td>{{item.sumnyc}}</td>
          <td>{{item.sumqt}}</td>
          <td>{{item.sumddchp+item.sumddcnp+item.sumddcwp+item.summtcwp+item.summtcyp+item.sumslc+item.sumqc+item.sumnyc+item.sumqt}}</td>
        </tr>
        <tr>
          <td>合计</td>
          <td>{{czj.sumddchp}}</td>
          <td>{{czj.sumddcnp}}</td>
          <td>{{czj.sumddcwp}}</td>
          <td>{{czj.sumddchp+czj.sumddcnp+czj.sumddcwp}}</td>
          <td>{{czj.summtcyp}}</td>
          <td>{{czj.summtcwp}}</td>
          <td>{{czj.summtcyp+czj.summtcwp}}</td>
          <td>{{czj.sumslc}}</td>
          <td>{{czj.sumqc}}</td>
          <td>{{czj.sumnyc}}</td>
          <td>{{czj.sumqt}}</td>
          <td>{{czj.sumddchp+czj.sumddcnp+czj.sumddcwp+czj.summtcyp+czj.summtcwp+czj.sumslc+czj.sumqc+czj.sumnyc+czj.sumqt}}</td>
        </tr>
        <tr>
          <td>总计</td>
          <td colspan="12">{{czj.sumddchp+czj.sumddcnp+czj.sumddcwp+czj.summtcyp+czj.summtcwp+czj.sumslc+czj.sumqc+czj.sumnyc+czj.sumqt}}</td>
        </tr>
        </tbody>
      </table>
      <table id="simple-table4" class="table  table-bordered table-hover" style="width: 20%">
        <thead>
          <tr style="height: 72px;">
            <th>进场合计</th>
            <th>分类</th>
          </tr>
        </thead>
        <tbody>
          <tr><td>{{czj.sumddchp+czj.sumddcnp+czj.sumddcwp}}</td><td>电动车</td></tr>
          <tr><td>{{czj.summtcyp+czj.summtcwp}}</td><td>摩托车</td></tr>
          <tr><td>{{czj.sumslc}}</td><td>三轮车</td></tr>
          <tr><td>{{czj.sumqc}}</td><td>汽车</td></tr>
          <tr><td>{{czj.sumnyc}}</td><td>农用车</td></tr>
          <tr><td>{{czj.sumqt}}</td><td>其他</td></tr>
          <tr><td>{{czj.sumddchp+czj.sumddcnp+czj.sumddcwp+czj.summtcyp+czj.summtcwp+czj.sumslc+czj.sumqc+czj.sumnyc+czj.sumqt}}</td><td>合计</td></tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "wxbase-vehParkLotStatistics",
    data: function() {
      return {
        title: '',
        cList: [],
        rList: [],
        vehParkLotStatisticsDto:{},
        chooseType:'3',
        showTable:false,
        cddchpAll: 0,
        cddcnpAll: 0,
        cddcwpAll: 0,
        cmtcwpAll: 0,
        cmtcypAll: 0,
        cnycAll: 0,
        cqcAll: 0,
        cqtAll: 0,
        cslcAll: 0,
        rddchpAll: 0,
        rddcnpAll: 0,
        rddcwpAll: 0,
        rmtcwpAll: 0,
        rmtcypAll: 0,
        rnycAll: 0,
        rqcAll: 0,
        rqtAll: 0,
        rslcAll: 0,
        rzj:{},
        czj:{},
        maps: [],
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
        _this.vehParkLotStatisticsDto={};
        _this.vehParkLotStatisticsDto.chooseType = _this.chooseType;
        if(_this.chooseType=='1'){
          _this.vehParkLotStatisticsDto.checkdate = $("#form-field-1").val();
          _this.title = $("#form-field-1").val()+"年";
        }else if(_this.chooseType=='2'){
          _this.vehParkLotStatisticsDto.checkdate = $("#form-field-2").val();
          let dates = $("#form-field-2").val().split("-");
          _this.title = dates[0]+"年"+dates[1]+"月";
        }else if(_this.chooseType=='3'){
          _this.vehParkLotStatisticsDto.checkdate = $("#form-field-3").val();
          let dates = $("#form-field-3").val().split("-");
          _this.title = dates[0]+"年"+dates[1]+"月"+dates[2];
        }
        _this.$forceUpdate();
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehParkLotStatistics/getList', _this.vehParkLotStatisticsDto).then((response)=>{
          Loading.hide();
          let resp = response.data.content;
          _this.rList = resp.rlist.mx || [];
          _this.rzj = resp.rlist.zj || {};
          _this.cList = resp.clist.mx || [];
          _this.czj = resp.clist.zj || {};
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
          _this.title = $("#form-field-1").val()+"年";
          paramsStr = paramsStr+ "&checkdate="+nf+"&title="+_this.title;
        }else if(_this.chooseType=='2'){
          let yf = $("#form-field-2").val();
          let dates = $("#form-field-2").val().split("-");
          _this.title = dates[0]+"年"+dates[1]+"月";
          paramsStr = paramsStr+ "&checkdate="+yf+"&title="+_this.title;
        }else if(_this.chooseType=='3'){
          let rq = $("#form-field-3").val();
          let dates = $("#form-field-3").val().split("-");
          _this.title = dates[0]+"年"+dates[1]+"月"+dates[2];
          paramsStr = paramsStr+ "&checkdate="+rq+"&title="+_this.title;
        }
        let url = process.env.VUE_APP_SERVER + '/wxbase/export/vehParkLotStatistics/exportVehParkLotStatistics?'+paramsStr;
        console.log(url);
        window.location.href = url;
      }
    }
  }
</script>