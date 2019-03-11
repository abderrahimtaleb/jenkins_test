package com.test.devops.DevOps.Project.Ctrls;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainCtrl{
    @GetMapping
    public String main(){
        return "The app is running";
    }
}
