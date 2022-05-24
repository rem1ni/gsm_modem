package com.example.demo.controller;

import com.example.demo.http.request.SmsRequest;
import com.example.demo.model.Message;
import com.example.demo.model.Subscriber;
import com.example.demo.repository.MessageRepos;
import com.example.demo.repository.SubscriberRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/get")
public class GetSms {
    @Autowired
    SubscriberRepos subscriberRepos;
    @Autowired
    MessageRepos messageRepos;

    @PostMapping("")
    public ResponseEntity<?> GetNewMessage(@RequestBody SmsRequest smsRequest) {
        if (smsRequest.getNumber()==null) return ResponseEntity.ok("fail");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        if (subscriberRepos.existsByNumber(smsRequest.getNumber())) {
            Subscriber subscriber = subscriberRepos.findByNumber(smsRequest.getNumber());
            Message message = new Message(subscriber,smsRequest.getText(),formatter.format(date), "RECEIVED");
            System.out.println(message);
            messageRepos.save(message);
            return ResponseEntity.ok("message received");
        }
        else{
            Subscriber subscriber=new Subscriber(smsRequest.getNumber());
            subscriberRepos.save(subscriber);
            Message message = new Message(subscriber,smsRequest.getText(),formatter.format(date), "RECEIVED");
            messageRepos.save(message);
            return ResponseEntity.ok("Subscriber created,message received");
        }
    }
}
