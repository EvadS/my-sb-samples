package com.se.sample.service;

import com.se.sample.models.Operation;
import com.se.sample.models.response.TaskResponse;

import java.util.UUID;

public interface TaskService {

    TaskResponse getById(String id);

    void submit(Operation op);

    void start(UUID id);

    void progress(UUID id, Integer progress);

    void cancel(UUID id);

    Boolean active(UUID id);

    void complete(UUID id);
}