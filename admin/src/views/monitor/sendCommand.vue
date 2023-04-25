<template>
  <div>
    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>基站编号</th>
          <th>信标编号</th>
          <th>序号</th>
          <th>时间整数部分</th>
          <th>时间小数部分</th>
          <th>相关参数</th>
          <th>相关参数</th>
          <th>晶振误差</th>
          <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="sendCommand in sendCommands">
          <td>{{sendCommand.nodeId}}</td>
          <td>{{sendCommand.loraId}}</td>
          <td>{{sendCommand.serialNumber}}</td>
          <td>{{sendCommand.wholeSec}}</td>
          <td>{{sendCommand.fracSec}}</td>
          <td>{{sendCommand.upChirpCor}}</td>
          <td>{{sendCommand.downChirpCor}}</td>
          <td>{{sendCommand.ppm}}</td>
          <td>{{sendCommand.createTime}}</td>
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
  name: "send-command",
  components: {Pagination},
  data: function (){
    return {
      sendCommands:[]
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
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/sendCommand/list',{
        page: page,
        size: _this.$refs.pagination.size,
      }).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.sendCommands = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>