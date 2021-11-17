<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">预警文件分析上传</h4>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;" class="text-right  " >
                  <tbody  >

                  <tr style="height: 10px;"></tr>
                  <tr>
                    <td  style="width: 10%">
                      项目名称：
                    </td>
                    <td style="width: 20%">
                      <input class="form-control" type="text"  v-model="xmmc"/>
                    </td>
                    <td style="width: 40%">
                      <button  type="button" v-on:click="list()" class="btn btn-sm  btn-info btn-round">
                        <i class="ace-icon fa fa-book"></i>
                        查询
                      </button>
                      &nbsp;&nbsp;&nbsp;
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
        <!-- query end -->
      </div><!-- col-md-12 -->
    </div><!-- row -->

    <div class="modal-body">
      <div class="row">
        <!-- 选择 项目部分 -->
        <div class="col-md-7">
          <div class="widget-main">
            <!-- 选择DIV -->
            <div class="content container-fluid">
              <div class="row">
                <div style="display: flex;flex-direction: row;flex-wrap: wrap;">
                  <button style="margin: 20px;border-radius: 30px;"  class="btn-info" :class="active == index ? 'bodycheck' : 'bodyontcheck'" v-for="( item ,index) in waterProUser" >
                    <div style="display: flex;flex-direction: column;padding: 10px;"   v-on:click="changeTab(index,item)" >
                      <img  style="width: 100px;" src="/static/favicon.png"/>
                      <span style="margin-top: 5px;font-size: 14px;">{{xmbhMap|optionMapKV(item.xmbh)}}</span>
                    </div>
                  </button>
                </div>

              </div>
            </div>

          </div>
        </div>
        <!-- 选择 设备 -->
        <div class="col-md-5">
          <table id="simple-table"   class="table table-striped table-bordered table-hover">
            <thead >
            <tr >
              <td   style="text-align: center;font-size: 15px;font-weight: bold;color: #0B61A4" colspan="4"> 项目名称：{{xmbhMap|optionMapKV(checkxmbh)}}</td>
            </tr>
            <tr>
              <th style="width: 5%;">序号</th>
              <th style="width: 10%;">设备名称</th>
              <th style="width: 10%;">设备编号</th>
              <th style="width: 10%;">操作</th>
            </tr>
            </thead>
            <tbody>
           <tr v-for="(equip,index) in waterProEquip">
              <td>{{index +1}}</td>
             <td>{{xmmcMap|optionMapKV(equip.sbsn)}}</td>
             <td>{{equip.sbsn}}</td>
             <td >
               <div class="hidden-sm hidden-xs btn-group">
                 <button  type="button" v-on:click="list()" class="btn btn-sm  btn-success btn-round">
                   <i class="ace-icon fa fa-book"></i>
                   查询
                 </button>
                 &nbsp;&nbsp;&nbsp;&nbsp;
                 <button  type="button" v-on:click="uploadinfo(checkxmbh,equip.sbsn)" class="btn btn-sm  btn-primary btn-round">
                   <i class="ace-icon fa fa-upload"></i>
                   上传
                 </button>
               </div>
             </td>

            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

  </div>
</template>
<script>
export default {
  name: "fileuploadinfo",
  data: function (){
    return {
      active:-1,
      xmmc:'',
      waterProUser:[],
      xmbhMap:{},
      xmmcMap:{},
      waterProEquip:[],
      checkxmmcMap:[],
      checkxmbh:[],

    }
  },
  mounted() {
    let _this = this;
    _this.queryallEquip();

  },
  methods: {
    uploadinfo(xmbh,sbsn){
      let _this = this;
      alert(xmbh+"===="+sbsn)

    },
    changeTab(index,item){
      let _this = this;
      _this.active = index;
      Loading.show();
      _this.checkxmbh = item.xmbh;
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/file/queryEquip', {xmbh:item.xmbh}).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.waterProEquip = resp.content;
      })
    },
    list(){
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/file/queryProject', {xmmc:_this.xmmc}).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.waterProUser = resp.content;
      })
    },
    queryallProject(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/admin/file/queryallProject').then((response) => {
        let resp = response.data;
        _this.xmbhMap = resp.content;
        _this.$forceUpdate();
        _this.list();
      })
    },

    queryallEquip(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/monitor/admin/file/queryallEquip',).then((response) => {
        let resp = response.data;
        _this.xmmcMap = resp.content;
        _this.$forceUpdate();
        _this.queryallProject();
      })
    },


  }
}
</script>
<style scoped>
.bodycheck{
  border: 1px solid red;
}
.bodyontcheck{
  border: 0px solid ;
}
</style>