package com.se.sample.models;

import com.se.sample.models.enums.TaskState;
import lombok.Data;

import java.util.UUID;

@Data
public class Task {

    private UUID id = UUID.randomUUID();
    private Integer progress = 0;
    private TaskState state = TaskState.CREATED;
    private String name;


}
