import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    // 设置默认网址打开的初始页
  {path: '/', redirect: '/index1/page1index1'},
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/index1',
    name: 'Index1',
    component: () => import(/* webpackChunkName: "about" */ '../views/homepage/index1'),

    children: [
      {
        path: 'page1index1',
        name: 'Page1Index1',
        component: () => import('../views/homepage/page1/index')
      },
      {
        path: '/page1addnewfile',
        name: 'page1AddnewFile',
        component: () => import('../views/homepage/page1/addnewfile')
      },
      {
        path: '/page1updatefile',
        name: 'page1UpdateFile',
        component: () => import('../views/homepage/page1/updatafile')
      },
      {
        path: 'page2index1',
        name: 'Page2Index1',
        component: () => import('../views/homepage/page2/index')
      },
      {
        path: '/page2updatefile',
        name: 'page2UpdateFile',
        component: () => import('../views/homepage/page2/updatefile')
      },
      {
        path: 'page3index1',
        name: 'Page3Index1',
        component: () => import('../views/homepage/page3/index')
      },
      {
        path: '/page3addnewfile',
        name: 'page3AddnewFile',
        component: () => import('../views/homepage/page3/addnewfile')
      },
      {
        path: '/page3updatefile',
        name: 'page3UpdateFile',
        component: () => import('../views/homepage/page3/updatefile')
      }
    ]
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
