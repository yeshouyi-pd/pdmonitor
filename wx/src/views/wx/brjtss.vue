<template>
    <div>
        <div class="ksxx">我的故障上报</div>
        <ul>
            <template v-for="(item,index) in brcolumnslx">
                <li :key="index">
                    <van-cell icon="/static/wx/bmfw/jtbz1.png" is-link :to="{path:'/brjtssinfo',query:{id:item.id}}">
                        {{columnslx|optionMapKV(item.gzlx)}}
                    </van-cell>
                </li>
            </template>
        </ul>

        <van-tabbar v-model="active">
            <van-tabbar-item replace to="/jtsssb" icon="wap-home">
                交通设施故障上报
            </van-tabbar-item>
            <van-tabbar-item replace to="/brjtss" icon="manager">
                我的故障上报
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "brjtss",
        data: function () {
            return {
                active: 1,
                title:'',
                brcolumnslx: [],
                columnslx: [],
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.selectfail();
            _this.getFacility();
        },
        methods: {
            selectfail() {
                let _this = this;
                let formData = new FormData();
                formData.append("openid", Tool.getWxOpendid());
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/reportFacilityfail/selectfail',
                    formData
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.brcolumnslx = resp.content;
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            /**
             * 获取交安故障类型类型
             */
            getFacility(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getFacility', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columnslx = resp.content;
                    console.log('columnslx', _this.columnslx);
                    /*for(let i in resp.content){
                        _this.columnslx.push(resp.content[i]);
                    }*/
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
        font-weight:bold;
        clear: both;
    }
</style>