package com.springboot.demo.kafkaclient.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author: zjhan
 * @Date: 2021/4/26 11:42
 * @Description:
 **/
@Component
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /***
     * 根据Kafka消息队列名称写入消息
     */
    public void sendMessageByTopicName(String topicName, String message) {
        kafkaTemplate.send(topicName, message).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.printf("Error while sending message %s to topic %s: %s%n", message, topicName,
                        throwable.getMessage());

            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.printf("Producer | Topic = %s | partition = %d , Offset = %d, send message:%s %n", topicName,
                        result.getRecordMetadata().partition(), result.getRecordMetadata().offset(), message);
            }
        });
    }
}
