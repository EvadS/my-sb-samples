package com.se.sample.service;


import com.se.sample.models.Operation;
import com.se.sample.models.Task;
import com.se.sample.models.response.TaskResponse;
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
       private final ApplicationEventPublisher publisher;

    public TaskServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.publisher = eventPublisher;
    }


    public List<String> getAll() {
        return tasksMap.values().stream().map(Task::toString).toList();
    }


    @Override
    public TaskResponse getById(String id) {

        UUID uuid = UUID.fromString(id);
        Task task = tasksMap.get(uuid);
        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId().toString());
        TaskState state = task.getState();
        taskResponse.setState(state.name());
        return taskResponse;
    }

    @Override
    public void submit(Operation op) {
        tasksMap.computeIfAbsent(op.getTask().getId(), k -> new Task());
        publisher.publishEvent(op);
    }

    @Override
    public void start(UUID id) {
        tasksMap.computeIfPresent(id, (k,v) -> v.copy(TaskState.RUNNING));
    }

    @Override
    public void progress(UUID id, Integer progress) {
        tasksMap.computeIfPresent(id, (k,v) -> v.copy(progress));
    }

    @Override
    public void cancel(UUID id) {
        tasksMap.computeIfPresent(id, (k,v) -> v.copy(TaskState.CANCELED));
    }

    @Override
    public Boolean active(UUID id) {
        if(tasksMap.get(id) !=   null ){
            return  tasksMap.get(id).getState() == TaskState.RUNNING;
        }

        throw new IllegalArgumentException("Task $id not found");
    }

    @Override
    public void complete(UUID id) {
        tasksMap.computeIfPresent(id, (k,v) -> v.copy(TaskState.COMPLETED));
    }

}
