<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">违法车查询</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
            <div style="float: right;margin-right: 100px;line-height: 37px;">
              <div>
                <button  type="button" v-on:click="list(1)" class="btn btn-sm  btn-info btn-round" style="margin-right: 10px">
                  <i class="ace-icon fa fa-book"></i>
                  查询
                </button>
                <a href="javascript:location.replace(location.href);"  class="btn btn-sm   btn-success btn-round">
                  <i class="ace-icon fa fa-refresh"></i>
                  重置
                </a>
              </div>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form>
                <table  style="font-size: 1.1em;width: 100%" class="text-right">
                  <tbody >
                  <tr>
                    <td style="width:7%" >
                      归类：
                    </td>
                    <td style="width: 15%">
                      <select v-model="vehParkLotDto.gl" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in wfgl" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:7%" >
                      车辆类别：
                    </td>
                    <td style="width: 15%">
                      <select v-model="vehParkLotDto.cllb" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in cllb" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width: 7%" >
                      号牌号码：
                    </td>
                    <td style="width: 15%">
<!--                      <input class="form-control" type="text" id="form-field-1"  v-model="vehParkLotDto.hphm" style="text-transform:uppercase"/>-->
                      <to-upper class="form-control" v-model="vehParkLotDto.hphm" idValue="form-field-1" type="toUpper"></to-upper>
                    </td>
                    <td  style="width: 7%">
                      条形码流水号：
                    </td>
                    <td style="width: 15%" >
                      <input class="form-control" type="text" id="form-field-2"  v-model="vehParkLotDto.lsh"/>
                    </td>
                  </tr>
                  <tr style="height: 8px"></tr>
                  <tr>
                    <td  style="width: 7%">
                      入场日期：
                    </td>
                    <td style="width: 15%" >
                      <times v-bind:startTime="startTimeIn"
                             v-bind:endTime="endTimeIn" startId="jcrqStart" endId="jcrqEnd"></times>
                    </td>
                    <td  style="width: 7%">
                      出场日期：
                    </td>
                    <td style="width: 15%" >
                      <times v-bind:startTime="startTimeOut"
                             v-bind:endTime="endTimeOut" startId="ccrqStart" endId="ccrqEnd"></times>
                    </td>
                    <td style="width: 7%" >
                      车辆识别代号：
                    </td>
                    <td style="width: 15%">
<!--                      <input class="form-control" type="text" id="form-field-3"  v-model="vehParkLotDto.clsbdh" style="text-transform:uppercase"/>-->
                      <to-upper class="form-control" v-model="vehParkLotDto.clsbdh" idValue="form-field-3" type="toUpper"></to-upper>
                    </td>
                    <td  style="width: 7%">
                      录入日期：
                    </td>
                    <td style="width: 15%" >
                      <times v-bind:startTime="startTime"
                             v-bind:endTime="endTime" startId="luruStart" endId="luruEnd"></times>
                    </td>
                  </tr>
                  <tr style="height: 8px"></tr>
                  </tbody>
                </table>
              </form>
              <div style="height: 30px;line-height: 30px;background-color: #F5F5F5" v-on:click="showMore()" id="topMore">
                <span style="margin-left: 47%">更多查询 <i class="ace-icon fa fa-chevron-down"></i></span>
              </div>
              <form>
                <table  style="font-size: 1.1em;width: 100%;display: none" class="text-right" id="moreQuery">
                  <tbody>
                  <tr>
                    <td  style="width: 7%">
                      凭证编号：
                    </td>
                    <td style="width: 15%" >
                      <input class="form-control" type="text" id="form-field-4"  v-model="vehParkLotDto.pzbh"/>
                    </td>
                    <td  style="width: 7%">
                      车身颜色：
                    </td>
                    <td style="width: 15%" >
                      <input class="form-control" type="text" id="form-field-5"  v-model="vehParkLotDto.csys"/>
                    </td>
                    <td  style="width: 7%">
                      发动机号：
                    </td>
                    <td style="width: 15%" >
                      <input class="form-control" type="text" id="form-field-6"  v-model="vehParkLotDto.fdjh"/>
                    </td>
                    <td  style="width: 7%">
                      大队名称：
                    </td>
                    <td style="width: 15%" >
                      <input class="form-control" type="text" id="form-field-7"  v-model="vehParkLotDto.fxjgmc"/>
                    </td>
                  </tr>
                  <tr style="height: 8px"></tr>
                  <tr>
                    <td  style="width: 7%">
                      停车场：
                    </td>
                    <td style="width: 15%" >
                      <select v-model="vehParkLotDto.sysOrgCode" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option v-for="item in sysOrgCodes" v-bind:value="item.deptcode">{{item.deptname}}</option>
                      </select>
                    </td>
                    <td  style="width: 7%">
                      停放位置：
                    </td>
                    <td style="width: 15%" >
                      <input class="form-control" type="text" id="form-field-8"  v-model="vehParkLotDto.tfwz"/>
                    </td>
                    <td  style="width: 7%">
                      状态：
                    </td>
                    <td style="width: 15%" >
                      <select v-model="vehParkLotDto.zt" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option value="1">入场</option>
                        <option value="2">出场</option>
                      </select>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </form>
              <div style="height: 30px;line-height: 30px;background-color: #F5F5F5;display: none" v-on:click="hiddenMore()" id="bottomMore">
                <span style="margin-left: 47%">隐藏查询 <i class="ace-icon fa fa-chevron-up"></i></span>
              </div>
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
        <th>归类</th>
        <th>条形码流水号</th>
        <th>号牌号码</th>
        <th>车辆识别代号</th>
        <th>发动机号</th>
        <th>车辆类别</th>
<!--        <th>车牌颜色</th>-->
        <th>车身颜色</th>
<!--        <th>车主姓名</th>-->
<!--        <th>身份证明号码</th>-->
        <th>凭证编号</th>
<!--        <th>机动车序号</th>-->
<!--        <th>档案编号</th>-->
<!--        <th>详细地址</th>-->
<!--        <th>违法时间</th>-->
<!--        <th>违法地点</th>-->
<!--        <th>违法地址</th>-->
<!--        <th>执勤民警</th>-->
        <th>大队名称</th>
<!--        <th>大队代码</th>-->
<!--        <th>拯救司机姓名</th>-->
<!--        <th>拯救车编号</th>-->
<!--        <th>违法代码</th>-->
        <th>入场日期</th>
<!--        <th>验车员</th>-->
        <th>停放位置</th>
        <th>出场日期</th>
        <th>录入日期</th>
<!--        <th>备注</th>-->
<!--        <th>出厂原因</th>-->
<!--        <th>移交单位</th>-->
<!--        <th>移交单位名称</th>-->
<!--        <th>出场接收人</th>-->
<!--        <th>出场人联系电话</th>-->
<!--        <th>出场描述</th>-->
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehParkLot in vehParkLots">
        <td>{{wfgl|optionMapKV(vehParkLot.gl)}}</td>
        <td>{{ vehParkLot.lsh }}</td>
        <td>{{ vehParkLot.hphm }}</td>
        <td>{{ vehParkLot.clsbdh }}</td>
        <td>{{ vehParkLot.fdjh }}</td>
        <td>{{cllb|optionMapKV(vehParkLot.cllb)}}</td>
<!--        <td>{{cplx|optionMapKV(vehParkLot.cpys)}}</td>-->
        <td>{{ vehParkLot.csys }}</td>
<!--        <td>{{ vehParkLot.syr }}</td>-->
<!--        <td>{{ vehParkLot.sfzmhm }}</td>-->
        <td>{{ vehParkLot.pzbh }}</td>
<!--        <td>{{ vehParkLot.xh }}</td>-->
<!--        <td>{{ vehParkLot.dabh }}</td>-->
<!--        <td>{{ vehParkLot.zsxxdz }}</td>-->
<!--        <td>{{ vehParkLot.wfsj }}</td>-->
<!--        <td>{{ vehParkLot.wfdd }}</td>-->
<!--        <td>{{ vehParkLot.wfdz }}</td>-->
<!--        <td>{{ vehParkLot.zqmj }}</td>-->
        <td>{{ vehParkLot.fxjgmc }}</td>
<!--        <td>{{ vehParkLot.fzjg }}</td>-->
<!--        <td>{{ vehParkLot.sjxm }}</td>-->
<!--        <td>{{ vehParkLot.zjcbh }}</td>-->
<!--        <td>{{ vehParkLot.wfdm }}</td>-->
        <td>{{ vehParkLot.rcrq }}</td>
<!--        <td>{{ vehParkLot.ycy }}</td>-->
        <td>{{ vehParkLot.tfwz }}</td>
        <td>{{ vehParkLot.ccrq }}</td>
        <td>{{ vehParkLot.createTime }}</td>
<!--        <td>{{ vehParkLot.bz }}</td>-->
<!--        <td>{{ vehParkLot.ccyy }}</td>-->
<!--        <td>{{ vehParkLot.yjdw }}</td>-->
<!--        <td>{{ vehParkLot.yjdwmc }}</td>-->
<!--        <td>{{ vehParkLot.ccjsr }}</td>-->
<!--        <td>{{ vehParkLot.ccrlxdh }}</td>-->
<!--        <td>{{ vehParkLot.ccms }}</td>-->
        <td><span v-if="vehParkLot.zt =='1'">入场</span><span v-if="vehParkLot.zt =='2'">出场</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="detail(vehParkLot)" class="btn btn-xs btn-info" title="详情">
              <i class="ace-icon fa fa-list bigger-120"></i>
            </button>
            <button v-on:click="makeQrcode(vehParkLot)" class="btn btn-xs btn-info" title="二维码">
              <i class="ace-icon fa fa-qrcode bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document"  style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">违法车信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">归类</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wfgl|optionMapKV(vehParkLot.gl)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">条形码流水号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.lsh}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">号牌号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.hphm}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">车辆识别代号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.clsbdh}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">发动机号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.fdjh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">车辆类别</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{cllb|optionMapKV(vehParkLot.cllb)}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">车牌颜色</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{cplx|optionMapKV(vehParkLot.cpys)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">车身颜色</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.csys}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">车主姓名</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.syr}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">身份证明号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{decryptByDES(vehParkLot.sfzmhm)}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">凭证编号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.pzbh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">机动车序号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.xh}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">档案编号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.dabh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">详细地址</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.zsxxdz}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">违法时间</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.wfsj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">违法地点</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.wfdd}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">违法地址</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.wfdz}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">执勤民警</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.zqmj}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">大队名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.fxjgmc}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">大队代码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.fzjg}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">拯救司机姓名</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.sjxm}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">拯救车编号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.zjcbh}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">违法代码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.wfdm}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">入场日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.rcrq}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">验车员</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.ycy}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">停放位置</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.tfwz}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">出场日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.ccrq}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">备注</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.bz}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">出厂原因</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{ccyy|optionMapKV(vehParkLot.ccyy)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">移交单位</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.yjdw}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">移交单位名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.yjdwmc}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">出场接收人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.ccjsr}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">出场人联系电话</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{decryptByDES(vehParkLot.ccrlxdh)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">出场描述</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.ccms}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">录入人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.createBy}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">录入日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.createTime}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">修改人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.updateBy}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">修改时间</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.updateTime}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">停车场</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{maps|optionMapKV(vehParkLot.sysOrgCode)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">状态</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="vehParkLot.zt =='1'">入场</span><span v-if="vehParkLot.zt =='2'">出场</span></span>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>

    <div id="form-modal1" class="modal fade" tabindex="-1" role="dialog" style="text-align: center;margin-top: 10%;">
      <img :src="qrcodeSrc" id="ewmImg"/>
      <div style="width: 360px;margin: 0 auto;background-color: #fff;padding-bottom: 20px;">
        <button type="button" class="btn btn-primary" v-on:click="dayin()">打印</button>
        <button type="button" class="btn btn-primary" v-on:click="guanbi()" style="margin-left: 20px">关闭</button>
      </div>
    </div>
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import Times from "../../components/times";
  import ToUpper from "../../components/upperLowerTransform";
  export default {
    components: {Pagination,Times,ToUpper},
    name: "wxbase-vehParkLotQuery",
    data: function() {
      return {
        vehParkLot: {},
        vehParkLots: [],
        vehParkLotDto:{},
        cllb:[],
        wfgl:[],
        cplx:[],
        sysOrgCodes:[],
        maps:[],
        ccyy:[],
        qrcodeMap:[],
        qrcodeSrc:''
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-vehParkLot-sidebar");
      _this.getCllb();
      _this.getWfgl();
      _this.getCplx();
      _this.maps = Tool.getDeptUser();
      _this.getCcyy();
      //获取停车场列表
      _this.getTcc();
    },
    methods: {
      //DES 解密
      decryptByDES(ciphertext) {
        if(ciphertext != undefined){
          let key = "11111111";
          let keyHex = CryptoJS.enc.Utf8.parse(key);
          let decrypted = CryptoJS.DES.decrypt({
            ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
          }, keyHex, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7,
          });
          return decrypted.toString(CryptoJS.enc.Utf8);
        }
      },
      /**
       * 录入开始时间
       */
      startTime(rep){
        let _this = this;
        _this.vehParkLotDto.stime = rep;
        _this.$forceUpdate();
      },

      /**
       * 录入结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehParkLotDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 进场开始时间
       */
      startTimeIn(rep){
        let _this = this;
        _this.vehParkLotDto.startTimeIn = rep;
        _this.$forceUpdate();
      },

      /**
       * 进场结束时间
       */
      endTimeIn(rep){
        let _this = this;
        _this.vehParkLotDto.endTimeIn = rep;
        _this.$forceUpdate();
      },

      /**
       * 出场开始时间
       */
      startTimeOut(rep){
        let _this = this;
        _this.vehParkLotDto.startTimeOut = rep;
        _this.$forceUpdate();
      },

      /**
       * 出场结束时间
       */
      endTimeOut(rep){
        let _this = this;
        _this.vehParkLotDto.endTimeOut = rep;
        _this.$forceUpdate();
      },

      /**
       * 点击详情
       */
      detail(vehParkLot) {
        let _this = this;
        _this.vehParkLot = $.extend({}, vehParkLot);
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        // let loginUser = Tool.getLoginUser();
        // if(loginUser && loginUser.deptcode){
        //   _this.vehParkLotDto.sysOrgCode=loginUser.deptcode;
        // }else{
        //   Toast.warning("loginUser已失效，请重新登录");
        //   return;
        // }
        Loading.show();
        _this.vehParkLotDto.page=page;
        _this.vehParkLotDto.size=_this.$refs.pagination.size;
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehParkLot/list', _this.vehParkLotDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehParkLots = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      /**
       * 显示更多查询
       */
      showMore(){
        $("#topMore").css("display","none");
        $("#moreQuery").css("display","inline-table");
        $("#bottomMore").css("display","block");
      },

      /**
       * 隐藏查询
       */
      hiddenMore(){
        $("#topMore").css("display","block");
        $("#moreQuery").css("display","none");
        $("#bottomMore").css("display","none");
      },

      /**
       * 获取车辆类别
       */
      getCllb(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getCllb', {
        }).then((response)=>{
          let resp = response.data;
          _this.cllb = resp.content;
        })
      },

      /**
       * 获取违法车归类
       */
      getWfgl(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWfgl', {
        }).then((response)=>{
          let resp = response.data;
          _this.wfgl = resp.content;
        })
      },

      /**
       * 获取车牌类型（车牌颜色）
       */
      getCplx(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getCplx', {
        }).then((response)=>{
          let resp = response.data;
          _this.cplx = resp.content;
        })
      },

      /**
       *获取停车场
       */
      getTcc(){
        let _this = this;
        let formData = new FormData();
        formData.append("depttype","C");
        // formData.append("depttype","A");
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDepts',
            formData
        ).then((response) => {
          let resp = response.data;
          if (resp.success) {
            _this.sysOrgCodes = resp.content;
          } else {
            Dialog({message: resp.message});
          }
        })
      },

      /**
       * 获取出场原因
       */
      getCcyy(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getCcyy', {
        }).then((response)=>{
          let resp = response.data;
          _this.ccyy = resp.content;
        })
      },

      /**
       * 获取二维码信息
       * @param vehParkLot
       */
      makeQrcode(vehParkLot){
        let _this = this;
        console.log(_this.qrcodeMap.keys());
        if(_this.qrcodeMap[vehParkLot.id]){
          _this.qrcodeSrc = _this.qrcodeMap[vehParkLot.id];
        }else{
          let param = {
            '条形码流水号':vehParkLot.lsh,
            '号牌号码':vehParkLot.hphm,
            '车辆识别代号':vehParkLot.clsbdh,
            '凭证编号':vehParkLot.pzbh
          };
          _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/qrcode/vehParkLot/getQRImage', param,{responseType: 'arraybuffer'}).then((response)=>{
            let bufferUrl = btoa(new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), ''));
            _this.qrcodeSrc =  "data:image/jpeg;base64," + bufferUrl;
            _this.qrcodeMap[vehParkLot.id] = _this.qrcodeSrc;
          })
        }
        $("#form-modal1").modal("show");
      },
      guanbi(){
        $("#form-modal1").modal("hide");
      },
      /**
       * 打印二维码
       */
      dayin(){
        $("#ewmImg").jqprint();
      }
    }
  }
</script>