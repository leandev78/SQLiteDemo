package com.miempresa.tiendaonline.tiendaonline.model;

import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@RequestMapping(name="carrito_producto")
public class CarritoProducto {

    @Id
    @Column(name="id_carrito")
    private Long id_carrito;

    @Column(name="id_producto")
    private Long id_producto;

    @Column(name="cantidad")
    private Integer cantidad;

    public CarritoProducto(){}

    public Long getId_carrito() { return id_carrito; }
    public void setId_carrito(Long id_carrito) { this.id_carrito = id_carrito; }

    public Long getId_producto() { return id_producto; }
    public void setId_producto(Long id_producto) { this.id_producto = id_producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    
}
