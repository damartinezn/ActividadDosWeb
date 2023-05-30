package com.unir.librosproduct.data;

import com.unir.librosproduct.model.pojo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
        List<Libro> findByTitulo(@Param("status") String titulo);


        List<Libro> findByTituloIgnoreCaseLikeAndEditorialIgnoreCaseLikeAndIsbn13IgnoreCaseLikeAndIsbn10IgnoreCaseLikeAndAutorIgnoreCaseLike(
                        @Param("titulo") String titulo, @Param("editorial") String editorial,
                        @Param("isbn13") String isbn13, @Param("isbn10") String isbn10, @Param("autor") String autor);

}
