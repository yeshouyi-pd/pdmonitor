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
      blingIcon:null,
      markMap:new Map(),//存放标记点marker对象
      lastTimeMap:new Map(),//存放标记点最后在线时间
      stationsIntervalId:null,
      blingMarkId:[],//存放闪烁标记点
      blingTimeMap:new Map(),//存放闪烁标记点最后在线时间
      sendCommandIntervalId:null
    }
  },
  created() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.9-100;
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
    //闪烁图标样式
    _this.blingIcon = new AMap.Icon({
      image: '/static/image/bling.gif',
      size: new AMap.Size(29, 36)
    });
    // 计时器为空，操作
    _this.stationsIntervalId = setInterval(() => {
      //遍历最后在线时间，查看是否超过五分钟
      for(let id in _this.lastTimeMap){
        let mark = _this.markMap[id];
        if(_this.isOverTime(_this.lastTimeMap[id],300000)){
          //超过五分钟
          mark.setIcon(_this.offlineIcon);
        }else{
          if(!_this.blingMarkId.includes(id)){
            mark.setIcon(_this.onlineIcon);
          }
        }
      }
    }, 120000);
    // 计时器为空，操作
    _this.sendCommandIntervalId = setInterval(() => {
      //遍历最后在线时间，查看是否超过90秒
      for(let id in _this.blingTimeMap){
        let mark = _this.markMap[id];
        if(_this.isOverTime(_this.blingTimeMap[id],90000)){
          //超过90秒
          mark.setIcon(_this.onlineIcon);
          _this.blingMarkId.splice(_this.blingMarkId.indexOf(id), 1);
        }
      }
    }, 60000);
    _this.$forceUpdate();
  },
  destroyed() {
    let _this = this;
    clearInterval(_this.stationsIntervalId);
    clearInterval(_this.sendCommandIntervalId);
  },
  methods: {
    //实时获取豚类信息
    getSendCommon(){
      let _this = this;
      let socket;
      if(typeof(WebSocket) == "undefined") {
        alert("您的浏览器不支持WebSocket,无法实时更新数据,请使用谷歌、火狐或IE11等浏览器!");
      }else{
        let socketUrl="ws://159.226.163.121:9091/monitor/sendCommandWebSocketServer/23_"+new Date().getTime();
        //let socketUrl="ws://192.168.10.13:9091/monitor/sendCommandWebSocketServer/23_"+new Date().getTime();
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
              if(id==data.nodeId){
                //获取marker,修改marker状态,修改最后在线时间
                let mark = _this.markMap[id];
                mark.setIcon(_this.blingIcon);
                _this.blingMarkId.push(id);
                _this.blingTimeMap[id] = data.createTime;
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
    //实时获取基站信息
    getStationHeart(){
      let _this = this;
      let socket;
      if(typeof(WebSocket) == "undefined") {
        alert("您的浏览器不支持WebSocket,无法实时更新数据,请使用谷歌、火狐或IE11等浏览器!");
      }else{
        let socketUrl="ws://159.226.163.121:9091/monitor/stationsWebSocketServer/22_"+new Date().getTime();
        //let socketUrl="ws://192.168.10.13:9091/monitor/stationsWebSocketServer/22_"+new Date().getTime();
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
              if(id==data.nodeId){
                //获取marker,修改marker状态,修改最后在线时间
                let mark = _this.markMap[id];
                if(data.status=="true"&&!_this.blingMarkId.includes(id)){
                  mark.setIcon(_this.onlineIcon);
                }else if(data.status=="false"){
                  mark.setIcon(_this.offlineIcon);
                }
                if(data.status=="true"){
                  _this.lastTimeMap[id] = data.createTime;
                }
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
      _this.amap.setCenter([113.799350,29.932999]);
      let polylineArr = new Array();
      for(let i=0;i<_this.stationsInfos.length;i++){
        //添加基站点
        let marker = new AMap.Marker({
          icon: _this.isOverTime(_this.stationsInfos[i].lastOnlineTime,300000)?_this.offlineIcon:_this.onlineIcon,
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
        //信息窗体
        marker.content = [];
        marker.content.push(_this.stationsInfos[i].nodeName);
        marker.content.push(_this.stationsInfos[i].id);
        marker.content.push(_this.stationsInfos[i].dh);
        marker.content.push(_this.stationsInfos[i].gps);
        AMap.event.addListener(marker, 'click', function (e) {
          let infoWindow = new AMap.InfoWindow({
            isCustom: true,  //使用自定义窗体
            content: _this.createInfoWindow(e.target.content),
            offset: new AMap.Pixel(16, -15)
          });
          infoWindow.open(_this.amap, e.target.getPosition());
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
      _this.getSendCommon();
    },
    createInfoWindow(content) {
      let _this = this;
      let info = document.createElement("div");
      info.className = "custom-info input-card content-window-card";

      //可以通过下面的方式修改自定义窗体的宽高
      //info.style.width = "400px";
      // 定义顶部标题
      let top = document.createElement("div");
      let titleD = document.createElement("div");
      let closeX = document.createElement("img");
      top.className = "info-top";
      titleD.innerHTML = "详情";
      closeX.src = "https://webapi.amap.com/images/close2.gif";
      closeX.onclick = _this.closeInfoWindow;

      top.appendChild(titleD);
      top.appendChild(closeX);
      info.appendChild(top);

      // 定义中部内容
      let middle = document.createElement("div");
      middle.className = "info-middle";
      middle.style.backgroundColor = 'white';
      middle.innerHTML = "<div>基站名称："+content[0]+"</div><div>基站ID："+content[1]+"</div><div>电话："+content[2]+"</div><div>gps："+content[3]+"</div>";
      info.appendChild(middle);

      // 定义底部内容
      let bottom = document.createElement("div");
      bottom.className = "info-bottom";
      bottom.style.position = 'relative';
      bottom.style.top = '0px';
      bottom.style.margin = '0 auto';
      let sharp = document.createElement("img");
      sharp.src = "https://webapi.amap.com/images/sharp.png";
      bottom.appendChild(sharp);
      info.appendChild(bottom);
      return info;
    },
    closeInfoWindow() {
      let _this = this;
      _this.amap.clearInfoWindow();
    },
    //判断在线时间是否超过5分钟
    isOverTime(lastTime,overTime){
      if(Tool.isEmpty(lastTime)){
        return true;
      }
      if((new Date().getTime()-new Date(lastTime).getTime())>overTime){
        return true;
      }else{
        return false;
      }
    }
  }
}
</script>
