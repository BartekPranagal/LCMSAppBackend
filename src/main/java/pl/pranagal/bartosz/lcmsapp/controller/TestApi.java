package pl.pranagal.bartosz.lcmsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {

    @GetMapping("/hello")
    public String get(){
        return "Hello";
    }

    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }
    @GetMapping("/test3")
    public String test3(){
        return "test3";
    }


}
