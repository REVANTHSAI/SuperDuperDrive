package com.udacity.jwdnd.course1.cloudstorage.Controller;


import com.udacity.jwdnd.course1.cloudstorage.Model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.Model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.CredServices;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredController {

    private final UserService userService;
    private final CredServices credServices;


    public CredController(UserService userService, CredServices credServices) {
        this.userService = userService;
        this.credServices = credServices;
    }

    @PostMapping
    public String postCredentials(@ModelAttribute Credentials credentials, Authentication authentication, Model model)
    {
        String updateFail = null;
        Users currentUser = userService.getUser(authentication.getName());
        if(currentUser!=null)
        {
            credentials.setUserid(currentUser.getUserId());
            Integer updateCount = credServices.insertCredentials(credentials);
            if(updateCount<1)
                model.addAttribute("updateFail",true);
            else
                model.addAttribute("updateSuccess",true);
        }
        return "result";
    }

    @GetMapping(value = "/delete")
    public String deleteCredentials(@ModelAttribute Credentials credentials, @RequestParam("id") int id, Authentication authentication, Model model)
    {
        Integer updateCount = credServices.deleteCredByID(id);
        if(updateCount<1)
            model.addAttribute("updateFail",true);
        else
            model.addAttribute("updateSuccess",true);
        return "result";
    }
}
