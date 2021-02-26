<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">报废信息查询</h4>
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

    <div>
    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>号牌种类</th>
        <th>号牌号码</th>
        <th>机动车序号</th>
        <th>机动车所有人</th>
        <th>回收企业名称</th>
        <th>车辆识别代号</th>
        <th>注销日期</th>
        <th>回收号牌</th>
        <th>回收行驶证</th>
        <th>回收登记证书</th>
        <th>监销民警</th>
        <th>报废日期</th>
        <th>录入日期</th>
        <th>审核部门</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="vehLogout in vehLogouts">
        <td>{{hpzl|optionMapKV(vehLogout.hpzl)}}</td>
        <td>{{vehLogout.hphm}}</td>
        <td>{{vehLogout.xh}}</td>
        <td>{{vehLogout.syr}}</td>
        <td>{{vehLogout.hsqymc}}</td>
        <td>{{vehLogout.clsbdh}}</td>
        <td>{{vehLogout.zxrq}}</td>
        <td><span v-if="vehLogout.hshp =='0'">未收回</span><span v-if="vehLogout.hshp =='1'" >回收机动车前号牌</span><span v-if="vehLogout.hshp =='2'">回收机动车后号牌</span><span v-if="vehLogout.hshp =='3'">回收机动车前、后号牌</span></td>
        <td><span v-if="vehLogout.hsxsz =='1'">收回</span><span v-else-if="vehLogout.hsxsz=='0'">未收回</span></td>
        <td><span v-if="vehLogout.hsdjzs =='0'">未收回</span><span v-if="vehLogout.hsdjzs =='1'">收回</span></td>
        <td>{{vehLogout.jxmj}}</td>
        <td>{{vehLogout.bfrq}}</td>
        <td>{{vehLogout.createTime}}</td>
        <td>{{maps|optionMapKV(vehLogout.glbm)}}</td>
        <td><span v-if="vehLogout.zt =='1'">已录入</span><span v-if="vehLogout.zt =='2'">审核通过</span><span v-if="vehLogout.zt =='3'">审核不通过</span></td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button v-on:click="edit(vehLogout)" class="btn btn-xs btn-info" title="详情">
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
      <div class="modal-dialog" role="document"  style="width:60%">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">车辆报废信息</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="profile-user-info profile-user-info-striped">
                <div class="profile-info-row">

                  <div class="profile-info-name"  style="width: 10%">号牌种类</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" >{{hpzl|optionMapKV(vehLogout.hpzl)}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">号牌号码</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" >{{vehLogout.hphm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">车辆识别代号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" >{{vehLogout.clsbdh}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">机动车序号</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehLogout.xh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name"  style="width: 10%">注销原因</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehLogout.zxyy}}</span>
                  </div>
                  <div class="profile-info-name" style="width: 10%">回收企业名称</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable">{{vehLogout.hsqymc}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">注销日期</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" >{{vehLogout.zxrq}}</span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">回收号牌</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" ><span v-if="vehLogout.hshp =='0'">未收回</span><span v-if="vehLogout.hshp =='1'">回收机动车前号牌</span><span v-if="vehLogout.hshp =='2'">回收机动车后号牌</span><span v-if="vehLogout.hshp =='3'">回收机动车前、后号牌</span></span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" style="width: 10%">回收行驶证</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable"><span v-if="vehLogout.hsxsz =='1'">收回</span><span v-else-if="vehLogout.hsxsz=='0'">未收回</span></span>
                  </div>
                  <div class="profile-info-name"  style="width: 10%">回收登记证书</div>
                  <div class="profile-info-value" style="width: 40%">
                    <span class="editable" ><span v-if="vehLogout.hsxsz =='0'">未收回</span><span v-if="vehLogout.hsxsz =='1'">收回</span></span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >处罚机关</div>
                  <div class="profile-info-value">
                    <span class="editable" > {{vehLogout.cfjg }}</span>
                  </div>
                  <div class="profile-info-name"  >处罚决定书编号</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.jdsbh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >处罚时间</div>
                  <div class="profile-info-value">
                    <span class="editable" > {{vehLogout.cfsj }}</span>
                  </div>
                  <div class="profile-info-name"  >监销民警</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.jxmj}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >注销机关</div>
                  <div class="profile-info-value">
                    <span class="editable" > {{vehLogout.zxjg}}</span>
                  </div>
                  <div class="profile-info-name"  >经办人</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.jbr}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >审核部门</div>
                  <div class="profile-info-value">
                    <span class="editable" > {{maps|optionMapKV(vehLogout.glbm)}}</span>
                  </div>
                  <div class="profile-info-name"  >备注</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.bz}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >车辆品牌</div>
                  <div class="profile-info-value">
                    <span class="editable" > {{vehLogout.clpp1}}</span>
                  </div>
                  <div class="profile-info-name"  >车辆型号</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.clxh}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >机动车所有人</div>
                  <div class="profile-info-value">
                    <span class="editable" > {{vehLogout.syr}}</span>
                  </div>
                  <div class="profile-info-name"  >报废日期</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.bfrq}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >录入人</div>
                  <div class="profile-info-value">
                    <span class="editable" id="shr"> {{ usersMap|optionMapKV(vehLogout.createBy) }}</span>
                  </div>
                  <div class="profile-info-name"  >录入时间</div>
                  <div class="profile-info-value" >
                    <span class="editable" id="shsj">{{vehLogout.createTime}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >审核人</div>
                  <div class="profile-info-value">
                    <span class="editable" > {{vehLogout.shr}}</span>
                  </div>
                  <div class="profile-info-name"  >审核日期</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.shsj}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >审核原因</div>
                  <div class="profile-info-value">
                    <span class="editable" > {{shyy|optionMapKV(vehLogout.shyy)}}</span>
                  </div>
                  <div class="profile-info-name"  >审核说明</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.shsm}}</span>
                  </div>
                </div>

                <div class="profile-info-row">
                  <div class="profile-info-name" >状态</div>
                  <div class="profile-info-value">
                    <span class="editable"><span v-if="vehLogout.zt =='1'">已录入</span><span v-if="vehLogout.zt =='2'">审核通过</span><span v-if="vehLogout.zt =='3'">审核不通过</span></span>
                  </div>
                  <div class="profile-info-name"  >流水号</div>
                  <div class="profile-info-value" >
                    <span class="editable" >{{vehLogout.lsh}}</span>
                  </div>
                </div>
                <h6 class="header blue lighter less-margin">图片</h6>
                <div class="profile-info-row">
                  <div class="profile-info-name" >图片</div>
                  <div class="profile-info-value">
                    <show-pic :pics="tpLj"></show-pic>
                  </div>
                </div>
                <h6 class="header blue lighter less-margin">视频</h6>
                <div class="profile-info-row">
                  <div class="profile-info-name" >视频</div>
                  <div class="profile-info-value">
                    <div id="videodiv" style="width: 100%;margin-left: 1%">
                      <video-player  class="video-player vjs-custom-skin"
                                     ref="videoPlayer"
                                     :playsinline="true"
                                     :options="playerOptions"
                      ></video-player>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
  import Times from "../../components/time";
  import Pagination from "../../components/pagination";
  import ShowPic from "../../components/showpic";
  import { videoPlayer } from 'vue-video-player';
  import 'video.js/dist/video-js.css';
  import 'vue-video-player/src/custom-theme.css';
  export default {
    components: {Pagination,Times,ShowPic,videoPlayer},
    name: "wxbase-vehLogout",
    data: function() {
      return {
        vehLogout: {},
        vehLogouts: [],
        vehLogoutDto:{},
        hpzl:[],
        usersMap:{},
        maps: [],
        shyy:[],
        tpLj:[],
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
        }
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 10;
      _this.list(1);
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("wxbase-vehLogout-sidebar");
      _this.getHpzl();
      _this.usersMap = Tool.getUserCode();
      _this.maps = Tool.getDeptUser() ;
      _this.getShyy();
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
          if("1" == zplx){
            _this.tpLj = resp.content;
          }else{
            if(null != resp.content&&null != resp.content[0]){
              _this.playerOptions['sources'][0]['src'] = process.env.VUE_APP_SERVER + resp.content[0].zplj;
              $("#videodiv").show();
            }else{
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
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/admin/vehLogout/list', _this.vehLogoutDto).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.vehLogouts = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);

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
       * 获取审核原因
       */
      getShyy(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getShyy', {
        }).then((response)=>{
          let resp = response.data;
          _this.shyy = resp.content;
        })
      }
    }
  }
</script>