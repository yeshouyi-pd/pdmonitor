<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">导出记录</h4>
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
                    <td style="width:12%">观察者中文名：</td>
                    <td style="width:13%">
                      <input class="input-sm" type="text" v-model="queryDto.gczzwm" style="width:90%; height:32px;"/>
                    </td>
                    <td style="width:12%">日期：</td>
                    <td style="width:13%">
                      <input class="input-sm" type="date" v-model="queryDto.date1" style="width:90%; height:32px;"/>
                    </td>
                    <td colspan="4" class="text-center">
                      <button type="button" v-on:click="list(1)" class="btn btn-sm btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      &nbsp;&nbsp;&nbsp;

                        <button type="button" v-on:click="exportQueryall()"    class="btn btn-sm btn-warning btn-round" style="margin-right: 10px;">
                          <i class="ace-icon fa fa-leaf"></i>
                          导出
                        </button>
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
          <th>日期</th>
          <th>时间</th>
          <th>经度</th>
          <th>纬度</th>
          <th>事件</th>
          <th>数据1</th>
          <th>数据2</th>
          <th>数据3</th>
          <th>数据4</th>
          <th>数据5</th>
          <th>数据6</th>
          <th>数据7</th>
          <th>数据8</th>
          <th>观察者中文名</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in items" :key="item.id">
          <td>{{ item.date1 }}</td>
          <td>{{ item.time1 }}</td>
          <td>{{ item.declat }}</td>
          <td>{{ item.declong }}</td>
          <td>{{ item.events }}</td>
          <td>{{ item.data1 }}</td>
          <td>{{ item.data2 }}</td>
          <td>{{ item.data3 }}</td>
          <td>{{ item.data4 }}</td>
          <td>{{ item.data5 }}</td>
          <td>{{ item.data6 }}</td>
          <td>{{ item.data7 }}</td>
          <td>{{ item.data8 }}</td>
          <td>{{ item.gczzwm }}</td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
  </div>
</template>

<script>
import Pagination from "../../components/pagination";
import qs from "qs";

export default {
    components: { Pagination },
    name: "admin-appMonitorExp",
    data: function () {
      return {
        queryDto: {},
        items: [],
        isExporting:false,
      };
    },
    mounted: function () {
      let _this = this;
      // 默认日期为当天
      _this.queryDto.date1 = _this.todayAsInputDate();
      _this.list(1);
    },
    methods: {

       exportQueryall() {
         this.queryDto.deptcode= Tool.getLoginUser().deptcode;
         this.$forceUpdate();
          let data = qs.stringify(this.queryDto);
         window.location.href = process.env.VUE_APP_SERVER + "/system/excel/exportCarinfo?" + data;
      },


      // 返回当天日期，格式 yyyy-MM-dd，适用于 input[type=date]
      todayAsInputDate() {
        const d = new Date();
        const y = d.getFullYear();
        const m = (d.getMonth() + 1).toString().padStart(2, '0');
        const dd = d.getDate().toString().padStart(2, '0');
        return `${y}-${m}-${dd}`;
      },
      list(page) {
        let _this = this;
        Loading.show();
        const postDto = Object.assign({}, _this.queryDto);
        // 日期自动格式化 YYYYMMDD
        if (postDto.date1) {
          const d = new Date(postDto.date1);
          if (!isNaN(d.getTime())) {
            const y = d.getFullYear();
            const m = (d.getMonth() + 1).toString().padStart(2, '0');
            const dd = d.getDate().toString().padStart(2, '0');
            postDto.date1 = `${y}${m}${dd}`;
          }
        }
        postDto.page = page;
        postDto.size = _this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax
          .post(process.env.VUE_APP_SERVER + "/system/admin/appMonitorExp/list", postDto)
          .then((response) => {
            Loading.hide();
            let resp = response.data;
            const content = resp.content || {};
            _this.items = content.list || [];
            _this.$refs.pagination.render(page, content.total || 0);
          });
      },
    },
  };
</script> 