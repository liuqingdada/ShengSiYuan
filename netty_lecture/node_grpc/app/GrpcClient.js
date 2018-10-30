const PROTO_FILE = '../../proto/Person.proto';

var grpc = require('grpc');

var grpcService = grpc.load(PROTO_FILE).com.shengsiyuan.proto;

var client = new grpcService.PersonService('127.0.0.1:8899', grpc.credentials.createInsecure());

client.getRealNameByUsername({username: 'node-lisi'}, function (error, respData) {
    console.log(respData);
});




