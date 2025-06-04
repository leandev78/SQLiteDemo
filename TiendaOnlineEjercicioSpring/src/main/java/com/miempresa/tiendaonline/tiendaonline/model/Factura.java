package com.miempresa.tiendaonline.tiendaonline.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  Para H2 y MySQL funciona bien
    @Column(name="id_factura")
    private Long idFactura;

    @Column(name="id_cliente")
    private Long idCliente;

    @Column(name="fecha")
    private LocalDateTime fecha;
    
    @Column(name="total")
    private Double total;


    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Esta @anotation se usa para controlar la serialización de las relaciones bidireccionales
    private List<DetalleFactura> detalles = new ArrayList<>();



    public Factura() { }



    public Long getIdFactura() {  return idFactura; }
    public void setIdFactura(Long idFactura) {  this.idFactura = idFactura; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public List<DetalleFactura> getDetalles() { return detalles; }

    public void setDetalles(List<DetalleFactura> detalles) {
    this.detalles = detalles;
    if (detalles != null) {
        for (DetalleFactura d : detalles) {
            d.setFactura(this);
        }
    }

}


         
}
