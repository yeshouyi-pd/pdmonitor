<template>
  <div style="width: 1920px;height: 100vh;background-image: url('/static/image/environment/bg02.png');background-size: 100%;margin: auto">
    <header style="position: relative;background: none">
      <img src="/largemonitors/assets/imgs/headertitle2.png" style="position: absolute;left: 30%;top:20px;width: 750px;">
      <div class="lefttitle" style="top: 15px;left: 25px;color: #fff;display: flex;">
<!--        <img src="/largemonitors/assets/imgs/左上角title.png" alt="" style="width: 250px;">-->
<!--        <span>-->
<!--          <div @click="chooseProject" style="cursor: pointer;">主页</div>-->
<!--        </span>-->
        <div class="title-item" @click="back">返回</div>
        <div v-if="!LOCAL_VIDEO" class="title-item" @click="chooseEnvironment" style="margin-left: 10px;">环境</div>
        <div v-if="!LOCAL_VIDEO" class="title-item" @click="chooseLargemonitors" style="margin-left: 10px;width: 90px;">定点监测</div>
      </div>
    </header>
    <div class="page-first-div">
      <div class="left-box">
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
        <div class="h5-left">
          <div class="h5-play-wrap">
            <video ref="videoElement"
                   class="centeredVideo"
                   id="myPlayer"
                   preload="auto"
                   type="rtmp/flv"
                   autoplay
                   muted
                   style="width: 100%;height: 100%"
            ></video>
          </div>
          <fieldset class="h5-fieldset-wrap">
            <legend style="color: #FFFFFF;margin-bottom: 0;">云台控制</legend>
            <div class="h5-step-wrap">
              <span style="color:#fff;">步长(1-8):</span>
              <select id="h5_ptz_step" style="width: 130px;"  v-model="selectedValue">
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
              <div class="h5-item-form" style="margin-bottom: 10px;">
                <label style="color: #fff">预置点：</label>
                <input type="text" id="h5_preset" v-model="yuzhidian">
              </div>
              <input type="button" class="h5-button" value="查看" @mousedown="onHandlePTZ('GotoPreset', true)">
            </div>
          </fieldset>
        </div>
      </div>
      <div class="right-box">
        <div class="right-top-box" id="playbox">
<!--          <video autoplay="autoplay" loop="loop" style="width: 100%;height: 100%" controls>-->
<!--            <source class="video" title="主监控位" src="/video/12.mp4" type="video/mp4" />-->
<!--          </video>-->
        </div>
        <div class="right-bottom-box">
          <div style="height: 414px;width: 97%;overflow:auto;margin-top: 1%;margin-left: 1%;color: #82CF48;font-size: 16px;">
            <table id="simple-table" class="table  table-bordered" >
              <thead>
              <tr>
                <td>设备名称</td>
                <td>开始时间</td>
                <td>结束时间</td>
                <td>视频</td>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in videoEvents">
                <td>{{devices|optionNSArray(item.sbbh)}}</td>
                <td>{{item.kssj}}</td>
                <td>{{item.jssj}}</td>
                <td style="cursor: pointer;" v-on:click="getPlayUrl(item)">查看视频</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/pagination";
import Times from "@/components/times";
import flvjs from "flv.js";

export default {
  name: 'video-dp',
  components: {Pagination,Times},
  data: function (){
    return {
      LOCAL_VIDEO:LOCAL_VIDEO,
      devices:[],
      videoEvents:[],
      tdh:'',
      sbbhSxt:'',
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
      sbbhTdh:'',
      sbbhTdhList:[
        {key:"0", value:"RPCDA4004,RPCDA4013"},
        {key:"2", value:"RPCDA4005,RPCDA4012"},
        {key:"4", value:"RPCDA4003"},
        {key:"6", value:"RPCDA4006"},
        {key:"8", value:"RPCDA4009"},
        {key:"10", value:"RPCDA4001"},
        {key:"12", value:"RPCDA4007"},
        {key:"14", value:"RPCDA4010"},
        {key:"16", value:"RPCDA4002"},
        {key:"18", value:"RPCDA4016"}
      ],
      sbbh:'',
      firstEnter:true,
      flvPlayer: "",
      websocketUrl:'ws://192.168.10.133:19091/monitor/device/monitor/',
      curChannel:0,
      selectedValue:5,
      yuzhidian:''
    }
  },
  created() {
    let _this = this;
    if(!Tool.isEmpty(location.search)){
      const params = new URLSearchParams(location.search);
      _this.tdh = params.get("tdh");
      _this.sbbhSxt = params.get("sbbh");
    }
    //获取所有的设备，因为要用到设备的位置
    _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getDevice').then((res)=>{
        let response = res.data;
        _this.devices = response.content.list;
    })
  },
  mounted() {
    let _this = this;
    window.addEventListener("message",this.onMessage);
    _this.getExplainVideoEvent();
    _this.firstEnter = false;
    if(_this.tdh){
      _this.changeChannel(_this.tdh);
    }else{
      _this.changeChannel(0);
    }
  },
  destroy(){
    let _this = this;
    _this.destroyVideos();
  },
  directives: {
    trigger: {
      inserted (el, pinging) {
        el.click()
      }
    }
  },
  methods: {
    onMessage(e){
      let _this = this;
      let param = JSON.parse(e.data);
      _this.sbbhTdh = param.data;
      if(!_this.firstEnter){
        _this.getExplainVideoEvent();
      }
    },
    getPlayUrl(item){
      let _this = this;
      Loading.show();
      $("#playbox").empty();
      let url = 'http://49.239.193.146:59088/FileInfo.asmx/GetPlayUrl';
      $.post(url,{"sbid": item.sbbh,"filename":item.wjlj.substring(item.wjlj.lastIndexOf("/")+1),"fbl":"1080","fhfs":"1"}, function (data, status) {
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
          Loading.hide();
        }else{
          Toast.error("未找到源文件或文件大小为0，无法转码！");
          Loading.hide();
        }
      })
    },
    getExplainVideoEvent(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getExplainVideoEvent', {sm:'1',sfysp:0,sbbh:_this.optionKVArray(_this.sbbhTdhList,_this.sbbhTdh)}).then((response)=>{
        let resp = response.data;
        _this.videoEvents = resp.content;
        if(_this.videoEvents.length>0){
          _this.getPlayUrl(_this.videoEvents[0]);
        }
      })
    },
    back(){
      let _this = this;
      if(_this.LOCAL_VIDEO){
        window.location.href = "/mobile/environmentDp";
      }else{
        window.location.href = "/admin/largemonitorsZj";
      }
    },
    chooseEnvironment(){
      window.location.href = "/mobile/environmentDp";
    },
    chooseLargemonitors(){
      let _this = this;
      window.location.href = "/mobile/largemonitorsZj";
    },
    vueSendMsg(numIframe) {
      let _this = this;
      console.log(_this.tdh);
      setTimeout(() =>{
        const iframeWindow = this.$refs[numIframe].contentWindow
        iframeWindow.postMessage({
          cmd: 'myVue',
          params: {
            tdh: _this.tdh
          }
        }, '*')
      }, 1000)
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
    },
    onHandlePTZ(name, isStop){
      let _this = this;
      let param2 = '';
      if(name=='GotoPreset'){
        if(Tool.isEmpty(_this.yuzhidian) || !(/^[0-9]+$/.test(_this.yuzhidian))){
          Toast.error("请填写数字！");
          return;
        }
        param2 = _this.yuzhidian;
      }else{
        param2 = _this.selectedValue;
      }
      if(!isStop) {
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/ptzController/ptzControlStart/'+name+"/"+_this.curChannel+"/0/"+param2, {}).then((response)=>{
          console.log(response);
        })
      } else {
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/ptzController/ptzControlEnd/'+name+"/"+_this.curChannel, {}).then((response)=>{
          console.log(response);
        })
      }
    },
    changeChannel(channel){
      let _this = this;
      document.querySelectorAll('#h5_channel_list li').forEach(item => {item.classList.remove('fn-fontBlue')});
      $("#channel-"+channel).addClass("fn-fontBlue");
      _this.curChannel = channel;
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
    }
  }
}
</script>
<style scoped>
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
  border: 1px solid #fff;
  color: #FFFFFF;
}
.h5-ul li:hover {
  background-color: #eee;
  color: #000;
}
.h5-left,
.h5-right {
  float: left;
  width: 980px;
  height: 630px;
}
.h5-play-wrap {
  width: 980px;
  height: 630px;
  background-color: #000;
  border: 1px solid #333;
  position: relative;
  overflow: hidden;
}
.fn-fontBlue{
  color: #4AAFE3 !important;
}
.h5-fieldset-wrap {
  width: 980px;
  padding: 10px;
}
.h5-step-wrap {
  margin-bottom: 10px;
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
.h5-preset-wrap input {
  width: 130px;
}
.title-item{
  width: 80px;
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
.left-box{
  width: 60%;
  height: 100%;
  display: flex;
  flex-direction: row;
}
.left-box-item{
  width: 50%;
  height: 50%;
}
.left-video-box{
  width: 95%;
  height: 89%;
  margin-left: 5%;
  background-color: rgb(7,14,40);
}
.left-text-style{
  height: 10%;
  text-align: center;
  color: #fff;
  margin-top: 2%;
  font-size: 16px;
}
.right-box{
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
}
.right-top-box{
  width: 97%;
  height: 49%;
  margin-left: 1%;
}
.right-bottom-box{
  width: 100%;
  height: 50%;
  text-align: center;
  color: #fff;
}
</style>