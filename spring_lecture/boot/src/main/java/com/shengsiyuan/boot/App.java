package com.shengsiyuan.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.annotation.PostConstruct;

/**
 * Created by andy
 * 2020/1/14.
 * Email: 1239604859@qq.com
 * <p>
 * 调试规范: 日志 远程调试
 * JDWP: Java Debug Wire Protocol, Java调试协议
 * java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=8000 -jar ***.jar
 * <p>
 * kafka:
 * 1
 * ./bin/zookeeper-server-start.sh config/zookeeper.properties
 * ./bin/kafka-server-start.sh config/server.properties
 * 2
 * ./bin/kafka-topics.sh --create --zookeeper localhost:2181(可集群配置localhost:2180,localhost:2181) --replication-factor 1 --partitions 1 --topic suhenTopic
 * ./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic suhenTopic
 * 3
 * ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic suhenTopic (--from-beginning) (--group name)
 * 4
 * ./bin/kafka-topics.sh --list --zookeeper localhost:2181
 * ./bin/kafka-topics.sh --list --bootstrap-server localhost:9092
 * 5
 * ./bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic suhenTopic
 */

@SpringBootApplication
@EnableWebSocket
@MapperScan
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.debug(App.class.getClassLoader().toString());
        // Default run
        SpringApplication.run(App.class, args);

        // Custom run
        /*SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);*/
    }

    @PostConstruct
    private void testLog() {
        logger.trace("Trace Message");
        logger.debug("Debug Message");
        logger.info("Info Message");
        logger.warn("Warn Message");
        logger.error("Error Message");
    }
}
