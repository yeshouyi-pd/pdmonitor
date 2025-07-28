<template>
    <div style="margin-top: 20px">
        <button type="button" v-on:click="selectFile()" class="btn btn-sm   btn-success btn-round">
            <i class="ace-icon fa fa-upload"></i>
            {{text}}
        </button>
        <input class="hidden" type="file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
    </div>
</template>

<script>
    export default {
        name: 'file',
        props: {
            text: {
                default: "上传文件"
            },
            inputId: {
                default: "file-upload"
            },
            suffixs: {
                default: []
            },
            use: {
                default: "pic"
            },
            afterUpload: {
                type: Function,
                default: null
            },
        },
        data: function () {
            return {
            }
        },
        methods: {
            uploadFile () {
                let _this = this;
                //key :file 必须前后端参数名一致
                let formData = new window.FormData();
                let file = _this.$refs.file.files[0];
                //_this.$refs.file.files[0] ==document.querySelector('#file-upload-input').files[0])

                // 判断文件格式
                let suffixs = _this.suffixs;
                let fileName = file.name;
                let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
                let validateSuffix = false;
                for (let i = 0; i < suffixs.length; i++) {
                    if (suffixs[i].toLowerCase() === suffix) {
                        validateSuffix = true;
                        break;
                    }
                }
                if (!validateSuffix) {
                    Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
                    $("#" + _this.inputId + "-input").val("");
                    return;
                }

                // key："file"必须和后端controller参数名一致
                formData.append('file', file);
                formData.append('use', _this.use);
                console.log("==========="+_this.use);
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/uploadfile/upload', formData).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    if(resp.success){
                        _this.afterUpload(resp);
                    }else {
                        Toast.warning("文件长度太大");
                    }

                    console.log("上传文件成功：", resp);

                    $("#" + _this.inputId + "-input").val("");
                });
            },

            selectFile () {
                let _this = this;
                $("#" + _this.inputId + "-input").trigger("click");
            }
        }
    }
</script>
