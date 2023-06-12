package com.wang.tomatodirect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttMessageReceiver implements MessageHandler {
 
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            MessageHeaders headers = message.getHeaders();
            System.out.println(MqttHeaders.RECEIVED_TOPIC);
            //获取消息Topic
            String receivedTopic = (String) headers.get(MqttHeaders.RECEIVED_TOPIC);
            log.info("[获取到的消息的topic :]{} ", receivedTopic);
            //获取消息体
            String payload = (String) message.getPayload();
            log.info("[获取到的消息的payload :]{} ", payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




 
}
