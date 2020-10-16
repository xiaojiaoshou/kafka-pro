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
 * @since: 2020/10/15 21:35
 * @author: guohuixiang
 */
@RequestMapping("/SequenceProducter")
@RestController
public class SequenceProducterController {


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("/send/message")
    public String send(String topic, String msg) {
        // 使用kafka模板发送信息
        ListenableFuture<SendResult<String, Object>> test = kafkaTemplate.send(topic, msg, msg);
        return "success";
    }
}
