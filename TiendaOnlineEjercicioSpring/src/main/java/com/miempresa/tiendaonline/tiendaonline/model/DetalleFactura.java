package com.miempresa.tiendaonline.tiendaonline.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="detalle_factura")
public class DetalleFactura {

    @EmbeddedId
    private DetalleFacturaId id; // compuesto por idFactura e idProducto

    @ManyToOne
    @MapsId("idFactura")                // Importante para indicar qué parte del ID compuesta es de Factura
    @JoinColumn(name = "id_factura")    // Se usa para referenciar con la clase Factura.
    @JsonBackReference                  // Se usa para controlar la serialización de las relaciones bidireccionales
    private Factura factura;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;





    public DetalleFactura(){}


    public DetalleFacturaId getId() { return id; }
    public void setId(DetalleFacturaId id) { this.id = id; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Factura getFactura() { return factura; }
    public void setFactura(Factura factura) { this.factura = factura; }
    
    
}
