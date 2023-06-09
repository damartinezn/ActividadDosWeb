package com.unir.backOperador.model.request;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Libro {

        private Long libroId;
        private String titulo;
        private Date anioPublicacion;
        private String editorial;
        private String isbn13;
        private String isbn10;
        private String imagen;
        private String sinopsis;
        private Integer cantidad;
        private String autor;
        private List<Critica> listCriticas;

}
