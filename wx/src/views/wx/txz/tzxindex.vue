<template>
    <div>
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
                <img src="/static/wx/txz/txzindex.jpg" style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>
      <div style="width:100%; position: absolute;left:50%;top:50%;transform: translate(-50%, -50%)">
        <van-row>
            <van-col offset="2" span="20" style="padding-top: 3em">
                <van-button round
                           v-on:click="totxz()"

                            style="background: linear-gradient(to right, #7FFFAA,#1E90FF);
                               box-shadow: 2px 2px 10px #00FFFF;height: 50px;"
                            size="large">
                    <font style="font-size: 1.3em;color: white">通行证办理</font>
                </van-button>
            </van-col>
            <van-col offset="2" span="20" style="padding-top: 5em">
                <van-button round
                            to="/txz/txzquery"
                            style="background: linear-gradient(to right, #7FFFAA,#1E90FF);
                               box-shadow: 2px 2px 10px #00FFFF;height: 50px;"
                            size="large">
                    <font style="font-size: 1.3em;color: white">业务查询</font>
                </van-button>
            </van-col>
        </van-row>
      </div>

      <div class="van-address-list__bottom" style="padding-bottom: 0px">
            <h2 style=" ;font-weight: bold;color: #4d69e0;font-size: 80%;">温馨提示：</h2>
            <p style="color: #969696;line-height: 1.4em;font-size: 0.7em;">XXX市公安交通管理局车辆通行证办理系统：支持互联网通行证申请、续办、查询等功能。申请通行证需要提供<span style="color:red">真实有效</span>的信息，如盗用他人信息、恶意提供虚假信息申请办理通行证，海口市公安交通管理局将追究其法律责任。如有疑问请拨打咨询电话：0311-85858148。
            </p>
      </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    export default {
        name:'tzxindex',
        data:function(){
            return{
              pass:{},

            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            SessionStorage.remove(LIKN_TO_TXZ);

        },
        methods:{
          totxz(){
            let _this = this;
             if(Tool.isEmpty(Tool.getWxUser()) ){
               Dialog({ message: "请实名认证" });
               _this.$router.push("/smrz");
             }
            _this.pass.openid=Tool.getWxUser().openid;//获取opendi
            _this.pass.id =  Tool.uuid(12);//生成ID
            _this.$forceUpdate();
            SessionStorage.set(LIKN_TO_TXZ,_this.pass);//包括预约基本信息
            _this.$router.push("/txz/txzydxz");
          },

        }

    }
</script>

<style scoped>


</style>
