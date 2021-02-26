<template>
    <div>
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
                <img src="/static/wx/ywyy/ywyyindex.jpg" style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>
        <van-notice-bar color="#1989fa" background="white" >
            <font style=" font-size: 0.9em;font-weight:bold;">
              {{dept.deptname}}
            </font>
        </van-notice-bar>
        <div
                round
                style="background-image: url('/static/wx/ywyy/yycheckdate.png');
                  background-size: 100% 100%;
                  background-position: center center;
                  overflow: auto;
                  height: 80px;
                  border-radius: 10px;
                  margin: 0 13px 0 13px;"
                 size="large"  @click="show = true">

            <div style="padding-top: 20px;margin-left: 50%">
                <font style="font-size: 1.4em;color: white;" >选择预约日期</font><br/>
                 <div v-show="!wwyy.yysj"
                      style="margin-left: 20%">
                     <font style="font-size: 0.8em;color: white;" >尚未选择时间</font>
                 </div>
               <div v-show="wwyy.yysj"
                    style="margin-left: 20%">
                <font style="font-size: 0.8em;color: white;" >{{wwyy.yysj}}</font>
               </div>
            </div>
        </div>
        <van-calendar v-model="show"
                      @confirm="onConfirm"
                      title="预约日期选择"
                      color="#1989fa"
                      :formatter="formatter"
                      :default-date="null"
                      :max-date="maxDate"/>
        <van-notice-bar color="#1989fa" background="white" >
            <font v-show="wwyy.yysj" style=" font-size: 0.9em;font-weight:bold;">
                预约时间段期
            </font>
        </van-notice-bar>
        <div class="van-address-list">
            <van-radio-group v-model="wwyy.yysd" direction="horizontal">
               <div   v-for="deptYysj in deptYysjDtos"  v-on:click="check(deptYysj.id)"   class="van-cell van-cell--center ">
                   <div  style="width: 100%" v-show="deptYysj.yymun > 0">
                        <div  style="float: left"><van-radio :name="deptYysj.id"/></div>
                        <div  style="float: left">{{deptYysj.stime}}-{{deptYysj.etime}}</div>
                        <div  style="float: right">剩余量：{{deptYysj.yymun}}</div>
                   </div>


               </div>
            </van-radio-group>
        </div>
        <div class="van-address-list__bottom">
            <van-button  round block type="info"
                         color="linear-gradient(to right,#00BFFF,#0000FF)"
                          v-on:click="gryysave()">
                下一步
            </van-button>
            <div style="margin-top: 8px"></div>
        </div>

    </div>

</template>

<script>
    import Dialog from "vant/lib/dialog";
    export default {
        name:'ywyusd',
        data:function(){
            return{
                wwyy:{},//保存的实体类对象
                show: false,//显示选择预约弹框
                maxDate: new Date(),
                yyWxYyDtos:[],//预约时段
                dept:{},//部门信息
                deptYysjDtos:[],//选中事件后显示预约当天可预约数
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            /**
             * wwyy 可能包括页面回退时候的其他参数
             * 为防止数据混乱 回退时只保修基础参数
             *
             */
            let wwyy =  SessionStorage.get(SAVY_YY_INFO)|| {} ;
            console.log(wwyy);
            if(Tool.isEmpty(wwyy)){
                _this.$router.push("/index");
            }
            if(Tool.isEmpty(wwyy.ywfl) ||
                Tool.isEmpty(wwyy.ywlx)||
                Tool.isEmpty(wwyy.deptcode)||
                Tool.isEmpty(wwyy.yytype)){
                _this.$router.push("/index");//必要参数不能为空
            }

            /**
             * 只保留基础属性
             *
             */
            _this.wwyy.ywfl = wwyy.ywfl;
            _this.wwyy.ywlx = wwyy.ywlx;
            _this.wwyy.deptcode = wwyy.deptcode;
            _this.wwyy.yytype = wwyy.yytype;
            _this.wwyy.daymax = wwyy.daymax;//可能为空
            _this.$forceUpdate();
            _this.getYyInfo();

        },
        methods:{
            /**
             * 个人预约保存
             */
            gryysave(){
                let _this = this;
                if(Tool.isEmpty(_this.wwyy.yysd)){
                    Dialog.alert({message: '请选择预约时间段！'});
                    return;

                }
                /**
                 * 传递信息
                 * 包括： 部门对象  预约预约对象那个
                 */
                for(let i = 0; i< _this.deptYysjDtos.length ; i++){
                    if(_this.wwyy.yysd === _this.deptYysjDtos[i].id){
                        _this.wwyy.yyrq = _this.deptYysjDtos[i].stime+"-"+_this.deptYysjDtos[i].etime;
                        _this.wwyy.yyslmax =_this.deptYysjDtos[i].yymun;//当前时段最大预约数量 （企业）
                        break;
                    }
                }

                _this.wwyy.deptname = _this.dept.deptname;
                let openid = "";
                 if(Tool.isEmpty(Tool.getWxUser()) ){
                     Dialog({ message: "请实名认证" });
                    _this.$router.push("/smrz");
                }else{
                    openid =Tool.getWxUser().openid;
                    if(Tool.isEmpty(openid)){
                      Dialog({ message: "操作异常！" });
                     _this.$router.push("/index");
                    }

                }
                _this.wwyy.openid = openid;
                /**
                 * 获取当天 当前部门 当前用户的已经最大预约数
                 */

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/queryCanyyBydept', _this.wwyy ).then((response)=>{
                    let resp = response.data;
                    let  daymax = resp.content;
                    if(daymax <= 0){//当前部门预约次数不足
                        Dialog.alert({message: '您在该部门当天预约次数已约完，请重新选择！'});
                    }else{
                        if(_this.wwyy.yyslmax > daymax){
                            _this.wwyy.yyslmax = daymax;//将当前的最大值设置为当前部门的最大值（减除当天已预约的）
                            _this.$forceUpdate();
                        }
                        /**
                         * 包含如下字段
                         * ywfl
                         * ywlx
                         * deptcode
                         * yysj  :2020-11-26
                         * yysd : dept_yysd 的id
                         * yyrq : 12:20 -13:20
                         * deptcode
                         * deptname
                         * yyslmax//部门表设置的当天办理最大数
                         */
                        SessionStorage.set(SAVY_YY_INFO,_this.wwyy);//继续传递 wwyy保存对象
                        if("1" === _this.wwyy.yytype){//个人预约
                            _this.$router.push("/ywyy/ywgryyxx");
                        }else if("2" === _this.wwyy.yytype){//企业预约
                            _this.$router.push("/ywyy/ywqyyyxx");
                        }else{
                            _this.$router.push("/index");
                        }

                    }

                })
            },
            /**
             * 选择DIV也会选中单选钮
             */
            check(obj){
               let _this =this;
               _this.wwyy.yysd = obj;
               _this.$forceUpdate();

            },

            /**
             * 获取预约情况
             */
            getYyInfo(){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/getDeptDay',   {
                    deptcode : _this.wwyy.deptcode
                }).then((response)=>{
                    let resp = response.data;
                    _this.dept=resp.content.dept;
                    _this.yyWxYyDtos = resp.content.yysl;
                    _this.getdateN(_this.dept.maxday-1);

                })
            },
            /**
             * 选择预约弹框
             */
            onConfirm(date) {
                let _this = this;
                _this.show = false;
                _this.wwyy.yysj= _this.formatDate(date);


                _this.$forceUpdate();

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/ywyy/getYysd',   {
                    deptcode : _this.dept.deptcode,
                    checkday :_this.wwyy.yysj,
                }).then((response)=>{
                    let resp = response.data;
                    _this.wwyy.yysd='';//重置选择状态
                    _this.deptYysjDtos =resp.content;//获取当天的预约情况
                    _this.$forceUpdate();

                })
            },
            /**
             * 获取当前时间的钱N天
             */

            getdateN(maxday){
                let _this = this;
                let day = new Date();
                day.setTime(day.getTime()+24*60*60*1000*maxday);
                let nian =day.getFullYear();
                let yue = day.getMonth();
                let ri = day.getDate();
                _this.maxDate = new Date(nian, yue, ri);
            },

            /**
             * 格式化时间
             * @param day
             * @returns {*}
             */

            formatter(day) {
                let _this = this;
                let  nien = day.date.getFullYear();
                let intmonth = day.date.getMonth() + 1;
                let intdate = day.date.getDate();
                let month = intmonth+'';
                let date = intdate+'';

                if(month.length === 1){//格式化时间
                    month ='0'+month;
                }
                if(date.length === 1){//格式化时间
                    date ='0'+date;
                }
                let nyr= nien+"-"+month+"-"+date
                let yydata = _this.yyWxYyDtos;

                for(let i = 0 ;i < yydata.length; i++){
                    let thisyy = yydata[i];
                    if(nyr === thisyy.yysj){
                        if("1" === thisyy.zt){
                            day.bottomInfo = '不可预约';
                            day.type ="disabled";
                        }else{
                            if(thisyy.yysl > 0){
                                day.topInfo='('+ thisyy.yysl+')';
                                day.bottomInfo = '可预约';
                            }else{
                                day.bottomInfo = '已约满';
                                day.type ="disabled";
                            }

                        }

                    }
                    continue;
                }


                return day;
            },
            formatDate(date) {
                let nien = date.getFullYear();
                let intmonth = date.getMonth() + 1;
                let intdate = date.getDate();
                let month = intmonth+'';
                let ri = intdate+'';
                if(month.length === 1){//格式化时间
                    month ='0'+month;
                }
                if(ri.length === 1){//格式化时间
                    ri ='0'+ri;
                }
                return nien+"-"+month+"-"+ri;
            },

        }


    }
</script>

<style scoped>
    .van-contact-card {
        padding: 10px;
    }
    .van-divider {
        display: -webkit-box;
        display: -webkit-flex;
        display: flex;
        -webkit-box-align: center;
        -webkit-align-items: center;
        align-items: center;
        margin: 0px 0;
        color: #969799;
        font-size: 14px;
        line-height: 24px;
        border-color: #ebedf0;
        border-style: solid;
        border-width: 0;
    }
</style>
