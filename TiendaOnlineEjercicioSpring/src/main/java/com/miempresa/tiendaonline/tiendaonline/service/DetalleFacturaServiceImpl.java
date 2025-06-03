package com.miempresa.tiendaonline.tiendaonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.tiendaonline.tiendaonline.model.DetalleFactura;
import com.miempresa.tiendaonline.tiendaonline.repository.DetalleFacturaRepository;

@Service
public class DetalleFacturaServiceImpl implements DetalleFacturaService{

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;


    @Override
    public List<DetalleFactura> buscarPorIdFactura(Long idFactura) {

        return detalleFacturaRepository.findById_IdFactura(idFactura);
    }

}
