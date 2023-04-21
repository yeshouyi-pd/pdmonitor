<template>
  <div>
    <div id="mapDiv" style="position:absolute;" :style="{height:+maxHeight+'px',width:+maxWidth+'px'}"></div>
  </div>
</template>
<script>
export default {
  name: 'stations-map',
  data: function (){
    return {
      stationsInfos:[],
      zoom:11,
      onlineIcon:null,
      offlineIcon:null,
      markMap:new Map(),//存放标记点marker对象
      lastTimeMap:new Map(),//存放最后在线时间
      stationsIntervalId:null
    }
  },
  created() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8-50;
    let w = document.documentElement.clientWidth || document.body.clientWidth;
    _this.maxWidth = w-230;
    _this.$forceUpdate();
    _this.getStationsInfo();
  },
  mounted() {
    let _this = this;
    //在线图标样式
    _this.onlineIcon = new AMap.Icon({
      image: '/static/image/online-marker.png',
      size: new AMap.Size(29, 36)
    });
    //离线图标样式
    _this.offlineIcon = new AMap.Icon({
      image: '/static/image/offline-marker.png',
      size: new AMap.Size(29, 36)
    });
    // 计时器为空，操作
    _this.stationsIntervalId = setInterval(() => {
      //遍历最后在线时间，查看是否超过五分钟
      for(let id in _this.lastTimeMap){
        let mark = _this.markMap[id];
        if(_this.isOverTime(_this.lastTimeMap[id])){
          //超过五分钟
          mark.setIcon(_this.offlineIcon);
        }else{
          mark.setIcon(_this.onlineIcon);
        }
      }
    }, 60000);
    _this.$forceUpdate();
  },
  destroyed() {
    let _this = this;
    clearInterval(_this.stationsIntervalId);
  },
  methods: {
    //实时获取基站信息
    getStationHeart(){
      let _this = this;
      let socket;
      if(typeof(WebSocket) == "undefined") {
        alert("您的浏览器不支持WebSocket,无法实时更新数据,请使用谷歌、火狐或IE11等浏览器!");
      }else{
        //let socketUrl="ws://146.56.226.176:9091/monitor/stationsWebSocketServer/22_"+new Date().getTime();
        let socketUrl="ws://192.168.10.13:9091/monitor/stationsWebSocketServer/22_"+new Date().getTime();
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
            //Toast.success(msg.data);
          }else{
            let data = JSON.parse(msg.data);
            //遍历所有的基站id
            for(let id in _this.markMap){
              if(id==data.id){
                //获取marker,修改marker状态,修改最后在线时间
                let mark = _this.markMap[id];
                mark.setIcon(_this.onlineIcon);
                _this.lastTimeMap[id] = data.createTime;
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
    //获取所有的基站点
    getStationsInfo(){
      let _this = this;
      Loading.show();
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/admin/stationsInfo/listAll').then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.stationsInfos = resp.content;
        _this.$nextTick(function (){
          _this.initMap();
        })
      })
    },
    //初始化地图
    initMap(){
      let _this = this;
      _this.amap = new AMap.Map('mapDiv', {
        resizeEnable: true,
        zoom: _this.zoom
      });
      //设置中心点
      _this.amap.setCenter([116.3083,29.136176]);
      let polylineArr = new Array();
      for(let i=0;i<_this.stationsInfos.length;i++){
        console.log(_this.stationsInfos[i]);
        //添加基站点
        let marker = new AMap.Marker({
          icon: _this.isOverTime(_this.stationsInfos[i].lastOnlineTime)?_this.offlineIcon:_this.onlineIcon,
          position: _this.stationsInfos[i].gps.split(','),
          offset: new AMap.Pixel(3,-34),
          zIndex: 101,
          anchor:'top-center',
          map: _this.amap
        });
        //点标记文本
        marker.setLabel({
          direction:'center',
          offset:new AMap.Pixel(0, -5),
          content: _this.stationsInfos[i].nodeName, //设置文本标注内容
        });
        //存放mark对象
        _this.markMap[_this.stationsInfos[i].id] = marker;
        //存放最后在线时间
        _this.lastTimeMap[_this.stationsInfos[i].id] = _this.stationsInfos[i].lastOnlineTime;
        //连接所有的基站点
        let itemarr = _this.stationsInfos[i].gps.split(",");
        polylineArr.push(new AMap.LngLat(itemarr[0],itemarr[1].trim()));
      }
      console.log(polylineArr);
      let  polyline = new AMap.Polyline ({
        path: polylineArr,//设置多边形边界路径
        borderWeight: 2, // 线条宽度，默认为 1
        strokeColor: '#dd0310', // 线条颜色
        lineJoin: 'round' // 折线拐点连接处样式
      });
      _this.amap.add(polyline);
      _this.getStationHeart();
    },
    //判断在线时间是否超过5分钟
    isOverTime(lastTime){
      if(Tool.isEmpty(lastTime)){
        return true;
      }
      if((new Date().getTime()-new Date(lastTime).getTime())>300000){
        return true;
      }else{
        return false;
      }
    }
  }
}
</script>
