package com.example.demo.repository;

import com.example.demo.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SubscriberRepos extends JpaRepository<Subscriber,Long> {
    Subscriber findByNumber(String number);
    Boolean existsByNumber(String number);
    List<Subscriber> findAllByOrderByIdAsc();
}
