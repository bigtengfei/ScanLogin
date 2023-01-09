import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter);

// 解决编程式路由往同一地址跳转时会报错的情况
const originalPush = VueRouter.prototype.push;
const originalReplace = VueRouter.prototype.replace;

// push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject);
    return originalPush.call(this, location).catch(err => err);
};

//replace
VueRouter.prototype.replace = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalReplace.call(this, location, onResolve, onReject);
    return originalReplace.call(this, location).catch(err => err);
};


const routes = [
    {
        path: '/',
        component: () => import('@/views/Layout'),
        redirect: '/index',
        children: [
            {
                path: '/index',
                name: 'index',
                component: () => import('@/views/Home'),
                meta: { title: '首页' }
            }
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/Login'),
        meta: { title: '登录' }
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
});


export default router;