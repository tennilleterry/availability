package com.schedule.availability.controllers;

import com.schedule.availability.models.Availability;
import com.schedule.availability.models.User;
import com.schedule.availability.models.data.AvailabilityDao;
import com.schedule.availability.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import java.util.List;

@Controller

@RequestMapping(value = "availability")

public class AvailabilityController {

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "list")
    public String index(Model model) {
        model.addAttribute("availabilities", availabilityDao.findAll());
        model.addAttribute("title", "Availability");
        return "availability/list";

    }




    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddAvailabilityForm(Model model){
        model.addAttribute("title", "Add Availability");
        model.addAttribute(new Availability());
        model.addAttribute("users", userDao.findAll());
       // model.addAttribute("users", anotheruser.getName());

        return "availability/add";

    }






    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddAvailabilityForm(@ModelAttribute @Valid Availability newAvailability,
                                             Errors errors,
                                             @RequestParam int userId,
                                             Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add availability");
            model.addAttribute("user", userDao.findAll());
            return "availability/add";

        }

        //User user = userDao.findOne(userId);
        //List<Availability> availabilities = user.getAvailabilities();
        //model.addAttribute("availabilities", availabilities);
       // model.addAttribute("title", user.getName());
        //return "redirect:/availability/list";


        User user = userDao.findOne(userId);
        newAvailability.setUser(user);
        availabilityDao.save(newAvailability);
        return "redirect:/availability/list";

        //Category cat = categoryDao.findOne(id);
        //List<Cheese> cheeses = cat.getCheeses();
        //model.addAttribute("cheeses", cheeses);
        //model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        //return "cheese/index";

    }



    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveAvailabilityForm(Model model){

        model.addAttribute("availabilities", availabilityDao.findAll());
        model.addAttribute("title", "Remove availability");
        return "availability/remove";

    }





    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveAvailabilityForm(@RequestParam int[] availabilityIds){
        for(int availabilityId : availabilityIds){
            availabilityDao.delete(availabilityId);

        }
        return "redirect:/availability/list";

    }


}
