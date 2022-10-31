<template>
    <div>
        <table class="table table-border table-bordered table-bg table-sort">
            <thead>
                <tr class="text-c">
                    <th width="100%" style="color: #669FC7;font-size: 16px;padding-left: 12px;">设备运行状态监控</th>
                </tr>
            </thead>
            <tbody>
                <tr class="text-c">
                    <td>
                        <div align="center" v-for="state in zcStates">
                            <div style="text-align: center;">
                                <div style="float: left;width:12.5%;height:190px;" align="center">
                                    <div style="width:93%;height:180px;position:relative;top:10px;border:3px solid #009900;border-radius:5px;">
                                        <div style="width:96%;height:170px;position:relative;top:2px;border:2px solid #009900;border-radius:2px;">
                                            <div style="color: #009900;position:relative;top:10px;">
                                                <p style="font-size: 18px;font-weight: bolder;">{{state.sbbh}}</p>
                                                <p style="font-size: 18px;font-weight: bold;">已上线</p>
                                                <p style="font-size: 10px;">{{state.sm2}}</p>
                                                <button v-on:click="startEquip(state.sm1)" class="btn btn-xs btn-primary " style="background-color: #3E753B!important;border-color: #468641;">
                                                  开机
                                                </button>
                                                <button v-on:click="restart(state.sm1)" class="btn btn-xs btn-danger " style="background-color: #3E753B!important;border-color: #468641;margin-left: 10px">
                                                  重启
                                                </button>
                                                <button v-on:click="closedEquip(state.sm1)" class="btn btn-xs btn-inverse " style="background-color: #3E753B!important;border-color: #468641;margin-left: 10px">
                                                  关机
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
              <tr>
                <td>
                  <div align="center" v-for="state in ycStates">
                    <div style="text-align: center;">
                      <div style="float: left;width:12.5%;height:190px;" align="center">
                        <div style="width:93%;height:180px;position:relative;top:10px;border:3px solid #FF0000;border-radius:5px;">
                          <div style="width:96%;height:170px;position:relative;top:2px;border:2px solid #FF0000;border-radius:2px;">
                            <div style="color: #FF0000;position:relative;top:10px;">
                              <p style="font-size: 18px;font-weight: bolder;">{{state.sbbh}}</p>
                              <p style="font-size: 18px;font-weight: bold;">未上线</p>
                              <p style="font-size: 10px;">{{state.sm2}}</p>
                              <button v-on:click="startEquip(state.sm1)" class="btn btn-xs btn-primary " style="background-color: #B74635!important;border-color: #D15B47;">
                                开机
                              </button>
                              <button v-on:click="restart(state.sm1)" class="btn btn-xs btn-danger " style="background-color: #B74635!important;border-color: #D15B47;margin-left: 10px">
                                重启
                              </button>
                              <button v-on:click="closedEquip(state.sm1)" class="btn btn-xs btn-inverse " style="background-color: #B74635!important;border-color: #D15B47;margin-left: 10px">
                                关机
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </td>
              </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name: "waterState",
        data: function() {
            return {
              zcStates:[],
              ycStates:[],
              waterEquipmentDto:{},
              restartinterval:10000
            }
        },
        mounted: function() {
          let _this = this;
          _this.findByAttrKey();
          _this.getWaterState();
          _this.dataRefreh();
        },
        methods: {
          findByAttrKey(){
            let _this = this;
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/attr/findByAttrKey/restartinterval').then((response)=>{
              let resp = response.data;
              if (resp.success) {
                _this.restartinterval = resp.content;
              }
            })
          },
          // 定时刷新数据函数
          dataRefreh() {
            let _this = this;
            // 计时器正在进行中，退出函数
            if (_this.intervalId != null) {
              return;
            }
            // 计时器为空，操作 30秒
            _this.intervalId = setInterval(() => {
              _this.getWaterState();
            }, 30000)
          },
          startEquip(sbcj){
            let _this = this;
            Loading.show();
            if(!Tool.isEmpty(sbcj)){
              _this.$ajax.post(process.env.VUE_APP_SERVER + '/power/admin/ldTaskListSec/startEquip/'+sbcj).then((res) => {
                Loading.hide();
                let response = res.data;
                if(response.success){
                  Toast.success("执行完毕");
                }else{
                  Toast.success("执行出错，请联系管理员");
                }
              })
            }else{
              Toast.success("没有SIM卡卡号，不执行！");
              Loading.hide();
            }
          },
          restart(sbcj){
            let _this = this;
            Loading.show();
            if(!Tool.isEmpty(sbcj)){
              _this.$ajax.post(process.env.VUE_APP_SERVER + '/power/admin/ldTaskListSec/restart/'+sbcj+"/"+_this.restartinterval).then((res) => {
                Loading.hide();
                let response = res.data;
                if(response.success){
                  Toast.success("执行完毕");
                }else{
                  Toast.success("执行出错，请联系管理员");
                }
              })
            }else{
              Toast.success("没有SIM卡卡号，不执行！");
              Loading.hide();
            }
          },
          closedEquip(sbcj){
            let _this = this;
            Loading.show();
            if(!Tool.isEmpty(sbcj)){
              _this.$ajax.post(process.env.VUE_APP_SERVER + '/power/admin/ldTaskListSec/closedEquip/'+sbcj).then((res) => {
                Loading.hide();
                let response = res.data;
                if(response.success){
                  Toast.success("执行完毕");
                }else{
                  Toast.success("执行出错，请联系管理员");
                }
              })
            }else{
              Toast.success("没有SIM卡卡号，不执行！");
              Loading.hide();
            }
          },
          /**
           * 获取所有的设备
           */
          getWaterState(){
            let _this = this;
            if("460100"!=Tool.getLoginUser().deptcode){
              _this.waterEquipmentDto.xmbh = Tool.getLoginUser().xmbh;
            }
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquiplog/getWaterState',_this.waterEquipmentDto).then((res) => {
              let response = res.data.content;
              _this.zcStates = [];
              _this.ycStates = [];
              for(let i=0;i<response.length;i++){
                let obj = response[i];
                if((new Date().getTime()-new Date(obj.cjsj.replace(/-/g,'/')).getTime())/1000>70){
                    _this.ycStates.push(obj);
                }else{
                    _this.zcStates.push(obj);
                }
              }
              _this.$forceUpdate();
            })
          },

        }
    }
</script>

<style scoped>

</style>