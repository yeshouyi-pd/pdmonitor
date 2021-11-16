<template>
  <div style="width: 100%;height: 100%;">
    <div id="map-top" style="width: 115px;height: 80px;border: 1px solid #EEEEEE;position: absolute;left: 12px;z-index: 100;background-color: #fff;">
      <div style="padding:5px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#03C449;padding-right: 10px"></i>设备正常 {{onLineCount}}个</div>
      <div style="padding:0px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#555555;padding-right: 10px"></i>设备离线 {{offLineCount}}个</div>
      <div style="padding:0px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#B03A5B;padding-right: 10px"></i>设备故障 {{errorCount}}个</div>
    </div>
    <div :style="{height: heightMax + 'px'}">
      <div id="containerMap" style="width:100%;height: 100%"></div>
    </div>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" style="width: 50%;" role="document">
        <div class="modal-content">
          <div style="float:left;width: 50%;overflow-y:scroll;">
            <div class="list-group" style="height: 500px;">
              <button v-for="(item,index) in equipmentFiles" type="button" @click="showRealPic(item.tplj)" style="cursor: pointer;border:1px solid #3490BA;" class="list-group-item">{{item.cjsj}}</button>
            </div>
          </div>
          <div style="float:left;width: 50%;">
            <div style="border: 0px solid red;text-align:center;">
              <img :src=srcpic style="height: 520px;width:50%;">
            </div>
          </div>
          <div class="clear"></div>
          <div class="modal-footer" style="background-color: #fff">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
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
      amap:'',
      equipmentFiles:[],
      srcpic:''
    }
  },
  mounted() {
    let _this = this;
    _this.amap = new AMap.Map('containerMap', {
      center: [114.299945,30.593221],
      resizeEnable: true,
      zoom: 5
    });

    let userInfo = Tool.getLoginUser();
    _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/findAll/' + Tool.getLoginUser().deptcode).then((response)=>{
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
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', {'xmbh':Tool.getLoginUser().xmbh}).then((response)=>{
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
          var icon = new AMap.Icon({
            image: 'https://vdata.amap.com/icons/b18/1/2.png',
            size: new AMap.Size(24, 24)
          });
          if("0001"==devices[i].sblb){
            let marker = new AMap.Marker({
              icon: icon,
              position: devices[i].gps.split(','),
              offset: new AMap.Pixel(-12,-12),
              zIndex: 101,
              map: _this.amap
            });
            marker.content = [];
            marker.content.push(devices[i].deptcode);
            marker.content.push(devices[i].centerCode);
            marker.content.push(devices[i].sbmc);
            marker.content.push(devices[i].sbsn);
            marker.content.push(devices[i].sblb);
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
              strokeColor: "#FF33FF",
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
            marker.content.push(devices[i].sbmc);
            marker.content.push(devices[i].sbsn);
            marker.content.push(devices[i].sblb);
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

      let middle1 = document.createElement("div");
      middle1.innerHTML = "<div>所属监测点："+_this.optionMapKV(_this.deptMap,content[0])+"</div>";
      middle.appendChild(middle1);
      let middle2 = document.createElement("div");
      middle2.innerHTML = "<div>所属数据中心："+_this.optionWDArray(_this.waterDatas,content[1])+"</div>";
      middle.appendChild(middle2);
      let middle3 = document.createElement("div");
      middle3.innerHTML = "<div>设备名称："+content[2]+"</div>";
      middle.appendChild(middle3);
      if("0001"==content[4]){
        let middle4 = document.createElement("div");
        middle4.innerHTML = "<div class='"+content[3]+"' style='cursor: pointer;'>设备编号："+content[3]+"<span style='margin:0 10px;color: blue;' class='"+content[3]+"'>详情</span></div>";
        middle4.addEventListener('click',function (e){
          console.log(e.target.className);
          _this.listsbbh(e.target.className);
        })
        middle.appendChild(middle4);
      }else if("0002"==content[4]){
        let middle4 = document.createElement("div");
        middle4.innerHTML = "<div>设备编号："+content[3]+"</div>";
        middle.appendChild(middle4);
      }
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
    /**
     * 列表查询
     */
    listsbbh(sbbh) {
      let _this = this;
      Loading.show();
      let obj = {};
      obj.sbbh=sbbh;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/listsbbh',obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFiles = resp.content;
        if(_this.equipmentFiles.length > 0){
          _this.srcpic = _this.equipmentFiles[0].tplj;
          _this.$forceUpdate();
          $("#form-modal").modal("show");
        }else {
          Toast.warning("暂无数据");
        }
      })
    },
    /**
     * 图片查看
     */
    showRealPic(tplj){
      let _this = this;
      _this.srcpic = tplj;
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
</style>