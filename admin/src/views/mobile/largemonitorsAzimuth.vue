<template>
  <div class="page-container">
    <div id="mapDiv" class="map-div"></div>
  </div>
</template>
<script>
export default {
  name: 'largemonitorsAzimuth',
  data: function (){
    return {
      zoom:15,//地图缩放比例
      equipments:[],//当前用户部门下的A4设备
      user:null,//当前用户
      infoWindow:null,
      polygonTextArr:[],
      allSbsn:[]
    }
  },
  created() {
    let _this = this;
    _this.user = Tool.getLoginUser();
    _this.getEquipment();//获取用户所在部门的设备
  },
  mounted() {
    let _this = this;
    _this.initMap();//初始化地图
    _this.openSocket();
  },
  methods:{
    openSocket(){
      let _this = this;
      let socket;
      if(typeof(WebSocket) == "undefined") {
        alert("您的浏览器不支持WebSocket,无法实时更新数据,请使用谷歌、火狐或IE11等浏览器!");
      }else{
        let socketUrl="ws://119.3.2.53:9091/monitor/websocket/21_"+new Date().getTime();
        //let socketUrl="ws://192.168.10.13:9091/monitor/websocket/21_"+new Date().getTime();
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
            let data = JSON.parse(msg.data);
            if(_this.allSbsn.includes(data.sbbh) && data.txtlx=='5'  && !Tool.isEmpty(data.sm1)){
              let arr = data.sm1.split("/");//2022_10_15_02_15_14-0:105,1:235
              let jdNum = new Map();
              jdNum.set("northNortheast",0);
              jdNum.set("northeastEast",0);
              jdNum.set("eastEastsouth",0);
              jdNum.set("eastsouthSouth",0);
              jdNum.set("southSouthwest",0);
              jdNum.set("southwestWest",0);
              jdNum.set("westWestnorth",0);
              jdNum.set("westnorthNorth",0);
              for(let i=0;i<arr.length;i++){
                let rqandjd = arr[i].split("-");//2022_10_15_02_15_14和0:105,1:235
                let item = [];
                if(rqandjd.length==2){
                  if(rqandjd[1].split(",").length>0){
                    let jdarr =  rqandjd[1].split(",");//0:105和1:235
                    for(let j=0;j<jdarr.length;j++){
                      if(jdarr[j].split(":").length==2){
                        let jtjd = jdarr[j].split(":")[1];//角度信息
                        if(jtjd>=0 && jtjd<45){
                          jdNum.set("northNortheast",parseInt(jdNum.get("northNortheast"))+1);
                        }else if(jtjd>=45 && jtjd<90){
                          jdNum.set("northeastEast",parseInt(jdNum.get("northeastEast"))+1);
                        }else if(jtjd>=90 && jtjd<135){
                          jdNum.set("eastEastsouth",parseInt(jdNum.get("eastEastsouth"))+1);
                        }else if(jtjd>=135 && jtjd<180){
                          jdNum.set("eastsouthSouth",parseInt(jdNum.get("eastsouthSouth"))+1);
                        }else if(jtjd>=180 && jtjd<225){
                          jdNum.set("southSouthwest",parseInt(jdNum.get("southSouthwest"))+1);
                        }else if(jtjd>=225 && jtjd<270){
                          jdNum.set("southwestWest",parseInt(jdNum.get("southwestWest"))+1);
                        }else if(jtjd>=270 && jtjd<315){
                          jdNum.set("westWestnorth",parseInt(jdNum.get("westWestnorth"))+1);
                        }else if(jtjd>=315 && jtjd<360){
                          jdNum.set("westnorthNorth",parseInt(jdNum.get("westnorthNorth"))+1);
                        }
                      }
                    }
                  }
                }
              }
              let sortJdNum = _this.sortMapByValue(jdNum);
              for(let m=0;m<_this.polygonTextArr.length;m++){
                let polygonText = _this.polygonTextArr[m];
                if(polygonText.getExtData().id==data.sbbh+"_"+sortJdNum[0]){
                  // 将文字设置为红色
                  polygonText.setStyle({
                    'font-size': '18px',
                    'color': 'red'
                  });
                }else if(polygonText.getExtData().id==data.sbbh+"_"+sortJdNum[1]){
                  // 将文字设置为红色
                  polygonText.setStyle({
                    'font-size': '18px',
                    'color': 'green'
                  });
                }else if(polygonText.getExtData().id==data.sbbh+"_"+sortJdNum[2]){
                  // 将文字设置为红色
                  polygonText.setStyle({
                    'font-size': '18px',
                    'color': 'blue'
                  });
                }
                setTimeout(function (){
                  // 将文字设置为红色
                  polygonText.setStyle({
                    'font-size': '16px',
                    'color': 'white'
                  });
                }, 3000);
              }
            }
          }
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
    //初始化地图
    initMap(){
      let _this = this;
      _this.amap = new AMap.Map('mapDiv', {
        resizeEnable: true,
        zoom: _this.zoom
      });
      _this.amap.setCenter(_this.user.deptgpsmap[_this.user.deptcode].split(","));
      // 信息弹窗
      _this.infoWindow = new AMap.InfoWindow({ offset: new AMap.Pixel(0, -20) });
    },
    //获取用户所在部门的设备
    getEquipment(){
      let _this = this;
      Loading.show();
      let obj = {'sblb':'0001','dqzl':'A4'}
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll',obj).then((res)=>{
        Loading.hide();
        let response = res.data;
        _this.equipments = response.content;
        _this.polygonTextArr = [];
        _this.allSbsn = [];
        for(let i=0;i<_this.equipments.length;i++){
          _this.allSbsn.push(_this.equipments[i].sbsn);
          let circleCenter = _this.equipments[i].gps.split(',');
          let point = {
            name: _this.equipments[i].sbsn,
            x: circleCenter[0],
            y: circleCenter[1],
            data: [
              {msg:'北-北东',r:0,angle:25,offset:new AMap.Pixel(-15,-15),id:_this.equipments[i].sbsn+"_"+"northNortheast"},
              {msg:'北东-东',r:45,angle:65,offset:new AMap.Pixel(15,10),id:_this.equipments[i].sbsn+"_"+"northeastEast"},
              {msg:'东-东南',r:90,angle:115,offset:new AMap.Pixel(12,-15),id:_this.equipments[i].sbsn+"_"+"eastEastsouth"},
              {msg:'东南-南',r:135,angle:160,offset:new AMap.Pixel(-12,10),id:_this.equipments[i].sbsn+"_"+"eastsouthSouth"},
              {msg:'南-南西',r:180,angle:205,offset:new AMap.Pixel(15,10),id:_this.equipments[i].sbsn+"_"+"southSouthwest"},
              {msg:'南西-西',r:225,angle:245,offset:new AMap.Pixel(-10,-12),id:_this.equipments[i].sbsn+"_"+"southwestWest"},
              {msg:'西-西北',r:270,angle:-65,offset:new AMap.Pixel(-10,12),id:_this.equipments[i].sbsn+"_"+"westWestnorth"},
              {msg:'西北-北',r:315,angle:-25,offset:new AMap.Pixel(10,-12),id:_this.equipments[i].sbsn+"_"+"westnorthNorth"}
            ]
          }
          _this.drawSite(point);
        }

      })
    },
    drawSite(mapPoint) {
      let _this = this;
      let point = new AMap.LngLat(mapPoint.x, mapPoint.y);
      for (let i = 0; i < mapPoint.data.length; i++) {
        let radian1 = 90 - mapPoint.data[i].r - 45
        let radian2 = 90 - mapPoint.data[i].r
        let pathArr = this.sector(point, 500, radian1, radian2);
        let latArr = [];
        let lngArr = [];
        for(let k=0;k<pathArr.length;k++){
          latArr.push(pathArr[k].lat);
          lngArr.push(pathArr[k].lng);
        }
        latArr = latArr.sort((a, b) => a - b);
        lngArr = lngArr.sort((a, b) => a - b);
        let oval = new AMap.Polygon({
          path: pathArr,
          strokeColor: '#ff8400',
          strokeOpacity: '1',
          strokeWeight: 1,
          fillColor: '#ff8400',
          fillOpacity: '0.3'
        })
        // 创建纯文本标记
        let text = new AMap.Text({
          text:mapPoint.data[i].msg,
          anchor:'center', // 设置文本标记锚点
          angle:mapPoint.data[i].angle,
          offset:mapPoint.data[i].offset,
          style:{
            'height': '15rem',
            'background-color': 'rgba(133, 197, 160, 0)',
            'text-align': 'center',
            'font-size': '16px',
            'border-width': 0,
            'color': 'white',
            'writing-mode':'vertical-lr'
          },
          extData:{
            'id':mapPoint.data[i].id
          },
          position: [(lngArr[0]+lngArr[lngArr.length-1])/2, (latArr[0]+latArr[latArr.length-1])/2]
        });
        text.setMap(_this.amap);
        _this.polygonTextArr.push(text);
        // let info = [];
        // info.push("<b>基站名称：</b>" + mapPoint.name);
        // info.push("<b>扇区名称：</b>" + mapPoint.data[i].msg);
        // let content = info.join("<br/>");
        // oval.content = content
        // oval.on('click', this.polygonClick);
        _this.amap.add(oval);
      }
    },
    polygonClick(e) {
      this.infoWindow.setContent(e.target.content);
      this.infoWindow.open(this.amap, [e.lnglat.getLng(), e.lnglat.getLat()]);
    },
    sector(center, len, radian1, radian2) {
      let points = [];
      let step = ((radian2 - radian1) / 10) || 10; //根据扇形的总夹角确定每步夹角度数，最大为10
      points.push(center);
      for (let i = radian1; i < radian2 + 0.001; i += step) { //循环获取每步的圆弧上点的坐标，存入点数组
        points.push(this.eOffsetBearing(center, len, i));
      }
      points.push(center);
      return points;
    },
    eOffsetBearing(center, len, bearing) {
      let lngConv = center.distance(new AMap.LngLat(center.getLng() + 0.1, center.getLat())) * 10
      let latConv = center.distance(new AMap.LngLat(center.getLng(), center.getLat() + 0.1)) * 10 //计算1纬度与原点的距离
      let lat = len * Math.sin(bearing * Math.PI / 180) / latConv; //正弦计算待获取的点的纬度与原点纬度差
      let lng = len * Math.cos(bearing * Math.PI / 180) / lngConv; //余弦计算待获取的点的经度与原点经度差
      return new AMap.LngLat(center.getLng() + lng, center.getLat() + lat);
    },
    sortMapByValue(map) {
      let mapArray = [];
      for (let [key, value] of map) {
        mapArray.push([key, value]);
      }
      mapArray.sort((a, b) => b[1] - a[1]); // 升序排序，如果需要降序排序，请将`a[1] - b[1]`更改为`b[1] - a[1]`
      return mapArray.map(item => item[0]);
    }
  }
}
</script>
<style scoped>
.page-container{
  width: 100%;
  height: 100vh;
}
.map-div{
  position:absolute;
  width: 100%;
  height: 100%;
}
</style>