package com.unir.librosproduct.service;

import com.unir.librosproduct.data.LibroRepository;
import com.unir.librosproduct.model.pojo.Libro;
import com.unir.librosproduct.model.request.CreateLibrorequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService{

    @Autowired
    private LibroRepository libroRepository;

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
        if (libro != null){
            libroRepository.delete(libro);
            return Boolean.TRUE;
        }else {
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
                    .cantidad(request.getCantidad()).build();
            return libroRepository.save(libro);
        } else{
            return null;
        }
    }
}
