<template>
    <div :style="'height: '+maxHeight+'px'">
      <div v-if="!isMobileflag" class="main-content" style="width: 100%;height: 100vh;background-image: url('/static/image/videobg.jpg');background-size: 100% 100%;">
        <div class="center" style="margin-top: 7%;margin-bottom: 4%">
          <span :style="'font-size:'+fontsize+'px'">
          <p>广东省中华白海豚国家重要湿地
          <p>中华白海豚环境耦合实时监测系统</p>
          </span>
        </div>
        <div id="login-box1" class="login-box visible widget-box no-border" style="width: 475px;margin-left: 55%;">
          <div class="widget-body">
            <div class="widget-main">
              <h4 class="header blue lighter bigger">
                <i class="ace-icon fa fa-coffee green"></i>
                请输入用户密码
              </h4>
              <div class="space-6"></div>
              <form>
                <fieldset>
                  <label class="block clearfix">
                              <span class="block input-icon input-icon-right">
                                <input v-model="user.loginName" type="text" class="form-control" placeholder="请输入用户名" />
                                <i class="ace-icon fa fa-user"></i>
                              </span>
                  </label>
                  <label class="block clearfix">
                              <span class="block input-icon input-icon-right">
                                <input  v-model="user.password"  type="password" class="form-control" placeholder="密码" />
                                <i class="ace-icon fa fa-lock"></i>
                              </span>
                  </label>
                  <label class="block clearfix">
                              <span class="block input-icon input-icon-right">
                                <div class="input-group">
                                  <input v-model="user.imageCode" type="text" class="form-control" placeholder="验证码">
                                  <span class="input-group-addon" id="basic-addon2">
                                    <img v-on:click="loadImageCode()" id="image-code" alt="验证码"/>
                                  </span>
                                </div>
                              </span>
                  </label>
                  <div class="space"></div>
                  <div class="clearfix">
                    <label class="inline">
                      <input v-model="remember" type="checkbox" class="ace" />
                      <span class="lbl">记住我</span>
                    </label>
                    <button type="button" class="width-35 pull-right btn btn-sm btn-primary" v-on:click="login()" >
                      <i class="ace-icon fa fa-key"></i>
                      <span class="bigger-110">登录</span>
                    </button>
                  </div>
                  <div class="space-4"></div>
                </fieldset>
              </form>
            </div><!-- /.widget-main -->
          </div><!-- /.widget-body -->
        </div><!-- /.login-box -->
        <div style="margin-top: 10%;text-align: center">
          <div>
            <img height="40px;" src="/static/image/loginButtom.png"/>
            <div style="margin-top: 5px;">广东珠江口中华白海豚国家级自然保护区管理局</div>
          </div>
        </div>
      </div>
      <div v-if="isMobileflag" class="main-content" style="width: 100%;height: 100%;background-image: url('/static/image/loginbg.jpg');background-size: 100% 100%;">
        <div class="center" style="margin-top: 15%">
        <span :style="'font-size:'+fontsize+'px'">
          <p>广东省中华白海豚国家重要湿地
          <p>中华白海豚环境耦合实时监测系统</p>
        </span>
        </div>
        <div id="login-box" class="login-box visible widget-box no-border" style="margin-top: 20%;">
          <div class="widget-body">
            <div class="widget-main">
              <h4 class="header blue lighter bigger">
                <i class="ace-icon fa fa-coffee green"></i>
                请输入用户密码
              </h4>
              <div class="space-6"></div>
              <form>
                <fieldset>
                  <label class="block clearfix">
                              <span class="block input-icon input-icon-right">
                                <input v-model="user.loginName" type="text" class="form-control" placeholder="请输入用户名" />
                                <i class="ace-icon fa fa-user"></i>
                              </span>
                  </label>
                  <label class="block clearfix">
                              <span class="block input-icon input-icon-right">
                                <input  v-model="user.password"  type="password" class="form-control" placeholder="密码" />
                                <i class="ace-icon fa fa-lock"></i>
                              </span>
                  </label>
                  <label class="block clearfix">
                              <span class="block input-icon input-icon-right">
                                <div class="input-group">
                                  <input v-model="user.imageCode" type="text" class="form-control" placeholder="验证码">
                                  <span class="input-group-addon" id="basic-addon2">
                                    <img v-on:click="loadImageCode()" id="image-code" alt="验证码"/>
                                  </span>
                                </div>
                              </span>
                  </label>
                  <div class="space"></div>
                  <div class="clearfix">
                    <label class="inline">
                      <input v-model="remember" type="checkbox" class="ace" />
                      <span class="lbl">记住我</span>
                    </label>
                    <button type="button" class="width-35 pull-right btn btn-sm btn-primary" v-on:click="login()" >
                      <i class="ace-icon fa fa-key"></i>
                      <span class="bigger-110">登录</span>
                    </button>
                  </div>
                  <div class="space-4"></div>
                </fieldset>
              </form>
            </div><!-- /.widget-main -->
          </div><!-- /.widget-body -->
        </div><!-- /.login-box -->
        <div style="margin-top: 10%;text-align: center">
          <div>
            <img  v-if="!isMobileflag" height="40px;" src="/static/image/loginButtom.png"/>
            <div>广东珠江口中华白海豚国家级自然保护区管理局</div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>

    export default {
        name:'login',
        data:function(){
            return{
              user:{},
              remember:true,
              imageCodeToken: "",
              isMobileflag:false,
              fontsize:35,
              imgsize: 105,
              paddingsize:6,
              marginleft:'60%',
              margin:70,
              margintop:'8%',
              maxHeight:'',
              LOCAL_ZHBHT:LOCAL_ZHBHT,
              LOCAL_SSBRL:LOCAL_SSBRL
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.maxHeight = $(window).height();
             _this.isMobile();

            $('body').removeClass('no-skin');
            $('body').attr('class', 'login-layout light-login');

            let   rememberUser =  LocalStorage.get(LOCAL_KEY_REMEMBER_USER)
            if(rememberUser){
                _this.user=rememberUser;
            }

            // 初始时加载一次验证码图片
            _this.loadImageCode();
        },
        methods:{

          isMobile() {
            let _this = this;
            if(/phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone/i.test(navigator.userAgent)){
              _this.isMobileflag =true;
              _this.fontsize =18;
              _this.imgsize = 31;
              _this.paddingsize=20;
              _this.marginleft=0;
              _this.margin=20;
              _this.margintop='6%';
            }else{
              _this.isMobileflag =false;
              _this.imgsize = 105;
              _this.marginleft='60%';
              _this.margintop='8%';
              if(window.screen.height<=720){
                _this.margin=0;
                _this.paddingsize=5;
                _this.fontsize =38;
              }else if(window.screen.height<=768){
                _this.margin=10;
                _this.paddingsize=5;
                _this.fontsize =35;
              }else if(window.screen.height<=800){
                _this.margin=10;
                _this.paddingsize=6;
                _this.fontsize =35;
              }else if(window.screen.height<=900){
                _this.margin=50;
                _this.paddingsize=6;
                _this.fontsize =35;
              }else if(window.screen.height<=1024){
                _this.paddingsize=6;
                _this.margin=100;
                _this.fontsize =35;
              }else if(window.screen.height<=1080){
                _this.paddingsize=6;
                _this.margin=70;
                _this.fontsize =35;
              }
            }
          },
            login(){//push跳转到某一个地址
                let _this = this;

                // 校验
                if (1 != 1
                    || !Validator.require(_this.user.loginName, "登陆名")
                    || !Validator.require(_this.user.password, "密码")
                    || !Validator.require(_this.user.imageCode, "验证码")
                ) {
                    return;
                }
                let md5= hex_md5(this.user.password);
                let rememberUser =  LocalStorage.get(LOCAL_KEY_REMEMBER_USER) || {};

                if(md5 !== rememberUser.md5 ){
                    _this.user.password=hex_md5(_this.user.password +KEY);
                }
                _this.user.imageCodeToken = _this.imageCodeToken;
                Loading.show();
                _this.user.xmbm = "002";
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/userVideo/login', _this.user).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                        //Toast.success("保存成功！");
                        Tool.setLoginUser(resp.content);
                        if(_this.remember){
                            let md5= hex_md5(this.user.password);
                            LocalStorage.set(LOCAL_KEY_REMEMBER_USER,{
                                loginName : resp.content.loginName,
                                password :_this.user.password,
                                md5:md5
                            });
                        }else{
                            LocalStorage.set(LOCAL_KEY_REMEMBER_USER,null);
                        }

                      _this.$router.push("/mobile/environmentDp")

                    } else {
                        _this.user.password="";
                        _this.loadImageCode();
                        Toast.warning(resp.message)
                    }
                })

            },
            /**
             * 加载图形验证码
             */
            loadImageCode: function () {
                let _this = this;
                _this.imageCodeToken = Tool.uuid(8);
                $('#image-code').attr('src', process.env.VUE_APP_SERVER + '/system/admin/kaptcha/image-code/' + _this.imageCodeToken);
            },
        }

    }
</script>

<style scoped>
    .login-content{
      position: relative;
    }
    .input-group-addon {
        padding: 0;
    }
    .bgimg {
        position:fixed;
        width:100%;
        height:100%;
    }
    .divrow{
        position:fixed;
        margin-top: 15%;
        margin-left: 60%;
    }
    .position-relative-gs{
      position: relative;
      width: 100%;
      text-align: center;
      font-family: "楷体";
      margin-top: 20px;
    }
    .position-relative-gs1{
      position: absolute;
      bottom: 30px;
      width: 100%;
      text-align: center;
      font-size: 24px;
      font-family: "楷体";
    }
    .logo-text-style{
      font-size: 20px;
      font-weight: 500;
      margin-left: 5px;
    }
</style>
