<template>
  <div class="dp-content">
    <div class="left-box">
      <div class="left-top-box">
        <div class="left-title-content">基本</div>
        <div class="left-line-box">
          <span>浮标名：</span>
          <input class="left-input-box" type="text" :value="curSbmc" disabled="true"/>
        </div>
        <div class="left-line-box">
          <span>设备号：</span>
          <select v-model="curSbbh" @change="changeData" class="left-select-box">
            <option v-for="item in zdysbList" :value="item.key">{{item.key}}</option>
          </select>
        </div>
        <div class="left-line-box" style="display: flex;flex-direction: row;align-items: center;">
          <span>时{{"\xa0\xa0\xa0\xa0"}}间：</span>
          <date style="width: 75%;" @methodName="changeDate" id-value="curdateid" :set-value="curDate"></date>
        </div>
        <div class="left-line-box">
          <span>信{{"\xa0\xa0\xa0\xa0"}}号：</span>
          <input class="left-input-box" type="text" value="北斗" disabled="true"/>
        </div>
      </div>
      <div class="left-center-box">
        <div class="left-title-content">状态</div>
        <div class="left-center-img-box">
          <div class="left-center-img-box-item">
            <img style="width: 50%;" src="/static/image/environment/zbtb.png" />
            <div class="left-center-img-box-text">
              <span style="font-size: 16px;">经度</span><br/>
              <span>108 29'50</span><br/>
              <span>53°E</span>
            </div>
          </div>
          <div class="left-center-img-box-item">
            <img style="width: 50%;" src="/static/image/environment/zbtb.png" />
            <div class="left-center-img-box-text">
              <span style="font-size: 16px;">纬度</span><br/>
              <span>108 29'50</span><br/>
              <span>53°E</span>
            </div>
          </div>
        </div>
        <div class="left-line-box">
          <span>电{{"\xa0\xa0\xa0\xa0"}}压：</span>
          <input class="left-input-box" type="text" value="14.4" disabled="true"/>
        </div>
        <div class="left-line-box">
          <span>方{{"\xa0\xa0\xa0\xa0"}}位：</span>
          <input class="left-input-box" type="text" value="103" disabled="true"/>
        </div>
        <div class="left-line-box">
          <span>锚{{"\xa0\xa0\xa0\xa0"}}灯：</span>
          <img style="width: 30px;" src="/static/image/environment/mie.png" />
        </div>
      </div>
      <div class="left-bottom-box">
        <div class="left-title-content">报警</div>
        <div style="display: flex;flex-direction: row;flex-wrap: wrap;margin-top: 10px;">
          <div class="left-line-box" style="width: 50%;">
            <span>舱{{"\xa0\xa0\xa0\xa0"}}门：</span>
            <img style="width: 30px;" src="/static/image/environment/wu.png" />
          </div>
          <div class="left-line-box" style="width: 35%;">
            <span>进{{"\xa0\xa0\xa0\xa0"}}水：</span>
            <img style="width: 30px;" src="/static/image/environment/you.png" />
          </div>
          <div class="left-line-box" style="width: 50%;">
            <span>移{{"\xa0\xa0\xa0\xa0"}}位：</span>
            <img style="width: 30px;" src="/static/image/environment/wu.png" />
          </div>
        </div>
      </div>
    </div>
    <div class="center-box">
      <div class="center-content">
        <div class="center-top-box">
          <div class="center-top-item">
            <p class="center-top-item-title">气压</p>
            <div class="center-top-item-content">
              <span>最大：</span>
              <input class="center-top-item-input" type="text" :value="maxPressure" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}hPa</span>
            </div>
            <div class="center-top-item-content">
              <span>最小：</span>
              <input class="center-top-item-input" type="text" :value="minPressure" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}hPa</span>
            </div>
            <div class="center-top-item-content">
              <span>最新：</span>
              <input class="center-top-item-input" type="text" :value="pressure" disabled="true" style="background-color: red !important;color: #fff !important;">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}hPa</span>
            </div>
          </div>
          <div class="center-top-item">
            <p class="center-top-item-title">气温</p>
            <div class="center-top-item-content">
              <span>最大：</span>
              <input class="center-top-item-input" type="text" :value="maxTemperature" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}℃</span>
            </div>
            <div class="center-top-item-content">
              <span>最小：</span>
              <input class="center-top-item-input" type="text" :value="minTemperature" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}℃</span>
            </div>
            <div class="center-top-item-content">
              <span>最新：</span>
              <input class="center-top-item-input" type="text" :value="temperature" disabled="true" style="background-color: red !important;color: #fff !important;">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}℃</span>
            </div>
          </div>
          <div class="center-top-item">
            <p class="center-top-item-title">湿度</p>
            <div class="center-top-item-content">
              <span>最大：</span>
              <input class="center-top-item-input" type="text" :value="maxHumidity" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}%</span>
            </div>
            <div class="center-top-item-content">
              <span>最小：</span>
              <input class="center-top-item-input" type="text" :value="minHumidity" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}%</span>
            </div>
            <div class="center-top-item-content">
              <span>最新：</span>
              <input class="center-top-item-input" type="text" :value="humidity" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}%</span>
            </div>
          </div>
          <div class="center-top-item">
            <p class="center-top-item-title">雨量</p>
            <div class="center-top-item-content">
              <span>存量：</span>
              <input class="center-top-item-input" type="text" value="0.0" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}hPa</span>
            </div>
            <div class="center-top-item-content">
              <span>增量：</span>
              <input class="center-top-item-input" type="text" value="0.0" disabled="true">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}hPa</span>
            </div>
          </div>
          <div class="center-top-item">
            <p class="center-top-item-title">辐射</p>
            <div class="center-top-item-content">
              <span>短波辐射：</span>
              <span>/</span>
              <span class="center-top-item-dw">{{"\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0"}}w/㎡</span>
            </div>
            <div class="center-top-item-content">
              <span>红外辐射：</span>
              <span>/</span>
              <span class="center-top-item-dw">{{"\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0"}}w/㎡</span>
            </div>
            <div class="center-top-item-content">
              <span>日照强度：</span>
              <span>/</span>
              <span class="center-top-item-dw">{{"\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0"}}w/㎡</span>
            </div>
          </div>
          <div class="center-top-item" style="margin-right: 0;">
            <p class="center-top-item-title">能见度</p>
            <div class="center-top-item-content">
              <input class="center-top-item-input" type="text" value="/" disabled="true" style="width: 80%">
              <span class="center-top-item-dw">{{"\xa0\xa0"}}m</span>
            </div>
          </div>
        </div>
        <div class="center-center-box">
          <div class="left-title-content">风速风向</div>
          <div class="center-center-content">
            <div class="center-center-content-item-first">
              <p>{{"\xa0\xa0"}}</p>
              <p>风速:(m/s)</p>
              <p>风向:</p>
            </div>
            <div class="center-center-content-item" style="width: 14%;">
              <p>两分钟平均</p>
              <p class="center-center-content-item-p">0</p>
              <p class="center-center-content-item-p">PPC</p>
            </div>
            <div class="center-center-content-item" style="width: 14%;">
              <p>十分钟平均</p>
              <p class="center-center-content-item-p">/</p>
              <p class="center-center-content-item-p">/</p>
            </div>
            <div class="center-center-content-item" style="width: 8%;">
              <p>最大</p>
              <p class="center-center-content-item-p">{{maxSecSpeed}}</p>
              <p class="center-center-content-item-p">{{maxSecDirection}}</p>
            </div>
            <div class="center-center-content-item" style="width: 8%;">
              <p>极大</p>
              <p class="center-center-content-item-p">{{maxSpeed}}</p>
              <p class="center-center-content-item-p">{{maxDirection}}</p>
            </div>
            <div class="center-center-content-item" style="width: 8%;">
              <p>瞬时</p>
              <p class="center-center-content-item-p">{{speed}}</p>
              <p class="center-center-content-item-p">{{direction}}</p>
            </div>
            <div class="center-center-content-item" style="width: 15%;">
              <p>最大风速出现时间</p>
              <div class="center-center-content-last">{{maxSecSpeedDate}}</div>
            </div>
            <div class="center-center-content-item" style="width: 15%;">
              <p>极大风速出现时间</p>
              <div class="center-center-content-last">{{maxSpeedDate}}</div>
            </div>
          </div>
        </div>
        <div class="center-bottom-box">
          <div class="center-bottom-left">
            <div class="center-bottom-left-top">
              <div class="left-title-content">云高</div>
              <div class="center-bottom-left-top-content">
                <div class="center-bottom-left-top-img">
                  <div class="center-bottom-left-top-img-item">
                    <img style="width: 100%;" src="/static/image/environment/njd.png"/>
                    <p style="margin-top: 10px;">垂直能见度</p>
                    <p>/(m)</p>
                  </div>
                  <div class="center-bottom-left-top-img-item">
                    <img style="width: 100%;" src="/static/image/environment/yun.png"/>
                    <p style="margin-top: 10px;">云</p>
                    <p>/</p>
                  </div>
                  <div class="center-bottom-left-top-img-item">
                    <img style="width: 100%;" src="/static/image/environment/sqnd.png"/>
                    <p style="margin-top: 10px;">水汽浓度</p>
                    <p>/</p>
                  </div>
                </div>
                <div class="center-bottom-left-top-table">
                  <table style="width: 100%;text-align: center;">
                    <thead>
                      <tr><th></th><th>云高(m)</th><th>云厚(m)</th><th>云重</th></tr>
                    </thead>
                    <tbody>
                      <tr><td>一层</td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td></tr>
                      <tr><td>二层</td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td></tr>
                      <tr><td>三层</td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td></tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
            <div class="center-bottom-left-bottom">
              <div class="center-bottom-left-bottom-left">
                <div class="left-title-content">波浪</div>
                <div class="center-bottom-content">
                  <div class="center-bottom-content-item" style="width: 15%">
                    <p>{{"\xa0\xa0"}}</p>
                    <p>最大:</p>
                    <p>有效:</p>
                    <p>平均:</p>
                  </div>
                  <div class="center-bottom-content-item" style="width: 20%;">
                    <p>波高(m)</p>
                    <p class="center-bottom-content-item-p">{{maxWaveH}}</p>
                    <p class="center-bottom-content-item-p">{{curWaveH}}</p>
                    <p class="center-bottom-content-item-p">{{avgWaveH}}</p>
                  </div>
                  <div class="center-bottom-content-item" style="width: 20%;">
                    <p>波周期(m)</p>
                    <p class="center-bottom-content-item-p">{{maxWavePeriod}}</p>
                    <p class="center-bottom-content-item-p">{{curWavePeriod}}</p>
                    <p class="center-bottom-content-item-p">{{avgWavePeriod}}</p>
                  </div>
                  <div class="center-bottom-content-item" style="width: 25%;margin-left: 5%;">
                    <p>波向</p>
                    <img style="width: 100%;" src="/static/image/environment/zbtb.png" />
                  </div>
                </div>
              </div>
              <div class="center-bottom-left-bottom-right">
                <div class="left-title-content">海流</div>
                <div class="left-line-box">
                  <span>abs速度：</span>
                  <input class="left-input-box" type="text" :value="!isEmpty(currentMeter)&&!isEmpty(currentMeter.absSpeed)?currentMeter.absSpeed:'/'" disabled="true"/>
                </div>
                <div class="left-line-box">
                  <span>倾斜度X：</span>
                  <input class="left-input-box" type="text" :value="!isEmpty(currentMeter)&&!isEmpty(currentMeter.tiltX)?currentMeter.tiltX:'/'" disabled="true"/>
                </div>
                <div class="left-line-box">
                  <span>倾斜度Y：</span>
                  <input class="left-input-box" type="text" :value="!isEmpty(currentMeter)&&!isEmpty(currentMeter.tiltY)?currentMeter.tiltY:'/'" disabled="true"/>
                </div>
              </div>
            </div>
          </div>
          <div class="center-bottom-right">
            <div class="left-title-content">温盐剖面</div>
            <div style="height:300px;" id="initEchart"></div>
            <table class="center-bottom-right-table">
              <thead>
                <tr><th>层</th><th>温度</th><th>盐度</th><th>深度</th><th>电导率</th></tr>
              </thead>
              <tbody>
                <tr><td><div>一层</div></td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td></tr>
                <tr><td><div>二层</div></td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td></tr>
                <tr><td><div>三层</div></td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td><td><div>/</div></td></tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div class="right-box">
      <div class="right-content-box">
        <div class="left-title-content">水质</div>
        <div class="right-content">
          <div class="right-content-item">
            <span>表面水温(℃)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>PH值</span>
            <input class="right-input-box" type="text" :value="!isEmpty(waterQualityNew)&&!isEmpty(waterQualityNew.ph)?waterQualityNew.ph:'/'" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>表层盐度(%m)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>硝酸盐(μg/L)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>表层电导率(us/cm)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>亚硝酸盐(μg/L)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>海水皮温(℃)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>磷酸盐(μg/L)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>浊度(FTU)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>氨氮(μg/L)</span>
            <input class="right-input-box" type="text" :value="!isEmpty(waterQualityNew)&&!isEmpty(waterQualityNew.ad)?waterQualityNew.ad:'/'" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>叶绿素(ppb)</span>
            <input class="right-input-box" type="text" :value="!isEmpty(waterQualityNew)&&!isEmpty(waterQualityNew.chlorophyll)?waterQualityNew.chlorophyll:'/'" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>COD(mg/L)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>溶解氧(μg/L)</span>
            <input class="right-input-box" type="text" :value="!isEmpty(waterQualityNew)&&!isEmpty(waterQualityNew.oxidative)?waterQualityNew.oxidative:'/'" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>硅酸盐(μg/L)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>空气CO₂(ppm)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>多环芳烃(ppb)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>水下CO₂(ppm)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
          <div class="right-content-item">
            <span>YSI盐度(%m)</span>
            <input class="right-input-box" type="text" value="/" disabled="true"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Date from "../../components/date";
export default {
  name: 'environment-new-dp',
  components: {Date},
  data: function (){
    return {
      curSbbh:'RPCDA4016',
      curSbmc:'RPCDA4016',
      curDate:null,
      zdysbbhList:['RPCDA4005','RPCDA4012','RPCDA4003','RPCDA4006-4','RPCDA4009-3','RPCDA4001','RPCDA4010','RPCDA4008','RPCDA4002','RPCDA4016'],
      zdysbList:[
        {key:"RPCDA4005", value:"3号航标"},
        {key:"RPCDA4012", value:"4号航标"},
        {key:"RPCDA4003", value:"5号航标"},
        {key:"RPCDA4006-4", value:"平台4"},
        {key:"RPCDA4009-3", value:"平台3"},
        {key:"RPCDA4001", value:"8号航标"},
        {key:"RPCDA4010", value:"10号航标"},
        {key:"RPCDA4008", value:"11号航标"},
        {key:"RPCDA4002", value:"淇澳岛"},
        {key:"RPCDA4016", value:"RPCDA4016"}
      ],
      currentMeter:null,
      turbidity:null,
      maxPressure:'/',
      minPressure:'/',
      pressure:'/',
      maxTemperature:'/',
      minTemperature:'/',
      temperature:'/',
      maxHumidity:'/',
      minHumidity:'/',
      humidity:'/',
      maxSpeed:'/',
      maxSecSpeed:'/',
      speed:'/',
      maxSpeedDate:'/',
      maxSecSpeedDate:'/',
      maxDirection:'/',
      maxSecDirection:'/',
      direction:'/',
      waterQualityNew:null,
      maxWaveH:'/',
      curWaveH:'/',
      avgWaveH:'/',
      maxWavePeriod:'/',
      curWavePeriod:'/',
      avgWavePeriod:'/'
    }
  },
  mounted() {
    let _this = this;
    _this.curDate = Tool.dateFormat("yyyy-MM-dd");
    _this.getCurrentMeterData();
    _this.getTurbidityData();
    _this.getMeteorologicalData();
    _this.getWaterQualityNewData();
    _this.getWaveDataData();
  },
  methods: {
    getWaveDataData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getWaveDataData', {"sbbh":_this.curSbbh,"stime":_this.curDate}).then((response)=> {
        let waveDatas = response.data.content;
        if (waveDatas.length > 0) {
          let waveData = waveDatas[waveDatas.length - 1];
          _this.curWaveH = waveData.waveH;
          _this.curWavePeriod = waveData.wavePeriod;
          let temp = waveDatas[0];
          _this.maxWaveH = temp.waveH;
          _this.maxWavePeriod = temp.wavePeriod;
          let sumWaveH = temp.waveH;
          let sumWavePeriod = temp.wavePeriod;
          for(let i=1;i<waveDatas.length;i++){
            if(_this.maxWaveH<waveDatas[i].waveH){
              _this.maxWaveH = waveDatas[i].waveH;
            }
            if(_this.maxWavePeriod<waveDatas[i].wavePeriod){
              _this.maxWavePeriod = waveDatas[i].wavePeriod;
            }
            sumWaveH+=waveDatas[i].waveH;
            sumWavePeriod+=waveDatas[i].wavePeriod;
          }
          _this.avgWaveH = (sumWaveH/waveDatas.length).toFixed(2);
          _this.avgWavePeriod = (sumWavePeriod/waveDatas.length).toFixed(2);
        }
      })
    },
    getWaterQualityNewData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getWaterQualityNewData', {"sbbh":_this.curSbbh,"stime":_this.curDate}).then((response)=> {
        let waterQualityNews = response.data.content;
        if (waterQualityNews.length > 0) {
          _this.waterQualityNew = waterQualityNews[waterQualityNews.length - 1];
        }
      })
    },
    getMeteorologicalData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getMeteorologicalData', {"bz":_this.curSbbh,"stime":_this.curDate}).then((response)=> {
        let meteorologicals = response.data.content;
        if(meteorologicals.length>0){
          let meteorological = meteorologicals[meteorologicals.length-1];
          if(!Tool.isEmpty(meteorological)){
            _this.pressure = _this.isEmpty(meteorological.pressure)?'/':meteorological.pressure;
            _this.temperature = _this.isEmpty(meteorological.temperature)?'/':meteorological.temperature;
            _this.humidity = _this.isEmpty(meteorological.humidity)?'/':meteorological.humidity;
            _this.speed = _this.isEmpty(meteorological.speed)?'/':meteorological.speed;
            _this.direction = _this.isEmpty(meteorological.winddirection)?'/':meteorological.winddirection;
          }
          let temp = meteorologicals[0];
          _this.minPressure = temp.pressure;
          _this.maxPressure = temp.pressure;
          _this.minTemperature = temp.temperature;
          _this.maxTemperature = temp.temperature;
          _this.minHumidity = temp.humidity;
          _this.maxHumidity = temp.humidity;
          _this.maxSpeed = temp.speed;
          _this.maxSecSpeed = temp.speed;
          _this.maxSpeedDate = temp.cjsj;
          _this.maxSecSpeedDate = temp.cjsj;
          _this.maxDirection = temp.winddirection;
          _this.maxSecDirection = temp.winddirection;
          for(let i=1;i<meteorologicals.length;i++){
            if(_this.minPressure>meteorologicals[i].pressure){
              _this.minPressure = meteorologicals[i].pressure;
            }
            if(_this.maxPressure<meteorologicals[i].pressure){
              _this.maxPressure = meteorologicals[i].pressure;
            }
            if(_this.minTemperature>meteorologicals[i].temperature){
              _this.minTemperature = meteorologicals[i].temperature;
            }
            if(_this.maxTemperature<meteorologicals[i].temperature){
              _this.maxTemperature = meteorologicals[i].temperature;
            }
            if(_this.minHumidity>meteorologicals[i].pressure){
              _this.minHumidity = meteorologicals[i].pressure;
            }
            if(_this.maxHumidity<meteorologicals[i].humidity){
              _this.maxHumidity = meteorologicals[i].humidity;
            }
            if(_this.maxSpeed<meteorological[i].speed){
              _this.maxSecSpeed = _this.maxSpeed;
              _this.maxSpeed = meteorological[i].speed;
              _this.maxSecSpeedDate =_this.maxSpeedDate;
              _this.maxSpeedDate = meteorological[i].cjsj;
            }else{
              if(meteorological[i].speed>_this.maxSecSpeed){
                _this.maxSecSpeed = meteorological[i].speed;
                _this.maxSecSpeedDate = meteorological[i].cjsj;
              }
            }
            if(_this.maxDirection<meteorological[i].winddirection){
              _this.maxSecDirection = _this.maxDirection;
              _this.maxDirection = meteorological[i].winddirection;
            }else{
              if(meteorological[i].winddirection>_this.maxSecDirection){
                _this.maxSecDirection = meteorological[i].winddirection;
              }
            }
          }
        }
      })
    },
    getTurbidityData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getTurbidityData', {"bz":_this.curSbbh,"stime":_this.curDate}).then((response)=>{
        let turbiditys = response.data.content;
        if(turbiditys.length>0){
          _this.turbidity = turbiditys[turbiditys.length-1];
        }
        let xAxisData = [];
        let data1 = [];
        let data2 = [];
        let data3 = [];
        for(let i=0;i<turbiditys.length;i++){
          let turbidity = turbiditys[i];
          xAxisData.push(turbidity.dateTime.substring(11,turbidity.dateTime.length));
          data1.push(turbidity.salinity);
          data2.push(turbidity.temperature);
          data3.push(turbidity.depth);
        }
        _this.initEchart(xAxisData,data1,data2,data3);
      })
    },
    initEchart(xAxisData,data1,data2,data3){
      let option = {
        grid: {
          bottom: '20px',
          top: '40px',
          left: '25px',
          right: '18px'
        },
        legend: {
          data: ['盐度','温度','深度'],
          textStyle: {
            color: "#000"
          }
        },
        tooltip: {
          trigger: "axis"
        },
        xAxis: {
          type: 'category',
          data: xAxisData,
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#000"
            }
          }
        },
        yAxis: {
          splitLine: { show: false },
          axisLine: {
            lineStyle: {
              type: "solid",
              color: "#000"
            }
          }
        },
        series: [
          {
            name: '盐度',
            type: 'line',
            data: data1,
            symbolSize: 7,
            lineStyle: {
              width: 6
            },
            itemStyle: {
              color: "blue"
            }
          },
          {
            name: '温度',
            type: 'line',
            data: data2,
            symbolSize: 7,
            lineStyle: {
              width: 6
            },
            itemStyle: {
              color: "green"
            }
          },
          {
            name: '深度',
            type: 'line',
            data: data3,
            symbolSize: 7,
            lineStyle: {
              width: 6
            },
            itemStyle: {
              color: "red"
            }
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById("initEchart"));
      echartsData.setOption(option);
    },
    getCurrentMeterData(){
      let _this = this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/environmentDp/getCurrentMeterData', {"bz":_this.curSbbh,"stime":_this.curDate}).then((response)=>{
        _this.currentMeter = response.data.content;
      })
    },
    changeDate(date){
      let _this = this;
      if(_this.curDate!=date){
        _this.curDate = date;
        _this.resetData();
      }
    },
    changeData(){
      let _this = this;
      if(_this.optionKVArray(_this.zdysbList,_this.curSbbh)!=_this.curSbmc){
        _this.curSbmc = _this.optionKVArray(_this.zdysbList,_this.curSbbh);
        _this.resetData();
      }
    },
    resetData(){
      let _this = this;
      _this.currentMeter=null;
      _this.turbidity=null;
      _this.maxPressure='/';
      _this.minPressure='/';
      _this.pressure='/';
      _this.maxTemperature='/';
      _this.minTemperature='/';
      _this.temperature='/';
      _this.maxHumidity='/';
      _this.minHumidity='/';
      _this.humidity='/';
      _this.maxSpeed='/';
      _this.maxSecSpeed='/';
      _this.speed='/';
      _this.maxSpeedDate='/';
      _this.maxSecSpeedDate='/';
      _this.maxDirection='/';
      _this.maxSecDirection='/';
      _this.direction='/';
      _this.waterQualityNew=null;
      _this.maxWaveH='/';
      _this.curWaveH='/';
      _this.avgWaveH='/';
      _this.maxWavePeriod='/';
      _this.curWavePeriod='/';
      _this.avgWavePeriod='/';
      _this.getCurrentMeterData();
      _this.getTurbidityData();
      _this.getMeteorologicalData();
      _this.getWaterQualityNewData();
      _this.getWaveDataData();
    },
    isEmpty(obj){
      if ((typeof obj == 'string')) {
        return !obj || obj.replace(/\s+/g, "") == ""
      } else {
        return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
      }
    },
    optionKVArray(list, key){
      if (!list || !key) {
        return "";
      } else {
        let result = "";
        for (let i = 0; i < list.length; i++) {
          if (key === list[i]["key"]) {
            result = list[i]["value"];
          }
        }
        return result;
      }
    }
  }
}
</script>
<style scoped>
.dp-content{
  width: 1920px;
  height: 100vh;
  background-image: url("/static/image/environment/gzadqbg.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  background-position: center center;
  display: flex;
  flex-direction: row;
}
.left-box{
  width: 20%;
  height: 100%;
  margin-left: 20px;
}
.left-top-box{
  width: 100%;
  height: calc(calc(calc(100vh - 20px) / 3) - 50px);
  margin-bottom: 10px;
  margin-top: 20px;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
}
.left-title-content{
  width: 40%;
  font-size: 18px;
  font-weight: bold;
  color: #267668;
  text-align: center;
  margin: auto;
  padding: 10px;
  border-radius: 20px;
  border-bottom: 1px solid #ccc;
}
.left-center-img-box{
  width: 100%;
  height: 25%;
  display: flex;
  flex-direction: row;
  margin-top: 10px;
}
.left-center-img-box-item{
  width: 50%;
  height: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
}
.left-center-img-box-text{
  margin-left: 10px;
  font-weight: 600;
}
.left-line-box{
  margin-left: 5%;
  margin-top: 10px;
  height: 35px;
  font-size: 16px;
  font-weight: bold;
}
.left-select-box{
  width: 75%;
  height: 35px;
  border-radius: 20px;
  padding: 0 10px;
  border: none;
}
.left-input-box{
  width: 75%;
  line-height: 35px;
  border-radius: 20px !important;
  border: none;
  font-size: 17px;
  padding-left: 15px;
  padding-top: 0;
  padding-bottom: 0;
  background-color: #fff !important;
}
.left-center-box{
  width: 100%;
  height: calc(calc(calc(100vh - 20px) / 3) + 80px);
  margin-bottom: 10px;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
}
.left-bottom-box{
  width: 100%;
  height: calc(calc(calc(100vh - 20px) / 3) - 70px);
  margin-bottom: 20px;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
}
.center-box{
  width: calc(65% - 40px);
  height: 100%;
}
.center-content{
  height: calc(100vh - 40px);
  margin: 20px 10px;
}
.center-top-box{
  height: 150px;
  display: flex;
  flex-direction: row;
}
.center-top-item{
  width: 16%;
  height: 100%;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
  margin-right: 1%;
}
.center-top-item-title{
  text-align: center;
  font-size: 18px;
  font-weight: 600;
  color: #267668;
  padding: 10px 10px 0 10px;
}
.center-top-item-content{
  display: flex;
  flex-direction: row;
  padding: 0 10px 10px 10px;
  font-size: 14px;
  font-weight: 600;
}
.center-top-item-input{
  width: 45%;
  height: 20px;
  background-color: #fff !important;
  border: none;
  border-radius: 10px !important;
  text-align: center;
}
.center-top-item-dw{
  font-weight: 300;
}
.center-center-box{
  height: 180px;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
  margin-top: 10px;
}
.center-center-content{
  width: 100%;
  display: flex;
  flex-direction: row;
  margin-top: 15px;
  padding: 0 10px;
  font-size: 14px;
  font-weight: 500;
}
.center-center-content-item-first{
  width: 7%;
}
.center-center-content-item{
  text-align: center;
  margin-left: 17px;
}
.center-center-content-item-p{
  background-color: #fff;
}
.center-center-content-last{
  width: 100%;
  height: 52px;
  line-height: 52px;
  background-color: #fff;
}
.center-bottom-box{
  height: calc(100vh - 390px);
  margin-top: 10px;
  display: flex;
  flex-direction: row;
}
.center-bottom-left{
  width: 67%;
}
.center-bottom-left-top{
  height: 55%;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
}
.center-bottom-left-top-content{
  width: 100%;
  display: flex;
  flex-direction: row;
  margin-top: 10px;
}
.center-bottom-left-top-img{
  width: 50%;
  padding: 10px;
  display: flex;
  flex-direction: row;
}
.center-bottom-left-top-img-item{
  width: 33%;
  padding: 0 10px;
  text-align: center;
  font-size: 14px;
  font-weight: 600;
}
.center-bottom-left-top-table{
  width: 50%;
  padding: 10px;
}
.center-bottom-left-top-table th,td{
  font-size: 14px;
  width: 25%;
  text-align: center;
  line-height: 40px;
}
.center-bottom-left-top-table td div{
  width: 80%;
  margin: auto;
  height: 32px;
  background-color: #fff;
}
.center-bottom-left-bottom{
  height: calc(45% - 10px);
  margin-top: 10px;
  display: flex;
  flex-direction: row;
}
.center-bottom-left-bottom-left{
  height: 100%;
  width: calc(calc(100% - 10px) / 2);
  margin-right: 10px;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
}
.center-bottom-content{
  width: 100%;
  display: flex;
  flex-direction: row;
  margin-top: 15px;
  padding: 0 10px;
  font-size: 14px;
  font-weight: 500;
  text-align: center;
}
.center-bottom-content-item{
  margin-left: 10px;
}
.center-bottom-content-item-p{
  background-color: #fff;
}
.center-bottom-left-bottom-right{
  height: 100%;
  width: calc(calc(100% - 10px) / 2);
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
}
.center-bottom-right{
  width: 32%;
  margin-left: 1%;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
}
.center-bottom-right-table{
  width: 100%;
  text-align: center;
}
.center-bottom-right-table th,td{
  font-size: 14px;
  width: 20%;
  text-align: center;
  line-height: 40px;
}
.center-bottom-right-table tr{
  height: 38px;
}
.center-bottom-right-table div{
  width: 80%;
  margin: auto;
  height: 32px;
  background-color: #fff;
}
.right-box{
  width: 15%;
  height: 100%;
  margin-right: 10px;
}
.right-content-box{
  width: 100%;
  height: calc(100vh - 40px);
  margin-top: 20px;
  background-color: #E3EFEF;
  border-radius: 30px;
  border: 1px solid #00aa00;
}
.right-content{
  width: 100%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.right-content-item{
  width: 40%;
  height: 65px;
  margin-left: 10%;
  margin-top: 20px;
}
.right-input-box{
  width: 80%;
  line-height: 20px;
  border-radius: 20px !important;
  border: none;
  font-size: 17px;
  padding-top: 0;
  padding-bottom: 0;
  background-color: #fff !important;
  text-align: center;
  margin-top: 10px;
}
.left-line-box /deep/#curdateid{
  height: 35px;
  border: none;
  padding: 0 10px;
  font-size: 16px;
  border-bottom-left-radius: 20px !important;
  border-top-left-radius: 20px !important;
}
</style>