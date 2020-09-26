package com.udacity.jwdnd.course1.cloudstorage.Persistence;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.Model.Files;
import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,filedata)"+
            "VALUES(#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insetFiles(Files files);

    @Select("SELECT * FROM FILES f JOIN USERS u ON f.userid=u.userid WHERE u.username = #{userName}")
    List<Files> getFilesByUserName(String userName);

    @Select("SELECT * FROM FILES WHERE userid = #{userID}")
    List<Files> getFilesByID(Integer userID);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFilesByFilesID(Integer fileId);

    @Select("SELECT * FROM FILES WHERE filename=#{filename}")
    List<Files> getFilesByFileName(String fileName);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileID}")
    Integer deleteFiles(Integer fileID);

}
