SESSION_KEY_COURSE = "SESSION_KEY_COURSE"; // 课程管理页面点击章管理时，保存课程信息
SESSION_KEY_CHAPTER = "SESSION_KEY_CHAPTER"; // 章管理页面点击节管理时，保存章信息
SESSION_KEY_LOGIN_USER = "SESSION_KEY_LOGIN_USER"; // 登录信息
SESSION_KEY_DEPT="SESSION_KEY_DEPT";//部门用户
CODE_KEY_JDC_YELX="2";//机动车业务类型
CODE_KEY_JSR_YELX="3";//驾驶人业务类型
CODE_KEY_WF_YELX ="5";//驾驶人业务类型
CODE_KEY_FSZMLM ="4";//身份证名类型
CODE_KEY_FBXXLX ="6";//发布信息类型
SESSION_KEY_ACCIDENT="SESSION_KEY_ACCIDENT";//事故管理

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