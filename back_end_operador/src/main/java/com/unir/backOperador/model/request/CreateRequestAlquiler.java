package com.unir.backOperador.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateRequestAlquiler {

    private Long usuarioId;
    private Long libroId;
    private Integer cantidadDias;

}
