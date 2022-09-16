<template>
  <div>
    <div class="row">
      <div class="col-xl-3 col-sm-3 col-12">
        <div class="card">
          <div class="card-body" style="border: 1px solid rgb(255, 238, 195);">
            <div data-v-03643b6e="" class="card-num" style="background-color: rgb(255, 238, 195);">409</div>
            <div data-v-03643b6e="" class="card-text">机动业务预约数量</div>
          </div>
        </div>
      </div>
      <div data-v-03643b6e="" class="col-xl-3 col-sm-3 col-12">
        <div data-v-03643b6e="" class="card">
          <div data-v-03643b6e="" class="card-body" style="border: 1px solid rgb(195, 227, 238);">
            <div data-v-03643b6e="" class="card-num" style="background-color: rgb(195, 227, 238);">194</div>
            <div data-v-03643b6e="" class="card-text">驾驶业务预约数量</div>
          </div>
        </div>
      </div>
      <div data-v-03643b6e="" class="col-xl-3 col-sm-3 col-12">
        <div data-v-03643b6e="" class="card">
          <div data-v-03643b6e="" class="card-body" style="border: 1px solid rgb(206, 255, 213);">
            <div data-v-03643b6e="" class="card-num" style="background-color: rgb(206, 255, 213);">730</div>
            <div data-v-03643b6e="" class="card-text">违法业务预约数量</div>
          </div>
        </div>
      </div>
      <div data-v-03643b6e="" class="col-xl-3 col-sm-3 col-12">
        <div data-v-03643b6e="" class="card">
          <div data-v-03643b6e="" class="card-body" style="border: 1px solid rgb(252, 202, 255);">
            <div data-v-03643b6e="" class="card-num" style="background-color: rgb(252, 202, 255);">0</div>
            <div data-v-03643b6e="" class="card-text">查验业务预约数量</div>
          </div>
        </div>
      </div>
    </div>
    <div class="widget-box left-top" style="text-align: center">
      <div class="widget-header ">
        <h5 class="widget-title">
          <i class="ace-icon fa fa-tachometer  "></i>
          监测站设备分布图
        </h5>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <table class="left-top-choose">
            <tbody>
            <tr>
              <td style="width: 80%">
                <select v-model="curSbsn" class="form-control">
                  <option value="" selected>请选择</option>
                  <option v-for="item in equipments" :value="item.sbsn">{{item.sbmc}}</option>
                </select>
              </td>
              <td >
                <button type="button" v-on:click="getGpsBySbsn()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                  <i class="ace-icon fa fa-book"></i>
                  查询
                </button>
              </td>
            </tr>
            </tbody>
          </table>
          <div id="mapDiv" style="position:absolute;" class="top-map"></div>
        </div><!-- /.widget-main -->
      </div><!-- /.widget-body -->
    </div><!-- /.widget-box -->

  </div>
</template>
<script>
import Dategreater from "../../components/dategreater";
export default {
  components: {Dategreater},
  name: 'largemonitors-ty',
  data: function (){
    return {
      equipments:[],
      curSbsn:'',
      curDateStr:'',
      jd:0,
      wd:0,
      map:null,
      zoom:12,
      markerClusterer:null,
      markers:[],
      maxHeight:'',
      maxWidth:''
    }
  },
  created() {
    let _this = this;
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
          gpslist.forEach(function (item){
            var icon = new TIcon("https://webapi.amap.com/theme/v1.3/markers/n/mark_bs.png",new TSize(19,27),{anchor:new TPixel(9,27)});
            let lnglat = new TLngLat(Number(item[0]),Number(item[1]));
            let marker = new TMarker(lnglat,{icon:icon});
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
<style scoped>
.left-top{
  width: 1100px;
  height: 580px;
  background-color: #fff;
}
.left-top-choose{
  font-size: 1.1em;
  position: absolute;
  z-index: 9999;
  left: 300px;
}
.top-map{
  width: 1080px;
  height: 520px;
}
</style>