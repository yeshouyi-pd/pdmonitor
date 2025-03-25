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
                  日期：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="uavStartTime" end-id="uavEndTime"></times>
                </td>
                <td style="width:20%" class="text-center">
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

    <div class="video-box">
      <div class="video-item" v-for="(item,index) in uavFlyVideoList">
        <video style="width: 100%;height: 90%" :src="item.videoUrl" controls autoplay></video>
        <div>{{item.cjsj}}</div>
      </div>
    </div>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>



  </div>
</template>
<script>
import Pagination from "../../components/pagination";
import Times from "../../components/times";
export default {
  name:'uav-fly-video',
  components:{Pagination,Times},
  data: function (){
    return {
      uavFlyVideo:{},
      uavFlyVideoDto:{},
      uavFlyVideoList:[]
    }
  },
  mounted() {
    let _this = this;
    _this.list(1);
    _this.$refs.pagination.size = 10;
  },
  methods: {
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.uavFlyVideoDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.uavFlyVideoDto.etime = rep;
      _this.$forceUpdate();
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.uavFlyVideoDto.page=page;
      _this.uavFlyVideoDto.size=_this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/uavFlyVideo/list', _this.uavFlyVideoDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.uavFlyVideoList = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    //详情
    detail(item) {
      let _this = this;
      $("#modal-video").modal("show");
      _this.uavFlyVideo = item;
    }
  }
}
</script>
<style scoped>
.video-box{
  display: flex;
  flex-wrap: wrap;
  text-align: center;
  font-size: 16px;
  font-weight: bold;
}
.video-item{
  width:19.5%;
  height: 295px;
  padding: 5px;
}
</style>