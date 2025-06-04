package com.miempresa.tiendaonline.tiendaonline.service;

import java.util.List;

import com.miempresa.tiendaonline.tiendaonline.model.Producto;

public interface ProductoService {
    
    List<Producto> listarProductos();

    Producto guardar(Producto producto);
    
}


