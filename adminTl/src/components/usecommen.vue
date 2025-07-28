<template>
  <div style="height: 100%;">
    <div class="widget-box">
      <div class="widget-header">
        <h4 class="widget-title">用户查询</h4>
      </div>
      <div class="widget-body">
        <div class="widget-main">
          <form>
            <table style="font-size: 1.1em;width:80%" class="text-right">
              <tbody>
              <tr>
                <td style="width:10%">
                  账号：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="userDto.loginName"/>
                </td>
                <td style="width: 10%">
                  姓名：
                </td>
                <td style="width: 15%">
                  <input class="form-control" type="text"  v-model="userDto.name"/>
                </td>
                <td colspan="2" class="text-center">
                  <button  type="button" v-on:click="listUser(1)" class="btn btn-sm  btn-info btn-round" style="margin-right: 10px;">
                    <i class="ace-icon fa fa-book"></i>
                    查询
                  </button>
                  <button   type="button" v-on:click="resetUser()"   class="btn btn-sm   btn-success btn-round">
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
      <table id="usercommen-table-mult" class="table  table-bordered table-hover" v-if="isMultiple">
        <thead>
        <tr>
          <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
            <label class="pos-rel">
              <input type="checkbox" class="ace" ref="selectAll">
              <span class="lbl"></span>
            </label>
          </th>
          <th>账号</th>
          <th>姓名</th>
          <th>部门</th>
        </tr>
        </thead>
        <tbody  ref="tbodyUsercommenMult">
        <tr v-for="user in users">
          <td class="center">
            <label class="pos-rel">
              <input type="checkbox" class="ace" :value="user.loginName">
              <span class="lbl"></span>
            </label>
          </td>
          <td>{{user.loginName}}</td>
          <td>{{user.name}}</td>
          <td>
            <template  v-for="deptc in alldept.filter(t=>{return t.deptcode===user.deptcode})" >
              {{deptc.deptname}}
            </template>
          </td>
        </tr>
        </tbody>
      </table>
      <table id="usercommen-table" class="table  table-bordered table-hover" v-if="!isMultiple">
        <thead>
        <tr>
          <th>账号</th>
          <th>姓名</th>
          <th>部门</th>
        </tr>
        </thead>
        <tbody ref="tbodyUsercommen">
        <tr v-for="user in users" v-bind:id="user.loginName">
          <td>{{user.loginName}}</td>
          <td>{{user.name}}</td>
          <td>
            <template  v-for="deptc in alldept.filter(t=>{return t.deptcode===user.deptcode})" >
              {{deptc.deptname}}
            </template>
          </td>
        </tr>
        </tbody>
      </table>
      <pagination ref="pagination" v-bind:list="listUser" v-bind:itemCount="10"></pagination>
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
  name: "user-commen",
  props:["usercodes","isMultiple"],
  components: {Pagination},
  data: function (){
    return {
      userDto:{},
      users:[],
      alldept:[],
      maxheight:'',
      activeClass:'active',
      chooseUsercode:[]
    }
  },
  mounted() {
    let _this = this;
    let h = document.documentElement.clientHeight || document.body.clientHeight;
    _this.maxheight = h*0.8-250;
    _this.$refs.pagination.size = 10;
    _this.chooseUsercode = $.extend([], _this.usercodes);
    _this.$forceUpdate();
    if(_this.isMultiple){
      _this.initMultTable();
    }else{
      _this.initTabel();
    }
    _this.listUser(1);
    _this.getAllDept();
  },
  methods: {
    saveChoose(){
      let _this = this;
      if(_this.chooseUsercode.length<=0){
        Toast.warning("请选择人员");
        return
      }
      _this.$emit('userBack',_this.chooseUsercode);

    },
    listUser(page){
      let _this = this;
      Loading.show();
      _this.userDto.page=page;
      _this.userDto.size=_this.$refs.pagination.size;
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getAllUser', _this.userDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.users = resp.content.list;
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
    resetUser(){
      let _this = this;
      _this.userDto = {};
      _this.$forceUpdate();
      _this.listUser(1);
    },
    /**
     *加载所有部门
     */
    getAllDept() {
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/CodeSetUtil/getAllDept').then((response)=>{
        let resp = response.data;
        _this.alldept = resp.content;
        _this.$forceUpdate();
      })
    },
    /**
     * 初始化表格及点击事件
     */
    initMultTable(){
      let _this = this;
      $('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);
      //select/deselect all rows according to table header checkbox
      $('#usercommen-table-mult input[type=checkbox]').eq(0).on('click', function(){
        let outThis = this;
        $('#usercommen-table-mult').find('tbody > tr').each(function(){
          let inputKJ = this.firstElementChild.firstElementChild.firstElementChild;
          let id = inputKJ.defaultValue;
          if(outThis.checked){
            $(inputKJ).prop('checked', true);
            _this.chooseUsercode.push(id);
          }else{
            $(inputKJ).prop('checked', false);
            _this.chooseUsercode.splice(_this.chooseUsercode.indexOf(id),1)
          }
        }).bind(outThis);
      });
      //select/deselect a row when the checkbox is checked/unchecked
      $('#usercommen-table-mult').on('click', 'td input[type=checkbox]' , function(){
        let id = this.defaultValue;
        if(this.checked) {
          if(!_this.chooseUsercode.includes(id)){
            _this.chooseUsercode.push(id);
          }
        }else {
          if(_this.chooseUsercode.includes(id)){
            _this.chooseUsercode.splice(_this.chooseUsercode.indexOf(id),1)
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
      let childrens = _this.$refs.tbodyUsercommenMult.children;
      let count =0;//计数
      if(childrens.length>0 && _this.chooseUsercode.length>0){
        for(let i=0;i<childrens.length;i++){
          let trChild = childrens[i];
          if(_this.chooseUsercode.includes(trChild.firstElementChild.firstElementChild.firstElementChild.defaultValue)){
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
      $('#usercommen-table').on('click', 'tr' , function(){
        let id = this.id;
        let childrens = _this.$refs.tbodyUsercommen.children;
        if(childrens.length>0){
          for(let i=0;i<childrens.length;i++) {
            let trChild = childrens[i];
            if(trChild.id==id){
              $(trChild).addClass(_this.activeClass);
              _this.chooseUsercode=[id];
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
      let childrens = _this.$refs.tbodyUsercommen.children;
      if(childrens.length>0 && _this.chooseUsercode.length>0){
        for(let i=0;i<childrens.length;i++){
          let trChild = childrens[i];
          if(_this.chooseUsercode.includes(trChild.id)){
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