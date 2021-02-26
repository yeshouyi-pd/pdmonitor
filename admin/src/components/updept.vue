<template>
    <div>

        <div class="row">
            <div class="col-sm-7">
                <input   disabled="disabled"   v-model="deptname"  class="form-control">
            </div>
            <div  class="col-sm-2">
                <button  v-on:click="chencktreeid()" type="button" class="btn   btn-sm  btn-success btn-round" >选择部门</button>
            </div>
        </div>

      <div  v-bind:id="modelId+'-input'" class="modal fade" tabindex="-1"  style="z-index: 1060"  role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">双击选择部门</h4>
                </div>
                <div class="modal-body">
                    <ul v-bind:id="checktree+'-tree'"  class="ztree"></ul>
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
    </div>
</template>

<script>
    export default {
        name: 'updept',
        props: {
            modelId: {
                default: "updept-model"
            },
            checktree:{
                default:"checktree"

            },
            afterCheck: {
                type: Function,
                default: null
            },
        },
        data: function () {
            return {
                checktrees:[],//选择的树
                deptname: ""
            }
        },
        mounted: function() {
            let _this = this;
            _this.getDeptTree();
        },
        methods: {

            /**
             * 选择树
             */
            chencktreeid(){
                let _this = this;
                $("#" + _this.modelId + "-input").modal("show");

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
                            rootPId: "0",
                            enable: true
                        }
                    },callback: {
                        onDblClick:  _this.zTreeOnDblClick
                    },
                };

                _this.zTree = $.fn.zTree.init( $("#" + _this.checktree + "-tree"), setting, _this.checktrees);
                _this.zTree.expandAll(true);
            },
            zTreeOnDblClick(event, treeId, treeNode) {
                let  _this = this;
                _this.deptname = treeNode.deptname;
                _this.afterCheck(treeNode.deptcode);
                $("#" + _this.modelId + "-input").modal("hide");
            },


        }
    }
</script>
