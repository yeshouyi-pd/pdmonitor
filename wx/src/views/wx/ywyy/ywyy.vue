<template>
    <div>
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
                <img src="/static/wx/ywyy/ywyyindex.jpg" style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>
        <div class="van-address-list">
          <div  class="divlan">
            机动车业务
          </div>
            <van-grid direction="horizontal"  gutter="2px" icon-size="30px" :column-num="3" >

                <van-grid-item v-for="codeset  in jdcyw"
                               v-bind:key="codeset.id"
                               :icon="codeset.content"
                               :text="codeset.name"
                               v-on:click="toYwxz('2',codeset.code)">

                </van-grid-item>

            </van-grid>
            <!-- 上边框 -->
          <div  class="divlan">
            驾驶证业务
          </div>
          <van-grid direction="horizontal"  gutter="2px" icon-size="30px" :column-num="3" >
                <van-grid-item v-for="codeset  in jsryw"
                               v-bind:key="codeset.id"
                               :icon="codeset.content"
                               :text="codeset.name"
                               v-on:click="toYwxz('3',codeset.code)">
                </van-grid-item>
            </van-grid>

          <div class="divlan">
            违法业务
          </div>


          <van-grid direction="horizontal"   gutter="2px" icon-size="30px" :column-num="3" >

            <van-grid-item   v-for="codeset  in fwyw"
                            v-bind:key="codeset.id"
                            :icon="codeset.content"
                            :text="codeset.name"
                           v-on:click="toYwxz('5',codeset.code)">
            </van-grid-item>
          </van-grid>
            <van-divider />
        </div>
        <div class="van-address-list__bottom" >
            <van-notice-bar left-icon="volume-o"
                            style="height: 20px"
                            text="温馨提示： 预约成功后请带上所需业务资料和身份证明，遵守预约时间，以免影响您的行程，预约未办理，如果违约超过三次，将被列入黑名单。" >
            </van-notice-bar>
        </div>
    </div>
</template>

<script>

    export default {
        name:'index',
        data:function(){
            return{
                jdcyw:[],//后台初始化的机动车所有业务
                jsryw:[],//后台初始化的驾驶人所有业务
                fwyw:[],//后台初始化的违法所有业务
                wwyy:{}, //最后保存到数据库的实体对象 现在包括ywfl ywlx 字段

            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.getAllYwlx();

        },
        methods:{
            /**
             * 获取业务类型信息
             */
            getAllYwlx(){
                 let _this =this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/getAllYwlx').then((res)=>{
                    let resp = res.data.content;
                    _this.jdcyw = resp.CODE_JDC_YWLX;
                    _this.jsryw = resp.CODE_JSR_YWLX;
                    _this.fwyw = resp.CODE_WF_YWLX;

                });

            },
            /**
             * 跳转到业务分类 业务须知界面
             * @param ywfl
             * @param ywlx
             */
            toYwxz(ywfl,ywlx){
                let _this =this;
                _this.wwyy.ywfl = ywfl;
                _this.wwyy.ywlx = ywlx;
                _this.$forceUpdate();
                SessionStorage.set(SAVY_YY_INFO,_this.wwyy);//保存预约信息
                _this.$router.push("/ywyy/ywydxz");


            },

        }

    }
</script>

<style scoped>
/deep/.van-grid-item__content {
  display: -webkit-box;
  display: -webkit-flex;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -webkit-flex-direction: column;
  flex-direction: column;
  box-sizing: border-box;
  height: 100%;
  padding: 8px 4px;
  background-color: #fff;
}

/deep/.van-grid-item__content--horizontal {
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -webkit-flex-direction: row;
  flex-direction: row;
}
    .van-grid-item {
        position: relative;
        box-sizing: border-box;
        height: 70px;
        color: #646566;
        font-size: 1em;

    }
    .van-grid-item >>>.van-grid-item__content--center {
       -webkit-box-align: center;
       -webkit-align-items: center;
       align-items: center;
       -webkit-box-pack: center;
       -webkit-justify-content: center;
       justify-content: left;
    }
 .divlan{
   background: #5cadff;
   border-radius:10px;
   text-align: center;
   color: white;
   font-size: 14px;
   margin: 5px
 }


</style>
