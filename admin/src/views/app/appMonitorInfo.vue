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
          <th style="min-width: 120px;">操作</th>
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
          <td style="min-width: 120px;">
            <div class="hidden-sm hidden-xs btn-group" style="white-space: nowrap;">
              <button v-on:click="showDetail(appMonitorInfo)" class="btn btn-xs btn-info" title="详细" style="margin-right: 2px;">
                <i class="ace-icon fa fa-eye bigger-120"></i>
              </button>
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

    <!-- 详细信息弹框 -->
    <div id="detail-modal" class="modal fade" tabindex="-1" role="dialog">
              <div class="modal-dialog modal-lg" role="document" style="max-width: 90%; width: 90%;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">观察周期详细信息</h4>
          </div>
          <div class="modal-body" style="padding: 15px;">

            
            <!-- 基本信息 -->
            <div class="row" style="margin-bottom: 10px;">
              <div class="col-md-12">
                <h5 style="color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 4px; margin-bottom: 10px;">
                  <i class="fa fa-info-circle"></i> <strong>基本信息</strong>
                </h5>
                <table class="table table-bordered" style="background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.05); border-collapse: separate; border-spacing: 0; width: 100%;">
                  <tr style="height: 36px;">
                    <td style="width: 12%; padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-info-circle" style="margin-right: 8px; color: #3498db;"></i>数据统计</td>
                    <td style="width: 21%; padding: 6px 12px; border-bottom: 1px solid #dee2e6; text-align: left;">
                      <span class="badge badge-primary" style="padding: 6px 10px; font-size: 12px; border-radius: 15px;">🐬 发现江豚信息: {{detailData.discoveryList ? detailData.discoveryList.length : 0}}</span>
                    </td>
                    <td style="width: 12%; padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-ship" style="margin-right: 8px; color: #3498db;"></i>考察船类型</td>
                    <td style="width: 21%; padding: 6px 12px; border-bottom: 1px solid #dee2e6; text-align: left;">{{detailData.appMonitorInfo ? getCodeName('A', detailData.appMonitorInfo.kcclx) : '-'}}</td>
                    <td style="width: 12%; padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-user" style="margin-right: 8px; color: #3498db;"></i>观察者中文名</td>
                    <td style="width: 22%; padding: 6px 12px; border-bottom: 1px solid #dee2e6; text-align: left;">{{detailData.appMonitorInfo ? detailData.appMonitorInfo.gczzwm : '-'}}</td>
                  </tr>
                  <tr style="height: 36px;">
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-map-marker" style="margin-right: 8px; color: #3498db;"></i>考察区域</td>
                    <td style="border-bottom: 1px solid #dee2e6;">{{detailData.appMonitorInfo ? detailData.appMonitorInfo.gcqy : '-'}}</td>
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-building" style="margin-right: 8px; color: #3498db;"></i>考察单位</td>
                    <td style="border-bottom: 1px solid #dee2e6;">{{detailData.appMonitorInfo ? detailData.appMonitorInfo.gcdw : '-'}}</td>
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-clock-o" style="margin-right: 8px; color: #3498db;"></i>开始观察时间</td>
                    <td style="border-bottom: 1px solid #dee2e6; background-color: #fff3cd; font-weight: 500; color: #856404;">{{detailData.appMonitorInfo ? detailData.appMonitorInfo.ksgcsj : '-'}}</td>
                  </tr>
                  <tr style="height: 36px;">
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-calendar-times-o" style="margin-right: 8px; color: #3498db;"></i>结束观察时间</td>
                    <td style="border-bottom: 1px solid #dee2e6; background-color: #fff3cd; font-weight: 500; color: #856404;">{{detailData.appMonitorInfo ? detailData.appMonitorInfo.jsgcsj : '-'}}</td>
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-eye" style="margin-right: 8px; color: #3498db;"></i>总体观察状况</td>
                    <td style="border-bottom: 1px solid #dee2e6;">{{detailData.appMonitorInfo ? getCodeName('C', detailData.appMonitorInfo.wztgczk) : '-'}}</td>
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-search" style="margin-right: 8px; color: #3498db;"></i>独立观察者所用方法</td>
                    <td style="border-bottom: 1px solid #dee2e6;">{{detailData.appMonitorInfo ? getCodeName('B', detailData.appMonitorInfo.pdlgczsyff) : '-'}}</td>
                  </tr>
                  <tr style="height: 36px;">
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-sun-o" style="margin-right: 8px; color: #3498db;"></i>眩光所在范围</td>
                    <td style="border-bottom: 1px solid #dee2e6;">{{detailData.appMonitorInfo ? getCodeName('D', detailData.appMonitorInfo.wxgszfw) : '-'}}</td>
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-lightbulb-o" style="margin-right: 8px; color: #3498db;"></i>眩光状况</td>
                    <td style="border-bottom: 1px solid #dee2e6;">{{detailData.appMonitorInfo ? getCodeName('E', detailData.appMonitorInfo.wxgzk) : '-'}}</td>
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-compass" style="margin-right: 8px; color: #3498db;"></i>近岸方向</td>
                    <td style="border-bottom: 1px solid #dee2e6;">{{detailData.appMonitorInfo ? getCodeName('F', detailData.appMonitorInfo.njafx) : '-'}}</td>
                  </tr>
                  <tr style="height: 36px;">
                    <td style="padding: 6px 12px; font-weight: 600; background-color: #f8f9fa; border-right: 2px solid #dee2e6; border-bottom: 1px solid #dee2e6;"><i class="fa fa-location-arrow" style="margin-right: 8px; color: #3498db;"></i>方向</td>
                    <td style="border-bottom: 1px solid #dee2e6;">{{detailData.appMonitorInfo ? getCodeName('G', detailData.appMonitorInfo.nfx) : '-'}}</td>
                    <td colspan="4" style="padding: 6px 12px; text-align: center; color: #6c757d; font-style: italic;">-</td>
                  </tr>
                </table>
              </div>
            </div>

            <!-- 发现江豚信息 -->
            <div class="row" style="margin-bottom: 10px;">
              <div class="col-md-12">
                <h5 style="color: #2c3e50; border-bottom: 2px solid #e74c3c; padding-bottom: 4px; margin-bottom: 10px;">
                  <i class="fa fa-search"></i> <strong>发现江豚信息</strong>
                </h5>
                <div v-if="detailData.discoveryList && detailData.discoveryList.length > 0">
                  <div class="table-responsive" style="max-height: 250px; overflow-y: auto;">
                    <table class="table table-bordered table-hover table-striped table-sm">
                      <thead class="thead-dark sticky-top" style="position: sticky; top: 0; z-index: 1000;">
                        <tr>
                          <th style="min-width: 120px;">发现时间</th>
                          <th style="min-width: 80px;">距离</th>
                          <th style="min-width: 80px;">观察者</th>
                          <th style="min-width: 80px;">方向</th>
                          <th style="min-width: 80px;">方法</th>
                          <th style="min-width: 100px;">物种名</th>
                          <th style="min-width: 100px;">行为描述</th>
                          <th style="min-width: 80px;">群体最少</th>
                          <th style="min-width: 80px;">群体最优</th>
                          <th style="min-width: 80px;">群体最多</th>
                          <th style="min-width: 80px;">注释编号</th>
                          <th style="min-width: 80px;">近岸距离</th>
                          <th style="min-width: 80px;">水深</th>
                          <th style="min-width: 80px;">环类类型</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="discovery in detailData.discoveryList" :key="discovery.id">
                          <td>{{discovery.fxsj}}</td>
                          <td>{{discovery.sjl}}</td>
                          <td>{{discovery.sgcz}}</td>
                          <td>{{discovery.sfw}}</td>
                          <td>{{getCodeName('L', discovery.sff)}}</td>
                          <td>{{getCodeName('H', discovery.swzm)}}</td>
                          <td>{{getCodeName('I', discovery.hxwms)}}</td>
                          <td>{{discovery.sqtzx || '-'}}</td>
                          <td>{{discovery.sqtzy || '-'}}</td>
                          <td>{{discovery.sqtzd || '-'}}</td>
                          <td>{{discovery.czsbh}}</td>
                          <td>{{discovery.hzjajl}}</td>
                          <td>{{discovery.hmztds}}</td>
                          <td>{{getCodeName('J', discovery.hhjlxdm)}}</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
                <div v-else class="text-center text-muted">
                  <p>暂无发现江豚信息</p>
                </div>
              </div>
            </div>

            <!-- 人工观察信息 -->
            <div class="row" style="margin-bottom: 10px;">
              <div class="col-md-12">
                <h5 style="color: #2c3e50; border-bottom: 2px solid #27ae60; padding-bottom: 4px; margin-bottom: 10px;">
                  <i class="fa fa-eye"></i> <strong>人工观察信息</strong>
                </h5>
                <div v-if="detailData.manualEntryeList && detailData.manualEntryeList.length > 0">
                  <div class="table-responsive" style="max-height: 250px; overflow-y: auto;">
                    <table class="table table-bordered table-hover table-striped table-sm">
                      <thead class="thead-dark sticky-top" style="position: sticky; top: 0; z-index: 1000;">
                        <tr>
                          <th style="min-width: 80px;">观察类型</th>
                          <th style="min-width: 120px;">开始观察时间</th>
                          <th style="min-width: 80px;">左方观察者</th>
                          <th style="min-width: 80px;">记录者</th>
                          <th style="min-width: 80px;">右方观察者</th>
                          <th style="min-width: 80px;">独立观察者</th>
                          <th style="min-width: 100px;">独立观察者所用方法</th>
                          <th style="min-width: 80px;">附加观察者</th>
                          <th style="min-width: 100px;">总体观察状况</th>
                          <th style="min-width: 100px;">眩光所在范围</th>
                          <th style="min-width: 100px;">眩光状况</th>
                          <th style="min-width: 80px;">近岸距离(米)</th>
                          <th style="min-width: 80px;">水深(米)</th>
                          <th style="min-width: 80px;">船速</th>
                          <th style="min-width: 80px;">近岸方向</th>
                          <th style="min-width: 80px;">方向</th>
                          <th style="min-width: 80px;">停泊船数量</th>
                          <th style="min-width: 80px;">移动船数量</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="manual in detailData.manualEntryeList" :key="manual.id">
                          <td>{{getTypesName(manual.types) || '-'}}</td>
                          <td>{{manual.ksgcsj || '-'}}</td>
                          <td>{{manual.pzfgcz || '-'}}</td>
                          <td>{{manual.pjlz || '-'}}</td>
                          <td>{{manual.pyfgcz || '-'}}</td>
                          <td>{{manual.pdlgcz || '-'}}</td>
                          <td>{{getCodeName('B', manual.pdlgczsyff) || '-'}}</td>
                          <td>{{manual.pfjgcz || '-'}}</td>
                          <td>{{getCodeName('C', manual.wztgczk) || '-'}}</td>
                          <td>{{getCodeName('D', manual.wxgszfw) || '-'}}</td>
                          <td>{{getCodeName('E', manual.wxgzk) || '-'}}</td>
                          <td>{{manual.njajl || '-'}}</td>
                          <td>{{manual.nss || '-'}}</td>
                          <td>{{manual.ncs || '-'}}</td>
                          <td>{{getCodeName('F', manual.njafx) || '-'}}</td>
                          <td>{{getCodeName('G', manual.nfx) || '-'}}</td>
                          <td>{{manual.vtbcsl || '-'}}</td>
                          <td>{{manual.vydcsl || '-'}}</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
                <div v-else class="text-center text-muted">
                  <p>暂无人工观察信息</p>
                </div>
              </div>
            </div>
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
    components: {Pagination},
    name: "admin-appMonitorInfo",
    data: function() {
      return {
        appMonitorInfo: {},
        appMonitorInfoDto: {},
        appMonitorInfos: [],
        codeMap: {}, // 代码映射
        detailData: {}, // 详细信息数据
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
        });
      },
      /**
       * 观察类型映射：types 1 -> N；2 -> P、V、W、N；其他 -> '-'
       */
      getTypesName(value) {
        if (value === 1 || value === '1') {
          return 'N';
        }
        if (value === 2 || value === '2') {
          return 'P、V、W、N';
        }
        return '-';
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
       * 显示详细信息
       */
      showDetail(appMonitorInfo) {
        let _this = this;
        Loading.show();
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/appMonitorInfo/getDetail/' + appMonitorInfo.id).then((response) => {
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            _this.detailData = resp.content;
            $("#detail-modal").modal("show");
          } else {
            Toast.warning(resp.message || "获取详细信息失败");
          }
        }).catch(() => {
          Loading.hide();
          Toast.error("获取详细信息失败");
        });
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

<style scoped>
/* 表格滚动优化 */
.table-responsive::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}

.table-responsive::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 4px;
}

.table-responsive::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 4px;
}

.table-responsive::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}

/* 表格样式优化 */
.table-sm td, .table-sm th {
  padding: 6px 8px;
  font-size: 12px;
  white-space: nowrap;
}

.table-sm td {
  max-width: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* 粘性表头 */
.sticky-top {
  position: sticky !important;
  top: 0 !important;
  z-index: 1000 !important;
}

/* 表格行悬停效果 */
.table-hover tbody tr:hover {
  background-color: rgba(0,123,255,0.1) !important;
}

/* 响应式表格 */
@media (max-width: 768px) {
  .table-responsive {
    font-size: 11px;
  }
  
  .table-sm td, .table-sm th {
    padding: 4px 6px;
  }
}
</style> 