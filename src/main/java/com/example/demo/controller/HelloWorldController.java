package com.example.demo.controller;

import com.example.demo.bean.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @GetMapping(path="/hello-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("good.morning.message", null, "Default Message", locale);
        return message;
    }
}
