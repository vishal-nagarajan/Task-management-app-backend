package com.example.TaskApp.Auth.repository;


import com.example.TaskApp.Auth.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<AuthUser, Long> {
  List<AuthUser> findByEmail(String email);
}