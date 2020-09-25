const http = require("http");

const server = http.createServer();

const qs = require("querystring");

server.on("request", (req, resp) => {
    const params = qs.parse(req.url.substring(2));

    resp.writeHead(200, {
        'Set-Cookie': 'l=a123456;Path=/;Domain=www.demo2.com;HttpOnly'   // HttpOnly:脚本无法读取
    })

    resp.write(JSON.stringify(params))
    resp.end()
})

server.listen(3002)
console.log("Http proxy server is running at port 3002")
