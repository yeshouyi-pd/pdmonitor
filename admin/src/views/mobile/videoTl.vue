<template>
  <div style="width: 1920px;height: 100vh;background-image: url('/static/image/environment/bg02.png');background-size: 100%;margin: auto">
    <header style="position: relative;background: none">
      <img src="/largemonitors/assets/imgs/headertitle1.png" style="position: absolute;left: 30%;top:20px;width: 750px;">
      <div class="lefttitle" style="top: 15px;left: 25px;color: #fff;display: flex;">
        <!--        <img src="/largemonitors/assets/imgs/左上角title.png" alt="" style="width: 250px;">-->
        <!--        <span>-->
        <!--          <div @click="chooseProject" style="cursor: pointer;">主页</div>-->
        <!--        </span>-->
        <div class="title-item" @click="back">返回</div>
      </div>
    </header>
    <div class="page-first-div">
      <div class="left-box">
        <iframe width="100%" height="100%" src="http://119.3.2.53:8808/" scrolling="no" frameborder="0"></iframe>
        <!--        <div class="left-box-item">-->
        <!--          &lt;!&ndash;          <div style="height: 5%;margin-top: 10px;display: flex;flex-direction: row;align-items: center;margin-left: 20px;">&ndash;&gt;-->
        <!--          &lt;!&ndash;            <div v-on:click="back()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer">&ndash;&gt;-->
        <!--          &lt;!&ndash;              返回&ndash;&gt;-->
        <!--          &lt;!&ndash;            </div>&ndash;&gt;-->
        <!--          &lt;!&ndash;          </div>&ndash;&gt;-->
        <!--          <div class="left-video-box">-->
        <!--            <iframe ref="firstIframe" @click="vueSendMsg(4,'firstIframe')" v-trigger width="100%" height="100%" src="http://119.3.2.53:9909/" scrolling="no" frameborder="0"></iframe>-->
        <!--          </div>-->
        <!--          <div class="left-text-style">7号浮标</div>-->
        <!--        </div>-->
      </div>
<!--      <div class="right-box">-->
<!--        <div class="right-top-box" id="playbox">-->
<!--          <video autoplay="autoplay" loop="loop" style="width: 100%;height: 100%" controls>-->
<!--            <source class="video" title="主监控位" src="/video/12.mp4" type="video/mp4" />-->
<!--          </video>-->
<!--        </div>-->
<!--        <div class="right-bottom-box">-->
<!--          <div style="height: 414px;width: 97%;overflow:auto;margin-top: 1%;margin-left: 1%;color: #82CF48;font-size: 16px;">-->
<!--            <table id="simple-table" class="table  table-bordered" >-->
<!--              <thead>-->
<!--              <tr>-->
<!--                <td>设备名称</td>-->
<!--                <td>开始时间</td>-->
<!--                <td>结束时间</td>-->
<!--                <td>视频</td>-->
<!--              </tr>-->
<!--              </thead>-->
<!--              <tbody>-->
<!--              <tr v-for="item in videoEvents">-->
<!--                <td>{{devices|optionNSArray(item.sbbh)}}</td>-->
<!--                <td>{{item.kssj}}</td>-->
<!--                <td>{{item.jssj}}</td>-->
<!--                <td style="cursor: pointer;" v-on:click="getPlayUrl(item)">查看视频</td>-->
<!--              </tr>-->
<!--              </tbody>-->
<!--            </table>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/pagination";
import Times from "@/components/times";

export default {
  name: 'video-dp',
  components: {Pagination,Times},
  data: function (){
    return {
      LOCAL_VIDEO:LOCAL_VIDEO,
      devices:[],
      videoEvents:[]
    }
  },
  created() {
    let _this = this;
    //获取所有的设备，因为要用到设备的位置
    _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getDevice').then((res)=>{
      let response = res.data;
      _this.devices = response.content.list;
    })
  },
  mounted() {
    let _this = this;
    _this.getExplainVideoEvent();
  },
  directives: {
    trigger: {
      inserted (el, pinging) {
        el.click()
      }
    }
  },
  methods: {
    getPlayUrl(item){
      let _this = this;
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
        }else{
          Toast.error("未找到源文件或文件大小为0，无法转码！");
        }
      })
    },
    getExplainVideoEvent(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getExplainVideoEvent', {sfysp:1}).then((response)=>{
        let resp = response.data;
        _this.videoEvents = resp.content;
        _this.getPlayUrl(_this.videoEvents[0]);
      })
    },
    back(){
      let _this = this;
      window.location.href = "/mobile/largemonitors";
    },
    vueSendMsg(tdh,numIframe) {
      setTimeout(() =>{
        const iframeWindow = this.$refs[numIframe].contentWindow
        iframeWindow.postMessage({
          cmd: 'myVue',
          params: {
            tdh: tdh
          }
        }, '*')
      }, 1000)
    }
  }
}
</script>
<style scoped>
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
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
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