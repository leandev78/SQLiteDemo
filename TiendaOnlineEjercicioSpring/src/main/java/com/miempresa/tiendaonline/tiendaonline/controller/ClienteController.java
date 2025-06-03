package com.miempresa.tiendaonline.tiendaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miempresa.tiendaonline.tiendaonline.model.Cliente;
import com.miempresa.tiendaonline.tiendaonline.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar(){

        System.out.println("Entró al método listar Clientes");
        return clienteService.listarClientes();
        
    }

    
}