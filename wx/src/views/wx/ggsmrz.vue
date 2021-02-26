<template>
    <div>
        <van-action-sheet v-model="show" title="实名认证">
            <div class="content">
                <div style="font-size: 15px">完成实名认证,可以确保本人信息的真实性,更加方便、快捷的办理各项业务,为您提供更高效的服务。</div>
                <br/>
                <van-button round plain type="info" v-on:click="smrz">开始实名认证</van-button>
            </div>
        </van-action-sheet>
    </div>
</template>

<script>
    export default {
        name: "ggsmrz",
        data: function () {
            return {
                show: false,
            }
        },
        mounted: function () {//mounted初始化方法

        },
        methods: {
            smrz() {
                let _this = this;
                _this.$router.push("/smrz");
            },
            xsff(){
                let _this = this;
                _this.show = true;
            }
        }
    }
</script>

<style scoped>

</style>