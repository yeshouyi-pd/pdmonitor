<template>
  <div class="page-content">
    <div class="row">

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
                      <div class="widget-main no-padding" style="height:740px;overflow-y:auto">
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
                              {{szjcx|optionMapKV(item.jcxm )}}
                            </td>
                            <td >
                              <div v-show="item.dataResult">
                                <b class="green">{{item.dataResult}}</b>{{JYXM_DW|optionKV(item.jcxm)}}
                              </div>

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
                 监测站设备分布图
                </h5>
              </div>

              <div class="widget-body">
                <div class="widget-main">
                  <div class="hr hr8 hr-double"></div>
                  <div class="clearfix">

                    <div class="widget-body">
                      <div class="widget-main no-padding" style="height:740px;">

                        <equipmentAMap v-bind:height-max="740" map-style=""></equipmentAMap>

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
              <div class="row" >
                <div class="widget-box" style="text-align: center">
                  <div class="widget-header ">
                    <h5 class="widget-title">
                      <i class="ace-icon fa fa-bell"></i>
                      豚预警
                    </h5>
                  </div>
                  <div class="widget-body">
                    <div class="widget-main">
                      <div></div>
                      <div class="hr hr8 hr-double"></div>

                      <div class="clearfix">

                        <div class="widget-body">
                          <div class="widget-main no-padding" style="height:740px;">
                            <div style="height: 334px;text-align: left;overflow-y:auto">
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
                                  <i class="ace-icon fa  fa-book blue"></i>出现次数
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
                            </div>
                            <div class="row" style="height: auto" >
                              <div class="widget-box" style="text-align: center">
                                <div class="widget-header ">
                                  <h5 class="widget-title">
                                    <i class="ace-icon fa fa-signal"></i>
                                    捕食比例
                                  </h5>
                                </div>
                                <div class="widget-body">
                                  <div class="widget-main">
                                    <div></div>
                                    <div class="hr hr8 hr-double"></div>

                                    <div class="clearfix">

                                      <div class="widget-body">
                                        <div class="widget-main no-padding" style="height:325px;text-align: left" >

                                          <div class="space-6"></div>
                                          <div id="piechart-placeholder" style="height: 250px"></div>

                                          <div class="hr hr8 hr-double"></div>

                                          <div class="clearfix">
                                            <div class="grid8">
															<span class="grey">
																<i class="ace-icon fa fa-bar-chart-o fa-2x blue"></i>
																&nbsp;侦测次数
															</span>
                                              <h4 class="bigger pull-right">{{ zccs }}</h4>
                                            </div>

                                            <div class="grid8">
															<span class="grey">
																<i class="ace-icon fa fa-check-square-o fa-2x green"></i>
																&nbsp;捕食次数
															</span>
                                              <h4 class="bigger pull-right">{{ bscs }}</h4>
                                            </div>
<!--                                            <div class="grid4">-->
<!--															<span class="grey">-->
<!--																<i class="ace-icon fa   fa-ban fa-2x  red"></i>-->
<!--																&nbsp;离线-->
<!--															</span>-->
<!--                                              <h4 class="bigger pull-right">{{ lx }}</h4>-->
<!--                                            </div>-->

<!--                                            <div class="grid4">-->
<!--															<span class="grey">-->
<!--																<i class="ace-icon fa fa-bolt fa-2x yellow"></i>-->
<!--																&nbsp; 故障-->
<!--															</span>-->
<!--                                              <h4 class="bigger pull-right">{{ gz }}</h4>-->
<!--                                            </div>-->

                                          </div>


                                        </div><!-- /.widget-main -->
                                      </div><!-- /.widget-body -->
                                    </div>
                                  </div><!-- /.widget-main -->
                                </div><!-- /.widget-body -->
                              </div><!-- /.widget-box -->
                            </div>

                          </div><!-- /.widget-main -->
                        </div><!-- /.widget-body -->
                      </div>
                    </div><!-- /.widget-main -->
                  </div><!-- /.widget-body -->
                </div><!-- /.widget-box -->
              </div>
              <!-- 最右侧上面  end  -->


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
import EquipmentAMap from "../monitor/equipmentAMap";
export default {
  components: {EquipmentAMap},
  name:'welcome',
  data: function() {
    return {
      waterQualityResults:[],
      kvMaps:[],
      count:{},
      szjcx:[],
      zs:0,
      zc:0,
      lx:0,
      gz:0,
      JYXM_DW:JYXM_DW,
      zccs:0,
      bscs:0
    }
  },
  mounted:function(){
    let _this = this;
    _this.getSzjcx();
    _this.getLatestDate();
    _this.getWarningDate();
    _this.getPieChart();
  },
  methods: {

    /**
     *  welcome 水质实时数据
     */
    getLatestDate() {
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getLatestDate/'+Tool.getLoginUser().xmbh).then((res)=>{
        let response = res.data;
        _this.waterQualityResults = response.content;
      })
    },

    /**
     *  welcome 实时越限警告
     */
    getWarningDate() {
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getWarningDate/'+Tool.getLoginUser().xmbh).then((res)=>{
        let response = res.data;
        _this.kvMaps = response.content;
      })
    },
    /**
     *  welcome 饼状图 数据
     */
    getPieChart() {
      let _this = this;
      let alarmNumbersDto = {};
      let url = process.env.VUE_APP_SERVER + '/monitor/welcome/getPieChart';
      if("460100"!=Tool.getLoginUser().deptcode){
        alarmNumbersDto.deptcode = Tool.getLoginUser().deptcode;
        url = process.env.VUE_APP_SERVER + '/monitor/welcome/getPieChart/'+Tool.getLoginUser().xmbh;
      }
      alarmNumbersDto.stime = moment().subtract(0, "days").format('YYYY-MM-DD');
      alarmNumbersDto.etime = moment().subtract(0, "days").format('YYYY-MM-DD');
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileToday/statisticsAlarmNumsByTimeSum', alarmNumbersDto).then((response) => {
        let resp = response.data;
        console.log(resp.content);
        _this.initPieEChart(resp.content);
      });
      _this.$ajax.get(url).then((res)=>{
        let response = res.data;
        let data = response.content;
        //_this.showPieChart(data);
        if(Tool.isNotEmpty(data)){
          let zss = 0;
          let zcs = 0;
          let lxs = 0;
          let gzs = 0;
            for (let i = 0;i <data.length ; i ++){
              zss = zss+data[i].data;
              if(data[i].color.includes("#68BC31")){
                zcs = data[i].data;
              }
              if(data[i].color.includes("#DA5430")){
                lxs = data[i].data;
              }
              if(data[i].color.includes("#FEE074")){
                gzs =data[i].data;
              }
            }
            _this.zs = zss;
            _this.zc = zcs;
            _this.lx = lxs;
            _this.gz = gzs;
        }

      })
    },
    initPieEChart(data){
      let _this = this;
      _this.zccs=data.num;
      _this.bscs=data.bnum;
      let option = {
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: data.num, name: '侦测次数' },
              { value: data.bnum, name: '捕食次数' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      let pieChart = echarts.init(document.getElementById('piechart-placeholder'));
      if (option && typeof option === 'object') {
        _this.$nextTick(function () {
          pieChart.setOption(option);
        })
      }
    },
    /**
     * 获取水质检测项
     */
    getSzjcx(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getSzjcx', {
      }).then((response)=>{
        let resp = response.data;
        _this.szjcx = resp.content;
      })
    },
    /**
     * 饼状图
     */
    showPieChart(data){
      let _this = this;
      let placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'250px'});
      _this.drawPieChart(placeholder, data);
      placeholder.data('chart', data);
      placeholder.data('draw', _this.drawPieChart);
      //pie chart tooltip example
      let $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
      let previousPoint = null;
      placeholder.on('plothover', function (event, pos, item) {
        if(item) {
          if (previousPoint != item.seriesIndex) {
            previousPoint = item.seriesIndex;
            let tip = item.series['label'] + " : " + item.series['percent']+'%';
            $tooltip.show().children(0).text(tip);
          }
          $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
        } else {
          $tooltip.hide();
          previousPoint = null;
        }
      });

    },


    /**
     * 饼状图base 方法
     */
    drawPieChart(placeholder, data, position) {
      $.plot(placeholder, data, {
        series: {
          pie: {
            show: true,
            tilt: 0.8,
            highlight: {
              opacity: 0.25
            },
            stroke: {
              color: '#fff',
              width: 2
            },
            startAngle: 2
          }
        },
        legend: {
          show: true,
          position: position || "ne",
          labelBoxBorderColor: null,
          margin: [-30, 15]
        }
        ,
        grid: {
          hoverable: true,
          clickable: true
        }
      })
    },


  },
}
</script>