<template>
  <div class="swiper-container" :id="id">
    <div class="swiper-wrapper" style="height: 300px" v-if="LOCAL_SSBRL">
      <div class="swiper-slide" v-for="Carousel in list">
        <img :src="Carousel.imgUrl" width="300px" height="280px"/>
      </div>
    </div>
    <div class="swiper-wrapper" style="height: 300px" v-if="LOCAL_ZHBHT">
      <div class="swiper-slide" v-for="Carousel in list">
        <img :src="Carousel.imgUrl" width="300px" height="240px"/>
        <div :id="Carousel.id"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Carousel",
  props: ["list","id"],
  data: function(){
    return {
      mySwiper:null,
      LOCAL_ZHBHT:LOCAL_ZHBHT,
      LOCAL_SSBRL:LOCAL_SSBRL,
      curSrc:''
    }
  },
  watch: {
    list: {
      immediate: true,
      handler(newVal,oldVal) {
        this.$nextTick(() => {
          if(this.mySwiper){
            this.mySwiper.destroy(true,false)
          }
          if(this.LOCAL_ZHBHT){
            if(newVal.length>0){
              let firstTplj =newVal[0].imgUrl;
              this.curSrc = firstTplj.substring(0,firstTplj.lastIndexOf(".")+1)+"wav";
              if(document.getElementById(newVal[0].id)){
                document.getElementById(newVal[0].id).innerHTML='<video autoplay controls width="300px" height="20px" style="margin-top: 10px;">\n' +
                    '          <source src="'+this.curSrc+'"  type="video/webm">\n' +
                    '          您的浏览器不支持 HTML5 video 标签。\n' +
                    '        </video>';
              }
            }
            this.mySwiper = new Swiper('#'+this.id,{
              autoplay : 5000,//可选选项，自动滑动
              loop : false,//可选选项，开启循环
              observer:true,
              onSlideChangeStart: function(swiper){
                if(newVal[swiper.activeIndex]){
                  let tplj = newVal[swiper.activeIndex].imgUrl;
                  this.curSrc = tplj.substring(0,tplj.lastIndexOf(".")+1)+"wav";
                  if(document.getElementById(newVal[swiper.activeIndex].id)){
                    document.getElementById(newVal[swiper.activeIndex].id).innerHTML='<video autoplay controls width="300px" height="20px" style="margin-top: 10px;">\n' +
                        '          <source src="'+this.curSrc+'"  type="video/webm">\n' +
                        '          您的浏览器不支持 HTML5 video 标签。\n' +
                        '        </video>';
                  }
                }
              }.bind(this)
            })
          }
          if(this.LOCAL_SSBRL){
            this.mySwiper = new Swiper('#'+this.id,{
              autoplay : 5000,//可选选项，自动滑动
              loop : true,//可选选项，开启循环
            })
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
