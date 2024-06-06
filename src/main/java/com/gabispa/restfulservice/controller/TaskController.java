package com.gabispa.restfulservice.controller;

import com.gabispa.restfulservice.dto.TaskDto;
import com.gabispa.restfulservice.entity.Task;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
  private final com.gabispa.restfulservice.service.impl.taskService taskService;

  @Autowired
  public TaskController(com.gabispa.restfulservice.service.impl.taskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/add")
  public String add(@RequestBody Task task) {
    try {
      taskService.addTask(task);
      return "add task success";

    } catch (Exception e) {
      return "error";
    }

  }


  @PatchMapping("/update/{id}")
  public String update(@PathVariable Long id, @RequestBody TaskDto taskDto) {
    try {
      taskService.updateTask(id, taskDto);
      return "update task success";

    } catch (EntityNotFoundException e) {
      return "error";
    }
  }

  @GetMapping()
  public List<Task> getTaskList() {
    try {
      return taskService.getAllTask();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }

  }


  @GetMapping("get/{id}")
  public Task getTaskById(@PathVariable Long id) {
    try {
      return taskService.getTaskById(id);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }

  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
   taskService.deleteTask(id);
    return new ResponseEntity<>("Delete task success", HttpStatus.OK);
  }
}
