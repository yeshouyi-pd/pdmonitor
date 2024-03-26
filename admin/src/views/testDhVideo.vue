<template>
  <div class="test">
    <div class="server">
      第一步 设置服务器地址和端口：
      <input v-model="serverIp" placeholder="请输入服务器地址" />
      <input v-model="wsPort" type="number" placeholder="请输入端口号" />
    </div>
    <div class="Monitor">
      第二步 启动监听服务器：
      <button @click="startMonitor">启动监听服务</button>
      <button @click="stopMonitor">关闭监听服务</button>
    </div>
    <div class="device">
      第三步 添加设备：
      <input v-model="deviceId" placeholder="请输入设备名称" />
      <button @click="addDevice">添加设备</button>
    </div>
    <div class="list">
      第四步 获取设备列表：
      <table>
        <thead>
        <tr>
          <th>设备ID</th>
          <th>设备名称</th>
          <th>设备IP</th>
          <th>设备端口</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item, i) in items" :key="i">
          <td>{{ item.deviceInfo.did }}</td>
          <td>{{ item.deviceInfo.name }}</td>
          <td>{{ item.deviceInfo.dip }}</td>
          <td>{{ item.deviceInfo.dport }}</td>
          <td>
            <button @click="initFlvPlayer(item.deviceInfo.did)">
              初始化视频播放器
            </button>
          </td>
          <td><button @click="preview(item.deviceInfo.did)">预览</button></td>
          <td>
            <button @click="stopPreview(item.deviceInfo.did)">
              停止预览
            </button>
          </td>
        </tr>
        </tbody>
      </table>

      <button @click="getDeviceList">获取设备列表</button>
    </div>
    <!-- <div class="websocket-container">
      <input v-model="deviceId" placeholder="设备ID" />
      <button @click="closeWebSocket">停止</button>
      <input v-model="message" placeholder="Message" />
      <button @click="sendMessage">Send</button>
    </div> -->
    <div id="big">
      <!-- <video ref="videoPlayer" autoplay style="width: 150px;height:150px;" controls></video> -->
    </div>
  </div>

</template>

<script lang="ts">
import flvjs from "flv.js";
import axios from "axios";
export default {
  data() {
    return {
      items: [],
      serverIp: "192.168.10.133",
      wsPort: 19091,
      deviceId: "1a1",
      websock: null,
      wsUrl: "", // WebSocket URL
      message: "", // Message to send
      flvjs: flvjs,
      flvPlayer: "",
    };
  },
  methods: {
    async startMonitor() {
      console.log("启动监听服务");
      const url1 = "http://" + this.serverIp + ":" + this.wsPort + "/monitor/monitorManager/start"; // 设置POST请求的URL
      const data = {
        serverIp: this.serverIp,
        port: 9500,
      }; // 设置POST请求的数据
      try {
        const response = await axios.post(url1, data, {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
          },
        });
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    },
    async stopMonitor() {
      console.log("关闭监听服务");
      const url2 = "http://" + this.serverIp + ":" + this.wsPort + "/monitor/monitorManager/stop"; // 设置POST请求的URL
      const data = {};
      try {
        const response = await axios.post(url2, data, {
          headers: {
            "Content-Type": "application/json", // 设置请求头中的Content-Type
            "Access-Control-Allow-Origin": "*", // 设置允许的跨域请求源
          },
        });
        console.log(response.data); // 处理响应数据
      } catch (error) {
        console.error(error); // 处理错误
      }
    },
    async addDevice() {
      console.log("添加设备");
      const url2 = "http://" + this.serverIp + ":" + this.wsPort + "/monitor/deviceManager/add"; // 设置POST请求的URL
      const data = {
        did: this.deviceId,
      };
      try {
        const response = await axios.post(url2, data, {
          headers: {
            "Content-Type": "application/json", // 设置请求头中的Content-Type
            "Access-Control-Allow-Origin": "*", // 设置允许的跨域请求源
          },
        });
        console.log(response.data); // 处理响应数据
      } catch (error) {
        console.error(error); // 处理错误
      }
    },
    async getDeviceList() {
      console.log("获取设备列表");
      const url2 = "http://" + this.serverIp + ":" + this.wsPort + "/monitor/deviceManager/list"; // 设置POST请求的URL
      const data = {};
      try {
        const response = await axios.post(url2, data, {
          headers: {
            "Content-Type": "application/json", // 设置请求头中的Content-Type
            "Access-Control-Allow-Origin": "*", // 设置允许的跨域请求源
          },
        });
        this.items = response.data.data; // 处理响应数据
        console.log(this.items, "items");
        // console.log(response.data);
      } catch (error) {
        console.error(error); // 处理错误
      }
    },

    closevideoElement(value) {},

    openvideoElement(value) {
      if (flvjs.isSupported()) {
        // 判断当前浏览器是否支持flv。
        var video = document.createElement("video");
        var dd = document.createElement("div");
        dd.innerText = value;
        video.setAttribute("id", value);
        video.setAttribute("controls", true);
        video.style.width = "160px";
        video.style.height = "120px";
        video.style.margin = "10px";
        document.getElementById("big").appendChild(video);
        // document.getElementById("big").appendChild(dd);
        console.log(video);
        const fixedUrl = "ws://";
        const fullUrl =
            fixedUrl +
            this.serverIp +
            ":" +
            this.wsPort +
            "/monitor/device/monitor/" +
            value +
            "/0";
        this.flvPlayer = flvjs.createPlayer({
          type: "flv",
          isLive: true,
          hasAudio: false,
          url: fullUrl,
        });
        // alert(fullUrl);
        this.flvPlayer.attachMediaElement(video); // 挂载video标签。
        this.flvPlayer.load();
        this.flvPlayer.play(); // 播放

      }
    },
    websocketOnOpen() {
      console.log("WebSocket connection established");
    },
    websocketOnError() {
      console.log("Connection error");
      this.closeWebSocket();
    },
    websocketOnMessage(event) {
      const resdata = JSON.parse(event.data);
      if (this.flvPlayer && event.data instanceof ArrayBuffer) {
        this.flvPlayer.appendBuffer(event.data);
      }
      console.log(resdata);
    },
    websocketOnClose(event) {
      console.log("WebSocket connection closed", event);
    },
    sendMessage() {
      if (this.websock && this.websock.readyState === WebSocket.OPEN) {
        this.websock.send(this.message);
      } else {
        console.log("WebSocket connection is not open");
      }
    },
    closeWebSocket() {
      if (this.websock) {
        this.websock.close();
      }
    },
    initFlvPlayer(value) {
      this.openvideoElement(value);
    },
    async preview(value) {
      console.log("预览视频");
      const url2 = "http://" + this.serverIp + ":" + this.wsPort + "/monitor/previewManager/start"; // 设置POST请求的URL
      const data = {
        did: value,
      };
      try {
        const response = await axios.post(url2, data, {
          headers: {
            "Content-Type": "application/json", // 设置请求头中的Content-Type
            "Access-Control-Allow-Origin": "*", // 设置允许的跨域请求源
          },
        });
        console.log(response.data); // 处理响应数据
      } catch (error) {
        console.error(error); // 处理错误
      }
    },
    async stopPreview(value) {
      console.log("停止预览视频");
      const url2 = "http://" + this.serverIp + ":" + this.wsPort + "/monitor/previewManager/stop"; // 设置POST请求的URL
      const data = {
        did: value,
      };
      try {
        const response = await axios.post(url2, data, {
          headers: {
            "Content-Type": "application/json", // 设置请求头中的Content-Type
            "Access-Control-Allow-Origin": "*", // 设置允许的跨域请求源
          },
        });
        console.log(response.data); // 处理响应数据
        this.closevideo(value);
      } catch (error) {
        console.error(error); // 处理错误
      }
    },
    // 删除视频元素
    closevideo(value, flvPlayer) {
      console.log(value, flvPlayer);
      const close = document.getElementById(value);
      close.remove();
      console.log(close);

      // this.flvPlayer.pause();
      // this.flvPlayer.unload();
      // this.flvPlayer.detachMediaElement();
      // this.flvPlayer.destroy();
      // this.flvPlayer = null;
    },
  },
  beforeDestroy() {
    this.closeWebSocket();
    this.flvPlayer.destroy();
  },
};
</script>
<style scoped>
.websocket-container {
  margin-top: 20px;
}
</style>