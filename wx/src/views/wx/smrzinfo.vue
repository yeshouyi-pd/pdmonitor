<template>
    <div style="padding-bottom: 70px;">
        <!--<remote-js src="/static/js/ydui.flexible.js"></remote-js>
        <remote-js src="/static/js/ydui.js"></remote-js>
        <remote-css href="/static/css/ydui.css"></remote-css>
        <remote-css href="/static/css/mystyle.css"></remote-css>-->

        <van-steps :active="active">
            <van-step>上传身份信息</van-step>
            <van-step>上传正脸照片</van-step>
            <van-step>认证结果</van-step>
        </van-steps>

        <van-form @submit="onSubmit">
            <div style="margin:10px auto;text-align: center;" @change="changeImagezm($event)">
                <van-uploader multiple :accept="'image/*'" :preview-size="30" :deletable="false"  :after-read="afterRead">
                    <img height="150px" width="90%" :src="imageszm" class="img-avatar" />
                </van-uploader>
            </div>
            <div style="margin:5px auto;text-align: center;" @change="changeImagefm($event)">
                <van-uploader multiple :accept="'image/*'" :preview-size="30" :deletable="false"  :after-read="afterRead">
                    <img height="150px" width="90%" :src="imagesfm" class="img-avatar" />
                </van-uploader>
            </div>
            <div style="color: #969799;text-align: center;font-size: 14px;">
                只允许上传jpg或png格式图片
            </div>
            <div v-on:click="showgf" style="color: #2A72F2;text-align: center;font-size: 14px;">
                查看拍摄规范
            </div>
            <van-field
                    v-model="username"
                    name="username"
                    label="姓名"
                    placeholder="姓名"
                    readonly
            />
            <van-field
                    v-model="sfzmhm"
                    name="sfzmhm"
                    label="身份证明号码"
                    placeholder="身份证明号码"
                    readonly
            />
            <van-field
                    v-model="lxdh"
                    name="lxdh"
                    label="手机号码"
                    placeholder="手机号码"
                    :rules="[{ required: true, message: '请输入手机号码' }]"
            />
            <div class="button_db" v-show="showBtn">
                <van-button round block type="info" native-type="submit">
                    下一步
                </van-button>
            </div>
            <van-overlay :show="show" @click="show = false">
                <div class="wrapper" @click.stop>
                    <div style="color: #FFFFFF;text-align: center;border: 0px solid red;">
                        <p style="font-size: 15px;">拍&nbsp;摄&nbsp;规&nbsp;范</p>
                        <div style="margin-top: 10px;">
                            <div style="position: absolute; left: 25%; top: 21%;"><van-image width="80%" height="80%" src="/static/wx/sfrz/true.png"/></div>&nbsp;&nbsp;
                            <van-image width="50%" height="50%" src="/static/wx/sfrz/scbz_xq.jpg"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 70%; top: 21%;font-size: 14px;" >正常</div>
                        </div>
                        <div style="margin-top: 10px;">
                            <div style="position: absolute; left: 25%; top: 35%;"><van-image width="80%" height="80%" src="/static/wx/sfrz/false.png"/></div>&nbsp;&nbsp;
                            <van-image width="50%" height="50%" src="/static/wx/sfrz/scbz_qbj.jpg"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 70%; top: 35%;font-size: 14px;" >缺边角</div>
                        </div>
                        <div style="margin-top: 10px;">
                            <div style="position: absolute; left: 25%; top: 49%;"><van-image width="80%" height="80%" src="/static/wx/sfrz/false.png"/></div>&nbsp;&nbsp;
                            <van-image width="50%" height="50%" src="/static/wx/sfrz/scbz_mh.jpg"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 70%; top: 49%;font-size: 14px;" >模糊</div>
                        </div>
                        <div style="margin-top: 10px;">
                            <div style="position: absolute; left: 25%; top: 63%;"><van-image width="80%" height="80%" src="/static/wx/sfrz/false.png"/></div>&nbsp;&nbsp;
                            <van-image width="50%" height="50%" src="/static/wx/sfrz/scbz_sg.jpg"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 70%; top: 63%;font-size: 14px;" >闪光</div>
                        </div>
                        <div style="margin-top: 10px;border: 1px solid #FFFFFF;height:35px;line-height:35px; overflow:hidden;font-size: 15px;width: 150px;position: absolute;left: 28%; top: 74%;" v-on:click="hidegf">我&nbsp;知&nbsp;道&nbsp;了</div>
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
        /*components: {
            'remote-css': {
                render(createElement) {
                    return createElement('link', {attrs: {rel: 'stylesheet', href: this.href}});
                },
                props: {
                    href: {type: String, required: true},
                },
            },
            'remote-js': {
                render(createElement) {
                    return createElement('script', {attrs: {type: 'text/javascript', src: this.src}});
                },
                props: {
                    src: {type: String, required: true},
                },
            },
        },*/
        name: "smrzinfo",
        data() {
            return {
                active: 0,
                username: '',
                sfzmhm: '',
                lxdh: '',
                show: false,
                info: {},
                imageszm: '/static/wx/sfzzm.jpg',
                imagesfm: '/static/wx/sfzmfm.jpg',
                clientHeight:document.documentElement.clientHeight,
                showBtn: true,  // 控制按钮盒子显示隐藏
            };
        },
        mounted() {
            window.onresize= ()=>{
                if(this.clientHeight>document.documentElement.clientHeight) {
                    this.showBtn =false
                }else{
                    this.showBtn = true
                }
            }
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
                if(_this.imageszm === '/static/wx/sfzzm.jpg'){
                    Notify({type: 'danger', message: '请上传身份证正面'});
                    return false;
                }else if(_this.imagesfm === '/static/wx/sfzmfm.jpg'){
                    Notify({type: 'danger', message: '请上传身份证反面'});
                    return false;
                }
                if(_this.lxdh.length != 11){
                    Notify({type: 'danger', message: '请填写正确的手机号码'});
                    return false;
                }
                Toast.loading({
                    duration: 0,
                    message: '加载中...',
                    forbidClick: true,
                });
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/sfrz/saveImage', {
                    sfzmhm: _this.sfzmhm,
                    lxdh: _this.lxdh,
                    base64imagesfm: _this.imagesfm,
                }).then((response) => {
                    Toast.clear();
                    let resp = response.data;
                    if(resp.success){
                        _this.$router.push({ path: "/smrzbrinfo",
                            query: {
                                sfzmhm:_this.sfzmhm,
                                tp2:_this.info.tp2,
                            }
                        });
                    }else{
                        Dialog({ message: resp.message });
                    }
                })
            },
            changeImagezm(e) {
                let file = e.target.files[0];
                if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
                    Notify({type: 'danger', message: '请上传jpg或png格式图片'});
                    return false;
                }
                let reader = new FileReader();
                let _this = this;
                reader.readAsDataURL(file);
                reader.onload = function () {
                    _this.imageszm = this.result;
                    Toast.loading({
                        duration: 0,
                        message: '加载中...',
                        forbidClick: true,
                    });
                    _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/sfrz/identpic', {
                        openid: Tool.getWxOpendid(),
                        base64imageszm: _this.imageszm,
                    }).then((response) => {
                        Toast.clear();
                        let resp = response.data;
                        if(resp.success){
                            _this.info = resp.content;
                            _this.username = _this.info.zjmc;
                            _this.sfzmhm = _this.info.zjhm;
                        }else{
                            Dialog({ message: resp.message });
                        }
                    })
                };
            },
            changeImagefm(e) {
                let file = e.target.files[0];
                if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
                    Notify({type: 'danger', message: '请上传jpg或png格式图片'});
                    return false;
                }
                let reader = new FileReader();
                let _this = this;
                reader.readAsDataURL(file);
                reader.onload = function () {
                    _this.imagesfm = this.result;
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
        position:fixed;
        bottom: 10px;
        left: 5%;
    }

</style>