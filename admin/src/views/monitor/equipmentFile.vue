<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">图片管理查询</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form>
                <table style="font-size: 1.1em;width:100%" class="text-right" >
                  <tbody>
                  <tr>
                    <td style="width: 5%;">
                      检测点：
                    </td>
                    <td style="width: 15%;">
                      <select v-model="equipmentFileDto.sbbh" class="form-control" id="form-field-select-1">
                        <option value="" selected>全部</option>
                        <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                      </select>
                    </td>
                    <td style="width: 5%;">
                      采集时间：
                    </td>
                    <td style="width: 25%;">
                      <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="eqstime" end-id="eqetime"></times>
                    </td>
                    <td style="width: 5%;">
                      类型：
                    </td>
                    <td style="width: 15%;">
                      <select v-model="equipmentFileDto.tplj" class="form-control" id="form-field-select-2">
                        <option value="" selected>请选择</option>
                        <option value="predation">捕食</option>
                      </select>
                    </td>
                    <td  style="width: 25%;" class="text-center">
                      <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      <button type="button" v-on:click="selectEquipmentCluster()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                        <i class="ace-icon fa fa-book"></i>
                        A4聚类
                      </button>
                      <button type="button" v-on:click="downloadVideoZip()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                        <i class="ace-icon fa fa-download"></i>
                        批量下载音频
                      </button>
                      <a href="javascript:location.replace(location.href);"  class="btn btn-sm btn-success btn-round">
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
        <!-- query end -->
      </div><!-- col-md-12 -->
    </div><!-- row -->

    <div>
      <div style="display: flex;flex-wrap: wrap;margin-bottom: 30px;">
        <div v-for="(item,index) in equipmentFiles" style="margin:20px;width: 150px;height: 300px;text-align: center;">
          <div style="text-align: center;width: 100px;margin: 0 auto;">
            <img alt="无图片" :src="item.tplj.replace(/http:\/\/[^/]+/, picServer)" style="width: 100px;height: 200px;cursor: pointer;" v-on:click="checkImg(item,index)">
          </div>
          <div style="margin: 0 auto;">{{waterEquipments|optionNSArray(item.sbbh)}}</div>
          <div style="margin: 0 auto;word-wrap: break-word;">{{item.cjsj}}</div>
          <div style="margin: 0 auto;" v-if="userDto.yj=='Y'">
            <button class="btn btn-white btn-default btn-round" style="margin: 0 auto;" v-on:click="download(item,1)">
              <i class="ace-icon fa fa-volume-down red2">下载音频</i>
            </button>
          </div>
<!--          <div style="margin: 0 auto;">-->
<!--            <button class="btn btn-white btn-default btn-round" style="margin: 0 auto;" v-on:click="download(item,2)">-->
<!--              <i class="ace-icon fa fa-volume-down red2">发现头数<span v-bind:id="item.id" style="color: #00aa00;font-weight: bold"></span></i>-->
<!--            </button>-->
<!--          </div>-->
          <div style="margin: 0 auto;" v-if="userDto.yj=='Y'">
            <button class="btn btn-white btn-default btn-round" style="margin: 0 auto;" v-on:click="downloadVedio(item)">
              <i class="ace-icon fa fa-volume-down red2">视频文件</i>
            </button>
          </div>
        </div>
      </div>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

    <div id="img-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 90%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">查看图片</h4>
          </div>
          <div class="modal-body">
<!--            <img :src="equipmentFile.tplj"/>-->
            <div style="margin-bottom: 20px;">
<!--              <button type="button" class="btn btn-white btn-default btn-round" v-on:click="showRealPic()">-->
<!--                查看原图-->
<!--              </button>-->
              <span style="font-size: 18px;margin: 10px 20px;">检测点：{{waterEquipments|optionNSArray(curSbsn)}}</span>
              <span style="font-size: 18px;">采集时间：{{curCjsj}}</span>
              <span style="font-size: 18px;">文件名称：{{curWjmc.substring(curWjmc.lastIndexOf("/")+1,curWjmc.length)}}</span>
            </div>
            <div style="display: flex;">
              <div style="text-align: left;margin: auto;">
                <img v-on:click="beforePic()" v-show="curIndex!=0" src="../../../public/static/image/turnLeft.png"/>
              </div>
              <div :style="{overflow:'auto',textAlign:'center'}">
                <img :src="curTplj" alt="无数据"/>
              </div>
              <div style="text-align: right;margin: auto;">
                <img v-on:click="nextPic()" v-show="curIndex!=equipmentFiles.length-1" src="../../../public/static/image/turnRight.png"/>
              </div>
            </div>
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

    <div id="img-modal-real" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 95%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">原图图片</h4>
          </div>
          <div class="modal-body" :style="{height:+maxHeight+'px',overflow:'auto',textAlign:'center'}">
            <img :src="curTplj" alt="无数据"/>
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

    <div id="cluster-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 95%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">A4聚类</h4>
          </div>
          <div class="modal-body" :style="{height:+maxHeight+'px',overflow:'auto',textAlign:'center',display:'flex'}">
            <div style="width: 80%;">
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
                            检测点：
                          </td>
                          <td style="width: 15%">
                            <select v-model="clusterDto.sbbh" class="form-control">
                              <option value="" selected>请选择</option>
                              <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                            </select>
                          </td>
                          <td style="width: 10%;">
                            开始日期：
                          </td>
                          <td style="width: 15%;">
                            <times :startTime="startTimeCluster" :endTime="endTimeCluster" :svalue="clusterStime" :evalue="clusterEtime" start-id="clusterId" end-id="clusterId"></times>
                          </td>
                          <td  style="width: 20%" class="text-center">
                            <button type="button" v-on:click="listCluster(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                              <i class="ace-icon fa fa-book"></i>
                              查询
                            </button>
                            <button type="button" v-on:click="resetCluster()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                              <i class="ace-icon fa fa-book"></i>
                              查询
                            </button>
                            <button type="button" v-on:click="exportCluster()" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">
                              <i class="ace-icon fa fa-leaf"></i>
                              导出
                            </button>
                            <button type="button" v-on:click="downloadCluster()" class="btn btn-xs btn-info btn-round" style="margin-right: 10px;">
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
                <table id="simple-table-cluster" class="table  table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>检测点</th>
                    <th>设备sn</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>头数</th>
                    <th style="width: 18%;">操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="item in equipFileCluster">
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
                <pagination ref="paginationCluster" v-bind:list="listCluster" v-bind:itemCount="10"></pagination>
              </div>
            </div>
            <div style="width: 20%;">
              <div class="widget-header" style="margin: auto;width: 90%;">
                <h4 class="widget-title">雷达图</h4>
              </div>
              <div v-show="cluster && cluster.jtnr">
                <h2>{{ldTime}}</h2>
                <h4 style="margin-top: 10px">N</h4>
                <div style="width: 350px;height: 380px;margin: auto;" id="echartEvent"></div>
                <h4>S</h4>
                <h4 v-if="zhbht || zhsp">(港珠澳大桥)</h4>
                <p>{{cluster.kssj}}至{{cluster.jssj}}</p>
                <p>保守估计发声头数<span style="color: red">{{cluster.ts}}头</span></p>
                <button v-if="showBtn" type="button" class="btn btn-white btn-default btn-round" v-on:click="reloadLdt">
                  <i class="ace-icon fa fa-refresh"></i>
                  重播
                </button>
              </div>
              <div v-show="!(cluster && cluster.jtnr)">
                <h2>已侦测，未定位！</h2>
              </div>
            </div>
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
import Pagination from "../../components/pagination";
import Times from "../../components/times";
import BigFile from "../../components/big-file";
import Uploads from "../../components/uploads";
export default {
  components: {Pagination,Times,BigFile,Uploads},
  name: "equipment-file",
  data: function (){
    return {
      equipmentFiles:[],
      equipmentFileDto:{},
      equipmentFile:{},
      maxHeight:'',
      curIndex:0,
      curTplj:'',
      curSbsn:'',
      curCjsj:'',
      curWjmc:'',
      waterEquipments:[],
      deptMap: [],
      userDto:null,
      shj:LOCAL_SSBRL,
      picServer:process.env.VUE_APP_PIC_SERVER,
      clusterDto:{},
      equipFileCluster:[],
      clusterStime:'',
      clusterEtime:'',
      cluster:{},
      myChart:null,
      echartsData:[],
      title:[],
      ldTime:'',
      intervalId:'',
      showBtn:false,
      canPlay:false,
      videoHeight:400,
      zhbht:LOCAL_ZHBHT,
      zhsp:LOCAL_VIDEO,
      timeHandle:null,
    }
  },
  mounted() {
    let _this = this;
    _this.userDto = Tool.getLoginUser();
    _this.deptMap = Tool.getDeptUser();
    _this.$refs.pagination.size = 24;
    _this.$refs.paginationCluster.size = 10;
    //_this.list(1);
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8;
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
    exportCluster(){
      let _this = this;
      let param = "";
      if("460100"!=Tool.getLoginUser().deptcode){
        param+="&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(!Tool.isEmpty(_this.clusterDto.sbbh)){
        param+="&sbbh="+_this.clusterDto.sbbh;
      }
      if(!Tool.isEmpty(_this.clusterDto.stime)){
        param+="&stime="+_this.clusterDto.stime;
      }
      if(!Tool.isEmpty(_this.clusterDto.etime)){
        param+="&etime="+_this.clusterDto.etime;
      }
      if(Tool.isEmpty(param)){
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportFileEvent';
      }else{
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportFileEvent?'+param.substring(1,param.length);
      }
    },
    downloadCluster(){
      let _this = this;
      let param = "";
      if("460100"!=Tool.getLoginUser().deptcode){
        param+="&xmbh="+Tool.getLoginUser().xmbh;
      }
      if(!Tool.isEmpty(_this.clusterDto.sbbh)){
        param+="&sbbh="+_this.clusterDto.sbbh;
      }else{
        Toast.error("请选择设备编号！");
        return;
      }
      if(!Tool.isEmpty(_this.clusterDto.stime)){
        param+="&stime="+_this.clusterDto.stime;
      }else{
        Toast.error("请选择开始日期！");
        return;
      }
      if(!Tool.isEmpty(_this.clusterDto.etime)){
        param+="&etime="+_this.clusterDto.etime;
      }else{
        Toast.error("请选择结束日期！");
        return;
      }
      let diff = _this.getDateDiff(_this.clusterDto.stime,_this.clusterDto.etime);
      if(diff>=3){
        Toast.error("日期相差不能超过3天！");
        return;
      }
      window.location.href = process.env.VUE_APP_SERVER + '/monitor/download/audio/downloadEquipmentFileEvent?'+param.substring(1,param.length);
    },
    selectEquipmentCluster(){
      let _this = this;
      if(!Tool.isEmpty(_this.equipmentFileDto.sbbh)){
        _this.clusterDto.sbbh = _this.equipmentFileDto.sbbh;
      }
      if(!Tool.isEmpty(_this.equipmentFileDto.stime)){
        _this.clusterDto.stime = _this.equipmentFileDto.stime;
        _this.clusterStime = _this.equipmentFileDto.stime;
      }
      if(!Tool.isEmpty(_this.equipmentFileDto.etime)){
        _this.clusterDto.etime = _this.equipmentFileDto.etime;
        _this.clusterEtime = _this.equipmentFileDto.etime;
      }
      $("#cluster-modal").modal("show");
      _this.listCluster(1);
    },
    resetCluster(){
      let _this = this;
      _this.clusterDto.sbbh = "";
      _this.clusterDto.stime = "";
      _this.clusterDto.etime = "";
      _this.clusterStime = "";
      _this.clusterEtime = "";
      _this.listCluster(1);
    },
    startTimeCluster(rep){
      let _this = this;
      _this.clusterDto.stime = rep;
      _this.$forceUpdate();
    },
    endTimeCluster(rep){
      let _this = this;
      _this.clusterDto.etime = rep;
      _this.$forceUpdate();
    },
    listCluster(page){
      let _this = this;
      Loading.show();
      _this.clusterDto.page = page;
      _this.clusterDto.size = _this.$refs.paginationCluster.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.clusterDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/listClusters', _this.clusterDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipFileCluster = resp.content.list;
        _this.$refs.paginationCluster.render(page, resp.content.total);
        _this.$nextTick(function (){
          for(let i=0;i<_this.equipFileCluster.length;i++){
            _this.hasVideo(_this.equipFileCluster[i].cjsj,_this.equipFileCluster[i].id);
          }
          if(_this.equipFileCluster.length>0){
            _this.showEcharts(_this.equipFileCluster[0]);
          }
        });
      })
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
    showEcharts(item){
      let _this = this;
      _this.cluster = $.extend({}, item);
      //先消除再创建
      if(_this.myChart!=null){
        _this.myChart.dispose();
      }
      if (_this.intervalId != null) {
        clearInterval(_this.intervalId); //清除计时器
        _this.intervalId = null; //设置为null
      }
      _this.showBtn = false;
      _this.$forceUpdate();
      if(Tool.isEmpty(item.jtnr)){
        //Toast.error("");
        return;
      }
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
    findDeviceInfo(){
      let _this = this;
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001','dqzl':'A1,A4'};
      }else{
        data = {'sblb':'0001','dqzl':'A1,A4','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        _this.waterEquipments = response.data.content;
      })
    },
    showRealPic(){
      $("#img-modal-real").modal("show");
    },
    beforePic(){
      let _this = this;
      _this.curIndex = _this.curIndex-1;
      _this.curTplj = _this.equipmentFiles[_this.curIndex].tplj.replace(/http:\/\/[^/]+/, _this.picServer);
      _this.curSbsn = _this.equipmentFiles[_this.curIndex].sbbh;
      _this.curCjsj = _this.equipmentFiles[_this.curIndex].cjsj;
      _this.curWjmc = _this.equipmentFiles[_this.curIndex].tplj;
      _this.$forceUpdate();
    },
    nextPic(){
      let _this = this;
      _this.curIndex = _this.curIndex+1;
      _this.curTplj = _this.equipmentFiles[_this.curIndex].tplj.replace(/http:\/\/[^/]+/, _this.picServer);
      _this.curSbsn = _this.equipmentFiles[_this.curIndex].sbbh;
      _this.curCjsj = _this.equipmentFiles[_this.curIndex].cjsj;
      _this.curWjmc = _this.equipmentFiles[_this.curIndex].tplj;
      _this.$forceUpdate();
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.equipmentFileDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.equipmentFileDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.equipmentFileDto.page=page;
      _this.equipmentFileDto.size=_this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode && _this.shj){
        _this.equipmentFileDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/lists',_this.equipmentFileDto).then((response)=>{
        let resp = response.data;
        _this.equipmentFiles = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
        Loading.hide();
      })
    },
    /**
     * 查看原图
     */
    checkImg(item,index){
      let _this = this;
      _this.curIndex = index;
      _this.curTplj = item.tplj.replace(/http:\/\/[^/]+/, _this.picServer);
      _this.curSbsn = item.sbbh;
      _this.curCjsj = item.cjsj;
      _this.curWjmc = item.tplj;
      _this.equipmentFile = $.extend({}, item);
      _this.$forceUpdate();
      $("#img-modal").modal("show");
    },
    /**
     * 下载
     */
    download(obj,type){
      let _this = this;
      let lj = '';
      if(type==1){
        lj=obj.tplj.substring(0,obj.tplj.lastIndexOf('.')+1)+'wav';
      }else if(type==2){
        lj=obj.tplj.substring(0,obj.tplj.lastIndexOf('.')+1)+'txt';
      }
      let paramsStr = "fileUrl="+lj.substring(0,lj.lastIndexOf("/")+1)+"&fileName="+lj.substring(lj.lastIndexOf("/")+1,lj.length);
       let url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFile?'+paramsStr;
      _this.$ajax.get(url).then((response)=>{
        if((response.data && typeof response.data == 'string'&& response.data.includes("系统异常"))||(response.data.message && response.data.message.includes("系统异常"))){
          Toast.error("未找到该文件！");
        }else{
          window.location.href = url;
        }
      })
    },
    downloadVedio(item){
      let _this = this;
      let url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downZipByWjmc?wjmc='+item.wjmc;
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
    downloadVideoZip(){
      let _this = this;
      if(Tool.isEmpty(_this.equipmentFileDto.sbbh)){
        Toast.error("请选择设备编号！");
      }
      if(Tool.isNotEmpty(_this.equipmentFileDto.etime)&&Tool.isEmpty(_this.equipmentFileDto.stime)){
        Toast.error("请选择采集开始日期！");
      }
      let paramsStr = "";
      if(Tool.isNotEmpty(_this.equipmentFileDto.stime)){
        paramsStr = paramsStr + "&stime="+_this.equipmentFileDto.stime;
      }
      if(Tool.isNotEmpty(_this.equipmentFileDto.etime)){
        paramsStr = paramsStr + "&etime="+_this.equipmentFileDto.etime;
      }
      if(Tool.isNotEmpty(_this.equipmentFileDto.sbbh)){
        paramsStr = paramsStr + "&sbbh="+_this.equipmentFileDto.sbbh;
      }
      let url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downVideoZip?'+paramsStr;
      _this.$ajax.get(url).then((response)=>{
        if(response.data && response.data.message && response.data.message.includes("系统异常")){
          Toast.error("系统异常，请联系管理员！");
        }else if(response.data && response.data.includes("未找到")){
          Toast.error("未找到对应音频！");
        }else{
          window.location.href = url;
        }
      })
    }
  }
}
</script>
<style scoped>
video {
  width: 100%;
  height: auto;
  margin-top: 10px;
}
</style>