package com.ghx.kafkapro.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

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


    /**
     * 发送同步消息(严格的顺序消息)
     * @param topic
     * @param msg
     * @return
     */
    @RequestMapping("/sendSync/message")
    public String sendSync(String topic, String msg) {
        String result = "success";
        try {

            // 同步发送消息
            SendResult<String, Object> sendResult =
                    kafkaTemplate.send(topic, 0, "key", msg).get();

//            // 同步发送消息（并且耗时限制在1秒）
//            SendResult<String, Object> sendResult =
//                    kafkaTemplate.send("topic", "发送同步消息").get(1000, TimeUnit.MICROSECONDS);
            // 消息发送到的topic
            topic = sendResult.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = sendResult.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = sendResult.getRecordMetadata().offset();
            System.out.println("producer发送消息成功:" + "topic: " + topic + " partition: " + partition + " offset: " + offset + "\n");
        } catch (InterruptedException e) {
            System.out.println("发送消息失败:" + e.getMessage());
            result = "fail";
        } catch (ExecutionException e) {
            System.out.println("发送消息失败:" + e.getMessage());
            result = "fail";
        }
        return result;
    }

    /**
     * 发送同步消息(严格的顺序消息)
     * @param topic
     * @param msg
     * @return
     */
    @RequestMapping("/test/sendSync/message")
    public String send(String topic, String msg) {
        String result = "success";
        try {

            for (int i = 0; i < 10; i++) {
                // 同步发送消息
                SendResult<String, Object> sendResult =
                        kafkaTemplate.send(topic, 0, "key", msg + "序号id" + i).get();
                if (i == 5) {
                    int a = i / 0;
                }

                // 消息发送到的topic
                topic = sendResult.getRecordMetadata().topic();
                // 消息发送到的分区
                int partition = sendResult.getRecordMetadata().partition();
                // 消息在分区内的offset
                long offset = sendResult.getRecordMetadata().offset();
                System.out.println("producer发送消息成功:" + "topic: " + topic + " partition: " + partition + " offset: " + offset + "\n");
            }
        } catch (InterruptedException e) {
            System.out.println("发送消息失败:" + e.getMessage());
            result = "fail";
        } catch (ExecutionException e) {
            System.out.println("发送消息失败:" + e.getMessage());
            result = "fail";
        }
        return result;
    }

}
