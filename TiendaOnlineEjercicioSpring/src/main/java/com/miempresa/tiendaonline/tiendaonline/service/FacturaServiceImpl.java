package com.miempresa.tiendaonline.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.tiendaonline.tiendaonline.model.Factura;
import com.miempresa.tiendaonline.tiendaonline.repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

}
