<template>
  <div style="width: 1920px;height: 100vh;background-image: url('/static/image/environment/bg02.png');background-size: 100%;margin: auto">
    <header style="position: relative;background: none">
      <img src="/largemonitors/assets/imgs/headertitle2.png" style="position: absolute;left: 30%;top:20px;width: 750px;">
      <div class="lefttitle" style="top: 15px;left: 25px;">
        <img src="/largemonitors/assets/imgs/左上角title.png" alt="" style="width: 250px;">
        <span>
          <div @click="chooseProject" style="cursor: pointer;">主页</div>
        </span>
      </div>
    </header>
    <div class="page-first-div">
      <div class="left-box">
        <iframe width="100%" height="100%" src="http://119.3.2.53:9909/" scrolling="no" frameborder="0"></iframe>
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
<!--        <div class="left-box-item">-->
<!--          <div class="left-video-box">-->
<!--            <iframe ref="secondIframe" @click="vueSendMsg(7,'secondIframe')" v-trigger width="100%" height="100%" src="http://119.3.2.53:9909/" scrolling="no" frameborder="0"></iframe>-->
<!--          </div>-->
<!--          <div class="left-text-style">8号浮标</div>-->
<!--        </div>-->
<!--        <div class="left-box-item">-->
<!--          <div class="left-video-box">-->
<!--            <iframe ref="threeIframe" @click="vueSendMsg(9,'threeIframe')" v-trigger width="100%" height="100%" src="http://119.3.2.53:9909/" scrolling="no" frameborder="0"></iframe>-->
<!--          </div>-->
<!--          <div class="left-text-style">5号浮标</div>-->
<!--        </div>-->
<!--        <div class="left-box-item">-->
<!--          <div class="left-video-box">-->
<!--            <iframe ref="fourIframe" @click="vueSendMsg(10,'fourIframe')" v-trigger width="100%" height="100%" src="http://119.3.2.53:9909/" scrolling="no" frameborder="0"></iframe>-->
<!--          </div>-->
<!--          <div class="left-text-style">4号浮标</div>-->
<!--        </div>-->
      </div>
      <div class="right-box">
        <div class="right-top-box">
          <video autoplay="autoplay" loop="loop" style="width: 100%;height: 100%" controls>
            <source class="video" title="主监控位" src="/video/12.mp4" type="video/mp4" />
          </video>
        </div>
        <div class="right-bottom-box">
          <div style="height: 414px;width: 97%;overflow:auto;margin-top: 1%;margin-left: 1%;color: #82CF48;font-size: 16px;">
            <table id="simple-table" class="table  table-bordered table-hover" >
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
                <td style="cursor: pointer;">查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td style="cursor: pointer;">查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td style="cursor: pointer;">查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td style="cursor: pointer;">查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td style="cursor: pointer;">查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td style="cursor: pointer;">查看视频</td>
              </tr>
              <tr>
                <td>3号浮标</td>
                <td>2023-10-09 14:04:01</td>
                <td>2023-10-09 14:07:34</td>
                <td style="cursor: pointer;">查看视频</td>
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

export default {
  name: 'video-dp',
  components: {Pagination,Times},
  data: function (){
    return {
      LOCAL_VIDEO:LOCAL_VIDEO
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
    chooseProject(){
      let _this = this;
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
    chooseEnvironment(){
      window.location.href = "/mobile/environmentDp";
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
  height: calc(100% - 88px);
  display: flex;
  flex-direction: row;
}
.left-box{
  width: 60%;
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