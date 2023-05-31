package com.unir.backOperador.service;

import java.util.List;

import com.unir.backOperador.model.pojo.Alquiler;
import com.unir.backOperador.model.request.CreateRequestAlquiler;

public interface AlquilerService {

    List<Alquiler> findByLibro(Long liboId);

    List<Alquiler> findByUsuario(Long userId);

    Alquiler aliquilarLibro(CreateRequestAlquiler request);

    Alquiler devolverLibro(Long alquilerId);

}
