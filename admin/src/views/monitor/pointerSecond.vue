<template>
  <div>
    <div id="echartsBox" style="display: flex;flex-wrap: wrap;"></div>
  </div>
</template>
<script>
import Date from "../../components/date";
export default {
  name: 'pointer-second',
  components: {Date},
  data: function (){
    return {
      pointerSecond:{},
      pointerSecondDto:{},
      pointerSeconds:[],
      echartsDataList:[],
      intervalId:null,
      deptMap: []
    }
  },
  created(){
    let _this = this;
    _this.dataRefreh();
  },
  destroyed(){
    // 在页面销毁后，清除计时器
    let _this = this;
    _this.clear();
  },
  mounted() {
    let _this = this;
    _this.deptMap = Tool.getDeptUser();
    _this.pointerSecondDto.cjsj = Tool.dateFormat("yyyy-MM-dd hh:mm:ss");
    _this.$forceUpdate();
    _this.listAll();
  },
  methods: {
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
    // 定时刷新数据函数
    dataRefreh() {
      let _this = this;
      // 计时器正在进行中，退出函数
      if (_this.intervalId != null) {
        return;
      }
      // 计时器为空，操作
      _this.intervalId = setInterval(() => {
        console.log("刷新" + new Date());
        _this.listAll();
      }, 600000);
    },
    // 停止定时器
    clear() {
      let _this = this;
      clearInterval(_this.intervalId); //清除计时器
      _this.intervalId = null; //设置为null
    },
    listAll(){
      let _this = this;
      Loading.show();
      if("460100"!=Tool.getLoginUser().deptcode){
        _this.pointerSecondDto.xmbh = Tool.getLoginUser().xmbh;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/pointerSecond/listAll', _this.pointerSecondDto).then((response) => {
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
            min: 100,
            max: 240,
            axisLine: {
              lineStyle: {
                width: 30,
                color: [
                  [0.3, '#327662'],
                  [0.38, '#F7BA0B'],
                  [0.57, '#DD7907'],
                  [0.92, '#fd666d'],
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
              formatter: '{value} dB\n'+time+'\n'+_this.optionMapKV(_this.deptMap,sm),
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