package com.ben.java.springboot.session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class SessionController {
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("name") String name, @RequestParam("pwd") String pwd){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user", name);
        return modelAndView;
    }
}
