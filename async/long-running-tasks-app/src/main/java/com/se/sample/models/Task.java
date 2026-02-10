package com.se.sample.models;

import com.se.sample.models.enums.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private UUID id = UUID.randomUUID();
    private Integer progress = 0;
    private TaskState state = TaskState.CREATED;



    public Task copy(Integer progress) {
        Task task = new Task();
        task.setId(this.id);
        task.setProgress(progress);
        task.setState(this.state);
        return task;
    }

    public Task copy(TaskState state) {
        Task task = new Task();
        task.setId(this.id);
        task.setProgress(this.progress);
        task.setState(state);

        return task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", progress=" + progress +
                ", state=" + state +
                '}';
    }
}
