<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>


    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>归类</th>
        <th>条形码流水号</th>
        <th>号牌号码</th>
<!--        <th>车辆识别代号</th>-->
<!--        <th>发动机号</th>-->
        <th>车辆类别</th>
<!--        <th>车牌颜色</th>-->
<!--        <th>车身颜色</th>-->
<!--        <th>车主姓名</th>-->
<!--        <th>身份证明号码</th>-->
        <th>凭证编号</th>
        <th>机动车序号</th>
<!--        <th>档案编号</th>-->
<!--        <th>详细地址</th>-->
<!--        <th>违法时间</th>-->
<!--        <th>违法地点</th>-->
<!--        <th>违法地址</th>-->
        <th>执勤民警</th>
        <th>大队名称</th>
<!--        <th>大队代码</th>-->
<!--        <th>拯救司机姓名</th>-->
        <th>拯救车编号</th>
        <th>违法代码</th>
        <th>入场日期</th>
<!--        <th>验车员</th>-->
        <th>停放位置</th>
<!--        <th>出场日期</th>-->
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
<!--        <td>{{ vehParkLot.clsbdh }}</td>-->
<!--        <td>{{ vehParkLot.fdjh }}</td>-->
        <td>{{cllb|optionMapKV(vehParkLot.cllb)}}</td>
<!--        <td>{{ vehParkLot.cpys }}</td>-->
<!--        <td>{{ vehParkLot.csys }}</td>-->
<!--        <td>{{ vehParkLot.syr }}</td>-->
<!--        <td>{{ vehParkLot.sfzmhm }}</td>-->
        <td>{{ vehParkLot.pzbh }}</td>
        <td>{{ vehParkLot.xh }}</td>
<!--        <td>{{ vehParkLot.dabh }}</td>-->
<!--        <td>{{ vehParkLot.zsxxdz }}</td>-->
<!--        <td>{{ vehParkLot.wfsj }}</td>-->
<!--        <td>{{ vehParkLot.wfdd }}</td>-->
<!--        <td>{{ vehParkLot.wfdz }}</td>-->
        <td>{{ vehParkLot.zqmj }}</td>
        <td>{{ vehParkLot.fxjgmc }}</td>
<!--        <td>{{ vehParkLot.fzjg }}</td>-->
<!--        <td>{{ vehParkLot.sjxm }}</td>-->
        <td>{{ vehParkLot.zjcbh }}</td>
        <td>{{ vehParkLot.wfdm }}</td>
        <td>{{ vehParkLot.rcrq }}</td>
<!--        <td>{{ vehParkLot.ycy }}</td>-->
        <td>{{ vehParkLot.tfwz }}</td>
<!--        <td>{{ vehParkLot.ccrq }}</td>-->
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
            <button v-on:click="edit(vehParkLot)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="detail(vehParkLot)" class="btn btn-xs btn-info" title="详情">
              <i class="ace-icon fa fa-list bigger-120"></i>
            </button>
            <button v-on:click="makeQrcode(vehParkLot)" class="btn btn-xs btn-info" title="二维码">
              <i class="ace-icon fa fa-qrcode bigger-120"></i>
            </button>
            <button v-on:click="del(vehParkLot.id)" class="btn btn-xs btn-danger" title="删除">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
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
            <h4 class="modal-title">违法车录入</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车辆类别</label>
                      <div class="col-sm-4">
                        <select v-model="vehParkLot.cllb" class="form-control">
                          <option v-for="(value, key) in cllb" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                      <label class="col-sm-1 control-label">号牌号码</label>
                      <div class="col-sm-4">
<!--                        <input v-model="vehParkLot.hphm" class="form-control" style="text-transform:uppercase">-->
                        <to-upper class="form-control" v-model="vehParkLot.hphm" idValue="hphmzd" type="toUpper"></to-upper>
                      </div>
                    </div>
                    <h6 class="header blue lighter less-margin">车辆基本信息</h6>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">归类</label>
                      <div class="col-sm-4">
                        <select v-model="vehParkLot.gl" class="form-control">
                          <option v-for="(value, key) in wfgl" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                      <label class="col-sm-1 control-label">发动机号</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.fdjh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">条形码流水号</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.lsh" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">车牌颜色</label>
                      <div class="col-sm-4">
                        <select v-model="vehParkLot.cpys" class="form-control">
                          <option v-for="(value, key) in cplx" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车辆识别代号</label>
                      <div class="col-sm-4">
<!--                        <input v-model="vehParkLot.clsbdh" class="form-control" style="text-transform:uppercase">-->
                        <to-upper class="form-control" v-model="vehParkLot.clsbdh" idValue="clsbdhzd" type="toUpper"></to-upper>
                      </div>
                      <label class="col-sm-1 control-label">车身颜色</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.csys" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车主姓名</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.syr" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">凭证编号</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.pzbh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">身份证明号码</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.sfzmhm" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">档案编号</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.dabh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">机动车序号</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.xh" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">详细地址</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.zsxxdz" class="form-control">
                      </div>
                    </div>
                    <h6 class="header blue lighter less-margin">违法信息</h6>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">违法时间</label>
                      <div class="col-sm-4">
                        <date-time @methodName="wfDate" idValue="cfDate" style="width:100%" :setValue="vehParkLot.wfsj"></date-time>
                      </div>
                      <label class="col-sm-1 control-label">违法地点</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.wfdd" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">违法地址</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.wfdz" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">执勤民警</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.zqmj" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">大队名称</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.fxjgmc" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">大队代码</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.fzjg" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">拯救司机姓名</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.sjxm" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">违法代码</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.wfdm" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">拯救车编号</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.zjcbh" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">入场日期</label>
                      <div class="col-sm-4">
                        <Date style="width:100%" @methodName="rcDate" idValue="bfDate" :setValue="vehParkLot.rcrq"></Date>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">验车员</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.ycy" class="form-control">
                      </div>
                      <label class="col-sm-1 control-label">停放位置</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.tfwz" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-4">
                        <input v-model="vehParkLot.bz" class="form-control">
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

    <!-- 查看 -->
    <div id="form-modal-onlyread" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document"  style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">违法车录入信息</h4>
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
<!--                <div class="profile-info-row">-->
<!--                  <div class="profile-info-name"  style="width: 10%">出厂原因</div>-->
<!--                  <div class="profile-info-value" style="width: 40%">-->
<!--                    <span class="editable">{{vehParkLot.ccyy}}</span>-->
<!--                  </div>-->
<!--                  <div class="profile-info-name" style="width: 10%">移交单位</div>-->
<!--                  <div class="profile-info-value" style="width: 40%">-->
<!--                    <span class="editable">{{vehParkLot.yjdw}}</span>-->
<!--                  </div>-->
<!--                </div>-->
<!--                <div class="profile-info-row">-->
<!--                  <div class="profile-info-name"  style="width: 10%">移交单位名称</div>-->
<!--                  <div class="profile-info-value" style="width: 40%">-->
<!--                    <span class="editable">{{vehParkLot.yjdwmc}}</span>-->
<!--                  </div>-->
<!--                  <div class="profile-info-name" style="width: 10%">出场接收人</div>-->
<!--                  <div class="profile-info-value" style="width: 40%">-->
<!--                    <span class="editable">{{vehParkLot.ccjsr}}</span>-->
<!--                  </div>-->
<!--                </div>-->
<!--                <div class="profile-info-row">-->
<!--                  <div class="profile-info-name"  style="width: 10%">出场人联系电话</div>-->
<!--                  <div class="profile-info-value" style="width: 40%">-->
<!--                    <span class="editable">{{decryptByDES(vehParkLot.ccrlxdh)}}</span>-->
<!--                  </div>-->
<!--                  <div class="profile-info-name" style="width: 10%">出场描述</div>-->
<!--                  <div class="profile-info-value" style="width: 40%">-->
<!--                    <span class="editable">{{vehParkLot.ccms}}</span>-->
<!--                  </div>-->
<!--                </div>-->
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
<!--                  <div class="profile-info-name"  style="width: 10%">出场日期</div>-->
<!--                  <div class="profile-info-value" style="width: 40%">-->
<!--                    <span class="editable">{{vehParkLot.ccrq}}</span>-->
<!--                  </div>-->
                  <div class="profile-info-name" style="width: 10%">备注</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehParkLot.bz}}</span>
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

    <!-- 查看二维码 -->
    <div id="form-modal-qrcode" class="modal fade" tabindex="-1" role="dialog" style="text-align: center;margin-top: 10%;">
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
  import Date from "../../components/date";
  import DateTime from "../../components/dateTime";
  import ToUpper from "../../components/upperLowerTransform";
  export default {
    components: {Pagination,Date,DateTime,ToUpper},
    name: "wxbase-vehParkLotWrite",
    data: function() {
      return {
        vehParkLot: {},
        vehParkLots: [],
        vehParkLotDto:{},
        cllb:[],
        wfgl:[],
        cplx:[],
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
       * 违法时间
       */
      wfDate(rep){
        let _this = this;
        _this.vehParkLot.wfsj = rep;
        _this.$forceUpdate();
      },

      /**
       * 入场日期
       */
      rcDate(rep){
        let _this = this;
        _this.vehParkLot.rcrq = rep;
        _this.$forceUpdate();
      },

      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehParkLot = {};
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehParkLot) {
        let _this = this;
        _this.vehParkLot = $.extend({}, vehParkLot);
        _this.vehParkLot.sfzmhm = _this.decryptByDES(vehParkLot.sfzmhm);
        _this.vehParkLot.ccrlxdh = _this.decryptByDES(vehParkLot.ccrlxdh);
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        let loginUser = Tool.getLoginUser();
        if(loginUser && loginUser.deptcode){
          _this.vehParkLotDto.sysOrgCode=loginUser.deptcode;
        }else{
          Toast.warning("loginUser已失效，请重新登录");
          return;
        }
        Loading.show();
        _this.vehParkLotDto.page=page;
        _this.vehParkLotDto.size=_this.$refs.pagination.size;
        _this.vehParkLotDto.zt = "1";
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehParkLot/list', _this.vehParkLotDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehParkLots = resp.content.list;
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
                // || !Validator.require(_this.vehParkLot.createBy, "录入人")
                // || !Validator.length(_this.vehParkLot.createBy, "录入人", 1, 128)
                // || !Validator.require(_this.vehParkLot.createTime, "录入日期")
                // || !Validator.require(_this.vehParkLot.updateBy, "修改人")
                // || !Validator.length(_this.vehParkLot.updateBy, "修改人", 1, 128)
                // || !Validator.require(_this.vehParkLot.updateTime, "修改时间")
                // || !Validator.length(_this.vehParkLot.sysOrgCode, "停车场部门 编号", 1, 128)
                || !Validator.require(_this.vehParkLot.gl, "归类")
                || !Validator.length(_this.vehParkLot.gl, "归类", 1, 45)
                || !Validator.require(_this.vehParkLot.lsh, "条形码流水号")
                || !Validator.length(_this.vehParkLot.lsh, "条形码流水号", 1, 45)
                || !Validator.require(_this.vehParkLot.hphm, "号牌号码")
                || !Validator.length(_this.vehParkLot.hphm, "号牌号码", 1, 20)
                || !Validator.require(_this.vehParkLot.clsbdh, "车辆识别代号")
                || !Validator.length(_this.vehParkLot.clsbdh, "车辆识别代号", 1, 45)
                || !Validator.require(_this.vehParkLot.fdjh, "发动机号")
                || !Validator.length(_this.vehParkLot.fdjh, "发动机号", 1, 20)
                || !Validator.require(_this.vehParkLot.cpys, "车牌颜色")
                || !Validator.length(_this.vehParkLot.cpys, "车牌颜色", 1, 2)
                || !Validator.require(_this.vehParkLot.csys, "车身颜色")
                || !Validator.length(_this.vehParkLot.csys, "车身颜色", 1, 2)
                || !Validator.require(_this.vehParkLot.syr, "车主姓名")
                || !Validator.length(_this.vehParkLot.syr, "车主姓名", 1, 45)
                || !Validator.require(_this.vehParkLot.sfzmhm, "身份证明号码")
                || !Validator.lengthCorrect(_this.vehParkLot.sfzmhm, "身份证明号码", 18)
                || !Validator.length(_this.vehParkLot.sfzmhm, "身份证明号码", 1, 18)
                || !Validator.require(_this.vehParkLot.pzbh, "凭证编号")
                || !Validator.length(_this.vehParkLot.pzbh, "凭证编号", 1, 15)
                || !Validator.require(_this.vehParkLot.xh, "机动车序号")
                || !Validator.length(_this.vehParkLot.xh, "机动车序号", 1, 45)
                || !Validator.require(_this.vehParkLot.dabh, "档案编号")
                || !Validator.length(_this.vehParkLot.dabh, "档案编号", 1, 45)
                || !Validator.require(_this.vehParkLot.zsxxdz, "详细地址")
                || !Validator.length(_this.vehParkLot.zsxxdz, "详细地址", 1, 128)
                || !Validator.require(_this.vehParkLot.wfdd, "违法地点")
                || !Validator.length(_this.vehParkLot.wfdd, "违法地点", 1, 128)
                || !Validator.require(_this.vehParkLot.wfdz, "违法地址")
                || !Validator.length(_this.vehParkLot.wfdz, "违法地址", 1, 128)
                || !Validator.require(_this.vehParkLot.zqmj, "执勤民警")
                || !Validator.length(_this.vehParkLot.zqmj, "执勤民警", 1, 45)
                || !Validator.require(_this.vehParkLot.fxjgmc, "大队名称")
                || !Validator.length(_this.vehParkLot.fxjgmc, "大队名称", 1, 128)
                || !Validator.require(_this.vehParkLot.fzjg, "大队代码")
                || !Validator.length(_this.vehParkLot.fzjg, "大队代码", 1, 45)
                || !Validator.require(_this.vehParkLot.sjxm, "拯救司机姓名")
                || !Validator.length(_this.vehParkLot.sjxm, "拯救司机姓名", 1, 128)
                || !Validator.require(_this.vehParkLot.zjcbh, "拯救车编号")
                || !Validator.length(_this.vehParkLot.zjcbh, "拯救车编号", 1, 45)
                || !Validator.require(_this.vehParkLot.wfdm, "违法代码")
                || !Validator.length(_this.vehParkLot.wfdm, "违法代码", 1, 45)
                || !Validator.require(_this.vehParkLot.ycy, "验车员")
                || !Validator.length(_this.vehParkLot.ycy, "验车员", 1, 128)
                || !Validator.require(_this.vehParkLot.tfwz, "停放位置")
                || !Validator.length(_this.vehParkLot.tfwz, "停放位置", 1, 128)
                || !Validator.length(_this.vehParkLot.bz, "备注", 1, 2000)
                || !Validator.length(_this.vehParkLot.ccyy, "出厂原因", 1, 45)
                || !Validator.length(_this.vehParkLot.yjdw, "移交单位", 1, 128)
                || !Validator.length(_this.vehParkLot.yjdwmc, "移交单位名称", 1, 128)
                || !Validator.length(_this.vehParkLot.ccjsr, "出场接收人", 1, 45)
                || !Validator.length(_this.vehParkLot.ccrlxdh, "出场人联系电话", 1, 128)
                || !Validator.length(_this.vehParkLot.ccms, "出场描述", 1, 45)
                || !Validator.length(_this.vehParkLot.zt, "状态1入场2出场", 1, 45)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehParkLot/save', _this.vehParkLot).then((response)=>{
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
        Confirm.show("删除违法车场后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehParkLot/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      },

      /**
       * 点击详情
       */
      detail(vehParkLot){
        let _this = this;
        _this.vehParkLot = $.extend({}, vehParkLot);
        $("#form-modal-onlyread").modal("show");
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
      makeQrcode(vehParkLot){
        let _this = this;
        let param = {
          '条形码流水号':vehParkLot.lsh,
          '号牌号码':vehParkLot.hphm,
          '车辆识别代号':vehParkLot.clsbdh,
          '凭证编号':vehParkLot.pzbh
        };
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/qrcode/vehParkLot/getQRImage', param,{responseType: 'arraybuffer'}).then((response)=>{
          let bufferUrl = btoa(new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), ''));
          _this.qrcodeSrc =  "data:image/jpeg;base64," + bufferUrl;
          $("#form-modal-qrcode").modal("show");
        })
      },
      dayin(){
        $("#ewmImg").jqprint();
      },
      guanbi(){
        $("#form-modal-qrcode").modal("hide");
      }
    }
  }
</script>