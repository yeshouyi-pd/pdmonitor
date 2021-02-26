<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">随手拍举报审核</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;width: 80%" class="text-right  " >
                  <tbody  >
                  <tr  >
                    <td style="width: 6%" >
                      车牌类型：
                    </td>
                    <td style="width: 8%">
                      <select v-model="webchatIllegalDto.cplx" class="form-control">
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in allcplx" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      车牌号码：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-3"  v-model="webchatIllegalDto.cphm"/>
                    </td>
                    <td style="width: 8%" >
                      身份证明号码：
                    </td>
                    <td style="width: 10%">
                      <input class="form-control" type="text" id="form-field-2"  v-model="webchatIllegalDto.sfzmhm"/>
                    </td>
                    <td style="width: 8%" >
                      手机号：
                    </td>
                    <td style="width: 10%">
                      <input class="form-control" type="text" id="form-field-5"  v-model="webchatIllegalDto.sjh"/>
                    </td>

                  </tr>
                  <tr style="height: 10px"></tr>
                  <tr>
                    <td style="width: 6%" >
                      违法类别：
                    </td>
                    <td style="width: 8%">
                      <select v-model="webchatIllegalDto.wflb" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in wflb" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
<!--                    <td style="width: 6%">-->
<!--                      奖励发放：-->
<!--                    </td>-->
<!--                    <td style="width: 8%" >-->
<!--                      <select v-model="webchatIllegalDto.jlff"  class="form-control" style="width: 95%">-->
<!--                        <option value="" selected>请选择</option>-->
<!--                        <option value="0" >未发放</option>-->
<!--                        <option value="1">已发放</option>-->
<!--                      </select>-->
<!--                    </td>-->
                    <td  style="width: 6%">
                      违法时间：
                    </td>
                    <td style="width: 10%" >
                      <times v-bind:startTime="startTime"
                             v-bind:endTime="endTime"></times>
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
          <th>车牌类型</th>
          <th>车牌号码</th>
          <th>违法类别</th>
          <th>违法行为</th>
          <th>违法时间</th>
          <th>违法路段</th>
          <th>举报人</th>
          <th>身份证明号码</th>
          <th>手机号</th>
          <th>举报时间</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <tr v-for="webchatIllegal in webchatIllegals">
          <td>{{allcplx|optionMapKV(webchatIllegal.cplx)}}</td>
          <td>{{webchatIllegal.cphm}}</td>
          <td>{{wflb|optionMapKV(webchatIllegal.wflb)}}</td>
          <td>{{webchatIllegal.wfxw}}</td>
          <td>{{webchatIllegal.wfsj}}</td>
          <td>{{webchatIllegal.wfld}}</td>
          <td>{{webchatIllegal.jbr}}</td>
          <td>{{decryptByDES(webchatIllegal.sfzmhm)}}</td>
          <td>{{decryptByDES(webchatIllegal.sjh)}}</td>
          <td>{{webchatIllegal.createTime}}</td>
          <td><span v-if="webchatIllegal.zt =='1'">已上报</span><span v-else-if="webchatIllegal.zt =='2'">审核通过</span><span v-else-if="webchatIllegal.zt =='3'">审核不通过</span></td>
          <td>
            <div class="hidden-sm hidden-xs btn-group">
              <button v-on:click="edit(webchatIllegal)" class="btn btn-xs btn-info">
                <i class="ace-icon fa fa-pencil bigger-120"></i>
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
            <h4 class="modal-title">随手拍详细信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">

              <h6 class="header blue lighter less-margin">随手拍基本信息</h6>
              <div class="profile-user-info profile-user-info-striped">


                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">录入人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.createBy}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">录入日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.createTime}}</span>
                  </div>
                </div>


                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">所属部门</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{maps|optionMapKV(webchatIllegal.sysOrgCode)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">违法行为描述</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.wfxwms}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">违法类别</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wflb|optionMapKV(webchatIllegal.wflb)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">违法行为</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.wfxw}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">是否车载照片</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="webchatIllegal.czzp =='1'">是</span><span v-if="webchatIllegal.czzp =='0'">否</span></span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">违法时间</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.wfsj}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">违法路段</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.wfld}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">坐标信息</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.wfgps}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">详细地址</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.wfldxxdz}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">车牌类型</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{allcplx|optionMapKV(webchatIllegal.cplx)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">车牌号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.cphm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">举报人</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.jbr}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">身份证明号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.sfzmhm}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">手机号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.sjh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">微信用户id</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.openid}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">流水号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.lsh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">奖励发放状态</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="webchatIllegal.jlff =='0'">未发放</span><span v-if="webchatIllegal.jlff =='1'">已发放</span></span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">违法行为详细</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{wfxw|optionMapKV(webchatIllegal.wfxwxx)}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">备注</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{webchatIllegal.bz}}</span>
                  </div>
                </div>
              </div>

              <h6 class="header blue lighter less-margin">举报图片</h6>
              <showpic v-bind:pics="pics"></showpic>

              <h6 class="header blue lighter less-margin">举报视频</h6>
              <div id="videodiv" style="width: 50%;margin-left: 1%">
                <video-player  class="video-player vjs-custom-skin"
                               ref="videoPlayer"
                               :playsinline="true"
                               :options="playerOptions"
                ></video-player>
              </div>

              <h6 class="header blue lighter less-margin">审核信息</h6>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核结果</label>
                <div class="col-sm-10">
                  <div class="radio">
                    <label>
                      <input name="form-field-radio" type="radio" class="ace" value="2" v-model="webchatIllegal.zt"/>
                      <span class="lbl">申请通过</span>
                    </label>
                    <label>
                      <input name="form-field-radio" type="radio" class="ace"  value="3" v-model="webchatIllegal.zt"/>
                      <span class="lbl">申请不通过</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">违法行为详细</label>
                <div class="col-sm-4">
                  <select v-model="webchatIllegal.wfxwxx" class="form-control">
                    <option v-for="(value, key) in wfxw" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核原因（非必填项，审核不通过需要填写）</label>
                <div class="col-sm-8">
                  <select v-model="webchatIllegal.shyy" class="form-control" >
                    <option v-for="(value, key) in shyy" v-bind:value="key">{{value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">审核说明</label>
                <div class="col-sm-8">
                  <input v-model="webchatIllegal.shsm" class="form-control">
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
import Showpic from "../../components/showpic";
import { videoPlayer } from 'vue-video-player';
import 'video.js/dist/video-js.css';
import 'vue-video-player/src/custom-theme.css';

export default {
  components: {Pagination,Times,Showpic,videoPlayer},
  name: "wxbase-webchatIllegal",
  data: function() {
    return {
      // videoDialogVisible: false, // 控制弹出层
      // events: ['fullscreenchange'],
      webchatIllegal: {},
      webchatIllegalDto: {},
      webchatReportIllcodeDto: {},
      webchatIllegals: [],
      maps: [],
      usersMap:[],
      allcplx: [],
      shyy:[],
      wfxw:[],
      wflb:[],
      pics:[],
      videos: [],
      images: [],
      urls: [],
      playerOptions : {
        playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [{
          type: "",//这里的种类支持很多种：基本视频格式、直播、流媒体等，具体可以参看git网址项目
          src: "" //url地址
        }],
        poster: "../../static/images/test.jpg", //你的封面地址
        // width: document.documentElement.clientWidth, //播放器宽度
        notSupportedMessage: '此视频暂无法播放，请稍后再试', //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true  //全屏按钮
        }
      },
    }
  },
  mounted: function() {
    let _this = this;
    _this.$refs.pagination.size = 10;
    _this.getWflb();
    _this.getCplx();
    _this.getWfxw();
    _this.list(1);
    _this.getShyy();
    _this.maps = Tool.getDeptUser() ;
    _this.usersMap = Tool.getUserCode();
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
    // onPlayerFullScreenchange (player) {
    //   player.exitFullscreen() //强制退出全屏，恢复正常大小
    //   this.videoDialogVisible = true
    // },
    /**
     * 获取审核原因
     */
    getShyy(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getShyy', {
      }).then((response)=>{
        let resp = response.data;
        _this.shyy = resp.content;
      })
    },
    /**
     * 获取违法行为
     */
    getWfxw(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWfxw', {
      }).then((response)=>{
        let resp = response.data;
        _this.wfxw = resp.content;
      })
    },
    /**
     * 获取车牌类型
     */
    getCplx(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getCplx', {
      }).then((response)=>{
        let resp = response.data;
        _this.allcplx = resp.content;
      })
    },
    /**
     * 获取违法类别
     */
    getWflb(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWflb', {
      }).then((response)=>{
        let resp = response.data;
        _this.wflb = resp.content;
      })
    },
    /**
     *开始时间
     */
    startTime(rep){
      let _this = this;
      _this.webchatIllegalDto.stime = rep;
      _this.$forceUpdate();
    },
    /**
     *结束时间
     */
    endTime(rep){
      let _this = this;
      _this.webchatIllegalDto.etime = rep;
      _this.$forceUpdate();
    },
    /**
     * 点击【新增】
     */
    add() {
      let _this = this;
      _this.webchatIllegal = {};
      $("#form-modal").modal("show");
    },

    /**
     * 点击【编辑】
     */
    edit(webchatIllegal) {
      let _this = this;
      _this.webchatIllegal = $.extend({}, webchatIllegal);
      _this.webchatIllegal.sfzmhm = _this.decryptByDES(webchatIllegal.sfzmhm);
      _this.webchatIllegal.sjh = _this.decryptByDES(webchatIllegal.sjh);
      _this.webchatIllegal.zt = '2';
      _this.getWfxw(webchatIllegal);
      _this.getWflb();
      _this.getFileInfo(_this.webchatIllegal.lsh,"1");
      _this.getFileInfo(_this.webchatIllegal.lsh,"2");
      $("#form-modal").modal("show");
    },
    /**
     * 获取当前信息的图片
     */
    getFileInfo(lsh,zplx){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatFileinfo/getFileinfoByLsh/'+ lsh+"/"+zplx).then((response)=>{
        let resp = response.data;
        if("1" == zplx){
          _this.pics = resp.content;
        }else{
          if(null != resp.content[0]){
            this.playerOptions['sources'][0]['src'] = process.env.VUE_APP_SERVER + resp.content[0].zplj;
            $("#videodiv").show();
          }else{
            this.playerOptions['sources'][0]['src'] = "";
            $("#videodiv").hide();
          }
        }
      })
    },
    /**
     * 列表查询
     */
    list(page) {
      let _this = this;
      _this.webchatIllegalDto.page=page;
      _this.webchatIllegalDto.size=_this.$refs.pagination.size;
      _this.webchatIllegalDto.zt="1";
      _this.$forceUpdate();
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatIllegal/list',_this.webchatIllegalDto).then((response)=>{
        Loading.hide();
        let resp = response.data;
        _this.webchatIllegals = resp.content.list;
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
          || !Validator.length(_this.webchatIllegal.zt, "状态", 1, 2)
          || !Validator.require(_this.webchatIllegal.zt, "审核结果")
          || !Validator.require(_this.webchatIllegal.wfxwxx, "违法行为详细")
          || !Validator.length(_this.webchatIllegal.shr, "审核人", 1, 128)
          || !Validator.length(_this.webchatIllegal.shyy, "审核原因", 1, 200)
          || !Validator.length(_this.webchatIllegal.shsm, "审核说明", 1, 4000)
      ) {
        return;
      }
      if(_this.webchatIllegal.zt == 3 && Tool.isEmpty(_this.webchatIllegal.shyy)){
        Toast.warning("请填写审核原因");
        return false;
      }
      if(_this.webchatIllegal.zt == 3 && Tool.isEmpty(_this.webchatIllegal.shsm)){
        Toast.warning("请填写审核说明");
        return false;
      }
      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatIllegal/save', _this.webchatIllegal).then((response)=>{
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
      Confirm.show("删除后不可恢复，确认删除？", function () {
        Loading.show();
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatIllegal/delete/' + id).then((response)=>{
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

