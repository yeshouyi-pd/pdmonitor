import  Vue from "vue"
import Router from "vue-router"
import Login from  "./views/login.vue"
import Admin from  "./views/admin.vue"
import Welcome from  "./views/admin/welcome.vue"
import User from  "./views/admin/user.vue"
import Resource from  "./views/admin/resource.vue"
import Role from  "./views/admin/role.vue"
import Dept from  "./views/admin/dept.vue"
import Codeset from  "./views/admin/codeset.vue"
import DeptYwlx from  "./views/admin/deptYwlx.vue"
import Attr from  "./views/admin/attr.vue"
import WxUsers from  "./views/wxbase/wxUsers.vue"
import WxNewinfo from  "./views/wxbase/wxNewinfo.vue"
import DrvQxks from  "./views/wxbase/drvQxks.vue"
import DrvQxksCheck from  "./views/wxbase/drvQxksCheck.vue"
import WxNewinfoAudit from  "./views/wxbase/wxNewinfoAudit.vue"
import ReportWater from  "./views/wxbase/reportWater.vue"
import ReportWaterCheck from  "./views/wxbase/reportWaterCheck.vue"
import VehLicelost from  "./views/wxbase/vehLicelost.vue"
import VehLicelostPre from "./views/wxbase/vehLicelostPre.vue"
import VehLicelostOut from "./views/wxbase/vehLicelostOut.vue"
import VehScrewCheck from  "./views/wxbase/vehScrewCheck.vue"
import VehScrew from "./views/wxbase/vehScrew.vue"
import VehScrewScene from "./views/wxbase/vehScrewScene.vue"
import ReportFacilityfail from "./views/wxbase/reportFacilityfail.vue"
import ReportFacilityfailCheck from "./views/wxbase/reportFacilityfailCheck.vue"
import WebchatBlack from  "./views/wxbase/webchatBlack.vue"
import WebchatBlackQuery from "./views/wxbase/webchatBlackQuery"
import VehPass  from  "./views/wxbase/vehPass.vue"
import VehPassCheck from "./views/wxbase/vehPassCheck.vue"
import VehPassline from "./views/wxbase/vehPassline.vue"
import VehRobbery from "./views/wxbase/vehRobbery.vue"
import VehRobberyCheck from "./views/wxbase/vehRobberyCheck.vue"
import VehInsuranceinfo from "./views/qypt/vehInsuranceinfo.vue"
import VehInsuranceinfoQuery from "./views/qypt/vehInsuranceinfoQuery.vue"
import VehImpawn from "./views/qypt/vehImpawn.vue"
import VehImpawnQuery from "./views/qypt/vehImpawnQuery.vue"
import VehImpawnCheck from "./views/qypt/vehImpawnCheck.vue"
import VehLogout from "./views/qypt/vehLogout.vue"
import VehLogoutCheck from "./views/qypt/vehLogoutCheck.vue"
import VehLogoutPre from "./views/qypt/vehLogoutPre.vue"
import WebchatReportIllcode from "./views/ssp/webchatReportIllcode.vue"
import WebchatIllegal from "./views/ssp/webchatIllegal.vue"
import WebchatIllegalCheck from "./views/ssp/webchatIllegalCheck.vue"
import RewardCheck from "./views/ssp/rewardCheck.vue"
import RewardSend from "./views/ssp/rewardSend.vue"
import RewardQuery from "./views/ssp/rewardQuery.vue"
import WebchatIllcode from "./views/wxbase/webchatIllcode.vue"
import VehScrewReport from "./views/wxbase/vehScrewReport"
import WebchatPostinfo from "./views/wxbase/webchatPostinfo"
import VehParkLotQuery from "./views/tcc/vehParkLotQuery.vue"
import VehParkLotWrite from "./views/tcc/vehParkLotWrite.vue"
import VehParkLotOut from "./views/tcc/vehParkLotOut.vue"
import VehParkLotBatchOut from "./views/tcc/vehParkLotBatchOut.vue"
import VehParkLotStatistics from "./views/tcc/vehParkLotStatistics.vue"
import DrvExam from "./views/jkmn/drvExam.vue"
import DrvExamlog from "./views/jkmn/drvExamlog.vue"
import DrvExamrecord from "./views/jkmn/drvExamrecord.vue"
import VehAccident from "./views/wxbase/vehAccident.vue";
import VehAccidentDetail from "./views/wxbase/vehAccidentDetail.vue";
import VehAccidentCheck from "./views/wxbase/vehAccidentCheck.vue";
import VehAccidentResponsibility from "./views/wxbase/vehAccidentResponsibility.vue";
import WxYy from "./views/wxbase/wxYy.vue"
import CjgWxYyStatistics from "./views/wxbase/cjgWxYyStatistics.vue"
import WfclWxYyStatistics from "./views/wxbase/wfclWxYyStatistics.vue"
import WxYwYyStatistics from "./views/wxbase/wxYwYyStatistics.vue";
import IndexStatistics from "./views/admin/indexStatistics.vue"
import WxPage from "./views/wxbase/wxPage.vue";

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
        redirect: "/login",
    },{
        path: "",
        redirect: "/login",
    },{
        path: "/login",
        component:Login
    },{
        path: "/",
        name:"admin",//为每一个路由都加上name属性，后续做通用的aidebar激活方法样式会用的
        component:Admin,
        meta: {//前端登录拦截 属性自定义
            loginRequire: true
        },
        children:[{
            path: "welcome",
            name:"welcome",
            component:Welcome
        },{
            path: "system/user",
            name:"system/user",
            component:User
        },{
            path: "system/resource",
            name:"system/resource",
            component:Resource
        },{
            path: "system/role",
            name:"system/role",
            component:Role
        },{
            path: "system/dept",
            name:"system/dept",
            component:Dept
        },{
            path: "system/codeset",
            name:"system/codeset",
            component:Codeset
        },{
            path: "system/attr",
            name:"system/attr",
            component:Attr
        },{
            path: "system/deptYwlx",
            name:"system/deptYwlx",
            component:DeptYwlx
        },{
            path: "wxbase/wxUsers",
            name:"wxbase/wxUsers",
            component:WxUsers
        },{
            path: "wxbase/wxNewinfo",
            name:"wxbase/wxNewinfo",
            component:WxNewinfo
        },{
            path: "wxbase/drvQxks",
            name:"wxbase/drvQxks",
            component:DrvQxks
        },{
            path: "wxbase/drvQxksCheck",
            name:"wxbase/drvQxksCheck",
            component:DrvQxksCheck
        },{
            path: "wxbase/wxNewinfoAudit",
            name:"wxbase/wxNewinfoAudit",
            component:WxNewinfoAudit
        },{
            path: "wxbase/reportWater",
            name:"wxbase/reportWater",
            component:ReportWater
        },{
            path: "wxbase/reportWaterCheck",
            name:"wxbase/reportWaterCheck",
            component:ReportWaterCheck
        },{
            path: "wxbase/vehLicelost",
            name:"wxbase/vehLicelost",
            component:VehLicelost
        },{
            path: "wxbase/vehLicelostPre",
            name:"wxbase/vehLicelostPre",
            component:VehLicelostPre
        },{
            path: "wxbase/vehLicelostOut",
            name:"wxbase/vehLicelostOut",
            component:VehLicelostOut
        },{
            path: "wxbase/vehScrew",
            name:"wxbase/vehScrew",
            component:VehScrew
        },{
            path: "wxbase/vehScrewCheck",
            name:"wxbase/vehScrewCheck",
            component:VehScrewCheck
        },{
            path: "wxbase/vehScrewScene",
            name:"wxbase/vehScrewScene",
            component:VehScrewScene
        },{
            path: "wxbase/reportFacilityfail",
            name:"wxbase/reportFacilityfail",
            component:ReportFacilityfail
        },{
            path: "wxbase/reportFacilityfailCheck",
            name:"wxbase/reportFacilityfailCheck",
            component:ReportFacilityfailCheck
        },{
            path: "wxbase/webchatBlack",
            name:"wxbase/webchatBlack",
            component:WebchatBlack
        },{
            path: "wxbase/webchatBlackQuery",
            name:"wxbase/webchatBlackQuery",
            component:WebchatBlackQuery
        },{
            path: "wxbase/vehPass",
            name:"wxbase/vehPass",
            component:VehPass
        },{
            path: "wxbase/vehPassCheck",
            name:"wxbase/vehPassCheck",
            component:VehPassCheck
        },{
            path: "wxbase/vehPassline",
            name:"wxbase/vehPassline",
            component:VehPassline
        },{
            path: "wxbase/vehRobbery",
            name:"wxbase/vehRobbery",
            component:VehRobbery
        },{
            path: "wxbase/vehRobberyCheck",
            name:"wxbase/vehRobberyCheck",
            component:VehRobberyCheck
        },{
            path: "qypt/vehInsuranceinfo",
            name:"qypt/vehInsuranceinfo",
            component:VehInsuranceinfo
        },{
            path: "qypt/vehInsuranceinfoQuery",
            name:"qypt/vehInsuranceinfoQuery",
            component:VehInsuranceinfoQuery
        },{
            path: "qypt/vehImpawn",
            name:"qypt/vehImpawn",
            component:VehImpawn
        },{
            path: "qypt/vehImpawnCheck",
            name:"qypt/vehImpawnCheck",
            component:VehImpawnCheck
        },{
            path: "qypt/vehImpawnQuery",
            name:"qypt/vehImpawnQuery",
            component:VehImpawnQuery
        },{
            path: "qypt/vehLogout",
            name:"qypt/vehLogout",
            component:VehLogout
        },{
            path: "qypt/vehLogoutCheck",
            name:"qypt/vehLogoutCheck",
            component:VehLogoutCheck
        },{
            path: "qypt/vehLogoutPre",
            name:"qypt/vehLogoutPre",
            component:VehLogoutPre
        },{
            path: "ssp/webchatReportIllcode",
            name:"ssp/webchatReportIllcode",
            component:WebchatReportIllcode
        },{
            path: "wxbase/webchatIllcode",
            name: "wxbase/webchatIllcode",
            component: WebchatIllcode
        }, {
            path: "ssp/webchatIllegal",
            name: "ssp/webchatIllegal",
            component: WebchatIllegal
        }, {
            path: "wxbase/vehScrewReport",
            name: "wxbase/vehScrewReport",
            component: VehScrewReport
        }, {
            path: "wxbase/webchatPostinfo",
            name: "wxbase/webchatPostinfo",
            component: WebchatPostinfo
        },{
            path: "ssp/webchatIllegalCheck",
            name: "ssp/webchatIllegalCheck",
            component: WebchatIllegalCheck
        },{
            path: "ssp/rewardCheck",
            name: "ssp/rewardCheck",
            component: RewardCheck
        },{
            path: "ssp/rewardSend",
            name: "ssp/rewardSend",
            component: RewardSend
        },{
            path: "ssp/rewardQuery",
            name: "ssp/rewardQuery",
            component: RewardQuery
        },{
            path: "tcc/vehParkLotQuery",
            name:"tcc/vehParkLotQuery",
            component:VehParkLotQuery
        },{
            path: "tcc/vehParkLotWrite",
            name:"tcc/vehParkLotWrite",
            component:VehParkLotWrite
        },{
            path: "tcc/vehParkLotOut",
            name:"tcc/vehParkLotOut",
            component:VehParkLotOut
        },{
            path: "tcc/vehParkLotBatchOut",
            name:"tcc/vehParkLotBatchOut",
            component:VehParkLotBatchOut
        },{
            path: "tcc/vehParkLotStatistics",
            name:"tcc/vehParkLotStatistics",
            component:VehParkLotStatistics
        },{
            path: "jkmn/drvExam",
            name:"jkmn/drvExam",
            component: DrvExam
        },{
            path: "jkmn/drvExamlog",
            name:"jkmn/drvExamlog",
            component: DrvExamlog
        },{
            path: "jkmn/drvExamrecord",
            name:"jkmn/drvExamrecord",
            component: DrvExamrecord
        },{
            path: "wxbase/vehAccident",
            name:"wxbase/vehAccident",
            component: VehAccident
        },{
            path: "wxbase/vehAccidentDetail",
            name:"wxbase/vehAccidentDetail",
            component: VehAccidentDetail
        },{
            path: "wxbase/vehAccidentCheck",
            name: "wxbase/vehAccidentCheck",
            component: VehAccidentCheck
        },{
            path: "wxbase/vehAccidentResponsibility",
            name: "wxbase/vehAccidentResponsibility",
            component: VehAccidentResponsibility
        },{
            path: "wxbase/wxYy",
            name: "wxbase/wxYy",
            component: WxYy
        },{
            path: "wxbase/cjgWxYyStatistics",
            name: "wxbase/cjgWxYyStatistics",
            component: CjgWxYyStatistics
        },{
            path: "wxbase/wfclWxYyStatistics",
            name: "wxbase/wfclWxYyStatistics",
            component: WfclWxYyStatistics
        },{
            path: "wxbase/wxYwYyStatistics",
            name: "wxbase/wxYwYyStatistics",
            component: WxYwYyStatistics
        },{
            path: "indexStatistics",
            name:"indexStatistics",
            component:IndexStatistics
        },{
            path: "wxbase/wxPage",
            name: "wxbase/wxPage",
            component: WxPage
        }]
    }]
})
