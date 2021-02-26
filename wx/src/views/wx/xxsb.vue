<template>
    <div>
        <van-image src="/static/wx/bmfw/xxsb1.jpg"/>

        <van-grid :column-num="3">
            <van-grid-item icon="/static/wx/ks.png" to="/tpcjbxz" text="套牌车举报"/>
            <van-grid-item icon="/static/wx/jtsb.png" to="/jtsssb" text="交通设施故障上报"/>
            <van-grid-item icon="/static/wx/jssb.png" to="/zssb" text="积水上报"/>
        </van-grid>

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
        name: "xxsb",
        data: function () {
            return {
                active: 0,
                activeNames: ['0'],
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

</style>