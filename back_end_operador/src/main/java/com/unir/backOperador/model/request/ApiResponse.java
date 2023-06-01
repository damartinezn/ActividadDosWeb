package com.unir.backOperador.model.request;

import org.springframework.http.HttpStatusCode;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse {
    private HttpStatusCode status;
    private String message;
}
