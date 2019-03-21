package com.sopark.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

    @GetMapping("/sample")
    public String simple(){
        return "sample";
    }
}
