package com.shengsiyuan.boot.kafka;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by andy
 * 2020/1/20.
 * Email: 1239604859@qq.com
 */

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class KafkaProducer {

    private final Gson mGson = new Gson();

    @Autowired
    private KafkaTemplate<String, String> mKafkaTemplate;

    public void sendKafkaMessage(KafkaMessage kafkaMessage) {
        mKafkaTemplate.send(Kafka.KafkaTopic.SUHEN_TOPIC, mGson.toJson(kafkaMessage));
    }
}
