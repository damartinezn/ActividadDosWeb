package com.unir.librosproduct.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "libro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Libro {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "libroId")
        private Long libroId;
        @Column(name = "titulo", unique = true, length = 255)
        @NonNull
        private String titulo;
        @Column(name = "anioPublicacion")
        @NonNull
        private Date anioPublicacion;
        @Column(name = "editorial")
        @NonNull
        private String editorial;
        @Column(name = "isbn13", unique = true, length = 13)
        private String isbn13;
        @Column(name = "isbn10", unique = true, length = 10)
        private String isbn10;
        @Column(name = "imagen")
        @NonNull
        private String imagen;
        @Column(name = "sinopsis", length = 500)
        @NonNull
        private String sinopsis;
        @Column(name = "cantidad")
        @NonNull
        private Integer cantidad;
        @Column(name = "autor")
        @NonNull
        private String autor;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "libroId")
        private List<Critica> listCriticas;

        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinTable(name = "libro_genero", joinColumns = { @JoinColumn(name = "libroId") }, inverseJoinColumns = {
                        @JoinColumn(name = "codigo") })
        @Builder.Default
        private Set<Genero> listGenero = new HashSet<>();
}
