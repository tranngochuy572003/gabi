package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.TaskDto;
import com.gabispa.restfulservice.entity.Task;
import com.gabispa.restfulservice.repository.ITaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor

public class taskService implements com.gabispa.restfulservice.service.taskService {

    private ITaskRepository iTaskRepository;

    @Autowired
    public taskService(ITaskRepository taskRepository) {
        this.iTaskRepository = taskRepository;
    }

    @Override
    public void addTask(Task task) {
        iTaskRepository.save(task);
    }

    @Override
    public List<Task> getAllTask() {
        return iTaskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> taskOptional = iTaskRepository.findById(id);
        return taskOptional.orElse(null);
    }

    @Override
    public void updateTask(Long id, TaskDto taskDto) {
        Optional<Task> taskOptional = iTaskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDescription(taskDto.getDescription());
            task.setName(taskDto.getName());
            task.setType(taskDto.getType());
            task.setTimeWork(taskDto.getTimeWork());
            iTaskRepository.save(task);

        } else {
            throw new EntityNotFoundException("Task not found with id: " + id);

        }
    }

    @Override
    public String deleteTask(Long id) {
        try {
            iTaskRepository.deleteById(id);
            return "delete task success";

        } catch (EntityNotFoundException e) {
            return "Invalid taskID";
        }
    }
}
