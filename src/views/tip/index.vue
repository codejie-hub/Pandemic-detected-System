<template>
  <div class="container">
    <el-timeline >
      <el-timeline-item timestamp="国内高风险地区" placement="top">
        <el-card> <Broadcast :list="heigharea" desc="高风险地区" textcolor="#f90b0b" /></el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="国内中风险地区" placement="top">
        <el-card><Broadcast
          :list="medialarea"
          desc="中风险地区"
          textcolor="#f9d801"
          width="4650px"
        /></el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="1" placement="top">
        <el-card>
          <h4>个人卫生</h4>
          <p>经常保持双手清洁，尤其在触摸口、鼻或眼之前。
            经常用洗手液和清水清洗双手，搓手最少20秒，并用纸巾擦干。</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2" placement="top">
        <el-card>
          <h4>预防须知</h4>
          <p> 打喷嚏或咳嗽时应用纸巾掩盖口鼻，把用过的纸巾弃置于有盖垃圾箱内，然后彻底清洁双手。
            减少前往人流密集的场所。如不可避免，应戴上外科口罩。
            避免到访医院。如有必要到访医院，应佩戴外科口罩及时刻注重个人和手部卫生。</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="3" placement="top">
        <el-card>
          <h4>避免接触</h4>
          <p>避免接触动物（包括野味）、禽鸟或其粪便；避免到海鲜、活禽市场或农场。
            切勿进食野味及切勿光顾有提供野味的餐馆。
            注意食物安全和卫生，避免进食或饮用生或未熟透的动物产品，包括奶类、蛋类和肉类。
            如有身体不适，特别是有发烧或咳嗽，应戴上外科口罩并尽快就诊。
          </p></el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>
<script>
import Broadcast from '@/components/Broadcast/index.vue'
import caseInfo from '@/assets/data/latest.js'
export default {
  name: 'Tip',
  components: {
    Broadcast
  },
  data() {
    return {
      heigharea: caseInfo.heigharea,
      medialarea: caseInfo.medialarea
    }
  }
}
</script>
<style lang="css">
.container{
  margin:30px
}
</style>
