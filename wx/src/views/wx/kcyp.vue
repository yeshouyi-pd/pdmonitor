<template>
    <div>
        <div style="width: 90%;height: 100%;margin: 5px auto;border: 1px solid #C9DCE9;border-radius: 5px;background-color: #D6F1FF;padding: 5px 0px 5px 5px;">
            <ul style="line-height: 25px;">
                <li style="font-size: 15px;">安全提示：</li>
                <li class="fontsize">1、发生交通事故时，全程开启危险报警闪光灯，放置三角警示牌，避免二次交通事故；</li>
                <li class="fontsize">2、请您在确保自身安全的情况下进行拍照取证，事故照片拍照注意清晰度、事故信息，便于快速划分责任；</li>
            </ul>
        </div>
        <div style="width: 90%;height: 100%;border: 1px solid #1A0F0F;margin: 10px auto;border-radius: 5px;padding: 5px 0px 5px 5px;">
            <ul style="line-height: 25px;">
                <li style="font-size: 15px;">我们将按照以下步骤引导您处理交通事故：</li>
                <li class="fontsize">1、拍摄双方车辆事故现场及驾照行驶证照片；</li>
                <li class="fontsize">2、照片审核，通过后将车辆移到不妨碍通行的安全地点；</li>
                <li class="fontsize">3、交通事故远程定责。</li>
            </ul>
        </div>
        <div style="width: 90%;height: 100%;border: 1px solid #1A0F0F;margin: 10px auto;border-radius: 5px;padding: 5px 0px 5px 5px;">
            <ul style="line-height: 25px;">
                <li style="font-size: 15px;">事故现场是否有以下情况：</li>
                <li class="fontsize">1、车辆受损严重至无法移动；</li>
                <li class="fontsize">2、任意一方有人员受伤；</li>
                <li class="fontsize">3、任意一方车牌无号码。</li>
            </ul>
        </div>

        <div class="button_db">
            <van-button to="/jtsgsb" round block type="info" native-type="button">
                无以上情况，下一步
            </van-button>
        </div>
        <div class="button_db1">
            <van-button round block type="info" native-type="button">
                拨打122
            </van-button>
        </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "kcyp",
        data: function () {
            return {

            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.getWxSmrzUser();
        },
        methods: {
            /**
             * 实名认证
             */
            getWxSmrzUser(){
                let _this = this;
                if(null == Tool.getWxUser()){
                    Dialog({ message: "请实名认证" });
                    _this.$router.push({path:'/smrz'});
                }
            },
        }
    }
</script>

<style scoped>
    .fontsize{
        font-size: 14px;
    }
    .button_db {
        width: 90%;
        margin: 20px 0% 0% 5%;
    }
    .button_db1 {
        width: 90%;
        margin: 5px 0% 0% 5%;
    }
    /*.button_db {
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
    }*/
</style>