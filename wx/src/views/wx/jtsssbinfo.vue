<template>
    <div>
        <div class="amap-wrapper">
            <el-amap ref="map" vid="amapDemo" :center="center" :zoom="zoom" :plugin="plugin" class="amap-demo">
                <el-amap-marker vid="marker" :position="position" :label="label"></el-amap-marker>
            </el-amap>
        </div>
        <van-form @submit="onSubmit">
            <van-field v-model="address" readonly label="故障地点"/>
            <van-field
                    v-model="gzxxdz"
                    label="故障详细地址"
                    placeholder="请输入故障详细地址"
            />
            <van-field v-model="name" readonly label="故障类型"/>
            <van-field
                    v-model="gzms"
                    rows="2"
                    autosize
                    label="文字描述"
                    type="textarea"
                    maxlength="50"
                    placeholder="请输入文字描述"
                    show-word-limit
            />
            <van-field required name="uploader" label="图片上传">
                <template #input>
                    <van-uploader :after-read="afterRead" :before-read="beforeRead" v-model="fileList" multiple/>
                </template>
            </van-field>
            <div style="margin-left: 15px;margin-top: 10px;font-size: 14px;">
                <van-checkbox style="float: left;" v-on:change="checkeds" v-model="checked" shape="square">阅</van-checkbox>
                读并同意<span style="color: #1d6fa6;" v-on:click="sbxz">《交安设备故障上报须知》</span>
            </div>
            <div class="button_db">
                <van-button id="but1" round block type="default" native-type="button">
                    确认上报
                </van-button>
                <van-button id="but2" style="display: none;" round block type="info" native-type="submit">
                    确认上报
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>

    import Notify from "vant/lib/notify";
    import Dialog from "vant/lib/dialog";
    import Toast from "vant/lib/toast";

    export default {
        name: "jtsssbinfo",
        data() {
            return {
                name: '',
                gzxxdz: '',
                fileList: [],
                imglist: [],
                address: '',
                gzlx: '',
                gzms: '',
                checked: '',
                zoom: 13,
                center: [114.343376,30.505187],
                position: [114.343376,30.505187],
                label: {
                    content: '',
                    offset:[10, 12]
                },
                plugin: [{
                    pName: 'Geolocation',
                }],
            };
        },
        mounted() {
            let _this = this;
            _this.gzlx = _this.$route.query.code;
            _this.name = _this.$route.query.name;
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
                let formData = new FormData();
                formData.append("imglist", JSON.stringify(_this.imglist));
                formData.append("openid", Tool.getWxOpendid());
                formData.append("gzgps", _this.position);
                formData.append("gzlx", _this.gzlx);
                formData.append("gzdd", _this.address);
                formData.append("gzxxdz", _this.gzxxdz);
                formData.append("gzms", _this.gzms);
                Toast.loading({
                    duration: 0,
                    message: '加载中...',
                    forbidClick: true,
                });
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/reportFacilityfail/wxsave',
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
            checkeds() {
                let _this = this;
                if(_this.checked){
                    $('#but1').hide();
                    $('#but2').show();
                }else{
                    $('#but1').show();
                    $('#but2').hide();
                }
            },
            sbxz(){
                let a = "交安设备故障上报须知,交安设备故障上报须知,交安设备故障上报须知,交安设备故障上报须知,交安设备故障上报须知,交安设备故障上报须知,交安设备故障上报须知,交安设备故障上报须知";
                Dialog({ message: a });
            },
        },
    }

</script>

<style scoped>
    .button_db {
        width: 90%;
        margin: 10px auto;
    }
    .amap-wrapper {
        width: 100%;
        height: 200px;
    }
</style>