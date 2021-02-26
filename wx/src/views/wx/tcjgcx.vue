<template>
    <div>
        <van-image width="100%" src="/static/wx/bmfw/tccx1.jpg"/>
        <van-empty image="search" v-show="showPic" :description="message" />
        <div v-show="showDiv" >
            <van-cell-group>
              <van-cell title="车牌号">{{vehParkLot.hphm}}</van-cell>
              <van-cell title="违法代码">{{vehParkLot.wfdm}}</van-cell>
              <van-cell title="违法时间">{{vehParkLot.wfsj | formatDateTime}}</van-cell>
              <van-cell title="停车场名称">{{allSysOrgCode|optionMapKV(vehParkLot.sysOrgCode)}}</van-cell>
<!--              <van-cell title="停车场联系电话" v-model="tcclxdh"></van-cell>-->
              <van-cell title="停放位置">{{vehParkLot.tfwz}}</van-cell>
            </van-cell-group>
        </div>
        <div class="button_db">
            <van-button style="width: 40%;" round type="info" v-on:click="navigation()" native-type="button">
                导航
            </van-button>
            &nbsp;&nbsp;
            <van-button style="width: 40%;" round type="info" to="/index" native-type="button">
                返回主页
            </van-button>
        </div>
        <div class="divtext">
            海口交警提醒您<br/>
            1、车辆被拖走后，数据上传有延迟，请您耐心等待！<br/>
            2、无正式车牌的车辆暂时无法使用本功能进行拖车查询。
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "tcjgcx",
        data() {
            return {
              showPic: '',
              showDiv: '',
              message: '',
              vehParkLot: {},
              allSysOrgCode:[],
              latitude:0,
              longitude:0
            };
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.vehParkLot.cllb = _this.$route.query.cllb;//路由传参
            _this.vehParkLot.hphm = _this.$route.query.hphm;
            _this.vehParkLot.clsbdh = _this.$route.query.clsbdh;
            _this.getDeptMap();
            _this.loadinfo();
            _this.wxConfig();
            _this.getLocation();
        },
        methods:{
          onClickLeft() {
            let _this = this;
            _this.$router.push("/tccx");
          },
          /**
           * 获取申领部门
           */
          getDeptMap(){
            let _this = this;
            _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDeptMap', {
            }).then((response)=>{
              let resp = response.data;
              _this.allSysOrgCode = resp.content;
            })
          },
          loadinfo(){
            let _this = this;
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehParkLot/selectParklot',
                _this.vehParkLot,
            ).then((response)=>{
              let resp = response.data;
              if (resp.success) {
                _this.vehParkLot = resp.content;
                _this.showDiv = 'true';
              } else {
                _this.showPic = 'true';
                _this.message = resp.message;
              }
            })
          },
          /**
           * 点击导航
           */
          navigation() {
            let _this = this;
            _this.openLocation();
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
            let name = _this.allSysOrgCode[_this.vehParkLot.sysOrgCode];
            wx.openLocation({
              latitude: _this.latitude, // 纬度，浮点数，范围为90 ~ -90
              longitude: _this.longitude, // 经度，浮点数，范围为180 ~ -180。
              name: name, // 位置名
              address: _this.vehParkLot.tfwz, // 地址详情说明
              scale: 15, // 地图缩放级别,整形值,范围从1~28。默认为最大
              infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
            });
          },
          getLocation(){
            let _this = this;
            wx.getLocation({
              type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
              success: function (res) {
                _this.latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                _this.longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                alert(latitude);
              }
            });
          }

        }
    }
</script>

<style scoped>
    .button_db {
        width: 100%;
        margin: 5% 0 0 10%;
    }
    .divtext {
        width: 90%;
        margin: 5% 0 0 5%;
        font-size: 13px;
        /*color: #969696;*/
        color: red;
        border: 1px solid #969696;
        border-radius: 5px;
    }
</style>