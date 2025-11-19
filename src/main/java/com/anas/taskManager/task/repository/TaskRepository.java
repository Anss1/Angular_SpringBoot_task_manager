package com.anas.taskManager.task.repository;

import com.anas.taskManager.task.entity.Priority;
import com.anas.taskManager.task.entity.Status;
import com.anas.taskManager.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);

    List<Task> findByDueDateLessThan(LocalDate date);

    List<Task> findByTitleContainingIgnoreCase(String keyword);


}
