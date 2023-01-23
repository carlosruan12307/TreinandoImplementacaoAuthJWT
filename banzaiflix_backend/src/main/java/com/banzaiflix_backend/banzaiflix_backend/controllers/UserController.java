package com.banzaiflix_backend.banzaiflix_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @GetMapping(value = "/")
    public String inicio(){
        return "bem vindo a api de usuarios";
    }
}
