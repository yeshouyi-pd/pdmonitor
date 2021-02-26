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
            path: "monitor/waterData",
            name:"monitor/waterData",
            component:WaterData
        }]
    }]
})
