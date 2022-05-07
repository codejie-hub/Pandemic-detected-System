<template>
  <div>
    <div :id="id" :style="{width:width,height:height}" />
  </div>
</template>
<script>
import chinaJson from '@/assets/china-covid-json/china.json'
import { getdata } from '@/api/cases.js'
export default {
  name: 'Chinamap',
  props: {
    width: { type: String, default: '700px' },
    height: { type: String, default: '500px' },
    id: { type: String, default: 'chinamap' }
  },
  data() {
    return {
      myChart: null, // echats实例
      //  数据测试用,现有确诊
      existdiagnosis: [
      ], // 现有确诊
      update: '', // 更新时间
      option: {
        title: {
          text: '中国疫情新增确诊数据概览',
          subtext: '数据来源于官方公布数据',
          sublink: '',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          showDelay: 0,
          transitionDuration: 0.2
        },
        visualMap: {
          // 视觉映射组件
          left: 'left',
          min: 0,
          max: 5000,
          inRange: {
            color: [
              '#e0f3f8',
              '#ff704e',
              '#f04040',
              '#b50a09'
            ]
          },
          text: ['High', 'Low'],
          calculable: true // 拖拽手柄
        },
        toolbox: {
          show: true,
          left: 'right',
          top: 'top',
          feature: {
            saveAsImage: {}
          }
        },
        series: [
          {
            name: '现有确诊人数',
            type: 'map',
            roam: false, // 关闭鼠标缩放和平移漫游
            map: 'china', //
            label: { show: true },
            zoom: 1.2,
            emphasis: {
              label: {
                show: true
              }
            },
            data: this.existdiagnosis
          }
        ]
      }
    }
  },
  //   监听数据变化
  watch: {
    existdiagnosis: function(newdata, olddata) {
      // 测试解决china地图初始时数据不显示的问题
      this.option.series[0].data = this.existdiagnosis
      // console.log(olddatatype);
      // 数据变化式，清空当前实例，会移除实例中所有的组件和图表
      this.myChart.clear()
      this.Init()
    }
  },
  mounted() {
    this.Init()
  },
  created() {
    var that = this
    that.gettencent()
  },
  methods: {
    Init() {
      // console.log('init-existdiagnosis')
      // console.log(this.existdiagnosis)
      var chartDom = document.getElementById(this.id)
      var myChart = this.$echarts.getInstanceByDom(chartDom)
      this.myChart = myChart
      if (this.myChart == null) this.myChart = this.$echarts.init(chartDom)
      //   myChart.showLoading();
      // 注册地图
      this.$echarts.registerMap('china', chinaJson)

      this.option && this.myChart.setOption(this.option)
      this.myChart.resize()
    },
    // resdata为对象数组
    handledata(resdata) {
      for (var i = 0; i < resdata.length; i++) {
        const item = {}
        item.name = resdata[i].name
        // item.value = resdata[i].today.confirm // 今日新增确诊
        item.value=resdata[i].total.nowConfirm // 现有确诊
        //  console.log('name=' + item.name)
        //  console.log('value=' + item.value)
        this.existdiagnosis.push(item)
      }
      // console.log('handle-existdiagnosis')
      // console.log(this.existdiagnosis)
      this.option.series[0].data = this.existdiagnosis
    },
    //
    gettencent() {
      // 请求腾讯接口
      getdata().then(response => {
        // console.log('tencent=')
        // console.log(response)
        this.handledata(response.data.diseaseh5Shelf.areaTree[0].children)
        // console.log('children=')
        // console.log(response.data.diseaseh5Shelf.areaTree[0].children)
        this.update = response.data.diseaseh5Shelf.lastUpdateTime
      }).catch(error => {
        console.log(error)
      })
    }

  }
}
</script>
