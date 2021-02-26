<template>
  <div>
    <van-image class="bgimg" src="/static/wx/bmfw/zmsy.jpg"/>
  </div>
</template>

<script>
import Notify from "vant/lib/notify";

export default {
  name: "code",
  data:function(){
    return{
      code: '',
      state: '',
      openid: '',
      accessToken: '',
      headimgurl: '',
    }
  },
  mounted:function(){//mounted初始化方法
    let _this = this;
    _this.getCode();
  },
  methods:{
    getQueryString(name) {
      let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      let r = window.location.search.substr(1).match(reg);
      if (r != null) return unescape(r[2]);
      return null;
    },
    getCode() {
      let _this = this;
      let code = _this.getQueryString("code");
      if (code == null || code === ""){
        let appid = "wxb7807839fc5db896";
        let url = encodeURIComponent("http://192.168.10.201/code");
        window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + url + "" +
            "&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect";
      } else {
        _this.code =  _this.getQueryString("code");
        _this.state =  _this.getQueryString("state");
        _this.getOpenid();
      }
    },
    getOpenid(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wechat/getOpenid', {
        code: _this.code,
        state: _this.state,
      }).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          _this.openid = resp.content.openid;
          _this.accessToken = resp.content.accessToken;
          Tool.setWxOpendid(resp.content.openid);
          Tool.setWxHeadimgurl(resp.content.headimgurl);
          _this.getWxuser();
        } else {
          Notify({type: 'danger', message: '获取openid失败'});
        }
      })
    },
    getWxuser(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wechat/getWxUser', {
        openid: _this.openid,
      }).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          if(null != resp.content){
            resp.content.zjhm = Tool.decryptByDES(resp.content.zjhm);
            resp.content.lxdh = Tool.decryptByDES(resp.content.lxdh);
            Tool.setWxUser(resp.content);
          }
        } else {
          Notify({type: 'danger', message: '获取微信用户失败'});
        }
        _this.$router.push({path:'/index'});
      })
    }
  }
}
</script>

<style scoped>
  .bgimg {
    position:fixed;
    width:100%;
    height:100%;
  }
</style>