<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">A4设备聚类事件查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 15%">
                  <select v-model="equipmentFileEventDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td style="width: 10%;">
                  开始日期：
                </td>
                <td style="width: 15%;">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="rStime" end-id="rEtime"></times>
                </td>
                <td  style="width: 20%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </a>
                  <button type="button" v-on:click="exportExcle()" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-leaf"></i>
                    导出
                  </button>
                  <button type="button" v-on:click="downloadEvent()" class="btn btn-xs btn-info" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-download"></i>
                    下载文件
                  </button>
                </td>
              </tr>
              </tbody>
            </table>
          </form>
        </div>
      </div>
    </div>

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>所属机构</th>
          <th>检测点</th>
          <th>设备sn</th>
          <th>开始时间</th>
          <th>结束时间</th>
          <th>头数</th>
          <th style="width: 18%;">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in equipmentFileEvents">
          <td>{{deptMap|optionMapKV(item.deptcode)}}</td>
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.kssj}}</td>
          <td>{{item.jssj}}</td>
          <td>{{item.ts}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="showEcharts(item)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="fa-solid fa fa-list bigger-120">雷达图</i>
              </button>
              <button :id="'xz'+item.id" v-if="userDto.yj=='Y'" v-on:click="downloadVideo(item.id)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="ace-icon fa fa-volume-down bigger-120">下载视频</i>
              </button>
              <button :id="item.id" v-on:click="watchVideo(item.cjsj)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="ace-icon fa  fa-video-camera bigger-120">历史回放</i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

    <div id="echart-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">雷达图</h4>
          </div>
          <div class="modal-body" style="width: 400px;height: 600px;margin: auto;text-align: center;padding: 0" >
            <h2>{{ldTime}}</h2>
            <h4 style="margin-top: 10px">N</h4>
            <div style="width: 400px;height: 380px;" id="echartEvent"></div>
            <h4>S</h4>
            <h4 v-if="zhbht || zhsp">(港珠澳大桥)</h4>
            <p>{{equipmentFileEvent.kssj}}至{{equipmentFileEvent.jssj}}</p>
            <p>保守估计发声头数<span style="color: red">{{equipmentFileEvent.ts}}头</span></p>
          </div>
          <div class="modal-footer">
            <button v-if="showBtn" type="button" class="btn btn-white btn-default btn-round" v-on:click="reloadLdt">
              <i class="ace-icon fa fa-refresh"></i>
              重播
            </button>
            <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
              <i class="ace-icon fa fa-times"></i>
              关闭
            </button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div id="video-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 80%">
        <div class="modal-content" style="width: 100%;margin: auto">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">历史回放</h4>
          </div>
          <div class="modal-body" :style="'height: '+videoHeight+'px;overflow-y: auto;width:100%;'" id="playbox">

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
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
import Times from "../../components/times";
import Pagination from "../../components/pagination";

export default {
  name: 'equipment-file-event',
  components: {Pagination,Times},
  data: function (){
    return {
      equipmentFileEvent:{},
      equipmentFileEventDto:{},
      equipmentFileEvents:[],
      deptMap: [],
      waterEquipments: [],
      myChart:null,
      intervalId:'',
      showBtn:false,
      echartsData:[],
      title:[],
      videos:[],
      videoHeight:400,
      zhbht:LOCAL_ZHBHT,
      ldTime:'',
      userDto:null,
      shj:LOCAL_SSBRL,
      zhsp:LOCAL_VIDEO,
      timeHandle:null,
      canPlay:false
    }
  },
  mounted() {
    let _this = this;
    _this.userDto = Tool.getLoginUser();
    _this.deptMap = Tool.getDeptUser();
    _this.$refs.pagination.size = 10;
    _this.$forceUpdate();
    _this.list(1);
    _this.findDeviceInfo();
    //监听模态框关闭
    $('#echart-modal').on('hidden.bs.modal', function () {
      //先消除再创建
      if(_this.myChart!=null){
        _this.myChart.dispose();
      }
      if (_this.intervalId != null) {
        clearInterval(_this.intervalId); //清除计时器
        _this.intervalId = null; //设置为null
      }
      _this.showBtn = false;
    });
    //监听模态框关闭
    $('#video-modal').on('hidden.bs.modal', function () {
      clearTimeout(_this.timeHandle);
    });
  },
  methods: {
    //下载文件
    downloadEvent(){
      let _this = this;
      let param = "";
      if("460100"!=Tool.getLoginUser().deptcode){
        param+="&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(!Tool.isEmpty(_this.equipmentFileEventDto.sbbh)){
        param+="&sbbh="+_this.equipmentFileEventDto.sbbh;
      }else{
        Toast.error("请选择设备编号！");
        return;
      }
      if(!Tool.isEmpty(_this.equipmentFileEventDto.stime)){
        param+="&stime="+_this.equipmentFileEventDto.stime;
      }else{
        Toast.error("请选择开始日期！");
        return;
      }
      if(!Tool.isEmpty(_this.equipmentFileEventDto.etime)){
        param+="&etime="+_this.equipmentFileEventDto.etime;
      }else{
        Toast.error("请选择结束日期！");
        return;
      }
      let diff = _this.getDateDiff(_this.equipmentFileEventDto.stime,_this.equipmentFileEventDto.etime);
      if(diff>=3){
        Toast.error("日期相差不能超过3天！");
        return;
      }
      window.location.href = process.env.VUE_APP_SERVER + '/monitor/download/audio/downloadEquipmentFileEvent?'+param.substring(1,param.length);
    },
    exportExcle(){
      let _this = this;
      let param = "";
      if("460100"!=Tool.getLoginUser().deptcode){
        param+="&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(!Tool.isEmpty(_this.equipmentFileEventDto.sbbh)){
        param+="&sbbh="+_this.equipmentFileEventDto.sbbh;
      }
      if(!Tool.isEmpty(_this.equipmentFileEventDto.stime)){
        param+="&stime="+_this.equipmentFileEventDto.stime;
      }
      if(!Tool.isEmpty(_this.equipmentFileEventDto.etime)){
        param+="&etime="+_this.equipmentFileEventDto.etime;
      }
      if(Tool.isEmpty(param)){
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportFileEvent';
      }else{
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportFileEvent?'+param.substring(1,param.length);
      }
    },
    //是否有视频
    hasVideo(cjsj,id){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/videoList', {'cjsj':cjsj}).then((response)=> {
        let resp = response.data;
        let videoDatas = resp.content;
        if(videoDatas.length>0){
          $("#xz"+id).attr("style","display:block");
          $("#"+id).attr("style","display:block");
        }else {
          $("#xz"+id).attr("style","display:none");
          $("#"+id).attr("style","display:none");
        }
      })
    },
    //历史回放
    watchVideo(cjsj){
      let _this = this;
      $("#playbox").empty();
      _this.canPlay = false;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/videoList', {'cjsj':cjsj}).then((response)=>{
         let resp = response.data;
         let videoDatas = resp.content;
         _this.$forceUpdate();
         if(videoDatas.length>0){
           if(videoDatas.length>=2){
             _this.videoHeight = '761';
           }else{
             _this.videoHeight = '400';
           }
           let isLast = false;
           for(let i=0;i<videoDatas.length;i++){
             let obj = videoDatas[i];
             if(i==videoDatas.length-1){
               isLast = true;
             }
             _this.getPlayUrl(obj.sbbh,obj.tplj.substring(obj.tplj.lastIndexOf("/")+1),isLast);
           }
         }else{
           Loading.hide();
           Toast.error("未找到对应视频！");
         }
      })
    },
    getPlayUrl(sbid,filename,isLast){
      let _this = this;
      let url = '';
      if(_this.shj){
        url="http://146.56.226.176:8088/FileInfo.asmx/GetPlayUrl";
      }else{
        url="http://49.239.193.146:49082/FileInfo.asmx/GetPlayUrl";
      }
      $.post(url,{"sbid": sbid,"filename":filename,"fbl":"1080","fhfs":"1"}, function (data, status) {
        if(status&&!(data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('不存在')||data.getElementsByTagName('Mesg')[0].childNodes[0].nodeValue.includes('文件大小为0'))){
          if(_this.fileExists(data.getElementsByTagName('PlayUrl')[0].childNodes[0].nodeValue)){
            Loading.hide();
            let video = document.createElement("video");
            video.setAttribute("width","100%");
            video.setAttribute("height","350px");
            video.setAttribute("controls","controls");
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
            $("#video-modal").modal("show");
            _this.canPlay = true;
          }else {
            clearTimeout(_this.timeHandle);
            _this.timeHandle = setTimeout(function (){_this.getPlayUrl(sbid,filename)}, 10000);
          }
        }else{
          if(isLast&&!_this.canPlay){
            Loading.hide();
            Toast.error("未找到源文件或文件大小为0，无法转码！");
          }
        }
      })
    },
    fileExists(url) {
      let isFlag;
      $.ajax({
        url: url,
        async: false,
        type: 'HEAD',
        error: function () {
          isFlag=0;
          return;
        },
        success: function () {
          isFlag=1;
          return;
        }
      });
      if(isFlag==1){
        return true;
      }else{
        return false;
      }
    },
    //重新播放雷达图
    reloadLdt(){
      let _this = this;
      _this.showBtn = false;
      clearInterval(_this.intervalId);
      _this.intervalId = null; //设置为null
      _this.myChart.dispose();
      _this.initEcharts();
      _this.loopEchartsData(_this.echartsData,_this.title);
    },
    showEcharts(item){
      let _this = this;
      _this.equipmentFileEvent = $.extend({}, item);
      _this.$forceUpdate();
      _this.initEcharts();
      _this.echartsData = [];
      _this.title = [];
      let arr = item.jtnr.split("/");//2022_10_15_02_15_14-0:105,1:235
      for(let i=0;i<arr.length;i++){
        let rqandjd = arr[i].split("-");//2022_10_15_02_15_14和0:105,1:235
        let item = [];
        let rqarr = rqandjd[0].split("_");//2022和10和15和02和15和14
        if(rqandjd.length==2){
          if(rqandjd[1].split(",").length>0){
            let jdarr =  rqandjd[1].split(",");//0:105和1:235
            for(let j=0;j<jdarr.length;j++){
              if(jdarr[j].split(":").length==2){
                item.push([_this.getRandomNumber(),jdarr[j].split(":")[1]]);
              }
            }
            _this.echartsData.push(item);
            _this.title.push(rqarr[0]+"-"+rqarr[1]+"-"+rqarr[2]+" "+rqarr[3]+":"+rqarr[4]+":"+rqarr[5]);
          }
        }
      }
      _this.loopEchartsData(_this.echartsData,_this.title);
      $("#echart-modal").modal("show");
    },
    getRandomNumber() {
      return Math.random() * (3 - 0.1) + 0.1;
    },
    loopEchartsData(list,title){
      let _this = this;
      _this.myChart.setOption({
        series: [{
          data:list[0],
          itemStyle:{
            color:(e)=>{
              return _this.domColor(e.dataIndex)
            }
          }
        }]
      });
      _this.ldTime=title[0];
      if(list.length>1){
        let k=1;
        _this.intervalId = setInterval(function () {
          if(k==list.length-1){
            k=list.length-1;
            clearInterval(_this.intervalId);
            _this.showBtn = true;
          }
          _this.ldTime=title[k];
          _this.myChart.setOption({
            series: [
              {
                data:list[k],
                itemStyle:{
                  color:(e)=>{
                    return _this.domColor(e.dataIndex)
                  }
                }
              }
            ],
            // title: {
            //   text: title[k],
            //   left:"19%"
            // }
          });
          k=k+1;
        }, 4000);
      }else{
        _this.showBtn = true;
      }
    },
    domColor(dataIndex){
      let newAry=[];
      for(let k=0;k<20;k++){
        let r = Math.ceil(Math.random()*256);
        let g = Math.ceil(Math.random()*256);
        let b = Math.ceil(Math.random()*256);
        newAry.push(`rgb(${r},${g},${b})`);
      }
      return newAry[dataIndex];
    },
    initEcharts(){
      let _this = this;
      let option = {
        polar: {
          center: ['50%', '50%']
        },
        angleAxis: {
          type: 'value',
          min: 0,
          max:360
        },
        radiusAxis: {
          min: 0,
          max: 3
        },
        series: [{
          coordinateSystem: 'polar',
          type: 'scatter',
          showSymbol: false
        }],
        animation:false
      };
      _this.myChart = echarts.init(document.getElementById("echartEvent"));
      _this.myChart.setOption(option);
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001','dqzl':'A4'};
      }else{
        data = {'sblb':'0001','dqzl':'A4','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        _this.$forceUpdate();
      })
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.equipmentFileEventDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.equipmentFileEventDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.equipmentFileEventDto.page = page;
      _this.equipmentFileEventDto.size = _this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.equipmentFileEventDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/listClusters', _this.equipmentFileEventDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFileEvents = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
        _this.$nextTick(function (){
          for(let i=0;i<_this.equipmentFileEvents.length;i++){
            _this.hasVideo(_this.equipmentFileEvents[i].cjsj,_this.equipmentFileEvents[i].id);
          }
        });
      })
    },
    downloadVideo(id){
      let _this = this;
      let url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downZipByA4Id?id='+id;
      _this.$ajax.get(url).then((response)=>{
        if(response.data && response.data.message && response.data.message.includes("系统异常")){
          Toast.error("系统异常，请联系管理员！");
        }else if(response.data && response.data.includes("未找到")){
          Toast.error("未找到对应视频！");
        }else{
          window.location.href = url;
        }
      })
    },
    getDateDiff(dateStr1, dateStr2) {
      // 处理常见的日期分隔符（兼容 "-" 和 "/"）
      const formatDateStr = (str) => str.replace(/\//g, '-');
      // 将字符串转换为 Date 对象
      const date1 = new Date(formatDateStr(dateStr1));
      const date2 = new Date(formatDateStr(dateStr2));
      // 验证日期有效性
      if (isNaN(date1) || isNaN(date2)) {
        throw new Error('Invalid date format. Use "YYYY-MM-DD" or "YYYY/MM/DD"');
      }
      // 转换为 UTC 时间消除时区影响
      const utc1 = Date.UTC(
          date1.getFullYear(),
          date1.getMonth(),
          date1.getDate()
      );
      const utc2 = Date.UTC(
          date2.getFullYear(),
          date2.getMonth(),
          date2.getDate()
      );
      // 计算天数差（绝对值）
      const timeDiff = Math.abs(utc2 - utc1);
      const dayDiff = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
      return dayDiff;
    }

  }
}
</script>
<style>
.myVideo-dimensions{
  width: 700px;
}
</style>