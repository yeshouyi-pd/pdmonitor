<template>
    <div style=" text-align:center;width: 100%;height:100%" >
      <div  style="width: 100%;height:195px;background-image: url('/static/wx/grzx.jpg');
background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
position:relative;border-radius:0 0 5px 5px">
        <div style="font-weight: bold;font-size: 15px;width: 100%;color: #FFFFFF;position:absolute;z-index:100;text-align: center;margin-top: 18px">

        </div>

        <!-- 居中 -->
        <van-row style="padding-top: 40px" type="flex">

          <van-col  offset="2" span="7">
             <div style="width: 84px;height: 84px;background: white;border-radius:42px;text-align: center">
               <van-image  style="padding-top: 2px" round width="80" height="80" :src="headimgurl"/>
             </div>
            </van-col>
          <van-col   span="10">
            <div style="text-align: left;padding-top: 14px">
              <font  style="color: white;line-height: 2em">{{info.zjmc}}</font><br/>

              <font color="white">{{info.zjhm}}</font><br/>

            </div>

          </van-col>
        </van-row>


        <br/>
      </div>

      <div style=" margin-left: 2%; background: white;top:155px ;width:96%;height: auto;position:absolute;z-index:100;
border-radius:20px;text-align: left" >

          <div  class="van-cell van-cell--clickable van-cell--borderless van-collapse-item__title van-collapse-item__title--borderless " style="border-radius: 30px;border: 0px">
            <i class="van-icon van-cell__left-icon">
              <img src="/static/wx/rz.png" class="van-icon__image"><!----></i>
            <div class="van-cell__title">
              <span>实名认证</span>
            </div>
            <div class="van-cell__value">
              <span><font color="green" style="font-weight: bold">已实名认证</font></span>
            </div>
          </div>
        <van-collapse accordion v-model="activeNames" >

          <van-collapse-item  title="我的车辆" name="1" icon="/static/wx/car.png">
            <div>
                    <span v-if="vehiles.length == '0'">
                        <p>您还没有绑定车辆,点击<a href="/grcl">绑定车辆</a></p>
                    </span>
              <span v-else-if="vehiles.length != 0">
                        <ul id="example-1">
                            <li v-for="vehile in vehiles" :key="vehile.id">
                                <div :id="'1' + vehile.id" v-on:click="onjbcl(vehile.id,vehile.hphm)" class="cldiv1">
                                    <div style="color: #FFFFFF;padding: 6% 0 0% 40%;">
                                        {{ vehile.hphm }}
                                    </div>
                                    <div style="color: #FFFFFF;padding: 2% 0 0% 40%;font-size: 14px;">
                                        {{allHpzl|optionMapKV(vehile.hpzl)}}
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <span style="margin-left: 15px;"><a href="/grcl">继续绑定车辆</a></span>
                        <span style="margin-left: 15px;"><a href="javascript:void(0);" v-on:click="jbcl">解绑车辆</a></span>
                    </span>
            </div>
          </van-collapse-item>
          <van-collapse-item title="我的缴费" name="2" icon="/static/wx/jf.png">

          </van-collapse-item>
          <van-collapse-item name="3" title="我的预约" icon="/static/wx/yy.png" :change="checktype(activeNames)">
            <span v-if="listdata.length == '0'">
                        <p>您没有相关预约信息！</p>
                    </span>
            <!-- 搜索提示 -->
              <span v-if="listdata.length == '0'">
                        <p>您没有相关预约信息！</p>
                    </span>
            <div v-show="listdata.length > 0">
              <template v-for="wxyy in   listdata">
                <div :key="wxyy.id" class="van-address-item" style="background-color:#FFFAFA ">
                  <div class="van-cell van-cell--borderless" style="background-color: #FFFAFA">
                    <div class="van-radio__icon van-radio__icon--round van-radio__icon--checked"
                         style="margin-top: 6px;font-size: 0.7em;">
                      <i class="van-icon " style="background: #00a0e9;border-color:#00a0e9"></i>
                    </div>
                    <div class="van-cell__value van-cell__value--alone   ">
                      <div class="van-radio">
                                <span class="van-radio__label">
                                <div class="van-address-item__name" style="font-weight: bold;font-size: 1em">
                                    {{wxyy.yelxname}}
                                </div>
                                <div class="van-address-item__address" style="font-size: 0.8em;color: #6c6c6c">
                                    预约时间：{{wxyy.yysj}} {{wxyy.yyrq}}<br/><br/>
                                    申请单位：{{wxyy.deptname}}
                                </div>
                                </span>

                      </div>
                    </div>
                    <div style="padding-top: 14px ;"  v-on:click="linktoxxxx(wxyy.id)">
                      <div style="float: left;color:#1989fa;font-size: 0.8em;font-weight: bold">
                        {{SLZT_STATUS|optionKVArray(wxyy.zt)}}
                      </div>
                      <div style="float: right">
                        <i style="color:#1989fa;" class=" van-icon van-icon-arrow van-cell__right-icon"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </template>
            </div>


          </van-collapse-item>
          <van-collapse-item title="电子保单" name="4" icon="/static/wx/bd.png">
              <div style="margin-bottom: 60px;">
                    <span v-if="vehiles.length == '0'">
                        <p>您还没有绑定车辆,点击<a href="/grcl">绑定车辆</a></p>
                    </span>
                  <span v-else-if="vehiles.length != 0">
                        <ul id="example-2">
                            <li v-for="vehile in vehiles" :key="vehile.id">
                                <div :id="'2' + vehile.id" v-on:click="ondzbd(vehile.id,vehile.hphm)" class="cldiv2">
                                    <div style="color: #FFFFFF;padding: 6% 0 0% 40%;">
                                        {{ vehile.hphm }}
                                    </div>
                                    <div style="color: #FFFFFF;padding: 2% 0 0% 40%;font-size: 14px;">
                                        {{allHpzl|optionMapKV(vehile.hpzl)}}
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <span style="margin-left: 15px;"><a href="javascript:void(0);" v-on:click="dzbd">查看电子保单</a></span>
                    </span>
              </div>
          </van-collapse-item>
        </van-collapse>

      </div>




        <van-tabbar style="z-index: 999;" v-model="active">
            <van-tabbar-item replace to="/index" icon="wap-home">
                掌上业务
            </van-tabbar-item>
            <van-tabbar-item replace to="/bmfw" icon="service">
                便民服务
            </van-tabbar-item>
            <van-tabbar-item replace to="/grzx" icon="manager">
                个人中心
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>

    import Dialog from "vant/lib/dialog";
    import Notify from "vant/lib/notify";

    export default {
        name: 'index',
        data: function () {
            return {
                active: 2,
                activeNames: ['0'],
                vehiles: [],
                allHpzl: [],
                info: {},
                hphm: '',
                headimgurl: '',
                listdata:[],
                SLZT_STATUS:[{key:"1", value:"已预约"},{key:"2", value:"已取消"},{key:"3", value:"已过期"},{key:"4", value:"已办结"},{key:"5", value:"已办结"}],//受理状态



            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getWxSmrzUser();
            _this.getWxUser();
            _this.getVehile();
            _this.getHpzl();
            _this.headimgurl = Tool.getWxHeadimgurl();
        },
        methods: {
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
            getWxUser(){
                let _this = this;
                let wxUser = Tool.getWxUser();
                if(null != wxUser){
                    _this.info.zjmc = wxUser.zjmc;
                    _this.info.zjhm = wxUser.zjhm;
                }
            },
            getVehile() {
                let _this = this;
                let formData = new FormData();
                formData.append("sfzmhm", _this.info.zjhm);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatVehicle/findBySfzmhm',
                    formData
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        _this.vehiles = resp.content;
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
            /**
             * 获取号牌种类
             */
            getHpzl(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getHpzl', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.allHpzl = resp.content;
                })
            },
            onjbcl(id,hphm){
                let _this = this;
                $(".cldiv1").css("background-image","url('/static/wx/bmfw/PCopy6.png')");
                $("#1"+id).css("background-image","url('/static/wx/bmfw/PCopy5.png')");
                _this.hphm = hphm;
            },
            /**
             * 解除车辆绑定
             */
            jbcl(){
                let _this = this;
                if(_this.hphm === ''){
                    Notify({type: 'danger', message: '请选择车辆'});
                    return false;
                }
                let formData = new FormData();
                formData.append("hphm", _this.hphm);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatVehicle/delVehile',
                    formData
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        Dialog.alert({
                            message: '解绑成功',
                        }).then(() => {
                            _this.$router.go(0);
                        });
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
            ondzbd(id,hphm){
                let _this = this;
                $(".cldiv2").css("background-image","url('/static/wx/bmfw/PCopy6.png')");
                $("#2"+id).css("background-image","url('/static/wx/bmfw/PCopy5.png')");
                _this.hphm = hphm;
            },
            /**
             * 查询电子保单
             */
            dzbd(){
                let _this = this;
                if(_this.hphm === ''){
                    Notify({type: 'danger', message: '请选择车辆'});
                    return false;
                }
                _this.$router.push({path:'/dzbdinfo', query:{
                        hphm: _this.hphm,
                    }});
            },
          /**
           * 预约信息
           */
          queryYyInfo() {
            let _this = this;
            let openid = Tool.getWxUser().openid;
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/queryYyInfo', {
              openid: openid
            }).then((response) => {
              let resp = response.data;
              _this.listdata = resp.content;
            })
          },
          checktype(obl){
            if(obl == 3){
              let _this = this;
              if(Tool.isEmpty(_this.listdata)){
                  _this.queryYyInfo()
              }


            }

          },
          /**
           * 详细信息
           */
          linktoxxxx(obj){
            let _this = this;
            SessionStorage.set(SAVY_YY_SUCCESS,obj);//保存预约信息的ID
            _this.$router.push("/ywyy/ywgryycg");

          }
        }
    }
</script>

<style scoped>
    .cldiv1 {
        width: 90%;height: 86px;
        border: 0px solid blue;
        margin: 10px auto;
        border-radius: 8px;
        background-image: url('/static/wx/bmfw/PCopy6.png');
    }
    .cldiv2 {
        width: 90%;height: 86px;
        border: 0px solid blue;
        margin: 10px auto;
        border-radius: 8px;
        background-image: url('/static/wx/bmfw/PCopy6.png');
    }
</style>
