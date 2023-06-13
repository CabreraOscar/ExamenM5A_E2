/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tendencias.app.Examen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 *
 * @author oscar
 */
@Data
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "Descripcion")
    private String Descripcion;
    @Column(name = "Precio")
    private double Precio;
    @Column(name = "Cantidad")
    private int Cantidad;
    @Column(name = "subTotal")
    private double subTotal;
    @Column(name = "descuento")
    private double descuento;
    @Column(name = "iva")
    private double iva;
    @Column(name = "pvp")
    private double pvp;
}
