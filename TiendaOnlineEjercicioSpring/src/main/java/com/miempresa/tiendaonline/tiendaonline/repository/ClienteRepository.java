package com.miempresa.tiendaonline.tiendaonline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miempresa.tiendaonline.tiendaonline.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{


    Optional<Cliente> findByEmailAndPassword(String email, String password);

    // ¿Cómo sabe Hibernate qué hacer?
    // Cuando escribís en tu ClienteRepository:
    // Optional<Cliente> findByEmailAndPassword(String email, String password);
    // Spring Data JPA analiza el nombre del método y:
    // findBy... le indica que debe realizar una búsqueda.
    // EmailAndPassword indica que debe buscar registros donde email = ? y password = ?.
    // Los nombres email y password deben coincidir exactamente con los nombres de los atributos en la clase Cliente.
    // Esto genera internamente una consulta similar a:
    // SELECT * FROM cliente WHERE email = ? AND password = ? 


}
