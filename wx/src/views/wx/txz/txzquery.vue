<template>
    <div >
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
                <img src="/static/wx/txz/txzindex.jpg" style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>
        <!-- 搜索提示 -->
        <div v-show="listdata.length === 0">
            <van-empty image="search" description="无相关信息,请重试！" />
        </div>
        <div  class="van-address-list">
            <div v-show="listdata.length > 0">
                <template v-for="txzinfo in   listdata">
                    <div :key="txzinfo.id" class="van-address-item" style="background-color:#FFFAFA ">
                        <div class="van-cell van-cell--borderless" style="background-color: #FFFAFA">
                            <div class="van-radio__icon van-radio__icon--round van-radio__icon--checked"
                                 style="margin-top: 6px;font-size: 0.7em;">
                                <i class="van-icon " style="background: #00a0e9;border-color:#00a0e9"></i>
                            </div>
                            <div class="van-cell__value van-cell__value--alone van-address-item__value">
                                <div class="van-radio">
                                <span class="van-radio__label">
                                <div class="van-address-item__name" style="font-weight: bold;font-size: 1em">
                                    {{txzinfo.hphm}}
                                </div>
                                <div class="van-address-item__address" style="font-size: 0.8em;color: #6c6c6c">
                                    申请时间：{{txzinfo.createTime}}<br/>
                                    有效期：{{txzinfo.yxqkssj}}-{{txzinfo.yxqjssj}}<br/>
                                    出发地：{{txzinfo.cfd}}<br/>
                                    目的地：{{txzinfo.mdd}}<br/>

                                </div>
                                </span>

                                </div>
                            </div>
                            <div style="padding-top: 40px ;"  v-on:click="linktotxzqueryInfo(txzinfo)">
                                <div style="float: left;color:#1989fa;font-size: 0.8em;font-weight: bold">
                                    {{SLZT_STATUS|optionKVArray(txzinfo.zt)}}
                                </div>
                                <div style="float: right">
                                    <i style="color:#1989fa;" class=" van-icon van-icon-arrow van-cell__right-icon"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </template>
            </div>

            <div  class="van-address-list__bottom">
                <van-button  round block type="info"
                             color="linear-gradient(to right,#7FFFAA,#1E90FF)"
                             style="box-shadow: 2px 2px 10px #00FFFF;"
                             to="/index">
                    返回首页
                </van-button>
                <div style="margin-top: 8px"></div>

            </div>
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    export default {

        name:'txzquery',
        data:function(){
            return{
                listdata:[],
                SLZT_STATUS:[{key:"1", value:"未审核"},{key:"2", value:"已审核"},{key:"3", value:"审核不通过"}],//受理状态


            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.queryTxz();


        },
        methods:{
          queryTxz() {
                let _this = this;
                let openid = "";
                if (Tool.isEmpty(Tool.getWxUser())) {
                    Dialog({message: "请实名认证"});
                    _this.$router.push("/smrz");
                } else {
                    openid = Tool.getWxUser().openid;
                    if (Tool.isEmpty(openid)) {
                        Dialog({message: "操作异常！"});
                        _this.$router.push("/index");
                    }

                }
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/lstxz/queryTxz', {
                    openid: openid
                }).then((response) => {
                    let resp = response.data;
                    _this.listdata = resp.content;
                })
            },

            /**
             * 详细信息
             */
            linktotxzqueryInfo(txzinfo){
                let _this = this;
                SessionStorage.set(LIKN_TO_TXZSHOWINFO,txzinfo);//保存预约信息的ID
                _this.$router.push("/txz/txzqueryInfo");

            }

        }

    }
</script>

<style scoped>



</style>
