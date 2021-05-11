<template>
  <div class="main-content" style="overflow: auto">
    <div class="main-content-inner">
    <div class="space-1"></div>
    <div>
						<span class="label label-primary arrowed-in-right label-lg">
									<b>水环境监测数据</b>
						</span>
    </div>
      <div class="space-2"></div>

      <div class="row">
        <div class="col-xs-12">
          <!-- PAGE CONTENT BEGINS -->
          <div class="row">
            <div class="col-xs-12">
              <table id="simple-table" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                  <th class="center">
                    序号
                  </th>
                  <th>设备名称</th>
                  <th>设备编号</th>
                  <th class="detail-col">详情</th>
                </tr>
                </thead>

                <tbody>
                <tr>
                  <td class="center">1</td>
                  <td>老湾豚类基地流速和深度设备</td>
                  <td>888888</td>
                  <td class="center">
                    <div class="action-buttons">
                      <a href="#" class="green bigger-140 show-details-btn" title="Show Details">
                        <i class="ace-icon fa fa-angle-double-down"></i>
                        <span class="sr-only">详情</span>
                      </a>
                    </div>
                  </td>

                </tr>
                <tr class="detail-row">
                  <td colspan="4">
                    <div class="table-detail">
                      <div class="row">
                        <div class="col-xs-12 col-sm-2">
                          <div class="text-center">
                            <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                              <div class="inline position-relative">
                                <a class="user-title-label" href="#">
                                  <i class="ace-icon fa fa-circle light-green"></i>
                                  &nbsp;
                                  <span class="white">老湾豚类基地流速和深度设备</span>
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>

                        <div class="col-xs-12 col-sm-12">
                          <div class="space visible-xs"></div>

                            <table  class="table table-striped table-bordered table-hover">
                              <thead>
                              <tr>
                                <td>检测项目</td>
                                <td>监测结果</td>
                                <td>检测时间</td>
                              </tr>

                              </thead>
                              <tbody>
                              <tr>
                                <td>waterVelocity</td>
                                <td>0.3591608</td>
                                <td>2021-05-09 20:00:40</td>
                              </tr>
                              <tr>
                                <td>waterVelocity</td>
                                <td>0.3591608</td>
                                <td>2021-05-09 20:00:40</td>
                              </tr>
                              </tbody>
                            </table>


                        </div>


                      </div>
                    </div>
                  </td>
                </tr>


                <tr>
                  <td class="center">1</td>
                  <td>老湾豚类基地流速和深度设备</td>
                  <td>888888</td>
                  <td class="center">
                    <div class="action-buttons">
                      <a href="#" class="green bigger-140 show-details-btn" title="Show Details">
                        <i class="ace-icon fa fa-angle-double-down"></i>
                        <span class="sr-only">详情</span>
                      </a>
                    </div>
                  </td>

                </tr>
                <tr class="detail-row">
                  <td colspan="4">
                    <div class="table-detail">
                      <div class="row">
                        <div class="col-xs-12 col-sm-2">
                          <div class="text-center">
                            <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                              <div class="inline position-relative">
                                <a class="user-title-label" href="#">
                                  <i class="ace-icon fa fa-circle light-green"></i>
                                  &nbsp;
                                  <span class="white">老湾豚类基地流速和深度设备</span>
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>

                        <div class="col-xs-12 col-sm-12">
                          <div class="space visible-xs"></div>

                          <table  class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                              <td>检测项目</td>
                              <td>监测结果</td>
                              <td>检测时间</td>
                            </tr>

                            </thead>
                            <tbody>
                            <tr>
                              <td>waterVelocity</td>
                              <td>0.3591608</td>
                              <td>2021-05-09 20:00:40</td>
                            </tr>
                            <tr>
                              <td>waterVelocity</td>
                              <td>0.3591608</td>
                              <td>2021-05-09 20:00:40</td>
                            </tr>
                            </tbody>
                          </table>


                        </div>


                      </div>
                    </div>
                  </td>
                </tr>
                </tbody>
              </table>



            </div><!-- /.span -->
          </div><!-- /.row -->
        </div>
      </div>


    </div>
  </div>

</template>

<script>
export default {
  name: "shjlistinfo",
  data: function () {
    return {
      sm1:'',

    }
  },
  mounted: function () {
    let _this =this;
    _this.activetbale();
    _this.sm1 = SessionStorage.get(MSHJSM);
    _this.getxxinfo();


  },
  methods: {

    getxxinfo(){
      let _this =this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/mobile/getthisDeptjxsj', {sm1:_this.sm1}).then((response)=>{
        let resp = response.data;
        let datas  =  resp.content
        console.log("========"+datas);
       })

      },

    activetbale(){
      let _this =this;
      $('[data-rel="tooltip"]').tooltip({placement: _this.tooltip_placement});
      /***************/
      $('.show-details-btn').on('click', function(e) {
        e.preventDefault();
        $(this).closest('tr').next().toggleClass('open');
        $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
      });
      /***************/

    },
    tooltip_placement(context, source){
      let $source = $(source);
      let $parent = $source.closest('table')
      let off1 = $parent.offset();
      let w1 = $parent.width();
      let off2 = $source.offset();
      if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
      return 'left';


    }








  }
}
</script>

<style scoped>

</style>