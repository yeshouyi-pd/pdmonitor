<template>
  <div>
    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>基站id</th>
          <th>基站名称</th>
          <th>基站编号</th>
          <th>信标编号</th>
          <th>原始数据</th>
          <th>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="stationsHeart in stationsHearts">
          <td>{{stationsHeart.nodeId}}</td>
          <td>{{stationsHeart.nodeName}}</td>
          <td>{{stationsHeart.nodeNum}}</td>
          <td>{{stationsHeart.loraId}}</td>
          <td>{{stationsHeart.data}}</td>
          <td>{{stationsHeart.createTime}}</td>
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
  name: "stations-heart",
  components: {Pagination},
  data: function (){
    return {
      stationsHearts:[]
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
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/stationsHeart/list',{
        page: page,
        size: _this.$refs.pagination.size,
      }).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.stationsHearts = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>