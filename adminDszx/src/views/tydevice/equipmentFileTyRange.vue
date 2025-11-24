<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">路线图</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  采集时间：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" v-bind:svalue="stime" v-bind:evalue="etime" start-id="rStime" end-id="rEtime"></times>
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
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </a>
                  <button type="button" v-on:click="exportExcel()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-download"></i>
                    导出
                  </button>
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
import Times from "../../components/times";
export default {
  components: {Times},
  name: 'equipment-file-ty-range',
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
      ssbrl:LOCAL_SSBRL,
      stime:'',
      etime:'',
      picServer:process.env.VUE_APP_PIC_SERVER
    }
  },
  created() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxHeight = h*0.8-110;
    let w = document.documentElement.clientWidth || document.body.clientWidth;
    _this.maxWidth = w-230;
    _this.stime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*7));
    _this.etime = Tool.dateFormat("yyyy-MM-dd",new Date(new Date().getTime()-3600000*24*1));
    _this.$forceUpdate();
    _this.getEquipmentByTy();
  },
  mounted() {
    let _this = this;
    _this.initMap();
  },
  methods: {
    exportExcel(){
      let _this = this;
      let paramsStr = "sbbh="+_this.curSbsn+"&stime="+_this.stime+"&etime="+_this.etime;
      let url = process.env.VUE_APP_SERVER + '/monitor/export/exportPontoonGps?'+paramsStr;
      window.location.href = url;
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.etime = rep;
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
          _this.amap.setCenter(new TLngLat(_this.jd,_this.wd));
          _this.getMapMark();
        }
      })
    },
    getMapMark(){
      let _this = this;
      _this.amap.clearOverLays();
      _this.getGpsBySbsn();
      _this.selectGpsOb();
      _this.getPontoonGpsBySbsn();
    },
    /**
     * 获取趸船的gps
     */
    getPontoonGpsBySbsn(){
      let _this = this;
      let obj = {
        "stime":_this.stime,
        "etime":_this.etime,
        "sbbh":_this.curSbsn
      }
      if("460100"!=Tool.getLoginUser().deptcode){
        obj.xmbh = Tool.getLoginUser().xmbh;
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/pontoonGps/selectGpsByDateRange', obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        let content = resp.content;
        if(content){
          for(let sbsn in content){
            let rqmap = content[sbsn];
            for(let rq in rqmap){
              let gpslist = rqmap[rq];
              if(gpslist && gpslist.length>0){
                let polylineArr = new Array();//多边形覆盖物节点坐标数组
                gpslist.forEach(function (item){
                  let itemarr = item.gps.split(",");
                  const lng = parseFloat(itemarr[0]);
                  const lat = parseFloat(itemarr[1].trim());
                  if(lng != 0 && lat != 0 && !isNaN(lng) && !isNaN(lat)){
                    polylineArr.push(new TLngLat(lng, lat));
                  }
                })
                _this.amap.setCenter(polylineArr[Math.floor(polylineArr.length / 2)]);
                let  polyline = new TPolyline (polylineArr, {
                  strokeWeight: 6, // 线条宽度，默认为 1
                  strokeColor: '#dd0310', // 线条颜色
                  strokeStyle: 'solid' // 折线拐点连接处样式
                });
                _this.amap.addOverLay(polyline);
              }
            }
          }
        }
      })
    },
    /**
     * 获取豚类的gps
     */
    getGpsBySbsn(){
      let _this = this;
      let obj = {
        "stime":_this.stime,
        "etime":_this.etime,
        "sbbh":_this.curSbsn
      }
      if("460100"!=Tool.getLoginUser().deptcode){
        obj.xmbh = Tool.getLoginUser().xmbh;
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileT/selectGpsByDateRange', obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        let gpslist = resp.content;
        if(gpslist && gpslist.length>0){
          gpslist.forEach(function (item){
            if(item.gps.split(",")[0]!='0'&&item.gps.split(",")[0]!='NaN'){
              let itemarr = item.gps.split(",");
              const lng = parseFloat(itemarr[0]);
              const lat = parseFloat(itemarr[1].trim());
              let lnglat = new TLngLat(lng, lat);
              let icon = new TIcon(window.location.origin+'/static/image/markerBlue.png', new TSize(43, 43));
              let marker = new TMarker(lnglat,{icon:icon});
              _this.amap.addOverLay(marker);
              if(item.ts){
                // 设置标记的文本
                let label=new TLabel({
                  text:item.ts,
                  offset:new TPixel(-5,-25),
                  position:lnglat
                });
                label.setZIndex(999);
                label.setBackgroundColor('#1296DB');
                label.setBorderColor('#1296DB');
                _this.amap.addOverLay(label);
              }
            }
          })
        }
      })
    },
    /**
     * 获取人工观察数据
     */
    selectGpsOb(){
      let _this = this;
      let obj = {
        "stime":_this.stime,
        "etime":_this.etime,
        "sbbh":_this.curSbsn
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileTyToday/selectGpsOb', obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        let gpslist = resp.content;
        if(gpslist && gpslist.length>0){
          gpslist.forEach(function (item){
            if(item.declat != '0' && item.declat != 'NaN'){
              let lnglat = new TLngLat(parseFloat(item.declat), parseFloat(item.declong));
              let icon = new TIcon(window.location.origin+'/static/image/markerRed.png', new TSize(43, 43));
              let marker = new TMarker(lnglat,{icon:icon});
              _this.amap.addOverLay(marker);
              if(item.data6){
                // 设置标记的文本
                let label=new TLabel({
                  text:item.data6,
                  offset:new TPixel(-5,-25),
                  position:lnglat
                });
                label.setZIndex(999);
                label.setBackgroundColor('#D81E06');
                label.setBorderColor('#D81E06');
                _this.amap.addOverLay(label);
              }
            }
          })
        }
      })
    },
    initMap(){
      let _this = this;
      if(_this.ssbrl){
        _this.amap = new TMap('mapDiv', {
          resizeEnable: true,
          zoom: _this.zoom
        });
      }
      if(_this.zhbht){
        _this.amap = new TMap('mapDiv', {
          resizeEnable: true,
          center:[113.333132,23.114138],
          zoom: _this.zoom
        });
      }
      _this.amap.enableHandleMouseScroll();
    }
  }
}
</script>