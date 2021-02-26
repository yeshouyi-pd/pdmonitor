<template>
    <div>
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
                <img src="/static/wx/txz/txzindex.jpg" style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>

      <div style="padding-top: 20px">
        <template v-for="(pics ,index) in fileLists">
          <div  v-on:click="getindex(index)">
            <van-uploader
                v-model="fileLists[index]"
                :key="index"
                :upload-text="fileList[index].code.name"
                :max-size="1024 * 1024 *10"
                @oversize="onOversize"
                :name="fileList[index].code.code"
                :max-count="1"

                :after-read="afterRead"/>

            <div style="text-align: center; padding-bottom: 4px;">
              <van-tag :key="index+'tag'"  plain  type="success">{{fileList[index].code.name}}</van-tag>
            </div>
          </div>
        </template>

      </div>
      <van-row style="margin-bottom: 10px;margin-top: 10px">
        <van-col span="12">
          <router-link to="/txz/txzinfo">
          <button
              class="van-button van-button--warning van-button--large van-goods-action-button van-goods-action-button--first van-goods-action-button--warning"
              style="background: linear-gradient(to right,	 #7FFFAA,#1E90FF);
                               box-shadow: 2px 2px 10px #00FFFF;"
          >返回</button>
          </router-link>
        </van-col>
        <van-col  span="12">
          <button
              class="van-button van-button--danger van-button--large van-goods-action-button van-goods-action-button--last van-goods-action-button--danger"
              style="background: linear-gradient(to right, #1E90FF,#7FFFAA);
                               box-shadow: 2px 2px 10px #00FFFF;"
              v-on:click="savetxz"

          >提交</button>
        </van-col>
      </van-row>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    import Toast from "vant/lib/toast";
    import Compressor from 'compressorjs';
    export default {
        name:'txzpic',
        data:function(){
            return{
              pass:{},
              fileList: [],//所有数据
              fileLists: [],
              index:'',//图片上传选择的下标

            }
        },
        mounted:function(){//mounted初始化方法
          let _this = this;
          let pass =  SessionStorage.get(LIKN_TO_TXZ)|| {} ;
          if(Tool.isEmpty(pass)){
             Dialog({ message: "操作异常，请重试" });
            _this.$router.push("/index");
          }

          _this.pass=pass;
          _this.getpic();
          //获取需要拍照的类型1

        },
        methods: {
          getindex(index){
            let _this =this;
            _this.index = index;
          },
          /**
           * 通行证保存
           */
          savetxz(){
            let _this =this;
            console.log(_this.fileLists)
            let size = _this.fileList.length;
            let thissize = 0;
            for(let i = 0; i < _this.fileLists.length; i++){
              if(Tool.isNotEmpty(_this.fileLists[i])){
                thissize = thissize +1;
              }
            }
            if(thissize !== size){
              Dialog({ message: "请上传相关资料！" });
              return;
            }
            //保存信息
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/lstxz/savetxz',   _this.pass).then((response)=>{
              let resp = response.data;
              if (resp.success) {
                SessionStorage.remove(LIKN_TO_TXZ) ;//移除缓存;
                _this.$router.push("/txz/txzssuccess");
              }else{
                Dialog({ message: "操作失败，请重试！" });
              }
            })


          },
          /**
           * 获取上传图片
           */
          getpic(){
            let _this = this;
            _this.$ajax.get(process.env.VUE_APP_SERVER +'/wxbase/wx/lstxz/gettxzdpzl').then((response)=>{
              let resp = response.data;
              _this.fileList=resp.content;
              for(let i =0; i< resp.content.length; i++){
                _this.fileLists.push(resp.content[i].list || []);
              }
            })

          },
          /**
           * 文件上传限制
           * @param file
           */
          onOversize(file) {
              Toast('文件大小不能超过 10M');
          },
          /**
           * 文件上传处理
           * @param file
           */
          afterRead(file,name) {
            let _this =this;
            file.status = 'uploading';
            file.message = '图片处理中..';
            new Compressor(file.file, {
              quality: 0.5,//压缩质量Compressor
              success(result) {
                let formData = new window.FormData();
                formData.append('file', result);
                formData.append('zpzl', name.name);//子类
                formData.append('zplx', '22');//大类
                formData.append('mid', _this.pass.id);//父ID
                formData.append('use', 'txz');//类型
                file.status = 'uploading';
                file.message = '上传中...';
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/lstxz/uploadpic', formData).then((response)=>{
                  let resp = response.data;
                  if(resp.success){
                    file.status = 'done';
                  }else {
                    file.status = 'failed';
                    file.message = '上传失败';
                    _this.fileLists[_this.index]=[];//失败后清除图片对象

                  }
                });
              },error(error) {
                file.status = 'uploading';
                file.message = '图片处理失败';
              }
            });



          },
        },
    }
</script>

<style scoped>
/deep/.van-uploader__wrapper {
  display: -webkit-box;
  display: -webkit-flex;
  display: flex;
  -webkit-flex-wrap: wrap;
  flex-wrap: wrap;
  width: 80%;
  text-align: center;
  padding-left: 10%;
}
/deep/.van-uploader {
  position: relative;
  display:inline;
  text-align:center;

}

/deep/.van-uploader__upload {
  position: relative;
  display: -webkit-box;
  display: -webkit-flex;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -webkit-flex-direction: column;
  flex-direction: column;
  -webkit-box-align: center;
  -webkit-align-items: center;
  align-items: center;
  -webkit-box-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  box-sizing: border-box;
  width: 100%;
  height:150px;
  background-color: #f7f8fa;
  text-align: center;
  background-image:url('/static/wx/txz/bgimg.jpg');
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
}
/deep/.van-uploader__preview-image {
  display: block;
  width: 100%;
  height: 150px;
  overflow: hidden;
  text-align: center;
}

</style>
