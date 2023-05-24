package com.unir.librosproduct.service;

import java.util.List;

import com.unir.librosproduct.model.pojo.Genero;
import com.unir.librosproduct.model.request.CreateRequestGenero;

public interface GeneroService {
    List<Genero> getGeneros();

    Genero getGenero(Long generoId);

    Boolean removeGenero(Long generoId);

    Genero createGenero(CreateRequestGenero request);
}
