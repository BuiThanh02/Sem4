package com.t2010a.demospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/hello")
public class HelloController {
    @RequestMapping(path = "world", method = RequestMethod.GET)
    public String say(){
        return "Hello World";
    }
    @RequestMapping(path = "talk", method = RequestMethod.GET)
    public String talk(){
        return "talk to World";
    }
}
