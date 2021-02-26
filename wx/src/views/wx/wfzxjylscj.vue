<template>
  <div>
    <div class="wfzxjylscj-title">
      <van-image :src="headimgurl" class="wfzxjylscj-title-img"/>
      <div>
        <div class="wfzxjylscj-box-height" style="line-height: 70px">{{xm}}</div>
        <div class="wfzxjylscj-box-height">身份证：{{sfzhm}}</div>
      </div>
    </div>
    <div style="width: 100%">
      <div class="wfzxjylscj-list" v-for="item in lscjList">
        <div class="wfzxjylscj-list-score">{{item.sdfs}}</div>
        <div class="wfzxjylscj-list-content">
          <van-cell title="电动车考试" :value="item.kssj" style="height: 40px;padding: 15px 20px 0 10px;border-top-right-radius: 10px;" />
          <van-cell :title="'处罚编号:'+item.cfbh" style="height: 40px;padding: 0 20px 0 10px;border-bottom-right-radius: 10px;">
            <template style="float: right">
              <van-button plain type="info" round size="mini">去预约</van-button>
            </template>
          </van-cell>
        </div>
      </div>
    </div>
    <van-empty description="没有历史成绩" v-if="showEmpty">
      <van-button round type="info" class="bottom-button" to="/index">返回主页</van-button>
    </van-empty>
  </div>
</template>
<script>
import Dialog from "vant/lib/dialog";
export default {
  name : "wfzxjylscj",
  data : function (){
    return{
      xm:"",
      sfzhm:"",
      cfbh:"",
      lscjList:[],
      headimgurl: '',
      showEmpty:false
    }
  },
  mounted() {
    let _this = this;
    _this.headimgurl = Tool.getWxHeadimgurl();
    let user = Tool.getWxUser();
    if(user){
      _this.xm = user.zjmc;
      _this.sfzhm = user.zjhm;
    }
    _this.initList();
  },
  methods:{
    initList(){
      let _this = this;
      if(_this.xm === '' || _this.sfzhm===''){
        Dialog({ message: "请实名认证" });
        _this.$router.push({path:'/smrz'});
      }
      let formData = {
        'xm':_this.xm,
        "sfzmhm":_this.sfzhm
      };
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/drvExam/findLog', formData).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          _this.lscjList = resp.content;
          if(_this.lscjList && _this.lscjList.length>0){
            _this.showEmpty = false;
          }else {
            _this.showEmpty = true;
          }
        } else {
          Dialog({message: resp.message});
        }
      })
    }
  }
}
</script>
<style scoped>
  .wfzxjylscj-title{
    height: 100px;
    display: flex;
    padding: 10px;
    background-image: url('/static/wx/wfzxjy/wfzxjybg.png');
    background-size: 100% 100%;
    background-position: center center;
    overflow: auto;
  }
  .wfzxjylscj-title-img{
    width: 70px;
    height: 70px;
    margin: 15px;
  }
  .wfzxjylscj-box-height{
    height: 50px;
  }
  .wfzxjylscj-list{
    height: 80px;
    width: 94%;
    display: flex;
    margin: 3%;
    border: 1px solid #ccc;
    border-radius: 10px;
  }
  .wfzxjylscj-list-score{
    width: 11%;
    height: 40px;
    margin: 15px 0;
    line-height: 40px;
    background-color: #1989fa;
    color: #FFFFFF;
    border-radius: 50%;
    font-size: 12px;
    text-align: center;
    margin-left: 1%;
  }
  .wfzxjylscj-list-content{
    width: 88%;
  }
  .bottom-button {
    width: 160px;
    height: 40px;
  }
</style>