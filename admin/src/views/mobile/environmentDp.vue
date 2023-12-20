<template>
  <div style="width: 1920px;height: 100vh;background-image: url('/static/image/environment/dpbg.png');background-size: 100% 100%;margin: auto">
    <div class="page-first-div">
      <div class="left-div">
        <div v-if="LOCAL_VIDEO" style="height: 4.1%;display: flex;flex-direction: row;align-items: center;margin-left: 20px;">
          <div v-on:click="mianNew()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer">
            主页
          </div>
          <div v-on:click="videoNew()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer;margin-left: 10px;">
            视频
          </div>
        </div>
        <div v-else style="height: 4.1%;display: flex;flex-direction: row;align-items: center;margin-left: 20px;">
          <div v-on:click="back()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer">
            返回
          </div>
        </div>
        <div class="left-content-div">
          <div class="left-top">
            <div class="title-name-div" style="height: 20%;">
              <span style="padding-top:0%;">设备情况</span>
            </div>
            <div class="left-top-content">
              <div class="content-box">
                <img src="/static/image/environment/jcy.png" />
                <div>
                  <span>设备编号</span><br/>
                  <select v-model="curSbbh" @change="changeData" class="form-control" style="background-color: #13225E;color: yellow;border-color: #34B9DF;">
                    <option style="color: yellow;" v-for="item in zdysbList" :value="item.key">{{item.key}}</option>
                  </select>
                </div>
              </div>
              <div class="content-box">
                <img src="/static/image/environment/jcy.png" />
                <div>
                  <span>设备点位</span><br/>
                  <span style="color: yellow;">{{zdysbList|optionKVArray(curSbbh)}}</span>
                </div>
              </div>
            </div>
            <div class="left-top-content">
              <div class="content-box">
                <img src="/static/image/environment/jcy.png" />
                <div>
                  <span>当前日期</span><br/>
                  <span>{{curDate}}</span>
                </div>
              </div>
              <div class="content-box">
                <img src="/static/image/environment/jcy.png" />
                <div>
                  <span>水听器数量</span><br/>
                  <span>15</span>
                </div>
              </div>
            </div>
          </div>
          <div class="left-center">
            <div class="title-name-div" style="height: 10%">
              <span style="padding-top:1%;">海流计监测数据</span>
            </div>
            <div class="center-content-div">
              <div class="center-content-item-first">
                <div style="width: 15%;margin: auto;">序号</div>
                <div style="width: 35%;margin: auto;">数据项</div>
                <div style="width: 50%;margin: auto;">数值</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">1</div>
                <div style="width: 35%;margin: auto;">Abs速度</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.absSpeed}}m/s</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">2</div>
                <div style="width: 35%;margin: auto;">方向</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.direction}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">3</div>
                <div style="width: 35%;margin: auto;">倾斜度X</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.tiltX}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">4</div>
                <div style="width: 35%;margin: auto;">倾斜度Y</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.tiltY}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">5</div>
                <div style="width: 35%;margin: auto;">最大倾斜度</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.maxTilt}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">6</div>
                <div style="width: 35%;margin: auto;">标准倾斜度</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.stdTilt}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">7</div>
                <div style="width: 35%;margin: auto;">Sp标准</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.spStd}}</div>
              </div>
            </div>
          </div>
          <div class="left-bottom">
            <div class="title-name-div">
              <span>温盐度浊度仪数据</span>
            </div>
            <div class="bottom-content" id="leftBottomEchart"></div>
          </div>
        </div>
      </div>
      <div class="center-div">
        <div class="dp-title">中华白海豚环境耦合实时监测系统</div>
        <div class="map-div">
          <EquipmentAMap v-bind:height-max="heightMax" :click-map-point="clickMapPoint"></EquipmentAMap>
        </div>
        <div class="center-content-bottom">
          <div class="title-name-div">
            <span style="padding-top: 1%;">分析视频</span>
          </div>
          <div class="center-bottom-div">
            <dv-scroll-board class="sc-div" :config="config" style="width:100% ;height:96%" ref="scrollBoard"/>
          </div>
        </div>
      </div>
      <div class="right-div">
        <div class="left-content-div" style="margin-top: 8%;">
          <div class="left-top">
            <div class="title-name-div" style="height: 20%;">
              <span style="padding-top:0%;">气象数据</span>
            </div>
            <div class="right-top-content" style="margin: 1% auto 0;">
              <div class="right-top-box">
                <img src="/static/image/environment/fs.png" />
                <div>
                  <span>风速</span><br/>
                  <span>{{meteorological.speed}}m/s</span>
                </div>
              </div>
              <div class="right-top-box" style="margin:0 1%;">
                <img src="/static/image/environment/wd.png" />
                <div>
                  <span>气温</span><br/>
                  <span>{{meteorological.temperature}}℃</span>
                </div>
              </div>
              <div class="right-top-box">
                <img src="/static/image/environment/qy.png" />
                <div>
                  <span>气压</span><br/>
                  <span>{{meteorological.pressure}}hPa</span>
                </div>
              </div>
            </div>
            <div class="right-top-content" style="margin: 1% auto 0;">
              <div class="right-top-box">
                <img src="/static/image/environment/fx.png" />
                <div>
                  <span>风向</span><br/>
                  <span>{{meteorological.winddirection}}</span>
                </div>
              </div>
              <div class="right-top-box" style="margin:0 1%;">
                <img src="/static/image/environment/sd.png" />
                <div>
                  <span>湿度</span><br/>
                  <span>{{meteorological.humidity}}%</span>
                </div>
              </div>
              <div class="right-top-box">
                <img src="/static/image/environment/tyg.png" />
                <div>
                  <span>太阳光强度</span><br/>
                  <span>{{meteorological.solarintensity}}W/m2</span>
                </div>
              </div>
            </div>
          </div>
          <div class="left-center">
            <div class="title-name-div" style="height: 10%">
              <span style="padding-top: 1%;">海浪数据</span>
            </div>
            <div class="center-content-div" id="rightCenterEchart"></div>
          </div>
          <div class="left-bottom">
            <div class="title-name-div">
              <span style="padding-top: 1%;">水质数据</span>
            </div>
            <div class="center-bottom-div" id="rightBottomEchart"></div>
          </div>
        </div>
      </div>
    </div>

    <div id="video-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 80%;height: 90%;">
        <div class="modal-content" style="width: 100%;height: 100%;margin: auto">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">分析视频</h4>
          </div>
          <div class="modal-body" style="height: 88%;overflow-y: auto;width:100%;" id="playbox">

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
              <i class="ace-icon fa fa-times"></i>
              关闭
            </button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="alarm-num-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 80%;height: 90%;">
        <div class="modal-content" style="width: 100%;height: 100%;margin: auto;">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">出现次数</h4>
          </div>
          <div class="modal-body" style="height: 88%;width: 100%;" id="alarmNumEcharts">

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
              <i class="ace-icon fa fa-times"></i>
              关闭
            </button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>
</template>
<script>
import EquipmentAMap from "../../components/dpMap";
export default {
  name: 'environment-dp',
  components:{EquipmentAMap},
  data: function (){
    return {
      LOCAL_VIDEO:LOCAL_VIDEO,
      curDate:'',
      heightMax:510,
      curSbbh:'RPCDA4016',
      curSbmc:'RPCDA4016',
      currentMeter:{},
      meteorological:{},
      intervalId:null,
      zdysbbhList:['RPCDA4005','RPCDA4012','RPCDA4003','RPCDA4006-4','RPCDA4009-3','RPCDA4001','RPCDA4010','RPCDA4008','RPCDA4002','RPCDA4016'],
      zdysbList:[
        {key:"RPCDA4005", value:"3号航标"},
        {key:"RPCDA4012", value:"4号航标"},
        {key:"RPCDA4003", value:"5号航标"},
        {key:"RPCDA4006-4", value:"平台4"},
        {key:"RPCDA4009-3", value:"平台3"},
        {key:"RPCDA4001", value:"8号航标"},
        {key:"RPCDA4010", value:"10号航标"},
        {key:"RPCDA4008", value:"11号航标"},
        {key:"RPCDA4002", value:"淇澳岛"},
        {key:"RPCDA4016", value:"RPCDA4016"}
      ],
      config: {
        headerBGC: "#054F7F",
        oddRowBGC: "#054F7F",
        evenRowBGC: "",
        align: ['center', 'center', 'center', 'center'],
        header: ['设备名称', '开始时间', '结束时间', '视频'],
        data: []
      },
      tdhList:[
        {key:"1", value:"1"},
        {key:"2", value:"3"},
        {key:"3", value:"5"},
        {key:"4", value:"7"},
        {key:"5", value:"9"},
        {key:"6", value:"11"},
        {key:"7", value:"13"},
        {key:"8", value:"15"},
        {key:"9", value:"17"},
        {key:"10", value:"19"}
      ]
    }
  },
  mounted() {
    let _this = this;
    _this.curDate = Tool.dateFormat("yyyy-MM-dd",new Date());
    _this.leftCenterData();
    _this.leftBottomData();
    _this.rightBottomData();
    _this.rightTopData();
    _this.rightCenterData();
    _this.centerBottomData();
    _this.dataRefreh();
    window.getPlayUrl = _this.getPlayUrl;
  },
  methods: {
    changeData(){
      let _this = this;
      //_this.leftBottomData();
      _this.rightBottomData();
      //_this.rightTopData();
      _this.rightCenterData();
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
        _this.leftCenterData();
        _this.leftBottomData();
        //_this.rightBottomData();
        _this.rightTopData();
        //_this.rightCenterData();
      }, 900000);
    },
    // 停止定时器
    clear() {
      let _this = this;
      clearInterval(_this.intervalId); //清除计时器
      _this.intervalId = null; //设置为null
    },
    back(){
      let _this = this;
      _this.clear();
      window.location.href = "/mobile/largemonitorsZj";
    },
    mianNew(){
      let _this = this;
      _this.clear();
      if(_this.LOCAL_VIDEO){
        _this.user = Tool.getLoginUser();
        _this.user.xmbh = "002";
        _this.$forceUpdate();
        Tool.setLoginUser(_this.user);
        _this.$router.push("/welcome");
      }else{
        window.location.href = "/admin/chooseProject";
      }
    },
    videoNew(){
      let _this = this;
      _this.clear();
      window.location.href = "/mobile/videoNewDp";
    },
    clickMapPoint(sbmc,sbbh){
      let _this = this;
      if(sbbh.includes("RPCD")){
        // _this.curSbmc = sbmc;
        // _this.curSbbh = sbbh;
        _this.getAlarmNum(sbbh);
      }else if(sbmc.includes("摄像头")){
        let tdh = _this.optionKVArray(_this.tdhList,sbbh);
        window.location.href = "/mobile/videoNewDp?tdh="+tdh;
      }else {
        Toast.error("该站点没有数据！");
      }
      // if(_this.zdysbbhList.includes(sbbh)){
      //   //_this.curSbmc = sbmc;
      //   _this.curSbbh = sbbh;
      //   _this.leftBottomData();
      //   _this.rightBottomData();
      //   _this.rightTopData();
      //   _this.rightCenterData();
      // }else{
      //   Toast.error("该站点没有环境数据！");
      // }
    },
    getAlarmNum(sbbh){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getHourCxcsBySbbh',{'sbbh':sbbh}).then((res)=>{
        $("#alarm-num-modal").modal("show");
        let response = res.data;
        let historyTem = response.content.history;
        let forecastTem = response.content.history;
        let history = [];
        let forecast = [];
        for(let i=0;i<historyTem.length;i++){
          history.push([historyTem[i].xs,historyTem[i].sumAlarmNum])
        }
        for(let i=0;i<forecastTem.length;i++){
          forecast.push([forecastTem[i].cxsj,forecastTem[i].cxcs])
        }
        _this.initAlarmNumEcharts(history,forecast);
      })
    },
    initAlarmNumEcharts(history, forecast){
      let _this = this;
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          name: '时间'
        },
        yAxis: {
          type: 'value',
          name: '次数'
        },
        series: [
          {
            type: 'line',
            data: history
          },
          {
            type: 'line',
            itemStyle: {
              color: "rgba(255, 0, 0, 1)"
            },
            lineStyle: {
              color: "rgba(245, 9, 9, 1)"
            },
            data: forecast
          }
        ]
      };
      setTimeout(function (){
        let echartsData = echarts.init(document.getElementById("alarmNumEcharts"));
        echartsData.setOption(option);
      },1000)
    },
    centerBottomData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getVideoEvent',{}).then((res)=>{
        let response = res.data;
        let eventDatas = response.content;
        let rolldata = []
        for(let i=0;i<eventDatas.length;i++){
          let item = eventDatas[i];
          let arrItem = [item.sbbh,item.kssj,item.jssj, `<div class="btn-detail" onclick="getPlayUrl('${item.sbbh}','${item.wjlj}')">查看视频</div>`];
          rolldata.push(arrItem);
        }
        this.$refs['scrollBoard'].updateRows(rolldata, 0);
      })
    },
    leftCenterData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getCurrentMeterData', {"bz":_this.curSbbh}).then((response)=>{
        _this.currentMeter = response.data.content;
      })
    },
    rightBottomData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getWaterQualityNewData', {"sbbh":_this.curSbbh,'stime':'2023-10-15'}).then((response)=>{
        let waterQualityNews = response.data.content;
        let xAxisDatas = [];
        let seriesData1 = [];
        let seriesData2 = [];
        let seriesData3 = [];
        let seriesData4 = [];
        for(let i=0;i<waterQualityNews.length;i++){
          let waterQualityNew = waterQualityNews[i];
          xAxisDatas.push(waterQualityNew.cjsj.substring(11,waterQualityNew.cjsj.length));
          seriesData1.push(waterQualityNew.oxidative);
          seriesData2.push(waterQualityNew.chlorophyll);
          seriesData3.push(waterQualityNew.ph);
          seriesData4.push(waterQualityNew.ad);
        }
        _this.initCenterBottomEchartData(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4);
      })
    },
    initCenterBottomEchartData(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4){
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ["溶解氧","叶绿素","ph","氨氮"],
          textStyle: {
            color: "#fff"
          }
        },
        grid: {
          bottom: '20px',
          top: '40px',
          left: '25px',
          right: '18px'
        },
        xAxis: {
          type: 'category',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          },
          data: xAxisDatas
        },
        yAxis: {
          type: 'value',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        series: [
          {
            name: '溶解氧',
            type: 'line',
            data: seriesData1,
            symbolSize: 7,
            lineStyle: {
              width: 6
            }
          },
          {
            name: '叶绿素',
            type: 'line',
            data: seriesData2,
            symbolSize: 7,
            lineStyle: {
              width: 6
            }
          },
          {
            name: 'ph',
            type: 'line',
            data: seriesData3,
            symbolSize: 7,
            lineStyle: {
              width: 6
            }
          },
          {
            name: '氨氮',
            type: 'line',
            data: seriesData4,
            symbolSize: 7,
            lineStyle: {
              width: 6
            },
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("rightBottomEchart"));
      echartsData.setOption(option);
    },
    leftBottomData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getTurbidityData', {"bz":_this.curSbbh}).then((response)=>{
        let turbiditys = response.data.content;
        let xAxisData = [];
        let data1 = [];
        let data2 = [];
        let data3 = [];
        for(let i=0;i<turbiditys.length;i++){
          let turbidity = turbiditys[i];
          xAxisData.push(turbidity.dateTime.substring(11,turbidity.dateTime.length));
          data1.push(turbidity.salinity);
          data2.push(turbidity.temperature);
          data3.push(turbidity.depth);
        }
        _this.initLeftBottomEchart(xAxisData,data1,data2,data3);
      })
    },
    initLeftBottomEchart(xAxisData,data1,data2,data3){
      let option = {
        grid: {
          bottom: '20px',
          top: '40px',
          left: '25px',
          right: '18px'
        },
        legend: {
          data: ['盐度','温度','深度'],
          textStyle: {
            color: "#fff"
          }
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: xAxisData,
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        yAxis: {
          splitLine: { show: false },
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        series: [
          {
            name: '盐度',
            type: 'line',
            data: data1,
            symbolSize: 7,
            lineStyle: {
              width: 6
            },
            itemStyle: {
              color: "blue"
            }
          },
          {
            name: '温度',
            type: 'line',
            data: data2,
            symbolSize: 7,
            lineStyle: {
              width: 6
            },
            itemStyle: {
              color: "green"
            }
          },
          {
            name: '深度',
            type: 'line',
            data: data3,
            symbolSize: 7,
            lineStyle: {
              width: 6
            },
            itemStyle: {
              color: "red"
            }
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("leftBottomEchart"));
      echartsData.setOption(option);
    },
    rightTopData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getMeteorologicalData', {"bz":_this.curSbbh}).then((response)=>{
        let meteorologicals = response.data.content;
        // let data = [];
        // let data1 = [];
        // let data2 = [];
        if(meteorologicals.length>0){
          _this.meteorological = meteorologicals[0];
        }
        // for(let i=0;i<meteorologicals.length;i++){
        //   let meteorological = meteorologicals[i];
        //   let dataItem = [];//风速和风向
        //   dataItem.push(meteorological.cjsj);
        //   dataItem.push(meteorological.speed);
        //   dataItem.push(meteorological.winddirection);
        //   data.push(dataItem);
        //   let dataItem1 = [];//温度
        //   dataItem1.push(meteorological.cjsj.substring(11,meteorological.cjsj.length));
        //   dataItem1.push(meteorological.temperature);
        //   data1.push(dataItem1)
        //   let dataItem2 = [];//湿度
        //   dataItem2.push(meteorological.cjsj.substring(11,meteorological.cjsj.length));
        //   dataItem2.push(meteorological.humidity);
        //   data2.push(dataItem2)
        // }
        // _this.initCenterBottomEchart(data);
        // _this.rightCenterEchart(data1,data2);
        //_this.rightBottomEchart(data2);
      })
    },
    initCenterBottomEchart(data){
      const directionMap = {};
      ['W', 'WSW', 'SW', 'SSW', 'S', 'SSE', 'SE', 'ESE', 'E', 'ENE', 'NE', 'NNE', 'N', 'NNW', 'NW', 'WNW'].forEach(function (name, index) {
        directionMap[name] = Math.PI / 8 * index;
      });
      const dims = {
        time: 0,
        windSpeed: 1,
        R: 2,
        minTemp: 3,
        maxTemp: 4
      };
      const arrowSize = 18;
      const renderArrow = function (param, api) {
        const point = api.coord([
          api.value(dims.time),
          api.value(dims.windSpeed)
        ]);
        return {
          type: 'path',
          shape: {
            pathData: 'M31 16l-15-15v9h-26v12h26v9z',
            x: -arrowSize / 2,
            y: -arrowSize / 2,
            width: arrowSize,
            height: arrowSize
          },
          rotation: directionMap[api.value(dims.R)],
          position: point,
          style: api.style({
            stroke: '#555',
            lineWidth: 1
          })
        };
      };
      let option = {
        tooltip: {
          trigger: 'axis',
          formatter: function (params) {
            return [
              echarts.format.formatTime(
                  'yyyy-MM-dd',
                  params[0].value[dims.time]
              ) +
              ' ' +
              echarts.format.formatTime('hh:mm:ss', params[0].value[dims.time]),
              '风速：' + params[0].value[dims.windSpeed],
              '风向：' + params[0].value[dims.R]
            ].join('<br>');
          }
        },
        grid: {
          left: '30px',
          right: '10px',
          bottom: '25px',
          top: '40px'
        },
        xAxis: {
          type: 'time',
          splitLine: {
            lineStyle: {
              color: '#ddd'
            }
          },
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        yAxis: [
          {
            name: '风速',
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            splitLine: {
              lineStyle: {
                color: '#ddd'
              }
            }
          }
        ],
        series: [
          // {
          //   type: 'custom',
          //   renderItem: renderArrow,
          //   encode: {
          //     x: dims.time,
          //     y: dims.windSpeed
          //   },
          //   data: data,
          //   z: 10
          // },
          {
            type: 'line',
            symbol: 'none',
            encode: {
              x: dims.time,
              y: dims.windSpeed
            },
            lineStyle: {
              color: '#aaa',
              type: 'dotted'
            },
            data: data,
            z: 1
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("centerBottomEchart"));
      echartsData.setOption(option);
    },
    rightCenterData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getWaveDataData', {"sbbh":_this.curSbbh,'stime':'2023-10-15'}).then((response)=>{
        let resp = response.data;
        let xAxisDatas = [];
        let seriesData1 = [];
        let seriesData2 = [];
        let seriesData3 = [];
        for(let i=0;i<resp.content.length;i++){
          let waveData = resp.content[i];
          xAxisDatas.push(waveData.cjsj.substring(11,waveData.cjsj.length));
          seriesData1.push(waveData.waveH);
          seriesData2.push(waveData.waveDirection);
          seriesData3.push(waveData.wavePeriod);
        }
        _this.rightCenterEchart(xAxisDatas,seriesData1,seriesData2,seriesData3);
      })
    },
    rightCenterEchart(xAxisDatas,seriesData1,seriesData2,seriesData3){
      let _this = this;
      let option = {
        grid: {
          bottom: '20px',
          top: '40px',
          left: '30px',
          right: '10px'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ["有效波高(m)","波向(°)","波周期"],
          textStyle: {
            color: "#fff"
          }
        },
        xAxis: {
          type: 'category',
          data: xAxisDatas,
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        yAxis: {
          type: 'value',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        series: [
          {
            name: '有效波高(m)',
            type: 'line',
            data: seriesData1,
            symbolSize: 7,
            lineStyle: {
              width: 6
            }
          },
          {
            name: '波向(°)',
            type: 'line',
            data: seriesData2,
            symbolSize: 7,
            lineStyle: {
              width: 6
            }
          },
          {
            name: '波周期',
            type: 'line',
            data: seriesData3,
            symbolSize: 7,
            lineStyle: {
              width: 6
            }
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("rightCenterEchart"));
      echartsData.setOption(option);
    },
    rightBottomEchart(data){
      let _this = this;
      let option = {
        grid: {
          bottom: '20px',
          top: '40px',
          left: '30px',
          right: '10px'
        },
        xAxis: {
          type: 'category',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        yAxis: {
          name: '湿度(%)',
          type: 'value',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        series: [
          {
            data: data,
            type: 'bar',
            itemStyle: {
              borderRadius: 50,
            }
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("rightBottomEchart"));
      echartsData.setOption(option);
    },
    getPlayUrl(sbbh,wjlj){
      let _this = this;
      $("#playbox").empty();
      let url = 'http://49.239.193.146:59088/FileInfo.asmx/GetPlayUrl';
      $.post(url,{"sbid": sbbh,"filename":wjlj.substring(wjlj.lastIndexOf("/")+1),"fbl":"1080","fhfs":"1"}, function (data, status) {
        if(status&&!(data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('不存在')||data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('文件大小为0'))){
          let video = document.createElement("video");
          video.setAttribute("width","100%");
          video.setAttribute("height","100%");
          video.setAttribute("controls","controls");
          video.setAttribute("autoplay","autoplay");
          if(Hls.isSupported()) {
            let hls = new Hls();
            hls.loadSource(data.getElementsByTagName('PlayUrl')[0].childNodes[0].nodeValue);
            hls.attachMedia(video);
            hls.on(Hls.Events.MANIFEST_PARSED,function() {
              video.play();
            });
          } else if (video.canPlayType('application/vnd.apple.mpegurl')) {
            video.src = data.getElementsByTagName('PlayUrl')[0].childNodes[0].nodeValue;
            video.addEventListener('loadedmetadata',function() {
              video.play();
            });
          }
          document.getElementById('playbox').appendChild(video);
          $("#video-modal").modal("show");
        }else{
          Toast.error("未找到源文件或文件大小为0，无法转码！");
        }
      })
    },
    optionKVArray(list, key){
      if (!list || !key) {
        return "";
      } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
          if (key === list[i]["key"]) {
            result = list[i]["value"];
          }
        }
        return result;
      }
    }
  }
}
</script>
<style scoped>
.page-first-div{
  width: 100%;
  height: 100%;
  display: flex;
}
.left-div{
  width: 30%;
  height: 100%;
}
.left-content-div{
  background-color: rgb(7,14,40);
  width: 96%;
  height: 93%;
  margin: auto;
}
.center-div{
  width: 40%;
  height: 100%;
}
.right-div{
  width: 30%;
  height: 100%;
}
.left-top{
  width: 100%;
  height: 20%;
  margin: 10px 0;
  background-color: #0F3A56;
  padding: 10px 0;
}
.left-center{
  width: 100%;
  height: 40%;
  background-color: #0F3A56;
  margin-bottom: 10px;
}
.left-bottom{
  width: 100%;
  height: 37.2%;
  background-color: #0F3A56;
}
.title-name-div{
  color: #fff;
  background-image: url("/static/image/environment/xhx.png");
  background-repeat: no-repeat;
  background-size: 90% 100%;
  background-position: center;
  height: 15%;
  font-size: 18px;
  font-weight: 600;
  letter-spacing: 1px;
}
.title-name-div span{
  margin-left: 5%;
  padding-top: 2%;
  display: block;
}
.left-top-content{
  height: 38%;
  display: flex;
  flex-direction: row;
  margin-top: 1%;
}
.content-box{
  height: 100%;
  width: 42.5%;
  margin-left: 5%;
  display: flex;
  background-color: rgb(14,141,252);
}
.content-box img{
  width: 18%;
  height: 70%;
  margin-top: 4%;
  margin-left: 4%;
}
.content-box div{
  color: #fff;
  margin: auto 0;
  padding-left: 5%;
}
.center-content-div{
  width: 90%;
  height: 88%;
  margin: auto;
}
.center-content-item-first{
  height: 10%;
  background-color: #043769;
  display: flex;
  flex-direction: row;
  color: #fff;
  text-align: center;
  margin-top: 1%;
}
.center-content-item{
  height: 12%;
  background: url("/static/image/environment/bghg.png") no-repeat;
  background-size: cover;
  margin-top: 0.5%;
  display: flex;
  flex-direction: row;
  color: #fff;
  text-align: center;
}
.bottom-content{
  height: 85%;
  width: 90%;
  margin: auto;
}
.dp-title{
  width: 100%;
  text-align: center;
  font-size: 30px;
  font-weight: 600;
  color: #fff;
  height: 83px;
  line-height: 83px;
  letter-spacing: 2px;
}
.center-content-bottom{
  height: calc(45vh - 115px);
  margin-top: 10px;
  background-color: #0F3A56;
}
.center-bottom-div{
  height: 85%;
  width: 90%;
  margin-left: 5%;
}
.right-top-content{
  height: 38%;
  width: 96%;
  display: flex;
  flex-direction: row;
}
.right-top-box{
  height: 100%;
  width: 32%;
  display: flex;
  background-image: url("/static/image/environment/rightTopBg.png");
}
.right-top-box img{
  height: 30px;
  width: 30px;
  margin: auto;
}
.right-top-box div{
  width: 70%;
  margin: auto;
  color: #fff;
}
/deep/.header{
  margin-top: 0 !important;
  margin-bottom: 0 !important;
}
</style>