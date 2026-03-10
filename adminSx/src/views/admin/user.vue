<template>
  <div>
        <!-- query start -->
        <div class="widget-box">
          <div class="widget-header">
            <h4 class="widget-title">代码管理</h4>
          </div>
          <div class="widget-body">
            <div class="widget-main">
              <form >
                <table  style="font-size: 1.1em;width:80%" class="text-right  " >
                  <tbody  >
                  <tr  >
                    <td style="width:10%" >
                      账号：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text"  v-model="queryuser.loginName"/>
                    </td>
                    <td style="width: 10%" >
                      姓名：
                    </td>
                    <td style="width: 10%">
                      <input class="input-sm" type="text"   v-model="queryuser.name"/>
                    </td>
                    <td  colspan="2" >

                    </td>
                  </tr>
                  <tr  >
                    <td style="width:8%" >
                      角色：
                    </td>
                    <td style="width: 10%">
                      <select v-model="queryuser.rode" class="form-control">
                        <option v-for="o in roles" v-bind:value="o.id">{{o.name}}</option>
                      </select>
                    </td>
                    <td style="width:8%" >
                      状态：
                    </td>
                    <td style="width: 10%">
                      <div class="radio" style="text-align: left">
                        <label>
                          <input name="form-zt-radio" type="radio"  value="Y" v-model="queryuser.zt" class="ace input-lg" />
                          <span class="lbl">启用</span>
                        </label>
                        <label>
                          <input name="form-zt-radio" type="radio"  value="N"  v-model="queryuser.zt" class="ace input-lg" />
                          <span class="lbl ">禁用</span>
                        </label>
                      </div>
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

    <div style="background: #F5F5F5;margin-top: -2px">
      <div class="space-2"></div>
      <button v-on:click="add()" class="btn  btn-sm btn-success btn-round">
        <i class="ace-icon fa fa-edit"></i>
        用户新增
      </button>&nbsp;
      <div class="space-2"></div>
    </div>


    <div>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>账号</th>
        <th>用户名称</th>
        <th>部门</th>
        <th>角色</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="user in users">
        <td>{{user.loginName}}</td>
        <td>{{user.name}}</td>
        <td>
          <template  v-for="deptc in alldept.filter(t=>{return t.deptcode===user.deptcode})" >
            {{deptc.deptname}}
          </template>
        </td>
        <td>
          <template  v-for="role in roles.filter(t=>{return t.id===user.rode})">
            {{role.name}}
          </template>
          </td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button v-on:click="edit(user)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button v-on:click="del(user.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
      <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
    </div>
    <div id="tree-modal" class="modal fade" tabindex="-1"  style="z-index: 1060"  role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">双击选择部门</h4>
          </div>
          <div class="modal-body">
            <ul id="checktree" class="ztree"></ul>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-white btn-default btn-round" data-dismiss="modal">
              <i class="ace-icon fa fa-times"></i>
              关闭
            </button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" style="width: 60%;height: auto" role="document">
        <div class="modal-content" >
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">用户管理</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-1 control-label">账号</label>
                <div class="col-sm-5">
                  <input v-model="user.loginName"   v-bind:disabled="user.id" class="form-control">
                </div>
                <label class="col-sm-1 control-label">密码</label>
                <div class="col-sm-5">
                  <input v-model="user.password"   class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-1 control-label">用户名称</label>
                <div class="col-sm-5">
                  <input v-model="user.name" class="form-control">
                </div>
                <label class="col-sm-1 control-label">证件号码</label>
                <div class="col-sm-5">
                  <input v-model="user.sfzhm" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-1 control-label">部门</label>
                <div class="col-sm-5">
                  <div class="row">
                    <div class="col-sm-9">
                      <input  style="display: none" v-model="user.deptcode"  class="form-control">
                      <input   disabled="disabled"  v-for="updept in alldept.filter(t=>{return t.deptcode===user.deptcode})" v-bind:value="updept.deptname"       class="form-control">
                    </div>
                    <div class="col-sm-5 " >
                      <button  s  v-on:click="chencktreeid()" type="button" class="btn btn-success " >选择部门</button>
                    </div>
                  </div>
                </div>
                <label class="col-sm-1 control-label">角色</label>
                <div class="col-sm-5">
                  <select v-model="user.rode" class="form-control">
                    <option v-for="o in roles" v-bind:value="o.id">{{o.name}}</option>
                  </select>

                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-1 control-label">用户有效期</label>
                <div class="col-sm-5">
                  <Dategreater  @methodName="yhyxqDate" style="width:100%" idValue="yhyxq"   :setValue="user.yhyxq"></Dategreater>
                </div>
                <label class="col-sm-1 control-label">密码有效期</label>
                <div class="col-sm-5">
                  <Dategreater @methodName="mmyxqDate" style="width:100%" idValue="mmyxq"   :setValue="user.mmyxq"></Dategreater>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-1 control-label">是否启用</label>
                <div class="col-sm-5">
                  <div class="radio">
                    <label>
                      <input name="form-zt-radio" type="radio"  value="Y" v-model="user.zt" class="ace input-lg" />
                      <span class="lbl bigger-120">是</span>
                    </label>
                    <label>
                      <input name="form-zt-radio" type="radio"  value="N"  v-model="user.zt" class="ace input-lg" />
                      <span class="lbl bigger-120">否</span>
                    </label>
                  </div>
                </div>
                <label class="col-sm-1 control-label">能否下载</label>
                <div class="col-sm-5">
                  <div class="radio">
                    <label>
                      <input name="form-xz-radio" type="radio"  value="Y" v-model="user.yj" class="ace input-lg" />
                      <span class="lbl bigger-120">是</span>
                    </label>
                    <label>
                      <input name="form-xz-radio" type="radio"  value="N"  v-model="user.yj" class="ace input-lg" />
                      <span class="lbl bigger-120">否</span>
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-1 control-label">大屏图片</label>
                <div class="col-sm-5">
                  <div>
                    <!-- 输入框和上传按钮的组合 -->
                    <div class="input-group">
                      <input type="text" 
                             v-model="user.ipstart" 
                             class="form-control" 
                             placeholder="请输入图片路径或选择文件上传"
                             id="image-path-input" />
                      <div class="input-group-btn">
                        <button type="button" 
                                class="btn btn-primary btn-sm" 
                                id="upload-btn"
                                title="选择文件上传"
                                style="border-radius: 0 4px 4px 0; border-left: 1px solid #ccc;">
                          <i class="ace-icon fa fa-upload"></i>
                          上传
                        </button>
                      </div>
                    </div>
                    <!-- 隐藏的文件输入框 -->
                    <input type="file" 
                           id="input-file" 
                           name="file" 
                           style="display: none;"
                           accept="image/*" />
                    <!-- 图片预览 -->
                    <div v-if="user.ipstart" class="mt-2">
                      <img :src="getImageUrl(user.ipstart)"
                           style="max-width: 200px; max-height: 150px; border: 1px solid #ddd; border-radius: 4px;"
                           alt="大屏图片预览" 
                           @error="handleImageError" />
                      <div class="text-muted small mt-1">{{getImageUrl(user.ipstart)}}</div>
                    </div>
                  </div>
                </div>
                <label class="col-sm-1 control-label">首页名字</label>
                <div class="col-sm-5">
                  <input v-model="user.ipend" class="form-control">
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
import Dategreater from "../../components/dategreater";

export default {
    components: {Pagination,Dategreater},
    name: "system-user",
    data: function() {
      return {
        queryuser:{},//查询对象
        user: {},
        users: [],
        roles:[],
        checktrees:[],
        alldept:[],
        selectedFile: null, // 选择的文件
        LOCAL_ZHBHT:LOCAL_ZHBHT,
        imgPath:process.env.VUE_APP_SERVER
      }
    },
    mounted: function() {
      let _this = this;
      _this.getAllres();
      _this.getAllDept();
      _this.$refs.pagination.size = 10;
      _this.list(1);
      _this.getDeptTree();
      // sidebar激活样式方法一
      // this.$parent.activeSidebar("system-user-sidebar");
      $('#form-modal').on('hidden.bs.modal', function () {
        // 弹窗关闭时完全重置文件输入组件
        _this.handleFileClear();
      });
      // 确保DOM完全渲染后再初始化文件上传功能
      _this.$nextTick(function() {
        _this.initFileUpload();
      });
    },
    methods: {
      /**
       * 处理文件选择并上传
       */
      handleFileSelect(file) {
        let _this = this;
        // 文件大小限制（5MB）
        if (file.size > 5 * 1024 * 1024) {
          Toast.error("文件大小不能超过5MB！", {icon: 0, title: "提示"});
          return;
        }
        // 上传文件到后台
        _this.uploadFile(file);
      },

      /**
       * 处理文件清除
       */
      handleFileClear() {
        let _this = this;
        console.log('文件已被清除');

        // 清除相关数据
        _this.user.ipstart = '';
        _this.selectedFile = null;

        // 清空文件输入框
        $('#input-file').val('');
        
        // 清空文本输入框
        $('#image-path-input').val('');

        // 强制更新视图
        _this.$forceUpdate();
      },

      /**
       * 初始化文件上传功能
       */
      initFileUpload() {
        let _this = this;
        
        // 确保DOM完全渲染后再初始化
        _this.$nextTick(function() {
          // 绑定上传按钮点击事件
          $('#upload-btn').off('click').on('click', function() {
            $('#input-file').click();
          });

          // 绑定文件选择事件
          $('#input-file').off('change').on('change', function(event) {
            let files = event.target.files;
            if (files && files.length > 0) {
              _this.handleFileSelect(files[0]);
            }
          });

          // 绑定输入框变化事件
          $('#image-path-input').off('input').on('input', function() {
            _this.$forceUpdate();
          });
        });
      },

      /**
       * 获取图片完整URL
       */
      getImageUrl(imagePath) {
        if (!imagePath) return '';
        
        // 如果已经是完整URL，直接返回
        if (imagePath.startsWith('http://') || imagePath.startsWith('https://')) {
          return imagePath;
        }
        
        // 如果是相对路径，拼接服务器地址
        return this.imgPath + imagePath;
      },

      /**
       * 处理图片加载错误
       */
      handleImageError(event) {
        console.warn('图片加载失败:', event.target.src);
        // 可以在这里添加默认图片或错误提示
      },

      /**
       * 重置文件上传组件
       */
      resetFileUpload() {
        let _this = this;
        
        // 清空文件输入框
        $('#input-file').val('');
        
        // 清空输入框
        $('#image-path-input').val('');
        
        // 重新初始化
        _this.initFileUpload();
      },

      /**
       * 上传文件到后台
       */
      uploadFile(file) {
        let _this = this;
        // 创建FormData对象
        let formData = new FormData();
        formData.append('file', file);
        formData.append('use', 'user'); // 用户头像使用user目录
        // 显示上传进度
        Loading.show("文件上传中...");
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/uploadfile/upload', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          },
          timeout: 30000 // 30秒超时
        }).then((response) => {
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            // 上传成功，保存文件路径
            _this.user.ipstart = process.env.VUE_APP_SERVER + resp.content;
            _this.selectedFile = file;
            Toast.success("文件上传成功！", {icon: 0, title: "提示"});
            console.log('文件上传成功，路径:', resp.content);
            _this.$forceUpdate();
          } else {
            Toast.error(resp.message || "文件上传失败！", {icon: 0, title: "提示"});
          }
        }).catch((error) => {
          Loading.hide();
          console.error('文件上传失败:', error);
          if (error.code === 'ECONNABORTED') {
            Toast.error("文件上传超时，请重试！", {icon: 0, title: "提示"});
          } else {
            Toast.error("文件上传失败，请重试！", {icon: 0, title: "提示"});
          }
        });
      },

      yhyxqDate(rep){
        let _this = this;
        _this.user.yhyxq = rep;
        _this.$forceUpdate();
      },
      mmyxqDate(rep){
        let _this = this;
        _this.user.mmyxq = rep;
        _this.$forceUpdate();

      },
      getDeptTree() {
        let _this = this;
        Loading.show();
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/dept/load-deptTree').then((res)=>{
          Loading.hide();
          let response = res.data;
          _this.checktrees = response.content;
          // 初始化树
          _this.checkinitTree();
        })
      },
      /**
       * 选择树
       */
      chencktreeid(){
        $("#tree-modal").modal("show");

      },
      /**
       *加载所有部门
       */
      getAllDept() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/dept/getAllDept').then((response)=>{
          let resp = response.data;
          _this.alldept = resp.content;

        })

      },
      /**
       * 点击【新增】
       */
      add() {
        let _this = this;
        _this.user = {};
        _this.user.zt = "Y";
        _this.user.yj = "Y";
        _this.user.ipstart = ''; // 确保清空图片路径
        _this.selectedFile = null;
        _this.$forceUpdate();
        $("#form-modal").modal("show");

        // 模态框显示后重新初始化文件上传功能
        _this.$nextTick(function() {
          // 先重置组件状态
          _this.resetFileUpload();
          // 然后重新初始化
          _this.initFileUpload();
        });
      },

      /**
       * 点击【编辑】
       */
      edit(user) {
        let _this = this;
        _this.user = $.extend({}, user);
        _this.user.password='';
        _this.selectedFile = null; // 清空选择的文件
        _this.$forceUpdate();
        $("#form-modal").modal("show");

        // 模态框显示后重新初始化文件上传功能
        _this.$nextTick(function() {
          // 先重置组件状态
          _this.resetFileUpload();
          // 然后重新初始化
          _this.initFileUpload();
        });
      },

      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.queryuser.page=page;
        _this.queryuser.size=_this.$refs.pagination.size;
        if(_this.LOCAL_ZHBHT){
          _this.queryuser.xmbm="zhuhai";
        }
        _this.$forceUpdate();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/list', _this.queryuser).then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.users = resp.content.list;
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
                || !Validator.require(_this.user.loginName, "登陆名")
                || !Validator.length(_this.user.loginName, "登陆名", 1, 50)
                || !Validator.length(_this.user.name, "用户名称", 1, 50)
                || !Validator.require(_this.user.name, "用户名称")
                || !Validator.require(_this.user.yj, "能否下载")
                || (!Validator.require(_this.user.password, "密码"))
                || (!Validator.require(_this.user.rode, "角色"))
                || (!Validator.require(_this.user.deptcode, "部门"))
                || (!Validator.require(_this.user.ipstart, "大屏图片"))
                || (!Validator.require(_this.user.ipend, "项目首页名称"))

        ) {
          return;
        }

        if(Tool.isNotEmpty(_this.user.password)){
          _this.user.password=hex_md5(_this.user.password +KEY);
        }
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/user/save', _this.user).then((response)=>{
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
        Confirm.show("删除用户后不可恢复，确认删除？", function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/system/admin/user/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          })
        });
      },
      /**
       * 获取所有角色
       */
      getAllres(){
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/role/allroles', {
        }).then((response)=>{
          let resp = response.data;

          _this.roles = resp.content;

        })

      },
      /**
       * 初始资源树
       */
      checkinitTree() {
        let _this = this;
        let setting = {
          data: {
            key:{
              name:"deptname",
            },
            simpleData: {
              idKey: "deptcode",
              pIdKey: "upcode",
              rootPId: "",
              enable: true
            }
          },callback: {
            onDblClick:  _this.zTreeOnDblClick
          },
        };

        _this.zTree = $.fn.zTree.init($("#checktree"), setting, _this.checktrees);
        _this.zTree.expandAll(true);
      },
      zTreeOnDblClick(event, treeId, treeNode) {
        let  _this = this;
        //_this.dept.upcode = treeNode.deptcode;
        _this.$set(_this.user,"deptcode",treeNode.deptcode);
        $("#tree-modal").modal("hide");
      },
    }
  }
</script>
