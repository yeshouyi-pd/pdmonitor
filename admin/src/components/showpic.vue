<template>
  <div class="row">
    <div class="gallerys">
      <ul class="cl portfolio-area">
        <li class="item" style="list-style-type:none" v-for="(pic ,index) in pics" :key="index+'pic'">
          <div class="portfoliobox"  style="text-align: center">
            <div class="picbox">
              <img class="gallery-pic" v-bind:src="path+pic.zplj" onclick="$.openPhotoGallery(this)" />
            </div>
             {{codes | optionMapAndMapKV(pic) }}
          </div>
          <div style="height: 20px"></div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'showpic',
  props: {
    pics: {  //需要接续的图片
      default: []
    },
  },
data: function () {
  return {
    codes:[],//名字
    path:process.env.VUE_APP_SERVER,
  }
},
mounted: function() {
  let _this =this;
  _this.gatCode();
},
methods: {
  /**
   * 获取业务类型
   */
  gatCode(){
    let _this = this;
    _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/utils/gatCode').then((res)=>{
      let response = res.data;
      _this.codes = response.content;
    })
  },

}
}
</script>
