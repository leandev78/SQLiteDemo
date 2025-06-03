package com.miempresa.tiendaonline.tiendaonline.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Carrito {

    @Id
    @Column(name="id_carrito")
    private Long id_carrito;

    @Column(name="id_cliente")
    private Long id_cliente;

    @Column(name="fecha_creacion")
    private LocalDateTime fecha_creacion;

    public Carrito(){}

    public Long getId_carrito() { return id_carrito; }
    public void setId_carrito(Long id_carrito) { this.id_carrito = id_carrito; }

    public Long getId_cliente() { return id_cliente; }
    public void setId_cliente(Long id_cliente) { this.id_cliente = id_cliente; }

    public LocalDateTime getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(LocalDateTime fecha_creacion) { this.fecha_creacion = fecha_creacion; }

    
}
