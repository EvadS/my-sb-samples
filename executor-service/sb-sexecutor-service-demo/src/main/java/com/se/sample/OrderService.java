package com.se.sample;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {
    @Autowired
    private ExecutorService executorService;
    public void processOrders(List<String> orderIds) {
        List<Future<String>> futures = new ArrayList<>();
        for (String orderId : orderIds) {
            Future<String> future = executorService.submit(() -> processOrder(orderId));
            futures.add(future);
        }
        // Collect results
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get()); // Blocking call (waits for task to finish)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String processOrder(String orderId) {
        System.out.println("Processing Order: " + orderId + " - " + Thread.currentThread().getName());
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        return "Order " + orderId + " Processed!";
    }

@PostConstruct
private void init(){
        testExecutor();
}

    public  void testExecutor()
    {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing task in thread: " + Thread.currentThread().getName());
            }
        });


        Future<?> submit = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Submitting task in thread: " + Thread.currentThread().getName());
            }
        });

        try {
            submit.get();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Caught Exception: " + e.getCause().getMessage());
        }
    }

    @PreDestroy
    public void shutdownExecutor() {
        System.out.println("PREDestroy: Shutting down ExecutorService...");
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("ExecutorService shutdown interrupted: " + e.getMessage());
        }
    }
}