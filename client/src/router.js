import { createRouter, createWebHistory } from 'vue-router'
import Navigation from './components/Navigation.vue'
import Home from './views/Home.vue'
// import Default from './views/Default.vue'
import Incomes from './views/Incomes.vue'
import IncomeCreate from './views/IncomeCreate.vue'
import Outgoings from './views/Outgoings.vue'
// import OutgoingCreate from './views/OutgoingCreate.vue'

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
      },
    },
    {
      path: '/incomes/create',
      components: {
        default: IncomeCreate,
        navbar: Navigation 
      },
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