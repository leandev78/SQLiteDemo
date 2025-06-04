package com.miempresa.tiendaonline.tiendaonline.service;

import java.util.List;

import com.miempresa.tiendaonline.tiendaonline.dto.ClienteDTO;
import com.miempresa.tiendaonline.tiendaonline.model.Cliente;

public interface ClienteService {
    
    List<Cliente> listarClientes();

    List<ClienteDTO> listarClientesDTO();

    Cliente guardar(Cliente cliente);
}
