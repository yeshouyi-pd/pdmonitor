<template>
    <div >
        <van-swipe :autoplay="3000">
            <van-swipe-item v-for="(image, index) in images" :key="index">
                <img v-lazy="image"  style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>
      <div class="basediv" >
        <div >
          <div  class="miandiv" style="background-image: url('/static/wx/index/yuyue.png')" v-on:click="ywywlx">
            <div class="maintext">
             车驾管业务预约
            </div>
            <div  class="showtext">
              人工窗口预约通道
            </div>
            <div class="ljjjdiv">
              <font  class="ljjjtext" >立即预约</font><br/>
            </div>
          </div>
          <div  class="miandiv"  style="background-image: url('/static/wx/index/cjgywbl.png')" onclick="javascript:window.location.href='/kfym'">
            <div class="maintext">
              车驾管业务办理
            </div>
            <div  class="showtext">
              安全  通畅
            </div>
            <div class="ljjjdiv">
              <font  class="ljjjtext" >立即办理</font><br/>
            </div>
          </div>
        </div>
        <div >
          <div  class="miandiv"  style="background-image: url('/static/wx/index/txz.png')" v-on:click="txzindex()">
            <div class="maintext">
              通行证办理
            </div>
            <div  class="showtext">
              便捷  高效
            </div>
            <div class="ljjjdiv">
              <font  class="ljjjtext" >立即办理</font><br/>
            </div>
          </div>
          <div  class="miandiv" style="background-image: url('/static/wx/index/kcyp.png')" v-on:click="kcyp()">
            <div class="maintext">
             快撤易赔
            </div>
            <div class="showtext">
              远程事故上报处理
            </div>
            <div class="ljjjdiv">
              <font class="ljjjtext">立即办理</font><br/>
            </div>
          </div>
        </div>
        <div style="width: 100% ;" >
          <div>
            <router-link v-for="item in pageList" v-bind:key="item.id" to="item.url">
            <div  class="ywldiv">
              <div v-if="item.pic" :style="{backgroundImage:'url('+item.pic+')'}" class="picdiv"></div>
              <div class="ywlxtext">{{item.name}}</div>
            </div>
            </router-link>
          </div>
        </div>
      </div>
        <van-tabbar v-model="active">
            <van-tabbar-item replace to="/index" icon="wap-home">
                掌上业务
            </van-tabbar-item>
            <van-tabbar-item replace to="/bmfw" icon="service">
                便民服务
            </van-tabbar-item>
            <van-tabbar-item replace to="/grzx" icon="manager">
                个人中心
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>

    import Dialog from "vant/lib/dialog";

    export default {
        name:'index',
        data:function(){
            return{
              active: 0,
              images: [
                '/static/wx/login1.jpg',
                '/static/wx/login2.jpg',
              ],
              openid: '',
              pageList:[]
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.getWxUser();
            _this.getPageList();
        },
        methods:{
          getPageList(){
            let _this = this;
            let formData = {
              'parentName':'zsyw',
              'isEnable':'1',
              "level":'1'
            };
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/wxPage/findAllByParam', formData).then((response)=>{
              let resp = response.data;
              if (resp.success) {
                _this.pageList = resp.content;
              } else {
                Dialog({message: resp.message});
              }
            })
          },
            /**
             * 跳转预约界面
             */
            ywywlx(){
                let _this = this;
               if(Tool.isEmpty(Tool.getWxUser()) ){
                     Dialog({ message: "请实名认证" });
                    _this.$router.push("/smrz");
                }else{
                    _this.$router.push("/ywyy/ywywlx");

                }
            },
        /**
         * 跳转预约界面
         */
        txzindex(){
            let _this = this;
            if(Tool.isEmpty(Tool.getWxUser()) ){
                Dialog({ message: "请实名认证" });
                _this.$router.push("/smrz");
            }else{
                _this.$router.push("/txz/tzxindex");

            }
        },
          /**
           *快撤易赔
           */
          kcyp(){
            let _this = this;
            _this.$router.push("/kcyp");
          },
            getWxUser(){
                if(null == Tool.getWxUser()){
                   // Dialog({ message: "未认证用户" });
                }else{
                   // Dialog({ message: "认证用户:"+Tool.getWxUser().zjmc});
                }
            },
        }

    }
</script>

<style scoped>
.basediv{
  margin: 10px 0 80px 0;
  padding:0;
  width: 100%;
  text-align: center;
  overflow-y:auto;
}
.miandiv{
  float:left;
  width: 45%;
  height: 5em;
  margin: 3%  8px  5px  8px;
  text-align: left;
  border-radius: 10px;
  box-shadow:3px 3px 3px #BEBEBE ;
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
}
.maintext{
  margin: 10px 0 0 20px;
  color: white;
  font-size: 0.8em;
  font-weight: bold
}
.showtext{
  padding: 4px 0 3px 20px ;
  border-radius: 5px;
  color: white;
  font-size: 0.6em;
}
.ljjjdiv{
  width: 60px;
  height: 21px;
  margin: 2px 0 0 20px ;
  text-align: center;
  border-radius: 5px;
  border: 0.9px white solid;
}
.ljjjtext{
  color: white;
  font-size: 0.6em;
}
.ywldiv{
  float:left ;width: 33%;
  margin: 6% 0 0 0;
}
.picdiv{
  background-color: #4263FF;
  width:50px;
  height: 50px;
  border-radius:15px;
  margin-left:29%;
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
}
.ywlxtext{
  margin: 3px 0 0 0px;
  font-size: 0.75em;
  color: black;
}

</style>
