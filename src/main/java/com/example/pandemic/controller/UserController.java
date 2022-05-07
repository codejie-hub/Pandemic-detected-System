package com.example.pandemic.controller;

import com.example.pandemic.entity.User;
import com.example.pandemic.mapper.UserMapper;
import com.example.pandemic.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codejie
 * @since 2022-04-25
 */
//处理登录请求
@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @PostMapping(value = "/login")
    public Result<User> handleLogin(@RequestBody User user){
        if(user==null) {
            System.out.print("user转换失败");
            return Result.error(500,"登录失败");
        }
        User check=userMapper.finduser(user.getUsername(),user.getPassword());
        if(check!=null) {
            String token="sdSSGDHASGDASD";
            return Result.success(200, "登录成功", user,token);
        }
        else{
            return Result.error(500,"登录失败");}
    }
}
