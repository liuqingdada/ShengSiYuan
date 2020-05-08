const http = require("http");

let responseData = '';

http.get({
    'host': 'localhost',
    'port': 3000
}, function (response) {
    response.on('data', function (chunk) {
        responseData += chunk;
    });
    response.on('end', function () {
        console.log(responseData);
    });
}).end();