<template>
    <div>
        <van-form @submit="onSubmit">
            <van-cell-group>
                <van-field v-model="address" label="渍水地点" />
                <van-field
                        v-model="zsddxx"
                        label="渍水详细地址"
                        placeholder="请输入渍水详细地址"
                />
                <van-field required name="uploader" label="渍水照片">
                    <template #input>
                        <van-uploader :after-read="afterRead" :before-read="beforeRead" v-model="fileList" multiple />
                    </template>
                </van-field>
                <van-field v-model="wxUser.zjmc" readonly label="上报人姓名" />
                <van-field v-model="wxUser.zjhm" label="身份证明号码" />
                <van-field
                        v-model="wxUser.lxdh"
                        label="手机号码"
                        placeholder="手机号码"
                />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="info" native-type="submit">
                    确认上报
                </van-button>
            </div>
        </van-form>
        <div style="width: 90%; margin: auto;line-height: 20px;font-size: 14px;color: red;">
            <p> 注意事项：<br/>
                &#32;请在确实发生渍水情况下提交本信息，
                若随意提交不实信息对排渍工作造成干扰的，
                海口交警将追究您的法律责任。</p>
        </div>
        <van-tabbar v-model="active">
            <van-tabbar-item replace to="/zssb" icon="wap-home">
                渍水上报
            </van-tabbar-item>
            <van-tabbar-item replace to="/zssbls" icon="manager">
                渍水上报历史
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>
    import Notify from "vant/lib/notify";
    import Dialog from "vant/lib/dialog";
    import Toast from "vant/lib/toast";

    export default {
        name: "zssb",
        data() {
            return {
                active: 0,
                address: '',
                zsddxx: '',
                wxUser: {},
                position: [],
                fileList: [],
                imglist: [],
            };
        },
        mounted() {
            let _this = this;
            _this.wxUser = Tool.getWxUser();
            _this.wxConfig();
        },
        methods: {
            afterRead(file) {
                let _this = this;
                _this.imglist.push(file.content);
            },
            // 返回布尔值
            beforeRead(file) {
                if (file.type !== 'image/jpeg') {
                    Notify({type: 'danger', message: '请上传jpg格式图片'});
                    return false;
                }
                return true;
            },
            onSubmit(values) {
                let _this = this;
                if(_this.imglist.length === 0){
                    Notify({type: 'danger', message: '请上传图片信息'});
                    return false;
                }
                console.log('submit', _this.imglist);
                if(Tool.isEmpty( _this.wxUser.lxdh)){
                  Dialog({ message: "请输入手机号码" });
                  return;
                }
                let formData = new FormData();
                formData.append("imglist", JSON.stringify(_this.imglist));
                formData.append("openid", Tool.getWxOpendid());
                formData.append("gps", _this.position);
                formData.append("zsdd", _this.address);
                formData.append("zsddxx", _this.zsddxx);
                formData.append("sbr", _this.wxUser.zjmc);
                formData.append("sfzmhm", _this.wxUser.zjhm);
                formData.append("sbrsjh", _this.wxUser.lxdh);
                Toast.loading({
                    duration: 0,
                    message: '加载中...',
                    forbidClick: true,
                });
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/reportWater/wxsave',
                    formData
                ).then((response)=>{
                    Toast.clear();
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push("/cgym");
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            checkeds(){
                let _this = this;

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
                        _this.position = new Array();
                        _this.position.push(longitude);
                        _this.position.push(latitude);
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
        },
    }
</script>

<style scoped>

</style>