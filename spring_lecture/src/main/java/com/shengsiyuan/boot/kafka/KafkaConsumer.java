package com.shengsiyuan.boot.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by andy
 * 2020/1/20.
 * Email: 1239604859@qq.com
 */

@Component
public class KafkaConsumer {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = {Kafka.KafkaTopic.SUHEN_TOPIC}, groupId = Kafka.KafkaGroup.SUHEN)
    public void obtainMessage(ConsumerRecord<String, String> consumerRecord) {
        logger.debug("obtaiMessage is invoked.");
        logger.debug(consumerRecord.toString());
        logger.debug("============================");
    }
}
