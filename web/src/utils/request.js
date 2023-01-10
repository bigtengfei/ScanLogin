import axios from 'axios'
import { Message } from 'element-ui'
import NProgress from 'nprogress'

// 构造axios请求
const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API,
    // 超时时间
    timeout: 5000
});

// 请求拦截
service.interceptors.request.use(
    config => {
        NProgress.start();
        // 是否包含token，包含则添加
        if (store.getters.token) {
            config.headers['Authorization'] = 'Bearer ' + getToken()
        }
        return config
    },
    error => {
        NProgress.start();
        return Promise.reject(error)
    }
)

// 响应拦截
service.interceptors.response.use(
    response => {
        const res = response.data
        const code = res.code || 200;
        const msg = res.msg || "请求出错";

        if (code !== 200) {
            if (code === 401 || code === 403) {
                Message({
                    message: res.msg || '登录已过期',
                    type: 'error',
                    duration: 2 * 1000
                })
            } else {
                Message({
                    message: msg,
                    type: 'error',
                    duration: 2 * 1000
                })
            }
            NProgress.done();
            return Promise.reject(new Error(msg))
        } else {
            NProgress.done();
            return res
        }
    },
    error => {

        console.log('err' + error)
        let { message } = error;
        if (message == "Network Error") {
            message = "后端接口连接异常";
        }
        else if (message.includes("timeout")) {
            message = "系统接口请求超时";
        }
        else if (message.includes("Request failed with status code")) {
            message = "系统接口" + message.substr(message.length - 3) + "异常";
        }
        Message({
            message: message,
            type: 'error',
            duration: 3 * 1000
        })
        NProgress.done();
        return Promise.reject(error)
    }
)

export default service

