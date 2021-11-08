import { createRouter, createWebHistory } from 'vue-router'
import First from './views/First.vue'
import Second from './views/Second.vue'

export const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      components: {
        default: First,
        navbar: Second
      }
    },
    {
      path: '/login',
      components: {
        default: First,
        navbar: Second
      }
    }
  ]
})