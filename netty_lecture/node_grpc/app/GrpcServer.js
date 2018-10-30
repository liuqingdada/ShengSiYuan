const PROTO_FILE = '../../proto/Person.proto';

var grpc = require('grpc');

var grpcService = grpc.load(PROTO_FILE).com.shengsiyuan.proto;

var server = new grpc.Server();

server.addService(grpcService.PersonService.service, {
    getRealNameByUsername: getRealNameByUsername,

    getPersonsByAge: getPersonsByAge,

    getPersonsWrapperByAges: getPersonsWrapperByAges,

    biTalk: biTalk
});

server.bind('127.0.0.1:8899', grpc.ServerCredentials.createInsecure());
server.start();

function getRealNameByUsername(call, callback) {
    console.log("call: " + call.request.username);

    callback(null, {realname: "张三"});
}

function getPersonsByAge() {

}

function getPersonsWrapperByAges() {

}

function biTalk() {

}



