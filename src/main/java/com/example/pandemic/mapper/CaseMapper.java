package com.example.pandemic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pandemic.entity.Cases;
import org.apache.ibatis.annotations.*;

import java.util.List;
/**
 * @author codejie
 * @since 2022-04-27
 */
@Mapper
public interface CaseMapper extends BaseMapper<Cases> {
    //返回所有确诊病例
    @Select("SELECT * FROM cases")
    List<Cases>allcases();
}
