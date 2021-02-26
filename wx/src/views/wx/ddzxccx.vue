<template>
  <div>
    <van-image width="100%" src="/static/wx/ddzxccx.jpg"/>
    <van-form validate-first @submit="onSubmit">
      <div>
        <van-field
            v-model="hphm"
            label="号牌号码"
            name="hphm"
            placeholder="请输入号牌号码"
        />
        <div class="button_db">
          <van-button round block type="info" native-type="submit">
            提交
          </van-button>
        </div>
      </div>
    </van-form>
  </div>
</template>
<script>
import Dialog from "vant/lib/dialog";
export default {
  name: "ddzxccx",
  data: function (){
    return {
      hphm: '',
    }
  },
  methods: {
    onSubmit() {
      let _this = this;
      if(Tool.isEmpty( _this.hphm)){
        Dialog({ message: "请输入正确号牌号码" });
        return;
      }
      _this.$router.push({path:'/ddzxccxjg', query:{hphm: _this.hphm}});
    }
  }
}
</script>
<style scoped>
.button_db {
  width: 90%;
  margin: 10% 0 0 5%;
}
</style>