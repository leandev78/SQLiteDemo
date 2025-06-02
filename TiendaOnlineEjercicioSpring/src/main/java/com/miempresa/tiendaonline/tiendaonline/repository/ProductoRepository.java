package com.miempresa.tiendaonline.tiendaonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.tiendaonline.tiendaonline.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
