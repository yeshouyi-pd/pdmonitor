<template>
  <div>
    <div style="width: 115px;height: 80px;border: 1px solid #EEEEEE;position: absolute;top: 0;left: 12px;z-index: 100;background-color: #fff;">
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
  data: function() {
    return {
      heightMax:'',
      waterDatas:[],
      deptMap:[],
      onLineCount:0,
      offLineCount:0,
      errorCount:0
    }
  },
  mounted() {
    let _this = this;
    let userInfo = Tool.getLoginUser();
    _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/findAll/' + userInfo.deptcode).then((response)=>{
      let resp = response.data;
      if (resp.success) {
        _this.waterDatas = resp.content;
        _this.$forceUpdate();
      }
    })
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.heightMax = h*0.8-20;
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
      let bmapChart = echarts.init(document.getElementById('map-wrap'));
      // let myData = [
      //   {name: '海门', value: [121.15, 31.89, 200], addr:'haimeijutidizhi'},
      //   {name: '招远', value: [120.38, 37.35, 100], addr:''},
      //   {name: '舟山', value: [122.207216, 29.985295, 0], addr:'zousanjutidizhi'},
      // ]
      let option = {
        amap: {
          center: [114.299945,30.593221],
          zoom: 7,
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