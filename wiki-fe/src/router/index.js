import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import AdminEbook from '../views/admin/admin-ebook'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        component: About
    },
    {
        path: '/admin/ebook',
        name: 'AdminEbook',
        component: AdminEbook
    }
]

const router = new VueRouter({
    routes
})

export default router
