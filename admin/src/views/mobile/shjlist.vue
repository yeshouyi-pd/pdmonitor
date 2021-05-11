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
        <div class="message-list" id="message-list">
          <div v-for="key in KvMap">
          <div class="message-item message-unread" v-on:click="shjlistinfo(key.key)">
            <i class="message-star ace-icon fa fa-star orange2"></i>

            <span class="sender" >
																		{{key.key}}
																	</span>

            <span class="time" style="font-size: 15px">{{key.value}}次 </span>

            <span  class="attachment">
              <i class="ace-icon fa  fa-bell" ></i>
            </span>
            <span class="summary">
																		<span class="badge badge-success mail-tag"></span>
																		<span class="text">
																			{{deptmap|optionMapKV(key.key)}}
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
    }
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
   shjlistinfo(sm1){
    let _this = this;
     SessionStorage.set(MSHJSM,sm1);
     SessionStorage.set(MSHJMC,_this.option(_this.deptmap,sm1));
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