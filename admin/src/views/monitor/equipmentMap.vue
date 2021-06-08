<template>
  <div>
    <div id="map-top" style="width: 115px;height: 80px;border: 1px solid #EEEEEE;position: absolute;left: 12px;z-index: 100;background-color: #fff;">
      <div style="padding:5px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#03C449;padding-right: 10px"></i>设备正常 {{onLineCount}}个</div>
      <div style="padding:0px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#555555;padding-right: 10px"></i>设备离线 {{offLineCount}}个</div>
      <div style="padding:0px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#B03A5B;padding-right: 10px"></i>设备故障 {{errorCount}}个</div>
    </div>
    <div id="map-wrap"  :style="{height: heightMax + 'px'}">
      <!-- 这里以后是地图 -->
    </div>
  </div>
</template>
<script>
export default {
  name:'equipment-map',
  props: {
    heightMax: {
      default: ""
    },
  },
  data: function() {
    return {
      waterDatas:[],
      deptMap:[],
      onLineCount:0,
      offLineCount:0,
      errorCount:0,
      centerLoction:[114.299945,30.593221],
    }
  },
  mounted() {
    let _this = this;

    let mapObj = new AMap.Map('iCenter');
    mapObj.plugin('AMap.Geolocation', function () {
      let geolocation = new AMap.Geolocation({
        enableHighAccuracy: true,//是否使用高精度定位，默认:true
        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
        maximumAge: 0,           //定位结果缓存0毫秒，默认：0
        convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
        showButton: true,        //显示定位按钮，默认：true
        buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
        buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
        showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
        showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
        panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
        zoomToAccuracy:true      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
      });
      mapObj.addControl(geolocation);
      geolocation.getCurrentPosition();
      AMap.event.addListener(geolocation, 'complete', function (complete) {
        console.log("complete"+complete.position);
        console.log(_this.centerLoction);
        _this.centerLoction=[complete.position.lng,complete.position.lat];
        console.log(_this.centerLoction);
      });//返回定位信息
      AMap.event.addListener(geolocation, 'error', function(error){
        console.log("error"+error);
      });      //返回定位出错信息
    });

    let userInfo = Tool.getLoginUser();
    _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/findAll/' + userInfo.deptcode).then((response)=>{
      let resp = response.data;
      if (resp.success) {
        _this.waterDatas = resp.content;
        _this.$forceUpdate();
      }
    })
    if(Tool.isEmpty(_this.heightMax)){
      let h = document.documentElement.clientHeight || document.body.clientHeight;
      _this.heightMax = h*0.8-20;
    }

    _this.deptMap = Tool.getDeptUser();
    _this.findDeviceInfo();
  },
  methods:{
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', {}).then((response)=>{
        Loading.hide();
        let devices = response.data.content;
        let myData = [];
        for(let i=0;i<devices.length;i++){
          let value = devices[i].gps.split(",");
          if(devices[i].sbzt=='1'){
            _this.onLineCount++;
            value.push(0);
          }else if(devices[i].sbzt=='2'){
            _this.offLineCount++;
            value.push(100);
          }else if(devices[i].sbzt=='3'){
            _this.errorCount++;
            value.push(200);
          }
          let obj = {
            'name':devices[i].sbmc,
            'value':value,
            'deptcode':_this.optionMapKV(_this.deptMap,devices[i].deptcode),
            'centerCode':_this.optionWDArray(_this.waterDatas,devices[i].centerCode)
          }
          myData.push(obj);
        }
        _this.initMap(myData);
      })
    },
    initMap(myData){
      let _this = this;
      console.log(_this.centerLoction);
      let bmapChart = echarts.init(document.getElementById('map-wrap'));
      // let myData = [
      //   {name: '海门', value: [121.15, 31.89, 200], addr:'haimeijutidizhi'},
      //   {name: '招远', value: [120.38, 37.35, 100], addr:''},
      //   {name: '舟山', value: [122.207216, 29.985295, 0], addr:'zousanjutidizhi'},
      // ]
      let option = {
        amap: {
          center: _this.centerLoction,
          zoom: 8,
          roam: true, // 允许缩放
        },
        tooltip : {
          trigger: 'item',
          formatter: function(param){
            return '设备名称：'+param.data.name+'</br>设备所属监测点：'+param.data.deptcode+'</br>设备所属数据中心：'+param.data.centerCode;
          }
        },
        visualMap: {	// 视觉映射组件
          type: 'continuous',
          min: 0,
          max: 200,
          calculable: true,
          inRange: {
            color: ['#03C449','#555555','#B03A5B'],
            symbol:['pin', 'pin', 'pin'],
            symbolSize: [30,30,30]
          }
        },
        series: [
          {
            type: 'scatter',
            coordinateSystem: 'amap', // 坐标系使用amap高德地图
            data: myData,
            itemStyle: {
              normal: {
                color: "#fff",
                shadowBlur: 10,
                shadowColor: "#333"
              }
            }
          }]
      }
      bmapChart.setOption(option);
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