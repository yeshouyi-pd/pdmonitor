<template>
  <div>
    <van-count-down :time="time" format="mm:ss" :auto-start="false" ref="countDown" @finish="timeIsFinished" class="wfzxjyksdj-ds"/>
    <div v-for="(item,index) in questionList">
      <div v-if="index==curIndex">
        <div v-if="item.dtlb=='1'">
          <div class="wfzxjyksdj-title">【选择题】 {{item.tmbt}}</div>
          <div class="wfzxjyksdj-content">
            <van-radio-group v-model="radio" :disabled="showAnswer?true:false">
              <van-radio name="A" :checked-color="checkedColor" class="wfzxjyksdj-item" v-if="item.a">A.{{item.a}}</van-radio>
              <van-radio name="B" :checked-color="checkedColor" class="wfzxjyksdj-item" v-if="item.b">B.{{item.b}}</van-radio>
              <van-radio name="C" :checked-color="checkedColor" class="wfzxjyksdj-item" v-if="item.c">C.{{item.c}}</van-radio>
              <van-radio name="D" :checked-color="checkedColor" class="wfzxjyksdj-item" v-if="item.d">D.{{item.d}}</van-radio>
            </van-radio-group>
          </div>
        </div>
        <div v-if="item.dtlb=='2'">
          <div class="wfzxjyksdj-title">【多选题】 {{item.tmbt}}</div>
          <div class="wfzxjyksdj-content">
            <van-checkbox-group v-model="result" :disabled="showAnswer?true:false">
              <van-checkbox name="A" :checked-color="checkedColor" class="wfzxjyksdj-item" v-if="item.a">A.{{item.a}}</van-checkbox>
              <van-checkbox name="B" :checked-color="checkedColor" class="wfzxjyksdj-item" v-if="item.b">B.{{item.b}}</van-checkbox>
              <van-checkbox name="C" :checked-color="checkedColor" class="wfzxjyksdj-item" v-if="item.c">C.{{item.c}}</van-checkbox>
              <van-checkbox name="D" :checked-color="checkedColor" class="wfzxjyksdj-item" v-if="item.d">D.{{item.d}}</van-checkbox>
            </van-checkbox-group>
          </div>
        </div>
        <div v-if="item.tp1" class="wfzxjyksdj-tp-div"><img :src="path+item.tp1" class="wfzxjyksdj-tp"/></div>
        <div v-if="item.tp2" class="wfzxjyksdj-tp-div"><img :src="path+item.tp2" class="wfzxjyksdj-tp"/></div>
        <div v-if="showAnswer&&correctAnswer" class="wfzxjyksdj-xsjg">
          <span>答题结果：</span>
          <span style="color:#4DAB4C;">正确</span>
        </div>
        <div v-if="showAnswer&&!correctAnswer" class="wfzxjyksdj-xsjg">
          <span>答题结果：</span>
          <span style="color:#fd112cf2;">错误</span>
          <span style="margin-left: 20px;">正确答案：</span>
          <span style="color:#4DAB4C;">{{item.zqda}}</span>
        </div>
        <div class="wfzxjyksdj-bottom-content">
          <van-button round block type="info" v-if="!showAnswer" v-on:click="dati(item)">答题</van-button>
          <van-button round block type="info" v-if="showAnswer&&index<totalQuestionCount" v-on:click="next(item,index)">下一题</van-button>
          <van-button round block type="info" v-if="showAnswer&&index==totalQuestionCount" v-on:click="submitDJ(item)">提交答卷</van-button>
          <div class="wfzxjyksdj-dtjd" v-show="index<totalQuestionCount">{{curIndex+1}}/100</div>
          <div class="wfzxjyksdj-cgcwsl">
            <van-icon name="passed" color="#1989fa" /> {{correctCount}}
            <van-icon name="close" color="#ee0a24" style="margin-left: 20px"/> {{ errorCount }}
          </div>
        </div>
      </div>
    </div>
    <van-overlay :show="showOverlay">
      <div class="wrapper">
        <van-loading type="spinner" color="#1989fa" />
      </div>
    </van-overlay>
    <van-dialog v-model="showDialog" :show-confirm-button="false" @close="jxdt()">
      <div class="ksdj-dialog-content">
        <div>您有未完成的考试</div>
        <img src="/static/wx/wfzxjy/wfzxjyksdj-ksbg.png" class="ksdj-dialog-content-img"/>
        <div class="ksdj-dialog-dtqk">
          <div class="ksdj-dialog-dtqk-item"><p>{{100-errorCount-correctCount}}</p><p>未答题数</p></div>
          <div class="ksdj-dialog-dtqk-item"><p>{{errorCount}}</p><p>错题数</p></div>
          <div class="ksdj-dialog-dtqk-item"><p>{{correctCount}}</p><p>考试得分</p></div>
        </div>
        <div class="ksdj-dialog-btn">
          <van-button class="ksdj-dialog-btn-item" size="small" plain type="info" v-on:click="xzjq()">现在交卷</van-button>
          <van-button class="ksdj-dialog-btn-item" size="small" type="info" v-on:click="jxdt()">继续答题</van-button>
        </div>
      </div>
    </van-dialog>
  </div>
</template>
<script>
import Dialog from "vant/lib/dialog";
export default {
  name: "wfzxjyksdj",
  data: function (){
    return {
      time:0,
      radio:'',
      result:[],
      questionList:[],
      curIndex:0,
      curItem:{},
      showAnswer:false,
      correctAnswer:false,
      correctCount:0,
      errorCount:0,
      totalQuestionCount:99,
      checkedColor:'#1989fa',
      startTime:'',
      answerList:[],
      tmIdList:[],
      path:process.env.VUE_APP_SERVER,
      showOverlay:false,
      type:'',
      logid:'',
      showDialog:false,
      firstSave:false,
      user:{},
    }
  },
  mounted() {
    let _this = this;
    _this.user = Tool.getWxUser();
    _this.initQuestion();
  },
  beforeDestroy() {
    let _this = this;
    if(_this.showAnswer){
      _this.next(_this.curItem,_this.curIndex);
    }
    if(!_this.firstSave){
      _this.submitDJ();
    }
  },
  methods:{
    initQuestion(){
      let _this = this;
      let formData = {
        'xm':_this.user.zjmc,
        "sfzmhm":_this.user.zjhm,
        "cfbh":_this.$route.query.cfbh
      };
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/drvExam/findLastLog', formData).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          //判断开始时间和当前时间是不是超过了45分钟
          let content = resp.content;
          if((!content) || (content && content.kssj && (new Date().getTime()-new Date(content.kssj).getTime())>45*60*1000)
              || (content.questionList && content.answerList && content.questionList.length==content.answerList.length)){
            _this.initNewQuestion();
          }else{
            _this.showDialog = true;
            _this.type = 'edit';
            _this.questionList = resp.content.questionList;
            _this.startTime = resp.content.kssj;
            _this.answerList = resp.content.answerList;
            _this.tmIdList = resp.content.tmIdList;
            _this.curIndex = _this.answerList.length;
            _this.correctCount = resp.content.correctCount;
            _this.errorCount = resp.content.errorCount;
            _this.logid = resp.content.id;
            _this.time = 45*60*1000-(new Date().getTime()-new Date(content.kssj).getTime());
          }
        } else {
          Dialog({message: resp.message});
        }
      })
    },
    initNewQuestion(){
      let _this = this;
      _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/wx/drvExam/selectByLimit', {
      }).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          _this.type = 'add';
          _this.questionList = resp.content;
          _this.startTime = Tool.dateFormat('yyyy-MM-dd hh:mm:ss',new Date());
          _this.time = 45*60*1000;
          _this.$forceUpdate();
          _this.$nextTick(function (){
            _this.$refs.countDown.start();
          });
        } else {
          Dialog({message: resp.message});
        }
      })
    },
    dati(item){
      let _this = this;
      _this.curItem = item;
      if(item.dtlb=='1'&&_this.radio){
        _this.showAnswer = true;
        if(_this.radio==item.zqda){
          _this.correctAnswer = true;
          _this.correctCount = _this.correctCount+1;
          _this.checkedColor = '#4DAB4C';
        }else{
          _this.correctAnswer = false;
          _this.errorCount = _this.errorCount+1;
          _this.checkedColor = '#fd112cf2';
        }
      }else if(item.dtlb=='2'&&_this.result&&_this.result.length>0){
        _this.showAnswer = true;
        if(_this.result.sort().join('')==item.zqda){
          _this.correctAnswer = true;
          _this.correctCount = _this.correctCount+1;
          _this.checkedColor = '#4DAB4C';
        }else{
          _this.correctAnswer = false;
          _this.errorCount = _this.errorCount+1;
          _this.checkedColor = '#fd112cf2';
        }
      }else{
        Dialog({message: "请选择答案"});
      }
    },
    next(item,index){
      let _this = this;
      let obj= {
        'kskm':item.kskm,
        'dtlb':item.dtlb,
        'tmbt':item.tmbt,
        'a':item.a,
        'b':item.b,
        'c':item.c,
        'd':item.d,
        'tp1':item.tp1,
        'tp2':item.tp2,
        'zqda':item.zqda,
        'wxid':Tool.getWxOpendid(),
        'dtsm':item.dtsm,
        'ktsx':_this.curIndex+1
      };
      if(item.dtlb=="1"){
        obj['xzda'] = _this.radio;
      }else{
        obj['xzda'] = _this.result.sort().join('');
      }
      if(_this.type=='add'){
        obj['id'] = item.id;
        _this.tmIdList.push(item.id);
      }else{
        obj['id'] = item.examid;
        _this.tmIdList.push(item.examid);
      }
      _this.answerList.push(obj);
      _this.curIndex = index+1;
      _this.radio='';
      _this.result=[];
      _this.showAnswer = false;
      _this.correctAnswer = false;
      _this.checkedColor = '#1989fa';
    },
    timeIsFinished(){
      let _this = this;
      _this.submitDJ();
    },
    submitDJ(item){
      let _this = this;
      _this.firstSave = true;
      var elem = document.activeElement;
      if (elem) elem.blur();
      let formData = {};
      if(item){
        _this.showOverlay = true;
        let obj = {
          'kskm':item.kskm,
          'dtlb':item.dtlb,
          'tmbt':item.tmbt,
          'a':item.a,
          'b':item.b,
          'c':item.c,
          'd':item.d,
          'tp1':item.tp1,
          'tp2':item.tp2,
          'zqda':item.zqda,
          'wxid':Tool.getWxOpendid(),
          'dtsm':item.dtsm,
          'ktsx':_this.curIndex+1
        };
        if(item.dtlb=="1"){
          obj['xzda'] = _this.radio;
        }else{
          obj['xzda'] = _this.result.sort().join('');
        }
        if(_this.type=='add'){
          obj['id'] = item.id;
          _this.tmIdList.push(item.id);
        }else{
          obj['id'] = item.examid;
          _this.tmIdList.push(item.examid);
        }
        _this.answerList.push(obj);
        formData ={
          'xm':_this.user.zjmc,
          "sfzmhm":_this.user.zjhm,
          "sjh":_this.user.lxdh,
          "cfbh":_this.$route.query.cfbh,
          "dtkm":'1',
          "kssj":_this.startTime,
          "sdfs":_this.correctCount,
          "wxid":Tool.getWxOpendid(),
          "answerList":_this.answerList
        };
      }else{
        formData ={
          'xm':_this.user.zjmc,
          "sfzmhm":_this.user.zjhm,
          "sjh":_this.user.lxdh,
          "cfbh":_this.$route.query.cfbh,
          "dtkm":'1',
          "kssj":_this.startTime,
          "sdfs":_this.correctCount,
          "wxid":Tool.getWxOpendid(),
          "answerList":_this.answerList,
          "questionList":_this.questionList,
          "tmIdList":_this.tmIdList
        };
      }
      if(_this.type=='edit'){
        formData['logid']=_this.logid;
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/wx/drvExam/saveSubmitRecord', formData).then((response)=>{
        let resp = response.data;
        if (resp.success) {
          if(item){
            _this.$router.push({
              path:'/wfzxjyksjg',query:{
                xm:_this.user.zjmc,
                sfzmhm:_this.user.zjhm,
                sjh:_this.user.lxdh,
                cfbh:_this.$route.query.cfbh,
                correctCount:_this.correctCount,
                errorCount:_this.errorCount
              }
            });
          }
        } else {
          Dialog({message: resp.message});
        }
      })
    },
    jxdt(){
      let _this = this;
      _this.showDialog = false;
      _this.$nextTick(function (){
        _this.$refs.countDown.start();
      });
    },
    xzjq(){
      let _this = this;
      _this.$router.push({
        path:'/wfzxjyksjg',query:{
          correctCount:_this.correctCount,
          errorCount:_this.errorCount
        }
      });
    }
  }
}
</script>
<style>
.wfzxjyksdj-ds{
  height: 20px;
  width: 20%;
  background-color: #9cb6dc59;
  color: #0885b5fa;
  text-align: center;
  border-radius: 10px;
  margin: 10px auto;
}
.wfzxjyksdj-title{
  margin: 20px;
  font-size: 14px;
}
.wfzxjyksdj-content{
  margin: 0 20px 20px 20px;
  font-size: 14px;
}
.wfzxjyksdj-item{
  margin-bottom: 10px;
}
.wfzxjyksdj-bottom-content{
  width: 90%;
  margin: 10px auto;
}
.wfzxjyksdj-dtjd{
  float: right;
  margin: 20px;
}
.wfzxjyksdj-cgcwsl{
  position: absolute;
  bottom: 20px;
  right: 40px;
}
.wfzxjyksdj-xsjg{
  width: 90%;
  margin: 0 auto;
  font-size: 14px;
  font-weight: 900;
}
.wfzxjyksdj-tp-div{
  width:100%;
  height: 100px;
}
.wfzxjyksdj-tp{
  height: 100px;
  width: 100px;
  padding-left: 20px;
}
.ksdj-dialog-content{
  margin: 20px;
  text-align: center;
}
.ksdj-dialog-content-img{
  width: 70%;
  height: 100px;
}
.ksdj-dialog-dtqk{
  display: flex;
}
.ksdj-dialog-dtqk-item{
  width: 33%;
  text-align: center;
}
.ksdj-dialog-btn{
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-around;
}
.ksdj-dialog-btn-item{
  width: 30%;
  text-align: center;
}
</style>