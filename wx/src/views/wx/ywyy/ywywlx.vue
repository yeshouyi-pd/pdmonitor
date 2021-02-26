<template>
    <div>
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
                <img src="/static/wx/ywyy/ywyyindex.jpg" style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>

      <div style="width:100%; position: absolute;left:50%;top:50%;transform: translate(-50%, -50%)">
        <van-row>
          <van-col offset="2" span="20" style="padding-top: 1.5em">
            <van-button
                round
                v-on:click="ywyy()"
                style="background: linear-gradient(to right, #7FFFAA,	#1E90FF);
                               height: 50px;"
                size="large">
              <font style="font-size: 1.3em;color: white">业务预约</font>
            </van-button>
          </van-col>
          <van-col offset="2" span="20" style="padding-top: 1.5em">
            <van-button @click="ywcx"
                        round
                        style="background: linear-gradient(to right, #7FFFAA,#1E90FF);
                              height: 50px;"
                        size="large">
              <font style="font-size: 1.3em;color: white">预约查询</font>
            </van-button>
          </van-col>
          <van-col offset="2" span="20" style="padding-top: 1.5em">
            <van-button @click="ywqx()"
                        round
                        style="background: linear-gradient(to right,#7FFFAA,#1E90FF);
                              height: 50px;"
                        size="large">
              <font style="font-size: 1.3em;color: white">预约取消</font>
            </van-button>
          </van-col>
        </van-row>

      </div>
      <div class="van-address-list__bottom" style="padding-bottom: 0px;">
        <h2 style="font-weight: bold;color: #4d69e0;font-size: 80%;">温馨提示：</h2>
        <p style="color: #969696;line-height: 1.4em;font-size: 0.7em;">XXX市公安交通管理局车辆通行证办理系统：支持互联网通行证申请、续办、查询等功能。申请通行证需要提供<span style="color:red">真实有效</span>的信息，如盗用他人信息、恶意提供虚假信息申请办理通行证，海口市公安交通管理局将追究其法律责任。如有疑问请拨打咨询电话：0311-85858148。
        </p>
      </div>

    </div>
</template>

<script>



    import Dialog from "vant/lib/dialog";

    export default {
        name:'ywywlx',
        data:function(){
            return{

            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;

        },
        methods:{
            /**
             * 业务预约前获取当前用户今天是否存在已经预约但是未办结的业务
             */
            ywyy(){
                let _this = this;
                let openid = "";
                 if(Tool.isEmpty(Tool.getWxUser()) ){
                     Dialog({ message: "请实名认证" });
                    _this.$router.push("/smrz");
                }else{
                    openid =Tool.getWxUser().openid;
                    if(Tool.isEmpty(openid)){
                      Dialog({ message: "操作异常！" });
                     _this.$router.push("/index");
                    }

                }
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/queryCountByopenid',{openid:openid }).then((response)=>{
                    let resp = response.data;
                    let wxyys = resp.content;
                    if(wxyys.length > 0){
                        Dialog.confirm({
                            theme: 'round-button',
                            confirmButtonText:'继续',
                            message: '您还有'+wxyys.length+"笔未办理的业务，是否继续预约",
                        })
                            .then(() => {
                                _this.$router.push("/ywyy/ywyy");
                            })
                            .catch(() => {
                               // _this.$router.push("/index");
                            });
                    }else{
                        _this.$router.push("/ywyy/ywyy");

                    }

                })

            },


            /**
             *业务查询
             */
            ywcx(){
                let _this = this;
                _this.$router.push("/ywyy/yyinfo");
             },
            /**
             * 业务取消
             */
            ywqx(){
                let _this = this;
                _this.$router.push("/ywyy/yyqx");

            },
        }

    }
</script>

<style scoped>

</style>
