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
import Attr from  "./views/admin/attr.vue"
import WaterData from "./views/monitor/waterData.vue"
import WaterEquipment from "./views/monitor/waterEquipment.vue"
import WaterQuality from "./views/monitor/waterQuality.vue"
import WaterQualityResult from "./views/monitor/waterQualityResult.vue"
import EquipmentMap from "./views/monitor/equipmentMap.vue"
import MonitorEquipmentNow from "./views/monitor/monitorEquipmentNow.vue"
import MonitorEquipmentHis from "./views/monitor/monitorEquipmentHis.vue"
import WaterNoiseImage from "./views/monitor/waterNoiseImage.vue"
import EquipmentFile from "./views/monitor/equipmentFile.vue"
import AlarmNumbers from "./views/monitor/alarmNumbers.vue"
import AlarmEvent from "./views/monitor/alarmEvent.vue";
import EquipmentFileMinute from "./views/monitor/equipmentFileMinute.vue";
import EquipmentFileHour from "./views/monitor/equipmentFileHour.vue";
import AlarmNumbersByTime from "./views/monitor/alarmNumbersByTime.vue";
import AuthorizeInfo from "./views/monitor/authorizeInfo.vue";
import InterfaceLog from "./views/monitor/interfaceLog.vue";
import Mindex  from "./views/mobile/mindex.vue"
import Shjlist  from "./views/mobile/shjlist.vue"
import Shjlistinfo  from "./views/mobile/shjlistinfo.vue"
import Jtlist  from "./views/mobile/jtlist.vue"
import Jtlistinfo  from "./views/mobile/jtlistinfo.vue"
import Largemonitors  from "./views/mobile/largemonitors.vue"
import Test  from "./views/mobile/test.vue"
import WaterProject from "./views/monitor/waterProject.vue";

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
        path: "/mobile/largemonitors",
        component: Largemonitors
    },{
        path: "/mobile/mindex",
        component:Mindex
    },{
        path: "/mobile/shjlist",
        component:Shjlist
    },{
        path: "/mobile/shjlistinfo",
        component:Shjlistinfo
    },{
        path: "/mobile/jtlist",
        component:Jtlist
    },{
        path: "/mobile/jtlistinfo",
        component:Jtlistinfo
    },{
            path: "/mobile/test",
            component: Test
        }
    ,{
        path: "/",
        name:"admin",//为每一个路由都加上name属性，后续做通用的aidebar激活方法样式会用的
        component:Admin,
        meta: {//前端登录拦截 属性自定义
            loginRequire: true
        },
        children:[
        {
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
            path: "monitor/waterData",
            name:"monitor/waterData",
            component:WaterData
        },{
            path: "monitor/waterEquipment",
            name:"monitor/waterEquipment",
            component:WaterEquipment
        },{
            path: "monitor/waterQuality",
            name:"monitor/waterQuality",
            component:WaterQuality
        },{
            path: "monitor/waterQualityResult",
            name:"monitor/waterQualityResult",
            component:WaterQualityResult
        },{
            path: "monitor/equipmentMap",
            name: "monitor/equipmentMap",
            component: EquipmentMap
        },{
            path: "monitor/monitorEquipmentNow",
            name: "monitor/monitorEquipmentNow",
            component: MonitorEquipmentNow
        },{
            path: "monitor/monitorEquipmentHis",
            name: "monitor/monitorEquipmentHis",
            component: MonitorEquipmentHis
        },{
            path: "monitor/waterNoiseImage",
            name: "monitor/waterNoiseImage",
            component: WaterNoiseImage
        },{
            path: "monitor/equipmentFile",
            name: "monitor/equipmentFile",
            component: EquipmentFile
        },{
            path: "monitor/alarmNumbers",
            name: "monitor/alarmNumbers",
            component: AlarmNumbers
        },{
            path: "monitor/alarmNumbers",
            name: "monitor/alarmNumbers",
            component: AlarmNumbers
        },{
            path: "monitor/equipmentFileMinute",
            name: "monitor/equipmentFileMinute",
            component: EquipmentFileMinute
        },{
            path: "monitor/equipmentFileHour",
            name: "monitor/equipmentFileHour",
            component: EquipmentFileHour
        },{
            path: "monitor/alarmNumbersByTime",
            name: "monitor/alarmNumbersByTime",
            component: AlarmNumbersByTime
        },{
            path: "monitor/alarmEvent",
            name: "monitor/alarmEvent",
            component: AlarmEvent
        },{
            path: "monitor/authorizeInfo",
            name: "monitor/authorizeInfo",
            component: AuthorizeInfo
        },{
            path: "monitor/interfaceLog",
            name: "monitor/interfaceLog",
            component: InterfaceLog
        },{
            path: "monitor/waterProject",
            name: "monitor/waterProject",
            component: WaterProject
        }]

    }]
})
