<template>
  <div class="category-container">
    <div class="category-box" ref="chart1"></div>
  </div>
</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';
import 'echarts-wordcloud';

export default {
  name: "Category",
  data() {
    return {
      categories: []
    }
  },
  created() {
    this.loadCategoryData()
  },
  watch: {
    // 使用watch属性监听categories的变化，mounted获取不到created中的数据
    categories: function (val) {
      // console.log(val)
      this.showEcharts()
    }
  },
  methods: {
    loadCategoryData() {
      request.get("/categories").then(
          response => {
            let tmp_data = []
            let categories = response.data.data.categoryList
            categories.forEach(function (category) {
              tmp_data.push({'name': category.name, 'value': Math.floor(Math.random() * (50 - 1 + 1) + 1)})
            })
            this.categories = tmp_data
          }
      )
    },
    showEcharts() {
      let chart = echarts.init(this.$refs.chart1)

      let option = {
        series: [{
          type: 'wordCloud',
          shape: 'cardioid',

          left: 'center',
          top: 'center',
          width: '80%',
          height: '80%',
          right: null,
          bottom: null,

          sizeRange: [18, 100],

          rotationRange: [-90, 90],
          rotationStep: 25,

          gridSize: 25,
          drawOutOfBound: false,

          // Global text style
          textStyle: {
            fontFamily: 'sans-serif',
            fontWeight: 'bold',
            // Color can be a callback function or a color string
            color: function () {
              // Random color
              return 'rgb(' + [
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255)
              ].join(',') + ')';
            }
          },

          emphasis: {
            focus: 'self',

            textStyle: {
              textShadowBlur: 10,
              textShadowColor: '#333'
            }
          },

          data: this.categories
        }]
      }
      chart.setOption(option)
    }
  }
}
</script>

<style scoped>
.category-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 120px - 50px);
  text-align: center;
}

.category-box {
  height: 500px;
  width: 800px;
  background-color: white;
  border-radius: 10px;
}
</style>