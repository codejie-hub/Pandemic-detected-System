<template>
  <!-- 数据统计，包括本土现有确诊，累计确诊，无症状感染者，境外输入，累积死亡 -->
  <div>
    <el-card shadow="never" class="card">
      <h1 :style="{ color: textcolor, fontSize: fontSize + 'px' }">
        {{ text }}:{{ number }}
      </h1>
      <span style="color:#626384;">较昨日+{{ newadd }}</span>
    </el-card>
  </div>
</template>
<script>
export default {
  name: 'Count',
  // 组件属性
  props: {
    number: { type: Number, default: 0 },
    text: { type: String },
    newadd: { type: Number, default: 0 },
    textcolor: { type: String, default: '#76727e' }
  },
  data() {
    return {
      styleObject: {
        background: 'transparent'
      },
      fontSize: 20
    }
  },
  methods: {}
}
</script>
<style lang="css" scoped>
.card {
  width: 200px;
  text-align: center;
  border:none;
}
h2 {
}
</style>
