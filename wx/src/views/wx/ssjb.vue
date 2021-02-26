<template>
    <div>
        <div class="upLoad">
            <label for="pop_video" >
                <video id="videoId" controls width="100%" height="50%"></video>
                <input style="display:none;" id="pop_video" type="file" accept="video/*" capture="camcorder" v-on:change="getVideo()" name="fileTrans" ref="file" value="">
                <div class="inputVideo">上传视频</div>
            </label>
        </div>
    </div>
</template>
<script>
    import Dialog from "vant/lib/dialog";
    import Toast from "vant/lib/toast";
    export default {
        name: "ssjb",
        data: function () {
            return {

            }
        },
        mounted: function () {//mounted初始化方法

        },
        methods: {
            getVideo() {
                let _this = this
                //获取上传文件标签
                let filesId = document.getElementById('pop_video');
                //获取音频标签
                let videoId = document.getElementById('videoId')
                //把当前files[0]传给getFileURL方法， getFileURL方法对其做一处理
                let url = this.getFileURL(filesId.files[0])
                if (url) {
                    //给video标签设置src
                    videoId.src = url
                }
                //_this.uploadFile(filesId.files[0], "123456789");
                console.log("file", filesId.files[0]);
            },
            uploadFile(file,lsh){
                let _this = this;
                let formData = new FormData();
                formData.append("file", file);
                formData.append("lsh", lsh);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/appupload/upload',
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
            getFileURL(file) {
                let getUrl = null
                if (window.createObjectURL != undefined) {
                    //basic
                    getUrl = window.createObjectURL(file)
                } else if (window.URL != undefined) {//window.URL 标准定义
                    //mozilla(firefox)
                    //获取一个http格式的url路径，这个时候就可以设置<img>中的显示
                    getUrl = window.URL.createObjectURL(file)
                } else if (window.webkitURL != undefined) {//window.webkitURL是webkit的内核
                    //webkit or chrome
                    getUrl = window.webkitURL.createObjectURL(file)
                }
                return getUrl
                //video 标签的 duration属性，获取当前视频的长度
                // let duration = videoId.duration
                // if (Math.floor(duration) > 60) {
                // that.layer.msg('视频不能大于60秒')
                // }
            },
        }
    }
</script>

<style scoped>
    .upLoad {
        background-color:#fff;
        height: 25rem;
        text-align: left;
        padding: 0.3rem;
    }
    .inputVideo {
        background-color: #00cc66;
        color: #fff;
        font-size: 0.32rem;
        width: 30%;
        height: 2rem;
        line-height: 2rem;
        border-radius: 0.4rem;
        text-align: center;
        margin: 0 auto;
    }

    .inputAudio {
        background-color: #5cadff;
        color: #fff;
        font-size: 0.32rem;
        width: 30%;
        height: 2rem;
        line-height: 2rem;
        border-radius: 0.4rem;
        text-align: center;
        margin: 0 auto;
    }

</style>