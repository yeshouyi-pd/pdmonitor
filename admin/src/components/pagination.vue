<template>
    <div class="row">
        <div class="col-xs-1">
            <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">

            </div>
        </div>
        <div class="col-xs-11">
            <div class="dataTables_paginate  paging_simple_numbers"  id="dynamic-table_paginate">
                <ul class="pagination">
                    <li class="paginate_button "
                        aria-controls="dynamic-table"
                        tabindex="0"
                        v-bind:disabled="page === 1"
                        v-on:click="selectPage(1)">
                        <a href="#"  >首页</a>
                    </li>
                    <li class="paginate_button"
                        aria-controls="dynamic-table"
                        tabindex="0"
                        v-bind:disabled="page === 1"
                        v-on:click="selectPage(page - 1)">
                        <a href="#">上一页</a>
                    </li>
                    <li class="paginate_button "
                        aria-controls="dynamic-table"
                        tabindex="0"
                        v-for="p in pages" v-bind:id="'page-' + p"
                        v-bind:class="{'btn-primary active':page == p}"
                        v-on:click="selectPage(p)">
                        <a href="#">{{p}}</a>
                    </li>
                    <li class="paginate_button "
                        aria-controls="dynamic-table"
                        tabindex="0"
                        v-bind:disabled="page === pageTotal"
                        v-on:click="selectPage(page + 1)">
                        <a href="#">下一页</a>
                    </li>
                    <li class="paginate_button "
                        aria-controls="dynamic-table"
                        tabindex="0"
                        id="dynamic-table_next"
                        v-bind:disabled="page === pageTotal"
                        v-on:click="selectPage(pageTotal)">
                        <a href="#"> {{pageTotal||1}}</a>
                    </li>

                    <li class="paginate_button "
                        aria-controls="dynamic-table"
                        tabindex="0">
                        <a href="#"> 共【{{total}}】条</a>
                    </li>
                    <li class="paginate_button "
                        aria-controls="dynamic-table"
                        tabindex="0">
                      <a href="#">到第<input v-model="choosePage" style="padding: 0;margin: 0;border: none;width: 80px;text-align: center;"/></a>
                    </li>
                    <li class="paginate_button "
                        aria-controls="dynamic-table"
                        tabindex="0"
                        v-bind:disabled="page === pageTotal"
                        v-on:click="selectPageChoose">
                      <a href="#">确认</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>


</template>

<script>
  export default {
    name: 'pagination',
    props: {
      list: {
        type: Function,
        default: null
      },
      itemCount: Number // 显示的页码数，比如总共有100页，只显示10页，其它用省略号表示
    },
    data: function () {
      return {
        total: 0, // 总行数
        size: 10, // 每页条数
        page: 0, // 当前页码
        pageTotal: 0, // 总页数
        pages: [], // 显示的页码数组
        choosePage: 0
      }
    },
    methods: {
      /**
       * 渲染分页组件
       * @param page
       * @param total
       */
      render(page, total) {
        let _this = this;
        _this.page = page;
        _this.total = total;
        _this.pageTotal = Math.ceil(total / _this.size);
        _this.pages = _this.getPageItems(_this.pageTotal, page, _this.itemCount || 5);
        _this.choosePage = page;
      },
      selectPageChoose(){
        let _this = this;
        _this.selectPage(_this.choosePage);
      },
      /**
       * 查询某一页
       * @param page
       */
      selectPage(page) {
        let _this = this;
        if (page < 1) {
          page = 1;
        }
        if (page > _this.pageTotal) {
          page = _this.pageTotal;
        }
        if (this.page !== page) {
          _this.page = page;
          if (_this.list) {
            _this.list(page);
          }
        }
      },

      /**
       * 当前要显示在页面上的页码
       * @param total
       * @param current
       * @param length
       * @returns {Array}
       */
      getPageItems(total, current, length) {
        let items = [];
        if (length >= total) {
          for (let i = 1; i <= total; i++) {
            items.push(i);
          }
        } else {
          let base = 0;
          // 前移
          if (current - 0 > Math.floor((length - 1) / 2)) {
            // 后移
            base = Math.min(total, current - 0 + Math.ceil((length - 1) / 2)) - length;
          }
          for (let i = 1; i <= length; i++) {
            items.push(base + i);
          }
        }
        return items;
      }
    }
  }
</script>

<style scoped>

</style>
