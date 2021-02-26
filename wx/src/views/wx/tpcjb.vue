<template>
    <div>
        <van-form @submit="onSubmit">
            <div class="ksxx">报案人资料</div>
                <ul>
                    <li v-for="(item, index) in barlist" :key="index">
                        <div style="margin:10px 0px 0px 10px;text-align: center;float: left;" @change="changeImageA($event,index)">
                            <van-uploader multiple :accept="'image/*'" :preview-size="30" :deletable="false" :after-read="afterRead">
                                <img :id="'A'+index" height="100px" width="100px" src="/static/wx/sctp.png" class="img-avatar" />
                            </van-uploader>
                            <div style="font-size: 12px;">{{item}}</div>
                        </div>
                    </li>
                </ul>
            <div class="ksxx">车辆资料</div>
            <ul>
                <li v-for="(item, index) in cllist" :key="index">
                    <div style="margin:10px 0px 0px 10px;text-align: center;float: left;" @change="changeImageB($event,index)">
                        <van-uploader multiple :accept="'image/*'" :preview-size="30" :deletable="false" :after-read="afterRead">
                            <img :id="'B'+index" height="100px" width="100px" src="/static/wx/sctp.png" class="img-avatar" />
                        </van-uploader>
                        <div style="font-size: 12px;">{{item}}</div>
                    </div>
                </li>
            </ul>
            <div style="clear: both;"></div>
            <div class="button_db">
                <van-button round block type="info" native-type="submit">
                    确认上报
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    import Notify from "vant/lib/notify";
    import Toast from "vant/lib/toast";
    import Compressor from 'compressorjs';

    export default {
        name: "tpcjb",
        data: function () {
            return {
                id: '',
                lsh: '',
                wxUser:{},
                barlist: [],
                cllist: [],
                barimgs: [],
                climgs: [],
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.id = _this.$route.query.id;
            _this.lsh = _this.$route.query.lsh;
            _this.wxUser = Tool.getWxUser();
            _this.getTbcsbBar();
            _this.getTbcsbCl();
        },
        methods: {
            afterRead(file) {
                console.log(file);
            },
            // 返回布尔值
            beforeRead(file) {
                return true;
            },
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                if(_this.barimgs.length == 0){
                    Notify({type: 'danger', message: '请上传报案人资料照片'});
                    return false;
                }
                if(_this.climgs.length == 0){
                    Notify({type: 'danger', message: '请上传车辆资料照片'});
                    return false;
                }
                /*let formData = new FormData();
                formData.append("id", _this.id);
                formData.append("barlist", _this.barimgs);
                formData.append("cllist", _this.climgs);
                Toast.loading({
                    duration: 0,
                    message: '加载中...',
                    forbidClick: true,
                });*/
                _this.$router.push({path:'/cgym'});
                /*_this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehRobbery/wxsavepic',
                    formData
                ).then((response)=>{
                    Toast.clear();
                    let resp = response.data;
                    if (resp.success) {
                        _this.$router.push({path:'/cgym'});
                    } else {
                        Dialog({ message: resp.message });
                    }
                })*/
            },
            changeImageA(e,index) {
                let file = e.target.files[0];
                if(file.size > 10 * 1024 * 1024){
                    Notify({type: 'danger', message: '文件大小不能超过 10M'});
                    return false;
                }
                if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
                    Notify({type: 'danger', message: '请上传jpg或png格式图片'});
                    return false;
                }
                let _this = this;
                let reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    $("#A"+index).attr('src',this.result);
                    _this.barimgs.push(index +'_'+ this.result.split(",")[1]);
                };
                new Compressor(file, {
                    quality: 0.5,
                    success(result) {
                        _this.uploadFile(result,_this.lsh,index,"1");
                    },error(error) {
                        Toast('压缩失败');
                    }
                });
            },
            changeImageB(e,index) {
                let file = e.target.files[0];
                if(file.size > 10 * 1024 * 1024){
                    Notify({type: 'danger', message: '文件大小不能超过 10M'});
                    return false;
                }
                if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
                    Notify({type: 'danger', message: '请上传jpg或png格式图片'});
                    return false;
                }
                let _this = this;
                let reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    $("#B"+index).attr('src',this.result);
                    _this.climgs.push(index + '_' + this.result.split(",")[1]);
                };
                new Compressor(file, {
                    quality: 0.5,
                    success(result) {
                        _this.uploadFile(file,_this.lsh,index,"2");
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
             * 获取套牌车报案人资料图片信息
             */
            getTbcsbBar(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getTbcsbBar', {
                }).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.barlist = resp.content;
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
            /**
             * 获取套牌车车辆资料图片信息
             */
            getTbcsbCl(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getTbcsbCl', {
                }).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.cllist = resp.content;
                    } else {
                        Dialog({message: resp.message});
                    }
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
        clear: both;
    }
    .button_db {
        width: 90%;
        margin: 10px 0 10px 5%;
    }
</style>