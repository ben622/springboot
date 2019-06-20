package com.ben.java.springboot.thymeleaf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Value("${com.ben.java.email}")
    private String email;
    @Value("${com.ben.java.home}")
    private String home;

    @RequestMapping()
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("email", email);
        modelAndView.addObject("home", home);
        return modelAndView;
    }

}
