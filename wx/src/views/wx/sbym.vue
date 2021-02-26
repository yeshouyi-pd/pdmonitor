<template>
    <div>
        <div>
            <div style="margin-top: 50px;">
                <van-row type="flex" justify="center">
                    <van-image
                            round
                            default="成功"
                            width="10rem"
                            height="10rem"
                            src="/static/wx/sb.png"
                    />
                </van-row>
                <div style="text-align: center;margin-top: 20px;font-size: 20px">操作失败</div>
            </div>
            <div class="button_db">
                <van-button round block type="info" to="/index" native-type="submit">
                    返回首页
                </van-button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "sbym"
    }
</script>

<style scoped>
    .button_db {
        width: 95%;
        position:fixed;
        bottom: 10px;
        left: 2.5%;
    }
</style>