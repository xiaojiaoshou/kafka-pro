package com.ghx.kafkapro.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 *
 * @description:
 * @type: JAVA
 * @since: 2020/10/15 18:32
 * @author: guohuixiang
 */
@Component
public class ConsumerListener {


    /**
     * 定义此消费者接收topics = "demo"的消息，与controller中的topic对应上即可
     * @param record 变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     */
    @KafkaListener(topics = "test")
    public void listen (ConsumerRecord<?, ?> record){
        System.out.printf("消费端接收到消序：topic: %s, offset: %d, value ：%s \n", record.topic(), record.offset(), record.value());
    }


}
