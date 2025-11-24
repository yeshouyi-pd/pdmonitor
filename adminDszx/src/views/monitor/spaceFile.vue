<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">开启记录查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 15%">
                  <select v-model="spaceFileDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>请选择</option>
                    <option v-for="item in waterEquipments" :value="item.sbsn">{{item.sbmc}}</option>
                  </select>
                </td>
                <td style="width: 10%;">
                  开始日期：
                </td>
                <td style="width: 15%;">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="rStime" end-id="rEtime"></times>
                </td>
                <td  style="width: 20%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </a>
                  <button type="button" v-on:click="exportExcle()" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-leaf"></i>
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

    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
          <tr>
            <th>设备名称</th>
            <th>设备编号</th>
            <th>预警值</th>
            <th>预警时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="spaceFile in spaceFiles">
            <td>{{waterEquipments|optionNSArray(spaceFile.sbbh)}}</td>
            <td>{{spaceFile.sbbh}}</td>
            <td>{{spaceFile.yjz}}</td>
            <td>{{spaceFile.cjsj}}</td>
          </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "@/components/times";
  export default {
    components: {Pagination, Times},
    name: "monitor-spaceFile",
    data: function() {
      return {
        spaceFileDto: {},
        spaceFile: {},
        spaceFiles: [],
        waterEquipments:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.findDeviceInfo();
      _this.$refs.pagination.size = 10;
      _this.list(1);
    },
    methods: {
      exportExcle(){
        let _this = this;
        let param = "";
        if(!Tool.isEmpty(_this.spaceFileDto.sbbh)){
          param+="&sbbh="+_this.spaceFileDto.sbbh;
        }
        if(!Tool.isEmpty(_this.spaceFileDto.stime)){
          param+="&stime="+_this.spaceFileDto.stime;
        }
        if(!Tool.isEmpty(_this.spaceFileDto.etime)){
          param+="&etime="+_this.spaceFileDto.etime;
        }
        if(Tool.isEmpty(param)){
          window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportSpaceFile';
        }else{
          window.location.href = process.env.VUE_APP_SERVER + '/monitor/export/exportSpaceFile?'+param.substring(1,param.length);
        }
      },
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.spaceFileDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.spaceFileDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.spaceFileDto.page = page;
        _this.spaceFileDto.size = _this.$refs.pagination.size;
        if("460100"!=Tool.getLoginUser().deptcode){
          _this.spaceFileDto.xmbh = Tool.getLoginUser().xmbh;
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/spaceFile/list', _this.spaceFileDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.spaceFiles = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },
      findDeviceInfo(){
        let _this = this;
        Loading.show();
        let data = {};
        if("460100"==Tool.getLoginUser().deptcode){
          data = {'sblb':'0001','dqzl':'A1,A4'};
        }else{
          data = {'sblb':'0001','dqzl':'A1,A4','xmbh':Tool.getLoginUser().xmbh};
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
          Loading.hide();
          _this.waterEquipments = response.data.content;
          _this.$forceUpdate();
        })
      }
    }
  }
</script>