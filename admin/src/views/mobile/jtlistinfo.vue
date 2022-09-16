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
                                <td>设备编号</td>
                                <td>检测时间</td>
                                <td>查看图片</td>
                                <td style="display: none">>音频</td>
                              </tr>

                              </thead>
                              <tbody>
                              <tr v-for="list in  lists.filter((x,y)=>{ return x.sbbh === set })" >
                                <td>
                                  {{set}}
                                </td>
                                <td>{{ list.cjsj }}</td>
                                <td style="text-align: center"  v-on:click="showpic(list.id)">

                                    <img  :data-original="list.tplj.substring(0,list.tplj.lastIndexOf('.')+1)+'jpg'"  style="width:10px;height: auto"  :id="list.id" :src="list.tplj.substring(0,list.tplj.lastIndexOf('.')+1)+'jpg'" :alt="list.cjsj">
                                </td>
                                <td style="display: none">
                                  <div style="width: 100%;text-align: center" >
                                    <aplayer :music="list.playDto"   :mini="true" ></aplayer>
                                  </div>
                                </td>
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
import Viewer from 'viewerjs';
import 'viewerjs/dist/viewer.css';
import Aplayer from 'vue-aplayer'
export default {
  components: {Aplayer},
  name: "jtlistinfo",
  data: function () {
    return {
      sm1:'',
      mc:'',
      lists:[], //数据
      sets:[], //业务
      JYXM_DW:JYXM_DW,
    }
  },
  mounted: function () {
    let _this =this;


    _this.sm1 = SessionStorage.get(MJTSM);
    if(Tool.isEmpty(_this.sm1)){
      _this.$router.push("/mobile/mindex");
    }
    _this.mc = SessionStorage.get(MJTMC);

    _this.getxxinfo();




  },
  methods: {
    showpic(id){
      let viewer = new Viewer(document.getElementById(id), {
        url: 'data-original',
        navbar:false,
        toolbar:{
          "zoomIn":1,
          "zoomOut":1,
          "oneToOne":1,
          "reset":1,
          "prev":0,
          "next":0,
          "play":0,
          "rotateLeft":1,
          "rotateRight":1,
          "flipHorizontal":0,
          "flipVertical":0,
        }

      });


    },
    getxxinfo(){
      let _this =this;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/mobile/getthisDeptjxsjJT', {deptcode:_this.sm1}).then((response)=>{
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