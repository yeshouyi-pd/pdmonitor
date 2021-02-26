<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">报废信息录入</h4>
            <div class="widget-toolbar">
              <a href="#" data-action="collapse">
                <i class="ace-icon fa fa-chevron-up"></i>
              </a>
            </div>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;width: 100%" class="text-right">
                  <tbody  >
                  <tr  >
                    <td style="width:6%" >
                      号牌种类：
                    </td>
                    <td style="width: 10%">
                      <select v-model="vehLogoutDto.hpzl" class="form-control" >
                        <option value="" selected>请选择</option>
                        <option v-for="(value, key) in hpzl" v-bind:value="key">{{value}}</option>
                      </select>
                    </td>
                    <td style="width:6%" >
                      号牌号码：
                    </td>
                    <td style="width: 8%">
                      <input type="text" id="form-field-1"  class="form-control"  v-model="vehLogoutDto.hphm"/>
                    </td>
                    <td style="width: 8%" >
                      车辆识别代号：
                    </td>
                    <td style="width: 8%">
                      <input class="form-control" type="text" id="form-field-2"  v-model="vehLogoutDto.clsbdh"/>
                    </td>
                    <td  style="width: 6%">
                      录入日期：
                    </td>
                    <td style="width: 15%" >
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
        <th>号牌种类</th>
        <th>号牌号码</th>
        <th>机动车序号</th>
<!--        <th>机动车所有人</th>-->
        <th>车辆识别代号</th>
<!--        <th>车辆品牌</th>-->
        <th>车辆型号</th>
<!--        <th>注销机关</th>-->
<!--        <th>注销日期</th>-->
<!--        <th>经办人</th>-->
<!--        <th>回收企业名称</th>-->
        <th>回收号牌</th>
        <th>回收行驶证</th>
        <th>回收登记证书</th>
        <th>监销民警</th>
        <th>处罚机关</th>
        <th>注销原因</th>
        <th>报废日期</th>
        <th>处罚时间</th>
        <th>录入日期</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehLogout in vehLogouts">
        <td>{{hpzl|optionMapKV(vehLogout.hpzl)}}</td>
        <td>{{vehLogout.hphm}}</td>
        <td>{{vehLogout.xh}}</td>
<!--        <td>{{vehLogout.syr}}</td>-->
        <td>{{vehLogout.clsbdh}}</td>
<!--        <td>{{vehLogout.clpp1}}</td>-->
        <td>{{vehLogout.clxh}}</td>
<!--        <td>{{vehLogout.zxjg}}</td>-->
<!--        <td>{{vehLogout.zxrq}}</td>-->
<!--        <td>{{vehLogout.jbr}}</td>-->
<!--        <td>{{vehLogout.hsqymc}}</td>-->
        <td><span v-if="vehLogout.hshp =='0'">未收回</span><span v-if="vehLogout.hshp =='1'" >回收机动车前号牌</span><span v-if="vehLogout.hshp =='2'">回收机动车后号牌</span><span v-if="vehLogout.hshp =='3'">回收机动车前、后号牌</span></td>
        <td><span v-if="vehLogout.hsxsz =='1'">收回</span><span v-else-if="vehLogout.hsxsz=='0'">未收回</span></td>
        <td><span v-if="vehLogout.hsdjzs =='0'">未收回</span><span v-if="vehLogout.hsdjzs =='1'">收回</span></td>
        <td>{{vehLogout.jxmj}}</td>
        <td>{{vehLogout.cfjg}}</td>
        <td>{{zxyy|optionMapKV(vehLogout.zxyy)}}</td>
        <td>{{vehLogout.bfrq}}</td>
        <td>{{vehLogout.cfsj}}</td>
        <td>{{vehLogout.createTime}}</td>
              <td><span v-if="vehLogout.zt =='1'">已录入</span><span v-if="vehLogout.zt =='2'">审核通过</span><span v-if="vehLogout.zt =='3'">审核不通过</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehLogout)" class="btn btn-xs btn-info" title="修改">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(vehLogout.id)" class="btn btn-xs btn-danger" title="删除">
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
      <div class="modal-dialog" role="document"  style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">报废信息录入</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
                    <div class="form-group">

                      <label class="col-sm-2 control-label">号牌种类*</label>
                      <div class="col-sm-4">
                        <select v-model="vehLogout.hpzl" class="form-control">
                          <option v-for="(value, key) in hpzl" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                      <label class="col-sm-2 control-label">号牌号码*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.hphm" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车辆识别代号*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.clsbdh" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">机动车序号*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.xh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">车辆品牌*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.clpp1" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">车辆型号*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.clxh" class="form-control">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">机动车所有人*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.syr" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">报废日期*</label>
                      <div class="col-sm-4">
<!--                        <input v-model="vehLogout.bfrq" class="form-control">-->
                        <Date style="width:100%" @methodName="bfDate" idValue="bfDate" :setValue="vehLogout.bfrq"></Date>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">处罚决定书编号*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.jdsbh" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">处罚时间*</label>
                      <div class="col-sm-4">
<!--                            <input id="cfDate" class="form-control">-->
                        <date-time @methodName="cfDate" idValue="cfDate" style="width:100%" :setValue="vehLogout.cfsj"></date-time>
                      </div>
                    </div>
<!--                    <div class="form-group">-->
<!--                      <label class="col-sm-2 control-label">注销机关</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="vehLogout.zxjg" class="form-control">-->
<!--                      </div>-->
<!--                      <label class="col-sm-1 control-label">注销日期</label>-->
<!--                      <div class="col-sm-4">-->
<!--&lt;!&ndash;                        <input v-model="vehLogout.zxrq" class="form-control">&ndash;&gt;-->
<!--                        <date @methodName="zxDate" idValue="zxDate" style="width:100%" :setValue="vehLogout.zxrq"></date>-->
<!--                      </div>-->
<!--                    </div>-->
                    <div class="form-group">
                      <label class="col-sm-2 control-label">监销民警*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.jxmj" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">回收号牌*</label>
                      <div class="col-sm-4">
                        <!--                        <input v-model="vehLogout.hshp" class="form-control">-->
                        <select v-model="vehLogout.hshp" class="form-control">
                          <option v-for="(value, key) in hshp" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                    </div>
<!--                    <div class="form-group">-->
<!--                      <label class="col-sm-2 control-label">回收企业名称</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="vehLogout.hsqymc" class="form-control">-->
<!--                      </div>-->
<!--                      <label class="col-sm-1 control-label">回收企业代码</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="vehLogout.sysOrgCode" class="form-control">-->
<!--                      </div>-->
<!--                    </div>-->
                    <div class="form-group">
                      <label class="col-sm-2 control-label">回收登记证书*</label>
                      <div class="col-sm-4">
                        <!--                        <input v-model="vehLogout.hsdjzs" class="form-control">-->
                        <div class="radio">
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="0" v-model="vehLogout.hsdjzs"/>
                            <span class="lbl">未回收</span>
                          </label>
                          <label>
                            <input name="form-field-radio" type="radio" class="ace"  value="1" v-model="vehLogout.hsdjzs"/>
                            <span class="lbl">收回</span>
                          </label>
                        </div>
                      </div>
                      <label class="col-sm-2 control-label">回收行驶证*</label>
                      <div class="col-sm-4">
<!--                        <input v-model="vehLogout.hsxsz" class="form-control">-->
                        <div class="radio">
                          <label>
                            <input name="form-field-radio1" type="radio" class="ace"  value="0" v-model="vehLogout.hsxsz"/>
                            <span class="lbl">未回收</span>
                          </label>
                          <label>
                            <input name="form-field-radio1" type="radio" class="ace"  value="1" v-model="vehLogout.hsxsz"/>
                            <span class="lbl">收回</span>
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">处罚机关*</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.cfjg" class="form-control">
                      </div>
                      <label class="col-sm-2 control-label">注销原因*</label>
                      <div class="col-sm-4">
<!--                        <input v-model="vehLogout.zxyy" class="form-control">-->
                        <select v-model="vehLogout.zxyy" class="form-control">
                          <option v-for="(value, key) in zxyy" v-bind:value="key">{{value}}</option>
                        </select>
                      </div>
                    </div>
                    <div class="form-group">
<!--                      <label class="col-sm-2 control-label">经办人</label>-->
<!--                      <div class="col-sm-4">-->
<!--                        <input v-model="vehLogout.jbr" class="form-control">-->
<!--                      </div>-->
                      <label class="col-sm-2 control-label">备注</label>
                      <div class="col-sm-4">
                        <input v-model="vehLogout.bz" class="form-control">
                      </div>
                    </div>
              <h6 class="header blue lighter less-margin">图片</h6>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">图片</label>
                      <div class="col-sm-10">
                        <show-pic :pics="tpLj"></show-pic>
                        <file input-id="tp1" :suffixs="picSuffixs" :afterUpload="tpAfterUpload"></file>
                      </div>
                    </div>
              <h6 class="header blue lighter less-margin">视频</h6>
                <div class="form-group">
                  <label class="col-sm-2 control-label">视频</label>
                  <div class="col-sm-10">
                    <div id="videodiv" style="width: 50%;margin-left: 1%" v-show="showVideo">
                      <video-player  class="video-player vjs-custom-skin"
                                     ref="videoPlayer"
                                     :playsinline="true"
                                     :options="playerOptions"
                      ></video-player>
                    </div>
                    <file input-id="video" :suffixs="videoSuffixs" :afterUpload="videoAfterUpload" use="video"></file>
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
import Date from "../../components/date";
import DateTime from "../../components/dateTime";
import File from "../../components/file";
import ShowPic from "../../components/showpic";
import { videoPlayer } from 'vue-video-player';
import 'video.js/dist/video-js.css';
import 'vue-video-player/src/custom-theme.css';
export default {
  components: {Pagination,Times,Date,DateTime,File,ShowPic,videoPlayer},
    name: "wxbase-vehLogout",
    data: function() {
      return {
        vehLogout: {},
        vehLogoutDto:{},
        vehLogouts: [],
        hpzl:[],
        maps: [],
        usersMap:[],
        hshp:[],
        hsxsz:[],
        hsdjzs:[],
        zxyy:[],
        picSuffixs:['png','jpg','gif','jpeg'],
        tpLj:[],
        videoSuffixs:['mp4','mpeg','ogg'],
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
        showVideo:false,
        videoLj:''
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.maps = Tool.getDeptUser() ;
      _this.usersMap = Tool.getUserCode();
      _this.getHpzl();
      _this.hshp={0: "未收回", 1: "回收机动车前号牌", 2: "回收机动车后号牌", 3: "回收机动车前、后号牌"};
      _this.hsxsz={0: "未收回", 1: "收回"};
      _this.hsdjzs = $.extend({}, _this.hsxsz);
      _this.getZxyy();
    },
    methods: {
      /**
       *开始时间
       */
      startTime(rep){
        let _this = this;
        _this.vehLogoutDto.stime = rep;
        _this.$forceUpdate();
      },
      /**
       *结束时间
       */
      endTime(rep){
        let _this = this;
        _this.vehLogoutDto.etime = rep;
        _this.$forceUpdate();
      },
      /**
       * 报废日期
       */
      bfDate(rep){
        let _this = this;
        _this.vehLogout.bfrq = rep;
        _this.$forceUpdate();
      },
      /**
       * 处罚时间
       */
      cfDate(rep){
        let _this = this;
        _this.vehLogout.cfsj = rep;
        _this.$forceUpdate();
      },
      /**
       * 注销日期
       */
      // zxDate(rep){
      //   let _this = this;
      //   _this.vehLogout.zxrq = rep;
      //   _this.$forceUpdate();
      // },
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
       * 获取注销原因
       */
      getZxyy(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getZxyy', {
        }).then((response)=>{
          let resp = response.data;
          _this.zxyy = resp.content;
        })
      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.vehLogout = {};
        _this.vehLogout.hsxsz="0";
        _this.vehLogout.hsdjzs="0";
        _this.tpLj = [];
        _this.playerOptions['sources'][0]['src'] = "";
        _this.videoLj = "";
        _this.showVideo = false;
        _this.$forceUpdate();
        $("#form-modal").modal("show");
      },

      /**
       * 点击【编辑】
       */
      edit(vehLogout) {
        let _this = this;
        _this.vehLogout = $.extend({}, vehLogout);
        _this.getFileInfo(_this.vehLogout.lsh,"1");
        _this.getFileInfo(_this.vehLogout.lsh,"2");
        $("#form-modal").modal("show");
      },

      /**
       * 获取当前信息的图片
       */
      getFileInfo(lsh,zplx){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/admin/webchatFileinfo/getFileinfoByLsh/'+ lsh+"/"+zplx).then((response)=>{
          let resp = response.data;
          _this.tpListStr = [];
          _this.videoLj = "";
          if("1" == zplx){
            _this.tpLj = resp.content;
            if(null!=_this.tpLj&&_this.tpLj.length>0){
              for(let i=0;i<_this.tpLj.length;i++){
                _this.tpListStr.push(_this.tpLj[i].zplj);
              }
            }
          }else{
            if(null != resp.content&&null != resp.content[0]){
              _this.showVideo = true;
              _this.playerOptions['sources'][0]['src'] = process.env.VUE_APP_SERVER + resp.content[0].zplj;
              $("#videodiv").show();
              _this.videoLj = resp.content[0].zplj;
            }else{
              _this.showVideo = false;
              _this.playerOptions['sources'][0]['src'] = "";
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
        Loading.show();
        _this.vehLogoutDto.page=page;
        _this.vehLogoutDto.size=_this.$refs.pagination.size;
        _this.vehLogoutDto.zt="1";
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLogout/list', _this.vehLogoutDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehLogouts = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);
        })
      },

      /**
       * 点击【保存】
       */
      save() {
        let _this = this;
        let vehLogoutDto = {};
        // 保存校验
        if (1 != 1
                || !Validator.length(_this.vehLogout.createBy, "创建人", 1, 50)
                || !Validator.length(_this.vehLogout.updateBy, "更新人", 1, 50)
                || !Validator.length(_this.vehLogout.sysOrgCode, "所属部门", 1, 64)
                || !Validator.length(_this.vehLogout.xh, "机动车序号", 1, 32)
                || !Validator.length(_this.vehLogout.hpzl, "号牌种类", 1, 10)
                || !Validator.length(_this.vehLogout.hphm, "号牌号码", 1, 10)
                || !Validator.length(_this.vehLogout.zxyy, "注销原因", 1, 2)
                || !Validator.length(_this.vehLogout.hsqymc, "回收企业名称", 1, 256)
                || !Validator.length(_this.vehLogout.hshp, "回收号牌", 1, 32)
                || !Validator.length(_this.vehLogout.hsxsz, "回收行驶证", 1, 32)
                || !Validator.length(_this.vehLogout.hsdjzs, "回收登记证书", 1, 32)
                || !Validator.length(_this.vehLogout.cfjg, "处罚机关", 1, 256)
                || !Validator.length(_this.vehLogout.jdsbh, "处罚决定书编号", 1, 32)
                || !Validator.length(_this.vehLogout.jxmj, "监销民警", 1, 128)
                || !Validator.length(_this.vehLogout.zxjg, "注销机关", 1, 32)
                || !Validator.length(_this.vehLogout.jbr, "经办人", 1, 32)
                || !Validator.length(_this.vehLogout.glbm, "", 1, 128)
                || !Validator.length(_this.vehLogout.bz, "备注", 1, 4000)
                || !Validator.length(_this.vehLogout.clpp1, "车辆品牌", 1, 256)
                || !Validator.length(_this.vehLogout.clxh, "车辆型号", 1, 256)
                || !Validator.length(_this.vehLogout.syr, "机动车所有人", 1, 256)
                || !Validator.length(_this.vehLogout.clsbdh, "车辆识别代号", 1, 25)
                || !Validator.length(_this.vehLogout.lsh, "流水号", 1, 255)
                || !Validator.length(_this.vehLogout.shr, "审核人", 1, 128)
                || !Validator.length(_this.vehLogout.shyy, "审核原因", 1, 128)
                || !Validator.length(_this.vehLogout.shsm, "", 1, 2000)
                || !Validator.length(_this.vehLogout.zt, "状态", 1, 1)
                || !Validator.require(_this.vehLogout.hpzl, "号牌种类")
                || !Validator.require(_this.vehLogout.hphm, "号牌号码")
                || !Validator.require(_this.vehLogout.clsbdh, "车辆识别代号")
                || !Validator.require(_this.vehLogout.xh, "机动车序号")
                || !Validator.require(_this.vehLogout.clpp1, "车辆品牌")
                || !Validator.require(_this.vehLogout.clxh, "车辆型号")
                || !Validator.require(_this.vehLogout.syr, "机动车所有人")
                || !Validator.require(_this.vehLogout.bfrq, "报废日期")
                || !Validator.require(_this.vehLogout.jdsbh, "处罚决定书编号")
                || !Validator.require(_this.vehLogout.cfsj, "处罚时间")
                || !Validator.require(_this.vehLogout.jxmj, "监销民警")
                || !Validator.require(_this.vehLogout.hshp, "回收号牌")
                || !Validator.require(_this.vehLogout.hsxsz, "回收行驶证")
                || !Validator.require(_this.vehLogout.hsdjzs, "回收登记证书")
                || !Validator.require(_this.vehLogout.cfjg, "处罚机关")
                || !Validator.require(_this.vehLogout.zxyy, "注销原因")
        ) {
          return;
        }
        if(!_this.videoLj || _this.tpLj.length<=0){
          Toast.warning("请上传视频或者图片");
          return;
        }
        vehLogoutDto = $.extend({}, _this.vehLogout);
        if(_this.tpLj.length>0){
          let tpListStr = [];
          for(var i=0;i<_this.tpLj.length;i++){
            tpListStr.push(_this.tpLj[i].zplj);
          }
          vehLogoutDto.tpListStr = tpListStr;
        }
        if(_this.videoLj){
          vehLogoutDto.videoLj = _this.videoLj;
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLogout/save', vehLogoutDto).then((response)=>{
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
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLogout/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      },
      tpAfterUpload(res){
        let _this = this;
        if(res && res.success){
          let obj = {
            'zplj':res.content
          };
          _this.tpLj.push(obj);
        }else{
          Toast.error(res.content);
        }
      },
      videoAfterUpload(res){
        let _this = this;
        if(res && res.success){
          _this.showVideo = true;
          _this.playerOptions['sources'][0]['src'] = process.env.VUE_APP_SERVER + res.content;
          _this.videoLj = res.content;
        }else{
          Toast.error(res.content);
        }
      },
    }
  }
</script>