import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import axios from "axios";
import filter from './filter/filter'
import VueAMap from 'vue-amap';
import Moment from 'moment';
import DataV from '@jiaminghi/data-view'
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/en'
import uploader from 'vue-simple-uploader';

Vue.use(uploader); // 全局注册组件
Vue.use(ElementUI, { locale })
Vue.use(DataV)
Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
    key: '5a7e61d356499dc86719d0b4960a1ad8',
    plugin: ['AMap.Geolocation', 'AMap.Autocomplete', 'AMap.CircleEditor'],
    // 默认高德 sdk 版本为 1.4.4
    v: '1.4.4',
    uiVersion: '1.0.11'
});
Vue.config.productionTip = false
Vue.prototype.$ajax = axios;//添加内置熟悉 Vue.prototype.xx 全局变量
Vue.prototype.moment = Moment;
Vue.prototype.$xhHisData = {
    rq : '2023-12-20',
    index : 0
};
// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log("请求：", config);
    let token = Tool.getLoginUser().token;
    if (Tool.isNotEmpty(token)) {
        config.headers.token = token;//所有的请求头加上token
       // console.log("请求headers增加token:", token);
    }
    return config;
}, error => {});
axios.interceptors.response.use(function (response) {
    console.log("返回结果：", response);
    return response;
}, error => {
    console.log("返回拦截：", error.response);
    let response = error.response;
    const status = response.status;
    if (status === 401) {
        // 判断状态码是401 跳转到登录
        console.log("未登录，跳到登录页面");
        Tool.setLoginUser(null);
        router.push('/login');
    }
    return {
        data: {
            success: false,
            message: "请重新登录"

        }
    };
});
// 全局过滤器
Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key])
});

//beforeEach  路由跳转之前的首位函数
// 路由登录拦截
router.beforeEach((to, from, next) => {
    // 要不要对meta.loginRequire属性做监控拦截;
    if (to.matched.some(function (item) {//如果loginRequire 配置true 就走监控拦截
        return item.meta.loginRequire   //和router.js loginRequire 一致
    })) {
        let loginUser = Tool.getLoginUser();
        if (Tool.isEmpty(loginUser)) {
           router.push('/login');
           // next('/login');

        } else {
            next();
        }
    } else {
        next();
    }
});
new Vue({
    router,//需要加载router路由配置
    render: h => h(App),
}).$mount('#app')
