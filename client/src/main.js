import Vue from 'vue'
import Vuex from 'vuex'
import App from './App.vue'
import { router } from './router'
// import 'shiver-design-system'
import './assets/styles/styles.css'
import store from './store'

Vue.use(Vuex)

new Vue({
  router,
  store,
  components: { App }
})