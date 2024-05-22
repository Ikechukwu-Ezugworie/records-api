package com.mkobo.assessment.recordsapi.queue;

import com.mkobo.assessment.recordsapi.config.QueueConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Send {

    private final static String QUEUE_NAME = "hello";
    private QueueConfig queueConfig;

    @Autowired
    public Send(QueueConfig queueConfig) {
        this.queueConfig = queueConfig;
    }

    @PostConstruct
    public void init() {
//        try (Channel channel = queueConfig.getChannel()) {
//            String message = "Hello World";
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//            log.info(" [x] Sent '{}'", message);
//        } catch (Exception ignored) {
//
//        }
    }
}

