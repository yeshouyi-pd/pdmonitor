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
                <table style="font-size: 1.1em;width:80%" class="text-right" >
                  <tbody>
                  <tr>
                    <td style="width: 15%;">
                      设备名称：
                    </td>
                    <td style="width: 15%;">
                      <select v-model="equipmentFileDto.sbbh" class="form-control" id="form-field-select-1">
                        <option value="" selected>请选择</option>
                        <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                      </select>
                    </td>
                    <td style="width: 15%;">
                      采集时间：
                    </td>
                    <td style="width: 25%;">
                      <time-range-picker v-bind:startTime="startTime" v-bind:endTime="endTime"></time-range-picker>
                    </td>
                    <td colspan="2" class="text-center">
                      <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                        <i class="ace-icon fa fa-book"></i>
                        查询
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
        <div v-for="(item,index) in equipmentFiles" style="margin:20px;width: 150px;height: 250px;display: flex;flex-wrap: wrap;">
          <div style="text-align: center;width: 150px;">
            <img :src="item.tplj" style="height: 200px;cursor: pointer;" v-on:click="checkImg(item,index)">
          </div>
          <div style="margin: 0 auto;">{{waterEquipments|optionNSArray(item.sbbh)}}</div>
          <div style="margin: 0 auto;">{{item.cjsj}}</div>
          <div style="margin: 0 auto;" v-if="item.hasAudio">
            <button class="btn btn-white btn-default btn-round" style="margin: 0 auto;" v-on:click="downloadAudio(item)">
              <i class="ace-icon fa fa-volume-down red2">下载音频</i>
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
            </div>
            <div style="display: flex;">
              <div style="text-align: left;margin: auto;">
                <img v-on:click="beforePic()" v-show="curIndex!=0" src="../../../public/static/image/turnLeft.png"/>
              </div>
              <div style="text-align: center;">
                <img :src="curTplj" :style="{height:+maxHeight-100+'px'}"/>
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
      <div class="modal-dialog" role="document" style="width: 55%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">原图图片</h4>
          </div>
          <div class="modal-body" :style="{height:+maxHeight+'px',overflow:'auto',textAlign:'center'}">
            <img :src="curTplj"/>
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
import TimeRangePicker from "../../components/timeRangePicker";
import BigFile from "../../components/big-file";
import Uploads from "../../components/uploads";
export default {
  components: {Pagination,TimeRangePicker,BigFile,Uploads},
  name: "equipment-file",
  data: function (){
    return {
      equipmentFiles:[],
      equipmentFileDto:{},
      equipmentFile:{},
      maxHeight:'',
      sbbhs:[],
      curIndex:0,
      curTplj:'',
      curSbsn:'',
      curCjsj:'',
      waterEquipments:[],
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 24;
    _this.list(1);
    _this.findSbbh();
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8;
    _this.findDeviceInfo();
  },
  methods: {
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', {'sblb':'0001'}).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
      })
    },
    showRealPic(){
      $("#img-modal-real").modal("show");
    },
    beforePic(){
      let _this = this;
      _this.curIndex = _this.curIndex-1;
      _this.curTplj = _this.equipmentFiles[_this.curIndex].tplj;
      _this.curSbsn = _this.equipmentFiles[_this.curIndex].sbbh;
      _this.curCjsj = _this.equipmentFiles[_this.curIndex].cjsj;
      _this.$forceUpdate();
    },
    nextPic(){
      let _this = this;
      _this.curIndex = _this.curIndex+1;
      _this.curTplj = _this.equipmentFiles[_this.curIndex].tplj;
      _this.curSbsn = _this.equipmentFiles[_this.curIndex].sbbh;
      _this.curCjsj = _this.equipmentFiles[_this.curIndex].cjsj;
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
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/list',_this.equipmentFileDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFiles = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    findSbbh(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/findSbbh', {}).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.sbbhs = resp.content;
      })
    },
    /**
     * 查看原图
     */
    checkImg(item,index){
      let _this = this;
      _this.curIndex = index;
      _this.curTplj = item.tplj;
      _this.curSbsn = item.sbbh;
      _this.curCjsj = item.cjsj;
      _this.equipmentFile = $.extend({}, item);
      _this.$forceUpdate();
      $("#img-modal").modal("show");
    },
    /**
     * 下载音频
     */
    downloadAudio(obj){
      let _this = this;
      if(!obj.hasAudio){
        Toast.warning("该图片没有对应的音频文件！");
        return;
      }
      let paramsStr = "fileUrl="+obj.tplj.substring(0,obj.tplj.lastIndexOf("/")+1)+"&fileName="+obj.tplj.substring(obj.tplj.lastIndexOf("/")+1,obj.tplj.length-3)+"wav";
      let url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFile?'+paramsStr;
      console.log(url);
      window.location.href = url;
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