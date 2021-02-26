<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">车牌螺丝固封申领查询统计</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;" class="text-right  " >
                  <tbody  >
                  <tr  >
                    <td  style="width: 6%">
                      申领时间：
                    </td>
                    <td style="width: 15%">
                      <times v-bind:startTime="startTime"
                             v-bind:endTime="endTime"></times>
                    </td>
                    <td   colspan="2" class="text-center">
                      <button  type="button" v-on:click="list()" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      &nbsp;&nbsp;&nbsp;
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
        <!-- query end -->
      </div><!-- col-md-12 -->
    </div><!-- row -->


    <div class="modal-body">
      <div class="form-group">
        <div class="col-sm-6">
          <h4 align="center">车牌固封螺丝申领记录表</h4>
          <table id="simple-table1" class="table  table-bordered table-hover" style="width: 80%">
            <thead>
            <tr>
              <th>申领网点</th>
              <th>线上申领数量</th>
              <th>线下申领数量</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="vehScrew in vehScrewCount">
              <td>{{ maps|optionMapKV(vehScrew.sysOrgCode) }}</td>
              <td>{{ vehScrew.xssl }}</td>
              <td>{{ vehScrew.xxsl }}</td>
            </tr>
            </tbody>
          </table>
        </div>

        <div class="col-sm-6">
          <h4 align="center">车牌固封螺丝申领异常表</h4>
          <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
              <th>申领网点</th>
              <th>号牌号码</th>
              <th>申领人身份证明号码</th>
              <th>申领次数</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="vehScrew in vehScrews">
              <td>{{ maps|optionMapKV(vehScrew.sysOrgCode) }}</td>
              <td>{{ vehScrew.hphm }}</td>
              <td>{{ decryptByDES(vehScrew.sfzmhm) }}</td>
              <td>{{ vehScrew.sumsl }}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div><!--form-group-->


    </div><!--modal-->

  </div>
</template>

<script>
import Times from "@/components/time";
export default {
  components: {Times},
  name: "wxbase-vehScrew",
  data: function() {
    return {
      vehScrew: {},
      vehScrewDto:{},
      vehScrews: [],
      vehScrewCount: [],
      maps: [],
      usersMap:[],
    }
  },
  mounted: function() {
    let _this = this;
    _this.list(1);
    _this.maps = Tool.getDeptUser() ;
    _this.usersMap = Tool.getUserCode();

  },
  methods: {

    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.vehScrewDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.vehScrewDto.etime = rep;
      _this.$forceUpdate();
    },

    /**
     * 列表查询
     */
    list() {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehScrew/vehScrewReport', _this.vehScrewDto ).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.vehScrews = resp.content.report1;
        _this.vehScrewCount = resp.content.report2;
      })
    },

//DES 解密
    decryptByDES(ciphertext) {
      if(ciphertext != undefined){
        let key = "11111111";
        let keyHex = CryptoJS.enc.Utf8.parse(key);
        let decrypted = CryptoJS.DES.decrypt({
          ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
        }, keyHex, {
          mode: CryptoJS.mode.ECB,
          padding: CryptoJS.pad.Pkcs7,
        });
        return decrypted.toString(CryptoJS.enc.Utf8);
      }
    },

  }
}
</script>