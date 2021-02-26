<template>
    <div>
        <el-amap vid="amap" :plugin="plugin" class="amap-demo" :center="center">
        </el-amap>

        <div class="toolbar">
        <span v-if="loaded">
          location: lng = {{ lng }} lat = {{ lat }} address = {{address}}
        </span>
            <span v-else>正在定位</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "dlwz",
        data:function(){
            let self = this;
            return{
                center: [114.34302, 30.4983],
                lng: '114.34302',
                lat: '30.4983',
                address: '',
                loaded: false,
                plugin: [{
                    pName: 'Geolocation',
                    events: {
                        init(o) {
                            // o 是高德地图定位插件实例
                            o.getCurrentPosition((status, result) => {
                                if (result && result.position) {
                                    self.lng = result.position.lng;
                                    self.lat = result.position.lat;
                                    self.center = [self.lng, self.lat];
                                    self.loaded = true;
                                    self.$nextTick();
                                }
                            });
                        }
                    }
                }]
            }
        },
        mounted:function(){//mounted初始化方法
            let _this = this;
            _this.acc();
        },
        methods:{
            abc(){
                let self = this;
                // 这里通过高德 SDK 完成。
                let geocoder = new AMap.Geocoder({
                    radius: 1000,
                    extensions: "all"
                });
                geocoder.getAddress([self.lng ,self.lat], function(status, result) {
                    if (status === 'complete' && result.info === 'OK') {
                        if (result && result.regeocode) {
                            self.address = result.regeocode.formattedAddress;
                            self.$nextTick();
                        }
                    }
                });
            },
            acc(){
                let _this = this;
                let geocoder = new AMap.Geocoder({
                    radius: 1000,
                    extensions: "all"
                });
                geocoder.getAddress([_this.lng ,_this.lat], function(status, result) {
                    if (status === 'complete' && result.info === 'OK') {
                        if (result && result.regeocode) {
                            alert(result.regeocode.formattedAddress);
                            _this.address = result.regeocode.formattedAddress;
                            _this.$nextTick();
                        }
                    }
                });
                AMap.event.addListener(geocoder, 'complete', onComplete);
                AMap.event.addListener(geocoder, 'error', onError);
                function onComplete (data) {
                    alert(JSON.stringify(data));
                }
                function onError (data) {
                    alert(JSON.stringify(data));
                }
            }
        }
    }
</script>

<style scoped>
    .amap-demo {
        height: 300px;
    }
</style>