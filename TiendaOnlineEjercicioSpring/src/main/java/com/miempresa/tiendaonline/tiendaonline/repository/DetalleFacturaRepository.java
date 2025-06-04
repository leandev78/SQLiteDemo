package com.miempresa.tiendaonline.tiendaonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.tiendaonline.tiendaonline.model.DetalleFactura;
import com.miempresa.tiendaonline.tiendaonline.model.DetalleFacturaId;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, DetalleFacturaId>{


    // Declaro un método personalizado que no pertenece a JpaRepository y esto es porque
    // detalle_factura tiene múltiples registros con el mismo id_factura, pero findById(id) 
    // busca por clave primaria, no por columna id_factura.

    // Analizar
    // id es el nombre del campo embebido (DetalleFacturaId), y idFactura es el campo dentro de esa clase. 
    // Spring Data JPA permite recorrer la propiedad anidada usando underscore (_).

    List<DetalleFactura> findById_IdFactura(Long idFactura);

    List<DetalleFactura> findById_IdProducto(Long idProducto);

    
}
