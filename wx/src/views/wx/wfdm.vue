<template>
    <div>
        <van-image width="100%" src="/static/wx/bmfw/wfdm1.jpg"/>
        <div>
            <van-tabs style="width: 90%;margin: auto;" v-model="active">
                <van-tab title="机动车">
                    <van-form>
                        <p>输入违法代码：(例如："10030")</p>
                        <input class="text" type="text" v-model="jdcwfdm">
                        <div class="button_db">
                            <van-button round block type="info" v-on:click="query(1)" native-type="button">
                                查询
                            </van-button>
                        </div>
                    </van-form>
                </van-tab>
                <van-tab title="非机动车">
                    <van-form>
                        <p>输入违法代码：(例如："20030")</p>
                        <input class="text" type="text" v-model="fjdcwfdm">
                        <div class="button_db">
                            <van-button round block type="info" v-on:click="query(2)" native-type="button">
                                查询
                            </van-button>
                        </div>
                    </van-form>
                </van-tab>
            </van-tabs>
        </div>
    </div>
</template>

<script>

    export default {
        name: "wfdm",
        data() {
            return {
                active: 0,
                jdcwfdm: '',
                fjdcwfdm: '',
            };
        },
        mounted:function(){//mounted初始化方法
            let _this = this;

        },
        methods:{
            query(lb) {
                let _this = this;
                if(1 == lb){
                    _this.$router.push({ path: "/wfdmjg",
                        query: {wfdm:_this.jdcwfdm,lb:lb}
                    });
                }else{
                    _this.$router.push({ path: "/wfdmjg",
                        query: {wfdm:_this.fjdcwfdm,lb:lb}
                    });
                }
            },
        }
    }
</script>

<style scoped>
    .text{
        width: 100%;
        height: 30px;
        border-color: #66afe9;
        outline: 0;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
    }
    .button_db {
        width: 100%;
        margin: 10% 0 0 0;
    }
    .van-cell {
        position: relative;
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        box-sizing: border-box;
        width: 100%;
        padding: 10px 16px;
        overflow: hidden;
        color: #323233;
        font-size: 14px;
        line-height: 20px;
        background-color: #fff;
    }
</style>