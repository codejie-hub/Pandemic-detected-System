<template>
  <div>
    <span :style="{ color: descolor }">{{ desc }}</span>

    <vue-seamless-scroll :data="list" :class-option="optionLeft" class="wrap">
      <ul class="item" :style="{width:width}">
        <li
          v-for="item in list"
          :key="'list-' + item.area"
          :style="{ color: textcolor }"
          v-text="item.area"
        />
      </ul>
    </vue-seamless-scroll>
  </div>
</template>
<script>
export default {
  name: 'Broadcast',
  // props: ["listData", "textcolor", "desc"],
  props: {
    list: Array,
    textcolor: String,
    desc: String,
    descolor: { type: String, default: '#ab1717' },
    width: { type: String, default: '2400px' }
  },
  data() {
    return {}
  },
  computed: {
    optionLeft() {
      return {
        direction: 2,
        limitMoveNum: this.list.length // 开始无缝滚动的数据量this.dataList.length
      }
    }
  }
}
</script>
<style lang="css" scoped>
.wrap {
  height: 40px;
  width: 1000px;
  overflow: hidden;
}
ul.item {
  display: flex;
  flex-direction: row;
  list-style: none;
}
ul.item li {
  margin-right: 25px;
}
</style>
