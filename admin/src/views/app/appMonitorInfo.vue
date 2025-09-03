<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">观察周期管理</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form>
                <table style="font-size: 1.1em;width:100%" class="text-right">
                  <tbody>
                  <tr style="height:50px;">
                    <td style="width:12%">
                      考察船类型：
                    </td>
                    <td style="width: 13%">
                      <select v-model="appMonitorInfoDto.kcclx" class="input-sm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['A']" v-bind:value="value">{{name}}</option>
                      </select>
                    </td>
                    <td style="width: 12%">
                      观察者中文名：
                    </td>
                    <td style="width: 13%">
                      <input class="input-sm" type="text" v-model="appMonitorInfoDto.gczzwm" placeholder="请输入观察者中文名" style="width:90%; height:32px;"/>
                    </td>
                    <td style="width: 12%">
                      考察区域：
                    </td>
                    <td style="width: 13%">
                      <input class="input-sm" type="text" v-model="appMonitorInfoDto.gcqy" placeholder="请输入考察区域" style="width:90%; height:32px;"/>
                    </td>
                    <td style="width: 12%">
                      考察单位：
                    </td>
                    <td style="width: 13%">
                      <input class="input-sm" type="text" v-model="appMonitorInfoDto.gcdw" placeholder="请输入考察单位" style="width:90%; height:32px;"/>
                    </td>
                  </tr>
                  <tr style="height:50px;">
                    <td style="width:12%">
                      独立观察者所用方法：
                    </td>
                    <td style="width: 13%">
                      <select v-model="appMonitorInfoDto.pdlgczsyff" class="input-sm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['B']" v-bind:value="value">{{name}}</option>
                      </select>
                    </td>
                    <td style="width: 12%">
                      总体观察状况：
                    </td>
                    <td style="width: 13%">
                      <select v-model="appMonitorInfoDto.wztgczk" class="input-sm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['C']" v-bind:value="value">{{name}}</option>
                      </select>
                    </td>
                    <td style="width: 12%">
                      眩光所在范围：
                    </td>
                    <td style="width: 13%">
                      <select v-model="appMonitorInfoDto.wxgszfw" class="input-sm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['D']" v-bind:value="value">{{name}}</option>
                      </select>
                    </td>
                    <td style="width: 12%">
                      眩光状况：
                    </td>
                    <td style="width: 13%">
                      <select v-model="appMonitorInfoDto.wxgzk" class="input-sm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['E']" v-bind:value="value">{{name}}</option>
                      </select>
                    </td>
                  </tr>
                  <tr style="height:50px;">
                    <td style="width:12%">
                      近岸方向：
                    </td>
                    <td style="width: 13%">
                      <select v-model="appMonitorInfoDto.njafx" class="input-sm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['F']" v-bind:value="value">{{name}}</option>
                      </select>
                    </td>
                    <td style="width: 12%">
                      方向：
                    </td>
                    <td style="width: 13%">
                      <select v-model="appMonitorInfoDto.nfx" class="input-sm" style="width:90%; height:32px;">
                        <option value="">请选择</option>
                        <option v-for="(name, value) in codeMap['G']" v-bind:value="value">{{name}}</option>
                      </select>
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
      </div><!-- col-md-12 -->
    </div><!-- row -->

    <div>
      <table id="simple-table" class="table table-bordered table-hover">
        <thead>
        <tr>
          <th>考察船类型</th>
          <th>观察者中文名</th>
          <th>观察者姓名</th>
          <th>考察区域</th>
          <th>考察单位</th>
          <th>开始观察时间</th>
          <th>左方观察者</th>
          <th>记录者</th>
          <th>右方观察者</th>
          <th>独立观察者</th>
          <th>独立观察者所用方法</th>
          <th>总体观察状况</th>
          <th>眩光所在范围</th>
          <th>眩光状况</th>
          <th>近岸距离(米)</th>
          <th>水深(米)</th>
          <th>船速</th>
          <th>近岸方向</th>
          <th>方向</th>
          <th>结束观察时间</th>
          <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="appMonitorInfo in appMonitorInfos">
          <td>{{getCodeName('A', appMonitorInfo.kcclx)}}</td>
          <td>{{appMonitorInfo.gczzwm}}</td>
          <td>{{appMonitorInfo.gczxm}}</td>
          <td>{{appMonitorInfo.gcqy}}</td>
          <td>{{appMonitorInfo.gcdw}}</td>
          <td>{{appMonitorInfo.ksgcsj}}</td>
          <td>{{appMonitorInfo.pzfgcz}}</td>
          <td>{{appMonitorInfo.pjlz}}</td>
          <td>{{appMonitorInfo.pyfgcz}}</td>
          <td>{{appMonitorInfo.pdlgcz}}</td>
          <td>{{getCodeName('B', appMonitorInfo.pdlgczsyff)}}</td>
          <td>{{getCodeName('C', appMonitorInfo.wztgczk)}}</td>
          <td>{{getCodeName('D', appMonitorInfo.wxgszfw)}}</td>
          <td>{{getCodeName('E', appMonitorInfo.wxgzk)}}</td>
          <td>{{appMonitorInfo.njajl}}</td>
          <td>{{appMonitorInfo.nss}}</td>
          <td>{{appMonitorInfo.ncs}}</td>
          <td>{{getCodeName('F', appMonitorInfo.njafx)}}</td>
          <td>{{getCodeName('G', appMonitorInfo.nfx)}}</td>
          <td>{{appMonitorInfo.jsgcsj}}</td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="del(appMonitorInfo.id)" class="btn btn-xs btn-danger" title="删除">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
              </button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "admin-appMonitorInfo",
    data: function() {
      return {
        appMonitorInfo: {},
        appMonitorInfoDto: {},
        appMonitorInfos: [],
        codeMap: {}, // 代码映射
      }
    },
    mounted: function() {
      let _this = this;
      _this.getCodeMap();
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("app-appMonitorInfo-sidebar");
    },
    methods: {
      /**
       * 获取代码映射
       */
      getCodeMap() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/appMonitorInfo/getCodeMap').then((res) => {
          let response = res.data;
          if (response.success) {
            _this.codeMap = response.content;
          }
        })
      },

      /**
       * 获取代码名称
       */
      getCodeName(type, value) {
        if (!value || !this.codeMap || !this.codeMap[type] || !this.codeMap[type][value]) {
          return value || '';
        }
        return this.codeMap[type][value];
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.appMonitorInfoDto.page = page;
        _this.appMonitorInfoDto.size = _this.$refs.pagination.size;
        _this.$forceUpdate();

        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/appMonitorInfo/list', _this.appMonitorInfoDto).then((response) => {
          Loading.hide();
          let resp = response.data;
          _this.appMonitorInfos = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("删除观察周期记录后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/appMonitorInfo/delete/' + id).then((response) => {
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