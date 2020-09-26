package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.Model.Files;
import com.udacity.jwdnd.course1.cloudstorage.Persistence.FilesMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class FileService {

    FilesMapper filesMapper;

    public FileService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public int insertFile(Files file) {
        return filesMapper.insetFiles(file);
    }

    public List<Files> getFilesByUserName(String userName) {
        return filesMapper.getFilesByUserName(userName);
    }

    public int deleteFileByID(Integer filedID){
        return filesMapper.deleteFiles(filedID);
    }

    public boolean isFileNamePresent(String fileName){
        return filesMapper.getFilesByFileName(fileName).size()>0;
    }

    public Files getFilesByFileID(Integer fileID){
        return filesMapper.getFilesByFilesID(fileID);
    }

}
