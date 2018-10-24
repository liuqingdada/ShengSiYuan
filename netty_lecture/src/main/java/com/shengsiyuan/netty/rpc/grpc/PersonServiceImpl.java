package com.shengsiyuan.netty.rpc.grpc;

import com.shengsiyuan.proto.IntReq;
import com.shengsiyuan.proto.PRequest;
import com.shengsiyuan.proto.PResponse;
import com.shengsiyuan.proto.PersonServiceGrpc;
import com.shengsiyuan.proto.StreamListResponse;
import com.shengsiyuan.proto.StreamReq;
import com.shengsiyuan.proto.StreamResp;
import com.shengsiyuan.proto.StreamResponse;

import java.util.UUID;

import io.grpc.stub.StreamObserver;

public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {
    @Override
    public void getRealNameByUsername(PRequest request,
                                      StreamObserver<PResponse> responseObserver) {
        System.out.println("receive client info: " + request);

        PResponse pResponse = PResponse.newBuilder()
                                       .setRealname("server handle -> " + request.getUsername())
                                       .build();
        responseObserver.onNext(pResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getPersonsByAge(IntReq request,
                                StreamObserver<StreamResponse> responseObserver) {
        System.out.println("receive client info: " + request);

        StreamResponse suhen = StreamResponse.newBuilder()
                                             .setAge(request.getAge())
                                             .setName("suhen")
                                             .setCity("BeiJing")
                                             .build();
        StreamResponse zhangsan = StreamResponse.newBuilder()
                                                .setAge(request.getAge())
                                                .setName("zhangsan")
                                                .setCity("BeiJing")
                                                .build();
        StreamResponse lisi = StreamResponse.newBuilder()
                                            .setAge(request.getAge())
                                            .setName("lisi")
                                            .setCity("BeiJing")
                                            .build();
        StreamResponse wangwu = StreamResponse.newBuilder()
                                              .setAge(request.getAge())
                                              .setName("wangwu")
                                              .setCity("BeiJing")
                                              .build();
        responseObserver.onNext(suhen);
        responseObserver.onNext(zhangsan);
        responseObserver.onNext(lisi);
        responseObserver.onNext(wangwu);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<IntReq> getPersonsWrapperByAges(StreamObserver<StreamListResponse>
                                                                  responseObserver) {
        return new StreamObserver<IntReq>() {
            @Override
            public void onNext(IntReq value) {
                System.out.println("on next");
                System.out.println(value);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("on error");
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                StreamResponse.Builder zhangsan = StreamResponse.newBuilder()
                                                                .setName("zhangsan")
                                                                .setAge(11)
                                                                .setCity("ShangHai");
                StreamResponse.Builder lisi = StreamResponse.newBuilder()
                                                            .setName("lisi")
                                                            .setAge(21)
                                                            .setCity("BeiJing");

                StreamListResponse response = StreamListResponse.newBuilder()
                                                                .addStreamResponse(zhangsan)
                                                                .addStreamResponse(lisi)
                                                                .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
                System.out.println("on completed\n");
            }
        };
    }

    @Override
    public StreamObserver<StreamReq> biTalk(StreamObserver<StreamResp> responseObserver) {
        return new StreamObserver<StreamReq>() {
            @Override
            public void onNext(StreamReq value) {
                System.out.println(value);

                responseObserver.onNext(StreamResp.newBuilder()
                                                  .setRespInfo(UUID.randomUUID()
                                                                   .toString())
                                                  .build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
