import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login'
import Main from '../components/main'

import UserSystem from '../views/usersystem'



Vue.use(VueRouter)

//避免重复点击的警告，报错
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}



const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
    children: [
     
      
      
      {
        path: '/usersystem',
        name: 'usersystem',
        component: () => import('@/views/usersystem.vue'),
        meta: { title: '系统用户管理' }
      },
      
      {
        path: '/carU',
        name: 'carU',
        component: () => import('@/views/User/carU.vue'),
        meta: { title: '车辆信息' }
      },
      {
        path: '/orderU',
        name: 'orderU',
        component: () => import('@/views/User/orderU.vue'),
        meta: { title: '订单信息' }
      },
      {
        path: '/UserInfoU',
        name: 'UserInfoU',
        component: () => import('@/views/User/UserInfoU.vue'),
        meta: { title: '个人信息' }
      },
      {
        path: '/benz',
        name: 'benz',
        component: () => import('@/views/car/system/Benz/Benz.vue'),
      },
      {
        path: '/bmw',
        name: 'bmw',
        component: () => import('@/views/car/system/BMW/BMW.vue'),
        meta: { title: '宝马系列' }
      },
      {
        path: '/nissan',
        name: 'nissan',
        component: () => import('@/views/car/system/Nissan/Nissan.vue'),
        meta: { title: '日产系列' }
      },
      {
        path: '/faw-vk',
        name: 'faw-vk',
        component: () => import('@/views/car/system/FAW-VK/FAW-VK.vue'),
        meta: { title: '大众系列' }
      },
      {
        path: '/deletecar',
        name: 'deletecar',
        component: () => import('@/views/car/system/caozuoyixia/DeleteData.vue'),
        meta: { title: '删除汽车信息' }
      },
      {
        path: '/addcar',
        name: 'addcar',
        component: () => import('@/views/car/system/caozuoyixia/AddData.vue'),
        meta: { title: '添加汽车信息' }
      },
      {
        path: '/ongoing',
        name: 'ongoing',
        component: () => import('@/views/order/ongoing.vue'),
        meta: { title: '进行订单' }
      },
      {
        path: '/finished',
        name: 'finished',
        component: () => import('@/views/order/finished.vue'),
        meta: { title: '历史订单' }
      },
      {
        path: '/addannounce',
        name: 'addannounce',
        component: () => import('@/views/announcement/Addannounce.vue'),
        meta: { title: '添加公告' }
      },
      {
        path: '/deleteannounce',
        name: 'deleteannounce',
        component: () => import('@/views/announcement/Deleteannounce.vue'),
        meta: { title: '删除公告' }
      },
      {
        path: '/showannounce',
        name: 'showannounce',
        component: () => import('@/views/announcement/Showannounce.vue'),
        meta: { title: '公告' }
      },
      
      
      {
        path: '/staff',
        name: 'staff',
        component: () => import('@/views/staff.vue'),
        meta: { title: '员工信息管理' }
      },
      
      
      
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import('../views/login.vue')
  },
  {
    path: '/findpass',
    name: 'findpass',
    component: ()=>import('../views/findPassword.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import( '../views/register.vue')
  },
  {
    path: '/forget',
    name: 'forget',
    component: () => import( '../views/forget.vue')
  },
  {
    path: '/modify',
    name: 'modify',
    component: () => import( '../views/modify.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
