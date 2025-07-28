<template>
    <div >
        <div class="main-content-inner">
            <div class="space-1"></div>
            <div>
        <span class="label label-primary arrowed-in-right label-lg">
          <b>数据信息提醒</b>
        </span>
            </div>
            <div class="space-6"></div>
          <div class="row">
            <div class="col-xs-4 col-sm-4 center" v-on:click="toshjlist(1)" >
              <div class="center">
						<span class="btn btn-app  btn-primary no-hover" style="margin: 1px;width: 93%">

             <span  v-on:click="toshjlist(1)" class="line-height-1 bigger-180"> {{shjbzcount}} </span>
                     <br/>
                   <span class="line-height-0 white">本周</span>
                      <br/>
              <span class="line-height-1 smaller-60">聚类事件次数 </span>
            </span>
              </div>
            </div>

            <div class="col-xs-4 col-sm-4 center" v-on:click="toshjlist(2)">
              <div class="center">
						<span class="btn btn-app  btn-purple no-hover" style="margin: 1px;width: 93%">

              <span  v-on:click="toshjlist(2)" class="line-height-1 bigger-180"> {{shjcount}} </span>
                      <br/>
               <span class="line-height-0 white">今日</span>
                 <br/>
              <span class="line-height-1 smaller-60">聚类事件次数 </span>
            </span>
              </div>
            </div>

            <div class="col-xs-4 col-sm-4 center" v-on:click="tojtlist()">
              <div class="center">
            <span class="btn btn-app  btn-success no-hover" style="margin: 1px ;width: 93%">
                     <span  v-on:click="tojtlist()" class="line-height-1 bigger-180"> {{jtcount}} </span>
                     <br/>

                  <span class="line-height-0 white">今日</span>
                <br/>
                     <span class="line-height-1 smaller-60"> 声学侦测次数 </span>
            </span>
              </div>
            </div>
          </div>

            <div class="space-12"></div>
            <div>
        <span class="label label-primary arrowed-in-right label-lg">
          <b>监测设备分布</b>
        </span>
            </div>
            <div class="bottoms">
              <EquipmentAMap v-bind:height-max="heightMax" v-bind:map-style="'amap://styles/fresh'"></EquipmentAMap>
            </div>
        </div>
    </div>
</template>

<script>
import EquipmentAMap from "../monitor/equipmentAMap";
    export default {
        name: "mindex",
      components:{EquipmentAMap},
        data: function () {
            return {
                heightMax:'',
                waterDatas:[],
                deptMap:[],
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
            if(Tool.isEmpty(_this.heightMax)){
                let h = document.documentElement.clientHeight || document.body.clientHeight;
                _this.heightMax = h*0.8-70;
            }

            _this.deptMap = Tool.getDeptUser();

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
.bottoms{
position: fixed;
bottom: 20px;
left:0;
width: 100%;
}
</style>