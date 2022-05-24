package com.example.demo.controller;

import com.example.demo.MessagesInfo;
import com.example.demo.http.request.HistoryRequest;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/history")
public class ShowHistory {
    @Autowired
    SubscriberRepos subscriberRepos;
    @Autowired
    MessageRepos messageRepos;

    @PostMapping("")
    public ResponseEntity<?> History(@RequestBody HistoryRequest historyRequest) {
        if (subscriberRepos.existsByNumber(historyRequest.getNumber())){
            Subscriber subscriber=subscriberRepos.findByNumber(historyRequest.getNumber());
            List<Message> messageList=messageRepos.findAllBySubscriber(subscriber);
            return ResponseEntity.ok(messageList);
        }
        else return ResponseEntity.ok("subscriber not found");
    }

}
