<template>
    <div>
        <ul>
            <template v-for="(item,index) in mysspjbs">
                <li :key="index">
                    <van-cell icon="/static/wx/bmfw/jbtp1.png"  is-link :to="{path:'/mysspjbinfo',query:{id:item.id}}">
                        {{wflbs|optionMapKV(item.wflb)}}
                    </van-cell>
                </li>
            </template>
        </ul>

        <van-tabbar v-model="active">
            <van-tabbar-item replace to="/ssjbpage" icon="wap-home">
                随手拍举报
            </van-tabbar-item>
            <van-tabbar-item replace to="/mysspjb" icon="manager">
                我的举报
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "mysspjb",
        data: function () {
            return {
                active: 1,
                title:'',
                mysspjbs: [],
                wflbs: [],
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.selectfail();
            _this.getWflb();
        },
        methods: {
            selectfail() {
                let _this = this;
                let formData = new FormData();
                formData.append("openid", Tool.getWxOpendid());
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatIllegalSave/myssp',
                    formData
                ).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.mysspjbs = resp.content;
                    } else {
                        Dialog({ message: resp.message });
                    }
                })
            },
          /**
           * 获取违法类别
           */
          getWflb(){
            let _this = this;
            _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWflb', {
            }).then((response)=>{
              let resp = response.data;
              _this.wflbs = resp.content;
            })
          },
        }
    }
</script>

<style scoped>

</style>