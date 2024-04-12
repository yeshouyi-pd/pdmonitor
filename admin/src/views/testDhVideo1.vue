<template>
  <div class="row" style=" height:500px;width: auto;background-color: #1c84c6">
    <video ref="videoElement"
           class="centeredVideo"
           id="myPlayer"
           preload="auto"
           type="rtmp/flv"
           controls
           autoplay
           muted
           style="width: 50%;height: 100%"
           @click="handleClick"
    ></video>
    <>
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
      error: ''
    };
  },
  mounted() {
    //this.loadFlv();
    //this.setupWebSocket();
    this.openvideoElement();
    this.getLogin();
  },
  methods: {
    login(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/realPlayManager/showPanel', {
      }).then((response)=>{

      })
    },
    async getLogin(){
      const url2 = "http://" + this.serverIp + ":" + this.wsPort + "/monitor/realPlayManager/startPlay/0"; // 设置POST请求的URL
      const data = {};
      try {
        const response = await axios.post(url2, data, {
          headers: {
            "Content-Type": "application/json", // 设置请求头中的Content-Type
            "Access-Control-Allow-Origin": "*", // 设置允许的跨域请求源
          },
        });
      } catch (error) {
        console.error(error); // 处理错误
      }
    },
    openvideoElement(){
      let videoElement = this.$refs.videoElement
      try {
        if (flvjs.isSupported()) {
          this.flvPlayer = flvjs.createPlayer({
            type: 'flv',					//媒体类型
            url: 'ws://'+this.serverIp +':'+'9093/monitor/device/monitor/1/0/'+new Date().getTime(),	//flv格式媒体URL
            //url:'http://192.168.10.133:19091/system/f/202403/file.flv',
            isLive: true,					//数据源是否为直播流
            hasAudio: false,				//数据源是否包含有音频
            hasVideo: true,					//数据源是否包含有视频
            enableStashBuffer: true,
          },{
            enableStashBuffer: false,
            stashInitialSize: 1024,
          });
          // this.flvPlayer = flvjs.createPlayer({
          //   type: 'flv',
          //   url: 'http://192.168.10.133:19091/system/f/202403/file.flv' // 替换为本地文件的URL
          // });
          this.flvPlayer.attachMediaElement(videoElement);	//将播放实例注册到节点
          this.flvPlayer.load(); 					//加载数据流
          this.flvPlayer.play();					//播放数据流
          this.flvPlayer.on(flvjs.Events.ERROR, (e) => {
            // destroy
            this.flvPlayer.detachMediaElement();
            this.flvPlayer.destroy();
            this.flvPlayer = null;
            // 进行重建的逻辑，这里不再展开
            this.openvideoElement();
          });

        }
      }catch (error){
        this.openvideoElement();
      }
    },
    handleClick(){

    },
    setupWebSocket() {
      this.flvPlayer1 = null
      let ws = new WebSocket('ws://'+this.serverIp +':'+this.wsPort+'/monitor/device/monitor/1/0/'+new Date().getTime());
      ws.binaryType = 'arraybuffer';

      ws.onopen = function() {
        console.log('WebSocket 连接成功');
      };

      ws.onmessage = function(e) {
        console.log(e);
        // let videoElement = document.getElementById('videoElementId');
        // this.flvPlayer1 = flvjs.createPlayer({
        //   type: 'flv',					//媒体类型
        //   url: 'ws://'+this.serverIp +':'+this.wsPort+'/monitor/device/monitor/1/0/'+new Date().getTime(),	//flv格式媒体URL
        //   isLive: true,					//数据源是否为直播流
        //   hasAudio: false,				//数据源是否包含有音频
        //   hasVideo: true,					//数据源是否包含有视频
        // });
        // this.flvPlayer1.attachMediaElement(videoElement);	//将播放实例注册到节点
        // this.flvPlayer1.load(); 					//加载数据流
        // this.flvPlayer1.play();					//播放数据流
        // this.flvPlayer1.on('error', (e) => {
        //   console.log(e)
        // })
      };

      ws.onerror = function() {
        console.log('WebSocket 连接出错');
      };

      ws.onclose = function(e) {
        console.log('websocket 断开: ' + e.code + ' ' + e.reason + ' ' + e.wasClean);
        console.log('WebSocket 连接关闭');
        // 尝试重连
        //setTimeout(this.setupWebSocket, 2000);
      };
    },
    loadFlv() {
      this.saveOptions()

      let video = document.getElementById('video')
      console.log(video);
      let flv = this.flv = new FlvExtend({
        element: video,
        frameTracking: this.extendSettings.frameTracking, // 追帧设置
        updateOnStart: this.extendSettings.updateOnStart, // 点击播放按钮后实时更新视频
        updateOnFocus: this.extendSettings.updateOnFocus, // 回到前台后实时更新
        reconnect: this.extendSettings.reconnect, // 断流后重连
        reconnectInterval: 2000, // 重连间隔(ms)
        maxReconnectAttempts: 10, // 最大重连次数
        trackingDelta: 2, // 追帧最大延迟
        showLog: true
      })

      this.player = this.flv.init(
          {
            type: 'flv',
            url: 'ws://'+this.serverIp +':'+this.wsPort+'/monitor/device/monitor/1/0/'+new Date().getTime(),
            isLive: true,
            hasAudio: this.flvSettings.hasAudio,
            withCredentials: this.flvSettings.withCredentials
          },
          {
            enableStashBuffer: false, // 如果您需要实时（最小延迟）来进行实时流播放，则设置为false
            autoCleanupSourceBuffer: true, // 对SourceBuffer进行自动清理
            stashInitialSize: 128, // 减少首帧显示等待时长
            enableWorker: true // 启用分离的线程进行转换
          }
      )

      // 播放
      this.player.play()

      // 绑定事件回调
      flv.onStatisticsInfo = (data) => {
        this.stats = JSON.stringify(data, null, 2)
      }

      flv.onError = (err, currentPlayer) => {
        console.log('onError', err, currentPlayer)
        this.error = '播放失败，准备重连'
      }

      flv.onReconnect = ({reconnectAttempts}) => {
        console.log('onReconnect')
        this.error = `尝试第${reconnectAttempts}次重连...`
      }

      flv.onReconnectFailed = (err) => {
        this.error = '重连失败，请检查视频'
      }

      flv.onProgress = e => {
        if (this.error) {
          this.error = ''
        }
      }
    },
    getOptions() {
      let flvSettings = sessionStorage.getItem('flvExtendDemo_flv')
      let extendSettings = sessionStorage.getItem('flvExtendDemo_extend')
      let url = sessionStorage.getItem('flvExtendDemo_url')

      if (flvSettings) {
        this.flvSettings = JSON.parse(flvSettings)
      }
      if (extendSettings) {
        this.extendSettings = JSON.parse(extendSettings)
      }
      if (url) {
        this.url = url
      }
    },
    saveOptions() {
      sessionStorage.setItem('flvExtendDemo_flv', JSON.stringify(this.flvSettings))
      sessionStorage.setItem('flvExtendDemo_extend', JSON.stringify(this.extendSettings))
      sessionStorage.setItem('flvExtendDemo_url', this.url)
    },
  }
}
</script>