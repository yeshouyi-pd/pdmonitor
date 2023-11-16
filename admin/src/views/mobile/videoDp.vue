<template>
  <div style="width: 1920px;height: 100vh;background-image: url('/static/image/environment/bg02.png');background-size: 100%;margin: auto">
    <div class="page-first-div">
      <div class="left-box">
        <div class="left-box-item">
          <div style="height: 5%;margin-top: 10px;display: flex;flex-direction: row;align-items: center;margin-left: 20px;">
            <div v-on:click="back()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer">
              返回
            </div>
          </div>
          <div class="left-video-box" style="height: 88%;">
            <iframe ref="firstIframe" @click="vueSendMsg(1,'firstIframe')" v-trigger width="100%" height="100%" src="http://119.3.2.53:9909/" scrolling="no" frameborder="0"></iframe>
          </div>
        </div>
        <div class="left-box-item">
          <div class="left-video-box" style="height: 88%;margin-top: 10%;">
            <iframe ref="secondIframe" @click="vueSendMsg(3,'secondIframe')" v-trigger width="100%" height="100%" src="http://119.3.2.53:9909/" scrolling="no" frameborder="0"></iframe>
          </div>
        </div>
        <div class="left-box-item">
          <div class="left-video-box">
            <video autoplay="autoplay" loop="loop" style="width: 100%;height: 100%">
              <source class="video" src="/video/11.mp4" type="video/mp4" />
            </video>
          </div>
        </div>
        <div class="left-box-item">
          <div class="left-video-box">
            <video autoplay="autoplay" loop="loop" style="width: 100%;height: 100%">
              <source class="video" src="/video/11.mp4" type="video/mp4" />
            </video>
          </div>
        </div>
      </div>
      <div class="right-box">
        <div class="right-top-box">
          <video autoplay="autoplay" loop="loop" style="width: 100%;height: 100%">
            <source class="video" title="主监控位" src="/video/12.mp4"/>
          </video>
        </div>
        <div class="right-bottom-box">
          <table style="width: 100%;overflow:auto;margin-top: 3%;">
            <thead>
              <tr>
                <td>设备位置</td>
                <td>开始时间</td>
                <td>结束时间</td>
                <td>视频</td>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td>查看视频</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
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

    }
  },
  mounted() {

  },
  directives: {
    trigger: {
      inserted (el, pinging) {
        el.click()
      }
    }
  },
  methods: {
    back(){
      let _this = this;
      window.location.href = "/mobile/largemonitorsZj";
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
.page-first-div{
  width: 100%;
  height: 100%;
  overflow: auto;
  display: flex;
  flex-direction: row;
}
.left-box{
  width: 50%;
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
  height: 95%;
  margin-left: 5%;
  margin-top: 3%;
  background-color: rgb(7,14,40);
}
.right-box{
  width: 50%;
  height: 100%;
  display: flex;
  flex-direction: column;
}
.right-top-box{
  width: 95%;
  height: 47%;
  margin-top: 3%;
  margin-left: 3%;
}
.right-bottom-box{
  width: 100%;
  height: 50%;
  text-align: center;
  color: #fff;
}
</style>