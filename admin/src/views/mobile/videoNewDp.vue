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
          <div class="modal-body" style="height: 85%;">
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
      websocketUrl:'ws://49.239.193.146:50091/monitor/device/monitor/'
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
</style>