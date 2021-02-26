<template>
    <div>
        <van-form @submit="onSubmit">
            <div class="ksxx">申领人信息</div>

            <van-field label="业务名称" :value="ywmc" readonly />
            <van-field label="申领人" :value="createBy" readonly />
            <van-field label="身份证明号码" :value="sfzmhm" readonly />

            <van-field label="是否本人车辆">
                <template #input>
                    <van-radio-group v-model="sfbr" direction="horizontal">
                        <van-radio name="1">是</van-radio>
                        <van-radio name="0">否</van-radio>
                    </van-radio-group>
                </template>
            </van-field>

            <van-field
                    v-model="hphm"
                    label="号牌号码"
                    placeholder="号牌号码"
            />

            <van-field
                    readonly
                    clickable
                    :value="sqyyxs"
                    label="申领原因"
                    placeholder="点击选择申领原因"
                    @click="showPickersqyy = true"
            />
            <van-popup v-model="showPickersqyy" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnssqyy"
                        @confirm="onConfirmsqyy"
                        @cancel="showPickersqyy = false"
                />
            </van-popup>

            <van-field
                    readonly
                    clickable
                    :value="slslxs"
                    label="申领螺丝数量"
                    placeholder="点击选择申领螺丝数量"
                    @click="showPickerslsl = true"
            />
            <van-popup v-model="showPickerslsl" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnsslsl"
                        @confirm="onConfirmslsl"
                        @cancel="showPickerslsl = false"
                />
            </van-popup>

            <van-field
                    readonly
                    clickable
                    :value="sysOrgCodexs"
                    label="申领地点"
                    placeholder="点击选择申领地点"
                    @click="showPickersysOrgCode = true"
            />

            <van-popup v-model="showPickersysOrgCode" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnssysOrgCode"
                        @confirm="onConfirmsysOrgCode"
                        @cancel="showPickersysOrgCode = false"
                />
            </van-popup>

            <div class="button_db1">
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
        name: "cplsslxx",
        data: function () {
            return {
                tel: '',
                sfbr: '1',
                lxdh: '',
                jddz: '',
                ywmc: '线下车牌螺丝固封申领',
                createBy: '',
                sfzmhm: '',
                slsl: '',
                slslxs: '一副(4个)',
                hphm: '',
                columnsslsl: ['一副(4个)', '二副(8个)'],
                showPickerslsl: false,
                sqyy: '',
                sqyyxs: '',
                columnssqyy: [],
                showPickersqyy: false,
                sysOrgCode: '',
                sysOrgCodexs: '',
                columnssysOrgCode: [],
                showPickersysOrgCode: false,
                value: '',
                showArea: false,
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.loadUser();
            _this.getWxScrewYy();
            _this.getDeptInfo();
            _this.wxConfig();
            _this.slsl = _this.$route.query.slsl;//路由传参
            if('1' == _this.$route.query.slsl){
                _this.slslxs = "一副(4个)";
            }else if('2' ==  _this.$route.query.slsl){
                _this.slslxs = "二副(8个)";
            }else{
                _this.slsl = 1;
            }
            _this.sysOrgCode = _this.$route.query.sysOrgCode;
            _this.sqyy = _this.$route.query.sqyy;
            _this.hphm = _this.$route.query.hphm;
            if(_this.$route.query.sfbr != null && _this.$route.query.sfbr != ""){
                _this.sfbr = _this.$route.query.sfbr;
            }
        },
        methods: {
            onConfirmsysOrgCode(value) {
                let _this = this;
                _this.sysOrgCodexs = value.text;
                _this.sysOrgCode = value.code;
                _this.showPickersysOrgCode = false;
            },
            onConfirmsqyy(value) {
                let _this = this;
                _this.sqyyxs = value.text;
                _this.sqyy = value.code;
                _this.showPickersqyy = false;
            },
            onConfirmslsl(value) {
                let _this = this;
                _this.slslxs = value;
                if(value === '一副(4个)'){
                    _this.slsl = 1;
                }else if(value === '二副(8个)'){
                    _this.slsl = 2;
                }
                _this.showPickerslsl = false;
            },
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                if(Tool.isEmpty( _this.hphm)){
                  Dialog({ message: "请输入号牌号码" });
                  return;
                }
                if(Tool.isEmpty( _this.sqyyxs)){
                  Dialog({ message: "请选择申领原因" });
                  return;
                }
                if(Tool.isEmpty( _this.slslxs)){
                  Dialog({ message: "请选择申领螺丝数量" });
                  return;
                }
                if(Tool.isEmpty( _this.sysOrgCodexs)){
                  Dialog({ message: "请选择申领地点" });
                  return;
                }
                _this.$router.push({path:'/cplsslxxjg', query:{
                        sysOrgCode: _this.sysOrgCode,
                        slsl: _this.slsl,
                        hphm: _this.hphm,
                        sqyy: _this.sqyy,
                        sfbr: _this.sfbr,
                }});
            },
            loadUser(){
                let _this = this;
                let wxUser = Tool.getWxUser();
                _this.createBy = wxUser.zjmc;
                _this.sfzmhm = wxUser.zjhm;
            },
            /**
             * 获取申领原因
             */
            getWxScrewYy(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxScrewYy', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columnssqyy = resp.content;
                    if((_this.sqyy == '' || _this.sqyy == undefined) && (_this.sqyyxs == '' || _this.sqyyxs == undefined)){
                        _this.sqyy = _this.columnssqyy[0].code;
                        _this.sqyyxs = _this.columnssqyy[0].text;
                    }else{
                        for(let i in _this.columnssqyy){
                            if(_this.sqyy === _this.columnssqyy[i].code){
                                _this.sqyyxs = _this.columnssqyy[i].text;
                            }
                        }
                    }
                })
            },
            /**
             * 获取申领部门
             */
            getDeptInfo(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDeptInfo', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columnssysOrgCode = resp.content;
                    for(let i in _this.columnssysOrgCode){
                        if(_this.sysOrgCode === _this.columnssysOrgCode[i].code){
                            _this.sysOrgCodexs = _this.columnssysOrgCode[i].text;
                        }
                    }
                })
            },
            wxConfig(){
                let _this = this;
                let formData = new FormData();
                formData.append("url", location.href.split("#")[0]);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wechat/getWxParams',
                    formData
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        wx.config({
                            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                            appId: _this.info.appId, // 必填，公众号的唯一标识
                            timestamp: _this.info.timestamp, // 必填，生成签名的时间戳
                            nonceStr: _this.info.nonceStr, // 必填，生成签名的随机串
                            signature:  _this.info.signature,// 必填，签名
                            jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表
                        });
                        _this.getLocation();
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            getLocation(){
                let _this = this;
                wx.getLocation({
                    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                    success: function (res) {
                        let latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                        let longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                        _this.getapproach(latitude,longitude);
                    }
                });
            },
            /**
             * 获取最近的领取车管所
             */
            getapproach(latitude,longitude){
                let _this = this;
                let formData = new FormData();
                formData.append("latitude", latitude);
                formData.append("longitude", longitude);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getapproach',
                    formData
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.sysOrgCodexs = resp.content.deptname;
                        _this.sysOrgCode = resp.content.deptcode;
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .button_db1 {
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
    }
</style>