package com.se.sample.service;

import com.se.sample.models.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Log4j2
@Service
public class MarketingService {

    private final TaskService taskService;


    public MarketingService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Async
    @EventListener
    void broadcast(Operation op ) {
        taskService.start(op.getTask().getId());

        IntStream stream = IntStream.range(1, 10);
        stream.forEach(actual -> {
            if(taskService.active(op.getTask().getId())){


                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                UUID id = op.getTask().getId();
                taskService.progress(id, actual * 10);
                log.info("Iteration {} finished.",id);
            }
            else {
                log.info("Broadcast canceled.");
                return;
            }
        });

        taskService.complete(op.getTask().getId());
    }
}
