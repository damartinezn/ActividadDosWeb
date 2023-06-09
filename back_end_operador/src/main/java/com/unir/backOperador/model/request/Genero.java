package com.unir.backOperador.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Genero {
    private Long codigo;
    private String nombre;
    private String descripcion;

}
