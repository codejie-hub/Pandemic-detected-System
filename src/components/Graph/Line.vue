<template>
  <div :id="id" :style="{width:width,height:height}" />
</template>
<script>
export default {
  name: 'LineChart',
  props: {
    width: {
      type: String,
      default: '500px'
    },
    height: {
      type: String,
      default: '400px'
    },
    id: {
      type: String,
      default: 'LineChart'
    }
  },
  data() {
    return {
      myChart: null,
      caseInfo: {},
      option: {
        title: {
          text: '新增趋势'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          data: ['新增确诊', '新增疑似', '新增治愈', '新增死亡'],
          left: 'center'
        }, toolbox: {
          feature: {
            saveAsImage: {}
          }
        }, grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis:
          {
            type: 'category',
            show: true,
            // boundaryGap: false,
            data: []
          },
        yAxis:
          {
            type: 'value'
          },
        series: [ // 数据
          {
            name: '新增确诊',
            type: 'line',
            symbol: 'circle',
            symbolSize: 2,
            // stack: 'Total', stack 设置为同一值为堆叠
            // areaStyle: {},
            emphasis: {
              focus: 'series'
            }, itemStyle: {

              color: '#cf4517'

            },
            smooth: true,
            data: []
          },
          {
            name: '新增疑似',
            type: 'line',
            symbol: 'circle',
            symbolSize: 2,
            // stack: 'Total',
            // areaStyle: {},
            smooth: true,
            emphasis: {
              focus: 'series'
            },
            data: []
          },
          {
            name: '新增治愈',
            type: 'line',
            symbol: 'circle',
            symbolSize: 2,
            // stack: 'Total',
            //  areaStyle: {},
            smooth: true,
            emphasis: {
              focus: 'series'
            },
            data: []
          },
          {
            name: '新增死亡',
            type: 'line',
            symbol: 'circle',
            symbolSize: 2,
            //  stack: 'Total',
            // areaStyle: {},
            smooth: true,
            emphasis: {
              focus: 'series'
            }, itemStyle: {
              color: '#80746f' },
            data: []
          }
        ]

      }
    }
  },
  computed: {
  },
  watch: {
    caseInfo: function(newdata, olddata) {
      this.option.xAxis.data = this.caseInfo.date
      this.option.series[0].data = this.caseInfo.addconfirm
      this.option.series[1].data = this.caseInfo.addnoInfect
      this.option.series[2].data = this.caseInfo.addhealth
      this.option.series[3].data = this.caseInfo.adddeadth
      this.myChart.clear()
      this.Init()
    }
  },
  created() {
    var that = this
    that.getdaily()
  },
  mounted() {
    setTimeout(this.getstore(), 6000)
    this.Init()
  },
  methods: {
    Init() {
      var chartDom = document.getElementById(this.id)
      var myChart = this.$echarts.getInstanceByDom(chartDom)
      this.myChart = myChart
      if (this.myChart == null) this.myChart = this.$echarts.init(chartDom)
      this.option && this.myChart.setOption(this.option)
    },
    // 获取每日新增数据
    getdaily() {
      this.$store.dispatch('cases/getDaily')
      console.log('获取每日新增数据请求已经发送')
    },
    getstore() {
      var list = this.$store.getters.chinaAdd
      console.log('获取store中的chinadd=')
      console.log(list)
      var caselist = { date: [],
        addconfirm: [],
        addnoInfect: [],
        addhealth: [],
        adddeadth: [] }
      for (var i = 0; i < list.length; i++) {
        caselist.date.push(list[i].date)
        caselist.addconfirm.push(list[i].infect)
        caselist.addnoInfect.push(list[i].suspect)
        caselist.adddeadth.push(list[i].dead)
        if (list[i].heal < 0) { caselist.addhealth.push(list[i].heal * (-1)) } else { caselist.addhealth.push(list[i].heal) }
      }
      this.caseInfo = caselist
    }
  }
}
</script>
<style lang="scss">

</style>
