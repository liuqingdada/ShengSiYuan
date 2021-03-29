package com.shengsiyuan.boot.controller;

import com.shengsiyuan.boot.kafka.KafkaMessage;
import com.shengsiyuan.boot.kafka.KafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by andy
 * 2020/1/20.
 * Email: 1239604859@qq.com
 */

@RestController
@RequestMapping(value = "/kafka", produces = MediaType.APPLICATION_JSON_VALUE)
public class KafkaController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaProducer mKafkaProducer;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public KafkaMessage sendKafkaMessage(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) {
        logger.debug("sendKafkaMessage invoked.");
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setId(id);
        kafkaMessage.setUsername(username);
        kafkaMessage.setPassword(password);
        kafkaMessage.setDate(new Date());

        mKafkaProducer.sendKafkaMessage(kafkaMessage);

        return kafkaMessage;
    }

    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    public KafkaMessage postKafkaMessage(@RequestBody KafkaMessage kafkaMessage) {
        logger.debug("postKafkaMessage invoked.");
        kafkaMessage.setDate(new Date());

        mKafkaProducer.sendKafkaMessage(kafkaMessage);

        return kafkaMessage;
    }
}
