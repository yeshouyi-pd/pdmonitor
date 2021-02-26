<template>
    <div>
        <van-image width="100%" src="/static/wx/bmfw/cpys1.jpg"/>
        <van-empty class="kbimg" image="search" v-show="showPic" :description="message" />
        <div v-show="showDiv">
            <van-cell-group>
                <van-cell title="车牌类型">{{columns|optionMapKV(vehLicelost.hpzl)}}</van-cell>
                <van-cell title="号牌号码" v-model="vehLicelost.hphm"/>
                <van-cell title="领取电话" v-model="vehLicelost.lqdz"/>
                <van-cell title="领取地址" v-model="vehLicelost.lqdh"/>
            </van-cell-group>
        </div>
        <div class="button_db">
            <van-button round block type="info" to="/index" native-type="submit">
                返回主页
            </van-button>
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "cpyscxjg",
        data: function () {
            return {
                vehLicelost: {},
                showPic: '',
                showDiv: '',
                message: '',
                columns: [],
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.vehLicelost.hpzl = _this.$route.query.hpzl;//路由传参
            _this.vehLicelost.hphm = _this.$route.query.hphm;
            _this.loadinfo();
            _this.getCplx();
        },
        methods: {
            onClickLeft() {
                let _this = this;
                _this.$router.push("/cpyscx");
            },
            /**
             * 获取车牌类型
             */
            getCplx(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getCplx', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columns = resp.content;
                    /*for(let i in resp.content){
                        _this.columns.push(resp.content[i]);
                    }*/
                })
            },
            loadinfo(){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehLicelost/selectLicelost',
                    _this.vehLicelost,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.vehLicelost = resp.content;
                        _this.showDiv = 'true';
                    } else {
                        _this.showPic = 'true';
                        _this.message = resp.message;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        position:fixed;
        bottom: 5%;
        left: 5%;
    }
    .kbimg {
        position:fixed;
        top:39%;
        width: 80%;
        left: 10%;
        height: 45%;
    }
</style>