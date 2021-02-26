<template>
    <div>
        <van-image width="100%" src="/static/wx/bmfw/tccx1.jpg"/>
        <van-form validate-first @submit="onSubmit">
            <div>
                <van-field
                        readonly
                        clickable
                        name="cllbmc"
                        :value="cllbmc"
                        label="车辆类别"
                        placeholder="请点击选择车辆类别"
                        @click="showPicker = true"
                />
                <van-popup v-model="showPicker" position="bottom">
                    <van-picker
                            show-toolbar
                            :columns="columns"
                            @confirm="onConfirm"
                            @cancel="showPicker = false"
                    />
                </van-popup>

                <van-field
                        v-model="hphm"
                        label="号牌号码"
                        name="hphm"
                        placeholder="请输入号牌号码"
                />

                <van-field
                        v-model="clsbdh"
                        label="车辆识别代号"
                        name="clsbdh"
                        placeholder="请输入车辆识别代号后六位"
                />
                <div class="button_db">
                    <van-button round block type="info" native-type="submit">
                        提交
                    </van-button>
                </div>
                <div class="divtext">
                    文明出行，规范停车！
                    海口交管部门对下列违停行为，
                    一律实行拖移车辆措施：<br/>
                    堵塞应急通道违法停车；<br/>
                    人行横道违法停车；<br/>
                    公交车站违法停车；<br/>
                    交叉路口及距路口50米路段违法停车；<br/>
                    公共停车场周边300米违法停车；<br/>
                    双排后多排违法停车；<br/>
                    易引发事故造成拥堵的违法停车；
                </div>
            </div>
        </van-form>
    </div>
</template>

<script>
import Dialog from "vant/lib/dialog";
    export default {
        name: "tccx",
        data() {
            return {
              hphm: '',
              cllbmc:'',
              cllb: '',
              clsbdh: '',
              value: '',
              columns: [],
              showPicker: false,
            };
        },
        mounted: function () {//mounted初始化方法
          let _this = this;
          _this.getCllb();
        },
        methods: {
          /**
           * 获取车辆类别
           */
          getCllb(){
            let _this = this;
            _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxCllb', {
            }).then((response)=>{
              let resp = response.data;
              _this.columns = resp.content;
            })
          },
          onConfirm(value) {
            let _this = this;
            _this.cllbmc = value.text;
            _this.cllb = value.code;
            _this.showPicker = false;
          },
          onSubmit() {
            let _this = this;
            if(Tool.isEmpty( _this.cllbmc)){
              Dialog({ message: "请点击选择车辆类别" });
              return;
            }
            if(Tool.isEmpty( _this.hphm)){
              Dialog({ message: "请输入正确号牌号码" });
              return;
            }
            if(Tool.isEmpty( _this.clsbdh)){
              Dialog({ message: "请输入车辆识别代号后六位" });
              return;
            }
            _this.$router.push({path:'/tcjgcx', query:{cllb: _this.cllb, hphm: _this.hphm, clsbdh: _this.clsbdh}});
          },
        }
    }
</script>

<style scoped>
    .button_db {
        width: 90%;
        margin: 10% 0 0 5%;
    }
    .divtext {
        width: 90%;
        margin: 5% 0 0 5%;
        font-size: 13px;
        /*color: #969696;*/
        color: red;
        border: 1px solid #969696;
        border-radius: 5px;
    }
</style>