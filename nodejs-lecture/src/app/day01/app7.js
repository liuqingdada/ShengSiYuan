const url = require('url');

const urlString = 'http://www.test.com?orderId=12345';
const urlObject = url.parse(urlString);

console.log(urlObject);


const urlObj = {
    protocol: 'http:',
    host: 'www.test.com',
    port: 80,
    hostname: 'www.test.com',
    search: '?orderId=12345',
    query: 'orderId=12345',
    pathname: '/',
    path: '/?orderId=12345',
};

let realAddress = url.format(urlObj);
console.log(realAddress);

const urlAddr = url.resolve('http://www.test.com', 'order');
console.log(urlAddr);
