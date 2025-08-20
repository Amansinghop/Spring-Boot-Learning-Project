package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Controller {

    @GetMapping("go")
    public String getMethodName(@RequestParam int a, @RequestParam int b) {
        int result = a + b;
        return "Result " + result;
    }

}
