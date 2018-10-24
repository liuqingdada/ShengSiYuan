package com.shengsiyuan.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.1)",
    comments = "Source: Person.proto")
public final class PersonServiceGrpc {

  private PersonServiceGrpc() {}

  public static final String SERVICE_NAME = "com.shengsiyuan.proto.PersonService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.shengsiyuan.proto.PRequest,
      com.shengsiyuan.proto.PResponse> getGetRealNameByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUsername",
      requestType = com.shengsiyuan.proto.PRequest.class,
      responseType = com.shengsiyuan.proto.PResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.shengsiyuan.proto.PRequest,
      com.shengsiyuan.proto.PResponse> getGetRealNameByUsernameMethod() {
    io.grpc.MethodDescriptor<com.shengsiyuan.proto.PRequest, com.shengsiyuan.proto.PResponse> getGetRealNameByUsernameMethod;
    if ((getGetRealNameByUsernameMethod = PersonServiceGrpc.getGetRealNameByUsernameMethod) == null) {
      synchronized (PersonServiceGrpc.class) {
        if ((getGetRealNameByUsernameMethod = PersonServiceGrpc.getGetRealNameByUsernameMethod) == null) {
          PersonServiceGrpc.getGetRealNameByUsernameMethod = getGetRealNameByUsernameMethod = 
              io.grpc.MethodDescriptor.<com.shengsiyuan.proto.PRequest, com.shengsiyuan.proto.PResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.shengsiyuan.proto.PersonService", "GetRealNameByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.shengsiyuan.proto.PRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.shengsiyuan.proto.PResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PersonServiceMethodDescriptorSupplier("GetRealNameByUsername"))
                  .build();
          }
        }
     }
     return getGetRealNameByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.shengsiyuan.proto.IntReq,
      com.shengsiyuan.proto.StreamResponse> getGetPersonsByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPersonsByAge",
      requestType = com.shengsiyuan.proto.IntReq.class,
      responseType = com.shengsiyuan.proto.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.shengsiyuan.proto.IntReq,
      com.shengsiyuan.proto.StreamResponse> getGetPersonsByAgeMethod() {
    io.grpc.MethodDescriptor<com.shengsiyuan.proto.IntReq, com.shengsiyuan.proto.StreamResponse> getGetPersonsByAgeMethod;
    if ((getGetPersonsByAgeMethod = PersonServiceGrpc.getGetPersonsByAgeMethod) == null) {
      synchronized (PersonServiceGrpc.class) {
        if ((getGetPersonsByAgeMethod = PersonServiceGrpc.getGetPersonsByAgeMethod) == null) {
          PersonServiceGrpc.getGetPersonsByAgeMethod = getGetPersonsByAgeMethod = 
              io.grpc.MethodDescriptor.<com.shengsiyuan.proto.IntReq, com.shengsiyuan.proto.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.shengsiyuan.proto.PersonService", "GetPersonsByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.shengsiyuan.proto.IntReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.shengsiyuan.proto.StreamResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PersonServiceMethodDescriptorSupplier("GetPersonsByAge"))
                  .build();
          }
        }
     }
     return getGetPersonsByAgeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.shengsiyuan.proto.IntReq,
      com.shengsiyuan.proto.StreamListResponse> getGetPersonsWrapperByAgesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPersonsWrapperByAges",
      requestType = com.shengsiyuan.proto.IntReq.class,
      responseType = com.shengsiyuan.proto.StreamListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.shengsiyuan.proto.IntReq,
      com.shengsiyuan.proto.StreamListResponse> getGetPersonsWrapperByAgesMethod() {
    io.grpc.MethodDescriptor<com.shengsiyuan.proto.IntReq, com.shengsiyuan.proto.StreamListResponse> getGetPersonsWrapperByAgesMethod;
    if ((getGetPersonsWrapperByAgesMethod = PersonServiceGrpc.getGetPersonsWrapperByAgesMethod) == null) {
      synchronized (PersonServiceGrpc.class) {
        if ((getGetPersonsWrapperByAgesMethod = PersonServiceGrpc.getGetPersonsWrapperByAgesMethod) == null) {
          PersonServiceGrpc.getGetPersonsWrapperByAgesMethod = getGetPersonsWrapperByAgesMethod = 
              io.grpc.MethodDescriptor.<com.shengsiyuan.proto.IntReq, com.shengsiyuan.proto.StreamListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.shengsiyuan.proto.PersonService", "GetPersonsWrapperByAges"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.shengsiyuan.proto.IntReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.shengsiyuan.proto.StreamListResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PersonServiceMethodDescriptorSupplier("GetPersonsWrapperByAges"))
                  .build();
          }
        }
     }
     return getGetPersonsWrapperByAgesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.shengsiyuan.proto.StreamReq,
      com.shengsiyuan.proto.StreamResp> getBiTalkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BiTalk",
      requestType = com.shengsiyuan.proto.StreamReq.class,
      responseType = com.shengsiyuan.proto.StreamResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.shengsiyuan.proto.StreamReq,
      com.shengsiyuan.proto.StreamResp> getBiTalkMethod() {
    io.grpc.MethodDescriptor<com.shengsiyuan.proto.StreamReq, com.shengsiyuan.proto.StreamResp> getBiTalkMethod;
    if ((getBiTalkMethod = PersonServiceGrpc.getBiTalkMethod) == null) {
      synchronized (PersonServiceGrpc.class) {
        if ((getBiTalkMethod = PersonServiceGrpc.getBiTalkMethod) == null) {
          PersonServiceGrpc.getBiTalkMethod = getBiTalkMethod = 
              io.grpc.MethodDescriptor.<com.shengsiyuan.proto.StreamReq, com.shengsiyuan.proto.StreamResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.shengsiyuan.proto.PersonService", "BiTalk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.shengsiyuan.proto.StreamReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.shengsiyuan.proto.StreamResp.getDefaultInstance()))
                  .setSchemaDescriptor(new PersonServiceMethodDescriptorSupplier("BiTalk"))
                  .build();
          }
        }
     }
     return getBiTalkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PersonServiceStub newStub(io.grpc.Channel channel) {
    return new PersonServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PersonServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PersonServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PersonServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PersonServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PersonServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRealNameByUsername(com.shengsiyuan.proto.PRequest request,
        io.grpc.stub.StreamObserver<com.shengsiyuan.proto.PResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUsernameMethod(), responseObserver);
    }

    /**
     */
    public void getPersonsByAge(com.shengsiyuan.proto.IntReq request,
        io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPersonsByAgeMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.shengsiyuan.proto.IntReq> getPersonsWrapperByAges(
        io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamListResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetPersonsWrapperByAgesMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamReq> biTalk(
        io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamResp> responseObserver) {
      return asyncUnimplementedStreamingCall(getBiTalkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUsernameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.shengsiyuan.proto.PRequest,
                com.shengsiyuan.proto.PResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .addMethod(
            getGetPersonsByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.shengsiyuan.proto.IntReq,
                com.shengsiyuan.proto.StreamResponse>(
                  this, METHODID_GET_PERSONS_BY_AGE)))
          .addMethod(
            getGetPersonsWrapperByAgesMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.shengsiyuan.proto.IntReq,
                com.shengsiyuan.proto.StreamListResponse>(
                  this, METHODID_GET_PERSONS_WRAPPER_BY_AGES)))
          .addMethod(
            getBiTalkMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.shengsiyuan.proto.StreamReq,
                com.shengsiyuan.proto.StreamResp>(
                  this, METHODID_BI_TALK)))
          .build();
    }
  }

  /**
   */
  public static final class PersonServiceStub extends io.grpc.stub.AbstractStub<PersonServiceStub> {
    private PersonServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PersonServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PersonServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PersonServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRealNameByUsername(com.shengsiyuan.proto.PRequest request,
        io.grpc.stub.StreamObserver<com.shengsiyuan.proto.PResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPersonsByAge(com.shengsiyuan.proto.IntReq request,
        io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetPersonsByAgeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.shengsiyuan.proto.IntReq> getPersonsWrapperByAges(
        io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamListResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetPersonsWrapperByAgesMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamReq> biTalk(
        io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamResp> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBiTalkMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class PersonServiceBlockingStub extends io.grpc.stub.AbstractStub<PersonServiceBlockingStub> {
    private PersonServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PersonServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PersonServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PersonServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.shengsiyuan.proto.PResponse getRealNameByUsername(com.shengsiyuan.proto.PRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUsernameMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.shengsiyuan.proto.StreamResponse> getPersonsByAge(
        com.shengsiyuan.proto.IntReq request) {
      return blockingServerStreamingCall(
          getChannel(), getGetPersonsByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PersonServiceFutureStub extends io.grpc.stub.AbstractStub<PersonServiceFutureStub> {
    private PersonServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PersonServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PersonServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PersonServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.shengsiyuan.proto.PResponse> getRealNameByUsername(
        com.shengsiyuan.proto.PRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;
  private static final int METHODID_GET_PERSONS_BY_AGE = 1;
  private static final int METHODID_GET_PERSONS_WRAPPER_BY_AGES = 2;
  private static final int METHODID_BI_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PersonServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PersonServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((com.shengsiyuan.proto.PRequest) request,
              (io.grpc.stub.StreamObserver<com.shengsiyuan.proto.PResponse>) responseObserver);
          break;
        case METHODID_GET_PERSONS_BY_AGE:
          serviceImpl.getPersonsByAge((com.shengsiyuan.proto.IntReq) request,
              (io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PERSONS_WRAPPER_BY_AGES:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getPersonsWrapperByAges(
              (io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamListResponse>) responseObserver);
        case METHODID_BI_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biTalk(
              (io.grpc.stub.StreamObserver<com.shengsiyuan.proto.StreamResp>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PersonServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PersonServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.shengsiyuan.proto.PersonProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PersonService");
    }
  }

  private static final class PersonServiceFileDescriptorSupplier
      extends PersonServiceBaseDescriptorSupplier {
    PersonServiceFileDescriptorSupplier() {}
  }

  private static final class PersonServiceMethodDescriptorSupplier
      extends PersonServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PersonServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PersonServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PersonServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUsernameMethod())
              .addMethod(getGetPersonsByAgeMethod())
              .addMethod(getGetPersonsWrapperByAgesMethod())
              .addMethod(getBiTalkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
