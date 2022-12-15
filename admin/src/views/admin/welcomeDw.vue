<template>
  <div></div>
</template>
<script>
import EquipmentMap from "../monitor/equipmentMap";
export default {
  components: {EquipmentMap},
  name:'welcome',
  data: function() {
    return {
      waterQualityResults:[],
      kvMaps:[],
      count:{},
      szjcx:[],
      zs:0,
      zc:0,
      lx:0,
      gz:0,
      JYXM_DW:JYXM_DW

    }
  },
  created() {
    let _this = this;
    _this.getLoginUser();
  },
  methods: {
    getLoginUser(){
      let _this = this;
      Loading.show();
      let obj = {
        "loginName" : "yy",
        "password" : hex_md5("whpd1234@"+KEY)
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/loginDw', obj).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          //Toast.success("保存成功！");
          resp.content.xmbh = "011";
          Tool.setLoginUser(resp.content);
          _this.$router.push("/welcome");
          console.log(Tool.getLoginUser());
        } else {
          obj.password="";
          Toast.warning(resp.message)
        }
      })
    }

  }
}
</script>