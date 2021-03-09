<template>
    <div class="main-container">
        <img class="bgimg" src="/static/image/index1.jpg"/>
      <br/><br/><br/><br/><br/><br/><br/><br/><br/>

        <div class="main-content" >
          <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                    <div class="center">
                                  <span style="font-size: 45px">
                                      <i class="ace-icon fa fa-leaf green"></i>
                                      <span style="color: #D6F1FF">水环境动态监测系统v1.0</span>
                                  </span>
                    </div>
                    <div class="login-container">
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
            </div><!-- /.row -->
          <div class="position-relative-gs"><h4>版权所有：武汉品度科技有限公司</h4></div>
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
                imageCodeToken: ""
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
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
                          _this.$router.push("/welcome")
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
      color: #fff;
      position: absolute;
      bottom: 10px;
      left: 50%;
    }
</style>
