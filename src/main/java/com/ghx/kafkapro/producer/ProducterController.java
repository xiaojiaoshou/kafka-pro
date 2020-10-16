package com.ghx.kafkapro.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @description:
 * @type: JAVA
 * @since: 2020/10/15 18:30
 * @author: guohuixiang
 */
@RequestMapping("/producter")
@RestController
public class ProducterController {


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("/send/message")
    public String send(String topic, String msg) {
        // 使用kafka模板发送信息
        ListenableFuture<SendResult<String, Object>> test = kafkaTemplate.send(topic, msg);
        return "success";
    }


    /**
     * 带回调结果的消息
     * @param topic
     * @param msg
     * @return
     */
    @RequestMapping("/sendResult/message")
    public String sendResult(String topic, String msg) {
        // 使用kafka模板发送信息
        ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send(topic, msg, msg);
        listenableFuture.addCallback(success -> {
            // 消息发送到的topic
            String topic1 = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);

        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
        return "success";
    }

}
