package com.example.TaskApp.repository;

import com.example.TaskApp.entity.Task;
import com.example.TaskApp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
  List<Task> findByUser(User userId);
  Task findByIdAndUser(Long id,User user);
  List<Task> findByUserAndTaskStatusAndStartDateBefore(User user,Boolean taskStatus,Timestamp currentDate);
	List<Task> findByUserAndTaskStatus(User user,Boolean taskStatus);
	List<Task> findByUserAndTaskStatusAndEndDateBefore(User user,Boolean taskStatus,Timestamp currentDate);
	List<Task> findByUserAndTaskStatusAndEndDateAfter(User user,Boolean taskStatus,Timestamp currentDate);

}
