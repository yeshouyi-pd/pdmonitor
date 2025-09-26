LOCAL_KEY_REMEMBER_USER = "LOCAL_KEY_REMEMBER_USER"; // 记住我
LOCAL_ZHBHT=false;//珠江保护区
LOCAL_SSBRL=true;//水生哺乳类   --江豚
LOCAL_VIDEO=false;//珠江视频  -- 白海豚
LOCAL_TLBHQ=false;//铜陵保护区

LocalStorage = {
    get: function (key) {
        let v = localStorage.getItem(key);
        if (v && typeof(v) !== "undefined" && v !== "undefined") {
            return JSON.parse(v);
        }
    },
    set: function (key, data) {
        localStorage.setItem(key, JSON.stringify(data));
    },
    remove: function (key) {
        localStorage.removeItem(key);
    },
    clearAll: function () {
        localStorage.clear();
    }
};
