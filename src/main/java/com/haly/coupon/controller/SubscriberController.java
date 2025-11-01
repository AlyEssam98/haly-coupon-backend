package com.haly.coupon.controller;

import com.haly.coupon.model.Subscriber;
import com.haly.coupon.service.SubscriberService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    private final SubscriberService service;

    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @PostMapping
    public Map<String, String> subscribe(@RequestBody Subscriber subscriber) {
        service.addSubscriber(subscriber);
        return Map.of("message", "Subscribed successfully!");
    }

    @GetMapping
    public Iterable<Subscriber> all() {
        return service.getAll();
    }
}
