<template>
  <div class="wrap" id="app">
    <header style="position: relative;">
      <img src="/largemonitors/assets/imgs/headertitle.png" style="position: absolute;left: 30%;top:20px;width: 750px;">
      <div class="lefttitle">
        <img src="/largemonitors/assets/imgs/左上角title.png" alt="">
        <span>
          <div @click="chooseProject" style="cursor: pointer;">主页</div>
        </span>
      </div>
    </header>
    <div class="content">
      <div class="pain">
        <div class="h25">
          <div class="imgs">
            <video width="100%" height="100%" autoplay="autoplay" loop="loop" controls>
              <source class="video" title="主监控位" src="/video/12.mp4"/>
            </video>
          </div>
        </div>
        <div class="h37">
          <div class="imgs" v-if="canPlay">
            <div id="playbox">
            </div>
          </div>
          <div class="imgs" v-if="!canPlay">

          </div>
        </div>
        <div class="h37">
          <div class="imgs">
            <swiper :list="swiperData" id="ceshi" style="text-align: center;width: 438px ;height: 290px;"></swiper>
          </div>
        </div>
      </div>
      <div class="bcenter">
        <div class="h63">
          <EquipmentAMap v-bind:height-max="heightMax" :click-map-point="clickMapPoint"></EquipmentAMap>
        </div>
        <div class="h37">
          <span>分析数据</span>
          <div class="imgs" style="padding: 25px;">
            <dv-scroll-board :config="config" style="width:100% ;height:100%" ref="scrollBoard"/>
          </div>
        </div>
      </div>
      <div class="pain">
        <div class="h25">
          <div class="imgs">
            <div>
              <div style="float: left;color: #fff">
                <div class="radio" style="margin-top: 3px;margin-left: 10px;font-size: 12px;">
                  <label style="width: 57px;">
                    <input name="form-top-radio" type="radio"  value="1" v-model="topDayType" class="ace" />
                    <span class="lbl bigger-120">上月</span>
                  </label>
                  <label style="width: 57px;">
                    <input name="form-top-radio" type="radio"  value="2" v-model="topDayType" class="ace" />
                    <span class="lbl bigger-120">本月</span>
                  </label>
                  <label style="width: 57px;">
                    <input name="form-top-radio" type="radio"  value="3" v-model="topDayType" class="ace" />
                    <span class="lbl bigger-120">本周</span>
                  </label>
                  <label style="width: 57px;">
                    <input name="form-top-radio" type="radio"  value="4" v-model="topDayType" class="ace" />
                    <span class="lbl bigger-120">当天</span>
                  </label>
                </div>
              </div>
              <div style="margin-bottom: 10px;width: 30%;float: right;">
                <select v-model="curTopSbbh" @change="getRightTopData" class="form-control" style="background-color: #13225E;color: #fff;border-color: #34B9DF;">
                  <option value="">全部</option>
                  <option v-for="item in devices" :value="item.sbsn">{{item.sbmc}}</option>
                </select>
              </div>
            </div>
            <div style="display: flex;justify-content: space-around;align-items: center;clear: both;">
              <div v-for="(item,index) in right1" :key="index" style="position: relative;">
                <el-progress :width="100" :stroke-width="5" stroke-linecap="round" type="circle"
                             :percentage="getTotal(item.value)" :color="customColorMethod(item.value)"
                             :show-text="false">
                </el-progress>
                <div :style="`width:100%;text-align:center;position: absolute;left: 50%;top: 50%;transform: translate(-50%,-50%);color:${customColorMethod(item.value)}`">
                  {{getFormat(item.value)}}
                </div>
                <div style="width: 100%;position: absolute;
                            bottom: -20px;
                            font-size: 12px;
                            color: #fff;text-align: center;">{{item.label}}</div>
              </div>
            </div>
          </div>
        </div>

        <div class=" h37">
          <span>侦测事件统计（以天为单位）</span>
          <div class="imgs">
            <div style="float: left;color: #fff">
              <div class="radio" style="margin-top: 10px;margin-left: 10px;font-size: 12px;">
                <label style="width: 80px;">
                  <input name="form-event-radio" type="radio"  value="1" v-model="eventDayType" class="ace" />
                  <span class="lbl bigger-120">前30天</span>
                </label>
                <label style="width: 80px;">
                  <input name="form-event-radio" type="radio"  value="2" v-model="eventDayType" class="ace" />
                  <span class="lbl bigger-120">前15天</span>
                </label>
                <label style="width: 80px;">
                  <input name="form-event-radio" type="radio"  value="3" v-model="eventDayType" class="ace" />
                  <span class="lbl bigger-120">前7天</span>
                </label>
              </div>
            </div>
            <div style="width: 30%;float: right;">
              <select v-model="curEventSbbh" @change="getAlarmEventData" class="form-control" style="background-color: #13225E;color: #fff;border-color: #34B9DF;">
                <option value="">全部</option>
                <option v-for="item in devices" :value="item.sbsn">{{item.sbmc}}</option>
              </select>
            </div>
            <div id="barChart" style="width: 438px ;height: 300px;clear: both;"></div>
          </div>
        </div>
        <div class="h37">
          <div class="imgs">
            <div style="width: 30%;float: right;">
              <select v-model="curBottomSbbh" @change="getPointer" class="form-control" style="background-color: #13225E;color: #fff;border-color: #34B9DF;">
                <option v-for="item in devices" :value="item.sbsn">{{item.sbmc}}</option>
              </select>
            </div>
            <div style="clear: both;height: 60%;display: flex;padding-bottom: 5px;margin-top: 10%;">
              <div style="width: 50%;height: 100%;">
                <div style="text-align: center;font-size: 12px;color: #fff;">鱼类指针</div>
                <div id="gauge1" style="width: 100%;height: 100%;"></div>
              </div>
              <div style="width: 50%;height: 100%;">
                <div style="text-align: center;font-size: 12px;color: #fff;">豚类指针</div>
                <div id="gauge2" style="width: 100%;height: 100%;"></div>
              </div>
            </div>
            <div style="text-align: center;color: #fff;font-size: 24px;">
              平均累计声音暴露水平(24小时)
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>
<script>
import EquipmentAMap from "../monitor/equipmentAMap";
import Swiper from "../../components/swipe";
export default {
  name:'largemonitors-zj',
  components:{EquipmentAMap,Swiper},
  data: function (){
    return {
      config: {
        headerBGC: "#1F2C94",
        oddRowBGC: "#1F2C94",
        evenRowBGC: "",
        align: ['center', 'center', 'center', 'center'],
        header: ['设备位置', '开始时间', '结束时间', '头数', '声谱图'],
        data: []
      },
      config2: {
        headerBGC: "#13498F",
        oddRowBGC: "#0E2B6D",
        evenRowBGC: "",
        align: ['center', 'center', 'center', 'center'],
        header: [`<span style="color:#D1E27F">设备编号</span>`, `<span style="color:#D1E27F">开始时间</span></span>`, `<span style="color:#D1E27F">结束时间</span>`, `<span style="color:#D1E27F">头数</span>`],
        columnWidth: [150, 100, 100, 50],
        data: []
      },
      right1: [],
      lx:0,
      gz:0,
      zc:0,
      zs:0,
      intervalId:null,
      deptMap:[],
      equipmentFiles:[],
      srcpic:'',
      heightMax:'',
      devices:[],
      swiperData:[],
      videoData:[],
      videoHeight:140,
      a4Device:[],
      curTopSbbh:'RPCDA4001',
      curEventSbbh:'RPCDA4001',
      a4chart:null,
      curBottomSbbh:'RPCDA4001',
      topDayType:'4',
      eventDayType:'3',
      canPlay:false,
      timeHandle:null
    }
  },
  watch: {
    topDayType() {
      let _this = this;
      _this.getRightTopData();
    },
    eventDayType() {
      let _this = this;
      _this.getAlarmEventData();
    }
  },
  created() {
    let _this = this;
    //获取所有的设备，因为要用到设备的位置
    _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getDevice').then((res)=>{
      let response = res.data;
      _this.devices = response.content.list;
      _this.$forceUpdate();
      _this.a4Device = response.content.a4List;
      _this.$forceUpdate();
      //_this.getSevenDayEvent();//左下角最近7天的总事件
      _this.getAlarmEventData();//右中最近7天的总事件
      _this.getRightTopData();//右上角获取当日声学侦测次数、事件(群次)、捕食次数
      _this.getPointer();
      _this.getA4AndA2JL();//中间下方，获取A2设备和A4设备聚类
      _this.getVideoDataNew();
    })
    _this.deptMap = Tool.getDeptUser();
  },
  mounted() {
    let _this = this;
    _this.a4chart = echarts.init(document.getElementById('barChart'));
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.heightMax = h*0.6-20;
    _this.dataRefreh();
    window.getSwipeData = _this.getSwipeData;
    //window.getVideoData = _this.getVideoData;
  },
  methods: {
    clickMapPoint(sbbh){
      let _this = this;
      _this.curTopSbbh=sbbh;
      _this.curEventSbbh=sbbh;
      _this.curBottomSbbh=sbbh;
      //_this.getSevenDayEvent();//左下角最近7天的总事件
      _this.getAlarmEventData();//右中最近7天的总事件
      _this.getRightTopData();//右上角获取当日声学侦测次数、事件(群次)、捕食次数
      _this.getPointer();
    },
    getPointer(){
      let _this = this;
      _this.getPointerDay();
      _this.getPointerSecond();
    },
    getPointerSecond(){
      let _this = this;
      let obj = {
        "type":"zjglj",
        "sbbh":_this.curBottomSbbh
      };
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getPointerSecond',obj).then((res)=>{
        let response = res.data.content;
        if(!Tool.isEmpty(response)){
          _this.gauge1(response.decibelValue);
        }else{
          _this.gauge1(124);
        }
      })
    },
    gauge1(value) {
      let chart = echarts.init(document.getElementById('gauge1'))
      let option = {
        series: [
          {
            type: 'gauge',
            startAngle: 180,
            endAngle: 0,
            center: ['50%', '60%'],
            radius: '75%',
            min: 100,
            max: 240,
            axisLine: {
              lineStyle: {
                width: 6,
                color: [
                  [0.3, '#327662'],
                  [0.38, '#F7BA0B'],
                  [0.57, '#DD7907'],
                  [0.92, '#fd666d'],
                  [1, '#701F29']
                ]
              }
            },
            pointer: {
              icon: 'path://M2090.36389,615.30999 L2090.36389,615.30999 C2091.48372,615.30999 2092.40383,616.194028 2092.44859,617.312956 L2096.90698,728.755929 C2097.05155,732.369577 2094.2393,735.416212 2090.62566,735.56078 C2090.53845,735.564269 2090.45117,735.566014 2090.36389,735.566014 L2090.36389,735.566014 C2086.74736,735.566014 2083.81557,732.63423 2083.81557,729.017692 C2083.81557,728.930412 2083.81732,728.84314 2083.82081,728.755929 L2088.2792,617.312956 C2088.32396,616.194028 2089.24407,615.30999 2090.36389,615.30999 Z',
              itemStyle: {
                color: '#aa0210'
              }
            },
            axisTick: {
              show: false,
              length: 12,
              lineStyle: {
                color: 'auto',
                width: 2
              }
            },
            splitLine: {
              show: false,
              length: 20,
              lineStyle: {
                color: 'auto',
                width: 5
              }
            },
            axisLabel: {
              color: '#fff',
              fontSize: 10,
              distance: -50
            },
            title: {
              offsetCenter: [0, '-10%'],
              fontSize: 20
            },
            detail: {
              show: true,
              formatter: "{value}dB",
              // offsetCenter: [0, '15%'],
              fontSize: 14,
              fontWeight: "normal",
              color: '#fff'
            },
            data: [
              {
                value: value,
                name: ''
              }
            ]
          }
        ]
      };
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    getPointerDay(){
      let _this = this;
      let obj = {
        "type":"zjglj",
        "sbbh":_this.curBottomSbbh
      };
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getPointerDay',obj).then((res)=>{
        let response = res.data.content;
        if(!Tool.isEmpty(response)){
          _this.gauge3(response.decibelValue);
        }else{
          _this.gauge3(130);
        }
      })
    },
    gauge3(value) {
      let chart = echarts.init(document.getElementById('gauge2'))
      let option = {
        series: [
          {
            type: 'gauge',
            startAngle: 180,
            endAngle: 0,
            center: ['50%', '60%'],
            radius: '75%',
            min: 120,
            max: 220,
            axisLine: {
              lineStyle: {
                width: 6,
                color: [
                  [0.75, '#327662'],
                  [0.95, '#F7BA0B'],
                  [1, '#701F29']
                ]
              }
            },
            pointer: {
              icon: 'path://M2090.36389,615.30999 L2090.36389,615.30999 C2091.48372,615.30999 2092.40383,616.194028 2092.44859,617.312956 L2096.90698,728.755929 C2097.05155,732.369577 2094.2393,735.416212 2090.62566,735.56078 C2090.53845,735.564269 2090.45117,735.566014 2090.36389,735.566014 L2090.36389,735.566014 C2086.74736,735.566014 2083.81557,732.63423 2083.81557,729.017692 C2083.81557,728.930412 2083.81732,728.84314 2083.82081,728.755929 L2088.2792,617.312956 C2088.32396,616.194028 2089.24407,615.30999 2090.36389,615.30999 Z',
              itemStyle: {
                color: '#aa0210'
              }
            },
            axisTick: {
              show: false,
              length: 12,
              lineStyle: {
                color: 'auto',
                width: 2
              }
            },
            splitLine: {
              show: false,
              length: 20,
              lineStyle: {
                color: 'auto',
                width: 5
              }
            },
            axisLabel: {
              color: '#fff',
              fontSize: 10,
              distance: -50
            },
            title: {
              offsetCenter: [0, '-10%'],
              fontSize: 20
            },
            detail: {
              show: true,
              formatter: "{value}dB",
              // offsetCenter: [0, '15%'],
              fontSize: 14,
              fontWeight: "normal",
              color: '#fff'
            },
            data: [
              {
                value: value,
                name: ''
              }
            ]
          }
        ]
      };
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
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
        //_this.getSevenDayEvent();//左下角最近7天的总事件
        _this.getAlarmEventData();//右中最近7天的总事件
        _this.getA4AndA2JL();//中间下方，获取A2设备和A4设备聚类
        _this.getRightTopData();//右上角获取当日声学侦测次数、事件(群次)、捕食次数
        _this.getPointer();
        _this.getVideoDataNew();
      }, 600000);
    },
    // 停止定时器
    clear() {
      let _this = this;
      clearInterval(_this.intervalId); //清除计时器
      _this.intervalId = null; //设置为null
    },
    chooseProject(){
      window.location.href = "/admin/chooseProject";
    },
    toTyDp(){
      window.location.href = "/mobile/largemonitorsTy";
    },
    getAlarmEventData(){
      let _this = this;
      let obj = {};
      obj.sbbh = _this.curEventSbbh;
      if(_this.eventDayType=='3'){
        obj.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*7));
        obj.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
      }else if(_this.eventDayType=='2'){
        obj.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*15));
        obj.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
      }else if(_this.eventDayType=='1'){
        obj.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*30));
        obj.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
      }
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileAlarmEvent/echartsAlarmDataByDp',obj).then((response)=>{
        let resp = response.data;
        _this.initEchartData(resp.content);
      })
    },
    initEchartData(data){
      let _this = this;
      if(_this.a4chart){
        _this.a4chart.dispose();
        _this.a4chart=null;
      }
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          bottom: '30%'
        },
        xAxis: {
          show: true,
          name: '事件日期',
          nameLocation:'center',
          type: 'category',
          nameGap: 30,
          boundaryGap: true,
          data:data.xAixsData,
          axisLine:{
            show: true,
            symbol: ['none', 'path://M5,20 L5,5 L8,8 L5,2 L2,8 L5,5 L5.3,6 L5.3,20 '],
            symbolOffset: 10,
            symbolSize:[35, 35],
            lineStyle: {
              color: "rgba(251, 251, 251, 1)"
            }
          },
          axisLabel: {
            show: true,
            width: 100,
            overflow: 'break'
          }
        },
        yAxis: {
          show: true,
          name: '事件次数',
          type: 'value',
          nameGap: 30,
          boundaryGap: [0, '30%'],
          axisLine:{
            show: true,
            symbol: ['none', 'path://M5,20 L5,5 L8,8 L5,2 L2,8 L5,5 L5.3,6 L5.3,20 '],
            symbolOffset: 10,
            symbolSize:[35, 35],
            lineStyle: {
              color: "rgba(251, 251, 251, 1)"
            }
          }
        },
        series: [
          {
            type: 'bar',
            smooth: 0.6,
            symbolSize: 8,
            lineStyle: {
              color: '#5470C6',
              width: 2
            },
            data: data.yAixsData
          }
        ]
      };
      _this.a4chart = echarts.init(document.getElementById("barChart"));
      _this.a4chart.setOption(option);
    },
    getA4AndA2JL(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getEventData').then((res)=>{
        let response = res.data;
        let eventDatas = response.content;
        if(eventDatas && eventDatas.length>0){
          _this.getSwipeData(eventDatas[0].sbbh,eventDatas[0].kssj,eventDatas[0].jssj);
          //_this.getVideoData(eventDatas[0].sbbh,eventDatas[0].kssj,eventDatas[0].jssj);
        }
        _this.config.data = [];
        for(let i=0;i<eventDatas.length;i++){
          let item = eventDatas[i];
          let arrItem = [_this.optionKVArray(_this.devices,item.sbbh),item.kssj,item.jssj,Tool.isEmpty(item.ts)?"/":item.ts, `<div class="btn-detail" onclick="getSwipeData('${item.sbbh}','${item.kssj}','${item.jssj}')">查看声谱图</div>`];
          _this.config.data.push(arrItem);
        }
        this.$refs['scrollBoard'].updateRows(_this.config.data, 0);
      })
    },
    getSwipeData(sbbh,kssj,jssj){
      let _this = this;
      //_this.getVideoData(sbbh,kssj,jssj)
      let item = {
        "sbbh":sbbh,
        "kssj":kssj,
        "jssj":jssj
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getSwipeData',item).then((response)=>{
        let resp = response.data;
        let arr = resp.content;
        let temp = [];
        for(let i=0;i<arr.length;i++){
          let obj = {
            "id":arr[i].id,
            "imgUrl":arr[i].tplj
          }
          temp.push(obj);
          _this.$forceUpdate();
        }
        _this.swiperData=$.extend([], temp);
        _this.$forceUpdate();
      })
    },
    getVideoData(sbbh,kssj,jssj){
      let _this = this;
      let item = {
        "sbbh":sbbh,
        "kssj":kssj,
        "jssj":jssj
      }
      _this.videoData=[];
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getVideoData',item).then((response)=>{
        let resp = response.data;
        _this.videoData = resp.content;
        if(_this.videoData.length<2){
          _this.videoHeight = 280;
        }else{
          _this.videoHeight = 140;
        }
        _this.$forceUpdate();
      })
    },
    getVideoDataNew(){
      let _this = this;
      $("#playbox").empty();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getLastVedio').then((response)=>{
        let resp = response.data.content;
        if(resp){
          _this.getPlayUrl(resp.sbbh,resp.tplj.substring(resp.tplj.lastIndexOf("/")+1));
        }
        _this.$forceUpdate();
      })
    },
    getPlayUrl(sbid,filename){
      let _this = this;
      $.post("http://49.239.193.146:49082/FileInfo.asmx/GetPlayUrl",{"sbid": sbid,"filename":filename,"fbl":"1080","fhfs":"1"}, function (data, status) {
        if(status&&!data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('不存在')){
          if(_this.fileExists(data.getElementsByTagName('PlayUrl')[0].childNodes[0].nodeValue)){
            _this.canPlay = true;
            let video = document.createElement("video");
            video.setAttribute("width","440px");
            video.setAttribute("height","270px");
            video.setAttribute("controls","controls");
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
            _this.$nextTick(function () {
              document.getElementById('playbox').appendChild(video);
            });
          }else {
            clearTimeout(_this.timeHandle);
            _this.timeHandle = setTimeout(function (){_this.getPlayUrl(sbid,filename)}, 10000);
          }
        }else{
          console.log("视频源文件不存在："+sbid+"======"+filename);
        }
      })
    },
    fileExists(url) {
      let isFlag;
      $.ajax({
        url: url,
        async: false,
        type: 'HEAD',
        error: function () {
          isFlag=0;
          return;
        },
        success: function () {
          isFlag=1;
          return;
        }
      });
      if(isFlag==1){
        return true;
      }else{
        return false;
      }
    },
    optionMapKV(object, key) {
      if (!object || !key) {
        return "";
      } else {
        let result = "";
        for (let enums in object) {
          if (key === enums) {
            result = object[enums];
          }
        }
        return result;
      }
    },
    optionKVArray(list, key) {
      if (!list || !key) {
        return "";
      } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
          if (key === list[i]["sbsn"]) {
            result = list[i]["fzwz"];
          }
        }
        return result;
      }
    },
    getRightTopData(){
      let _this = this;
      let obj = {};
      obj.type = 'zjglj';
      obj.sbbh = _this.curTopSbbh;
      let url = "/monitor/admin/equipmentFileToday/statisticsAlarmNumsByTimeSum";
      if(_this.topDayType=='4'){
        obj.stime = moment().subtract(0, "days").format('YYYY-MM-DD');
        obj.etime = moment().subtract(0, "days").format('YYYY-MM-DD');
      }else{
        url = "/monitor/welcome/statisticsAlarmNumsByTimeSum";
        obj.topDayType=_this.topDayType;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + url, obj).then((response) => {
        let resp = response.data;
        let alarmDatas = resp.content;
        let zccs = { label: '当日声学侦测次数', value: alarmDatas.num==null?0:alarmDatas.num };
        let sjcs = { label: '当日事件(群次)', value: alarmDatas.nnm==null?0:alarmDatas.nnm };
        let bscs = { label: '当日捕食次数', value: alarmDatas.bnum==null?0:alarmDatas.bnum };
        _this.right1 = [zccs,sjcs,bscs];
      })
    },
    getTotal(val) {
      let total = 0
      this.right1.forEach((num) => {
        total += num.value
      })
      return ((val / total) * 100)
    },
    customColorMethod(val) {
      if (val < 70) {
        return '#00DDFF';
      } else if (val < 170) {
        return '#80FFA5';
      } else if (val < 2000) {
        return '#C94A6F';
      } else if (val > 2000) {
        return '#37A2FF';
      }
    },
    getFormat(item) {
      return item + '次'
    }
  }
}
</script>
<style scoped>

html,
body,
div,
span,
applet,
object,
iframe,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
a,
abbr,
acronym,
address,
big,
cite,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
s,
samp,
small,
strike,
strong,
sub,
sup,
tt,
var,
b,
u,
i,
center,
dl,
dt,
dd,
ol,
ul,
li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
embed,
figure,
figcaption,
footer,
header,
hgroup,
menu,
nav,
output,
ruby,
section,
summary,
time,
mark,
audio,
video {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  font-weight: normal;
  vertical-align: baseline;
}

/* HTML5 display-role reset for older browsers */
article,
aside,
details,
figcaption,
figure,
footer,
header,
hgroup,
menu,
nav,
section {
  display: block;
}

ol,
ul,
li {
  list-style: none;
}

blockquote,
q {
  quotes: none;
}

blockquote:before,
blockquote:after,
q:before,
q:after {
  content: '';
  content: none;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}

/* custom */
a {
  color: #7e8c8d;
  text-decoration: none;
  -webkit-backface-visibility: hidden;
}

::-webkit-scrollbar {
  width: 5px;
  height: 5px;
}

::-webkit-scrollbar-track-piece {
  background-color: rgba(0, 0, 0, 0.2);
  -webkit-border-radius: 6px;
}

::-webkit-scrollbar-thumb:vertical {
  height: 5px;
  background-color: rgba(125, 125, 125, 0.7);
  -webkit-border-radius: 6px;
}

::-webkit-scrollbar-thumb:horizontal {
  width: 5px;
  background-color: rgba(125, 125, 125, 0.7);
  -webkit-border-radius: 6px;
}

html,
body {
  width: 100%;
  font-family: 'syn', "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}

body {
  line-height: 1;
  -webkit-text-size-adjust: none;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
/deep/.imgs .dv-scroll-board .header{
  line-height: 0px;
  margin-bottom: 0px;
  margin-top: 0px;
  padding-bottom: 0px;
  border-bottom: none;
}
/deep/.el-progress-circle__track {
  stroke: #061073;
}
</style>