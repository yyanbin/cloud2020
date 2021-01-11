package com.srpingcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.srpingcloud.service.impl.MessageProviderImpl;

@RestController
public class SendMessageController {
    @Autowired
    private MessageProviderImpl messageProvider;

    @GetMapping("/send")
    public void sendMessage(){
        messageProvider.sendMsg();
    }
}
