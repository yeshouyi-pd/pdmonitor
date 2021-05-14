<template>
  <div style="display: flex">
    <div id="tree" class="ztree" :style="{height: heightMax + 'px', overflowY: 'auto', width:'20%', border:'1px solid #ccc'}"></div>
    <div :style="{height: heightMax + 'px', width:'78%', border: '1px solid #ccc', marginLeft: '1%'}">
      <div style="width: 100%;height: 6%;display: flex">
        <label style="font-size: 22px;margin-left: 20px;margin-top: 3px;">时间:</label>
        <div class="col-sm-4" style="display: flex;width: 60%;">
          <div class="radio">
            <label>
              <input name="form-sbzt-radio" type="radio"  value="1" v-model="chooseTimeType" class="ace input-lg" />
              <span class="lbl bigger-120">最近30天</span>
            </label>
            <label>
              <input name="form-sbzt-radio" type="radio"  value="2"  v-model="chooseTimeType" class="ace input-lg" />
              <span class="lbl bigger-120">最近15天</span>
            </label>
            <label>
              <input name="form-sbzt-radio" type="radio"  value="3"  v-model="chooseTimeType" class="ace input-lg" />
              <span class="lbl bigger-120">最近7天</span>
            </label>
          </div>
          <button v-on:click="selectData()" class="btn btn-white btn-default btn-round" style="height: 34px;margin-top: 5px;margin-left: 20px;">
            <i class="ace-icon fa fa-book"></i>
            查询
          </button>
        </div>
      </div>
      <div id="echarbox" style="width: 100%;height: 94%;overflow-y: auto"></div>
    </div>
  </div>
</template>
<script>
export default {
  name:"monitor-equipment-his",
  data:function(){
    return {
      trees:[],
      heightMax:'',
      yAxisName:'',
      chooseJcx:'',
      jcxdw:'',
      chooseTimeType:'1',
      curNode:null
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.heightMax = h*0.8-20;
    _this.findMonitorEqupmentTree();
  },
  methods:{
    findMonitorEqupmentTree(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findMonitorEqupmentTree').then((res) => {
        let response = res.data;
        _this.trees = response.content;
        // 初始化树
        _this.initTree();
      })
    },
    initTree(){
      let _this = this;
      let setting = {
        data: {
          simpleData: {
            idKey: 'code'
          },
          key: {
            name: "name"
          }
        },
        view: {
          showIcon: false
        },
        callback: {
          onClick: _this.zTreeOnClick
        },
      };
      _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.trees);
      _this.zTree.expandAll(true);
      //获取第一个节点
      let node = _this.zTree.getNodes()[0].children[0];
      //选中第一个子节点
      _this.zTree.selectNode(node);
      setting.callback.onClick(null, _this.zTree.setting.treeId, node);
    },
    zTreeOnClick(event, treeId, treeNode){
      let _this = this;
      _this.curNode = treeNode;
      if(treeNode.type=='2'){
        _this.findWaterQualityResultByMonth(treeNode);
      }
    },
    findYAxisName(code){
      let _this = this;
      if(code=='waterDepth'){
        _this.yAxisName='水深(单位：M)';
        _this.chooseJcx='水深';
        _this.jcxdw='M';
      }else if(code=='ph'){
        _this.yAxisName='PH值(单位：pH)';
        _this.chooseJcx='PH值';
        _this.jcxdw='pH';
      }else if(code=='waterVelocity'){
        _this.yAxisName='水流(单位：M/S)';
        _this.chooseJcx='水流';
        _this.jcxdw='M/S';
      }else if(code=='temperature'){
        _this.yAxisName='温度(单位：°C)';
        _this.chooseJcx='温度';
        _this.jcxdw='°C';
      }
      _this.$forceUpdate();
    },
    findWaterQualityResultByMonth(treeNode){
      let _this = this;
      let waterQualityResultDto = {
        'deviceId':treeNode.code,
        'chooseTimeType':_this.chooseTimeType
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterQualityAvg/findWaterQualityResultByMonth', waterQualityResultDto).then((response)=> {
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          let data = resp.content==null?[]:resp.content;
          $("#echarbox").empty();
          for(let key in data){
            _this.findYAxisName(treeNode.code);
            let echartData = data[key]==null?[]:data[key];
            $("#echarbox").append("<div id='echarts-data-"+key+"' style='height: 350px;margin-bottom: 30px;'></div>");
            _this.initEcharts(echartData, 'echarts-data-'+key);
          }
        } else {
          Toast.warning(resp.message)
        }
      })
    },
    initEcharts(data,id){
      let _this = this;
      let option = {
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          show: true,
          name: '采集时间',
          type: 'category',
          nameGap: 30,
          boundaryGap: true,
          axisLine:{
            show: true,
            symbol: ['none', 'path://M5,20 L5,5 L8,8 L5,2 L2,8 L5,5 L5.3,6 L5.3,20 '],
            symbolOffset: 10,
            symbolSize:[35, 35]
          },
          axisLabel: {
            show: true,
            width: 100,
            overflow: 'break'
          }
        },
        yAxis: {
          show: true,
          name: _this.yAxisName,
          type: 'value',
          nameGap: 30,
          boundaryGap: [0, '30%'],
          axisLine:{
            show: true,
            symbol: ['none', 'path://M5,20 L5,5 L8,8 L5,2 L2,8 L5,5 L5.3,6 L5.3,20 '],
            symbolOffset: 10,
            symbolSize:[35, 35]
          }
        },
        series: [
          {
            type: 'line',
            smooth: 0.6,
            symbolSize: 8,
            lineStyle: {
              color: '#5470C6',
              width: 5
            },
            // label: {
            //   show: true,
            //   position: 'top'
            // },
            data: data
          }
        ]
      };
      let echartsData = echarts.init(document.getElementById(id));
      echartsData.setOption(option);
    },
    selectData(){
      let _this = this;
      if(_this.curNode.type=='2'){
        _this.findYAxisName(_this.curNode.code);
        _this.findWaterQualityResultByMonth(_this.curNode);
      }
    }
  }
}
</script>