/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tendencias.app.Examen.controller;

import com.tendencias.app.Examen.model.Producto;
import com.tendencias.app.Examen.service.ProductoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oscar
 */
@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoServiceImpl productoService;

    @Operation(summary = "Se obtiene la lista de Producto")
    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listaProducto() {
        return new ResponseEntity<>(productoService.findByAll(), HttpStatus.OK);
    }

    @Operation(summary = "Debe enviar los campos del Producto")
    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto p) {
        return new ResponseEntity<>(productoService.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Integer id, @RequestBody Producto p) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            try { 
                producto.setId(p.getId());
                producto.setDescripcion(p.getDescripcion()); 
                producto.setPrecio(p.getPrecio());
                producto.setCantidad(p.getCantidad());
                producto.setPvp(p.getPvp());
                producto.setIva(p.getIva());
                producto.setDescuento(p.getDescuento());
                producto.setSubTotal(p.getSubTotal());
                return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/calcular-venta")
    public ResponseEntity<Double> calcularVenta(@RequestBody Producto p) {
    double subtotal = p.getCantidad() * p.getPrecio();
    double descuento = 0.0;
    double iva = 0.0;
    double total = 0.0;

    if (subtotal > 50) {
        descuento = subtotal * 0.1;
    }

    iva = subtotal * 0.12;
    total = subtotal - descuento + iva;

    return new ResponseEntity<>(total, HttpStatus.OK);
}

    @DeleteMapping
    public ResponseEntity<Producto> elimiarProducto(@PathVariable Integer id) {
        productoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
