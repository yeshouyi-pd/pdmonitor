<template>
    <div>
        <van-image src="/static/wx/bmfw/bmfw1.jpg"/>

        <div class="basediv" >
            <div>
                <div class="miandiv" style="background-image: url('/static/wx/bmfw/Group6.png');" onclick="javascript:window.location.href='/xxsb'">
                    <div class="maintext">
                        信息上报
                    </div>
                    <div  class="showtext">
                        在线上报
                    </div>
                </div>
                <div class="miandiv" style="background-image: url('/static/wx/bmfw/Group10.png')" onclick="javascript:window.location.href='/kfym'">
                    <div class="maintext">
                        远程处理
                    </div>
                    <div  class="showtext">
                        在线处理
                    </div>
                </div>
            </div>
            <div>
                <div class="miandiv" style="background-image: url('/static/wx/bmfw/Group13.png')" onclick="javascript:window.location.href='/ssjbpage'">
                    <div class="maintext">
                        随手举报
                    </div>
                    <div  class="showtext">
                        安全 方便 省心
                    </div>
                </div>
                <div class="miandiv" style="background-image: url('/static/wx/bmfw/Group15.png')" onclick="javascript:window.location.href='/kfym'">
                    <div class="maintext">
                        电视交警
                    </div>
                    <div class="showtext">
                        便民 规范 高效
                    </div>
                </div>
            </div>
        </div>

        <div style="width: 100%;text-align: center;" >
            <div>
              <router-link v-for="item in pageList" v-bind:key="item.id" to="item.url">
                <div  class="ywldiv">
                  <div v-if="item.pic" :style="{backgroundImage:'url('+item.pic+')'}" class="picdiv"></div>
                  <div class="ywlxtext">{{item.name}}</div>
                </div>
              </router-link>
            </div>
        </div>
        <div style="clear:both;margin-bottom: 80px;"></div>
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
    export default {
        name: "bmfw",
        data:function(){
            return{
              active: 1,
              openid: '',
              pageList:[]
            }
        },
        mounted:function(){//mounted初始化方法
          let _this = this;
          _this.getPageList();
        },
        methods:{
          onChange(index) {
            let _this = this;
          },
          getPageList(){
            let _this = this;
            let formData = {
              'parentName':'bmfw',
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
        }
    }
</script>

<style scoped>
    form,p,div,ol,ul,p,h1,h2,h3,h4,input,li,span{margin:0px;padding:0px;}
    img{border:0;}
    h1{font-size:1.4em;}
    h2{font-size:1.17em; color:#0060a1}
    h3{font-size:1em;}
    .Nav{
        display: flex;
        margin-top:0px;
        width:100%;
        background: #ffffff;
    }
    .Nav .Nav-left{
        width:50%;
        padding:0;
        box-sizing: border-box;
        border-right:1px solid #eeeeee;
    }
    .Nav .Nav-left h3{
        font-size:0.9em;
    }
    .Nav .Nav-left h4{
        font-size:0.76em;
        color:#aaa;
    }
    .Nav .Nav-left img{
        width:100%;
    }
    .Nav .Nav-right{
        width:50%;
        box-sizing: border-box;
    }
    .Nav-right .Nav-r-top{
        display: flex;
        align-items: center;
        padding:16px 10px;
        height: 50px;
    }
    .Nav-right .Nav-r-top:first-child{
        border-bottom:1px solid #eeeeee;
        border-top:1px solid #eeeeee;
    }
    .Nav-right .Nav-r-top:nth-child(2){
        border-bottom:1px solid #eeeeee;
    }
    .Nav-right .Nav-r-top .txt{
        width:70%
    }
    .Nav-right .Nav-r-top .txt h3{
        font-size:0.9em;
        margin-left: 50%;
    }
    .Nav-right .Nav-r-top .txt h4{
        font-size:0.7em;
        color:#aaa;
    }
    .Nav-right .Nav-r-top .img{
        width:30%;
    }
    .Nav-right .Nav-r-top .img img{
        width:100%;
    }
    .basediv{
        margin: 10px 0 0 0;
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
        margin: 12% 0 0 46%;
        color: white;
        font-size: 0.8em;
        font-weight: bold
    }
    .showtext{
        padding: 4px 0 3px 46% ;
        border-radius: 5px;
        color: white;
        font-size: 0.6em;
    }
    .picdiv{
        background-color: #4263FF;
        width:50px;
        height: 50px;
        border-radius:15px;
        margin-left:23%;
        background-size: 100% 100%;
        background-position: center center;
        overflow: auto;
    }
    .ywlxtext{
        margin: 3px 0 0 0px;
        font-size: 0.75em;
        color: black;
    }
    .ywldiv{
        float:left ;width: 24%;
        margin: 6% 0 0 0;
    }
</style>