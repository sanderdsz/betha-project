import { createRouter, createWebHistory } from 'vue-router'
import Navigation from './components/Navigation.vue'
import Home from './views/Home.vue'
// import Default from './views/Default.vue'
import Incomes from './views/Incomes.vue'
import Outgoings from './views/Outgoings.vue'

export const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      components: {
        default: Home,
        navbar: Navigation
      },
      /*
      children: [
        {
          path: '/home',
          component: Default
        }
      ]
      */
    },
    {
      path: '/incomes',
      components: {
        default: Incomes,
        navbar: Navigation
      }
    },
    {
      path: '/outgoings',
      components: {
        default: Outgoings,
        navbar: Navigation
      }
    }
  ]
})