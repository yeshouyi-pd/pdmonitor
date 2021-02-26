<template>
    <div>
        <div class="ksxx">车辆绑定</div>
        <van-form @submit="onSubmit">
            <van-cell-group>
                <van-field v-model="wxUser.zjmc" readonly label="车主姓名" />
                <van-field v-model="wxUser.zjhm" readonly label="车主身份证明号码" />
                <van-field
                        readonly
                        clickable
                        :value="hpzlxs"
                        label="号牌种类"
                        placeholder="请点击选择号牌种类"
                        @click="showPicker = true"
                />
                <van-popup v-model="showPicker" position="bottom">
                    <van-picker
                            show-toolbar
                            :columns="columns"
                            @confirm="onConfirm"
                            @cancel="showPicker = false"
                    />
                </van-popup>
                <van-field
                        v-model="hphm"
                        label="号牌号码"
                        placeholder="号牌号码"
                />
                <van-field
                        v-model="clsbdh"
                        label="车辆识别代号后六位"
                        placeholder="车辆识别代号后六位"
                />
            </van-cell-group>
            <div class="button_db">
                <van-button round block type="info" native-type="submit">
                    提交绑定
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    import Notify from "vant/lib/notify";

    export default {
        name: "grcl",
        data() {
            return {
                wxUser: {},
                hpzl: '',
                hpzlxs: '',
                hphm: '',
                clsbdh: '',
                columns:[],
                showPicker: false,
            };
        },
        mounted() {
            let _this = this;
            _this.wxUser = Tool.getWxUser();
            _this.getHpzl();
        },
        methods: {
            onSubmit(values) {
                let _this = this;
                if(Tool.isEmpty( _this.hpzlxs)){
                  Dialog({ message: "请点击选择号牌种类" });
                  return;
                }
                if(Tool.isEmpty( _this.hphm)){
                  Dialog({ message: "请填写号牌号码" });
                  return;
                }
                if(Tool.isEmpty( _this.clsbdh)){
                  Dialog({ message: "请填写车辆识别代号后六位" });
                  return;
                }
                let formData = new FormData();
                formData.append("hphm", _this.hphm);
                formData.append("clsbdh", _this.clsbdh);
                formData.append("hpzl", _this.hpzl);
                formData.append("xm", _this.wxUser.zjmc);
                formData.append("sfzmhm", _this.wxUser.zjhm);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatVehicle/wxsave',
                    formData
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        _this.$router.push("/cgym");
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
            /**
             * 获取号牌种类
             */
            getHpzl(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxHpzl', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columns = resp.content;
                })
            },
            onConfirm(value) {
                let _this = this;
                _this.hpzlxs = value.text;
                _this.hpzl = value.code;
                _this.showPicker = false;
            },
        }
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        position:fixed;
        bottom: 10px;
        left: 5%;
    }
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