package com.shengsiyuan.netty.rpc.grpc;

import com.java.util.function.UncheckedConsumer;

import java.io.IOException;
import java.util.Optional;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

    private Server mServer;

    private void start() throws IOException {
        mServer = ServerBuilder.forPort(8899)
                               .addService(new PersonServiceImpl())
                               .build();
        Optional.of(mServer)
                .ifPresent(UncheckedConsumer.unchecked(s -> {
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

    private void awaitTermination() throws InterruptedException {
        Optional.of(mServer)
                .ifPresent(UncheckedConsumer.unchecked(Server::awaitTermination));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.awaitTermination();
    }
}
