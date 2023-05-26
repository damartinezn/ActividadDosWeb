package com.unir.backOperador.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Critica {

    private Long criticaId;
    private String usuario;
    private Double calificacion;
    private String comentario;

}
