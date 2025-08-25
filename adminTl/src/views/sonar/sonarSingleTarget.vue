<template>
  <div>
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">单体目标信息查询</h4>
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
                  <input class="form-control" type="text"  v-model="sonarSingleTargetDto.deviceCode"/>
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
            <th>单体数量</th>
            <th>目标强度</th>
            <th>目标距离换能器距离</th>
<!--            <th>目标出现时间</th>-->
            <th>生物类别代码</th>
          </tr>
        </thead>
        <tbody ref="targetTbody" id="targetTbody"></tbody>
      </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/times";
  export default {
    components: {Pagination,Times},
    name: "sonar-sonarSingleTarget",
    data: function() {
      return {
        sonarSingleTargetDto: {},
        sonarSingleTarget: {},
        sonarSingleTargets: [],
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
        _this.sonarSingleTargetDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.sonarSingleTargetDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.sonarSingleTargetDto.page = page;
        _this.sonarSingleTargetDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/sonarSingleTarget/list', _this.sonarSingleTargetDto).then((response)=>{
          Loading.hide();
          $("#targetTbody").empty();
          let resp = response.data;
          _this.sonarSingleTargets = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
          for(let i=0;i<_this.sonarSingleTargets.length;i++){
            let sonarSingleTarget = _this.sonarSingleTargets[i];
            for(let j=0;j<sonarSingleTarget.sonarSingleValueList.length;j++){
              let sonarSingleValue = sonarSingleTarget.sonarSingleValueList[j];
              let tr = document.createElement("tr");
              if(j==0){
                let td1 = document.createElement("td");
                td1.innerText=sonarSingleTarget.deviceCode;
                let td2 = document.createElement("td");
                td2.innerText=sonarSingleTarget.acTime;
                let td3 = document.createElement("td");
                td3.innerText=sonarSingleTarget.num;
                tr.append(td1);
                tr.append(td2);
                tr.append(td3);
                td1.rowSpan = sonarSingleTarget.sonarSingleValueList.length;
                td2.rowSpan = sonarSingleTarget.sonarSingleValueList.length;
                td3.rowSpan = sonarSingleTarget.sonarSingleValueList.length;
              }
              let td4 = document.createElement("td");
              td4.innerText=sonarSingleValue.dTs;
              let td5 = document.createElement("td");
              td5.innerText=sonarSingleValue.dRange;
              // let td6 = document.createElement("td");
              // td6.innerText=sonarSingleValue.lTime;
              let td7 = document.createElement("td");
              td7.innerText=_this.getBioTypeName(sonarSingleValue.nBioTypeCode);
              tr.append(td4);
              tr.append(td5);
              // tr.append(td6);
              tr.append(td7);
              _this.$refs.targetTbody.append(tr);
            }
          }
        })
      },
      getBioTypeName(nBioTypeCode){
        if(nBioTypeCode=='1701'){
          return "毛虾";
        }else if(nBioTypeCode=='1702'){
          return "水母";
        }else if(nBioTypeCode=='1703'){
          return "鱼群";
        }else if(nBioTypeCode=='1704'){
          return "藻类";
        }else if(nBioTypeCode=='1705'){
          return "其他";
        }
      }
    }
  }
</script>