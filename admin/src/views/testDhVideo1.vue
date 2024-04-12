<template>
  <div class="row" style=" height:700px;width: auto;background-color: #1c84c6">
    <video ref="videoElement"
           class="centeredVideo"
           id="myPlayer"
           preload="auto"
           type="rtmp/flv"
           controls
           autoplay
           muted
           style="width: 50%;height: 500px"
    ></video>
    <input v-model="websocketUrl" style="width: 100%"/>
    <input v-model="playUrl" style="width: 100%"/>
    <button v-on:click="startPlay()">开始</button>
  </div>
</template>
<script>
import flvjs from "flv.js";
import axios from "axios";
export default {
  data() {
    return {
      serverIp:"192.168.10.133",
      wsPort: 19091,
      flvPlayer: "",
      url: '',
      flvSettings: {
        withCredentials: false,
        hasAudio: false
      },
      extendSettings: {
        frameTracking: true,
        updateOnStart: true,
        updateOnFocus: true,
        reconnect: true
      },
      stats: '',
      error: '',
      playUrl:'/monitor/realPlayManager/startPlay/0',
      websocketUrl:'ws://192.168.10.133:19091/monitor/device/monitor/1/0/'+new Date().getTime(),
      timer: null
    };
  },
  mounted() {
    this.getLogin();
  },
  methods: {
    async getLogin(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/realPlayManager/login', {}).then((response) => {
          //_this.startPlay();
      })
    },
    startPlay(){
      let _this = this;
      _this.openvideoElement();
      _this.$ajax.post(process.env.VUE_APP_SERVER + _this.playUrl, {}).then((response) => {

      })
    },
    openvideoElement(){
      let _this = this;
      try {
        if (flvjs.isSupported()) {
          if(_this.flvPlayer){
            // destroy
            _this.flvPlayer.detachMediaElement();
            _this.flvPlayer.destroy();
            _this.flvPlayer = null;
          }
          let videoElement = this.$refs.videoElement
          _this.flvPlayer = flvjs.createPlayer({
            type: 'flv',					//媒体类型
            url: this.websocketUrl,	//flv格式媒体URL
            isLive: true,					//数据源是否为直播流
            hasAudio: false,				//数据源是否包含有音频
            hasVideo: true,					//数据源是否包含有视频
          },{
            enableStashBuffer: false,//关闭IO隐藏缓冲区
            stashInitialSize: 128,
          });
          _this.flvPlayer.attachMediaElement(videoElement);	//将播放实例注册到节点
          _this.flvPlayer.load(); 					//加载数据流
          _this.flvPlayer.play();					//播放数据流
        }else {
          console.log("flvjs不支持");
        }
      }catch (error){
        console.log(error);
      }
    },
  }
}
</script>