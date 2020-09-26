package com.udacity.jwdnd.course1.cloudstorage.Controller;


import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.Model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;
    private final UserService userService;

    public NotesController(NotesService notesService, UserService userService) {
        this.notesService = notesService;
        this.userService = userService;
    }

    @PostMapping
    public String postNotes(@ModelAttribute Notes notes, Authentication authentication, Model model)
    {
        Users currentUser = userService.getUser(authentication.getName());
        if(currentUser!=null)
        {
            notes.setUserid(currentUser.getUserId());
            Integer updateCount = notesService.insertNotes(notes);
            if(updateCount<1)
                model.addAttribute("updateFail",true);
            else
                model.addAttribute("updateSuccess",true);
        }
        return "result";
    }

    @GetMapping(value = "/delete")
    public String deleteNotes(@ModelAttribute Notes notes, @RequestParam("id") int id, Authentication authentication, Model model)
    {
        Integer updateCount = notesService.deleteNotesByID(id);
        if(updateCount<1)
            model.addAttribute("updateFail",true);
        else
            model.addAttribute("updateSuccess",true);
        return "result";
    }
}
