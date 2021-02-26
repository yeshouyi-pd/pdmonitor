<template>
    <div>
        <van-form>
            <van-cell title="报案人">{{info.xm}}</van-cell>
            <van-cell title="报案人身份证号">{{info.sfzmhm}}</van-cell>
            <van-cell title="号牌号码">{{info.hphm}}</van-cell>
            <van-cell title="报案时间">{{info.createTime}}</van-cell>
            <van-cell title="上报图片"></van-cell>
            <img v-for="(item,index) in piclist" style="margin-left: 10px;" :key="index" width="100" height="100" :src="path+item.zplj" @click="HandleclickImg(index)"/>
            <div style="clear: both;"></div>
            <div class="button_db">
                <van-button to="/index" round block type="info" native-type="button">
                    返回主页
                </van-button>
            </div>
        </van-form>

    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    import ImagePreview from "vant/lib/image-preview";

    export default {
        name: "tpcjblsinfo",
        data() {
            return {
                id: '',
                info: {},
                piclist: [],
                show: false,
                index: 0,
                images: [],
                path: '',
            };
        },
        mounted() {
            let _this = this;
            _this.id = _this.$route.query.id;
            _this.selectinfo();
        },
        methods: {
            onChange(index) {
                this.index = index;
            },
            HandleclickImg(index) {
                let _this = this;
                ImagePreview({
                    images: _this.images,
                    startPosition: index,
                });
            },
            selectinfo(){
                let _this = this;
                let formData = new FormData();
                formData.append("id", _this.id)
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehRobbery/selectinfo',
                    formData
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content.info;
                        _this.info.sfzmhm = Tool.decryptByDES(_this.info.sfzmhm);
                        _this.piclist = resp.content.piclist;
                        _this.path = process.env.VUE_APP_SERVER;
                        for(let i in _this.piclist){
                            _this.images.push(process.env.VUE_APP_SERVER +_this.piclist[i].zplj);
                        }
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
        },
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        margin: 10px 0 10px 5%;
    }
</style>