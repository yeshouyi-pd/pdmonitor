<template>
  <div>
    <!-- query start -->
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">系统日志</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form >
            <table  style="font-size: 1.1em;" class="text-right " >
              <tbody  >
              <tr  >
                <td style="width:6%"  >
                  操作人：
                </td>
                <td style="width: 8%">
                  <input class="form-control" type="text"  v-model="querysysLog.czr"/>
                </td>
                <td style="width: 6%" >
                  操作菜单：
                </td>
                <td style="width: 8%">
                  <input class="form-control" type="text"   v-model="querysysLog.czcd"/>
                </td>
                <td style="width:6%" >
                  操作时间：
                </td>
                <td style="width: 12%">
                  <Times v-bind:startTime="startTime"
                         v-bind:endTime="endTime" >
                  </Times>
                </td>
                <td  style="width: 14%" class="text-center">
                  <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  &nbsp;&nbsp;&nbsp;
                  <button type="button" v-on:click="resetPage()"  class="btn btn-sm   btn-success btn-round">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
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

    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th>操作人</th>
            <th>操作ip</th>
            <th style="width: 30%">操作菜单</th>
            <th>操作说明</th>
            <th style="width: 15%">操作时间</th>
            <th style="width: 25%">操作条件</th>

      </tr>
      </thead>

      <tbody>
      <tr v-for="sysLog in sysLogs">
              <td>{{sysLog.czr}}</td>
              <td>{{sysLog.czip}}</td>
              <td>{{sysLog.czcd}}</td>
              <td>{{sysLog.czsm}}</td>
              <td>{{sysLog.czsj}}</td>
              <td>{{sysLog.cztj}}</td>
      </tr>
      </tbody>
    </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>

  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/time";
  export default {
    inject:['reload'],
    components: {Pagination,Times},
    name: "system-sysLog",
    data: function() {
      return {
        querysysLog:{},
        sysLog: {},
        sysLogs: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
    },
    methods: {
      resetPage(){
        let _this = this;
        _this.reload();
      },
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.querysysLog.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.querysysLog.etime = rep;
        _this.$forceUpdate();
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.querysysLog.page=page;
        _this.querysysLog.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/sysLog/list', _this.querysysLog).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.sysLogs = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

    }
  }
</script>