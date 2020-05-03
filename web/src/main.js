import Vue from "vue";
import App from "./App.vue";
import router from './router'
import './plugins/element.js'
import 'expose-loader?$!expose-loader?jQuery!jquery'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css' 
// import 'bootstrap/dist/css/bootstrap-theme.css' 
import axios from 'axios'
import Vuex from 'vuex'
import store from './store/index'

Vue.config.productionTip = false;
Vue.prototype.$http = axios
Vue.use(Vuex)


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
