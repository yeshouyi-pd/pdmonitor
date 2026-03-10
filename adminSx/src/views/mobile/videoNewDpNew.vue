<template>
  <div style="width: 1920px;height: 100vh;background-color: rgb(25,107,136);background-size: 100%;margin: auto">
    <header style="position: relative;background: none">
      <img src="/largemonitors/assets/imgs/headertitle2.png" style="position: absolute;left: 30%;top:20px;width: 750px;">
      <div class="lefttitle" style="top: 15px;left: 25px;color: #fff;display: flex;">
        <div class="title-item" @click="back">返回</div>
      </div>
      <div class="lefttitle" style="top: 15px;left: 185px;color: #fff;display: flex;">
        <div class="title-item" @click="showChannel">在线预览</div>
      </div>
    </header>
    <div class="page-first-div">
      <div class="left-box">
        <div class="left-item-box">
          <div class="title-item-box">监测点数据</div>
          <div style="display: flex;color: #fff;justify-content: space-around;margin-top: 10px;">
            <div style="width: 30%;">
              <select v-model="curEventSbbh" @change="getVideoEventCountByRq" class="form-control" style="background-color: #13225E;color: #fff;border-color: #34B9DF;">
                <option value="">全部</option>
                <option v-for="item in devices" :value="item.sbsn">{{item.sbmc}}</option>
              </select>
            </div>
            <div class="radio" style="margin-right: 10px;text-align: center;">
              <label style="width: 110px;">
                <input name="form-event-radio" type="radio"  value="1" v-model="eventDayType" class="ace" />
                <span class="lbl bigger-120">前30天</span>
              </label>
              <label style="width: 110px;">
                <input name="form-event-radio" type="radio"  value="2" v-model="eventDayType" class="ace" />
                <span class="lbl bigger-120">前15天</span>
              </label>
              <label style="width: 110px;">
                <input name="form-event-radio" type="radio"  value="3" v-model="eventDayType" class="ace" />
                <span class="lbl bigger-120">前7天</span>
              </label>
            </div>
          </div>
          <div id="leftTopEchart" style="width: 100%;height: 75%;"></div>
        </div>
        <div class="left-item-box">
          <div class="title-item-box">监测点历史数据</div>
          <div id="leftBottomEchart" style="width: 100%;height: 90%;"></div>
        </div>
      </div>
      <div class="right-box">
        <div class="right-container" id="playbox">

        </div>
      </div>
    </div>

    <div id="video-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 100%;height: 100%;">
        <div class="modal-content" style="width: 100%;height: 100%;margin: auto">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">在线实时预览</h4>
          </div>
          <div class="modal-body" style="height: 85%;display: flex;">
            <ul class="h5-ul" id="h5_channel_list">
              <li id="channel-0" v-on:click="changeChannel(0)">D1 桥面1可见光（11.252.220.131）</li>
              <li id="channel-2" v-on:click="changeChannel(2)">D3 桥面2可见光（11.252.220.132）</li>
              <li id="channel-4" v-on:click="changeChannel(4)">D5 桥面3可见光（11.252.220.133）</li>
              <li id="channel-6" v-on:click="changeChannel(6)">D7 桥面4可见光（11.252.220.134）</li>
              <li id="channel-8" v-on:click="changeChannel(8)">D9 桥面5可见光（11.252.220.135）</li>
              <li id="channel-10" v-on:click="changeChannel(10)">D11 桥面6可见光（11.252.220.136）</li>
              <li id="channel-12" v-on:click="changeChannel(12)">D13 桥面7可见光（11.252.220.137）</li>
              <li id="channel-14" v-on:click="changeChannel(14)">D15 桥面8可见光（11.2252.220.138）</li>
              <li id="channel-16" v-on:click="changeChannel(16)">D17 铁塔1可见光(192.168.3.21)</li>
              <li id="channel-18" v-on:click="changeChannel(18)">D19 铁塔2可见光（192.168.3.22）</li>
            </ul>
            <div style="width: calc(100% - 200px);">
              <video ref="videoElement"
                     class="centeredVideo"
                     id="myPlayer"
                     preload="auto"
                     type="rtmp/flv"
                     autoplay
                     muted
                     style="width: 100%;height: 75%"
              ></video>
              <fieldset class="h5-fieldset-wrap">
                <legend style="color: #000;margin-bottom: 0;">云台控制</legend>
                <div class="h5-step-wrap">
                  <span style="color:#000;">步长(1-8):</span>
                  <select id="h5_ptz_step" style="width: 230px;height: 40px;"  v-model="selectedValue">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                  </select>
                </div>
                <div class="h5-ptz-wrap" title="云台按钮操作界面">
                  <input type="button" class="h5-button" value="左上" @mousedown="onHandlePTZ('LeftUp', false)" @mouseup="onHandlePTZ('LeftUp', true)">
                  <input type="button" class="h5-button" value="上" @mousedown="onHandlePTZ('Up', false)" @mouseup="onHandlePTZ('Up', true)">
                  <input type="button" class="h5-button" value="右上" @mousedown="onHandlePTZ('RightUp', false)" @mouseup="onHandlePTZ('RightUp', true)">
                  <input type="button" class="h5-button" value="左" @mousedown="onHandlePTZ('Left', false)" @mouseup="onHandlePTZ('Left', true)">
                  <input class="h5-button" value="" style="opacity: 0;">
                  <input type="button" class="h5-button" value="右" @mousedown="onHandlePTZ('Right', false)" @mouseup="onHandlePTZ('Right', true)">
                  <input type="button" class="h5-button" value="左下" @mousedown="onHandlePTZ('LeftDown', false)" @mouseup="onHandlePTZ('LeftDown', true)">
                  <input type="button" class="h5-button" value="下" @mousedown="onHandlePTZ('Down', false)" @mouseup="onHandlePTZ('Down', true)">
                  <input type="button" class="h5-button" value="右下" @mousedown="onHandlePTZ('RightDown', false)" @mouseup="onHandlePTZ('RightDown', true)">
                </div>
                <div class="h5-zoomfocus-wrap" title="变倍聚焦操作界面">
                  <input type="button" class="h5-button" value="变倍-" @mousedown="onHandlePTZ('ZoomWide', false)" @mouseup="onHandlePTZ('ZoomWide', true)">
                  <input type="button" class="h5-button" value="变倍+" @mousedown="onHandlePTZ('ZoomTele', false)" @mouseup="onHandlePTZ('ZoomTele', true)">
                  <input type="button" class="h5-button" value="聚焦-" @mousedown="onHandlePTZ('FocusFar', false)" @mouseup="onHandlePTZ('FocusFar', true)">
                  <input type="button" class="h5-button" value="聚焦+" @mousedown="onHandlePTZ('FocusNear', false)" @mouseup="onHandlePTZ('FocusNear', true)">
                  <input type="button" class="h5-button" value="光圈-" @mousedown="onHandlePTZ('IrisSmall', false)" @mouseup="onHandlePTZ('IrisSmall', true)">
                  <input type="button" class="h5-button" value="光圈+" @mousedown="onHandlePTZ('IrisLarge', false)" @mouseup="onHandlePTZ('IrisLarge', true)">
                </div>
                <div class="h5-preset-wrap" title="预置点操作界面">
                  <div class="h5-item-form" id="h5-item-form" style="margin-bottom: 10px;">
                    <span style="color: #000;width: 130px;border: none;font-size: 20px;">预置点：</span>
                    <label id="yzd-1" name="1" @mousedown="onHandlePTZ('GotoPreset', false, $event)" >1</label>
                    <label id="yzd-2" name="2" @mousedown="onHandlePTZ('GotoPreset', false, $event)" >2</label>
                    <label id="yzd-3" name="3" @mousedown="onHandlePTZ('GotoPreset', false, $event)" >3</label>
                  </div>
                  <!--            <button v-on:click="getRealVideo(1)" class="btn btn-xs btn-info" style="margin-left: 10px;">-->
                  <!--              <i class="ace-icon fa fa-book bigger-120">查看实时分析列表</i>-->
                  <!--            </button>-->
                </div>
              </fieldset>
            </div>
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
import flvjs from "flv.js";

export default {
  name: 'video-new-dp',
  data: function (){
    return {
      sbbhSxtList:[
        {key:"1", value:"RPCDA4004,RPCDA4013"},
        {key:"2", value:"RPCDA4005,RPCDA4012"},
        {key:"3", value:"RPCDA4003"},
        {key:"4", value:"RPCDA4006"},
        {key:"5", value:"RPCDA4009"},
        {key:"6", value:"RPCDA4001"},
        {key:"7", value:"RPCDA4007"},
        {key:"8", value:"RPCDA4010"},
        {key:"9", value:"RPCDA4002"},
        {key:"10", value:"RPCDA4016"}
      ],
      sbbhTdhList:[
        {key:"0", value:"RPCDA4004"},
        {key:"2", value:"RPCDA4005"},
        {key:"4", value:"RPCDA4003"},
        {key:"6", value:"RPCDA4006"},
        {key:"8", value:"RPCDA4009"},
        {key:"10", value:"RPCDA4001"},
        {key:"12", value:"RPCDA4007"},
        {key:"14", value:"RPCDA4010"},
        {key:"16", value:"RPCDA4002"},
        {key:"18", value:"RPCDA4016"}
      ],
      alldevice:[],
      devices:[],
      curEventSbbh:'',
      stime:'',
      etime:'',
      eventDayType:'3',
      sbbhSxt:'',
      tdh:'',
      videoIndex:0,
      videoEvents:[],
      curChannel:0,
      flvPlayer: "",
      websocketUrl:'ws://49.239.193.146:50091/monitor/device/monitor/',
      selectedValue:5,
      yuzhidian:''
    }
  },
  watch: {
    eventDayType(){
      let _this = this;
      _this.getVideoEventCountByRq();
    }
  },
  created() {
    let _this = this;
    if(!Tool.isEmpty(location.search)){
      const params = new URLSearchParams(location.search);
      _this.tdh = params.get("tdh");
      _this.sbbhSxt = params.get("sbbh");
      _this.curEventSbbh = _this.optionKVArray(_this.sbbhTdhList,_this.tdh);
    }
    _this.getDevice();
    _this.stime=Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*7));
    _this.etime=Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
    //监听模态框关闭
    $('#video-modal').on('hidden.bs.modal', function () {
      _this.destroyVideos();
    });
  },
  mounted() {
    let _this = this;
    _this.getExplainVideoEvent();
  },
  methods: {
    getExplainVideoEvent(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getExplainVideoEvent', {sm:'1',sbbh:_this.optionKVArray(_this.sbbhSxtList,_this.sbbhSxt)}).then((response)=>{
        let resp = response.data;
        _this.videoEvents = resp.content;
        if(_this.videoEvents.length>0){
          if(0==_this.videoEvents[0].sfysp){
            _this.getPlayUrl(_this.videoEvents[0]);
          }else if(2==_this.videoEvents[0].sfysp){
            _this.watchVideo(_this.videoEvents[0].wjlj);
          }
        }
      })
    },
    getPlayUrl(item){
      let _this = this;
      Loading.show();
      let url = 'http://49.239.193.146:59088/FileInfo.asmx/GetPlayUrl';
      $.post(url,{"sbid": item.sbbh,"filename":item.wjlj.substring(item.wjlj.lastIndexOf("/")+1),"fbl":"1080","fhfs":"1"}, function (data, status) {
        if(status&&!(data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('不存在')||data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('文件大小为0'))){
          $("#playbox").empty();
          let video = document.createElement("video");
          video.setAttribute("width","100%");
          video.setAttribute("height","100%");
          video.setAttribute("id","videoPalyId");
          video.setAttribute("controls","controls");
          video.setAttribute("autoplay","autoplay");
          //video.setAttribute("loop","loop");
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
          document.getElementById("videoPalyId").addEventListener("ended",function (){
            console.log("播放完");
            _this.videoIndex++;
            if(_this.videoIndex>=_this.videoEvents.length){
              _this.videoIndex=0;
            }
            let obj = _this.videoEvents[_this.videoIndex];
            if(0==obj.sfysp){
              _this.getPlayUrl(obj);
            }else if(2==obj.sfysp){
              _this.watchVideo(obj.wjlj);
            }
          })
          Loading.hide();
        }else{
          Toast.error("未找到源文件或文件大小为0，无法转码！");
          Loading.hide();
        }
      })
    },
    watchVideo(wjlj){
      let _this = this;
      Loading.show();
      $("#playbox").empty();
      let video = document.createElement("video");
      video.setAttribute("width","100%");
      video.setAttribute("height","100%");
      video.setAttribute("id","videoPalyId");
      video.setAttribute("controls","controls");
      video.setAttribute("autoplay","autoplay");
      //video.setAttribute("loop","loop");
      video.src = wjlj;
      document.getElementById('playbox').appendChild(video);
      video.play();
      document.getElementById("videoPalyId").addEventListener("ended",function (){
        console.log("播放完");
        _this.videoIndex++;
        if(_this.videoIndex>=_this.videoEvents.length){
          _this.videoIndex=0;
        }
        let obj = _this.videoEvents[_this.videoIndex];
        if(0==obj.sfysp){
          _this.getPlayUrl(obj);
        }else if(2==_this.obj.sfysp){
          _this.watchVideo(obj.wjlj);
        }
      })
      Loading.hide();
    },
    getVideoEventCountByRq(){
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
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getVideoEventCountByRq',obj).then((res)=>{
        let lists = res.data.content;
        let rq = [];
        let yshsl = [];
        let wshsl = [];
        for(let i=0;i<lists.length;i++){
          let obj = lists[i];
          rq.push(obj.rq);
          yshsl.push(obj.yhc);
          wshsl.push(obj.whc);
        }
        _this.initLeftTopEchart(rq,yshsl,wshsl);
      })
    },
    getVideoEventCountBySbbh(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getVideoEventCountBySbbh',{}).then((res)=>{
        let lists = res.data.content;
        let sbmcs = [];
        let yshsl = [];
        let wshsl = [];
        for(let i=0;i<lists.length;i++){
          let obj = lists[i];
          sbmcs.push(_this.optionNSArray(_this.alldevice,obj.sbbh));
          yshsl.push(obj.yhc);
          wshsl.push(obj.whc);
        }
        _this.initLeftBottomEchart(sbmcs,yshsl,wshsl);
      })
    },
    getDevice(){
      let _this = this;
      //获取所有的设备，因为要用到设备的位置
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getDevice').then((res)=>{
        let response = res.data;
        _this.alldevice = response.content.list;
        for(let i=0;i<_this.alldevice.length;i++){
          if(_this.alldevice[i].dqzl=='A1' || _this.alldevice[i].dqzl =='A4'){
            _this.devices.push(_this.alldevice[i]);
          }
        }
        _this.$forceUpdate();
        _this.getVideoEventCountByRq();
        _this.getVideoEventCountBySbbh();
      })
    },
    initLeftTopEchart(xAxisData,seriesData1,seriesData2){
      let _this = this;
      let option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          textStyle: {
            color: "#fff"
          },
          right: "4%"
        },
        grid: {
          top: '8%',
          left: '1%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          axisLine: {
            show: false,
            lineStyle: {
              color: "#fff"
            }
          },
          axisTick: {
            show: false
          },
          data: xAxisData
        },
        yAxis: {
          type: 'value',
          show: false,
          axisLine: {
            lineStyle: {
              color: "#fff"
            }
          }
        },
        series: [
          {
            name: '已审核',
            data: seriesData1,
            stack: 'total',
            type: 'bar'
          },
          {
            name: '未审核',
            data: seriesData2,
            stack: 'total',
            itemStyle: {
              color: "rgba(160, 26, 26, 1)"
            },
            type: 'bar'
          }
        ]
      };
      let chart = echarts.init(document.getElementById('leftTopEchart'));
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    initLeftBottomEchart(yAxisData,seriesData1,seriesData2) {
      let _this = this;
      let option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          textStyle: {
            color: "#fff"
          },
          right: "4%"
        },
        grid: {
          top: '8%',
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          show: false,
          axisLine: {
            lineStyle: {
              color: "#fff"
            }
          }
        },
        yAxis: {
          type: 'category',
          data: yAxisData,
          axisLine: {
            show: false,
            lineStyle: {
              color: "#fff"
            }
          },
          axisTick: {
            show: false
          }
        },
        series: [
          {
            name: '已审核',
            type: 'bar',
            stack: 'total',
            label: {
              show: true
            },
            emphasis: {
              focus: 'series'
            },
            data: seriesData1
          },
          {
            name: '未审核',
            type: 'bar',
            stack: 'total',
            label: {
              show: true
            },
            emphasis: {
              focus: 'series'
            },
            itemStyle: {
              color: "rgba(160, 26, 26, 1)"
            },
            data: seriesData2
          }
        ]
      };
      let chart = echarts.init(document.getElementById('leftBottomEchart'));
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    showChannel(){
      let _this = this;
      $("#video-modal").modal("show");
      if(_this.tdh){
        _this.changeChannel(_this.tdh);
      }else{
        _this.changeChannel(0);
      }
    },
    changeChannel(channel){
      let _this = this;
      _this.curChannel = channel;
      document.querySelectorAll('#h5_channel_list li').forEach(item => {item.classList.remove('fn-fontBlue')});
      document.querySelectorAll('#h5-item-form label').forEach(item => {item.classList.remove('fn-fontBlue')});
      $("#channel-"+channel).addClass("fn-fontBlue");
      _this.openvideoElement();
    },
    openvideoElement(){
      let _this = this;
      if(_this.flvPlayer){
        // destroy
        _this.flvPlayer.pause();
        _this.flvPlayer.unload();
        _this.flvPlayer.detachMediaElement();
        _this.flvPlayer.destroy();
        _this.flvPlayer = null;
      }
      try {
        if (flvjs.isSupported()) {
          let videoElement = this.$refs.videoElement
          _this.flvPlayer = flvjs.createPlayer({
            type: 'flv',					//媒体类型
            url: _this.websocketUrl+_this.curChannel,	//flv格式媒体URL
            isLive: true,					//数据源是否为直播流
            hasAudio: false,				//数据源是否包含有音频
            hasVideo: true,					//数据源是否包含有视频
            liveDelay: 3 // 设置3秒的实时播放延迟
          },{
            autoCleanupSourceBuffer: true,
            // autoCleanupMinBackwardDuration: 60,
            // autoCleanupMaxBackwardDuration:12,
            enableWorker: false, //不启用分离线程
            enableStashBuffer: false,//关闭IO隐藏缓冲区
            stashInitialSize: 128,
            lazyLoad: false
          });
          _this.flvPlayer.attachMediaElement(videoElement);	//将播放实例注册到节点
          _this.flvPlayer.load(); 					//加载数据流
          _this.flvPlayer.play();					//播放数据流
          _this.flvPlayer.on(flvjs.Events.ERROR, (errType, errDetail) =>{
            _this.openvideoElement();
          })
          setInterval(() => {
            if (_this.flvPlayer.buffered && _this.flvPlayer.buffered.length) {
              let end = _this.flvPlayer.buffered.end(0); // 获取当前buffered值
              let diff = end - _this.flvPlayer.currentTime; // 获取buffered与currentTime的差值
              if (diff >= 5) {// 如果差值大于等于3s 手动跳帧 这里可根据自身需求来定
                //单个视频
                _this.flvPlayer.currentTime = _this.flvPlayer.buffered.end(0);//手动跳帧
              }
            }
          }, 5000); //5000毫秒执行一次
        }else {
          console.log("flvjs不支持");
        }
      }catch (error){
        console.log(error);
      }
    },
    destroyVideos(){
      let _this = this;
      if(!_this.flvPlayer) return
      _this.flvPlayer.pause()
      _this.flvPlayer.unload()
      _this.flvPlayer.detachMediaElement()
      _this.flvPlayer.destroy()
      _this.flvPlayer = null
    },
    onHandlePTZ(name, isStop,e){
      let _this = this;
      let param2 = '';
      if(name=='GotoPreset'){
        _this.yuzhidian = e.target.innerText;
        if(Tool.isEmpty(_this.yuzhidian) || !(/^[0-9]+$/.test(_this.yuzhidian))){
          Toast.error("请填写数字！");
          return;
        }
        document.querySelectorAll('#h5-item-form label').forEach(item => {item.classList.remove('fn-fontBlue')});
        $("#"+e.target.id).addClass("fn-fontBlue");
        param2 = _this.yuzhidian;
      }else{
        param2 = _this.selectedValue;
      }
      if(!isStop) {
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/ptzController/ptzControlStart/'+name+"/"+_this.curChannel+"/0/"+param2, {}).then((response)=>{
          let resp = response.data;
          if(resp.code=='201'){
            Toast.error(resp.message);
          }
        })
      } else {
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/ptzController/ptzControlEnd/'+name+"/"+_this.curChannel, {}).then((response)=>{
          let resp = response.data;
          if(resp.code=='201'){
            Toast.error(resp.message);
          }
        })
      }
    },
    back(){
      let _this = this;
      window.location.href = "/mobile/environmentDp";
    },
    optionKVArray(list, key){
      if (!list) {
        return "";
      } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
          if (key == list[i]["key"]) {
            result = list[i]["value"];
          }
        }
        return result;
      }
    },
    optionNSArray(list, key){
      if (!list || !key) {
        return "";
      } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
          if (key === list[i]["sbsn"]) {
            result = list[i]["sbmc"];
          }
        }
        return result;
      }
    }
  }
}
</script>
<style scoped>
.title-item{
  width: 120px;
  padding: 5px;
  border: 1px solid #4AAFE3;
  text-align: center;
  font-size: 18px;
  cursor: pointer;
  font-weight: bold;
  color: #4AAFE3;
}
.page-first-div{
  width: 100%;
  height: calc(100% - 88px);
  display: flex;
  flex-direction: row;
}
.title-item-box{
  width: 100%;
  height: 10%;
  background-image: url(/largemonitors/assets/imgs/biaotikuang2.png);
  background-size: 50% 100%;
  color: #fff;
  font-size: 18px;
  padding: 6px 40px;
  background-repeat: no-repeat;
}
.left-box{
  width: 39%;
  height: 100%;
}
.left-item-box{
  width: 96%;
  height: 49%;
  background-color: rgb(15,66,91);
  margin: 1% auto;
  border-radius: 10px;
}
.right-box{
  width: 60%;
  height: 100%;
  display: flex;
  flex-direction: column;
}
.right-container{
  width: 100%;
  height: 99%;
  background-color: rgb(15,66,91);
  margin: 0.8% auto 0;
  border-radius: 10px;
}
.h5-ul {
  /* float: left; */
  /* background: #ccc; */
  list-style: none;
  /* max-height: 250px; */
  /* overflow-y: scroll; */
  width: 150px;
  text-align: center;
  margin-right: 5px;
  /* height: 100vh; */
  overflow-y: auto;
}
.h5-ul li {
  cursor: pointer;
  height: 60px;
  line-height: 30px;
  border: 1px solid #19D88D;
  color: #000;
}
.h5-ul li:hover {
  background-color: #19D88D;
  color: #000;
}
.fn-fontBlue{
  background-color: #19D88D !important;
}
.h5-fieldset-wrap {
  /*width: 980px;*/
  padding: 10px;
}
.h5-step-wrap {
  margin-bottom: 10px;
  float: left;
  margin-right: 20px;
  font-size: 20px;
}
.h5-zoomfocus-wrap {
  width: 200px;
  float: left;
}
.h5-preset-wrap {
  float: left;
  margin-top: 10px;
}
.h5-ptz-wrap {
  width: 210px;
  float: left;
  color: #000;
}
.h5-ptz-wrap .h5-button {
  width: 60px;
  height: 30px;
  margin-bottom: 3px;
}
.h5-zoomfocus-wrap .h5-button {
  width: 80px;
  height: 30px;
  margin-bottom: 3px;
}
.h5-preset-wrap .h5-button {
  width: 60px;
  height: 30px;
  margin-bottom: 3px;
}
.h5-item-form input{
  display: none;
}
.h5-item-form label {
  width: 80px;
  display: inline-block;
  height: 30px;
  border: 1px solid #19D88D;
  text-align: center;
  line-height: 30px;
  font-size: 20px;
  color: #000;
  margin-right: 20px;
  cursor: pointer;
}
.h5-item-form label:hover {
  background-color: #19D88D;
}
</style>