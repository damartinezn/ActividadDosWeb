package com.unir.backOperador.facade;


import com.unir.backOperador.model.request.Libro;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibrosFacade {

    @Value("${getLibro.url}")
    private String getLibrosUrl;

    @Value("${getLibro.urlAlquiler}")
    private String getLibroAlquilarUrl;

    @Value("${getLibro.urlDevolver}")
    private String getLibroDevolverUrl;

    private final RestTemplate restTemplate;

    public Libro getLibroRest(String id) {
        try {
            return restTemplate.getForObject(String.format(getLibrosUrl, id), Libro.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        }
    }

    public Libro putAlquilarLibroRest(String id) {
        try {
            log.info(" url  : {} ", String.format(getLibroAlquilarUrl, id));
            return restTemplate.getForObject(String.format(getLibroAlquilarUrl, id), Libro.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        }
    }

    public Libro putDevolverLibroRest(String id) {
        try {
            log.info(" url  : {} ", String.format(getLibroDevolverUrl, id));
            return restTemplate.getForObject(String.format(getLibroDevolverUrl, id), Libro.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        }
    }

}
