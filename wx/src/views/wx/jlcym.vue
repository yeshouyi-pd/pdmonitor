<template>
    <div>
        <van-image class="bgimg" src="/static/wx/bmfw/ylcym1.jpg"/>
        <div class="button_db">
            <van-button v-on:click="onbutton()" round block type="info" native-type="button">
                已挪车
            </van-button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "jlcym",
        data() {
            return {
                info: {},
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.info.sgbh = _this.$route.query.sgbh;
            _this.info.dsrxm1 = _this.$route.query.dsrxm1;
            _this.info.dsrsjhm1 = _this.$route.query.dsrsjhm1;
        },
        methods: {
            onbutton() {
                let _this = this;
                _this.$router.push({path:'/jtsgxxcj', query:{sgbh: _this.info.sgbh,dsrxm1: _this.info.dsrxm1,dsrsjhm1: _this.info.dsrsjhm1}});
            },
        }
    }
</script>

<style scoped>
    .button_db {
        width: 80%;
        position:fixed;
        bottom: 5%;
        left: 10%;
    }
    .bgimg {
        position:fixed;
        width:100%;
        height:100%;
    }
</style>