package com.udacity.jwdnd.course1.cloudstorage.Controller;


import com.udacity.jwdnd.course1.cloudstorage.Model.Files;
import com.udacity.jwdnd.course1.cloudstorage.Model.Users;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {
    private final UserService userService;
    private final FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping
    public String postFiles(@RequestParam(value = "fileName") MultipartFile multipartFile, Authentication authentication, Model model) throws IOException {
        String updateError =  null;

        Users currentUser = userService.getUser(authentication.getName());
        byte fileData[] = multipartFile.getBytes();
        String fileName = multipartFile.getOriginalFilename();
        if(fileService.isFileNamePresent(fileName)) {
                updateError = "File with this name is already present";
                model.addAttribute("updateError",updateError);
            }
        else{
                int resultCount = fileService.insertFile(new Files(null, fileName, multipartFile.getContentType(), multipartFile.getSize(), currentUser.getUserId(), fileData));
                if(resultCount<1)
                    model.addAttribute("updateFail",true);
                else
                    model.addAttribute("updateSuccess",true);
            }
        return "result";
    }


    @GetMapping("/download")
    public ResponseEntity fileDownload(@RequestParam Integer fileID){
        Files newFile = fileService.getFilesByFileID(fileID);
        ByteArrayResource resource = new ByteArrayResource(newFile.getFiledata());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(newFile.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + newFile.getFilename() + "\"").body(resource);
    }

    @GetMapping(value = "/delete")
    public String deleteFiles(@RequestParam("id") int id)
    {
        fileService.deleteFileByID(id);
        return "redirect:/home";
    }

}
