package com.unir.librosproduct.service;

import com.unir.librosproduct.data.GeneroRepository;
import com.unir.librosproduct.data.LibroRepository;
import com.unir.librosproduct.model.pojo.Genero;
import com.unir.librosproduct.model.pojo.Libro;
import com.unir.librosproduct.model.request.CreateLibrorequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
            Libro auxGuardado = libroRepository.save(libro);
            if (request.getListGenero() != null && request.getListGenero().size() > 0) {
                auxGuardado.setListGenero(request.getListGenero());
                for (var genero : request.getListGenero()) {
                    Genero aux = generoRepository.findById(genero.getCodigo())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "No se encontro el género con el id" + String.valueOf(genero.getCodigo())));
                    if (aux != null && libro.getListGenero() != null && libro.getListGenero().size() > 0) {
                        aux.getListLibro().add(auxGuardado);
                    }
                }
                auxGuardado = libroRepository.save(auxGuardado);
            }
            return auxGuardado;
        } else {
            return null;
        }
    }

    @Override
    public Libro alquilarLibro(Long libroId) {
        if (libroId != null) {
            Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new EntityNotFoundException(
                "No se encontro el libro con el id : " + String.valueOf(libroId)));
            if (libro != null && libro.getCantidad() > 0) {
                libro.setCantidad(libro.getCantidad() - 1);
                return libroRepository.save(libro);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Libro> getLibrosByAllParams(CreateLibrorequest libroBuscar) {
        List<Libro> libros = new ArrayList<>();
        log.info(" Request received for BY ALL PRODUCTO product : {} ", libroBuscar);
        if (libroBuscar != null) {
            libros = libroRepository
                    .findByTituloIgnoreCaseLikeAndEditorialIgnoreCaseLikeAndIsbn13IgnoreCaseLikeAndIsbn10IgnoreCaseLikeAndAutorIgnoreCaseLike(
                            "%" + (libroBuscar.getTitulo() == null ? "" : libroBuscar.getTitulo()) + "%",
                            "%" + (libroBuscar.getEditorial() == null ? "" : libroBuscar.getEditorial()) + "%",
                            "%" + (libroBuscar.getIsbn13() == null ? "" : libroBuscar.getIsbn13()) + "%",
                            "%" + (libroBuscar.getIsbn10() == null ? "" : libroBuscar.getIsbn10()) + "%",
                            "%" + (libroBuscar.getAutor() == null ? "" : libroBuscar.getAutor()) + "%");
        }

        return libros.isEmpty() ? null : libros;
    }

    @Override
    public Libro devolverLibro(Long libroId) {
        if (libroId != null) {
            Libro libro = libroRepository.findById(libroId).orElseThrow(() -> new EntityNotFoundException(
                "No se encontro el libro con el id : " + String.valueOf(libroId)));
            libro.setCantidad(libro.getCantidad() + 1);
            return libroRepository.save(libro);
        } else {
            return null;
        }
    }

    @Override
    public Libro editLibro(Long librodId, CreateLibrorequest request) {
        Libro editar = libroRepository.findById(librodId).orElseThrow(
                () -> new EntityNotFoundException("No existe el libro con el ID : " + String.valueOf(librodId)));
        if (request != null && StringUtils.hasLength(request.getTitulo().trim())
                && StringUtils.hasLength(request.getEditorial().trim())
                && StringUtils.hasLength(request.getIsbn13().trim())
                && StringUtils.hasLength(request.getIsbn10().trim())
                && StringUtils.hasLength(request.getImagen().trim())
                && StringUtils.hasLength(request.getSipnosis().trim())
                && request.getCantidad() != null && request.getCantidad() >= 0 && editar != null) {
                editar.setTitulo(request.getTitulo());
                editar.setAnioPublicacion(request.getAnioPublicacion());
                editar.setEditorial(request.getEditorial());
                editar.setIsbn13(request.getIsbn13());
                editar.setIsbn10(request.getIsbn10());
                editar.setImagen(request.getImagen());
                editar.setSipnosis(request.getSipnosis());
                editar.setCantidad(request.getCantidad());
                editar.setAutor(request.getAutor());
            if (request.getListCriticas() != null && request.getListCriticas().size() > 0) {
                editar.setListCriticas(request.getListCriticas());
            }
            editar = libroRepository.save(editar);
            if (request.getListGenero() != null && request.getListGenero().size() > 0) {
                editar.setListGenero(request.getListGenero());
                for (var genero : request.getListGenero()) {
                    Genero aux = generoRepository.findById(genero.getCodigo())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "No se encontro el género con el id : " + String.valueOf(genero.getCodigo())));
                    if (aux != null && editar.getListGenero() != null && editar.getListGenero().size() > 0) {
                        aux.getListLibro().add(editar);
                    }
                }
                editar = libroRepository.save(editar);
            }
            return editar;
        } else {
            return null;
        }
    }
}
