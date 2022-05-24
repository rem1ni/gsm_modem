package com.example.demo.controller;

import com.example.demo.http.request.SentRequest;
import com.example.demo.model.Message;
import com.example.demo.model.Subscriber;
import com.example.demo.repository.MessageRepos;
import com.example.demo.repository.SubscriberRepos;
import com.example.demo.service.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class SendSms {
    @Autowired
    SubscriberRepos subscriberRepos;
    @Autowired
    MessageRepos messageRepos;
    @PostMapping("/send")
    public ResponseEntity<?> Send(@RequestBody SentRequest sentRequest) throws IOException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        if (subscriberRepos.existsByNumber(sentRequest.getNumber())){
            Subscriber subscriber=subscriberRepos.findByNumber(sentRequest.getNumber());
            Message message=new Message(subscriber,sentRequest.getText(),formatter.format(date), "SENT");
            messageRepos.save(message);
            Json.SendTo(message.getSubscriber().getNumber(),message.getText());

                return ResponseEntity.ok("message send");
        }
        return ResponseEntity.ok("user not found");
    }
}
