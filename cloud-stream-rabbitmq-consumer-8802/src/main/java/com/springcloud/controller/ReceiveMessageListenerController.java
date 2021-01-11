package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String serverPort;

    /**
     * 监听
     *
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void setInput(Message<String> message) {
        System.out.println(serverPort + ":接收到的消息：" + message.getPayload());
    }
}
