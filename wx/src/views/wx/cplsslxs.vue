<template>
    <div>
        <van-form @submit="onSubmit">
            <div class="ksxx">申领人信息</div>

            <van-field label="业务名称" :value="ywmc" readonly />
            <van-field label="申领人" :value="create_by" readonly />
            <van-field label="身份证明号码" :value="sfzmhm" readonly />

            <van-field
                    readonly
                    clickable
                    :value="hphm"
                    label="申领车辆"
                    placeholder="点击选择申领车辆"
                    @click="showPickerhphm = true"
            />
            <van-popup v-model="showPickerhphm" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnshphm"
                        @confirm="onConfirmhphm"
                        @cancel="showPickerhphm = false"
                />
            </van-popup>

            <van-field
                    readonly
                    clickable
                    :value="sqyyxs"
                    label="申领原因"
                    placeholder="点击选择申领原因"
                    @click="showPickersqyy = true"
            />
            <van-popup v-model="showPickersqyy" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnssqyy"
                        @confirm="onConfirmsqyy"
                        @cancel="showPickersqyy = false"
                />
            </van-popup>

            <van-field
                    readonly
                    clickable
                    :value="slslxs"
                    label="申领螺丝数量"
                    placeholder="点击选择申领螺丝数量"
                    @click="showPickerslsl = true"
            />
            <van-popup v-model="showPickerslsl" position="bottom">
                <van-picker
                        show-toolbar
                        :columns="columnsslsl"
                        @confirm="onConfirmslsl"
                        @cancel="showPickerslsl = false"
                />
            </van-popup>

            <div class="ksxx">邮寄地址</div>

            <van-field
                    v-model="sjr"
                    label="收件人"
                    placeholder="收件人"
            />
            <van-field
                    v-model="lxdh"
                    label="联系电话"
                    placeholder="联系电话"
            />
            <van-field
                    readonly
                    clickable
                    :value="value"
                    label="地区选择"
                    placeholder="点击选择省市区"
                    @click="showArea = true"
            />
            <van-popup v-model="showArea" position="bottom">
                <van-area
                        :area-list="areaList"
                        @confirm="onConfirm"
                        @cancel="showArea = false"
                />
            </van-popup>

            <van-field
                    v-model="jddz"
                    label="街道地址"
                    placeholder="街道地址"
            />

            <div class="button_db1">
                <van-button round block type="info" native-type="submit">
                    下一步
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import Dialog from "vant/lib/dialog";

    export default {
        name: "cplsslxs",
        data: function () {
            return {
                sjr: '',
                lxdh: '',
                jddz: '',
                ywmc: '车牌螺丝固封申领',
                create_by: '',
                sfzmhm: '',
                slslxs: '一副(4个)',
                slsl: '',
                columnsslsl: ['一副(4个)', '二副(8个)'],
                showPickerslsl: false,
                sqyyxs: '',
                sqyy: '',
                columnssqyy: [],
                showPickersqyy: false,
                hphm: '',
                columnshphm: [],
                showPickerhphm: false,
                value: '',
                code: '',
                showArea: false,
                areaList: {
                    province_list: {
                        420000: '湖北省',
                    },
                    city_list: {
                        421000: '荆州市',
                        420900: '孝感市',
                        420700: '鄂州市',
                    },
                    county_list: {
                        421002: '沙市区',
                        421087: '松滋市',
                    }
                },
            }
        },
        mounted: function () {//mounted初始化方法
            let _this = this;
            _this.loadUser();
            _this.getWxScrewYy();
            _this.getAddrInfo();
            _this.getVehile();
            _this.slsl = _this.$route.query.slsl;//路由传参
            if('1' == _this.$route.query.slsl){
                _this.slslxs = "一副(4个)";
            }else if('2' ==  _this.$route.query.slsl){
                _this.slslxs = "二副(8个)";
            }else{
                _this.slsl = 1;
            }
            _this.hphm = _this.$route.query.hphm;
            _this.sqyy = _this.$route.query.sqyy;
            _this.sjr = _this.$route.query.sjr;
            _this.lxdh = _this.$route.query.lxdh;
            _this.value = _this.$route.query.dqname;
            _this.code = _this.$route.query.dq;
            _this.jddz = _this.$route.query.jddz;
        },
        methods: {
            onConfirmhphm(value) {
                let _this = this;
                _this.hphm = value.text;
                _this.showPickerhphm = false;
            },
            onConfirmsqyy(value) {
                let _this = this;
                _this.sqyyxs = value.text;
                _this.sqyy = value.code;
                _this.showPickersqyy = false;
            },
            onConfirmslsl(value) {
                let _this = this;
                _this.slslxs = value;
                if(value === '一副(4个)'){
                    _this.slsl = 1;
                }else if(value === '二副(8个)'){
                    _this.slsl = 2;
                }
                _this.showPickerslsl = false;
            },
            onConfirm(values) {
                this.value = values
                    .filter((item) => !!item)
                    .map((item) => item.name)
                    .join('/');
                this.showArea = false;
                this.code = values
                    .filter((item) => !!item)
                    .map((item) => item.code)
                    .join('/');
            },
            onSubmit(values) {
                let _this = this;
                console.log('submit', values);
                if(Tool.isEmpty( _this.hphm)){
                  Dialog({ message: "请选择申领车辆" });
                  return;
                }
                if(Tool.isEmpty( _this.sqyy)){
                  Dialog({ message: "请选择申领原因" });
                  return;
                }
                if(Tool.isEmpty( _this.slsl)){
                  Dialog({ message: "请选择申领螺丝数量" });
                  return;
                }
                if(Tool.isEmpty( _this.sjr)){
                  Dialog({ message: "请填写收件人" });
                  return;
                }
                if(Tool.isEmpty( _this.lxdh)){
                  Dialog({ message: "请填写联系电话" });
                  return;
                }
                if(Tool.isEmpty( _this.value)){
                  Dialog({ message: "请选择省市区" });
                  return;
                }
                if(Tool.isEmpty( _this.jddz)){
                  Dialog({ message: "请填写街道地址" });
                  return;
                }
                _this.$router.push({path:'/cplssljg', query:{
                        hphm: _this.hphm,
                        sqyy: _this.sqyy,
                        slsl: _this.slsl,
                        sjr: _this.sjr,
                        lxdh: _this.lxdh,
                        dq: _this.code,
                        dqname: _this.value,
                        jddz: _this.jddz,
                }});
            },
            loadUser(){
                let _this = this;
                let wxUser = Tool.getWxUser();
                _this.create_by = wxUser.zjmc;
                _this.sfzmhm = wxUser.zjhm;
            },
            /**
             * 获取申领原因
             */
            getWxScrewYy(){
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/wxbase/CodeSetUtil/getWxScrewYy', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.columnssqyy = resp.content;
                    if((_this.sqyy == '' || _this.sqyy == undefined) && (_this.sqyyxs == '' || _this.sqyyxs == undefined)){
                        _this.sqyy = _this.columnssqyy[0].code;
                        _this.sqyyxs = _this.columnssqyy[0].text;
                    }else{
                        for(let i in _this.columnssqyy){
                            if(_this.sqyy === _this.columnssqyy[i].code){
                                _this.sqyyxs = _this.columnssqyy[i].text;
                            }
                        }
                    }
                })
            },
            /**
             * 获取申领原因
             */
            getAddrInfo(){
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/addrInfo/list', {
                }).then((response)=>{
                    let resp = response.data;
                    _this.areaList = resp.content;
                })
            },
            /**
             * 获取车辆信息
             */
            getVehile() {
                let _this = this;
                let formData = new FormData();
                formData.append("sfzmhm", Tool.getWxUser().zjhm);
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/wxbase/webchatVehicle/getVehile',
                    formData
                ).then((response) => {
                    let resp = response.data;
                    if (resp.success) {
                        _this.columnshphm = resp.content;
                        if(_this.hphm == '' || _this.hphm == undefined){
                            _this.hphm = _this.columnshphm[0].text;
                        }
                    } else {
                        Dialog({message: resp.message});
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .button_db1 {
        width: 95%;
        margin:auto;
        margin-top: 20px;
        margin-bottom: 10px;
    }
    .ksxx {
        font-size: 17px;
        width: 100%;
        background-color: #F2F2F2;
        height: 40px;
        text-align: center;
        line-height: 40px;
    }
</style>