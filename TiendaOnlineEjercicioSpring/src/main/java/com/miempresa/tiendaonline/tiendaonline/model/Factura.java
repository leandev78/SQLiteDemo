package com.miempresa.tiendaonline.tiendaonline.model;

import java.sql.Time;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="factura")
public class Factura {

    @Id
    @Column(name="id_factura")
    private Long id_factura;

    @Column(name="id_cliente")
    private Long id_cliente;

    @Column(name="fecha")
    private LocalDateTime fecha;
    
    @Column(name="total")
    private Double total;

    public Factura() { }

    public Long getId_factura() {  return id_factura; }
    public void setId_factura(Long id_factura) {  this.id_factura = id_factura; }

    public Long getId_cliente() { return id_cliente; }
    public void setId_cliente(Long id_cliente) { this.id_cliente = id_cliente; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

        
}
