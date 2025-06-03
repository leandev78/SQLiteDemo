package com.miempresa.tiendaonline.tiendaonline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.tiendaonline.tiendaonline.dto.ClienteDTO;
import com.miempresa.tiendaonline.tiendaonline.model.Cliente;
import com.miempresa.tiendaonline.tiendaonline.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        
        return clienteRepository.findAll();

    }

    // Aca estoy usando el patrón de diseño DTO. Si bien no es 
    // obligatorio usar DTOs es muy buena práctica en una arquitectura MVC, 
    // especialmente si estamos desarrollando una APIs REST para separa y 
    // encapsular nuestro modelo de dominio. 
    public List<ClienteDTO> listarClientesDTO() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(c -> new ClienteDTO(c.getId_cliente(), c.getNombre(), c.getApellido(), c.getEmail()))
                .collect(Collectors.toList());    
    }
    
}
