package com.andersen.maks.jwt.domain;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class Consumer {

    @JmsListener(destination = "simple-jms-queue")
    public String receiveQueue(String text) {
        return text;
    }
}
