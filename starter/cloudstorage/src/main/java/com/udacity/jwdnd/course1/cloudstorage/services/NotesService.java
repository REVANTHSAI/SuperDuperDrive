package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.Persistence.NotesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NotesService {
    NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public int insertNotes(Notes notes) {
        if(this.isNotesPresent(notes.getNoteid())) {
            return notesMapper.updateNotes(notes);
        }
        else{
            return notesMapper.insertNotes(notes);
        }
    }

    public List<Notes> getNotesByUserID(Integer userID) {
        return notesMapper.getNotesByUserID(userID);
    }

    public List<Notes> getNotesByUserName(String userName) {
        return notesMapper.getNotesByUserName(userName);
    }

    public int deleteNotesByID(Integer notesID) {
        return notesMapper.deleteNotes(notesID);
    }

    public Notes getNotesByID(Integer notesID){
        return notesMapper.getNotesByNoteID(notesID);
    }

    public boolean isNotesPresent(Integer notesID)
    {
        System.out.println("boolean Value "+notesID+ "Bool" +Objects.nonNull(this.getNotesByID(notesID)));
        return Objects.nonNull(this.getNotesByID(notesID));
    }
}
