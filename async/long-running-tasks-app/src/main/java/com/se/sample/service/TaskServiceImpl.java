package com.se.sample.service;


import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.se.sample.models.Operation;
import com.se.sample.models.Task;
import com.se.sample.models.TaskRequest;
import com.se.sample.models.TaskResponse;
import com.se.sample.models.enums.TaskState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskServiceImpl implements TaskService {

    private Map<UUID, Task> tasksMap = new ConcurrentHashMap<>();
    private ApplicationEventPublisher publisher;


    public TaskResponse createTask(TaskRequest taskRequest) {
        UUID id = UUID.randomUUID();

        Task task = new Task();
        task.setId(id);
        task.setName(taskRequest.getTaskName());
        tasksMap.put(id, task);

        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(id.toString());
        taskResponse.setName(taskRequest.getTaskName());
        return taskResponse;
    }

    public List<String> getAll() {
        return tasksMap.values().stream().map(task -> task.getId() + ", " + task.getName() + ", " + task.getProgress()).toList();
    }


    @Override
    public TaskResponse getById(String id) {

UUID uuid = UUID.fromString(id);
        Task task = tasksMap.get(uuid);

        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId().toString());
        taskResponse.setName(task.getName());
        TaskState state = task.getState();
        taskResponse.setState(state.name());
        return taskResponse;
    }

    @Override
    public void submit(Operation op) {

    }

    @Override
    public void start(UUID id) {

    }

    @Override
    public void progress(UUID id, Integer progress) {

    }

    @Override
    public void cancel(UUID id) {

    }

    @Override
    public Boolean active(UUID id) {
        return null;
    }

    @Override
    public void complete(UUID id) {

    }

//
//    void submit(Operation op) {
//        tasksMap.computeIfAbsent(op.getTask().getId(), p -> op.getTask());
//        publisher.publishEvent(op);
//    }

    // submit
    // start
    // progress

    // cancel
    // active
    // complete

}
