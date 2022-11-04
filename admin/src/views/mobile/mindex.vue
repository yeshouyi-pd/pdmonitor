<template>
    <div class="main-content" style="overflow: auto">
        <div class="main-content-inner">
            <div class="space-1"></div>
            <div>
						<span class="label label-primary arrowed-in-right label-lg">
									<b>设备信息提醒</b>
						</span>
            </div>
            <div class="space-6"></div>
            <div class="row">
                <div class="col-xs-4 col-sm-4 center">
                    <div class="center">
						<span class="btn btn-app  btn-light no-hover" style="margin: 1px;width: 93%">
                    <span class="line-height-1 bigger-200 blue"> {{ onLineCount+offLineCount+ errorCount}} </span>
                      <br/>
                    <span class="line-height-1 smaller-90">设备总数 </span>
            </span>
                    </div>
                </div>

                <div class="col-xs-4 col-sm-4 center">
                    <div class="center">
            <span class="btn btn-app  btn-success  no-hover" style="margin: 1px;width: 93%">
                   <span class="line-height-1 bigger-200"> {{ onLineCount }} </span>
                    <br/>
                   <span class="line-height-1 smaller-90"> 正常设备 </span>
            </span>
                    </div>
                </div>

                <div class="col-xs-4 col-sm-4 center">
                    <div class="center">
            <span class="btn btn-app  btn-yellow  no-hover" style="margin: 1px ;width: 93%">
                     <span class="line-height-1 bigger-200"> {{ offLineCount+ errorCount}} </span>
                     <br/>
                     <span class="line-height-1 smaller-90"> 异常设备 </span>
            </span>
                    </div>
                </div>
            </div>


            <div class="space-6"></div>
            <div>
        <span class="label label-primary arrowed-in-right label-lg">
          <b>数据信息提醒</b>
        </span>
            </div>


            <div class="space-6"></div>
          <div class="row">
            <div class="col-xs-4 col-sm-4 center">
              <div class="center">
						<span class="btn btn-app  btn-primary no-hover" style="margin: 1px;width: 93%">
                <span class="line-height-0 white">本周</span>
             <span  v-on:click="toshjlist(1)" class="line-height-1 bigger-110"> {{shjbzcount}} </span>
                      <br/>
              <span class="line-height-1 smaller-60">聚类事件次数 </span>
            </span>
              </div>
            </div>

            <div class="col-xs-4 col-sm-4 center">
              <div class="center">
						<span class="btn btn-app  btn-purple no-hover" style="margin: 1px;width: 93%">
                 <span class="line-height-0 white">今日</span>
              <span  v-on:click="toshjlist(2)" class="line-height-1 bigger-110"> {{shjcount}} </span>
                      <br/>
              <span class="line-height-1 smaller-60">聚类事件次数 </span>
            </span>
              </div>
            </div>

            <div class="col-xs-4 col-sm-4 center">
              <div class="center">
            <span class="btn btn-app  btn-pink  no-hover" style="margin: 1px ;width: 93%">
                     <span  v-on:click="tojtlist()" class="line-height-1 bigger-110"> {{jtcount}} </span>
                     <br/>
                     <span class="line-height-1 smaller-60"> 声学侦测次数 </span>
            </span>
              </div>
            </div>
          </div>

            <div class="space-6"></div>
            <div>
        <span class="label label-primary arrowed-in-right label-lg">
          <b>监测设备分布</b>
        </span>
            </div>
            <div class="space-6"></div>

            <div>
                <div id="map-top" style="width: 115px;height: 80px;border: 1px solid #EEEEEE;position: absolute;left: 12px;z-index: 100;background-color: #fff;">
                    <div style="padding:5px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#03C449;padding-right: 10px"></i>设备正常 {{onLineCount}}个</div>
                    <div style="padding:0px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#555555;padding-right: 10px"></i>设备离线 {{offLineCount}}个</div>
                    <div style="padding:0px 0px 5px 5px"><i class="fa fa-map-marker" style="color:#B03A5B;padding-right: 10px"></i>设备故障 {{errorCount}}个</div>
                </div>
                <div id="map-wrap"  :style="{height: heightMax + 'px'}">
                    <!-- 这里以后是地图 -->
                </div>
            </div>


        </div>
    </div>
</template>

<script>
    export default {
        name: "mindex",
        data: function () {
            return {
                heightMax:'',
                waterDatas:[],
                deptMap:[],
                onLineCount:0,
                offLineCount:0,
                errorCount:0,
                KvMap:[],
                BzKvMap:[],
                shjcount:0,//当后天聚类事件
                shjbzcount:0,//本周聚类事件
                KvMapjt:[],
                jtcount:0,

            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this; //this 变成本地变量  避坑
            _this.loginUser = Tool.getLoginUser();
            if (!_this.hasResourceRouter(_this.$route.name)) {
                _this.$router.push("/login");
            }
            _this.getEquipmentEventByDept();//今天聚类事件
            _this.getBzEquipmentEventByDept();//本周聚类事件
            _this.getAlljtByDept();//江豚预警


            let userInfo = Tool.getLoginUser();
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/findAll/' + userInfo.deptcode).then((response)=>{
                let resp = response.data;
                if (resp.success) {
                    _this.waterDatas = resp.content;
                    _this.$forceUpdate();
                }
            })
            if(Tool.isEmpty(_this.heightMax)){
                let h = document.documentElement.clientHeight || document.body.clientHeight;
                _this.heightMax = h*0.6-70;
            }

            _this.deptMap = Tool.getDeptUser();
            _this.findDeviceInfo();

        }, methods: {
            toshjlist (tyep){
                let _this = this;
                if(tyep == 1){
                  if(_this.shjbzcount < 1){
                    Toast.warning("暂无相关数据")
                  }else{
                    SessionStorage.set(MSHJMAP,_this.BzKvMap);
                    SessionStorage.set(TODATORWEEK,1);//本周
                    _this.$router.push("/mobile/shjlist");
                  }
                }else if(tyep == 2){
                  if(_this.shjcount < 1){
                    Toast.warning("暂无相关数据")
                  }else{
                    SessionStorage.set(TODATORWEEK,2);//当天
                    SessionStorage.set(MSHJMAP,_this.KvMap);
                    _this.$router.push("/mobile/shjlist");
                  }
                }

            },

            tojtlist (){
                let _this = this;
                if(_this.jtcount < 1){
                    Toast.warning("暂无相关数据")
                }else{
                    _this.$router.push("/mobile/jtlist");
                }

            },

        
            /**
             *  welcome 聚类事件今天
             */
            getEquipmentEventByDept() {
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/mobile/getEquipmentEventByDept', {deptcode:_this.loginUser.deptcode}).then((res)=>{
                    let response = res.data;
                    _this.KvMap = response.content;
                    if(!Tool.isEmpty(_this.KvMap)){
                        let count =0;
                        for(let key of _this.KvMap){
                            count = count +key.value;
                        }
                        _this.shjcount = count;
                    }
                })
            },


        /**
         *  welcome 聚类事件本周
         */
        getBzEquipmentEventByDept() {
          let _this = this;
          _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/mobile/getBzEquipmentEventByDept', {deptcode:_this.loginUser.deptcode}).then((res)=>{
            let response = res.data;
            _this.BzKvMap = response.content;
            if(!Tool.isEmpty(_this.BzKvMap)){
              let count =0;
              for(let key of _this.BzKvMap){
                count = count +key.value;
              }
              _this.shjbzcount = count;
            }
          })
        },

            /**
             *江豚预警
             */
            getAlljtByDept() {
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/mobile/getAlljtByDept', {deptcode:_this.loginUser.deptcode}).then((res)=>{
                    let response = res.data;
                    _this.KvMapjt = response.content;
                    if(!Tool.isEmpty(_this.KvMapjt)){
                        let count =0;
                        for(let key of _this.KvMapjt){
                            count = count +key.value;
                        }
                        _this.jtcount = count;
                        SessionStorage.set(MJTMAP,_this.KvMapjt);

                    }
                })
            },

            /**
             * 查找是否有权限
             * @param router
             */
            hasResourceRouter(router) {
                let _this = this;
                let resources = Tool.getLoginUser().resources;
                if (Tool.isEmpty(resources)) {
                    return false;
                }
                for (let i = 0; i < resources.length; i++) {
                    if (Tool.isEmpty(router)) {
                        return true;
                    } else {
                        if (router === resources[i].page) {
                            return true;
                        }
                    }

                }
                return false;
            },    findDeviceInfo(){
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', {}).then((response)=>{
                    Loading.hide();
                    let devices = response.data.content;
                    let myData = [];
                    for(let i=0;i<devices.length;i++){
                        let value = devices[i].gps.split(",");
                        if(devices[i].sbzt=='1'){
                            _this.onLineCount++;
                            value.push(0);
                        }else if(devices[i].sbzt=='2'){
                            _this.offLineCount++;
                            value.push(100);
                        }else if(devices[i].sbzt=='3'){
                            _this.errorCount++;
                            value.push(200);
                        }
                        let obj = {
                            'name':devices[i].sbmc,
                            'value':value,
                            'deptcode':_this.optionMapKV(_this.deptMap,devices[i].deptcode),
                            'centerCode':_this.optionWDArray(_this.waterDatas,devices[i].centerCode)
                        }
                        myData.push(obj);
                    }
                    _this.initMap(myData);
                })
            },
            initMap(myData){
                let bmapChart = echarts.init(document.getElementById('map-wrap'));
                // let myData = [
                //   {name: '海门', value: [121.15, 31.89, 200], addr:'haimeijutidizhi'},
                //   {name: '招远', value: [120.38, 37.35, 100], addr:''},
                //   {name: '舟山', value: [122.207216, 29.985295, 0], addr:'zousanjutidizhi'},
                // ]
                let option = {
                    amap: {
                        center: [114.299945,30.593221],
                        zoom: 8,
                        roam: true, // 允许缩放
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: function(param){
                            return '设备名称：'+param.data.name+'</br>设备所属监测点：'+param.data.deptcode+'</br>设备所属数据中心：'+param.data.centerCode;
                        }
                    },
                    visualMap: {	// 视觉映射组件
                        type: 'continuous',
                        min: 0,
                        max: 200,
                        calculable: true,
                        inRange: {
                            color: ['#03C449','#555555','#B03A5B'],
                            symbol:['pin', 'pin', 'pin'],
                            symbolSize: [30,30,30]
                        }
                    },
                    series: [
                        {
                            type: 'scatter',
                            coordinateSystem: 'amap', // 坐标系使用amap高德地图
                            data: myData,
                            itemStyle: {
                                normal: {
                                    color: "#fff",
                                    shadowBlur: 10,
                                    shadowColor: "#333"
                                }
                            }
                        }]
                }
                bmapChart.setOption(option);
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

<style scoped>

</style>