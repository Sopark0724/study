package com.sopark.demobootweb;

import org.springframework.web.bind.annotation.*;

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
}
