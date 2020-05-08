const http = require("http");

let responseData = '';

const option = {
    'host': 'localhost',
     'port': 3000
};

const request = http.request(option);

request.on('response', function (response) {
    response.on('data', function (chunk) {
        responseData += chunk;
    });
    response.on('end', function () {
        console.log(responseData);
    });
}).end();