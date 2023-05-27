package com.unir.librosproduct.data;

import com.unir.librosproduct.model.pojo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
        List<Libro> findByTitulo(String titulo);

        @Query("SELECT l FROM Libro l WHERE l.titulo like :titulo  " +
                        "AND l.editorial like :editorialB  " +
                        "AND l.anioPublicacion = CASE WHEN :anioPublicacionB IS NULL THEN l.anioPublicacion ELSE :anioPublicacionB END "
                        +
                        "AND l.isbn13 = CASE WHEN :isbn13B IS NULL THEN l.isbn13 ELSE :isbn13B END " +
                        "AND l.isbn10 = CASE WHEN :isbn10B IS NULL THEN l.isbn10 ELSE :isbn10B END " +
                        "AND l.autor  = CASE WHEN :autorB IS NULL THEN l.autor ELSE :autorB END ")
        List<Libro> findByAllParmas(String titulo, String editorialB, Date anioPublicacionB,
                        String isbn13B, String isbn10B, String autorB);

}
