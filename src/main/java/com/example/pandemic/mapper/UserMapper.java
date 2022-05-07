package com.example.pandemic.mapper;


import com.example.pandemic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * @author codejie
 * @since 2022-04-27
 */
@Mapper
public interface UserMapper {
    //     查找用户
    @Select("Select * from user where username =#{name} and password=#{password}")
    User finduser(@Param("name") String name, @Param("password") String password);
}
