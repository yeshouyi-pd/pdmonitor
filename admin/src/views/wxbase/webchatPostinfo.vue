<template>
  <div>
    <p>
      <!--<button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;-->
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>


    <div>
      <table id="simple-table" class="table  table-bordered table-hover">
        <thead>
        <tr>
          <th>接口用户名</th>
          <th>接口用户密码</th>
          <th>合作方唯一识别号</th>
          <th>寄件人省份编号</th>
          <th>寄件人城市编号</th>
          <th>寄件人区域编号</th>
          <th>寄件公司名称</th>
          <th>寄件人姓名</th>
          <th>寄件人手机号码</th>
          <th>更新时间</th>
          <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="webchatPostinfo in webchatPostinfos">
          <td>{{ webchatPostinfo.loginName }}</td>
          <td>{{ webchatPostinfo.loginPwd }}</td>
          <td>{{ webchatPostinfo.distributorOrderid }}</td>
          <td>{{ webchatPostinfo.senderProvinceCode }}</td>
          <td>{{ webchatPostinfo.senderCityCode }}</td>
          <td>{{ webchatPostinfo.senderCountyCode }}</td>
          <td>{{ webchatPostinfo.senderCompany }}</td>
          <td>{{ webchatPostinfo.senderName }}</td>
          <td>{{ webchatPostinfo.senderPhone }}</td>
          <td>{{ webchatPostinfo.updateTime }}</td>

          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(webchatPostinfo)" class="btn btn-xs btn-info" title="详情">
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
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">邮政寄件人信息设置</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">接口用户名</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.loginName" class="form-control">
                </div>
                <label class="col-sm-2 control-label">接口用户密码</label>
                <div class="col-sm-4">
                  <input v-model="webchatPostinfo.loginPwd" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">密钥</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.secretKey" class="form-control">
                </div>
                <label class="col-sm-2 control-label">合作方唯一识别号</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.distributorOrderid" class="form-control">
                </div>

              </div>
              <div class="form-group">

                <label class="col-sm-2 control-label">寄件人省份编号</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.senderProvinceCode" class="form-control">
                </div>
                <label class="col-sm-2 control-label">寄件人城市编号</label>
                <div class="col-sm-4">
                  <input v-model="webchatPostinfo.senderCityCode" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">寄件人区域编号</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.senderCountyCode" class="form-control">
                </div>
                <label class="col-sm-2 control-label">寄件公司名称</label>
                <div class="col-sm-4">
                  <input v-model="webchatPostinfo.senderCompany" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">寄件人姓名</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.senderName" class="form-control">
                </div>
                <label class="col-sm-2 control-label">寄件人手机号码</label>
                <div class="col-sm-4">
                  <input v-model="webchatPostinfo.senderPhone" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">寄件人地址</label>
                <div class="col-sm-9">
                  <input v-model="webchatPostinfo.senderAddress" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">保价金额(分)</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.protectPrice" class="form-control">
                </div>
                <label class="col-sm-2 control-label">保费</label>
                <div class="col-sm-4">
                  <input v-model="webchatPostinfo.insuranceAmount" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">运费(单位分)</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.deliverMoney" class="form-control">
                </div>
                <label class="col-sm-2 control-label">物品名称</label>
                <div class="col-sm-4">
                  <input v-model="webchatPostinfo.goods" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">物品数量</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.goodsQuantity" class="form-control">
                </div>
                <label class="col-sm-2 control-label">物品重量（克）</label>
                <div class="col-sm-4">
                  <input v-model="webchatPostinfo.goodsWeight" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">面单类型</label>
                <div class="col-sm-3">
                  <div class="radio">
                    <label>
                      <input name="emsType" type="radio" class="ace" value="1" v-model="webchatPostinfo.emsType"/>
                      <span class="lbl">标准快递</span>
                    </label>
                    <label>
                      <input name="emsType" type="radio" class="ace" value="0" v-model="webchatPostinfo.emsType"/>
                      <span class="lbl">快递包裹</span>
                    </label>
                  </div>
                </div>
                <label class="col-sm-2 control-label">快递费支付方式</label>
                <div class="col-sm-4">
                  <div class="radio">
                    <label>
                      <input name="whoPay" type="radio" class="ace" value="1" v-model="webchatPostinfo.whoPay"/>
                      <span class="lbl">收件人付款</span>
                    </label>
                    <label>
                      <input name="whoPay" type="radio" class="ace" value="0" v-model="webchatPostinfo.whoPay"/>
                      <span class="lbl">寄件人付款</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">面单号码</label>
                <div class="col-sm-3">
                  <input v-model="webchatPostinfo.mailNo" class="form-control">
                </div>
                <label class="col-sm-2 control-label">其他信息</label>
                <div class="col-sm-4">
                  <input v-model="webchatPostinfo.other" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">备注</label>
                <div class="col-sm-10">
                  <input v-model="webchatPostinfo.demo" class="form-control">
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

export default {
  components: {Pagination},
  name: "wxbase-webchatPostinfo",
  data: function () {
    return {
      webchatPostinfo: {},
      webchatPostinfos: [],
    }
  },
  mounted: function () {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.list(1);
    // sidebar激活样式方法一
    // this.$parent.activeSidebar("wxbase-webchatPostinfo-sidebar");

  },
  methods: {
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.webchatPostinfo = {};
      _this.webchatPostinfo.whoPay = "1";
      _this.webchatPostinfo.emsType = "1";
      _this.webchatPostinfo.deliverMoney = "2000";
      _this.webchatPostinfo.goodsWeight = "400";
      _this.webchatPostinfo.insuranceAmount = "0";
      _this.webchatPostinfo.protectPrice = "0";
      _this.$forceUpdate();
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(webchatPostinfo) {
      let _this = this;
      _this.webchatPostinfo = $.extend({}, webchatPostinfo);
      $("#form-modal").modal("show");
    },

    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatPostinfo/list', {
        page: page,
        size: _this.$refs.pagination.size,
      }).then((response) => {
        Loading.hide();
        let resp = response.data;
        _this.webchatPostinfos = resp.content.list;
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
          || !Validator.require(_this.webchatPostinfo.loginName, "接口用户名")
          || !Validator.length(_this.webchatPostinfo.loginName, "接口用户名", 1, 128)
          || !Validator.require(_this.webchatPostinfo.loginPwd, "接口用户密码")
          || !Validator.length(_this.webchatPostinfo.loginPwd, "接口用户密码", 1, 128)
          || !Validator.require(_this.webchatPostinfo.distributorOrderid, "合作方唯一识别号")
          || !Validator.length(_this.webchatPostinfo.distributorOrderid, "合作方唯一识别号", 1, 128)
          || !Validator.require(_this.webchatPostinfo.secretKey, "密钥")
          || !Validator.length(_this.webchatPostinfo.secretKey, "密钥", 1, 128)
          || !Validator.require(_this.webchatPostinfo.senderProvinceCode, "寄件人省份编号")
          || !Validator.length(_this.webchatPostinfo.senderProvinceCode, "寄件人省份编号", 1, 20)
          || !Validator.require(_this.webchatPostinfo.senderCityCode, "寄件人城市编号")
          || !Validator.length(_this.webchatPostinfo.senderCityCode, "寄件人城市编号", 1, 20)
          || !Validator.require(_this.webchatPostinfo.senderCountyCode, "寄件人区域编号")
          || !Validator.length(_this.webchatPostinfo.senderCountyCode, "寄件人区域编号", 1, 20)
          || !Validator.length(_this.webchatPostinfo.senderCompany, "寄件公司名称", 1, 128)
          || !Validator.require(_this.webchatPostinfo.senderName, "寄件人姓名")
          || !Validator.length(_this.webchatPostinfo.senderName, "寄件人姓名", 1, 128)
          || !Validator.require(_this.webchatPostinfo.senderPhone, "寄件人手机号码")
          || !Validator.length(_this.webchatPostinfo.senderPhone, "寄件人手机号码", 1, 20)
          || !Validator.require(_this.webchatPostinfo.senderAddress, "寄件人地址")
          || !Validator.length(_this.webchatPostinfo.senderAddress, "寄件人地址", 1, 128)
          || !Validator.require(_this.webchatPostinfo.deliverMoney, "运费，单位是分")
          || !Validator.require(_this.webchatPostinfo.goods, "物品名称")
          || !Validator.length(_this.webchatPostinfo.goods, "物品名称", 1, 128)
          || !Validator.require(_this.webchatPostinfo.goodsWeight, "物品重量（单位   克），重量不确定的，就传0")
          || !Validator.length(_this.webchatPostinfo.demo, "备注", 1, 128)
          || !Validator.require(_this.webchatPostinfo.emsType, "面单类型（0：快递包裹；1：标准快递）")
          || !Validator.length(_this.webchatPostinfo.mailNo, "面单号码", 1, 45)
          || !Validator.length(_this.webchatPostinfo.other, "传递Json格式并与邮政协商好的其他信息", 1, 128)
          || !Validator.length(_this.webchatPostinfo.createBy, "创建人", 1, 128)
          || !Validator.length(_this.webchatPostinfo.updateBy, "更新人", 1, 128)
          || !Validator.length(_this.webchatPostinfo.sysOrgCode, "操作部门", 1, 45)
      ) {
        return;
      }

      Loading.show();
      _this.webchatPostinfo.senderAddressType = "0";
      _this.webchatPostinfo.receiverAddressType = "0";
      _this.$forceUpdate();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatPostinfo/save', _this.webchatPostinfo).then((response) => {
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
      Confirm.show("删除邮政快递信息后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatPostinfo/delete/' + id).then((response) => {
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