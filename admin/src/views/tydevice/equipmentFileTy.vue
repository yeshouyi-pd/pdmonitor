<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">图片管理查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  日期：
                </td>
                <td style="width: 25%">
                  <Dategreater @methodName="dateMethod" style="width:100%" idValue="tyDateStr"   :setValue="curDateStr"></Dategreater>
                </td>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 20%">
                  <select v-model="curSbsn" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in equipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td colspan="2" class="text-center">
                  <button type="button" v-on:click="getMapMark()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </a>
                </td>
              </tr>
              </tbody>
            </table>
          </form>
        </div>
      </div>
    </div>
    <div id="mapDiv" style="position:absolute;" :style="{height:+maxHeight+'px',width:+maxWidth+'px'}"></div>
  </div>
</template>
<script>
import Dategreater from "../../components/dategreater";
export default {
  components: {Dategreater},
  name: 'equipment-file-ty',
  data: function (){
    return {
      equipments:[],
      curSbsn:'',
      curDateStr:'',
      jd:0,
      wd:0,
      map:null,
      zoom:11,
      markerClusterer:null,
      markers:[],
      maxHeight:'',
      maxWidth:'',
      amap:'',
      zhbht:LOCAL_ZHBHT,
      ssbrl:LOCAL_SSBRL
    }
  },
  created() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8-110;
    let w = document.documentElement.clientWidth || document.body.clientWidth;
    _this.maxWidth = w-230;
    _this.curDateStr = Tool.dateFormat("yyyy-MM-dd");
    _this.getEquipmentByTy();
  },
  mounted() {
    let _this = this;
    _this.initMap();
  },
  methods: {
    dateMethod(rep){
      let _this = this;
      _this.curDateStr = rep;
      _this.$forceUpdate();
    },
    //获取拖曳设备
    getEquipmentByTy(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001','dqzl':'A2'};
      }else{
        data = {'sblb':'0001','dqzl':'A2','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.equipments = response.data.content;
        if(_this.equipments.length>0){
          _this.curSbsn = _this.equipments[0].sbsn;
          let arr = _this.equipments[0].gps.split(",");
          _this.jd = Number(arr[0]);
          _this.wd = Number(arr[1]);
          _this.getMapMark();
        }
      })
    },
    getMapMark(){
      let _this = this;
      _this.amap.clearMap();
      _this.getGpsBySbsn();
      _this.getPontoonGpsBySbsn();
    },
    /**
     * 获取趸船的gps
     */
    getPontoonGpsBySbsn(){
      let _this = this;
      let obj = {
        "rq":_this.curDateStr,
        "sbbh":_this.curSbsn
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/pontoonGps/selectGps', obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        let gpslist = resp.content;
        if(gpslist && gpslist.length>0){
          let polylineArr = new Array();//多边形覆盖物节点坐标数组
          _this.amap.setCenter([gpslist[0].split(",")[0],gpslist[0].split(",")[1]]);
          gpslist.forEach(function (item){
            let itemarr = item.split(",");
            polylineArr.push(new AMap.LngLat(itemarr[0],itemarr[1].trim()));
          })
          console.log(polylineArr);
          let  polyline = new AMap.Polyline ({
            path: polylineArr,//设置多边形边界路径
            borderWeight: 2, // 线条宽度，默认为 1
            strokeColor: '#dd0310', // 线条颜色
            lineJoin: 'round' // 折线拐点连接处样式
          });
          _this.amap.add(polyline);
        }
      })
    },
    /**
     * 获取豚类的gps
     */
    getGpsBySbsn(){
      let _this = this;
      let obj = {
        "rq":_this.curDateStr,
        "sbbh":_this.curSbsn
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileTyToday/selectTodayGps', obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        let gpslist = resp.content;
        if(gpslist && gpslist.length>0){
          _this.amap.setCenter(gpslist[0].gps.split(","));
          gpslist.forEach(function (item){
            let marker = new AMap.Marker({
              position: item.gps.split(","),
              icon: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
              anchor:'bottom-center',
              offset: new AMap.Pixel(0, 0),
              map: _this.amap
            });
            marker.setLabel({
              direction:'center',
              offset:new AMap.Pixel(0, -5),
              content: item.ts, //设置文本标注内容
            });
          })
        }
      })
    },
    initMap(){
      let _this = this;
      if(_this.ssbrl){
        _this.amap = new AMap.Map('mapDiv', {
          resizeEnable: true,
          zoom: _this.zoom
        });
      }
      if(_this.zhbht){
        _this.amap = new AMap.Map('mapDiv', {
          resizeEnable: true,
          center:[113.333132,23.114138],
          zoom: _this.zoom
        });
      }
    }
  }
}
</script>