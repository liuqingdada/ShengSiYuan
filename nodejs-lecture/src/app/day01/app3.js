const http = require('http');

const server = http.createServer(function (req, resp) {
    let data = '';

    req.on('data', function (chunk) {
        data += chunk;
    });

    console.log(data);

    req.on('end', function () {
        let method = req.method;
        let headers = JSON.stringify(req.headers);
        let httpVersion = req.httpVersion;
        let requstUrl = req.url;

        resp.writeHead(200, {'Content-Type': 'text/html'});

        let responseData = method + ", " + headers + ", " + httpVersion + ", " + requstUrl;

        resp.end(responseData);
    });
});

server.listen(3000, function () {
    console.log('Node Server started on port 3000');
});