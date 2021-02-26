<template>
    <div>
        <van-image src="/static/wx/u893.jpg"/>

        <van-form validate-first @submit="onSubmit">

            <div style="margin:10px 18px;">
                请选择车辆
            </div>
            <span v-if="vehiles.length == '0'">
                <p>您还没有绑定车辆,点击<a href="/grcl">绑定车辆</a></p>
            </span>
            <span v-else-if="vehiles.length != 0">
                <ul id="example-1">
                    <li v-for="vehile in vehiles" :key="vehile.id">
                        <div :id="vehile.id" v-on:click="dzbd(vehile.id,vehile.hphm)" class="cldiv">
                            <div style="color: #FFFFFF;padding: 6% 0 0% 40%;">
                                {{ vehile.hphm }}
                            </div>
                            <div style="color: #FFFFFF;padding: 2% 0 0% 40%;font-size: 14px;">
                                {{allHpzl|optionMapKV(vehile.hpzl)}}
                            </div>
                        </div>
                    </li>
                </ul>
            </span>

            <div class="button_db">
                <van-button round block type="info" native-type="submit">
                    查看电子保单
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Notify from "vant/lib/notify";
    import Dialog from "vant/lib/dialog";

    export default {
        name: "dzbd",
        data:function(){
            return{
                hphm: '',
                wxUser: {},
                dzbdinfo: {},
                vehiles: [],
                allHpzl: [],
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.getWxSmrzUser();
            _this.getWxUser();
            _this.getVehile();
            _this.getHpzl();
        },
        methods:{
            getWxUser(){
                let _this = this;
                let wxUser = Tool.getWxUser();
                if(null != wxUser){
                    _this.wxUser = wxUser;
                }
            },
            dzbd(id,hphm){
                let _this = this;
                $(".cldiv").css("background-image","url('/static/wx/bmfw/PCopy6.png')");
                $("#"+id).css("background-image","url('/static/wx/bmfw/PCopy5.png')");
                _this.hphm = hphm;
            },
            onSubmit(values) {
                console.log('submit', values);
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
             * 获取车辆信息
             */
            getVehile() {
                let _this = this;
                let formData = new FormData();
                formData.append("sfzmhm", _this.wxUser.zjhm);
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
        width: 95%;
        position:fixed;
        bottom: 10px;
        left: 2.5%;
    }
    .cldiv {
        width: 90%;height: 86px;
        border: 0px solid blue;
        margin: 10px auto;
        border-radius: 8px;
        background-image: url('/static/wx/bmfw/PCopy6.png');
    }
</style>