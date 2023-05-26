package com.unir.backOperador.service;



import com.unir.backOperador.data.AlquilerRepository;
import com.unir.backOperador.model.request.Libro;
import com.unir.backOperador.facade.LibrosFacade;
import com.unir.backOperador.model.pojo.Alquiler;
import com.unir.backOperador.model.request.CreateRequestAlquiler;
import lombok.extern.slf4j.Slf4j;
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
    public Long aliquilarLibro(CreateRequestAlquiler request) {
        log.info(" ---------------------------------------------------- : {} ", request);
        if (request != null && request.getUsuarioId() > 0
                && request.getLibroId() > 0
                && request.getCantidadDias() > 0) {
            Boolean bandera = buscarLibro(request.getLibroId());
            bandera = alquilarLibro(request.getLibroId());
            Alquiler save = new Alquiler();
            if (bandera) {
                Alquiler alquiler = Alquiler.builder().usuarioId(request.getUsuarioId())
                        .libroId(request.getLibroId()).cantidadDias(request.getCantidadDias()).build();
                save = alquilerRepository.save(alquiler);
            } else {
                save = null;
            }

            return save != null && save.getAlquilerId() > 0 ? save.getAlquilerId() : null;
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
        Libro libro = librosFacade.postAlquilarLibroRest(String.valueOf(libroId));
        if (libro != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
