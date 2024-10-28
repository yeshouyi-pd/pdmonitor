<template>
  <div class="input-group">
    <input type="text" class=" form-control" v-bind:id="idValue" v-model="setValue"/>
  </div>
</template>

<script>
export default {
  name: 'datecheck',
  props: ['idValue','setValue'],
data: function () {
  return {
  }
},
mounted: function() {
  let _this =this;
  _this.dateInit();
},
methods: {

  dateInit(){
    let _this =this;

    $("#"+_this.idValue).datepicker({
      autoclose:true,
      clearBtn:1,
      todayHighlight : 1,
      minView : 1,
      forceParse : 0,
    }).on('hide',(ev) => {
      let d =   Tool.checkTime($("#"+_this.idValue).val()
          ,new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());//2020-12-03
      if(d){
        //_this.cfDate($("#datepick").val());
        _this.$emit('methodName',$("#"+_this.idValue).val());
      }else {
        $("#"+_this.idValue).val("");
        Toast.warning("开始时间不能大于今天时间");
      }


    });


  }
}
}
</script>
<style scoped>
.form-control{
  color: yellow !important; /* 修改日期选择框字体颜色 */
  background-color: rgb(19, 34, 94) !important;
}
</style>
