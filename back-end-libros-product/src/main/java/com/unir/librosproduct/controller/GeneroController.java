package com.unir.librosproduct.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.unir.librosproduct.model.pojo.Genero;
import com.unir.librosproduct.model.request.CreateRequestGenero;
import com.unir.librosproduct.service.GeneroService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GeneroController {
    private final GeneroService generoService;

    @GetMapping("/generos")
    public ResponseEntity<List<Genero>> getGeneros(@RequestHeader Map<String, String> headers){
        log.info(" headers : {} ", headers);
        List<Genero> generos = generoService.getGeneros();
        if (generos != null) {
            return ResponseEntity.ok(generos);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/generos/{generoId}")
    public ResponseEntity<Genero> getGenero(@PathVariable String generoId){
        log.info(" Request received for product : {} ", generoId);
        Genero genero = generoService.getGenero(Long.parseLong(generoId));
        if (genero != null) {
            return ResponseEntity.ok(genero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/generos/{generoId}")
    public ResponseEntity<Void> deleteGenero(@PathVariable String generoId){
        log.info(" Request received for product : {} ", generoId);
        Boolean removed = generoService.removeGenero(Long.parseLong(generoId));
        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/generos")
    public ResponseEntity<Genero> saveLibro(@RequestBody CreateRequestGenero request){
        log.info(" Request save for libro : {} ", request.toString());
        Genero saveGenero = generoService.createGenero(request);
        if (saveGenero != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveGenero);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
