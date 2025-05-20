<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">视频管理查询</h4>
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
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="uavStartTime" end-id="uavEndTime"></times>
                </td>
                <td style="width:20%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
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

    <div class="video-box">
      <div class="video-item" v-for="(item,index) in uavFlyVideoList">
        <video style="width: 100%;height: 90%" :src="item.videoUrl" controls></video>
        <div style="display: flex;flex-wrap: nowrap;flex-direction: row;justify-content: space-around;">
          <div>
            <button type="button" v-on:click="correlation(item)" v-if="!(item.tpids && item.jlid && item.sm)" class="btn btn-sm btn-grey btn-round" style="margin-right: 10px;">
              关联
            </button>
          </div>
          <div style="margin: auto;">{{item.cjsj}}</div>
          <div>
            <button type="button" v-on:click="showDetail(item)" v-if="item.tpids || item.jlid || item.sm" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">
              详情
            </button>
          </div>
        </div>
      </div>
    </div>
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>

    <div id="correlation-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" style="width: 80%;height: auto" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">关联</h4>
          </div>
          <div class="modal-body">
            <ul class="nav nav-tabs padding-18 tab-size-bigger" id="myTab">
              <li v-if="!item.tpids" :class="tabIndex=='1'?'active':''" v-on:click="changeTab(1)">
                <a data-toggle="tab" href="#faq-tab-1" aria-expanded="true">
                  关联声谱图
                </a>
              </li>
              <li v-if="!item.jlid" :class="tabIndex=='2'?'active':''" v-on:click="changeTab(2)">
                <a data-toggle="tab" href="#faq-tab-2" aria-expanded="false">
                  关联A4聚类
                </a>
              </li>
              <li v-if="!item.sm" :class="tabIndex=='3'?'active':''" v-on:click="changeTab(3)">
                <a data-toggle="tab" href="#faq-tab-2" aria-expanded="false">
                  上传评估数据
                </a>
              </li>
            </ul>
            <equip-file-commen v-if="tabIndex=='1'" @choose-after="chooseAfter" :tpids.sync="item.tpids" :uav-fly-video-id="item.id" :cjsj="item.cjsj"></equip-file-commen>
            <event-commen v-if="tabIndex=='2'" @choose-after="chooseAfter" :jlid.sync="item.jlid" :uav-fly-video-id="item.id" :cjsj="item.cjsj"></event-commen>
            <body-assess v-if="tabIndex=='3'" @choose-after="chooseAfter" :id.sync="item.sm" :uav-fly-video-id="item.id"></body-assess>
          </div>
        </div>
      </div>
    </div>

    <div id="detail-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" style="width: 1320px;height: auto" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">详情</h4>
          </div>
          <div class="modal-body">
            <div style="display:inline-flex;justify-content: space-around;">
              <div v-if="item.jlid" style="width: 350px;height: 250px;">
                <div style="width: 350px;height: 230px;" id="echartEvent"></div>
                <div style="text-align: center;">{{ldTime}}</div>
              </div>
              <div v-for="(tplj,index) in tpljList" :key="index">
                <img alt="无图片" style="width: 150px;height: 250px;margin: 0 10px;" :src="tplj.substring(0,tplj.lastIndexOf('.')+1)+'jpg'">
              </div>
            </div>
            <div style="margin-top: 20px;display: flex;">
              <video style="width: 400px;height: 400px;" :src="item.videoUrl" controls></video>
              <img v-if="item.sm" alt="无图片" style="width: 200px;height: 400px;margin: 0 80px;" :src="bodyAssessInfo.imgUrl">
              <div style="width: 500px;text-align: center;">
                <form class="form-horizontal">
                  <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                      <div class="profile-info-name" style="width: 20%;">体积</div>
                      <div class="profile-info-value" style="width: 40%;">
                        <span class="editable">{{bodyAssessInfo.volume}}</span>
                      </div>
                    </div>
                    <div class="profile-info-row">
                      <div class="profile-info-name" style="width: 20%;">BAI</div>
                      <div class="profile-info-value" style="width: 40%;">
                        <span class="editable">{{bodyAssessInfo.bai}}</span>
                      </div>
                    </div>
                    <div class="profile-info-row">
                      <div class="profile-info-name" style="width: 20%;">体长</div>
                      <div class="profile-info-value" style="width: 40%;">
                        <span class="editable">{{bodyAssessInfo.bodyLength}}</span>
                      </div>
                    </div>
                    <div class="profile-info-row">
                      <div class="profile-info-name" style="width: 20%;">估算年龄段</div>
                      <div class="profile-info-value" style="width: 40%;">
                        <span class="editable">{{bodyAssessInfo.ageGroup}}</span>
                      </div>
                    </div>
                    <div class="profile-info-row">
                      <div class="profile-info-name" style="width: 20%;">总体重(kg)</div>
                      <div class="profile-info-value" style="width: 40%;">
                        <span class="editable">{{bodyAssessInfo.totalWeight}}</span>
                      </div>
                    </div>
                    <div class="profile-info-row">
                      <div class="profile-info-name" style="width: 20%;">总体重BMI值</div>
                      <div class="profile-info-value" style="width: 40%;">
                        <span class="editable">{{bodyAssessInfo.totalBmi}}</span>
                      </div>
                    </div>
                    <div class="profile-info-row">
                      <div class="profile-info-name" style="width: 20%;">胖瘦判定</div>
                      <div class="profile-info-value" style="width: 40%;">
                        <span class="editable">{{bodyAssessInfo.fatThin}}</span>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>
<script>
import Pagination from "../../components/pagination";
import Times from "../../components/times";
import EquipFileCommen from "../../components/equipFileCommen";
import EventCommen from "../../components/eventCommen";
import BodyAssess from "../../components/bodyAssess";
export default {
  name:'uav-fly-video',
  components:{Pagination,Times,EquipFileCommen,EventCommen,BodyAssess},
  data: function (){
    return {
      uavFlyVideo:{},
      uavFlyVideoDto:{},
      uavFlyVideoList:[],
      item:{},
      tabIndex:'',
      ldTime:'',//雷达图
      myChart:null,//雷达图
      echartsData:[],//雷达图
      title:[],//雷达图
      tpljList:[],
      bodyAssessInfo:{}
    }
  },
  mounted() {
    let _this = this;
    _this.list(1);
    _this.$refs.pagination.size = 10;
    //监听模态框关闭
    $('#correlation-modal').on('hidden.bs.modal', function () {
      _this.tabIndex = '';
      _this.list(1);
    });
    //
    $('#detail-modal').on('hidden.bs.modal', function () {
      $("#echartEvent").empty();
    });
  },
  methods: {
    //详情
    showDetail(item){
      let _this = this;
      _this.bodyAssessInfo={};
      if(item.sm){
        _this.getBodyAssess(item.sm);
      }
      if(_this.myChart){
        _this.myChart.dispose();
      }
      _this.tpljList = [];
      _this.echartsData = [];
      _this.ldTime='';
      _this.item = item;
      _this.tpljList = item.tpids.split(',');
      $("#detail-modal").modal("show");
      _this.$nextTick(function (){
        _this.initEcharts();
        if(item.jlid){
          let arr = item.jlid.split("/");//2022_10_15_02_15_14-0:105,1:235
          for(let i=0;i<arr.length;i++){
            let rqandjd = arr[i].split("-");//2022_10_15_02_15_14和0:105,1:235
            let item = [];
            let rqarr = rqandjd[0].split("_");//2022和10和15和02和15和14
            if(rqandjd.length==2){
              if(rqandjd[1].split(",").length>0){
                let jdarr =  rqandjd[1].split(",");//0:105和1:235
                for(let j=0;j<jdarr.length;j++){
                  if(jdarr[j].split(":").length==2){
                    item.push([1.5,jdarr[j].split(":")[1]]);
                  }
                }
                _this.echartsData.push(item);
                _this.title.push(rqarr[0]+"-"+rqarr[1]+"-"+rqarr[2]+" "+rqarr[3]+":"+rqarr[4]+":"+rqarr[5]);
              }
            }
          }
          _this.loopEchartsData(_this.echartsData,_this.title);
        }
      })
    },
    getBodyAssess(id){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/admin/bodyAssess/getById/'+id).then((response) => {
        let resp = response.data;
        _this.bodyAssessInfo = resp.content;
      })
    },
    changeTab(index){
      let _this = this;
      _this.tabIndex = index;
    },
    //关联
    correlation(item){
      let _this = this;
      _this.item = item;
      if(Tool.isEmpty(_this.item.tpids)){
        _this.tabIndex = "1";
      }else if(Tool.isEmpty(_this.item.jlid)){
        _this.tabIndex = "2";
      }else if(Tool.isEmpty(_this.item.sm)){
        _this.tabIndex = "3";
      }
      $("#correlation-modal").modal("show");
    },
    //关联选择结束以后
    chooseAfter(param){
      let _this = this;
      if(param.index=='1'){
        _this.item.tpids = param.ids;
        if(Tool.isEmpty(_this.item.jlid)){
          _this.tabIndex = "2";
        }else if(Tool.isEmpty(_this.item.sm)){
          _this.tabIndex = "3";
        }else{
          $("#correlation-modal").modal("hide");
        }
      }else if(param.index=='2'){
        _this.item.jlid = param.ids;
        if(Tool.isEmpty(_this.item.tpids)){
          _this.tabIndex = "1";
        }else if(Tool.isEmpty(_this.item.sm)){
          _this.tabIndex = "3";
        }else{
          $("#correlation-modal").modal("hide");
        }
      }else if(param.index=='3'){
        _this.item.sm = param.ids;
        if(Tool.isEmpty(_this.item.tpids)){
          _this.tabIndex = "1";
        }else if(Tool.isEmpty(_this.item.jlid)){
          _this.tabIndex = "2";
        }else{
          $("#correlation-modal").modal("hide");
        }
      }
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.uavFlyVideoDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.uavFlyVideoDto.etime = rep;
      _this.$forceUpdate();
    },
    list(page){
      let _this = this;
      Loading.show();
      _this.uavFlyVideoDto.page=page;
      _this.uavFlyVideoDto.size=_this.$refs.pagination.size;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/uavFlyVideo/list', _this.uavFlyVideoDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.uavFlyVideoList = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    },
    initEcharts(){
      let _this = this;
      let option = {
        polar: {
          center: ['50%', '50%']
        },
        angleAxis: {
          type: 'value',
          min: 0,
          max:360
        },
        radiusAxis: {
          min: 0,
          max: 3
        },
        series: [{
          coordinateSystem: 'polar',
          type: 'scatter',
          showSymbol: false
        }],
        animation:false
      };
      _this.myChart = echarts.init(document.getElementById("echartEvent"));
      _this.myChart.setOption(option);
    },
    loopEchartsData(list,title){
      let _this = this;
      _this.myChart.setOption({
        series: [{
          data:list[0],
          itemStyle:{
            color:(e)=>{
              return _this.domColor(e.dataIndex)
            }
          }
        }]
      });
      _this.ldTime=title[0];
      if(list.length>1){
        let k=1;
        _this.intervalId = setInterval(function () {
          if(k==list.length-1){
            k=list.length-1;
            clearInterval(_this.intervalId);
            _this.showBtn = true;
          }
          _this.ldTime=title[k];
          _this.myChart.setOption({
            series: [
              {
                data:list[k],
                itemStyle:{
                  color:(e)=>{
                    return _this.domColor(e.dataIndex)
                  }
                }
              }
            ],
            // title: {
            //   text: title[k],
            //   left:"19%"
            // }
          });
          k=k+1;
        }, 4000);
      }else{
        _this.showBtn = true;
      }
    },
    domColor(dataIndex){
      let newAry=[];
      for(let k=0;k<20;k++){
        let r = Math.ceil(Math.random()*256);
        let g = Math.ceil(Math.random()*256);
        let b = Math.ceil(Math.random()*256);
        newAry.push(`rgb(${r},${g},${b})`);
      }
      return newAry[dataIndex];
    }
  }
}
</script>
<style scoped>
.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover{
  background-color: #fff;
  color: #576373;
  border-top: 2px solid #4C8FBD;
}
.nav-tabs>li>a, .nav-tabs>li>a:focus{
  background-color: #fff;
}
.video-box{
  display: flex;
  flex-wrap: wrap;
  text-align: center;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 20px;
}
.video-item{
  width:19.5%;
  height: 295px;
  padding: 5px;
  margin-bottom: 10px;
}
.profile-info-row {
  height: 40px;
  line-height: 40px;
}
</style>