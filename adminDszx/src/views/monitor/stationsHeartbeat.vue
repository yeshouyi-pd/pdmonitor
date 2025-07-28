<template>
  <div>
    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>基站id</th>
          <th>状态</th>
          <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="stationsHeartbeat in stationsHeartbeats">
          <td>{{stationsHeartbeat.nodeId}}</td>
          <td>{{stationsHeartbeat.status}}</td>
          <td>{{stationsHeartbeat.createTime}}</td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>
  </div>
</template>
<script>
import Pagination from "../../components/pagination";
export default {
  name: "stations-heartbeat",
  components: {Pagination},
  data: function (){
    return {
      stationsHeartbeats:[]
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
  },
  methods: {
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/stationsHeartbeat/list',{
        page: page,
        size: _this.$refs.pagination.size,
      }).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.stationsHeartbeats = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>