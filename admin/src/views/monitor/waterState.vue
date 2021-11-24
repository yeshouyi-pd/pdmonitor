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
                                <div style="float: left;width:12.5%;height:140px;" align="center">
                                    <div style="width:93%;height:130px;position:relative;top:10px;border:3px solid #009900;border-radius:5px;">
                                        <div style="width:96%;height:120px;position:relative;top:2px;border:2px solid #009900;border-radius:2px;">
                                            <div style="color: #009900;position:relative;top:10px;">
                                                <p style="font-size: 18px;font-weight: bolder;">{{state.sbsn}}</p>
                                                <p style="font-size: 18px;font-weight: bold;">设备正常</p>
                                                <p style="font-size: 10px;">{{state.sbmc}}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div align="center" v-for="state in ycStates">
                            <div style="text-align: center;">
                                <div style="float: left;width:12.5%;height:140px;" align="center">
                                    <div style="width:93%;height:130px;position:relative;top:10px;border:3px solid #FF0000;border-radius:5px;">
                                        <div style="width:96%;height:120px;position:relative;top:2px;border:2px solid #FF0000;border-radius:2px;">
                                            <div style="color: #FF0000;position:relative;top:10px;">
                                                <p style="font-size: 18px;font-weight: bolder;">{{state.sbsn}}</p>
                                                <p style="font-size: 18px;font-weight: bold;">设备异常</p>
                                                <p style="font-size: 10px;">{{state.sbmc}}</p>
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
            }
        },
        mounted: function() {
            let _this = this;
            _this.getWaterState();
        },
        methods: {
            /**
             * 获取所有的设备
             */
            getWaterState(){
                let _this = this;
                if("460100"!=Tool.getLoginUser().deptcode){
                  _this.waterEquipmentDto.xmbh = Tool.getLoginUser().xmbh;
                }
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/getWaterState',_this.waterEquipmentDto).then((res) => {
                    let response = res.data;
                    _this.zcStates = response.content.zclist;
                    _this.ycStates = response.content.yclist;
                })
            },
        },
    }
</script>

<style scoped>

</style>