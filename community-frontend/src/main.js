import Vue from "vue";
import service from "@/utils/request";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Vuex from "vuex";
import "./plugins/element.js";
import "font-awesome/css/font-awesome.min.css";
import axios from "axios";
Vue.config.productionTip = false;
Vue.prototype.axios = service;
Vue.prototype.$axios = axios;
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");

Vue.use(Vuex);
