package com.se.sample.service;


import com.se.sample.models.Task;
import com.se.sample.models.TaskRequest;
import com.se.sample.models.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService {


    private Map<UUID, Task> tasksMap = new ConcurrentHashMap<>();

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
}
