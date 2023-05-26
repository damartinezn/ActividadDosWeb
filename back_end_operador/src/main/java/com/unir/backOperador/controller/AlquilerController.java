package com.unir.backOperador.controller;

import com.unir.backOperador.model.request.CreateRequestAlquiler;
import com.unir.backOperador.service.AlquilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlquilerController {
    
    private final AlquilerService alquilerService;

    @PostMapping("/alquilar")
    public ResponseEntity<Long> alquilarLibro(@RequestBody CreateRequestAlquiler request){
        log.info(" Request save for libro : {} ", request.toString());
        Long guardado = alquilerService.aliquilarLibro(request);
        if(guardado != null ) {
            return ResponseEntity.ok(guardado);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
