package com.unir.backOperador.model.pojo;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alquiler")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alquilerId")
    private Long alquilerId;
    @Column(name = "usuarioId")
    @NonNull
    private Long usuarioId;
    @Column(name = "libroId")
    @NonNull
    private Long libroId;
    @Column(name = "cantidadDias")
    @NonNull
    private Integer cantidadDias;
    @Column(name = "fechaRegistro")
    private Date fechaRegistro;
    @Column(name = "fechaFinAlquiler")
    private Date fechaFinAlquiler;

    @PrePersist
    private void PrePersist() {
        fechaRegistro = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaRegistro);
        calendar.add(Calendar.DAY_OF_YEAR, cantidadDias); // Sumar n días alquilados
        fechaFinAlquiler = calendar.getTime();
    }

}
