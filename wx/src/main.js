import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import axios from "axios";
import SessionStorage from  "../public/static/js/session-storage.js";
import LocalStorage from  "../public/static/js/local-storage.js";
import Tool from  "../public/static/js/tool.js";
import filter from './filter/filter'
import Vant,{Lazyload} from 'vant';
import AMap from 'vue-amap'
import 'vant/lib/index.css'
import ImagePreview from "vant/lib/image-preview";

/*import VeeValidate, {Validator} from '../node_modules/vee-validate/dist/vee-validate';
import veeMessage from '../node_modules/vee-validate/dist/locale/zh_CN';
Vue.use(VeeValidate, {
    classes: true,
    classNames: {
        valid: 'is-valid',
        invalid: 'is-invalid'
    }
});
Validator.localize('zh_CN', veeMessage);*/
Vue.use(ImagePreview);
Vue.use(Lazyload);
Vue.use(Vant);
Vue.use(AMap)
Vue.config.productionTip = false
Vue.prototype.$ajax = axios;//添加内置熟悉 Vue.prototype.xx 全局变量
//初始化vue-amap
AMap.initAMapApiLoader({
  //在高德平台申请的key
      key:'5a7e61d356499dc86719d0b4960a1ad8',
      plugin: [
          "AMap.Autocomplete", //输入提示插件
          "AMap.PlaceSearch",//POI搜索插件
          "AMap.Scale",//右下角缩略图插件 比例尺
          "AMap.OverView",//地图鹰眼插件
          "AMap.ToolBar",//地图工具条
          "AMap.MapType",//类别切换控件，实现默认图层与卫星图、实施交通图层之间切换的控制
          "AMap.PolyEditor",//编辑 折线多，边形
          "AMap.CircleEditor",//圆形编辑器插件
          "AMap.Geolocation",//定位控件，用来获取和展示用户主机所在的经纬度位置
          "AMap.Geocoder"//坐标转地址
      ]
});
// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log("请求：", config);
    //config.headers.header("Content-Security-Policy: upgrade-insecure-requests");
  return config;
}, error => {});
axios.interceptors.response.use(function (response) {
  console.log("返回结果：", response);
  return response;
}, error => {
  console.log("返回拦截：", error.response);
});
// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});

//beforeEach  路由跳转之前的首位函数
// 路由登录拦截
router.beforeEach((to, from, next) => {
    // 要不要对meta.smrzRequire属性做监控拦截;
    if (to.matched.some(function (item) {//smrzRequire 配置true 就走监控拦截
        return item.meta.smrzRequire   //和router.js smrzRequire 一致
    })) {
        /*let wxUser = Tool.getWxUser();
        let wxUser = SessionStorage.get(SESSION_KEY_WX_USER);
        if (Tool.isEmpty(wxUser)) {
            router.push('/smrz');
        } else {
            next();
        }*/
        next();
    } else {
        next();
    }
});

new Vue({
  router,//需要加载router路由配置
  render: h => h(App),
}).$mount('#app')
