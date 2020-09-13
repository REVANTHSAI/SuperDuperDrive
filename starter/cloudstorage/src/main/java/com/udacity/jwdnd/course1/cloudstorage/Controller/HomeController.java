package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.Model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredServices;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NotesService notesService;
    private final UserService userService;
    private final CredServices credServices;
    private final EncryptionService encryptionService;

    public HomeController(NotesService notesService, UserService userService, CredServices credServices, EncryptionService encryptionService) {
        this.notesService = notesService;
        this.userService = userService;
        this.credServices =credServices;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHomeData(Authentication authentication,Model model)
    {
        List<Notes> notesList = notesService.getNotesByUserName(authentication.getName());
        List<Credentials> credList =  credServices.getCredByUserName(authentication.getName());
        for(Credentials cred:credList)
        {
            cred.setdPassword(encryptionService.decryptValue(cred.getPassword(),cred.getKey()));
        }
        model.addAttribute("NotesList",notesList);
        model.addAttribute("CredList",credList);
        return "home";
    }

    @PostMapping(value = "/notes")
    public String postNotes(@ModelAttribute Notes notes,Authentication authentication,Model model)
    {
        Users currentUser = userService.getUser(authentication.getName());
        if(currentUser!=null)
        {
            notes.setUserid(currentUser.getUserId());
            notesService.insertNotes(notes);
        }
        return "redirect:/home";
    }

    @PostMapping(value = "/credentials")
    public String postNotes(@ModelAttribute Credentials credentials,Authentication authentication,Model model)
    {
        Users currentUser = userService.getUser(authentication.getName());
        if(currentUser!=null)
        {
            credentials.setUserid(currentUser.getUserId());
            credServices.insertCredentials(credentials);
        }
        return "redirect:/home";
    }

    @GetMapping(value = "/notes/delete")
    public String deleteNotes(@ModelAttribute Notes notes,@RequestParam("id") int id,Authentication authentication,Model model)
    {
        notesService.deleteNotesByID(id);
        return "redirect:/home";
    }

    @GetMapping(value = "/credentials/delete")
    public String deleteNotes(@ModelAttribute Credentials credentials,@RequestParam("id") int id,Authentication authentication,Model model)
    {
        credServices.deleteCredByID(id);
        return "redirect:/home";
    }
}
