package com.example.pandemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pandemic.entity.Cases;
import com.example.pandemic.mapper.CaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author codejie
 * @since 2022-04-27
 */
@Service
public class CaseServiceImpl implements com.example.pandemic.service.CaseService {
    @Autowired
    CaseMapper caseMapper;

    @Override
    public boolean AddCase(Cases cases) {
        //插入数据
     caseMapper.insert(cases);
        return false;
    }

    @Override
    public boolean DeleCaseByCid(long cid) {
        // 根据id删除
        caseMapper.deleteById(cid);
        return false;
    }

    @Override
    public boolean UpdateCase(Cases cases) {
        // 更新
        caseMapper.updateById(cases);
        return true;
    }

    @Override
    public List<Cases> getAllCase() {
        return caseMapper.allcases();
    }

    @Override
    public Cases FindCaseByCid(long cid) {
        // 根据cid查询
        return caseMapper.selectById(cid);
    }

    @Override
    public long GetCasesByArea(String city) {
        // 查询指定地区的确诊病例数量
        QueryWrapper<Cases> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("city",city);
        return caseMapper.selectCount(queryWrapper);
    }
}
