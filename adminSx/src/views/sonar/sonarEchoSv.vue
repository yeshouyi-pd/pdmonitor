<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">平均SV回波图信息查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  采集时间：
                </td>
                <td style="width: 25%">
                  <times v-bind:startTime="startTime" v-bind:endTime="endTime" start-id="eftstime" end-id="eftetime"></times>
                </td>
                <td style="width:10%">
                  声呐换能器的编号：
                </td>
                <td style="width: 20%">
                  <input class="form-control" type="text"  v-model="sonarEchoDto.deviceCode"/>
                </td>
                <td style="width:20%" class="text-center">
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
          <!--            <th>SV值数组</th>-->
          <!--            <th>SV值数量</th>-->
          <!--            <th>时间戳数组</th>-->
          <!--            <th>时间戳数量</th>-->
<!--          <th>操作</th>-->
        </tr>
        </thead>
        <tbody>
        <tr v-for="sonarEcho in sonarEchos">
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
          <!--            <td>{{sonarEcho.bufferSonar}}</td>-->
          <!--            <td>{{sonarEcho.bufferSizeSonar}}</td>-->
          <!--            <td>{{sonarEcho.bufferTimeStamp}}</td>-->
          <!--            <td>{{sonarEcho.bufferSizeTimeStamp}}</td>-->
<!--          <td>-->
<!--            <div class="hidden-sm hidden-xs btn-group">-->
<!--              <button v-on:click="edit(sonarEcho)" class="btn btn-xs btn-info">-->
<!--                <i class="ace-icon fa fa-pencil bigger-120"></i>-->
<!--              </button>-->
<!--              <button v-on:click="del(sonarEcho.id)" class="btn btn-xs btn-danger">-->
<!--                <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
<!--              </button>-->
<!--            </div>-->
<!--          </td>-->
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
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
import Times from "../../components/times";
export default {
  components: {Pagination,Times},
  name: "sonar-sonarEcho",
  data: function() {
    return {
      sonarEchoDto: {},
      sonarEcho: {},
      sonarEchos: [],
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
      _this.sonarEchoDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.sonarEchoDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.sonarEchoDto.page = page;
      _this.sonarEchoDto.size = _this.$refs.pagination.size;
      _this.sonarEchoDto.type = 2;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/sonarEcho/list', _this.sonarEchoDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.sonarEchos = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>