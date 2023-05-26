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
        Boolean removed = libroService.removeLibro(Long.parseLong(libroId));
        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/libros")
    public ResponseEntity<Libro> saveLibro(@RequestBody CreateLibrorequest request) {
        log.info(" Request save for libro : {} ", request.toString());
        Libro saveLibro = libroService.createLibro(request);
        if (saveLibro != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveLibro);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/alquilar/{alquilarId}")
    public ResponseEntity<Libro> cantidadAlquilarLibro(@PathVariable String alquilarId) {
        log.info(" Alquilar libro con los nuevos valores me pueden mandar todo me interes el id cantidad y días : {} ",
        alquilarId);
        Libro saveLibro = libroService.alquilarLibro(Long.parseLong(alquilarId));
        if (saveLibro != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveLibro);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
