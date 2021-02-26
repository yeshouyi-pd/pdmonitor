<template>
    <div style="width: 90%;margin: auto; border: 0px solid red;">
        <p align="center" style="font-size: 18px">{{info.title}}</p>
        <p align="center" style="font-size: 13px">
            <span>发布时间：{{info.createTime | formatDateTime}}</span></p>
        <van-divider/>
        <div v-html="info.content" class="showpic" style="line-height: 23px;">
        </div>
    </div>
</template>

<script>
    import Notify from "vant/lib/notify";

    export default {
        name: "jtdtinfo",
        data() {
            return {
                info: {},
                id: '',
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.id = _this.$route.query.id;//路由传参
            _this.showinfo();
        },
        methods: {
            showinfo(){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wxNewinfo/findByID', {
                    id: _this.id,
                }).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                    } else {
                        Notify({type: 'danger', message: '查看详细失败'});
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .showpic >>> img{
        width: 100%;
    }
</style>