<template>
  <div>
    <div id="echartsBox" style="display: flex;flex-wrap: wrap;"></div>
  </div>
</template>
<script>
import Date from "../../components/date";
export default {
  name: 'pointer-day',
  components: {Date},
  data: function (){
    return {
      pointerDay:{},
      pointerDayDto:{},
      pointerDays:[],
      echartsDataList:[],
      waterEquipments:[]
    }
  },
  created(){
    let _this = this;
    _this.findDeviceInfo();
  },
  mounted() {
    let _this = this;
    _this.pointerDayDto.cjsj = Tool.dateFormat("yyyy-MM-dd hh:mm:ss");
    _this.$forceUpdate();
    _this.listAll();
  },
  methods: {
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001','dqzl':'A1,A4'};
      }else{
        data = {'sblb':'0001','dqzl':'A1,A4','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        _this.$forceUpdate();
      })
    },
    optionMapKV(list, key){
      if (!list || !key) {
        return "";
      } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
          if (key === list[i]["sbsn"]) {
            result = list[i]["sbmc"];
          }
        }
        return result;
      }
    },
    listAll(){
      let _this = this;
      Loading.show();
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.pointerDayDto.xmbh = Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/pointerDay/listAll', _this.pointerDayDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.echartsDataList = resp.content;
        _this.initEcharts();
      })
    },
    initEcharts(){
      let _this = this;
      let echartsBox = document.getElementById("echartsBox");
      for(let i=0;i<_this.echartsDataList.length;i++){
        let obj = _this.echartsDataList[i];
        let div = document.createElement('div');
        div.style.width = "500px";
        div.style.height = "500px";
        let gaugeChart = echarts.init(div);
        let option = _this.initOption(obj.decibelValue,obj.cjsj,obj.sm);
        gaugeChart.setOption(option);
        echartsBox.append(div);
      }
    },
    initOption(value,time,sm){
      let _this = this;
      let option = {
        series: [
          {
            type: 'gauge',
            startAngle: 180,
            endAngle: 0,
            min: 120,
            max: 220,
            axisLine: {
              lineStyle: {
                width: 30,
                color: [
                  [0.6, '#327662'],
                  [0.8, '#F7BA0B'],
                  [1, '#701F29']
                ]
              }
            },
            pointer: {
              itemStyle: {
                color: 'auto'
              }
            },
            axisTick: {
              distance: -30,
              length: 8,
              lineStyle: {
                color: '#fff',
                width: 2
              }
            },
            splitLine: {
              distance: -30,
              length: 30,
              lineStyle: {
                color: '#fff',
                width: 4
              }
            },
            axisLabel: {
              color: 'auto',
              distance: 40,
              fontSize: 20
            },
            detail: {
              valueAnimation: true,
              formatter: '{value} dB\n'+time+'\n'+_this.optionMapKV(_this.waterEquipments,sm),
              color: 'auto'
            },
            data: [
              {
                value: value
              }
            ]
          }
        ]
      };
      return option;
    }
  }
}
</script>