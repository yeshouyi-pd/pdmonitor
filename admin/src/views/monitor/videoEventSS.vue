<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">实时视频分析查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  核查状态：
                </td>
                <td style="width: 15%">
                  <select v-model="videoEventDto.sm" class="form-control">
                    <option value="" selected>请选择</option>
                    <option value="1">已核查</option>
                    <option value="0">未核查</option>
                  </select>
                </td>
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
                  <!--                  <button type="button" v-on:click="exportExcle()" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">-->
                  <!--                    <i class="ace-icon fa fa-leaf"></i>-->
                  <!--                    导出-->
                  <!--                  </button>-->
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
          <th style="width: 18%;">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in videoEvents">
          <td>{{deptMap|optionMapKV(item.bz)}}</td>
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.kssj}}</td>
          <td>{{item.jssj}}</td>
          <td>
            <button v-on:click="watchVideo(item.wjlj,item.id,item.sm)" class="btn btn-xs btn-info" style="margin-left: 10px;">
              <i class="ace-icon fa fa-volume-down bigger-120">查看分析视频</i>
            </button>
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
            <h4 class="modal-title">视频回放</h4>
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
  name: 'video-event-ss',
  components: {Pagination,Times},
  data: function (){
    return {
      equipmentFileEvent:{},
      videoEventDto:{},
      videoEvents:[],
      deptMap: [],
      waterEquipments: [],
      videoHeight:550,
      userDto:null,
      timeHandle:null,
      canPlay:false,
      videoDatas:[],
      wjlj:''
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
    $('#video-modal').on('hidden.bs.modal', function () {
      clearTimeout(_this.timeHandle);
    });
  },
  methods: {
    exportExcle(){
      let _this = this;
      let param = "";
      if(!Tool.isEmpty(_this.videoEventDto.sbbh)){
        param+="&sbbh="+_this.videoEventDto.sbbh;
      }
      if(!Tool.isEmpty(_this.videoEventDto.stime)){
        param+="&stime="+_this.videoEventDto.stime;
      }
      if(!Tool.isEmpty(_this.videoEventDto.etime)){
        param+="&etime="+_this.videoEventDto.etime;
      }
      if(Tool.isEmpty(param)){
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportFileEvent';
      }else{
        window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportFileEvent?'+param.substring(1,param.length);
      }
    },
    //历史回放
    watchVideo(wjlj,id,sm){
      let _this = this;
      $("#playbox").empty();
      _this.wjlj = wjlj;
      let video = document.createElement("video");
      video.setAttribute("id","video"+id);
      video.setAttribute("width","100%");
      video.setAttribute("height","450px");
      video.setAttribute("controls","controls");
      video.src = wjlj;
      document.getElementById('playbox').appendChild(video);
      if(!Tool.isEmpty(sm)&&sm!='1'){
        let div = document.createElement("div");
        div.setAttribute("id","fx"+id);
        div.setAttribute("style","margin-bottom:10px;");
        div.innerHTML = "<button name='"+id+"' id=\"checkPass\" type=\"button\" class=\"btn btn-lg btn-success\" >\n" +
            "              <i class=\"ace-icon fa fa-check\"></i>\n" +
            "              核查通过\n" +
            "            </button>\n" +
            "            <button name='"+id+"' id=\"checkUnpass\" type=\"button\" class=\"btn btn-lg btn-danger\" style=\"margin-left:20px;\">\n" +
            "              <i class=\"ace-icon fa fa-times\"></i>\n" +
            "              核查不通过\n" +
            "            </button>";
        document.getElementById('playbox').appendChild(div);
        document.getElementById("checkPass").onclick = function (params) {
          _this.checkSave(params.target.getAttribute('name'),'1');
        }
        document.getElementById("checkUnpass").onclick = function (params) {
          _this.checkSave(params.target.getAttribute('name'),'2');
        }
      }
      $("#video-modal").modal("show");
      video.play();
    },
    checkSave(id,sm){
      let _this = this;
      _this.ischeck = true;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/videoEvent/checkSave', {'id':id,'sm':sm}).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if(resp.success){
          if(sm=='2'){
            $("#video"+id).remove();
          }
          Toast.success("保存成功");
          $("#fx"+id).empty();
        }else{
          Loading.hide();
          Toast.error("保存失败");
        }
      })
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
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
      _this.videoEventDto.sfysp = 2;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/videoEvent/listSs', _this.videoEventDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.videoEvents = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>
<style>
.myVideo-dimensions{
  width: 700px;
}
</style>