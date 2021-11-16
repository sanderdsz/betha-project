import { createRouter, createWebHistory } from 'vue-router'
import Navigation from './components/Navigation.vue'
import Home from './views/Home.vue'

export const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      components: {
        default: Home,
        navbar: Navigation
      }
    }
  ]
})