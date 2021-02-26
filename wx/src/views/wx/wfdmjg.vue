<template>
    <div>
        <van-image width="100%" src="/static/wx/bmfw/wfdm1.jpg"/>
        <van-empty class="kbimg" image="search" v-show="showPic" :description="message" />
        <div v-show="showDiv">
            <van-cell-group>
                <van-cell title="违法代码" :value="webchatIllcode.wfdm" />
                <van-cell title="违法行为" :value="webchatIllcode.wfxw" />
                <van-cell title="违法条例" :value="webchatIllcode.wftl" />
                <van-cell title="处罚依据" :value="webchatIllcode.cfyj" />
                <van-cell title="计分" :value="webchatIllcode.jf" />
                <van-cell title="处罚金额" :value="webchatIllcode.fkje" />
            </van-cell-group>
            <div v-on:click="fhwfdm" style="text-align: center;font-size: 12px;color: indianred;">其它违法代码查询></div>
        </div>
        <div class="button_db" >
            <van-button round block type="info" to="/index" native-type="button">
                返回首页
            </van-button>
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "wfdmjg",
        data() {
            return {
                showPic: '',
                showDiv: '',
                message: '',
                wfdm: '',
                lb: '',
                webchatIllcode: {},
            };
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.wfdm = _this.$route.query.wfdm;
            _this.lb = _this.$route.query.lb;
            _this.onSubmit();
        },
        methods:{
            fhwfdm() {
                let _this = this;
                _this.$router.push("/wfdm");
            },
            onSubmit() {
                let _this = this;
                this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatIllcode/selectwfdm', {
                    wfdm: _this.wfdm,
                    lb: _this.lb,
                }).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.webchatIllcode = resp.content;
                        _this.showDiv = 'true';
                    } else {
                        _this.showPic = 'true';
                        _this.message = resp.message;
                    }
                })
            },
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
    .van-cell {
        position: relative;
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        box-sizing: border-box;
        width: 100%;
        padding: 10px 16px;
        overflow: hidden;
        color: #323233;
        font-size: 14px;
        line-height: 18px;
        background-color: #fff;
    }
    .kbimg {
        position:fixed;
        top:33%;
        width: 80%;
        left: 10%;
        height: 45%;
    }
</style>