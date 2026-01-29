package com.se.sample.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Counter {
    private final AtomicInteger count = new AtomicInteger(0);

    // every 5 ms
    @Scheduled(fixedDelay = 5)
    public void scheduled() {
        System.out.println("triggered, time" + LocalDateTime.now());
        this.count.incrementAndGet();
    }

    public int getInvocationCount() {
        return this.count.get();
    }
}
