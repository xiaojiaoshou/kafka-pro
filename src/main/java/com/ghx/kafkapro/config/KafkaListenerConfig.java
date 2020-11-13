package com.ghx.kafkapro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

/**
 *
 * @description:
 * @type: JAVA
 * @since: 2020/10/15 20:46
 * @author: guohuixiang
 */
@Configuration
public class KafkaListenerConfig {

    @Bean("autoAckContainerFactory")
    public ConcurrentKafkaListenerContainerFactory ackAutoContainerFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setBatchListener(true);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean("ackBatchContainerFactory")
    @Primary
    public ConcurrentKafkaListenerContainerFactory ackBatchContainerFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        // 并发创建的消费者数量
        factory.setConcurrency(1);
        factory.setBatchListener(true);
        factory.getContainerProperties().setPollTimeout(2000);
        // 设置提交偏移量的方式,kafka的消费端提交交给用户去处理
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }


}
