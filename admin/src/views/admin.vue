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
                          <i class="fa fa-leaf"></i>
                          水环境动态监测系统v1.0
                      </small>
                  </a>
              </div>
              <div class="navbar-buttons navbar-header pull-right" role="navigation">
                  <ul class="nav ace-nav">
                      <li class="grey dropdown-modal">
                          <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                              <i class="ace-icon fa fa-tasks"></i>
                              <span class="badge badge-grey">4</span>
                          </a>

                          <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                              <li class="dropdown-header">
                                  <i class="ace-icon fa fa-check"></i>
                                  4 Tasks to complete
                              </li>

                              <li class="dropdown-content">
                                  <ul class="dropdown-menu dropdown-navbar">
                                      <li>
                                          <a href="#">
                                              <div class="clearfix">
                                                  <span class="pull-left">Software Update</span>
                                                  <span class="pull-right">65%</span>
                                              </div>

                                              <div class="progress progress-mini">
                                                  <div style="width:65%" class="progress-bar"></div>
                                              </div>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#">
                                              <div class="clearfix">
                                                  <span class="pull-left">Hardware Upgrade</span>
                                                  <span class="pull-right">35%</span>
                                              </div>

                                              <div class="progress progress-mini">
                                                  <div style="width:35%" class="progress-bar progress-bar-danger"></div>
                                              </div>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#">
                                              <div class="clearfix">
                                                  <span class="pull-left">Unit Testing</span>
                                                  <span class="pull-right">15%</span>
                                              </div>

                                              <div class="progress progress-mini">
                                                  <div style="width:15%" class="progress-bar progress-bar-warning"></div>
                                              </div>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#">
                                              <div class="clearfix">
                                                  <span class="pull-left">Bug Fixes</span>
                                                  <span class="pull-right">90%</span>
                                              </div>

                                              <div class="progress progress-mini progress-striped active">
                                                  <div style="width:90%" class="progress-bar progress-bar-success"></div>
                                              </div>
                                          </a>
                                      </li>
                                  </ul>
                              </li>

                              <li class="dropdown-footer">
                                  <a href="#">
                                      See tasks with details
                                      <i class="ace-icon fa fa-arrow-right"></i>
                                  </a>
                              </li>
                          </ul>
                      </li>

                      <li class="purple dropdown-modal">
                          <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                              <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                              <span class="badge badge-important">8</span>
                          </a>

                          <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                              <li class="dropdown-header">
                                  <i class="ace-icon fa fa-exclamation-triangle"></i>
                                  8 Notifications
                              </li>

                              <li class="dropdown-content">
                                  <ul class="dropdown-menu dropdown-navbar navbar-pink">
                                      <li>
                                          <a href="#">
                                              <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
														New Comments
													</span>
                                                  <span class="pull-right badge badge-info">+12</span>
                                              </div>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#">
                                              <i class="btn btn-xs btn-primary fa fa-user"></i>
                                              Bob just signed up as an editor ...
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#">
                                              <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
														New Orders
													</span>
                                                  <span class="pull-right badge badge-success">+8</span>
                                              </div>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#">
                                              <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
														Followers
													</span>
                                                  <span class="pull-right badge badge-info">+11</span>
                                              </div>
                                          </a>
                                      </li>
                                  </ul>
                              </li>

                              <li class="dropdown-footer">
                                  <a href="#">
                                      See all notifications
                                      <i class="ace-icon fa fa-arrow-right"></i>
                                  </a>
                              </li>
                          </ul>
                      </li>

                      <li class="green dropdown-modal">
                          <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                              <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
                              <span class="badge badge-success">5</span>
                          </a>

                          <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                              <li class="dropdown-header">
                                  <i class="ace-icon fa fa-envelope-o"></i>
                                  5 Messages
                              </li>

                              <li class="dropdown-content">
                                  <ul class="dropdown-menu dropdown-navbar">
                                      <li>
                                          <a href="#" class="clearfix">
                                              <img src="../../public/ace/assets/images/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
                                              <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Alex:</span>
														Ciao sociis natoque penatibus et auctor ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>a moment ago</span>
													</span>
												</span>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#" class="clearfix">
                                              <img src="../../public/ace/assets/images/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
                                              <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Susan:</span>
														Vestibulum id ligula porta felis euismod ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>20 minutes ago</span>
													</span>
												</span>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#" class="clearfix">
                                              <img src="../../public/ace/assets/images/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
                                              <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Bob:</span>
														Nullam quis risus eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>3:15 pm</span>
													</span>
												</span>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#" class="clearfix">
                                              <img src="../../public/ace/assets/images/avatars/avatar2.png" class="msg-photo" alt="Kate's Avatar" />
                                              <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Kate:</span>
														Ciao sociis natoque eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>1:33 pm</span>
													</span>
												</span>
                                          </a>
                                      </li>

                                      <li>
                                          <a href="#" class="clearfix">
                                              <img src="../../public/ace/assets/images/avatars/avatar5.png" class="msg-photo" alt="Fred's Avatar" />
                                              <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Fred:</span>
														Vestibulum id penatibus et auctor  ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>10:09 am</span>
													</span>
												</span>
                                          </a>
                                      </li>
                                  </ul>
                              </li>

                              <li class="dropdown-footer">
                                  <a href="inbox.html">
                                      See all messages
                                      <i class="ace-icon fa fa-arrow-right"></i>
                                  </a>
                              </li>
                          </ul>
                      </li>

                      <li class="light-blue dropdown-modal">
                          <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                              <img class="nav-user-photo" src="../../public/ace/assets/images/avatars/user.jpg" alt="Jason's Photo" />
                              <span class="user-info">
									<small>欢迎您,</small>
									{{loginUser.name}}
								</span>

                              <i class="ace-icon fa fa-caret-down"></i>
                          </a>

                          <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                              <li>
                                  <a href="#">
                                      <i class="ace-icon fa fa-cog"></i>
                                      系统设置
                                  </a>
                              </li>

                              <li>
                                  <a  href="profile.html">
                                      <i class="ace-icon fa fa-user"></i>
                                      个人信息
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
                 <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large" >
                     <button class="btn btn-success"  >
                         <i class="ace-icon fa fa-signal" ></i>
                     </button>
                     &nbsp;
                     <button class="btn btn-info"  >
                         <i class="ace-icon fa fa-pencil"></i>
                     </button>
                     &nbsp;
                     <button class="btn btn-warning"  >
                         <i class="ace-icon fa fa-users"></i>
                     </button>
                     &nbsp;
                     <button class="btn btn-danger" >
                         <i class="ace-icon fa fa-cogs"></i>
                     </button>

                 </div>


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
                               <router-view></router-view>
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

