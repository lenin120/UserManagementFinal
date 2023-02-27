package com.javaCurse.curse.controllers;

import com.javaCurse.curse.Utils.JWTUtil;
import com.javaCurse.curse.dao.UserDAO;
import com.javaCurse.curse.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private UserDAO userDao;
    @Autowired
    private JWTUtil jwtUtil;
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        User userCredentials = userDao.getUserByCredentials(user);
        if(userCredentials != null){
            return jwtUtil.create(String.valueOf(userCredentials.getId()), userCredentials.getEmail());
        }
        return "FAIL";
    }
}
