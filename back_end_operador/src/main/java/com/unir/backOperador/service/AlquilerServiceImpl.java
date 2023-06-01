package com.unir.backOperador.service;

import com.unir.backOperador.data.AlquilerRepository;
import com.unir.backOperador.model.request.Libro;

import jakarta.persistence.EntityNotFoundException;

import com.unir.backOperador.facade.LibrosFacade;
import com.unir.backOperador.model.pojo.Alquiler;
import com.unir.backOperador.model.request.CreateRequestAlquiler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlquilerServiceImpl implements AlquilerService {
    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private LibrosFacade librosFacade;

    @Override
    public Alquiler aliquilarLibro(CreateRequestAlquiler request) {
        log.info(" Elementos de entrada :  ", request);
        if (request != null && request.getUsuarioId() > 0
                && request.getLibroId() > 0
                && request.getCantidadDias() > 0) {
            Boolean bandera = buscarLibro(request.getLibroId());
            bandera = alquilarLibro(request.getLibroId());
            Alquiler save = new Alquiler();
            if (bandera) {
                Alquiler alquiler = Alquiler.builder().usuarioId(request.getUsuarioId())
                        .libroId(request.getLibroId()).cantidadDias(request.getCantidadDias())
                        .estado("V").build();
                save = alquilerRepository.save(alquiler);
            } else {
                save = null;
            }
            return save != null && save.getAlquilerId() > 0 ? save : null;
        } else {
            return null;
        }
    }

    @Override
    public List<Alquiler> findByLibro(Long liboId) {
        List<Alquiler> alquiler = alquilerRepository.findByLibroId(liboId);
        return alquiler.isEmpty() ? null : alquiler;
    }

    @Override
    public List<Alquiler> findByUsuario(Long userId) {
        List<Alquiler> alquilerUser = alquilerRepository.findByUsuarioId(userId);
        return alquilerUser.isEmpty() ? null : alquilerUser;
    }

    @Override
    public Alquiler devolverLibro(Long alquilerId) {
        log.info(" Elementos de entrada para devolver :  ", alquilerId);
        if (alquilerId != null && alquilerId > 0) {
            Alquiler devolver = alquilerRepository.findById(alquilerId)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "No se encontro el registro de alquiler con el id : " + String.valueOf(alquilerId)));
            if (devolver != null && !devolver.getEstado().equalsIgnoreCase("F")) {
                Boolean bandera = buscarLibro(devolver.getLibroId());
                bandera = retonarLibro(devolver.getLibroId());
                if (bandera) {
                    devolver.setEstado("F");
                    devolver.setFechaRealDevolucion(new Date());
                    devolver = alquilerRepository.save(devolver);
                } else {
                    devolver = null;
                }
            } else {
                devolver = null;
            }
            return devolver != null && devolver.getAlquilerId() > 0 ? devolver : null;
        } else {
            return null;
        }
    }

    private Boolean buscarLibro(Long libroId) {
        Libro libro = librosFacade.getLibroRest(String.valueOf(libroId));
        if (libro != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private Boolean alquilarLibro(Long libroId) {
        Libro libro = librosFacade.putAlquilarLibroRest(String.valueOf(libroId));
        if (libro != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private Boolean retonarLibro(Long libroId) {
        Libro libro = librosFacade.putDevolverLibroRest(String.valueOf(libroId));
        if (libro != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
