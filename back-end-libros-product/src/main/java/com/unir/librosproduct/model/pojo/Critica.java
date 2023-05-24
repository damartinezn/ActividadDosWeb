package com.unir.librosproduct.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "critica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Critica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "criticaId")
    private Long criticaId;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "calificacion")
    private Double calificacion;
    @Column(name = "comentario")
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libroId")
    @JsonIgnore
    private Libro libroId;
}
