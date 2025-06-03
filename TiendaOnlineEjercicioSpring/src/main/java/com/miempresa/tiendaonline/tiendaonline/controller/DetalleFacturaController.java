package com.miempresa.tiendaonline.tiendaonline.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miempresa.tiendaonline.tiendaonline.model.DetalleFactura;
import com.miempresa.tiendaonline.tiendaonline.service.DetalleFacturaService;

@RestController
@RequestMapping("/detallefactura")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @PostMapping
    public List<DetalleFactura> listar(@RequestBody Map<String, Long> body) {
        Long id = body.get("id");
        return detalleFacturaService.buscarPorIdFactura(id);
    }

}
