<template>
    <div>
        <van-form @submit="onSubmit">
            <div class="ksxx">部门类型</div>
            <ul id="example-1">
                <li v-for="(dept,index) in depttypes" :key="index">
                    <div :id="index" v-on:click="dzbd(index)" class="lxdiv">
                        <p>{{dept}}</p>
                    </div>
                </li>
            </ul>
            <div class="button_db">
                <van-button round block type="info" native-type="submit">
                    下一步
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    import Notify from "vant/lib/notify";

    export default {
        name: "bswdlx",
        data:function(){
            return{
                code: '',
                wxUser: {},
                depttypes: [],
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.getWxUser();
            _this.getDepttype();
        },
        methods: {
            getWxUser() {
                let _this = this;
                let wxUser = Tool.getWxUser();
                if (null != wxUser) {
                    _this.wxUser = wxUser;
                }
            },
            dzbd(code) {
                let _this = this;
                $("div").css("background", "");
                $("#" + code).css("background", "#16BC41");
                _this.code = code;
            },
            onSubmit(values) {
                console.log('submit', values);
                let _this = this;
                if (_this.code === '') {
                    Notify({type: 'danger', message: '请选择部门类型'});
                    return false;
                }
                _this.$router.push({
                    path: '/bswd', query: {
                        code: _this.code,
                    }
                });
            },
            /**
             * 获取部门类型
             */
            getDepttype(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getDepttype', {
                }).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.depttypes = resp.content;
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .lxdiv {
        width: 90%;
        height: 55px;
        border: 1px solid #FDFDFD;
        margin: 8px auto;
        text-align: center;
        border-radius: 8px;
        background: #1679DD;
        color: #FDFDFD;
    }
    .ksxx {
        font-size: 17px;
        width: 100%;
        background-color: #F2F2F2;
        height: 40px;
        text-align: center;
        line-height: 40px;
        font-weight:bold;
    }
    .button_db {
        width: 90%;
        position:fixed;
        bottom: 10px;
        left: 5%;
    }
</style>