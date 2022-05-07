package com.example.pandemic.service.impl;

import com.example.pandemic.entity.User;
import com.example.pandemic.mapper.UserMapper;
import com.example.pandemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author codejie
 * @since 2022-04-27
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User findUser(String name,String password) {
      return   userMapper.finduser(name,password);
    }
}
