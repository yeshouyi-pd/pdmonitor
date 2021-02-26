<template>
    <div>
        <van-image height="70px" src="/static/wx/u956.jpg"/>
        <van-form @submit="onSubmit">
            <van-field
                    readonly
                    clickable
                    :value="fsxxxs"
                    label="事故发生情形"
                    placeholder="请点击选择事故发生情形"
                    @click="showPickerd = true"
            />
            <van-popup v-model="showPickerd" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnsd"
                        @confirm="onConfirmd"
                        @cancel="showPickerd = false"
                />
            </van-popup>
            <div class="ksxx">已方驾驶证信息</div>
            <van-field
                    v-model="info.dsrxm1"
                    label="当事人姓名"
                    placeholder="当事人姓名"
                    readonly
            />
            <van-field
                    v-model="info.hphm1"
                    label="号牌号码"
                    placeholder="号牌号码"
                    readonly
            />
            <van-field
                    v-model="info.dsrsjhm1"
                    label="联系电话"
                    placeholder="联系电话"
                    readonly
            />
            <!--<van-field
                    value="{{depts|optionMapKV(info.dsrbxgs1)}}"
                    placeholder="请输入用户名"
            />-->
           <van-field
                    v-model="dsrbxgs1xs"
                    label="保险公司"
                    placeholder="保险公司"
                    readonly
            />
            <van-field
                    v-model="info.dsrbxpzh1"
                    label="交强险保单号"
                    placeholder="交强险保单号"
            />
            <van-field name="radio" label="责任认定">
                <template #input>
                    <van-radio-group @change="onchage" v-model="radio1" direction="horizontal">
                        <van-radio name="1">全责</van-radio>
                        <van-radio name="2">无责</van-radio>
                    </van-radio-group>
                </template>
            </van-field>
            <div class="ksxx">对方驾驶证信息</div>
            <van-field
                    v-model="info.dsrxm2"
                    label="当事人姓名"
                    placeholder="当事人姓名"
                    readonly
            />
            <van-field
                    v-model="info.hphm2"
                    label="号牌号码"
                    placeholder="号牌号码"
                    readonly
            />
            <van-field
                    v-model="info.dsrsjhm2"
                    label="联系电话"
                    placeholder="联系电话"
                    readonly
            />
            <van-field
                    clickable
                    :value="bxgsxs"
                    label="保险公司"
                    placeholder="保险公司"
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
                    v-model="info.dsrbxpzh2"
                    label="交强险保单号"
                    placeholder="交强险保单号"
            />
            <van-field name="radio" label="责任认定">
                <template #input>
                    <van-radio-group @change="onchages" v-model="radio2" direction="horizontal">
                        <van-radio name="1">全责</van-radio>
                        <van-radio name="2">无责</van-radio>
                    </van-radio-group>
                </template>
            </van-field>
            <div style="width: 95%;margin: 10px auto;">
                <van-button round block type="info" native-type="submit">
                    对事故责任认定无异议
                </van-button>
            </div>
            <div style="width: 95%;margin: 10px auto;">
                <van-button round block v-on:click="jtsgyydz" type="info" native-type="button">
                    事故责任认定有争议，提交交警远程定责
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Notify from "vant/lib/notify";
    import Dialog from "vant/lib/dialog";

    export default {
        name: "jtsgdz",
        data() {
            return {
                radio1: '',
                radio2: '',
                info: {},
                dsrbxgs1xs: '',
                bxgsxs: '',
                columns: [],
                showPicker: false,
                fsxxxs: '',
                columnsd: [],
                showPickerd: false,
                depts: [],
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.info.sgbh = _this.$route.query.sgbh;
            _this.info.dsrxm1 = _this.$route.query.dsrxm1;
            _this.info.dsrsjhm1 = _this.$route.query.dsrsjhm1;
            _this.info.hphm1 = _this.$route.query.hphm1;
            _this.info.dsrbxgs1 = _this.$route.query.dsrbxgs1;
            _this.info.dsrxm2 = _this.$route.query.dsrxm2;
            _this.info.dsrsjhm2 = _this.$route.query.dsrsjhm2;
            _this.info.hphm2 = _this.$route.query.hphm2;
            _this.getDeptInfo();
            _this.getJtsgqx();
            _this.getDeptMap();
        },
        methods: {
            afterRead(file) {
                console.log(file);
            },
            beforeRead(file) {
                return true;
            },
            onchage(value) {
                let _this = this;
                _this.radio1 = value;
                if(value == '1'){
                    _this.radio2 = '2';
                }else{
                    _this.radio2 = '1';
                }
            },
            onchages(value){
                let _this = this;
                _this.radio2 = value;
                if(value == '1'){
                    _this.radio1 = '2';
                }else{
                    _this.radio1 = '1';
                }
            },
            onSubmit(values) {
                let _this = this;
              if(Tool.isEmpty( _this.fsxxxs)){
                Dialog({ message: "请点击选择事故发生情形" });
                return;
              }
              if(Tool.isEmpty( _this.info.dsrbxpzh1)){
                Dialog({ message: "请填写已方交强险保单号" });
                return;
              }
              if(Tool.isEmpty( _this.bxgsxs)){
                Dialog({ message: "请选择对方本人保险公司" });
                return;
              }
              if(Tool.isEmpty( _this.info.dsrbxpzh2)){
                Dialog({ message: "请填写对方交强险保单号" });
                return;
              }
              if(Tool.isEmpty(_this.radio1)){
                Dialog({ message: "请选择已方责任认定" });
                return;
              }
              if(Tool.isEmpty(_this.radio2)){
                Dialog({ message: "请选择对方责任认定" });
                return;
              }
                _this.info.dsrbsgzr1 = _this.radio1;
                _this.info.dsrbsgzr2 = _this.radio2;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/wxsaveAccident',
                    _this.info,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push({path:'/jtsgzzs', query:{sgbh: _this.info.sgbh}});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            jtsgyydz(){
                let _this = this;
              if(Tool.isEmpty( _this.fsxxxs)){
                Dialog({ message: "请点击选择事故发生情形" });
                return;
              }
              if(Tool.isEmpty( _this.info.dsrbxpzh1)){
                Dialog({ message: "请填写已方交强险保单号" });
                return;
              }
              if(Tool.isEmpty( _this.bxgsxs)){
                Dialog({ message: "请选择对方本人保险公司" });
                return;
              }
              if(Tool.isEmpty( _this.info.dsrbxpzh2)){
                Dialog({ message: "请填写对方交强险保单号" });
                return;
              }
                _this.info.dsrbsgzr1 = _this.radio1;
                _this.info.dsrbsgzr2 = _this.radio2;
                _this.info.zt = "0";
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/wxsaveAccident',
                    _this.info,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push({path:'/jtsgyydz', query:{sgbh: _this.info.sgbh}});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            onConfirm(value) {
                let _this = this;
                _this.bxgsxs = value.text;
                _this.info.dsrbxgs2 = value.code;
                _this.showPicker = false;
            },
            onConfirmd(value) {
                let _this = this;
                _this.fsxxxs = value.text;
                _this.info.sgqx= value.code;
                _this.showPickerd = false;
            },
            /**
             * 获取部门信息
             */
            getDeptInfo(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDeptInfo?type=D', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columns = resp.content;
                    for(let i in resp.content){
                        if(resp.content[i].code == _this.info.dsrbxgs1){
                            _this.dsrbxgs1xs = resp.content[i].text;
                        }
                    }
                    /*for(let i in resp.content){
                        _this.columns.push(resp.content[i]);
                    }*/
                })
            },
            getDeptMap(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDeptMap', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.depts = resp.content;
                })
            },
            /**
             * 获取交通事故情形
             */
            getJtsgqx(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getJtsgqx', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columnsd = resp.content;
                })
            },
        }
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
    }
</style>