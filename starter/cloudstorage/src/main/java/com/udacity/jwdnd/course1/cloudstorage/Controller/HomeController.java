package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.Model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NotesService notesService;
    private final UserService userService;

    public HomeController(NotesService notesService, UserService userService) {
        this.notesService = notesService;
        this.userService = userService;
    }

    @GetMapping
    public String getHome(@ModelAttribute Notes notes, Authentication authentication,Model model)
    {
        List<Notes> notesList = notesService.getNotesByUserName(authentication.getName());
        model.addAttribute("NotesList",notesList);
        return "home";
    }

    @PostMapping
    public String postHome(@ModelAttribute Notes notes,Authentication authentication,Model model)
    {
        Users currentUser = userService.getUser(authentication.getName());
        if(currentUser!=null)
        {
            notes.setUserid(currentUser.getUserId());
            notesService.insertNotes(notes);
            List<Notes> notesList = notesService.getNotesByUserName(authentication.getName());
            model.addAttribute("NotesList",notesList);
        }
        return "home";
    }
}
