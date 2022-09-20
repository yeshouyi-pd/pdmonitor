<template>
  <div class="ty-body">
    <div class="row">
      <div class="col-xl-3 col-sm-3 col-12">
        <div class="card">
          <div class="card-body" style="border: 1px solid rgb(255, 238, 195);">
            <div class="card-num" style="background-color: rgb(255, 238, 195);">{{topData.cxcs}}</div>
            <div class="card-text">侦测次数</div>
          </div>
        </div>
      </div>
      <div class="col-xl-3 col-sm-3 col-12">
        <div class="card">
          <div class="card-body" style="border: 1px solid rgb(195, 227, 238);">
            <div class="card-num" style="background-color: rgb(195, 227, 238);">{{topData.bscs}}</div>
            <div class="card-text">捕食次数</div>
          </div>
        </div>
      </div>
      <div class="col-xl-3 col-sm-3 col-12">
        <div class="card">
          <div class="card-body" style="border: 1px solid rgb(206, 255, 213);">
            <div class="card-num" style="background-color: rgb(206, 255, 213);">{{topData.jlcs}}</div>
            <div class="card-text">聚类次数</div>
          </div>
        </div>
      </div>
      <div class="col-xl-3 col-sm-3 col-12">
        <div class="card">
          <div class="card-body" style="border: 1px solid rgb(252, 202, 255);">
            <div class="card-num" style="background-color: rgb(252, 202, 255);">{{topData.ts}}</div>
            <div class="card-text">最多豚数</div>
          </div>
        </div>
      </div>
    </div>
    <div style="display: flex">
      <div class="left">
        <table class="left-choose">
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
        <div id="mapDiv" style="position:absolute;" class="left-map"></div>
      </div>
      <div class="right">

      </div>
    </div>
  </div>
</template>
<script>

export default {
  components: {},
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
      maxWidth:'',
      topData:{}
    }
  },
  created() {
    let _this = this;
    _this.curDateStr = Tool.dateFormat("yyyy-MM-dd");
    _this.getEquipmentByTy();
    _this.getTopData();
  },
  mounted() {
    let _this = this;
    _this.initMap();
  },
  methods: {
    //获取当日出现次数，捕食次数，聚类次数，最多豚数
    getTopData(){
      let _this = this;
      Loading.show();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileTyToday/getDataStatistics').then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.topData = resp.content;
      })
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
<style scoped>
.ty-body{
  background-color: #fff;
}
.card-body{
  display: flex;
  width: 98%;
  height: 80px;
  border-radius: 10px
}
.card-num{
  height: 60px;
  line-height: 60px;
  margin-top: 10px;
  width: 16%;
  text-align: center;
  border-radius: 10px;
  margin-left: 5px;
  font-size: 22px;
}
.card-text{
  line-height: 80px;
  margin-left: 10px;
  font-size: 18px;
  font-weight: 600;
}
.left{
  margin-top: 10px;
  width: 1100px;
  height: 830px;
  background-color: #fff;
}
.left-choose{
  font-size: 1.1em;
  position: absolute;
  z-index: 9999;
  left: 300px;
}
.left-map{
  width: 1080px;
  height: 830px;
}
.right{

}
</style>