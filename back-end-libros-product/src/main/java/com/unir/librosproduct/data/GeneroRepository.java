package com.unir.librosproduct.data;

import com.unir.librosproduct.model.pojo.Genero;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    List<Genero> findByNombre(String nombre);
}
