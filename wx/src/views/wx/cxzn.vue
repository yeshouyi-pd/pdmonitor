<template>
    <div>
        <div class="ksxx">出行指南</div>
        <van-list v-model="loading" :finished="finished" finished-text="没有更多了">
            <ul>
                <template v-for="(item,index) in list">
                    <li :key="index">
                        <div style="width: 90%;margin: auto;" v-on:click="showinfo(item)">
                            <div style="font-size: 16px;">{{item.title}}</div>
                            <div style="text-align: right;font-size: 13px;">{{item.createTime | formatDateTime}}</div>
                            <van-divider/>
                        </div>
                    </li>
                </template>
            </ul>
        </van-list>
    </div>
</template>

<script>
    export default {
        name: "cxzn",
        data() {
            return {
                list: [],
                loading: false,
                finished: false,
            };
        },
        mounted: function () {
            let _this = this;
            _this.getList();
        },
        methods: {
            showinfo(item) {
                let _this = this;
                this.$router.push({path:'/jtdtinfo', query:{id: item.id}})
            },
            /**
             * 获取信息
             */
            getList(){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wxNewinfo/wxlist', {
                    xxlb: '3',
                }).then((response)=>{
                    let resp = response.data;
                    _this.list = resp.content;
                    this.finished = true;
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
    }
</style>