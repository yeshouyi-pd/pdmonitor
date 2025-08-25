<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">生物量数据查询</h4>
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
                  <input class="form-control" type="text"  v-model="sonarBiomassDto.deviceCode"/>
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
            <th>生物密度(个/m³)</th>
            <th>生物重量累加值(kg)</th>
            <th>生物重量或生物通量(kg或个/㎡·s)</th>
            <th>平均SV(dB)</th>
            <th>采集时间</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sonarBiomass in sonarBiomasss">
            <td>{{sonarBiomass.deviceCode}}</td>
            <td>{{sonarBiomass.biomassDensity}}</td>
            <td>{{sonarBiomass.biomassWeightAccumulated}}</td>
            <td>{{sonarBiomass.biomassWeight}}</td>
            <td>{{sonarBiomass.averageSv}}</td>
            <td>{{sonarBiomass.acTime}}</td>
          </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="10"></pagination>
    </div>

  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/times";
  export default {
    components: {Pagination,Times},
    name: "sonar-sonarBiomass",
    data: function() {
      return {
        sonarBiomassDto: {},
        sonarBiomass: {},
        sonarBiomasss: [],
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
        _this.sonarBiomassDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.sonarBiomassDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.sonarBiomassDto.page = page;
        _this.sonarBiomassDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/sonarBiomass/list', _this.sonarBiomassDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.sonarBiomasss = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      }
    }
  }
</script>