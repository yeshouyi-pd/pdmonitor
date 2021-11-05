<template>
  <div >
    <button type="button" v-on:click="showupload" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
      <i class="ace-icon fa fa-upload"></i>
      上传
    </button>

    <div id="updoad-modal" class="modal fade" tabindex="-1"  style="z-index: 1060"  role="dialog">
      <div class="modal-dialog" style="width: 680px;height: auto" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">文件上传</h4>
          </div>
          <div class="modal-body">
            <!--========================================  -->
            <div class="hello">
              <div class="upload">
                <div class="upload_warp">
                  <div class="upload_warp_left" @click="fileClick">
                    <img src="/static/image/upload/upload.png">
                  </div>
                  <div class="upload_warp_right" @drop="drop($event)" @dragenter="dragenter($event)" @dragover="dragover($event)">
                    或者将文件拖到此处
                  </div>
                </div>
                <div class="upload_warp_text">
                  选中{{imgList.length}}张文件，共{{bytesToSize(this.size)}}
                </div>
                <input @change="fileChange($event)" type="file" id="upload_file" multiple style="display: none"/>
                <div class="upload_warp_img" v-show="imgList.length!=0">
                  <div class="upload_warp_img_div" v-for="(item,index) of imgList">
                    <div class="upload_warp_img_div_top">
                      <div class="upload_warp_img_div_text">
                        {{item.file.name}}
                      </div>
                      <img src="/static/image/upload/del.png" class="upload_warp_img_div_del" @click="fileDel(index)">
                    </div>
                    <img :src="item.file.src">
                  </div>
                </div>
                <div class="upload_warp_text" style="text-align: center">
                  <button type="button"   v-show="show1" v-on:click="upload" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-upload"></i>
                    上传
                  </button>
                  <button type="button"  v-show="show2" disabled="disabled" v-on:click="upload" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-upload"></i>
                    上传
                  </button>
                  <button type="button"   v-on:click="refresh" class="btn btn-sm btn-success btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-refresh"></i>
                    清空
                  </button>
                </div>
              </div>
            </div>
            <!--========================================  -->
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-sm btn-danger btn-round" data-dismiss="modal">
              <i class="ace-icon fa fa-times"></i>
              关闭
            </button>


          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>
</template>

<script>
    export default {
      name: 'uploads',
      props: {
        suffixs: {
          default: []
        },
        use: {
          default: ""
        },
        mainid: {
          default: ""
        },
      },
        data: function () {
          return {
            imgList: [],
            size: 0,
            thisdo:0,
            show1:true,
            show2:false
          }
        },
        methods: {
          showupload(){
            let  _this = this;
            $("#updoad-modal").modal("show");
            _this.refresh();
          },
          refresh(){
            let  _this = this;
            _this.imgList = [];
            _this.size= 0;
            _this.thisdo=0;
            _this.show1=true;
            _this.show2=false;
            this.$forceUpdate();

          },
          upload(){
            let _this = this;
            if(Tool.isEmpty(_this.imgList)){
              Toast.warning("请选择要上传文件！")
              return;
            }
             _this.show1=false;
             _this.show2=true;
            _this.uploadFile(this.imgList[_this.thisdo].file);//初始传第一张

          },
          //=============================================================================
          uploadFile (file) {
            let _this = this;
            console.log(JSON.stringify(file));
            // 生成文件标识，标识多次上传的是不是同一个文件
            let key = hex_md5(file.name + file.size + file.type);
            let key10 = parseInt(key, 16);
            let key62 = Tool._10to62(key10);
            // 判断文件格式
            let suffixs = _this.suffixs;
            let fileName = file.name;
            let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();

            if(file.size > 5*1024*1024*1024){
              Toast.warning("单次文件上传不能超过5G");
              return;

            }

            // 文件分片
            let shardSize = 20 * 1024 * 1024;    //以10MB为一个分片
            let shardIndex = 1;		//分片索引，1表示第1个分片
            let size = file.size;
            let shardTotal = Math.ceil(size / shardSize); //总片数

            let param = {
              'shardIndex': shardIndex,
              'shardSize': shardSize,
              'shardTotal': shardTotal,
              'use': _this.use,
              'name': file.name,
              'suffix': suffix,
              'size': file.size,
              'key': key62,
              'mianid':_this.mainid,

            };

            _this.check(param,file);
          },

          /**
           * 检查文件状态，是否已上传过？传到第几个分片？
           */
          check (param,file) {
            let _this = this;
            _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/uploadfile/check/' + param.key).then((response)=>{
              let resp = response.data;
              if (resp.success) {
                let obj = resp.content;
                if (!obj) {
                  param.shardIndex = 1;
                  console.log("没有找到文件记录，从分片1开始上传");
                  _this.uploads(param,file);
                } else if (obj.shardIndex === obj.shardTotal) {
                  // 已上传分片 = 分片总数，说明已全部上传完，上传下一个
                  _this.imgList[_this.thisdo].file.src='/static/image/upload/sg.png';
                  this.$forceUpdate();
                  if(_this.thisdo <  _this.imgList.length -1){
                    _this.thisdo = _this.thisdo +1;
                    _this.upload();
                  }else{
                    _this.show1=true;
                    _this.show2=false;
                    _this.thisdo = 0;
                    Toast.success("上传完成！")
                  }
                }  else {
                  param.shardIndex = obj.shardIndex + 1;
                  console.log("找到文件记录，从分片" + param.shardIndex + "开始上传");
                  _this.uploads(param,file);
                }
              } else {
                Toast.warning("文件上传失败");
              }
            })
          },

          /**
           * 将分片数据转成base64进行上传
           */
          uploads (param,file) {
            let _this = this;
            let shardIndex = param.shardIndex;
            let shardTotal = param.shardTotal;
            let shardSize = param.shardSize;
            let fileShard = _this.getFileShard(shardIndex, shardSize,file);
            Progress.show(parseInt((shardIndex - 1) * 100 / shardTotal),parseInt(_this.thisdo+1));
            let formData = new window.FormData();
            formData.append('shard', fileShard);
            formData.append('shardIndex',  param.shardIndex);
            formData.append('shardSize', param.shardSize);
            formData.append('shardTotal', param.shardTotal);
            formData.append('use', param.use);
            formData.append('name', param.name);
            formData.append('suffix', param.suffix);
            formData.append('size', param.size);
            formData.append('key', param.key);
            formData.append('mianid', param.mianid);
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/uploadfile/uploadbig', formData).then((response) => {
              let resp = response.data;
              console.log("上传文件成功：", resp);
              Progress.show(parseInt((shardIndex - 1) * 100 / shardTotal),parseInt(_this.thisdo+1));
              if (shardIndex < shardTotal) {
                // 上传下一个分片
                param.shardIndex = param.shardIndex + 1;
                _this.uploads(param,file);
              } else {

                _this.imgList[_this.thisdo].file.src='/static/image/upload/sg.png';
                this.$forceUpdate();
                //传完后继续上传下一个
                Progress.hide();
                if(_this.thisdo <  _this.imgList.length -1){
                  _this.thisdo = _this.thisdo +1;
                 _this.upload();
                }else{
                  _this.show1=true;
                  _this.show2=false;
                  _this.thisdo = 0;
                  Toast.success("上传完成！")
                }
              }
            });

          },

          getFileShard (shardIndex, shardSize,file) {
            let _this = this;
            let start = (shardIndex - 1) * shardSize;	//当前分片起始位置
            let end = Math.min(file.size, start + shardSize); //当前分片结束位置
            let fileShard = file.slice(start, end); //从文件中截取当前的分片数据
            return fileShard;
          },


          //=============================================================================
          fileClick() {
            document.getElementById('upload_file').click()
          },
          fileChange(el) {
            if (!el.target.files[0].size) return;
            this.fileList(el.target);
            el.target.value = ''
          },
          fileList(fileList) {
            let files = fileList.files;
            for (let i = 0; i < files.length; i++) {
              //判断是否为文件夹
              if (files[i].type != '') {
                this.fileAdd(files[i]);
              } else {
                //文件夹处理
                this.folders(fileList.items[i]);
              }
            }
          },
          //文件夹处理
          folders(files) {
            let _this = this;
            //判断是否为原生file
            if (files.kind) {
              files = files.webkitGetAsEntry();
            }
            files.createReader().readEntries(function (file) {
              for (let i = 0; i < file.length; i++) {
                if (file[i].isFile) {
                  _this.foldersAdd(file[i]);
                } else {
                  _this.folders(file[i]);
                }
              }
            })
          },
          foldersAdd(entry) {
            let _this = this;
            entry.file(function (file) {
              _this.fileAdd(file)
            })
          },
          fileAdd(file) {
            //总大小
            this.size = this.size + file.size;
            //判断是否为图片文件
            let suffix =  file.name.substring( file.name.lastIndexOf(".") + 1,  file.name.length).toLowerCase();
            if (suffix == 'mp4' || suffix=='wav' ) {
              file.src = '/static/image/upload/wenjian.png';
              this.imgList.push({
                file
              });
            }
          },
          fileDel(index) {
            this.size = this.size - this.imgList[index].file.size;//总大小
            this.imgList.splice(index, 1);
          },
          bytesToSize(bytes) {
            if (bytes === 0) return '0 B';
            let k = 1000, // or 1024
                sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
                i = Math.floor(Math.log(bytes) / Math.log(k));
            return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
          },
          dragenter(el) {
            el.stopPropagation();
            el.preventDefault();
          },
          dragover(el) {
            el.stopPropagation();
            el.preventDefault();
          },
          drop(el) {
            el.stopPropagation();
            el.preventDefault();
            this.fileList(el.dataTransfer);
          }

        }

    }
</script>

<style scoped>
.upload_warp_img_div_del {
  position: absolute;
  top: 6px;
  width: 16px;
  right: 4px;
}

.upload_warp_img_div_top {
  position: absolute;
  top: 0;
  width: 100%;
  height: 30px;
  background-color: rgba(0, 0, 0, 0.4);
  line-height: 30px;
  text-align: left;
  color: #fff;
  font-size: 12px;
  text-indent: 4px;
}

.upload_warp_img_div_text {
  white-space: nowrap;
  width: 80%;
  overflow: hidden;
  text-overflow: ellipsis;
}

.upload_warp_img_div img {
  max-width: 100%;
  max-height: 100%;
  vertical-align: middle;
}

.upload_warp_img_div {
  position: relative;
  height: 100px;
  width: 120px;
  border: 1px solid #ccc;
  margin: 0px 30px 10px 0px;
  float: left;
  line-height: 100px;
  display: table-cell;
  text-align: center;
  background-color: #eee;
  cursor: pointer;
}

.upload_warp_img {
  border-top: 1px solid #D2D2D2;
  padding: 14px 0 0 14px;
  overflow: hidden
}

.upload_warp_text {
  text-align: left;
  margin-bottom: 10px;
  padding-top: 10px;
  text-indent: 14px;
  border-top: 1px solid #ccc;
  font-size: 14px;
}

.upload_warp_right {
  float: left;
  width: 57%;
  margin-left: 2%;
  height: 100%;
  border: 1px dashed #999;
  border-radius: 4px;
  line-height: 130px;
  color: #999;
}

.upload_warp_left img {
  margin-top: 32px;
}

.upload_warp_left {
  float: left;
  width: 40%;
  height: 100%;
  border: 1px dashed #999;
  border-radius: 4px;
  cursor: pointer;
}

.upload_warp {
  margin: 14px;
  height: 130px;
}

.upload {
  border: 1px solid #ccc;
  background-color: #fff;
  width: 650px;
  box-shadow: 0px 1px 0px #ccc;
  border-radius: 4px;
}

.hello {
  width: 650px;
  text-align: center;
}
</style>
