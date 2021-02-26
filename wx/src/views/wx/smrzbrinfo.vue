<template>
    <div>
        <van-steps :active="active">
            <van-step>上传身份信息</van-step>
            <van-step>上传正脸照片</van-step>
            <van-step>认证结果</van-step>
        </van-steps>
        <van-form @submit="onSubmit">
            <div style="text-align: center;margin-top: 50px;line-height: 30px;color: red;font-size: 15px;">
                正脸照片
            </div>
            <div style="margin:5px auto;text-align: center;" @change="changeImagebr($event)">
                <van-uploader multiple :accept="'image/*'" :preview-size="30" :deletable="false"  :after-read="afterRead">
                    <img height="200px" width="150px" :src="imagesbr" class="img-avatar" />
                </van-uploader>
            </div>
            <div style="color: #969799;text-align: center;font-size: 14px;">
                只允许上传jpg或png格式图片
            </div>
            <div v-on:click="showgf" style="color: #2A72F2;text-align: center;font-size: 14px;">
                查看拍摄规范
            </div>
            <div class="button_db">
                <van-button round block type="info" native-type="submit">
                    提交认证
                </van-button>
            </div>
            <van-overlay :show="show" @click="show = false">
                <div class="wrapper" @click.stop>
                    <div style="color: #FFFFFF;text-align: center;border: 0px solid red;">
                        <p style="font-size: 15px;">拍&nbsp;摄&nbsp;规&nbsp;范</p>
                        <div style="margin-top: 10px;">
                            <van-image width="25%" height="25%" style="left: -5%;" src="/static/wx/sfrz/rlzmt_bz.png"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 65%; top: 19%;font-size: 14px;" >保持水平拍摄<br/>脸部完成露出</div>
                        </div>
                        <div style="margin-top: 10px;">
                            <van-image width="29%" height="29%" style="left: -3%;" src="/static/wx/sfrz/rlzmt_zg.png"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 65%; top: 40%;font-size: 14px;" >保持光线适宜<br/>避免太强太弱</div>
                        </div>
                        <div style="margin-top: 10px;">
                            <van-image width="29%" height="29%" style="left: -3%;" src="/static/wx/sfrz/rlzmt_hd.png"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 65%; top: 62%;font-size: 14px;" >保持环境安静<br/>避免镜头摇晃</div>
                        </div>
                        <div style="border: 1px solid #FFFFFF;height:35px;line-height:35px; font-size: 15px;width: 150px;position: absolute;left: 28%; top: 78%;" v-on:click="hidegf">我&nbsp;知&nbsp;道&nbsp;了</div>
                    </div>
                </div>
            </van-overlay>
        </van-form>
    </div>
</template>

<script>
    import Notify from "vant/lib/notify";
    import Dialog from "vant/lib/dialog";
    import Toast from "vant/lib/toast";

    export default {
        name: "smrzbrinfo",
        data() {
            return {
                active: 1,
                sfzmhm: '',
                tp2: '',
                show: false,
                imagesbr: '/static/wx/sfrz/rlzmt_bj.png',
            };
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.sfzmhm = _this.$route.query.sfzmhm;
            _this.tp2 = _this.$route.query.tp2;
        },
        methods: {
            afterRead(file) {
                console.log(file);
            },
            // 返回布尔值
            beforeRead(file) {

            },
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                if (_this.imagesbr === '/static/wx/sfrz/rlzmt_bj.png') {
                    Notify({type: 'danger', message: '请上传本人正脸照片'});
                    return false;
                }
                Toast.loading({
                    duration: 0,
                    message: '加载中...',
                    forbidClick: true,
                });
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/sfrz/saveWxUser', {
                    sfzmhm: _this.sfzmhm,
                    tp2: _this.tp2,
                    base64imagesbr: _this.imagesbr,
                }).then((response) => {
                    Toast.clear();
                    let resp = response.data;
                    if (resp.success) {
                        Tool.setWxUser(resp.content);
                        _this.$router.push("/cgym");
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
            changeImagebr(e) {
                let file = e.target.files[0];
                if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
                    Notify({type: 'danger', message: '请上传jpg或png格式图片'});
                    return false;
                }
                let reader = new FileReader();
                let _this = this;
                reader.readAsDataURL(file);
                reader.onload = function () {
                    _this.imagesbr = this.result;
                };
            },
            showgf(){
                let _this = this;
                _this.show = true;
            },
            hidegf(){
                let _this = this;
                _this.show = false;
            },
        }
    }
</script>

<style scoped>

    .wrapper {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 80%;
    }

    .button_db {
        width: 90%;
        position: absolute;
        bottom: 10px;
        transform: translateX(5%);
    }

</style>