<template>
    <div>
        <div class="ksxx">取消考试结果</div>
        <van-row type="flex" justify="center">
            <van-image v-show="showcg"  round default="成功" width="7rem" height="7rem" src="/static/wx/cg.png"/>
            <van-image v-show="showsb"  round default="失败" width="7rem" height="7rem" src="/static/wx/sb.png"/>
        </van-row>
        <van-cell-group>
            <van-cell title="考试科目">{{allkskm|optionMapKV(drvQxks.qxkm)}}</van-cell>
            <van-cell title="考试时间" v-model="drvQxks.yykssjstr"/>
            <van-cell title="姓名" v-model="drvQxks.xm"/>
            <van-cell title="证件类型">{{allzjlx|optionMapKV(drvQxks.zjlx)}}</van-cell>
            <van-cell title="身份证明号码" v-model="drvQxks.sfzmhm"></van-cell>
            <van-cell title="手机号码" v-model="drvQxks.sjhm"/>
            <van-cell title="申请时间" v-model="drvQxks.createTime"/>
        </van-cell-group>
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
        name: "qxksjg",
        data: function () {
            return {
                drvQxks: {},
                yykssj : '',
                showcg: '',
                showsb: '',
                allkskm: '',
                allzjlx: '',
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.drvQxks.sfzmhm = _this.$route.query.sfzmhm;//路由传参
            _this.drvQxks.yykssjstr = _this.$route.query.yykssjstr;
            _this.loadinfo();
            _this.getKskm();
            _this.getZjlx();
        },
        methods: {
            onClickLeft() {
                let _this = this;
                _this.$router.push("/qxks");
            },
            loadinfo(){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/drvQxks/selecetDrvQxks',
                    _this.drvQxks,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.drvQxks = resp.content;
                        _this.drvQxks.sfzmhm = Tool.decryptByDES(_this.drvQxks.sfzmhm);
                        _this.drvQxks.sjhm = Tool.decryptByDES(_this.drvQxks.sjhm);
                        _this.showcg = 'true';
                    } else {
                        _this.drvQxks.sfzmhm = '';
                        _this.drvQxks.yykssjstr = '';
                        _this.showsb = 'true';
                        Dialog({ message: resp.message });
                    }
                })
            },
            /**
             * 获取考试科目
             */
            getKskm(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getKskm', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.allkskm = resp.content;
                })
            },
            /**
             * 获取证件类型
             */
            getZjlx(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getSfzjlx', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.allzjlx = resp.content;
                })
            },
        }
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        position:fixed;
        bottom: 10px;
        left: 5%;
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