<template>
    <div>
        <div class="ksxx">快撤易赔事故</div>
        <ul>
            <li v-for="(item, index) in accidents" :key="item.id">
                <div>
                    <van-radio-group v-model="radio">
                        <van-radio :name="item.sgbh">
                            <span class="h3">事故地点：{{item.sgdd}}</span>
                            <br/>
                            <span class="h4">事故时间：{{item.sgfssj}}</span>
                        </van-radio>
                    </van-radio-group>
                    <van-divider/>
                </div>
            </li>
        </ul>

        <div style="margin: 16px;">
            <van-button round block type="info" v-on:click="navigation" native-type="button">
                查看详细
            </van-button>
        </div>

    </div>
</template>

<script>

    import Dialog from "vant/lib/dialog";
    import Notify from "vant/lib/notify";

    export default {
        name: "sgjdcx",
        data() {
            return {
                radio: '',
                accidents: [],
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getWxSmrzUser();
            _this.selectAccidentOpenid();
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
            /**
             * 获取事故信息
             */
            selectAccidentOpenid(){
                let _this = this;
                let formData = new FormData();
                formData.append("openid", Tool.getWxOpendid());
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/selectAccidentOpenid',
                    formData
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        _this.accidents = resp.content;
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
            navigation(){
                let _this = this;
                if(_this.radio === ''){
                    Notify({type: 'danger', message: '请先选择一项事故'});
                }else{
                    let sgbh = _this.radio;
                    _this.$router.push({path:'/sgjdcxinfo', query:{
                            sgbh: sgbh,
                        }});
                }
            },
        }
    }
</script>

<style scoped>
    ul li div {
        margin-top: 8px;
        margin-left: 5px;
    }

    .h3 {
        font-size: 15px;
    }

    .h4 {
        font-size: 13px;
        color: #aaa;
    }

    .ksxx {
        font-size: 17px;
        width: 100%;
        background-color: #F2F2F2;
        height: 40px;
        text-align: center;
        line-height: 40px;
        font-weight:bold;
    }
</style>