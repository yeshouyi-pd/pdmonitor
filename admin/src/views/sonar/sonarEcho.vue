<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>


    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
                    <th></th>
            <th>声呐换能器的编号</th>
            <th>采集时间</th>
            <th>ping个数</th>
            <th>ping中点的个数</th>
            <th>相邻点间距</th>
            <th>色卡最大值</th>
            <th>色卡最小值</th>
            <th>当前这段数据的平均SV</th>
            <th>计算平均SV的最大深度</th>
            <th>计算平均SV的最小深度</th>
            <th>SV值数组</th>
            <th>SV值数量</th>
            <th>时间戳数组</th>
            <th>时间戳数量</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="sonarEcho in sonarEchos">
              <td>{{sonarEcho.id}}</td>
              <td>{{sonarEcho.deviceCode}}</td>
              <td>{{sonarEcho.acTime}}</td>
              <td>{{sonarEcho.numPing}}</td>
              <td>{{sonarEcho.numDot}}</td>
              <td>{{sonarEcho.dr}}</td>
              <td>{{sonarEcho.dbMax}}</td>
              <td>{{sonarEcho.dbMin}}</td>
              <td>{{sonarEcho.averSv}}</td>
              <td>{{sonarEcho.avrMax}}</td>
              <td>{{sonarEcho.avrMin}}</td>
              <td>{{sonarEcho.bufferSonar}}</td>
              <td>{{sonarEcho.bufferSizeSonar}}</td>
              <td>{{sonarEcho.bufferTimeStamp}}</td>
              <td>{{sonarEcho.bufferSizeTimeStamp}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(sonarEcho)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(sonarEcho.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">声呐换能器的编号</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.deviceCode" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">采集时间</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.acTime" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">ping个数</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.numPing" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">ping中点的个数</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.numDot" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">相邻点间距</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.dr" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">色卡最大值</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.dbMax" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">色卡最小值</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.dbMin" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">当前这段数据的平均SV</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.averSv" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">计算平均SV的最大深度</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.avrMax" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">计算平均SV的最小深度</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.avrMin" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">SV值数组</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.bufferSonar" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">SV值数量</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.bufferSizeSonar" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">时间戳数组</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.bufferTimeStamp" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">时间戳数量</label>
                      <div class="col-sm-10">
                        <input v-model="sonarEcho.bufferSizeTimeStamp" class="form-control">
                      </div>
                    </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "sonar-sonarEcho",
    data: function() {
      return {
      sonarEcho: {},
      sonarEchos: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("sonar-sonarEcho-sidebar");

    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.sonarEcho = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(sonarEcho) {
        let _this = this;
        _this.sonarEcho = $.extend({}, sonarEcho);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/sonar/admin/sonarEcho/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.sonarEchos = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;

        // 保存校验
        if (1 != 1
                || !Validator.length(_this.sonarEcho.deviceCode, "声呐换能器的编号", 1, 50)
                || !Validator.length(_this.sonarEcho.acTime, "采集时间", 1, 50)
                || !Validator.length(_this.sonarEcho.bufferSonar, "SV值数组", 1, 255)
                || !Validator.length(_this.sonarEcho.bufferTimeStamp, "时间戳数组", 1, 255)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/sonar/admin/sonarEcho/save', _this.sonarEcho).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            Toast.success("保存成功！");
          } else {
            Toast.warning(resp.message)
          }
        })
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("删除后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/sonar/admin/sonarEcho/delete/' + id).then((response)=>{
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