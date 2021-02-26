<template>
    <div>
        <div class="ksxx">申领人信息</div>
        <van-form @submit="onSubmit">
            <van-cell-group>
                <van-cell title="业务名称" :value="ywmc" />
                <van-cell title="申领人" :value="createBy"  />
                <van-cell title="身份证明号码" :value="sfzmhm"  />
                <van-cell title="申领车辆" :value="info.hphm"  />
                <van-cell title="申领车牌螺丝数量" :value="slslxs"  />
                <van-cell title="申领原因">{{allScrewYy|optionMapKV(info.sqyy)}}</van-cell>
                <van-cell title="收件人" :value="info.sjr"  />
                <van-cell title="联系电话" :value="info.lxdh"  />
                <van-cell title="收货地址" :value="dq+info.jddz"  />
            </van-cell-group>
            <div class="button_db1">
                <van-button round block type="info" native-type="submit">
                    确认办理
                </van-button>
            </div>
            <div class="button_db2">
                <van-button round block v-on:click="lastPage()" type="primary"  native-type="button">
                    返回修改
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "cplssljg",
        data: function () {
            return {
                ywmc: '线上车牌螺丝固封申领',
                createBy: '',
                sfzmhm: '',
                info: {},
                slslxs: '',
                allScrewYy: '',
                dq:'',
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.info.slsl = _this.$route.query.slsl;//路由传参
            if('1' == _this.$route.query.slsl){
                _this.slslxs = "一副(4个)";
            }else if('2' ==  _this.$route.query.slsl){
                _this.slslxs = "二副(8个)";
            }
            _this.info.hphm = _this.$route.query.hphm;
            _this.info.sqyy = _this.$route.query.sqyy;
            _this.info.sjr = _this.$route.query.sjr;
            _this.info.lxdh = _this.$route.query.lxdh;
            _this.dq = _this.$route.query.dqname;
            _this.info.dq = _this.$route.query.dq;
            _this.info.jddz = _this.$route.query.jddz;
            _this.loadUser();
            _this.getScrewYy();
        },
        methods: {
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                _this.info.ywmc = _this.ywmc;
                _this.info.createBy = _this.createBy;
                _this.info.sfzmhm = _this.sfzmhm;
                _this.info.sllb = '1';//1线上申领2线下申领
                _this.info.openid = Tool.getWxOpendid();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehScrew/wxsave',
                    _this.info,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.$router.push({path:'/cgym'});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            lastPage(){
                let _this = this;
                _this.$router.push({path:'/cplsslxs', query:{
                        hphm: _this.info.hphm,
                        sqyy: _this.info.sqyy,
                        slsl: _this.info.slsl,
                        sjr: _this.info.sjr,
                        lxdh: _this.info.lxdh,
                        dqname: _this.dq,
                        dq: _this.info.dq,
                        jddz: _this.info.jddz,
                 }});
            },
            loadUser(){
                let _this = this;
                let wxUser = Tool.getWxUser();
                _this.createBy = wxUser.zjmc;
                _this.sfzmhm = wxUser.zjhm;
            },
            /**
             * 获取申领原因
             */
            getScrewYy(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getScrewYy', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.allScrewYy = resp.content;
                })
            },
        }
    }
</script>

<style scoped>
    .ksxx {
        font-size: 17px;
        width: 100%;
        background-color: #F2F2F2;
        height: 40px;
        text-align: center;
        line-height: 40px;
    }
    .button_db1 {
        width: 95%;
        position:fixed;
        bottom: 60px;
        left: 2.5%;
    }
    .button_db2 {
        width: 95%;
        bottom: 10px;
        position:fixed;
        left: 2.5%;
    }
</style>