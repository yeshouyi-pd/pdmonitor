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
                      设备名称：
                    </td>
                    <td style="width: 15%;">
                      <select v-model="equipmentFileDto.sbbh" class="form-control" id="form-field-select-1">
                        <option value="" selected>请选择</option>
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
            <img alt="无图片" :src="item.tplj.substring(0,item.tplj.lastIndexOf('.')+1)+'jpg'" style="width: 100px;height: 200px;cursor: pointer;" v-on:click="checkImg(item,index)">
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
      <div class="modal-dialog" role="document" style="width: 55%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">查看图片</h4>
          </div>
          <div class="modal-body">
<!--            <img :src="equipmentFile.tplj"/>-->
            <div>
              <button type="button" class="btn btn-white btn-default btn-round" v-on:click="showRealPic()">
                查看原图
              </button>
              <span style="font-size: 18px;margin: 10px 20px;">设备名称：{{waterEquipments|optionNSArray(curSbsn)}}</span>
              <span style="font-size: 18px;">采集时间：{{curCjsj}}</span>
              <span style="font-size: 18px;">文件名称：{{curWjmc.substring(curWjmc.lastIndexOf("/")+1,curWjmc.length)}}</span>
            </div>
            <div style="display: flex;">
              <div style="text-align: left;margin: auto;">
                <img v-on:click="beforePic()" v-show="curIndex!=0" src="../../../public/static/image/turnLeft.png"/>
              </div>
              <div style="text-align: center;width: 500px;overflow-x: auto;">
                <img :src="curTplj" :style="{height:+maxHeight-100+'px'}"  alt="无数据"/>
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

<!--    <div class="form-group">
      <div class="col-sm-10">
        <Uploads    v-bind:suffixs="['mp4']"
                    v-bind:use="'1'"
                    v-bind:mainid="'1'" ></Uploads>
      </div>
    </div>-->
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
      userDto:null,
      shj:LOCAL_SSBRL
    }
  },
  mounted() {
    let _this = this;
    _this.userDto = Tool.getLoginUser();
    _this.$refs.pagination.size = 24;
    _this.list(1);
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8;
    _this.findDeviceInfo();
  },
  methods: {
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
      _this.curTplj = _this.equipmentFiles[_this.curIndex].tplj.substring(0,_this.equipmentFiles[_this.curIndex].tplj.lastIndexOf('.')+1)+'jpg';
      _this.curSbsn = _this.equipmentFiles[_this.curIndex].sbbh;
      _this.curCjsj = _this.equipmentFiles[_this.curIndex].cjsj;
      _this.curWjmc = _this.equipmentFiles[_this.curIndex].tplj;
      _this.$forceUpdate();
    },
    nextPic(){
      let _this = this;
      _this.curIndex = _this.curIndex+1;
      _this.curTplj = _this.equipmentFiles[_this.curIndex].tplj.substring(0,_this.equipmentFiles[_this.curIndex].tplj.lastIndexOf('.')+1)+'jpg';
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
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/lists',_this.equipmentFileDto).then((response)=>{
        let resp = response.data;
        _this.equipmentFiles = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
        Loading.hide();
        //_this.getTs(_this.equipmentFiles);
      })
    },
    getTs(equipmentFiles){
      let _this = this;
      for(let i=0;i<equipmentFiles.length;i++){
        let id = equipmentFiles[i].id;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/getTs/'+equipmentFiles[i].wjmc+"/"+equipmentFiles[i].sbbh).then((response)=>{
          Loading.hide();
          let resp = response.data;
          let item = resp.content;
          if(!Tool.isEmpty(item)){
            document.getElementById(id).innerText="("+item.ts+")";
          }else{
            document.getElementById(id).innerText="";
          }
        })
      }
    },
    /**
     * 查看原图
     */
    checkImg(item,index){
      let _this = this;
      _this.curIndex = index;
      _this.curTplj = item.tplj.substring(0,item.tplj.lastIndexOf('.')+1)+'jpg';
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
      // let url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFile?'+paramsStr;
      let url = "";
      if(_this.shj){
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFile53?'+paramsStr;
      }else{
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFile?'+paramsStr;
      }
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
      //Loading.show();
      let url = "";
      if(_this.shj){
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downVideoZip53?'+paramsStr;
      }else{
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downVideoZip?'+paramsStr;
      }
      _this.$ajax.get(url).then((response)=>{
        //Loading.hide();
        if(response.data && response.data.message && response.data.message.includes("系统异常")){
          Toast.error("系统异常，请联系管理员！");
        }else if(response.data && response.data.includes("未找到")){
          Toast.error("未找到对应视频！");
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