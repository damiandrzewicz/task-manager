import Vue from 'vue'
import VueRouter from 'vue-router'

import Projects from "@/views/Projects.vue"
import Dashboard from "@/views/Dashboard.vue"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Dashboard',
    component: Dashboard
  },
  {
    path: '/projects/:parentId?/subprojects',
    name: 'Projects',
    component: Projects,
    // props: true
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
