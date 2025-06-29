package com.se.quartz.service;

import com.se.quartz.dto.request.CreateTaskRequest;
import com.se.quartz.entity.Task;
import com.se.quartz.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.se.quartz.AlreadyExistsException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;

    private final JobService jobService;

    /**
     * Create a task
     *
     * @param request CreateTaskRequest
     * @return Task
     */
    @Transactional
    public Task create(CreateTaskRequest request) {
        // todo: check by exsists
        taskRepository.findByName(request.getName())
                .ifPresent(s -> {
                    throw new AlreadyExistsException(request.getName());
                });

        Task task = taskRepository.save(Task.builder()
            .name(request.getName())
            .group(request.getGroup())
            .cronExpression(request.getCronExpression())
            .build());

        try {
            jobService.scheduleTaskJob(task);
        } catch (SchedulerException e) {
            log.error("Error scheduling task: {}", task.getName(), e);
        }

        return task;
    }


    public List<Task> list() {
        List<Task> all = taskRepository.findAll();
        return all;
    }

    @Transactional
    public void delete(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        boolean isUnScheduledJob = jobService.unScheduleTaskJob(task);
        if (isUnScheduledJob) {
            taskRepository.delete(task);
            log.info("Task {} deleted successfully", task.getName());
        } else {
            log.error("Error deleting task: {}", task.getName());
        }
    }

    @Transactional
    public void delete(String id) {
        delete(UUID.fromString(id));
    }

    public Task getById(String id) {
        UUID uuid = UUID.fromString(id);
        Task task = taskRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Task not found"));
return  task;
    }
}
