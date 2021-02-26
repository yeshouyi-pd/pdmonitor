<template>
    <div>
        <div class="ksxx">申领人信息</div>
        <van-form @submit="onSubmit">
            <van-cell-group>
                <van-cell title="业务名称" :value="ywmc" />
                <van-cell title="申领人" :value="createBy"  />
                <van-cell title="身份证明号码" :value="sfzmhm"  />
                <van-cell title="申领车辆" :value="hphm"  />
                <van-cell title="申领车牌螺丝数量" :value="slslxs"  />
                <van-cell title="申领原因">{{allScrewYy|optionMapKV(sqyy)}}</van-cell>
                <van-cell title="申领地点">{{allSysOrgCode|optionMapKV(sysOrgCode)}}</van-cell>
            </van-cell-group>
            <div>
            </div>
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
        name: "cplsslxxjg",
        data: function () {
            return {
                ywmc: '线下车牌螺丝固封申领',
                createBy: '',
                sfzmhm: '',
                slsl: '',
                slslxs: '',
                sysOrgCode: '',
                sqyy: '',
                hphm: '',
                sfbr: '',
                info: {},
                allScrewYy: {},
                allSysOrgCode: {},
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.slsl = _this.$route.query.slsl;//路由传参
            if('1' == _this.$route.query.slsl){
                _this.slslxs = "一副(4个)";
            }else if('2' ==  _this.$route.query.slsl){
                _this.slslxs = "二副(8个)";
            }
            _this.sysOrgCode = _this.$route.query.sysOrgCode;
            _this.sqyy = _this.$route.query.sqyy;
            _this.hphm = _this.$route.query.hphm;
            _this.sfbr = _this.$route.query.sfbr;
            _this.loadUser();
            _this.getScrewYy();
            _this.getDeptMap();
        },
        methods: {
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                _this.info.sllb = '2';//1线上申领2线下申领
                _this.info.slsl = _this.slsl;
                _this.info.sysOrgCode = _this.sysOrgCode;
                _this.info.sqyy = _this.sqyy;
                _this.info.hphm = _this.hphm;
                _this.info.sfbr = _this.sfbr;
                _this.info.ywmc = _this.ywmc;
                _this.info.createBy = _this.createBy;
                _this.info.sfzmhm = _this.sfzmhm;
                _this.info.sllb = '2';//1线上申领2线下申领
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
                _this.$router.push({path:'/cplsslxx', query:{
                        sysOrgCode: _this.sysOrgCode,
                        slsl: _this.slsl,
                        hphm: _this.hphm,
                        sqyy: _this.sqyy,
                        sfbr: _this.sfbr,
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