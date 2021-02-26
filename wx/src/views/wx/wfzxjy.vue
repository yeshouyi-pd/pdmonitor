<template>
  <div>
    <van-form @submit="onSubmit">
      <van-field
          value="电动车"
          name="kskm"
          label="考试类型"
          readonly
      />
      <van-field
          v-model="xm"
          type="text"
          name="xm"
          label="姓名"
          placeholder="姓名"
          readonly
      />
      <van-field
          v-model="sfzh"
          type="text"
          name="sfzh"
          label="身份证号"
          placeholder="身份证号"
          readonly
      />
      <van-field
          v-model="sjhm"
          type="tel"
          name="sjhm"
          label="手机号码"
          placeholder="手机号码"
          readonly
      />
      <van-field
          v-model="cfbh"
          type="text"
          name="cfbh"
          label="处罚编号"
          placeholder="处罚编号"
      />
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit" class="wfzxjy-magin-bottom">在线考试</van-button>
        <van-button round block plain type="info" to="/wfzxjylscj">历史成绩</van-button>
      </div>
    </van-form>
    <div class="wfzxjy-ts">温馨提示：全真模拟考试共100题，每做错1道题扣1分，考试过程中非正常提交答卷（如考试中断、退出等），再次进入考试时可以选择继续答题或提交答卷，过了答题时间则自动提交答卷。</div>
  </div>
</template>
<script>
import Dialog from "vant/lib/dialog";
export default {
  name:'wfzxjy',
  data:function(){
    return{
      kskm: '',
      xm:'',
      sfzh: '',
      sjhm: '',
      cfbh: '',
      pattern:/^\d{11}$/
    }
  },
  mounted() {
    let _this = this;
    _this.getWxSmrzUser();
  },
  methods:{
    /**
     * 实名认证
     */
    getWxSmrzUser(){
      let _this = this;
      if(null == Tool.getWxUser()){
        Dialog({ message: "请实名认证" });
        _this.$router.push({path:'/smrz'});
      }else{
        let user = Tool.getWxUser();
        _this.xm = user.zjmc;
        _this.sfzh = user.zjhm;
        _this.sjhm = user.lxdh;
      }
    },
    onSubmit(){
      let _this = this;
      if(Tool.isEmpty( _this.cfbh)){
        Dialog({ message: "请填写处罚编号" });
        return;
      }
      _this.$router.push({
        path: "/wfzxjyspxx",
        query:{
          xm:_this.xm,
          sfzh:_this.sfzh,
          cfbh:_this.cfbh
        }
      });//违法在线教育视频学习
    }
  }

}
</script>

<style scoped>
.wfzxjy-magin-bottom{
  margin-bottom: 20px;
}
.van-button--normal {
  font-size: 18px;
}
.wfzxjy-ts{
  margin: 16px;
  font-size: 14px;
  color: #999;
}
</style>