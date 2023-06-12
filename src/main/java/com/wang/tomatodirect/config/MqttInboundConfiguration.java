package com.wang.tomatodirect.config;

import com.wang.tomatodirect.service.MqttMessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;
import java.util.UUID;

@Configuration
public class MqttInboundConfiguration {
 
    @Autowired
    private MqttConfig mqttConfig;
 
    @Resource
    private MqttPahoClientFactory factory;
    @Resource
    private MqttMessageReceiver mqttMessageReceiver;
    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(UUID.randomUUID().toString(), factory, "topic/browser");
        adapter.setCompletionTimeout(60000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setRecoveryInterval(10000);
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInBoundChannel());
        return adapter;
    }
 
 
    /**
     * 此处可以使用其他消息通道
     * Spring Integration默认的消息通道，它允许将消息发送给一个订阅者，然后阻碍发送直到消息被接收。
     *
     * @return MessageChannel
     */
    @Bean
    public MessageChannel mqttInBoundChannel() {
        return new DirectChannel();
    }
 
 
    /**
     * mqtt入站消息处理工具，对于指定消息入站通道接收到生产者生产的消息后处理消息的工具。
     *
     * @return MessageHandler
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInBoundChannel")
    public MessageHandler mqttMessageHandler() {
        return this.mqttMessageReceiver;
    }
}