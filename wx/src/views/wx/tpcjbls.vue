<template>
    <div>
        <div class="ksxx">报案历史记录</div>
        <ul>
            <template v-for="(item,index) in list">
                <li :key="index">
                    <van-cell icon="/static/wx/bmfw/bajl1.png" is-link :to="{path:'/tpcjblsinfo',query:{id:item.id}}">
                        {{item.hphm}}
                    </van-cell>
                </li>
            </template>
        </ul>
    </div>
</template>

<script>

    import Toast from "vant/lib/toast";
    import Dialog from "vant/lib/dialog";

    export default {
        name: "tpcjbls",
        data: function () {
            return {
                list: [],
                wxUser: {},
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.wxUser = Tool.getWxUser();
            _this.getRobbery();
        },
        methods: {
            getRobbery(){
                let _this = this;
                let formData = new FormData();
                formData.append("sfzmhm", _this.wxUser.zjhm);
                Toast.loading({
                    duration: 0,
                    message: '加载中...',
                    forbidClick: true,
                });
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/vehRobbery/getRobbery',
                    formData
                ).then((response)=>{
                    Toast.clear();
                    let resp = response.data;
                    if (resp.success) {
                        _this.list = resp.content;
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .ksxx {
        font-size: 17px;
        width: 100%;
        background-color: #F2F2F2;
        height: 40px;
        text-align: center;
        line-height: 40px;
        font-weight:bold;
    }
</style>