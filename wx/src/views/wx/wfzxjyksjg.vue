<template>
  <div class="wfzxjyksjg-content">
    <div class="wfzxjyksjg-ksfs">{{correctCount}}分</div>
    <div class="wfzxjyksjg-zsnr">
      <van-divider :style="{ color: '#000', borderColor: '#1989fa', padding: '0 20%'}" class="wfzxjyksjg-sfhg">
        {{ sfjg }}
      </van-divider>
      <p class="wfzxjyksjg-zhpj">综合评价</p>
      <div class="wfzxjyksjg-bccj">本次成绩</div>
      <div>
        <div><van-icon name="passed" color="#1989fa" class="wfzxjyksjg-dccomstyle"/><van-icon name="close" color="#ee0a24" class="wfzxjyksjg-dccomstyle"/></div>
        <div><span class="wfzxjyksjg-dccomstyle wfzxjyksjg-dcsmstyle">{{correctCount}}题</span><span class="wfzxjyksjg-dccomstyle wfzxjyksjg-dcsmstyle">{{errorCount}}题</span></div>
      </div>
      <div class="wfzxjyksjg-ksxx">
        <p><span>考试类别：</span>正式考试</p>
        <p><span>姓名：</span>{{ xm }}</p>
        <p><span>身份证号：</span>{{ sfzmhm }}</p>
        <p><span>手机号码：</span>{{ sjh }}</p>
        <p><span>处罚编号：</span>{{ cfbh }}</p>
      </div>
      <div class="wfzxjyksjg-btn">
        <van-button round block type="info">预约办理</van-button>
        <van-button round block plain type="info" style="margin-top: 10px" to="/index">返回首页</van-button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "wfzxjyksjg",
  data: function (){
    return {
      correctCount:0,
      errorCount:0,
      sfjg:'',
      xm:'',
      sfzmhm:'',
      sjh:'',
      cfbh:''
    }
  },
  mounted() {
    let _this = this;
    _this.xm = _this.$route.query.xm;
    _this.sfzmhm = _this.$route.query.sfzmhm;
    _this.sjh = _this.$route.query.sjh;
    _this.cfbh = _this.$route.query.cfbh;
    _this.correctCount = _this.$route.query.correctCount;
    _this.errorCount = _this.$route.query.errorCount;
    if(_this.correctCount>=90){
      _this.sfjg='及格';
    }else{
      _this.sfjg='不及格';
    }
  }
}
</script>
<style scoped>
.wfzxjyksjg-content{
  background-image: url('/static/wx/wfzxjy/wfzxjyksjg-bg.jpg');
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}
.wfzxjyksjg-ksfs{
  font-size: 30px;
  color: red;
  position: fixed;
  top: 11%;
  left: 43%;
}
.wfzxjyksjg-zsnr{
  position: fixed;
  width: 100%;
  top: 17%;
  height: 74%;
  overflow-y: auto;
}
.wfzxjyksjg-sfhg{
  margin-bottom: 0;
  font-size: 18px;
}
.wfzxjyksjg-zhpj{
  margin: 5px;
  text-align: center;
  font-size: 12px;
  color: #D5DCE4;
}
.wfzxjyksjg-bccj{
  text-align: center;
  width: 100px;
  padding: 3px;
  font-size: 14px;
  margin: 0 auto;
  background-color: #C9E6FB;
  border-radius: 10px;
  color: #97BBDA;
}
.wfzxjyksjg-dccomstyle{
  width: 50%;
  text-align: center;
  margin: 3px 0;
}
.wfzxjyksjg-dcsmstyle{
  display: inline-block;
}
.wfzxjyksjg-ksxx{
  width: 74%;
  margin: 10px auto;
  border: 1px solid #C9DCE9;
  border-radius: 8px;
  background-color: #D6F1FF;
  padding-left: 10px;
  font-size: 14px;
  padding-top: 10px;
}
.wfzxjyksjg-ksxx p{
  margin-bottom: 10px;
  margin-top: 0;
}
.wfzxjyksjg-ksxx span{
  display: inline-block;
  width: 70px;
  color: #516775;
}
.wfzxjyksjg-btn{
  width: 74%;
  margin: 0 auto;
}
</style>