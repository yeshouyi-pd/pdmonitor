<template>
    <div>
        <van-image height="70px" src="/static/wx/u956.jpg"/>
        <van-form @submit="onSubmit">
            <van-field name="radio" label="事故是否发生在高架">
                <template #input>
                    <van-radio-group v-model="radio" direction="horizontal">
                        <van-radio name="1">是</van-radio>
                        <van-radio name="2">否</van-radio>
                    </van-radio-group>
                </template>
            </van-field>
            <div class="ksxx">已方驾驶证信息</div>
            <!--<div style="margin:10px auto;text-align: center;" @change="changeImageA($event)">
                <van-uploader multiple :accept="'image/*'" :preview-size="30" :deletable="false"  :after-read="afterRead">
                    <img height="150px" width="90%" :src="imagesA" class="img-avatar" />
                </van-uploader>
            </div>-->
            <van-field
                    v-model="info.dsrxm1"
                    label="当事人姓名"
                    placeholder="当事人姓名"
            />
            <van-field
                    v-model="info.dsrsjhm1"
                    label="联系电话"
                    placeholder="联系电话"
            />
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
                    v-model="info.hphm1"
                    label="号牌号码"
                    placeholder="号牌号码"
            />

            <div class="ksxx">对方驾驶证信息</div>
            <!--<div style="margin:5px auto;text-align: center;" @change="changeImageB($event)">
                <van-uploader multiple :accept="'image/*'" :preview-size="30" :deletable="false"  :after-read="afterRead">
                    <img height="150px" width="90%" :src="imagesB" class="img-avatar" />
                </van-uploader>
            </div>-->
            <van-field
                    v-model="info.dsrxm2"
                    label="当事人姓名"
                    placeholder="当事人姓名"
            />
            <van-field
                    v-model="info.dsrsjhm2"
                    label="联系电话"
                    placeholder="联系电话"
            />
            <van-field
                    readonly
                    clickable
                    :value="hpzlxsd"
                    label="号牌种类"
                    placeholder="请点击选择号牌种类"
                    @click="showPickerd = true"
            />
            <van-popup v-model="showPickerd" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnsd"
                        @confirm="onConfirmd"
                        @cancel="showPickerd = false"
                />
            </van-popup>
            <van-field
                    v-model="info.hphm2"
                    label="号牌号码"
                    placeholder="号牌号码"
            />
            <div style="width: 95%;margin: 10px auto;">
                <van-button round block type="info" native-type="submit">
                    下一步
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Notify from "vant/lib/notify";
    import Dialog from "vant/lib/dialog";

    export default {
        name: "jtsgxxcj",
        data() {
            return {
                radio: '2',
                sgbh: '',
                info: {},
                hpzlxs: '',
                hpzlxsd: '',
                columns: [],
                showPicker: false,
                columnsd: [],
                showPickerd: false,
                imagesA: '/static/wx/jsz.png',
                imagesB: '/static/wx/jsz.png',
            };
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.sgbh = _this.$route.query.sgbh;
            _this.info.dsrxm1 = _this.$route.query.dsrxm1;
            _this.info.dsrsjhm1 = _this.$route.query.dsrsjhm1;
            _this.getHpzl();
        },
        methods: {
            afterRead(file) {
                console.log(file);
            },
            beforeRead(file) {
                return true;
            },
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                /*if(_this.imagesA === '/static/wx/jsz.png'){
                    Notify({type: 'danger', message: '请上传已方驾驶证照片'});
                    return false;
                }else if(_this.imagesB === '/static/wx/jsz.png'){
                    Notify({type: 'danger', message: '请上传对方驾驶证照片'});
                    return false;
                }*/
              if(Tool.isEmpty( _this.info.dsrxm1)){
                Dialog({ message: "请输入已方当事人姓名" });
                return;
              }
              if(Tool.isEmpty( _this.info.dsrsjhm1)){
                Dialog({ message: "请输入已方联系电话" });
                return;
              }
              if(Tool.isEmpty( _this.hpzlxs)){
                Dialog({ message: "请点击选择已方号牌种类" });
                return;
              }
              if(Tool.isEmpty( _this.info.hphm1)){
                Dialog({ message: "请填写已方号牌号码" });
                return;
              }
              if(Tool.isEmpty( _this.info.dsrxm2)){
                Dialog({ message: "请输入对方当事人姓名" });
                return;
              }
              if(Tool.isEmpty( _this.info.dsrsjhm2)){
                Dialog({ message: "请输入对方联系电话" });
                return;
              }
              if(Tool.isEmpty( _this.hpzlxsd)){
                Dialog({ message: "请点击选择对方号牌种类" });
                return;
              }
              if(Tool.isEmpty( _this.info.hphm2)){
                Dialog({ message: "请填写对方号牌号码" });
                return;
              }
                _this.info.sfgj = _this.radio;
                _this.info.sgbh = _this.sgbh;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/wxsaveAccident',
                    _this.info,
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.info = resp.content;
                        _this.$router.push({path:'/jtsgdz', query:{dsrxm1: _this.info.dsrxm1, dsrsjhm1: _this.info.dsrsjhm1, hphm1: _this.info.hphm1, dsrbxgs1: _this.info.dsrbxgs1,
                                dsrxm2: _this.info.dsrxm2, dsrsjhm2: _this.info.dsrsjhm2, hphm2: _this.info.hphm2, sgbh: _this.info.sgbh}});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            /*changeImageA(e) {
                let file = e.target.files[0];
                if(file.size > 4 * 1024 * 1024){
                    Notify({type: 'danger', message: '文件大小不能超过 4M'});
                    return false;
                }
                if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
                    Notify({type: 'danger', message: '请上传jpg或png格式图片'});
                    return false;
                }
                let reader = new FileReader();
                let _this = this;
                reader.readAsDataURL(file);
                reader.onload = function () {
                    _this.imagesA = this.result;
                };
            },
            changeImageB(e) {
                let file = e.target.files[0];
                if(file.size > 4 * 1024 * 1024){
                    Notify({type: 'danger', message: '文件大小不能超过 4M'});
                    return false;
                }
                if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
                    Notify({type: 'danger', message: '请上传jpg或png格式图片'});
                    return false;
                }
                let reader = new FileReader();
                let _this = this;
                reader.readAsDataURL(file);
                reader.onload = function () {
                    _this.imagesB = this.result;
                };
            },*/
            /**
             * 获取号牌种类
             */
            getHpzl(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxHpzl', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columns = resp.content;
                    _this.columnsd = resp.content;
                })
            },
            onConfirm(value) {
                let _this = this;
                _this.hpzlxs = value.text;
                _this.info.hpzl1 = value.code;
                _this.showPicker = false;
            },
            onConfirmd(value) {
                let _this = this;
                _this.hpzlxsd = value.text;
                _this.info.hpzl2= value.code;
                _this.showPickerd = false;
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