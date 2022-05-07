// 封装echarts
// 初始化Chart
/**
 *
 * @param {echarts} echarts实例对象
 * @param {option}  配置项
 * @param {id}  容器ID
 */
export function InitChart(echarts, option, id) {
  var chartDom = document.getElementById(id)
  var myChart = echarts.init(chartDom)
  option && myChart.setOption(option)
}
