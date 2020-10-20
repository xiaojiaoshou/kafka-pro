package com.ghx.kafkapro.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

/**
 *
 * @description:
 * @type: JAVA
 * @since: 2020/10/15 18:32
 * @author: guohuixiang
 */
@Component
public class ConsumerListener {


    private volatile int count;

    TreeSet<String> treeSet = new TreeSet<>();


    /**
     * 定义此消费者接收topics = "test"的消息，与controller中的topic对应上即可
     * @param record 变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     */
    // @KafkaListener(topics = "test", groupId = "oms_group")
    public void listener(ConsumerRecord<?, ?> record) {
        System.out.printf("消费端接收到消序：topic: %s, offset: %d, value ：%s \n", record.topic(), record.offset(), record.value());
    }

    /**
     * 定义此消费者接收topics = "test"的消息，与controller中的topic对应上即可
     * @param record 变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     */
    // @KafkaListener(topics = "local_oms.user", groupId = "oms_group")
    // @KafkaListener(topics = "local_oms.customer", groupId = "oms_group-a")
    @KafkaListener(topics = "local_oms_stu", groupId = "oms_group-b", containerFactory = "ackContainerFactory")
    public void testListener(ConsumerRecord<?, ?> record, Acknowledgment ack) {


        System.out.printf("消费端接收到消序：topic: %s, offset: %d, value ：%s \n", record.topic(), record.offset(), record.value());
        count++;

        String value = (String) record.value();
        JSONObject jsonObject = JSON.parseObject(value);
        JSONArray data = jsonObject.getJSONArray("data");
        JSONObject jsonObject1 = data.getJSONObject(0);
        String age = jsonObject1.getString("age");
        treeSet.add(age);


        System.out.println("总数量count=" + count + " set:=" + treeSet.size());
        ack.acknowledge();

        // throw  new RuntimeException();

    }


    /**
     * 定义此消费者接收topics = "test"的消息，与controller中的topic对应上即可
     * @param record 变量代表消息本身，可以通过ConsumerRecord<?,?>类型的record变量来打印接收的消息的各种信息
     */
    // @KafkaListener(containerFactory = "ackContainerFactory", topics = "first",groupId = "oms_group")
    public void listenerAck(ConsumerRecord<Object, Object> record, Acknowledgment ack) {
        System.out.printf("消费端接收到消序：topic: %s, offset: %d, value ：%s \n", record.topic(), record.offset(), record.value());
        // 手动提交offset
        ack.acknowledge();
    }


}
