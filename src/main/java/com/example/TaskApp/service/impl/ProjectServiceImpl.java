package com.example.TaskApp.service.impl;

import com.example.TaskApp.dto.ProjectDTO;
import com.example.TaskApp.entity.Project;
import com.example.TaskApp.entity.User;
import com.example.TaskApp.model.TaskResponseObject;
import com.example.TaskApp.model.TaskStatusCode;
import com.example.TaskApp.repository.ProjectRepository;
import com.example.TaskApp.service.ProjectService;
import com.example.TaskApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  UserService userService;

  @Override
  public Project get(Long id) {
    return projectRepository.findById(id).get();
  }

  @Override
  public TaskResponseObject<ProjectDTO> save(ProjectDTO projectDTO) {
    Project project = new Project();
    TaskResponseObject<ProjectDTO> responseObject = new TaskResponseObject<>();
    if (!isUniqueProject(projectDTO.getProject())) {
      project.setProjectName(projectDTO.getProject());
      project.setUser(userService.get(projectDTO.getUserId()));
      projectRepository.save(project);

      responseObject.setResponseObject(projectDTO);
      responseObject.setStatus(TaskStatusCode.SUCCESS);

    } else {
      responseObject.setResponseObject(projectDTO);
      responseObject.setStatus(TaskStatusCode.PROJECT_ALREADY_EXISTS);
    }
    return responseObject;
  }

  @Override
  public Project update(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public void delete(Long id) {
    projectRepository.deleteById(id);
  }

  @Override
  public Boolean isUniqueProject(String name) {
    return projectRepository.existsByProjectName(name);
  }

  @Override
  public List<Project> getAllProjectsForUser(Long userId) {
    User user = userService.get(userId);
    return projectRepository.findAllByUser(user);
  }

  @Override
  public Project findByName(String name) {
    return projectRepository.findByProjectName(name);
  }
}
