package com.springboot.demo.kafkaclient.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: zjhan
 * @Date: 2021/4/26 11:41
 * @Description:
 **/
@Component
public class Consumer {
    @KafkaListener(topics = "${topic}")
    public void listenCrawlData(ConsumerRecord<?, ?> record) throws Exception {
        Optional<?> optional = Optional.ofNullable(record.value());
        if (optional.isPresent()) {
            String message = optional.get().toString();
            System.out.println("receive: " + message);
        }
    }
}
