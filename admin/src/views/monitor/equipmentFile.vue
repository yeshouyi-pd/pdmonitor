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
                      设备编号：
                    </td>
                    <td style="width: 15%;">
                      <select v-model="equipmentFileDto.sbbh" class="form-control" id="form-field-select-1">
                        <option value="" selected>请选择</option>
                        <option v-for="item in sbbhs" :value="item">{{item}}</option>
                      </select>
                    </td>
                    <td style="width: 15%;">
                      采集时间：
                    </td>
                    <td style="width: 25%;">
                      <Times v-bind:startTime="startTime" v-bind:endTime="endTime"></Times>
                    </td>
                    <td colspan="2" class="text-center">
                      <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round">
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
        <div v-for="item in equipmentFiles" style="margin:20px;width: 150px;height: 250px;display: flex;flex-wrap: wrap;">
          <div style="text-align: center;width: 150px;">
            <img :src="item.tplj" style="height: 200px;cursor: pointer;" v-on:click="checkImg(item)">
          </div>
          <div style="margin: 0 auto;">{{item.sbbh}}</div>
          <div style="margin: 0 auto;">{{item.cjsj}}</div>
          <div style="margin: 0 auto;">
            <button class="btn btn-white btn-default btn-round" style="margin: 0 auto;" v-on:click="downloadAudio(item)">
              <i class="ace-icon fa fa-volume-down red2">下载音频</i>
            </button>
          </div>
        </div>
      </div>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

    <div id="img-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">查看原图</h4>
          </div>
          <div class="modal-body" :style="{height:+maxHeight+'px',overflow:'auto'}">
            <img :src="equipmentFile.tplj"/>
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
import Times from "../../components/time";
export default {
  components: {Pagination,Times},
  name: "equipment-file",
  data: function (){
    return {
      equipmentFiles:[],
      equipmentFileDto:{},
      equipmentFile:{},
      maxHeight:'',
      sbbhs:[]
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 24;
    _this.list(1);
    _this.findSbbh();
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8;
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
    checkImg(item){
      let _this = this;
      _this.equipmentFile = $.extend({}, item);
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
      // console.log(obj.substring(0,obj.length-3)+"wav");
      // //window.location.href = obj.substring(0,obj.length-3)+"wav";
      //window.location = "http://146.56.226.176:8088/tempData/888888/2021_04_30_19_52_09.wav";
      //window.open("http://146.56.226.176:8088/tempData/888888/2021_04_30_19_52_09.wav")
      var eleLink = document.createElement('a');
      eleLink.download = obj.tplj.substring(0,obj.tplj.length-3)+"wav";
      eleLink.style.display = 'none';
      // 字符内容转变成blob地址
      var blob = new Blob(['wav']);
      eleLink.href = URL.createObjectURL(blob);
      // 触发点击
      document.body.appendChild(eleLink);
      eleLink.click();
      // 然后移除
      document.body.removeChild(eleLink);
    }
  }
}
</script>