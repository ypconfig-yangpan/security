package com.cancer.security.demo.service;


import com.cancer.security.demo.entity.User;

/**
 * 
 */
public interface UserService {
    User getUserByUsername();

    void register(User user);
}
