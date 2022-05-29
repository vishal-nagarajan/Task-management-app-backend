package com.example.TaskApp.controller;

import com.example.TaskApp.entity.User;
import com.example.TaskApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService taskService;

    @GetMapping(value = "/{id}")
    public User get(@PathVariable(name = "id")Long id){
        return taskService.get(id);
    }

		@GetMapping(value = "/name/{name}")
		public User getByUserName(@PathVariable(name = "name") String name){
			return taskService.findByName(name);
		}

    @PostMapping
    public User save(@RequestHeader(name = "userName") String user){
        return taskService.save(user);
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(name = "id")Long id){
        taskService.delete(id);
    }

}
