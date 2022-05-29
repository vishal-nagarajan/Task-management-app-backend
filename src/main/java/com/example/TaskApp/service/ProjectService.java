package com.example.TaskApp.service;

import com.example.TaskApp.dto.ProjectDTO;
import com.example.TaskApp.entity.Project;
import com.example.TaskApp.model.TaskResponseObject;

import java.util.List;

public interface ProjectService {
    public Project get(Long id);
    public TaskResponseObject<ProjectDTO> save(ProjectDTO Project);
    public Project update(Project Project);
    public void delete(Long id);
    Boolean isUniqueProject(String name);
    List<Project> getAllProjectsForUser(Long userId);
    Project findByName(String name);
}
