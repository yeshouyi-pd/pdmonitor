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
        <table class="table  table-bordered table-hover" >
          <thead>
            <tr>
              <th>开始时间</th>
              <th>结束时间</th>
              <th>头数</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in eventData">
              <td>{{item.kssj}}</td>
              <td>{{item.jssj}}</td>
              <td>{{item.ts}}</td>
            </tr>
          </tbody>
        </table>
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
      topData:{},
      eventData:[]
    }
  },
  created() {
    let _this = this;
    _this.curDateStr = Tool.dateFormat("yyyy-MM-dd");
    _this.getEquipmentByTy();
    _this.getTopData();
    _this.getTodayEvent();
  },
  mounted() {
    let _this = this;
    _this.initMap();
    _this.openSocket();
  },
  methods: {
    openSocket(){
      var socket;
      if(typeof(WebSocket) == "undefined") {
        alert("您的浏览器不支持WebSocket,无法实时更新数据,请使用谷歌、火狐或IE11等浏览器!");
      }else{
        var socketUrl="ws://146.56.226.176:9091/monitor/websocket/21";
        console.log(socketUrl);
        if(socket!=null){
          socket.close();
          socket=null;
        }
        socket = new WebSocket(socketUrl);
        //打开事件
        socket.onopen = function() {
          console.log("websocket已打开");
        };
        //获得消息事件
        socket.onmessage = function(msg) {
          if(msg.data.includes("连接成功")){
            Toast.success(msg.data);
          }else{
            console.log(msg.data);
          }
          console.log(msg);
        };
        //关闭事件
        socket.onclose = function() {
          console.log("websocket已关闭");
        };
        //发生了错误事件
        socket.onerror = function() {
          console.log("websocket发生了错误");
        }
      }
    },
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
    //获取当天聚类事件
    getTodayEvent(){
      let _this = this;
      Loading.show();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentTyEvent/getTodayEvent').then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.eventData = resp.content;
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
  width: 80%;
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
  width: 78%;
  height: 830px;
}
.right{
  width: 19%;
  margin-top: 10px;
  border: 1px solid #ccc;
}
</style>