<template>
  <div>
    <span class="wfzxjyzbdt-title">非机动车违法考试</span>
    <div class="wfzxjyzbdt-content">
      <div class="wfzxjyzbdt-item">考试类型：电动车</div>
      <div class="wfzxjyzbdt-item">考试时间：45分钟</div>
      <div class="wfzxjyzbdt-item">出题规则：根据交管局出题规则</div>
    </div>
    <van-button round block type="info" class="wfzxjy-magin-bottom" style="margin: 16px;width: 90%;" v-on:click="wfzxjyksdj()">开始答卷</van-button>
    <div class="wfzxjyzbdt-ts">温馨提示：考试中每做错1道题扣1分，考试结束可直接进行“预约办理”或者“返回首页”进入违法在线教育历史成绩中进行预约。</div>
  </div>
</template>
<script>
export default {
  name:"wfzxjyzbdt",
  data:function (){
    return{

    }
  },
  methods:{
    wfzxjyksdj(){
      let _this = this;
      this.$router.push({
        path: "/wfzxjyksdj",
        query: {
          cfbh: _this.$route.query.cfbh
        }
      });
    }
  }
}
</script>
<style>
.wfzxjyzbdt-title{
  display: block;
  width: 100%;
  text-align: center;
  height: 60px;
  margin-top: 20px;
}
.wfzxjyzbdt-content{
  padding: 0 20% 10% 20%;
}
.wfzxjyzbdt-item{
  padding-bottom: 10px;
}
.wfzxjyzbdt-ts{
  margin: 16px;
  font-size: 14px;
  color: #999;
}
</style>