package com.sopark.demobootweb;

import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String hello(@RequestParam("name") Person person) {
        return "hello " + person.getName();
    }

    @GetMapping("/message")
    public String message(@RequestBody String body){
        return "hello person";
    }

    @GetMapping("/jsonMessage")
    public Person jsonMessage(@RequestBody Person person){
        return person;
    }

    @GetMapping("/login")
    public Login handlerArgument(Login login){
        return login;
    }
}
