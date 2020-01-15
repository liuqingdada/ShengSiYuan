package com.shengsiyuan.boot.controller;

import com.shengsiyuan.boot.config.AppConfigBean;
import com.shengsiyuan.boot.domain.Person;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by andy
 * 2020/1/14.
 * Email: 1239604859@qq.com
 */

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    private static final Log logger = LogFactory.getLog(PersonController.class);

    @Value("${suhenConfig.suhenObject.name}")
    private String name;
    @Value("${suhenConfig.suhenObject.age}")
    private int age;

    @Autowired
    private AppConfigBean appConfigBean;

    @RequestMapping(value = "person", method = RequestMethod.GET)
    public Person getPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName(name);
        person.setBirth(new Date());

        logger.info(age);
        logger.info(appConfigBean.getName());
        logger.info(appConfigBean.getAge());

        return person;
    }
}
