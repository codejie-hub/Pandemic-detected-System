package com.example.pandemic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * @author codejie
 * @since 2022-04-27
 */
@TableName(value = "user")
@Data
public class User {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
