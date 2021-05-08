<template>
  <div class="page-content">
    <div class="row">
      <div class="hr hr32 hr-dotted"></div>
      <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->

        <!-- col-sm-3 start-->
        <div class="row">
          <div class="space-6"></div>
          <div class="col-sm-3 ">
            <div class="widget-box" style="text-align: center">
              <div class="widget-header" >
                  <h5 class="widget-title">
                    <i class="ace-icon fa fa-bar-chart-o"></i>
                   水质实时数据
                  </h5>
              </div>
              <div class="widget-body">
                <div class="widget-main">
                  <div></div>
                  <div class="hr hr8 hr-double"></div>

                  <div class="clearfix">
                    <div class="widget-body">
                      <div class="widget-main no-padding" style="height:740px;overflow-x:auto">
                        <table class="table table-bordered table-striped">
                          <thead class="thin-border-bottom">
                          <tr>
                            <th>
                              <i class="ace-icon fa   fa-barcode blue"></i>设备编号
                            </th>

                            <th>
                              <i class="ace-icon fa fa-check-square-o blue"></i>检验项目
                            </th>

                            <th class="hidden-480">
                              <i class="ace-icon fa  fa-pencil-square-o blue"></i>检验结果
                            </th>
                            <th class="hidden-480">
                              <i class="ace-icon fa  fa-calendar blue"></i>更新时间
                            </th>
                          </tr>
                          </thead>

                          <tbody>


                          <tr v-for="item  in  waterQualityResults" style="text-align: left">
                            <td>{{ item.ip }}</td>
                            <td>
                             {{ item.jcxm }}
                            </td>
                            <td >
                              <b class="green">{{item.dataResult}}</b>
                            </td>
                            <td>{{item.createTime}}</td>
                          </tr>


                          </tbody>
                        </table>
                      </div><!-- /.widget-main -->
                    </div><!-- /.widget-body -->
                  </div>
                </div><!-- /.widget-main -->
              </div><!-- /.widget-body -->
            </div><!-- /.widget-box -->
          </div>
          <!-- col-sm-3  end-->
          <!-- col-sm-6  start-->
          <div class="col-sm-6">
            <div class="widget-box" style="text-align: center">
              <div class="widget-header ">
                <h5 class="widget-title">
                  <i class="ace-icon fa fa-tachometer  "></i>
                 监测站分布图
                </h5>
              </div>

              <div class="widget-body">
                <div class="widget-main">
                  <div id="piechart-placeholder"></div>
                  <div class="hr hr8 hr-double"></div>
                  <div class="clearfix">

                    <div class="widget-body">
                      <div class="widget-main no-padding" style="height:740px;">

                        11111111

                      </div><!-- /.widget-main -->
                    </div><!-- /.widget-body -->
                  </div>
                </div><!-- /.widget-main -->
              </div><!-- /.widget-body -->
            </div><!-- /.widget-box -->
          </div><!-- /.col -->
          <!-- col-sm-6  end-->
          <!-- col-sm-3  start-->

          <div class="col-sm-3">
            <div class="col-xs-12">
              <div class="row">
                <div class="widget-box" style="text-align: center">
                  <div class="widget-header ">
                    <h5 class="widget-title">
                      <i class="ace-icon fa fa-bell"></i>
                      实时越限告警
                    </h5>
                  </div>
                  <div class="widget-body">
                    <div class="widget-main">
                      <div></div>
                      <div class="hr hr8 hr-double"></div>

                      <div class="clearfix">

                        <div class="widget-body">
                          <div class="widget-main no-padding" style="height:325px;">
                            <table class="table table-bordered table-striped">
                              <thead class="thin-border-bottom">
                              <tr>
                                <th>
                                  <i class="ace-icon fa fa-flag blue"></i>监控点
                                </th>

                                <th>
                                  <i class="ace-icon fa  fa-barcode blue"></i>设备编号
                                </th>

                                <th class="hidden-480">
                                  <i class="ace-icon fa  fa-book blue"></i>告警次数
                                </th>
                              </tr>
                              </thead>

                              <tbody>
                              <tr v-for="keval in kvMaps">
                                <td>{{ keval.deptname }}</td>
                                <td>
                                 {{keval.text}}
                                </td>
                                <td>
                                  <b class="red">{{keval.code}}</b>
                                </td>
                              </tr>
                              </tbody>
                            </table>
                          </div><!-- /.widget-main -->
                        </div><!-- /.widget-body -->
                      </div>
                    </div><!-- /.widget-main -->
                  </div><!-- /.widget-body -->
                </div><!-- /.widget-box -->
              </div>
              <!-- 最右侧上面  end  -->

              <div class="row">
                <div class="widget-box" style="text-align: center">
                  <div class="widget-header ">
                    <h5 class="widget-title">
                      <i class="ace-icon fa fa-signal"></i>
                      监测站在线率
                    </h5>
                  </div>
                  <div class="widget-body">
                    <div class="widget-main">
                      <div></div>
                      <div class="hr hr8 hr-double"></div>

                      <div class="clearfix">

                        <div class="widget-body">
                          <div class="widget-main no-padding" style="height:325px;">
                            <table class="table table-bordered table-striped">
                              <thead class="thin-border-bottom">
                              <tr>
                                <th>
                                  <i class="ace-icon fa fa-caret-right blue"></i>name
                                </th>

                                <th>
                                  <i class="ace-icon fa fa-caret-right blue"></i>price
                                </th>

                                <th class="hidden-480">
                                  <i class="ace-icon fa fa-caret-right blue"></i>status
                                </th>
                              </tr>
                              </thead>

                              <tbody>
                              <tr>
                                <td>internet.com</td>

                                <td>
                                  <small>
                                    <s class="red">$29.99</s>
                                  </small>
                                  <b class="green">$19.99</b>
                                </td>

                                <td class="hidden-480">
                                  <span class="label label-info arrowed-right arrowed-in">on sale</span>
                                </td>
                              </tr>
                              </tbody>
                            </table>
                          </div><!-- /.widget-main -->
                        </div><!-- /.widget-body -->
                      </div>
                    </div><!-- /.widget-main -->
                  </div><!-- /.widget-body -->
                </div><!-- /.widget-box -->
              </div>
              <!-- 最右侧下面  end  -->
            </div>

          </div>

          <!-- col-sm-3  end-->
        </div><!-- /.row -->

        <div class="hr hr32 hr-dotted"></div>
        <!-- PAGE CONTENT ENDS -->

      </div>
    </div>


  </div>
</template>
<script>

export default {
  name:'welcome',
  data: function() {
    return {
      waterQualityResults:[],
      kvMaps:[],
    }
  },
  mounted:function(){
    let _this = this;
    _this.getLatestDate();
    _this.getWarningDate();
  },
  methods: {

    /**
     *  welcome 水质实时数据
     */
    getLatestDate() {
      let _this = this;
      Loading.show();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getLatestDate').then((res)=>{
        Loading.hide();
        let response = res.data;
        _this.waterQualityResults = response.content;
      })
    },

    /**
     *  welcome 实时越限警告
     */
    getWarningDate() {
      let _this = this;
      Loading.show();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getWarningDate').then((res)=>{
        Loading.hide();
        let response = res.data;
        _this.kvMaps = response.content;
        console.log(_this.kvMaps);
      })
    }


  },

}
</script>