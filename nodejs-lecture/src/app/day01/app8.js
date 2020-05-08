const queryString = require('querystring');

const str = 'name=zhangsan&address=xiamen';

const obj = queryString.parse(str);

console.log(obj);


const object = {
    name: 'lisi',
    address: 'beijing'
};

const ret = queryString.stringify(object);

console.info(ret);