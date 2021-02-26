<template>
  <div>
    <div class="upLoad">
      <!--<span style="font-size: 12px;">上传视频（大小不超过10M）</span>-->
      <label for="pop_video" >
        <video id="videoId" controls width="100%" height="50%"></video>
        <input style="display:none;" id="pop_video" type="file" accept="video/*" capture="camcorder" v-on:change="getVideo()" name="fileTrans" ref="file" value="">
        <div class="inputVideo">上传举报视频</div>
      </label>
    <van-form @submit="onSubmit">
      <van-field v-model="wflb" readonly label="违法类别"/>
      <van-field
          v-model="wfxwms"
          rows="2"
          autosize
          label="违法行为描述"
          type="textarea"
          maxlength="200"
          placeholder="请输入被举报车辆违法行为（选填）"
          show-word-limit
      />
      <!--&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 12px;">上传举报证据</span>-->
      <!--<van-checkbox v-model="czzp" icon-size="13px" style="font-size: 10px;">是否车载照片（勾选/取消勾选将清除已上传的信息）</van-checkbox>-->
      <van-field name="uploader" label="上传举报图片">
        <template #input>
          <van-uploader :after-read="afterRead" :before-read="beforeRead" v-model="images" :max-size="1024 * 1024 * 10" @oversize="onOversize" multiple/>
        </template>
      </van-field>

      <van-field
              v-model="wfsj"
              clearable label="违法时间"
              placeholder="请输入违法时间"
              @focus="start"
      />

      <van-field
          v-model="wfld"
          name="wfld"
          label="违法路段"
          placeholder="请输入违法路段名称"
      />
      <van-field
          readonly
          clickable
          :value="cplxxs"
          label="车牌类型"
          placeholder="车牌类型"
          @click="showPickerhpzl = true"
      />
      <van-popup v-model="showPickerhpzl" position="bottom">
        <van-picker
            show-toolbar
            :columns="columncplx"
            @confirm="onConfirmhpzl"
            @cancel="showPickerhpzl = false"
        />
      </van-popup>
      <van-field
          v-model="cphm"
          name="cphm"
          label="车牌号码"
          placeholder="车牌号码"
      />
      <van-field
          v-model="jbr"
          name="jbr"
          label="举报人"
          placeholder="举报人"
      />

      <van-field
          v-model="sfzmhm"
          name="sfzmhm"
          label="身份证明号码"
          placeholder="身份证明号码"
      />

      <van-field
          v-model="sjh"
          name="sjh"
          label="手机号码"
          placeholder="手机号码"
      />

      <div style="margin-left: 15px;margin-top: 10px;font-size: 14px;">
        <van-checkbox style="float: left;" v-on:change="checkeds" v-model="checked" shape="square">已</van-checkbox>
        阅读<span style="color: #1d6fa6;" v-on:click="sspzysx">《随手怕举报注意事项》</span>
        <span>并自愿承担相关法律责任</span>
      </div>
      <div class="button_db">
        <van-button id="but1" round block type="default" native-type="button">
          提交
        </van-button>
        <van-button id="but2" style="display: none;" round block type="info" native-type="submit">
          提交
        </van-button>
      </div>
    </van-form>
    <van-popup v-model="show" position="bottom">
      <van-datetime-picker
          v-model="currentDate"
          type="datetime"
          :min-date="minDate"
          :max-date="maxDate"
          @confirm="confirm"
          @cancel="cancel"
          :formatter="formatter"
      />
    </van-popup>
    </div>
  </div>
</template>

<script>
import Notify from "vant/lib/notify";
import Dialog from "vant/lib/dialog";
import Toast from "vant/lib/toast";

export default {
  name: "ssjb",
  data() {
    return {
      file:{},
      cphm: '',
      wfsj: '',
      czzp: '',
      wflb:'',
      wflbcode: '',
      wfld: '',
      columncplx: [],
      sfzmhm: '',
      sjh: '',
      show: false, //开始时间弹窗
      minHour: 10,
      maxHour: 20,
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(),
      currentDate: new Date(), //开始标准时间
      currentDate1: new Date(), //结束标准时间
      starttime: "", //开始时间
      cplxxs: '',
      wxUser: {},
      cplx:'',
      wfqy: '',
      dlwz: '',
      wfxwms: '',
      name: '',
      pattern: /^1(3|4|5|6|7|8|9)d{9}$/,
      position: [],
      wfxw: '',
      dfhpzl: '',
      dfhphm: '',
      lxdh: '',
      jbr: '',
      images: [],
      videos: [],
      imageList: [],
      videoList: [],
      checked: '',
      showPickerwfxw: false,
      columnswfxw: ['未按规定停车', '闯红灯'],
      showPickerhpzl: false,
      columnshpzl: ['小型汽车', '大型汽车'],
      showArea: false,
      duration: '',
    };
  },
  mounted() {
    let _this = this;
    _this.wflb = _this.$route.query.name;
    _this.wflbcode = _this.$route.query.code;
    _this.cplx = _this.$route.query.cplx;
    _this.wxUser = Tool.getWxUser();
    _this.jbr = _this.wxUser.zjmc;
    _this.sfzmhm = _this.wxUser.zjhm;
    _this.sjh = _this.wxUser.lxdh;
    _this.getCplx();
    _this.wxConfig();
  },
  methods: {// 选择开始时间
    start() {
      this.show = true;
    },
    // 选择结束时间
    end() {
      this.show1 = true;
    },
    checkeds() {
      let _this = this;
      if(_this.checked){
        $('#but1').hide();
        $('#but2').show();
      }else{
        $('#but1').show();
        $('#but2').hide();
      }
    },
    // 点击确定
    confirm() {
      let month = Number(this.currentDate.getMonth()) + 1;
      let date = this.currentDate.getDate();
      let hour =this.currentDate.getHours();
      let minute = this.currentDate.getMinutes();
      let second =this.currentDate.getSeconds();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (date >= 0 && date <= 9) {
        date = "0" + date;
      }if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
      }if (minute >= 0 && minute <= 9) {
        minute = "0" + minute;
      }if (second >= 0 && second <= 9) {
        second = "0" + second;
      }
      this.show = false;
      this.wfsj =
          this.currentDate.getFullYear() + "-" + (month) + "-" + date + " " + hour + ":" + minute + ":" + second;
    },
    sspzysx(){
      let a = "随手怕举报注意事项,随手怕举报注意事项,随手怕举报注意事项,随手怕举报注意事项";
      Dialog({ message: a });
    },
    /**
     * 获取车牌类型
     */
    getCplx(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxCplx', {
      }).then((response)=>{
        let resp = response.data;
        _this.columncplx = resp.content;
        for(let i in _this.columncplx){
          if(_this.cplx === _this.columncplx[i].code){
            _this.cplxxs = _this.columncplx[i].text;
          }
        }
      })
    },
    wxConfig(){
       let _this = this;
       let formData = new FormData();
       formData.append("url", location.href.split("#")[0]);
       _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wechat/getWxParams',
           formData
       ).then((response)=>{
         let resp = response.data;
         if (resp.success) {
           _this.info = resp.content;
           wx.config({
             debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
             appId: _this.info.appId, // 必填，公众号的唯一标识
             timestamp: _this.info.timestamp, // 必填，生成签名的时间戳
             nonceStr: _this.info.nonceStr, // 必填，生成签名的随机串
             signature:  _this.info.signature,// 必填，签名
             jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表
           });
           _this.getLocation();
         } else {
           Dialog({ message: resp.message });
         }
       })
     },
     getLocation(){
       let _this = this;
       wx.getLocation({
         type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
         success: function (res) {
           let latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
           let longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
           _this.position = new Array();
           _this.position.push(longitude);
           _this.position.push(latitude);
           _this.$nextTick();
           _this.geocoder(longitude, latitude);
         }
       });
     },
    geocoder(longitude,latitude){
      let _this = this;
      let geocoder = new AMap.Geocoder({
        radius: 1000,
        extensions: "all"
      });
      geocoder.getAddress([longitude ,latitude], function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
          if (result && result.regeocode) {
            _this.wfld = result.regeocode.formattedAddress;
            _this.$nextTick();
          }
        }
      });
      AMap.event.addListener(geocoder, 'complete', onComplete);
      AMap.event.addListener(geocoder, 'error', onError);
      function onComplete (data) {
        //console.log(JSON.stringify(data));
      }
      function onError (data) {
        console.log(JSON.stringify(data));
      }
    },
    cancel() {
      this.show = false;
    },
    // 处理控件显示的时间格式
    formatter(type, value) {
      // 格式化选择器日期
      if (type === "year") {
        return `${value}年`;
      } else if (type === "month") {
        return `${value}月`;
      } else if (type === "day") {
        return `${value}日`;
      } else if (type === "hour") {
        return `${value}时`;
      } else if (type === "minute") {
        return `${value}分`;
      }
      return value;
    },
    afterRead(file) {
      let _this = this;
      console.log(file.content.size);
      _this.imageList.push(file.content);
    },
    // 返回布尔值
    beforeRead(file) {
      if (file.type !== 'image/jpeg') {
        Notify({type: 'danger', message: '请上传jpg格式图片'});
        return false;
      }
      return true;
    },
    /**
     * 文件上传限制
     * @param file
     */
    onOversize(file) {
      Toast('文件大小不能超过 10M');
    },
    onSubmit(values) {
      let _this = this;
      if(_this.imageList.length === 0){
        Notify({type: 'danger', message: '请上传图片信息'});
        return false;
      }
      console.log('submit', _this.imageList);
      if(Tool.isEmpty( _this.wfsj)){
        Dialog({ message: "请输入违法时间" });
        return;
      }
      if(Tool.isEmpty( _this.wfld)){
        Dialog({ message: "请输入违法路段名称" });
        return;
      }
      if(Tool.isEmpty( _this.cplxxs)){
        Dialog({ message: "请选择对方车辆号牌种类" });
        return;
      }
      if(Tool.isEmpty( _this.cphm)){
        Dialog({ message: "请输入对方车牌号码" });
        return;
      }
      if(Tool.isEmpty( _this.jbr)){
        Dialog({ message: "请输入举报人" });
        return;
      }
      if(Tool.isEmpty( _this.sfzmhm)){
        Dialog({ message: "请输入身份证明号码" });
        return;
      }
      if(Tool.isEmpty( _this.sjh)){
        Dialog({ message: "请输入手机号码" });
        return;
      }
      let formData = new FormData();
      formData.append("openid", Tool.getWxOpendid());
      formData.append("czzp", _this.czzp);
      formData.append("wfgps", _this.position);
      formData.append("wflb", _this.wflbcode);
      formData.append("images", JSON.stringify(_this.imageList));
      formData.append("wfxwms", _this.wfxwms);
      formData.append("wfxw", _this.wfxwms);
      formData.append("wfsj", _this.wfsj);
      formData.append("wfld", _this.wfld);
      formData.append("wfldxxdz", _this.wfld);
      formData.append("cplx", _this.cplx);
      formData.append("cphm", _this.cphm);
      formData.append("jbr", _this.jbr);
      formData.append("sfzmhm", _this.sfzmhm);
      formData.append("sjh", _this.sjh);
        Toast.loading({
            duration: 0,
            message: '加载中...',
            forbidClick: true,
        });
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatIllegalSave/wxsave',
          formData
      ).then((response)=>{
        let resp = response.data;
        Toast.clear();
        if (resp.success) {
          _this.info = resp.content;
          if(_this.duration == ''){
            _this.$router.push("/cgym");
          }else{
            _this.uploadFile(_this.file, _this.info.lsh);
          }
        } else {
          Dialog({ message: resp.message });
        }
      })
    },
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
        //video 标签的 duration属性，获取当前视频的长度
        setTimeout(() => {
          _this.duration = videoId.duration;
          console.log("duration", _this.duration);
          if (Math.floor(videoId.duration) > 30) {
            Dialog({ message: '视频不能大于30秒，请重新上传' });
            videoId.src = "";
          }else{
            _this.file = filesId.files[0];
          }
        }, 800);
      }
      console.log("file", _this.file);
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
    },
    uploadFile(file,lsh){
      let _this = this;
      let formData = new FormData();
      formData.append("file", file);
      formData.append("lsh", lsh);
        Toast.loading({
            duration: 0,
            message: '加载中...',
            forbidClick: true,
        });
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/appupload/upload',
              formData
      ).then((response)=>{
        let resp = response.data;
        Toast.clear();
        if (resp.success) {
          _this.$router.push("/cgym");
        } else {
          Dialog({ message: resp.message });
        }
      })
    },
    onConfirm(values) {
      this.wfqy = values
          .filter((item) => !!item)
          .map((item) => item.name)
          .join('/');
      this.showArea = false;
    },
    onConfirmwfxw(value) {
      this.wfxw = value;
      this.showPickerwfxw = false;
    },
    onConfirmhpzl(value) {
      let _this = this;
      _this.cplxxs = value.text;
      _this.cplx = value.code;
      _this.showPickerhpzl = false;

    },
  },
}
</script>

<style scoped>
  .button_db {
    width: 90%;
    margin: 10px 0 10px 5%;
  }
  .upLoad {
    background-color:#fff;
    text-align: left;
    height: 25rem;
    padding: 0.3rem;
  }
  .inputVideo {
    background-color: #00cc66;
    color: #fff;
    font-size: 13px;
    width: 80%;
    height: 2rem;
    line-height: 2rem;
    border-radius: 0.4rem;
    text-align: center;
    margin: 0 auto;
  }
</style>