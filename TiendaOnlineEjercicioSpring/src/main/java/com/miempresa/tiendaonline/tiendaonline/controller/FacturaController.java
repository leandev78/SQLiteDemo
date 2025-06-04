package com.miempresa.tiendaonline.tiendaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miempresa.tiendaonline.tiendaonline.model.Factura;
import com.miempresa.tiendaonline.tiendaonline.service.FacturaService;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;
    
    @GetMapping
    public List<Factura> listar() {
        
        System.out.println("Entró al método listar Factura");
        return facturaService.listarFacturas();
    }  

    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        return facturaService.guardar(factura);
    }


    @PostMapping("/completa")
    public Factura crearFacturaCompleta(@RequestBody Factura factura) {
        return facturaService.guardarFacturaCompleta(factura);
    }    

}
