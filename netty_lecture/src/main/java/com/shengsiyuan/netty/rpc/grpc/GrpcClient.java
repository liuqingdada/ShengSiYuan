package com.shengsiyuan.netty.rpc.grpc;

import com.java.util.function.UncheckedConsumer;
import com.shengsiyuan.proto.IntReq;
import com.shengsiyuan.proto.PRequest;
import com.shengsiyuan.proto.PResponse;
import com.shengsiyuan.proto.PersonServiceGrpc;
import com.shengsiyuan.proto.StreamListResponse;
import com.shengsiyuan.proto.StreamReq;
import com.shengsiyuan.proto.StreamResp;
import com.shengsiyuan.proto.StreamResponse;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class GrpcClient {

    public static void main(String[] args) throws Exception {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 8899)
                                                             .usePlaintext()
                                                             .build();
        PersonServiceGrpc.PersonServiceBlockingStub stub = PersonServiceGrpc.newBlockingStub(
                managedChannel);
        PersonServiceGrpc.PersonServiceStub asyncStub = PersonServiceGrpc.newStub(managedChannel);

        /**/

        PRequest suhen = PRequest.newBuilder()
                                 .setUsername("suhen")
                                 .build();
        PResponse pResponse = stub.getRealNameByUsername(suhen);
        System.out.println(pResponse);

        System.out.println("=====================================");

        Iterator<StreamResponse> persons = stub.getPersonsByAge(IntReq.newBuilder()
                                                                      .setAge(19)
                                                                      .build());
        persons.forEachRemaining(resp -> System.out.println(resp.toString()));

        System.out.println("=====================================");

        StreamObserver<IntReq> personsWrapperByAges = asyncStub.getPersonsWrapperByAges(
                new StreamObserver<StreamListResponse>() {
                    @Override
                    public void onNext(StreamListResponse value) {
                        System.out.println("on next");
                        value.getStreamResponseList()
                             .forEach(System.out::println);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("on error");
                        t.printStackTrace();
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("on completed\n");
                    }
                });
        personsWrapperByAges.onNext(IntReq.newBuilder()
                                          .setAge(12)
                                          .build());
        personsWrapperByAges.onNext(IntReq.newBuilder()
                                          .setAge(22)
                                          .build());
        personsWrapperByAges.onNext(IntReq.newBuilder()
                                          .setAge(32)
                                          .build());
        personsWrapperByAges.onNext(IntReq.newBuilder()
                                          .setAge(42)
                                          .build());
        personsWrapperByAges.onCompleted();

        System.out.println("=====================================");

        StreamObserver<StreamReq> reqStreamObserver = asyncStub.biTalk(
                new StreamObserver<StreamResp>() {
                    @Override
                    public void onNext(StreamResp value) {
                        System.out.println(value);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("on completed\n");
                    }
                });

        Stream.generate(() -> StreamReq.newBuilder()
                                       .setReqInfo(LocalDateTime.now()
                                                                .toString())
                                       .build())
              .limit(10)
              .forEach(UncheckedConsumer.unchecked(t -> {
                  reqStreamObserver.onNext(t);
                  Thread.sleep(1000);
              }));
    }
}
