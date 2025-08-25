import  Vue from "vue"
import Router from "vue-router"
import Login from  "./views/login.vue"
import LoginVideo from "./views/loginVideo"
import Admin from  "./views/admin.vue"
import Welcome from  "./views/admin/welcome.vue"
import User from  "./views/admin/user.vue"
import UserVideo from  "./views/admin/userVideo.vue"
import Resource from  "./views/admin/resource.vue"
import Role from  "./views/admin/role.vue"
import Dept from  "./views/admin/dept.vue"
import Codeset from  "./views/admin/codeset.vue"
import Attr from  "./views/admin/attr.vue"
import SysLog from "./views/admin/sysLog.vue"
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
import LargemonitorsZj  from "./views/mobile/largemonitorsZj.vue"
import LargemonitorsAzimuth from "./views/mobile/largemonitorsAzimuth.vue"
import Test  from "./views/mobile/test.vue"
import WaterProject from "./views/monitor/waterProject.vue";
import WaterState from "./views/monitor/waterState.vue"
import WaterEquiplog from "./views/monitor/waterEquiplog.vue"
import WaterProUser from "./views/monitor/waterProUser.vue";
import WaterProEquip from "./views/monitor/waterProEquip.vue";
import ChooseProject from "./views/admin/chooseProject.vue"
import WaterRawfile from "./views/monitor/waterRawfile.vue";
import Fleuploadinfo from "./views/monitor/fileuploadinfo.vue";
import CameraInfo from "./views/monitor/cameraInfo.vue";
import PredationNum from "./views/monitor/predationNum.vue";
import GroupEvent from "./views/monitor/groupEvent.vue";
import PointerSecond from "./views/monitor/pointerSecond.vue";
import PointerDay from "./views/monitor/pointerDay.vue";
import EquipmentFileTable from "./views/monitor/equipmentFileTable.vue";
import EquipmentFileTy from "./views/tydevice/equipmentFileTy.vue";
import EquipmentFileTyTable from "./views/tydevice/equipmentFileTyTable.vue";
import EquipmentTyEvent from "./views/tydevice/equipmentTyEvent.vue";
import LargemonitorsTy from "./views/mobile/largemonitorsTy.vue";
import DcbData from "./views/electricity/dcbData.vue";
import DlbData from "./views/electricity/dlbData.vue";
import TdData from "./views/electricity/tdData.vue";
import LdTaskList from "./views/electricity/ldTaskList.vue";
import EquipmentFileEvent from "./views/monitor/equipmentFileEvent.vue";
import WelcomeDw from "./views/admin/welcomeDw.vue";
import EquipmentFileTyRange from "./views/tydevice/equipmentFileTyRange.vue";
import PontoonGps from "./views/tydevice/pontoonGps.vue";
import StationsMap from "./views/monitor/stationsMap.vue";
import StationsInfo from "./views/monitor/stationsInfo.vue";
import StationsHeart from "./views/monitor/stationsHeart.vue";
import SendCommand from "./views/monitor/sendCommand.vue";
import StationsHeartbeat from "./views/monitor/stationsHeartbeat.vue";
import DeviceStateLog from "./views/monitor/deviceStateLog.vue";
import MeteorologicalData from "./views/environment/meteorologicalData.vue";
import Turbidity from "./views/environment/turbidity.vue";
import CurrentMeter from "./views/environment/currentMeter.vue";
import EnvironmentDp from "./views/mobile/environmentDp.vue";
import VideoDp from "./views/mobile/videoDp.vue";
import VideoNewDp from "./views/mobile/videoNewDp.vue";
import WaterQualityNew from "./views/environment/waterQualityNew.vue";
import WaveData from "./views/environment/waveData.vue";
import EnvironmentNewDp from "./views/mobile/environmentNewDp";
import VideoEvent from "./views/monitor/videoEvent.vue";
import ForecastNum from "./views/monitor/forecastNum.vue";
import SeaSurfaceSalinity from "./views/monitor/seaSurfaceSalinity.vue";
import VideoTl from "./views/mobile/videoTl.vue";
import VideoEventSS from "./views/monitor/videoEventSS.vue";
import testDhVideo from "./views/testDhVideo1.vue";
import VideoEventTl from "./views/monitor/videoEventTl.vue";
import VideoDpSecond from "./views/mobile/videoDpSecond.vue";
import SolarPannel from "./views/electricity/solarPannel.vue";
import AzimuthAngle from "./views/monitor/azimuthAngle.vue";
import AzimuthAngleUnique from "./views/monitor/azimuthAngleUnique.vue";
import UavFlyVideo from "./views/uav/uavFlyVideo.vue";
import UavFlyVideoNoGl from "./views/uav/uavFlyVideoNoGl.vue";
import NutritionalStatusAssessment from "./views/nutritionalStatus/nutritionalStatusAssessment.vue";
import VideoImageExtraction from "./views/nutritionalStatus/videoImageExtraction.vue";
import SpaceFile from "./views/monitor/spaceFile.vue";
import BeconFile from "./views/monitor/beconFile.vue";
import BeconFileStatistics from "./views/monitor/beconFileStatistics.vue";
import BeconFileTy from "./views/monitor/beconFileTy.vue";
import AngleFile from "./views/monitor/angleFile.vue";
import SonarBiomass from "./views/sonar/sonarBiomass.vue";
import SonarEcho from "./views/sonar/sonarEcho.vue";
import SonarEchoSv from "./views/sonar/sonarEchoSv.vue";
import SonarSingleTarget from "./views/sonar/sonarSingleTarget.vue";

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
        path: "/mobile/largemonitorsTy",
        component: LargemonitorsTy
    },{
        path: "/mobile/largemonitors",
        component: Largemonitors
    },{
        path: "/mobile/largemonitorsZj",
        component: LargemonitorsZj
    },{
        path: "/mobile/largemonitorsAzimuth",
        component: LargemonitorsAzimuth
    },{
        path: "/mobile/environmentDp",
        component: EnvironmentDp
    },{
        path: "/mobile/environmentNewDp",
        component: EnvironmentNewDp
    },{
        path: "/mobile/videoDp",
        component: VideoDp
    },{
        path: "/mobile/videoNewDp",
        component: VideoNewDp
    },{
        path: "/mobile/videoTl",
        component: VideoTl
    },{
        path: "/admin/chooseProject",
        name: "/admin/chooseProject",
        component: ChooseProject
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
    },{
        path: "/welcomeDw",
        component:WelcomeDw
    },{
        path: "/mobile/videoDpSecond",
        component: VideoDpSecond
    },{
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
            path: "system/userVideo",
            name: "system/userVideo",
            component:UserVideo
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
            path: "system/sysLog",
            name:"system/sysLog",
            component:SysLog
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
        },{
            path: "monitor/waterState",
            name:"monitor/waterState",
            component:WaterState
        },{
            path: "monitor/waterEquiplog",
            name:"monitor/waterEquiplog",
            component:WaterEquiplog
        },{
            path: "monitor/waterProUser",
            name: "monitor/waterProUser",
            component: WaterProUser
        },{
            path: "monitor/waterProEquip",
            name: "monitor/waterProEquip",
            component: WaterProEquip
        },{
            path: "monitor/waterRawfile",
            name: "monitor/waterRawfile",
            component: WaterRawfile
        },{
            path: "monitor/fileuploadinfo",
            name: "monitor/fileuploadinfo",
            component: Fleuploadinfo
        },{
            path: "monitor/cameraInfo",
            name: "monitor/cameraInfo",
            component: CameraInfo
        },{
            path: "monitor/predationNum",
            name: "monitor/predationNum",
            component: PredationNum
        },{
            path: "monitor/groupEvent",
            name: "monitor/groupEvent",
            component: GroupEvent
        },{
            path: "monitor/pointerSecond",
            name: "monitor/pointerSecond",
            component: PointerSecond
        },{
            path: "monitor/pointerDay",
            name: "monitor/pointerDay",
            component: PointerDay
        },{
            path: "monitor/equipmentFileTable",
            name: "monitor/equipmentFileTable",
            component: EquipmentFileTable
        },{
            path: "tydevice/equipmentFileTy",
            name: "tydevice/equipmentFileTy",
            component: EquipmentFileTy
        },{
            path: "tydevice/equipmentFileTyTable",
            name: "tydevice/equipmentFileTyTable",
            component: EquipmentFileTyTable
        },{
            path: "tydevice/equipmentTyEvent",
            name: "tydevice/equipmentTyEvent",
            component: EquipmentTyEvent
        },{
            path: "electricity/dcbData",
            name: "electricity/dcbData",
            component: DcbData
        },{
            path: "electricity/dlbData",
            name: "electricity/dlbData",
            component: DlbData
        },{
            path: "electricity/tdData",
            name: "electricity/tdData",
            component: TdData
        },{
            path: "electricity/ldTaskList",
            name: "electricity/ldTaskList",
            component: LdTaskList
        },{
            path: "monitor/equipmentFileEvent",
            name: "monitor/equipmentFileEvent",
            component: EquipmentFileEvent
        },{
            path: "tydevice/equipmentFileTyRange",
            name: "tydevice/equipmentFileTyRange",
            component: EquipmentFileTyRange
        },{
            path: "tydevice/pontoonGps",
            name: "tydevice/pontoonGps",
            component: PontoonGps
        },{
            path: "monitor/stationsMap",
            name: "monitor/stationsMap",
            component: StationsMap
        },{
            path: "monitor/stationsInfo",
            name: "monitor/stationsInfo",
            component: StationsInfo
        },{
            path: "monitor/stationsHeart",
            name: "monitor/stationsHeart",
            component: StationsHeart
        },{
            path: "monitor/sendCommand",
            name: "monitor/sendCommand",
            component: SendCommand
        },{
            path: "monitor/stationsHeartbeat",
            name: "monitor/stationsHeartbeat",
            component: StationsHeartbeat
        },{
            path: "monitor/deviceStateLog",
            name: "monitor/deviceStateLog",
            component: DeviceStateLog
        },{
            path: "environment/meteorologicalData",
            name: "environment/meteorologicalData",
            component: MeteorologicalData
        },{
            path: "environment/turbidity",
            name: "environment/turbidity",
            component: Turbidity
        },{
            path: "environment/currentMeter",
            name: "environment/currentMeter",
            component: CurrentMeter
        },{
            path: "environment/waterQualityNew",
            name: "environment/waterQualityNew",
            component: WaterQualityNew
        },{
            path: "environment/waveData",
            name: "environment/waveData",
            component: WaveData
        },{
            path: "monitor/videoEvent",
            name: "monitor/videoEvent",
            component: VideoEvent
        },{
            path: "monitor/forecastNum",
            name: "monitor/forecastNum",
            component: ForecastNum
        },{
            path: "monitor/seaSurfaceSalinity",
            name: "monitor/seaSurfaceSalinity",
            component: SeaSurfaceSalinity
        },{
            path: "monitor/videoEventSS",
            name: "monitor/videoEventSS",
            component: VideoEventSS
        },{
            path: "testDhVideo",
            name: "testDhVideo",
            component: testDhVideo
        },{
            path: "monitor/videoEventTl",
            name: "monitor/videoEventTl",
            component: VideoEventTl
        },{
            path: "monitor/solarPannel",
            name: "monitor/solarPannel",
            component: SolarPannel
        },{
            path: "monitor/azimuthAngle",
            name: "monitor/azimuthAngle",
            component: AzimuthAngle
        },{
            path: "monitor/azimuthAngleUnique",
            name: "monitor/azimuthAngleUnique",
            component: AzimuthAngleUnique
        },{
            path: "monitor/uavFlyVideo",
            name: "monitor/uavFlyVideo",
            component: UavFlyVideo
        },{
            path: "monitor/nutritionalStatusAssessment",
            name: "monitor/nutritionalStatusAssessment",
            component: NutritionalStatusAssessment
            },{
            path: "monitor/videoImageExtraction",
            name: "monitor/videoImageExtraction",
            component: VideoImageExtraction
        },{
            path: "monitor/uavFlyVideoNoGl",
            name: "monitor/uavFlyVideoNoGl",
            component: UavFlyVideoNoGl
        },{
            path: "monitor/spaceFile",
            name: "monitor/spaceFile",
            component: SpaceFile
        },{
            path: "monitor/beconFile",
            name: "monitor/beconFile",
            component: BeconFile
        },{
            path: "monitor/beconFileStatistics",
            name: "monitor/beconFileStatistics",
            component: BeconFileStatistics
        },{
            path: "monitor/beconFileTy",
            name: "monitor/beconFileTy",
            component: BeconFileTy
        },{
            path: "monitor/angleFile",
            name: "monitor/angleFile",
            component: AngleFile
        },{
            path: "sonar/sonarBiomass",
            name: "sonar/sonarBiomass",
            component: SonarBiomass
        },{
            path: "sonar/sonarEcho",
            name: "sonar/sonarEcho",
            component: SonarEcho
        },{
            path: "sonar/sonarEchoSv",
            name: "sonar/sonarEchoSv",
            component: SonarEchoSv
        },{
            path: "sonar/sonarSingleTarget",
            name: "sonar/sonarSingleTarget",
            component: SonarSingleTarget
        }]
    }]
})
