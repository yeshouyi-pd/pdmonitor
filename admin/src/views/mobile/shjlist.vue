<template>
  <div class="main-content" style="overflow: auto">
    <div class="main-content-inner">
    <div class="space-1"></div>
    <div>
						<span class="label label-primary arrowed-in-right label-lg">
									<b>聚类事件次数</b>
						</span>
    </div>
      <div class="space-6"></div>
        <div class="message-list" id="message-list">
          <div v-for="key in KvMap">
          <div class="message-item message-unread" v-on:click="shjlistinfo(key.sbbh)">
            <i class="message-star ace-icon fa fa-star orange2"></i>

            <span class="sender" >
																		{{waterEquipments|optionNSArray(key.sbbh)}}
																	</span>

            <span class="time" style="font-size: 15px">{{key.value}}次 </span>

            <span  class="attachment">
              <i class="ace-icon fa  fa-bell" ></i>
            </span>
            <span class="summary">
																		<span class="badge badge-success mail-tag"></span>
																		<span class="text">
																			{{deptmap|optionMapKV(key.deptcode)}}
																		</span>
            </span>

          </div>
            <div class="space-6" ></div>
        </div>

        </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "shjlist",
  data: function () {
    return {
      KvMap:[],
      deptmap:{},
      waterEquipments:[]
    }
  },
  created() {
    let _this = this;
    _this.findDeviceInfo();
  },
  mounted: function () {
    let _this =this;
    _this.KvMap  =  SessionStorage.get(MSHJMAP )|| [] ;
    if(Tool.isEmpty(_this.KvMap)){
      _this.$router.push("/mobile/mindex");
    }
    _this.getdeptname();

  },
  methods: {
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {};
      if("460100"==Tool.getLoginUser().deptcode){
        data = {'sblb':'0001','dqzl':'A1,A4'};
      }else{
        data = {'sblb':'0001','dqzl':'A1,A4','xmbh':Tool.getLoginUser().xmbh};
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        _this.$forceUpdate();
      })
    },
    /**
     *江豚预警
     */
    getdeptname() {
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getdeptname').then((res)=>{
        let response = res.data;
        _this.deptmap = response.content;
      })
    },
   shjlistinfo(sbbh){
    let _this = this;
     SessionStorage.set(MSHJSM,sbbh);
    // SessionStorage.set(MSHJMC,_this.option(_this.deptmap,sm1));
    _this.$router.push("/mobile/shjlistinfo");
  },

    option(object ,key){
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






  }
}
</script>

<style scoped>

</style>