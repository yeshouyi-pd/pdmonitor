<template>
    <div>
        <div class="ksxx">电子保单</div>
        <van-empty image="search" v-show="showPic" :description="message" />
        <div class="div" v-show="showDiv">
            <van-cell-group>
                <van-cell title="号牌号码">{{hphm}}</van-cell>
                <van-cell title="车辆识别代号(后六位)">{{dzbdinfo.clsbdh}}</van-cell>
                <van-cell title="交强险投标状态">
                    <span v-if="dzbdinfo.jqxtbzt == '1'">确认(投保状态)</span>
                    <span v-else-if="dzbdinfo.jqxtbzt == '2'">一般投保退保</span>
                    <span v-else-if="dzbdinfo.jqxtbzt == '3'">重复投保退保</span>
                    <span v-else-if="dzbdinfo.jqxtbzt == '4'">起保前退保</span>
                </van-cell>
                <van-cell title="车船税纳税税所属时期起始日期">{{dzbdinfo.ccsnsqsrq}}</van-cell>
                <van-cell title="车船税纳税税所属时期终止日期">{{dzbdinfo.ccsnszzrq}}</van-cell>
            </van-cell-group>
        </div>
        <div class="button_db">
            <van-button to="/index" round block type="info" native-type="button">
                返回首页
            </van-button>
        </div>

    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "dzbdinfo",
        data:function(){
            return{
                hphm: '',
                showPic: '',
                showDiv: '',
                message: '',
                dzbdinfo: {},
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.hphm = _this.$route.query.hphm;
            _this.getDzbdInfo();
        },
        methods: {
            getDzbdInfo(){
                let _this = this;
                let formData = new FormData();
                formData.append("hphm", _this.hphm);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehInsuranceinfo/findByHphm',
                    formData
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        _this.dzbdinfo = resp.content;
                        _this.dzbdinfo.bbxrsfzmhm = Tool.decryptByDES(_this.dzbdinfo.bbxrsfzmhm);
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
        width: 95%;
        position:fixed;
        bottom: 10px;
        left: 2.5%;
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
    .bgimg {
        position:fixed;
        width:100%;
        height:100%;
    }
    .kbimg {
        position:fixed;
        top:39%;
        width: 80%;
        left: 10%;
        height: 45%;
    }
</style>