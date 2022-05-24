package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="messages")
public class Message  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "Subscriber_number",referencedColumnName = "number")
    private Subscriber subscriber;
    private String text;
    private String date;
    private String status;
    public Message(Subscriber subscriber, String text, String date,String status) {
        this.subscriber = subscriber;
        this.text = text;
        this.date = date;
        this.status=status;
    }

    public Message() {

    }


    public Subscriber getSubscriber() {
        return subscriber;
    }
    public String getSubscriberNumber() {
        return subscriber.getNumber();
    }
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
