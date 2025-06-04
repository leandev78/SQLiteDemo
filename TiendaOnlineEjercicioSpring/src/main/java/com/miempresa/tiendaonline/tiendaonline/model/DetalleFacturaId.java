package com.miempresa.tiendaonline.tiendaonline.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DetalleFacturaId implements Serializable {

    @Column(name = "id_factura")
    private Long idFactura;

    @Column(name = "id_producto")
    private Long idProducto;


    public DetalleFacturaId(){
        
    }


    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    // equals() y hashCode() - obligatorios para claves compuestas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleFacturaId)) return false;
        DetalleFacturaId that = (DetalleFacturaId) o;
        return Objects.equals(idFactura, that.idFactura) &&
               Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFactura, idProducto);
    }
        
}