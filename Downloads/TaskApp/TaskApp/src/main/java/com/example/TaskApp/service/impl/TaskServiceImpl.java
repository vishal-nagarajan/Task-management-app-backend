package com.example.TaskApp.service.impl;

import com.example.TaskApp.dto.TaskDTO;
import com.example.TaskApp.dto.TentativeTodoDTO;
import com.example.TaskApp.entity.Client;
import com.example.TaskApp.entity.Project;
import com.example.TaskApp.entity.Task;
import com.example.TaskApp.entity.User;
import com.example.TaskApp.entity.Work;
import com.example.TaskApp.repository.TaskRepository;
import com.example.TaskApp.repository.WorkRepository;
import com.example.TaskApp.service.ClientService;
import com.example.TaskApp.service.ProjectService;
import com.example.TaskApp.service.TaskService;
import com.example.TaskApp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ClientService clientService;

    @Autowired
		WorkRepository workRepository;

    @Override
    public Task get(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        Task task = new Task();
        User user = userService.get(taskDTO.getUserId());
        Project project = projectService.get(taskDTO.getProject());
        Client client = clientService.get(taskDTO.getClient());
        BeanUtils.copyProperties(taskDTO,task);
        task.setClient(client);
        task.setProject(project);
        task.setUser(user);
        task.setTaskStatus(true);
        BeanUtils.copyProperties(taskRepository.save(task),taskDTO);
        return taskDTO;
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TentativeTodoDTO> getTentativeToDo(Long userId) {
        User user = userService.get(userId);
			List<Task> tasks = taskRepository
				.findByUserAndTaskStatusAndStartDateBefore(user, Boolean.TRUE,
					new Timestamp(System.currentTimeMillis()));
			return listReturnUtil(tasks);
    }

	@Override
	public Task modifyStartDate(Long taskId, String startDateString) {
		Task taskToSave = taskRepository.findById(taskId).get();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		try {
			startDate = formatter.parse(startDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		taskToSave.setStartDate(startDate);
		return taskRepository.save(taskToSave);
	}

	@Override
	public TaskDTO findbyTaskIdAnduserId(Long id, Long userId) {
		User user = userService.get(userId);
		Task task = taskRepository.findByIdAndUser(id,user);
		TaskDTO taskDTO = new TaskDTO();
		BeanUtils.copyProperties(task,taskDTO);
		taskDTO.setClient(task.getClient().getId());
		taskDTO.setProject(task.getProject().getId());
		taskDTO.setUserId(task.getUser().getId());
		return taskDTO;

	}

	@Override
	public void modifyStatus(Long taskId) {
		Task task = taskRepository.findById(taskId).get();
		task.setTaskStatus(Boolean.FALSE);
		taskRepository.save(task);
	}

	public List<TentativeTodoDTO> completedtasks(Long userId){
		User user = userService.get(userId);
		List<Task> tasks = taskRepository
			.findByUserAndTaskStatus(user, Boolean.FALSE);
		return listReturnUtil(tasks);
	}


	public List<TentativeTodoDTO> overDueTasks(Long userId){
		User user = userService.get(userId);
		List<Task> tasks = taskRepository.findByUserAndTaskStatusAndEndDateBefore(user, Boolean.TRUE,
			new Timestamp(System.currentTimeMillis()));
		return listReturnUtil(tasks);
	}

	public List<TentativeTodoDTO> yetDueTasks(Long userId){
		User user = userService.get(userId);
		List<Task> tasks = taskRepository.findByUserAndTaskStatusAndEndDateAfter(user, Boolean.TRUE,
			new Timestamp(System.currentTimeMillis()));
		return listReturnUtil(tasks);
	}

	//list of task for a given user not completed and is in worklog
	public List<TentativeTodoDTO> taskInProgress(Long userId) {
		User user = userService.get(userId);
		List<Task> tasks = taskRepository.findByUserAndTaskStatus(user, Boolean.TRUE);
		List<Work> works = workRepository.findByTaskIn(tasks);
		if (!CollectionUtils.isEmpty(works)) {
			return listReturnUtil(
				new ArrayList<>(works.stream().map(Work::getTask).collect(Collectors.toSet())));
		}
		return new ArrayList<>();
	}


	public List<TentativeTodoDTO> allTasksForAUser(Long userId){
		User user = userService.get(userId);
		List<Task> tasks = taskRepository.findByUser(user);
		return listReturnUtil(tasks);
	}

	public List<Task> allTasksForAUserForFile(Long userId){
		User user = userService.get(userId);
		return taskRepository.findByUser(user);
	}
	private List<TentativeTodoDTO> listReturnUtil(List<Task> tasks){
		List<TentativeTodoDTO>  tentativeTodoDTOs= new ArrayList<>();
		for (Task task : tasks) {
			TentativeTodoDTO tentativeTodoDTO =
				TentativeTodoDTO.builder().taskId(task.getId()).taskName(task.getTitle()).build();
			tentativeTodoDTOs.add(tentativeTodoDTO);
		}
		return tentativeTodoDTOs;
	}

}
