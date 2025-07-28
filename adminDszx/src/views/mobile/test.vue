<template>
    <div>
        <div style="width: 200px;height: 163px;" id="divPlugin" class="plugin"></div>
        <table style="display: none;" cellpadding="0" cellspacing="3" border="0">
            <tr>
                <td class="tt">IP地址</td>
                <td><input id="loginip" type="text" class="txt" value="" /></td>
                <td class="tt">端口号</td>
                <td><input id="port" type="text" class="txt" value="" /></td>
            </tr>
            <tr>
                <td class="tt">用户名</td>
                <td><input id="username" type="text" class="txt" value="" /></td>
                <td class="tt">密码</td>
                <td><input id="password" type="password" class="txt" value="" /></td>
            </tr>
            <tr>
                <td class="tt">设备端口</td>
                <td colspan="2"><input id="deviceport" type="text" class="txt" />（可选参数）</td>
                <td>
                    窗口分割数&nbsp;
                    <select class="sel2" @change="changeWndNum(this.value)">
                        <option value="1" selected>1x1</option>
                        <option value="2">2x2</option>
                        <option value="3">3x3</option>
                        <option value="4">4x4</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="tt">RTSP端口</td>
                <td colspan="3"><input id="rtspport" type="text" class="txt" />（可选参数）</td>
            </tr>
            <tr>
                <td colspan="4">
                    <input type="button" class="btn" value="登录" @click="clickLogin()" />
                    <input type="button" class="btn" value="退出" @click="clickLogout()" />
                </td>
            </tr>
            <tr>
                <td class="tt">已登录设备</td>
                <td>
                    <select id="ip" class="sel" @change="getChannelInfo(),getDevicePort()"></select>
                </td>
                <td class="tt">通道列表</td>
                <td>
                    <select id="channels" class="sel"></select>
                </td>
            </tr>
            <tr>
                <td class="tt">码流类型</td>
                <td>
                    <select id="streamtype" class="sel">
                        <option value="1">主码流</option>
                        <option value="2">子码流</option>
                        <option value="3">第三码流</option>
                        <option value="4">转码码流</option>
                    </select>
                </td>
                <td>
                    <input type="button" class="btn" value="开始预览" @click="clickStartRealPlay()" />
                    <input type="button" class="btn" value="停止预览" @click="clickStopRealPlay()" />
                </td>
            </tr>
        </table>
        <remote-js id="videonode" src="/hk/codebase/webVideoCtrl.js"></remote-js>
        <remote-js src="/hk/cn/videoiframe.js"></remote-js>
    </div>
</template>
<script>
    export default {
        components:{
            'remote-js':{
                render(createElement) {
                    return createElement('script', { attrs: { type: 'text/javascript', src: this.src }});
                },
                props: {
                    src: { type: String, required: true },
                },
            },
        },
        name:'test',
        data: function() {
            return {
                g_iWndIndex:0,
                camera:{},
            }
        },
        mounted:function(){
            let _this = this;
            _this.getCamera();
        },
        methods: {
            getQueryString(name) {
                let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                let r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]);
                return null;
            },
            // 获取摄像头数据
            getCamera() {
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/monitor/admin/cameraInfo/getCamera',{
                    id:_this.getQueryString("id"),
                }).then((res)=>{
                    let response = res.data;
                    _this.camera = response.content;
                    if(Tool.isNotEmpty(_this.camera)){
                        $("#loginip").val(_this.camera.ip);
                        $("#port").val(_this.camera.port);
                        $("#username").val(_this.camera.username);
                        $("#password").val(_this.camera.camerapws);
                        setTimeout(function () {
                            _this.clickLogin();
                        }, 1000);
                    }
                })
            },
            // 登录
            clickLogin() {
                let _this = this;
                let szIP = $("#loginip").val(),
                    szPort = $("#port").val(),
                    szUsername = $("#username").val(),
                    szPassword = $("#password").val();
                if ("" == szIP || "" == szPort) {
                    return;
                }
                let szDeviceIdentify = szIP + "_" + szPort;
                let iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
                    success: function (xmlDoc) {
                        console.log(szDeviceIdentify + " 登录成功！");
                        $("#ip").prepend("<option value='" + szDeviceIdentify + "'>" + szDeviceIdentify + "</option>");
                        setTimeout(function () {
                            $("#ip").val(szDeviceIdentify);
                            _this.getChannelInfo();
                            _this.getDevicePort();
                            _this.clickStartRealPlay();
                        }, 10);
                    },
                    error: function (status, xmlDoc) {
                        console.log(szDeviceIdentify + " 登录失败！", status, xmlDoc);
                    }
                });
                if (-1 == iRet) {
                    console.log(szDeviceIdentify + " 已登录过！");
                }
            },
            // 退出
            clickLogout() {
                let _this = this;
                let szDeviceIdentify = $("#ip").val(),
                    szInfo = "";
                if (null == szDeviceIdentify) {
                    return;
                }
                _this.clickStopRealPlay();
                let iRet = WebVideoCtrl.I_Logout(szDeviceIdentify);
                if (0 == iRet) {
                    szInfo = "退出成功！";
                    $("#ip option[value='" + szDeviceIdentify + "']").remove();
                    _this.getChannelInfo();
                    _this.getDevicePort();
                } else {
                    szInfo = "退出失败！";
                }
                console.log(szDeviceIdentify + " " + szInfo);
            },
            // 开始预览
            clickStartRealPlay(iStreamType) {
                let _this = this;
                let oWndInfo = WebVideoCtrl.I_GetWindowStatus(_this.g_iWndIndex),
                    szDeviceIdentify = $("#ip").val(),
                    iRtspPort = parseInt($("#rtspport").val(), 10),
                    iChannelID = parseInt($("#channels").val(), 10),
                    bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
                    szInfo = "";
                if ("undefined" === typeof iStreamType) {
                    iStreamType = parseInt($("#streamtype").val(), 10);
                }
                if (null == szDeviceIdentify) {
                    return;
                }
                let startRealPlay = function () {
                    WebVideoCtrl.I_StartRealPlay(szDeviceIdentify, {
                        iRtspPort: iRtspPort,
                        iStreamType: iStreamType,
                        iChannelID: iChannelID,
                        bZeroChannel: bZeroChannel,
                        success: function () {
                            szInfo = "开始预览成功！";
                            console.log(szDeviceIdentify + " " + szInfo);
                        },
                        error: function (status, xmlDoc) {
                            if (403 === status) {
                                szInfo = "设备不支持Websocket取流！";
                            } else {
                                szInfo = "开始预览失败！";
                            }
                            console.log(szDeviceIdentify + " " + szInfo);
                        }
                    });
                };
                if (oWndInfo != null) {// 已经在播放了，先停止
                    WebVideoCtrl.I_Stop({
                        success: function () {
                            startRealPlay();
                        }
                    });
                } else {
                    startRealPlay();
                }
            },
            // 停止预览
            clickStopRealPlay() {
                let _this = this;
                let oWndInfo = WebVideoCtrl.I_GetWindowStatus(_this.g_iWndIndex),
                    szInfo = "";
                if (oWndInfo != null) {
                    WebVideoCtrl.I_Stop({
                        success: function () {
                            szInfo = "停止预览成功！";
                            console.log(oWndInfo.szDeviceIdentify + " " + szInfo);
                        },
                        error: function () {
                            szInfo = "停止预览失败！";
                            console.log(oWndInfo.szDeviceIdentify + " " + szInfo);
                        }
                    });
                }
            },
            // 获取通道
            getChannelInfo() {
                let szDeviceIdentify = $("#ip").val(),
                    oSel = $("#channels").empty();
                if (null == szDeviceIdentify) {
                    return;
                }
                // 模拟通道
                WebVideoCtrl.I_GetAnalogChannelInfo(szDeviceIdentify, {
                    async: false,
                    success: function (xmlDoc) {
                        let oChannels = $(xmlDoc).find("VideoInputChannel");
                        $.each(oChannels, function (i) {
                            let id = $(this).find("id").eq(0).text(),
                                name = $(this).find("name").eq(0).text();
                            if ("" == name) {
                                name = "Camera " + (i < 9 ? "0" + (i + 1) : (i + 1));
                            }
                            oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
                        });
                        console.log(szDeviceIdentify + " 获取模拟通道成功！");
                    },
                    error: function (status, xmlDoc) {
                        console.log(szDeviceIdentify + " 获取模拟通道失败！", status, xmlDoc);
                    }
                });
                // 数字通道
                WebVideoCtrl.I_GetDigitalChannelInfo(szDeviceIdentify, {
                    async: false,
                    success: function (xmlDoc) {
                        let oChannels = $(xmlDoc).find("InputProxyChannelStatus");
                        $.each(oChannels, function (i) {
                            let id = $(this).find("id").eq(0).text(),
                                name = $(this).find("name").eq(0).text(),
                                online = $(this).find("online").eq(0).text();
                            if ("false" == online) {// 过滤禁用的数字通道
                                return true;
                            }
                            if ("" == name) {
                                name = "IPCamera " + (i < 9 ? "0" + (i + 1) : (i + 1));
                            }
                            oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
                        });
                        console.log(szDeviceIdentify + " 获取数字通道成功！");
                    },
                    error: function (status, xmlDoc) {
                        console.log(szDeviceIdentify + " 获取数字通道失败！", status, xmlDoc);
                    }
                });
                // 零通道
                WebVideoCtrl.I_GetZeroChannelInfo(szDeviceIdentify, {
                    async: false,
                    success: function (xmlDoc) {
                        let oChannels = $(xmlDoc).find("ZeroVideoChannel");

                        $.each(oChannels, function (i) {
                            let id = $(this).find("id").eq(0).text(),
                                name = $(this).find("name").eq(0).text();
                            if ("" == name) {
                                name = "Zero Channel " + (i < 9 ? "0" + (i + 1) : (i + 1));
                            }
                            if ("true" == $(this).find("enabled").eq(0).text()) {// 过滤禁用的零通道
                                oSel.append("<option value='" + id + "' bZero='true'>" + name + "</option>");
                            }
                        });
                        console.log(szDeviceIdentify + " 获取零通道成功！");
                    },
                    error: function (status, xmlDoc) {
                        console.log(szDeviceIdentify + " 获取零通道失败！", status, xmlDoc);
                    }
                });
            },
            // 获取端口
            getDevicePort() {
                let szDeviceIdentify = $("#ip").val();
                if (null == szDeviceIdentify) {
                    return;
                }
                let oPort = WebVideoCtrl.I_GetDevicePort(szDeviceIdentify);
                if (oPort != null) {
                    $("#deviceport").val(oPort.iDevicePort);
                    $("#rtspport").val(oPort.iRtspPort);
                    console.log(szDeviceIdentify + " 获取端口成功！");
                } else {
                    console.log(szDeviceIdentify + " 获取端口失败！");
                }
            },
        },
        created(){
            // 在页面创建时
            let _this = this;
        },
        destroyed(){
            // 在页面销毁后
            console.log('页面要离开了。');
            let _this = this;
            _this.clickLogout();
        }
    }
</script>
<style rel="stylesheet/stylus">
    body {
        margin: 0;
        padding: 0;
        border: 0;
    }
    .plugin {
        width: 300px;
        height: 200px;
    }
</style>