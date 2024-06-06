package com.gabispa.restfulservice.service.impl;

import com.gabispa.restfulservice.dto.TaskDto;
import com.gabispa.restfulservice.entity.Task;
import com.gabispa.restfulservice.exception.BadRequestException;

import com.gabispa.restfulservice.mapper.taskMapper;
import com.gabispa.restfulservice.repository.taskRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor

public class taskService implements com.gabispa.restfulservice.service.taskService {

  private taskRepository taskRepository;

  @Autowired
  public taskService(taskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public void addTask(Task task) {
    if (task.getName().isEmpty()) {
      throw new BadRequestException("SupplyName isn't empty");
    }
    taskRepository.save(task);
  }

  @Override
  public List<Task> getAllTask() {
    return taskRepository.findAll();
  }

  @Override
  public Task getTaskById(Long id) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    if (taskOptional.isPresent()) {
      return taskOptional.get();
    } else {
      throw new BadRequestException("TaskId is invalid");
    }
  }

  @Override
  public void updateTask(Long id, TaskDto taskDto) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    if (taskOptional.isPresent()) {
      Task task = taskOptional.get();
      task = taskMapper.toEntity(taskDto);
      taskRepository.save(task);

    } else {
      throw new BadRequestException("Task not found with id: " + id);
    }
  }

  @Override
  public void deleteTask(Long id) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    if (taskOptional.isPresent()) {
      taskRepository.deleteById(id);
    } else {
      throw new BadRequestException("Task not found with id: " + id);
    }

  }
}
