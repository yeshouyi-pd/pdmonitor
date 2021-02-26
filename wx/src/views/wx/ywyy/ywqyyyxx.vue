<template>
    <div>
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item >
                <img src="/static/wx/ywyy/ywyyindex.jpg" style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>
        <div style="text-align: center;height: auto;margin:-1px;
                    background: #F9F4F6;color: #CDC9C9;font-weight: bold;
                     font-size: 0.8em;">
            预约信息</div>
        <div class="van-cell van-cell--center   ">
            <div  class="van-cell__value van-cell__value--alone van-contact-card__value">
                <div  style="width: 100%;font-size: 1em;color: #aaa">
                    <div style="float: left">预约日期</div>
                    <div  style="float: right">{{wwyy.yysj}}</div>
                </div>
            </div>
        </div>
        <div class="van-cell van-cell--center   ">
            <div  class="van-cell__value van-cell__value--alone van-contact-card__value">
                <div  style="width: 100%;font-size: 1em;color: #aaa">
                    <div style="float: left">预约时段</div>
                    <div  style="float: right">{{wwyy.yyrq}}</div>
                </div>
            </div>
        </div>
        <div class="van-cell van-cell--center   ">
            <div  class="van-cell__value van-cell__value--alone van-contact-card__value">
                <div  style="width: 100%;font-size: 1em;color: #aaa">
                    <div style="float: left;width: 30%">预约单位</div>
                    <div  style="float: left;width: 70%;font-size: 0.9em;">{{wwyy.deptname}}</div>
                </div>
            </div>
        </div>

        <div class="van-address-list" style="padding-top: 0px;">
            <van-cell-group>
                <div style="text-align: center;height: auto;margin: 0px;
                    background: #F9F4F6;color: #CDC9C9;font-weight: bold;
                     font-size: 0.8em;">
                    预约人信息</div>
                <van-field   label="姓名"  v-model="wwyy.name"  placeholder="请输入姓名"/>
                <van-field
                        readonly
                        clickable
                        label="证件类型"
                        :value="value"
                        placeholder="选择证件类型"
                        @click="showPicker = true"
                />
                <van-popup v-model="showPicker" round position="bottom">
                    <van-picker
                            show-toolbar
                            :columns="zjlxs"
                            @cancel="showPicker = false"
                            @confirm="onConfirm"
                    />
                </van-popup>
                <van-field  label="证件号码" v-model="wwyy.zjhm" placeholder="请输入证件号码" />
                <van-field type="tel" label="手机号" v-model="wwyy.sjhm" placeholder="请输入手机号码" />

                <van-field label="客车类型">
                    <template #input>
                        <van-radio-group v-model="wwyy.kclx" direction="horizontal">
                            <van-radio name="Y">国产车</van-radio>
                            <van-radio name="N">进口车</van-radio>
                        </van-radio-group>
                    </template>
                </van-field>
                <van-field label="是否新能源">
                    <template #input>
                        <van-radio-group v-model="wwyy.sfxny" direction="horizontal">
                            <van-radio name="1">是</van-radio>
                            <van-radio name="2">否</van-radio>
                        </van-radio-group>
                    </template>
                </van-field>
                <van-field  label="车架号后六位" v-model="wwyy.vin"  placeholder="请输入车架号后六位" />
                <div style="text-align: center;height: auto;margin: 0px;
                    background: #F9F4F6;color: #CDC9C9;font-weight: bold;
                     font-size: 0.8em;">
                    预约企业信息</div>
                <van-field   label="单位名称"  v-model="wwyy.dwmc"  placeholder="请输入单位名称"/>
                <van-field   label="社会信用代码"  v-model="wwyy.xydm"  placeholder="请输入社会信用代码"/>
                <van-field name="stepper" label="预约数量">
                    <template #input>
                        <van-stepper
                                theme="round"
                                button-size="18"
                                disable-input
                                v-model="wwyy.yysl"
                                async-change
                                :value="wwyy.yysl"
                                v-on:change="checkMaxNum()"/>
                    </template>
                </van-field>
            </van-cell-group>


            <div style="margin-top: 30px;">
                <van-button round block type="info"
                            color="linear-gradient(to right,#00BFFF,#0000FF)"
                            v-on:click="savewwyy()">
                    提交
                </van-button>
            </div>
        </div>
    </div>
</template>




<script>
    import Dialog from "vant/lib/dialog";
    export default {
        name:'ywqyyyxx',
        data:function(){
            return{
                wwyy:{},//保存的实体类对象
                value: '居民身份证',
                showPicker: false,
                zjlxs: [],
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            let wwyy =  SessionStorage.get(SAVY_YY_INFO)|| {} ;
            console.log(wwyy);
            if(Tool.isEmpty(wwyy)){
                _this.$router.push("/index");//跳转index页面 重新预约
            }
            if(Tool.isEmpty(wwyy.ywfl)||
                Tool.isEmpty(wwyy.ywlx)||
                Tool.isEmpty(wwyy.yysj)||
                Tool.isEmpty(wwyy.yysd)||
                Tool.isEmpty(wwyy.yyrq)||
                Tool.isEmpty(wwyy.deptcode)||
                Tool.isEmpty(wwyy.deptname)||
                Tool.isEmpty(wwyy.yyslmax)||
                Tool.isEmpty(wwyy.yytype)){
                _this.$router.push("/index");//跳转index页面 重新预约
            }

            /**
             * 包含如下字段  防止页面回退 保证必要属性都不为空
             * ywfl
             * ywlx
             * yysj  :2020-11-26
             * yysd : dept_yysd 的id
             * yyrq : 12:20 -13:20
             * deptcode
             * deptname
             * yyslmax
             */
            _this.wwyy.ywfl = wwyy.ywfl;
            _this.wwyy.ywlx = wwyy.ywlx;
            _this.wwyy.yysj = wwyy.yysj;
            _this.wwyy.yysd = wwyy.yysd;
            _this.wwyy.yyrq = wwyy.yyrq;
            _this.wwyy.deptcode = wwyy.deptcode;
            _this.wwyy.deptname = wwyy.deptname;
            _this.wwyy.yyslmax = wwyy.yyslmax;//当前时段的最大预约值
            _this.wwyy.yysl='1';//预约数量
            _this.wwyy.yytype = wwyy.yytype;
            _this.getZjlx();//获取证件类型
            _this.wwyy.zjlx = 'A'; //默认证件类型
            _this.$forceUpdate();


        },
        methods:{

            /**
             * 验证当前选择的最大数不能超过当前时段的最大能预约数
             */
            checkMaxNum(){
             let _this = this;
             if(_this.wwyy.yysl > _this.wwyy.yyslmax){
                  Dialog({ message: "申请数量不足！" });
                 _this.wwyy.yysl = _this.wwyy.yyslmax;
                 _this.$forceUpdate();
             }

            },
            /**
             * 保存预约信息
             */
            savewwyy(){
                let _this = this;
                if(Tool.isEmpty(Tool.getWxUser()) ){
                    Dialog({ message: "请实名认证" });
                    _this.$router.push("/smrz");
                }

                _this.wwyy.id = Tool.uuid(12);//生成ID
                _this.wwyy.openid=Tool.getWxUser().openid;//获取opendi
                _this.$forceUpdate();
                if(Tool.isEmpty( _this.wwyy.name)){
                    Dialog({ message: "请填写姓名" });
                    return;
                }
                if(Tool.isEmpty( _this.wwyy.zjhm)){
                    Dialog({ message: "请填写证件号码" });
                    return;
                }
                if(Tool.isEmpty( _this.wwyy.sjhm)){
                    Dialog({ message: "请填写手机号码" });
                    return;
                }
                if(Tool.isEmpty( _this.wwyy.kclx)){
                    Dialog({ message: "请选择客车类型" });
                    return;
                }
                if(Tool.isEmpty( _this.wwyy.dwmc)){
                    Dialog({ message: "请填写单位名称" });
                    return;
                }
                if(Tool.isEmpty( _this.wwyy.xydm)){
                    Dialog({ message: "请填写企业信用代码" });
                    return;
                }

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/savewwyy',_this.wwyy ).then((response)=>{
                    let resp = response.data;
                    if(resp.success){
                        SessionStorage.remove(SAVY_YY_INFO);//移除当前传递的对象 防止跨页面直接访问
                        SessionStorage.set(SAVY_YY_SUCCESS,_this.wwyy.id);//保存预约信息的ID
                        _this.$router.push("/ywyy/ywgryycg");
                    }else {
                        /**
                         * 预约数量不足 重新跳转部门先择
                         */
                        Dialog.alert({
                            message: resp.message,
                        }).then(() => {
                            // on close
                        });
                        // SessionStorage.set(LINK_TO_YY_INFO,_this.wwyy);//包括预约基本信息
                        _this.$router.push("/ywyy/ywsldw");//重新选择
                    }



                })


            },

            /**
             * 初始化获取业务类型
             */
            getZjlx(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/getZjlx').then((response)=>{
                    let resp = response.data;
                    _this.zjlxs=resp.content;

                })

            },

            /**
             * 回调获取证件的code值
             * @param value
             */
            onConfirm(value) {
                let   _this = this;
                _this.value = value.text;
                _this.wwyy.zjlx = value.code;
                _this.$forceUpdate();
                _this.showPicker = false;
            },

        }


    }
</script>

<style scoped>

</style>
