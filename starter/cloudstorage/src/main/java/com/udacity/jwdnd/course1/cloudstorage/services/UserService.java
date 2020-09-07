package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.Users;
import com.udacity.jwdnd.course1.cloudstorage.Persistence.UserMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    UserMapper userMapper;
    HashService hashService;

    public UserService(UserMapper mapper, HashService hashService) {
        this.userMapper = mapper;
        this.hashService = hashService;
    }

    public boolean isUserExist(String userName)
    {
        return (userMapper.getUser(userName)!=null);
    }

    public int insertUser(Users users)
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(users.getPassword(), encodedSalt);
        return userMapper.insertUser(new Users(null, users.getUsername(), encodedSalt, hashedPassword, users.getFirstName(), users.getLastName()));
    }

    public Users getUser(String userName)
    {
        return userMapper.getUser(userName);
    }
}
