/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tendencias.app.Examen.repository;

import com.tendencias.app.Examen.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author oscar
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
        @Query(value = "Select * from producto p where p.nombre = :nombre", nativeQuery = true)
    public Producto buscarProducto(String nombre);
}
