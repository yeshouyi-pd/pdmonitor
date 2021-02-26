<template>
    <div >
        <van-search
                v-model="wwyy.searchvalue"
                show-action
                shape="round"
                background="#00BFFF"
                placeholder="请输入搜索关键词"
                v-on:click="getDeptByLwlx()"
        >
            <template #action>
                <div    v-on:click="getDeptByLwlx()">搜索</div>
            </template>
        </van-search>

        <van-radio-group v-model="wwyy.deptcode" >
            <div class="van-address-list">
                    <div  v-for="(dept ,index) in depes"   class="van-cell van-cell--borderless"  v-on:click="check(dept.deptcode)">
                        <div  class="van-cell__value van-cell__value--alone van-address-item__value">
                            <div  role="radio" tabindex="0" aria-checked="true" class="van-radio">
                                <van-radio  class="van-radio__icon van-radio__icon--round "    v-bind:name="dept.deptcode" />
                                <span class="van-radio__label" >
                                     <div class="van-address-item__name">
                                       <b >{{dept.deptname}} </b>
                                       <span  v-show="index === 0" class="van-tag van-tag--round van-tag--danger van-address-item__tag">
                                          推荐
                                        </span>
                                      </div>
                                      <div class="van-address-item__address">
                                      {{dept.linkadd}}<br/>
                                       {{dept.linktel}}
                                     </div>
                                 </span>
                            </div>
                        </div>
                    </div>
                    <van-divider
                            :style="{  borderColor: '#DCDCDC', padding: '0 1px' }">
                    </van-divider>


            </div>
        </van-radio-group>
        <div class="van-address-list__bottom">
            <div class="van-goods-action" >
                <button class="van-button van-button--warning van-button--large van-goods-action-button van-goods-action-button--first van-goods-action-button--warning"
                        style="background: linear-gradient(to right,	 #7FFFAA,#1E90FF);
                               box-shadow: 2px 2px 10px #00FFFF;"
                        @click="yytype('1')">
                    <div class="van-button__content">
                        <span class="van-button__text">个人预约</span>
                    </div>
                </button>
                <button class="van-button van-button--danger van-button--large van-goods-action-button van-goods-action-button--last van-goods-action-button--danger"
                        style="background: linear-gradient(to right, #1E90FF,#7FFFAA);
                               box-shadow: 2px 2px 10px #00FFFF;"
                        @click="yytype('2')">
                    <div class="van-button__content">
                        <span class="van-button__text">企业预约</span>
                    </div>
                </button>
            </div>
            <div style="margin-top: 10px"></div>
        </div>

    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    export default {
        name:'ywsldw',
        data:function(){

            return {
                wwyy:{},//保存的实体类对象
                depes:[],//能办理该业务的所有部门
            };

        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            let wwyy =  SessionStorage.get(SAVY_YY_INFO)|| {} ;
            /**
             * 不要参数不能为空
             */
            if(Tool.isEmpty(wwyy)){
                _this.$router.push("/index");//跳转index页面 重新预约
            }
            if(Tool.isEmpty(wwyy.ywfl) ||
                Tool.isEmpty(wwyy.ywlx)){
                _this.$router.push("/index");//必要参数不能为空
            }
            _this.wwyy.ywfl = wwyy.ywfl;
            _this.wwyy.ywlx = wwyy.ywlx;
            _this.$forceUpdate();
            _this.getDeptByLwlx();

        },
        methods:{
            check(obj){//点击DIV 也选择当前部门 并set 到wwyy deptcode=里面
                let _this = this;
                _this.wwyy.deptcode=obj;
                _this.$forceUpdate();
            },

            /**
             * 1 个人预约
             * 2 企业预约
             */
             yytype(obj){
                let _this = this;
                if(Tool.isEmpty(_this.wwyy.deptcode)){
                    Dialog.alert({message: '请选择受理单位！'});
                    return;
                }
                _this.wwyy.yytype=obj;//设置预约类型
                /**
                 * 根据部门 得到个人预约和企业预约的最大预约数
                 */
                for(let i = 0 ; i < _this.depes.length; i++){
                    if(_this.wwyy.deptcode ===  _this.depes[i].deptcode){
                        let dept =  _this.depes[i];//得到选中部门信息
                        if(_this.wwyy.yytype === '1'){//个人
                            _this.wwyy.daymax = dept.gryymax;//临时参数 当前能办理的最大值
                        }
                        if(_this.wwyy.yytype === '2'){//企业
                            _this.wwyy.daymax = dept.qyyymax;
                        }
                        break;
                    }
                }
                _this.$forceUpdate();
                SessionStorage.set(SAVY_YY_INFO,_this.wwyy);//继续传递 wwyy保存对象
                _this.$router.push("/ywyy/ywyusd");

            },
            /**
             *获取能办理 业务的所有部门
             */
            getDeptByLwlx(){
                let _this = this;
                _this.wwyy.deptcode='';
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/getDeptByYwlx',   _this.wwyy).then((response)=>{
                    let resp = response.data;
                    if (resp.success) {
                        _this.depes = resp.content ;
                    }
                })

            },

            /**
             * 浏览器定位 暂时没用需要正式域名和https
             */
            getpositioning(){
                    let geolocation = new AMap.Geolocation({
                        // 是否使用高精度定位，默认：true
                        enableHighAccuracy: true,
                        // 设置定位超时时间，默认：无穷大
                        timeout: 10000,
                        // 定位按钮的停靠位置的偏移量，默认：Pixel(10, 20)
                        buttonOffset: new AMap.Pixel(10, 20),
                        //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                        zoomToAccuracy: true,
                        //  定位按钮的排放位置,  RB表示右下
                        buttonPosition: 'RB'
                    })

                    geolocation.getCurrentPosition()
                    AMap.event.addListener(geolocation, 'complete', onComplete)
                    AMap.event.addListener(geolocation, 'error', onError)

                    function onComplete (data) {
                        // data是具体的定位信息
                        alert(JSON.stringify(data));
                    }

                    function onError (data) {
                        // 定位出错
                        alert(JSON.stringify(data));
                    }


            },
        }


    }
</script>

<style scoped>
    .van-divider {
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        -webkit-box-align: center;
        -webkit-align-items: center;
        align-items: center;
        margin: 1px 0;
        color: #969799;
        font-size: 2px;
        line-height: 2px;
        border-color: #ebedf0;
        border-style: solid;
        border-width: 0;
    }
</style>
