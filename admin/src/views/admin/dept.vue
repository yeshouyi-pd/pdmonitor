<template>
    <div>
        <div class="row">
            <div class="col-md-9">
                <!-- query start -->
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">部门查询</h4>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form >
                                <table  style="font-size: 1.1em;" class="text-right  " >
                                    <tbody  >
                                    <tr  >
                                        <td style="width: 15%" >
                                        上级部门：
                                    </td>
                                        <td style="width: 25%">
                                            <updept v-bind:after-check="queyCheckedepe"
                                                    v-bind:checktree="'querytreeid'"
                                                    v-bind:model-id="'querytreemode'" >
                                            </updept>
                                        </td>
                                        <td style="width: 15%" >
                                            部门名称：
                                        </td>
                                        <td style="width: 25%">
                                            <input v-model="deptDto.deptname"  class="form-control">
                                        </td>
                                      <td>

                                      </td>
                                    </tr>
                                    <tr>
                                        <td >
                                            部门编号：
                                        </td>
                                        <td>
                                            <input v-model="deptDto.deptcode" class="form-control" >
                                        </td>
                                      <td >
                                        部门类别：
                                      </td>
                                      <td>
                                        <select v-model="deptDto.depttype" class="form-control" id="form-field-select-1">
                                          <option value="" selected>请选择</option>
                                          <option v-for="(key,value) in depttypes" :value="value">{{key}}</option>
                                        </select>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td >
                                        创建时间：
                                      </td>

                                      <td colspan="">
                                        <Times v-bind:startTime="startTime"
                                               v-bind:endTime="endTime" >

                                        </Times>
                                      </td>
                                      <td    class="text-center">
                                        <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round">
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
                <div style="background: #F5F5F5;margin-top: -2px">
                    <div class="space-2"></div>
                    <button v-on:click="add()" class="btn  btn-sm btn-success btn-round">
                        <i class="ace-icon fa fa-edit"></i>
                        新增
                    </button>&nbsp;
                    <div class="space-2"></div>
                </div>
                <table id="simple-table"  class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>部门类别</th>
                        <th>上级部门</th>
                        <th>创建时间</th>
                        <th>创建人</th>
                        <th>修改时间</th>
                        <th>修改人</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr v-for="(dept,index) in depts">
                        <td>{{dept.deptcode}}</td>
                        <td style="width: 15%">{{dept.deptname}}</td>
                        <td style="width: 15%">{{depttypes|optionMapKV(dept.depttype)}}</td>
                        <td style="width: 15%">{{dept.upcode}}</td>
                        <td>{{dept.createtime | formatDateTime}}</td>
                        <td>{{dept.createman}}</td>
                        <td>{{dept.updatetime | formatDateTime }}</td>
                        <td>{{dept.updateman}}</td>
                        <td>
                            <div class="hidden-sm hidden-xs btn-group">
                                <button v-on:click="editYlwx(dept)" class="btn btn-xs btn-info" title="详情">
                                    <i class="ace-icon fa fa-list bigger-120"></i>
                                </button>

                                <button v-on:click="edit(dept)" class="btn btn-xs btn-info" title="修改">
                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                </button>
                                <button v-on:click="del(dept.id)" class="btn btn-xs btn-danger" title="删除">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
            </div>
            <div class="col-md-3">
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>

        <div id="tree-modal" class="modal fade" tabindex="-1"  style="z-index: 1060"  role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">双击选择上级部门</h4>
                    </div>
                    <div class="modal-body">
                        <ul id="checktree" class="ztree"></ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
                            <i class="ace-icon fa fa-times"></i>
                            关闭
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <div id="map-modal"    class="modal fade" tabindex="-1" style="z-index: 1070"   role="dialog">
            <div class="modal-dialog" style="width: 90%;height: auto" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">位置选择</h4>
                    </div>
                    <div class="modal-body">


                        <!--  选择高德位置-->
                        <div class="row" style="margin-right: 0px">
                            <div style="width: 295px">
                                <div class="input-group">
                                    <input type="text" v-model="address" class="form-control search-query" placeholder="请输入办事网点" />
                                    <span class="input-group-btn">
											<button @click="search" type="button" class="btn btn-purple btn-sm">
												<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
														搜索
												</button>
									</span>
                                </div>
                            </div>
                        </div>

                        <div style="display:flex;width:100%">

                            <div id="result" class="amap_lib_placeSearch">
                                <div class="amap_lib_placeSearch_list amap-pl-pc"  v-for="(item,index) in poiArr"
                                     @click="openMarkerTipById1(item,index,$event)"
                                     v-on:dblclick="check(item)"
                                     @mouseout="onmouseout_MarkerStyle(index+1,$event)"
                                     :key="index">
                                    <div class="poibox" style="border-bottom: 1px solid #eaeaea">
                                        <div class="amap_lib_placeSearch_poi poibox-icon" :class="index==selectedIndex?'selected':''">{{index+1}}</div>
                                        <div class="poi-img" v-if="item.url" :style="'background-image:url('+item.url+'?operate=merge&amp;w=90&amp;h=56&amp;position=5)'"
                                        ></div>
                                        <h3 class="poi-title" >
                                            <span class="poi-name">{{item.name}}</span>
                                        </h3>
                                        <div class="poi-info" >
                                            <p class="poi-addr">地址：{{item.address}}</p>
                                            <p class="poi-tel">电话：{{item.tel}}</p>
                                            <p class="poi-tel">经纬度：{{item.location.lng }},{{item.location.lat }}</p>
                                        </div>
                                        <div class="clear"></div>
                                    </div>
                                </div>
                            </div>
                            <div id="iCenter"></div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->


        <div id="form-modal"    class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" style="width: 80%;height: auto" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">部门维护</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">上级部门</label>
                                <div class="col-sm-5">
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <input  style="display: none" v-model="dept.upcode"  class="form-control">
                                            <input   disabled="disabled" v-for="updept in alldept.filter(t=>{return t.deptcode===dept.upcode})" v-bind:value="updept.deptname"       class="form-control">
                                        </div>
                                        <div  class="col-sm-2">
                                            <button  v-on:click="chencktreeid()" type="button" class="btn btn-success" >选择上级部门</button>
                                        </div>
                                    </div>
                                </div>
                                <label class="col-sm-1 control-label">机构类别</label>
                                <div class="col-sm-5">
                                  <select v-model="dept.depttype" class="form-control">
                                     <option v-for="(value,key) in depttypes" v-bind:value="key">{{value}}</option>
                                  </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">部门编号</label>
                                <div class="col-sm-5">
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <input v-model="dept.deptcode"  v-bind:disabled="dept.id"  class="form-control">
                                        </div>
                                        <div  class="col-sm-2">
                                            <button  v-on:click="openmap(dept.id,dept.deptname)" type="button" class="btn btn-success" >选择办事网点</button>
                                        </div>
                                    </div>
                                </div>
                                <label class="col-sm-1 control-label">部门名称</label>
                                <div class="col-sm-5">
                                    <input v-model="dept.deptname" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">联系人</label>
                                <div class="col-sm-5">
                                    <input v-model="dept.linkman" class="form-control">
                                </div>
                                <label class="col-sm-1 control-label">联系电话</label>
                                <div class="col-sm-5">
                                    <input v-model="dept.linktel" class="form-control">
                                </div>
                            </div>
                          <div class="form-group">
                            <label class="col-sm-1 control-label">最大提前预约天数</label>
                            <div class="col-sm-5">
                              <input  v-model="dept.maxday" class="form-control" />
                            </div>
                            <label class="col-sm-1 control-label">是否启用预约</label>
                            <div class="col-sm-5">
                              <div class="radio">
                                <label>
                                  <input name="form-yyzt-radio" type="radio"  value="Y" v-model="dept.yyzt" class="ace input-lg" />
                                  <span class="lbl bigger-120">是</span>
                                </label>
                                <label>
                                  <input name="form-yyzt-radio" type="radio"  value="N"  v-model="dept.yyzt" class="ace input-lg" />
                                  <span class="lbl bigger-120">否</span>
                                </label>
                              </div>
                            </div>
                          </div>
                            <div class="form-group">
                              <label class="col-sm-1 control-label">个人每日预约最大数</label>
                              <div class="col-sm-5">
                                    <input v-model="dept.gryymax" class="form-control">
                                </div>
                                <label class="col-sm-1 control-label">企业每日预约最大数</label>
                                <div class="col-sm-5">
                                    <input v-model="dept.qyyymax" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">部门地址</label>
                                <div class="col-sm-5">
                                    <input v-model="dept.linkadd" class="form-control">
                                </div>
                                <label class="col-sm-1 control-label">部门状态</label>
                                <div class="col-sm-5">
                                    <div class="radio">
                                        <label>
                                            <input name="form-status-radio" type="radio"     value="Y" v-model="dept.status" class="ace input-lg" />
                                            <span class="lbl ">启用</span>
                                        </label>
                                        <label>
                                            <input name="form-status-radio" type="radio"  value="N"  v-model="dept.status" class="ace input-lg" />
                                            <span class="lbl">禁用</span>
                                        </label>
                                    </div>

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-1 control-label">坐标经度</label>
                                <div class="col-sm-5">
                                    <input v-model="dept.jd" class="form-control">
                                </div>
                                <label class="col-sm-1 control-label">坐标纬度</label>
                                <div class="col-sm-5">
                                    <input v-model="dept.wd" class="form-control">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-1 control-label">描述</label>
                                <div class="col-sm-5">
                                    <input v-model="dept.deptdesc" class="form-control">
                                </div>
                                <label class="col-sm-1 control-label">违法业务</label>
                                <div class="col-sm-5">
                                    <div class="checkbox">
                                        <template v-for="(value, key) in fwyw">
                                            <label>
                                                <input name="fwyw" type="checkbox" :value="key"    v-model="checkfwywVal"  class="ace input-lg" />
                                                <span class="lbl bigger-120">{{value}}</span>
                                            </label>
                                        </template>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-1 control-label">机动车业务</label>
                                <div class="col-sm-5">
                                    <div class="checkbox">
                                        <template v-for="(value, key) in jdcyw">
                                            <label>
                                                <input name="jdcyw" type="checkbox" :value="key"  v-model="checkjdcywVal"   class="ace input-lg" />
                                                <span class="lbl bigger-120">{{value}}</span>
                                            </label>
                                        </template>

                                    </div>
                                </div>
                                <label class="col-sm-1 control-label">驾驶人业务</label>
                                <div class="col-sm-5">
                                    <div class="checkbox">
                                        <template v-for="(value, key) in jsryw">
                                            <label>
                                                <input name="jsryw" type="checkbox" :value="key"    v-model="checkjsrywVal"  class="ace input-lg" />
                                                <span class="lbl bigger-120">{{value}}</span>
                                            </label>
                                        </template>
                                    </div>
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
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";
    import Times from "../../components/time";
    import Updept from "../../components/updept";
    export default {
        components: {Pagination,Times,Updept},
        name: "system-dept",
        data: function() {
            return {
                deptDto:{},//查询条件对象
                checkjdcywVal:[],//选择的机动车业务
                checkjsrywVal:[],//选择的驾驶人业务
                checkfwywVal:[],//选择的违法业务
                depttypes:[],
                jdcyw:[],//后台初始化的机动车所有业务
                jsryw:[],//后台初始化的驾驶人所有业务
                fwyw:[],//后台初始化的违法所有业务
                dept: {},
                depts: [],
                trees:[],
                checktrees:[],
                alldept:[],
                address:'',//保存地址的汉字名字
                tel:'',//电话
                names:'',//地址名称
                map:'',//保存地址的经纬度
                poiArr: [],//左边搜索出来的数组
                windowsArr: [],//信息窗口的数组
                marker: [],
                mapObj: "",//地图对象
                selectedIndex: -1

            }
        },
        mounted: function() {
            let _this = this;
            _this.list(1);
            _this.getDeptTree();
            _this.getAllYwlx();
            //or change it into a date range picker

            // sidebar激活样式方法一
            // this.$parent.activeSidebar("system-dept-sidebar");

        },
        methods: {
            queyCheckedepe(rep){
                let _this = this;
                _this.deptDto.upcode = rep;//获取查询条件上级部门
                _this.$forceUpdate();

            },
            /**
             *开始时间
             */
            startTime(rep){
                let _this = this;
                _this.deptDto.stime = rep;
                _this.$forceUpdate();
            },
            /**
             *结束时间
             */
            endTime(rep){
                let _this = this;
                _this.deptDto.etime = rep;
                _this.$forceUpdate();
            },
            /**
             * 获取所有业务类型
             */
            getAllYwlx(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/dept/getAllYwlx').then((response)=>{
                    let resp = response.data.content;
                    _this.jdcyw = resp.CODE_JDC_YWLX;
                    _this.jsryw = resp.CODE_JSR_YWLX;
                    _this.fwyw = resp.CODE_WF_YWLX;
                   _this.depttypes=resp.CODE_XX_DEPTTYPE
                })
            },
            editYlwx(dept){
                let _this = this;
                SessionStorage.set(SESSION_KEY_DEPT,dept); //写入缓存
                _this.$router.push("/system/deptYwlx");

            },
            openmap(objs,checkname){
                let _this = this;
                _this.tel='';//电话
                _this.names='';//地址名称
                _this.map='';//保存地址的经纬度
                _this.poiArr= [];//左边搜索出来的数组
                _this.windowsArr= [];//信息窗口的数组
                _this.marker= [];
                _this.mapObj= "";//地图对象
                _this.address='';//保存地址的汉字名字
                _this.mapInit();
                if(objs){
                    _this.placeSearch(checkname);

                }else{

                    _this.placeSearch("");
                }



                $("#map-modal").modal("show");
            },
            check(info){
                let _this = this;
                _this.$set(_this.dept,"deptname",info.name);
                _this.$set(_this.dept,"linkadd",info.address);
                _this.$set(_this.dept,"linktel",info.tel);
                _this.$set(_this.dept,"jd",info.location.lng);
                _this.$set(_this.dept,"wd",info.location.lat);

                $("#map-modal").modal("hide");
            },
            /**
             * 点击【删除】
             */
            getAllDept() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/dept/getAllDept').then((response)=>{
                    let resp = response.data;
                    _this.alldept = resp.content;

                })

            },
            /**
             * 选择树
             */
            chencktreeid(){
                let _this = this;
                _this.getAllDept();
                $("#tree-modal").modal("show");

            },
            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                _this.checkjdcywVal=[];//选择的机动车业务
                _this.checkjsrywVal=[];//选择的驾驶人业务
                _this.checkfwywVal=[];//选择的违法业务
                _this.dept = {};
                _this.dept.status="Y";
                _this.dept.fdr="Y";
                _this.dept.yyzt="Y";
                _this.$forceUpdate();
               // _this.getAllDept()
                $("#form-modal").modal("show");
            },
            getDeptTree() {
                let _this = this;
                Loading.show();
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/dept/load-deptTree').then((res)=>{
                    Loading.hide();
                    let response = res.data;
                    _this.trees = response.content;
                    _this.checktrees = response.content;

                    // 初始化树
                    _this.initTree();
                    _this.checkinitTree();
                })
            },

            /**
             * 点击【编辑】
             */
            edit(dept) {
                let _this = this;
                _this.checkjdcywVal = [];
                _this.checkjsrywVal = [];
                _this.checkfwywVal = [];
                _this.dept = $.extend({}, dept);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/deptYwlx/getYwlxByDept',  _this.dept).then((response)=>{
                    let resp = response.data.content;
                    _this.checkjdcywVal = resp.CODE_JDC_YWLX;
                    _this.checkjsrywVal = resp.CODE_JSR_YWLX;
                    _this.checkfwywVal = resp.CODE_WF_YWLX;


                })
                _this.getAllDept()
                $("#form-modal").modal("show");

            },

            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.deptDto.page = page;
                _this.deptDto.size = _this.$refs.pagination.size;
                _this.$forceUpdate();

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/dept/list', _this.deptDto).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    _this.depts = resp.content.list;
                    _this.$refs.pagination.render(page, resp.content.total);

                })
            },

            /**
             * 点击【保存】
             */
            save() {
                let _this = this;

                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.dept.deptcode, "部门编号")
                    || !Validator.length(_this.dept.deptcode, "部门编号", 1, 36)
                    || !Validator.require(_this.dept.deptname, "部门名称")
                    || !Validator.length(_this.dept.deptname, "部门名称", 1, 100)
                    || !Validator.length(_this.dept.upcode, "上级部门", 1, 100)
                    || !Validator.length(_this.dept.deptdesc, "描述", 1, 200)
                    || !Validator.length(_this.dept.linkadd, "部门地址", 1, 200)
                    || !Validator.length(_this.dept.linktel, "部门电话", 1, 50)
                    || !Validator.length(_this.dept.linkman, "联系人", 1, 100)
                    || !Validator.length(_this.dept.status, "状态", 1, 12)
                    || !Validator.require(_this.dept.jd, "经度")
                    || !Validator.length(_this.dept.jd, "经度", 1, 12)
                    || !Validator.require(_this.dept.wd, "纬度")
                    || !Validator.length(_this.dept.wd, "纬度", 1, 12)
                    || !Validator.require(_this.dept.gryymax+"", "个人每日预约数")
                    || !Validator.require(_this.dept.qyyymax+"", "企业每日预约数")
                    || !Validator.checkisNaN(_this.dept.gryymax, "个人每日预约数")
                    || !Validator.checkisNaN(_this.dept.qyyymax, "企业每日预约数")
                    || !Validator.require(_this.dept.depttype, "机构类别")
                    || !Validator.require(_this.dept.maxday+"", "最大提前预约天数")
                    || !Validator.checkisNaN(_this.dept.maxday, "最大提前预约天数")



                ) {
                    return;
                }

                Loading.show();
                _this.$set(_this.dept,"checkjdcywVal",_this.checkjdcywVal);
                _this.$set(_this.dept,"checkjsrywVal",_this.checkjsrywVal);
                _this.$set(_this.dept,"checkfwywVal",_this.checkfwywVal);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/dept/save', _this.dept).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                        $("#form-modal").modal("hide");
                        _this.list(1);
                        _this.getDeptTree();
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
                Confirm.show("删除部门后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/dept/delete/' + id).then((response)=>{
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            _this.list(1);
                            _this.getDeptTree();
                            Toast.success("删除成功！");
                        }else{
                            Toast.warning(resp.message);
                        }
                    })
                });
            },
            /**
             * 初始资源树
             */
            initTree() {
                let _this = this;
                let setting = {
                    data: {
                        key:{
                            name:"deptname",
                        },
                        simpleData: {
                            idKey: "deptcode",
                            pIdKey: "upcode",
                            rootPId: "0",
                            enable: true
                        }
                    },callback: {
                    onDblClick:  _this.zTreeRightOnDblClick
                  },
                };

                _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.trees);
                _this.zTree.expandAll(true);
            },
            zTreeRightOnDblClick(event, treeId, treeNode) {//双击右侧树查询
              let  _this = this;
              _this.edit(treeNode);
            },
            /**
             * 初始资源树
             */
            checkinitTree() {
                let _this = this;
                let setting = {
                    data: {
                        key:{
                            name:"deptname",
                        },
                        simpleData: {
                            idKey: "deptcode",
                            pIdKey: "upcode",
                            rootPId: "0",
                            enable: true
                        }
                    },callback: {
                        onDblClick:  _this.zTreeOnDblClick
                    },
                };

                _this.zTree = $.fn.zTree.init($("#checktree"), setting, _this.checktrees);
                _this.zTree.expandAll(true);
            },
            zTreeOnDblClick(event, treeId, treeNode) {
                let  _this = this;
              //_this.deptDto.deptcode = treeNode.deptcode;
              _this.deptDto.upcode = treeNode.deptcode;
              _this.$forceUpdate();
              _this.list(1);

            },
            //创建一个map
            mapInit() {
                let  _this = this;
                _this.mapObj = new AMap.Map("iCenter", {
                    resizeEnable: true
                })
            },
            //根据名字地址去搜索结果
            placeSearch(name) {
                let  _this = this;
                let MSearch;
                _this.mapObj.plugin(
                    ["AMap.PlaceSearch", "AMap.ToolBar", "AMap.Scale"],
                    () => {
                        _this.mapObj.addControl(new AMap.ToolBar())
                        _this.mapObj.addControl(new AMap.Scale())
                        MSearch = new AMap.PlaceSearch({
                            //构造地点查询类
                            city: "" //城市
                        });
                        AMap.event.addListener(MSearch,"complete",_this.keywordSearch_CallBack) //返回地点查询结果
                        MSearch.search(name); //关键字查询
                    }
                );
            },
            //结果的回调
            keywordSearch_CallBack(data) {
                let  _this = this;
                let poiArr = data.poiList.pois
                let resultCount = poiArr.length
                _this.poiArr = poiArr; //左边要渲染的数据
                for (let i = 0; i < resultCount; i++) {
                    _this.addmarker(i, poiArr[i])
                    _this.poiArr[i].url = _this.poiArr[i].photos? _this.poiArr[i].photos[0]? _this.poiArr[i].photos[0].url: "": ""
                }
                _this.mapObj.setFitView()
            },
            //添加marker&infowindow
            addmarker(i, d) {
                let  _thiss = this;
                let lngX = d.location.getLng();
                let latY = d.location.getLat();
                let markerOption = {
                    map: _thiss.mapObj,
                    position: new AMap.LngLat(lngX, latY)
                };
                let mar = new AMap.Marker(markerOption);
                _thiss.marker.push(new AMap.LngLat(lngX, latY));
                let infoWindow = new AMap.InfoWindow({
                    content:
                        "<h3>" +
                        d.name +
                        "</h3>" +
                        _thiss.TipContents(d.name, d.address,d.location.getLng()) +
                        `</br>联系电话:`+d.tel +
                        `</br>经纬度:`+lngX+","+latY+

                        `</br><a  id="me"  href="javascript:;">选择当前地址</a>`,
                    size: new AMap.Size(300, 0),
                    autoMove: true,
                    offset: new AMap.Pixel(0, -30)
                });
                _thiss.windowsArr.push(infoWindow);
                let _this = this;
                let aa = (e) => {
                    let obj = mar.getPosition();
                    _thiss.tel =d.tel;
                    _thiss.names=d.name;
                    _thiss.map = obj //这里保存的地址经纬度
                    _thiss.address = d.address //这里保存的是地址名字
                    window.removeEventListener("click", _this.demo)
                    infoWindow.open(_this.mapObj, obj);
                    window.addEventListener("click", _this.demo)
                }
                AMap.event.addListener(mar, "click", aa)
            },
            TipContents(name, address) {
                let _this = this;
                //窗体内容
                if (
                    name == "" ||
                    name == "undefined" ||
                    name == null ||
                    name == " undefined" ||
                    typeof name == "undefined"
                ) {
                    type = "暂无";
                }
                if (
                    address == "" ||
                    address == "undefined" ||
                    address == null ||
                    address == " undefined" ||
                    typeof address == "undefined"
                ) {
                    address = "暂无";
                }
                let str = `地址：${address}`
                return str
            },
            demo(e) {
                if (e.target.id === `me`) {
                    e.stopPropagation();
                    this.saveAddress();
                }
            },
            saveAddress() {
                let _this = this;
                _this.$set(_this.dept,"deptname",_this.names);
                _this.$set(_this.dept,"linkadd",_this.address);
                _this.$set(_this.dept,"linktel",_this.tel);
                _this.$set(_this.dept,"jd",_this.map.lng);
                _this.$set(_this.dept,"wd",_this.map.lat);
                $("#map-modal").modal("hide");
            },
            openMarkerTipById1(itme,pointid, thiss) {
                let _this = this;
                //根据id 打开搜索结果点tip
                _this.tel =itme.tel;
                _this.names=itme.name;
                _this.address = itme.address; //这里保存的是地址名字

                thiss.currentTarget.style.background = "#CAE1FF";
                _this.selectedIndex = pointid,
                    _this.map = _this.marker[pointid],
                    window.removeEventListener("click", _this.demo);
                _this.windowsArr[pointid].open(_this.mapObj, _this.marker[pointid])
                window.addEventListener("click", _this.demo);
            },
            onmouseout_MarkerStyle(pointid, thiss) {
                //鼠标移开后点样式恢复
                thiss.currentTarget.style.background = ""
            },
            search() {
                let _this = this;
                _this.map='';//保存地址的经纬度
                _this.poiArr= [];//左边搜索出来的数组
                _this.windowsArr= [];//信息窗口的数组
                _this.marker= [];
                _this.mapObj= "";//地图对象
                _this.mapInit();
                _this.placeSearch(_this.address);
            }
        }
    }
</script>
<style  scoped>
    #iCenter {
        height: 500px;
        position: relative;
        display: flex;
        flex: 1;
    }
    #result {
        width: 210px;
        position: relative;
        height: 500px;
        overflow-y: scroll;
        border-right: 1px solid #ccc;
    }
    .amap_lib_placeSearch {
        height: 100%;
        overflow-y: scroll;
    }
    .amap_lib_placeSearch_page {
        position: absolute;
        bottom: 0;
        width: 100%;
    }
    #me {
        border-top: 1px solid #ccc;
        margin-top: 6px;
        padding-top: 6px;
        width: 100%;
        display: block;
    }
    .amap_lib_placeSearch .poibox {
        border-bottom: 1px solid #eaeaea;
        cursor: pointer;
        padding: 5px 0 5px 10px;
        position: relative;
        min-height: 35px;
    }
    .amap_lib_placeSearch_poi {
        background: url(https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png)
        no-repeat;
        height: 31px;
        width: 19px;
        cursor: pointer;
        left: -1px;
        text-align: center;
        color: #fff;
        font: 12px arial, simsun, sans-serif;
        padding-top: 3px;
    }
    .amap_lib_placeSearch .poibox .poi-title {
        margin-left: 25px;
        font-size: 13px;
        overflow: hidden;
    }
    .amap_lib_placeSearch .amap_lib_placeSearch_poi {
        position: absolute;
    }
    .amap_lib_placeSearch .poibox .poi-info {
        word-break: break-all;
        margin: 0 0 0 25px;
        overflow: hidden;
    }
    .amap_lib_placeSearch .poibox .poi-info p {
        color: #999;
        font-family: Tahoma;
        line-height: 20px;
        font-size: 12px;
    }
    .amap_lib_placeSearch .poibox .poibox-icon {
        margin-left: 7px;
        margin-top: 4px;
    }
    .amap-pl-pc .poi-img {
        float: right;
        margin: 3px 8px 0;
        width: 90px;
        height: 56px;
        overflow: hidden;
    }
    .poibox {
        cursor: pointer;
    }
    .poibox:hover {
        background: #f6f6f6;
    }
    .selected {
        background-image: url(https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png) !important;
    }
    .amap-info-content {
        width: 200px !important;
    }
</style>