<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">视频图像抽提</h4>
      </div>
    </div>
    <p>
      <big-file-upload></big-file-upload>
    </p>

  </div>
</template>
<script>
import BigFileUpload from "../../components/big-file-upload1";
export default {
  name:'video-image-extraction',
  components:{BigFileUpload},
  data:function (){
    return {

    }
  },
  mounted() {
    let _this = this;

  },
  methods: {
    //上传视频
    addVideo(){
      let _this = this;
      _this.showUpload = true;
    },
  }
}
</script>
<style scoped>

</style>