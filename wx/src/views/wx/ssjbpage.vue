<template>
  <div>
    <van-image src="/static/wx/u1675.jpg"/>
    <ul>
      <template v-for="(item,index) in wflb">
        <li :key="index">
          <van-cell icon="/static/wx/bmfw/jbtp1.png" :title=item is-link :to="{path:'/ssjbinfo',query:{code:index,name:item}}"/>
        </li>
      </template>
    </ul>
    <van-tabbar v-model="active">
      <van-tabbar-item replace to="/ssjbpage" icon="wap-home">
        随手拍举报
      </van-tabbar-item>
      <van-tabbar-item replace to="/mysspjb" icon="manager">
        我的举报
      </van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
import Dialog from "vant/lib/dialog";

export default {
  name: "ssjbpage",
  data: function () {
    return {
      active: 0,
      title:'',
      wflb :[],
    }
  },
  mounted: function () {//mounted初始化方法
    let _this = this;
    _this.getWxSmrzUser();
    _this.getWflb();
  },
  methods: {
    /**
     * 获取违法类别
     */
    getWflb(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWflb', {
      }).then((response)=>{
        let resp = response.data;
        _this.wflb = resp.content;
      })
    },
    /**
     * 实名认证
     */
    getWxSmrzUser(){
      let _this = this;
      if(null == Tool.getWxUser()){
        Dialog({ message: "请实名认证" });
        _this.$router.push({path:'/smrz'});
      }
    },
    // /**
    //  * 获取交安故障类型类型
    //  */
    // getFacility(){
    //   let _this = this;
    //   _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getFacility', {
    //   }).then((response)=>{
    //     let resp = response.data;
    //     _this.columnslx = resp.content;
    //     console.log('columnslx', _this.columnslx);
    //     /*for(let i in resp.content){
    //         _this.columnslx.push(resp.content[i]);
    //     }*/
    //   })
    // },
  }
}
</script>

<style scoped>

</style>