<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">捕食次数查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  捕食日期：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime"></times>
                </td>
                <td style="width:10%">
                  设备sn：
                </td>
                <td style="width: 10%">
                  <input class="form-control" type="text"  v-model="predationNumDto.sbbh"/>
                </td>
                <td  style="width: 25%" class="text-center">
                  <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </a>
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
            <th>监测点</th>
            <th>设备名称</th>
            <th>设备sn</th>
            <th>捕食日期</th>
            <th>鲸豚出现次数</th>
            <th>鲸豚捕食次数</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="predationNum in predationNums">
            <td>{{deptMap|optionMapKV(predationNum.deptcode)}}</td>
            <td>{{waterEquipments|optionNSArray(predationNum.sbbh)}}</td>
            <td>{{predationNum.sbbh}}</td>
            <td>{{predationNum.cjsj}}</td>
            <td>{{predationNum.alarmNum}}</td>
            <td>{{predationNum.predationNum}}</td>
          </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/time";
  export default {
    components: {Pagination,Times},
    name: "monitor-predationNum",
    data: function() {
      return {
        predationNum: {},
        predationNums: [],
        predationNumDto: {},
        deptMap: [],
        waterEquipments: []
      }
    },
    mounted: function() {
      let _this = this;
      _this.deptMap = Tool.getDeptUser();
      _this.$refs.pagination.size = 10;
      _this.$forceUpdate();
      _this.list(1);
      _this.findDeviceInfo();
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("monitor-predationNum-sidebar");
    },
    methods: {
      findDeviceInfo(){
        let _this = this;
        Loading.show();
        let data = {};
        if("460100"==Tool.getLoginUser().deptcode){
          data = {'sblb':'0001'};
        }else{
          data = {'sblb':'0001','xmbh':Tool.getLoginUser().xmbh};
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterEquipment/findAll', data).then((response)=>{
          Loading.hide();
          _this.waterEquipments = response.data.content;
        })
      },
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.predationNumDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.predationNumDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.predationNumDto.page = page;
        _this.predationNumDto.size = _this.$refs.pagination.size;
        if("460100"!=Tool.getLoginUser().deptcode){
          _this.predationNumDto.xmbh=Tool.getLoginUser().xmbh;
        }
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/predationNum/list', _this.predationNumDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.predationNums = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

    }
  }
</script>