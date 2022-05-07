package com.example.pandemic.service;

import com.example.pandemic.entity.Cases;

import java.util.List;
/**
 * @author codejie
 * @since 2022-04-27
 */
public interface CaseService {
    // 添加病例
    boolean AddCase(Cases cases);
    // 删除指定病例
    boolean DeleCaseByCid(long cid);
    // 更新病例
    boolean UpdateCase(Cases cases);
    // 获取所有病例信息
    List<Cases>getAllCase();
    // 获取指定病例信息
    Cases FindCaseByCid(long cid);
    // 获取指定地区的病例
    long GetCasesByArea(String city);
}
