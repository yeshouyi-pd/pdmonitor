<template>
  <div style="height: 630px;">
    <form class="form-horizontal" style="margin-top: 20px;">
      <div class="form-group">
        <label class="col-sm-2 control-label">体积</label>
        <div class="col-sm-3">
          <input v-model="bodyAssess.volume" class="form-control">
        </div>
        <label class="col-sm-2 control-label">BAI</label>
        <div class="col-sm-3">
          <input v-model="bodyAssess.bai" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">体长</label>
        <div class="col-sm-3">
          <input v-model="bodyAssess.bodyLength" class="form-control">
        </div>
        <label class="col-sm-2 control-label">估算年龄段</label>
        <div class="col-sm-3">
          <input v-model="bodyAssess.ageGroup" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">总体重(kg)</label>
        <div class="col-sm-3">
          <input v-model="bodyAssess.totalWeight" class="form-control">
        </div>
        <label class="col-sm-2 control-label">总体重BMI值</label>
        <div class="col-sm-3">
          <input v-model="bodyAssess.totalBmi" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label">胖瘦判定</label>
        <div class="col-sm-3">
          <input v-model="bodyAssess.fatThin" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-3 control-label">图片</label>
        <div class="col-sm-9">
          <img v-if="imgUrl" :src="imgUrl" width="150px;" height="120px;" />
          <file input-id="sm1" :suffixs="picSuffixs" :afterUpload="imgUpload"></file>
        </div>
      </div>
    </form>
    <div style="text-align: center;margin-top: 160px;">
      <button v-on:click="saveBodyAssess()" type="button" class="btn btn-primary">保存</button>
    </div>
  </div>
</template>
<script>
import File from "@/components/file";
export default {
  name:'body-assess',
  components:{File},
  props: ["uavFlyVideoId","id"],
  data: function(){
    return {
      picSuffixs:['png','jpg','gif'],
      imgUrl:'',
      bodyAssess:{}
    }
  },
  methods:{
    imgUpload(res){
      let _this = this;
      if(res && res.success){
        _this.imgUrl = process.env.VUE_APP_SERVER + res.content;
        _this.bodyAssess.imgUrl = _this.imgUrl;
      }else{
        Toast.error(res.content);
      }
    },
    saveBodyAssess(){
      let _this = this;
      // 保存校验
      if (1 != 1
          || !Validator.require(_this.bodyAssess.volume, "体积")
          || !Validator.require(_this.bodyAssess.bai, "BAI")
          || !Validator.require(_this.bodyAssess.bodyLength, "体长")
          || !Validator.require(_this.bodyAssess.ageGroup, "估算年龄段")
          || !Validator.require(_this.bodyAssess.totalWeight, "总体重(kg)")
          || !Validator.require(_this.bodyAssess.totalBmi, "总体重BMI值")
          || !Validator.require(_this.bodyAssess.fatThin, "胖瘦判定")
          || !Validator.require(_this.bodyAssess.imgUrl, "图片")
      ) {
        return;
      }
      _this.bodyAssess.uavFlyVideoId=_this.uavFlyVideoId;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/bodyAssess/saveChoose', _this.bodyAssess).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          //调用父组件方法
          this.$emit('choose-after', {index:'3',ids:resp.content.id});
        } else {
          Toast.warning(resp.message);
        }
      })
    }
  }
}
</script>