package com.mkobo.assessment.recordsapi.queue;

import com.mkobo.assessment.recordsapi.config.QueueConfig;
import com.rabbitmq.client.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
@DependsOn("send")
public class Receive {

    private final static String QUEUE_NAME = "hello";
    private QueueConfig queueConfig;

    @Autowired
    public Receive(QueueConfig queueConfig) {
        this.queueConfig = queueConfig;
    }


    @PostConstruct
    public void init() {
        try (Channel channel = queueConfig.getChannel()) {
                                log.info(" [*] Waiting for messages. To exit press CTRL+C");
//            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//                log.info(" [x] Received '{}'", message);
//            };
//
//            CancelCallback cancelCallback = consumerTag -> log.info(" [x] Cancelled '" + consumerTag + "'");
//
//            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
