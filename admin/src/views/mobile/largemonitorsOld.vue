<template>
    <div class="data_bodey">
        <remote-css href="/css/BigData.css" rel="stylesheet" type="text/css" />
        <remote-css href="/css/index.css" rel="stylesheet" type="text/css" />
        <remote-css href="/css/index01.css" rel="stylesheet" type="text/css" />
        <remote-css href="/js/bstable/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <remote-css href="/js/bstable/css/bootstrap-table.css" rel="stylesheet" type="text/css" />
        <remote-css href="/css/Security_operation.css" rel="stylesheet" type="text/css" />
        <remote-css rel="stylesheet" href="/js/artDialog/skins/default.css" type="text/css"/>

        <div class="index_nav" >
            <ul style="height: 30px; margin-bottom: 0px;">
                <!--<router-link to="/admin/chooseProject">-->
                    <li @click="chooseProject" class="l_left total_chose_fr nav_active">主页</li>
                    <li @click="toTyDp" class="l_left total_chose_fr nav_active">巡护</li>
                <!--</router-link>-->
            </ul>
        </div>
        <div class="index_tabs" >
            <!--安防运作-->
            <div class="inner" style="height: 109%;">
                <div class="left_cage">
                    <div class="dataAllBorder01 cage_cl" style="margin-top: 9% !important; height: 24%;">
                        <video autoplay="autoplay" loop="loop" class="dataAllBorder02 video_cage">
                            <source class="video" title="主监控位" src="/video/12.mp4"/>
                        </video>
                    </div>
                    <div class="dataAllBorder01 cage_cl" style="margin-top: 1.5% !important; height: 38%;">
                        <div class="dataAllBorder02 video_cage">
                            <div v-for="(item,index) in cameras"  class="video_around" style="border: 0px solid red;">
                                <iframe v-if="index < 4" width="200px;" height="163px;" :src="'/mobile/test?id='+item.id" frameborder="0"></iframe>
                            </div>
                        </div>
                    </div>
                    <div class="dataAllBorder01 cage_cl" style="margin-top: 1.5% !important; height: 32%">
                        <div class="dataAllBorder02"  style="height: 100%;width: 100%">
                            <div class="map_title">鲸豚出现事件统计图</div>
                            <div id="container" style="height: 100%;width: 100%;border: 0px solid red;"></div>
                        </div>
                    </div>
                </div>

                <div class="center_cage">
                    <div class="dataAllBorder01 cage_cl" style="margin-top: 3.5% !important; height: 62.7%; position: relative;">
                        <div class="dataAllBorder02" style="position: relative; overflow: hidden;">
                            <!--标题栏-->
                            <div class="map_title_box" style="height: 6%">
                                <div class="map_title_innerbox">
                                    <div class="map_title">实时地图</div>
                                </div>
                            </div>
                            <EquipmentAMap v-bind:height-max="heightMax"></EquipmentAMap>
                        </div>
                    </div>

                    <div class="dataAllBorder01 cage_cl" style="margin-top: 0.6% !important; height: 32.1%;">
                        <div class="dataAllBorder02" id="map_title_innerbox">
                            <div class="map_title_box">
                                <div class="map_title_innerbox">
                                    <div class="map_title" style="background-image: url(/img/second_title.png);">分析数据</div>
                                </div>
                            </div>
                            <div class="dataAllBorder01 cage_cl check_increase" style=" margin-top: 0.1% !important;height: 90%;">
                                <div style="overflow-y:scroll;" class="dataAllBorder02 over_hide dataAllBorder20" id="over_hide">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr style="background: #395DC0;color: #FFFFFF;height: 30px;">
                                            <th style="width: 25%;font-size: 16px;">监控点</th>
                                            <th style="width: 25%;font-size: 16px;">设备编号</th>
                                            <th style="width: 25%;font-size: 16px;">声学侦测次数</th>
                                            <th style="width: 25%;font-size: 16px;">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="keval in kvMaps" style="text-align: left;background: #18468E;height: 30px;">
                                            <td>{{ deptMap|optionMapKV(keval.deptcode) }}</td>
                                            <td>
                                                {{keval.text}}
                                            </td>
                                            <td>
                                                <b class="green">{{keval.code}}</b>
                                            </td>
                                            <td>
                                                <button @click="jsmethod(keval.text)" class="btn btn-success">查看详细</button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="right_cage">
                    <!--顶部切换位置-->
                    <div class="dataAllBorder01 cage_cl" style="margin-top: 9% !important; height: 24%">
                        <div class="dataAllBorder02" id="cage_cl" style="border: 0px solid red;padding-left: 10%;padding-top: 5%;">
                            <div class="analysis">当日声学侦测次数：{{alarmDatas.num}}次</div>
                            <div class="analysis" style="margin: 5px 0;">当日事件(群次)：{{alarmDatas.nnm}}次</div>
                            <div class="analysis">当日捕食次数：{{alarmDatas.bnum}}次</div>
                            <div class="analysis" style="margin: 5px 0;">当日群次：{{eventDatas.length}}次</div>
                        </div>
                    </div>

                    <div class="dataAllBorder01 cage_cl check_increase" style=" margin-top: 1.5% !important;">
                        <!--切换01-->
                        <div style="overflow-y:scroll;" class="dataAllBorder02 over_hide dataAllBorder20" id="over_hide2">
                            <table class="table table-bordered">
                                <thead>
                                <tr style="background: #395DC0;color: #FFFFFF;">
                                    <th style="width: 20%;">设备编号</th>
                                    <th style="width: 30%;">开始时间</th>
                                    <th style="width: 30%;">结束时间</th>
                                    <th style="width: 20%;">头数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="item  in  eventDatas" style="text-align: left;background: #18468E;">
                                    <td>{{ item.sbbh }}</td>
                                    <td>{{ item.kssj.substring(11) }}</td>
                                    <td>{{ item.jssj.substring(11) }}</td>
                                    <td>{{ item.ts }}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="dataAllBorder01 cage_cl check_decrease" style="margin-top: 1.5% !important; height: 32%; position: relative;">
                        <div class="dataAllBorder02 over_hide" style="padding: 1.2%;width: 100%;height: 100%;">
                            <div class="analysis">设备在线率</div>
                            <div class="danger_contain_box">
                                <div id="piechart-placeholder"></div>
                                <ul class="danger_depart danger_depart01">
                                    <li class="danger_ico" style="background-position-x: 0px"><i class="ace-icon fa fa-bar-chart-o fa-2x blue"></i></li>
                                    <li class="data_name">总数</li>
                                    <li class="data data01">{{ zs }}</li>
                                    <li class="data data02"></li>
                                </ul>
                                <ul class="danger_depart danger_depart01">
                                    <li class="danger_ico" style="background-position-x: 0px"><i class="ace-icon fa fa-check-square-o fa-2x green"></i></li>
                                    <li class="data_name">在线</li>
                                    <li class="data data01">{{ zc }}</li>
                                    <li class="data data02"></li>
                                </ul>
                                <ul class="danger_depart danger_depart01">
                                    <li class="danger_ico" style="background-position-x: 0px"><i class="ace-icon fa   fa-ban fa-2x  red"></i></li>
                                    <li class="data_name">离线</li>
                                    <li class="data data01">{{ lx }}</li>
                                    <li class="data data02"></li>
                                </ul>
                                <ul class="danger_depart danger_depart01">
                                    <li class="danger_ico" style="background-position-x: 0px"><i class="ace-icon fa fa-bolt fa-2x yellow"></i></li>
                                    <li class="data_name">故障</li>
                                    <li class="data data01">{{ gz }}</li>
                                    <li class="data data02"></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
                <div class="modal-dialog" style="width: 50%;" role="document">
                    <div class="modal-content" style="background: #395DC0;">
                        <div style="float:left;width: 50%;overflow-y:scroll;">
                            <div class="list-group" style="height: 500px;">
                                <button v-for="(item,index) in equipmentFiles" type="button" @click="showRealPic(item.tplj)" style="background: #395DC0; color:#FEFEFF;cursor: pointer;border:1px solid #3490BA;" class="list-group-item">{{moment(item.cjsj).format("YYYY-MM-DD HH:mm:ss")}}</button>
                            </div>
                        </div>
                        <div style="float:left;width: 50%;">
                            <div style="border: 0px solid red;text-align:center;">
                                <img :src=srcpic style="height: 520px;width:50%;">
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="modal-footer" style="background: #395DC0;">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

        </div>
    </div>
</template>

<script>
    import EquipmentAMap from "../monitor/equipmentAMap";
    export default {
        components:{
            EquipmentAMap,
            'remote-css': {
                render(createElement) {
                    return createElement('link', { attrs: { rel: 'stylesheet', href: this.href }});
                },
                props: {
                    href: { type: String, required: true },
                },
            },
            'remote-js': {
                render(createElement) {
                    return createElement('script', { attrs: { type: 'text/javascript', src: this.src }});
                },
                props: {
                    src: { type: String, required: true },
                },
            },
        },
        name: "largemonitors-old",
        data:function(){
            return{
                waterQualityResults:[],
                kvMaps:[],
                count:{},
                szjcx:[],
                zs:0,
                zc:0,
                lx:0,
                gz:0,
                JYXM_DW:JYXM_DW,
                Time:{},
                equipmentFiles:[],
                equipmentFileDto:{},
                srcpic:'',
                containerDate:{},
                alarmNumbersDto:{},
                alarmNumbersDto2:{},
                yAixsData:[],
                xAixsData:[],
                alarmDatas:{},
                intervalId:null,
                cameras:[],
                heightMax:'',
                deptMap:[],
                eventDatas:[]
            }
        },
        mounted: function () {
            let _this = this;
            _this.deptMap = Tool.getDeptUser();
            _this.getDataCamera();
            //_this.getSzjcx();
            _this.getPieChart();
            _this.getEventData();
            //_this.getLatestDate();
            //_this.TimeControl();
            //_this.Interval();
            _this.getWarningDate();
            _this.getContainerDate();
            _this.TimeSum();
            let h = document.documentElement.clientHeight || document.body.clientHeight;
            _this.heightMax = h*0.8-20;
        },
        methods: {
            toTyDp(){
                window.location.href = "/mobile/largemonitorsTy";
            },
            chooseProject(){
                window.location.href = "/admin/chooseProject";
            },
            // 获取摄像头数据
            getDataCamera() {
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/cameraInfo/getDataCamera',{
                    deptcode:Tool.getLoginUser().deptcode,
                }).then((res)=>{
                    let response = res.data;
                    _this.cameras = response.content;
                })
            },
            // 定时刷新数据函数
            dataRefreh() {
                let _this = this;
                // 计时器正在进行中，退出函数
                if (_this.intervalId != null) {
                    return;
                }
                // 计时器为空，操作
                _this.intervalId = setInterval(() => {
                    console.log("刷新" + new Date());
                    //_this.getSzjcx();
                    _this.getPieChart();
                    _this.getEventData();
                    //_this.getLatestDate();
                    //_this.TimeControl();
                    _this.getWarningDate();
                    _this.getContainerDate();
                    _this.TimeSum();
                }, 600000);
            },
            // 停止定时器
            clear() {
                let _this = this;
                clearInterval(_this.intervalId); //清除计时器
                _this.intervalId = null; //设置为null
            },
            TimeSum(){
                let _this = this;
                if("460100"!=Tool.getLoginUser().deptcode){
                  _this.alarmNumbersDto.deptcode = Tool.getLoginUser().deptcode;
                }
                _this.alarmNumbersDto.stime = moment().subtract(0, "days").format('YYYY-MM-DD');
                _this.alarmNumbersDto.etime = moment().subtract(0, "days").format('YYYY-MM-DD');
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileToday/statisticsAlarmNumsByTimeSum', _this.alarmNumbersDto).then((response) => {
                    let resp = response.data;
                    _this.alarmDatas = resp.content;
                    console.log(_this.alarmDatas);
                })
            },
            /**
             * 图片查看
             */
            showRealPic(tplj){
                let _this = this;
                _this.srcpic = tplj;
            },
            /**
             * 列表查询
             */
            listsbbh(sbbh) {
                let _this = this;
                Loading.show();
                _this.equipmentFileDto.sbbh=sbbh;
                _this.$forceUpdate();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileToday/listsbbh',_this.equipmentFileDto).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    _this.equipmentFiles = resp.content;
                    if(_this.equipmentFiles.length > 0){
                        _this.srcpic = _this.equipmentFiles[0].tplj;
                    }
                })
            },
            jsmethod(sbbh){
                let _this = this;
                _this.listsbbh(sbbh);
                _this.$forceUpdate();
                $("#form-modal").modal("show");
            },
            /**
             *  折线图
             */
            getContainerDate() {
                let _this = this;
                _this.alarmNumbersDto2.deptcode = Tool.getLoginUser().deptcode;
                _this.alarmNumbersDto2.stime = moment().subtract(7, "days").format('YYYY-MM-DD');
                _this.alarmNumbersDto2.etime = moment().subtract(1, "days").format('YYYY-MM-DD');
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileAlarmEvent/echartsAlarmDataByDp',_this.alarmNumbersDto2).then((response)=>{
                    let resp = response.data;
                    _this.containerDate = resp.content;
                    _this.yAixsData = _this.containerDate.yAixsData;
                    _this.xAixsData = _this.containerDate.xAixsData;
                    let dom = document.getElementById("container");
                    let myChart = echarts.init(dom);
                    let option;
                    option = {
                        tooltip: {
                            trigger: 'axis'
                        },
                        xAxis: {
                            show: true,
                            type: 'category',
                            name: '日期',
                            data: _this.xAixsData,
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#FFFFFF'
                                }
                            },
                        },
                        yAxis: {
                            show: true,
                            type: 'value',
                            name: '事件次数',
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#FFFFFF'
                                }
                            },
                        },
                        series: [
                            {
                                data: _this.yAixsData,
                                type: 'bar',
                                smooth: true
                            }
                        ]
                    };
                    if (option && typeof option === 'object') {
                      _this.$nextTick(function () {
                        myChart.setOption(option);
                      })
                    }
                })

            },
            /**
             *  welcome 实时越限警告
             */
            getWarningDate() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getWarningDate').then((res)=>{
                    let response = res.data;
                    _this.kvMaps = response.content;
                })
            },
            /**
             * 数据滑动定时
             */
            Interval(){
                let _this = this;
                _this.Time = setInterval(_this.TimeControl,3300);    //开始定时
                $(".message_scroll_box").mouseenter(function(){
                    clearInterval(_this.Time);    //停止定时
                }).mouseleave(function(){
                    _this.Time = setInterval(_this.TimeControl,3500);    //再次定时
                })
            },
            /**
             * 数据滑动
             */
            TimeControl(){
                $(".message_scroll_box").animate({marginTop:96},800,
                    function(){
                        $(".message_scroll_box").css({marginTop:0});    //把顶部的边界清零
                        $(".message_scroll_box .message_scroll:first").before($(".message_scroll_box .message_scroll:last"));    //在第一个新闻后面插入最后一个新闻
                    }
                );
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
             *  welcome 水质实时数据
             */
            getLatestDate() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getLatestDate').then((res)=>{
                    let response = res.data;
                    _this.waterQualityResults = response.content;
                })
            },
            /**
             * 事件数据
             */
            getEventData(){
              let _this = this;
              _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getEventData').then((res)=>{
                let response = res.data;
                _this.eventDatas = response.content;
              })
            },
            /**
             *  welcome 饼状图 数据
             */
            getPieChart() {
              let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getPieChart').then((res)=>{
                    let response = res.data;
                    let data = response.content;
                    _this.showPieChart(data);
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
              let dom = document.getElementById("piechart-placeholder");
              let myPieChart = echarts.init(dom);
              let option;
              option = {
                tooltip: {
                  trigger: 'item',
                  formatter: '{b}: ({d}%)'
                },
                // legend: {
                //   data: [
                //     '捕食行为',
                //     '江豚出现'
                //   ]
                // },
                series: [
                  {
                    type: 'pie',
                    // labelLine: {
                    //   length: 30
                    // },
                    label: {
                      formatter: ' {b|{b}：} {per|{d}%}  ',
                      backgroundColor: '#F6F8FC',
                      borderColor: '#8C8D8E',
                      borderWidth: 1,
                      borderRadius: 4,
                      rich: {
                        b: {
                          color: '#4C5058',
                          fontSize: 14,
                          fontWeight: 'bold',
                          lineHeight: 33
                        },
                        per: {
                          color: '#fff',
                          backgroundColor: '#4C5058',
                          padding: [3, 4],
                          borderRadius: 4
                        }
                      }
                    },
                    data: data
                  }
                ]
              };
              if (option && typeof option === 'object') {
                _this.$nextTick(function () {
                  myPieChart.setOption(option);
                })
              }
            },
            /**
             * 饼状图
             */
            showPieChart(data){
                let _this = this;
                let placeholder = $('#piechart-placeholder').css({'width':'100%' , 'height':'100%'});
                _this.drawPieChart(placeholder, data);
                placeholder.data('chart', data);
                placeholder.data('draw', _this.drawPieChart);
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
                    // legend: {
                    //     show: true,
                    //     position: position || "ne",
                    //     labelBoxBorderColor: null,
                    //     margin: [-30, 15]
                    // }
                    // ,
                    grid: {
                        hoverable: true,
                        clickable: true
                    }
                })
            },
        },
        created(){
            let _this = this;
            _this.dataRefreh();
        },
        destroyed(){
            // 在页面销毁后，清除计时器
            let _this = this;
            _this.clear();
        }
    }
</script>

<style scoped>

</style>