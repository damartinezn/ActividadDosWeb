package com.unir.backOperador.controller;

import com.unir.backOperador.model.pojo.Alquiler;
import com.unir.backOperador.model.request.CreateRequestAlquiler;
import com.unir.backOperador.service.AlquilerService;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlquilerController {

    private final AlquilerService alquilerService;

    @GetMapping("/alquilar/{libroId}")
    public ResponseEntity<List<Alquiler>> getLibrosAlquilados(@PathVariable String libroId) {
        log.info(" headers : {} ", libroId);
        List<Alquiler> librosAlquilados = alquilerService.findByLibro(Long.valueOf(libroId));
        if (librosAlquilados != null) {
            return ResponseEntity.ok(librosAlquilados);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/alquilar/user/{usuarioId}")
    public ResponseEntity<List<Alquiler>> getAlquiladosByUser(@PathVariable String usuarioId) {
        log.info(" headers : {} ", usuarioId);
        List<Alquiler> librosAlquilados = alquilerService.findByUsuario(Long.valueOf(usuarioId));
        if (librosAlquilados != null) {
            return ResponseEntity.ok(librosAlquilados);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @PostMapping("/alquilar")
    public ResponseEntity<Alquiler> alquilarLibro(@RequestBody CreateRequestAlquiler request) {
        log.info(" Request save for libro : {} ", request.toString());
        try {
            Alquiler guardado = alquilerService.aliquilarLibro(request);
            if (guardado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            // Error genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/alquilar/{libroId}")
    public ResponseEntity<Alquiler> devolverLibro(@PathVariable("libroId") String alquilerId) {
        log.info(" Request save for libro : {} ", alquilerId);
        try {
            Alquiler guardado = alquilerService.devolverLibro(Long.valueOf(alquilerId));
            if (guardado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            // Error genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
