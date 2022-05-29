package com.example.TaskApp.controller;

import com.example.TaskApp.dto.ProjectDTO;
import com.example.TaskApp.entity.Project;
import com.example.TaskApp.entity.Task;
import com.example.TaskApp.model.TaskResponseObject;
import com.example.TaskApp.model.TaskStatusCode;
import com.example.TaskApp.service.ProjectService;
import com.example.TaskApp.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {
  @Autowired
  ProjectService projectService;

  @GetMapping(value = "/{id}")
  public Project get(@PathVariable(name = "id") Long id) {
    return projectService.get(id);
  }

  @PostMapping
  public TaskResponseObject<ProjectDTO> save(@RequestBody ProjectDTO projectDTO) {
    return projectService.save(projectDTO);
  }

  @GetMapping("/list")
  public TaskResponseObject<List<ProjectDTO>> getAllProjectsForUser(@RequestHeader(name = "UserId") Long userId) {
    List<ProjectDTO> projectDTOList = new ArrayList<>();
    List<Project> projectList =projectService.getAllProjectsForUser(userId);
    for(Project project:projectList){
      ProjectDTO projectDTO = new ProjectDTO();
      projectDTO.setProject(project.getProjectName());
      projectDTO.setProjectId(project.getId());
      projectDTOList.add(projectDTO);
    }
    return new TaskResponseObject<>(projectDTOList,TaskStatusCode.SUCCESS);
  }


  //    @DeleteMapping(value = "/{id}")
  //    public void delete(@PathVariable(name = "id")Long id){
  //        clientService.delete(id);
  //    }

}
