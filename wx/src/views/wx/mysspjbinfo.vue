<template xmlns:c="http://www.w3.org/1999/html">
  <div>
    <van-form>
      <van-cell title="随手拍违法类型">{{wflbs|optionMapKV(info.wflb)}}</van-cell>
      <van-cell title="违法路段">{{info.wfld}}</van-cell>
      <van-cell title="上报状态">{{info.zt}}</van-cell>
      <van-cell title="上报时间">{{info.createTime | formatDateTime}}</van-cell>
      <!--<van-field v-model="info.clyj" rows="2" autosize label="处理意见" type="textarea"/>-->
      <van-cell title="上报图片"></van-cell>
      <img v-for="(item,index) in piclist" style="margin-left: 10px;" :key="index" width="100" height="100" :src="path+item.zplj" @click="HandleclickImg(index)"/>
      <div class="button_db">
        <van-button to="/index" round block type="info" native-type="button">
          返回主页
        </van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import Dialog from "vant/lib/dialog";
import ImagePreview from "vant/lib/image-preview";

export default {
  name: "mysspjbinfo",
  data() {
    return {
      info: {},
      wflbs : [],
      columnsclyj : [
        { '1': '已上报'},
        { '2': '已处理'},
        { '3': '虚假上报'},
      ],
      path: '',
      piclist: [],
      images: [],
    };
  },
  mounted() {
    let _this = this;
    _this.id = _this.$route.query.id;
    _this.getWflb();
    _this.selectinfo();
  },
  methods: {
    selectinfo(){
      let _this = this;
      let formData = new FormData();
      formData.append("id", _this.id)
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatIllegalSave/selectinfo',
          formData
      ).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          _this.info = resp.content.info;
          _this.piclist = resp.content.piclist;
          _this.path = process.env.VUE_APP_SERVER;

          for(let i in _this.piclist){
            _this.images.push(process.env.VUE_APP_SERVER +_this.piclist[i].zplj);
          }
          if(_this.info.zt == '1'){
            _this.info.zt = '已上报'
          }else if(_this.info.zt == '2'){
            _this.info.zt = '审核通过'
          }else if(_this.info.zt == '3'){
            _this.info.zt = '审核不通过'
          }
        } else {
          Dialog({ message: resp.message });
        }
      })
    },
    HandleclickImg(index) {
      let _this = this;
      ImagePreview({
        images: _this.images,
        startPosition: index,
      });
    },
    /**
     * 获取违法类别
     */
    getWflb(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWflb', {
      }).then((response)=>{
        let resp = response.data;
        _this.wflbs = resp.content;
      })
    },
  },
}
</script>

<style scoped>
.button_db {
  width: 95%;
  position:fixed;
  bottom: 10px;
  left: 2.5%;
}
</style>