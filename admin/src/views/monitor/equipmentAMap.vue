<template>
    <div>
      <div :style="{height: heightMax + 'px'}">
        <div id="equipmentamap" style="width:100%;height: 100%" ></div>
      </div>
    </div>
</template>
<script>
    export default {
        name:'equipment-amap',
        props: {
            heightMax: {
                default: ""
            },
            mapStyle: {
              default: "amap://styles/darkblue"
            },
            clickMapPoint:{
              type: Function,
              default: null
            }
        },
        data: function() {
            return {
                deptMap:[],
                onLineCount:0,
                offLineCount:0,
                errorCount:0,
                centerLoction:[113.63,22.24],
                amap:'',
                LOCAL_ZHBHT:LOCAL_ZHBHT,
                LOCAL_SSBRL:LOCAL_SSBRL,
                LOCAL_VIDEO:LOCAL_VIDEO,
                LOCAL_TLBHQ:LOCAL_TLBHQ
            }
        },
        mounted() {
            let _this = this;
            _this.createAmap();
            _this.deptMap = Tool.getDeptUser();
            _this.findDeviceInfo();
        },
        methods:{
            createAmap(){
                let _this = this;
                let loginuser = Tool.getLoginUser();
                let usergps = loginuser.deptgpsmap[loginuser.deptcode];
                if(_this.LOCAL_ZHBHT || _this.LOCAL_VIDEO){
                  _this.amap = new AMap.Map('equipmentamap', {
                    center: [113.73,22.30],
                    resizeEnable: true,
                    zoom: 11,
                    // mapStyle: _this.mapStyle
                  });
                  let polygonArr = new Array();//多边形覆盖物节点坐标数组
                  polygonArr.push([113.666667, 22.4]);
                  polygonArr.push([113.716667, 22.4]);
                  polygonArr.push([113.716667, 22.183333]);
                  polygonArr.push([113.666667, 22.183333]);
                  let  polygon = new AMap.Polygon({
                    path: polygonArr,//设置多边形边界路径
                    strokeColor: "#6A8F71", //线颜色
                    strokeOpacity: 0.2, //线透明度
                    strokeWeight: 3,    //线宽
                    fillColor: "#85C5A0", //填充色
                    fillOpacity: 0.35//填充透明度
                  });
                  let polygonArr2 = new Array();//多边形覆盖物节点坐标数组
                  polygonArr2.push([113.716667, 22.4]);
                  polygonArr2.push([113.869444, 22.4]);
                  polygonArr2.push([113.869444, 22.366667]);
                  polygonArr2.push([113.766667, 22.366667]);
                  polygonArr2.push([113.766667, 22.216667]);
                  polygonArr2.push([113.816667, 22.216667]);
                  polygonArr2.push([113.833333, 22.183333]);
                  polygonArr2.push([113.716667, 22.183333]);
                  let  polygon2 = new AMap.Polygon({
                    path: polygonArr2,//设置多边形边界路径
                    strokeColor: "#76987C", //线颜色
                    strokeOpacity: 0.2, //线透明度
                    strokeWeight: 3,    //线宽
                    fillColor: "#BFE39B", //填充色
                    fillOpacity: 0.35//填充透明度
                  });
                  let polygonArr3 = new Array();//多边形覆盖物节点坐标数组
                  polygonArr3.push([113.869444, 22.366667]);
                  polygonArr3.push([113.869444, 22.333333]);
                  polygonArr3.push([113.847778, 22.273056]);
                  polygonArr3.push([113.838889, 22.266667]);
                  polygonArr3.push([113.816667, 22.216667]);
                  polygonArr3.push([113.766667, 22.216667]);
                  polygonArr3.push([113.766667, 22.366667]);
                  let  polygon3 = new AMap.Polygon({
                    path: polygonArr3,//设置多边形边界路径
                    strokeColor: "#908983", //线颜色
                    strokeOpacity: 0.2, //线透明度
                    strokeWeight: 3,    //线宽
                    fillColor: "#B59A8F", //填充色
                    fillOpacity: 0.35//填充透明度
                  });
                  let overlayGroup = new AMap.OverlayGroup([polygon,polygon2,polygon3])
                  _this.amap.add(overlayGroup);
                  // 创建纯文本标记
                  let text = new AMap.Text({
                    text:'实验区',
                    anchor:'center', // 设置文本标记锚点
                    style:{
                      'height': '15rem',
                      'background-color': 'rgba(133, 197, 160, 0)',
                      'text-align': 'center',
                      'font-size': '20px',
                      'border-width': 0,
                      'color': 'white',
                      'writing-mode':'vertical-lr'
                    },
                    position: [113.691667, 22.291667]
                  });
                  text.setMap(_this.amap);
                  // 创建纯文本标记
                  let text1 = new AMap.Text({
                    text:'缓冲区',
                    anchor:'center', // 设置文本标记锚点
                    style:{
                      'height': '15rem',
                      'background-color': 'rgba(133, 197, 160, 0)',
                      'text-align': 'center',
                      'font-size': '20px',
                      'border-width': 0,
                      'color': 'white',
                      'writing-mode':'vertical-lr'
                    },
                    position: [113.741667, 22.291667]
                  });
                  text1.setMap(_this.amap);
                  // 创建纯文本标记
                  let text2 = new AMap.Text({
                    text:'核心区',
                    anchor:'center', // 设置文本标记锚点
                    style:{
                      'height': '15rem',
                      'background-color': 'rgba(133, 197, 160, 0)',
                      'text-align': 'center',
                      'font-size': '20px',
                      'border-width': 0,
                      'color': 'white',
                      'writing-mode':'vertical-lr'
                    },
                    position: [113.791667, 22.291667]
                  });
                  text2.setMap(_this.amap);
                }else{
                  if(_this.LOCAL_SSBRL){
                    _this.amap = new AMap.Map('equipmentamap', {
                      //center: [114.299945,30.593221],
                      resizeEnable: true,
                      zoom: 5,
                      //mapStyle: _this.mapStyle
                    });
                    if(!Tool.isEmpty(usergps)){
                      _this.amap.setCenter(usergps.split(","));
                    }else{
                      _this.amap.setCenter([114.299945,30.593221]);
                    }
                  }else if(_this.LOCAL_TLBHQ){
                    _this.amap = new AMap.Map('equipmentamap', {
                      //center: [117.773,31.0355],
                      resizeEnable: true,
                      zoom: 10,
                      mapStyle: _this.mapStyle
                    });
                    if(!Tool.isEmpty(usergps)){
                      _this.amap.setCenter(usergps.split(","));
                    }else{
                      _this.amap.setCenter([117.773,31.0355]);
                    }
                  }
                }
            },
            findDeviceInfo(){
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', {}).then((response)=>{
                    Loading.hide();
                    let devices = response.data.content;
                    for(let i=0;i<devices.length;i++){
                        if(Tool.isEmpty(devices[i].gps)){
                          continue;
                        }
                        if(devices[i].sbzt=='1'){
                            _this.onLineCount++;
                        }else if(devices[i].sbzt=='2'){
                            _this.offLineCount++;
                        }else if(devices[i].sbzt=='3') {
                            _this.errorCount++;
                        }
                        let icon = new AMap.Icon({
                            image: '/largemonitors/assets/imgs/zcsb.png',
                            size: new AMap.Size(29, 29)
                        });
                        let ycicon = new AMap.Icon({
                            image: '/largemonitors/assets/imgs/ycsb.png',
                            size: new AMap.Size(29, 29)
                        })
                        let cameraicon = new AMap.Icon({
                          image: '/largemonitors/assets/imgs/Camera.png',
                          size: new AMap.Size(35, 35)
                        })
                        if("0001"==devices[i].sblb){
                            let marker = new AMap.Marker({
                                icon: devices[i].sbzt=='1'?icon:ycicon,
                                position: devices[i].gps.split(','),
                                offset: new AMap.Pixel(-12,-12),
                                zIndex: 101,
                                map: _this.amap
                            });
                            marker.setLabel({
                              direction:'center',
                              offset: new AMap.Pixel(10, 0),  //设置文本标注偏移量
                              content: "<div style='color: #fff'>"+devices[i].fzwz+"</div>", //设置文本标注内容
                            });
                            marker.content = [];
                            marker.content.push(devices[i].deptcode);
                            marker.content.push(devices[i].centerCode);
                            marker.content.push(devices[i].fzwz);
                            marker.content.push(devices[i].sbsn);
                            AMap.event.addListener(marker, 'click', function (e) {
                                let infoWindow = new AMap.InfoWindow({
                                    isCustom: true,  //使用自定义窗体
                                    content: _this.createInfoWindow(e.target.content),
                                    offset: new AMap.Pixel(16, -15)
                                });
                                infoWindow.open(_this.amap, e.target.getPosition());
                            });
                            let circle = new AMap.Circle({
                                center: devices[i].gps.split(','),
                                radius: 500, //半径
                                borderWeight: 3,
                                strokeColor: "#6D9DE9",
                                strokeWeight: 6,
                                strokeOpacity: 1,
                                fillOpacity: 0.4,
                                strokeStyle: 'dashed',
                                strokeDasharray: [10, 10],
                                // 线样式还支持 'dashed'
                                fillColor: '#1791fc',
                                zIndex: 50,
                            })
                            circle.setMap(_this.amap);
                            // 缩放地图到合适的视野级别
                            //_this.amap.setFitView([ circle ])
                        }else if("0002"==devices[i].sblb){
                            let marker = new AMap.Marker({
                                position: devices[i].gps.split(','),
                                map: _this.amap
                            });
                            marker.content = [];
                            marker.content.push(devices[i].deptcode);
                            marker.content.push(devices[i].centerCode);
                            marker.content.push(devices[i].fzwz);
                            marker.content.push(devices[i].sbsn);
                            //marker.on('click', _this.markerClick);
                            //鼠标点击marker弹出自定义的信息窗体
                            AMap.event.addListener(marker, 'click', function (e) {
                                let infoWindow = new AMap.InfoWindow({
                                    isCustom: true,  //使用自定义窗体
                                    content: _this.createInfoWindow(e.target.content),
                                    offset: new AMap.Pixel(16, -40)
                                });
                                infoWindow.open(_this.amap, e.target.getPosition());
                            });
                        }else if("004"==devices[i].sblb){
                          let marker = new AMap.Marker({
                            icon: cameraicon,
                            position: devices[i].gps.split(','),
                            offset: new AMap.Pixel(-12,-12),
                            zIndex: 101,
                            map: _this.amap
                          });
                          marker.setLabel({
                            direction:'center',
                            offset: new AMap.Pixel(10, 0),  //设置文本标注偏移量
                            content: "<div style='color: #fff'>"+devices[i].fzwz+"</div>", //设置文本标注内容
                          });
                          marker.content = [];
                          marker.content.push(devices[i].deptcode);
                          marker.content.push(devices[i].centerCode);
                          marker.content.push(devices[i].fzwz);
                          marker.content.push(devices[i].sbsn);
                          AMap.event.addListener(marker, 'click', function (e) {
                            let infoWindow = new AMap.InfoWindow({
                              isCustom: true,  //使用自定义窗体
                              content: _this.createInfoWindow(e.target.content),
                              offset: new AMap.Pixel(16, -40)
                            });
                            infoWindow.open(_this.amap, e.target.getPosition());
                          });
                        }
                    }
                })
            },
            createInfoWindow(content) {
                let _this = this;
                _this.clickMapPoint(content[2],content[3]);
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
                middle.innerHTML = "<div>所属机构："+_this.optionMapKV(_this.deptMap,content[0])+"</div><div>设备点位："+content[2]+"</div><div>设备编号："+content[3]+"</div>";
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
            optionMapKV(object, key){
                if (!object || !key) {
                    return "";
                } else {
                    let result = "";
                    for(let enums in object){
                        if (key === enums) {
                            result = object[enums];
                        }
                    }
                    return result;
                }
            },
            optionWDArray(list, key){
                if (!list || !key) {
                    return "";
                } else {
                    let result = "";
                    for (let i = 0; i < list.length; i++) {
                        if (key === list[i]["centerCode"]) {
                            result = list[i]["centerName"];
                        }
                    }
                    return result;
                }
            }
        }
    }
</script>
<style>
    deep .amap-info{
        position: absolute;
        left: 507px;
        top: 150px;
    }
    .bottom-center .amap-info-contentContainer{
        padding-bottom: 0px;
    }
    .custom-info {
        border: solid 1px silver;
    }
    .content-window-card {
        position: relative;
        box-shadow: none;
        bottom: 0;
        left: 0;
        width: auto;
        padding: 0;
    }
    .input-card {
        display: flex;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border-radius: 0.4rem;
        right: 1rem;
        flex: 1 1 auto;
    }
    div.info-top {
        position: relative;
        background: none repeat scroll 0 0 #F9F9F9;
        border-bottom: 1px solid #CCC;
        border-radius: 5px 5px 0 0;
    }
    div.info-top div {
        display: inline-block;
        color: #333333;
        font-size: 14px;
        font-weight: bold;
        line-height: 31px;
        padding: 0 10px;
    }
    div.info-top img {
        position: absolute;
        top: 10px;
        right: 10px;
        transition-duration: 0.25s;
    }
    div.info-middle {
        font-size: 12px;
        padding: 10px 6px;
        line-height: 20px;
        background-color: white;
        text-align: left;
    }
    div.info-bottom {
        height: 0px;
        width: 100%;
        clear: both;
        text-align: center;
        position: relative;
        top: 0px;
        margin: 0px auto;
    }
    div.info-bottom img {
        position: relative;
        z-index: 104;
    }
    .amap-container img {
        max-width: none!important;
        max-height: none!important;
    }
    .nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover{
      background-color: rgb(8, 16, 65);
      color: #fff;
      border-top: 2px solid #0B61A4;
    }
    .nav-tabs>li>a, .nav-tabs>li>a:focus{
      background-color: rgb(8, 16, 65);
    }
</style>