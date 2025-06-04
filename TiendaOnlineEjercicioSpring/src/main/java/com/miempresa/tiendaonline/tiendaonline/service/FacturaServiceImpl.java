package com.miempresa.tiendaonline.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.tiendaonline.tiendaonline.model.DetalleFactura;
import com.miempresa.tiendaonline.tiendaonline.model.DetalleFacturaId;
import com.miempresa.tiendaonline.tiendaonline.model.Factura;
import com.miempresa.tiendaonline.tiendaonline.repository.DetalleFacturaRepository;
import com.miempresa.tiendaonline.tiendaonline.repository.FacturaRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;



    @Override
    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }



    @Override
    public Factura guardar(Factura factura) {
        return facturaRepository.save(factura);
    }


    @Transactional
    public Factura guardarFacturaCompleta(Factura factura) {
        
        // Guardar la factura primero para que se genere el ID
        Factura facturaGuardada = facturaRepository.save(factura);

        return facturaGuardada;
    }



}
