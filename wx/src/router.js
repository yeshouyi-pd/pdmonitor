import  Vue from "vue"
import Router from "vue-router"
import Index from "./views/index.vue"//首页
import Bmfw from "./views/bmfw.vue"
import Ywyy from "./views/wx/ywyy/ywyy.vue" //业务类型选择
import Ywydxz from "./views/wx/ywyy/ywydxz.vue"//阅读须知
import Ywywlx from "./views/wx/ywyy/ywywlx.vue"//预约类型（预约 查询预约情况  取消 ）
import Ywsldw from "./views/wx/ywyy/ywsldw.vue"//预约单位
import Ywyusd from "./views/wx/ywyy/ywyusd.vue"//预约时间段选择
import Ywgryyxx from "./views/wx/ywyy/ywgryyxx.vue"//个人预约信息
import Ywqyyyxx from "./views/wx/ywyy/ywqyyyxx.vue"//企业预约信息
import Ywgryycg from "./views/wx/ywyy/ywgryycg.vue"//预约查询详情
import Yyinfo from "./views/wx/ywyy/yyinfo.vue"//预约查询列表
import Yyqx from "./views/wx/ywyy/yyqx.vue"//预约查询列表
import Tzxindex from "./views/wx/txz/tzxindex.vue"//通行证首页
import Txzydxz from "./views/wx/txz/txzydxz.vue"//通行证阅读须知
import Txzinfo from "./views/wx/txz/txzinfo.vue"//通行证首页
import Txzpic from "./views/wx/txz/txzpic.vue"//通行证图片上传
import Txzssuccess from "./views/wx/txz/txzssuccess.vue"//通行证图片上传
import TxzqueryInfo from "./views/wx/txz/txzqueryInfo.vue"//通行证图片上传
import Txzquery from "./views/wx/txz/txzquery.vue"//通行证信息查看
import Grzx from "./views/wx/grzx.vue"//个人中心
import Tccx from "./views/wx/tccx.vue"//
import Tcjgcx from "./views/wx/tcjgcx.vue"
import Bswd from "./views/wx/bswd.vue"//办事网点
import Xxsb from "./views/wx/xxsb.vue"//信息上报
import Qxks from "./views/wx/qxks.vue"//取消考试
import Qxksjg from "./views/wx/qxksjg.vue"//取消考试结果
import Jtdt from "./views/wx/jtdt.vue"//交通动态
import Jtdtinfo from "./views/wx/jtdtinfo.vue"//交通动态详细
import Wfdm from "./views/wx/wfdm.vue"//违法代码查询
import Wfdmjg from "./views/wx/wfdmjg.vue"//违法代码查询结果
import Jtsssb from "./views/wx/jtsssb.vue"//交通设施故障上报
import Jtsssbinfo from "./views/wx/jtsssbinfo.vue"//交通设施故障上报详细
import Cgym from "./views/wx/cgym.vue"//成功页面
import Sbym from "./views/wx/sbym.vue"//失败页面
import Zssb from "./views/wx/zssb.vue"//渍水上报
import Zssbls from "./views/wx/zssbls.vue"//渍水上报历史
import Zssbinfo from "./views/wx/zssbinfo.vue"//渍水上报详细
import Sgjdcx from "./views/wx/sgjdcx.vue"//快撤易赔事故进度查询
import Sgjdcxinfo from "./views/wx/sgjdcxinfo.vue"//快撤易赔事故进度查询详细
import Ssjb from "./views/wx/ssjb.vue"
import SsjbPage from "./views/wx/ssjbpage.vue"
import SsjbInfo from "./views/wx/ssjbinfo.vue"
import MySsjb from "./views/wx/mysspjb.vue"
import MySsjbInfo from "./views/wx/mysspjbinfo.vue"
import Smrz from "./views/wx/smrz.vue"//实名认证
import Smrzinfo from "./views/wx/smrzinfo.vue"//实名认证身份证上传
import Smrzbrinfo from "./views/wx/smrzbrinfo.vue"//实名认证本人照片上传
import Ggsmrz from "./views/wx/ggsmrz.vue"
import Cxzn from "./views/wx/cxzn.vue"//出行指南
import Cpyscx from "./views/wx/cpyscx.vue"//车牌遗失查询
import Cpyscxjg from "./views/wx/cpyscxjg.vue"//车牌遗失查询结果
import Cplssl from "./views/wx/cplssl.vue"//号牌螺丝申请
import Cplsslxs from "./views/wx/cplsslxs.vue"//号牌螺丝详细
import Cplssljg from "./views/wx/cplssljg.vue"//号牌螺丝申请结果
import Cplsslxx from "./views/wx/cplsslxx.vue"//线下号牌螺丝申请
import Cplsslxxjg from "./views/wx/cplsslxxjg.vue"//线下号牌螺丝申请结果
import Code from "./views/code.vue"//微信公众号获取openid页面
import Brjtss from "./views/wx/brjtss.vue"//我的交安故障上报
import Brjtssinfo from "./views/wx/brjtssinfo.vue"//我的交安故障上报详细
import Dzbd from "./views/wx/dzbd.vue"//电子保单
import Dzbdinfo from "./views/wx/dzbdinfo.vue"//电子保单详细
import Tpcjb from "./views/wx/tpcjb.vue"//套牌车上报
import Grcl from "./views/wx/grcl.vue"//个人车辆绑定
import Bswdlx from "./views/wx/bswdlx.vue"//办事网点部门类型
import Tpcjbxz from "./views/wx/tpcjbxz.vue"//套牌车上报须知
import Tpcjbxx from "./views/wx/tpcjbxx.vue"//套牌车上报车辆选择
import Tpcjbls from "./views/wx/tpcjbls.vue"//套牌车上报历史记录
import Tpcjbinfo from "./views/wx/tpcjbinfo.vue"//套牌车上报信息填写
import Tpcjblsinfo from "./views/wx/tpcjblsinfo.vue"//套牌车上报历史记录详细
import Kcyp from "./views/wx/kcyp.vue"//快撤易赔
import Jtsgsb from "./views/wx/jtsgsb.vue"//快撤易赔事故上报
import Jtsgsbpic from "./views/wx/jtsgsbpic.vue"//快撤易赔事故上报图片上传
import Jtsgsbjg from "./views/wx/jtsgsbjg.vue"//快撤易赔事故上报结果
import Jlcym from "./views/wx/jlcym.vue"//快撤易赔事故已挪车页面
import Jtsgxxcj from "./views/wx/jtsgxxcj.vue"//快撤易赔事故信息采集
import Jtsgdz from "./views/wx/jtsgdz.vue"//快撤易赔事故定责
import Jtsgyydz from "./views/wx/jtsgyydz.vue"//快撤易赔事故远程定责
import Jtsgzzs from "./views/wx/jtsgzzs.vue"//快撤易赔事故责任书
import Jtsgyjba from "./views/wx/jtsgyjba.vue"//快撤易赔事故一键报案
import Dlwz from "./views/wx/dlwz.vue"//地理位置测试
import Wfzxjy from "./views/wx/wfzxjy.vue"//违法在线教育
import Wfzxjylscj from "./views/wx/wfzxjylscj.vue"//违法在线教育历史成绩
import Wfzxjyspxx from "./views/wx/wfzxjyspxx.vue"//违法在线教育视频学习
import Wfzxjyzbdt from "./views/wx/wfzxjyzbdt.vue"//违法在线教育准备答题
import Wfzxjyksdj from "./views/wx/wfzxjyksdj.vue"//违法在线教育开始答卷
import Wfzxjyksjg from "./views/wx/wfzxjyksjg.vue"//违法在线教育考试结果
import Kfym from "./views/wx/kfym.vue"//未开发页面展示
import Ddzxccx from "./views/wx/ddzxccx.vue"//电动自行车查询
import Ddzxccxjg from "./views/wx/ddzxccxjg.vue"//电动自行车查询结果

/**
 * @type {{(location: RawLocation): Promise<Route>; (location: RawLocation, onComplete?: Function, onAbort?: ErrorHandler): void}}
 */
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

Vue.use(Router);

export default new Router({
    mode:"history",
    base:process.env.BASE_URL,
    routes:[{
        path: "*",
        redirect: "/index",
    },{
        path: "",
        redirect: "/index",
    },{
        path: "/index",
        component:Index
    },{
        path: "/ywyy/ywydxz",
        component:Ywydxz
    },{
        path: "/ywyy/ywywlx",
        component:Ywywlx
    },{
        path: "/ywyy/ywyy",
        component:Ywyy
    },{
        path: "/ywyy/ywsldw",
        component:Ywsldw
    },{
        path: "/ywyy/ywyusd",
        component:Ywyusd
    },{
        path: "/ywyy/ywgryyxx",
        component:Ywgryyxx
    },{
        path: "/ywyy/ywqyyyxx",
        component:Ywqyyyxx
    },{
        path: "/ywyy/ywgryycg",
        component:Ywgryycg
    },{
        path: "/ywyy/yyinfo",
        component:Yyinfo
    },{
        path: "/ywyy/yyqx",
        component:Yyqx
    },{
        path: "/txz/tzxindex",
        component:Tzxindex
    },{
        path: "/txz/txzydxz",
        component:Txzydxz
    },{
        path: "/txz/txzinfo",
        component:Txzinfo
    },{
        path: "/txz/txzpic",
        component:Txzpic
    },{
        path: "/txz/txzssuccess",
        component:Txzssuccess
    },{
        path: "/txz/txzquery",
        component:Txzquery
    },{
        path: "/txz/txzqueryInfo",
        component:TxzqueryInfo
    },{
        path: "/bmfw",
        component:Bmfw
    },{
        path: "/grzx",
        component:Grzx
    },{
        path: "/tccx",
        component:Tccx
    },{
        path: "/tcjgcx",
        component:Tcjgcx
    },{
        path: "/bswd",
        component:Bswd
    },{
        path: "/xxsb",
        component:Xxsb
    },{
        path: "/qxks",
        component:Qxks
    },{
        path: "/qxksjg",
        component:Qxksjg
    },{
        path: "/jtdt",
        component:Jtdt
    },{
        path: "/jtdtinfo",
        component:Jtdtinfo
    },{
        path: "/wfdm",
        component:Wfdm
    },{
        path: "/wfdmjg",
        component:Wfdmjg
    },{
        path: "/jtsssb",
        component:Jtsssb
    },{
        path: "/jtsssbinfo",
        component:Jtsssbinfo
    },{
        path: "/cgym",
        component:Cgym
    },{
        path: "/sbym",
        component:Sbym
    },{
        path: "/zssb",
        component:Zssb
    },{
        path: "/zssbls",
        component:Zssbls
    },{
        path: "/zssbinfo",
        component:Zssbinfo
    }, {
        path: "/sgjdcx",
        component: Sgjdcx
    }, {
        path: "/sgjdcxinfo",
        component: Sgjdcxinfo
    }, {
        path: "/ssjb",
        component: Ssjb
    }, {
        path: "/ssjbpage",
        component: SsjbPage
    }, {
        path: "/ssjbinfo",
        component: SsjbInfo
    },{
        path: "/mysspjb",
        component: MySsjb
    }, {
        path: "/mysspjbinfo",
        component: MySsjbInfo
    }, {
        path: "/smrz",
        component: Smrz
    }, {
        path: "/smrzinfo",
        component: Smrzinfo
    }, {
        path: "/smrzbrinfo",
        component: Smrzbrinfo
    }, {
        path: "/ggsmrz",
        component: Ggsmrz
    }, {
        path: "/cxzn",
        component: Cxzn
    }, {
        path: "/cpyscx",
        component: Cpyscx
    }, {
        path: "/cpyscxjg",
        component: Cpyscxjg
    }, {
        path: "/cplssl",
        component: Cplssl,
        meta: {//前端实名认证拦截 属性自定义
            smrzRequire: true
        },
    }, {
        path: "/cplsslxs",
        component: Cplsslxs
    }, {
        path: "/cplssljg",
        component: Cplssljg
    }, {
        path: "/cplsslxx",
        component: Cplsslxx
    }, {
        path: "/cplsslxxjg",
        component: Cplsslxxjg
    }, {
        path: "/code",
        component: Code
    }, {
        path: "/brjtss",
        component: Brjtss
    }, {
        path: "/brjtssinfo",
        component: Brjtssinfo
    }, {
        path: "/dzbd",
        component: Dzbd
    }, {
        path: "/dzbdinfo",
        component: Dzbdinfo
    }, {
        path: "/tpcjb",
        component: Tpcjb
    }, {
        path: "/grcl",
        component: Grcl
    }, {
        path: "/bswdlx",
        component: Bswdlx
    }, {
        path: "/tpcjbxz",
        component: Tpcjbxz
    }, {
        path: "/tpcjbxx",
        component: Tpcjbxx
    }, {
        path: "/tpcjbls",
        component: Tpcjbls
    }, {
        path: "/tpcjbinfo",
        component: Tpcjbinfo
    }, {
        path: "/tpcjblsinfo",
        component: Tpcjblsinfo
    }, {
        path: "/kcyp",
        component: Kcyp
    }, {
        path: "/jtsgsb",
        component: Jtsgsb
    }, {
        path: "/jtsgsbpic",
        component: Jtsgsbpic
    }, {
        path: "/jtsgsbjg",
        component: Jtsgsbjg
    }, {
        path: "/jlcym",
        component: Jlcym
    }, {
        path: "/jtsgxxcj",
        component: Jtsgxxcj
    }, {
        path: "/jtsgdz",
        component: Jtsgdz
    }, {
        path: "/jtsgyydz",
        component: Jtsgyydz
    }, {
        path: "/jtsgzzs",
        component: Jtsgzzs
    }, {
        path: "/jtsgyjba",
        component: Jtsgyjba
    }, {
        path: "/dlwz",
        component: Dlwz
    },{
        path: "/wfzxjy",
        component: Wfzxjy
    },{
        path: "/wfzxjylscj",
        component: Wfzxjylscj
    },{
        path: "/wfzxjyspxx",
        component: Wfzxjyspxx
    },{
        path: "/wfzxjyzbdt",
        component: Wfzxjyzbdt
    },{
        path: "/wfzxjyksdj",
        component: Wfzxjyksdj
    },{
        path: "/wfzxjyksjg",
        component: Wfzxjyksjg
    },{
        path: "/kfym",
        component: Kfym
    },{
        path: "/ddzxccx",
        component: Ddzxccx
    },{
        path: "/ddzxccxjg",
        component: Ddzxccxjg
    }]

})
