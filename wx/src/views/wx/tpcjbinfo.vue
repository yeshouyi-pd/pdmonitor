<template>
    <div>
        <div class="ksxx">套牌车上报</div>
        <van-form @submit="onSubmit">
            <van-field v-model="info.hphm" readonly label="号牌号码"/>
            <van-field v-model="wxUser.zjmc" readonly label="姓名"/>
            <van-field v-model="wxUser.zjhm" readonly label="身份证明号码"/>
            <van-field
                    v-model="wxUser.lxdh"
                    label="手机号码"
                    placeholder="手机号码"
            />
            <van-field
                    v-model="info.jtzz"
                    label="联系地址"
                    placeholder="联系地址"
            />
            <van-field
                    v-model="info.zj"
                    label="套牌车证据"
                    placeholder="套牌车证据"
            />
            <van-field
                    v-model="info.qksm"
                    rows="2"
                    autosize
                    label="情况说明"
                    type="textarea"
                    maxlength="100"
                    placeholder="请输入情况说明"
                    show-word-limit
            />
            <div class="button_db">
                <van-button round block type="info" native-type="submit">
                    下一步
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "tpcjbinfo",
        data() {
            return {
                wxUser: {},
                info: {},
            };
        },
        mounted() {
            let _this = this;
            _this.info.hphm = _this.$route.query.hphm;
            _this.info.hpzl = _this.$route.query.hpzl;
            _this.wxUser = Tool.getWxUser();
        },
        methods: {
            onSubmit(values) {
                let _this = this;
                if(Tool.isEmpty( _this.wxUser.lxdh)){
                  Dialog({ message: "请输入手机号码" });
                  return;
                }
                if(Tool.isEmpty( _this.info.jtzz)){
                  Dialog({ message: "请输入联系地址" });
                  return;
                }
                if(Tool.isEmpty( _this.info.zj)){
                  Dialog({ message: "请输入套牌车证据" });
                  return;
                }
                _this.info.openid = Tool.getWxOpendid();
                _this.info.xm = _this.wxUser.zjmc;
                _this.info.sfzmhm = _this.wxUser.zjhm;
                _this.info.lxdh = _this.wxUser.lxdh;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehRobbery/wxsave',
                    _this.info
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push({path:'/tpcjb', query:{
                                id: _this.info.id,lsh: _this.info.lsh,
                            }});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
        },
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        margin: 5% 0 0 5%;
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