<template>
    <div>
        <div style="font-size: 30px;text-align: center;width: 90%;margin: auto;line-height: 50px;">保险报案</div>
        <div style="font-size: 18px;text-align: center;width: 90%;margin: auto;line-height: 40px;">您可以向保险公司报案了</div>
        <div style="font-size: 16px;text-align: center;width: 90%;margin: auto;">甲方保险信息</div>
        <div style="width: 95%;margin: auto;">
            <van-cell-group>
                <van-cell title="姓名">{{dsrxm1}}</van-cell>
                <van-cell title="责任"><span v-if="dsrbsgzr1 == '1'">全责</span><span v-else-if="dsrbsgzr1 == '2'">无责</span></van-cell>
                <van-cell title="保险公司">{{depts|optionMapKV(dsrbxgs1)}}</van-cell>
            </van-cell-group>
        </div>
        <div style="width: 88%;margin:10px auto;font-size: 14px;color: red;">
            提示：如果您是无责方驾驶员，请在取得全责方驾驶员同意后选择一键报案.
        </div>
        <div class="button_db">
            <van-button v-on:click="yjba" round block type="info" native-type="button">
                一键报案
            </van-button>
        </div>
        <div class="button_db1">
            <van-button v-on:click="fqyjba" round block type="info" native-type="button">
                放弃一键报案
            </van-button>
        </div>
    </div>
</template>

<script>

    import Dialog from "vant/lib/dialog";

    export default {
        name: "jtsgyjba",
        data() {
            return {
                sgbh: '',
                dsrxm1: '',
                dsrbsgzr1: '',
                dsrbxgs1: '',
                depts: [],
                info: {},
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.sgbh = _this.$route.query.sgbh;
            _this.dsrxm1 = _this.$route.query.dsrxm1;
            _this.dsrbsgzr1 = _this.$route.query.dsrbsgzr1;
            _this.dsrbxgs1 = _this.$route.query.dsrbxgs1;
            _this.getDeptMap();
        },
        methods: {
            /**
             * 获取部门信息
             */
            getDeptMap(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDeptMap', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.depts = resp.content;
                })
            },
            /**
             * 一键报案
             */
            yjba(){
                let _this = this;
                _this.info.sgbh = _this.sgbh;
                _this.info.zt = "2";
                _this.info.clfs = "1";
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/updateAccident',
                    _this.info,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push({path:'/cgym'});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            /**
             * 放弃一键报案
             */
            fqyjba(){
                let _this = this;
                _this.info.sgbh = _this.sgbh;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/delAccident',
                    _this.info,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        Dialog.alert({
                            message: '放弃一键报案成功',
                        }).then(() => {
                            _this.$router.push({path:'/index'});
                        });
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        position: absolute;
        bottom: 65px;
        transform: translateX(5%);
    }
    .button_db1 {
        width: 90%;
        position: absolute;
        bottom: 10px;
        transform: translateX(5%);
    }
</style>