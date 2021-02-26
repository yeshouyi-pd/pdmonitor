Tool = {

  /**
   * 比较事件大小
   * @param stime
   * @param etime
   */
  checkTime: function (stime,etime){
    if(this.isNotEmpty(stime) && this.isNotEmpty(etime) ){
      let date1 = stime.replace(/-/g,'/');
      let date2 = etime.replace(/-/g,'/');
      let timestamp1 = new Date(date1).getTime();
      let timestamp2 = new Date(date2).getTime();
      let r = timestamp1 <= timestamp2;
      if (r){
        return true;

      }else {
        return false;
      }


    }else{
      return true;
    }
  },


  isNotEmptystr: function (str, min, max) {
    return $.trim(str).length >0;
  },

  /**
   * 空校验 null或""都返回true
   */
  isEmpty: function (obj) {
    if ((typeof obj == 'string')) {
      return !obj || obj.replace(/\s+/g, "") == ""
    } else {
      return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
    }
  },

  /**
   * 非空校验
   */
  isNotEmpty: function (obj) {
    return !this.isEmpty(obj);
  },

  /**
   * 长度校验
   */
  isLength: function (str, min, max) {
    return $.trim(str).length >= min && $.trim(str).length <= max;
  },

  /**
   * 时间格式化，date为空时取当前时间
   */
  dateFormat: function (format, date) {
    let result;
    if (!date) {
      date = new Date();
    }
    const option = {
      "y+": date.getFullYear().toString(),        // 年
      "M+": (date.getMonth() + 1).toString(),     // 月
      "d+": date.getDate().toString(),            // 日
      "h+": date.getHours().toString(),           // 时
      "m+": date.getMinutes().toString(),         // 分
      "s+": date.getSeconds().toString()          // 秒
    };
    for (let i in option) {
      result = new RegExp("(" + i + ")").exec(format);
      if (result) {
        format = format.replace(result[1], (result[1].length == 1) ? (option[i]) : (option[i].padStart(result[1].length, "0")))
      }
    }
    return format;
  },

  /**
   * 移除对象数组中的对象
   * @param array
   * @param obj
   * @returns {number}
   */
  removeObj: function (array, obj) {
    let index = -1;
    for (let i = 0; i < array.length; i++) {
      if (array[i] === obj) {
        array.splice(i, 1);
        index = i;
        break;
      }
    }
    return index;
  },

  /**
   * 10进制转62进制
   * @param number
   * @returns {string}
   * @private
   */
  _10to62: function (number) {
    let chars = '0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ';
    let radix = chars.length;
    let arr = [];
    do {
      let mod = number % radix;
      number = (number - mod) / radix;
      arr.unshift(chars[mod]);
    } while (number);
    return arr.join('');
  },


  /**
   * 获取用户
   * @param loginUser
   */
  getUserCode: function () {
    let _this = this;
    return this.getLoginUser().usermap;
  },

  /**
   * 获取部门
   * @param loginUser
   */
  getDeptUser: function () {
    let _this = this;
    return this.getLoginUser().deptmap;
  },


  /**
   * 保存登录用户信息
   */
  setLoginUser: function (loginUser) {
    SessionStorage.set(SESSION_KEY_LOGIN_USER, loginUser);
  },

  /**
   * 获取登录用户信息
   */
  getLoginUser: function () {
    return SessionStorage.get(SESSION_KEY_LOGIN_USER) || {};
  },

  /**
   * 随机生成[len]长度的[radix]进制数
   * @param len
   * @param radix 默认62
   * @returns {string}
   */
  uuid: function (len, radix) {
    let chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    let uuid = [];
    radix = radix || chars.length;

    for (let i = 0; i < len; i++) {
      uuid[i] = chars[0 | Math.random() * radix];
    }

    return uuid.join('');
  },

  /**
   * 查找是否有权限
   * @param id 资源id
   */
  hasResource: function (id) {
    let _this = this;
    let resources = _this.getLoginUser().resources;
    if (_this.isEmpty(resources)) {
      return false;
    }
    for (let i = 0; i < resources.length; i++) {
      if (id === resources[i].id) {
        return true;
      }
    }
    return false;
  },

  //DES加密
  encryptByDES : function (message){
    let key = "11111111";
    let keyHex = CryptoJS.enc.Utf8.parse(key);
    let encrypted = CryptoJS.DES.encrypt(message, keyHex, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
  },

  //DES解密
  //DES 解密
  decryptByDES(ciphertext) {
    if(ciphertext != undefined){
      let key = "11111111";
      let keyHex = CryptoJS.enc.Utf8.parse(key);
      let decrypted = CryptoJS.DES.decrypt({
        ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
      }, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7,
      });
      return decrypted.toString(CryptoJS.enc.Utf8);
    }
  },

};