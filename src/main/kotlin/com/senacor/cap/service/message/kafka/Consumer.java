package com.senacor.cap.service.message.kafka;

import com.senacor.cap.service.message.ChatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private ChatMessageService chatMessageService;


    @KafkaListener(topics = "default", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));

        // save message
        chatMessageService.saveChatMessage("default", "engineer", message);


    }
}
