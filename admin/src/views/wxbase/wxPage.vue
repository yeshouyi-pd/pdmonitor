<template>
  <div>
    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
        <tr>
            <th>页面名称</th>
            <th>页面路径</th>
            <th>页面图片</th>
<!--            <th>页面顺序</th>-->
<!--            <th>页面级别</th>-->
<!--            <th>页面关联id</th>-->
            <th>父页面名称</th>
            <th>是否启用</th>
        </tr>
      </thead>

      <tbody>
      <tr v-for="wxPage in wxPages">
              <td>{{wxPage.name}}</td>
              <td>{{wxPage.url}}</td>
              <td>{{wxPage.pic}}</td>
<!--              <td>{{wxPage.sx}}</td>-->
<!--              <td>{{wxPage.level}}</td>-->
<!--              <td>{{wxPage.linkId}}</td>-->
              <td>{{wxPage.parentName}}</td>
              <td>
                <div class="testswitch">
                  <input class="testswitch-checkbox" :id="wxPage.name" v-on:click="switchClick(wxPage)" type="checkbox" :checked="wxPage.isEnable=='1'">
                  <label class="testswitch-label" :for="wxPage.name">
                    <span class="testswitch-inner" data-on="启用" data-off="禁用"></span>
                    <span class="testswitch-switch"></span>
                  </label>
                </div>
              </td>
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
    components: {Pagination},
    name: "wxbase-wxPage",
    data: function() {
      return {
      wxPage: {},
      wxPages: [],
    }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-wxPage-sidebar");
    },
    methods: {
      switchClick(wxPage){
        let _this = this;
        _this.wxPage = $.extend({}, wxPage);
        if ($("#"+wxPage.name).is(':checked')) {
          $("#"+wxPage.name).prop('checked',false);
          Confirm.show("确认要启用？", function () {
            Loading.show();
            _this.wxPage.isEnable='1';
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxPage/save', _this.wxPage).then((response)=>{
              Loading.hide();
              let resp = response.data;
              if (resp.success) {
                $("#"+wxPage.name).prop('checked',true);
                Toast.success("启用成功！");
                _this.list(_this.$refs.pagination.page);
              } else {
                Toast.warning("启用失败！");
              }
            })
          });
        } else {
          $("#"+wxPage.name).prop('checked',true);
          Confirm.show("确认要禁用？", function () {
            Loading.show();
            _this.wxPage.isEnable='0';
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxPage/save', _this.wxPage).then((response)=>{
              Loading.hide();
              let resp = response.data;
              if (resp.success) {
                $("#"+wxPage.name).prop('checked',false);
                Toast.success("禁用成功！");
                _this.list(_this.$refs.pagination.page);
              } else {
                Toast.warning("禁用失败！");
              }
            })
          });
        }
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxPage/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.wxPages = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>