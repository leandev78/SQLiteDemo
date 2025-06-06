package com.miempresa.tiendaonline.tiendaonline.service;

import java.util.List;
import java.util.Optional;

import com.miempresa.tiendaonline.tiendaonline.dto.ClienteDTO;
import com.miempresa.tiendaonline.tiendaonline.model.Cliente;

public interface ClienteService {
    
    List<Cliente> listarClientes();

    List<ClienteDTO> listarClientesDTO();

    Cliente guardar(Cliente cliente);

    Optional<Cliente> login(String email, String password);
}
