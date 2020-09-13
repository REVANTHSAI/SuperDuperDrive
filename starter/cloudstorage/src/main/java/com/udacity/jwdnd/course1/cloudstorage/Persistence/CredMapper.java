package com.udacity.jwdnd.course1.cloudstorage.Persistence;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Mapper
public interface CredMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userID}")
    List<Credentials> getCredByUserID(Integer userID);

    @Select("SELECT * FROM CREDENTIALS c JOIN USERS u ON c.userid=u.userid WHERE u.username = #{userName}")
    List<Credentials> getCredByUserName(String userName);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialID}")
    Credentials getCredByCredID(Integer credentialID);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialID}")
    Integer deleteCred(Integer credentialID);

    @Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid)"+
            "VALUES(#{url},#{username},#{key},#{password},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    Integer insertCred(Credentials cred);

    @Update("UPDATE CREDENTIALS SET url=#{url},key=#{key},username=#{username},password=#{password} WHERE credentialid = #{credentialid}")
    Integer updateCred(Credentials cred);


}
