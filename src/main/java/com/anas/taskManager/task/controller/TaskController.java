package com.anas.taskManager.task.controller;

import com.anas.taskManager.task.entity.Task;
import com.anas.taskManager.task.service.TaskServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceInterface taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.findAll();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task task = taskService.findById(id);

        return task != null? ResponseEntity.ok(task) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task newTask = taskService.save(task);

        return new ResponseEntity<>(newTask,HttpStatus.CREATED); // 201 created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task task){
        Task existingTask = taskService.findById(id);

        if (existingTask == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // to ensure that the id for the updated task is the same as the id in the path
        task.setId(id);

        Task updatedTask = taskService.save(task);

        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTaskById(@PathVariable Long id){

        if (taskService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 no_content
    }
}
