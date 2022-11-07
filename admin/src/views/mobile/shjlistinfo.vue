<template>
  <div class="main-content" style="overflow: auto">
    <div class="main-content-inner">
    <div class="space-1"></div>
    <div>
						<span class="label label-primary arrowed-in-right label-lg">
									<b>【{{sbbh}}】</b>
						</span>
    </div>
      <div class="space-2"></div>

      <div class="row">
        <div class="col-xs-12">
          <!-- PAGE CONTENT BEGINS -->
          <div class="row">
            <div class="col-xs-12">
              <table id="simple-table" class="table  table-bordered table-hover">
                <thead>
                <tr>
                  <th class="center">
                    序号
                  </th>
                  <th>设备SN</th>
                  <th>开始时间</th>
                  <th>结束时间</th>
                  <th class="detail-col">雷达图</th>
                </tr>
                </thead>

                <tbody>
                <template v-for="(entity,index)  in equipmentFileEvents"  >
                <tr v-on:click="showEcharts(entity)">
                  <td class="center">{{ index +1 }}</td>
                  <td>{{entity.sbbh}}</td>
                  <td>{{entity.kssj}} </td>
                  <td>{{entity.jssj}} </td>
                  <td class="center">
                    <div class="action-buttons">
                      <a href="#"  v-on:click="showEcharts(entity)" class="green bigger-140 show-details-btn" :id="index+'details'"  title="Show Details">
                        <i class="ace-icon fa fa-angle-double-down"></i>
                        <span class="sr-only">详情</span>
                      </a>
                    </div>
                  </td>
                </tr>
                </template>

                </tbody>
              </table>

            </div><!-- /.span -->
          </div><!-- /.row -->
        </div>
      </div>


    </div>


    <div id="echart-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">雷达图</h4>
          </div>
          <div class="modal-body" style="width: 350px;height: 500px;margin: auto;text-align: center" >
            <div style="width: 350px;height: 400px;" id="echartEvent"></div>
            <p>{{equipmentFileEvent.kssj}}至{{equipmentFileEvent.jssj}}</p>
            <p>保守估计发声头数<span style="color: red">{{equipmentFileEvent.ts}}头</span></p>
          </div>
          <div class="modal-footer">
            <button v-if="showBtn" type="button" class="btn btn-white btn-default btn-round" v-on:click="reloadLdt">
              <i class="ace-icon fa fa-refresh"></i>
              重播
            </button>
            <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
              <i class="ace-icon fa fa-times"></i>
              关闭
            </button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>

</template>

<script>
export default {
  name: "shjlistinfo",
  data: function () {
    return {
      sbbh:'',
      equipmentFileEvents:[],
      lists:[], //数据
      sets:[], //业务
      JYXM_DW:JYXM_DW,
      szjcx:[],
      myChart:null,
      intervalId:'',
      equipmentFileEvent:{},
      showBtn:false,
      echartsData:[],
      title:[],
      queryTyep:2,//1 查询本周 2 查询当天
    }
  },
  mounted: function () {
    let _this =this;
    _this.sbbh = SessionStorage.get(MSHJSM);
    _this.queryTyep=  SessionStorage.get(TODATORWEEK )|| 2 ;
    if(Tool.isEmpty(_this.sbbh)){
      _this.$router.push("/mobile/mindex");
    }

    _this.list();
    //监听模态框关闭
    $('#echart-modal').on('hidden.bs.modal', function () {
      //先消除再创建
      if(_this.myChart!=null){
        _this.myChart.dispose();
      }
      if (_this.intervalId != null) {
        clearInterval(_this.intervalId); //清除计时器
        _this.intervalId = null; //设置为null
      }
      _this.showBtn = false;
    });


  },
  methods: {
    //重新播放雷达图
    reloadLdt(){
      let _this = this;
      _this.showBtn = false;
      clearInterval(_this.intervalId);
      _this.intervalId = null; //设置为null
      _this.myChart.dispose();
      _this.initEcharts();
      _this.loopEchartsData(_this.echartsData,_this.title);
    },
    showEcharts(item){
      let _this = this;
      _this.equipmentFileEvent = $.extend({}, item);
      _this.$forceUpdate();
      _this.initEcharts();
      _this.echartsData = [];
      _this.title = [];
      let arr = item.jtnr.split("/");//2022_10_15_02_15_14-0:105,1:235
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
      $("#echart-modal").modal("show");
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
        }],
        title: {text: title[0],left:"19%"}
      });
      if(list.length>1){
        let k=1;
        _this.intervalId = setInterval(function () {
          if(k==list.length-1){
            k=list.length-1;
            clearInterval(_this.intervalId);
            _this.showBtn = true;
          }
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
            title: {
              text: title[k],
              left:"19%"
            }
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

    /**
     * 列表查询
     */
    list() {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/mobile/list', {sbbh:_this.sbbh,sm:_this.queryTyep}).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFileEvents = resp.content;
      })
    },

  }
}
</script>

<style scoped>

</style>