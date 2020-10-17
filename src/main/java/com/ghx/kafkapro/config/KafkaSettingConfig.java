package com.ghx.kafkapro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

/**
 *
 * @description:
 * @type: JAVA
 * @since: 2020/10/15 20:46
 * @author: guohuixiang
 */
@Configuration
public class KafkaSettingConfig {

    @Bean("ackContainerFactory")
    public ConcurrentKafkaListenerContainerFactory ackContainerFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory factory =
                new ConcurrentKafkaListenerContainerFactory();
        // kafka的消费端提交交给用户去处理
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }


    @Bean("autoAckContainerFactory")
    @Primary
    public ConcurrentKafkaListenerContainerFactory ackAutoContainerFactory(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory factory =
                new ConcurrentKafkaListenerContainerFactory();
        // kafka的消费端提交交给用户去处理
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
