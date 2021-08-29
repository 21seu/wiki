import Vue from 'vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import axios from 'axios'

axios.defaults.baseURL = process.env.VUE_APP_SERVER

Vue.config.productionTip = false
Vue.use(Antd);

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')

console.log('环境', process.env.NODE_ENV);
console.log('服务端', process.env.VUE_APP_SERVER);