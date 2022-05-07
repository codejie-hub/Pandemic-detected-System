package com.example.pandemic.service;

import com.example.pandemic.entity.User;
/**
 * @author codejie
 * @since 2022-04-27
 */
public interface UserService {
       User findUser(String name,String password);
}
