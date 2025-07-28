<template>
        <div class="input-daterange input-group">
            <input type="text" class="input-sm form-control"  v-bind:value="svalue"   v-bind:id="startId"/>
            <span class="input-group-addon">
					<i class="fa fa-exchange"></i>
			</span>
            <input type="text" class="input-sm form-control"  v-bind:value="evalue"  v-bind:id="endId"/>
    </div>
</template>

<script>
    export default {
        name: 'timecheck',
        props: {
            startTime: {
                type: Function,
                default: null
            },
            endTime: {
                type: Function,
                default: null
            },
            svalue: {
                default: ""
            },
            evalue: {
                default: ""
            },
            startId: { //String类型，必须是定义过的，可以是空字符串""
              type: String,
              required: true,
            },
            endId: { //String类型，必须是定义过的，可以是空字符串""
              type: String,
              required: true,
            }
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

                $("#" + _this.startId).datepicker({
                    autoclose:true,
                    clearBtn:1,
                    todayHighlight : 1,
                    minView : 1,
                    forceParse : 0,
                }).on('hide', (ev) => {
                    let d =   Tool.checkTime($("#" + _this.startId).val()
                        ,$("#" + _this.endId).val());
                    if(d){
                        _this.startTime($("#" + _this.startId).val());
                    }else {
                        $("#" + _this.startId).val("");
                        Toast.warning("开始时间不能大于结束时间");
                    }


                });



                $("#" + _this.endId).datepicker({
                    autoclose:true,
                    clearBtn:1,
                    todayHighlight : 1,
                    minView : 1,
                    forceParse : 0,

                }).on('hide', (ev) => {
                 let d =   Tool.checkTime($("#" + _this.startId).val()
                   ,$("#" + _this.endId).val());
                 if(d){
                     _this.endTime($("#" + _this.endId).val());
                 }else {
                     $("#" + _this.endId).val("");
                     Toast.warning("结束时间不能小于开始时间");
                 }


                });


            }
        }
    }
</script>
