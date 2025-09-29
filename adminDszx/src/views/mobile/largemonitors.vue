<template>
  <div class="wrap" id="app">
    <header style="position: relative;">
      <img src="/largemonitors/assets/imgs/dszxTitle.png" style="position: absolute;left: 37.9%;top:20px;width: 450px;">
      <div class="lefttitle" v-if="LOCAL_TLBHQ">
        <img src="/largemonitors/assets/imgs/左上角title.png" alt="">
        <span>
          <div @click="chooseProject" style="cursor: pointer;">主页</div>
          <div style="margin: 0 10px;"> / </div>
          <div @click="toTlVideo" style="cursor: pointer;">视频</div>
        </span>
      </div>
      <div class="lefttitle" v-else>
        <img style="width: 250px" src="/largemonitors/assets/imgs/左上角title.png" alt="">
        <span>
          <div @click="chooseProject" style="cursor: pointer;">主页</div>
          <div style="margin: 0 10px;"> / </div>
          <div @click="toTyDp" style="cursor: pointer;">巡护</div>
          <div style="margin: 0 10px;"> / </div>
          <div @click="toAzimuthDp" style="cursor: pointer;">方位</div>
        </span>
      </div>
      <img class="right-top-img" src="/static/RPCD14.png"/>
    </header>
    <div class="content">
      <div class="pain">
        <div class="h25">
          <div class="imgs">
            <swiper-video id="swiperVideoTop" :list="swiperVideoTop" style="text-align: center;width: 100% ;height: 100%;"></swiper-video>
          </div>
        </div>
        <div class="h37">
          <div class="imgs">
            <swiper-video id="swiperVideo" :list="swiperVideo" style="text-align: center;width: 100% ;height: 100%;"></swiper-video>
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
          <EquipmentAMap v-bind:height-max="heightMax"></EquipmentAMap>
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
          <div class="imgs" style="display: flex;justify-content: space-around;align-items: center;">
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
        <div class=" h37">
          <span>侦测头次统计（以天为单位）</span>
          <div class="imgs">
            <div id="barChart" style="width: 438px ;height: 320px;"></div>
          </div>
        </div>
        <div class="h37">
          <div class="imgs" style="height: 100%;display: flex;padding-bottom: 5px;">
            <div style="width: 50%;height: 100%;position: relative;">
              <div id="gauge1" style="width: 100%;height: 100%;"></div>
              <span style="position: absolute;top: 0;left: 50%;transform:translateX(-50%);font-size: 12px;color: #fff;">鱼类指针</span>
              <img v-show="secondBling" src="/static/image/bjss.gif" style="position: absolute;top: 0;left: 70%;width: 20px;height: 20px;">
              <div style="position: absolute;left: 0;top: 65%;font-size: 12px;color: #fff;">
                <div class="flex">
                  <span style="color: #a4c656;width:30px;display: inline-block;padding: 2px 0;">142</span>
                  <span>造成鱼体暂时性听力阈值损伤</span>
                </div>
                <div class="flex">
                  <span style="color: #ffc74b;width:30px ;display: inline-block;padding: 2px 0;">153</span>
                  <span>造成鱼体压力荷尔蒙改变</span>
                </div>
                <div class="flex">
                  <span style="color: #f1843d;width:30px ;display: inline-block;padding: 2px 0;">180</span>
                  <span>造成鱼体永久性的听力阈值损失</span>
                </div>
                <div class="flex">
                  <span style="color: #ed483b;width:30px ;display: inline-block;padding: 2px 0;">228.9</span>
                  <span>造成鱼体死亡风险</span>
                </div>
              </div>
            </div>
            <div style="width: 50%;height: 100%;position: relative;">
              <div id="gauge2" style="width: 100%;height: 100%;"></div>
              <span style="position: absolute;top: 0;left: 50%;transform:translateX(-50%);font-size: 12px;color: #fff;">豚类指针</span>
              <img v-show="dayBling" src="/static/image/bjss.gif" style="position: absolute;top: 0;left: 70%;width: 20px;height: 20px;">
              <div style="position: absolute;left: 0;top: 65%;font-size: 12px;color: #fff;">
                <div class="flex">
                  <span style="color: #ffc74b;width:30px ;display: inline-block;padding: 2px 0;">180</span>
                  <span class="ellipsis">造成长江江豚暂时性听力阈值损伤</span>
                </div>
                <div class="flex">
                  <span style="color: #f1843d;width:30px ;display: inline-block;padding: 2px 0;">200</span>
                  <span>造成长江江豚永久性的听力阈值损失</span>
                </div>
              </div>
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
import SwiperVideo from "../../components/swipeVideo";
export default {
  name:'largemonitors',
  components:{EquipmentAMap,Swiper,SwiperVideo},
  data: function (){
    return {
      swiperVideoTop:["/video/20250624153459.mp4","/video/20250805174653.mp4","/video/20250805174922.mp4","/video/0535ce331b6b98663266f6ef22e61767_raw.mp4","/video/d961730715e37479a46ae9caf6da9a2f_raw.mp4","/video/ff5c986d9ad223f47bb3cdfb5328bfdb_raw.mp4"],
      swiperVideo:["/video/bf9eb11e4054d2e3a67804a52125a3b1_raw.mp4","/video/a9a14ced44cca820607a9a5ceb995dd6_raw.mp4","/video/7b51d96ea9ef4a9da5ace9723176ec46_raw.mp4"],
      tdhList:[
        {key:"C11", value:"9"},
        {key:"C12", value:"5"},
        {key:"C13", value:"3"},
        {key:"C14", value:"29"},
        {key:"C15", value:"27"},
        {key:"C16", value:"25"},
        {key:"C17", value:"23"},
        {key:"C18", value:"19"},
        {key:"C19", value:"15"}
      ],
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
      intervalIdTemp:null,
      secondCount:30,
      dayCount:30,
      secondTimer:null,
      dayTimer:null,
      secondBling:false,
      dayBling:false,
      LOCAL_SSBRL:LOCAL_SSBRL,
      LOCAL_TLBHQ:LOCAL_TLBHQ,
      LOCAL_ZHBHT:LOCAL_ZHBHT
    }
  },
  created() {
    let _this = this;
    //获取所有的设备，因为要用到设备的位置
    _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getDevice').then((res)=>{
      let response = res.data;
      _this.devices = response.content.list;
      _this.$forceUpdate();
      _this.getA4AndA2JL();//中间下方，获取A2设备和A4设备聚类
    })
    _this.getThreeDayTs();//左下角最近三天的总头数
    _this.getRightTopData();//右上角获取当日声学侦测次数、事件(群次)、捕食次数
    _this.getPointerDay();
    _this.getPointerSecond();
    _this.deptMap = Tool.getDeptUser();
    _this.openSocket();
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.heightMax = h*0.6-20;
    _this.dataRefreh();
    window.getSwipeData = _this.getSwipeData;
    window.getVideoData = _this.getVideoData;
  },
  methods: {
    clickMapPoint(sbmc,sbbh){
      let _this = this;
      if(sbbh.includes("C1")){
        let tdh = _this.optionKVArrayNew(_this.tdhList,sbbh);
        window.location.href = "/mobile/videoTl?tdh="+tdh+"&sbbh="+sbbh;
      }else {
        Toast.error("该站点没有数据！");
      }
    },
    toTlVideo(){
      window.location.href = "/mobile/videoTl";
    },
    //倒计时
    countDownSecond(){
      let _this = this;
      _this.secondCount--;
      if(_this.secondCount==0){
        _this.secondCount = 30;
        clearTimeout(_this.secondTimer);
        _this.getNoRequestData();
        _this.secondTimer=null;
        _this.secondBling = false;
        return;
      }
      _this.secondTimer = setTimeout(function (){_this.countDownSecond();},1000);
    },
    countDownDay(){
      let _this = this;
      _this.dayCount--;
      if(_this.dayCount==0){
        _this.dayCount = 30;
        clearTimeout(_this.dayTimer);
        _this.dayTimer=null;
        _this.dayBling = false;
        return;
      }
      _this.dayTimer = setTimeout(function (){_this.countDownDay();},1000);
    },
    //获取指针实时数据
    openSocket(){
      let _this = this;
      let socket;
      if(typeof(WebSocket) == "undefined") {
        alert("您的浏览器不支持WebSocket,无法实时更新数据,请使用谷歌、火狐或IE11等浏览器!");
      }else{
        let socketUrl="ws://58.215.212.246:39091/monitor/websocket/21_"+new Date().getTime();
        console.log(socketUrl);
        if(socket!=null){
          socket.close();
          socket=null;
        }
        socket = new WebSocket(socketUrl);
        //打开事件
        socket.onopen = function() {
          console.log("websocket已打开");
        };
        //获得消息事件
        socket.onmessage = function(msg) {
          if(msg.data.includes("连接成功")){
            //Toast.success(msg.data);
            console.log(msg.data);
          }else{
            let data = JSON.parse(msg.data);
            if("1018"==data.type){//每秒
              if(_this.intervalIdTemp!=null){
                clearInterval(_this.intervalIdTemp);
                _this.intervalIdTemp=null;
              }
              if(_this.secondTimer!=null){
                clearTimeout(_this.secondTimer);
                _this.secondTimer=null;
              }
              _this.gauge1(data.ts);
              _this.secondCount = 30;
              _this.secondBling = true;
              _this.countDownSecond();
            }else if("1019"==data.type){//每天
              if(_this.dayTimer!=null){
                clearTimeout(_this.dayTimer);
                _this.dayTimer=null;
              }
              _this.gauge2(data.ts);
              _this.dayCount = 30;
              _this.dayBling = true;
              _this.countDownDay();
            }
          }
        };
        //关闭事件
        socket.onclose = function() {
          console.log("websocket已关闭");
        };
        //发生了错误事件
        socket.onerror = function() {
          console.log("websocket发生了错误");
        }
      }
    },
    getPointerSecond(){
      let _this = this;
      // _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getPointerSecond',{}).then((res)=>{
      //   let response = res.data.content;
      //   if(!Tool.isEmpty(response)){
      //     _this.gauge1(response.decibelValue);
      //   }else{
      //     _this.gauge1(124);
      //   }
      //   //108-115
      //
      // })
      _this.getNoRequestData();
    },
    getNoRequestData(){
      let _this = this;
      _this.intervalIdTemp = setInterval(() => {
        let value = Math.ceil(Math.random()*(115-108))+108;
        _this.gauge1(value);
      }, 3000);
    },
    gauge1(value) {
      let chart = echarts.init(document.getElementById('gauge1'))
      let option = {
        series: [
          {
            type: 'gauge',
            startAngle: 180,
            endAngle: 0,
            center: ['50%', '45%'],
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
              distance: -40
            },
            title: {
              offsetCenter: [0, '-10%'],
              fontSize: 20
            },
            detail: {
              show: true,
              formatter: "{value}dB",
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
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getPointerDay',{type:'zjglj',sbbh:'DSZXA4001'}).then((res)=>{
        let response = res.data.content;
        if(!Tool.isEmpty(response)){
          _this.gauge2(response.decibelValue);
        }else{
          _this.gauge2(130);
        }
      })
    },
    gauge2(value) {
      let chart = echarts.init(document.getElementById('gauge2'))
      let option = {
        series: [
          {
            type: 'gauge',
            startAngle: 180,
            endAngle: 0,
            center: ['50%', '45%'],
            radius: '75%',
            min: 120,
            max: 220,
            axisLine: {
              lineStyle: {
                width: 6,
                color: [
                  [0.6, '#327662'],
                  [0.8, '#F7BA0B'],
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
              distance: -40
            },
            title: {
              offsetCenter: [0, '-10%'],
              fontSize: 20
            },
            detail: {
              show: true,
              formatter: "{value}dB",
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
        _this.getThreeDayTs();//左下角最近三天的总头数
        _this.getA4AndA2JL();//中间下方，获取A2设备和A4设备聚类
        _this.getRightTopData();//右上角获取当日声学侦测次数、事件(群次)、捕食次数
        //_this.getPointerDay();
        //_this.getPointerSecond();
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
    toAzimuthDp(){
      window.location.href = "/mobile/largemonitorsAzimuth";
    },
    getThreeDayTs(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getThreeDayTs').then((res)=>{
        let response = res.data;
        let contentDatas = response.content;
        let legendData = [];
        let seriesData = [];
        if(contentDatas.map){
          for(let sbbh in contentDatas.map){
            legendData.push(sbbh);
            let infos = contentDatas.map[sbbh];
            let rqs = contentDatas.rqs;
            let allData = [];
            for(let i=0;i<infos.length;i++){
              let seriesItem = [];
              let info = infos[i];
              if(rqs.includes(info.rq)){
                let index = rqs.findIndex((x) => x == info.rq);
                rqs.splice(index,1);
              }
              seriesItem.push(info.rq,info.ts);
              allData.push(seriesItem);
            }
            for(let k=0;k<rqs.length;k++){
              let seriesItem = [];
              seriesItem.push(rqs[k],0);
              allData.push(seriesItem);
            }
            allData.sort();
            let obj = {
              name: sbbh,
              type: 'bar',
              data: allData
            }
            seriesData.push(obj);
          }
        }
        _this.initBarChar(legendData,seriesData);
      })
    },
    initBarChar(legendData,seriesData){
      let option = {
        legend: {
          data: legendData,
          textStyle: {
            color: "rgba(251, 251, 251, 1)"
          }
        },
        tooltip: {
          show:true,
        },
        xAxis: [
          {
            type: 'category',
            name: '日期',
            axisLine: {
              lineStyle: {
                color: "rgba(251, 251, 251, 1)"
              }
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '侦测头次',
            axisLine: {
              lineStyle: {
                color: "rgba(251, 251, 251, 1)"
              }
            }
          }
        ],
        series: seriesData
      };
      let chart = echarts.init(document.getElementById('barChart'))
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    getA4AndA2JL(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getEventData').then((res)=>{
        let response = res.data;
        let eventDatas = response.content;
        if(eventDatas && eventDatas.length>0){
          _this.getSwipeData(eventDatas[0].sbbh,eventDatas[0].kssj,eventDatas[0].jssj);
          _this.getVideoData(eventDatas[0].sbbh,eventDatas[0].kssj,eventDatas[0].jssj);
        }
        _this.config.data = []
        for(let i=0;i<eventDatas.length;i++){
          let item = eventDatas[i];
          let arrItem = [_this.optionKVArray(_this.devices,item.sbbh),item.kssj.substring(11),item.jssj.substring(11),item.ts, `<div class="btn-detail" onclick="getSwipeData('${item.sbbh}','${item.kssj}','${item.jssj}')">查看声谱图</div>`];
          _this.config.data.push(arrItem);
        }
        this.$refs['scrollBoard'].updateRows(_this.config.data, 0);
      })
    },
    getSwipeData(sbbh,kssj,jssj){
      let _this = this;
      _this.getVideoData(sbbh,kssj,jssj)
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
    optionKVArrayNew(list, key) {
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
    },
    getRightTopData(){
      let _this = this;
      let obj = {};
      if("460100"!=Tool.getLoginUser().deptcode){
        obj.deptcode = Tool.getLoginUser().deptcode;
      }
      obj.stime = moment().subtract(0, "days").format('YYYY-MM-DD');
      obj.etime = moment().subtract(0, "days").format('YYYY-MM-DD');
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileToday/statisticsAlarmNumsByTimeSum', obj).then((response) => {
        let resp = response.data;
        let alarmDatas = resp.content;
        let zccs = { label: '当日声学侦测次数', value: alarmDatas.num };
        let sjcs = { label: '当日事件(群次)', value: alarmDatas.nnm };
        let bscs = { label: '当日捕食次数', value: alarmDatas.bnum };
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
.right-top-img{
  width: 70px;
  position: absolute;
  right: 20px;
  top: 10px;
}
</style>