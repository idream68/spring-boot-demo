package com.springboot.demo.kafkaclient;

import com.springboot.demo.kafkaclient.client.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: zjhan
 * @Date: 2021/4/26 11:44
 * @Description:
 **/
@Component
public class SendMessage {
    @Autowired
    Producer producer;
    @Value("${topic:testSender}")
    public String topicName;
    @Value("${message:testSenderMessage}")
    public String message;

    @Scheduled(fixedDelay = 10000)
    public void send() {
        producer.sendMessageByTopicName(topicName, message);
    }
}
