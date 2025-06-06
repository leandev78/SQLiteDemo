package com.miempresa.tiendaonline.tiendaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miempresa.tiendaonline.tiendaonline.dto.ClienteDTO;
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

    @GetMapping("/dto")
    public List<ClienteDTO> listarDTO(){

        System.out.println("Entró al método listar Clientes DTO");
        return clienteService.listarClientesDTO();       
    }    

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {

        return clienteService.guardar(cliente);

    }    

    @PostMapping("/login")
    public ResponseEntity<Cliente> login(@RequestBody Cliente loginRequest) {
        return clienteService.login(loginRequest.getEmail(), loginRequest.getPassword())
            .map(cliente -> ResponseEntity.ok(cliente))
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    
}
