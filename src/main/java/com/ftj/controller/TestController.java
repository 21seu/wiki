package com.ftj.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by fengtj on 2021/8/22 23:11
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello wiki";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post," + name;
    }
}
