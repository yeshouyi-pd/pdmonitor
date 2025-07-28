<template>
  <div class="input-group">
    <input type="text" class=" form-control" v-bind:id="idValue" v-model="setValue"/>
    <span class="input-group-addon">
      <i class="fa fa-calendar bigger-110"></i>
    </span>
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

    dateInit: function () {
      let _this = this;

      $("#" + _this.idValue).datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',//显示格式
        locale: moment.locale('zh-cn'),
        icons: {
          time: 'fa fa-clock-o',
          date: 'fa fa-calendar',
          up: 'fa fa-chevron-up',
          down: 'fa fa-chevron-down',
          previous: 'fa fa-chevron-left',
          next: 'fa fa-chevron-right',
          today: 'fa fa-arrows ',
          clear: 'fa fa-trash',
          close: 'fa fa-times'
        }
      }).on('dp.change', function (e) {
        if($("#"+_this.idValue).val()){
          //处理代码
          let d =   Tool.checkTime($("#"+_this.idValue).val().split(" ")[0]
              ,new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate());//2020-12-03
          if(d){
            _this.$emit('methodName',$("#"+_this.idValue).val());
          }else {
            $("#"+_this.idValue).val("");
            Toast.warning("开始时间不能大于今天时间");
          }
        }
      });


    }
  }
}
</script>
