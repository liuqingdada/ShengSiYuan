// GENERATED CODE -- DO NOT EDIT!

'use strict';
var grpc = require('grpc');
var Person_pb = require('./Person_pb.js');

function serialize_com_shengsiyuan_proto_IntReq(arg) {
  if (!(arg instanceof Person_pb.IntReq)) {
    throw new Error('Expected argument of type com.shengsiyuan.proto.IntReq');
  }
  return new Buffer(arg.serializeBinary());
}

function deserialize_com_shengsiyuan_proto_IntReq(buffer_arg) {
  return Person_pb.IntReq.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_shengsiyuan_proto_PRequest(arg) {
  if (!(arg instanceof Person_pb.PRequest)) {
    throw new Error('Expected argument of type com.shengsiyuan.proto.PRequest');
  }
  return new Buffer(arg.serializeBinary());
}

function deserialize_com_shengsiyuan_proto_PRequest(buffer_arg) {
  return Person_pb.PRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_shengsiyuan_proto_PResponse(arg) {
  if (!(arg instanceof Person_pb.PResponse)) {
    throw new Error('Expected argument of type com.shengsiyuan.proto.PResponse');
  }
  return new Buffer(arg.serializeBinary());
}

function deserialize_com_shengsiyuan_proto_PResponse(buffer_arg) {
  return Person_pb.PResponse.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_shengsiyuan_proto_StreamListResponse(arg) {
  if (!(arg instanceof Person_pb.StreamListResponse)) {
    throw new Error('Expected argument of type com.shengsiyuan.proto.StreamListResponse');
  }
  return new Buffer(arg.serializeBinary());
}

function deserialize_com_shengsiyuan_proto_StreamListResponse(buffer_arg) {
  return Person_pb.StreamListResponse.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_shengsiyuan_proto_StreamReq(arg) {
  if (!(arg instanceof Person_pb.StreamReq)) {
    throw new Error('Expected argument of type com.shengsiyuan.proto.StreamReq');
  }
  return new Buffer(arg.serializeBinary());
}

function deserialize_com_shengsiyuan_proto_StreamReq(buffer_arg) {
  return Person_pb.StreamReq.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_shengsiyuan_proto_StreamResp(arg) {
  if (!(arg instanceof Person_pb.StreamResp)) {
    throw new Error('Expected argument of type com.shengsiyuan.proto.StreamResp');
  }
  return new Buffer(arg.serializeBinary());
}

function deserialize_com_shengsiyuan_proto_StreamResp(buffer_arg) {
  return Person_pb.StreamResp.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_com_shengsiyuan_proto_StreamResponse(arg) {
  if (!(arg instanceof Person_pb.StreamResponse)) {
    throw new Error('Expected argument of type com.shengsiyuan.proto.StreamResponse');
  }
  return new Buffer(arg.serializeBinary());
}

function deserialize_com_shengsiyuan_proto_StreamResponse(buffer_arg) {
  return Person_pb.StreamResponse.deserializeBinary(new Uint8Array(buffer_arg));
}


var PersonServiceService = exports.PersonServiceService = {
  getRealNameByUsername: {
    path: '/com.shengsiyuan.proto.PersonService/GetRealNameByUsername',
    requestStream: false,
    responseStream: false,
    requestType: Person_pb.PRequest,
    responseType: Person_pb.PResponse,
    requestSerialize: serialize_com_shengsiyuan_proto_PRequest,
    requestDeserialize: deserialize_com_shengsiyuan_proto_PRequest,
    responseSerialize: serialize_com_shengsiyuan_proto_PResponse,
    responseDeserialize: deserialize_com_shengsiyuan_proto_PResponse,
  },
  getPersonsByAge: {
    path: '/com.shengsiyuan.proto.PersonService/GetPersonsByAge',
    requestStream: false,
    responseStream: true,
    requestType: Person_pb.IntReq,
    responseType: Person_pb.StreamResponse,
    requestSerialize: serialize_com_shengsiyuan_proto_IntReq,
    requestDeserialize: deserialize_com_shengsiyuan_proto_IntReq,
    responseSerialize: serialize_com_shengsiyuan_proto_StreamResponse,
    responseDeserialize: deserialize_com_shengsiyuan_proto_StreamResponse,
  },
  getPersonsWrapperByAges: {
    path: '/com.shengsiyuan.proto.PersonService/GetPersonsWrapperByAges',
    requestStream: true,
    responseStream: false,
    requestType: Person_pb.IntReq,
    responseType: Person_pb.StreamListResponse,
    requestSerialize: serialize_com_shengsiyuan_proto_IntReq,
    requestDeserialize: deserialize_com_shengsiyuan_proto_IntReq,
    responseSerialize: serialize_com_shengsiyuan_proto_StreamListResponse,
    responseDeserialize: deserialize_com_shengsiyuan_proto_StreamListResponse,
  },
  biTalk: {
    path: '/com.shengsiyuan.proto.PersonService/BiTalk',
    requestStream: true,
    responseStream: true,
    requestType: Person_pb.StreamReq,
    responseType: Person_pb.StreamResp,
    requestSerialize: serialize_com_shengsiyuan_proto_StreamReq,
    requestDeserialize: deserialize_com_shengsiyuan_proto_StreamReq,
    responseSerialize: serialize_com_shengsiyuan_proto_StreamResp,
    responseDeserialize: deserialize_com_shengsiyuan_proto_StreamResp,
  },
};

exports.PersonServiceClient = grpc.makeGenericClientConstructor(PersonServiceService);
