<template>
    <div>
        <div class="ksxx">考生信息</div>
        <van-form @submit="onSubmit">
            <van-field
                    v-model="drvQxks.xm"
                    label="姓名"
                    placeholder="姓名"
                    readonly
            />
            <van-field
                    readonly
                    clickable
                    :value="zjlxs"
                    label="证件类型"
                    placeholder="证件类型"
                    @click="showPicker = true"
            />
            <van-popup v-model="showPicker" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columns"
                        @confirm="onConfirm"
                        @cancel="showPicker = false"
                />
            </van-popup>
            <van-field
                    v-model="drvQxks.sfzmhm"
                    label="证件号码"
                    placeholder="证件号码"
                    readonly
            />
            <van-field
                    v-model="drvQxks.sjhm"
                    label="手机号码"
                    placeholder="手机号码"
                    readonly
            />
            <van-field
                    readonly
                    clickable
                    :value="qxkms"
                    label="取消考试科目"
                    placeholder="取消考试科目"
                    @click="showPickerkskm = true"
            />
            <van-popup v-model="showPickerkskm" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnskskm"
                        @confirm="onConfirmkskm"
                        @cancel="showPickerkskm = false"
                />
            </van-popup>
            <van-field
                    v-model="drvQxks.yykssjstr"
                    label="考试时间"
                    placeholder="考试时间"
                    @click="show = true"
            />
            <van-calendar
                    title="日历"
                    :min-date="minDate"
                    :default-date="defaultDate"
                    v-model="show"
                    @confirm="onConfirmksks"
            />
            <div class="button_db">
                <van-button round block type="info" native-type="submit">
                    提交
                </van-button>
            </div>
        </van-form>
        <div class="wfzxjy-ts">
            温馨提示：<br/>
            1.本平台只针对在交管12123互联网平台预约考试成功后取消。<br/>
            2.因微信已实名认证，在此平台申请的取消考试预约默认为本人申请。<br/>
            3.如果您已预约成功，因故无法参加考试的，请至少提前3天进行取消申请。<br/>
            4.申请人所提交的申请，将于提交后的1个工作日内受理。<br/>
            5.取消预约成功的，考试名额将不再保留，需重新进行考试预约后方能参加考试。取消预约失败的，请携带有效的身份证明在预约的时间和考场参加考试。如未按时参加考试的，此次考试成绩将判定为不及格。<br/>
            6.取消考试后，下次预约考试排名优先规则以本次取消时间为准。
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "qxks",
        data: function () {
            return {
                active: 0,
                activeNames: ['0'],
                qxkms: '',
                zjlxs: '居民身份证',
                drvQxks: {},
                columns: [],
                showPicker: false,
                columnskskm: [],
                showPickerkskm: false,
                show: false,
                minDate: new Date(),
                defaultDate: new Date(),
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getWxSmrzUser();
            _this.getKskm();
            _this.getSfzjlx();
            _this.getWxUser();
            _this.getdateN(3);
            _this.drvQxks.zjlx = 'A';
        },
        methods: {
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                if(Tool.isEmpty( _this.drvQxks.xm)){
                  Dialog({ message: "请输入姓名" });
                  return;
                }
                if(Tool.isEmpty( _this.zjlxs)){
                  Dialog({ message: "请选择证件类型" });
                  return;
                }
                if(Tool.isEmpty( _this.drvQxks.sfzmhm)){
                  Dialog({ message: "请输入证件号码" });
                  return;
                }
                if(Tool.isEmpty( _this.drvQxks.sjhm)){
                  Dialog({ message: "请输入手机号码" });
                  return;
                }
                if(Tool.isEmpty( _this.qxkms)){
                  Dialog({ message: "请选择取消考试科目" });
                  return;
                }
                if(Tool.isEmpty( _this.drvQxks.yykssjstr)){
                  Dialog({ message: "请选择考试时间" });
                  return;
                }
                _this.drvQxks.openid = Tool.getWxOpendid();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/drvQxks/wxsave',
                    _this.drvQxks,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push({path:'/qxksjg', query:{sfzmhm: _this.drvQxks.sfzmhm, yykssjstr: _this.drvQxks.yykssjstr}});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            onConfirm(value) {
                let _this = this;
                _this.zjlxs = value.text;
                _this.drvQxks.zjlx = value.code;
                _this.showPicker = false;
            },
            onConfirmkskm(value) {
                let _this = this;
                _this.qxkms = value.text;
                _this.drvQxks.qxkm = value.code;
                _this.showPickerkskm = false;
            },
            formatDate(date) {
                return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
            },
            onConfirmksks(date) {
                let _this = this;
                _this.show = false;
                _this.drvQxks.yykssjstr = this.formatDate(date);
            },
            /**
             * 获取考试科目
             */
            getKskm(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxKskm', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columnskskm = resp.content;
                    console.log('columnskskm', _this.columnskskm);
                    /*for(let i in resp.content){
                        _this.columnskskm.push(resp.content[i]);
                    }*/
                })
            },
            /**
             * 获取身份证件类型
             */
            getSfzjlx(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxZjlx', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columns = resp.content;
                    /*for(let i in resp.content){
                        _this.columns.push(resp.content[i]);
                    }*/
                })
            },
            getDay(day) {
                let today = new Date();
                let targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
                today.setTime(targetday_milliseconds);
                let tYear = today.getFullYear();
                let tMonth = today.getMonth();
                let tDate = today.getDate();
                tMonth = this.doHandleMonth(tMonth + 1);
                tDate = this.doHandleMonth(tDate);
                return tYear + "-" + tMonth + "-" + tDate;
            },
            getWxUser(){
                let _this = this;
                let wxUser = Tool.getWxUser();
                if(null != wxUser){
                    _this.drvQxks.xm = wxUser.zjmc;
                    _this.drvQxks.sfzmhm = wxUser.zjhm;
                    _this.drvQxks.sjhm = wxUser.lxdh;
                }
            },
            getdateN(maxday){
                let _this = this;
                let day = new Date();
                day.setTime(day.getTime()+24*60*60*1000*maxday);
                let nian =day.getFullYear();
                let yue = day.getMonth();
                let ri = day.getDate();
                _this.minDate = new Date(nian, yue, ri);
                _this.defaultDate = _this.minDate;
            },
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
    .button_db {
        width: 90%;
        margin: 20px 0 0 5%;
        /*position:fixed;
        bottom: 10px;
        left: 5%;*/
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
    .wfzxjy-ts{
        margin: 16px;
        font-size: 14px;
        color: #999;
    }
</style>