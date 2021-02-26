<template>
    <div>
        <van-image width="100%" src="/static/wx/bmfw/cpys1.jpg"/>
        <div>
            <van-form validate-first @submit="onSubmit">
                <van-field
                        readonly
                        clickable
                        :value="hpzlzs"
                        label="车牌类型"
                        placeholder="请选择车牌类型"
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
                        v-model="vehLicelost.hphm"
                        label="号牌号码"
                        placeholder="请输入号牌号码"
                />
                <div class="button_db">
                    <van-button round block type="info" native-type="submit">
                        查询
                    </van-button>
                </div>
            </van-form>
        </div>
    </div>
</template>

<script>
import Dialog from "vant/lib/dialog";
    export default {
        name: "cpyscx",
        data() {
            return {
                vehLicelost: {},
                hpzlzs: '',
                pattern: /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/,
                columns: [],
                showPicker: false,
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getCplx();
        },
        methods: {
            onConfirm(value) {
                let _this = this;
                _this.hpzlzs = value.text;
                _this.vehLicelost.hpzl = value.code;
                _this.showPicker = false;
            },
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
              if(Tool.isEmpty( _this.hpzlzs)){
                Dialog({ message: "请选择车牌类型" });
                return;
              }
              if(Tool.isEmpty( _this.vehLicelost.hphm)){
                Dialog({ message: "请输入正确号牌号码" });
                return;
              }
                _this.$router.push({path:'/cpyscxjg', query:{hpzl: _this.vehLicelost.hpzl, hphm: _this.vehLicelost.hphm}});
            },
            /**
             * 获取车牌类型
             */
            getCplx(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxCplx', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columns = resp.content;
                    /*for(let i in resp.content){
                        _this.columns.push(resp.content[i]);
                    }*/
                })
            },
        }
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        /*position:fixed;*/
        margin: 10% 0 0 5%;
    }
</style>