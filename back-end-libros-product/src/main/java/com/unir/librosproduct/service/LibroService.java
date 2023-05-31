package com.unir.librosproduct.service;

import com.unir.librosproduct.model.pojo.Libro;
import com.unir.librosproduct.model.request.CreateLibrorequest;

import java.util.List;

public interface LibroService {
    List<Libro> getLibros();

    Libro getLibro(Long libroId);

    List<Libro> getLibrosByAllParams(CreateLibrorequest libroBuscar);

    Boolean removeLibro(Long libroId);

    Libro createLibro(CreateLibrorequest request);

    Libro alquilarLibro(Long libroId);

    Libro devolverLibro(Long libroId);
}
