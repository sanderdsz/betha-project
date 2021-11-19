import { createRouter, createWebHistory } from 'vue-router'
import Navigation from './components/Navigation.vue'
import Home from './views/Home.vue'
import Hello from './components/Hello.vue'
import World from './components/World.vue'
import Chacha from './components/Chacha.vue'

export const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      components: {
        default: Home,
        navbar: Navigation
      },
      children: [
        {
          path: 'chacha',
          component: Chacha
        }
      ]
    },
    {
      path: '/hello',
      components: {
        default: Hello,
        navbar: Navigation
      }
    },
    {
      path: '/world',
      components: {
        default: World,
        navbar: Navigation
      }
    }
  ]
})