<template>
  <div>
    <div id="form-modal-onlyread" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document"  style="width:100%">
        <div class="modal-content">
          <div class="modal-header">
            <button v-on:click="back()" class="btn  btn-info btn-round">
              <i class="ace-icon fa fa-backward"></i>
              返回
            </button>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <h6 class="header blue lighter less-margin">事故信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">流水号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.lsh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">事故发生时间</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.sgfssj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">事故地点</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.sgdd}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">事故情形</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{sgqx|optionMapKV(vehAccident.sgqx)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">行政区划</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.xzqh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">管理部门</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{maps|optionMapKV(vehAccident.glbm)}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">是否高架/高速</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.sfgj=='1'">是</span>
                    <span class="editable" v-if="vehAccident.sfgj=='2'">否</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">天气</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.tq}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">处理方式</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.clfs=='1'">自行商定</span>
                    <span class="editable" v-if="vehAccident.clfs=='2'">远程定责</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">审核人</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.shr}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">审核原因</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.shyy}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">审核结果</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.shsm=='1'">审核通过</span>
                    <span class="editable" v-if="vehAccident.shsm=='2'">审核不通过</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">定责民警</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dzmj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">定责时间</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dzsj}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">备注</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.bz}}</span>
                  </div>
                </div>
              </div>
              <h6 class="header blue lighter less-margin">事故图片</h6>
              <showpic v-bind:pics="pics"></showpic>
              <h6 class="header blue lighter less-margin">当事人1信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">当事人姓名</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrxm1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">身份证号码</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrsfzmhm1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">手机号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrsjhm1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">号牌种类</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{hpzl|optionMapKV(vehAccident.hpzl1)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">号牌号码</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.hphm1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">交通方式</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrjtfs1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">车损部位</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrcsbw1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">事故责任</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.dsrbsgzr1=='1'">全责</span>
                    <span class="editable" v-if="vehAccident.dsrbsgzr1=='2'">无责</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">违法行为</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrwfxw1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">违法条款</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrwftk1}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">保险公司</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{depts|optionMapKV(vehAccident.dsrbxgs1)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">保险期止</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrbxqz1}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">当事人所属</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.dsrss1=='1'">甲方</span>
                    <span class="editable" v-if="vehAccident.dsrss1=='2'">已方</span>
                  </div>
                  <div class="profile-info-name"  style="width: 8%">保险凭证号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrbxpzh1}}</span>
                  </div>
                </div>
              </div>
              <h6 class="header blue lighter less-margin">当事人2信息</h6>
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">当事人姓名</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrxm2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">身份证号码</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrsfzmhm2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">手机号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrsjhm2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">号牌种类</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{hpzl|optionMapKV(vehAccident.hpzl2)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">号牌号码</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.hphm2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">交通方式</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrjtfs2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">车损部位</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrcsbw2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">事故责任</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.dsrbsgzr2=='1'">全责</span>
                    <span class="editable" v-if="vehAccident.dsrbsgzr2=='2'">无责</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">违法行为</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrwfxw2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">违法条款</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrwftk2}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">保险公司</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{depts|optionMapKV(vehAccident.dsrbxgs2)}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 8%">保险期止</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrbxqz2}}</span>
                  </div>
                </div>
                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 8%">当事人所属</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable" v-if="vehAccident.dsrss2=='1'">甲方</span>
                    <span class="editable" v-if="vehAccident.dsrss2=='2'">已方</span>
                  </div>
                  <div class="profile-info-name"  style="width: 8%">保险凭证号</div>
                  <div class="profile-info-value" style="width: 22%">
                    <span class="editable">{{vehAccident.dsrbxpzh2}}</span>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showpic from "@/components/showpic";
  export default {
    name: "wxbase-vehAccidentDetail",
    components: {showpic},
    data: function() {
      return {
        vehAccident: {},
        vehAccidents: [],
        pics:[],
        hpzl:[],
        type:'',
        maps: [],
        sgqx: [],
        depts: [],
      }
    },
    mounted: function() {
      let _this = this;
      _this.vehAccident = SessionStorage.get(SESSION_KEY_ACCIDENT)|| {};
      _this.type = _this.$route.query.type;
      if(Tool.isEmpty(_this.vehAccident)){
        _this.$router.push("/welcome");
      }else{
        _this.getFileInfo(_this.vehAccident.lsh);
      }
      _this.getHpzl();
      _this.maps = Tool.getDeptUser() ;
      _this.getSgqx();
      _this.getDeptMap();
    },
    methods: {
      back(){
        let _this = this;
        if(_this.type=='query'){
          _this.$router.push("/wxbase/vehAccident");
        }else if(_this.type=='check'){
          _this.$router.push("/wxbase/vehAccidentCheck");
        }else if(_this.type=='responsibility'){
          _this.$router.push("/wxbase/vehAccidentResponsibility");
        }
      },
      getFileInfo(lsh){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatFileinfo/getFileinfoByLsh/'+ lsh).then((response)=>{
          let resp = response.data;
          _this.pics = resp.content;
        })
      },
      /**
       * 获取号牌种类
       */
      getHpzl(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getHpzl', {
        }).then((response)=>{
          let resp = response.data;
          _this.hpzl = resp.content;
        })
      },
      /**
       * 获取事故情形
       */
      getSgqx(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getSgqx', {
        }).then((response)=>{
          let resp = response.data;
          _this.sgqx = resp.content;
        })
      },
      /**
       * 获取保险公司
       */
      getDeptMap(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDeptMap', {
        }).then((response)=>{
          let resp = response.data;
          _this.depts = resp.content;
        })
      }
    }
  }
</script>