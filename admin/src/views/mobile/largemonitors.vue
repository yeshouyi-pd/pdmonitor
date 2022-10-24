<template>
  <div class="wrap" id="app">
    <header style="position: relative;">
      <img src="/largemonitors/assets/imgs/headertitle.png" style="position: absolute;left: 39%;top:20px;width: 450px;">
      <div class="lefttitle">
        <img src="/largemonitors/assets/imgs/左上角title.png" alt="">
        <span>
          <div @click="chooseProject" style="cursor: pointer;">主页</div>
<!--          <div style="margin: 0 10px;"> / </div>-->
<!--          <div @click="toTyDp" style="cursor: pointer;">巡护</div>-->
        </span>
      </div>
    </header>
    <div class="content">
      <div class="pain">
        <div class="h25">
          <div class="imgs">
<!--            <video width="100%" height="100%" poster="/largemonitors/assets/imgs/video.png">-->
<!--              <source src="movie.mp4" type="video/mp4">-->
<!--              <source src="movie.ogg" type="video/ogg">-->
<!--              <source src="movie.webm" type="video/webm">-->
<!--              <object data="movie.mp4">-->
<!--                <embed src="movie.swf">-->
<!--              </object>-->
<!--            </video>-->
            <video width="100%" height="100%" autoplay="autoplay" loop="loop">
              <source class="video" title="主监控位" src="/video/12.mp4"/>
            </video>
          </div>
        </div>
        <div class="h37">
          <div class="imgs">
            <div style="height: 50%;">
              <video width="100%" height="100%" poster="/largemonitors/assets/imgs/video.png">
                <source src="movie.mp4" type="video/mp4">
                <source src="movie.ogg" type="video/ogg">
                <source src="movie.webm" type="video/webm">
                <object data="movie.mp4">
                  <embed src="movie.swf">
                </object>
              </video>
            </div>
            <div style="height: 50%;">
              <video width="100%" height="100%" poster="/largemonitors/assets/imgs/video.png">
                <source src="movie.mp4" type="video/mp4">
                <source src="movie.ogg" type="video/ogg">
                <source src="movie.webm" type="video/webm">
                <object data="movie.mp4">
                  <embed src="movie.swf">
                </object>
              </video>
            </div>

          </div>
        </div>
        <div class="h37">
          <span>鲸豚出现事件统计图</span>
          <div class="imgs">
            <div id="barChart" style="width: 438px ;height: 290px;"></div>
          </div>
        </div>
      </div>
      <div class="bcenter">
        <div class="h63">
<!--          <div class="imgs">-->
<!--            <div id="mp" style="width: 100% ;height: 100%;"></div>-->
<!--          </div>-->
          <EquipmentAMap v-bind:height-max="heightMax"></EquipmentAMap>
        </div>
        <div class="h37">
          <span>分析数据</span>
          <div class="imgs" style="padding: 25px;">
            <dv-scroll-board :config="config" style="width:100% ;height:100%" ref="scrollBoard"/>
          </div>
        </div>
      </div>
      <div class="pain">
        <div class="h25">
          <div class="imgs" style="display: flex;justify-content: space-around;align-items: center;">
            <div v-for="(item,index) in right1" :key="index" style="position: relative;">
              <el-progress :width="100" :stroke-width="5" stroke-linecap="round" type="circle"
                           :percentage="getTotal(item.value)" :color="customColorMethod(item.value)"
                           :show-text="false">
              </el-progress>
              <div :style="`width:100%;text-align:center;position: absolute;left: 50%;top: 50%;transform: translate(-50%,-50%);color:${customColorMethod(item.value)}`">
                {{getFormat(item.value)}}
              </div>
              <div style="width: 100%;position: absolute;
                            bottom: -20px;
                            font-size: 12px;
                            color: #fff;text-align: center;">{{item.label}}</div>
            </div>

          </div>

        </div>
        <div class=" h37">
          <div class="imgs">
            <dv-scroll-board :config="config2"  ref="scrollBoard2"/>
          </div>
        </div>
        <div class="h37">
          <span>设备故障率</span>
          <div class="imgs">
            <div style="width:100%;height:70%">
              <div id="ggg" style="width: 438px; height: 203px"></div>
            </div>
            <div style="width:100%;height:30%;display: flex;">
              <div style="width:25%;height:100%;display: flex;align-items: center;color: #fff;">
                <div id="t1" style="width: 55px; height: 87px; padding: 0px; margin: 0px; border-width: 0px;"></div>
                <div>
                  <div style="margin-bottom: 10px;">总数</div>
                  <div style="color: #49C6E5;">{{ zs }}</div>
                </div>
              </div>
              <div style="width:25%;height:100%;display: flex;align-items: center;color: #fff;">
                <div id="t2" style="width: 55px; height: 87px; padding: 0px; margin: 0px; border-width: 0px;"></div>
                <div>
                  <div style="margin-bottom: 10px;">在线</div>
                  <div style="color: #49C6E5;">{{ zc }}</div>
                </div>
              </div>
              <div style="width:25%;height:100%;display: flex;align-items: center;color: #fff;">
                <div id="t3" style="width: 55px; height: 87px; padding: 0px; margin: 0px; border-width: 0px;"></div>
                <div>
                  <div style="margin-bottom: 10px;">离线</div>
                  <div style="color: #49C6E5;">{{ lx }}</div>
                </div>
              </div>
              <div style="width:25%;height:100%;display: flex;align-items: center;color: #fff;">
                <div id="t4" style="width: 55px; height: 87px; padding: 0px; margin: 0px; border-width: 0px;"></div>
                <div>
                  <div style="margin-bottom: 10px;">故障</div>
                  <div style="color: #49C6E5;">{{ gz }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" style="width: 50%;" role="document">
        <div class="modal-content" style="background: #395DC0;">
          <div style="float:left;width: 50%;overflow-y:scroll;">
            <div class="list-group" style="height: 500px;">
              <button v-for="(item,index) in equipmentFiles" type="button" @click="showRealPic(item.tplj)" style="background: #395DC0; color:#FEFEFF;cursor: pointer;border:1px solid #3490BA;" class="list-group-item">{{moment(item.cjsj).format("YYYY-MM-DD HH:mm:ss")}}</button>
            </div>
          </div>
          <div style="float:left;width: 50%;">
            <div style="border: 0px solid red;text-align:center;">
              <img alt="无图片" :src=srcpic style="height: 520px;width:50%;">
            </div>
          </div>
          <div class="clear"></div>
          <div class="modal-footer" style="background: #395DC0;">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>
<script>
import EquipmentAMap from "../monitor/equipmentAMap";
export default {
  name:'largemonitors',
  components:{EquipmentAMap},
  data: function (){
    return {
      config: {
        headerBGC: "#1F2C94",
        oddRowBGC: "#1F2C94",
        evenRowBGC: "",
        align: ['center', 'center', 'center', 'center'],
        header: ['监测点', '设备编号', '声学侦测次数', '操作'],
        data: []
      },
      config2: {
        headerBGC: "#13498F",
        oddRowBGC: "#0E2B6D",
        evenRowBGC: "",
        align: ['center', 'center', 'center', 'center'],
        header: [`<span style="color:#D1E27F">设备编号</span>`, `<span style="color:#D1E27F">开始时间</span></span>`, `<span style="color:#D1E27F">结束时间</span>`, `<span style="color:#D1E27F">头数</span>`],
        columnWidth: [150, 100, 100, 50],
        data: []
      },
      right1: [],
      lx:0,
      gz:0,
      zc:0,
      zs:0,
      intervalId:null,
      deptMap:[],
      equipmentFiles:[],
      srcpic:'',
      heightMax:'',
    }
  },
  created() {
    let _this = this;
    _this.getAlarmEventStatistics();//左下角出现事件统计图
    _this.getWarningDate();//中间下方，获取各个设备的声学侦测次数和详情
    _this.getRightTopData();//右上角获取当日声学侦测次数、事件(群次)、捕食次数
    _this.getEventData();//右侧中间获取事件数据
    _this.getDeviceUsed();//右下角设备在线率
    _this.deptMap = Tool.getDeptUser();
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.heightMax = h*0.6-20;
    // _this.map();
    _this.dataRefreh();
    window.jsmethod = _this.jsmethod;
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
        _this.getAlarmEventStatistics();//左下角出现事件统计图
        _this.getWarningDate();//中间下方，获取各个设备的声学侦测次数和详情
        _this.getRightTopData();//右上角获取当日声学侦测次数、事件(群次)、捕食次数
        _this.getEventData();//右侧中间获取事件数据
        _this.getDeviceUsed();//右下角设备在线率
      }, 600000);
    },
    // 停止定时器
    clear() {
      let _this = this;
      clearInterval(_this.intervalId); //清除计时器
      _this.intervalId = null; //设置为null
    },
    chooseProject(){
      window.location.href = "/admin/chooseProject";
    },
    toTyDp(){
      window.location.href = "/mobile/largemonitorsTy";
    },
    getAlarmEventStatistics(){
      let _this = this;
      let obj = {};
      obj.deptcode = Tool.getLoginUser().deptcode;
      obj.stime = moment().subtract(7, "days").format('YYYY-MM-DD');
      obj.etime = moment().subtract(1, "days").format('YYYY-MM-DD');
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileAlarmEvent/echartsAlarmDataByDp',obj).then((response)=>{
        let resp = response.data;
        let containerDate = resp.content;
        _this.barChart(containerDate.yAixsData,containerDate.xAixsData);
      })
    },
    barChart(yAixsData,xAixsData) {
      let chart = echarts.init(document.getElementById('barChart'))
      let option = {
        grid: {
          left: '15%',
          right: '15%',
          bottom: '10%'
        },
        xAxis: {
          type: 'category',
          data: xAixsData,
          name: ''+moment().subtract(0, "days").format('YYYY-MM')+'',
          nameTextStyle: {
            color: '#Fff'
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: '#fff',
          },

        },
        yAxis: {
          name: '事件次数',
          type: 'value',
          nameTextStyle: {
            color: '#Fff'
          },
          axisLabel: {
            color: '#fff'
          },
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: false
          }
        },
        series: [
          {
            barWidth: 15,
            data: yAixsData,
            type: 'bar',
            itemStyle: {
              color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    { offset: 0, color: '#43BB8F' },
                    { offset: 1, color: '#28B4D8' }
                  ]
              )
            }
          }
        ]
      }
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    getWarningDate() {
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getWarningDate').then((res)=>{
        let response = res.data;
        let kvMaps = response.content;
        for(let i=0;i<kvMaps.length;i++){
          let item = kvMaps[i];
          let arrItem = [_this.optionMapKV(_this.deptMap,item.deptcode), item.text, `<span style="color:#D1E27F">${item.code}</span>`, `<div class="btn-detail" onclick="jsmethod('${item.text}')">查看详情</div>`];
          _this.config.data.push(arrItem);
        }
        _this.$refs['scrollBoard'].updateRows(_this.config.data, 0);
      })
    },
    optionMapKV(object, key) {
      if (!object || !key) {
        return "";
      } else {
        let result = "";
        for (let enums in object) {
          if (key === enums) {
            result = object[enums];
          }
        }
        return result;
      }
    },
    jsmethod(sbbh){
      console.log(sbbh);
      let _this = this;
      _this.listsbbh(sbbh);
      _this.$forceUpdate();
      $("#form-modal").modal("show");
    },
    /**
     * 列表查询
     */
    listsbbh(sbbh) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileToday/listsbbh',{'sbbh':sbbh}).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFiles = resp.content;
        if(_this.equipmentFiles.length > 0){
          _this.srcpic = _this.equipmentFiles[0].tplj.substring(0,_this.equipmentFiles[0].tplj.lastIndexOf('.')+1)+'jpg';
        }
      })
    },
    /**
     * 图片查看
     */
    showRealPic(tplj){
      let _this = this;
      _this.srcpic = tplj.substring(0,tplj.lastIndexOf('.')+1)+'jpg';
    },
    getRightTopData(){
      let _this = this;
      let obj = {};
      if("460100"!=Tool.getLoginUser().deptcode){
        obj.deptcode = Tool.getLoginUser().deptcode;
      }
      obj.stime = moment().subtract(0, "days").format('YYYY-MM-DD');
      obj.etime = moment().subtract(0, "days").format('YYYY-MM-DD');
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileToday/statisticsAlarmNumsByTimeSum', obj).then((response) => {
        let resp = response.data;
        let alarmDatas = resp.content;
        let zccs = { label: '当日声学侦测次数', value: alarmDatas.num };
        let sjcs = { label: '当日事件(群次)', value: alarmDatas.nnm };
        let bscs = { label: '当日捕食次数', value: alarmDatas.bnum };
        _this.right1 = [zccs,sjcs,bscs];
      })
    },
    /**
     * 事件数据
     */
    getEventData(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getEventData').then((res)=>{
        let response = res.data;
        let eventDatas = response.content;
        _this.config2.data = []
        for(let i=0;i<eventDatas.length;i++){
          let item = eventDatas[i];
          let arrItem = [item.sbbh,item.kssj.substring(11),item.jssj.substring(11),item.ts];
          _this.config2.data.push(arrItem);
        }
        this.$refs['scrollBoard2'].updateRows(_this.config2.data, 0);
      })
    },
    getDeviceUsed(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/welcome/getPieChart').then((res)=>{
        let response = res.data;
        let data = response.content;
        for(let i=0;i<data.length;i++){
          let item = data[i];
          if("故障"==item.label){
            _this.gz = item.data;
          }else if("离线"==item.label){
            _this.lx = item.data;
          }else if("正常"==item.label){
            _this.zc = item.data;
          }
          _this.zs += item.data;
        }
        _this.$nextTick(function (){
          _this.ggg()
          _this.t1()
          _this.t2()
          _this.t3()
          _this.t4()
        })
      })
    },
    getTotal(val) {
      let total = 0
      this.right1.forEach((num) => {
        total += num.value
      })
      return ((val / total) * 100)
    },
    customColorMethod(val) {
      if (val < 70) {
        return '#00DDFF';
      } else if (val < 170) {
        return '#80FFA5';
      } else if (val < 2000) {
        return '#C94A6F';
      } else if (val > 2000) {
        return '#37A2FF';
      }
    },
    getFormat(item) {
      return item + '次'
    },
    map() {
      var map = new BMap.Map("mp")
      var point = new BMap.Point(114.376839, 30.657386)
      map.centerAndZoom(point, 12)
      this.$nextTick(() => {
        map.setMapStyleV2({ styleId: 'b617c51a0b7f7dce5f2ab5e325527308' })
      })
    },
    ggg() {
      let _this = this;
      let chart = echarts.init(document.getElementById('ggg'))
      let option = {
        color: ['#123C91', new echarts.graphic.LinearGradient(
            0, 0, 0, 1,
            [
              { offset: 0, color: '#75E26B' },
              { offset: 1, color: '#1E95D7' }
            ]
        )],
        series: [
          {
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: true,
            itemStyle: {
              borderRadius: 5,
              borderColor: '#123C91',
              borderWidth: 0
            },
            data: [
              { value: _this.lx, name: '离线' },
              { value: _this.zc, name: '在线' },
            ]
          }
        ]
      };
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    t1() {
      let _this = this;
      let chart = echarts.init(document.getElementById('t1'))
      let option = {
        color: ['#75E26B'],
        series: [
          {
            title: {
              text: '总量',
              subtext: 'Fake Data',
              left: 'right'
            },
            type: 'pie',
            radius: ['30%', '50%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 5,
              borderColor: '#123C91',
              borderWidth: 0
            },
            label: { show: false, },
            data: [
              { value: _this.zs, name: '总量' },
            ]
          },
        ]
      };
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    t2() {
      let _this = this;
      let chart = echarts.init(document.getElementById('t2'))
      let option = {
        color: ['#123C91', new echarts.graphic.LinearGradient(
            0, 0, 0, 1,
            [
              { offset: 0, color: '#75E26B' },
              { offset: 1, color: '#1E95D7' }
            ]
        )],
        series: [
          {
            title: {
              text: '在线',
              subtext: 'Fake Data',
              left: 'right'
            },
            type: 'pie',
            radius: ['30%', '50%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 5,
              borderColor: '#123C91',
              borderWidth: 0
            },
            label: { show: false, },
            data: [
              { value: _this.zs, name: '总量' },
              { value: _this.zc, name: '在线' },
            ]
          },
        ]
      };
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    t3() {
      let _this = this;
      let chart = echarts.init(document.getElementById('t3'))
      let option = {
        color: ['#123C91', new echarts.graphic.LinearGradient(
            0, 0, 0, 1,
            [
              { offset: 0, color: '#75E26B' },
              { offset: 1, color: '#1E95D7' }
            ]
        )],
        series: [
          {
            title: {
              text: '离线',
              subtext: 'Fake Data',
              left: 'right'
            },
            type: 'pie',
            radius: ['30%', '50%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 5,
              borderColor: '#123C91',
              borderWidth: 0
            },
            label: { show: false, },
            data: [
              { value: _this.zs, name: '总量' },
              { value: _this.lx, name: '离线' },
            ]
          },
        ]
      };
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    t4() {
      let _this = this;
      let chart = echarts.init(document.getElementById('t4'))
      let option = {
        color: ['#123C91', new echarts.graphic.LinearGradient(
            0, 0, 0, 1,
            [
              { offset: 0, color: '#75E26B' },
              { offset: 1, color: '#1E95D7' }
            ]
        )],
        series: [
          {
            title: {
              text: '故障',
              subtext: 'Fake Data',
              left: 'right'
            },
            type: 'pie',
            radius: ['30%', '50%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 5,
              borderColor: '#123C91',
              borderWidth: 0
            },
            label: { show: false, },
            data: [
              { value: _this.zs, name: '总量' },
              { value: _this.gz, name: '故障' },
            ]
          },
        ]
      };
      chart.setOption(option)
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
  }
}
</script>
<style scoped>

html,
body,
div,
span,
applet,
object,
iframe,
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote,
pre,
a,
abbr,
acronym,
address,
big,
cite,
code,
del,
dfn,
em,
img,
ins,
kbd,
q,
s,
samp,
small,
strike,
strong,
sub,
sup,
tt,
var,
b,
u,
i,
center,
dl,
dt,
dd,
ol,
ul,
li,
fieldset,
form,
label,
legend,
table,
caption,
tbody,
tfoot,
thead,
tr,
th,
td,
article,
aside,
canvas,
details,
embed,
figure,
figcaption,
footer,
header,
hgroup,
menu,
nav,
output,
ruby,
section,
summary,
time,
mark,
audio,
video {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  font-weight: normal;
  vertical-align: baseline;
}

/* HTML5 display-role reset for older browsers */
article,
aside,
details,
figcaption,
figure,
footer,
header,
hgroup,
menu,
nav,
section {
  display: block;
}

ol,
ul,
li {
  list-style: none;
}

blockquote,
q {
  quotes: none;
}

blockquote:before,
blockquote:after,
q:before,
q:after {
  content: '';
  content: none;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}

/* custom */
a {
  color: #7e8c8d;
  text-decoration: none;
  -webkit-backface-visibility: hidden;
}

::-webkit-scrollbar {
  width: 5px;
  height: 5px;
}

::-webkit-scrollbar-track-piece {
  background-color: rgba(0, 0, 0, 0.2);
  -webkit-border-radius: 6px;
}

::-webkit-scrollbar-thumb:vertical {
  height: 5px;
  background-color: rgba(125, 125, 125, 0.7);
  -webkit-border-radius: 6px;
}

::-webkit-scrollbar-thumb:horizontal {
  width: 5px;
  background-color: rgba(125, 125, 125, 0.7);
  -webkit-border-radius: 6px;
}

html,
body {
  width: 100%;
  font-family: 'syn', "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}

body {
  line-height: 1;
  -webkit-text-size-adjust: none;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
/deep/.imgs .dv-scroll-board .header{
  line-height: 0px;
  margin-bottom: 0px;
  margin-top: 0px;
  padding-bottom: 0px;
  border-bottom: none;
}
/deep/.el-progress-circle__track {
  stroke: #061073;
}
</style>