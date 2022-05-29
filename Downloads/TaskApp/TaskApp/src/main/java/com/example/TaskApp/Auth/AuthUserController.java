package com.example.TaskApp.Auth;

import com.example.TaskApp.Auth.model.AuthUser;
import com.example.TaskApp.Auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class AuthUserController {

  @Autowired
  private UserRepo userRepo;

  @GetMapping("/info")
  public AuthUser getUserDetails(){
    String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userRepo.findByEmail(email).get(0);
  }


}