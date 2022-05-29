package com.example.TaskApp.controller;

import com.example.TaskApp.dto.TaskDTO;
import com.example.TaskApp.dto.TentativeTodoDTO;
import com.example.TaskApp.entity.Task;
import com.example.TaskApp.service.TaskService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
  @Autowired
  TaskService taskService;

  @GetMapping(value = "/{id}")
  public Task get(@PathVariable(name = "id") Long id) {
    return taskService.get(id);
  }

	@GetMapping(value = "/task/{id}")
	public TaskDTO getTaskByIdAndUser(@PathVariable(name = "id") Long id,
		@RequestHeader(value = "userId") Long userId) {
		return taskService.findbyTaskIdAnduserId(id, userId);
	}

  @PostMapping
  public TaskDTO save(@RequestBody TaskDTO task) {
    return taskService.save(task);
  }

  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable(name = "id") Long id) {
    taskService.delete(id);
  }


  @GetMapping(value = "/tentative-todo")
  public List<TentativeTodoDTO> getTentativeToDo(@RequestHeader(name = "userId") Long userId) {
    return taskService.getTentativeToDo(userId);

  }

	@GetMapping(value = "/completed")
	public List<TentativeTodoDTO> getCompletedTask(@RequestHeader(name = "userId") Long userId) {
		return taskService.completedtasks(userId);
	}

	@GetMapping(value = "/task-in-progress")
	public List<TentativeTodoDTO> getProgressTasks(@RequestHeader(name = "userId") Long userId) {
		return taskService.taskInProgress(userId);
	}

	@GetMapping(value = "/task-over-due")
	public List<TentativeTodoDTO> getOverDueTasks(@RequestHeader(name = "userId") Long userId) {
		return taskService.overDueTasks(userId);
	}

	@GetMapping(value = "/task-yet-to-be-due")
	public List<TentativeTodoDTO> getyetToBeDueTasks(@RequestHeader(name = "userId") Long userId) {
		return taskService.yetDueTasks(userId);
	}

	@GetMapping(value = "/all")
	public List<TentativeTodoDTO> getAllTasks(@RequestHeader(name = "userId") Long userId) {
		return taskService.allTasksForAUser(userId);
	}



	@PutMapping(value = "update/start-date")
	public void modifyStartDate(@RequestHeader(name = "taskId") Long taskId,
		@RequestHeader(name = "startDate") String startDateString) {

		taskService.modifyStartDate(taskId, startDateString);
	}

	@PutMapping(value = "update/status")
	public void makeTaskCompleted(@RequestHeader(name = "taskId") Long taskId) {
		taskService.modifyStatus(taskId);
	}
}
