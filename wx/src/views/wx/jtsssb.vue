<template>
    <div>
        <van-image src="/static/wx/bmfw/jtss1.jpg"/>
        <ul>
            <template v-for="(item,index) in columnslx">
                <li :key="index">
                    <van-cell icon="/static/wx/bmfw/jtbz1.png" :title=item is-link :to="{path:'/jtsssbinfo',query:{code:index,name:item}}"/>
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
    export default {
        name: "jtsssb",
        data: function () {
            return {
                active: 0,
                title:'',
                columnslx: [],
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getFacility();
        },
        methods: {
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
    .van-icon-location-o::before {
        background-image: url("/static/wx/bmfw/jtbz1.png");
    }
</style>