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

import javax.validation.Valid;

@Controller

@RequestMapping(value = "availability")

public class AvailabilityController {

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private UserDao userDao;



    @RequestMapping(value="list")
    public String index(Model model){
        model.addAttribute("availabilities", availabilityDao.findAll());
        model.addAttribute("title", "All Availability");
        return "availability/list";

    }



    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddAvailabilityForm(Model model){
        model.addAttribute("title", "Add Availability");
        model.addAttribute(new Availability());
        model.addAttribute("users", userDao.findAll());
        return "availability/add";

    }



    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddAvailabilityForm(@ModelAttribute @Valid Availability newAvailability,
                                             Errors errors,
                                             @RequestParam int userId,
                                             Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add availability");
            return "availability/add";

        }

        User user = userDao.findOne(userId);
        newAvailability.setUser(user);
        availabilityDao.save(newAvailability);
        return "redirect:/availability/list";

    }



    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveAvailabilityForm(Model model){
        model.addAttribute("availabilities", availabilityDao.findAll());
        model.addAttribute("title", "Remove availability");
        return "availability/remove";

    }



 //   @RequestMapping(value = "remove", method = RequestMethod.POST)
 //   public String processRemoveAvailabilityForm(@RequestParam int[] availabilityIds){
  //      for(int availabilityId : availabilityIds){
   //         availabilityDao.delete(availabilityId);

 //       }
 //       return "redirect:/availability/list";

 //   }



}