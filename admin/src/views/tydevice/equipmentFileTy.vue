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
                  <Dategreater @methodName="dateMethod" style="width:100%" idValue="tyDateStr"   :setValue="curDateStr"></Dategreater>
                </td>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
                  <select v-model="curSbsn" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in equipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td colspan="2" class="text-center">
                  <button type="button" v-on:click="getGpsBySbsn()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
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
    <div id="mapDiv" style="position:absolute;" :style="{height:+maxHeight+'px',width:+maxWidth+'px'}"></div>
  </div>
</template>
<script>
import Dategreater from "../../components/dategreater";
export default {
  components: {Dategreater},
  name: 'equipment-file-ty',
  data: function (){
    return {
      equipments:[],
      curSbsn:'',
      curDateStr:'',
      jd:0,
      wd:0,
      map:null,
      zoom:11,
      markerClusterer:null,
      markers:[],
      maxHeight:'',
      maxWidth:''
    }
  },
  created() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8-110;
    let w = document.documentElement.clientWidth || document.body.clientWidth;
    _this.maxWidth = w-230;
    _this.curDateStr = Tool.dateFormat("yyyy-MM-dd");
    _this.getEquipmentByTy();
  },
  mounted() {
    let _this = this;
    _this.initMap();
  },
  methods: {
    dateMethod(rep){
      let _this = this;
      _this.curDateStr = rep;
      _this.$forceUpdate();
    },
    //获取拖曳设备
    getEquipmentByTy(){
      let _this = this;
      Loading.show();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/getTyEquipment').then((res)=>{
        Loading.hide();
        let response = res.data;
        _this.equipments = response.content;
        if(_this.equipments.length>0){
          _this.curSbsn = _this.equipments[0].sbsn;
          let arr = _this.equipments[0].gps.split(",");
          _this.jd = Number(arr[0]);
          _this.wd = Number(arr[1]);
          _this.map.centerAndZoom(new TLngLat(_this.jd,_this.wd),_this.zoom);
          _this.getGpsBySbsn();
        }
      })
    },
    getGpsBySbsn(){
      let _this = this;
      _this.map.clearOverLays();
      let obj = {
        "rq":_this.curDateStr,
        "sbbh":_this.curSbsn
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileTyToday/selectTodayGps', obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        let gpslist = resp.content;
        if(gpslist && gpslist.length>0){
          console.log(gpslist.length/2);
          _this.map.panTo(new TLngLat(Number(gpslist[parseInt(gpslist.length/2)].gps.split(",")[0]),Number(gpslist[parseInt(gpslist.length/2)].gps.split(",")[1])));
          gpslist.forEach(function (item){
            var icon = new TIcon("https://webapi.amap.com/theme/v1.3/markers/n/mark_bs.png",new TSize(19,27),{anchor:new TPixel(9,27)});
            let gps = item.gps.split(",");
            let lnglat = new TLngLat(Number(gps[0]),Number(gps[1]));
            let marker = new TMarker(lnglat,{icon:icon});
            marker.setTitle(item.ts);
            _this.map.addOverLay(marker);
          })
        }
      })
    },
    initMap(){
      let _this = this;
      //初始化地图对象
      _this.map=new TMap("mapDiv");
      //设置显示地图的中心点和级别
      if(_this.wd!=0&&_this.wd!=0){
        _this.map.centerAndZoom(new TLngLat(_this.jd,_this.wd),_this.zoom);
      }
      //允许鼠标滚轮缩放地图
      _this.map.enableHandleMouseScroll();
    },
    initMapMarkers(){
      let _this = this;
      let config = {
        markers:_this.markers
      };
      //最简单的用法，生成一个marker数组，然后调用markerClusterer类即可
      _this.markerClusterer = new TMarkerClusterer(_this.map,config);
    }
  }
}
</script>