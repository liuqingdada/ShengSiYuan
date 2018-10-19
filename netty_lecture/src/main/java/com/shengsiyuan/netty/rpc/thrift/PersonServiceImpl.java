package com.shengsiyuan.netty.rpc.thrift;

import com.shengsiyuan.thrift.DataException;
import com.shengsiyuan.thrift.Person;
import com.shengsiyuan.thrift.PersonService;

import org.apache.thrift.TException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByName(String name) throws DataException, TException {
        System.out.println("Got Client Param: " + name);

        Map<Integer, String> map = new HashMap<Integer, String>() {
            {
                this.put(1, "1");
                this.put(2, "2");
            }
        };
        Person person = new Person();
        person.setName(name)
              .setAge(19)
              .setMarried(false)
              .setChildren(Arrays.asList("hello", "world"))
              .setMapper(map);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got Client Param: ");
        System.out.println(person);
    }
}
