<template>
  <div>

    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">图片管理查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  采集时间：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="eqstime" end-id="eqetime"></times>
                </td>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
<!--                  <input class="form-control" type="text"  v-model="equipmentFileDto.sbbh"/>-->
                  <select v-model="equipmentFileDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td style="width: 20%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round">
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
          <th>监测点</th>
          <th>设备名称</th>
          <th>设备sn</th>
          <th>时间</th>
          <th>类型</th>
          <th style="width: 10%;">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in equipmentFiles">
          <td>{{deptMap|optionMapKV(item.deptcode)}}</td>
          <td>{{waterEquipments|optionNSArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{moment(item.cjsj).format("YYYY-MM-DD HH:mm:ss")}}</td>
          <td><span v-if="item.tplj.includes('predation')">捕食</span><span v-else>出现</span></td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="detail(item)" class="btn btn-xs btn-info" title="详情">
                <i class="ace-icon fa fa-list bigger-120"></i>
              </button>
              <button v-if="userDto.yj=='Y'"  v-on:click="downloadFile(item)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="ace-icon fa fa-volume-down bigger-120">下载文件</i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>

    <div id="img-modal-real" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 95%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">原图图片</h4>
          </div>
          <div class="modal-body" :style="{height:+maxHeight+'px',overflow:'auto',textAlign:'center'}">
            <img :src="curTplj"/>

            <table class="table  table-bordered table-hover" style="margin-top: 20px;">
              <thead>
                <tr>
                  <th>类型</th>
                  <th>路径</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in details">
                  <td>
                    <span v-if="item.tplj.includes('txt')">文件</span>
                    <span v-if="item.tplj.includes('wav')">音频</span>
                    <span v-if="item.tplj.includes('png') || item.tplj.includes('jpg')">图片</span>
                  </td>
                  <td>{{item.tplj}}</td>
                  <td>
                    <button v-if="userDto.yj=='Y'" class="btn btn-white btn-default btn-round" style="margin: 0 auto;" v-on:click="downloadFile(item)">
                      <i class="ace-icon fa fa-volume-down red2">下载文件</i>
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
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

export default {
  name: 'equipment-file-table',
  components:{Pagination,Times},
  data: function (){
    return {
      equipmentFile:{},
      equipmentFileDto:{},
      equipmentFiles:[],
      deptMap:[],
      waterEquipments:[],
      maxHeight:'',
      curTplj:'',
      details:[],
      userDto:null,
      shj:LOCAL_SSBRL,
      picServer:process.env.VUE_APP_PIC_SERVER
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8;
    _this.userDto = Tool.getLoginUser();
    _this.deptMap = Tool.getDeptUser();
    _this.list(1);
    _this.$refs.pagination.size = 10;
    _this.findDeviceInfo();
  },
  methods: {
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
      })
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.equipmentFileDto.page=page;
      _this.equipmentFileDto.size=_this.$refs.pagination.size;
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.equipmentFileDto.xmbh=Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/lists', _this.equipmentFileDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.equipmentFiles = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    //详情
    detail(item){
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/detail/'+item.wjmc, {}).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.details = resp.content;
        for(let i=0;i<_this.details.length;i++){
          let tempTplj = _this.details[i].tplj;
          if(tempTplj.includes("jpg") || tempTplj.includes("png")){
            _this.curTplj = tempTplj.replace(/http:\/\/[^/]+/, _this.picServer);
            _this.$forceUpdate();
          }
        }
        $("#img-modal-real").modal("show");
      })
    },
    //下载文件
    downloadFile(obj){
      let _this = this;
      let paramsStr = "fileUrl="+obj.tplj.substring(0,obj.tplj.lastIndexOf("/")+1)+"&fileName="+obj.tplj.substring(obj.tplj.lastIndexOf("/")+1,obj.tplj.length);
      let url = "";
      if(_this.shj){
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFile53?'+paramsStr;
      }else{
        url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFile?'+paramsStr;
      }
      console.log(url);
      window.location.href = url;
    }
  }
}
</script>