<template>
  <div class="dashboard-container">
    <el-row type="flex">
      <!-- 第一列 -->
      <el-col :span="12">
        <el-row>
          <LineChart /></el-row>
        <el-row>
          <BarChart />
        </el-row>
      </el-col>
      <!-- 第二列 -->
      <el-col :span="12">
        <el-row type="flex">
          <el-col>
            <Count :number="localConfirm" text="现有确诊" :newadd="nowConfirm" textcolor="#e6360a" />
          </el-col>
          <el-col>
            <Count :number="confirm" text="累积确诊" :newadd="addconfirm" textcolor="#be4b4b" />
          </el-col>
          <el-col>
            <Count :number="dead" text="累积死亡" :newadd="adddead" textcolor="#a8a8a8" />
          </el-col>
        </el-row>
        <el-row type="flex">
          <el-col>
            <Count :number="importedCase" text="境外输入" :newadd="addimportedCase" textcolor="#357392" />
          </el-col>
          <el-col>
            <Count :number="noInfect" text="无症状" :newadd="addnoInfect" textcolor="#94319b" />
          </el-col>
          <el-col>
            <Count :number="heal" text="累积治愈" :newadd="addheal" textcolor="#28c36b" />
          </el-col>
        </el-row>
        <el-row style="margin-top:30px">
          <Chinamap />
        </el-row>
      </el-col>
      <!--第三列  -->
    </el-row>
  </div>
</template>

<script>
import LineChart from '@/components/Graph/Line.vue'
import BarChart from '@/components/Graph/Barchart.vue'
import Chinamap from '@/components/Map/china.vue'
import Count from '@/components/Count/index.vue'
export default {
  name: 'Dashboard',
  components: {
    LineChart,
    Chinamap,
    BarChart,
    Count
  },
  data() {
    return {
      update_time: '2022-05-01', // 数据更新时间
      localConfirm: 387136, // 现有确诊
      nowConfirm: 12435, // 当日新增确诊
      heal: 260853, // 累积治愈
      addheal: 3635, // 新增治愈
      dead: 15233, // 累积死亡
      adddead: 51, // 新增死亡
      confirm: 663222, // 累积确诊
      addconfirm: 16121, // 较昨日增加累积确诊
      importedCase: 18181, // 累积境外输入
      addimportedCase: 4, // 新增境外输入
      nowSevere: 447, // 正在观察人数
      noInfect: 180362, // 现有无症状感染者
      addnoInfect: 7409 // 当日新增无症状感染者
    }
  },
  computed: {
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
}
</style>
