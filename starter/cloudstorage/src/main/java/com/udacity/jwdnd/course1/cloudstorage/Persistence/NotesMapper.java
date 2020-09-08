package com.udacity.jwdnd.course1.cloudstorage.Persistence;

import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.*;


@Mapper
public interface NotesMapper {

     @Select("SELECT * FROM NOTES WHERE userid = #{userID}")
     List<Notes> getNotesByUserID(Integer userID);

    @Select("SELECT * FROM NOTES n JOIN USERS u ON n.userid=u.userid WHERE username = #{userName}")
    List<Notes> getNotesByUserName(String userName);


    @Select("SELECT * FROM NOTES WHERE noteid = #{noteID}")
    Notes getNotesByNoteID(Integer noteID);

     @Delete("DELETE * FROM NOTES WHERE noteid = #{noteID}")
     Integer deleteNotes(Integer noteID);

     @Insert("INSERT INTO NOTES (notetitle,notedescription,userid)"+
             "VALUES(#{notetitle},#{notedescription},#{userid})")
     @Options(useGeneratedKeys = true, keyProperty = "noteid")
     Integer insertNotes(Notes notes);

}
