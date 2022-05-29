package com.example.TaskApp.repository;

import com.example.TaskApp.entity.Project;
import com.example.TaskApp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
  Boolean existsByProjectName(String name);
  List<Project> findAllByUser(User user);
  Project findByProjectName(String name);
}
