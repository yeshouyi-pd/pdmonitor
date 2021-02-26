<template>
    <div>
        <van-notice-bar
                wrapable
                :scrollable="false"
                text="请提交真实的当事人信息，便于交警电话回访及快速的划分责任"
        />
        <van-form @submit="onSubmit">
            <div style="border: 0px solid #1A0F0F;margin: 10px auto;">
                <van-field
                        v-model="info.dsrxm1"
                        label="当事人姓名"
                        placeholder="当事人姓名"
                />
                <van-field
                        v-model="info.dsrsjhm1"
                        label="本人联系号码"
                        placeholder="本人联系号码"
                />
                <van-field
                        clickable
                        :value="bxgsxs"
                        label="本人保险公司"
                        placeholder="本人保险公司"
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
                        v-model="address"
                        label="事故发生所在区域"
                        placeholder="事故发生所在区域"
                />
                <div class="button_db">
                    <van-button round block type="info" native-type="submit">
                        下一步
                    </van-button>
                </div>
            </div>
        </van-form>
    </div>
</template>

<script>

    import Dialog from "vant/lib/dialog";
    import Toast from "vant/lib/toast";

    export default {
        name: "jtsgsb",
        data: function () {
            return {
                wxUser: {},
                info: {},
                bxgsxs: '',
                address: '',
                columns: [],
                showPicker: false,
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getWxUser();
            _this.getDeptInfo();
            _this.wxConfig();
        },
        methods: {
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                if(Tool.isEmpty( _this.info.dsrxm1)){
                  Dialog({ message: "请输入当事人姓名" });
                  return;
                }
                if(Tool.isEmpty( _this.info.dsrsjhm1)){
                  Dialog({ message: "请输入本人联系号码" });
                  return;
                }
                if(Tool.isEmpty( _this.bxgsxs)){
                  Dialog({ message: "请选择本人保险公司" });
                  return;
                }
                if(Tool.isEmpty( _this.address)){
                  Dialog({ message: "请输入事故发生所在区域" });
                  return;
                }
                let formData = new FormData();
                formData.append("openid", Tool.getWxOpendid());
                formData.append("dsrxm1", _this.info.dsrxm1);
                formData.append("dsrsjhm1", _this.info.dsrsjhm1);
                formData.append("dsrbxgs1", _this.info.dsrbxgs1);
                formData.append("sgdd", _this.address);
                Toast.loading({
                    duration: 0,
                    message: '加载中...',
                    forbidClick: true,
                });
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/wxsave',
                    formData
                ).then((response)=>{
                    Toast.clear();
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push({path:'/jtsgsbpic',
                            query:{sgbh: _this.info.sgbh,lsh: _this.info.lsh,dsrxm1: _this.info.dsrxm1,dsrsjhm1: _this.info.dsrsjhm1}});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            onConfirm(value) {
                let _this = this;
                _this.bxgsxs = value.text;
                _this.info.dsrbxgs1 = value.code;
                _this.showPicker = false;
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
                    /*for(let i in resp.content){
                        _this.columns.push(resp.content[i]);
                    }*/
                })
            },
            getWxUser(){
                let _this = this;
                let wxUser = Tool.getWxUser();
                if(null != wxUser){
                    _this.info.dsrxm1 = wxUser.zjmc;
                    _this.info.dsrsjhm1 = wxUser.lxdh;
                }
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
                        _this.$nextTick();
                        _this.geocoder(longitude, latitude);
                    }
                });
            },
            geocoder(longitude,latitude){
                let _this = this;
                let geocoder = new AMap.Geocoder({
                    radius: 1000,
                    extensions: "all"
                });
                geocoder.getAddress([longitude ,latitude], function(status, result) {
                    if (status === 'complete' && result.info === 'OK') {
                        if (result && result.regeocode) {
                            _this.address = result.regeocode.formattedAddress;
                            _this.info.sgdd = _this.address;
                            _this.$nextTick();
                        }
                    }
                });
                AMap.event.addListener(geocoder, 'complete', onComplete);
                AMap.event.addListener(geocoder, 'error', onError);
                function onComplete (data) {
                    //console.log(JSON.stringify(data));
                }
                function onError (data) {
                    console.log(JSON.stringify(data));
                }
            },
        }
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        margin: 20px 0 0 5%;
    }
</style>