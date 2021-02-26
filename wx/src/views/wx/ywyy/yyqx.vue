<template>
    <div >
        <!-- 搜索 -->
        <van-search
                v-model="wxyy.zjhm"
                show-action
                label="身份证号码"
                shape="round"
                background="linear-gradient(to right,#7FFFAA,#1E90FF)"
                placeholder="请输入预约人身份证号"
        >
            <template #action>
                <div @click="queryYyInfo">搜索</div>
            </template>
        </van-search>

        <!-- 搜索提示 -->
        <div v-show="listdata.length === 0">
            <van-empty image="search"  description="无相关信息,请重试！" />
        </div>
        <div  class="van-address-list">
            <div v-show="listdata.length > 0">
                <template v-for="wxyy in   listdata">
                    <div :key="wxyy.id" class="van-address-item" style="background-color:#FFFAFA ">
                        <div class="van-cell van-cell--borderless" style="background-color: #FFFAFA">
                            <div class="van-radio__icon van-radio__icon--round van-radio__icon--checked"
                                 style="margin-top: 6px;font-size: 0.7em;">
                                <i class="van-icon " style="background: #00a0e9;border-color:#00a0e9"></i>
                            </div>
                            <div class="van-cell__value van-cell__value--alone van-address-item__value">
                                <div class="van-radio">
                                <span class="van-radio__label">
                                <div class="van-address-item__name" style="font-weight: bold;font-size: 1em">
                                    {{wxyy.yelxname}}
                                </div>
                                <div class="van-address-item__address" style="font-size: 0.8em;color: #6c6c6c">
                                    申请时间：{{wxyy.cjsj}}<br/>
                                    预约时段：{{wxyy.yysj}} {{wxyy.yyrq}}<br/>
                                    申请单位：{{wxyy.deptname}}
                                </div>
                                </span>

                                </div>
                            </div>
                            <div style="padding-top: 35px ;">
                                <van-button
                                        v-on:click="quyy(wxyy)"
                                        style="height: 1.8em"
                                        size="mini"
                                        round type="primary" color="linear-gradient(to right, #ff6034, #ee0a24)" ><font style="font-size:0.6em">取消预约</font></van-button>
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

        name:'yyqx',
        data:function(){
            return{
                listdata:[],
                wxyy:{},
                SLZT_STATUS:[{key:"1", value:"已预约"},{key:"2", value:"已取消"},{key:"3", value:"已过期"},{key:"4", value:"已办结"},{key:"5", value:"已办结"}],//受理状态


            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.queryYyInfo();


        },
        methods:{
            queryYyInfo() {
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
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/queryYyInfo', {
                    openid: openid,
                    zt:'1',//已预约
                    zjhm:_this.wxyy.zjhm,
                }).then((response) => {
                    let resp = response.data;
                    _this.listdata = resp.content;
                })
            },

            /**
             * 详细信息
             */
            quyy(obj){
                let _this = this;
                Dialog.confirm({
                    theme: 'round-button',
                    confirmButtonText:'确定',
                    message: '您是否确定取消'+'<font style="font-weight: bold;color: #00a0e9">'+obj.yelxname+'</font>' +'业务预约？',
                })
                    .then(() => {
                        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/xyyy', {
                            id: obj.id,
                        }).then((response) => {
                            let resp = response.data;
                            if(resp.success){
                                if(1 === resp.content){
                                    _this.queryYyInfo();//刷新页面
                                    Dialog({message: "操作成功！"});

                                }else{
                                    Dialog({message: "操作失败，请重试！"});
                                }
                            }else{
                                Dialog({message: "系统异常，请重试！"});
                            }

                        })

                    })
                    .catch(() => {

                    });

            },

        }

    }
</script>

<style scoped>



</style>
