package com.unir.backOperador.facade;

import com.unir.backOperador.model.request.Libro;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
            // Configurar el encabezado con el tipo de contenido
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Configurar el cuerpo de la solicitud PATCH
            String requestJson = "{}";
            // Crear la entidad HTTP con el encabezado y el cuerpo
            HttpEntity<Object> entity = new HttpEntity<>(requestJson, headers);
            // Realizar la solicitud PATCH
            ResponseEntity<Libro> response = restTemplate.exchange(
                    String.format(getLibroAlquilarUrl, id),
                    HttpMethod.PUT,
                    entity,
                    Libro.class);
            // Manejar la respuesta
            Libro responseBody = new Libro();
            if (response.getStatusCode().is2xxSuccessful()) {
                // La solicitud fue exitosa
                responseBody = response.getBody();
                // Procesar la respuesta
            } else {
                // La solicitud falló
                responseBody = null;
                // Manejar el error
            }
            return responseBody;
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        } catch (Exception e) {
            log.error("Client Error ", e.getStackTrace(), id);
            e.printStackTrace();
            return null;
        }
    }

    public Libro putDevolverLibroRest(String id) {
        try {
            log.info(" url  : {} ", String.format(getLibroDevolverUrl, id));
            // Configurar el encabezado con el tipo de contenido
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Configurar el cuerpo de la solicitud PATCH
            String requestJson = "{}";
            // Crear la entidad HTTP con el encabezado y el cuerpo
            HttpEntity<Object> entity = new HttpEntity<>(requestJson, headers);
            // Realizar la solicitud PATCH
            ResponseEntity<Libro> response = restTemplate.exchange(
                    String.format(getLibroDevolverUrl, id),
                    HttpMethod.PUT,
                    entity,
                    Libro.class);
            // Manejar la respuesta
            Libro responseBody = new Libro();
            if (response.getStatusCode().is2xxSuccessful()) {
                // La solicitud fue exitosa
                responseBody = response.getBody();
                // Procesar la respuesta
            } else {
                // La solicitud falló
                responseBody = null;
                // Manejar el error
            }
            return responseBody;
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Product with ID {}", e.getStatusCode(), id);
            return null;
        } catch (Exception e) {
            log.error("Client Error ", e.getStackTrace(), id);
            e.printStackTrace();
            return null;
        }
    }

}
