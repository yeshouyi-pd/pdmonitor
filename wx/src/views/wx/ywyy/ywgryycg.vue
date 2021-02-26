<template>
    <div >
        <div style="text-align: center;height: auto;
                    color: #00BFFF;
                     font-size: 1.1em;
                     padding-top: 10px">
            预约信息【{{SLZT_STATUS|optionKVArray(yyxxinfo.zt)}}】
        </div>
        <!-- 搜索提示 -->
        <div v-show="!yyxxinfo.id">
            <van-empty image="search" description="无相关信息,请重试！" />
        </div>
          <div class="van-address-list"  v-show="yyxxinfo.id">
          <van-row type="flex" justify="center">
            <van-col >
                <van-image  style="width: 100%;" :src="yyxxinfo.ywm"  />
            </van-col>
          </van-row>
            <van-row>
              <van-col span="5" offset="14">
                <div style=" background-image: url('/static/image/dh.png');" v-on:click="openLocation()" class="dh">
                </div>
              </van-col>
              <van-col span="5">
                <div  class="dh"  style="background: #00a0e9" @click="show = true;indexs=1;thisurl=ywlxinfo.lcto" >
                  <font  style="font-size: 1.1em; line-height:35px; color: #FFFFFF">
                    办事<br/>
                  </font>
                  <font  style="font-size: 1.1em; line-height:10px; color: #FFFFFF">
                    流程
                  </font>
                </div>
              </van-col>
            </van-row>
            <van-cell-group>
              <van-cell title="姓名">{{yyxxinfo.name}}</van-cell>
              <van-cell title="身份证号">{{yyxxinfo.zjhm}}</van-cell>
              <van-cell title="申请时间">{{yyxxinfo.cjsj}}</van-cell>
              <van-cell v-show="yyxxinfo.yytype ==='2'"  title="预约数量">{{yyxxinfo.yysl}}</van-cell>
              <van-cell title="受理状态">{{SLZT_STATUS|optionKVArray(yyxxinfo.zt)}}</van-cell>
              <van-cell title="预约类型">{{YYXX_STATUS|optionKVArray(yyxxinfo.yytype)}}</van-cell>
              <van-cell title="预约日期">{{yyxxinfo.yysj}}</van-cell>
              <van-cell title="预约时段">{{yyxxinfo.yyrq}}</van-cell>
              <van-cell v-show="yyxxinfo.yytype ==='2'" title="企业名称">{{yyxxinfo.dwmc}}</van-cell>
              <van-cell  title="受理单位">{{deptinfo.deptname}}</van-cell>
            </van-cell-group>


            <van-overlay   z-index="1004" :show="show" @click="show = false">
              <div class="wrapper" @click.stop>
                <div style="margin-top: 10%;color: #FFFFFF;text-align: center;border: 0px solid red;">
                  <div style="text-align: left">
                   <p style="font-size: 15px;">办事流程:{{ ywlxinfo.bslc}}</p>
                   <p style="font-size: 15px;">所需资料:{{ ywlxinfo.sxzl}}</p>
                  </div>
                  <div style="margin-top: 10%;" v-show="thisurl">
                    <van-image width="90%" height="auto" :src="SERVERURL+thisurl"/>&nbsp;&nbsp;

                  </div>
                  <div  v-on:click="getnext(indexs)"  style="margin-top: 10px;border: 1px solid #FFFFFF;height:35px;line-height:35px; overflow:hidden;font-size: 15px;width: 100px;position: absolute;left: 20%; top: 80%;" >下&nbsp;一&nbsp;张&nbsp;{{indexs}}</div>
                  <div  @click="show = false"  style="margin-top: 10px;border: 1px solid #FFFFFF;height:35px;line-height:35px; overflow:hidden;font-size: 15px;width: 100px;position: absolute;left: 50%; top: 80%;" >我&nbsp;知&nbsp;道&nbsp;了</div>
                 </div>
              </div>
            </van-overlay>
          <van-row>
            <van-col span="22" offset="1">
                <div style="font-size: 0.8em;margin-top: 10px; color: #B0B0B0;">
                温馨提示：<br/>
                可进入【个人中心】-【我的预约】或首页【预约查询】页面，进行查看或取消预约操作。
                </div>
            </van-col>
          </van-row>
        </div>
        <div class="van-address-list__bottom">
        <van-button  round block type="info"
                     color="linear-gradient(to right,#00BFFF,#0000FF)"
                     to="/index">
            返回首页
        </van-button>
            <div style="margin-top: 8px"></div>
        </div>

    </div>
</template>




<script>
     import Dialog from "vant/lib/dialog";
     import Notify from "vant/lib/notify";
     import Toast from "vant/lib/toast";
    export default {
        name:'ywgryycg',
        data:function(){
            return{
                yyxxinfo:{},//预约信息
                deptinfo:{},//部门信息
                ywlxinfo:{},//流程信息
                SLZT_STATUS:[{key:"1", value:"已预约"},{key:"2", value:"已取消"},{key:"3", value:"已过期"},{key:"4", value:"已办结"},{key:"5", value:"已办结"}],//受理状态
                YYXX_STATUS:[{key:"1", value:"个人预约"},{key:"2", value:"企业预约"}],//受理状态
                show: false,
                SERVERURL :process.env.VUE_APP_SERVER,
                thisurl:"",
                indexs:1

        }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            let id =  SessionStorage.get(SAVY_YY_SUCCESS)|| '' ;
            _this.getYyxxInfo(id);
            _this.wxConfig();

        },
        methods:{
          getnext(onj){
            let _this = this;
            if(onj == 1){
              _this.indexs=2;
              _this.thisurl =this.ywlxinfo.lctt;
            }
            if(onj == 2){
              _this.indexs=3;
              _this.thisurl =this.ywlxinfo.lcth;
            }
            if(onj == 3){
              _this.indexs=4;
              _this.thisurl =this.ywlxinfo.lctf;
            }
            if(onj == 4){
              _this.indexs=1;
              _this.thisurl =this.ywlxinfo.lcto;
            }

          },
            /**
             * 获取预约信息
             * @param id
             */
            getYyxxInfo(id){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/getYyxxInfo',{
                    id:id
                }).then((response)=>{
                    let resp = response.data;
                        _this.yyxxinfo = resp.content.yyxxinfo || {};
                        _this.deptinfo = resp.content.deptinfo || {};
                        _this.ywlxinfo = resp.content.ywlxinfo || {};
                         _this.thisurl=_this.ywlxinfo.lcto;


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
            wx.openLocation({
              latitude: _this.deptinfo.wd, // 纬度，浮点数，范围为90 ~ -90
              longitude: _this.deptinfo.jd, // 经度，浮点数，范围为180 ~ -180。
              name: _this.deptinfo.deptname, // 位置名
              address: _this.deptinfo.linkadd, // 地址详情说明
              scale: 15, // 地图缩放级别,整形值,范围从1~28。默认为最大
              infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
            });

          },


        }


    }
</script>

<style scoped>
    .dh{
      text-align: center;

      background-size: 100% 100%;
      background-position: center center;
      overflow: auto;
      width: 60px;height: 60px;
      border-radius:30px;
    }
    .van-notice-bar {
        color: #000000;
        font-size: 1.1em;
        line-height: 25px;
    }
    .van-divider {
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        -webkit-box-align: center;
        -webkit-align-items: center;
        align-items: center;
        margin: 0px 0;
        color: #969799;
        font-size: 14px;
        line-height: 24px;
        border-color: #ebedf0;
        border-style: solid;
        border-width: 0;
    }
    .van-cell__title {
      -webkit-box-flex: 1;
      -webkit-flex: 1;
      flex: 1;
    }
     .van-cell__value {
      -webkit-box-flex: 2;
      -webkit-flex: 2;
      flex: 2;
    }

</style>
