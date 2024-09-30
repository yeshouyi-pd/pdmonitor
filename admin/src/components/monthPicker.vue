<template>
  <div class="input-group" style="width: 100px;">
    <input type="text" class="form-control" style="background-color:transparent;border:none;font-size:17px;color:#d0ccff;" v-bind:id="idValue" v-model="setValue"/>
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
        format: 'YYYY年MM月',//显示格式
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
          _this.$emit('methodName',$("#"+_this.idValue).val());
        }
      });
    }
  }
}
</script>
<style scoped>
/deep/.bootstrap-datetimepicker-widget {
  color: #d0ccff !important; /* 修改日期选择框字体颜色 */
  background-color: #121E32 !important;
}
/deep/.bootstrap-datetimepicker-widget table td span:hover{
  color: #fff !important;
  background:#00C3E6 !important;
}
</style>
