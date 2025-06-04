package com.miempresa.tiendaonline.tiendaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miempresa.tiendaonline.tiendaonline.model.Producto;
import com.miempresa.tiendaonline.tiendaonline.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {


    @RequestMapping("/inicio")
    public String Producto(){
        return "Bienvenido a mi Producto Inicial!!!";
    }

    @Autowired
    private ProductoService productoService;    


    @GetMapping
    public List<Producto> listar() {
        
        System.out.println("Entró al método listar Productos");
        return productoService.listarProductos();
    }    


    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoService.guardar(producto);
    }

}