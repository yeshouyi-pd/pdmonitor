<template>
  <div class="main-content" style="overflow: auto">
    <div class="main-content-inner">
    <div class="space-1"></div>
    <div>
						<span class="label label-primary arrowed-in-right label-lg">
									<b>【{{mc}}】</b>
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
                <template v-for="(name,set,index)  in sets"  >
                <tr v-on:click="activetbale(set+'details');">
                  <td class="center">{{ index +1 }}</td>
                  <td>{{name}}</td>
                  <td>{{set}} </td>
                  <td class="center">
                    <div class="action-buttons">
                      <a href="#" class="green bigger-140 show-details-btn" :id="set+'details'"  title="Show Details">
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
                                  <span class="white">{{ name }}</span>
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
                              <tr v-for="list in  lists.filter((x,y)=>{ return x.ip === set })" >
                                <td>
                                  {{szjcx|optionMapKV(list.jcxm )}}
                                </td>
                                <td>
                                  <div v-show="list.dataResult">
                                    <b class="green">{{list.dataResult}}</b>{{JYXM_DW|optionKV(list.jcxm)}}
                                  </div>
                                </td>
                                <td>{{ list.createTime }}</td>
                              </tr>
                              </tbody>
                            </table>
                        </div>
                      </div>
                    </div>
                  </td>
                </tr>
                </template>

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
      mc:'',
      lists:[], //数据
      sets:[], //业务
      JYXM_DW:JYXM_DW,
      szjcx:[],

    }
  },
  mounted: function () {
    let _this =this;
    _this.sm1 = SessionStorage.get(MSHJSM);
    if(Tool.isEmpty(_this.sm1)){
      _this.$router.push("/mobile/mindex");
    }
    _this.mc = SessionStorage.get(MSHJMC);


    _this.getxxinfo();
    _this.getSzjcx();

  },
  methods: {

    /**
     * 获取水质检测项
     */
    getSzjcx(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getSzjcx', {
      }).then((response)=>{
        let resp = response.data;
        _this.szjcx = resp.content;
      })
    },

    getxxinfo(){
      let _this =this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/mobile/getthisDeptjxsj', {sm1:_this.sm1}).then((response)=>{
        let resp = response.data;
        let datas  =  resp.content
        _this.lists = datas.list;
         _this.sets  =datas.map;


       })

      },

    activetbale(id){
      let _this =this;
      $('[data-rel="tooltip"]').tooltip({placement: _this.tooltip_placement});
      /***************/
         $('#'+id).closest('tr').next().toggleClass('open');
         $('#'+id).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
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