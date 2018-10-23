package com.shengsiyuan.netty.rpc.grpc;

import com.shengsiyuan.proto.PRequest;
import com.shengsiyuan.proto.PResponse;
import com.shengsiyuan.proto.PersonServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("127.0.0.1", 8899)
                                                             .usePlaintext()
                                                             .build();
        PersonServiceGrpc.PersonServiceBlockingStub stub = PersonServiceGrpc.newBlockingStub(
                managedChannel);

        PRequest suhen = PRequest.newBuilder()
                                 .setUsername("suhen")
                                 .build();
        PResponse pResponse = stub.getRealNameByUsername(suhen);
        System.out.println(pResponse);
    }
}
