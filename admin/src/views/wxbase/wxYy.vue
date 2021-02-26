<template>
  <div>

    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">业务预约查询</h4>
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
                    <td  style="width: 6%">
                      预约时间：
                    </td>
                    <td style="width: 15%">
                      <times v-bind:startTime="startTime"
                             v-bind:endTime="endTime"></times>
                    </td>
                    <td style="width:6%" >
                      业务分类：
                    </td>
                    <td style="width: 8%">
                      <select v-model="wxYyDto.ywfl" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option value="2">机动车业务</option>
                        <option value="3">驾驶人业务</option>
                        <option value="5">违法业务</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      预约类型：
                    </td>
                    <td style="width: 8%">
                      <select v-model="wxYyDto.yytype" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option value="1">个人</option>
                        <option value="2">单位</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      状态：
                    </td>
                    <td style="width: 8%">
                      <select v-model="wxYyDto.zt" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option value="1">正常</option>
                        <option value="2">取消</option>
                        <option value="3">过期</option>
                        <option value="4">办结</option>
                      </select>
                    </td>
                    <td style="width: 6%;">
                       预约人姓名：
                    </td>
                    <td style="width: 8%">
                      <input v-model="wxYyDto.name" class="form-control"/>
                    </td>
                    <td style="width: 6%;">
                      证件号码：
                    </td>
                    <td style="width: 8%">
                      <input v-model="wxYyDto.zjhm" class="form-control"/>
                    </td>
                    <td style="width: 6%;">
                      联系电话：
                    </td>
                    <td style="width: 8%">
                      <input v-model="wxYyDto.sjhm" class="form-control"/>
                    </td>
                    <td   colspan="2" class="text-center">
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
      </div><!-- col-md-12 -->
    </div><!-- row -->

    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>预约部门</th>
        <th>预约人姓名</th>
        <th>联系电话</th>
        <th>证件类型</th>
        <th>证件号码</th>
        <th>创建时间</th>
        <th>预约时间</th>
        <th>预约日段</th>
        <th>预约数量</th>
        <th>业务分类</th>
        <th>业务类型</th>
        <th>预约类型</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="wxYy in wxYys">
        <td>{{maps|optionMapKV(wxYy.deptcode)}}</td>
        <td>{{wxYy.name}}</td>
        <td>{{wxYy.sjhm}}</td>
        <td>{{zjlx|optionMapKV(wxYy.zjlx)}}</td>
        <td>{{wxYy.zjhm}}</td>
        <td>{{wxYy.cjsj}}</td>
        <td>{{wxYy.yysj}}</td>
        <td>{{wxYy.yyrq}}</td>
        <td>{{wxYy.yysl}}</td>
        <td><span v-if="wxYy.ywfl=='2'">机动车业务</span><span v-if="wxYy.ywfl=='3'">驾驶人业务</span><span v-if="wxYy.ywfl=='5'">违法业务</span></td>
        <td><span v-if="wxYy.ywfl=='2'">{{jdcyw|optionCNArray(wxYy.ywlx)}}</span><span v-if="wxYy.ywfl=='3'">{{jsryw|optionCNArray(wxYy.ywlx)}}</span><span v-if="wxYy.ywfl=='5'">{{wfywyw|optionCNArray(wxYy.ywlx)}}</span></td>
        <td><span v-if="wxYy.yytype=='1'">个人</span><span v-if="wxYy.yytype=='2'">单位</span></td>
        <td><span v-if="wxYy.zt=='1'">正常</span><span v-if="wxYy.zt=='2'">取消</span><span v-if="wxYy.zt=='3'">过期</span><span v-if="wxYy.zt=='4'">办结</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="detail(wxYy)" class="btn btn-xs btn-info" title="详情">
              <i class="ace-icon fa fa-list bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document" style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">预约部门</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{maps|optionMapKV(wxYy.deptcode)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">预约类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" v-if="wxYy.yytype=='1'">个人</span>
                    <span class="editable" v-if="wxYy.yytype=='2'">单位</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">预约人姓名</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.name}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">联系电话</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.sjhm}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">证件类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{zjlx|optionMapKV(wxYy.zjlx)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">证件号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.zjhm}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">客车类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" v-if="wxYy.kclx=='Y'">国产车</span>
                    <span class="editable" v-if="wxYy.kclx=='N'">进口车</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">是否新能源</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" v-if="wxYy.sfxny=='1'">是</span>
                    <span class="editable" v-if="wxYy.sfxny=='2'">否</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">车架号后六位</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.vin}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">单位名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.dwmc}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">预约时间</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.yysj}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">预约数量</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.yysl}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">预约日段</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.yyrq}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">受理时间</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.slsj}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">业务分类</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" v-if="wxYy.ywfl=='2'">机动车业务</span>
                    <span class="editable" v-if="wxYy.ywfl=='3'">驾驶人业务</span>
                    <span class="editable" v-if="wxYy.ywfl=='5'">违法业务</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">业务类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" v-if="wxYy.ywfl=='2'">{{jdcyw|optionCNArray(wxYy.ywlx)}}</span>
                    <span class="editable" v-if="wxYy.ywfl=='3'">{{jsryw|optionCNArray(wxYy.ywlx)}}</span>
                    <span class="editable" v-if="wxYy.ywfl=='5'">{{wfywyw|optionCNArray(wxYy.ywlx)}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">状态</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" v-if="wxYy.zt=='1'">正常</span>
                    <span class="editable" v-if="wxYy.zt=='2'">取消</span>
                    <span class="editable" v-if="wxYy.zt=='3'">过期</span>
                    <span class="editable" v-if="wxYy.zt=='4'">办结</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">社会信用代码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.xydm}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">创建时间</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wxYy.cjsj}}</span>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/time";
  export default {
    components: {Pagination,Times},
    name: "wxbase-wxYy",
    data: function() {
      return {
        wxYy: {},
        wxYys: [],
        wxYyDto: {},
        zjlx:[],
        maps: [],
        jdcyw:[],
        jsryw:[],
        wfywyw:[]
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-wxYy-sidebar");
      _this.getZjlx();
      _this.getAllYwlx();
      _this.maps = Tool.getDeptUser();
    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.wxYyDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.wxYyDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 点击【详情】
       */
      detail(wxYy) {
        let _this = this;
        _this.wxYy = $.extend({}, wxYy);
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.wxYyDto.page = page;
        _this.wxYyDto.size = _this.$refs.pagination.size;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/wxYy/list', _this.wxYyDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.wxYys = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },
      /**
       * 获取证件类型`
       */
      getZjlx(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getSfzjlx', {
        }).then((response)=>{
          let resp = response.data;
          _this.zjlx = resp.content;
        })
      },
      /**
       * 获取业务类型信息
       */
      getAllYwlx(){
        let _this =this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/getAllYwlx').then((res)=>{
          let resp = res.data.content;
          _this.jdcyw = resp.CODE_JDC_YWLX;
          _this.jsryw = resp.CODE_JSR_YWLX;
          _this.wfywyw = resp.CODE_WF_YWLX;
          console.log(_this.jdcyw);
          console.log(_this.jsryw);
          console.log(_this.wfywyw);
        });
      },
    }
  }
</script>