import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 引入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 引入 mavon-editor
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// 引入自定义的全局样式
import './assets/css/global.css'

Vue.config.productionTip = false

// 使用ElementUI，size:small表示项目中拥有size属性的组件的默认尺寸均为small
Vue.use(ElementUI, {size: 'mini'});
Vue.use(mavonEditor)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
