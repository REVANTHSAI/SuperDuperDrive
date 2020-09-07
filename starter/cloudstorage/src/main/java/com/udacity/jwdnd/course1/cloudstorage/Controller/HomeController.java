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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    NotesService notesService;
    UserService userService;

    @GetMapping
    public String getHomeMapping(Authentication authentication,Model model)
    {
          Users currentUser = userService.getUser("rsr361");
//        if(currentUser!=null)
//        {
//            List<Notes> notesList = notesService.getNotesByUserID(currentUser.getUserId());
//            model.addAttribute("NotesList",notesList);
//        }
        return "home";
    }
}
