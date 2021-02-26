<template>
  <div>
    <van-image width="100%" src="/static/wx/ddzxccx.jpg"/>
    <van-empty image="search" v-show="showPic" :description="message" />
    <div v-show="showDiv" >
      <van-cell-group>
        <van-cell title="号牌号码">{{info.hphm}}</van-cell>
        <van-cell title="号牌种类"><span v-if="info.hpzl=='01'">蓝牌电动车(合格)</span><span v-if="info.hpzl=='02'">黄牌电动车(临时)</span><span v-if="info.hpzl=='03'">残疾人电动</span></van-cell>
        <van-cell title="车架号">{{info.cjh}}</van-cell>
        <van-cell title="所有人">{{info.syr}}</van-cell>
        <van-cell title="证件号">{{info.zhhm}}</van-cell>
        <van-cell title="手机号">{{info.sjhm}}</van-cell>
        <van-cell title="生产厂家">{{info.sccj}}</van-cell>
        <van-cell title="登记地点">{{info.dw}}</van-cell>
        <van-cell title="登记日期">{{info.zcrq}}</van-cell>
      </van-cell-group>
    </div>
    <van-button round type="info" to="/index" native-type="button" class="button_db">
        返回主页
    </van-button>
  </div>
</template>
<script>
export default {
  name: "ddzxccxjg",
  data: function (){
    return {
      info:{},
      showPic:true,
      showDiv:false,
      message:'该接口暂时有问题，请稍后再试'
    }
  },
  mounted() {
    let _this = this;
    _this.findData();
  },
  methods: {
    findData(){
      let _this = this;
      let formData = new FormData();
      formData.append("hphm",_this.$route.query.hphm);
      formData.append("userName",Tool.getWxUser().zjmc);
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/jdccxLog/getDdcQuery',
          formData
      ).success((response)=>{
        let resp = response.data;
        if (resp.success) {
          _this.info = resp.content;
          if(_this.info.fhztm && _this.info.fhztm=='1'){
            _this.showDiv = true;
          }else{
            _this.showpic = true;
            _this.message = "未查询到相关信息";
          }
        } else {
          _this.message = resp.message;
        }
      }).error((data)=>{
        _this.showpic = true;
        _this.message = "未查询到相关信息";
      })
    },
  }
}
</script>
<style scoped>
.button_db {
  width: 80%;
  margin: 5% 0 0 10%;
}
</style>