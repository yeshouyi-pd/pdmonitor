<template>
    <div>
        <div style="width: 100%;height: 150px;border: 0px solid blue;margin: 0px auto;background-color: #66CCFF;color: #FFFFFF;border-radius: 5px;">
            <ul style="line-height: 25px;padding-top: 10px;">
                <li style="text-align: center;">您的事故报案信息已上报</li>
                <li style="text-align: center;">(编号{{sgbh}})</li>
                <li style="width: 95%;margin: 0px auto;">系统正在查验拍摄的现场取证照片是否清晰。请你在确保安全的情况下，在原地稍后...，等待系统推送审核结果。</li>
            </ul>
        </div>
        <div>
            <p style="text-align: center;font-size: 20px;">请您保持电话畅通</p>
            <p style="text-align: center;">
                <van-image height="170px" width="170px" :src="path" />
            </p>
            <p style="width: 95%;margin: 0px auto;line-height: 25px;font-size: 14px;color: red;">上面的二维码请对方驾驶员用微信扫一扫，事故双方均可实时查看处理进展，避免争议</p>
        </div>
        <div class="button_db">
            <van-button v-on:click="but()" round block type="info" native-type="button">
                信息采集
            </van-button>
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "jtsgsbjg",
        data: function () {
            return {
                info: {},
                sgbh: '',
                path: '',
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.sgbh = _this.$route.query.sgbh;//路由传参
            _this.info.lsh = _this.$route.query.lsh;
            _this.info.dsrxm1 = _this.$route.query.dsrxm1;
            _this.info.dsrsjhm1 = _this.$route.query.dsrsjhm1;
            _this.path = process.env.VUE_APP_SERVER + '/wxbase/vehAccident/ewm?sgbh='+_this.sgbh;
        },
        methods: {
            but(){
                let _this = this;
                _this.$router.push({path:'/jlcym', query:{sgbh: _this.sgbh,dsrxm1: _this.info.dsrxm1,dsrsjhm1: _this.info.dsrsjhm1}});
            }
        }
    }
</script>

<style scoped>
    .button_db {
        width: 95%;
        position:fixed;
        bottom: 10px;
        left: 2.5%;
    }
</style>