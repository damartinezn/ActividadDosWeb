package com.unir.librosproduct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.librosproduct.data.GeneroRepository;
import com.unir.librosproduct.model.pojo.Genero;
import com.unir.librosproduct.model.request.CreateRequestGenero;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public List<Genero> getGeneros() {
        List<Genero> listGeneros = generoRepository.findAll();
        return listGeneros.isEmpty() ? null : listGeneros;
    }

    @Override
    public Genero getGenero(Long generoId) {
        return generoRepository.findById(generoId).orElse(null);
    }

    @Override
    public Boolean removeGenero(Long generoId) {
        Genero genero = generoRepository.findById(generoId).orElse(null);
        if (genero != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Genero createGenero(CreateRequestGenero request) {
        if (request != null && StringUtils.hasLength(request.getNombre().trim())
                && StringUtils.hasLength(request.getNombre().trim())) {
            Genero genero = Genero.builder().nombre(request.getNombre())
                    .descripcion(request.getDescripcion()).build();
            return generoRepository.save(genero);
        } else {
            return null;
        }
    }

}
