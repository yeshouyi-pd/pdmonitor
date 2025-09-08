<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">发现江豚信息</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form>
                <table style="font-size: 1.1em; width:100%" class="text-right">
                  <tbody>
                  <tr style="height:50px;">
                    <td style="width:12%">
                      观察者姓名：
                    </td>
                    <td style="width: 13%">
                      <input class="input-sm" type="text" v-model="queryDto.sgcz" style="width:90%; height:32px;"/>
                    </td>
                    <td style="width:12%">
                      注释编码：
                    </td>
                    <td style="width: 13%">
                      <input class="input-sm" type="text" v-model="queryDto.czsbh" style="width:90%; height:32px;"/>
                    </td>
                    <td style="width:12%">
                      方法：
                    </td>
                    <td style="width: 13%">
                      <select class="input-sm" v-model="queryDto.sff" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['B']" :key="'B'+value" :value="value">{{name}}</option>
                      </select>
                    </td>
                  </tr>
                  <tr style="height:50px;">
                    <td style="width:12%">
                      物种名：
                    </td>
                    <td style="width: 13%">
                      <select class="input-sm" v-model="queryDto.swzm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['H']" :key="'H'+value" :value="value">{{name}}</option>
                      </select>
                    </td>
                    <td style="width:12%">
                      行为描述：
                    </td>
                    <td style="width: 13%">
                      <select class="input-sm" v-model="queryDto.hxwms" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['I']" :key="'I'+value" :value="value">{{name}}</option>
                      </select>
                    </td>
                    <td style="width:12%">
                      环类类型：
                    </td>
                    <td style="width: 13%">
                      <select class="input-sm" v-model="queryDto.hhjlxdm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['J']" :key="'J'+value" :value="value">{{name}}</option>
                      </select>
                    </td>
                  </tr>
                  <tr style="height:50px;">
                    <td style="width:12%">发现时间从：</td>
                    <td style="width: 13%">
                      <input class="input-sm" type="datetime-local" v-model="queryDto.fxsjStart" style="width:90%; height:32px;"/>
                    </td>
                    <td style="width:12%">到：</td>
                    <td style="width: 13%">
                      <input class="input-sm" type="datetime-local" v-model="queryDto.fxsjEnd" style="width:90%; height:32px;"/>
                    </td>
                    <td colspan="4" class="text-center">
                      <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      &nbsp;&nbsp;&nbsp;
                      <a href="javascript:location.replace(location.href);" class="btn btn-sm btn-success btn-round">
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
        <!-- query end -->
      </div>
    </div>

    <div>
      <table id="simple-table" class="table table-bordered table-hover">
        <thead>
        <tr>
          <th>发现时间</th>
          <th>距离</th>
          <th>观察者</th>
          <th>方向</th>
          <th>方法</th>
          <th>物种名</th>
          <th>行为描述</th>
          <th>群体最少</th>
          <th>群体最优</th>
          <th>群体最多</th>
          <th>注释编号</th>
          <th>近岸距离</th>
          <th>水深</th>
          <th>环类类型</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in items" :key="item.id">
          <td>{{ item.fxsj }}</td>
          <td>{{ item.sjl }}</td>
          <td>{{ item.sgcz }}</td>
          <td>{{ item.sfw }}</td>
          <td>{{ getCodeName('L', item.sff) }}</td>
          <td>{{ getCodeName('H', item.swzm) }}</td>
          <td>{{ getCodeName('I', item.hxwms) }}</td>
          <td>{{ item.sqtzx }}</td>
          <td>{{ item.sqtzy }}</td>
          <td>{{ item.sqtzd }}</td>
          <td>{{ item.czsbh }}</td>
          <td>{{ item.hzjajl }}</td>
          <td>{{ item.hmztds }}</td>
          <td>{{ getCodeName('J', item.hhjlxdm) }}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="showDetail(item)" class="btn btn-xs btn-info" title="详情">
                <i class="ace-icon fa fa-info-circle bigger-120"></i>
              </button>
              <button v-on:click="del(item.id)" class="btn btn-xs btn-danger" title="删除">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>

    <!-- 详情弹窗 -->
    <div id="detail-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog modal-lg" role="document" style="max-width: 90%; width: 90%;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" style="font-weight:600; color:#2c3e50;">发现江豚信息 - 详情</h4>
          </div>
          <div class="modal-body">
            <h5 style="color:#2c3e50; border-bottom:2px solid #3498db; padding-bottom:6px; margin-bottom:12px;">基本信息</h5>
            <table class="table table-bordered table-striped table-sm" style="margin-bottom:10px;">
              <tbody>
                <tr style="height:36px;">
                  <td style="width:18%; background:#f8f9fa;">观察周期ID</td>
                  <td style="width:32%;">{{ (detailData.appMonitorInfo && detailData.appMonitorInfo.id) ? detailData.appMonitorInfo.id : '-' }}</td>
                  <td style="width:18%; background:#f8f9fa;">观察者中文名</td>
                  <td style="width:32%;">{{ (detailData.appMonitorInfo && detailData.appMonitorInfo.gczzwm) ? detailData.appMonitorInfo.gczzwm : '-' }}</td>
                </tr>
                <tr style="height:36px;">
                  <td style="background:#f8f9fa;">开始观察时间</td>
                  <td style="color:#856404; background:#fff3cd; font-weight:500;">{{ (detailData.appMonitorInfo && detailData.appMonitorInfo.ksgcsj) ? detailData.appMonitorInfo.ksgcsj : '-' }}</td>
                  <td style="background:#f8f9fa;">结束观察时间</td>
                  <td style="color:#856404; background:#fff3cd; font-weight:500;">{{ (detailData.appMonitorInfo && detailData.appMonitorInfo.jsgcsj) ? detailData.appMonitorInfo.jsgcsj : '-' }}</td>
                </tr>
                <tr style="height:36px;">
                  <td style="background:#f8f9fa;">考察船类型</td>
                  <td>{{ (detailData.appMonitorInfo && detailData.appMonitorInfo.kcclx) ? getCodeName('A', detailData.appMonitorInfo.kcclx) : '-' }}</td>
                  <td style="background:#f8f9fa;">考察单位</td>
                  <td>{{ (detailData.appMonitorInfo && detailData.appMonitorInfo.gcdw) ? detailData.appMonitorInfo.gcdw : '-' }}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: { Pagination },
    name: "admin-appMonitorDiscovery",
    data: function () {
      return {
        queryDto: {},
        items: [],
        codeMap: {},
        detailItem: {},
        detailData: {},
      };
    },
    mounted: function () {
      let _this = this;
      _this.getCodeMap();
      _this.list(1);
    },
    methods: {
      getCodeMap() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/appMonitorInfo/getCodeMap').then((res) => {
          let response = res.data;
          if (response.success) {
            _this.codeMap = response.content || {};
          }
        });
      },
      getCodeName(type, code) {
        if (!type || !code || !this.codeMap || !this.codeMap[type]) return code;
        const map = this.codeMap[type] || {};
        return map[code] || code;
      },
      showDetail(item) {
        let _this = this;
        _this.detailItem = Object.assign({}, item);
        _this.detailData = {};
        Loading.show();
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/appMonitorDiscovery/getDetailByMid/' + encodeURIComponent(item.mid))
          .then((res) => {
            Loading.hide();
            const resp = res.data;
            if (resp && resp.success) {
              _this.detailData = resp.content || {};
            } else {
              _this.detailData = {};
              Toast.warning(resp && resp.message ? resp.message : '获取详情失败');
            }
            $("#detail-modal").modal("show");
          })
          .catch(() => {
            Loading.hide();
            _this.detailData = {};
            Toast.error('获取详情失败');
            $("#detail-modal").modal("show");
          });
      },
      // 将 datetime-local 值（如 2025-09-03T16:09）转为后端需要的 yyyy-MM-dd HH:mm:ss
      formatDateForBackend(value) {
        if (!value) return '';
        // 去掉毫秒与秒缺失情况
        let s = String(value).trim();
        // 替换 T 为 空格
        s = s.replace('T', ' ');
        // 如果没有秒，则补上 :00
        if (/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/.test(s)) {
          s = s + ':00';
        }
        // 简单裁剪掉可能存在的秒后的小数
        s = s.replace(/(\d{2}:\d{2}:\d{2})(\.\d+)?$/, '$1');
        return s;
      },
      list(page) {
        let _this = this;
        Loading.show();
        // 克隆并格式化时间参数
        const postDto = Object.assign({}, _this.queryDto);
        postDto.page = page;
        postDto.size = _this.$refs.pagination.size;
        if (postDto.fxsjStart) postDto.fxsjStart = _this.formatDateForBackend(postDto.fxsjStart);
        if (postDto.fxsjEnd) postDto.fxsjEnd = _this.formatDateForBackend(postDto.fxsjEnd);
        _this.$forceUpdate();
        _this.$ajax
          .post(process.env.VUE_APP_SERVER + "/system/admin/appMonitorDiscovery/list", postDto)
          .then((response) => {
            Loading.hide();
            let resp = response.data;
            _this.items = resp.content.list || [];
            _this.$refs.pagination.render(page, resp.content.total || 0);
          });
      },
      del(id) {
        let _this = this;
        Confirm.show("删除后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax
            .delete(process.env.VUE_APP_SERVER + "/system/admin/appMonitorDiscovery/delete/" + id)
            .then((response) => {
              Loading.hide();
              let resp = response.data;
              if (resp.success) {
                _this.list(1);
                Toast.success("删除成功！");
              }
            });
        });
      },
    },
  };
</script> 