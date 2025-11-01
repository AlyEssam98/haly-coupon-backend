package com.haly.coupon.service;

import com.haly.coupon.model.Subscriber;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberService {

    private final List<Subscriber> subscribers = new ArrayList<>();

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println("New subscriber added: " + subscriber.getEmail());
    }

    public List<Subscriber> getAll() {
        return subscribers;
    }
}
