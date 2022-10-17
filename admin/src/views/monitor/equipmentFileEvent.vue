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
          <th>所属机构</th>
          <th>检测点</th>
          <th>设备sn</th>
          <th>开始时间</th>
          <th>结束时间</th>
          <th>头数</th>
          <th style="width: 12%;">操作</th>
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
              <button v-on:click="downloadVedio(item.id)" class="btn btn-xs btn-info" style="margin-left: 10px;">
                <i class="ace-icon fa fa-volume-down bigger-120">下载视频</i>
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
          <div class="modal-body" style="width: 400px;height: 550px;margin: auto;text-align: center" >
            <div style="width: 400px;height: 450px;" id="echartEvent"></div>
            <p>{{equipmentFileEvent.kssj}}至{{equipmentFileEvent.jssj}}</p>
            <p>保守估计发生头数<span style="color: red">{{equipmentFileEvent.ts}}头</span></p>
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
  name: '',
  components: {Pagination,Times},
  data: function (){
    return {
      equipmentFileEvent:{},
      equipmentFileEventDto:{},
      equipmentFileEvents:[],
      deptMap: [],
      waterEquipments: [],
      myChart:null,
      intervalId:''
    }
  },
  mounted() {
    let _this = this;
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
    });
  },
  methods: {
    showEcharts(item){
      let _this = this;
      _this.equipmentFileEvent = $.extend({}, item);
      _this.$forceUpdate();
      _this.initEcharts();
      let list = [];
      let title = [];
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
                item.push([1.5,jdarr[j].split(":")[1]]);
              }
            }
            list.push(item);
            title.push(rqarr[0]+"-"+rqarr[1]+"-"+rqarr[2]+" "+rqarr[3]+":"+rqarr[4]+":"+rqarr[5]);
          }
        }
      }
      let k=0;
      _this.intervalId = setInterval(function () {
        if(k==list.length){
          k=0;
        }
        _this.myChart.setOption({
          series: [
            {
              data:list[k]
            }
          ],
          title: {
            text: title[k],
            left:"19%"
          }
        });
        k=k+1;
      }, 4000);
      $("#echart-modal").modal("show");
    },
    initEcharts(){
      let _this = this;
      let option = {
        polar: {
          center: ['45%', '50%']
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
        animationDuration: 400
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
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileEvent/list', _this.equipmentFileEventDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFileEvents = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    downloadFile(id){
      let url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downAudioFileById?id='+id;
      console.log(url);
      window.location.href = url;
    },
    downloadVedio(id){
      let _this = this;
      let url = process.env.VUE_APP_SERVER + '/monitor/download/audio/downZipById?id='+id;
      _this.$ajax.get(url).then((response)=>{
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