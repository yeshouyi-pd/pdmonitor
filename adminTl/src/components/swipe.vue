<template>
  <div class="swiper-container" :id="id">
    <div class="swiper-wrapper" style="height: 300px" >
      <div class="swiper-slide" v-for="Carousel in list">
        <img :src="Carousel.imgUrl" width="300px" height="290px"/>
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
          this.mySwiper = new Swiper('#'+this.id,{
            autoplay : 5000,//可选选项，自动滑动
            loop : true,//可选选项，开启循环
          })
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
