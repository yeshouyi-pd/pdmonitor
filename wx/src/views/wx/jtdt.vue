<template>
    <div>
        <div class="ksxx">交通动态</div>
        <van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
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
        name: "jtdt",
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
            onLoad() {
                /*setTimeout(() => {
                    for (let i = 0; i < 10; i++) {
                        let a = {"message": "智慧交通交管助力城市缓堵 道路拥堵指数全国排名连年后退", "time": "2020-05-06"};
                        this.list.push(a);
                    }
                    // 加载状态结束
                    this.loading = false;
                    // 数据全部加载完成
                    if (this.list.length >= 40) {
                        this.finished = true;
                    }
                }, 1000);*/
            },
            showinfo(item) {
                console.log('id', item.id);
                let _this = this;
                this.$router.push({path:'/jtdtinfo', query:{id: item.id}})
            },
            /**
             * 获取信息
             */
            getList(){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wxNewinfo/wxlist', {
                    xxlb: '1',
                }).then((response)=>{
                    let resp = response.data;
                    _this.list =  resp.content;
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