package com.shengsiyuan.netty.rpc.grpc;

import com.java.util.function.ThrowingConsumer;
import com.shengsiyuan.proto.PRequest;
import com.shengsiyuan.proto.PResponse;
import com.shengsiyuan.proto.PersonServiceGrpc;

import java.io.IOException;
import java.util.Optional;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class GrpcServer {

    private static class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {
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
    }

    // ******************************* //

    private Server mServer;

    private void start() throws IOException {
        mServer = ServerBuilder.forPort(8899)
                               .addService(new PersonServiceImpl())
                               .build();
        Optional.of(mServer)
                .ifPresent(ThrowingConsumer.unchecked(s -> {
                    s.start();
                    System.out.println("server is started");
                }));

        Runtime.getRuntime()
               .addShutdownHook(new Thread(() -> {
                   System.out.println("close jvm");
                   GrpcServer.this.shutdown();
               }));
    }

    private void shutdown() {
        Optional.of(mServer)
                .ifPresent(Server::shutdown);
    }

    private void awairTermination() throws InterruptedException {
        Optional.of(mServer)
                .ifPresent(ThrowingConsumer.unchecked(Server::awaitTermination));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.awairTermination();
    }
}
