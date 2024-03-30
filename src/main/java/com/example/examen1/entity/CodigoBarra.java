package com.example.examen1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "codigo_barra")
@NoArgsConstructor
@AllArgsConstructor
public class CodigoBarra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String codigo;
    private boolean activo;
}
