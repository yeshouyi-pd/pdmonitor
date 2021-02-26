<template>
  <div style="width: 90%;margin:5%">
    <video-tool ref="_zxjyVedio" src="/static/wx/wfzxjy/example.mp4"></video-tool>
    <van-button round block type="info" v-on:click="startTest()" style="margin: 20px 0">考试</van-button>
    <div class="wfzxjyspxx-ts">温馨提示:</div>
    <div class="wfzxjyspxx-ts">1.视频文件较大，建议在良好的wifi环境下观看。获取视频失败时请点击重试。</div>
    <div class="wfzxjyspxx-ts">2.当天查扣车辆不能预约当天办理，请预约次日前来办理。</div>
    <van-count-down millisecond :time="time" :auto-start="false" ref="countDown" @finish="isFinished" format="ss:SSS" v-show="false"/>
  </div>
</template>
<script>
import videoTool from "@/components/videoTool";
import Dialog from "vant/lib/dialog";
export default {
  name: "wfzxjyspxx",
  components:{videoTool},
  data: function (){
    return{
      time : 25,
      isFinish : false,
    }
  },
  mounted() {
    let _this = this;
    //判断是否有记录
    //_this.initLog();
  },
  methods:{
    initLog(){
      let _this = this;
      let formData = {
        'xm':_this.$route.query.xm,
        "sfzmhm":_this.$route.query.zjhm,
        "cfbh":_this.$route.query.cfbh
      };
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/drvExam/findLog', formData).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          if(resp.content && resp.content.length>0){
            _this.isFinish = true;
          }
        } else {
          Dialog({message: resp.message});
        }
      })
    },
    startPlay(){
      let _this = this;
      if(this.$refs._zxjyVedio.isFirstTouch){
        _this.time = (new Date(this.$refs._zxjyVedio.$video.duration)).getTime()*1000;
        _this.$forceUpdate();
      }
      this.$nextTick(function (){
        _this.$refs.countDown.start();
      });
    },
    stopPlay(){
      let _this = this;
      _this.$refs.countDown.pause();
    },
    startTest(){
      let _this = this;
      if(!this.$refs._zxjyVedio.isFirstTouch){
        _this.$refs._zxjyVedio.state.playing = true;
        _this.$refs._zxjyVedio.clickPlayBtn();
      }
      if(_this.isFinish){
        _this.$router.push({
          path: "/wfzxjyzbdt",
          query: {
            cfbh: _this.$route.query.cfbh
          }
        });//违法在线教育准备答题
      }else{
        _this.$refs.countDown.pause();
        Dialog({ message: '请观看完整视频' });
      }
    },
    isFinished(){
      let _this = this;
      _this.isFinish = true;
    }
  }
}
</script>
<style scoped>
.wfzxjyspxx-ts{
  font-size: 14px;
  color: #999;
}
</style>