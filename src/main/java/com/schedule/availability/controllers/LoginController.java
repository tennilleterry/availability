package com.schedule.availability.controllers;

import com.schedule.availability.models.User;
import com.schedule.availability.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Errors;



@Controller
@RequestMapping(value = "user")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="login", method = RequestMethod.GET)
    public String displayUserLoginForm(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("previousUsername", "");
        return "user/login";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String processUserLoginForm(Model model,
                                       String userName,
                                       String password){
        if(userDao.existsByName(userName)){
            User logIn = userDao.findByName(userName);
            if(password.equals(logIn.getPassword())){
                return "redirect:/availability/add";
            } else {
                model.addAttribute("title", "Login");
                model.addAttribute("previousUsername", userName);
                model.addAttribute("passwordError", "Incorrect password");
                return "user/login";
            }
        } else {
            model.addAttribute("title", "Login");
            model.addAttribute("usernameError", "That user does not exist.");
            return "user/login";
        }
    }
}