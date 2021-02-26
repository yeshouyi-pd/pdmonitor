<template>
  <div>
      <div class="row">
          <div class="col-sm-12 ">
              <div class="widget-box transparent">
                  <div class="widget-header widget-header-large">
                      <h3 class="widget-title grey lighter">
                          <i class="ace-icon fa fa-leaf green"></i>
                          部门配置信息
                      </h3>
                  </div>
                  <div class="widget-body">
                      <div class="widget-main padding-24">
                          <div class="row">
                              <div class="col-sm-3">
                                  <div class="row">
                                      <div class="col-xs-11 label label-lg label-info arrowed-in arrowed-right">
                                          <b>部门信息</b>
                                      </div>
                                  </div>

                                  <div>
                                      <ul class="list-unstyled spaced">
                                          <li>
                                              <i class="ace-icon fa fa-caret-right blue"></i>部门名称：<b class="red">{{dept.deptname}}</b>
                                          </li>

                                          <li>
                                              <i class="ace-icon fa fa-caret-right blue"></i>部门编号：{{dept.deptcode}}
                                          </li>

                                          <li>
                                              <i class="ace-icon fa fa-caret-right blue"></i>联系地址：{{dept.linkadd}}
                                          </li>

                                          <li v-show="dept.linkman">
                                              <i class="ace-icon fa fa-caret-right blue"></i>
                                              联系人：{{dept.linkman}}
                                          </li>
                                      </ul>
                                  </div>
                              </div><!-- /.col -->

                              <div class="col-sm-2">
                                  <div class="row">
                                      <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
                                          <b>机动车业务</b>
                                      </div>
                                  </div>

                                  <div>
                                      <ul class="list-unstyled  spaced">
                                          <li v-for="deptYwlx in deptYwlxsall" v-show="deptYwlx.ywfl==='2'">
                                              <i class="ace-icon fa fa-caret-right green"></i>
                                                  {{checkjdcywVal | optionMapKV(deptYwlx.ywlx ) }}
                                          </li>


                                      </ul>
                                  </div>
                              </div><!-- /.col -->
                              <div class="col-sm-2">
                                  <div class="row">
                                      <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
                                          <b>驾驶人业务</b>
                                      </div>
                                  </div>

                                  <div>
                                      <ul class="list-unstyled  spaced">
                                          <li v-for="deptYwlx in deptYwlxsall" v-show="deptYwlx.ywfl==='3'">
                                              <i class="ace-icon fa fa-caret-right green"></i>
                                              {{checkjsrywVal | optionMapKV(deptYwlx.ywlx ) }}
                                          </li>
                                      </ul>
                                  </div>
                              </div><!-- /.col -->
                              <div class="col-sm-2">
                                  <div class="row">
                                      <div class="col-xs-11 label label-lg label-success arrowed-in arrowed-right">
                                          <b>违法业务</b>
                                      </div>
                                  </div>
                                  <div>
                                      <ul class="list-unstyled  spaced">
                                          <li v-for="deptYwlx in deptYwlxsall" v-show="deptYwlx.ywfl==='5'">
                                              <i class="ace-icon fa fa-caret-right green"></i>
                                              {{checkfwywVal | optionMapKV(deptYwlx.ywlx ) }}
                                          </li>
                                      </ul>
                                  </div>
                              </div><!-- /.col -->
                              <div class="col-sm-3">
                                  <div class="row">
                                      <div class="col-xs-11 label label-lg label-info arrowed-in arrowed-right">
                                          <b>预约时段</b>
                                      </div>
                                  </div>
                                  <div>
                                      <ul class="list-unstyled  spaced" >

                                          <li v-for="yysd in deptYysjs">
                                              <i class="ace-icon fa fa-caret-right green"></i>
                                                   {{yysd.stime}}-{{yysd.etime}}&nbsp;&nbsp;&nbsp;<b class="red">【{{yysd.yymun}}】</b>
                                              <i class="ace-icon fa fa-edit green" v-on:click="updateyysf(yysd)" ></i>
                                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                              <i class="ace-icon fa fa-trash-o red" v-on:click="deleteyysf(yysd.id)"></i>
                                          </li>
                                      </ul>
                                  </div>
                              </div><!-- /.col -->
                          </div><!-- /.row -->
                      </div>
                  </div>
              </div>
          </div>
      </div>
      <div>
          <p>
              <button v-on:click="addbeforyytime()" class="btn  btn-info btn-round">
                  <i class="ace-icon fa fa-edit"></i>
                   预约时段配置
              </button>
              &nbsp;
              <button v-on:click="add()" class="btn  btn-info btn-round">
                  <i class="ace-icon fa fa-edit"></i>
                  业务流程配置(批量)
              </button>
              &nbsp;&nbsp;
              <button v-on:click="list(1)" class="btn  btn-info btn-round">
                  <i class="ace-icon fa fa-refresh"></i>
                  刷新
              </button>
              &nbsp;
              <button v-on:click="getprant()" class="btn  btn-danger btn-round">
                  <i class="ace-icon fa fa-refresh"></i>
                  返回
              </button>
          </p>

          <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
            <th style="width: 15%">办事流程</th>
            <th style="width: 15%">所需资料</th>
            <th>业务分类</th>
            <th>业务类型</th>
            <th>流程图一</th>
            <th>流程图二</th>
            <th>流程图三</th>
            <th>流程图四</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="deptYwlx in deptYwlxs">
          <td>
              {{deptYwlx.bslc}}
         </td>
          <td>
              {{deptYwlx.sxzl}}
          </td>
              <td>
                  {{allVal | optionMapKV(deptYwlx.ywfl) }}
                 </td>

              <td>
                  <template v-if="deptYwlx.ywfl ==='2'">
                      {{checkjdcywVal | optionMapKV(deptYwlx.ywlx) }}
                  </template>
                  <template v-if="deptYwlx.ywfl ==='3'">
                      {{checkjsrywVal | optionMapKV(deptYwlx.ywlx) }}
                  </template>
                  <template v-if="deptYwlx.ywfl ==='5'">
                      {{checkfwywVal | optionMapKV(deptYwlx.ywlx) }}
                  </template>
              </td>
              <td style="width: 100px">
                  <div v-show="deptYwlx.lcto"  >
                      <img  v-bind:src="path+deptYwlx.lcto" style="width: 100px" class="img-responsive">
                  </div>

              </td>
              <td style="width: 100px">
                  <div v-show="deptYwlx.lctt"  >
                      <img  v-bind:src="path+deptYwlx.lctt" style="width: 100px" class="img-responsive">
                  </div>

              </td>
              <td style="width: 100px">
                  <div v-show="deptYwlx.lcth"  >
                      <img  v-bind:src="path+deptYwlx.lcth" style="width: 100px" class="img-responsive">
                  </div>
                 </td>
              <td style="width: 100px">
                  <div v-show="deptYwlx.lctf"  >
                      <img  v-bind:src="path+deptYwlx.lctf" style="width: 100px" class="img-responsive">
                  </div>
              </td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(deptYwlx)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(deptYwlx.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
          <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
      </div>
    <div id="edit-form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width: 70%" >
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h5 class="modal-title">业务配置【{{dept.deptname}}】
                     <b class="red">{{allVal | optionMapKV(deptYwlx.ywfl) }}</b>
                      <template v-if="deptYwlx.ywfl ==='2'">
                          <b class="red"> 【{{checkjdcywVal | optionMapKV(deptYwlx.ywlx) }}】</b>
                      </template>
                      <template v-if="deptYwlx.ywfl ==='3'">
                          <b class="red">【{{checkjsrywVal | optionMapKV(deptYwlx.ywlx) }}】</b>
                      </template>
                      <template v-if="deptYwlx.ywfl ==='5'">
                          <b class="red">【{{checkfwywVal | optionMapKV(deptYwlx.ywlx) }}】</b>
                      </template>
                  </h5>
              </div>
              <div class="modal-body">
                  <form class="form-horizontal">
                      <div class="form-group">
                          <label class="col-sm-2 control-label">办事流程</label>
                          <div class="col-sm-10 ">
                              <textarea  style="height: 80px" v-model="deptYwlx.bslc"  class="autosize-transition form-control" placeholder="请输入当前业务详细办事流程"></textarea>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-2 control-label">所需资料</label>
                          <div class="col-sm-10 ">
                              <textarea  style="height: 80px" v-model="deptYwlx.sxzl"  class="autosize-transition form-control" placeholder="请输入当前业务所需要的资料"></textarea>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-1 col-sm-offset-1  control-label">流程图一</label>
                          <div class="col-sm-4">
                              <div class="row">
                                  <div class="col-sm-8">
                                      <div v-show="deptYwlx.lcto" class="row" >
                                          <img  v-bind:src="path+deptYwlx.lcto" style="height: 100px" class="img-responsive">
                                      </div>
                                  </div>
                                  <div class="col-sm-4" >
                                      <file  v-bind:after-upload="afterUploadone"
                                             v-bind:text="'流程图一'"
                                             v-bind:use="'ywlct'"
                                             v-bind:input-id="'imageone-upload'"
                                             v-bind:suffixs="['jpg','jpeg','png']"></file>
                                  </div>
                              </div>
                          </div>

                          <label class="col-sm-1 control-label">流程图二</label>
                          <div class="col-sm-5">
                              <div class="row">
                                  <div class="col-sm-8">
                                      <div v-show="deptYwlx.lctt" class="row" >
                                          <img  v-bind:src="path+deptYwlx.lctt" style="height: 100px" class="img-responsive">
                                      </div>
                                  </div>
                                  <div class="col-sm-4" >
                                      <file  v-bind:after-upload="afterUploadtwo"
                                             v-bind:text="'流程图二'"
                                             v-bind:use="'ywlct'"
                                             v-bind:input-id="'imagetwo-upload'"
                                             v-bind:suffixs="['jpg','jpeg','png']"></file>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="form-group" style="margin-top: 30px">
                          <label class="col-sm-1 col-sm-offset-1  control-label">流程图三</label>
                          <div class="col-sm-4">
                              <div class="row">
                                  <div class="col-sm-8">
                                      <div v-show="deptYwlx.lcth" class="row" >
                                          <img  v-bind:src="path+deptYwlx.lcth" style="height: 100px" class="img-responsive">
                                      </div>
                                  </div>
                                  <div class="col-sm-4" >
                                      <file  v-bind:after-upload="afterUploadthree"
                                             v-bind:text="'流程图三'"
                                             v-bind:use="'ywlct'"
                                             v-bind:input-id="'imagethree-upload'"
                                             v-bind:suffixs="['jpg','jpeg','png']"></file>
                                  </div>
                              </div>
                          </div>
                          <label class="col-sm-1 control-label">流程图四</label>
                          <div class="col-sm-5">
                              <div class="row">
                                  <div class="col-sm-8">
                                      <div v-show="deptYwlx.lctf" class="row" >
                                          <img  v-bind:src="path+deptYwlx.lctf" style="height: 100px" class="img-responsive">
                                      </div>
                                  </div>
                                  <div class="col-sm-4" >
                                      <file  v-bind:after-upload="afterUploadfour"
                                             v-bind:text="'流程图四'"
                                             v-bind:use="'ywlct'"
                                             v-bind:input-id="'imagefour-upload'"
                                             v-bind:suffixs="['jpg','jpeg','png']"></file>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="form-group">
                          <label class="col-sm-2 control-label">备注</label>
                          <div class="col-sm-10">
                              <textarea   v-model="deptYwlx.bz" style="height: 50px" class="autosize-transition form-control" ></textarea>
                          </div>
                      </div>
                  </form>
              </div>
              <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                  <button v-on:click="savethis()" type="button" class="btn btn-primary">保存</button>
              </div>
          </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->


      <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document" style="width: 70%" >
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title">业务流程配置【{{dept.deptname}}】</h4>
                  </div>
                  <div class="modal-body">
                      <form class="form-horizontal">
                          <div class="form-group">
                              <label class="col-sm-2 control-label">机动车业务</label>
                              <div class="col-sm-10">
                                  <div class="checkbox">
                                      <template v-for="ywlx in deptYwlxsall" >
                                          <label v-show="ywlx.ywfl ==='2'">
                                              <input name="jdcyw" type="checkbox"  :value="ywlx.ywlx"  v-model="thischeckjdcywVal"     class="ace input-lg" />
                                              <span class="lbl bigger-120">{{checkjdcywVal | optionMapKV(ywlx.ywlx ) }}</span>
                                          </label>
                                      </template>
                                  </div>
                              </div>

                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 control-label">驾驶人业务</label>
                              <div class="col-sm-10">
                                  <div class="checkbox">
                                      <template v-for="ywlx in deptYwlxsall" >
                                          <label v-show="ywlx.ywfl ==='3'">
                                              <input name="jsryw" type="checkbox" :value="ywlx.ywlx"  v-model="thischeckjsrywVal"   class="ace input-lg" />
                                              <span class="lbl bigger-120">{{checkjsrywVal | optionMapKV(ywlx.ywlx ) }}</span>
                                          </label>
                                      </template>
                                  </div>
                              </div>

                          </div>

                          <div class="form-group">
                              <label class="col-sm-2 control-label">违法业务</label>
                              <div class="col-sm-10">
                                  <div class="checkbox">
                                      <template v-for="ywlx in deptYwlxsall" >
                                          <label v-show="ywlx.ywfl ==='5'">
                                              <input name="wfyw" type="checkbox" :value="ywlx.ywlx"   v-model="thischeckfwywVal"   class="ace input-lg" />
                                              <span class="lbl bigger-120">{{checkfwywVal | optionMapKV(ywlx.ywlx ) }}</span>
                                          </label>
                                      </template>
                                  </div>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 control-label">办事流程</label>
                              <div class="col-sm-10 ">
                                  <textarea  style="height: 80px" v-model="deptYwlx.bslc"  class="autosize-transition form-control" placeholder="请输入当前业务详细办事流程"></textarea>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 control-label">所需资料</label>
                              <div class="col-sm-10 ">
                                  <textarea  style="height: 80px" v-model="deptYwlx.sxzl"  class="autosize-transition form-control" placeholder="请输入当前业务所需要的资料"></textarea>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-1 col-sm-offset-1  control-label">流程图一</label>
                              <div class="col-sm-4">
                                  <div class="row">
                                      <div class="col-sm-8">
                                          <div v-show="deptYwlx.lcto" class="row" >
                                              <img  v-bind:src="path+deptYwlx.lcto" style="height: 100px" class="img-responsive">
                                          </div>
                                      </div>
                                      <div class="col-sm-4" >
                                          <file  v-bind:after-upload="afterUploadone"
                                                 v-bind:text="'流程图一'"
                                                 v-bind:use="'ywlct'"
                                                 v-bind:input-id="'imageone-upload'"
                                                 v-bind:suffixs="['jpg','jpeg','png']"></file>
                                      </div>
                                  </div>
                              </div>

                              <label class="col-sm-1 control-label">流程图二</label>
                              <div class="col-sm-5">
                                  <div class="row">
                                      <div class="col-sm-8">
                                          <div v-show="deptYwlx.lctt" class="row" >
                                              <img  v-bind:src="path+deptYwlx.lctt" style="height: 100px" class="img-responsive">
                                          </div>
                                      </div>
                                      <div class="col-sm-4" >
                                          <file  v-bind:after-upload="afterUploadtwo"
                                                 v-bind:text="'流程图二'"
                                                 v-bind:use="'ywlct'"
                                                 v-bind:input-id="'imagetwo-upload'"
                                                 v-bind:suffixs="['jpg','jpeg','png']"></file>
                                      </div>
                                  </div>
                              </div>
                          </div>
                          <div class="form-group" style="margin-top: 30px">
                              <label class="col-sm-1 col-sm-offset-1  control-label">流程图三</label>
                              <div class="col-sm-4">
                                  <div class="row">
                                      <div class="col-sm-8">
                                          <div v-show="deptYwlx.lcth" class="row" >
                                              <img  v-bind:src="path+deptYwlx.lcth" style="height: 100px" class="img-responsive">
                                          </div>
                                      </div>
                                      <div class="col-sm-4" >
                                          <file  v-bind:after-upload="afterUploadthree"
                                                 v-bind:text="'流程图三'"
                                                 v-bind:use="'ywlct'"
                                                 v-bind:input-id="'imagethree-upload'"
                                                 v-bind:suffixs="['jpg','jpeg','png']"></file>
                                      </div>
                                  </div>
                              </div>
                              <label class="col-sm-1 control-label">流程图四</label>
                              <div class="col-sm-5">
                                  <div class="row">
                                      <div class="col-sm-8">
                                          <div v-show="deptYwlx.lctf" class="row" >
                                              <img  v-bind:src="path+deptYwlx.lctf" style="height: 100px" class="img-responsive">
                                          </div>
                                      </div>
                                      <div class="col-sm-4" >
                                          <file  v-bind:after-upload="afterUploadfour"
                                                 v-bind:text="'流程图四'"
                                                 v-bind:use="'ywlct'"
                                                 v-bind:input-id="'imagefour-upload'"
                                                 v-bind:suffixs="['jpg','jpeg','png']"></file>
                                      </div>
                                  </div>
                              </div>
                          </div>

                          <div class="form-group">
                              <label class="col-sm-2 control-label">备注</label>
                              <div class="col-sm-10">
                                  <textarea   v-model="deptYwlx.bz" style="height: 50px" class="autosize-transition form-control" ></textarea>
                              </div>
                          </div>
                      </form>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                      <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
                  </div>
              </div><!-- /.modal-content -->
          </div><!-- /.modal-dialog -->
      </div><!-- /.modal -->


      <div id="yytime_form-modal" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document" style="width: 50%" >
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title">预约时段配置【{{dept.deptname}}】</h4>
                  </div>
                  <div class="modal-body">
                      <form class="form-horizontal">
                          <div class="form-group">

                                  <label class="col-sm-2 control-label">预约时段:</label>
                                  <div class="col-sm-2">
                                      <input id="date-timepicker1"    v-on:blur="getstarvalue()"   type="text" name="start" class="form-control" />
                                  </div>
                                  <label class="col-sm-1  control-label">至</label>
                                  <div class="col-sm-2">
                                     <input id="date-timepicker2"   v-on:blur="getendvalue()"     type="text"  class="form-control" />
                                  </div>
                                  <label class="col-sm-2   control-label">预约数：</label>
                              <div class="col-sm-2">
                                  <input  type="text"  v-model="deptYysj.yymun" class="form-control"  />
                              </div>
                          </div>
                      </form>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                      <button v-on:click="addyytime()" type="button" class="btn btn-primary">保存</button>
                  </div>
              </div><!-- /.modal-content -->
          </div><!-- /.modal-dialog -->
      </div><!-- /.modal -->
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import File from "../../components/file";
  export default {
    components: {File,Pagination},
    name: "system-deptYwlx",
    data: function() {
      return {
          thischeckjdcywVal:[],//当前选择的机动车业务
          thischeckjsrywVal:[],//当前选择的驾驶人业务
          thischeckfwywVal:[],//当前选择的违法业务
          checkjdcywVal:[],//选择的机动车业务
          checkjsrywVal:[],//选择的驾驶人业务
          checkfwywVal:[],//选择的违法业务
          allVal:[],//业务总类
          dept:{},
      deptYwlx: {},
      deptYwlxs: [],
          deptYysj:{},//预约时段配置
          deptYysjs:[],
          strat:"",//开始时间
          endt:"",//结束时间
          path:process.env.VUE_APP_SERVER,
          deptYwlxsall:[]
    }
    },
    mounted: function() {

        let _this = this;
        let  dept = SessionStorage.get(SESSION_KEY_DEPT)|| {};
        if(Tool.isEmpty(dept)){
            _this.$router.push("/welcome");
        }
        _this.dept = dept;
        _this.$refs.pagination.size = 10;
        _this.getywflandyewlx();
        _this.deptYysjList();
        _this.list(1);//页面初始化执行
        _this.listall();//所有业务类型
        this.$parent.activeSidebae("system-dept-sidebar");

      // sidebar激活样式方法一
      // this.$parent.activeSidebar("system-deptYwlx-sidebar");

    },
    methods: {

        /**
         *预约时段修改
         */
        updateyysf(yysd){
            let _this = this;
            _this.addbeforyytime();
            _this.deptYysj = $.extend({}, yysd);
            $("#date-timepicker1").val(_this.deptYysj.stime);
            $("#date-timepicker2").val(_this.deptYysj.etime);

        },
        /**
         *预约时段删除
         */
        deleteyysf(id){
            let _this = this;
            Confirm.show("删除时段配置后不可恢复，确认删除？", function () {
                Loading.show();
                _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/deleteDeptYysj/' + id).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                        _this.deptYysjList();
                        Toast.success("删除成功！");
                    }
                })
            });
        },

        /**
         *保存预约时段
         */
        addyytime(){
            let _this = this;
            _this.deptYysj.deptcode=_this.dept.deptcode;
            _this.$forceUpdate();

            // 保存校验
            if (1 != 1
                || !Validator.require(_this.deptYysj.stime, "预约时段开始时间")
                || !Validator.require(_this.deptYysj.etime, "预约时段结束时间")
                || !Validator.require(_this.deptYysj.yymun, "预约数量")

            ) {
                return;
            }
            if (!(/(^[1-9]\d*$)/.test(_this.deptYysj.yymun))) {
                Toast.warning("预约数量格式不合法，请输入整数！")
                return;
            }
            Loading.show();
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/addDeptYysj', _this.deptYysj).then((response)=>{
                Loading.hide();
                let resp = response.data;
                if (resp.success) {
                    $("#yytime_form-modal").modal("hide");
                    _this.deptYysjList();
                    Toast.success("保存成功！");
                } else {
                    Toast.warning(resp.message)
                }
            })

        },

        getstarvalue(){
            let _this = this;
            let star = $("#date-timepicker1").val()
            _this.deptYysj.stime=star;
            _this.$forceUpdate();



        },
        getendvalue(){
            let _this = this;
            let end= $("#date-timepicker2").val()
            _this.deptYysj.etime=end;
            _this.$forceUpdate();
        },
        /**
         * 预约时段添加
         */
        addbeforyytime(){
            let _this = this;
            $("#date-timepicker1").val("");
            $("#date-timepicker2").val("");
            _this.deptYysj ={};
            if(!ace.vars['old_ie']) $('#date-timepicker1').datetimepicker({
                format: 'HH:mm',//use this option to display seconds
                icons: {
                    time: 'fa fa-clock-o',
                    date: 'fa fa-calendar',
                    up: 'fa fa-chevron-up',
                    down: 'fa fa-chevron-down',
                    previous: 'fa fa-chevron-left',
                    next: 'fa fa-chevron-right',
                    today: 'fa fa-arrows ',
                    clear: 'fa fa-trash',
                    close: 'fa fa-times'
                }
            }).next().on(ace.click_event, function(){
                $(_this).prev().focus();
            });

            if(!ace.vars['old_ie']) $('#date-timepicker2').datetimepicker({
                format: 'HH:mm',//use this option to display seconds
                icons: {
                    time: 'fa fa-clock-o',
                    date: 'fa fa-calendar',
                    up: 'fa fa-chevron-up',
                    down: 'fa fa-chevron-down',
                    previous: 'fa fa-chevron-left',
                    next: 'fa fa-chevron-right',
                    today: 'fa fa-arrows ',
                    clear: 'fa fa-trash',
                    close: 'fa fa-times'
                }
            }).next().on(ace.click_event, function(){
                $(_this).prev().focus();
            });
            $("#yytime_form-modal").modal("show");

        },
        /**
         *图片上传回调函数
         */
      afterUploadone(resp){//回调主键方法
          let _this = this;
          let image = resp.content;
          _this.deptYwlx.lcto = image;
          _this.$forceUpdate();
      },

        /**
         *图片上传回调函数
         */
        afterUploadtwo(resp){//回调主键方法
            let _this = this;
            let image = resp.content;
            _this.deptYwlx.lctt = image;
            _this.$forceUpdate();
        },
        /**
         *图片上传回调函数
         */
        afterUploadthree(resp){//回调主键方法
            let _this = this;
            let image = resp.content;
            _this.deptYwlx.lcth = image;
            _this.$forceUpdate();
        },
        /**
         *图片上传回调函数
         */
        afterUploadfour(resp){//回调主键方法
            let _this = this;
            let image = resp.content;
            _this.deptYwlx.lctf = image;
            _this.$forceUpdate();
        },


        /**
         *返回上一层
         */

        getprant(){
            let _this = this;
            _this.$router.push("/system/dept");
        },
        /**
         * 获取业务类型
         */
        getywflandyewlx(){
            let _this = this;
            _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/getywflandyewlx').then((response)=>{
                let resp = response.data.content;
                _this.checkjdcywVal = resp.CODE_JDC_YWLX;
                _this.checkjsrywVal = resp.CODE_JSR_YWLX;
                _this.checkfwywVal = resp.CODE_WF_YWLX;
                _this.allVal=resp.YWZL_CODE;

            })
        },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
          _this.thischeckjdcywVal=[];//当前选择的机动车业务
          _this.thischeckjsrywVal=[];//当前选择的驾驶人业务
          _this.thischeckfwywVal=[];//当前选择的违法业务
        _this.deptYwlx = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(deptYwlx) {
        let _this = this;
        _this.deptYwlx = $.extend({}, deptYwlx);
        $("#edit-form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/list', {
          page: page,
          size: _this.$refs.pagination.size,
            deptcode: _this.dept.deptcode,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.deptYwlxs = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

        })
      },
      listall() {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/listall', {
          deptcode: _this.dept.deptcode,
        }).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.deptYwlxsall = resp.content;

        })
      },

        /**
         * 查询业务预约时段列表
         */
       deptYysjList() {
            let _this = this;
            Loading.show();
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/queryDeptYysj',
                _this.dept,
            ).then((response)=>{
                Loading.hide();
                let resp = response.data;
                _this.deptYysjs = resp.content;
            })
        },
        /**
         * 点击【保存】
         */
        savethis() {
            let _this = this;

            // 保存校验
            if (1 != 1
                || !Validator.length(_this.deptYwlx.deptcode, "部门编号", 1, 50)
                || !Validator.length(_this.deptYwlx.bslc, "办事流程", 1, 1000)
                || !Validator.require(_this.deptYwlx.bslc, "办事流程")
                || !Validator.length(_this.deptYwlx.sxzl, "所需资料", 1, 1000)
                || !Validator.length(_this.deptYwlx.bz, "备注", 1, 300)
            ) {
                return;
            }

            Loading.show();
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/save', _this.deptYwlx).then((response)=>{
                Loading.hide();
                let resp = response.data;
                if (resp.success) {
                    $("#edit-form-modal").modal("hide");
                    _this.list(1);
                    Toast.success("保存成功！");
                } else {
                    Toast.warning(resp.message)
                }
            })
        },
      /**
       * 点击【保存】
       */
      save() {
        let _this = this;

        // 保存校验
        if (1 != 1
                || !Validator.length(_this.deptYwlx.deptcode, "部门编号", 1, 50)
                || !Validator.length(_this.deptYwlx.bslc, "办事流程", 1, 1000)
                || !Validator.require(_this.deptYwlx.bslc, "办事流程")
                || !Validator.length(_this.deptYwlx.sxzl, "所需资料", 1, 1000)
                || !Validator.length(_this.deptYwlx.bz, "备注", 1, 300)
        ) {
          return;
        }

          if( Tool.isEmpty(_this.thischeckjdcywVal) &&
              Tool.isEmpty(_this.thischeckjsrywVal) &&
              Tool.isEmpty(_this.thischeckfwywVal)){
              Toast.warning("请选择业务类型！");
              return;
          }
          _this.$set(_this.deptYwlx,"checkjdcywVal",_this.thischeckjdcywVal);
          _this.$set(_this.deptYwlx,"checkjsrywVal",_this.thischeckjsrywVal);
          _this.$set(_this.deptYwlx,"checkfwywVal",_this.thischeckfwywVal);
          _this.$set(_this.deptYwlx,"deptcode",_this.dept.deptcode);
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/saveall', _this.deptYwlx).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            Toast.success("保存成功！");
          } else {
            Toast.warning(resp.message)
          }
        })
      },

      /**
       * 点击【删除】
       */
      del(id) {
        let _this = this;
        Confirm.show("删除部门业务后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      }
    }
  }
</script>