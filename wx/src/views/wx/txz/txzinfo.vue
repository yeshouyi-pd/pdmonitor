<template>
    <div>
        <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
            <van-swipe-item>
                <img src="/static/wx/txz/txzindex.jpg" style="width: 100%;" />
            </van-swipe-item>
        </van-swipe>
        <van-cell-group  style="margin-bottom: 40px">
            <van-field
                    readonly
                    clickable
                    label="号牌种类"
                    :value="pass.hpzlvalue"
                    placeholder="选择号牌种类"
                    @click="showhpzlPicker = true"
            />
            <van-popup v-model="showhpzlPicker" round position="bottom">
                <van-picker
                        show-toolbar
                        :columns="hpzls"
                        :default-index="pass.hpzlindex"
                        @cancel="showhpzlPicker = false"
                        @confirm="hpzlonConfirm"
                />
            </van-popup>
            <van-field  label="号牌号码" readonly v-on:click="checkhphm()"  v-model="pass.hphm"  placeholder="请选择号牌号码" />
            <van-field
                    readonly
                    clickable
                    label="通行证类型"
                    :value="pass.txzlxvalue"
                    placeholder="选择通行证类型"
                    ref="checkbox"
                    @click="showtxzlxPicker = true"
            />
            <van-popup v-model="showtxzlxPicker" round position="bottom">
                <van-picker
                        show-toolbar
                        :default-index="pass.txzlxindex"
                        :columns="txzlxs"
                        @cancel="showtxzlxPicker = false"
                        @confirm="txzlxonConfirm"
                />
            </van-popup>
            <van-field  label="个人/单位名" v-model="pass.sqr" placeholder="个人/单位名" />
            <van-field  label="所有人"  v-model="pass.syr" placeholder="所有人" />
            <van-field  label="联系人电话" type="tel" v-model="pass.lxdh" placeholder="联系人电话" />
            <van-field  label="申请开始时间"  style="display: none"  v-model="pass.yxqkssj"/>
            <van-field  label="申请结束时间"  style="display: none"  v-model="pass.yxqjssj"/>
            <van-field label="申请有效期" :value="date" readonly    @click="show = true" />
            <van-calendar
                    title="选择申请通行证有效期范围"
                    v-model="show"
                    type="range"
                    :default-date="null"
                    :min-date="minDate"
                    :max-date="maxDate"
                    @confirm="onConfirm" />
            <van-row>
                <van-col span="18">
                    <van-field  label="出发地" placeholder="出发地"  v-model="pass.cfd"/>
                </van-col>
                <van-col span="6">
                    <van-button
                            v-on:click="getadd('1')"
                            style="width: 80%"
                            size="small"
                            round
                            color="linear-gradient(to right, #43CBFF,#9708CC)"
                            >出发地</van-button
                    >
                </van-col>
            </van-row>
            <van-row>
                <van-col span="18">
                    <van-field  label="目的地" placeholder="目的地" v-model="pass.mdd"/>
                </van-col>
                <van-col span="6">
                    <van-button
                            v-on:click="getadd('2')"
                            style="width: 80%"
                            size="small"
                            round
                            color="linear-gradient(to right, #43CBFF,#9708CC)"
                            >目的地</van-button
                           >
                </van-col>
                <van-field
                        v-model="pass.sqsm"
                        rows="2"
                        autosize
                        label="申请说明"
                        type="textarea"
                        maxlength="200"
                        placeholder="请填写详细需求说明"
                        show-word-limit
                />
            </van-row>
            <van-dialog v-model="showmap"
                        :before-close="beforeclose"
                        style="width: 95%;height: 90%;margin-top: 5%"
                        show-cancel-button>
                <div style="height: 100%">
                    <iframe  id ="roadiframe"  style="width: 100%;height: 100%" :src="src">
                    </iframe>
                </div>
            </van-dialog>
            <van-dialog v-model="showhphm"
                        :before-close="beforeclosehphm"
                        style="width: 95%;height: 60%;line-height:1em;font-size:10px"
                        show-cancel-button>
                <div style="height:100%">
                    <iframe    style="width: 100%;height: 100%" :src="hphmsrc">
                    </iframe>
                </div>
            </van-dialog>
        </van-cell-group>
        <van-row style="margin-bottom: 10px">
            <van-col span="12">
                <button
                        class="van-button van-button--warning van-button--large van-goods-action-button van-goods-action-button--first van-goods-action-button--warning"
                        style="background: linear-gradient(to right,	 #7FFFAA,#1E90FF);
                               box-shadow: 2px 2px 10px #00FFFF;"
                         >上一步</button>
            </van-col>
            <van-col  span="12">
                <button
                        class="van-button van-button--danger van-button--large van-goods-action-button van-goods-action-button--last van-goods-action-button--danger"
                        style="background: linear-gradient(to right, #1E90FF,#7FFFAA);
                               box-shadow: 2px 2px 10px #00FFFF;"
                       v-on:click="touploadpic()"
                       >下一步</button>
            </van-col>
        </van-row>



    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    export default {
        name:'txzinfo',
        data:function(){
            return{
                showmap: false,//地图
                showhphm:false,//号牌
                date: '',//时间范围 显示
                show: false, //日期
                src:'',
                hphmsrc:'',
                pass:{},//通行证对象
                minDate:new Date(),//最小开始时间
                maxDate:new Date(),//最大结束时间
                txzlxs:[],//通行证类型数据
                showtxzlxPicker:false,//通行证模态框
                showhpzlPicker:false,//号牌种类模态框
                hpzls:[],//号牌种类 数据

            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
          _this.getdateN();//获取开始结束时间段
          _this.gettzlywlx();//获取证件类型
            let pass =  SessionStorage.get(LIKN_TO_TXZ)|| {} ;
             if(Tool.isNotEmpty(pass)){
               _this.pass=pass;
               //显示开始结束时间
               if(Tool.isNotEmpty(pass.yxqkssj) && Tool.isNotEmpty(pass.yxqjssj)){
                 _this.date = pass.yxqkssj+"至"+pass.yxqjssj;

               }

             }else{
                Dialog({ message: "操作异常，请重试" });
               _this.$router.push("/index");
             }


        },
        methods:{
          /**
           *跳转信息上传
           */
          touploadpic(){
            let   _this = this;

            if(Tool.isEmpty( _this.pass.hpzl)){
              Dialog({ message: "请选择号牌种类" });
              return;
            }
            if(Tool.isEmpty( _this.pass.hphm)){
              Dialog({ message: "请选择号牌号码" });
              return;
            }
            if(Tool.isEmpty( _this.pass.txzlx)){
              Dialog({ message: "请选择通行证类型" });
              return;
            }
            if(Tool.isEmpty( _this.pass.sqr)){
              Dialog({ message: "请填写单位名称" });
              return;
            }
            if(Tool.isEmpty( _this.pass.syr)){
              Dialog({ message: "请填写所有人信息" });
              return;
            }
            if(Tool.isEmpty( _this.pass.lxdh)){
              Dialog({ message: "请填写联系电话" });
              return;
            }
            if(Tool.isEmpty( _this.pass.yxqkssj)||Tool.isEmpty( _this.pass.yxqjssj)){
              Dialog({ message: "请选择申请有效期范围" });
              return;
            }
            if(Tool.isEmpty( _this.pass.cfd)){
              Dialog({ message: "请填写出发地" });
              return;
            }
            if(Tool.isEmpty( _this.pass.mdd)){
              Dialog({ message: "请填写目的地" });
              return;
            }
             SessionStorage.set(LIKN_TO_TXZ,_this.pass);//包括预约基本信息
             _this.$router.push("/txz/txzpic");//重新选择
          },
            /**
             * 初始化获取业务类型
             */
            gettzlywlx(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/wx/lstxz/gettzlywlx').then((response)=>{
                    let resp = response.data;
                    _this.txzlxs=resp.content.txzlist;
                    _this.hpzls=resp.content.hpzllist;

                })

            },

            /**
             *号牌种类 回调
             */
            hpzlonConfirm(value,index){
                let   _this = this;
                _this.pass.hpzlvalue = value.text;
                _this.pass.hpzl = value.code;
                _this.pass.hpzlindex = index;//索引
                _this.$forceUpdate();
                _this.showhpzlPicker = false;

            },
            /**
             *通行证类型 回调
             */
            txzlxonConfirm(value,index){
                let   _this = this;
                _this.pass.txzlxindex =index;//索引
                _this.pass.txzlxvalue = value.text;
                _this.pass.txzlx = value.code;
                _this.$forceUpdate();
                _this.showtxzlxPicker = false;

            },
            /**
             * 回调获取选择号牌号码
             */
            beforeclosehphm(action, done){
                let _this =this;
                if(action === 'confirm'){
                    let checkhphm=  SessionStorage.get("CHECKHPHM");
                    if(Tool.isNotEmpty(checkhphm)){
                        _this.pass.hphm = checkhphm;//出发地
                        _this.$forceUpdate();
                    }else{
                        Dialog({ message: "当前号牌格式不正确，请重新选择" });
                    }
                }

                done();//关闭弹框

            },
            /**
             * 回调获取选择的地点信息
             */
            beforeclose(action, done){
                let _this =this;
                if(action === 'confirm'){
                    let checktype =  SessionStorage.get("CHECK_TYPE");
                    if(checktype === '1'){ //出发地
                        let fcd =  SessionStorage.get("CFD_MANE");//出发地名字
                        let fcdzb =  SessionStorage.get("CFD_ZB");//出发地坐标
                        if(Tool.isNotEmpty(fcd)){
                            _this.pass.cfd = fcd;//出发地
                            _this.$forceUpdate();
                        }
                    }else if(checktype === '2'){//目的地
                        let mdd =  SessionStorage.get("MDD_MANE");//目的地名字
                        let mddzb =  SessionStorage.get("MDD_ZB");//目的地坐标
                        if(Tool.isNotEmpty(mdd)){
                            _this.pass.mdd = mdd;//出发地
                            _this.$forceUpdate();
                        }
                    }
                }

                done();//关闭弹框

            },
            checkhphm(){
                let _this =this;
                SessionStorage.remove("CHECKHPHM");
                _this.hphmsrc = '/static/utils/hphm.html?times='+new Date().getTime();
                _this.showhphm=true;

            },
            /**
             *选择地点 1 出发地 2 目的地
             */
            getadd(obj){
                  let _this =this;
                   SessionStorage.set("CHECK_TYPE",obj)
                  _this.src = '/static/utils/road.html?times='+new Date().getTime();
                  _this.showmap=true;
            },
            formatDate(date) {
                let _this = this;
                let nien = date.getFullYear();
                let intmonth =date.getMonth() + 1;
                let intdate = date.getDate();
                let month = intmonth+'';
                let ri = intdate+'';

                if(month.length === 1){//格式化时间
                    month ='0'+month;
                }
                if(ri.length === 1){//格式化时间
                    ri ='0'+ri;
                }
                return  nien+"-"+month+"-"+ri;

            },
            onConfirm(date) {
                let  [start, end] = date;
                let _this = this;
                _this.show = false;
                _this.pass.yxqkssj=_this.formatDate(start);
                _this.pass.yxqjssj=_this.formatDate(end);
                _this.$forceUpdate();
                _this.date = `${_this.formatDate(start)} 至 ${_this.formatDate(end)}`;
            },
            /**
             * 获取当前时间的前N天
             */

            getdateN(){
                let _this = this;
                let checkStart = 3;//一天之后
                let checkEnd = 94;//最大90天
                let dayStart = new Date();
                dayStart.setTime(dayStart.getTime()+24*60*60*1000*checkStart);
                let dayEnd = new Date();
                dayEnd.setTime(dayEnd.getTime()+24*60*60*1000*checkEnd);
                _this.minDate = new Date(dayStart.getFullYear(), dayStart.getMonth(), dayStart.getDate());
                _this.maxDate = new Date(dayEnd.getFullYear(), dayEnd.getMonth(), dayEnd.getDate());
            },


        }

    }
</script>

<style scoped>
 /deep/.van-dialog__content{
    height: 88%;

}

</style>
