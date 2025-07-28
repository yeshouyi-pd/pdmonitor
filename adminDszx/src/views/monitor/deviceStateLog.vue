<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">图片管理查询</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form>
                <table style="font-size: 1.1em;width:100%" class="text-right" >
                  <tbody>
                  <tr>
                    <td style="width: 10%;">
                      设备名称：
                    </td>
                    <td style="width: 15%;">
                      <select v-model="deviceStateLogDto.sbbh" class="form-control" id="form-field-select-1">
                        <option value="" selected>请选择</option>
                        <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                      </select>
                    </td>
                    <td style="width: 10%;">
                      采集时间：
                    </td>
                    <td style="width: 25%;">
                      <times v-bind:startTime="startTime" v-bind:endTime="endTime"  v-bind:svalue="deviceStateLogDto.stime" v-bind:evalue="deviceStateLogDto.etime"></times>
                    </td>
                    <td  style="width: 25%;" class="text-center">
                      <button type="button" v-on:click="list()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      <a href="javascript:location.replace(location.href);"  class="btn btn-sm btn-success btn-round">
                        <i class="ace-icon fa fa-refresh"></i>
                        重置
                      </a>
                      <button type="button" v-on:click="exportExce()" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                        <i class="ace-icon fa fa-book"></i>
                        导出
                      </button>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </form>
            </div>
          </div>
        </div>
        <!-- query end -->
      </div><!-- col-md-12 -->
    </div><!-- row -->

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr id="table-tr">
          <th></th>
          <th v-for="(item,index) in waterEquipments" :key="index" :id="item.sbsn">{{item.sbmc}}</th>
        </tr>
        </thead>
        <tbody id="table-tbody">

        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import Times from "../../components/time";
export default {
  name: 'device-state-log',
  components: {Times},
  data: function (){
    return {
      ssbrl:LOCAL_SSBRL,
      deviceStateLogDto:{},
      waterEquipments:[]
    }
  },
  mounted() {
    let _this = this;
    _this.findDeviceInfo();
    let day1 = new Date();
    day1.setDate(day1.getDate() - 15);
    let day2 = new Date();
    day2.setDate(day2.getDate() - 1);
    _this.deviceStateLogDto.stime = Tool.dateFormat('yyyy-MM-dd',day1);
    _this.deviceStateLogDto.etime = Tool.dateFormat('yyyy-MM-dd',day2);
  },
  methods: {
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.deviceStateLogDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.deviceStateLogDto.etime = rep;
      _this.$forceUpdate();
    },
    exportExce(){
      let _this = this;
      let paramsStr = "stime="+_this.deviceStateLogDto.stime;
      if(Tool.isNotEmpty(_this.deviceStateLogDto.etime)){
        paramsStr = paramsStr + "&etime="+_this.deviceStateLogDto.etime;
      }
      if(_this.ssbrl && "460100"!=Tool.getLoginUser().deptcode){
        paramsStr = paramsStr + "&xmbh="+Tool.getLoginUser().xmbh;
      }
      let url = process.env.VUE_APP_SERVER + '/monitor/export/exportDeviceStateLog?'+paramsStr;
      console.log(url);
      window.location.href = url;
    },
    /**
     * 列表查询
     */
    list() {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/deviceStateLog/listByRq', _this.deviceStateLogDto).then((response) => {
        Loading.hide();
        let resp = response.data;
        let tbody = document.getElementById("table-tbody");
        let rqMap = resp.content;
        $("#table-tbody").empty();
        for(let rq in rqMap){
          let tr = document.createElement("tr");
          let thtr = document.getElementById("table-tr");
          let td = document.createElement("td");
          td.innerHTML = rq;
          tr.append(td);
          let ztMap = rqMap[rq];
          for(let i=1;i<thtr.childNodes.length;i++){
            let td1 = document.createElement("td");
            let tr1 = thtr.childNodes[i];
            if(!Tool.isEmpty(ztMap[tr1.id])){
              if("0"==ztMap[tr1.id]){
                td1.innerHTML = '√';
              }else if("1"==ztMap[tr1.id]){
                td1.innerHTML = '×';
              }else{
                td1.innerHTML = '';
              }
            }
            tr.append(td1);
          }
          tbody.append(tr);
        }
      })
    },
    findDeviceInfo(){
      let _this = this;
      Loading.show();
      let data = {'sblb':'0001','dqzl':'A1,A4'};
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
        Loading.hide();
        _this.waterEquipments = response.data.content;
        _this.list();
      })
    },
  }
}
</script>