package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;


    @Test
    void shouldDeleteTask() {
        //Given
        Task taskMock = new Task(1L, "title", "content");

        //When
        dbService.saveTask(taskMock);
        dbService.deleteTask(taskMock.getId());

        //Then
        assertEquals(dbService.getAllTasks().size(), 0);
    }

    @Test
    void shouldGetAllTasks() {
        //Given
        List<Task> tasksMock = List.of(new Task(1L, "title", "content"));
        when(taskRepository.findAll()).thenReturn(tasksMock);
        //When
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertThat(tasks).isNotNull();
        assertEquals(1, tasks.size());
        assertEquals("title", tasks.get(0).getTitle());
        assertEquals("content", tasks.get(0).getContent());
    }

    @Test
    void shouldReturnEmptyOptional() throws TaskNotFoundException {
        //Given
        Task task =new Task(1L, "namedTask", "test");
        when(taskRepository.findById(any())).thenReturn(Optional.of(task));
        Long id = 1L;

        //When
        Task retrievedTask = dbService.getTask(task.getId());

        //Then
        assertEquals("namedTask",retrievedTask.getTitle());
    }

    @Test
    void shouldGetTask() {
        //Given
        Task taskMock = new Task(1L, "title", "content");
        when(taskRepository.findById(taskMock.getId())).thenReturn(Optional.ofNullable(taskMock));
        //When
        Task task = null;
        try {
            task = dbService.getTask(taskMock.getId());
        } catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
        Optional<Task> task1 = Optional.of(task);
        //Then
        assertTrue(task1.isPresent());
        assertEquals("title", task1.get().getTitle());
        assertEquals("content", task1.get().getContent());
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task taskMock = new Task(1L, "title", "content");
        when(taskRepository.save(taskMock)).thenReturn(taskMock);
        //When
        Task task = dbService.saveTask(taskMock);
        //Then
        assertThat(task).isNotNull();
        assertEquals(taskMock.getId(), task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }
}