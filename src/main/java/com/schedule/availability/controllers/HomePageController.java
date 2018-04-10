package com.schedule.availability.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value = "")
public class HomePageController {
    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Home Page");
        return "index";
    }

}
