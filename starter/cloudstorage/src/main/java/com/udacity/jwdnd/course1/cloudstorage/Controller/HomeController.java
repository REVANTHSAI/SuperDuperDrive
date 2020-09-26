package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.Model.Files;
import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.Model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final NotesService notesService;
    private final UserService userService;
    private final CredServices credServices;
    private final EncryptionService encryptionService;
    private final FileService fileService;

    public HomeController(NotesService notesService, UserService userService, CredServices credServices, EncryptionService encryptionService,FileService fileService) {
        this.notesService = notesService;
        this.userService = userService;
        this.credServices =credServices;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomeData(Authentication authentication,Model model)
    {
        List<Notes> notesList = notesService.getNotesByUserName(authentication.getName());
        List<Credentials> credList =  credServices.getCredByUserName(authentication.getName());
        List<Files> filesList = fileService.getFilesByUserName(authentication.getName());

        for(Credentials cred:credList) {
            cred.setdPassword(encryptionService.decryptValue(cred.getPassword(),cred.getKey()));
        }

        model.addAttribute("NotesList",notesList);
        model.addAttribute("CredList",credList);
        model.addAttribute("FileList",filesList);
        return "home";
    }

}
