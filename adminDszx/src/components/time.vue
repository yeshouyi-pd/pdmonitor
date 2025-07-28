<template>
        <div class="input-daterange input-group">
            <input type="text" class="input-sm form-control"  v-bind:value="svalue"   v-bind:id="startId+'-input'"/>
            <span class="input-group-addon">
					<i class="fa fa-exchange"></i>
			</span>
            <input type="text" class="input-sm form-control"  v-bind:value="evalue"  v-bind:id="endId+'-input'"/>
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

                $("#" + _this.startId + "-input").datepicker({
                    autoclose:true,
                    clearBtn:1,
                    todayHighlight : 1,
                    minView : 1,
                    forceParse : 0,
                }).on('hide', (ev) => {
                    let d =   Tool.checkTime($("#" + _this.startId + "-input").val()
                        ,$("#" + _this.endId + "-input").val());
                    if(d){
                        _this.startTime($("#" + _this.startId + "-input").val());
                    }else {
                        $("#" + _this.startId + "-input").val("");
                        Toast.warning("开始时间不能大于结束时间");
                    }


                });



                $("#" + _this.endId + "-input").datepicker({
                    autoclose:true,
                    clearBtn:1,
                    todayHighlight : 1,
                    minView : 1,
                    forceParse : 0,

                }).on('hide', (ev) => {
                 let d =   Tool.checkTime($("#" + _this.startId + "-input").val()
                   ,$("#" + _this.endId + "-input").val());
                 if(d){
                     _this.endTime($("#" + _this.endId + "-input").val());
                 }else {
                     $("#" + _this.endId + "-input").val("");
                     Toast.warning("结束时间不能小于开始时间");
                 }


                });


            }
        }
    }
</script>
