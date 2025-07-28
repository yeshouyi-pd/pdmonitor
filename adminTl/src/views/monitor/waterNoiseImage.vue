<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">水噪声图片查询</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;width: 100%" class="text-right">
                  <tbody>
                  <tr>
                    <td style="width:8%">设备sn：</td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-2"  class="input-sm"  v-model="equipmentFileDto.sbbn"/>
                    </td>
                    <td  style="width: 6%">采集日期：</td>
                    <td style="width: 15%" >
                      <times v-bind:startTime="startTime" v-bind:endTime="endTime"></times>
                    </td>
                    <td colspan="2" class="text-center">
                      <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>查询
                      </button>
                      <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round">
                        <i class="ace-icon fa fa-refresh"></i>重置
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
      <div style="display: flex">
        <div style="display: flex">
<!--          <img src="./public/static/image/index1.jpg"/>-->
          <span>设备sn:test2</span>
          <span>采集时间:2021-03-31 09:37:21</span>
        </div>
      </div>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="20"></pagination>
    </div>
  </div>
</template>
<script>
import Pagination from "../../components/pagination";
export default {
  components:{Pagination},
  name: 'water-noise-image',
  data: function(){
    return{
      equipmentFile:{},
      equipmentFileDto:{},
      equipmentFiles:[]
    }
  },
  mounted() {
    let _this = this;
    _this.$refs.pagination.size = 20;
    _this.list(1);
  },
  methods:{
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.equipmentFileDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.equipmentFileDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      _this.equipmentFileDto.page=page;
      _this.equipmentFileDto.size=_this.$refs.pagination.size;
      _this.$forceUpdate();
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFile/list',_this.equipmentFileDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFiles = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
      })
    }
  }
}
</script>