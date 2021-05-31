<template>
  <div>
      <div id="navbar" class="navbar navbar-default          ace-save-state">
          <div class="navbar-container ace-save-state" id="navbar-container">
              <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
                  <span class="sr-only">Toggle sidebar</span>

                  <span class="icon-bar"></span>

                  <span class="icon-bar"></span>

                  <span class="icon-bar"></span>
              </button>

              <div class="navbar-header pull-left">
                  <a href="index.html" class="navbar-brand">
                      <small>
                          <img style="height: 28px;margin-top: -8px;" src="/static/favicon.png"/>
                        水下声学实时预警系统v1.0
                      </small>
                  </a>
              </div>
              <div class="navbar-buttons navbar-header pull-right" role="navigation">
                  <ul class="nav ace-nav">
                    <li class="light-blue dropdown-modal" v-on:click="downloadOperationManual()">
                        <a   class=" dropdown-toggle" style="cursor: pointer;">
                          <font color="white">下载操作手册</font>
                        </a>
                    </li>

                      <li class="light-blue dropdown-modal">
                        <router-link to="/welcome">
                          <a   class=" dropdown-toggle" >
                            <font color="white">返回首页</font>
                          </a>
                        </router-link>
                      </li>


                      <li class="light-blue dropdown-modal">
                          <a data-toggle="dropdown" href="#" class="dropdown-toggle">
<!--                              <img class="nav-user-photo" src="../../public/ace/assets/images/avatars/user.jpg" alt="Jason's Photo" />-->
                              <span class="user-info">
									<small>欢迎您,</small>
									{{loginUser.name}}
								</span>

                              <i class="ace-icon fa fa-caret-down"></i>
                          </a>

                          <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
<!--                              <li>-->
<!--                                  <a href="#">-->
<!--                                      <i class="ace-icon fa fa-cog"></i>-->
<!--                                      系统设置-->
<!--                                  </a>-->
<!--                              </li>-->

<!--                              <li>-->
<!--                                  <a  href="profile.html">-->
<!--                                      <i class="ace-icon fa fa-user"></i>-->
<!--                                      个人信息-->
<!--                                  </a>-->
<!--                              </li>-->
                            <li>
                              <a  v-on:click="changePwd()">
                                <i class="ace-icon fa fa-key"></i>
                                修改密码
                              </a>
                            </li>
                             <li class="divider"></li>

                              <li>
                                  <a v-on:click="logout()" href="#">
                                      <i class="ace-icon fa fa-power-off"></i>
                                      退出登录
                                  </a>
                              </li>
                          </ul>
                      </li>
                  </ul>
              </div>
          </div><!-- /.navbar-container -->
      </div>

      <div     class="sidebar      h-sidebar        profile-activity       navbar-collapse collapse          ace-save-state" style="padding-top: 0px">


          <ul class="nav nav-list">
              <li class="  hover">
                  <img class="pull-left "  src="../../public/static/image/jj.jpg" />
              </li>
              <template v-for="(resource,index) in resources">
                  <li  class="hover" v-bind:class="index !== 0 || 'active' "  v-bind:id="index+'transverse'"  v-on:click="getsources(index+'transverse',resource.id)"  v-show="hasResource(resource.id)"  v-if="resource.id !=='00' && resource.id.length <3" >
                      <a href="#" class="dropdown-toggle navbar-toggle">

                          <span class="menu-text">
								{{resource.name}}
							</span>

                          <b class="arrow fa fa-angle-down"></b>
                      </a>

                      <b class="arrow"></b>

                  </li>
              </template>

          </ul><!-- /.nav-list -->
      </div>



      <div class="main-container ace-save-state" id="main-container">

         <div class="sidebar                  responsive                    ace-save-state">
             <div class="sidebar-shortcuts" id="sidebar-shortcuts">



             </div><!-- /.sidebar-shortcuts -->

              <ul class="nav nav-list"  id="sidebar">
               <template v-for="resource in showresources">
                      <li  class="" v-show="hasResource(resource.id)"  v-if="resource.id.length ===4" >
                              <a href="#" class="dropdown-toggle">
                                  <i class="menu-icon fa " :class="resource.iocn" ></i>
                                  <span class="menu-text">{{resource.name}}</span>

                                  <b class="arrow fa fa-angle-down"></b>
                              </a>

                              <b class="arrow"></b>
                              <ul class="submenu">
                                  <template v-for="res in showresources.filter(t=>{return t.parent===resource.id})">
                                      <li v-show="hasResource(res.id)" class=""   v-bind:id="res.setbar"  >
                                          <router-link :to="{path:'/'+res.page }">
                                              <i class="menu-icon fa fa-caret-right"></i>
                                              {{res.name}}
                                          </router-link>

                                          <b class="arrow"></b>
                                      </li>
                                  </template>
                              </ul>
                      </li>
                  </template>
              </ul><!-- /.nav-list -->

              <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                  <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
              </div>
          </div>

          <div class="main-content">
              <div class="main-content-inner">
                  <div class="page-content">
                      <div class="row">
                          <div class="col-xs-12">
                              <!--子组件-->
                               <router-view v-if="isRouterAlive"></router-view>
                              <!-- PAGE CONTENT ENDS -->
                          </div><!-- /.col -->
                      </div><!-- /.row -->
                  </div><!-- /.page-content -->
              </div>
          </div><!-- /.main-content -->

          <div class="footer">
              <div class="footer-inner">
                  <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder"></span>
							武汉品度科技有限公司
						</span>

                  </div>
              </div>
          </div>

          <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
              <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
          </a>
      </div><!-- /.main-container -->

    <div id="form-modal-pwd" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改密码</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">原密码</label>
                <div class="col-sm-10">
                  <input v-model="oldPwd" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">新密码</label>
                <div class="col-sm-10">
                  <input v-model="newPwd" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="savePwd()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div>
      </div>
    </div>

  </div>

</template>
<script>
    export default {
        name:'admin',
        data:function(){
            return{
                loginUser:{},
                resources:[],//菜单
                showresources :[],//竖向菜单
              oldPwd:'',
              newPwd:'',
                isRouterAlive:true
            }
        },
        provide(){
          return {
            reload:this.reload
          }
        },
        mounted:function(){//mounted初始化方法
            let _this = this; //this 变成本地变量  避坑
            _this.loginUser =Tool.getLoginUser();
            _this.getallres(_this.loginUser);//获取所有菜单
            $("body").removeClass('login-layout light-login');
            $("body").attr('class', 'no-skin');


            //要是直接链接跳转 watch 可能监听不到  如直接冲login跳到welcome.所以初始化也添加一个
            _this.activeSidebae(_this.$route.name.replace("/","-")+"-sidebar");
           $.getScript('/ace/assets/js/ace.min.js');


            if (!_this.hasResourceRouter(_this.$route.name)) {
                _this.$router.push("/login");
            }

        },
        watch:{//监听
            $route:{//监听路由变化 跳转的是 /  只对/  mane 为admin 下的所有只路由有效
                handler:function (val, oldVal) {
                    let _this = this;


                    if (!_this.hasResourceRouter(val.name)) {
                        _this.$router.push("/login");
                        return;
                    }

                    _this.$nextTick(function () {//页面加载完后执行
                       _this.activeSidebae(_this.$route.name.replace("/","-")+"-sidebar");
                    })
                }
            }
        },
        methods:{
          downloadOperationManual(){
            window.location.href = process.env.VUE_APP_SERVER + '/system/downloadfile/downloadLocal';
          },
          reload(){
            let _this = this;
            _this.isRouterAlive = false;
            _this.$nextTick(function (){
              _this.isRouterAlive = true;
            })
          },
          changePwd(){
            $("#form-modal-pwd").modal("show");
          },
          savePwd(){
            let _this = this;
            // 保存校验
            if (1 != 1
                || !Validator.require(_this.oldPwd, "原密码")
                || !Validator.length(_this.oldPwd, "原密码", 1, 10)
                || !Validator.require(_this.newPwd, "新密码")
                || !Validator.length(_this.newPwd, "新密码", 1, 10)
            ) {
              return;
            }
            if(_this.oldPwd==_this.newPwd){
              Toast.warning("新密码不能与原密码相同！");
              return;
            }
            Loading.show();
            _this.loginUser.oldPwd = hex_md5(_this.oldPwd +KEY);
            _this.loginUser.newPwd = hex_md5(_this.newPwd +KEY);
            _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/changePwd', _this.loginUser).then((response)=>{
              Loading.hide();
              let resp = response.data;
              if (resp.success) {
                Toast.success(resp.message);
                $("#form-modal-pwd").modal("hide");
                _this.$router.push("/login");
              } else {
                Toast.warning(resp.message);
                return;
              }
            })
          },
            /**
             *横向菜单
             * 点击很想菜单动态加载数据
             */
            getsources(id,dateid){
                let _this  =this;
                $("#" + id).siblings().removeClass("active");
                $("#" + id).addClass("active");

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/getResById', {id:dateid}).then((response)=>{
                    if(response.data.success){
                        _this.showresources = response.data.content;
                        $("#sidebar  li ul li").each(function(){//激活样式选择
                            let that  =this;
                            if($(that).hasClass("active")){
                                $(that).removeClass("active");
                                $(that).parent().parent().removeClass("open active");
                                $(that).parent().removeClass("nav-show");
                                $(that).parent().addClass("nav-hide");
                                $(that).parent().css('display','');
                                $(that).parent().css('display','hide');
                            }
                        })
                        $("#sidebar li:first").addClass("open");

                    }



                })



            },


            activeSidebae:function (id) {
                // 兄弟菜单去掉active样式，自身增加active样式
                $("#" + id).siblings().removeClass("active");
                $("#" + id).siblings().find("li").removeClass("active");
                $("#" + id).addClass("active");

                // 如果有父菜单，父菜单的兄弟菜单去掉open active，父菜单增加open active
                let parentLi = $("#" + id).parents("li");
                if (parentLi) {
                    parentLi.siblings().removeClass("open active");
                    parentLi.siblings().find("li").removeClass("active");
                    parentLi.siblings().find("ul").removeClass("nav-show");
                    parentLi.siblings().find("ul").addClass("nav-hide");
                    parentLi.siblings().find("ul").css('display','');
                    parentLi.siblings().find("ul").css('display','hide');
                    parentLi.addClass("open active");
                }
            },
            logout(){
                let _this  =this;
                Loading.show();
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/user/logout/'+_this.loginUser.token).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    if (resp.success) {
                        //Toast.success("保存成功！");
                         Tool.setLoginUser(null);
                        _this.$router.push("/login")
                    } else {
                        Toast.warning(resp.message)
                    }
                })

            },
            /**
             * 查找是否有权限
             * @param id
             */
            hasResource(id) {
                return Tool.hasResource(id);
            },
            /**
             * 查找是否有权限
             * @param router
             */
            hasResourceRouter(router) {
                let _this = this;
                let resources = Tool.getLoginUser().resources;
                if (Tool.isEmpty(resources)) {
                    return false;
                }
                for (let i = 0; i < resources.length; i++) {
                    if("welcome" === router){
                        return true;
                    }else{
                        if (router === resources[i].page) {
                            return true;
                        }
                    }

                }
                return false;
            },
            getallres(usre){
                let _this = this;
                if(Tool.isNotEmpty(usre)){

                    _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/getAllRes',{rode:usre.rode}).then((res)=>{
                        let response = res.data;
                        _this.resources = response.content;
                        if(Tool.isNotEmpty(_this.resources)){//初始化显示菜单
                            if(Tool.isEmpty( _this.showresources ) ){
                                this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/resource/getResById', {id:_this.resources[0].id}).then((response)=>{
                                    _this.showresources = response.data.content;
                                    $("#sidebar li:first").addClass("open");
                                })
                            }
                        }

                    })

                }


            }
        }

    }


</script>

