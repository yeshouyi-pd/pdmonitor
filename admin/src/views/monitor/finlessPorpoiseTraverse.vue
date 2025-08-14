<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">江豚行动轨迹查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  项目名称：
                </td>
                <td style="width: 15%">
                  <input v-model="finlessPorpoiseTraverseDto.xmbhmc" style="width: 100%;" />
                </td>
                <td style="width: 10%;">
                  图片日期：
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
<!--                  <button type="button" v-on:click="exportExcle()" class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">-->
<!--                    <i class="ace-icon fa fa-leaf"></i>-->
<!--                    导出-->
<!--                  </button>-->
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
            <th>项目编号</th>
            <th>项目编号名称</th>
            <th>创建时间</th>
            <th>图片路径</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="finlessPorpoiseTraverse in finlessPorpoiseTraverses">
            <td>{{finlessPorpoiseTraverse.xmbh}}</td>
            <td>{{finlessPorpoiseTraverse.xmbhmc}}</td>
            <td>{{finlessPorpoiseTraverse.cjsj}}</td>
            <td>{{finlessPorpoiseTraverse.tplj}}</td>
            <td>
              <div class="hidden-sm hidden-xs btn-group">
                <button v-on:click="detail(finlessPorpoiseTraverse)" class="btn btn-xs btn-info" title="详情">
                  <i class="ace-icon fa fa-list bigger-120"></i>
                </button>
                <button v-on:click="del(finlessPorpoiseTraverse.id)" class="btn btn-xs btn-danger" title="删除">
                  <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>

    <div id="form-modal-img" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 50%">
        <div class="modal-content" style="width: 100%;margin: auto">
          <div class="modal-body">
            <img :src="finlessPorpoiseTraverse.tplj"/>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/times";
  export default {
    components: {Pagination,Times},
    name: "monitor-finlessPorpoiseTraverse",
    data: function() {
      return {
        finlessPorpoiseTraverseDto:{},
        finlessPorpoiseTraverse: {},
        finlessPorpoiseTraverses: [],
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.finlessPorpoiseTraverseDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.finlessPorpoiseTraverseDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.finlessPorpoiseTraverseDto.page = page;
        _this.finlessPorpoiseTraverseDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/finlessPorpoiseTraverse/list', _this.finlessPorpoiseTraverseDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.finlessPorpoiseTraverses = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },
      /**
       * 详情
       */
      detail(finlessPorpoiseTraverse) {
        let _this = this;
        _this.finlessPorpoiseTraverse = finlessPorpoiseTraverse;
        $("#form-modal-img").modal("show");
      },
      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("删除后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/monitor/admin/finlessPorpoiseTraverse/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      }
    }
  }
</script>