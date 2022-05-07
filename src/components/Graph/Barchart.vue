<template>
  <div :id="id" :style="{width:width,height:height}" />
</template>
<script>
import { getdata } from '@/api/cases.js'
export default {
  name: 'BarChart',
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
      default: 'BarChart'
    }
  },
  data() {
    return {
      myChart: null,
      xdata: [],
      seriesdata: [],
      option: {
        title: {
          text: '地区新增病例'
        },
        tooltip: {
          trigger: 'axis'
        },
        toolbox: {
          feature: {
            saveAdImage: {
              show: true,
              title: '地区新增病例'
            }
          }
        },

        xAxis: {
          type: 'category',
          data: [], // this.xdata,
          axisPointer: {
            type: 'shadow'
          }
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value}'
          }
        },
        series: [
          {
            data: [], // this.seriesdata,
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
              color: 'rgba(180, 180, 180, 0.2)'
            },
            markPoint: {
              // 标记最多确诊
              data: [
                { type: 'max', name: 'Max' }
                // { type: "min", name: "Min" },
              ]
            }
          }
        ]
      }
    }
  },
  watch: {
    xdata: function(newdata, olddata) {
      this.option.xAxis.data = this.xdata
      this.myChart.clear()
      this.Init()
    },
    seriesdata: function(newdata, olddata) {
      this.option.series[0].data = this.seriesdata
      this.myChart.clear()
      this.Init()
    }
  },
  mounted() {
    this.Init()
  }, created() {
    var that = this
    that.gettencent()
  },
  methods: {
    Init() {
      var chartDom = document.getElementById(this.id)
      var myChart = this.$echarts.getInstanceByDom(chartDom)
      this.myChart = myChart
      if (this.myChart == null) this.myChart = this.$echarts.init(chartDom)
      this.option && this.myChart.setOption(this.option)
    },
    //
    gettencent() {
      // 请求腾讯接口
      getdata().then(response => {
       // console.log('tencent=')
        // console.log(response)
        this.handledata(response.data.diseaseh5Shelf.areaTree[0].children)
       // console.log('children=')
        //console.log(response.data.diseaseh5Shelf.areaTree[0].children)
        this.update = response.data.diseaseh5Shelf.lastUpdateTime
      }).catch(error => {
        console.log(error)
      })
    },
    // resdata为对象数组
    handledata(resdata) {
      for (var i = 0; i < resdata.length; i++) {
        this.xdata.push(resdata[i].name)
        this.seriesdata.push(resdata[i].today.confirm)
      }
    }

  }
}
</script>
<style lang="css" scoped>

</style>
