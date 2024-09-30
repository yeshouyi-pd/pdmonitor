<template>
  <div class="swiper-container" :id="id">
    <div class="swiper-wrapper" style="height: 300px" >
      <div class="swiper-slide" v-for="item in list" style="width: 100%;height: 100%;">
        <video width="100%" height="100%" autoplay="autoplay" controls loop>
          <source class="video" title="主监控位" :src="item"/>
        </video>
      </div>
    </div>
    <!-- 如果需要分页器 -->
    <div class="swiper-pagination"></div>
  </div>
</template>

<script>
export default {
  name: "swiper-video",
  props: ["list","id"],
  data: function(){
    return {
      mySwiper:null,
    }
  },
  watch: {
    list: {
      immediate: true,
      handler(newVal,oldVal) {
        this.$nextTick(() => {
          let _this = this;
          if(this.mySwiper){
            this.mySwiper.destroy(true,false)
          }
          this.mySwiper = new Swiper('#'+this.id,{
            loop: true,
            pagination : '.swiper-pagination',
            observer:true,
            observeParents:true,
          })
          var videos = document.querySelectorAll(".swiper-slide video");
          for (var i = 0; i < videos.length; i++) {
            videos[i].addEventListener("ended", function() {
              console.log(_this.mySwiper);
              if (_this.mySwiper.activeIndex === videos.length - 1) {
                // 最后一个视频结束，重新开始
                _this.mySwiper.swipeTo(0);
              } else {
                // 自动播放下一个视频
                _this.mySwiper.swipeNext();
              }
            });
          }
        });
      }
    }
  },
  methods: {
    fileExists(url){

      let isExists;

      $.ajax({

        url:url,

        async:false,

        type:'HEAD',

        timeout:2000,

        error:function(){

          isExists=0;

        },

        success:function(){

          isExists=1;

        }

      });

      if(isExists==1){

        return true;

      }else{

        return false;

      }

    },
    /**
     * 判断服务的文件是否存在
     * @param filepath 文件地址
     * @param filename
     * @returns {Boolean}
     */
    isExistFile(filepath){
      if(filepath == null || filepath === ""){
        return false
      }
      let xmlhttp;
      if (window.XMLHttpRequest){
        xmlhttp=new XMLHttpRequest();
      }else{
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
      }
      xmlhttp.open("GET",filepath,false);
      xmlhttp.send();
      if(xmlhttp.readyState === 4){
        if(xmlhttp.status === 200) return true; //url存在
        else if(xmlhttp.status === 404) return false; //url不存在
        else return false;//其他状态
      }
    }
  }
};
</script>
<style></style>
