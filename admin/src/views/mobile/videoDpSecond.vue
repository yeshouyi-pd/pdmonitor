<template>
  <div class="page-content">
    <div class="title-content">
      <header style="position: relative;background: none">
        <img src="/largemonitors/assets/imgs/headertitle2.png" style="position: absolute;left: 30%;top:20px;width: 750px;">
        <div class="lefttitle" style="top: 15px;left: 25px;color: #fff;display: flex;">
          <!--        <img src="/largemonitors/assets/imgs/左上角title.png" alt="" style="width: 250px;">-->
          <!--        <span>-->
          <!--          <div @click="chooseProject" style="cursor: pointer;">主页</div>-->
          <!--        </span>-->
          <div class="title-item" @click="back">返回</div>
          <div v-if="!LOCAL_VIDEO" class="title-item" @click="chooseEnvironment" style="margin-left: 10px;">环境</div>
          <div v-if="!LOCAL_VIDEO" class="title-item" @click="chooseLargemonitors" style="margin-left: 10px;width: 90px;">定点监测</div>
        </div>
      </header>
    </div>
    <div class="content-box">
      <div class="top-content">
        <div class="left-top bg-style">
          <div class="select-box">
            <select v-model="sbbh" class="top-select">
              <option v-for="item in sbbhList" :value="item.sbbh">{{item.sbmc}}</option>
            </select>
          </div>
          <div class="top-content-box">
            <div class="top-content-item" v-for="item in videoEvents">
              <div v-on:click="showVideo(item)">
                <div>{{ item.kssj }}</div>
                <div>{{ item.jssj }}</div>
              </div>
              <img v-on:click="download(item)" class="top-content-item-img" src="/static/image/environment/rightTopBg.png" />
            </div>
          </div>
        </div>
        <div class="right_top">
          <div id="playbox" style="width: 1440px;height: 48vh;margin: 3.4vh auto 0;"></div>
        </div>
      </div>
      <div class="bottom-content">
        <div class="left-bottom bg-style">
          <div class="select-box yf-select">
            <span>月度分布统计 ——</span>
            <month-picker @methodName="changeMethod" idValue="staticmonth" :setValue="tjyf"></month-picker>
          </div>
          <div class="piechart-content" id="pieChart"></div>
        </div>
        <div class="right-bottom bg-style">
          <div class="right-bottom-content">
            <div class="right-bottom-left">
              <span>各航标数据总量统计</span>
              <div class="barchart-content" id="barChart"></div>
            </div>
            <div class="right-bottom-right">
              <div class="bottom-right-content">
                <div class="sl-style" style="padding-top: 10px;">{{totalInfo.whc}}</div>
                <div class="sz-style"><img src="/static/image/environment/red.png"> 总声学确认事件</div>
                <div class="sl-style">{{totalInfo.yhc}}</div>
                <div class="sz-style"><img src="/static/image/environment/green.png"> 总声光确认事件</div>
                <div class="sl-style">{{totalInfo.sssp}}</div>
                <div class="sz-style"><img src="/static/image/environment/yellow.png"> 总光学确认事件</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import monthPicker from "@/components/monthPicker";
export default {
  name:'video-dp-second',
  components: {monthPicker},
  data: function (){
    return {
      LOCAL_VIDEO:LOCAL_VIDEO,
      videoEvents:[],
      sbbhList:[
        {'sbbh':'RPCDA4013','sbmc':'一号航标'},
        {'sbbh':'RPCDA4004','sbmc':'二号航标'},
        {'sbbh':'RPCDA4005','sbmc':'三号航标'},
        {'sbbh':'RPCDA4012','sbmc':'四号航标'},
        {'sbbh':'RPCDA4003','sbmc':'五号航标'},
        {'sbbh':'RPCDA4006','sbmc':'六号航标'},
        {'sbbh':'RPCDA4009','sbmc':'七号航标'},
        {'sbbh':'RPCDA4001','sbmc':'八号航标'},
        {'sbbh':'RPCDA4007','sbmc':'九号航标'},
        {'sbbh':'RPCDA4010','sbmc':'十号航标'},
        {'sbbh':'RPCDA4008','sbmc':'十一号航标'},
        {'sbbh':'RPCDA4011','sbmc':'十二号航标'},
        {'sbbh':'RPCDA4015','sbmc':'十三号航标'},
        {'sbbh':'RPCDA4014','sbmc':'十四号航标'},
        {'sbbh':'RPCDA4002','sbmc':'十五号航标'},
        {'sbbh':'RPCDA4016','sbmc':'十六号航标'}
      ],
      sbbh:'RPCDA4013',
      tjyf:'',
      totalInfo:{
        'whc':0,
        'yhc':0,
        'sssp':0
      },
      devices:[]
    }
  },
  mounted() {
    let _this = this;
    _this.tjyf = Tool.dateFormat("yyyy年MM月",new Date());
    //获取所有的设备，因为要用到设备的位置
    _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getDevice').then((res)=>{
      let response = res.data;
      _this.devices = response.content.list;
      _this.getExplainVideoEvent();
      _this.staticmonthMethod();
      _this.staticMethod();
      _this.selectStaticsByRq();
    })
  },
  methods: {
    back(){
      let _this = this;
      if(_this.LOCAL_VIDEO){
        window.location.href = "/mobile/environmentDp";
      }else{
        window.location.href = "/admin/largemonitorsZj";
      }
    },
    chooseEnvironment(){
      window.location.href = "/mobile/environmentDp";
    },
    chooseLargemonitors(){
      let _this = this;
      window.location.href = "/mobile/largemonitorsZj";
    },
    selectStaticsByRq(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/selectStaticsByRq', {}).then((response)=>{
        let lists = response.data.content;
        let sbmcList = [];
        let sxqrList = [];
        let sgqrList = [];
        let gxqrList = [];
        for(let i=0;i<lists.length;i++){
          let obj = lists[i];
          sbmcList.push(_this.optionNSArray(_this.devices,obj.sbbh));
          sxqrList.push(obj.whc);
          sgqrList.push(obj.yhc);
          gxqrList.push(obj.sssp)
        }
        _this.initStaticsBySbbh(sbmcList,sxqrList,sgqrList,gxqrList);
      })
    },
    initStaticsBySbbh(sbmcList,sxqrList,sgqrList,gxqrList) {
      let _this = this;
      let option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          textStyle: {
            color: "#fff"
          },
          bottom: "-3"
        },
        grid: {
          top: '10%',
          left: '1%',
          right: '4%',
          bottom: '10%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          show: false,
          axisLine: {
            lineStyle: {
              color: "#fff"
            }
          }
        },
        yAxis: {
          type: 'category',
          data: sbmcList,
          axisLine: {
            show: false,
            lineStyle: {
              color: "#fff"
            }
          },
          axisTick: {
            show: false
          }
        },
        series: [
          {
            name: '声学确认事件',
            data: sxqrList,
            stack: 'total',
            itemStyle: {
              color: "rgba(21, 96, 130, 1)"
            },
            type: 'bar'
          },
          {
            name: '声光确认事件',
            data: sgqrList,
            stack: 'total',
            itemStyle: {
              color: "rgba(223, 113, 50, 1)"
            },
            type: 'bar'
          },
          {
            name: '光学确认事件',
            data: gxqrList,
            stack: 'total',
            itemStyle: {
              color: "rgba(24, 102, 34, 1)"
            },
            type: 'bar'
          }
        ]
      };
      let chart = echarts.init(document.getElementById('barChart'));
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    staticMethod(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/staticMonth', {}).then((response)=>{
        _this.totalInfo = response.data.content;
      })
    },
    staticmonthMethod(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/staticMonth', {'yf':_this.tjyf.replace('年','-').replace('月','')}).then((response)=>{
        let resp = response.data.content;
        if(!Tool.isEmpty(resp)){
          let data = [];
          data.push({ value:resp.whc,name:'声学事件'})
          data.push({ value:resp.yhc,name:'声光事件'})
          data.push({ value:resp.sssp,name:'光学事件',selected:true})
          _this.initPieEcharts(data);
        }
      })
    },
    initPieEcharts(data){
      let option = {
        tooltip: {
          trigger: 'item'
        },
        series: [
          {
            type: 'pie',
            radius: ['30%', '90%'],
            selectedMode: true,
            itemStyle: {
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: true,
              position: 'inside',
              formatter: '{b}\n{d}',
              fontSize: 16,
              fontWeight: 'bold'
            },
            data: data
          }
        ]
      };
      let chart = echarts.init(document.getElementById('pieChart'))
      chart.setOption(option);
    },
    changeMethod(resp){
      let _this = this;
      if(Tool.isEmpty(resp)){
        return;
      }
      if(resp==_this.tjyf){
        return;
      }
      _this.tjyf = resp;
      _this.staticmonthMethod();
    },
    getExplainVideoEvent(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/welcome/getExplainVideoEvent', {sm:'1'}).then((response)=>{
        let resp = response.data;
        _this.videoEvents = resp.content;
        if(_this.videoEvents.length>0){
          if(0==_this.videoEvents[0].sfysp){
            _this.getPlayUrl(_this.videoEvents[0]);
          }else if(2==_this.videoEvents[0].sfysp){
            _this.watchVideo(_this.videoEvents[0].wjlj);
          }
        }
      })
    },
    download(item){
      let _this = this;
      let wjlj = item.wjlj.replace("49.239.193.146:59088","192.168.3.11:18088");
      let paramsStr = "fileUrl="+wjlj.substring(0,wjlj.lastIndexOf("/")+1)+"&fileName="+wjlj.substring(wjlj.lastIndexOf("/")+1,wjlj.length);
      window.location.href = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFile53?'+paramsStr;
    },
    showVideo(item){
      let _this = this;
      if(item.sfysp==2){
        _this.watchVideo(item.wjlj);
      }else{
        _this.getPlayUrl(item);
      }
    },
    watchVideo(wjlj){
      let _this = this;
      Loading.show();
      $("#playbox").empty();
      let video = document.createElement("video");
      video.setAttribute("width","100%");
      video.setAttribute("height","100%");
      video.setAttribute("controls","controls");
      video.setAttribute("autoplay","autoplay");
      video.setAttribute("loop","loop");
      video.src = wjlj;
      document.getElementById('playbox').appendChild(video);
      Loading.hide();
    },
    getPlayUrl(item){
      let _this = this;
      Loading.show();
      $("#playbox").empty();
      let url = 'http://49.239.193.146:59088/FileInfo.asmx/GetPlayUrl';
      $.post(url,{"sbid": item.sbbh,"filename":item.wjlj.substring(item.wjlj.lastIndexOf("/")+1),"fbl":"1080","fhfs":"1"}, function (data, status) {
        if(status&&!(data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('不存在')||data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('文件大小为0'))){
          let video = document.createElement("video");
          video.setAttribute("width","100%");
          video.setAttribute("height","100%");
          video.setAttribute("controls","controls");
          video.setAttribute("autoplay","autoplay");
          video.setAttribute("loop","loop");
          if(Hls.isSupported()) {
            let hls = new Hls();
            hls.loadSource(data.getElementsByTagName('PlayUrl')[0].childNodes[0].nodeValue);
            hls.attachMedia(video);
            hls.on(Hls.Events.MANIFEST_PARSED,function() {
              video.play();
            });
          } else if (video.canPlayType('application/vnd.apple.mpegurl')) {
            video.src = data.getElementsByTagName('PlayUrl')[0].childNodes[0].nodeValue;
            video.addEventListener('loadedmetadata',function() {
              video.play();
            });
          }
          document.getElementById('playbox').appendChild(video);
          Loading.hide();
        }else{
          Toast.error("未找到源文件或文件大小为0，无法转码！");
          Loading.hide();
        }
      })
    },
    optionNSArray(list, key){
      if (!list || !key) {
        return "";
      } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
          if (key === list[i]["sbsn"]) {
            result = list[i]["sbmc"];
          }
        }
        return result;
      }
    }
  }
}
</script>
<style scoped>
.page-content{
  width: 1920px;
  height: 100vh;
  background-color: #0c0e21;
  padding: 0;
}
.title-content{
  width: 1920px;
  height: 8vh;
}
.title-item{
  width: 80px;
  padding: 5px;
  border: 1px solid #4AAFE3;
  text-align: center;
  font-size: 18px;
  cursor: pointer;
  font-weight: bold;
  color: #4AAFE3;
}
.content-box{
  width: 1920px;
  height: 92vh;
}
.top-content{
  display: flex;
}
.left-top{
  width: 380px;
  height: 55vh;
}
.select-box{
  width: 180px;
  height: 4.5vh;
  background-image: url(/static/image/environment/selectk.png);
  background-repeat: no-repeat;
  background-size: 97% 93%;
  background-position: center;
  margin: 8% 25px 0px;
}
.top-select{
  height: 30px;
  width: 89%;
  margin: 3px 0 0 11px;
  border: none;
  background-color: transparent;
  color: #d0ccff;
  font-size: 17px;
  font-weight: bold;
  appearance: none;
  -moz-appearance: none;
  -webkit-appearance: none;
  cursor: pointer;
  background: transparent url(/static/image/environment/sjx.png) no-repeat;
  background-position: 130px 5px;
}
option{
  color: #d0ccff;        /* 设置文本颜色为蓝色 */
  background-color: #0c0e21; /* 设置背景颜色为黄色 */
  border: 1px solid #000000; /* 设置边框为黑色 */
  padding: 5px;          /* 设置内边距为5px */
  font-size: 16px;       /* 设置字体大小为16px */
  font-weight: bold;
}
.top-content-box{
  width: 85%;
  height: 43vh;
  overflow-y: auto;
  margin: auto;
  padding-left: 5px;
}
.top-content-box::-webkit-scrollbar{
  width: 10px;
}
.top-content-box::-webkit-scrollbar-thumb{
  background: #616ABF;
}
.top-content-box::-webkit-scrollbar-track{
  background: #8F9099;
}
.top-content-item{
  display: flex;
  background-color: #161938;
  padding: 7px;
  font-size: 18px;
  color: #64b7d1;
  justify-content: space-around;
  margin-top: 8px;
}
.top-content-item-img{
  width: 90px;
  height: 50px;
}
.right_top{
  width: 1560px;
  height: 55vh;
  background-image: url("/static/image/environment/spk.png");
  background-repeat: no-repeat;
  background-size: 97% 93%;
  background-position: center;
}
.bottom-content{
  display: flex;
}
.left-bottom{
  width: 380px;
  height: 37vh;
}
.yf-select{
  margin: 4.3% 25px 0;
  width: 280px;
  display: flex;
  color: #d0ccff;
  font-weight: bold;
}
.yf-select span{
  font-size: 17px;
  margin: 4.1px 0 0 13px;
}
.piechart-content{
  width: 90%;
  height: 26vh;
  margin: 10px auto 0;
}
.right-bottom{
  width: 1560px;
  height: 37vh;
}
.right-bottom-content{
  width: 92%;
  height: 32vh;
  margin: 15px auto 0;
  display: flex;
}
.right-bottom-left{
  width: 72%;
  height: 100%;
}
.right-bottom-left span{
  font-size: 18px;
  margin: 5px 0 0 10px;
  color: #d0ccff;
  font-weight: bold;
}
.barchart-content{
  width: 100%;
  height: 89%;
}
.right-bottom-right{
  width: 28%;
  height: 100%;
}
.bottom-right-content{
  width: 88%;
  height: 88%;
  margin: 5% 0 0 0;
  background-color: #161938;
}
.sl-style{
  font-size: 28px;
  color: #d0ccff;
  margin-left: 20px;
}
.sz-style{
  font-size: 20px;
  color: #64b7d1;
  margin-left: 20px;
}
.bg-style{
  background-image: url("/static/image/environment/bgk.png");
  background-repeat: no-repeat;
  background-size: 105% 100%;
  background-position: center;
}
</style>