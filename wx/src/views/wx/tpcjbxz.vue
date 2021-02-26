<template>
    <div>
        <div class="ksxx">套牌车报案须知</div>
        <div style="font-size: 14px;width: 95%; margin: auto;">
            <p>（1）欢迎使用海口交警套牌车报案功能。</p>
            <p>（2）仅支持实名认证本人名下车辆报案，他人车辆及单位车辆，请前往交警窗口办理。</p>
            <p>（3）严禁提供虚假材料，违者将按照有关法律规定进行处理。</p>
            <p>（4）需提供报案人身份证、行驶证及车身照片。</p>
        </div>
        <div class='placeholder-view'></div>
        <div class="button_db1">
            <van-button round block to="tpcjbxx" type="info" native-type="submit">
                套牌车报案
            </van-button>
        </div>
        <div class="button_db2">
            <van-button round block to="tpcjbls" type="info" native-type="submit">
                报案历史记录
            </van-button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "tpcjbxz",
        data: function () {
            return {

            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
        },
        methods: {

        }
    }
</script>

<style scoped>
    .ksxx {
        font-size: 17px;
        width: 100%;
        background-color: #F2F2F2;
        height: 40px;
        text-align: center;
        line-height: 40px;
    }
    .button_db1 {
        width: 95%;
        position:fixed;
        bottom: 60px;
        left: 2.5%;
    }
    .button_db2 {
        width: 95%;
        bottom: 10px;
        position:fixed;
        left: 2.5%;
    }
    .placeholder-view{
        width: 100%;
        height: 100px;
    }
</style>