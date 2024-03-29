package com.example.examen1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "categoria")
@Table(name = "categoria")

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String descripcion;
    private boolean activo;
}
