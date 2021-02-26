<template>
    <div>
        <div class="ksxx">我的渍水上报</div>
        <ul>
            <template v-for="(item,index) in brcolumnslx">
                <li :key="index">
                    <van-cell icon="shop-o" is-link :to="{path:'/zssbinfo',query:{id:item.id}}">
                        {{item.zsdd}}
                    </van-cell>
                </li>
            </template>
        </ul>

        <van-tabbar v-model="active">
            <van-tabbar-item replace to="/zssb" icon="wap-home">
                渍水上报
            </van-tabbar-item>
            <van-tabbar-item replace to="/zssbls" icon="manager">
                渍水上报历史
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "zssbls",
        data() {
            return {
                active: 1,
                brcolumnslx: [],
                wxUser: {},
            };
        },
        mounted() {
            let _this = this;
            _this.wxUser = Tool.getWxUser();
            _this.selectWater();
        },
        methods: {
            selectWater() {
                let _this = this;
                let formData = new FormData();
                formData.append("openid", Tool.getWxOpendid());
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/reportWater/selectWater',
                    formData
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.brcolumnslx = resp.content;
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
        },
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
        font-weight:bold;
        clear: both;
    }
</style>