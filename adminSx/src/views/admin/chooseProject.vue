<template>
  <div>
    <div style="display: flex;flex-direction: row;flex-wrap: wrap;">
      <button style="margin: 30px;border-radius: 50px;" class="btn-info" v-for="item in xmbhs" v-on:click="toWelcome(item)">
        <div style="display: flex;flex-direction: column;padding: 50px;height: 446px;">
          <img src="/static/PRCD15.png" style="width: 236px;"/>
          <span style="margin-top: 20px;font-size: 30px;display: block;width: 236px;">{{xmbhMap|optionMapKV(item)}}</span>
        </div>
      </button>
    </div>
  </div>
</template>
<script>
export default {
  name: "choose-project",
  data: function (){
    return {
      user:'',
      xmbhs:[],
      xmbhMap:{},
      localZhbht:LOCAL_ZHBHT
    }
  },
  mounted() {
    let _this = this;
    _this.user = Tool.getLoginUser();
    _this.$forceUpdate();
    _this.getXmbhAndXmmc();
    _this.getWaterProjects();
  },
  methods: {
    toWelcome(xmbh){
      let _this = this;
      console.log(xmbh);
      _this.user.xmbh = xmbh;
      _this.$forceUpdate();
      Tool.setLoginUser(_this.user);
      _this.$router.push("/welcome");
    },
    getXmbhAndXmmc(){
      let _this = this;
      Loading.show();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getXmbhAndXmmc').then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.xmbhMap = resp.content;
        _this.$forceUpdate();
      })
    },
    getWaterProjects(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"!=Tool.getLoginUser().deptcode){
        data = {"usercode":_this.user.loginName};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/findWaterProjectAllByExample', data).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.xmbhs = resp.content;
        if(_this.localZhbht&&_this.xmbhs.includes("002")){
          _this.xmbhs.splice(_this.xmbhs.indexOf("002"),1);
        }
        _this.$forceUpdate();
      })
    }
  }
}
</script>