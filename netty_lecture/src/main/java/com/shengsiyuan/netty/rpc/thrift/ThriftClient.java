package com.shengsiyuan.netty.rpc.thrift;

import com.shengsiyuan.thrift.Person;
import com.shengsiyuan.thrift.PersonService;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFastFramedTransport(new TSocket("127.0.0.1", 8899), 600);

        TProtocol protocol = new TCompactProtocol(transport);

        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();

            Person person = client.getPersonByName("suhen");
            System.out.println(person);

            System.out.println("==================");

            Person person2 = new Person();
            person2.setName("zhangsan")
                   .setAge(30)
                   .setMarried(true);

            client.savePerson(person2);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);

        } finally {
            transport.close();
        }
    }
}
