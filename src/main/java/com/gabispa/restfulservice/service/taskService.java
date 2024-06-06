package com.gabispa.restfulservice.service;

import com.gabispa.restfulservice.dto.taskDto;
import com.gabispa.restfulservice.entity.Task;

import java.util.List;

public interface taskService {
  void addTask(Task task);
  List<Task> getAllTask();
  Task getTaskById(Long id);
  void updateTask(Long id, taskDto taskDto);
  void deleteTask(Long id);
}
