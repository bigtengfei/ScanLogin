module.exports = {
  // 生产环境是否要生成 sourceMap
  productionSourceMap: false,
  // 应用访问路径，二级目录
  publicPath: process.env.VUE_APP_CONTEXT_PATH,
  // 打包生成目录
  outputDir: 'dist',
  // 打包后静态资源存放目录
  assetsDir: 'static',
  // 是否开启eslint校验
  lintOnSave: false,
  // webpack-dev-server 相关配置(代理设置)
  // devServer: {
  //   port: process.env.VUE_APP_ACCESS_PORT,
  //   // 局域网内ip:prot访问
  //   host: '0.0.0.0',
  //   open: false,
  //   proxy: {
  //     [process.env.VUE_APP_BASE_API]: {
  //       target: 'http://47.100.255.143:9999',
  //       changeOrigin: true,
  //       pathRewrite: {
  //         ['^' + process.env.VUE_APP_BASE_API]: ''
  //       }
  //     }
  //   },
  //   // 已弃用，更改为以下方式
  //   // disableHostCheck: true
  //   historyApiFallback: true,
  //   allowedHosts: "all",
  // }

}