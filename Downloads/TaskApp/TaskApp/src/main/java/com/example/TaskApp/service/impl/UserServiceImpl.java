package com.example.TaskApp.service.impl;

import com.example.TaskApp.entity.User;
import com.example.TaskApp.repository.UserRepository;
import com.example.TaskApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(String  userName) {
    	User user = new User();
    	user.setUserName(userName);
        return userRepository.save(user);
    }

    @Override
    public User update(User User) {
        return userRepository.save(User);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByUserName(name).get(0);
    }
}
