package com.gabispa.restfulservice.mapper;

import com.gabispa.restfulservice.dto.taskDto;
import com.gabispa.restfulservice.entity.Task;

public class taskMapper {
  public static taskDto toDto(Task task) {
    taskDto taskDto = new taskDto();
    taskDto.setDescription(task.getDescription());
    taskDto.setTimeWork(task.getTimeWork());
    taskDto.setState(task.getState());
    taskDto.setType(task.getType());
    taskDto.setName(task.getName());
    return taskDto;

  }

  public static Task toEntity(taskDto taskDto) {
    Task task = new Task();
    task.setDescription(taskDto.getDescription());
    task.setTimeWork(taskDto.getTimeWork());
    task.setState(taskDto.getState());
    task.setType(taskDto.getType());
    task.setName(taskDto.getName());
    return task;
  }
}
