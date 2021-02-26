<template>
    <div>
        <van-steps inactive-icon="clock" active-icon="location" :active="0">
            <van-step>业务须知</van-step>
            <van-step>业务类型</van-step>
            <van-step>填写信息</van-step>
        </van-steps>
        <div style="text-align: center;">
                <font color="red" style="font-size: 1.5em">业务须知</font>
        </div>
        <div class="van-address-list">
            <van-notice-bar background="white" color ="black" wrapable :scrollable="false">
                (1) 申请人确认身份证在有效期限内且信息未发生变化。
                <p/>
                (2) 申请人应确认所需预约业务种类相关的驾驶证或机动车不存在不能办理业务的情形。
                <p/>
                (3) 申请成功后，请在预约日期当天到达预约车管所办理业务。
                <p/>
                <font color="red">
                    *目前办证大厅业务预约支持XXX，请按所属辖区预约相关业务。
                    <p/>
                    *申请人在提交预约申请且成功后视为业务已登记，不得取消。如果预约当天无法抵达预约车管所办理业务，请在过期后重新预约。
                    <p/>
                    *多次预约但在预约办理日期未到达预约办理地点办理业务的，将被记入黑名单，停止预约办理业务的权限。
                </font>
                <div style="margin-left: 15px;margin-top: 10px;font-size: 14px;">
                    <van-checkbox style="float: left;" v-on:change="checkeds" v-model="checked" shape="square">
                        <font style="font-size: 1em;font-weight: bold;color: #00a0e9"> 阅读并同意</font>

                    </van-checkbox>
                </div>
            </van-notice-bar>
        </div>
        <div class="van-address-list__bottom">
            <div style="margin: 16px;">
                <van-button id="hides" round block type="info"
                            color="linear-gradient(to right,#00BFFF,#0000FF)"
                            disabled="disabled"
                >
                    我同意并已阅读
                </van-button>

                <van-button  id="shows"  style="display: none" round block type="info"
                             color="linear-gradient(to right,#00BFFF,#0000FF)"
                             to="/ywyy/ywsldw">
                    我同意并已阅读
                </van-button>
            </div>
            <div style="margin-top: 8px"></div>
        </div>
    </div>
</template>

<script>

    export default {
        name:'ywydxz',
        data:function(){
            return{
                checked:false,//定时器 ，销毁时捕获
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.checked=false;



        },
        methods:{
            checkeds() {
                let _this = this;
                if(_this.checked){
                    $('#hides').hide();
                    $('#shows').show();
                }else{
                    $('#hides').show();
                    $('#shows').hide();
                }
            },

        },


    }
</script>

<style scoped>

    /deep/ .van-notice-bar__content {
        position: relative;
        white-space: normal;
        word-wrap: break-word;
        color: #646566;
        font-size: 1.0em;

    }
</style>
