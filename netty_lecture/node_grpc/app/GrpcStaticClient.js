var service = require("../static_codegen/Person_grpc_pb");
var message = require('../static_codegen/Person_pb');
var grpc = require('grpc');

var client = new service.PersonServiceClient('127.0.0.1:8899', grpc.credentials.createInsecure());

var reqMsg = new message.PRequest();
reqMsg.setUsername('wangwu');

client.getRealNameByUsername(reqMsg, function (error, respData) {
    console.log(respData);
});

