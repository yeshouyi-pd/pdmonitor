<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">视频事件查询</h4>
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
                  <select v-model="videoEventDto.sbbh" class="form-control" id="form-field-select-1">
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
          <th>检测点</th>
          <th>设备sn</th>
          <th>开始时间</th>
          <th>结束时间</th>
          <th style="width: 18%;">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in videoEvents">
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.kssj}}</td>
          <td>{{item.jssj}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="watchVideo(item)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="ace-icon fa  fa-video-camera bigger-120">查看</i>
              </button>
              <button v-on:click="download(item)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="ace-icon fa fa-download bigger-120">下载</i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

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
  name: 'video-event-tl',
  components: {Pagination,Times},
  data: function (){
    return {
      equipmentFileEvent:{},
      videoEventDto:{},
      videoEvents:[],
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
      timeHandle:null,
      canPlay:false
    }
  },
  mounted() {
    let _this = this;
    _this.userDto = Tool.getLoginUser();
    _this.deptMap = Tool.getDeptUser();
    console.log( _this.deptMap);
    _this.$refs.pagination.size = 10;
    _this.$forceUpdate();
    _this.list(1);
    _this.findDeviceInfo();
    //监听模态框关闭
    $('#video-modal').on('hidden.bs.modal', function () {
      clearTimeout(_this.timeHandle);
    });
  },
  methods: {
    download(item){
      let _this = this;
      window.location.href = process.env.VUE_APP_SERVER + '/monitor/download/audio/downVideo?id='+item.id;
    },
    //历史回放
    watchVideo(obj){
      let _this = this;
      $("#playbox").empty();
      _this.canPlay = false;
      _this.videoHeight = '400';
      _this.getPlayUrl(obj.sbbh,obj.wjlj.substring(obj.wjlj.lastIndexOf("/")+1),true);
    },
    getPlayUrl(sbid,filename,isLast){
      let _this = this;
      let url = 'http://111.38.21.161:7003/FileInfo.asmx/GetPlayUrl';
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
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001','dqzl':'A1,A4'};
      }else{
        data = {'sblb':'0001','dqzl':'A1,A4','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        console.log(_this.waterEquipments);
        _this.$forceUpdate();
      })
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.videoEventDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.videoEventDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.videoEventDto.page = page;
      _this.videoEventDto.size = _this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.videoEventDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/videoEvent/list', _this.videoEventDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.videoEvents = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    explainVideo(){

    },
  }
}
</script>
<style>
.myVideo-dimensions{
  width: 700px;
}
</style>