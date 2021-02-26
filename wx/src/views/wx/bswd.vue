<template>
    <div>
        <div class="ksxx">办事网点</div>
        <van-empty image="search" v-show="showPic" :description="message" />
        <div class="div" v-show="showDiv">
            <ul>
                <li v-for="(item, index) in depes" :key="item.id">
                    <div>
                        <van-radio-group v-model="radio">
                            <van-radio :name="item.jd+','+item.wd+','+item.deptname+','+item.linkadd">
                                <span class="h3">{{item.deptname}}</span>
                                <br/>
                                <span class="h4">地址：{{item.linkadd}}</span>
                                <br/>
                                <span class="h4">电话：<a href="#">{{item.linktel}}</a></span>
                            </van-radio>
                        </van-radio-group>
                        <van-divider/>
                    </div>
                </li>
            </ul>
        </div>

        <div style="margin: 16px;">
            <van-button round block type="info" v-on:click="navigation" native-type="button">
                开始导航
            </van-button>
        </div>
        <div style="margin: 16px;">
            <van-button round block type="info" to="/index" native-type="button">
                返回首页
            </van-button>
        </div>
    </div>
</template>

<script>
    import Notify from "vant/lib/notify";
    import Dialog from "vant/lib/dialog";

    export default {
        name: "bswd",
        data() {
            return {
                radio: '',
                showPic: '',
                showDiv: '',
                message: '',
                code: '',
                depes: []
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.code = _this.$route.query.code;//路由传参
            _this.getDeptByType();
            _this.wxConfig();
        },
        methods: {
            /**
             * 点击导航
             */
            navigation() {
                let _this = this;
                _this.openLocation();
                //Notify({type: 'primary', message: this.radio});
            },
            /**
             *获取能办理 业务的所有部门
             */
            getDeptByType(){
                let _this = this;
                let formData = new FormData();
                formData.append("depttype", _this.code);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDepts',
                    formData
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        _this.depes = resp.content;
                        if(_this.depes.length != 0){
                            this.showDiv = 'true';
                        }else{
                            _this.showPic = 'true';
                            _this.message = '未查询到相关网点信息！';
                        }
                    } else {
                        Dialog({message: resp.message});
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
                            jsApiList: ['openLocation','getLocation'] // 必填，需要使用的JS接口列表
                        });
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            openLocation(){
                let _this = this;
                if(_this.radio === ''){
                    Dialog({ message: "请先选择办事网点" });
                }else{
                    let split = _this.radio.split(",");
                    wx.openLocation({
                        latitude: split[1], // 纬度，浮点数，范围为90 ~ -90
                        longitude: split[0], // 经度，浮点数，范围为180 ~ -180。
                        name: split[2], // 位置名
                        address: split[3], // 地址详情说明
                        scale: 15, // 地图缩放级别,整形值,范围从1~28。默认为最大
                        infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
                    });
                }
            },
            getLocation(){
                wx.getLocation({
                    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                    success: function (res) {
                        let latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                        let longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                    }
                });
            }
        },
    }
</script>

<style scoped>
    ul li div {
        margin-top: 8px;
        margin-left: 5px;
    }

    .h3 {
        font-size: 15px;
    }

    .h4 {
        font-size: 13px;
        color: #aaa;
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
</style>