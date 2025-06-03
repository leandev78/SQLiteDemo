package com.miempresa.tiendaonline.tiendaonline.service;

import java.util.List;

import com.miempresa.tiendaonline.tiendaonline.model.DetalleFactura;

public interface DetalleFacturaService {

    List<DetalleFactura> buscarPorIdFactura(Long idFactura);

}
