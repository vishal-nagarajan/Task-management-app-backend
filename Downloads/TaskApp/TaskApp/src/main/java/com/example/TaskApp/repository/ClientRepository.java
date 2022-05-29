package com.example.TaskApp.repository;

import com.example.TaskApp.entity.Client;
import com.example.TaskApp.entity.Project;
import com.example.TaskApp.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {
  Boolean existsByClientName(String name);
  List<Client> findAllByUser(User user);
  Client findByClientName(String name);
}
