<template>
  <div style="height: 100%;">
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">设备查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:100%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  设备名称：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="waterEquipmentDto.sbmc"/>
                </td>
                <td style="width: 10%">
                  设备SN：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="waterEquipmentDto.sbsn"/>
                </td>
                <td style="width: 30%;" class="text-center">
                  <button  type="button" v-on:click="listEquipment(1)" class="btn btn-sm  btn-info btn-round" style="margin-right: 10px;">
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
      <table id="equipmentcommen-table-mult" class="table  table-bordered table-hover" v-if="isMultiple">
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
          <th>所属监测点</th>
          <th>所属数据中心</th>
          <th>设备类别</th>
          <th>设备状态</th>
        </tr>
        </thead>
        <tbody ref="tbodyEquipmentcommenMult">
        <tr v-for="waterEquipment in waterEquipments">
          <td class="center">
            <label class="pos-rel">
              <input type="checkbox" class="ace" :value="waterEquipment.sbsn">
              <span class="lbl"></span>
            </label>
          </td>
          <td>{{waterEquipment.sbmc}}</td>
          <td>{{waterEquipment.sbsn}}</td>
          <td>{{deptMap|optionMapKV(waterEquipment.deptcode)}}</td>
          <td>{{waterDatas|optionWDArray(waterEquipment.centerCode)}}</td>
          <td>{{sblbs|optionMapKV(waterEquipment.sblb)}}</td>
          <td><span v-if="waterEquipment.sbzt=='1'">正常</span><span v-if="waterEquipment.sbzt=='2'">离线</span><span v-if="waterEquipment.sbzt=='3'">设备故障</span></td>
        </tr>
        </tbody>
      </table>
      <table id="equipmentcommen-table" class="table  table-bordered table-hover" v-if="!isMultiple">
        <thead>
        <tr>
          <th>设备名称</th>
          <th>设备SN</th>
          <th>所属监测点</th>
          <th>所属数据中心</th>
          <th>设备类别</th>
          <th>设备状态</th>
        </tr>
        </thead>
        <tbody ref="tbodyEquipmentcommen">
        <tr v-for="waterEquipment in waterEquipments" v-bind:id="waterEquipment.sbsn">
          <td>{{waterEquipment.sbmc}}</td>
          <td>{{waterEquipment.sbsn}}</td>
          <td>{{deptMap|optionMapKV(waterEquipment.deptcode)}}</td>
          <td>{{waterDatas|optionWDArray(waterEquipment.centerCode)}}</td>
          <td>{{sblbs|optionMapKV(waterEquipment.sblb)}}</td>
          <td><span v-if="waterEquipment.sbzt=='1'">正常</span><span v-if="waterEquipment.sbzt=='2'">离线</span><span v-if="waterEquipment.sbzt=='3'">设备故障</span></td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="listEquipment" v-bind:itemCount="10"></pagination>
    </div>
    <div style="text-align: right;margin-top: 20px;">
      <button type="button" class="btn btn-default" data-dismiss="modal" style="margin-right: 10px;">取消</button>
      <button v-on:click="saveChoose" type="button" class="btn btn-primary">保存</button>
    </div>
  </div>
</template>
<script>
import Pagination from "@/components/pagination";

export default {
  name: 'equipment-commen',
  props: ["sbsncodes","isMultiple"],
  components: {Pagination},
  data: function (){
    return {
      waterEquipmentDto: {},
      waterEquipments:[],
      deptMap:[],
      waterDatas:[],
      sblbs:[],
      maxheight:'',
      activeClass:'active',
      chooseSbsncode:[]
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxheight = h*0.8-250;
    _this.$refs.pagination.size = 10;
    _this.chooseSbsncode = $.extend([], _this.sbsncodes);
    _this.$forceUpdate();
    if(_this.isMultiple){
      _this.initMultTable();
    }else{
      _this.initTabel();
    }
    _this.listEquipment(1);
    _this.findWaterData();
    _this.getSblb();
    _this.deptMap = Tool.getDeptUser();
  },
  methods: {
    /**
     * 获取设备型号
     */
    getSblb(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getSblb').then((res) => {
        let response = res.data;
        _this.sblbs = response.content;
        _this.$forceUpdate();
      })
    },
    /**
     * 查询数据中心
     */
    findWaterData(){
      let _this = this;
      let userInfo = Tool.getLoginUser();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/waterData/findAll/' + userInfo.deptcode).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          _this.waterDatas = resp.content;
          _this.$forceUpdate();
        }
      })
    },
    saveChoose(){
      let _this = this;
      if(_this.chooseSbsncode.length<=0){
        Toast.warning("请选择设备");
        return
      }
      _this.$emit('equipmentBack',_this.chooseSbsncode);
    },
    listEquipment(page){
      let _this = this;
      Loading.show();
      _this.waterEquipmentDto.page=page;
      _this.waterEquipmentDto.size=_this.$refs.pagination.size;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getAllEuqipment', _this.waterEquipmentDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.waterEquipments = resp.content.list;
        _this.$refs.pagination.render(page, resp.content.total);
        if(_this.isMultiple){
          _this.$nextTick(function (){
            _this.updateMultTable();
          })
        }else{
          _this.$nextTick(function (){
            _this.updateTable();
          })
        }
      })
    },
    resetEquipment(){
      let _this = this;
      _this.waterEquipmentDto = {};
      _this.$forceUpdate();
      _this.listEquipment(1);
    },
    /**
     * 初始化表格及点击事件
     */
    initMultTable(){
      let _this = this;
      $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
      //select/deselect all rows according to table header checkbox
      $('#equipmentcommen-table-mult input[type=checkbox]').eq(0).on('click', function(){
        let outThis = this;
        $('#equipmentcommen-table-mult').find('tbody > tr').each(function(){
          let inputKJ = this.firstElementChild.firstElementChild.firstElementChild;
          let id = inputKJ.defaultValue;
          if(outThis.checked){
            $(inputKJ).prop('checked', true);
            _this.chooseSbsncode.push(id);
          }else{
            $(inputKJ).prop('checked', false);
            _this.chooseSbsncode.splice(_this.chooseSbsncode.indexOf(id),1)
          }
        }).bind(outThis);
      });
      //select/deselect a row when the checkbox is checked/unchecked
      $('#equipmentcommen-table-mult').on('click', 'td input[type=checkbox]' , function(){
        let id = this.defaultValue;
        if(this.checked) {
          if(!_this.chooseSbsncode.includes(id)){
            _this.chooseSbsncode.push(id);
          }
        }else {
          if(_this.chooseSbsncode.includes(id)){
            _this.chooseSbsncode.splice(_this.chooseSbsncode.indexOf(id),1)
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
      if(childrens.length>0 && _this.chooseSbsncode.length>0){
        for(let i=0;i<childrens.length;i++){
          let trChild = childrens[i];
          if(_this.chooseSbsncode.includes(trChild.firstElementChild.firstElementChild.firstElementChild.defaultValue)){
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
    },
    initTabel(){
      let _this = this;
      $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
      //select/deselect a row when the checkbox is checked/unchecked
      $('#equipmentcommen-table').on('click', 'tr' , function(){
        let id = this.id;
        let childrens = _this.$refs.tbodyEquipmentcommen.children;
        if(childrens.length>0){
          for(let i=0;i<childrens.length;i++) {
            let trChild = childrens[i];
            if(trChild.id==id){
              $(trChild).addClass(_this.activeClass);
              _this.chooseSbsncode=[id];
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
      let childrens = _this.$refs.tbodyEquipmentcommen.children;
      if(childrens.length>0 && _this.chooseSbsncode.length>0){
        for(let i=0;i<childrens.length;i++){
          let trChild = childrens[i];
          if(_this.chooseSbsncode.includes(trChild.id)){
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