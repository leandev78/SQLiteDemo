package com.miempresa.tiendaonline.tiendaonline.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping("/")
    public String Home(){
        
        return "Bienvenido a mi Home!!!";
    }

}
