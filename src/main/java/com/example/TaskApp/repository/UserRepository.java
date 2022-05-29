package com.example.TaskApp.repository;

import com.example.TaskApp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
  List<User> findByUserName(String name);
}
