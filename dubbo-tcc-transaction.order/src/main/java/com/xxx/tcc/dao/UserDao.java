package com.xxx.tcc.dao;



import com.xxx.tcc.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "id",column = "USER_ID"),
            @Result(property = "name",column = "NAME")
    })
    User findById(Integer id) throws SQLException;

    @Insert("INSERT INTO user(NAME) VALUES(#{name})")
    @Results({
            @Result(property = "name",column = "NAME")
    })
    void insert(User user) throws SQLException;
}