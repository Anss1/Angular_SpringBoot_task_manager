package com.anas.taskManager.task.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.anas.taskManager.task.entity.Task;
import com.anas.taskManager.task.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void findAll_ShouldReturnTaskList(){
        // given
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("task1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("task2");

        List<Task> expectedTasks = List.of(task1,task2);

        // mockito
        when (taskRepository.findAll()).thenReturn(expectedTasks);

        // when
        List<Task> actualTasks = taskService.findAll();

        // then
        assertNotNull(actualTasks);

        assertEquals(2,actualTasks.size());

        assertEquals("task1",actualTasks.get(0).getTitle());

        // verify mock called one time
        verify(taskRepository,times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnTask(){
        // given
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("task1");

        when (taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        // when
        Task actualTask = taskService.findById(1L);

        // then
        assertNotNull(actualTask);
        assertEquals("task1",actualTask.getTitle());
        verify(taskRepository,times(1)).findById(1L);
    }

    @Test
    void findById_WhenTaskDoesntExist_ShouldReturnNull(){

        when (taskRepository.findById(99L)).thenReturn(Optional.empty());

        // when
        Task actualTask = taskService.findById(99L);

        // then
        assertNull(actualTask);
        verify(taskRepository,times(1)).findById(99L);
    }

    @Test
    void save_WhenAddingNewTask_ShouldReturnTask(){
        Task taskToSave = new Task();
        taskToSave.setTitle("new task");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("new task");

        when (taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task actualTask = taskService.save(taskToSave);

        assertNotNull(actualTask);
        assertNotNull(actualTask.getId());
        assertEquals("new task",actualTask.getTitle());
        verify(taskRepository,times(1)).save(any(Task.class));
    }

    @Test
    void deleteById_ShouldNotReturnAndPass(){
        Long taskIdToDelete = 1L;
        doNothing().when(taskRepository).deleteById(taskIdToDelete);
        taskService.deleteById(taskIdToDelete);
        verify(taskRepository,times(1)).deleteById(taskIdToDelete);
    }
}
