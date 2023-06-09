package com.unir.librosproduct.controller;

import com.unir.librosproduct.model.pojo.Libro;
import com.unir.librosproduct.model.request.CreateLibrorequest;
import com.unir.librosproduct.service.LibroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LibroController {

    private final LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> getLibros(@RequestHeader Map<String, String> headers) {
        log.info(" headers : {} ", headers);
        List<Libro> libros = libroService.getLibros();
        if (libros != null) {
            return ResponseEntity.ok(libros);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/libros/{libroId}")
    public ResponseEntity<Libro> getLibro(@PathVariable String libroId) {
        log.info(" Request received for product : {} ", libroId);
        Libro libros = libroService.getLibro(Long.parseLong(libroId));
        if (libros != null) {
            return ResponseEntity.ok(libros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/libros/{libroId}")
    public ResponseEntity<Void> deleteLibro(@PathVariable String libroId) {
        log.info(" Request received for product : {} ", libroId);
        try {
            Boolean removed = libroService.removeLibro(Long.parseLong(libroId));
            if (Boolean.TRUE.equals(removed)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/libros")
    public ResponseEntity<Libro> saveLibro(@RequestBody CreateLibrorequest request) {
        log.info(" Request save for libro : {} ", request.toString());
        try {
            Libro saveLibro = libroService.createLibro(request);
            if (saveLibro != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(saveLibro);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/libros/{libroId}")
    public ResponseEntity<Libro> actualizarDevovlerLibro(@PathVariable String libroId,
            @RequestBody CreateLibrorequest request) {
        log.info(" Devolver libro el id cantidad y días : {} ", libroId);
        try {
            Libro saveLibro = libroService.editLibro(Long.parseLong(libroId), request);
            if (saveLibro != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(saveLibro);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/libros/search")
    public ResponseEntity<List<Libro>> getLibrosByAllParams(@RequestBody CreateLibrorequest request) {
        log.info(" Request save for libro : {} ", request.toString());
        try {
            List<Libro> getLibros = libroService.getLibrosByAllParams(request);
            if (getLibros != null) {
                return ResponseEntity.ok(getLibros);
            } else {
                return ResponseEntity.ok(Collections.emptyList());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/libros/rent/{alquilarId}")
    public ResponseEntity<Libro> cantidadAlquilarLibro(@PathVariable String alquilarId,
            @RequestBody(required = false) CreateLibrorequest request) {
        log.info(" Alquilar libro  : {} ", alquilarId);
        Libro saveLibro = libroService.alquilarLibro(Long.parseLong(alquilarId));
        if (saveLibro != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveLibro);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/libros/return/{libroId}")
    public ResponseEntity<Libro> cantidadDevolverLibro(@PathVariable String libroId,
            @RequestBody(required = false) CreateLibrorequest request) {
        log.info(" Devolver libro el id cantidad y días : {} ", libroId);
        Libro saveLibro = libroService.devolverLibro(Long.parseLong(libroId));
        if (saveLibro != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveLibro);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
