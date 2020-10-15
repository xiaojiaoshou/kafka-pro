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
    public String send(String msg) {
        // 使用kafka模板发送信息
        ListenableFuture<SendResult<String, Object>> test = kafkaTemplate.send("test", msg);

        return "success";
    }

}
