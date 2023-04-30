package com.sepfort.letters.controllers;

import com.sepfort.letters.services.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public String getGreeting() {
        return "Hello, me name is messenger letters";
    }

    @GetMapping("/{id}")
    public String getGreetingById(@PathVariable Integer id) {
        System.out.println("Запрос в БД " + id);
        return greetingService.getGreetingById(id);
    }

}
