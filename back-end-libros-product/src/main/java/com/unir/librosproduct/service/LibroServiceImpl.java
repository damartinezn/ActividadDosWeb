package com.unir.librosproduct.service;

import com.unir.librosproduct.data.GeneroRepository;
import com.unir.librosproduct.data.LibroRepository;
import com.unir.librosproduct.model.pojo.Genero;
import com.unir.librosproduct.model.pojo.Libro;
import com.unir.librosproduct.model.request.CreateLibrorequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public List<Libro> getLibros() {
        List<Libro> libros = libroRepository.findAll();
        return libros.isEmpty() ? null : libros;
    }

    @Override
    public Libro getLibro(Long libroId) {
        return libroRepository.findById(libroId).orElse(null);
    }

    @Override
    public Boolean removeLibro(Long libroId) {
        Libro libro = libroRepository.findById(libroId).orElse(null);
        if (libro != null) {
            libroRepository.delete(libro);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Libro createLibro(CreateLibrorequest request) {
        if (request != null && StringUtils.hasLength(request.getTitulo().trim())
                && StringUtils.hasLength(request.getEditorial().trim())
                && StringUtils.hasLength(request.getIsbn13().trim())
                && StringUtils.hasLength(request.getIsbn10().trim())
                && StringUtils.hasLength(request.getImagen().trim())
                && StringUtils.hasLength(request.getSipnosis().trim())
                && request.getCantidad() != null && request.getCantidad() >= 0) {
            Libro libro = Libro.builder().titulo(request.getTitulo())
                    .anioPublicacion(request.getAnioPublicacion())
                    .editorial(request.getEditorial())
                    .isbn13(request.getIsbn13())
                    .isbn10(request.getIsbn10())
                    .imagen(request.getImagen())
                    .sipnosis(request.getSipnosis())
                    .cantidad(request.getCantidad())
                    .autor(request.getAutor()).build();
            if (request.getListCriticas() != null && request.getListCriticas().size() > 0) {
                libro.setListCriticas(request.getListCriticas());
            }
            if (request.getListGenero() != null && request.getListGenero().size() > 0) {
                for (var genero : request.getListGenero()) {
                    Genero aux = generoRepository.findById(genero.getCodigo()).orElse(null);
                    if (aux != null && libro.getListGenero() != null && libro.getListGenero().size() > 0) {
                        libro.getListGenero().add(aux);
                    }
                    if (aux != null && libro.getListGenero() == null) {
                        Set<Genero> auxgen = new HashSet<>();
                        auxgen.add(aux);
                        libro.setListGenero(auxgen);
                    }
                }
            }
            return libroRepository.save(libro);
        } else {
            return null;
        }
    }

    @Override
    public Libro alquilarLibro(Long libroId) {
        if (libroId != null) {
            Libro libro = libroRepository.findById(libroId).orElse(null);
            libro.setCantidad(libro.getCantidad() - 1);
            return libroRepository.save(libro);
        } else {
            return null;
        }
    }

    @Override
    public List<Libro> getLibrosByAllParams(CreateLibrorequest libroBuscar) {
        List<Libro> libros = new ArrayList<>();
        log.info(" Request received for BY ALL PRODUCTO product : {} ", libroBuscar);
        if (libroBuscar != null) {
            libros = libroRepository.findByAllParmas("%"+libroBuscar.getTitulo() != null ?libroBuscar.getTitulo():"" +"%",
                    "%"+libroBuscar.getEditorial()+"%",
                    libroBuscar.getAnioPublicacion(),
                    libroBuscar.getIsbn13(),
                    libroBuscar.getIsbn10(),
                    libroBuscar.getAutor());
        }

        return libros.isEmpty() ? null : libros;
    }
}
