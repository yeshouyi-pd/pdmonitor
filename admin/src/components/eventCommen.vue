<template>
  <div style="height: 650px;">
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">A4聚类查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width: 5%;">
                  设备名称：
                </td>
                <td style="width: 15%;">
                  <select v-model="equipmentFileEventDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>全部</option>
                    <option v-for="item in waterEquipments" :value="item.key">{{item.value}}</option>
                  </select>
                </td>
                <td style="width: 30%;" class="text-center">
                  <button  type="button" v-on:click="listEvent(1)" class="btn btn-sm  btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <button   type="button" v-on:click="resetEquipment()"   class="btn btn-sm   btn-success btn-round">
                    <i class="ace-icon fa fa-refresh"></i>
                    重置
                  </button>
                </td>
              </tr>
              </tbody>
            </table>
          </form>
        </div>
      </div>
    </div>
    <div :style="{height:maxheight+'px',overflowY:'auto',overflowX:'hidden'}">
      <table id="eventCommen-table" class="table  table-bordered table-hover" >
        <thead>
        <tr>
          <th>设备名称</th>
          <th>设备SN</th>
          <th>开始时间</th>
          <th>结束时间</th>
          <th>头数</th>
        </tr>
        </thead>
        <tbody ref="tbodyEventCommen">
        <tr v-for="item in equipmentFileEvents" v-bind:id="item.id">
          <td>{{waterEquipments|optionKVArray(item.sbbh)}}</td>
          <td>{{item.sbbh}}</td>
          <td>{{item.kssj}}</td>
          <td>{{item.jssj}}</td>
          <td>{{item.ts}}</td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="listEvent" v-bind:itemCount="10"></pagination>
    </div>
    <div style="text-align: right;margin-top: 20px;">
      <button v-on:click="saveChoose" type="button" class="btn btn-primary">保存</button>
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/pagination";

export default {
  name: 'event-commen',
  props: ["uavFlyVideoId","cjsj","jlid"],
  components: {Pagination},
  data: function (){
    return {
      equipmentFileEventDto: {},
      waterEquipments:[{'key':'JSA4001','value':'君山农业局01'},{'key':'JSA4002','value':'君山农业局02'}],
      equipmentFileEvents:[],
      idJtnrMap:new Map(),
      maxheight:'',
      activeClass:'active',
      chooseEventId:[]
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxheight = h*0.8-250;
    _this.$refs.pagination.size = 10;
    _this.$forceUpdate();
    _this.initTabel();
    _this.listEvent(1);
  },
  methods: {
    saveChoose(){
      let _this = this;
      if(_this.chooseEventId.length<=0){
        Toast.warning("请选择聚类事件");
        return
      }
      Loading.show();
      let uavFlyVideoDto={"id":_this.uavFlyVideoId,"jlid":_this.chooseEventId.toString()};
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/uavFlyVideo/update', uavFlyVideoDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          //调用父组件方法
          this.$emit('choose-after', {index:'2',ids:_this.chooseEventId.toString()});
        } else {
          Toast.warning(resp.message);
        }
      })
    },
    listEvent(page){
      let _this = this;
      Loading.show();
      _this.equipmentFileEventDto.page=page;
      _this.equipmentFileEventDto.size=_this.$refs.pagination.size;
      _this.equipmentFileEventDto.etime=_this.cjsj;
      _this.$forceUpdate();
      _this.idJtnrMap = new Map();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/yueYangChooseEvent', _this.equipmentFileEventDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFileEvents = resp.content.list;
        for(let i=0;i<this.equipmentFileEvents.length;i++){
          let obj = this.equipmentFileEvents[i];
          _this.idJtnrMap.set(obj.id+"",obj.jtnr);
        }
        _this.$refs.pagination.render(page, resp.content.total);
        _this.$nextTick(function (){
          _this.updateTable();
        })
      })
    },
    resetEquipment(){
      let _this = this;
      _this.equipmentFileEventDto = {};
      _this.$forceUpdate();
      _this.listEvent(1);
    },
    /**
     * 初始化表格及点击事件
     */
    initTabel(){
      let _this = this;
      $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
      //select/deselect a row when the checkbox is checked/unchecked
      $('#eventCommen-table').on('click', 'tr' , function(){
        let id = this.id;
        let childrens = _this.$refs.tbodyEventCommen.children;
        if(childrens.length>0){
          for(let i=0;i<childrens.length;i++) {
            let trChild = childrens[i];
            if(trChild.id==id){
              $(trChild).addClass(_this.activeClass);
              _this.chooseEventId=[_this.idJtnrMap.get(id)];
            }else{
              $(trChild).removeClass(_this.activeClass);
            }
          }
        }
      });
    },
    /**
     * 更新表格
     */
    updateTable(){
      let _this = this;
      //获取当前页面的数据
      let childrens = _this.$refs.tbodyEventCommen.children;
      if(childrens.length>0 && _this.chooseEventId.length>0){
        for(let i=0;i<childrens.length;i++){
          let trChild = childrens[i];
          if(_this.chooseEventId.includes(trChild.id)){
            $(trChild).addClass(_this.activeClass);
          }else{
            $(trChild).removeClass(_this.activeClass);
          }
        }
      }else{
        $('tr').removeClass(_this.activeClass);
      }
    }
  }
}
</script>