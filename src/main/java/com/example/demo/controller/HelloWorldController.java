package com.example.demo.controller;

import com.example.demo.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path="/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path="/hello-world")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path="/hello-world/path-variable/{info}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String info) {
        return new HelloWorldBean("Hello World", info);
    }
}
