package com.example.TaskApp.Auth.service;

import com.example.TaskApp.Auth.model.AuthUser;
import com.example.TaskApp.Auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    List<AuthUser> userRes = userRepo.findByEmail(email);
    if(userRes.isEmpty())
      throw new UsernameNotFoundException("Could not findUser with email = " + email);
    AuthUser user = userRes.get(0);
    return new org.springframework.security.core.userdetails.User(
        email,
        user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
  }
}
