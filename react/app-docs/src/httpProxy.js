const {createProxyMiddleware} = require('http-proxy-middleware')
module.exports = function (app, baseUrl, path) {
    app.use(
        path,
        createProxyMiddleware({
            target: baseUrl,
            changeOrigin: true,
        })
    );
}
