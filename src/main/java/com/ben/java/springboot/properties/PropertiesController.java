package com.ben.java.springboot.properties;

import com.ben.java.springboot.configure.DatabaseConfig;
import com.ben.java.springboot.configure.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    @Value("${com.ben.java.email}")
    private String email;
    @Value("${com.ben.java.home}")
    private String home;

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private DatabaseConfig databaseConfig;

    @GetMapping("/annotation/value")
    public String getCustomAnnotatonValueModule(){
        return "email:" + email + ",home:" + home;
    }

    @GetMapping("/javaben/value")
    public String getCustomJavabenValueModule(){
        return userConfig.toString();
    }

    @GetMapping("/readDBConfig")
    public String readDBConfig(){
        return databaseConfig.toString();
    }

}
