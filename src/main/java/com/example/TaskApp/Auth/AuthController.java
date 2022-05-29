package com.example.TaskApp.Auth;

import com.example.TaskApp.Auth.Util.JWTUtil;
import com.example.TaskApp.Auth.model.LoginCredentials;
import com.example.TaskApp.Auth.model.AuthUser;
import com.example.TaskApp.Auth.repository.UserRepo;
import com.example.TaskApp.model.TaskResponseObject;
import com.example.TaskApp.model.TaskStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired private UserRepo userRepo;
  @Autowired private JWTUtil jwtUtil;
  @Autowired private AuthenticationManager authManager;
  @Autowired
  private PasswordEncoder passwordEncoder;


  @PostMapping("/register")
  public TaskResponseObject<Map<String, Object>> registerHandler(@RequestBody AuthUser user){
    String encodedPass = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPass);
    user = userRepo.save(user);
    String token = jwtUtil.generateToken(user.getEmail());
		TaskResponseObject<Map<String, Object>> response =
			new TaskResponseObject<>(Collections.singletonMap("jwt-token", token),
				TaskStatusCode.SUCCESS);



    return response;
  }

  @PostMapping("/login")
  public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
    try {
      UsernamePasswordAuthenticationToken authInputToken =
          new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

      authManager.authenticate(authInputToken);

      String token = jwtUtil.generateToken(body.getEmail());

      return Collections.singletonMap("jwt-token", token);
    }catch (Exception authExc){
      throw new RuntimeException("Invalid Login Credentials");
    }
  }


}
