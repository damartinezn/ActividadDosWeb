package com.unir.librosproduct.model.request;

import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.unir.librosproduct.model.pojo.Critica;
import com.unir.librosproduct.model.pojo.Genero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateLibrorequest {
    private String titulo;
    private Date anioPublicacion;
    private String editorial;
    private String isbn13;
    private String isbn10;
    private String imagen;
    private String sinopsis;
    private String autor;
    private Integer cantidad;
    List<Critica> listCriticas;
    Set<Genero> listGenero;

}
