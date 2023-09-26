<template>
    <div>
      <div :style="{height: heightMax + 'px'}">
        <div id="equipmentamap" style="width:100%;height: 100%" ></div>
      </div>

      <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document" style="width: 70%;border: 1px solid #0B61A4;color: #fff;">
          <div class="modal-header" style="background-color: #081041">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="color: #fff;">X</span></button>
            <h4 class="modal-title">气象及海洋监测</h4>
          </div>
          <div class="modal-content" style="background-color: #081041">
            <div style="margin-bottom: 20px;">
              <ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
                <li class="active" v-on:click="changeTab(1)">
                  <a data-toggle="tab" href="#faq-tab-1" aria-expanded="true" >
                    气象监测
                  </a>
                </li>
                <li class="" v-on:click="changeTab(2)">
                  <a data-toggle="tab" href="#faq-tab-2" aria-expanded="false" >
                    温盐深浊度仪数据
                  </a>
                </li>
                <li class="" v-on:click="changeTab(3)">
                  <a data-toggle="tab" href="#faq-tab-2" aria-expanded="false" >
                    海流计数据
                  </a>
                </li>
              </ul>
            </div>
            <div v-show="environmentType==1" >
              <div id="echartMeteorological"  style='height: 500px;width: 1200px;'></div>
            </div>
            <div v-show="environmentType==2" >
              <select v-model="sbbh" class="form-control" style="width: 20%;background-color: #081041;color: #fff;">
                <option value="RPCDA4016">RPCDA4016</option>
                <option value="ZDY21001">ZDY21001</option>
                <option value="ZDY21002">ZDY21002</option>
              </select>
              <div id="echartTurbidity"  style='height: 500px;width: 1200px;'></div>
            </div>
            <div v-show="environmentType==3" >
              <div id="echartCurrentMeter"  style='height: 500px;width: 1200px;'></div>
            </div>
          </div>
        </div>
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
                zhbht:LOCAL_ZHBHT,
                environmentType:1,
                sbbh:"RPCDA4016"
            }
        },
        watch: {
          sbbh() {
            let _this = this;
            _this.getTurbidityData();
          }
        },
        mounted() {
            let _this = this;
            _this.createAmap();
            _this.deptMap = Tool.getDeptUser();
            _this.findDeviceInfo();
            _this.getCurrentMeterData();
            _this.getMeteorologicalData();
            _this.getTurbidityData();
        },
        methods:{
            getCurrentMeterData(){
              let _this = this;
              let obj = {};
              obj.stime = Tool.dateFormat("yyyy-MM-dd",new Date());
              obj.etime = Tool.dateFormat("yyyy-MM-dd",new Date());
              _this.$forceUpdate();
              _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/currentMeter/getAllDataByTime',obj).then((response)=>{
                Loading.hide();
                let resp = response.data;
                let xAxisDatas = [];
                let seriesData1 = [];
                let seriesData2 = [];
                let seriesData3 = [];
                let seriesData4 = [];
                let seriesData5 = [];
                let seriesData6 = [];
                let seriesData7 = [];
                let seriesData8 = [];
                let seriesData9 = [];
                for(let i=0;i<resp.content.length;i++){
                  let currentMeter = resp.content[i];
                  xAxisDatas.push(currentMeter.cjsj);
                  seriesData1.push(currentMeter.absSpeed);
                  seriesData2.push(currentMeter.tiltX);
                  seriesData3.push(currentMeter.tiltY);
                  seriesData4.push(currentMeter.spStd);
                  seriesData5.push(currentMeter.strength);
                  seriesData6.push(currentMeter.pingCount);
                  seriesData7.push(currentMeter.absTilt);
                  seriesData8.push(currentMeter.maxTilt);
                  seriesData9.push(currentMeter.stdTilt);
                }
                _this.$nextTick(function (){
                  _this.initCurrentMeterEchart(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7,seriesData8,seriesData9);
                })
              })
            },
            initCurrentMeterEchart(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7,seriesData8,seriesData9){
              let option = {
                tooltip: {
                  trigger: 'axis'
                },
                legend: {
                  data: ["abs速度","倾斜度X","倾斜度Y","Sp标准","力度","平计数","Abs倾斜度","最大倾斜度","标准倾斜度"],
                  textStyle: {
                    color: "#fff"
                  }
                },
                grid: {
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true
                },
                xAxis: {
                  type: 'category',
                  data: xAxisDatas,
                  axisLine: {
                    lineStyle: {
                      color: "#fff"
                    }
                  }
                },
                yAxis: {
                  type: 'value',
                  axisLine: {
                    lineStyle: {
                      color: "#fff"
                    }
                  }
                },
                series: [
                  {
                    name: 'abs速度',
                    type: 'line',
                    data: seriesData1
                  },
                  {
                    name: '倾斜度X',
                    type: 'line',
                    data: seriesData2
                  },
                  {
                    name: '倾斜度Y',
                    type: 'line',
                    data: seriesData3
                  },
                  {
                    name: 'Sp标准',
                    type: 'line',
                    data: seriesData4
                  },
                  {
                    name: '力度',
                    type: 'line',
                    data: seriesData5
                  },
                  {
                    name: '平计数',
                    type: 'line',
                    data: seriesData6
                  },
                  {
                    name: 'Abs倾斜度',
                    type: 'line',
                    data: seriesData7
                  },
                  {
                    name: '最大倾斜度',
                    type: 'line',
                    data: seriesData8
                  },
                  {
                    name: '标准倾斜度',
                    type: 'line',
                    data: seriesData9
                  }
                ]
              };
              let echartsData = echarts.init(document.getElementById("echartCurrentMeter"));
              echartsData.setOption(option);
            },
            getMeteorologicalData(){
              let _this = this;
              let obj = {};
              obj.stime = Tool.dateFormat("yyyy-MM-dd",new Date());
              obj.etime = Tool.dateFormat("yyyy-MM-dd",new Date());
              _this.$forceUpdate();
              _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/meteorologicalData/getAllDataByTime',obj).then((response)=>{
                Loading.hide();
                let resp = response.data;
                let xAxisDatas = [];
                let seriesData1 = [];
                let seriesData2 = [];
                let seriesData3 = [];
                let seriesData4 = [];
                let seriesData5 = [];
                let seriesData6 = [];
                let seriesData7 = [];
                let seriesData8 = [];
                let seriesData9 = [];
                let seriesData10 = [];
                for(let i=0;i<resp.content.length;i++){
                  let meteorologicalData = resp.content[i];
                  xAxisDatas.push(meteorologicalData.cjsj);
                  seriesData1.push(meteorologicalData.speed);
                  seriesData2.push(meteorologicalData.winddirection);
                  seriesData3.push(meteorologicalData.temperature);
                  seriesData4.push(meteorologicalData.humidity);
                  seriesData5.push(meteorologicalData.pressure);
                  seriesData6.push(meteorologicalData.minuterainfall);
                  seriesData7.push(meteorologicalData.hourrainfall);
                  seriesData8.push(meteorologicalData.dayrainfall);
                  seriesData9.push(meteorologicalData.rainfallaccumulation);
                  seriesData10.push(meteorologicalData.solarintensity);
                }
                _this.$nextTick(function (){
                  _this.initMeteorologicalEchart(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7,seriesData8,seriesData9,seriesData10);
                })
              })
            },
            initMeteorologicalEchart(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7,seriesData8,seriesData9,seriesData10){
              let option = {
                tooltip: {
                  trigger: 'axis'
                },
                legend: {
                  data: ["风速","风向","温度","湿度","大气压力","分钟雨量","小时雨量","24小时雨量","观测雨量累加值","太阳光强度"],
                  textStyle: {
                    color: "#fff"
                  }
                },
                grid: {
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true
                },
                xAxis: {
                  type: 'category',
                  data: xAxisDatas,
                  axisLine: {
                    lineStyle: {
                      color: "#fff"
                    }
                  }
                },
                yAxis: {
                  type: 'value',
                  axisLine: {
                    lineStyle: {
                      color: "#fff"
                    }
                  }
                },
                series: [
                  {
                    name: '风速',
                    type: 'line',
                    data: seriesData1
                  },
                  {
                    name: '风向',
                    type: 'line',
                    data: seriesData2
                  },
                  {
                    name: '温度',
                    type: 'line',
                    data: seriesData3
                  },
                  {
                    name: '湿度',
                    type: 'line',
                    data: seriesData4
                  },
                  {
                    name: '大气压力',
                    type: 'line',
                    data: seriesData5
                  },
                  {
                    name: '分钟雨量',
                    type: 'line',
                    data: seriesData6
                  },
                  {
                    name: '小时雨量',
                    type: 'line',
                    data: seriesData7
                  },
                  {
                    name: '24小时雨量',
                    type: 'line',
                    data: seriesData8
                  },
                  {
                    name: '观测雨量累加值',
                    type: 'line',
                    data: seriesData9
                  },
                  {
                    name: '太阳光强度',
                    type: 'line',
                    data: seriesData10
                  }
                ]
              };
              let echartsData = echarts.init(document.getElementById("echartMeteorological"));
              echartsData.setOption(option);
            },
            getTurbidityData(){
              let _this = this;
              let obj = {};
              obj.bz = _this.sbbh;
              obj.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
              obj.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
              _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/turbidity/getAllDataByTime',obj).then((response)=>{
                let resp = response.data;
                let xAxisDatas = [];
                let seriesData1 = [];
                let seriesData2 = [];
                let seriesData3 = [];
                let seriesData4 = [];
                let seriesData5 = [];
                let seriesData6 = [];
                let seriesData7 = [];
                for(let i=0;i<resp.content.length;i++){
                  let turbidity = resp.content[i];
                  xAxisDatas.push(turbidity.dateTime);
                  seriesData1.push(turbidity.turbidityH);
                  seriesData2.push(turbidity.turibidityL);
                  seriesData3.push(turbidity.depth);
                  seriesData4.push(turbidity.temperature);
                  seriesData5.push(turbidity.conductivity);
                  seriesData6.push(turbidity.salinity);
                  seriesData7.push(turbidity.batVolt);
                }
                _this.$nextTick(function (){
                  _this.initTurbidityEchart(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7);
                })
              })
            },
            initTurbidityEchart(xAxisDatas,seriesData1,seriesData2,seriesData3,seriesData4,seriesData5,seriesData6,seriesData7){
              let option = {
                tooltip: {
                  trigger: 'axis'
                },
                legend: {
                  data: ["浊度高量程","浊度低量程","深度","温度","电导率","盐度","电池电压"],
                  textStyle: {
                    color: "#fff"
                  }
                },
                grid: {
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true
                },
                xAxis: {
                  type: 'category',
                  data: xAxisDatas,
                  axisLine: {
                    lineStyle: {
                      color: "#fff"
                    }
                  }
                },
                yAxis: {
                  type: 'value',
                  axisLine: {
                    lineStyle: {
                      color: "#fff"
                    }
                  }
                },
                series: [
                  {
                    name: '浊度高量程',
                    type: 'line',
                    data: seriesData1
                  },
                  {
                    name: '浊度低量程',
                    type: 'line',
                    data: seriesData2
                  },
                  {
                    name: '深度',
                    type: 'line',
                    data: seriesData3
                  },
                  {
                    name: '温度',
                    type: 'line',
                    data: seriesData4
                  },
                  {
                    name: '电导率',
                    type: 'line',
                    data: seriesData5
                  },
                  {
                    name: '盐度',
                    type: 'line',
                    data: seriesData6
                  },
                  {
                    name: '电池电压',
                    type: 'line',
                    data: seriesData7
                  }
                ]
              };
              let echartsData = echarts.init(document.getElementById("echartTurbidity"));
              echartsData.setOption(option);
            },
            changeTab(type){
              let _this = this;
              _this.environmentType = type;
            },
            createAmap(){
                let _this = this;
                if(_this.zhbht){
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
                  _this.amap = new AMap.Map('equipmentamap', {
                    center: [114.299945,30.593221],
                    resizeEnable: true,
                    zoom: 5,
                    mapStyle: _this.mapStyle
                  });
                }
            },
            findDeviceInfo(){
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', {}).then((response)=>{
                    Loading.hide();
                    let devices = response.data.content;
                    for(let i=0;i<devices.length;i++){
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
                        if("0001"==devices[i].sblb){
                            let marker = new AMap.Marker({
                                icon: devices[i].sbzt=='1'?icon:ycicon,
                                position: devices[i].gps.split(','),
                                offset: new AMap.Pixel(-12,-12),
                                zIndex: 101,
                                map: _this.amap
                            });
                            if(_this.zhbht){
                              marker.setLabel({
                                direction:'center',
                                offset: new AMap.Pixel(10, 0),  //设置文本标注偏移量
                                content: "<div style='color: #fff'>"+devices[i].fzwz+"</div>", //设置文本标注内容
                              });
                            }
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
                        }
                    }
                })
            },
            createInfoWindow(content) {
                let _this = this;
                if("RPCDA4016"==content[3]){
                  $("#form-modal").modal("show");
                }
                //_this.clickMapPoint(content[3]);
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