<template>
    <div>
        <van-cell-group>
            <van-cell title="事故发生地点" v-model="accident.sgdd"/>
            <van-cell title="事故上报时间" v-model="accident.sgfssj"/>
            <van-cell title="事故照片"/>
            <ul>
                <li v-for="(item, index) in images" :key="item">
                    <van-image width="100" height="100" :src="item" v-on:click="HandleclickImg(index)"/>
                </li>
            </ul>
            <van-cell title="处理状态">
                <span v-if="accident.zt == 0">未处理</span>
                <span v-else-if="accident.zt == 1">已处理</span>
            </van-cell>
        </van-cell-group>
        <div style="margin: 16px;">
            <van-button round block type="info" @click="$router.back(-1)" native-type="button">
                返回
            </van-button>
        </div>

    </div>
</template>

<script>

    import Dialog from "vant/lib/dialog";
    import ImagePreview from "vant/lib/image-preview";

    export default {
        name: "sgjdcxinfo",
        data() {
            return {
                accident: {},
                sgbh: '',
                images: [],
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.sgbh = _this.$route.query.sgbh;//路由传参
            _this.onload();
        },
        methods: {
            onload() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/selectAccident?sgbh='+_this.sgbh
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        _this.accident = resp.content;
                        _this.onloadpic();
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
            onloadpic(){
                let _this = this;
                let formData = new FormData();
                formData.append("lsh", _this.accident.lsh);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatFileinfo/getFileinfoByLsh',
                    formData
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        for(let i in resp.content){
                            _this.images.push(process.env.VUE_APP_SERVER + resp.content[i].zplj);
                        }
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
            HandleclickImg(index) {
                let _this = this;
                ImagePreview({
                    images: _this.images,
                    startPosition: index,
                });
            },
        }
    }
</script>

<style scoped>
    ul li {
        display: inline-block;
        margin-left: 10px;
        margin-top: 5px;
        padding: 0px;
    }
</style>