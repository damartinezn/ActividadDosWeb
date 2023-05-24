package com.unir.librosproduct.model.request;


import lombok.*;

import java.sql.Date;

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
    private String sipnosis;
    private Integer cantidad;

}
