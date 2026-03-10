<template>
  <div class="input-group">
    <input type="text" class="input-sm form-control"  v-bind:value="svalue"   v-bind:id="startId+'-input-time'"/>
    <span class="input-group-addon">
					<i class="fa fa-exchange"></i>
			</span>
    <input type="text" class="input-sm form-control"  v-bind:value="evalue"  v-bind:id="endId+'-input-time'"/>
  </div>
</template>

<script>
export default {
  name: 'time-range-picker',
  props: {
    startTime: {
      type: Function,
      default: null
    },
    endTime: {
      type: Function,
      default: null
    },
    startId: {
      default: "startId"
    },
    endId: {
      default: "endId"
    },
    svalue: {
      default: ""
    },
    evalue: {
      default: ""
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

      $("#" + _this.startId + "-input-time").datetimepicker({
        format: 'YYYY-MM-DD HH:mm',//显示格式
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
        if($("#" + _this.startId + "-input-time").val()){
          //处理代码
          let d = Tool.checkTime($("#" + _this.startId + "-input-time").val()
              ,$("#" + _this.endId + "-input-time").val());
          if(d){
            _this.startTime($("#" + _this.startId + "-input-time").val());
          }else {
            $("#" + _this.startId + "-input-time").val("");
            Toast.warning("开始时间不能大于结束时间");
          }
        }
      });



      $("#" + _this.endId + "-input-time").datetimepicker({
        format: 'YYYY-MM-DD HH:mm',//显示格式
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
      }).on('dp.change', (ev) => {
        let d =   Tool.checkTime($("#" + _this.startId + "-input-time").val()
            ,$("#" + _this.endId + "-input-time").val());
        if(d){
          _this.endTime($("#" + _this.endId + "-input-time").val());
        }else {
          $("#" + _this.endId + "-input-time").val("");
          Toast.warning("结束时间不能小于开始时间");
        }


      });


    }
  }
}
</script>
