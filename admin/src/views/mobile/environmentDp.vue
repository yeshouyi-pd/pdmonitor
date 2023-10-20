<template>
  <div style="width: 100%;height: 936px;background-image: url('/static/image/environment/dpbg.png');background-size: 100%;margin: auto">
    <div class="page-first-div">
      <div class="left-div">
        <div style="height: 6.1%;display: flex;flex-direction: row;align-items: center;margin-left: 20px;">
          <div v-on:click="back()" style="color: rgb(255, 255, 255);font-size: 16px;border: 1px solid #043769;background-color:rgb(10,33,61);width: 15%;text-align: center;padding: 5px 0;cursor: pointer">
            返回
          </div>
        </div>
        <div class="left-content-div">
          <div class="left-top">
            <div class="title-name-div">
              <span style="padding-top:0%;">设备情况</span>
            </div>
            <div class="left-top-content">
              <div class="content-box">
                <img src="/static/image/environment/jcy.png" />
                <div>
                  <span>设备点位</span><br/>
                  <span style="color: yellow;">{{curSbmc}}</span>
                </div>
              </div>
              <div class="content-box">
                <img src="/static/image/environment/jcy.png" />
                <div>
                  <span>监测仪器数量</span><br/>
                  <span>5</span>
                </div>
              </div>
            </div>
            <div class="left-top-content">
              <div class="content-box">
                <img src="/static/image/environment/jcy.png" />
                <div>
                  <span>设备编号</span><br/>
                  <span style="color: yellow;">{{curSbbh}}</span>
                </div>
              </div>
              <div class="content-box">
                <img src="/static/image/environment/jcy.png" />
                <div>
                  <span>水听器数量</span><br/>
                  <span>15</span>
                </div>
              </div>
            </div>
          </div>
          <div class="left-center">
            <div class="title-name-div" style="height: 10%">
              <span>海流计监测数据</span>
            </div>
            <div class="center-content-div">
              <div class="center-content-item-first">
                <div style="width: 15%;margin: auto;">序号</div>
                <div style="width: 35%;margin: auto;">数据项</div>
                <div style="width: 50%;margin: auto;">数值</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">1</div>
                <div style="width: 35%;margin: auto;">Abs速度</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.absSpeed}}m/s</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">2</div>
                <div style="width: 35%;margin: auto;">方向</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.direction}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">3</div>
                <div style="width: 35%;margin: auto;">倾斜度X</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.tiltX}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">4</div>
                <div style="width: 35%;margin: auto;">倾斜度Y</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.tiltY}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">5</div>
                <div style="width: 35%;margin: auto;">最大倾斜度</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.maxTilt}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">6</div>
                <div style="width: 35%;margin: auto;">标准倾斜度</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.stdTilt}}</div>
              </div>
              <div class="center-content-item">
                <div style="width: 15%;margin: auto;">7</div>
                <div style="width: 35%;margin: auto;">Sp标准</div>
                <div style="width: 50%;margin: auto;color: yellow;">{{currentMeter.spStd}}</div>
              </div>
            </div>
          </div>
          <div class="left-bottom">
            <div class="title-name-div">
              <span>温盐度浊度仪数据</span>
            </div>
            <div class="bottom-content" id="leftBottomEchart"></div>
          </div>
        </div>
      </div>
      <div class="center-div">
        <div class="dp-title">动态观测平台</div>
        <div class="map-div">
          <EquipmentAMap v-bind:height-max="heightMax" :click-map-point="clickMapPoint"></EquipmentAMap>
        </div>
        <div class="center-content-bottom" id="centerBottomEchart"></div>
      </div>
      <div class="right-div">
        <div class="left-content-div" style="margin-top: 10%;">
          <div class="left-top">
            <div class="title-name-div">
              <span style="padding-top:0%;">气象数据</span>
            </div>
            <div class="right-top-content" style="margin: 1% auto 0;">
              <div class="right-top-box">
                <img src="/static/image/environment/fs.png" />
                <div>
                  <span>风速</span><br/>
                  <span>{{meteorological.speed}}m/s</span>
                </div>
              </div>
              <div class="right-top-box" style="margin:0 1%;">
                <img src="/static/image/environment/wd.png" />
                <div>
                  <span>气温</span><br/>
                  <span>{{meteorological.temperature}}℃</span>
                </div>
              </div>
              <div class="right-top-box">
                <img src="/static/image/environment/qy.png" />
                <div>
                  <span>气压</span><br/>
                  <span>{{meteorological.pressure}}hPa</span>
                </div>
              </div>
            </div>
            <div class="right-top-content" style="margin: 1% auto 0;">
              <div class="right-top-box">
                <img src="/static/image/environment/fx.png" />
                <div>
                  <span>风向</span><br/>
                  <span>{{meteorological.winddirection}}</span>
                </div>
              </div>
              <div class="right-top-box" style="margin:0 1%;">
                <img src="/static/image/environment/sd.png" />
                <div>
                  <span>湿度</span><br/>
                  <span>{{meteorological.humidity}}%</span>
                </div>
              </div>
              <div class="right-top-box">
                <img src="/static/image/environment/tyg.png" />
                <div>
                  <span>太阳光强度</span><br/>
                  <span>{{meteorological.solarintensity}}W/m2</span>
                </div>
              </div>
            </div>
          </div>
          <div class="left-center">
            <div class="title-name-div" style="height: 10%">
              <span>温度数据</span>
            </div>
            <div class="center-content-div" id="rightCenterEchart"></div>
          </div>
          <div class="left-bottom">
            <div class="title-name-div">
              <span>湿度数据</span>
            </div>
            <div class="bottom-content" id="rightBottomEchart"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EquipmentAMap from "../../components/dpMap";
export default {
  name: 'environment-dp',
  components:{EquipmentAMap},
  data: function (){
    return {
      heightMax:500,
      curSbbh:'RPCDA4016',
      curSbmc:'RPCDA4016',
      currentMeter:{},
      meteorological:{},
      intervalId:null
    }
  },
  mounted() {
    let _this = this;
    _this.leftCenterData();
    _this.leftBottomData();
    _this.centerBottomData();
    _this.dataRefreh();
  },
  methods: {
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
        _this.leftCenterData();
        _this.leftBottomData();
        _this.centerBottomData();
      }, 900000);
    },
    // 停止定时器
    clear() {
      let _this = this;
      clearInterval(_this.intervalId); //清除计时器
      _this.intervalId = null; //设置为null
    },
    back(){
      let _this = this;
      _this.clear();
      window.location.href = "/mobile/largemonitorsZj";
    },
    clickMapPoint(sbmc,sbbh){
      let _this = this;
      _this.curSbmc = sbmc;
      _this.curSbbh = sbbh;
      _this.leftCenterData();
      _this.leftBottomData();
      _this.centerBottomData();
    },
    leftCenterData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getCurrentMeterTodayData/'+_this.curSbbh, {}).then((response)=>{
        Loading.hide();
        _this.currentMeter = response.data.content;
      })
    },
    leftBottomData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getTurbidityTodayData/'+_this.curSbbh, {}).then((response)=>{
        Loading.hide();
        let turbiditys = response.data.content;
        let data = [];
        for(let i=0;i<turbiditys.length;i++){
          let turbidity = turbiditys[i];
          let dataItem = [];
          dataItem.push(turbidity.dateTime.substring(11,turbidity.dateTime.length));
          dataItem.push(turbidity.salinity);
          data.push(dataItem);
        }
        _this.initLeftBottomEchart(data);
      })
    },
    initLeftBottomEchart(data){
      let option = {
        grid: {
          bottom: '20px',
          top: '40px'
        },
        legend: {
          top: 10,
          data: ['盐度(PSU)'],
          textStyle: {
            color: '#fff'
          }
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        yAxis: {
          splitLine: { show: false },
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        series: [
          {
            name: '盐度(PSU)',
            symbolSize: function (data) {
              return data[1]*2;
            },
            data: data,
            type: 'scatter'
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("leftBottomEchart"));
      echartsData.setOption(option);
    },
    centerBottomData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getMeteorologicalTodayData/'+_this.curSbbh, {}).then((response)=>{
        Loading.hide();
        let meteorologicals = response.data.content;
        let data = [];
        let data1 = [];
        let data2 = [];
        if(meteorologicals.length>0){
          _this.meteorological = meteorologicals[0];
        }
        for(let i=0;i<meteorologicals.length;i++){
          let meteorological = meteorologicals[i];
          let dataItem = [];//风速和风向
          dataItem.push(meteorological.cjsj);
          dataItem.push(meteorological.speed);
          dataItem.push(meteorological.winddirection);
          data.push(dataItem);
          let dataItem1 = [];//温度
          dataItem1.push(meteorological.cjsj.substring(11,meteorological.cjsj.length));
          dataItem1.push(meteorological.temperature);
          data1.push(dataItem1)
          let dataItem2 = [];//湿度
          dataItem2.push(meteorological.cjsj.substring(11,meteorological.cjsj.length));
          dataItem2.push(meteorological.humidity);
          data2.push(dataItem2)
        }
        _this.initCenterBottomEchart(data);
        _this.rightCenterEchart(data1);
        _this.rightBottomEchart(data2);
      })
    },
    initCenterBottomEchart(data){
      const directionMap = {};
      ['W', 'WSW', 'SW', 'SSW', 'S', 'SSE', 'SE', 'ESE', 'E', 'ENE', 'NE', 'NNE', 'N', 'NNW', 'NW', 'WNW'].forEach(function (name, index) {
        directionMap[name] = Math.PI / 8 * index;
      });
      const dims = {
        time: 0,
        windSpeed: 1,
        R: 2,
        minTemp: 3,
        maxTemp: 4
      };
      const arrowSize = 18;
      const renderArrow = function (param, api) {
        const point = api.coord([
          api.value(dims.time),
          api.value(dims.windSpeed)
        ]);
        return {
          type: 'path',
          shape: {
            pathData: 'M31 16l-15-15v9h-26v12h26v9z',
            x: -arrowSize / 2,
            y: -arrowSize / 2,
            width: arrowSize,
            height: arrowSize
          },
          rotation: directionMap[api.value(dims.R)],
          position: point,
          style: api.style({
            stroke: '#555',
            lineWidth: 1
          })
        };
      };
      let option = {
        tooltip: {
          trigger: 'axis',
          formatter: function (params) {
            return [
              echarts.format.formatTime(
                  'yyyy-MM-dd',
                  params[0].value[dims.time]
              ) +
              ' ' +
              echarts.format.formatTime('hh:mm:ss', params[0].value[dims.time]),
              '风速：' + params[0].value[dims.windSpeed],
              '风向：' + params[0].value[dims.R]
            ].join('<br>');
          }
        },
        grid: {
          left: '30px',
          right: '10px',
          bottom: '25px',
          top: '40px'
        },
        xAxis: {
          type: 'time',
          splitLine: {
            lineStyle: {
              color: '#ddd'
            }
          },
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        yAxis: [
          {
            name: '风速,风向',
            axisLine: {
              lineStyle: {
                color: '#fff'
              }
            },
            splitLine: {
              lineStyle: {
                color: '#ddd'
              }
            }
          }
        ],
        series: [
          {
            type: 'custom',
            renderItem: renderArrow,
            encode: {
              x: dims.time,
              y: dims.windSpeed
            },
            data: data,
            z: 10
          },
          {
            type: 'line',
            symbol: 'none',
            encode: {
              x: dims.time,
              y: dims.windSpeed
            },
            lineStyle: {
              color: '#aaa',
              type: 'dotted'
            },
            data: data,
            z: 1
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("centerBottomEchart"));
      echartsData.setOption(option);
    },
    rightCenterEchart(data){
      let _this = this;
      let option = {
        grid: {
          bottom: '20px',
          top: '40px',
          left: '30px',
          right: '10px'
        },
        xAxis: {
          type: 'category',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        yAxis: {
          name: '温度',
          type: 'value',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        series: [
          {
            data: data,
            type: 'line'
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("rightCenterEchart"));
      echartsData.setOption(option);
    },
    rightBottomEchart(data){
      let _this = this;
      let option = {
        grid: {
          bottom: '20px',
          top: '40px',
          left: '30px',
          right: '10px'
        },
        xAxis: {
          type: 'category',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        yAxis: {
          name: '湿度(%)',
          type: 'value',
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#fff"
            }
          }
        },
        series: [
          {
            data: data,
            type: 'bar',
            itemStyle: {
              borderRadius: 50,
            }
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("rightBottomEchart"));
      echartsData.setOption(option);
    }
  }
}
</script>
<style scoped>
.page-first-div{
  width: 100%;
  height: 100%;
  display: flex;
  overflow: auto;
}
.left-div{
  width: 30%;
  height: 100%;
}
.left-content-div{
  background-color: rgb(7,14,40);
  width: 96%;
  height: 93%;
  margin: auto;
}
.center-div{
  width: 40%;
  height: 100%;
}
.right-div{
  width: 30%;
  height: 100%;
}
.left-top{
  width: 100%;
  height: 20%;
}
.left-center{
  width: 100%;
  height: 44%;
}
.left-bottom{
  width: 100%;
  height: 36%;
}
.title-name-div{
  color: #fff;
  background-image: url("/static/image/environment/xhx.png");
  background-repeat: no-repeat;
  background-size: 90% 100%;
  background-position: center;
  height: 15%;
}
.title-name-div span{
  margin-left: 5%;
  padding-top: 2%;
  display: block;
}
.left-top-content{
  height: 40%;
  display: flex;
  flex-direction: row;
  margin-top: 1%;
}
.content-box{
  height: 100%;
  width: 42.5%;
  margin-left: 5%;
  display: flex;
  background-color: rgb(10,33,61);
}
.content-box img{
  width: 20%;
  height: 70%;
  margin-top: 4%;
  margin-left: 4%;
}
.content-box div{
  color: #fff;
  margin: auto 0;
  padding-left: 5%;
}
.center-content-div{
  width: 90%;
  height: 88%;
  margin: auto;
}
.center-content-item-first{
  height: 10%;
  background-color: #043769;
  display: flex;
  flex-direction: row;
  color: #fff;
  text-align: center;
  margin-top: 1%;
}
.center-content-item{
  height: 12%;
  background: url("/static/image/environment/bghg.png") no-repeat;
  background-size: cover;
  margin-top: 0.5%;
  display: flex;
  flex-direction: row;
  color: #fff;
  text-align: center;
}
.bottom-content{
  height: 85%;
  width: 90%;
  margin: auto;
}
.dp-title{
  width: 100%;
  text-align: center;
  font-size: 24px;
  color: #fff;
  height: 83px;
  line-height: 83px;
}
.center-content-bottom{
  height: 347px;
}
.right-top-content{
  height: 40%;
  width: 96%;
  display: flex;
  flex-direction: row;
}
.right-top-box{
  height: 100%;
  width: 32%;
  display: flex;
  background-image: url("/static/image/environment/rightTopBg.png");
}
.right-top-box img{
  height: 30px;
  width: 30px;
  margin: auto;
}
.right-top-box div{
  width: 70%;
  margin: auto;
  color: #fff;
}
</style>