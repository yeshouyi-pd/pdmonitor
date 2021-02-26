<template>
    <div>
        <div style="margin: 20px auto;text-align: center;">简易交通事故认定责任书</div>
        <div>
            <table cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="2">当事人信息(甲方)</td>
                </tr>
                <tr>
                    <td style="width: 40%;">姓名</td>
                    <td>{{info.dsrxm1}}</td>
                </tr>
                <tr>
                    <td>号牌号码</td>
                    <td>{{info.hphm1}}</td>
                </tr>
                <tr>
                    <td>保险公司</td>
                    <td>{{depts|optionMapKV(info.dsrbxgs1)}}</td>
                </tr>
                <tr>
                    <td>保单编号</td>
                    <td>{{info.dsrbxpzh1}}</td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td>{{info.dsrsjhm1}}</td>
                </tr>

                <tr>
                    <td colspan="2">当事人信息(乙方)</td>
                </tr>
                <tr>
                    <td style="width: 40%;">姓名</td>
                    <td>{{info.dsrxm2}}</td>
                </tr>
                <tr>
                    <td>号牌号码</td>
                    <td>{{info.hphm2}}</td>
                </tr>
                <tr>
                    <td>保险公司</td>
                    <td>{{depts|optionMapKV(info.dsrbxgs2)}}</td>
                </tr>
                <tr>
                    <td>保单编号</td>
                    <td>{{info.dsrbxpzh2}}</td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td>{{info.dsrsjhm2}}</td>
                </tr>

                <tr>
                    <td colspan="2">当事人信息责任</td>
                </tr>
                <tr>
                    <td style="width: 40%;">甲方负起本次事故</td>
                    <td>全部责任<span v-if="info.dsrbsgzr1 == '1'">√</span>&emsp;&emsp;
                        无责任<span v-if="info.dsrbsgzr1 == '2'">√</span></td>
                </tr>
                <tr>
                    <td>乙方负起本次事故</td>
                    <td>全部责任<span v-if="info.dsrbsgzr2 == '1'">√</span>&emsp;&emsp;
                        无责任<span v-if="info.dsrbsgzr2 == '2'">√</span></td>
                </tr>
                <tr>
                    <td colspan="2">以上内容均为事实，如有不实，请承担法律责任</td>
                </tr>
                <tr>
                    <td colspan="2">
                        海口市公安局交通警察支队<br/>
                        中国保险监管管理委员会海口监管局
                        监制
                    </td>
                </tr>
            </table>
        </div>
        <div style="width: 95%;margin: 10px auto;">
            <van-button round block type="info" v-on:click="onxyb" native-type="button">
                下一步
            </van-button>
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "jtsgzzs",
        data() {
            return {
                info: {},
                depts: [],
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.info.sgbh = _this.$route.query.sgbh;
            _this.getDeptMap();
            _this.onload();
        },
        methods: {
            onload() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/selectAccident?sgbh='+_this.info.sgbh
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            onxyb(){
                let _this = this;
                _this.$router.push({path:'/jtsgyjba', query:{dsrxm1: _this.info.dsrxm1, dsrbsgzr1: _this.info.dsrbsgzr1, dsrbxgs1: _this.info.dsrbxgs1, sgbh: _this.info.sgbh}});
            },
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
        }
    }
</script>

<style scoped>
    table{
        width: 98%;
        margin: 0 auto;
        line-height: 28px;
        text-align: center;
        font-size: 14px;
    }
    table,table tr th, table tr td {
        border:1px solid #A8A8A8;
    }
</style>