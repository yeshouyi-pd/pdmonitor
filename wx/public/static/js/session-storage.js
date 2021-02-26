SESSION_KEY_COURSE = "SESSION_KEY_COURSE"; // 课程管理页面点击章管理时，保存课程信息
SESSION_KEY_CHAPTER = "SESSION_KEY_CHAPTER"; // 章管理页面点击节管理时，保存章信息
SESSION_KEY_LOGIN_USER = "SESSION_KEY_LOGIN_USER"; // 登录信息
SAVY_YY_INFO="SAVY_YY_INFO";//保存预约信息
SAVY_YY_SUCCESS ="SAVY_YY_SUCCESS";//保存预约信息成功后跳转
SESSION_KEY_WX_USER = "SESSION_KEY_WX_USER"; // 微信用户信息
SESSION_KEY_WX_OPENID = "SESSION_KEY_WX_OPENID"; // 微信用户openid
LIKN_TO_TXZ="LIKN_TO_TXZ";//通行证办理
LIKN_TO_TXZSHOWINFO="LIKN_TO_TXZSHOWINFO";//通行证查看详情
SESSION_KEY_WX_HEADIMGURL = "SESSION_KEY_WX_HEADIMGURL";// 微信头像图片

SessionStorage = {
    get: function (key) {
        let v = sessionStorage.getItem(key);
        if (v && typeof(v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    set: function (key, data) {
        sessionStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        sessionStorage.removeItem(key);
    },
    clearAll: function () {
        sessionStorage.clear();
    }
};