package com.javaCurse.curse.controllers;

import com.javaCurse.curse.Utils.JWTUtil;
import com.javaCurse.curse.dao.UserDAO;
import com.javaCurse.curse.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController  {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private JWTUtil jwtUtil;


    private boolean validateToken(String token){

        String userID = jwtUtil.getKey(token);
        return userID != null;

    }
    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Lenin");
        user.setLastName("Anguisaca");
        user.setEmail("lenin.anguisaca@ucuenca.edu.ec");
        user.setPhoneNumber("0963946261");
        user.setPassword("lenin0207b");
        return user;
    }
    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsersList(@RequestHeader (value="Authorization") String token ) {
        if(!validateToken(token)) return null;
        return userDao.getUsersList();
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void register(@RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.register(user);
    }



    @RequestMapping(value = "user/21")
    public User editUser() {
        User user = new User();
        user.setName("Lenin");
        user.setLastName("Anguisaca");
        user.setEmail("lenin.anguisaca@ucuenca.edu.ec");
        user.setPhoneNumber("0963946261");
        user.setPassword("lenin0207b");
        return user;
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader (value="Authorization") String token , @PathVariable Long id) {
        if(!validateToken(token)) return;
        userDao.delete(id);
    }




}
