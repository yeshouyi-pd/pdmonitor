<template>
    <div>
        <div class="ksxx"><span style="font-size: 17px;font-weight:bold;margin-left: 10px;">上传资料</span><span v-on:click="showgf" style="float: right;font-size: 15px;margin-right: 10px;color: #8ECFE0;">拍摄样照</span></div>
        <van-form validate-first @submit="onSubmit">
            <ul>
                <li v-for="(item, index) in barlist" :key="index">
                    <div style="margin:10px 0px 0px 10px;text-align: center;float: left;" @change="changeImage($event,index)">
                        <van-uploader multiple :accept="'image/*'" :preview-size="30" :deletable="false" :after-read="afterRead">
                            <img :id="'A'+index" height="100px" width="100px" src="/static/wx/sctp.png" class="img-avatar" />
                        </van-uploader>
                        <div style="font-size: 12px;">{{item}}</div>
                    </div>
                </li>
            </ul>
            <div style="clear: both;"></div>
            <div style="width: 90%; margin:20px auto;color: red;line-height: 20px;font-size: 14px;">
                <p>
                    温馨提示：<br/>
                    1、请您在确保自身安全的情况下进行拍照取证；<br/>
                    2、事故照片拍照注意清晰度、事故信息，便于交警快速划分责任。<br/>
                </p>
            </div>
            <div class="button_db">
                <van-button round block type="info" native-type="submit">
                    拍照上传
                </van-button>
            </div>
            <van-overlay :show="show" @click="show = false">
                <div class="wrapper" @click.stop>
                    <div style="color: #FFFFFF;text-align: center;border: 0px solid red;">
                        <p style="font-size: 15px;">拍&nbsp;摄&nbsp;规&nbsp;范</p>
                        <div style="margin-top: 10px;">
                            <div style="position: absolute; left: 25%; top: 13%;"><van-image width="80%" height="80%" src="/static/wx/sfrz/true.png"/></div>&nbsp;&nbsp;
                            <van-image width="30%" height="30%" src="/static/wx/sfrz/scbz_xq.jpg"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 70%; top: 13%;font-size: 14px;" >正常</div>
                        </div>
                        <div style="margin-top: 10px;">
                            <div style="position: absolute; left: 25%; top: 26%;"><van-image width="80%" height="80%" src="/static/wx/sfrz/false.png"/></div>&nbsp;&nbsp;
                            <van-image width="30%" height="30%" src="/static/wx/sfrz/scbz_qbj.jpg"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 70%; top: 26%;font-size: 14px;" >缺边角</div>
                        </div>
                        <div style="margin-top: 10px;">
                            <div style="position: absolute; left: 25%; top: 40%;"><van-image width="80%" height="80%" src="/static/wx/sfrz/false.png"/></div>&nbsp;&nbsp;
                            <van-image width="30%" height="30%" src="/static/wx/sfrz/scbz_mh.jpg"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 70%; top: 40%;font-size: 14px;" >模糊</div>
                        </div>
                        <div style="margin-top: 10px;">
                            <div style="position: absolute; left: 25%; top: 54%;"><van-image width="80%" height="80%" src="/static/wx/sfrz/false.png"/></div>&nbsp;&nbsp;
                            <van-image width="30%" height="30%" src="/static/wx/sfrz/scbz_sg.jpg"/>&nbsp;&nbsp;
                            <div style="position: absolute;left: 70%; top: 54%;font-size: 14px;" >闪光</div>
                        </div>
                        <div style="margin-top: 10px;border: 1px solid #FFFFFF;height:35px;line-height:35px; overflow:hidden;font-size: 15px;width: 150px;position: absolute;left: 28%; top: 74%;" v-on:click="hidegf">我&nbsp;知&nbsp;道&nbsp;了</div>
                    </div>
                </div>
            </van-overlay>
        </van-form>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    import Notify from "vant/lib/notify";
    import Toast from "vant/lib/toast";
    import Compressor from 'compressorjs';

    export default {
        name: "jtsgsbpic",
        data: function () {
            return {
                info: {},
                barlist: [],
                barimgs: [],
                show: false,
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getJtsgsbpic();
            _this.info.sgbh = _this.$route.query.sgbh;//路由传参
            _this.info.lsh = _this.$route.query.lsh;
            _this.info.dsrxm1 = _this.$route.query.dsrxm1;
            _this.info.dsrsjhm1 = _this.$route.query.dsrsjhm1;
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
                if(_this.barimgs.length < 5){
                    Notify({type: 'danger', message: '请上传全部的资料照片'});
                    return false;
                }
                _this.$router.push({path:'/jtsgsbjg', query:{sgbh: _this.info.sgbh,lsh: _this.info.lsh
                    ,dsrxm1: _this.info.dsrxm1,dsrsjhm1: _this.info.dsrsjhm1}});
                /*_this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehAccident/wxsave',
                    formData
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        Toast.clear();
                        _this.info = resp.content;
                        _this.$router.push({path:'/jtsgsbjg',
                            query:{sgbh: _this.info.sgbh,lsh: _this.info.lsh}});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })*/
            },
            changeImage(e,index) {
                let file = e.target.files[0];
                if(file.size > 10 * 1024 * 1024){
                    Notify({type: 'danger', message: '文件大小不能超过 10M'});
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
                    $("#A"+index).attr('src',this.result);
                    _this.barimgs.push(index + '_' + this.result.split(",")[1]);
                };
                new Compressor(file, {
                    quality: 0.5,
                    success(result) {
                        _this.uploadFile(file,_this.info.lsh,index,"3");
                    },error(error) {
                        Toast('压缩失败');
                    }
                });
            },
            uploadFile(file,lsh,zplx,pd){
                let _this = this;
                let formData = new FormData();
                formData.append("file", file);
                formData.append("lsh", lsh);
                formData.append("zplx", zplx);
                formData.append("pd", pd);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/appupload/uploadpic',
                    formData
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        Toast('上传成功');
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
            /**
             * 获取交通事故图片信息
             */
            getJtsgsbpic(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getJtsgsbpic', {
                }).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.barlist = resp.content;
                    } else {
                        Dialog({message: resp.message});
                    }
                })
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
    .ksxx {
        width: 100%;
        background-color: #F2F2F2;
        height: 40px;
        line-height: 40px;
    }
    .button_db {
        width: 90%;
        position:fixed;
        bottom: 10px;
        left: 5%;
    }
</style>