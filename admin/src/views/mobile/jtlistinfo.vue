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

                <template v-for="(list ,index)  in lists"  >
                <tr v-on:click="activetbale(index+'details');">
                  <td class="center">{{ index +1 }}</td>
                  <td>{{list.sbmc}}</td>
                  <td>{{list.sbbh}} </td>
                  <td class="center">
                    <div class="action-buttons">
                      <a href="#" class="green bigger-140 show-details-btn" :id="index+'details'"  title="Show Details">
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
                                  <span class="white">{{ list.sbmc }}</span>
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
                               <td >图片&音频</td>
                              </tr>

                              </thead>
                              <tbody>

                              <tr v-for="(info ,index)  in list.list"  @click="playwav(info.playDto,info.tplj,info.cjsj,list.sbbh)" >
                                <td>{{list.sbbh}}</td>
                                <td>{{info.cjsj }}</td>
                               <td >
                                  <div style="width: 100%;text-align: center" @click="playwav(info.playDto,info.tplj,info.cjsj,list.sbbh)">
                                    <i class=" ace-icon fa fa-sitemap light-green"></i>

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

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">信息查看</h4>
          </div>
          <div class="modal-body" style="text-align: center">
            <div class="col-xs-12 col-sm-2">
              <div class="text-center">
                <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                  <div class="inline position-relative">
                    <a class="user-title-label" href="#">
                      <i class="ace-icon fa fa-circle light-green"></i>
                      &nbsp;
                      <span class="white"> {{sbbh}}【 {{ cjsj}} 】</span>

                    </a>

                  </div>
                </div>
              </div>
            </div>
            <table class="table">
              <tr >
                <td style="text-align: left">
                  <div class="space-12"></div>
                  图片信息：</td>
                <td style="text-align: left">
                  <div class="space-12"></div>
                  <div  v-on:click="showpic()"   >
                    <img  :data-original="tplj"
                          style="width:100px;height: auto"  id="showpic"
                          :src="tplj">
                  </div>
                </td>
              </tr>

              <tr>
                <td style="text-align: left">
                  <div class="space-12"></div>
                  音频信息：</td>
                <td style="text-align: left">
                  <div class="space-12"></div>
                <aplayer :music="playDto"  :mini="true" ></aplayer>
                </td>

              </tr>

            </table>

          </div>
          <div class="modal-footer">

            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


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
      JYXM_DW:JYXM_DW,
      playDto:{"src":"http://"},//音频播放对象
      tplj:"",
      cjsj:"",
      sbbh:""
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
    //播放WV
    playwav(dto,tplj,cjsj,sbbh){
      let _this =this;
      $("#form-modal").modal("show");
      _this.playDto = dto;
      _this.tplj=tplj;
      _this.cjsj=cjsj;
      _this.sbbh=sbbh;
    },
    showpic(){
        let viewer = new Viewer(document.getElementById("showpic"), {
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
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/mobile/getthisDeptjxsjJT', {deptcode:_this.sm1}).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.lists = resp.content
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