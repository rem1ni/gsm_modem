package com.example.demo.repository;

import com.example.demo.model.Message;;
import com.example.demo.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepos extends JpaRepository<Message,Long> {
    List<Message> findAllBySubscriber(Subscriber subscriber);
}
