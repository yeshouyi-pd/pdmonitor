<template>
    <div>
        <van-image src="/static/wx/sfrz/smrz_top.png"/>
        <div style="height: 20px"></div>
        <div style="font-size: 15px;width: 95%;margin: auto;line-height: 20px;">
            请先进行实名认证，完成实名认证，可以确保本人信息的真实性，更加方便、快捷的办理各项业务，为您提供更高效的服务。
        </div>
        <div style="margin-left: 10px;margin-top: 10px;font-size: 14px;">
            <van-checkbox style="float: left;" v-on:change="checkeds" v-model="checked" shape="square">我</van-checkbox>
            已认真阅读并同意<span style="color: #1d6fa6;" v-on:click="sbxz">《认证授权》</span>
        </div>
        <div class="button_db">
            <van-button id="but1" round block type="default" native-type="button">
                立即认证
            </van-button>
        </div>
        <div class="button_db">
            <van-button id="but2" style="display: none;" v-on:click="smrzinfo" round block type="info" native-type="submit">
                立即认证
            </van-button>
        </div>
        <div class="button_db1">
            <van-button to="/index" round block type="info" native-type="button">
                返回首页
            </van-button>
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "smrz",
        data() {
            return {
                checked: '',
            };
        },
        mounted() {

        },
        methods: {
            smrzinfo() {
                let _this = this;
                _this.$router.push("/smrzinfo");
            },
            checkeds() {
                let _this = this;
                if(_this.checked){
                    $('#but1').hide();
                    $('#but2').show();
                }else{
                    $('#but1').show();
                    $('#but2').hide();
                }
            },
            sbxz(){
                let sfrz = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本服务由“武汉品度科技有限公司”提供技术支持。您在接受服务过程中，需要调用您的手机摄像头，并且根据业务场景的不同，" +
                    "需要读取您的身份证个人信息，包括姓名、公民身份号码、本人相片、证件的有效期和签发机关等身份证信息，并且截取您的脸部图像，从而实现身份比对。" +
                    "\t\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如您使用不成功，请确保您已经按照要求正确使用本服务，并联系人工客服核实个人信息进行处理。";
                Dialog({ message: sfrz });
            },
        }
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        position: absolute;
        bottom: 65px;
        transform: translateX(5%);
    }
    .button_db1 {
        width: 90%;
        position: absolute;
        bottom: 10px;
        transform: translateX(5%);
    }
</style>