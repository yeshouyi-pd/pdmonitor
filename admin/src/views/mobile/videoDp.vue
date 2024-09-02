<template>
  <div style="width: 1920px;height: 100vh;background-image: url('/static/image/environment/bg02.png');background-size: 100%;margin: auto">
    <header style="position: relative;background: none">
      <img src="/largemonitors/assets/imgs/headertitle2.png" style="position: absolute;left: 30%;top:20px;width: 750px;">
      <div class="lefttitle" style="top: 15px;left: 25px;">
        <img src="/largemonitors/assets/imgs/左上角title.png" alt="" style="width: 250px;">
        <span>
          <div @click="chooseProject" style="cursor: pointer;">主页 </div>
          <div style="padding: 0 5px;">/</div>
          <div @click="back" style="cursor: pointer;"> 返回</div>
        </span>
      </div>
    </header>
    <div class="page-first-div">
      <div class="left-box">
        <div class="left-box-item" style="color: #82CF48;font-size: 16px;text-align: center;overflow-y: auto">
          <table id="simple-table" class="table table-bordered">
            <thead>
            <tr>
              <td>设备名称</td>
              <td>开始时间</td>
              <td>结束时间</td>
              <td>视频</td>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in videoEvents">
              <td>{{devices|optionNSArray(item.sbbh)}}</td>
              <td>{{item.kssj}}</td>
              <td>{{item.jssj}}</td>
              <td>
                <button v-on:click="showVideo(item)" class="btn btn-xs btn-info" >
                  <i class="ace-icon fa fa-book bigger-120">查看</i>
                </button>
                <button v-on:click="download(item)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                  <i class="ace-icon fa fa-download bigger-120">下载</i>
                </button>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
        <div class="left-box-item">
          <times class="form-time" v-bind:startTime="startTime" v-bind:endTime="endTime" v-bind:svalue="stime" v-bind:evalue="etime" start-id="dpStart" end-id="dpEnd"></times>
          <div id="staticsRqEchart" style="height: 98%;width: 90%;"></div>
        </div>
        <div class="left-box-item">
          <select v-model="sbbh" @change="selectStaticsBySbbh" class="form-control form-select">
            <option v-for="item in sbbhList" :value="item.sbbh">{{item.sbmc}}</option>
          </select>
          <div id="staticsSbbhEchart" style="height: 98%;width: 90%;"></div>
        </div>
      </div>
      <div class="right-box"  id="playbox">

      </div>
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/pagination";
import Times from "@/components/times";
export default {
  name: 'video-dp',
  components: {Pagination,Times},
  data: function (){
    return {
      sbbhList:[
        {'sbbh':'RPCDA4013','sbmc':'一号标'},
        {'sbbh':'RPCDA4004','sbmc':'二号标'},
        {'sbbh':'RPCDA4005','sbmc':'三号标'},
        {'sbbh':'RPCDA4012','sbmc':'四号标'},
        {'sbbh':'RPCDA4003','sbmc':'五号标'},
        {'sbbh':'RPCDA4006','sbmc':'六号标'},
        {'sbbh':'RPCDA4009','sbmc':'七号标'},
        {'sbbh':'RPCDA4001','sbmc':'八号标'},
        {'sbbh':'RPCDA4007','sbmc':'九号标'},
        {'sbbh':'RPCDA4010','sbmc':'十号标'},
        {'sbbh':'RPCDA4008','sbmc':'十一号标'},
        {'sbbh':'RPCDA4011','sbmc':'十二号标'},
        {'sbbh':'RPCDA4015','sbmc':'十三号标'},
        {'sbbh':'RPCDA4014','sbmc':'十四号标'},
        {'sbbh':'RPCDA4002','sbmc':'十五号标'},
        {'sbbh':'RPCDA4016','sbmc':'十六号标'}
      ],
      sbbh:'RPCDA4013',
      stime:'',
      etime:'',
      devices:[],
      videoEvents:[],
    }
  },
  created() {
    let _this = this;
    _this.stime=Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*30));
    _this.etime=Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24));
    //获取所有的设备，因为要用到设备的位置
    _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getDevice').then((res)=>{
      let response = res.data;
      _this.devices = response.content.list;
      _this.getExplainVideoEvent();
      _this.selectStaticsByRq();
      _this.selectStaticsBySbbh();
    })
  },
  watch:{
    stime(newVal, oldVal){
      if(newVal!=oldVal){
        this.selectStaticsByRq();
      }
    },
    etime(newVal, oldVal){
      if(newVal!=oldVal){
        this.selectStaticsByRq();
      }
    }
  },
  methods: {
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.etime = rep;
      _this.$forceUpdate();
    },
    selectStaticsByRq(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/selectStaticsByRq', {stime:_this.stime,etime:_this.etime}).then((response)=>{
        let lists = response.data.content;
        let sbmcList = [];
        let sxqrList = [];
        let sgqrList = [];
        let gxqrList = [];
        for(let i=0;i<lists.length;i++){
          let obj = lists[i];
          sbmcList.push(_this.optionNSArray(_this.devices,obj.sbbh));
          sxqrList.push(obj.yhc);
          sgqrList.push(obj.whc);
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
      let chart = echarts.init(document.getElementById('staticsRqEchart'));
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    selectStaticsBySbbh(){
      let _this = this;
      let stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*14));
      let etime = Tool.dateFormat("yyyy-MM-dd",new Date());
      // let stime = '2024-05-15';
      // let etime = '2024-07-16';
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/selectStaticsBySbbh', {sbbh:_this.sbbh,stime:stime,etime:etime}).then((response)=>{
        let lists = response.data.content;
        let rqList = [];
        let sxqrList = [];
        let sgqrList = [];
        let gxqrList = [];
        for(let i=0;i<lists.length;i++){
          let obj = lists[i];
          rqList.push(obj.rq);
          sxqrList.push(obj.whc);
          sgqrList.push(obj.yhc);
          gxqrList.push(obj.sssp);
        }
        _this.initStaticsByRq(rqList,sxqrList,sgqrList,gxqrList);
      })
    },
    initStaticsByRq(rqList,sxqrList,sgqrList,gxqrList){
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
          top: '8%',
          left: '1%',
          right: '4%',
          bottom: '10%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          axisLine: {
            show: false,
            lineStyle: {
              color: "#fff"
            }
          },
          axisTick: {
            show: false
          },
          data: rqList
        },
        yAxis: {
          type: 'value',
          show: false,
          axisLine: {
            lineStyle: {
              color: "#fff"
            }
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
      let chart = echarts.init(document.getElementById('staticsSbbhEchart'));
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
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
    back(){
      let _this = this;
      window.location.href = "/mobile/environmentDp";
    },
    chooseProject(){
      window.location.href = "/admin/chooseProject";
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
.page-first-div{
  width: 100%;
  height: calc(100% - 88px);
  display: flex;
  flex-direction: row;
}
.left-box{
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.left-box-item{
  width: 100%;
  height: 33%;
}
.form-time{
  position: absolute;
  width: 250px;
  left: 26%;
}
.form-select{
  position: absolute;
  width: 150px;
  left: 31%;
  background-color: #6FB3E0;
  color: white;
}
.right-box{
  width: 60%;
  height: 100%;
  display: flex;
  flex-direction: column;
}

</style>