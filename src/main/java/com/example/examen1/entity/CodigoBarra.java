package com.example.examen1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "codigo_barra")
public class CodigoBarra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private boolean activo;
}
