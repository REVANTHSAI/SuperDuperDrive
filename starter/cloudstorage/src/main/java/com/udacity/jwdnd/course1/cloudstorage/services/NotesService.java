package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.Persistence.NotesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public int insertNotes(Notes notes) {
        return notesMapper.insertNotes(notes);
    }

    public List<Notes> getNotesByUserID(Integer userID) {
        return notesMapper.getNotesByUserID(userID);
    }

    public int deleteNotesByID(Integer notesID) {
        return notesMapper.deleteNotes(notesID);
    }

    public Notes getNotesByID(Integer notesID){
        return notesMapper.getNotesByNoteID(notesID);
    }
}
