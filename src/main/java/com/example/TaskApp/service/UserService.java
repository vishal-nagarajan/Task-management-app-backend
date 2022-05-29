package com.example.TaskApp.service;

import com.example.TaskApp.entity.User;

public interface UserService {
    public User get(Long id);
    public User save(String userName);
    public User update(User User);
    public void delete(Long id);
    User findByName(String name);
}
