package com.anas.taskManager.task.service;

import com.anas.taskManager.task.entity.Task;

import java.util.List;

public interface TaskServiceInterface {
    List<Task> findAll();
    Task findById(Long id);
    Task save(Task task);
    void deleteById(Long id);
}
