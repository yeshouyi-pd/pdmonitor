<template>
    <div>
        <div>
            <div style="margin-top: 50px;">
                <van-row type="flex" justify="center">
                    <van-image
                            round
                            default="成功"
                            width="10rem"
                            height="10rem"
                            src="/static/wx/cg.png"
                    />
                </van-row>
                <div style="text-align: center;margin-top: 20px;font-size: 20px">
                    已提交交警远程定责，请耐心候！
                </div>
            </div>
            <div class="button_db">
                <van-button round block type="info" to="/index" native-type="submit">
                    返回首页
                </van-button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "jtsgyydz",
        data() {
            return {
                info: {},
                depts: [],
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.info.sgbh = _this.$route.query.sgbh;
            _this.info.dsrxm1 = _this.$route.query.dsrxm1;
            _this.info.dsrbsgzr1 = _this.$route.query.dsrbsgzr1;
            _this.info.dsrbxgs1 = _this.$route.query.dsrbxgs1;
            _this.getDeptMap();
        },
        methods: {
            onload() {
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/selectAccident',
                    _this.info,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push({path:'/jtsgyydz', query:{dsrxm1: _this.info.dsrxm1, dsrbsgzr1: _this.info.dsrbsgzr1, dsrbxgs1: _this.info.dsrbxgs1, sgbh: _this.info.sgbh}});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            /**
             * 获取部门信息
             */
            getDeptMap(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDeptMap', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.depts = resp.content;
                })
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
</style>