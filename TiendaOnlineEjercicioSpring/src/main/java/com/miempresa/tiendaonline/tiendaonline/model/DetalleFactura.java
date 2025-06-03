package com.miempresa.tiendaonline.tiendaonline.model;

import jakarta.persistence.*;

@Entity
@Table(name="detalle_factura")
public class DetalleFactura {

    @EmbeddedId
    private DetalleFacturaId id; // compuesto por idFactura e idProducto

    @Column(name = "cantidad")
    private Integer descripcion;

    @Column(name = "precio_unitario")
    private Double precio;

    public DetalleFactura(){}

    
    public DetalleFacturaId getId() { return id; }
    public void setId(DetalleFacturaId id) { this.id = id; }

    // public Long getIdFactura() { return idFactura; }
    // public void setIdFactura(Long idFactura) { this.idFactura = idFactura; }

    // public Long getIdProducto() { return idProducto; }
    // public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public Integer getDescripcion() { return descripcion; }
    public void setDescripcion(Integer descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    
}
