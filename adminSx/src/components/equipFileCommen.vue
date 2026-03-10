<template>
  <div style="height: 650px;">
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">声谱图查询</h4>
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
                  <select v-model="equipmentFileDto.sbbh" class="form-control" id="form-field-select-1">
                    <option value="" selected>全部</option>
                    <option v-for="item in waterEquipments" :value="item.key">{{item.value}}</option>
                  </select>
                </td>
                <td style="width: 30%;" class="text-center">
                  <button  type="button" v-on:click="listEquipmentFile(1)" class="btn btn-sm  btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <button   type="button" v-on:click="resetEquipmentFile()"   class="btn btn-sm   btn-success btn-round">
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
      <table id="equipfile-table-mult" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
            <label class="pos-rel">
              <input type="checkbox" class="ace" ref="selectAll">
              <span class="lbl"></span>
            </label>
          </th>
          <th>设备名称</th>
          <th>设备SN</th>
          <th>采集时间</th>
<!--          <th>图片</th>-->
        </tr>
        </thead>
        <tbody ref="tbodyEquipmentcommenMult">
        <tr v-for="equipmentFile in equipmentFiles">
          <td class="center">
            <label class="pos-rel">
              <input type="checkbox" class="ace" :value="equipmentFile.tplj">
              <span class="lbl"></span>
            </label>
          </td>
          <td>{{waterEquipments|optionKVArray(equipmentFile.sbbh)}}</td>
          <td>{{equipmentFile.sbbh}}</td>
          <td>{{equipmentFile.cjsj}}</td>
<!--          <td><img alt="无图片" :src="equipmentFile.tplj.replaceAll('txt','jpg')" style="width: 100px;height: 200px;cursor: pointer;"></td>-->
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="listEquipmentFile" v-bind:itemCount="10"></pagination>
    </div>
    <div style="text-align: right;margin-top: 20px;">
      <button v-on:click="saveChoose" type="button" class="btn btn-primary">保存</button>
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/pagination";

export default {
  name: 'equip-file-commen',
  props: ["uavFlyVideoId","cjsj","tpids"],
  components: {Pagination},
  data: function (){
    return {
      equipmentFileDto: {},
      waterEquipments:[{'key':'JSA4001','value':'君山农业局01'},{'key':'JSA4002','value':'君山农业局02'}],
      equipmentFiles:[],
      maxheight:'',
      activeClass:'active',
      chooseTpljs:[]
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxheight = h*0.8-250;
    _this.$refs.pagination.size = 10;
    _this.$forceUpdate();
    _this.initMultTable();
    _this.listEquipmentFile(1);
  },
  methods: {
    saveChoose(){
      let _this = this;
      if(_this.chooseTpljs.length<=0){
        Toast.warning("请选择声谱图");
        return
      }
      //保存选择的声谱图
      Loading.show();
      let uavFlyVideoDto={"id":_this.uavFlyVideoId,"tpids":_this.chooseTpljs.toString()};
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/uavFlyVideo/update', uavFlyVideoDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        if (resp.success) {
          //调用父组件方法
          this.$emit('choose-after', {index:'1',ids:_this.chooseTpljs.toString()});
        } else {
          Toast.warning(resp.message);
        }
      })
    },
    //查询表格声谱图数据
    listEquipmentFile(page){
      let _this = this;
      Loading.show();
      _this.equipmentFileDto.page=page;
      _this.equipmentFileDto.size=_this.$refs.pagination.size;
      _this.equipmentFileDto.etime=_this.cjsj;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/equipmentFileP/yueYangChoose', _this.equipmentFileDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.equipmentFiles = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
        _this.$nextTick(function (){
          _this.updateMultTable();
        })
      })
    },
    resetEquipmentFile(){
      let _this = this;
      _this.equipmentFileDto = {};
      _this.$forceUpdate();
      _this.listEquipmentFile(1);
    },
    /**
     * 初始化表格及点击事件
     */
    initMultTable(){
      let _this = this;
      $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
      //select/deselect all rows according to table header checkbox
      $('#equipfile-table-mult input[type=checkbox]').eq(0).on('click', function(){
        let outThis = this;
        $('#equipfile-table-mult').find('tbody > tr').each(function(){
          let inputKJ = this.firstElementChild.firstElementChild.firstElementChild;
          let id = inputKJ.defaultValue;
          if(outThis.checked){
            $(inputKJ).prop('checked', true);
            _this.chooseTpljs.push(id);
          }else{
            $(inputKJ).prop('checked', false);
            _this.chooseTpljs.splice(_this.chooseTpljs.indexOf(id),1)
          }
        }).bind(outThis);
      });
      //select/deselect a row when the checkbox is checked/unchecked
      $('#equipfile-table-mult').on('click', 'td input[type=checkbox]' , function(){
        let id = this.defaultValue;
        if(this.checked) {
          if(!_this.chooseTpljs.includes(id)){
            _this.chooseTpljs.push(id);
          }
        }else {
          if(_this.chooseTpljs.includes(id)){
            _this.chooseTpljs.splice(_this.chooseTpljs.indexOf(id),1)
          }
        }
      });
    },
    /**
     * 更新表格
     */
    updateMultTable(){
      let _this = this;
      //获取当前页面的数据
      let childrens = _this.$refs.tbodyEquipmentcommenMult.children;
      let count =0;//计数
      if(childrens.length>0 && _this.chooseTpljs.length>0){
        for(let i=0;i<childrens.length;i++){
          let trChild = childrens[i];
          if(_this.chooseTpljs.includes(trChild.firstElementChild.firstElementChild.firstElementChild.defaultValue)){
            count++;
            $(trChild.firstElementChild.firstElementChild.firstElementChild).prop('checked', true);
          }else{
            $(trChild.firstElementChild.firstElementChild.firstElementChild).prop('checked', false);
          }
          if(childrens.length==count){
            $(_this.$refs.selectAll).prop('checked', true)
          }else{
            $(_this.$refs.selectAll).prop('checked', false)
          }
        }
      }else{
        $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
      }
    }
  }
}
</script>