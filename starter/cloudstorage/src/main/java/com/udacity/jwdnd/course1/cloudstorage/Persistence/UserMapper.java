package com.udacity.jwdnd.course1.cloudstorage.Persistence;

import com.udacity.jwdnd.course1.cloudstorage.Model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    Users getUser(String userName);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) "+
            "VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer insertUser(Users users);
}
