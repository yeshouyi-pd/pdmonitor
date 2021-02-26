Validator = {
  require: function (value, text) {
    if (Tool.isEmpty(value)) {
      Toast.warning(text + "不能为空");
      return false;
    } else {
      return true
    }
  },

  length: function (value, text, min, max) {
    if (Tool.isEmpty(value)) {
      return true;
    }
    if (!Tool.isLength(value, min, max)) {
      Toast.warning(text + "长度" + min + "~" + max + "位");
      return false;
    } else {
      return true
    }
  },
  checkisNaN:function(value,text){
    if (Tool.isEmpty(value)) {
      return true;
    }
    let re = /^[0-9]+.?[0-9]*/;//判断字符串是否为数字//判断正整数/[1−9]+[0−9]∗]∗/
    if (!re.test(value)) {
      Toast.warning(text + "请输入数字");
       return false;
    }else{
      return true
    }

  },
  checkIsFF:function(value,text){
    let re = /^[0-9]*$/;//判断正整数+0
    if (Tool.isEmpty(value)) {
      return true;
    }
    if (!re.test(value)) {
      Toast.warning(text + "请输入正整数");
      return false;
    }else{
      return true
    }
  },
  checkIsDigits:function(value,text){
    let re = /^[1-9][0-9]*$/;//判断正整数
    if (Tool.isEmpty(value)) {
      return true;
    }
    if (!re.test(value)) {
      Toast.warning(text + "请输入正整数");
      return false;
    }else{
      return true
    }
  },
  lengthCorrect:function(value,text,len){
    if (Tool.isEmpty(value)) {
      Toast.warning(text + "长度" + len + "位");
      return false;
    }
    if (!Tool.isLength(value, len, len)) {
      Toast.warning(text + "长度" + len + "位");
      return false;
    } else {
      return true
    }
  },
  checkIsGH:function(value,text){
    let re = /^(\+\d{2}-)?(0\d{2,3}-)?\d{7,8}$/;//判断固定电话
    let reg = /^[1][1-9][0-9]\d{8}$/;
    if (Tool.isEmpty(value)) {
      return true;
    }
    if (!re.test(value)&&!reg.test(value)) {
      Toast.warning(text + "请输入正确的电话号码");
      return false;
    }else{
      return true
    }
  }
}

;