<template>
    <div>
        <div class="ksxx">申领人信息</div>
        <div style="font-size: 14px;width: 95%; margin: auto;">
            <p>（1）车牌固封螺丝申领功能是海口市公安局交通管理局为方便用户开通的便民服务功能。</p>
            <p>（2）《机动车号牌专用固封装置》（GA804—2008）的有关规定：号牌固封装置主要由螺栓、螺母、固封底座、固封扣盖组成。固封扣盖上印有代表省、自治区、直辖市简称的汉字和代表发牌机关代号的字母，与号牌上登记编号的省、自治区、直辖市简称的汉字和代表发牌机关代号一致。机动车号牌必须按照规定悬挂，在指定位置使用号牌专用固封装置安装，不使用机动车号牌专用固封装置或者使用其他装置安装的属违法行为。</p>
            <p>（3）用户可自行选择使用线上或线下渠道进行固封螺丝申领。线上申领时，需车主本人提交申请，且在一个自然年内申请人每辆车只能在线上申领一次。</p>
            <p>（4）线上申领的单独补发号牌专用固封装置费（批准收费文号：国家发展和改革委员会、财政部发改价格【2004】2831号）和快递费由申请人自行承担。</p>
            <p>（5）线上渠道暂时无法提供收据，如需收据请前往线下渠道办理领取。</p>
        </div>
        <div class='placeholder-view'></div>
        <div class="button_db1">
            <van-button round block to="cplsslxs" type="info" native-type="submit">
                线上办理
            </van-button>
        </div>
        <div class="button_db2">
            <van-button round block to="cplsslxx" type="info" native-type="submit">
                线下办理
            </van-button>
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "cplssl",
        data: function () {
            return {

            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getWxSmrzUser();
        },
        methods: {
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