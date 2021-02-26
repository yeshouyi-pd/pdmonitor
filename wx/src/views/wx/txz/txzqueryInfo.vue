<template>
    <div>
      <div style="background-color:white">
        <div  class="van-address-item" style="background-color:white ;padding-bottom: 60px">
          <div class="van-cell van-cell--borderless" style="border-radius:20px;background-color: #FFFAFA;margin-top:1em">
            <div class="van-cell__value van-cell__value--alone van-address-item__value">
              <div class="van-radio" >
                                <span class="van-radio__label">
                                <div class="" style="height: 1em; font-weight: bold;font-size: 2em;">
                                    <div style="margin-top: 1em;text-align: center">
                                      {{pass.hphm}}
                                      <van-tag    v-bind:type="ztshow.class" >{{ztshow.show}}</van-tag>

                                    </div>

                                </div>
                                <div class="van-address-item__address" style="font-size: 1.1em;line-height:2em; color: #6c6c6c">
                                  所有人：{{pass.syr }}<br/>
                                  联系电话：{{pass.lxdh }}<br/>
                                  申请时间：{{pass.createTime}}<br/>
                                  申请状态：<span class="colors">{{SLZT_STATUS|optionKVArray(pass.zt)}}</span><br/>
                                  通行证类型：{{zjlx|optionMapKV(pass.txzlx)}}<br/>
                                  有效期：<span class="colors">{{pass.yxqkssj}}至{{pass.yxqjssj}}</span><br/>
                                  出发地：{{pass.cfd}}<br/>
                                  目的地：{{pass.mdd}}<br/>
                                  通行线路：<span class="colors">{{pass.txxl }}</span><br/>
                                  申请说明：{{pass.sqsm }}<br/>
                                </div>
                                </span>

              </div>
            </div>
          </div>
        </div>

      </div>



      <div class="van-address-list__bottom">
        <van-button  round block type="info"
                     color="linear-gradient(to right,#00BFFF,#0000FF)"
                     to="/index">
          返回首页
        </van-button>
        <div style="margin-top: 8px"></div>
      </div>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";
    export default {

        name:'txzqueryInfo',
        data:function(){
            return{
              pass:{},
              SLZT_STATUS:[{key:"1", value:"未审核"},{key:"2", value:"已审核"},{key:"3", value:"审核不通过"}],//受理状态
              zjlx:[],
              ztshow: {},

            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            let pass =   SessionStorage.get(LIKN_TO_TXZSHOWINFO) || {};
            _this.pass = pass;
            _this.getZljx();
            _this.JudgeTime();

        },
        methods:{
          getZljx(){
            let _this = this;
            _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/wx/lstxz/getZljx').then((response) => {
              let resp = response.data;
              _this.zjlx = resp.content;
            })

          },
          JudgeTime(){
            let _this = this;
            let  nowDate = new Date();
            let nowTime = Date.UTC(nowDate.getFullYear(), nowDate.getMonth() + 1, nowDate.getDate());

            if(Tool.isNotEmpty(_this.pass.yxqkssj) &&
                Tool.isNotEmpty(_this.pass.yxqjssj)&&
                 Tool.isNotEmpty(_this.pass.zt)){
              let planStartTime=this.pass.yxqkssj.split('-');
              let planStopTime=this.pass.yxqjssj.split('-');
              let startTime= Date.UTC(parseInt(planStartTime[0]), parseInt(planStartTime[1]), parseInt(planStartTime[2]));
              let stopTime= Date.UTC(parseInt(planStopTime[0]), parseInt(planStopTime[1]), parseInt(planStopTime[2]));

                if( _this.pass.zt === '2'){
                  console.log(nowTime +"========="+startTime+"==============="+stopTime)

                  if(nowTime < startTime){
                  _this.ztshow.show="未生效";
                  _this.ztshow.class="primary";
                }else if(nowTime > stopTime){
                  _this.ztshow.show="已过期";
                  this.ztshow.class="danger";
                }else{
                  _this.ztshow.show="正常";
                  this.ztshow.class="success";
                }
              }else{
                _this.ztshow.show="无效";
                this.ztshow.class="warning";
              }

            }else{
              _this.ztshow.show="无效";
              this.ztshow.class="warning";
            }
          }


        }

    }
</script>

<style scoped>
 .colors{
   color: red;

 }



</style>
