package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.Model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.Persistence.CredMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class CredServices {
    private CredMapper credMapper;
    private EncryptionService encryptionService;

    public CredServices(CredMapper credMapper, EncryptionService encryptionService) {
        this.credMapper = credMapper;
        this.encryptionService = encryptionService;
    }

    public Credentials getCredByCredID(Integer credID) {
       Credentials credentials = credMapper.getCredByCredID(credID);
       if(isCredPresent(credID)) {
           String pass = credentials.getPassword();
           String key = credentials.getKey();
           String decPass = encryptionService.decryptValue(pass, key);
           credentials.setPassword(decPass);
           credentials.setdPassword(pass);
       }
       return credentials;
    }
    public List<Credentials> getCredByUserName(String userName)
    {
        return credMapper.getCredByUserName(userName);
    }

    public int updatedCred(Credentials cred){
        return credMapper.updateCred(cred);
    }


    public int insertCredentials(Credentials credentials){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        if(this.isCredPresent(credentials.getCredentialid())){
            String encodedPassword = encryptionService.encryptValue(credentials.getPassword(),encodedKey);
            credentials.setKey(encodedKey);
            credentials.setPassword(encodedPassword);
            return this.updatedCred(credentials);
        }
        else{
            String encodedPassword = encryptionService.encryptValue(credentials.getPassword(),encodedKey);
            credentials.setKey(encodedKey);
            credentials.setPassword(encodedPassword);
            return credMapper.insertCred(credentials);
        }
    }

    public boolean isCredPresent(Integer credID)
    {
        System.out.println("Inside IsCredPresent"+ credMapper.getCredByCredID(credID));
        return Objects.nonNull(credMapper.getCredByCredID(credID));
    }

    public Integer deleteCredByID(Integer credID){
        return credMapper.deleteCred(credID);
    }
}
