<template>
  <div style="width: 1920px;height: 100vh;background-image: url('/static/image/environment/dpbg.png');background-size: 100% 100%;margin: auto">
    <div class="page-first-div">
      <div class="left-div">
        <div v-if="LOCAL_VIDEO" style="height: 4.1%;display: flex;flex-direction: row;align-items: center;margin-left: 20px;">
          <div v-on:click="mianNew()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer">
            主页
          </div>
          <div v-on:click="sgdp()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer;margin-left: 10px;">
            声光大屏
          </div>
          <div v-on:click="videoNew()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer;margin-left: 10px;">
            视频
          </div>
<!--          <div v-on:click="test()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer;margin-left: 10px;">-->
<!--            测试-->
<!--          </div>-->
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
                  <span><date style="width: 75%;padding-left: 0;" @methodName="changeDate" id-value="curdateid" :set-value="curDate"></date></span>
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
            <div class="title-name-div" style="height: 14%">
              <span style="padding-top:1%;">海流监测数据 <b style="color: red">{{zdysbList|optionKVArray(curSbbh)}}</b></span>
            </div>
            <div class="meter-box">
              <div class="meter-item" id="meterEchartsOne"></div>
              <div class="meter-item" id="meterEchartsTwo"></div>
              <div class="meter-item" id="meterEchartsThree"></div>
              <div class="meter-item" id="meterEchartsFour"></div>
            </div>
          </div>
          <div class="left-center-bottom">
            <div class="title-name-div" style="height: 17%;">
              <span>海浪数据 <b style="color: red">{{zdysbList|optionKVArray(curSbbh)}}</b></span>
            </div>
            <div class="bottom-content" style="display: flex;">
              <div class="wave-content">
                <div class="wave-box">
                  <div class="wave-item">
                    <div class="wave-echarts" id="wave-level-echarts"></div>
                    <div class="wave-text">海况-{{level}}级</div>
                  </div>
                  <div class="wave-item">
                    <div class="wave-echarts" id="wave-direction-echarts"></div>
                    <div class="wave-text">海浪-{{waveName}}</div>
                  </div>
                </div>
                <div class="wave-text">波浪周期-{{wavePeriod}}</div>
              </div>
              <div class="wind-direction-content" id="wind-direction-echarts"></div>
            </div>
          </div>
          <div class="left-bottom">
            <div class="title-name-div" style="height: 17%;">
              <span>温盐度浊度仪数据 <b style="color: red">{{zdysbList|optionKVArray(curSbbh)}}</b></span>
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
          <img :src="tempUrl" style="width: 100%;height: 100%;"/>
        </div>
      </div>
      <div class="right-div">
        <div class="left-content-div" style="margin-top: 8%;">
          <div class="right-top">
            <div class="title-name-div" style="height: 5%;">
              <span style="padding-top:0%;">气象数据 <b style="color: red">保护区楼顶气象站</b></span>
            </div>
            <div class="right-top-content" id="temperature-echart"></div>
            <div class="right-top-content" id="speed-echart"></div>
            <div class="right-top-content" id="humidity-echart"></div>
          </div>
          <div class="left-bottom">
            <div class="title-name-div" style="height:17%;">
              <span style="padding-top: 1%;">水质数据 <b style="color: red">{{zdysbList|optionKVArray(curSbbh)}}</b></span>
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
import Date from "../../components/date";
export default {
  name: 'environment-dp',
  components:{EquipmentAMap,Date},
  data: function (){
    return {
      LOCAL_VIDEO:LOCAL_VIDEO,
      curDate:'',
      heightMax:510,
      curSbbh:'RPCDA4016',
      curSbmc:'16号航标',
      currentMeter:{},
      meteorological:{},
      intervalId:null,
      zdysbList:[
        {key:"RPCDA4013", value:"1号航标"},
        {key:"RPCDA4004", value:"2号航标"},
        {key:"RPCDA4005", value:"3号航标"},
        {key:"RPCDA4012", value:"4号航标"},
        {key:"RPCDA4003", value:"5号航标"},
        {key:"RPCDA4006", value:"6号航标"},
        {key:"RPCDA4009", value:"7号航标"},
        {key:"RPCDA4001", value:"8号航标"},
        {key:"RPCDA4007", value:"9号航标"},
        {key:"RPCDA4010", value:"10号航标"},
        {key:"RPCDA4008", value:"11号航标"},
        {key:"RPCDA4011", value:"12号航标"},
        {key:"RPCDA4015", value:"13号航标"},
        {key:"RPCDA4014", value:"14号航标"},
        {key:"RPCDA4002", value:"15号航标"},
        {key:"RPCDA4016", value:"16号航标"},
        // {key:"RPCDA4000", value:"16号航标"}
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
        {key:"1", value:"0"},
        {key:"2", value:"2"},
        {key:"3", value:"4"},
        {key:"4", value:"6"},
        {key:"5", value:"8"},
        {key:"6", value:"10"},
        {key:"7", value:"12"},
        {key:"8", value:"14"},
        {key:"9", value:"16"},
        {key:"10", value:"18"}
      ],
      dtimeList:['2023-10-25','2023-10-24','2023-10-23','2023-10-22','2023-10-21','2023-10-20','2023-10-19','2023-10-18','2023-10-17','2023-10-16','2023-10-15'],
      dtimeIndex:0,
      dtime:'',
      tempUrl:'',
      level:null,
      waveName:null,
      wavePeriod:null,
      meterUSpeedData:null,
      meterVSpeedData:null,
      meterSpeedData:null,
      meterDirectionData:null,
      waveLevelData:null,
      waveDirectionData:null,
      winddirectionData:null,
      speedData:null,
      temperatureData:null
    }
  },
  mounted() {
    let _this = this;
    _this.curDate = Tool.dateFormat("yyyy-MM-dd");
    // if(_this.$xhHisData.rq==_this.curDate){
    //   _this.dtime = _this.dtimeList[_this.$xhHisData.index];
    // }else{
    //   _this.$xhHisData.rq = _this.curDate;
    //   _this.$xhHisData.index++;
    //   if(_this.$xhHisData.index>10){
    //     _this.$xhHisData.index = 0;
    //   }
    //   _this.dtime = _this.dtimeList[_this.$xhHisData.index];
    // }
    _this.getSeaSurfacePic();
    _this.leftCenterData();//海流计
    _this.leftBottomData();
    _this.rightBottomData();
    _this.rightTopData();//气象数据-历史平均数据
    _this.rightTopDirectionData();//气象数据-实时方向数据
    _this.rightCenterData();
    //_this.centerBottomData();
    _this.dataRefreh();
    window.getPlayUrl = _this.getPlayUrl;
  },
  methods: {
    //修改当前日期
    changeDate(date){
      let _this = this;
      if(_this.curDate!=date){
        _this.curDate = date;
        _this.leftCenterData();//海流计
        _this.leftBottomData();//温盐深浊度仪
        _this.rightTopData();//气象数据-历史平均数据
        _this.rightCenterData();//海浪数据
        _this.rightBottomData();//水质数据
      }
    },
    //获取海表盐度图片
    getSeaSurfacePic(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getSeaSurfacePic', {}).then((response)=>{
        _this.tempUrl = response.data.content.imgUrl;
      })
    },
    //修改设备编号
    changeData(){
      let _this = this;
      _this.leftCenterData();//海流计
      _this.leftBottomData();//温盐深浊度仪
      _this.rightCenterData();//海浪数据
      _this.rightBottomData();//水质数据
      _this.rightTopData();//气象数据-历史平均数据
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
        console.log("刷新" + Tool.dateFormat("yyyy-MM-dd"));
        _this.leftCenterData();//海流计
        _this.rightCenterData();//海浪数据
        _this.rightTopDirectionData();//气象数据-实时方向数据
      }, 1200000);//20分钟
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
    test(){
      let _this = this;
      _this.clear();
      window.location.href = "/mobile/videoDpSecond";
    },
    sgdp(){
      let _this = this;
      _this.clear();
      window.location.href = "/mobile/videoDpSecond";
    },
    videoNew(){
      let _this = this;
      _this.clear();
      window.location.href = "/mobile/videoNewDp?tdh=0&sbbh=1";
    },
    clickMapPoint(sbmc,sbbh){
      let _this = this;
      if(sbbh.includes("RPCD")){
        // _this.curSbmc = sbmc;
        // _this.curSbbh = sbbh;
        _this.getAlarmNum(sbbh);
      }else if(sbmc.includes("摄像头")){
        let tdh = _this.optionKVArray(_this.tdhList,sbbh);
        window.location.href = "/mobile/videoNewDp?tdh="+tdh+"&sbbh="+sbbh;
      }else {
        Toast.error("该站点没有数据！");
      }
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
        echartsData.setOption(option,true);
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
    //海流计
    leftCenterData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getCurrentMeterDataXs', {"bz":_this.curSbbh,"stime":_this.curDate}).then((response)=>{
        _this.currentMeter = response.data.content;
        _this.initLeftCenterEchartData();
      })
    },
    initLeftCenterEchartData(){
        let _this = this;
        if(!Tool.isEmpty(_this.currentMeter.uspeed)){
          _this.currentMeter.uspeed = Number(_this.currentMeter.uspeed).toFixed(3);
          let option1 = _this.initOption("东西向流速",_this.currentMeter.uspeed,-1,1,'#FFAB91','#FD7347');
          if(_this.meterUSpeedData){
            _this.meterUSpeedData.dispose();
          }
          _this.meterUSpeedData = echarts.init(document.getElementById("meterEchartsOne"));
          _this.meterUSpeedData.setOption(option1,true);
        }else{
          let option1 = _this.initOption("东西向流速",null,-1,1,'#FFAB91','#FD7347');
          if(_this.meterUSpeedData){
            _this.meterUSpeedData.dispose();
          }
          _this.meterUSpeedData = echarts.init(document.getElementById("meterEchartsOne"));
          _this.meterUSpeedData.setOption(option1,true);
        }
        if(!Tool.isEmpty(_this.currentMeter.vspeed)){
          _this.currentMeter.vspeed = Number(_this.currentMeter.vspeed).toFixed(3);
          let option2 = _this.initOption("南北向流速",_this.currentMeter.vspeed,-3,3,'#FFAB91','#FD7347');
          if(_this.meterVSpeedData){
            _this.meterVSpeedData.dispose();
          }
          _this.meterVSpeedData = echarts.init(document.getElementById("meterEchartsTwo"));
          _this.meterVSpeedData.setOption(option2,true);
        }else{
          let option2 = _this.initOption("南北向流速",null,-3,3,'#FFAB91','#FD7347');
          if(_this.meterVSpeedData){
            _this.meterVSpeedData.dispose();
          }
          _this.meterVSpeedData = echarts.init(document.getElementById("meterEchartsTwo"));
          _this.meterVSpeedData.setOption(option2,true);
        }
        if(!Tool.isEmpty(_this.currentMeter.absSpeed)){
          _this.currentMeter.absSpeed = Number(_this.currentMeter.absSpeed).toFixed(3);
          let option3 = _this.initOption("流速",_this.currentMeter.absSpeed,-3,3,'#FFAB91','#FD7347');
          if(_this.meterSpeedData){
            _this.meterSpeedData.dispose();
          }
          _this.meterSpeedData = echarts.init(document.getElementById("meterEchartsThree"));
          _this.meterSpeedData.setOption(option3,true);
        }else{
          let option3 = _this.initOption("流速",null,-3,3,'#FFAB91','#FD7347');
          if(_this.meterSpeedData){
            _this.meterSpeedData.dispose();
          }
          _this.meterSpeedData = echarts.init(document.getElementById("meterEchartsThree"));
          _this.meterSpeedData.setOption(option3,true);
        }
        if(!Tool.isEmpty(_this.currentMeter.direction)){
          _this.currentMeter.direction = Number(_this.currentMeter.direction).toFixed(3);
          let option4 = _this.luopanOption(_this.currentMeter.direction,"流向");
          if(_this.meterDirectionData){
            _this.meterDirectionData.dispose();
          }
          _this.meterDirectionData = echarts.init(document.getElementById("meterEchartsFour"));
          _this.meterDirectionData.setOption(option4,true);
        }else {
          let option4 = _this.luopanOption(null,"流向");
          if(_this.meterDirectionData){
            _this.meterDirectionData.dispose();
          }
          _this.meterDirectionData = echarts.init(document.getElementById("meterEchartsFour"));
          _this.meterDirectionData.setOption(option4,true);
        }
    },
    initOption(name,data,min,max,color1,color2){
      return {
        series: [
          {
            type: 'gauge',
            center: ['50%', '70%'],
            radius: "94%",
            startAngle: 200,
            endAngle: -20,
            min: min,
            max: max,
            itemStyle: {
              color: color1
            },
            progress: {
              show: true,
              width: 8
            },
            pointer: {
              show: false
            },
            axisLine: {
              lineStyle: {
                width: 16
              }
            },
            axisTick: {
              show: false
            },
            splitLine: {
              distance: -22,
              length: 5,
              lineStyle: {
                width: 1,
                color: '#fff'
              }
            },
            axisLabel: {
              distance: -5,
              color: '#fff',
              fontSize: 8
            },
            detail:{
              fontSize:12,
              offsetCenter:[0,0],
              color:'#fff'
            },
            title: {
              color: '#fff',
              fontSize: 10
            },
            data: [
              {
                name: name,
                value: data
              }
            ]
          },
          {
            type: 'gauge',
            center: ['50%', '70%'],
            radius: "94%",
            startAngle: 200,
            endAngle: -20,
            min: min,
            max: max,
            itemStyle: {
              color: color2
            },
            progress: {
              show: true,
              width: 4
            },
            pointer: {
              show: false
            },
            axisLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            detail: {
              show: false
            },
            data: [
              {
                value: data
              }
            ]
          }
        ]
      };
    },
    luopanOption(data,name){
      return {
        series: [{
          type: 'gauge',
          detail: {
            textStyle: {
              fontSize: 10,
              fontWeight: 'bolder',
              color: '#fff'
            }
          },
          startAngle: 90,
          splitNumber: 8,
          endAngle: 450,
          min: 0,
          max: 360,
          axisLabel: {
            color: '#fff',
            fontSize: 10,
            distance: -35,
            formatter: function (value) {
              const directions = ['北', '东北', '东', '东南', '南', '西南', '西', '西北'];
              const index = Math.floor((value + 22.5) / 45) % 8; // 计算当前值对应的方向索引，考虑ECharts的0度在右侧，需要调整
              return directions[index];
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: false
          },
          pointer: {
            width: 2
          },
          title: {
            color: '#fff',
            fontSize: 10
          },
          data: [{
            value: data,
            name: name
          }],
          radius: "70%"
        }]
      };
    },
    //水质数据
    rightBottomData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getWaterQualityNewDataRq', {"sbbh":_this.curSbbh,"stime":_this.curDate}).then((response)=>{
        let waterQualityNews = response.data.content;
        let xAxisDatas = [];
        let seriesData1 = [];
        let seriesData2 = [];
        let seriesData3 = [];
        let seriesData4 = [];
        for(let i=0;i<waterQualityNews.length;i++){
          let waterQualityNew = waterQualityNews[i];
          xAxisDatas.push(waterQualityNew.cjsj.substring(0,10));
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
          data: ["溶解氧","ph",'叶绿素'],
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
            name: '溶解氧',
            type: 'line',
            data: seriesData1,
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
            name: '叶绿素',
            type: 'line',
            data: seriesData2,
            symbolSize: 7,
            lineStyle: {
              width: 6
            }
          },
          // {
          //   name: '氨氮',
          //   type: 'line',
          //   data: seriesData4,
          //   symbolSize: 7,
          //   lineStyle: {
          //     width: 6
          //   },
          // }
        ]
      };
      let echartsData = echarts.init(document.getElementById("rightBottomEchart"));
      echartsData.setOption(option,true);
    },
    //温盐深浊度仪
    leftBottomData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getTurbidityDataRq', {"bz":_this.curSbbh,"stime":_this.curDate}).then((response)=>{
        let turbiditys = response.data.content;
        let xAxisData = [];
        let data1 = [];
        let data2 = [];
        let data3 = [];
        for(let i=0;i<turbiditys.length;i++){
          let turbidity = turbiditys[i];
          xAxisData.push(turbidity.dateTime.substring(0,10));
          data1.push(turbidity.salinity);
          data2.push(turbidity.temperature);
          data3.push(turbidity.turibidityL);
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
          data: ['温度','盐度','浊度'],
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
            name: '浊度',
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
      echartsData.setOption(option,true);
    },
    //气象数据
    rightTopDirectionData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getMeteorologicalData', {"bz":"RPCDA4000","stime":_this.curDate}).then((response)=>{
        let meteorologicals = response.data.content;
        if(meteorologicals.length>0){
          _this.meteorological = meteorologicals[0];
          _this.meteorological.solarintensity = _this.meteorological.solarintensity?Number(_this.meteorological.solarintensity).toFixed(3):_this.meteorological.solarintensity;
          _this.meteorological.humidity = _this.meteorological.humidity?Number(_this.meteorological.humidity).toFixed(3):_this.meteorological.humidity;
          _this.meteorological.winddirection = _this.meteorological.winddirection?Number(_this.meteorological.winddirection).toFixed(3):_this.meteorological.winddirection;
          _this.meteorological.pressure = _this.meteorological.pressure?Number(_this.meteorological.pressure).toFixed(3):_this.meteorological.pressure;
          _this.meteorological.temperature = _this.meteorological.temperature?Number(_this.meteorological.temperature).toFixed(3):_this.meteorological.temperature;
          _this.meteorological.speed = _this.meteorological.speed?Number(_this.meteorological.speed).toFixed(3):_this.meteorological.speed;
        }
        let option4 = _this.luopanOption(_this.meteorological.winddirection,"风向");
        if(_this.winddirectionData){
          _this.winddirectionData.dispose();
        }
        _this.winddirectionData = echarts.init(document.getElementById("wind-direction-echarts"));
        _this.winddirectionData.setOption(option4,true);
      })
    },
    rightTopData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getMeteorologicalDataAvg', {"bz":"RPCDA4000","stime":_this.curDate,"dayCount":-29}).then((response)=>{
        let meteorologicals = response.data.content;
        let rqData=[];
        let temperature=[];
        let speed=[];
        let humidity=[];
        for(let i=0;i<meteorologicals.length;i++){
          let obj = meteorologicals[i];
          rqData.push(obj.rq);
          temperature.push(obj.temperature);
          speed.push(obj.speed);
          humidity.push(obj.humidity);
        }
        let option1 = _this.meteorologicalOption("每日平均温度",rqData,temperature,'rgb(255, 173, 134)','rgb(255, 0, 0)');
        if(_this.temperatureData){
          _this.temperatureData.dispose();
        }
        _this.temperatureData = echarts.init(document.getElementById("temperature-echart"));
        _this.temperatureData.setOption(option1,true);
        let option2 = _this.meteorologicalOption("每日平均风速",rqData,speed,'rgb(128, 255, 165)','rgb(1, 191, 236)');
        if(_this.speedData){
          _this.speedData.dispose();
        }
        _this.speedData = echarts.init(document.getElementById("speed-echart"));
        _this.speedData.setOption(option2,true);
        let option3 = _this.meteorologicalOption("每日平均湿度",rqData,humidity,'rgb(116, 183, 231)','rgb(27, 125, 221)');
        if(_this.humidityData){
          _this.humidityData.dispose();
        }
        _this.humidityData = echarts.init(document.getElementById("humidity-echart"));
        _this.humidityData.setOption(option3,true);
      })
    },
    meteorologicalOption(titleText,xAxisData,yAxisData,color,color1){
      return {
        title: {
          left: 'center',
          text: titleText,
          textStyle: {
            color:'#fff'
          }
        },
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          bottom: '20px',
          top: '40px',
          left: '30px',
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
          data: xAxisData
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '100%'],
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
            type: 'line',
            smooth: true,
            symbol: 'none',
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: color1
                },
                {
                  offset: 1,
                  color: color
                }
              ])
            },
            data:yAxisData,
            itemStyle: {
              color: color1
            },
            lineStyle: {
              width: 3
            }
          }
        ]
      };
    },
    //海浪数据
    rightCenterData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getWaveDataXs', {"sbbh":_this.curSbbh,"stime":_this.curDate}).then((response)=>{
        let resp = response.data.content;
        if(resp!=null){
          if(resp.wavePeriod){
            _this.wavePeriod = Number(resp.wavePeriod).toFixed(3);
          }
          if(resp.waveH==0){
            this.level = 0;
            this.waveName = "无浪";
          }else if(resp.waveH<0.1){
            this.level = 1;
            this.waveName = "微浪";
          }else if(resp.waveH>=0.1 && resp.waveH<0.5){
            this.level = 2;
            this.waveName = "小浪";
          }else if(resp.waveH>=0.5 && resp.waveH<1.25){
            this.level = 3;
            this.waveName = "轻浪";
          }else if(resp.waveH>=1.25 && resp.waveH<2.5){
            this.level = 4;
            this.waveName = "中浪";
          }else if(resp.waveH>=2.5 && resp.waveH<4.0){
            this.level = 5;
            this.waveName = "大浪";
          }else if(resp.waveH>=4.0 && resp.waveH<6.0){
            this.level = 6;
            this.waveName = "巨浪";
          }else if(resp.waveH>=6.0 && resp.waveH<9.0){
            this.level = 7;
            this.waveName = "狂浪";
          }else if(resp.waveH>=9.0 && resp.waveH<14.0){
            this.level = 8;
            this.waveName = "狂涛";
          }else if(resp.waveH>=14.0){
            this.level = 9;
            this.waveName = "怒涛";
          }
          let option1 = _this.initOption("",_this.level,-1,10,'#FFAB91','#FD7347');
          if(_this.waveLevelData){
            _this.waveLevelData.dispose();
          }
          _this.waveLevelData = echarts.init(document.getElementById("wave-level-echarts"));
          _this.waveLevelData.setOption(option1,true);
          if(resp.waveDirection){
            resp.waveDirection = Number(resp.waveDirection).toFixed(3);
          }
          let option4 = _this.luopanOption(resp.waveDirection,"流向");
          if(_this.waveDirectionData){
            _this.waveDirectionData.dispose();
          }
          _this.waveDirectionData = echarts.init(document.getElementById("wave-direction-echarts"));
          _this.waveDirectionData.setOption(option4,true);
        }
        // let resp = response.data;
        // let xAxisDatas = [];
        // let seriesData1 = [];
        // let seriesData2 = [];
        // let seriesData3 = [];
        // for(let i=0;i<resp.content.length;i++){
        //   let waveData = resp.content[i];
        //   xAxisDatas.push(waveData.cjsj.substring(11,waveData.cjsj.length));
        //   seriesData1.push(waveData.waveH);
        //   seriesData2.push(waveData.waveDirection);
        //   seriesData3.push(waveData.wavePeriod);
        // }
        // _this.rightCenterEchart(xAxisDatas,seriesData1,seriesData2,seriesData3);
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
      echartsData.setOption(option,true);
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
      echartsData.setOption(option,true);
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
  height: 30%;
  background-color: #0F3A56;
  margin-bottom: 10px;
}
.left-center-bottom{
  width: 100%;
  height: 22%;
  background-color: #0F3A56;
  margin-bottom: 10px;
}
.left-bottom{
  width: 100%;
  height: 25%;
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
  padding-top: 1%;
  display: block;
}
.meter-box{
  height: 86%;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}
.meter-item{
  width: 50%;
  height: 50%;
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
.wave-content{
  width: 66%;
  height: 90%;
}
.wind-direction-content{
  width: 33%;
  height: 90%;
}
.wave-box{
  width: 100%;
  height: 99%;
  display: flex;
}
.wave-item{
  width: 50%;
  height: 100%;
}
.wave-echarts{
  width: 100%;
  height: 90%;
}
.wave-text{
  height: 10%;
  font-size: 12px;
  color: #fff;
  text-align: center;
}
.center-content-item-first{
  height: 14%;
  background-color: #043769;
  display: flex;
  flex-direction: row;
  color: #fff;
  text-align: center;
  margin-top: 1%;
}
.center-content-item{
  height: 15%;
  background: url("/static/image/environment/bghg.png") no-repeat;
  background-size: cover;
  margin-top: 0.5%;
  display: flex;
  flex-direction: row;
  color: #fff;
  text-align: center;
}
.bottom-content{
  height: 82%;
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
  height: calc(60vh - 115px);
  margin-top: 10px;
  background-color: #0F3A56;
}
.center-bottom-div{
  height: 82%;
  width: 90%;
  margin-left: 5%;
}
.right-top{
  width: 100%;
  height: 74.5%;
  margin: 10px 0;
  background-color: #0F3A56;
  padding: 10px 0;
}
.right-top-content{
  height: 32%;
  width: 96%;
  margin: auto;
}
.right-top-box{
  height: 100%;
  width: 32%;
  display: flex;
  background-image: url("/static/image/environment/rightTopBg.png");
  border-radius: 10px;
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