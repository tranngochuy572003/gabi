package com.gabispa.restfulservice.service;

import com.gabispa.restfulservice.dto.TaskDto;
import com.gabispa.restfulservice.entity.Task;

import java.util.List;

public interface ITaskService {
  void addTask(Task task);
  List<Task> getAllTask();
  Task getTaskById(Long id);
  void updateTask(Long id, TaskDto taskDto);
  String deleteTask(Long id);
}
