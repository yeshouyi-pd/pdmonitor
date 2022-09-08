<template>
  <div class="input-group">
    <input type="text" format="yyyy-MM-dd"  maxlength="10" class=" form-control" readonly v-bind:id="idValue" v-model="setValue"/>
    <span class="input-group-addon">
      <i class="fa fa-calendar bigger-110"></i>
    </span>
  </div>
</template>

<script>
export default {
  name: 'dategreater',
  props:  {
    idValue: {
      default: "idValue"
    },
    setValue: {
      default:""
    },
  },
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
      // let d =   Tool.checkTime($("#"+_this.idValue).val()
      //     ,new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());//2020-12-03
      // if(!d){
      //   //_this.cfDate($("#datepick").val());
      //   _this.$emit('methodName',$("#"+_this.idValue).val());
      // }else {
      //   $("#"+_this.idValue).val("");
      //   Toast.warning("请选择时间大于今天！");
      // }
      _this.$emit('methodName',$("#"+_this.idValue).val());

    });


  }
}
}
</script>
