package com.shengsiyuan.boot.kafka;

/**
 * Created by andy
 * 2020/1/20.
 * Email: 1239604859@qq.com
 */

public interface Kafka {

    interface KafkaGroup {
        String SUHEN = "suhen";
        String COOPER = "cooper";
    }

    interface KafkaTopic {
        String COOPER_TOPIC = "cooperTopic";
        String SUHEN_TOPIC = "suhenTopic";
    }
}
