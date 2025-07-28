const APP = new Vue({
    el: '#app',
    data: {
        config: {
            headerBGC: "#1F2C94",
            oddRowBGC: "#1F2C94",
            evenRowBGC: "",
            align: ['center', 'center', 'center', 'center'],
            header: ['监测点', '设备编号', '声学侦测次数', '操作'],

            data: [
                ['邵阳湖水文水源监测中心', 'NO0202011200005', `<span style="color:#D1E27F">6</span>`, `<div class="btn">查看详情</div>`],
                ['铜陵淡水豚国家级自然保护区', 'SN2209201001', `<span style="color:#D1E27F">1075</span>`, `<div class="btn">查看详情</div>`],
                ['南京长江江豚省级自然保护区', 'SN0902110008', `<span style="color:#D1E27F">221</span>`, `<div class="btn">查看详情</div>`],
                ['邵阳湖水文水源监测中心', 'NO0202011200005', `<span style="color:#D1E27F">6</span>`, `<div class="btn">查看详情</div>`],
            ]
        },
        config2: {
            headerBGC: "#13498F",
            oddRowBGC: "#0E2B6D",
            evenRowBGC: "",
            align: ['center', 'center', 'center', 'center'],
            header: [`<span style="color:#D1E27F">设备编号</span>`, `<span style="color:#D1E27F">开始时间</span></span>`, `<span style="color:#D1E27F">结束时间</span>`, `<span style="color:#D1E27F">头数</span>`],
            columnWidth: [150, 100, 100, 50],
            data: [
                ['NO0202011200005', '2022/09/18', '2022/09/18', '5'],
                ['NO0202011200005', '2022/09/18', '2022/09/18', '6'],
                ['NO0202011200005', '2022/09/18', '2022/09/18', '7'],
                ['NO0202011200005', '2022/09/18', '2022/09/18', '8'],
                ['NO0202011200005', '2022/09/18', '2022/09/18', '9'],
                ['NO0202011200005', '2022/09/18', '2022/09/18', '3'],
                ['NO0202011200005', '2022/09/18', '2022/09/18', '2'],
                ['NO0202011200005', '2022/09/18', '2022/09/18', '5']
            ]
        },
        right1: [{ label: '当日声学侦测次数', value: 1302 }, { label: '当日事件(群次)', value: 62 }, {
            label: '当日捕食次数', value: 160
        }],
    },
    mounted() {
        this.barChart()
        this.map()
        this.ggg()
        this.t1()
        this.t2()
        this.t3()
        this.t4()
    },
    methods: {
        getTotal(val) {
            let total = 0
            this.right1.forEach((num) => {
                total += num.value
            })
            return ((val / total) * 100)
        },
        customColorMethod(val) {
            if (val < 70) {
                return '#ADB489';
            } else if (val < 170) {
                return '#96BDE3';
            } else if (val < 2000) {
                return '#C94A6F';
            }
        },
        getFormat(item) {
            return item + '次'
        },
        map() {
            var map = new BMap.Map("mp")
            var point = new BMap.Point(114.376839, 30.657386)
            map.centerAndZoom(point, 12)

            this.$nextTick(() => {
                map.setMapStyleV2({ styleId: 'b617c51a0b7f7dce5f2ab5e325527308' })
            })
        },
        barChart() {
            let chart = echarts.init(document.getElementById('barChart'))
            let option = {
                grid: {
                    left: '15%',
                    right: '15%',
                    bottom: '10%'
                },
                xAxis: {
                    type: 'category',
                    data: ['25日', '24日', '23日', '22日', '21日', '20日', '19日'],
                    name: '2022/09',
                    nameTextStyle: {
                        color: '#Fff'
                    },
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        color: '#fff',
                    },

                },
                yAxis: {
                    name: '事件次数',
                    type: 'value',
                    nameTextStyle: {
                        color: '#Fff'
                    },
                    axisLabel: {
                        color: '#fff'
                    },
                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    splitLine: {
                        show: false
                    }
                },
                series: [
                    {
                        barWidth: 15,
                        data: [55, 30, 15, 85, 40, 120, 25],
                        type: 'bar',
                        itemStyle: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    { offset: 0, color: '#43BB8F' },
                                    { offset: 1, color: '#28B4D8' }
                                ]
                            )
                        }
                    }
                ]
            }
            chart.setOption(option)
            window.addEventListener('resize', () => {
                chart.resize()
            })
        },
        ggg() {
            let chart = echarts.init(document.getElementById('ggg'))
            let option = {

                color: ['#123C91', new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        { offset: 0, color: '#75E26B' },
                        { offset: 1, color: '#1E95D7' }
                    ]
                )],
                series: [
                    {
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: true,
                        itemStyle: {
                            borderRadius: 5,
                            borderColor: '#123C91',
                            borderWidth: 0
                        },
                        data: [
                            { value: 735, name: '离线' },
                            { value: 1048, name: '在线' },
                        ]
                    }
                ]
            };
            chart.setOption(option)
            window.addEventListener('resize', () => {
                chart.resize()
            })
        },
        t1() {
            let chart = echarts.init(document.getElementById('t1'))
            let option = {
                color: ['#123C91', new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        { offset: 0, color: '#75E26B' },
                        { offset: 1, color: '#1E95D7' }
                    ]
                )],
                series: [
                    {
                        title: {
                            text: '总量',
                            subtext: 'Fake Data',
                            left: 'right'
                        },
                        type: 'pie',
                        radius: ['30%', '50%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 5,
                            borderColor: '#123C91',
                            borderWidth: 0
                        },
                        label: { show: false, },
                        data: [
                            { value: 735, name: '离线' },
                            { value: 1048, name: '在线' },
                        ]
                    },
                ]
            };
            chart.setOption(option)
            window.addEventListener('resize', () => {
                chart.resize()
            })
        },
        t2() {
            let chart = echarts.init(document.getElementById('t2'))
            let option = {
                color: ['#123C91', new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        { offset: 0, color: '#75E26B' },
                        { offset: 1, color: '#1E95D7' }
                    ]
                )],
                series: [
                    {
                        title: {
                            text: '总量',
                            subtext: 'Fake Data',
                            left: 'right'
                        },
                        type: 'pie',
                        radius: ['30%', '50%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 5,
                            borderColor: '#123C91',
                            borderWidth: 0
                        },
                        label: { show: false, },
                        data: [
                            { value: 735, name: '离线' },
                            { value: 1048, name: '在线' },
                        ]
                    },
                ]
            };
            chart.setOption(option)
            window.addEventListener('resize', () => {
                chart.resize()
            })
        },
        t3() {
            let chart = echarts.init(document.getElementById('t3'))
            let option = {
                color: ['#123C91', new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        { offset: 0, color: '#75E26B' },
                        { offset: 1, color: '#1E95D7' }
                    ]
                )],
                series: [
                    {
                        title: {
                            text: '总量',
                            subtext: 'Fake Data',
                            left: 'right'
                        },
                        type: 'pie',
                        radius: ['30%', '50%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 5,
                            borderColor: '#123C91',
                            borderWidth: 0
                        },
                        label: { show: false, },
                        data: [
                            { value: 735, name: '离线' },
                            { value: 1048, name: '在线' },
                        ]
                    },
                ]
            };
            chart.setOption(option)
            window.addEventListener('resize', () => {
                chart.resize()
            })
        },
        t4() {
            let chart = echarts.init(document.getElementById('t4'))
            let option = {
                color: ['#123C91', new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        { offset: 0, color: '#75E26B' },
                        { offset: 1, color: '#1E95D7' }
                    ]
                )],
                series: [
                    {
                        title: {
                            text: '总量',
                            subtext: 'Fake Data',
                            left: 'right'
                        },
                        type: 'pie',
                        radius: ['30%', '50%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 5,
                            borderColor: '#123C91',
                            borderWidth: 0
                        },
                        label: { show: false, },
                        data: [
                            { value: 735, name: '离线' },
                            { value: 1048, name: '在线' },
                        ]
                    },
                ]
            };
            chart.setOption(option)
            window.addEventListener('resize', () => {
                chart.resize()
            })
        },
    }
})