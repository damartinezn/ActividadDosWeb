package com.unir.backOperador.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unir.backOperador.model.pojo.Alquiler;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    List<Alquiler> findByLibroId(Long userIdLong);
}
