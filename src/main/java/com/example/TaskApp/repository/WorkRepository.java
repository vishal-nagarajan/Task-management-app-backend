package com.example.TaskApp.repository;

import com.example.TaskApp.entity.Task;
import com.example.TaskApp.entity.Work;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends CrudRepository<Work,Long> {
	List<Work> findByTaskIn(List<Task> tasks);
}
