<template>
    <div class="main-container">
        <img class="bgimg" src="/static/image/loginbg.jpg"/>
      <div :style="'padding-top:'+paddingsize+'%'"></div>
        <div class="main-content" style="padding-left: 0px;padding-right: 0px;">
          <div>
            <div :class="isMobileflag?'col-sm-10':'col-sm-4'" :style="'margin-left:'+marginleft+'%;'">

              <div class="center">
                                  <span :style="'font-size:'+fontsize+'px'">
<!--                                      <i class="ace-icon fa fa-leaf green"></i>-->
                                    <img :style="'height:'+imgsize+'px;margin-top: -10px;'" src="/static/favicon.png"/>
                                      <span >齿鲸类实时声学分析预警系统</span>
                                  </span>
              </div>

                    <div class="login-container" :style="'margin:'+margin+'px auto'">
                        <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
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

                                                    <button type="button"
                                                            class="width-35 pull-right btn btn-sm btn-primary"
                                                             v-on:click="login()" >
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

                        </div><!-- /.position-relative -->
                    </div>
                </div><!-- /.col -->
            <div v-if="isMobileflag" class="col-xs-10" style="margin-top: 10px;">
                    <span>
                      <i class="ace-icon fa fa-square blue"></i>
                      <span style="margin-left: 10px;">创新技术</span>
                    </span>
              <span style="margin-left: 5px;">
                      <i class="ace-icon fa fa-square blue"></i>
                      <span style="margin-left: 10px;">实时平台</span>
                    </span>
              <span style="margin-left: 5px;">
                      <i class="ace-icon fa fa-square blue"></i>
                      <span style="margin-left: 10px;">专业团队</span>
                    </span>
            </div>
            <div v-else class="col-xs-4" style="margin-top: 20px;margin-left: 15%;">
                    <span>
                      <i class="ace-icon fa fa-square blue"></i>
                      <span class="logo-text-style">创新技术</span>
                    </span>
              <span style="margin-left: 20px;">
                      <i class="ace-icon fa fa-square blue"></i>
                      <span class="logo-text-style">实时平台</span>
                    </span>
              <span style="margin-left: 20px;">
                      <i class="ace-icon fa fa-square blue"></i>
                      <span class="logo-text-style">专业团队</span>
                    </span>
            </div>
            </div><!-- /.row -->
          <div   class="position-relative-gs"><img height="60px;" src="/static/image/loginButtom.png"/></div>
        </div><!-- /.main-content -->
    </div><!-- /.main-container -->
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
                fontsize:45,
                imgsize: 51,
                paddingsize:6,
              marginleft:45,
              margin:100
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
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
              _this.fontsize =27;
              _this.imgsize = 31;
              _this.paddingsize=20;
              _this.marginleft=0;
              _this.margin=20;
            }else{
              _this.isMobileflag =false;
              _this.imgsize = 51;
              _this.marginleft=45;
              if(window.screen.height<=720){
                _this.margin=0;
                _this.paddingsize=5;
                _this.fontsize =38;
              }else if(window.screen.height<=768){
                _this.margin=10;
                _this.paddingsize=5;
                _this.fontsize =45;
              }else if(window.screen.height<=800){
                _this.margin=10;
                _this.paddingsize=6;
                _this.fontsize =45;
              }else if(window.screen.height<=900){
                _this.margin=50;
                _this.paddingsize=6;
                _this.fontsize =45;
              }else if(window.screen.height<=1024){
                _this.paddingsize=6;
                _this.margin=100;
                _this.fontsize =45;
              }else if(window.screen.height<=1080){
                _this.paddingsize=6;
                _this.margin=120;
                _this.fontsize =45;
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
                let   rememberUser =  LocalStorage.get(LOCAL_KEY_REMEMBER_USER) || {};

                if(md5 !== rememberUser.md5 ){
                    _this.user.password=hex_md5(_this.user.password +KEY);

                }
                _this.user.imageCodeToken = _this.imageCodeToken;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/login', _this.user).then((response)=>{
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

                         //window.open("/welcome", "_self")
                      if(_this.isMobileflag){
                        _this.$router.push("/mobile/mindex")
                      }else{
                          //_this.$router.push("/mobile/largemonitors")
                          _this.$router.push("/admin/chooseProject")
                      }

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
.col-sm-4{
  width: 55%;
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
      position: absolute;
      bottom: 30px;
      width: 100%;
      text-align: center;
    }
    .logo-text-style{
      font-size: 20px;
      font-weight: 500;
      margin-left: 5px;
    }
</style>
