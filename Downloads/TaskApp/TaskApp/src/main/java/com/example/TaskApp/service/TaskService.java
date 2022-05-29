package com.example.TaskApp.service;

import com.example.TaskApp.dto.TaskDTO;
import com.example.TaskApp.dto.TentativeTodoDTO;
import com.example.TaskApp.entity.Task;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface TaskService {
    public Task get(Long id);
    public TaskDTO save(TaskDTO Task);
    public Task update(Task Task);
    public void delete(Long id);
    List<TentativeTodoDTO> getTentativeToDo(Long userId);
    Task modifyStartDate(Long taskId,String startDate);
    TaskDTO findbyTaskIdAnduserId(Long id,Long userId);
    void modifyStatus(Long taskId);
	List<TentativeTodoDTO> completedtasks(Long userId);
	List<TentativeTodoDTO> overDueTasks(Long userId);
	List<TentativeTodoDTO> taskInProgress(Long userId);
	List<TentativeTodoDTO> allTasksForAUser(Long userId);
	List<TentativeTodoDTO> yetDueTasks(Long userId);
	List<Task> allTasksForAUserForFile(Long userId);

}
