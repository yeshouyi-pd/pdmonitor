<template>
  <div class="swiper-container" :id="id">
    <div class="swiper-wrapper" style="height: 300px">
      <div class="swiper-slide" v-for="Carousel in list"><img  :src="Carousel.imgUrl" width="300px" height="420px"/></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Carousel",
  props: ["list","id"],
  data: function(){
    return {
      mySwiper:null
    }
  },
  watch: {
    list: {
      immediate: true,
      handler() {
        this.$nextTick(() => {
          if(this.mySwiper){
            this.mySwiper.destroy(true,false)
          }
          console.log(this.list);
          this.mySwiper = new Swiper('#'+this.id,{
            autoplay : 3000,//可选选项，自动滑动
            loop : true,//可选选项，开启循环
          })
        });
      },
    },
  },
};
</script>
<style></style>
