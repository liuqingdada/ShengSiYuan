var service = require("../static_codegen/Person_grpc_pb");
var message = require('../static_codegen/Person_pb');
var grpc = require('grpc');

var server = new grpc.Server();
server.addService(service.PersonServiceService, {
    getRealNameByUsername: getRealNameByUsername,

    getPersonsByAge: getPersonsByAge,

    getPersonsWrapperByAges: getPersonsWrapperByAges,

    biTalk: biTalk
});

server.bind('127.0.0.1:8899', grpc.ServerCredentials.createInsecure());
server.start();

function getRealNameByUsername(call, callback) {
    console.log("call: " + call.request.getUsername());

    var pepole = new message.PResponse();
    pepole.setRealname("server handle: " + call.request.getUsername());
    callback(null, pepole);
}

function getPersonsByAge() {

}

function getPersonsWrapperByAges() {

}

function biTalk() {

}



